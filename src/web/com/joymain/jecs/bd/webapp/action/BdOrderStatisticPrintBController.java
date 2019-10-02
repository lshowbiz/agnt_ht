package com.joymain.jecs.bd.webapp.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.pd.service.PdExchangeOrderManager;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.pr.service.JprRefundManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 销售业绩统计表 时间
 * 
 * @author Alvin
 * 
 */
public class BdOrderStatisticPrintBController extends BaseController implements
		Controller {
	private transient final Log log = LogFactory
			.getLog(BdOrderStatisticPrintBController.class);

	private BdPeriodManager bdPeriodManager = null;
	private JpoMemberOrderManager jpoMemberOrderManager = null;
	private JprRefundManager jprRefundManager = null;

	private PdExchangeOrderManager pdExchangeOrderManager;
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'execute' method");
		}
		 RequestUtil.freshSession(request,"bdOrderStatisticPrintB",20l);
		// ================AiAgent LOGIN IMFORMATION
		SysUser defSysUser = SessionLogin.getLoginUser(request);
		// =========================================
		String strAction = request.getParameter("strAction");
		String createBTime = "";
		String createETime = "";
		String wWeeks = "";
		String wMonth="";
		if(RequestUtil.saveOperationSession(request,"bdOrderStatisticPrintB",20l)!=0){
			return new ModelAndView("bd/bdOrderStatisticPrintB");
		}
		if("bdOrderStatisticWeekB".equals(strAction)){
			wWeeks = request.getParameter("wWeek");
			wWeeks = WeekFormatUtil.getFormatWeek("f",wWeeks);
			if (StringUtil.isEmpty(wWeeks) || wWeeks.length() != 6){
				saveMessage(request, LocaleUtil.getLocalText("error.wweek.not.existed"));
				return new ModelAndView("redirect:bd/bdOrderStatisticB.html?strAction=bdOrderStatisticB");
			}
			BdPeriod bdPeriod = bdPeriodManager.getBdPeriodByWeek(new Integer(wWeeks.substring(0, 4)), new Integer(wWeeks.substring(4, 6)), null);
			if(bdPeriod==null){
				saveMessage(request, LocaleUtil.getLocalText("error.wweek.not.existed"));
				return new ModelAndView("redirect:bd/bdOrderStatisticB.html?strAction=bdOrderStatisticB");
			}else{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				createBTime = dateFormat.format(bdPeriod.getStartTime())+ " 00:00:00";
				createETime = dateFormat.format(DateUtil.getDateOffset(bdPeriod.getEndTime(), 5, -1))+ " 23:59:59";
			}
		}else if("bdOrderStatisticDateB".equals(strAction)){
			createBTime = request.getParameter("createBTime");
			createETime = request.getParameter("createETime");
			if(StringUtil.isEmpty(createBTime) || StringUtil.isEmpty(createETime)) {
				saveMessage(request, LocaleUtil.getLocalText("error.wweek.not.existed"));
				return new ModelAndView("redirect:bd/bdOrderStatisticB.html?strAction=bdOrderStatisticB");
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
			Date b = simpleDateFormat.parse(createBTime);
			Date e = simpleDateFormat.parse(createETime);
			Date t = simpleDateFormat.parse("2009-12-31");
			if(b.after(t)){
				createBTime += " 00:00:00";
			}else{
				createBTime += " 12:00:00";
			}
			if(e.after(t)){
				createETime += " 23:59:59";
			}else{
				createETime += " 12:00:00";
			}
		}
		//结算月
		else if("bdOrderStatisticMonthB".equals(strAction)){
			wMonth = request.getParameter("wMonth");
			wMonth = WeekFormatUtil.getFormatMonth("f", wMonth);
			if (StringUtil.isEmpty(wMonth) || wMonth.length() != 6){
				saveMessage(request, LocaleUtil.getLocalText("error.wweek.not.existed"));
				return new ModelAndView("redirect:bd/bdOrderStatisticB.html?strAction=bdOrderStatisticB");
			}
			List monthList = bdPeriodManager.getBdPeriodsByMonth(new String(wMonth.substring(0, 4)), new String(wMonth.substring(4, 6)));
			if(monthList==null  || monthList.size()==0){
				saveMessage(request, LocaleUtil.getLocalText("error.wweek.not.existed"));
				return new ModelAndView("redirect:bd/bdOrderStatisticB.html?strAction=bdOrderStatisticB");
			}else{
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				BdPeriod bdPeriod =(BdPeriod)monthList.get(0);
				Date begingTime2=bdPeriod.getStartTime();
				Date endTime2=bdPeriod.getEndTime();
				
				   for (int i = 1; i < monthList.size(); i++) {
					 BdPeriod bdPeriod2=(BdPeriod)monthList.get(i);
					 
					if(bdPeriod2.getStartTime().before(begingTime2)){
						begingTime2=bdPeriod2.getStartTime();
					}
					if(bdPeriod2.getEndTime().after(endTime2))
					{
						endTime2=bdPeriod2.getEndTime();
					}
					
				}
				   createBTime = dateFormat.format(begingTime2)+ " 00:00:00";
				   createETime = dateFormat.format(DateUtil.getDateOffset(endTime2, 5, -1))+ " 23:59:59";
		         
			}
		}
		
		String companyCode = request.getParameter("companyCode");
		if(!"AA".equals(defSysUser.getCompanyCode())){
			companyCode = defSysUser.getCompanyCode();
		}
		
		String productType = request.getParameter("productType");
		
		List poMemberOrderList = jpoMemberOrderManager.getJpoMemberOrderStaticsCheckedCompany(createBTime, createETime, companyCode, productType,"BC");//会员订单
		List prRefundList = jprRefundManager.getJprReRefundStaticsCheckedCompany(createBTime, createETime, companyCode, productType,"0");//会员退货订
		List pdExchangeOrderDetailList = pdExchangeOrderManager.getPdExchangeOrderDetailStaticsCheckedCompany(createBTime,createETime,companyCode,productType);//会员换货单换货信息
		List pdExchangeOrderBackList = pdExchangeOrderManager.getPdExchangeOrderBackStaticsCheckedCompany(createBTime, createETime, companyCode, productType);//会员换货单退回信息
		
		request.setAttribute("wWeeks", wWeeks);
		request.setAttribute("startTime", createBTime);
		request.setAttribute("endTime", createETime);
		request.setAttribute("poMemberOrderList", poMemberOrderList);
		request.setAttribute("prRefundList", prRefundList);
		request.setAttribute("pdExchangeOrderDetailList", pdExchangeOrderDetailList);
		request.setAttribute("pdExchangeOrderBackList", pdExchangeOrderBackList);
		
		return new ModelAndView("bd/bdOrderStatisticPrintB");
	}

	public void setPdExchangeOrderManager(
			PdExchangeOrderManager pdExchangeOrderManager) {
		this.pdExchangeOrderManager = pdExchangeOrderManager;
	}

	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	public void setJprRefundManager(JprRefundManager jprRefundManager) {
		this.jprRefundManager = jprRefundManager;
	}

}