package com.joymain.jecs.bd.webapp.action;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.bd.service.BdOutwardBankManager;
import com.joymain.jecs.bd.service.JbdSendRecordManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;


public class BdSendOperaterStatisticReportController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(BdSendOperaterStatisticReportController.class);
    private JbdSendRecordManager jbdSendRecordManager = null;
    public void setJbdSendRecordManager(JbdSendRecordManager jbdSendRecordManager) {
		this.jbdSendRecordManager = jbdSendRecordManager;
	}


	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        String companyCode="";
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        if("C".equals(defSysUser.getUserType())){
        	companyCode=defSysUser.getCompanyCode();  
    		if("AA".equals(defSysUser.getCompanyCode())){
    			companyCode = null;
    		}
    	}

		String wweek=request.getParameter("wweek");
    	CommonRecord crm=RequestUtil.toCommonRecord(request);
        crm.addField("companyCode", companyCode);
        Pager pager = new Pager("bdSendOperaterStatisticListTable",request, 20);


        crm.addField("allot","true");

     

    	if(!StringUtil.isInteger(wweek)){
    		request.setAttribute("bdSendOperaterStatisticListTable_totalRows", pager.getTotalObjects());
    		return new ModelAndView("bd/bdSendOperaterStatisticReportList");
    	}else{
//    		计数总金额
    		
    		List bdSendOperaterStatisticList=jbdSendRecordManager.getBdSendRecordReport(crm);
    		request.setAttribute("bdSendOperaterStatisticListTable_totalRows", pager.getTotalObjects());
    		
    		return new ModelAndView("bd/bdSendOperaterStatisticReportList", "bdSendOperaterStatisticList", bdSendOperaterStatisticList);
    	}
    		
    	

    
    }
}
