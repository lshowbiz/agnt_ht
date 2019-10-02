package com.joymain.jecs.pr.webapp.action;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.pr.model.JprRefund;
import com.joymain.jecs.pr.service.JprRefundManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 入库表单
 * @author Alvin
 *
 */
public class JprRefundIntoFormController extends BaseFormController {
	private JprRefundManager jprRefundManager = null;

	public void setJprRefundManager(JprRefundManager jprRefundManager) {
		this.jprRefundManager = jprRefundManager;
	}

	public JprRefundIntoFormController() {
		setCommandName("jprRefund");
		setCommandClass(JprRefund.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {

    	//================AiAgent LOGIN IMFORMATION
    	SysUser defSysUser = SessionLogin.getLoginUser(request);
    	//=========================================
    	
		JprRefund jprRefund = new JprRefund();
		
		String roNo = request.getParameter("roNo");
		if(!StringUtil.isEmpty(roNo)){
			jprRefund = jprRefundManager.getJprRefund(roNo);
			if(jprRefund==null || !"N".equalsIgnoreCase(jprRefund.getLocked()) || !"1".equals(jprRefund.getRefundStatus()) ){
				return null;
			}
			request.setAttribute("intoStatus", jprRefund.getIntoStatus());
		}
		if(!"AA".equals(defSysUser.getCompanyCode())){
			if(!defSysUser.getCompanyCode().equals(jprRefund.getCompanyCode())){
				return null;
			}
		}
		return jprRefund;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
    	//================AiAgent LOGIN IMFORMATION
    	SysUser defSysUser = SessionLogin.getLoginUser(request);
    	//=========================================
    	String intoStatus = request.getAttribute("intoStatus").toString();
		JprRefund jprRefund = (JprRefund) command;
		
		/*//modify gw 2014-11-04----------------------begin---结算费用
   	 String repairFee = request.getParameter("repairFee");
   	 String renovationFee = request.getParameter("renovationFee");
   	 String logisticsFee = request.getParameter("logisticsFee");
   	 String settledBonus = request.getParameter("settledBonus");
   	 String fillFreight = request.getParameter("fillFreight");
   	// String logisticsFeeSix = request.getParameter("logisticsFeeSix");
   	// String logisticsFeeSeven = request.getParameter("logisticsFeeSeven");
   	// String logisticsFeeEight = request.getParameter("logisticsFeeEight");	
			jprRefund.setRepairFee(repairFee);
			jprRefund.setRenovationFee(renovationFee);
			jprRefund.setLogisticsFee(logisticsFee);
			jprRefund.setSettledBonus(settledBonus);
			jprRefund.setFillFreight(fillFreight);
		//	jprRefund.setLogisticsFeeSix(logisticsFeeSix);
		//	jprRefund.setLogisticsFeeSeven(logisticsFeeSeven);
		//	jprRefund.setLogisticsFeeEight(logisticsFeeEight);
		
			Float sum =0f;	
			
		if(!StringUtil.isEmpty(repairFee)){
			sum += Float.parseFloat(repairFee);
		}
		if(!StringUtil.isEmpty(renovationFee)){
			sum += Float.parseFloat(renovationFee);
		}
		if(!StringUtil.isEmpty(logisticsFee)){
			sum += Float.parseFloat(logisticsFee);
		}
		if(!StringUtil.isEmpty(settledBonus)){
			sum += Float.parseFloat(settledBonus);
		}
		if(!StringUtil.isEmpty(fillFreight)){
			sum += Float.parseFloat(fillFreight);
		}
		if(!StringUtil.isEmpty(logisticsFeeSix)){
			sum += Float.parseFloat(logisticsFeeSix);
		}
		if(!StringUtil.isEmpty(logisticsFeeSeven)){
			sum += Float.parseFloat(logisticsFeeSeven);
		}
		if(!StringUtil.isEmpty(logisticsFeeEight)){
			sum += Float.parseFloat(logisticsFeeEight);
		}
		
   	//modify gw 2014-11-04----------------------end
*/		
		 String refundTye = request.getParameter("refundTye");	
		 jprRefund.setRefundTye(refundTye);
		jprRefund.setIntoUNo(defSysUser.getUserCode());
		jprRefund.setIntoTime(new Date());
		if("2".equals(intoStatus)){
			if("2".equals(jprRefund.getIntoStatus())){
				jprRefundManager.saveJprRefund(jprRefund);
			}else{
				jprRefundManager.saveJprRefundInto(jprRefund);//货拿回来
			}
		}else{
			if("2".equals(jprRefund.getIntoStatus())){
				jprRefundManager.saveJprRefundInto(jprRefund);//退货
			}else{
				jprRefundManager.saveJprRefund(jprRefund);
			}
		}
		saveMessage(request, getText(defSysUser.getDefCharacterCoding(),"prRefund.into.success"));
		return new ModelAndView(getSuccessView()).addObject("needReload", "1");
	}
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
    	String[] allowedFields = {"intoStatus","intoRemark"};
    	binder.setAllowedFields(allowedFields);
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
