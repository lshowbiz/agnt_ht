package com.joymain.jecs.fi.webapp.action;

import java.util.Date;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.ServerDateUtil;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.fi.model.FiTransferAccount;
import com.joymain.jecs.fi.service.FiTransferAccountManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建会员转账申请单
 * @author lenovo
 * 
 */
public class FiTransferAccountFormController extends BaseFormController {
	private FiTransferAccountManager fiTransferAccountManager = null;
	private SysUserManager sysUserManager = null;

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}
	public void setFiTransferAccountManager(
			FiTransferAccountManager fiTransferAccountManager) {
		this.fiTransferAccountManager = fiTransferAccountManager;
	}

	public FiTransferAccountFormController() {
		setCommandName("fiTransferAccount");
		setCommandClass(FiTransferAccount.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		
		FiTransferAccount fiTransferAccount = new FiTransferAccount();
		
		return fiTransferAccount;
	}

	//提交转账申请单
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		FiTransferAccount fiTransferAccount = (FiTransferAccount) command;
		String strAction = request.getParameter("strAction");
		//当前用户
		SysUser loginSysUser = SessionLogin.getLoginUser(request);

		if ("addFiTransferAccount".equals(strAction)) {
			
			log.debug("进入addFiTransferAccount");
			//验证转账目标用户是否存在
			SysUser destinationUser=this.sysUserManager.getSysUser(fiTransferAccount.getDestinationUserCode());
			if(destinationUser==null || !destinationUser.getUserType().equals("M")){
				
				//如果不存在
				MessageUtil.saveLocaleMessage(request, LocaleUtil.getLocalText("error.destination.not.existed"));
				return showForm(request, response, errors);	
			}
			log.debug("验证转账目标用户不能为自己");
			
			//验证转账目标用户不能为自己
			if(destinationUser.getUserCode().equals(loginSysUser.getUserCode())){
				
				//如果转账目标用户填写为自己
				MessageUtil.saveLocaleMessage(request, LocaleUtil.getLocalText("error.destinationuser.error"));
				return showForm(request, response, errors);	
			}
			log.debug("验证单笔转账交易额度控制");
			//单笔转账交易额度控制，采用自动化配置方式			
			String limitNum = ConfigUtil.getConfigValue(loginSysUser.getCompanyCode().toUpperCase(), "transferaccount.max.value");
			BigDecimal limit = new BigDecimal(limitNum);
			
			//如果单笔交易额度不小于5000
			if(limit.compareTo(fiTransferAccount.getMoney()) == -1 || limit.compareTo(fiTransferAccount.getMoney()) == 0){
				
				//超过额度限制提示
				MessageUtil.saveLocaleMessage(request, LocaleUtil.getLocalText("fiTransferAccount.paymax.error")+limitNum+"");
				return showForm(request, response, errors);	
			}
			
			//单日转账交易额度控制，采用自动化配置方式
			String limitDayNum = ConfigUtil.getConfigValue(loginSysUser.getCompanyCode().toUpperCase(), "transferaccount.daymax.value");
			BigDecimal limitDay = new BigDecimal(limitDayNum);
			
			//获取当前用户当日转账总额
			BigDecimal sumDayTransferValue = fiTransferAccountManager.getSumTransferValueByTransferCode(loginSysUser.getUserCode());
			BigDecimal sumDayTransferValues=sumDayTransferValue.add(fiTransferAccount.getMoney());
			
			//如果单日最高额度不小于2W
			if(limitDay.compareTo(sumDayTransferValues) == -1){
				
				MessageUtil.saveLocaleMessage(request, LocaleUtil.getLocalText("fiTransferAccount.paydaymax.error")+limitDayNum+"");
				return showForm(request, response, errors);	
			}
			log.debug("验证转账支付密码");
			//前台输入支付密码
			String transferPayPwd = request.getParameter("paypwd");
			
			//验证转账支付密码(提现密码)
			if(transferPayPwd==null || !StringUtil.encodePassword(transferPayPwd, "md5").equalsIgnoreCase(loginSysUser.getPassword2())){
				
				MessageUtil.saveLocaleMessage(request, LocaleUtil.getLocalText("fiTransferAccount.paypwd.error"));
				return showForm(request, response, errors);	
			}
							
			//设置当前转账用户
			fiTransferAccount.setTransferUserCode(loginSysUser.getUserCode());
			
			fiTransferAccount.setCreaterCode(loginSysUser.getUserCode());
			fiTransferAccount.setCreaterName(loginSysUser.getUserName());
			fiTransferAccount.setCreateTime(new Date());
							
			try{
				log.debug("创建转账单start");
				//创建转账单
				fiTransferAccountManager.addTransferAccounts(fiTransferAccount, loginSysUser);
				log.debug("创建转账单end");
			}catch(AppException ae){
				
				log.error("错误:", ae);
				
				//如果创建失败,提示：转账单创建失败，请查询余额是否足够
				MessageUtil.saveLocaleMessage(request, LocaleUtil.getLocalText("fiTransferAccount.add.error"));
				return showForm(request, response, errors);	
			}
			//页面跳转和执行结果提示
			saveMessage(request, LocaleUtil.getLocalText("fiTransferAccount.add.success"));
		}
		
		ModelAndView mv=new ModelAndView("redirect:fiTransferAccounts.html");
		mv.addObject("strAction", "fiTransferAccountList");
		mv.addObject("needReload", "1");
		return mv;
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		// binder.setAllowedFields(allowedFields);
		// binder.setDisallowedFields(disallowedFields);
		// binder.setRequiredFields(requiredFields);
		String[] allowedFields = {
    			"destinationUserCode",
    			"money",
    			"notes",
    			"money",
    			"paypwd"
    			};
    	binder.setAllowedFields(allowedFields);
		super.initBinder(request, binder);
	}
}
