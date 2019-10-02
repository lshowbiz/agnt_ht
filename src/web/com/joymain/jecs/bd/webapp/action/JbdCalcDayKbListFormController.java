package com.joymain.jecs.bd.webapp.action;

import java.util.Date;
import java.util.Locale;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.bd.model.JbdCalcDayKbList;
import com.joymain.jecs.bd.service.JbdCalcDayKbListManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdCalcDayKbListFormController extends BaseFormController {
    private JbdCalcDayKbListManager jbdCalcDayKbListManager = null;

    public void setJbdCalcDayKbListManager(JbdCalcDayKbListManager jbdCalcDayKbListManager) {
        this.jbdCalcDayKbListManager = jbdCalcDayKbListManager;
    }
    public JbdCalcDayKbListFormController() {
        setCommandName("jbdCalcDayKbList");
        setCommandClass(JbdCalcDayKbList.class);
    }
    private JmiMemberManager jmiMemberManager;
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdCalcDayKbList jbdCalcDayKbList = null;

        if (!StringUtils.isEmpty(id)) {
            jbdCalcDayKbList = jbdCalcDayKbListManager.getJbdCalcDayKbList(id);
        } else {
            jbdCalcDayKbList = new JbdCalcDayKbList();
            jbdCalcDayKbList.setKbMoney(new BigDecimal(0));
        }

        return jbdCalcDayKbList;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        SysUser defSysUser = SessionLogin.getLoginUser(request);
    
		String strAction = request.getParameter("strAction");
        JbdCalcDayKbList jbdCalcDayKbList = (JbdCalcDayKbList) command;
        if(jbdCalcDayKbList.getStatus()!=null && jbdCalcDayKbList.getStatus().intValue()==1){
    		errors.reject("已结算，操作失败","已结算，操作失败");
    		return showForm(request, response, errors);
    	}
        if("addJbdCalcDayKbList".equals(strAction)||"editJbdCalcDayKbList".equals(strAction)){
        	if(StringUtil.isEmpty(jbdCalcDayKbList.getUserCode())){
        		errors.rejectValue("userCode", "isNotNull",new Object[] { this.getText("miMember.memberNo") }, "");
    			return showForm(request, response, errors);
        	}else{
        		JmiMember jmiMember=jmiMemberManager.getJmiMember(jbdCalcDayKbList.getUserCode());
            	if(null==jmiMember){

        			errors.reject("miMember.notFound");
            		return showForm(request, response, errors);
            	}
        	}
        	
        }
        boolean isNew = (jbdCalcDayKbList.getId() == null);
        Locale locale = request.getLocale();
		String key=null;
		if ("deleteJbdCalcDayKbList".equals(strAction)  ) {
			jbdCalcDayKbListManager.removeJbdCalcDayKbList(jbdCalcDayKbList.getId().toString());
		}else{
			jbdCalcDayKbList.setOperationDate(new Date());
			jbdCalcDayKbList.setOperationNo(defSysUser.getUserCode());
			 if("addJbdCalcDayKbList".equals(strAction)){
				 jbdCalcDayKbList.setStatus(0);
			 }
			jbdCalcDayKbListManager.saveJbdCalcDayKbList(jbdCalcDayKbList);
		}
		this.saveMessage(request, "更新成功");
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
