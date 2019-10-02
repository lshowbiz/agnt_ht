package com.joymain.jecs.po.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

@SuppressWarnings("unchecked")
public class JpoEcoPackageStatisticsController implements Controller {
	private final Log log = LogFactory.getLog(JpoEcoPackageStatisticsController.class);

	 private JpoMemberOrderManager jpoMemberOrderManagerReport = null;

	public void setJpoMemberOrderManagerReport(
			JpoMemberOrderManager jpoMemberOrderManagerReport) {
		this.jpoMemberOrderManagerReport = jpoMemberOrderManagerReport;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'JpoEcoPackageStatisticsController.handleRequest' method...");
		}

		SysUser loginSysUser = SessionLogin.getLoginUser(request);

		CommonRecord crm = RequestUtil.toCommonRecord(request);

		if(request.getParameter("search")==null){
            Pager pager = new Pager("jpoMemberOrderListTable",request, 20);
            request.setAttribute("jpoMemberOrderListTable_totalRows", pager.getTotalObjects());
            return new ModelAndView("po/jpoMemberOrderStatisticSTJ", Constants.JPOMEMBERORDER_LIST, null);
        }

        String isMobile = request.getParameter("isMobile");
        
        crm.addField("productFlag", "0");
//        crm.addField("sysUser.jmiMember.papernumber", loginSysUser.getJmiMember().getPapernumber());
        crm.addField("ismobile",isMobile);
    	crm.addField("companyCode", loginSysUser.getCompanyCode());
    	if(loginSysUser.getIsMember()){
//    		crm.addField("sysUser.userCode",loginSysUser.getUserCode());
    		crm.addField("sysUser.jmiMember.papernumber",loginSysUser.getJmiMember().getPapernumber());
    	}
    	
    	if("C".equals(loginSysUser.getUserType())){
    		if("xls".equals(request.getParameter("jpoMemberOrderListTable_ev"))){
        		if(RequestUtil.saveOperationSession(request,"poMemberOrderStatisticXML",20l)!=0){
        			return new ModelAndView("redirect:jpoEcoPackageStatistics.html");
        		}
    		}else{
        		if(RequestUtil.saveOperationSession(request,"poMemberOrderStatisticSTJ",3l)!=0){
        			return new ModelAndView("redirect:jpoEcoPackageStatistics.html");
        		}
    		}
    	}else if("M".equals(loginSysUser.getUserType())){
    		if(RequestUtil.saveOperationSession(request,"poMemberOrderStatisticSTJ",3l)!=0){
    			return new ModelAndView("redirect:jpoEcoPackageStatistics.html");
    		}
    	}
    	
        Pager pager = new Pager("jpoMemberOrderListTable",request, 20);
        List jpoMemberOrders = jpoMemberOrderManagerReport.getJpoMemberOrdersByCrm(crm,pager);
      
        Map sumMap = jpoMemberOrderManagerReport.getSumAmountByCrm(crm);
        request.setAttribute("sumMap", sumMap);
        
        List products = jpoMemberOrderManagerReport.getStatisticProductSale(crm);
        
        request.setAttribute("jpoMemberOrderListTable_totalRows", pager.getTotalObjects());
        
        return new ModelAndView("po/jpoMemberOrderStatisticSTJ","jpoMemberOrderList",jpoMemberOrders).addObject("pmProductList", products);

	}

}
