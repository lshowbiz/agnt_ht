package com.joymain.jecs.fi.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.service.FiBankbookBalanceManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class FiProductPointBalanceController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiProductPointBalanceController.class);
    private FiBankbookBalanceManager fiBankbookBalanceManager = null;

    public void setFiBankbookBalanceManager(FiBankbookBalanceManager fiBankbookBalanceManager) {
        this.fiBankbookBalanceManager = fiBankbookBalanceManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
		if("C".equals(loginSysUser.getUserType())){
	        RequestUtil.freshSession(request,"listFiProductPointBalance",3l);
    	}else if("M".equals(loginSysUser.getUserType())){
            RequestUtil.freshSession(request,"listFiProductPointBalance",3l);
    	}

		CommonRecord crm = RequestUtil.toCommonRecord(request);
		if(StringUtils.isEmpty(request.getParameter("companyCode"))){
			crm.addField("companyCode", SessionLogin.getLoginUser(request).getCompanyCode());
		}

		Pager pager = new Pager("fiBankbookBalanceListTable", request, 20);
		List fiBankbookBalances = null;
		
		if(request.getParameter("search")!=null){
			/*if("C".equals(loginSysUser.getUserType())){
				if("xls".equals(request.getParameter("fiBankbookBalanceListTable_ev"))){
	        		if(RequestUtil.saveOperationSession(request,"listFiBankbookBalanceXML",20l)!=0){
	        			return new ModelAndView("redirect:fiBankbookBalances.html");
	        		}
	    		}else{
	    			if(RequestUtil.saveOperationSession(request,"listFiBankbookBalanceJJ",3l)!=0){
	        			return new ModelAndView("redirect:fiBankbookBalances.html");
	        		}
	    		}
        	}else if("M".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"listFiBankbookBalanceJJ",3l)!=0){
        			return new ModelAndView("redirect:fiBankbookBalances.html");
        		}
        	}*/
			fiBankbookBalances=this.fiBankbookBalanceManager.getFiProductPointBalancesByCrm(crm, pager);
		}
		// 根据数据重新设置总数
		if(fiBankbookBalances!=null && fiBankbookBalances.size()>0){
			request.setAttribute("fiBankbookBalanceListTable_totalRows", pager.getTotalObjects());
		}else{
			request.setAttribute("fiBankbookBalanceListTable_totalRows", 0);
		}
		
        return new ModelAndView("fi/fiProductPointBalanceList", Constants.FIPRODUCTPOINTBALANCE_LIST, fiBankbookBalances);
    }
}
