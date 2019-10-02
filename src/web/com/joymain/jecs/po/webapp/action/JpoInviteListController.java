package com.joymain.jecs.po.webapp.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.po.model.JpoInviteList;
import com.joymain.jecs.po.service.JpoInviteListManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JpoInviteListController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoInviteListController.class);
    private JpoInviteListManager jpoInviteListManager = null;

    public void setJpoInviteListManager(JpoInviteListManager jpoInviteListManager) {
        this.jpoInviteListManager = jpoInviteListManager;
    }

    @Override
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JpoInviteList jpoInviteList = new JpoInviteList();
        // populate object with request parameters
        BeanUtils.populate(jpoInviteList, request.getParameterMap());
        
        
	//List jpoInviteLists = jpoInviteListManager.getJpoInviteLists(jpoInviteList);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jpoInviteListListTable",request, 20);
        List jpoInviteLists = jpoInviteListManager.getJpoInviteListsByCrm(crm,pager);
        request.setAttribute("jpoInviteListListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("po/jpoInviteListList","jpoInviteLists", jpoInviteLists);
    }
}
