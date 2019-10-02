package com.joymain.jecs.fi.webapp.action;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.fi.model.FiPayAccount;
import com.joymain.jecs.fi.service.FiPayAccountManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class FiPayAccountFormController extends BaseFormController {
    private FiPayAccountManager fiPayAccountManager = null;

    public void setFiPayAccountManager(FiPayAccountManager fiPayAccountManager) {
        this.fiPayAccountManager = fiPayAccountManager;
    }
    public FiPayAccountFormController() {
        setCommandName("fiPayAccount");
        setCommandClass(FiPayAccount.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String accountId = request.getParameter("accountId");
        FiPayAccount fiPayAccount = null;

        if (!StringUtils.isEmpty(accountId)) {
            fiPayAccount = fiPayAccountManager.getFiPayAccount(accountId);
        } else {
            fiPayAccount = new FiPayAccount();
        }

        return fiPayAccount;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        FiPayAccount fiPayAccount = (FiPayAccount) command;
        boolean isNew = (fiPayAccount.getAccountId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteFiPayAccount".equals(strAction)  ) {
		fiPayAccountManager.removeFiPayAccount(fiPayAccount.getAccountId().toString());
		key="fiPayAccount.delete";
	}else{
		
		//验证默认商户号，只能一个
		List list = fiPayAccountManager.getDefaultAccounts(fiPayAccount.getAccountId());
		if(list != null && list.size()>0){
			
			saveMessage(request, "已经存在默认商户号！");
    		return showForm(request, response, errors);	
		}
		
		//当前用户
		SysUser loginSysUser = SessionLogin.getLoginUser(request);
		fiPayAccount.setCreateTime(new Date());
		fiPayAccount.setCreateUserCode(loginSysUser.getUserCode());
		fiPayAccount.setCreateUserName(loginSysUser.getUserName());
		
		fiPayAccountManager.saveFiPayAccount(fiPayAccount);
		key="fiPayAccount.update";
	}

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
