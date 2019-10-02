package com.joymain.jecs.mi.webapp.action;

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
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.mi.model.JmiCustomerLevelNote;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiCustomerLevelNoteManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JmiCustomerLevelNoteController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiCustomerLevelNoteController.class);
    private JmiCustomerLevelNoteManager jmiCustomerLevelNoteManager = null;
    private JmiMemberManager jmiMemberManager;
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	public void setJmiCustomerLevelNoteManager(JmiCustomerLevelNoteManager jmiCustomerLevelNoteManager) {
        this.jmiCustomerLevelNoteManager = jmiCustomerLevelNoteManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }


    	SysUser defSysUser=SessionLogin.getLoginUser(request);
    	
        CommonRecord crm=RequestUtil.toCommonRecord(request);

        crm.addField("companyCode", defSysUser.getCompanyCode());
        
        
        String strAction=request.getParameter("strAction");
        
        if("sendToPcnModifyLevel".equals(strAction)){
        	String[] strRegisterSuccessCodes = request.getParameter("strRegisterSuccessCodes").split(",");
    
        	for (int i = 0; i < strRegisterSuccessCodes.length; i++) {
    			if (!StringUtils.isEmpty(strRegisterSuccessCodes[i])) {
    				JmiCustomerLevelNote jmiCustomerLevelNote=jmiCustomerLevelNoteManager.getJmiCustomerLevelNote(strRegisterSuccessCodes[i]);
    				if("1".equals(jmiCustomerLevelNote.getStatus())){
    					JmiMember jmiMember=jmiMemberManager.getJmiMember(jmiCustomerLevelNote.getUserCode());
        				jmiMemberManager.sendPcnMananger(jmiMember, "ModifyLevel", jmiCustomerLevelNote.getNewCustomerLevel(), jmiCustomerLevelNote.getRemark(), defSysUser, strRegisterSuccessCodes[i]);
    				}
    			}
    		}
        }
        
        
        
        
        Pager pager = new Pager("jmiCustomerLevelNoteListTable",request, 20);
        List jmiCustomerLevelNotes = jmiCustomerLevelNoteManager.getJmiCustomerLevelNotesByCrm(crm,pager);
        request.setAttribute("jmiCustomerLevelNoteListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("mi/jmiCustomerLevelNoteList", Constants.JMICUSTOMERLEVELNOTE_LIST, jmiCustomerLevelNotes);
    }
}
