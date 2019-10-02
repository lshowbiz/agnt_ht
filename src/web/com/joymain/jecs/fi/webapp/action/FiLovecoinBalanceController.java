package com.joymain.jecs.fi.webapp.action;

import java.util.List;
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
import com.joymain.jecs.fi.model.FiLovecoinBalance;
import com.joymain.jecs.fi.service.FiLovecoinBalanceManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class FiLovecoinBalanceController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiLovecoinBalanceController.class);
    private FiLovecoinBalanceManager fiLovecoinBalanceManager = null;

    public void setFiLovecoinBalanceManager(FiLovecoinBalanceManager fiLovecoinBalanceManager) {
        this.fiLovecoinBalanceManager = fiLovecoinBalanceManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        FiLovecoinBalance fiLovecoinBalance = new FiLovecoinBalance();
        // populate object with request parameters
        BeanUtils.populate(fiLovecoinBalance, request.getParameterMap());

	//List fiLovecoinBalances = fiLovecoinBalanceManager.getFiLovecoinBalances(fiLovecoinBalance);
	/**** auto pagination ***/

        Pager pager = new Pager("fiLovecoinBalanceListTable",request, 20);
        
//        SysUser loginSysUser = SessionLogin.getLoginUser(request);
//		if("C".equals(loginSysUser.getUserType())){
//	        RequestUtil.freshSession(request,"listFiBankbookBalance",3l);
//    	}else if("M".equals(loginSysUser.getUserType())){
//            RequestUtil.freshSession(request,"listFiBankbookBalance",3l);
//    	}
		CommonRecord crm = RequestUtil.toCommonRecord(request);
//		if(StringUtils.isEmpty(request.getParameter("companyCode"))){
//			crm.addField("companyCode", SessionLogin.getLoginUser(request).getCompanyCode());
//		}
		List fiLovecoinBalances =null;
        if(request.getParameter("search")!=null){
		
    		if(RequestUtil.saveOperationSession(request,"listFiBankbookBalance",3l)!=0){
    			return new ModelAndView("redirect:fiLovecoinBalances.html");
    		}
        	
			fiLovecoinBalances = fiLovecoinBalanceManager.getFiLovecoinBalancesByCrm(crm,pager);
        }
		
     // 根据数据重新设置总数
		if(fiLovecoinBalances!=null && fiLovecoinBalances.size()>0){
			request.setAttribute("fiLovecoinBalanceListTable_totalRows", pager.getTotalObjects());
		}else{
			request.setAttribute("fiLovecoinBalanceListTable_totalRows", 0);
		}

        return new ModelAndView("fi/fiLovecoinBalanceList", Constants.FILovecoinBalance_LIST, fiLovecoinBalances);
    }
}
