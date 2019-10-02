package com.joymain.jecs.pm.webapp.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import service.MsgSendService;

import com.integracoreb2b.ArrayOfOrderResult;
import com.integracoreb2b.OrderResult;
import com.integracoreb2b.OrderResults;
import com.joymain.jecs.Constants;
import com.joymain.jecs.pm.model.JpmProduct;
import com.joymain.jecs.pm.model.JpmProductSaleErp;
import com.joymain.jecs.pm.model.JpmProductSaleNew;
import com.joymain.jecs.pm.service.JpmProductManager;
import com.joymain.jecs.pm.service.JpmProductSaleLogManager;
import com.joymain.jecs.pm.service.JpmProductSaleManager;
import com.joymain.jecs.pm.service.JpmProductSaleNewManager;
import com.joymain.jecs.sys.model.JocsInterfaceLog;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.ReportLogUtilService;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JpmProductFormController extends BaseFormController {
	private JpmProductSaleManager jpmProductSaleManager = null;
	private JpmProductSaleLogManager jpmProductSaleLogManager = null;
	private JpmProductSaleNewManager jpmProductSaleNewManager = null;
	public void setJpmProductSaleLogManager(
			JpmProductSaleLogManager jpmProductSaleLogManager) {
		this.jpmProductSaleLogManager = jpmProductSaleLogManager;
	}

	public void setJpmProductSaleManager(
			JpmProductSaleManager jpmProductSaleManager) {
		this.jpmProductSaleManager = jpmProductSaleManager;
	}

	private JpmProductManager jpmProductManager = null;

	public void setJpmProductManager(JpmProductManager jpmProductManager) {
		this.jpmProductManager = jpmProductManager;
	}

	public void setJpmProductSaleNewManager(
			JpmProductSaleNewManager jpmProductSaleNewManager) {
		this.jpmProductSaleNewManager = jpmProductSaleNewManager;
	}

	public JpmProductFormController() {
		setCommandName("jpmProduct");
		setCommandClass(JpmProduct.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		String productNo = request.getParameter("productNo");
		JpmProduct jpmProduct = null;

		if (!StringUtils.isEmpty(productNo)) {
			jpmProduct = jpmProductManager.getJpmProduct(productNo);
		}

		if (jpmProduct == null) {
			jpmProduct = new JpmProduct();
		}
		List companyList = jpmProductSaleManager
				.getAlCompanyNotSaled(productNo);
		request.setAttribute("companyList", companyList);

		return jpmProduct;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		JpmProduct jpmProduct = (JpmProduct) command;
		String companySelect = request.getParameter("companySelect");
		

//		boolean isNew = (jpmProduct.getProductNo() == null);
//		Locale locale = request.getLocale();
		String key = null;
		String strAction = request.getParameter("strAction");
		String view = "redirect:jpmProducts.html?strAction="+strAction;
		if ("addJpmProduct".equals(strAction)) {
			key = "pmProduct.add";
			// createCharacterKey(pmProduct);
			jpmProductManager.saveJpmProduct(jpmProduct);
			//WuCF JpmProductSaleNew Modify By WuCF 20130917
			createPmProductSale(jpmProduct);

			//默认会处理生成CN区的商品销售表的数据
			if(companySelect==null || "".equals(companySelect)){
				companySelect = "CN";
			}
			createPmProductSales(companySelect, jpmProduct,request);
			
			// StartupListener.setupContext(request.getSession().getServletContext(),true);
		} else if ("editJpmProduct".equals(strAction)) {
			key = "pmProduct.update";
			jpmProductManager.saveJpmProduct(jpmProduct);
		}

		SysUser loginUser = SessionLogin.getLoginUser(request);
		//==========================Modify By WuCF 20160504 合规第二期代码迁移接口
		//获得登录账号对象(主要获得当前登录账号的所属国别地区) 
		SysUser loginSysUser = SessionLogin.getLoginUser(request);
		//获取接口开关标示
		String swtichSend = ListUtil.getListValue(loginSysUser.getCompanyCode().toUpperCase(), "interface.sendswitch", "goods.add");
		
		String FILE_URL = ListUtil.getListValue(loginUser.getCompanyCode(), "jpmproductsaleimage.url", "1")+"productNew/";
		JpmProductSaleErp jpmProductSaleErp = new JpmProductSaleErp();
		if ("addJpmProduct".equals(strAction)) {
			jpmProductSaleErp.setPRODUCT_NO(jpmProduct.getProductNo());//EC编码
			jpmProductSaleErp.setPRODUCT_CATEGORY(jpmProduct.getProductCategory());//商品类别
			jpmProductSaleErp.setERP_PRODUCT_NO(jpmProduct.getErpProductNo());//ERP编码
			jpmProductSaleErp.setUNIT_NO(jpmProduct.getUnitNo());//单位
			jpmProductSaleErp.setPRODUCT_STYLE("");//规格:默认为空，目前只有美体才有
			jpmProductSaleErp.setPRODUCT_SIZE("");//尺寸
			jpmProductSaleErp.setCOMPANY_CODE(loginUser.getCompanyCode());//默认:CN中国区
			jpmProductSaleErp.setPRODUCT_NAME(jpmProduct.getProductName());//名称
			jpmProductSaleErp.setSTATUS("0");//销售状态，新增默认为0
			
			jpmProductSaleErp.setREMARK("");//备注
			jpmProductSaleErp.setWHO_PRICE(String.valueOf(jpmProduct.getErpPrice()));//ERP价格
			jpmProductSaleErp.setPRODUCT_DESC(jpmProduct.getProductDesc());
			jpmProductSaleErp.setSTART_ON_SALE("");//起始时间
			jpmProductSaleErp.setEND_ON_SALE("");//截止时间
			jpmProductSaleErp.setHOT_FLAG("0");//是否热卖：默认0 否
			jpmProductSaleErp.setSORT_FLAG("");//排序
			DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			jpmProductSaleErp.setCREATE_TIME(f.format(new Date()));//当前时间====
			jpmProductSaleErp.setBRIEF_INTRODUCTION("");//简介
			jpmProductSaleErp.setHEALTH_KNOWLEDGE("");//健康知识
			jpmProductSaleErp.setPRODUCT_SPECIFICATION("");//规格
			jpmProductSaleErp.setSIMG_LINK(FILE_URL+"productNew/nopic1.jpg");//初次新增默认小图
			jpmProductSaleErp.setCIMG_LINK(FILE_URL+"productNew/nopic2.jpg");//初次新增默认中图
			jpmProductSaleErp.setBIMG_LINK(FILE_URL+"productNew/nopic3.jpg");//初次新增默认大图
			
			//----------------------Modify By WuCF 20150105接口数据交互
			JSONObject jsonObject = JSONObject.fromObject(jpmProductSaleErp);
			//将json对象转换成json字符串
			String returnnoJsonString = jsonObject.toString();
			
			//调用发送接口---------------------------开始
			MsgSendService msgSendService = new MsgSendService();
			msgSendService.setSender(Constants.QU_SEND);
			//方法名
			String methodName = "goods.add";
			//modify gw 2015-03-03 捕获异常-修改bug
			try{
				log.info("goods.add(jpmpProductFormAdd)："+returnnoJsonString.toString());
				if("Y".equals(swtichSend)){//Modify By WuCF 20160504 开关判断
					String aa = msgSendService.post(returnnoJsonString.toString(), methodName);
					//调用发送接口---------------------------结束
					
					//=============================接口日志写入开始
					JocsInterfaceLog jocsInterfaceLog = new JocsInterfaceLog();
					jocsInterfaceLog.setLogType("0");//0：本地发送数据    1：本地接收数据
					jocsInterfaceLog.setSender(Constants.QU_SEND);//数据的接收方
					jocsInterfaceLog.setMethod(methodName);//方法名称
					jocsInterfaceLog.setContent(returnnoJsonString.toString());//发送内容content
					jocsInterfaceLog.setReturnDesc(aa);//返回内容
					
					ReportLogUtilService.addJocsInterface(jocsInterfaceLog);//写入日志操作
					//=============================接口日志写入完毕
				}
			}catch(Exception e){
				e.printStackTrace();
				log.debug(e.toString());
			}
		} else if ("editJpmProduct".equals(strAction)) {

			/*jpmProductSaleErp.setPRODUCT_NO(jpmProduct.getProductNo());//EC编码
			jpmProductSaleErp.setCOMPANY_CODE(loginUser.getCompanyCode());//默认:CN中国区
			jpmProductSaleErp.setPRODUCT_CATEGORY(jpmProduct.getProductCategory());//商品类别
			jpmProductSaleErp.setERP_PRODUCT_NO(jpmProduct.getErpProductNo());//ERP编码
			jpmProductSaleErp.setUNIT_NO(jpmProduct.getUnitNo());//单位
//			jpmProductSaleErp.setPRODUCT_NAME(jpmProduct.getProductName());//名称
*/			
			//Modify By WuCF 20160506 校验ERP_PRODUCT_NO是否合法，目前暂时屏蔽
			// 解析soap
			/*JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			//获取wsdl地址
			org.apache.cxf.endpoint.Client client = dcf.createClient("http://www.integracoreb2b.com/IntCore/IncomingOrders.asmx?wsdl");
			
			Object[] reply = null;
			// 返回的结果
			reply = client.invoke("OrderImport", new Object[] { jpmProduct.getErpProductNo() });
			System.out.println("reply:"+reply);
			if (reply != null) {
				OrderResults results = (OrderResults) reply[0];
				if (results != null) {
					System.out.println("total：" + results.getError());
					;
					ArrayOfOrderResult orderMessage = results.getOrderMessage();
					List<OrderResult> orderResult = orderMessage
							.getOrderResult();
					for (OrderResult result : orderResult) {
						System.out.println(result.getOrderNumber() + " - "
										+ result.getError() + '-'
										+ result.isAccepted());
					}
				}
			}*/
			
			//----------------------Modify By WuCF 20150105将java对象转换成json对象
			StringBuffer returnnoJsonString = new StringBuffer("");
			returnnoJsonString.append("{\"PRODUCT_NO\":\"");
			returnnoJsonString.append(jpmProduct.getProductNo());
			returnnoJsonString.append("\",\"PRODUCT_CATEGORY\":\"");
			returnnoJsonString.append(jpmProduct.getProductCategory());
			returnnoJsonString.append("\",\"COMPANY_CODE\":\"");
			returnnoJsonString.append(loginUser.getCompanyCode());
			returnnoJsonString.append("\",\"ERP_PRODUCT_NO\":\"");
			returnnoJsonString.append(jpmProduct.getErpProductNo());
			returnnoJsonString.append("\",\"WHO_PRICE\":\"");
			returnnoJsonString.append(jpmProduct.getErpPrice());
			returnnoJsonString.append("\"}");
			//----------------------Modify By WuCF 20150105接口数据交互
			/*JSONObject jsonObject = JSONObject.fromObject(jpmProductSaleErp);
			//将json对象转换成json字符串
			String returnnoJsonString = jsonObject.toString();*/
			
			//调用发送接口---------------------------开始
			MsgSendService msgSendService = new MsgSendService();
			msgSendService.setSender(Constants.QU_SEND);
			//方法名
			String methodName = "goods.add";
			//modify gw 2015-03-03 捕获异常-修改bug
			try{
				log.info("goods.add(jpmpProductFormEdit)："+returnnoJsonString.toString());
				if("Y".equals(swtichSend)){//Modify By WuCF 20160504 开关判断
					String aa = msgSendService.post(returnnoJsonString.toString(), methodName);
					//调用发送接口---------------------------结束
					
					//=============================接口日志写入开始
					JocsInterfaceLog jocsInterfaceLog = new JocsInterfaceLog();
					jocsInterfaceLog.setLogType("0");//0：本地发送数据    1：本地接收数据
					jocsInterfaceLog.setSender(Constants.QU_SEND);//数据的接收方
					jocsInterfaceLog.setMethod(methodName);//方法名称
					jocsInterfaceLog.setContent(returnnoJsonString.toString());//发送内容content
					jocsInterfaceLog.setReturnDesc(aa);//返回内容
					
					ReportLogUtilService.addJocsInterface(jocsInterfaceLog);//写入日志操作
					//=============================接口日志写入完毕
				}
			}catch(Exception e){
				e.printStackTrace();
				log.debug(e.toString());
			}
		}
		
		saveMessage(request, getText(SessionLogin.getLoginUser(request)
				.getDefCharacterCoding(), key));

		return new ModelAndView(view);
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		// binder.setAllowedFields(allowedFields);
		// binder.setDisallowedFields(disallowedFields);
		// binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}

	/**
	 * 建分公司条目
	 * 
	 * @param companySelect
	 * @param pmProduct
	 */
	private void createPmProductSales(String companySelect, JpmProduct pmProduct,HttpServletRequest request) {
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		if (StringUtils.isNotEmpty(companySelect)) {
			String[] companys = companySelect.split(",");
			for (int i = 0; i < companys.length; i++) {
				//WuCF JpmProductSaleNew Modify By WuCF 20130917
				JpmProductSaleNew jpmProductSaleNew = new JpmProductSaleNew();
				jpmProductSaleNew.setJpmProduct(pmProduct);
				jpmProductSaleNew.setCompanyCode(companys[i]);
				jpmProductSaleNew.setProductName(pmProduct.getProductName());
				jpmProductSaleNew.setProductDesc(pmProduct.getProductDesc());
				jpmProductSaleNew.setStatus("0");
				jpmProductSaleNew.setCreateUserCode(sessionLogin.getUserCode());//创建者和创建时间
				jpmProductSaleNew.setCreateTime(new Date());
				jpmProductSaleNewManager.saveJpmProductSaleNew(jpmProductSaleNew);
//				saveLog(jpmProductSale,request); 
			}
		}

	}

	//WuCF JpmProductSaleNew Modify By WuCF 20130917
	/*private void saveLog(JpmProductSaleNew jpmProductSale,HttpServletRequest request){
		try {
			JpmProductSaleLog jpmProductSaleLog = jpmProductSaleLogManager.getJpmProductSaleToLog(jpmProductSale);
			jpmProductSaleLog.setEditTime(new Date());
			jpmProductSaleLog.setEditUserCode(SessionLogin.getLoginUser(request).getUserCode());
			jpmProductSaleLogManager.saveJpmProductSaleLog(jpmProductSaleLog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		} 
	}*/
	
	//WuCF JpmProductSaleNew Modify By WuCF 20130917
	private void createPmProductSale(JpmProduct pmProduct) {
		// TODO Auto-generated method stub
		JpmProductSaleNew pmProductSale = new JpmProductSaleNew();
		pmProductSale.setJpmProduct(pmProduct);
		pmProductSale.setCompanyCode("AA");
		pmProductSale.setProductName(pmProduct.getProductName());
		pmProductSale.setProductDesc(pmProduct.getProductDesc());
		jpmProductSaleNewManager.saveJpmProductSaleNew(pmProductSale);

	}
}
