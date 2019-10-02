package com.joymain.jecs.po.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 生态家套餐统计
 * @author xiaoman001
 *
 */
public class JpoMemberOrderSTJStatisticController implements Controller {
    private final Log log = LogFactory.getLog(JpoMemberOrderSTJStatisticController.class);
    private JpoMemberOrderManager jpoMemberOrderManagerReport = null;
    
    
    public void setJpoMemberOrderManagerReport(
			JpoMemberOrderManager jpoMemberOrderManagerReport) {
		this.jpoMemberOrderManagerReport = jpoMemberOrderManagerReport;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
        
    	if("C".equals(loginSysUser.getUserType())){
            RequestUtil.freshSession(request,"poMemberOrderStatistic",3l);
    	}else if("M".equals(loginSysUser.getUserType())){
            RequestUtil.freshSession(request,"poMemberOrderStatistic",3l);
    	}

    	if(request.getParameter("search")==null){
            Pager pager = new Pager("jpoMemberOrderSTJListTable",request, 20);
            request.setAttribute("jpoMemberOrderSTJListTable_totalRows", pager.getTotalObjects());
            return new ModelAndView("po/jpoMemberOrderSTJStatistic", "jpoMemberOrderList", null);
        }

        CommonRecord crm = RequestUtil.toCommonRecord(request);
        String isMobile = request.getParameter("isMobile");
        
        crm.addField("ismobile",isMobile);
    	crm.addField("companyCode", loginSysUser.getCompanyCode());
    	if(loginSysUser.getIsMember()){
    		crm.addField("sysUser.userCode",loginSysUser.getUserCode());
    	}
    	if("C".equals(loginSysUser.getUserType())){
    		if("xls".equals(request.getParameter("jpoMemberOrderListTable_ev"))){
        		if(RequestUtil.saveOperationSession(request,"poMemberOrderStatisticXML",20l)!=0){
        			return new ModelAndView("redirect:jpoMemberOrderSTJStatistic.html");
        		}
    		}else{
        		if(RequestUtil.saveOperationSession(request,"poMemberOrderStatistic",3l)!=0){
        			return new ModelAndView("redirect:jpoMemberOrderSTJStatistic.html");
        		}
    		}
    	}else if("M".equals(loginSysUser.getUserType())){
    		if(RequestUtil.saveOperationSession(request,"poMemberOrderStatistic",3l)!=0){
    			return new ModelAndView("redirect:jpoMemberOrderSTJStatistic.html");
    		}
    	}
    	
        Pager pager = new Pager("jpoMemberOrderListTable",request, 20);
        List jpoMemberOrders = jpoMemberOrderManagerReport.getJpoMemberOrdersSTJByCrm(crm,pager);
      
        Map sumMap = jpoMemberOrderManagerReport.getSumAmountSTJByCrm(crm);
        request.setAttribute("sumMap", sumMap);
        
        crm.addField("stj", "stj");
        List products = jpoMemberOrderManagerReport.getStatisticProductSale(crm);
     
        request.setAttribute("jpoMemberOrderListTable_totalRows", pager.getTotalObjects());
        
        return new ModelAndView("po/jpoMemberOrderSTJStatistic","jpoMemberOrderList",jpoMemberOrders).addObject("pmProductList", products);
    }

}
