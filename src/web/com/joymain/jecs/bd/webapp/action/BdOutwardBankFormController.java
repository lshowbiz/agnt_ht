package com.joymain.jecs.bd.webapp.action;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.bd.model.BdOutwardBank;
import com.joymain.jecs.bd.service.BdOutwardBankManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class BdOutwardBankFormController extends BaseFormController {
	private BdOutwardBankManager bdOutwardBankManager = null;

	public void setBdOutwardBankManager(BdOutwardBankManager bdOutwardBankManager) {
		this.bdOutwardBankManager = bdOutwardBankManager;
	}

	public BdOutwardBankFormController() {
		setCommandName("bdOutwardBank");
		setCommandClass(BdOutwardBank.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String bankId = request.getParameter("bankId");
		BdOutwardBank bdOutwardBank = null;

		if (!StringUtils.isEmpty(bankId)) {
			bdOutwardBank = bdOutwardBankManager.getBdOutwardBank(bankId);
		} else {
			bdOutwardBank = new BdOutwardBank();
		}

		return bdOutwardBank;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
		BdOutwardBank bdOutwardBank = (BdOutwardBank) command;

		SysUser defSysUser = SessionLogin.getLoginUser(request);
		if ("C".equals(defSysUser.getUserType())) {
			bdOutwardBank.setCompanyCode((defSysUser.getCompanyCode()));
			if ("AA".equals(defSysUser.getCompanyCode())) {
				bdOutwardBank.setCompanyCode(request.getParameter("companyCode"));
			}
		}

		boolean isNew = (bdOutwardBank.getBankId() == null);
		Locale locale = request.getLocale();
		if ("deleteBdOutwardBank".equals(request.getParameter("strAction"))) {
			bdOutwardBankManager.removeBdOutwardBank(bdOutwardBank.getBankId().toString());

			// saveMessage(request, getText("bdOutwardBank.deleted", locale));
			saveMessage(request, getText("bdOutwardBank.deleted"));
		} else {
			String key = "sys.message.updateSuccess";
			if ("addBdOutwardBank".equals(request.getParameter("strAction"))) {
				key = "sys.message.updateSuccess";
			}
			
			if(StringUtil.isEmpty(bdOutwardBank.getBankCode())){
				this.saveMessage(request, "汇出银行代码不能为空");
				return showForm(request, response, errors);
			}
			
			if (null == bdOutwardBank.getBankId() || "".equals(bdOutwardBank.getBankId())) {
				BdOutwardBank bdOutwardBankFind = new BdOutwardBank();
				bdOutwardBankFind.setBankCode(bdOutwardBank.getBankCode());
				List res = bdOutwardBankManager.getBdOutwardBanks(bdOutwardBankFind);
				if (res.size() != 0) {
					this.saveMessage(request, LocaleUtil.getLocalText("bdOutwardBank.bankCodeExist"));
					return showForm(request, response, errors);
				}
			}
			try {
				bdOutwardBankManager.saveBdOutwardBank(bdOutwardBank);
				saveMessage(request, getText(key));

			} catch (Exception e) {
				this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateFail"));
			}

		}

		return new ModelAndView("redirect:/bdOutwardBanks.html?strAction=bdOutwardBanks");
	}
}
