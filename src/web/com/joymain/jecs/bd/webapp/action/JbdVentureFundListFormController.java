package com.joymain.jecs.bd.webapp.action;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.model.JbdVentureFundList;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.bd.service.JbdVentureFundListManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.MessageUtil;

public class JbdVentureFundListFormController extends BaseFormController {
    private JbdVentureFundListManager jbdVentureFundListManager = null;
    private BdPeriodManager bdPeriodManager;
    private JmiMemberManager jmiMemberManager = null;
    private JpoMemberOrderManager jpoMemberOrderManager;
    public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	public void setJbdVentureFundListManager(JbdVentureFundListManager jbdVentureFundListManager) {
        this.jbdVentureFundListManager = jbdVentureFundListManager;
    }
    public JbdVentureFundListFormController() {
        setCommandName("jbdVentureFundList");
        setCommandClass(JbdVentureFundList.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdVentureFundList jbdVentureFundList = null;

        if (!StringUtils.isEmpty(id)) {
            jbdVentureFundList = jbdVentureFundListManager.getJbdVentureFundList(id);
        } else {
            jbdVentureFundList = new JbdVentureFundList();
        }

        return jbdVentureFundList;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdVentureFundList jbdVentureFundList = (JbdVentureFundList) command;

        if(this.checkForm(jbdVentureFundList, errors)){
    		return showForm(request, response, errors);
        }

		try {
			Date curDate=new Date();
			Integer weekInt=bdPeriodManager.getBdPeriodByTimeFormated(curDate, null);
			
			jbdVentureFundList.setPercentage(new BigDecimal(0.06));
			jbdVentureFundList.setCalcStatus(0);
			jbdVentureFundList.setStatus(1);
			jbdVentureFundList.setCreateWeek(weekInt);
			
			
			jbdVentureFundListManager.saveJbdVentureFundList(jbdVentureFundList);
			MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.saveLocaleMessage(request, "sys.message.updateFail");
		}

        return new ModelAndView("redirect:jbdVentureFundLists.html?strAction=jbdVentureFundLists");
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}

    private boolean checkForm(JbdVentureFundList jbdVentureFundList ,BindException errors){
    	boolean isNotPass=false;
    	if(StringUtil.isEmpty(jbdVentureFundList.getUserCode())){
			errors.reject("会员不能为空","会员不能为空");
			isNotPass = true;
    	}else if(null==jmiMemberManager.getJmiMember(jbdVentureFundList.getUserCode())){
			errors.reject("会员不存在","会员不存在");
			isNotPass = true;
    	}
    	
    	if(null==jbdVentureFundList.getWweek()){
			errors.reject("期别不能为空","期别不能为空");
			isNotPass = true;
    	}else if(null==bdPeriodManager.getBdPeriodByFormatedWeek(jbdVentureFundList.getWweek().toString())){
			errors.reject("期别不存在","期别不存在");
			isNotPass = true;
    	}
    	
    	if(StringUtil.isEmpty(jbdVentureFundList.getRecommendNo())){
			errors.reject("推荐人不能为空","推荐人不能为空");
			isNotPass = true;
    	}else if(null==jmiMemberManager.getJmiMember(jbdVentureFundList.getRecommendNo())){
			errors.reject("推荐人不存在","推荐人不存在");
			isNotPass = true;
    	}
    	
    	if(null==jbdVentureFundList.getPvAmt()){
			errors.reject("PV不能为空","PV不能为空");
			isNotPass = true;
    	}
    	
    	if(StringUtil.isEmpty(jbdVentureFundList.getOrderNo())){
			errors.reject("订单号不能为空","订单号不能为空");
			isNotPass = true;
    	}else if(null==jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(jbdVentureFundList.getOrderNo())){
			errors.reject("订单不存在","订单不存在");
			isNotPass = true;
    	}
    	
    	if(StringUtil.isEmpty(jbdVentureFundList.getOrderType())){
			errors.reject("订单类型不能为空","订单类型不能为空");
			isNotPass = true;
    	}
    	
    	return isNotPass;
    }
	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
}
