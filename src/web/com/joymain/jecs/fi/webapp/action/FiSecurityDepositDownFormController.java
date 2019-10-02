package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.fi.model.FiSecurityDeposit;
import com.joymain.jecs.fi.service.FiSecurityDepositManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
/**
 * 保证金扣减
 * @author Administrator
 *
 */
public class FiSecurityDepositDownFormController extends BaseFormController {
    private FiSecurityDepositManager fiSecurityDepositManager = null;
    private SysUserManager sysUserManager = null;

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

    public void setFiSecurityDepositManager(FiSecurityDepositManager fiSecurityDepositManager) {
        this.fiSecurityDepositManager = fiSecurityDepositManager;
    }
    public FiSecurityDepositDownFormController() {
        setCommandName("fiSecurityDeposit");
        setCommandClass(FiSecurityDeposit.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
    	
        String fsdId = request.getParameter("fsdId");
        FiSecurityDeposit fiSecurityDeposit = null;

        if (!StringUtils.isEmpty(fsdId)) {
            fiSecurityDeposit = fiSecurityDepositManager.getFiSecurityDeposit(fsdId);
        } else {
            fiSecurityDeposit = new FiSecurityDeposit();
        }

        return fiSecurityDeposit;
    }

    //扣减保证金提交
    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        
        String strAction = request.getParameter("strAction");
        if ("downFiSecurityDeposit".equals(strAction)) {
        	
        	FiSecurityDeposit fiSecurityDeposit = (FiSecurityDeposit) command;
            
            String amount = request.getParameter("downAmount");
            String notes = request.getParameter("remark");
            SysUser loginSysUser = SessionLogin.getLoginUser(request);

            //扣减保证金
            try{
            	
            	fiSecurityDepositManager.downFiSecurityDeposit(fiSecurityDeposit.getFsdId().toString(), loginSysUser, amount, notes);
            }catch(AppException a){
            	
            	MessageUtil.saveLocaleMessage(request, a.getMessage());
    			return showForm(request, response, errors);	
            }
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
