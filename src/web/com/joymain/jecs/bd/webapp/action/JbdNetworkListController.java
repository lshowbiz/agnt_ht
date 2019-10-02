package com.joymain.jecs.bd.webapp.action;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.JbdNetworkList;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.bd.service.JbdNetworkListManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdNetworkListController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdNetworkListController.class);
    private JbdNetworkListManager jbdNetworkListManager = null;
    private BdPeriodManager bdPeriodManager;
    public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public void setJbdNetworkListManager(JbdNetworkListManager jbdNetworkListManager) {
        this.jbdNetworkListManager = jbdNetworkListManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        SysUser defSysUser = SessionLogin.getLoginUser(request);

        CommonRecord crm=RequestUtil.toCommonRecord(request);
        
//        WeekFormatUtil.setSearchFweek(crm);
        
        
         
        
        
        
        if("M".equals(defSysUser.getUserType())){
        	crm.addField("userCode", defSysUser.getUserCode());
        }else{
        	crm.addField("companyCode", defSysUser.getCompanyCode());
        }
        
        String startWeek=request.getParameter("startWeek");

        crm.addField("startWeek", WeekFormatUtil.getFormatWeek("f",startWeek));
        
        

        String endWeek=request.getParameter("endWeek");

        crm.addField("endWeek", WeekFormatUtil.getFormatWeek("f",endWeek));
        
        Pager pager = new Pager("jbdNetworkListListTable",request, 20);
        

        String userCode=request.getParameter("userCode");
        
        List<JbdNetworkList> jbdNetworkLists = null;
        
        if(!StringUtil.isEmpty(userCode) || !StringUtil.isEmpty(startWeek) ||!StringUtil.isEmpty(endWeek)){
        	jbdNetworkLists = jbdNetworkListManager.getJbdNetworkListsByCrm(crm,pager);
        	Object[] sumObject=jbdNetworkListManager.getJbdNetworkListsByCrmSum(crm);

        	request.setAttribute("sumnetworkMoney", sumObject[0]);
        	request.setAttribute("sumbalanceMoney", sumObject[1]);
        	request.setAttribute("sumdeductMoney", sumObject[2]);
        }
        
        
        
        
        
        
        
        
        request.setAttribute("jbdNetworkListListTable_totalRows", pager.getTotalObjects());
        
        

        return new ModelAndView("bd/jbdNetworkListList", Constants.JBDNETWORKLIST_LIST, jbdNetworkLists);
    }
}
