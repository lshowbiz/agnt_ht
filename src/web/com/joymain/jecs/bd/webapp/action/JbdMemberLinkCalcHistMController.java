package com.joymain.jecs.bd.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.bd.service.JbdSendRecordHistManager;
import com.joymain.jecs.mi.service.JmiLinkRefManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdMemberLinkCalcHistMController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdMemberLinkCalcHistMController.class);
    private JbdSendRecordHistManager jbdSendRecordHistManager;
    private JmiLinkRefManager jmiLinkRefManager = null;
    public void setJmiLinkRefManager(JmiLinkRefManager jmiLinkRefManager) {
		this.jmiLinkRefManager = jmiLinkRefManager;
	}
    public void setJbdSendRecordHistManager(
			JbdSendRecordHistManager jbdSendRecordHistManager) {
		this.jbdSendRecordHistManager = jbdSendRecordHistManager;
	}

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        CommonRecord crm=RequestUtil.toCommonRecord(request);
        String companyCode="";
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        if("C".equals(defSysUser.getUserType())){
        	companyCode=defSysUser.getCompanyCode();  
    		if("AA".equals(defSysUser.getCompanyCode())){
    			companyCode=request.getParameter("companyCode");
    		}
    	}

        Pager pager = new Pager("bdSendRecordListTable",request, 20);
        
        String memberNo= crm.getString("userCode",request.getParameter("userCode"));
        String wweek= crm.getString("wweek",request.getParameter("wweek"));
        String name= crm.getString("name",request.getParameter("name"));
        

        	if (StringUtil.isEmpty(memberNo)){
            	request.setAttribute("bdSendRecordListTable_totalRows", pager.getTotalObjects());
    			return new ModelAndView("bd/bdSendRecordMList", "bdSendRecordList", null);	
    		}else if (!jmiLinkRefManager.getJmiLinkRefIsM(memberNo)){
            	request.setAttribute("bdSendRecordListTable_totalRows", pager.getTotalObjects());
    			return new ModelAndView("bd/bdSendRecordMList", "bdSendRecordList", null);	
    			
    		}else{
    			crm.addField("companyCode", companyCode);
    			crm.addField("tableName", "VJbdSendRecord");
    			
    			List bdSendRecords=jbdSendRecordHistManager.getJbdSendRecordHistsByCrm(crm, pager);
    			request.setAttribute("bdSendRecordListTable_totalRows", pager.getTotalObjects());

            	request.setAttribute("sortAble", "true");
    	        return new ModelAndView("bd/bdSendRecordMList", "bdSendRecordList", bdSendRecords);
    		}  
    }
}
