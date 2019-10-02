package com.joymain.jecs.fi.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiPayAdvice;
import com.joymain.jecs.fi.service.JfiPayAdviceManager;
import com.joymain.jecs.fi.service.JfiPayBankManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.webapp.util.LocaleUtil;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
/**
 * 付款通知表单
 * @author Alvin
 *
 */
public class JfiPayAdviceFormController extends BaseFormController {
    private JfiPayAdviceManager jfiPayAdviceManager = null;
    private JfiPayBankManager jfiPayBankManager = null;
    private JmiMemberManager jmiMemberManager = null;
    private SysIdManager sysIdManager = null;

	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	public void setJfiPayBankManager(JfiPayBankManager jfiPayBankManager) {
		this.jfiPayBankManager = jfiPayBankManager;
	}
	public void setJfiPayAdviceManager(JfiPayAdviceManager jfiPayAdviceManager) {
        this.jfiPayAdviceManager = jfiPayAdviceManager;
    }
    public JfiPayAdviceFormController() {
        setCommandName("jfiPayAdvice");
        setCommandClass(JfiPayAdvice.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
		String adviceCode = request.getParameter("adviceCode");
		JfiPayAdvice jfiPayAdvice = null;

		if (!StringUtils.isEmpty(adviceCode)) {
			jfiPayAdvice = jfiPayAdviceManager.getJfiPayAdvice(adviceCode);
		} else {
			jfiPayAdvice = new JfiPayAdvice();
		}

		List jfiPayBanks = new ArrayList();
		if (SessionLogin.getLoginUser(request).getIsManager()) {
			jfiPayBanks = this.jfiPayBankManager.getJfiPayBanks(null);
		} else if (SessionLogin.getLoginUser(request).getIsCompany()) {
			jfiPayBanks = this.jfiPayBankManager.getJfiPayBanksByCompany(SessionLogin.getLoginUser(request).getCompanyCode());
		} else if (SessionLogin.getLoginUser(request).getIsMember()){
			JmiMember jmiMember = this.jmiMemberManager.getJmiMember(SessionLogin.getLoginUser(request).getUserCode());
			jfiPayBanks = this.jfiPayBankManager.getJfiPayBanksWithStr(new String[] { jmiMember.getPbNo(), jmiMember.getPbNo1(),
					jmiMember.getPbNo2() });
			if(!StringUtils.isEmpty(adviceCode) && !jfiPayAdvice.getSysUser().getUserCode().equals(jmiMember.getUserCode())){
				return null;
			}
		}

		request.setAttribute("fiPayBanks", jfiPayBanks);

		return jfiPayAdvice;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

		JfiPayAdvice jfiPayAdvice = (JfiPayAdvice) command;

		if ("deletePayAdvice".equalsIgnoreCase(request.getParameter("strAction"))) {
			jfiPayAdvice = this.jfiPayAdviceManager.getJfiPayAdvice(jfiPayAdvice.getAdviceCode());
			if (jfiPayAdvice.getStatus() != 2 && jfiPayAdvice.getStatus() != 4) {
				jfiPayAdviceManager.removeJfiPayAdvice(jfiPayAdvice.getAdviceCode().toString());
				saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(), "fiPayAdvice.deleted"));
			}
		} else {
			String key = "fiPayAdvice.updated";
			if ("addPayAdvice".equalsIgnoreCase(request.getParameter("strAction"))) {
				key = "fiPayAdvice.added";

				String newAdviceCode = this.sysIdManager.buildIdStr("advicecode_"+ SessionLogin.getLoginUser(request).getCompanyCode().toLowerCase());
				if (StringUtils.isEmpty(newAdviceCode)) {
					throw new AppException(LocaleUtil.getLocalText("sysId.build.error", new String[] { "advicecode_"+ SessionLogin.getLoginUser(request).getCompanyCode().toLowerCase() }));
				}
				jfiPayAdvice.setAdviceCode(newAdviceCode);
				jfiPayAdvice.setCompanyCode(SessionLogin.getLoginUser(request).getCompanyCode());
				jfiPayAdvice.setSysUser(SessionLogin.getLoginUser(request));
				jfiPayAdvice.setCreaterCode(SessionLogin.getOperatorUser(request).getUserCode());
				jfiPayAdvice.setCreaterName(SessionLogin.getOperatorUser(request).getUserName());
				jfiPayAdvice.setCreateTime(new Date());
				jfiPayAdvice.setStatus(1);
			}
			jfiPayAdviceManager.saveJfiPayAdvice(jfiPayAdvice);

			saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(), key));
		}

		ModelAndView mv = new ModelAndView(getSuccessView());
		mv.addObject("strAction", "listFiPayAdvices");
		mv.addObject("needReload", "1");
		return mv;
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
    	String[] allowedFields = {
    						"accountCode",
    						"payDate",
    						"payMoney",
    						"notice",
    						"dealType",
    						"telephone",
    						"remark"
    						};
		binder.setAllowedFields(allowedFields);
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
