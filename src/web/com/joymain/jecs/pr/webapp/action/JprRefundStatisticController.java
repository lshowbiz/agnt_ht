package com.joymain.jecs.pr.webapp.action;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.po.service.JpoMemberOrderListManager;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.pr.model.JprRefund;
import com.joymain.jecs.pr.model.JprRefundList;
import com.joymain.jecs.pr.service.JprRefundManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.lowagie.text.pdf.AcroFields.Item;
/**
 * 退货统计
 * @author Alvin
 *
 */
public class JprRefundStatisticController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(JprRefundStatisticController.class);

	private JprRefundManager jprRefundManager = null;
	private JpoMemberOrderListManager jpoMemberOrderListManager;
	
	private BdPeriodManager bdPeriodManager;
	
	
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	//================AiAgent LOGIN IMFORMATION
    	SysUser defSysUser = SessionLogin.getLoginUser(request);
    	//=========================================
        RequestUtil.freshSession(request,"prRefundStatistic",10l);
    	
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		crm.addField("returnType", "0");
		if(!"AA".equals(defSysUser.getCompanyCode())){
			crm.addField("companyCode", defSysUser.getCompanyCode());
		}
		
		Pager pager = new Pager("prRefundListTable", request, Constants.PAGE_SIZE);
		String roNo = request.getParameter("roNo");
		String memberOrderNo = request.getParameter("jpoMemberOrder.memberOrderNo");
		String userCode = request.getParameter("sysUser.userCode");
		String createBTime = request.getParameter("createBTime");
		String createETime = request.getParameter("createETime");
		String intoBTime = request.getParameter("intoBTime");
		String intoETime = request.getParameter("intoETime");
		String refundBTime = request.getParameter("refundBTime");
		String refundETime = request.getParameter("refundETime");
		String intoStatus = request.getParameter("intoStatus");
		String refundStatus = request.getParameter("refundStatus");
		String exportFlag = request.getParameter("exportFlag");
		String refundTye = request.getParameter("refundTye");

		String mes = "";
		if(exportFlag!=null && exportFlag.equalsIgnoreCase("Y")){
			List<JprRefund> jprRefunds = jprRefundManager.getJprRefundsByCrm(crm,null);
			if(jprRefunds.size()>10000){
				mes = "导出数据不可以超过20000条.";
				MessageUtil.saveMessage(request, mes);
				return new ModelAndView("redirect:jprRefundStatistic.html");
			}
			
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename=refundItems.xls");
			return new ModelAndView("pr/jprRefundExport","jprRefunds",jprRefunds);
		}
		
        if (StringUtil.isEmpty(refundStatus)&&StringUtil.isEmpty(roNo)&&StringUtil.isEmpty(memberOrderNo)&&StringUtil.isEmpty(intoStatus)&&StringUtil.isEmpty(userCode)&&(StringUtil.isEmpty(createBTime)&&StringUtil.isEmpty(createETime))&&(StringUtil.isEmpty(intoBTime)&&StringUtil.isEmpty(intoETime))&&(StringUtil.isEmpty(refundBTime)&&StringUtil.isEmpty(refundETime)&&StringUtil.isEmpty(refundTye))){	
        	request.setAttribute("prRefundListTable_totalRows", pager.getTotalObjects());
			return new ModelAndView("pr/jprRefundStatistic", Constants.JPRREFUND_LIST, null);
        }else{
        	if("C".equals(defSysUser.getUserType())){
        		if("xls".equals(request.getParameter("prRefundListTable_ev"))){
            		if(RequestUtil.saveOperationSession(request,"prRefundStatistic",10l)!=0){
            			return new ModelAndView("redirect:jprRefundStatistic.html");
            		}
        		}
        	}
    		List jprRefunds = jprRefundManager.getJprRefundsByCrm(crm, pager);
    		//modify by fu 2016-05-23  -----------解决禅道的bug2026 
    		for(int i=0;i<jprRefunds.size();i++){
    			///public BdPeriod getBdPeriodByTime(final Date theTime, final Long wid);
    			JprRefund jprRefund = (JprRefund) jprRefunds.get(i);
    			BdPeriod jbdPeriod = bdPeriodManager.getBdPeriodByTime(jprRefund.getJpoMemberOrder().getCheckDate(),0l);
    			 Integer fyear = jbdPeriod.getFyear();
    			 Integer fmonth = jbdPeriod.getFmonth();
    			 Integer fweek = jbdPeriod.getFweek();
    			 String fmonthS = fmonth>9?fmonth.toString():(0+fmonth.toString());
    			 String fweekS = fweek>9?fweek.toString():(0+fweek.toString());
    			 String orderStatus = fyear.toString()+fmonthS+fweekS;
    			 jprRefund.setJpoCheckDate(orderStatus);
    		}
    		//modify by fu 2016-05-23 ------------解决禅道的bug2026 
    		
    		List products = jprRefundManager.statisticProductSale(crm);
    		request.setAttribute("prRefundListTable_totalRows", pager.getTotalObjects());
    		return new ModelAndView("pr/jprRefundStatistic", Constants.JPRREFUND_LIST, jprRefunds).addObject(Constants.JPMPRODUCT_LIST, products);
        }
	}
	public void setJprRefundManager(JprRefundManager jprRefundManager) {
		this.jprRefundManager = jprRefundManager;
	}
	public JpoMemberOrderListManager getJpoMemberOrderListManager() {
		return jpoMemberOrderListManager;
	}
	public void setJpoMemberOrderListManager(
			JpoMemberOrderListManager jpoMemberOrderListManager) {
		this.jpoMemberOrderListManager = jpoMemberOrderListManager;
	}
	
	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

}
