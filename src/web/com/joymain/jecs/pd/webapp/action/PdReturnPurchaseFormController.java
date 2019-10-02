package com.joymain.jecs.pd.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.Constants;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.pd.model.PdReturnPurchase;
import com.joymain.jecs.pd.service.PdReturnPurchaseDetailManager;
import com.joymain.jecs.pd.service.PdReturnPurchaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysIdManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdReturnPurchaseFormController extends BaseFormController {
	private PdReturnPurchaseManager pdReturnPurchaseManager = null;
	private PdReturnPurchaseDetailManager pdReturnPurchaseDetailManager = null;
	private SysIdManager sysIdManager = null;

	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}

	public void setPdReturnPurchaseDetailManager(
			PdReturnPurchaseDetailManager pdReturnPurchaseDetailManager) {
		this.pdReturnPurchaseDetailManager = pdReturnPurchaseDetailManager;
	}

	public void setPdReturnPurchaseManager(
			PdReturnPurchaseManager pdReturnPurchaseManager) {
		this.pdReturnPurchaseManager = pdReturnPurchaseManager;
	}

	public PdReturnPurchaseFormController() {
		setCommandName("pdReturnPurchase");
		setCommandClass(PdReturnPurchase.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		super.initPmProductMap(request);
		super.initStateCodeParem(request);
		String rpNo = request.getParameter("rpNo");
		String strAction = request.getParameter("strAction");
		PdReturnPurchase pdReturnPurchase = null;
		// List pdReturnPurchaseDetails = new ArrayList();
		Set pdReturnPurchaseDetails = null;
		boolean disabled = false;
		String checkButtonKey = null;// 按钮显示的文字
		String checkFlag = "-1";// 新建
		if (!StringUtils.isEmpty(rpNo)) {

			pdReturnPurchase = pdReturnPurchaseManager
					.getPdReturnPurchase(rpNo);
			pdReturnPurchaseDetails = pdReturnPurchase
					.getPdReturnPurchaseDetails();
		} else {

			pdReturnPurchase = new PdReturnPurchase();
		}

		if ("checkPdReturnPurchase".equals(strAction)) {// 审核
			checkButtonKey = "button.checked";
			checkFlag = "1";
			disabled = true;
		} else if ("financePdReturnPurchase".equals(strAction)) {// 财务审核
			checkButtonKey = "button.finance";
			checkFlag = "2";
			disabled = true;
		} else if ("confirmPdReturnPurchase".equals(strAction)) {// 确认
			checkButtonKey = "operation.button.confirm";
			checkFlag = "3";
			disabled = true;
		} else if ("editPdReturnPurchase".equals(strAction)) {// 编辑
			checkFlag = "0";
			checkButtonKey = "button.submit";
			if (pdReturnPurchase.getOrderFlag() >= 0) {// 已经初审的入库单不能编辑
				disabled = true;
			}

		} else if ("addPdReturnPurchase".equals(strAction)) {
			checkFlag = "-1";
			checkButtonKey = "button.next";

		}

		request.setAttribute(Constants.PDRETURNPURCHASEDETAIL_LIST,
				pdReturnPurchaseDetails);
		request.setAttribute("checkButtonKey", checkButtonKey);
		request.setAttribute("strAction", strAction);
		request.setAttribute("checkFlag", checkFlag);
		request.setAttribute("disabledFlag", disabled);
		request.setAttribute("rpNo", rpNo);
		log.info("checkFlag=" + strAction);
		log.info("disabled=" + disabled);
		return pdReturnPurchase;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
		List errorMsgs = new ArrayList();
		PdReturnPurchase pdReturnPurchase = (PdReturnPurchase) command;
		// boolean isNew = (pdReturnPurchase.getRpNo() == null);
		Locale locale = request.getLocale();
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		String strAction = request.getParameter("strAction");
		String view = "redirect:pdReturnPurchases.html?strAction="+strAction;
		//view = "redirect:pdSendInfos.html";
		String key = "pdReturnPurchase.updated";

		if ("checkPdReturnPurchase".equals(strAction)) {// 初审
			if (pdReturnPurchaseManager.getProductCountByRpNo(pdReturnPurchase
					.getRpNo()) > 0) {
				checkPdReturnPurchase(request, response, pdReturnPurchase);
				key = "pdReturnPurchase.check";
			} else {
				errors.reject("pd.qtyMustBeMoreThanZero", new Object[] {},
						LocaleUtil.getLocalText("pd.qtyMustBeMoreThanZero"));
				errorMsgs.add(LocaleUtil.getLocalText("pd.qtyMustBeMoreThanZero"));
				request.getSession().setAttribute("errorMsgs", errorMsgs);
        		view = "redirect:editPdReturnPurchase.html?strAction=checkPdReturnPurchase&rpNo="
					+ pdReturnPurchase.getRpNo();
//				return showForm(request, response, errors);
			}

//			view = "redirect:pdReturnPurchases.html?strAction=checkPdReturnPurchase";
		} else if ("financePdReturnPurchase".equals(strAction)) {// 财务审核
			financePdReturnPurchase(request, response, pdReturnPurchase);
			key = "notice.pd.finance";
			view = "redirect:pdReturnPurchases.html?strAction=financePdReturnPurchase";
		} else if ("confirmPdReturnPurchase".equals(strAction)) {// 确认
			confirmPdReturnPurchase(request, response, pdReturnPurchase);
			key = "pdReturnPurchase.confirm";
			view = "redirect:pdReturnPurchases.html?strAction=confirmPdReturnPurchase";
		} else if ("addPdReturnPurchase".equals(strAction)) {// 新建
			String rpNo = addPdReturnPurchase(request, response,
					pdReturnPurchase);
			key = "pdReturnPurchase.added";
			view = "redirect:editPdReturnPurchase.html?strAction=editPdReturnPurchase&rpNo="
					+ rpNo;
		} else if ("submitPdReturnPurchase".equals(strAction)) {// 提交
			// String
			// rpNo=addPdReturnPurchase(request,response,pdReturnPurchase);
			view = "redirect:pdReturnPurchases.html?strAction=editPdReturnPurchase";
			if (pdReturnPurchaseManager.getProductCountByRpNo(pdReturnPurchase
					.getRpNo()) > 0) {
				submitPdReturnPurchase(request, response, pdReturnPurchase);
				key = "pdReturnPurchase.check";
			} else {
				errors.reject("pd.qtyMustBeMoreThanZero", new Object[] {},
						LocaleUtil.getLocalText("pd.qtyMustBeMoreThanZero"));
				errorMsgs.add(LocaleUtil.getLocalText("pd.qtyMustBeMoreThanZero"));
				request.getSession().setAttribute("errorMsgs", errorMsgs);
        		view = "redirect:editPdReturnPurchase.html?strAction=editPdReturnPurchase&rpNo="
					+ pdReturnPurchase.getRpNo();
//				return showForm(request, response, errors);
			}
		
			key = "pdReturnPurchase.submit";
			
		} else if ("editPdReturnPurchase".equals(strAction)) {// 编辑
			key = "pd.hasSubmitted";

			if (pdReturnPurchase.getOrderFlag() == -1) {
				editPdReturnPurchase(request, response, pdReturnPurchase);
				key = "pdReturnPurchase.updated";
			}

			view = "redirect:pdReturnPurchases.html?strAction=editPdReturnPurchase";
		} else if ("deletePdReturnPurchase".equals(strAction)) {// 删除
			key = "pd.hasSubmitted";
			if (pdReturnPurchase.getOrderFlag() == -1) {
				pdReturnPurchaseManager.removePdReturnPurchase(pdReturnPurchase
						.getRpNo().toString());
				view = "redirect:pdReturnPurchases.html?strAction=editPdReturnPurchase";
				//modify fu 2015-09-11 beign 发货退回单删除接口消息发送出去瞬间添加发货退回货入库的判断
				String orderFlag = pdReturnPurchaseManager.getPdReturnOrderFlag(pdReturnPurchase.getRpNo().toString());
				if((!StringUtil.isEmpty(orderFlag))&&"3".equals(orderFlag)){
					// 在发货退回单接口消息推送下去之前，判断发货退回单有没有接收到接口发货退回单入库操作，如果有，那么就不允许删除发货退回单
    				MessageUtil.saveLocaleMessage(request, "发货退回单已经接收到接口入库消息，这张发货退回单不可以删除！");
		 		}else{
		 			key = "pdReturnPurchase.delete";
		 			saveMessage(request, getText(sessionLogin.getDefCharacterCoding(), key));
		 		}
				//modify fu 2015-09-11 end
				return new ModelAndView(view);
			}
		} else if ("toNewPdReturnPurchase".equals(strAction)) {
			key = "error.cannotreturn";

			if ((pdReturnPurchase.getOrderFlag() == 1 )
					|| (pdReturnPurchase.getOrderFlag() == 0)) {
				pdReturnPurchase.setOrderFlag(-1);

				key = "operation.notice.tonew";
				pdReturnPurchaseManager.savePdReturnPurchase(pdReturnPurchase);
			}
			view = "redirect:pdReturnPurchases.html?strAction=checkPdReturnPurchase";

		}

		saveMessage(request, getText(sessionLogin.getDefCharacterCoding(), key));
		return new ModelAndView(view);
	}

	private void submitPdReturnPurchase(HttpServletRequest request,
			HttpServletResponse response, PdReturnPurchase pdReturnPurchase) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		pdReturnPurchase.setOrderFlag(0);
		pdReturnPurchase.setEditTime(new Date());
		pdReturnPurchase.setEditUsrCode(sessionLogin.getUserCode());
		pdReturnPurchaseManager.savePdReturnPurchase(pdReturnPurchase);
	}

	/**
	 * 财务审核
	 * 
	 * @param request
	 * @param response
	 * @param pdReturnPurchase
	 */
	private void financePdReturnPurchase(HttpServletRequest request,
			HttpServletResponse response, PdReturnPurchase pdReturnPurchase) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		pdReturnPurchase.setOrderFlag(2);
		pdReturnPurchase.setFinanceUsrCode(sessionLogin.getUserCode());
		pdReturnPurchase.setFinanceTime(new Date());
		pdReturnPurchaseManager.savePdReturnPurchase(pdReturnPurchase);

	}

	/**
	 * 编辑
	 * 
	 * @param request
	 * @param response
	 * @param pdReturnPurchase
	 */
	private void editPdReturnPurchase(HttpServletRequest request,
			HttpServletResponse response, PdReturnPurchase pdReturnPurchase) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);

		pdReturnPurchase.setOrderFlag(-1);
		pdReturnPurchase.setEditUsrCode(sessionLogin.getUserCode());
		pdReturnPurchase.setFinanceTime(new Date());
		pdReturnPurchaseManager.savePdReturnPurchase(pdReturnPurchase);
	}

	/**
	 * 新建
	 * 
	 * @param request
	 * @param response
	 * @param pdReturnPurchase
	 */
	private String addPdReturnPurchase(HttpServletRequest request,
			HttpServletResponse response, PdReturnPurchase pdReturnPurchase) {
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		pdReturnPurchase.setCompanyCode(sessionLogin.getCompanyCode());
		pdReturnPurchase.setCreateUsrCode(sessionLogin.getUserCode());
		pdReturnPurchase.setRpNo(sysIdManager.buildIdStr("rpno"));
		pdReturnPurchase.setCreateTime(new Date());
		pdReturnPurchase.setOrderFlag(-1);
		// pdReturnPurchase.setCheckStatus("1");
		// pdReturnPurchase.setFinanceStatus("1");
		// pdReturnPurchase.setOkStatus("1");
		pdReturnPurchase.setAmount(new BigDecimal(0));
		log.info("xx=" + pdReturnPurchase.getAmount());

		pdReturnPurchaseManager.savePdReturnPurchase(pdReturnPurchase);
		return pdReturnPurchase.getRpNo();
		// TODO Auto-generated method stub
		// AiAgent aiAgent = ;
		// pdReturnPurchase.setAiAgent(aiAgent);
	}

	/**
	 * 确认
	 * 
	 * @param request
	 * @param response
	 * @param pdReturnPurchase
	 */
	private void confirmPdReturnPurchase(HttpServletRequest request,
			HttpServletResponse response, PdReturnPurchase pdReturnPurchase) {
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		pdReturnPurchase.setOkUsrCode(sessionLogin.getUserCode());
		pdReturnPurchase.setOkTime(new Date());
		pdReturnPurchase.setOrderFlag(3);
		// SysUser sysUser = pdReturnPurchase.getSysUser();
		String flag = "1";
		/*
		 * if("Q".equalsIgnoreCase(pdReturnPurchase.getSysUser().getUserType())||
		 * "P".equalsIgnoreCase(pdReturnPurchase.getSysUser().getUserType())){
		 * AiAgent agent =
		 * aiAgentManager.getAiAgent(pdReturnPurchase.getSysUser(
		 * ).getUserCode()); flag = agent.getDeport(); }
		 */
		log.info("11");
		pdReturnPurchaseManager.confirmPdReturnPurchase(pdReturnPurchase, flag);

		// TODO Auto-generated method stub

	}

	/**
	 * 初审
	 * 
	 * @param request
	 * @param response
	 * @param pdReturnPurchase
	 */
	private void checkPdReturnPurchase(HttpServletRequest request,
			HttpServletResponse response, PdReturnPurchase pdReturnPurchase) {
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		pdReturnPurchase.setCheckUsrCode(sessionLogin.getUserCode());
		pdReturnPurchase.setCheckTime(new Date());
		pdReturnPurchase.setOrderFlag(1);
		pdReturnPurchaseManager.savePdReturnPurchase(pdReturnPurchase);
		// TODO Auto-generated method stub
		// request.setAttribute("strAction", "checkPdReturnPurchase");
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		// binder.setAllowedFields(allowedFields);
		// binder.setDisallowedFields(disallowedFields);
		// binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
