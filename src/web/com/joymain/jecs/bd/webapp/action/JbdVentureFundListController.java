package com.joymain.jecs.bd.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.JbdVentureFundList;
import com.joymain.jecs.bd.service.JbdVentureFundListManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.MessageUtil;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdVentureFundListController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdVentureFundListController.class);
    private JbdVentureFundListManager jbdVentureFundListManager = null;

    public void setJbdVentureFundListManager(JbdVentureFundListManager jbdVentureFundListManager) {
        this.jbdVentureFundListManager = jbdVentureFundListManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdVentureFundListListTable",request, 20);
        String userCode=request.getParameter("userCode");

        String strAction=request.getParameter("strAction");
        
        if("deleteJbdVentureFundList".equals(strAction)){
            String id=request.getParameter("id");
            
            JbdVentureFundList jbdVentureFundList=jbdVentureFundListManager.getJbdVentureFundList(id);
            if(1!=jbdVentureFundList.getStatus()||0!=jbdVentureFundList.getCalcStatus()){

    			MessageUtil.saveLocaleMessage(request, "sys.message.updateFail");
            	
            }else{

                try {
                	jbdVentureFundListManager.removeJbdVentureFundList(id);
        			MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
        		} catch (Exception e) {
        			e.printStackTrace();
        			MessageUtil.saveLocaleMessage(request, "sys.message.updateFail");
        		}
            }
            
        }
        
        List jbdVentureFundLists = null;
        
        if(!StringUtil.isEmpty(userCode)){
        	jbdVentureFundLists = jbdVentureFundListManager.getJbdVentureFundListsByCrm(crm,pager);
        }
        
        request.setAttribute("jbdVentureFundListListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("bd/jbdVentureFundListList", Constants.JBDVENTUREFUNDLIST_LIST, jbdVentureFundLists);
    }
}
