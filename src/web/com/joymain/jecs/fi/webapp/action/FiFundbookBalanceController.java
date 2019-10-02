package com.joymain.jecs.fi.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.FiFundbookBalance;
import com.joymain.jecs.fi.service.FiFundbookBalanceManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class FiFundbookBalanceController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiFundbookBalanceController.class);
    private FiFundbookBalanceManager fiFundbookBalanceManager = null;

    public void setFiFundbookBalanceManager(FiFundbookBalanceManager fiFundbookBalanceManager) {
        this.fiFundbookBalanceManager = fiFundbookBalanceManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

//        FiFundbookBalance fiFundbookBalance = new FiFundbookBalance();
//        // populate object with request parameters
//        BeanUtils.populate(fiFundbookBalance, request.getParameterMap());
//
//	//List fiFundbookBalances = fiFundbookBalanceManager.getFiFundbookBalances(fiFundbookBalance);
//	/**** auto pagination ***/
//	CommonRecord crm=RequestUtil.toCommonRecord(request);
//        Pager pager = new Pager("fiFundbookBalanceListTable",request, 20);
//        List fiFundbookBalances = fiFundbookBalanceManager.getFiFundbookBalancesByCrm(crm,pager);
//        request.setAttribute("fiFundbookBalanceListTable_totalRows", pager.getTotalObjects());
//        /*****/
        
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
		if("C".equals(loginSysUser.getUserType())){
	        RequestUtil.freshSession(request,"listFiFundbookBalanceJJ",3l);
    	}else if("M".equals(loginSysUser.getUserType())){
            RequestUtil.freshSession(request,"listFiFundbookBalanceJJ",3l);
    	}

		CommonRecord crm = RequestUtil.toCommonRecord(request);
		if(StringUtils.isEmpty(request.getParameter("companyCode"))){
			crm.addField("companyCode", SessionLogin.getLoginUser(request).getCompanyCode());
		}

		Pager pager = new Pager("fiFundbookBalanceListTable", request, 20);
		List fiFundbookBalances = null;
		
		if(request.getParameter("search")!=null){
			if("C".equals(loginSysUser.getUserType())){
				if("xls".equals(request.getParameter("fiFundbookBalanceListTable_ev"))){
	        		if(RequestUtil.saveOperationSession(request,"listFiFundbookBalanceXML",20l)!=0){
	        			return new ModelAndView("redirect:fiFundbookBalances.html");
	        		}
	    		}else{
	    			if(RequestUtil.saveOperationSession(request,"listFiFundbookBalanceJJ",3l)!=0){
	        			return new ModelAndView("redirect:fiFundbookBalances.html");
	        		}
	    		}
        	}else if("M".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"listFiFundbookBalanceJJ",3l)!=0){
        			return new ModelAndView("redirect:fiFundbookBalances.html");
        		}
        	}
			fiFundbookBalances=this.fiFundbookBalanceManager.getFiFundbookBalancesByCrm(crm, pager);
		}
		// 根据数据重新设置总数
		if(fiFundbookBalances!=null && fiFundbookBalances.size()>0){
			request.setAttribute("fiFundbookBalanceListTable_totalRows", pager.getTotalObjects());
		}else{
			request.setAttribute("fiFundbookBalanceListTable_totalRows", 0);
		}

        return new ModelAndView("fi/fiFundbookBalanceList", "fiFundbookBalanceList", fiFundbookBalances);
    }
}
