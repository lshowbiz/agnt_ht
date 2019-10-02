package com.joymain.jecs.pm.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import service.MsgSendService;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pm.model.JpmProductSaleNew;
import com.joymain.jecs.pm.service.JpmProductSaleNewManager;
import com.joymain.jecs.sys.model.JocsInterfaceLog;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.ReportLogUtilService;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JpmProductSaleNewController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(JpmProductSaleNewController.class);
	private JpmProductSaleNewManager jpmProductSaleNewManager = null;

	public void setJpmProductSaleNewManager(JpmProductSaleNewManager jpmProductSaleNewManager) {
		this.jpmProductSaleNewManager = jpmProductSaleNewManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		JpmProductSaleNew jpmProductSaleNew = new JpmProductSaleNew();
		// populate object with request parameters
		BeanUtils.populate(jpmProductSaleNew, request.getParameterMap());
		String strAction = request.getParameter("strAction");
		//List jpmProductSaleNews = jpmProductSaleNewManager.getJpmProductSaleNews(jpmProductSaleNew);
		/**** auto pagination ***/
		CommonRecord crm=RequestUtil.toCommonRecord(request);
		Pager pager = new Pager("jpmProductSaleNewListTable",request, 20);
		crm=initCommonRecord(request);//存放request中的值例如：strAction
		
		//==========================Modify By WuCF 20160504 合规第二期代码迁移接口
		//获得登录账号对象(主要获得当前登录账号的所属国别地区) 
		SysUser loginSysUser = SessionLogin.getLoginUser(request);
		//获取接口开关标示
		String swtichSend = ListUtil.getListValue(loginSysUser.getCompanyCode().toUpperCase(), "interface.sendswitch", "goods.batch_verifier");
		
		String uniNoStr = request.getParameter("uniNoStr");
		String status2 = request.getParameter("status2"); 
		if(StringUtils.isNotEmpty(uniNoStr) && StringUtils.isNotEmpty(status2)){
			int i = jpmProductSaleNewManager.batchAuditProductNews(uniNoStr, status2); 
			
			//==========================Modify By WuCF OMS接口 20141110
			String productNos = jpmProductSaleNewManager.getProductNos(uniNoStr);//商品编码集合字符串 
			String status = status2;//状态
			
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
			
			log.info("goods.batch_verifier(jpmpProductSaleNewController)："+returnnoJsonString.toString());
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
		}
		
		//如果不是全球，则设置区域
		String companyCode = request.getParameter("companyCode");
		SysUser loginUser = SessionLogin.getLoginUser(request);
		if(StringUtils.isNotEmpty(companyCode)){
			crm.setValue("companyCode", companyCode);
		}else{
			if (!"AA".equalsIgnoreCase(loginUser.getCompanyCode())) {
				crm.setValue("companyCode",loginUser.getCompanyCode());
	
			} 
		}
		
		String productNo = request.getParameter("productNo");
    	String productName = request.getParameter("productName");
    	String status = request.getParameter("status");
    	String confirm = request.getParameter("confirm");
    	String cxFlag = request.getParameter("cxFlag");
    	String productCategory = request.getParameter("productCategory");
    	
    	crm.setValue("productNo", productNo);
    	crm.setValue("productName", productName);
    	crm.setValue("status", status);
    	crm.setValue("confirm", confirm);
    	crm.setValue("cxFlag", cxFlag);
    	crm.setValue("productCategory", productCategory);
		
		List jpmProductSaleNews = jpmProductSaleNewManager.getJpmProductSaleNewsByCrm(crm,pager);
		request.setAttribute("jpmProductSaleNewListTable_totalRows", pager.getTotalObjects());
		/*****/ 

		return new ModelAndView("pm/jpmProductSaleNewList", Constants.JPMPRODUCTSALENEW_LIST, jpmProductSaleNews);
	}
}
