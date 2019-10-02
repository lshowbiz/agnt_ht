package com.joymain.jecs.fi.webapp.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.service.JbdSendNoteManager;
import com.joymain.jecs.bd.service.impl.JbdSendNoteManagerImpl;
import com.joymain.jecs.fi.model.JfiBankbookBalance;
import com.joymain.jecs.fi.model.JfiBankbookJournal;
import com.joymain.jecs.fi.service.JfiBankbookBalanceManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 提现
 * @author Alvin
 *
 */
public class JfiMoneyController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiMoneyController.class);
    private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;
    private SysUserManager sysUserManager = null;
    private JbdSendNoteManager jbdSendNoteManager = null;

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

    	SysUser loginSysUser = SessionLogin.getLoginUser(request);
    	if("M".equals(loginSysUser.getUserType())){
    		JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(loginSysUser.getUserCode());
    		request.setAttribute("amountall", jfiBankbookBalance.getBalance());
    	}
    	request.setAttribute("time", new Date().getTime());
    	
    	if("post".equalsIgnoreCase(request.getMethod())){
    		String amount1 = request.getParameter("amount1");
    		String amount2 = request.getParameter("amount2");
    		String amount3 = request.getParameter("amount3");
    		String userCode = request.getParameter("userCode");
    		String password = request.getParameter("password");
    		BigDecimal amount = new BigDecimal(0);
    		SysUser sysUser = null;
    		if(StringUtil.isEmpty(amount1) || StringUtil.isEmpty(amount2) || StringUtil.isEmpty(amount3)){
        		saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"fiMoney.fail.amount"));
    			return new ModelAndView("fi/jfiMoneyList");
    		}else{
    			try{
    				int amount1Int = Integer.parseInt(amount1);
    				int amount2Int = Integer.parseInt(amount2);
    				int amount3Int = Integer.parseInt(amount3);
    				if(amount1Int < 0){
                		saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"fiMoney.fail.amount"));
            			return new ModelAndView("fi/jfiMoneyList");
    				}
    				if(amount2Int < 0 || amount2Int > 9){
                		saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"fiMoney.fail.amount"));
            			return new ModelAndView("fi/jfiMoneyList");
    				}
    				if(amount3Int < 0 || amount3Int > 9){
                		saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"fiMoney.fail.amount"));
            			return new ModelAndView("fi/jfiMoneyList");
    				}
    			}catch(Exception e){
            		saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"fiMoney.fail.amount"));
        			return new ModelAndView("fi/jfiMoneyList");
    			}
    			BigDecimal amount1Big = new BigDecimal(amount1);
    			BigDecimal amount2Big = new BigDecimal(amount2);
    			BigDecimal amount3Big = new BigDecimal(amount3);
    			
    			amount = amount1Big.add(amount2Big.divide(new BigDecimal("10")));
    			amount = amount.add(amount3Big.divide(new BigDecimal("100")));
    		}
    		String amountall = request.getParameter("amountall");
    		if(!StringUtil.isEmpty(amountall)){
    			amount = new BigDecimal(amountall); 
    		}
			if(amount.compareTo(new BigDecimal("3"))!=1){
        		saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"fiMoney.fail.amount"));
    			return new ModelAndView("fi/jfiMoneyList");
			}
    		if(StringUtil.isEmpty(userCode)){
        		saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"fiMoney.fail.userCode"));
    			return new ModelAndView("fi/jfiMoneyList");
    		}else{
    			sysUser = sysUserManager.getSysUser(userCode);
    			if(sysUser==null){
    				saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"fiMoney.fail.userCode"));
        			return new ModelAndView("fi/jfiMoneyList");
    			}
    			if("M".equals(loginSysUser.getUserType()) && !sysUser.getUserCode().equals(loginSysUser.getUserCode())){
            		saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"fiMoney.fail.userCode"));
        			return new ModelAndView("fi/jfiMoneyList");
    			}
    		}
    		if("M".equals(loginSysUser.getUserType()) && StringUtil.isEmpty(password)){
        		saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"fiMoney.fail.password"));
    			return new ModelAndView("fi/jfiMoneyList");
    		}
    		if("M".equals(loginSysUser.getUserType())){
    			String passwordMd5 = StringUtil.encodePassword(password, "md5");
    			if(!passwordMd5.equals(loginSysUser.getPassword2())){
            		saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"fiMoney.fail.password"));
        			return new ModelAndView("fi/jfiMoneyList");
    			}
    		}
    		if(!loginSysUser.getCompanyCode().equals(sysUser.getCompanyCode())){
    			saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"fiMoney.fail.userCode"));
    			return new ModelAndView("fi/jfiMoneyList");
    		}
    		JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(sysUser.getUserCode());
    		if(amount.compareTo(jfiBankbookBalance.getBalance())==1){
        		saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"fiMoney.fail.amount.notEnough"));
    			return new ModelAndView("fi/jfiMoneyList");
    		}else{
    			String time = request.getParameter("time");
    			if(StringUtil.isEmpty(time)){
    		    	request.setAttribute("time", new Date().getTime());
        			return new ModelAndView("fi/jfiMoneyList");
    			}
    			jbdSendNoteManager.saveJbdSendNoteAndDeductBankbook(sysUser.getCompanyCode(), sysUser, loginSysUser.getUserCode(), loginSysUser.getUserName(), amount,time);
    		}
        	saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"fiMoney.success"));
            return new ModelAndView("redirect:jbdSendNotes.html");
    	}
        return new ModelAndView("fi/jfiMoneyList");
    }

	public void setJfiBankbookBalanceManager(
			JfiBankbookBalanceManager jfiBankbookBalanceManager) {
		this.jfiBankbookBalanceManager = jfiBankbookBalanceManager;
	}

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public void setJbdSendNoteManager(JbdSendNoteManager jbdSendNoteManager) {
		this.jbdSendNoteManager = jbdSendNoteManager;
	}
}
