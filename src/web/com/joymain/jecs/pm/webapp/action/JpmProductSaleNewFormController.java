package com.joymain.jecs.pm.webapp.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import service.MsgSendService;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pm.model.JpmCouponRelationship;
import com.joymain.jecs.pm.model.JpmProduct;
import com.joymain.jecs.pm.model.JpmProductSaleErp;
import com.joymain.jecs.pm.model.JpmProductSaleNew;
import com.joymain.jecs.pm.service.JpmProductManager;
import com.joymain.jecs.pm.service.JpmProductSaleNewManager;
import com.joymain.jecs.sys.model.JocsInterfaceLog;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.ReportLogUtilService;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JpmProductSaleNewFormController extends BaseFormController {
	private JpmProductSaleNewManager jpmProductSaleNewManager = null;
	private JpmProductManager jpmProductManager = null;
	
	public JpmProductManager getJpmProductManager() {
		return jpmProductManager;
	}
	public void setJpmProductManager(JpmProductManager jpmProductManager) {
		this.jpmProductManager = jpmProductManager;
	}
	public JpmProductSaleNewManager getJpmProductSaleNewManager() {
		return jpmProductSaleNewManager;
	}
	public void setJpmProductSaleNewManager(JpmProductSaleNewManager jpmProductSaleNewManager) {
		this.jpmProductSaleNewManager = jpmProductSaleNewManager;
	}
	public JpmProductSaleNewFormController() {
		setCommandName("jpmProductSaleNew");
		setCommandClass(JpmProductSaleNew.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
	throws Exception {
		String uniNo = request.getParameter("uniNo");
		JpmProductSaleNew jpmProductSaleNew = null;

		if (!StringUtils.isEmpty(uniNo)) {
			jpmProductSaleNew = jpmProductSaleNewManager.getJpmProductSaleNew(uniNo);
			Set<JpmCouponRelationship> set = jpmProductSaleNew.getJpmCouponRelationships();
			System.out.println("set:"+set);
			for(JpmCouponRelationship jcrs :set){
				System.out.println("========"+jcrs);
				System.out.println(jcrs.getJpmCouponInfo());
				System.out.println(jcrs.getJpmCouponInfo().getCpId());
			}
		} else {
			jpmProductSaleNew = new JpmProductSaleNew();
		}
		
		boolean disabled = false; 
		String strAction = request.getParameter("strAction");
		if ("confirmJpmProductSaleNew".equals(strAction)) {
			disabled = true;
		}else if("editpmProductSaleNew".equals(strAction)){
			disabled = true;
		}else if("editStorageCordon".equals(strAction)){
			disabled = true;
		}
		       
		//如果不是全球，则设置区域
		SysUser loginUser = SessionLogin.getLoginUser(request);
		if (!"AA".equalsIgnoreCase(loginUser.getCompanyCode())) {
			jpmProductSaleNew.setCompanyCode(loginUser.getCompanyCode());

		} 
		request.setAttribute("disabled", disabled);
		request.setAttribute("strAction", strAction);
		return jpmProductSaleNew;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command,
			BindException errors)
	throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
		JpmProduct pmProduct = new JpmProduct(); 
		JpmProductSaleNew jpmProductSaleNew = (JpmProductSaleNew) command;
		
		//隐藏掉批发价,折扣价,因为下面做了诸多校验,那么就在这里给他们赋初值---add by gw ----begin
		BigDecimal price = new BigDecimal(0);
		jpmProductSaleNew.setWhoPrice(price);
		jpmProductSaleNew.setDiscountPrice(price);
		//隐藏掉批发价,折扣价,因为下面做了诸多校验,那么就在这里给他们赋初值---add by gw ----end
		
		SysUser loginUser = SessionLogin.getLoginUser(request);
		
		//==========================Modify By WuCF 20160504 合规第二期代码迁移接口
		//获取接口开关标示
		String swtichSend = ListUtil.getListValue(loginUser.getCompanyCode().toUpperCase(), "interface.sendswitch", "goods.add");
		
		String view = "redirect:jpmProductSaleNews.html?strAction=editJpmProductSaleNew";
		String key = null;
		String strAction = request.getParameter("strAction");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if ("addJpmProductSaleNew".equals(strAction)) {
			jpmProductSaleNew.setConfirm("0");
			jpmProductSaleNew.setStatus("0");
			
			//如果未选择商品编号
        	if(StringUtils.isEmpty(String.valueOf(jpmProductSaleNew.getJpmProduct().getProductNo()))
        		|| (StringUtils.isNotEmpty(String.valueOf(jpmProductSaleNew.getJpmProduct().getProductNo())) 
        				&& "null".equals(String.valueOf(jpmProductSaleNew.getJpmProduct().getProductNo())))){ 
        		errors.reject("piProductNew.uncheck.unino", new Object[] {},LocaleUtil.getLocalText("piProductNew.uncheck.unino"));
				return showForm(request, response, errors);
        	}
        	
        	//是否填写批发价并满足是数字
        	if(!StringUtils.isNumeric(String.valueOf(jpmProductSaleNew.getWhoPrice()))){ 
        		errors.reject("piProductNew.uncheck.whoprice", new Object[] {},LocaleUtil.getLocalText("piProductNew.uncheck.unino"));
				return showForm(request, response, errors);
        	}
        	 
        	//是否填写折扣价并满足是数字
        	if(!StringUtils.isNumeric(String.valueOf(jpmProductSaleNew.getDiscountPrice()))){ 
        		errors.reject("piProductNew.uncheck.discountprice", new Object[] {},LocaleUtil.getLocalText("piProductNew.uncheck.unino"));
				return showForm(request, response, errors);
        	} 
			
			//判断商品是否已经存在
			pmProduct = jpmProductManager.getJpmProduct(jpmProductSaleNew.getJpmProduct().getProductNo());
        	if(jpmProductSaleNewManager.existProductNo(jpmProductSaleNew.getCompanyCode(), pmProduct.getProductNo())){
        		errors.reject("piProductNew.exits", new Object[] {},LocaleUtil.getLocalText("piProductNew.exits"));
				return showForm(request, response, errors);
        	} 
        	 
        	jpmProductSaleNew.setJpmProduct(pmProduct);
			jpmProductSaleNew.setCreateUserCode(loginUser.getUserCode()); 
        	jpmProductSaleNew.setCreateTime(new Date());
        	if(StringUtils.isEmpty(jpmProductSaleNew.getCompanyCode())){
        		jpmProductSaleNew.setCompanyCode("CN");//如果没有选择国际分区，则默认为CN
        	} 
        	
        	//保存商品的时候，保存和代金券的绑定关系
        	bindJpmCoupon(request, jpmProductSaleNew);
        	 
			jpmProductSaleNewManager.saveJpmProductSaleNew(jpmProductSaleNew); 
			key="jpmProductSaleNew.add";
			
			//==========================Modify By WuCF OMS接口 20141112
			JpmProductSaleErp jpmProductSaleErp = new JpmProductSaleErp();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//UNDO
			jpmProductSaleErp.setPRODUCT_NO(jpmProductSaleNew.getJpmProduct().getProductNo());//商品编码
			jpmProductSaleErp.setERP_PRODUCT_NO(jpmProductSaleNew.getJpmProduct().getErpProductNo());//ERP编码
			jpmProductSaleErp.setSTATUS(jpmProductSaleNew.getStatus());//商品状态
			jpmProductSaleErp.setWHO_PRICE(String.valueOf(jpmProductSaleNew.getJpmProduct().getErpPrice()));//价格取值ERP价格
			jpmProductSaleErp.setPRODUCT_NAME(jpmProductSaleNew.getProductName());//商品名称
			if(jpmProductSaleNew.getStartOnSale()!=null && !"".equals(jpmProductSaleNew.getStartOnSale())){
				jpmProductSaleErp.setSTART_ON_SALE(sdf.format(jpmProductSaleNew.getStartOnSale())+" 00:00:00");//起始时间
			}
			if(jpmProductSaleErp.getEND_ON_SALE()!=null && !"".equals(jpmProductSaleErp.getEND_ON_SALE())){
				jpmProductSaleErp.setEND_ON_SALE(sdf.format(jpmProductSaleNew.getEndOnSale())+" 23:59:59");//截止时间
			}
			jpmProductSaleErp.setHOT_FLAG(jpmProductSaleNew.getHotFlag());//是否热卖
			jpmProductSaleErp.setSORT_FLAG(String.valueOf(jpmProductSaleNew.getSortFlag()));//排序号
			jpmProductSaleErp.setCREATE_TIME(format.format(new Date()));//创建时间
			jpmProductSaleErp.setBRIEF_INTRODUCTION(jpmProductSaleNew.getBriefIntroduction());////简介
			jpmProductSaleErp.setHEALTH_KNOWLEDGE(jpmProductSaleNew.getHealthKnowledge());//健康知识
			jpmProductSaleErp.setPRODUCT_SPECIFICATION(jpmProductSaleNew.getProductSpecification());//规格		
			jpmProductSaleErp.setCOMPANY_CODE(loginUser.getCompanyCode().toUpperCase());//国别
			//----------------------Modify By WuCF 20150105接口数据交互
			JSONObject jsonObject = JSONObject.fromObject(jpmProductSaleErp);
			//将json对象转换成json字符串
			String returnnoJsonString = jsonObject.toString();
			
			//调用发送接口---------------------------开始
			MsgSendService msgSendService = new MsgSendService();
			msgSendService.setSender(Constants.QU_SEND);
			//方法名
			String methodName = "goods.add";
			log.info("goods.add(jpmpProductSaleNewFormAdd)："+jsonObject);
			if("Y".equals(swtichSend)){//Modify By WuCF 20160504 开关判断
				String aa = msgSendService.post(returnnoJsonString, methodName);
				
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
			//调用发送接口---------------------------结束
		}else if ("deleteJpmProductSaleNew".equals(strAction)  ) {
			jpmProductSaleNewManager.removeJpmProductSaleNew(jpmProductSaleNew.getUniNo().toString());
			key="jpmProductSaleNew.delete";
		}else if("editJpmProductSaleNew".equals(strAction)){ 
			//是否填写批发价并满足是数字
        	if(!StringUtils.isNumeric(String.valueOf(jpmProductSaleNew.getWhoPrice()))){ 
        		errors.reject("piProductNew.uncheck.whoprice", new Object[] {},LocaleUtil.getLocalText("piProductNew.uncheck.unino"));
				return showForm(request, response, errors);
        	} 
        	 
        	//是否填写折扣价并满足是数字
        	if(!StringUtils.isNumeric(String.valueOf(jpmProductSaleNew.getDiscountPrice()))){ 
        		errors.reject("piProductNew.uncheck.discountprice", new Object[] {},LocaleUtil.getLocalText("piProductNew.uncheck.unino"));
				return showForm(request, response, errors);
        	}
			
        	//修改人
			jpmProductSaleNew.setUpdateUserCode(loginUser.getUserCode());
			jpmProductSaleNew.setUpdateTime(new Date());
		   	//保存商品的时候，保存和代金券的绑定关系
        	bindJpmCoupon(request, jpmProductSaleNew);
			
			jpmProductSaleNewManager.saveJpmProductSaleNew(jpmProductSaleNew);
			key="jpmProductSaleNew.update";
			
			//==========================Modify By WuCF OMS接口 20141112
			JpmProductSaleErp jpmProductSaleErp = new JpmProductSaleErp();
			//UNDO
			jpmProductSaleErp.setPRODUCT_NO(jpmProductSaleNew.getJpmProduct().getProductNo());//商品编码
			jpmProductSaleErp.setERP_PRODUCT_NO(jpmProductSaleNew.getJpmProduct().getErpProductNo());//ERP编码
			jpmProductSaleErp.setSTATUS(jpmProductSaleNew.getStatus());//商品状态
			jpmProductSaleErp.setWHO_PRICE(String.valueOf(jpmProductSaleNew.getJpmProduct().getErpPrice()));//价格取值ERP价格
			jpmProductSaleErp.setPRODUCT_NAME(jpmProductSaleNew.getProductName());//商品名称
			if(jpmProductSaleNew.getStartOnSale()!=null && !"".equals(jpmProductSaleNew.getStartOnSale())){
				jpmProductSaleErp.setSTART_ON_SALE(sdf.format(jpmProductSaleNew.getStartOnSale())+" 00:00:00");//起始时间
			}
			if(jpmProductSaleErp.getEND_ON_SALE()!=null && !"".equals(jpmProductSaleErp.getEND_ON_SALE())){
				jpmProductSaleErp.setEND_ON_SALE(sdf.format(jpmProductSaleNew.getEndOnSale())+" 23:59:59");//截止时间
			}
			jpmProductSaleErp.setHOT_FLAG(jpmProductSaleNew.getHotFlag());//是否热卖
			jpmProductSaleErp.setSORT_FLAG(String.valueOf(jpmProductSaleNew.getSortFlag()));//排序号
			jpmProductSaleErp.setBRIEF_INTRODUCTION(jpmProductSaleNew.getBriefIntroduction());////简介
			jpmProductSaleErp.setHEALTH_KNOWLEDGE(jpmProductSaleNew.getHealthKnowledge());//健康知识
			jpmProductSaleErp.setPRODUCT_SPECIFICATION(jpmProductSaleNew.getProductSpecification());//规格	
			jpmProductSaleErp.setCOMPANY_CODE(loginUser.getCompanyCode().toUpperCase());//国别
			
			/*StringBuffer returnnoJsonString = new StringBuffer("");
			returnnoJsonString.append("{\"ERP_PRODUCT_NO\":\"");
			returnnoJsonString.append(jpmProductSaleNew.getJpmProduct().getErpProductNo());
			returnnoJsonString.append("\",\"PRODUCT_NO\":\"");
			returnnoJsonString.append(jpmProductSaleNew.getJpmProduct().getProductNo());
			returnnoJsonString.append("\",\"PRODUCT_NAME\":\"");
			returnnoJsonString.append(jpmProductSaleNew.getProductName());
			if(jpmProductSaleNew.getStartOnSale()!=null && !"".equals(jpmProductSaleNew.getStartOnSale())){
				returnnoJsonString.append("\",\"START_ON_SALE\":\"");
				returnnoJsonString.append(String.valueOf(jpmProductSaleNew.getStartOnSale()+" 00:00:00"));
			}
			if(jpmProductSaleErp.getEND_ON_SALE()!=null && !"".equals(jpmProductSaleErp.getEND_ON_SALE())){
				returnnoJsonString.append("\",\"END_ON_SALE\":\"");
				returnnoJsonString.append(String.valueOf(jpmProductSaleNew.getEndOnSale()+" 23:59:59"));
			}
			returnnoJsonString.append("\",\"HOT_FLAG\":\"");
			returnnoJsonString.append(jpmProductSaleNew.getHotFlag());
			returnnoJsonString.append("\",\"SORT_FLAG\":\"");
			returnnoJsonString.append(jpmProductSaleNew.getSortFlag());
			returnnoJsonString.append("\",\"BRIEF_INTRODUCTION\":\"");
			returnnoJsonString.append(jpmProductSaleNew.getBriefIntroduction());
			returnnoJsonString.append("\",\"HEALTH_KNOWLEDGE\":\"");
			returnnoJsonString.append(jpmProductSaleNew.getHealthKnowledge());
			returnnoJsonString.append("\",\"PRODUCT_SPECIFICATION\":\"");
			returnnoJsonString.append(jpmProductSaleNew.getProductSpecification());
			returnnoJsonString.append("\",\"PRODUCT_DESC\":\"");
			returnnoJsonString.append(jpmProductSaleNew.getProductDesc());
			returnnoJsonString.append("\",\"REMARK\":\"");
			returnnoJsonString.append(jpmProductSaleNew.getRemark());
			
			returnnoJsonString.append("\"}");*/
			
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
				log.info("goods.add(jpmpProductSaleNewFormEdit)："+jsonObject);
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
		}else if("confirmJpmProductSaleNew".equals(strAction)){
//			jpmProductSaleNew.setStatus("1"); 
			view = "redirect:jpmProductSaleNews.html?strAction=confirmJpmProductSaleNew";
			key="jpmProductSaleNew.batchAudit"; 
			
			//修改人
			jpmProductSaleNew.setUpdateUserCode(loginUser.getUserCode());
			jpmProductSaleNew.setUpdateTime(new Date());
		   	//保存商品的时候，保存和代金券的绑定关系
        	bindJpmCoupon(request, jpmProductSaleNew);
			jpmProductSaleNewManager.saveJpmProductSaleNew(jpmProductSaleNew);
			
			//==========================Modify By WuCF OMS接口 20141110
			String productNos = jpmProductSaleNewManager.getProductNos(String.valueOf(jpmProductSaleNew.getUniNo()));//商品编码集合字符串 
			String status = jpmProductSaleNew.getStatus();//状态
			
			//----------------------Modify By WuCF 20150105接口数据交互
			StringBuffer returnnoJsonString = new StringBuffer("");
			returnnoJsonString.append("{\"PRODUCT_NOS\":\"");
			returnnoJsonString.append(productNos);
			returnnoJsonString.append("\",\"STATUS\":\"");
			returnnoJsonString.append(status);
			returnnoJsonString.append("\"}");
			//调用发送接口---------------------------开始
			MsgSendService msgSendService = new MsgSendService();
			msgSendService.setSender(Constants.QU_SEND);//渠道平台
			
			//方法名
			String methodName = "goods.batch_verifier";
			//modify gw 2015-03-03 捕获异常-修改bug
			try{
				swtichSend = ListUtil.getListValue(loginUser.getCompanyCode().toUpperCase(), "interface.sendswitch", "goods.batch_verifier");
				
				log.info("goods.batch_verifier(jpmpProductSaleNewFormConfirm)："+returnnoJsonString.toString());
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
		}else if("editStorageCordon".equals(strAction)){
			//是否填写批发价并满足是数字
        	if(!StringUtils.isNumeric(String.valueOf(jpmProductSaleNew.getWhoPrice()))){ 
        		errors.reject("piProductNew.uncheck.whoprice", new Object[] {},LocaleUtil.getLocalText("piProductNew.uncheck.unino"));
				return showForm(request, response, errors);
        	} 
        	 
        	//是否填写折扣价并满足是数字
        	if(!StringUtils.isNumeric(String.valueOf(jpmProductSaleNew.getDiscountPrice()))){ 
        		errors.reject("piProductNew.uncheck.discountprice", new Object[] {},LocaleUtil.getLocalText("piProductNew.uncheck.unino"));
				return showForm(request, response, errors);
        	}
			
        	//修改人
			jpmProductSaleNew.setUpdateUserCode(loginUser.getUserCode());
			jpmProductSaleNew.setUpdateTime(new Date());
        	
			jpmProductSaleNewManager.saveJpmProductSaleNew(jpmProductSaleNew);
			key="jpmProductSaleNew.update";
			view = "redirect:jpmProductSaleNews.html?strAction=editStorageCordon";
		}

		saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(), key));  
		return new ModelAndView(view); 
	}
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		String view = "";
		String key = "";
		String uniNoStr = request.getParameter("uniNoStr");//获取多选字符串的uniNoStr
		String status2 = request.getParameter("status2");//修改的状态
		String productNo = request.getParameter("productNo");//获取多选字符串的uniNoStr
		request.setAttribute("uniNoStr", uniNoStr);
		request.setAttribute("status2", status2);
		request.setAttribute("productNo", productNo); 
		if(StringUtils.isNotEmpty(uniNoStr) && !"null".equals(uniNoStr)){//批量审核处理
			view = "redirect:jpmProductSaleNews.html?strAction=confirmJpmProductSaleNew";
			key="jpmProductSaleNew.batchAudit";
			saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(), key));  
			return ; 
		}else{
			super.initBinder(request, binder);
		}
	}
	
	
	private void bindJpmCoupon(HttpServletRequest request,JpmProductSaleNew jpmProductSaleNew){
		String cpIdStr = request.getParameter("cpId");
		if (cpIdStr.equals("")) {
			Set<JpmCouponRelationship> set = jpmProductSaleNew.getJpmCouponRelationships();
			System.out.println("set:"+set);
			for(JpmCouponRelationship jcrs :set){
				System.out.println(jcrs.getJpmCouponInfo().getCpId());
				cpIdStr = jcrs.getJpmCouponInfo().getCpId().toString();
			}
		}
		Long cpId = new Long("0");
		if(!StringUtil.blankOrNull(cpIdStr)){
			cpId = new Long(cpIdStr);
		}
		Long uniNo = jpmProductSaleNew.getUniNo();
		Date createTime = new Date();
		
		SysUser loginUser = SessionLogin.getLoginUser(request);
		String createUserCode = loginUser.getUserCode();
		
		JpmCouponRelationship jpmCouponRelationship = new JpmCouponRelationship(cpId, uniNo, createTime, createUserCode);
		
		
		//保存代金券的相关信息
		
		Set<JpmCouponRelationship> jpmCpuponRelationships = jpmProductSaleNew.getJpmCouponRelationships();
		if(jpmCpuponRelationships != null){
			
			jpmCpuponRelationships.clear();//每次编辑时清空，业务上按一对一的设计
		}
		
		
		if(cpId != 0){
			jpmCpuponRelationships.add(jpmCouponRelationship);
			
		}
		
		//jpmCouponRelationshipManager.saveJpmCouponRelationship(jpmCouponRelationship);
	}
	private void bindJpmCoupon2(HttpServletRequest request,JpmProductSaleNew jpmProductSaleNew){
		String cpIdStr = "";
		Set<JpmCouponRelationship> set = jpmProductSaleNew.getJpmCouponRelationships();
		System.out.println("set:"+set);
		for(JpmCouponRelationship jcrs :set){
			System.out.println(jcrs.getJpmCouponInfo().getCpId());
			cpIdStr = jcrs.getJpmCouponInfo().getCpId().toString();
		}
		//String cpIdStr = request.getParameter("cpId");
		Long cpId = new Long("0");
		if(!StringUtil.blankOrNull(cpIdStr)){
			cpId = new Long(cpIdStr);
		}
		Long uniNo = jpmProductSaleNew.getUniNo();
		Date createTime = new Date();
		
		SysUser loginUser = SessionLogin.getLoginUser(request);
		String createUserCode = loginUser.getUserCode();
		
		JpmCouponRelationship jpmCouponRelationship = new JpmCouponRelationship(cpId, uniNo, createTime, createUserCode);
		
		
		//保存代金券的相关信息
		
		Set<JpmCouponRelationship> jpmCpuponRelationships = jpmProductSaleNew.getJpmCouponRelationships();
		if(jpmCpuponRelationships != null){
			
			jpmCpuponRelationships.clear();//每次编辑时清空，业务上按一对一的设计
		}
		
		
		if(cpId != 0){
			jpmCpuponRelationships.add(jpmCouponRelationship);
			
		}
		
		//jpmCouponRelationshipManager.saveJpmCouponRelationship(jpmCouponRelationship);
	}

}
