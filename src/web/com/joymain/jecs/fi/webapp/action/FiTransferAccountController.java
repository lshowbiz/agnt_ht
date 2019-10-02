package com.joymain.jecs.fi.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.FiTransferAccount;
import com.joymain.jecs.fi.service.FiTransferAccountManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * 会员转账列表
 * 
 * @author lenovo
 * 
 */
public class FiTransferAccountController extends BaseController implements
		Controller {
	private final Log log = LogFactory
			.getLog(FiTransferAccountController.class);
	private FiTransferAccountManager fiTransferAccountManager = null;

	public void setFiTransferAccountManager(
			FiTransferAccountManager fiTransferAccountManager) {
		this.fiTransferAccountManager = fiTransferAccountManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		
		FiTransferAccount fiTransferAccount = new FiTransferAccount();
		
		BeanUtils.populate(fiTransferAccount, request.getParameterMap());

		//会员转账列表只需要查询自己的转账单即可
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		crm.addField("createrCode", SessionLogin.getLoginUser(request).getUserCode());
		
		Pager pager = new Pager("fiTransferAccountListTable", request, 20);
		List fiTransferAccounts = fiTransferAccountManager
				.getFiTransferAccountsByCrm(crm, pager);
		request.setAttribute("fiTransferAccountListTable_totalRows", pager
				.getTotalObjects());

		return new ModelAndView("fi/fiTransferAccountList",
				Constants.FITRANSFERACCOUNT_LIST, fiTransferAccounts);
	}
}
