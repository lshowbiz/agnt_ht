package com.joymain.jecs.bd.webapp.action;

import java.util.Date;
import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.model.JbdManualCon;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.bd.service.JbdManualConManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdManualConFormController extends BaseFormController {
    private JbdManualConManager jbdManualConManager = null;
	private BdPeriodManager bdPeriodManager;
	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
    public void setJbdManualConManager(JbdManualConManager jbdManualConManager) {
        this.jbdManualConManager = jbdManualConManager;
    }
    public JbdManualConFormController() {
        setCommandName("jbdManualCon");
        setCommandClass(JbdManualCon.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdManualCon jbdManualCon = null;

        if (!StringUtils.isEmpty(id)) {
            jbdManualCon = jbdManualConManager.getJbdManualCon(id);
            
            jbdManualCon.setStartWeek(StringUtil.formatInt(WeekFormatUtil.getFormatWeek("w",jbdManualCon.getStartWeek().toString())));
            jbdManualCon.setEndWeek(StringUtil.formatInt(WeekFormatUtil.getFormatWeek("w",jbdManualCon.getEndWeek().toString())));
        } else {
            jbdManualCon = new JbdManualCon();
        }

        
        
        return jbdManualCon;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdManualCon jbdManualCon = (JbdManualCon) command;


        SysUser defSysUser = SessionLogin.getLoginUser(request);
        if(jbdManualCon.getStartWeek()==null){
			errors.rejectValue("startWeek", "isNotNull",new Object[] { "开始期" }, "");
			return showForm(request, response, errors);
        }else{
        	BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByFormatedWeek(WeekFormatUtil.getFormatWeek("f",jbdManualCon.getStartWeek().toString()));
        	if(bdPeriod==null){
        		errors.reject("开始期不存在","开始期不存在");
        		return showForm(request, response, errors);
        	}
        }
        if(jbdManualCon.getEndWeek()==null){
			errors.rejectValue("endWeek", "isNotNull",new Object[] { "结束期" }, "");
			return showForm(request, response, errors);
        }else{
        	BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByFormatedWeek(WeekFormatUtil.getFormatWeek("f",jbdManualCon.getEndWeek().toString()));
        	if(bdPeriod==null){
        		errors.reject("结束期不存在","结束期不存在");
        		return showForm(request, response, errors);
        	}
        }
        
        jbdManualCon.setStartWeek(StringUtil.formatInt(WeekFormatUtil.getFormatWeek("f",jbdManualCon.getStartWeek().toString())));

        jbdManualCon.setEndWeek(StringUtil.formatInt(WeekFormatUtil.getFormatWeek("f",jbdManualCon.getEndWeek().toString())));
        jbdManualCon.setCreateNo(defSysUser.getUserCode());
        jbdManualCon.setCreateTime(new Date());
        
		jbdManualConManager.saveJbdManualCon(jbdManualCon);
		
		this.saveMessage(request, "保存成功");
		
		

        return new ModelAndView(getSuccessView());
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
