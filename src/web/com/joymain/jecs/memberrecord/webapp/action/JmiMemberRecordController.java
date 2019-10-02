package com.joymain.jecs.memberrecord.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.memberrecord.service.JmiMemberRecordManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JmiMemberRecordController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiMemberRecordController.class);
    private JmiMemberRecordManager jmiMemberRecordManager = null;

    public void setJmiMemberRecordManager(
			JmiMemberRecordManager jmiMemberRecordManager) {
		this.jmiMemberRecordManager = jmiMemberRecordManager;
	}


	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        String strAction=request.getParameter("strAction");
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        
        Pager pager = new Pager("jmiMemberRecordListTable",request, 20);

        String userCode=request.getParameter("userCode");
        String papernumber=request.getParameter("papernumber");
       
        List jmiMemberRecords =null;
	
        if ((!"".equals(userCode) && null!=userCode) ||(!"".equals(papernumber) && null!=papernumber)){
            jmiMemberRecords = jmiMemberRecordManager.getJmiMemberRecordList(crm, pager);
        }
        request.setAttribute("jmiMemberRecordListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("memberrecord/jmiMemberRecord", "jmiMemberRecordList", jmiMemberRecords);
    }

}
