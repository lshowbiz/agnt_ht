package com.joymain.jecs.fi.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.ServerDateUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.FiBankbookTemp;
import com.joymain.jecs.fi.model.FiTransferAccount;
import com.joymain.jecs.fi.service.FiTransferAccountManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * 会员转账单列表管理
 * 内容：转账单审核、撤销
 * @author lenovo
 * 
 */
public class FiTransferAccountChkController extends BaseController implements
		Controller {
	private final Log log = LogFactory
			.getLog(FiTransferAccountChkController.class);
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
		
		SysUser loginSysUser = SessionLogin.getLoginUser(request);
		
		//核准
		if ("checkFiTransferAccount".equals(request.getParameter("strAction"))) {
			
			//判断当前操作用户
			
			//前台传入的需要转账的单子
			String[] taIds = request.getParameter("strTempIds").split(",");
			List<FiTransferAccount> fiTransferAccountList = new ArrayList<FiTransferAccount>();
			for (int i = 0; i < taIds.length; i++) {
				if (!StringUtils.isEmpty(taIds[i])) {
					
					//取出转账单信息
					FiTransferAccount fiTransferAccount = this.fiTransferAccountManager.getFiTransferAccount(taIds[i]);
					
					// 判断转账单状态,新单才可以审核
					if (fiTransferAccount.getStatus() == 1) {
						
						//set核准人信息
						fiTransferAccount.setCheckerCode(loginSysUser.getUserCode());
						fiTransferAccount.setCheckerName(loginSysUser.getUserName());
						fiTransferAccount.setCheckeTime(new Date());
						fiTransferAccount.setDealDate(new Date());
						
						//加入list
						fiTransferAccountList.add(fiTransferAccount);
					}
				}
			}
			
			//审核转账 (其中包括1.转账成功，目标账户存入资金；2.修改转账单状态。在同一个事务里面)
			if(fiTransferAccountList != null){
				try{
					
					fiTransferAccountManager.checkTransferAccounts(fiTransferAccountList);
				}catch(AppException ae){
					log.error("错误:", ae);
					//页面跳转和执行结果提示
					saveMessage(request, LocaleUtil.getLocalText("fiTransferAccount.check.error"));
					ModelAndView mv=new ModelAndView("redirect:fiTransferAccountsChk.html");
					mv.addObject("strAction", "fiTransferAccountChkList");
					mv.addObject("needReload", "1");
					return mv;
				}
			}
			
			//页面跳转和执行结果提示
			saveMessage(request, LocaleUtil.getLocalText("fiTransferAccount.check.success"));
			ModelAndView mv=new ModelAndView("redirect:fiTransferAccountsChk.html");
			mv.addObject("strAction", "fiTransferAccountChkList");
			mv.addObject("needReload", "1");
			return mv;
		}
		
		//撤销
		if ("revokeFiTransferAccount".equals(request.getParameter("strAction"))) {
			
			//判断当前操作用户
			
			//前台传入的需要转账的单子
			String[] taIds = request.getParameter("strTempIds").split(",");
			List<FiTransferAccount> fiTransferAccountList = new ArrayList<FiTransferAccount>();
			for (int i = 0; i < taIds.length; i++) {
				if (!StringUtils.isEmpty(taIds[i])) {
					
					//取出转账单信息
					FiTransferAccount fiTransferAccount = this.fiTransferAccountManager.getFiTransferAccount(taIds[i]);
					
					// 判断转账单状态,新单才可以审核
					if (fiTransferAccount.getStatus() == 1) {
						
						//set核准人信息
						fiTransferAccount.setCheckerCode(loginSysUser.getUserCode());
						fiTransferAccount.setCheckerName(loginSysUser.getUserName());
						fiTransferAccount.setCheckeTime(new Date());
						fiTransferAccount.setDealDate(new Date());
						
						//加入list
						fiTransferAccountList.add(fiTransferAccount);
					}
				}
			}
			
			//撤销转账单 (其中包括1.撤销成功，资金退回转账用户；2.修改转账单状态。在同一个事务里面)
			if(fiTransferAccountList != null){
				try{
			
					fiTransferAccountManager.revokeTransferAccounts(fiTransferAccountList);			
				}catch(AppException ae){
					log.error("错误:", ae);
					//页面跳转和执行结果提示
					saveMessage(request, LocaleUtil.getLocalText("fiTransferAccount.revoke.error"));
					ModelAndView mv=new ModelAndView("redirect:fiTransferAccountsChk.html");
					mv.addObject("strAction", "fiTransferAccountChkList");
					mv.addObject("needReload", "1");
					return mv;
				}
			}
			
			//页面跳转和执行结果提示
			saveMessage(request, LocaleUtil.getLocalText("fiTransferAccount.revoke.success"));
			ModelAndView mv=new ModelAndView("redirect:fiTransferAccountsChk.html");
			mv.addObject("strAction", "fiTransferAccountChkList");
			mv.addObject("needReload", "1");
			return mv;
		}

		//查询列表
		FiTransferAccount fiTransferAccount = new FiTransferAccount();
		BeanUtils.populate(fiTransferAccount, request.getParameterMap());
		
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		
		Pager pager = new Pager("fiTransferAccountChkListTable", request, 20);
		List fiTransferAccountsChk = fiTransferAccountManager.getFiTransferAccountsByCrm(crm, pager);
		request.setAttribute("fiTransferAccountChkListTable_totalRows", pager.getTotalObjects());

		return new ModelAndView("fi/fiTransferAccountChkList",
				Constants.FITRANSFERACCOUNTCHK_LIST, fiTransferAccountsChk);
	}
}
