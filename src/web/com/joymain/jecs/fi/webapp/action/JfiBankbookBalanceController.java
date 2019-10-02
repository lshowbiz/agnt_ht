package com.joymain.jecs.fi.webapp.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.service.JfiBankbookBalanceManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 存摺余额统计
 * @author Alvin
 *
 */
public class JfiBankbookBalanceController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiBankbookBalanceController.class);
    private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;

    public void setJfiBankbookBalanceManager(JfiBankbookBalanceManager jfiBankbookBalanceManager) {
        this.jfiBankbookBalanceManager = jfiBankbookBalanceManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        SysUser loginSysUser = SessionLogin.getLoginUser(request);
		if("C".equals(loginSysUser.getUserType())){
	        RequestUtil.freshSession(request,"listFiBankbookBalance",3l);
    	}else if("M".equals(loginSysUser.getUserType())){
            RequestUtil.freshSession(request,"listFiBankbookBalance",3l);
    	}
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		if(StringUtils.isEmpty(request.getParameter("companyCode"))){
			crm.addField("companyCode", SessionLogin.getLoginUser(request).getCompanyCode());
		}

		Pager pager = new Pager("jfiBankbookBalanceListTable", request, 20);
		List jfiBankbookBalances = null;
		
		if(request.getParameter("search")!=null){
			if("C".equals(loginSysUser.getUserType())){
				if("xls".equals(request.getParameter("jfiBankbookBalanceListTable_ev"))){
	        		if(RequestUtil.saveOperationSession(request,"listFiBankbookBalanceXML",20l)!=0){
	        			return new ModelAndView("redirect:jfiBankbookBalances.html");
	        		}
	    		}else{
	    			if(RequestUtil.saveOperationSession(request,"listFiBankbookBalance",3l)!=0){
	        			return new ModelAndView("redirect:jfiBankbookBalances.html");
	        		}
	    		}
        	}else if("M".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"listFiBankbookBalance",3l)!=0){
        			return new ModelAndView("redirect:jfiBankbookBalances.html");
        		}
        	}
			jfiBankbookBalances=this.jfiBankbookBalanceManager.getJfiBankbookBalancesByCrm(crm, pager);
		}
		// 根据数据重新设置总数
		if(jfiBankbookBalances!=null && jfiBankbookBalances.size()>0){
			request.setAttribute("jfiBankbookBalanceListTable_totalRows", pager.getTotalObjects());
		}else{
			request.setAttribute("jfiBankbookBalanceListTable_totalRows", 0);
		}
		
        return new ModelAndView("fi/jfiBankbookBalanceList", Constants.JFIBANKBOOKBALANCE_LIST, jfiBankbookBalances);
    }
}
