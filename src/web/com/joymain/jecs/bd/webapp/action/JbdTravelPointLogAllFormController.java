package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.bd.model.JbdTravelPointAll;
import com.joymain.jecs.bd.model.JbdTravelPointLogAll;
import com.joymain.jecs.bd.service.JbdTravelPointAllManager;
import com.joymain.jecs.bd.service.JbdTravelPointLogAllManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdTravelPointLogAllFormController extends BaseFormController {
    private JbdTravelPointLogAllManager jbdTravelPointLogAllManager = null;
    private JmiMemberManager jmiMemberManager;
    private JbdTravelPointAllManager jbdTravelPointAllManager;

    public void setJbdTravelPointAllManager(JbdTravelPointAllManager jbdTravelPointAllManager) {
        this.jbdTravelPointAllManager = jbdTravelPointAllManager;
    }

    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

    public void setJbdTravelPointLogAllManager(JbdTravelPointLogAllManager jbdTravelPointLogAllManager) {
        this.jbdTravelPointLogAllManager = jbdTravelPointLogAllManager;
    }
    public JbdTravelPointLogAllFormController() {
        setCommandName("jbdTravelPointLogAll");
        setCommandClass(JbdTravelPointLogAll.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String logId = request.getParameter("logId");
        JbdTravelPointLogAll jbdTravelPointLogAll = null;

        if (!StringUtils.isEmpty(logId)) {
            jbdTravelPointLogAll = jbdTravelPointLogAllManager.getJbdTravelPointLogAll(logId);
        } else {
            jbdTravelPointLogAll = new JbdTravelPointLogAll();
        }

        return jbdTravelPointLogAll;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdTravelPointLogAll jbdTravelPointLogAll = (JbdTravelPointLogAll) command;
        boolean isNew = (jbdTravelPointLogAll.getLogId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdTravelPointLogAll".equals(strAction)  ) {
		jbdTravelPointLogAllManager.removeJbdTravelPointLogAll(jbdTravelPointLogAll.getLogId().toString());
		key="jbdTravelPointLogAll.delete";
	}else{
		
		SysUser defSysUser = SessionLogin.getLoginUser(request);

		if (this.checkForm(jbdTravelPointLogAll, errors)) {
			return showForm(request, response, errors);
		}
		
		jbdTravelPointLogAllManager.saveJbdTravelPointLogAll(jbdTravelPointLogAll,defSysUser);
		key="sys.message.updateSuccess";
	}
	saveMessage(request, getText(SessionLogin.getLoginUser(request)
			.getDefCharacterCoding(), key));
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
    
    private boolean checkForm(JbdTravelPointLogAll jbdTravelPointLogAll,BindException errors){

    	boolean isNotPass=false;
    	if(StringUtil.isEmpty(jbdTravelPointLogAll.getUserCode())){
			errors.reject("会员不能为空","会员不能为空");
			isNotPass = true;
    	}else if(null==jmiMemberManager.getJmiMember(jbdTravelPointLogAll.getUserCode())){
			errors.reject("会员不存在","会员不存在");
			isNotPass = true;
    	}
    	
    	if(null== jbdTravelPointLogAll.getFyear()){
			errors.reject("请输入年份","请输入年份");
			isNotPass = true;
    	}
    	if(StringUtil.isEmpty(jbdTravelPointLogAll.getDealType())){
			errors.reject("请选择类型","请选择类型");
			isNotPass = true;
    	}

    	if(null==jbdTravelPointLogAll.getUsePoint()){
			errors.reject("请输入积分","请输入积分");
			isNotPass = true;
    	}else if(!StringUtil.isInteger(jbdTravelPointLogAll.getUsePoint().toString())){
			errors.reject("请输入整数积分","请输入整数积分");
			isNotPass = true;
    	}else if(Integer.valueOf(jbdTravelPointLogAll.getUsePoint().toString())<=0){
			errors.reject("输入整数积分必须大于0","输入整数积分必须大于0");
			isNotPass = true;
    	}
    	if ("D".equals(jbdTravelPointLogAll.getDealType())){//取出积分，验证积分是否足够
			JbdTravelPointAll JbdTravelPointAll = jbdTravelPointAllManager.getJbdTravelPointAll(jbdTravelPointLogAll.getUserCode(),jbdTravelPointLogAll.getFyear().intValue());
			if(null==JbdTravelPointAll){
				errors.reject("积分不足,不能取出","积分不足,不能取出");
				isNotPass = true;
			}else{
				if(JbdTravelPointAll.getTotal().compareTo(jbdTravelPointLogAll.getUsePoint())<0){
					errors.reject("积分不足,不能取出","积分不足,不能取出");
					isNotPass = true;
				}
			}
		}
    	
    	if(!MeteorUtil.isStrLen(jbdTravelPointLogAll.getRemark(), 500)){
			errors.reject("备注输入超长","备注输入超长");
			isNotPass = true;
		}
    	
    	return isNotPass;
    }
}
