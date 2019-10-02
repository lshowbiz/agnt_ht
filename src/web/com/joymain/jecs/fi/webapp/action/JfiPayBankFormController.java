package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiPayBank;
import com.joymain.jecs.fi.service.JfiPayBankManager;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
/**
 * 收款银行表单
 * @author Alvin
 *
 */
public class JfiPayBankFormController extends BaseFormController {
    private JfiPayBankManager jfiPayBankManager = null;

    public void setJfiPayBankManager(JfiPayBankManager jfiPayBankManager) {
        this.jfiPayBankManager = jfiPayBankManager;
    }
    public JfiPayBankFormController() {
        setCommandName("jfiPayBank");
        setCommandClass(JfiPayBank.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
    	String accountCode = request.getParameter("accountCode");
		JfiPayBank fiPayBank = null;

		if (!StringUtils.isEmpty(accountCode)) {
			fiPayBank = jfiPayBankManager.getJfiPayBank(accountCode);
		}
		
		if(fiPayBank==null){
			fiPayBank=new JfiPayBank();
		}

		return fiPayBank;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

		JfiPayBank jfiPayBank = (JfiPayBank) command;

		if ("deleteFiPayBank".equalsIgnoreCase(request.getParameter("strAction"))) {
			jfiPayBankManager.removeJfiPayBank(jfiPayBank.getAccountCode().toString());

			saveMessage(request, getText("fiPayBank.deleted"));
		} else {
			if("AA".equals(SessionLogin.getLoginUser(request).getCompanyCode())){
				jfiPayBank.setCompanyCode(request.getParameter("companyCode"));
			}else{
				jfiPayBank.setCompanyCode(SessionLogin.getLoginUser(request).getCompanyCode());
			}
			String key = "fiPayBank.updated";
			if ("addFiPayBank".equalsIgnoreCase(request.getParameter("strAction"))) {
				key="fiPayBank.added";
				// 判断是否存在
				JfiPayBank oldJfiPayBank = this.jfiPayBankManager.getJfiPayBank(jfiPayBank.getAccountCode());
				if (oldJfiPayBank != null) {
					// 存在
					errors.rejectValue("accountCode","error.accountCode.existed");
					return showForm(request, response, errors);
				}
			}
			jfiPayBankManager.saveJfiPayBank(jfiPayBank);

			
			saveMessage(request, getText(key));
		}

		ModelAndView mv=new ModelAndView(getSuccessView());
		mv.addObject("needReload", "1");
		return mv;
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
