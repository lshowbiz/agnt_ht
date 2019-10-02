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
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.FiBankbookTemp;
import com.joymain.jecs.fi.model.FiTransferFundbook;
import com.joymain.jecs.fi.service.FiFundbookJournalManager;
import com.joymain.jecs.fi.service.FiTransferAccountManager;
import com.joymain.jecs.fi.service.FiTransferFundbookManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * 产业基金转账单列表管理
 * 内容：转账单审核、撤销
 * @author lenovo
 * 
 */
public class FiTransferFundbookChkController extends BaseController implements
		Controller {
	private final Log log = LogFactory.getLog(FiTransferFundbookChkController.class);
	private FiTransferFundbookManager fiTransferFundbookManager = null;
	
	public void setFiTransferFundbookManager(
			FiTransferFundbookManager fiTransferFundbookManager) {
		this.fiTransferFundbookManager = fiTransferFundbookManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		
		SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
		
		//核准
		if ("checkFiTransferFundbook".equals(request.getParameter("strAction"))) {
			
			//判断当前操作用户
			
			//前台传入的需要转账的单子
			String[] taIds = request.getParameter("strTempIds").split(",");
			List<FiTransferFundbook> fiTransferFundbookList = new ArrayList<FiTransferFundbook>();
			for (int i = 0; i < taIds.length; i++) {
				if (!StringUtils.isEmpty(taIds[i])) {
					
					//取出转账单信息
					FiTransferFundbook fiTransferFundbook = this.fiTransferFundbookManager.getFiTransferFundbook(taIds[i]);
					
					// 判断转账单状态,新单才可以审核
					if (fiTransferFundbook.getStatus() == 1) {
						
						//set核准人信息
						fiTransferFundbook.setCheckerCode(operatorSysUser.getUserCode());
						fiTransferFundbook.setCheckerName(operatorSysUser.getUserName());
						fiTransferFundbook.setCheckeTime(new Date());
						fiTransferFundbook.setDealDate(new Date());
						
						//加入list
						fiTransferFundbookList.add(fiTransferFundbook);
					}
				}
			}
			
			//审核转账 (其中包括1.转账成功，目标账户存入资金；2.修改转账单状态。在同一个事务里面)
			if(fiTransferFundbookList != null){
				try{
					
					fiTransferFundbookManager.checkFiTransferFundbooks(fiTransferFundbookList);
				}catch(AppException ae){
					log.error("错误:", ae);
					//页面跳转和执行结果提示
					saveMessage(request, "审核失败!");
					ModelAndView mv=new ModelAndView("redirect:fiTransferFundbooksChk.html");
					mv.addObject("strAction", "fiTransferFundbookChkList");
					mv.addObject("needReload", "1");
					return mv;
				}
			}
			
			//页面跳转和执行结果提示
			saveMessage(request, "审核成功！");
			ModelAndView mv=new ModelAndView("redirect:fiTransferFundbooksChk.html");
			mv.addObject("strAction", "fiTransferFundbookChkList");
			mv.addObject("needReload", "1");
			return mv;
		}
		
		//撤销
		if ("revokeFiTransferFundbook".equals(request.getParameter("strAction"))) {
			
			//判断当前操作用户
			
			//前台传入的需要转账的单子
			String[] taIds = request.getParameter("strTempIds").split(",");
			List<FiTransferFundbook> fiTransferFundbookList = new ArrayList<FiTransferFundbook>();
			for (int i = 0; i < taIds.length; i++) {
				if (!StringUtils.isEmpty(taIds[i])) {
					
					//取出转账单信息
					FiTransferFundbook fiTransferFundbook = this.fiTransferFundbookManager.getFiTransferFundbook(taIds[i]);
					
					// 判断转账单状态,新单才可以审核
					if (fiTransferFundbook.getStatus() == 1) {
						
						//set核准人信息
						fiTransferFundbook.setCheckerCode(operatorSysUser.getUserCode());
						fiTransferFundbook.setCheckerName(operatorSysUser.getUserName());
						fiTransferFundbook.setCheckeTime(new Date());
						fiTransferFundbook.setDealDate(new Date());
						
						//加入list
						fiTransferFundbookList.add(fiTransferFundbook);
					}
				}
			}
			
			//撤销转账单 (其中包括1.撤销成功，资金退回转账用户；2.修改转账单状态。在同一个事务里面)
			if(fiTransferFundbookList != null){
				try{
			
					fiTransferFundbookManager.revokeFiTransferFundbooks(fiTransferFundbookList);			
				}catch(AppException ae){
					log.error("错误:", ae);
					//页面跳转和执行结果提示
					saveMessage(request, "撤销失败！");
					ModelAndView mv=new ModelAndView("redirect:fiTransferFundbooksChk.html");
					mv.addObject("strAction", "fiTransferFundbookChkList");
					mv.addObject("needReload", "1");
					return mv;
				}
			}
			
			//页面跳转和执行结果提示
			saveMessage(request, "撤销成功！");
			ModelAndView mv=new ModelAndView("redirect:fiTransferFundbooksChk.html");
			mv.addObject("strAction", "fiTransferFundbookChkList");
			mv.addObject("needReload", "1");
			return mv;
		}

		//查询列表
		FiTransferFundbook fiTransferFundbook = new FiTransferFundbook();
		BeanUtils.populate(fiTransferFundbook, request.getParameterMap());
		
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		/** add by hdg 2016-12-23 */
        CustomField[] fields = crm.getFields();
		w:for(CustomField field : fields) {
			String name=field.getName();
			if(!StringUtils.isEmpty(name)) {
				if("endcreatetime".equals(name)) {
					String value = (String)field.getValue();
					if(!StringUtils.isEmpty(value)) {
						field.setValue(value+" 23:59:59");
					}
					break w;
				}
			}
		}
		 /** add by hdg 2016-12-23 */
		Pager pager = new Pager("fiTransferFundbookChkListTable", request, 20);
		List fiTransferFundbooksChk = fiTransferFundbookManager.getFiTransferFundbooksByCrm(crm, pager);
		request.setAttribute("fiTransferFundbookChkListTable_totalRows", pager.getTotalObjects());

		return new ModelAndView("fi/fiTransferFundbookChkList", "fiTransferFundbookChkList", fiTransferFundbooksChk);
	}
}
