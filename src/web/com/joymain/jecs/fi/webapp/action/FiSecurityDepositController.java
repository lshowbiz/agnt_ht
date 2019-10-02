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
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.FiSecurityDeposit;
import com.joymain.jecs.fi.service.FiSecurityDepositManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class FiSecurityDepositController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiSecurityDepositController.class);
    private FiSecurityDepositManager fiSecurityDepositManager = null;

    public void setFiSecurityDepositManager(FiSecurityDepositManager fiSecurityDepositManager) {
        this.fiSecurityDepositManager = fiSecurityDepositManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        //当前操作用户
		SysUser operationSysUser = SessionLogin.getLoginUser(request);
		//系统规定的保证金额度
		String limit = ConfigUtil.getConfigValue(operationSysUser.getCompanyCode().toUpperCase(), "security.deposit.limit");
		
        String isMakeUp=request.getParameter("makeup");
        //单个补齐
        if(("1").equals(isMakeUp)){
        	
        	String fsdId=request.getParameter("fsdId");
        	if(!StringUtil.isEmpty(fsdId)){
        		fiSecurityDepositManager.makeUpFiSecurityDeposit(fsdId, operationSysUser, limit);
        	}
        	
        	//页面跳转和执行结果提示
			saveMessage(request, "执行完成！如果会员电子存折余额不足，将没法补齐，具体请参考执行结果！");
			ModelAndView mv=new ModelAndView("redirect:fiSecurityDeposits.html");
			mv.addObject("needReload", "1");
			return mv;
        }
        //全部补齐
        if(("all").equals(isMakeUp)){
        	
        	fiSecurityDepositManager.makeUpAllFiSecurityDeposit(operationSysUser, limit);
        	
        	//页面跳转和执行结果提示
        	saveMessage(request, "执行完成！会员电子存折余额不足的，将没法补齐，具体请参考执行结果！");
			ModelAndView mv=new ModelAndView("redirect:fiSecurityDeposits.html");
			mv.addObject("needReload", "1");
			return mv;
        }
        
        FiSecurityDeposit fiSecurityDeposit = new FiSecurityDeposit();
        // populate object with request parameters
        BeanUtils.populate(fiSecurityDeposit, request.getParameterMap());

        //List fiSecurityDeposits = fiSecurityDepositManager.getFiSecurityDeposits(fiSecurityDeposit);
        /**** auto pagination ***/
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("fiSecurityDepositListTable",request, 20);
        List fiSecurityDeposits = fiSecurityDepositManager.getFiSecurityDepositsByCrm(crm,pager);
        request.setAttribute("fiSecurityDepositListTable_totalRows", pager.getTotalObjects());
        /*****/
        
        request.setAttribute("securityDepositLimit",limit);

        return new ModelAndView("fi/fiSecurityDepositList", Constants.FISECURITYDEPOSIT_LIST, fiSecurityDeposits);
    }
}
