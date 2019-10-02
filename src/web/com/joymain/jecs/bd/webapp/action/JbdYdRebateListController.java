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
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.JbdJdSendRecord;
import com.joymain.jecs.bd.service.JbdJdSendRecordManager;
import com.joymain.jecs.bd.service.JbdYdRebateListManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import yspay.util.StringUtil;

public class JbdYdRebateListController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdYdRebateListController.class);
    private JbdYdRebateListManager jbdYdRebateListManager = null;

    public void setJbdYdRebateListManager(
			JbdYdRebateListManager jbdYdRebateListManager) {
		this.jbdYdRebateListManager = jbdYdRebateListManager;
	}



	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JbdJdSendRecord jbdJdSendRecord = new JbdJdSendRecord();
        // populate object with request parameters
        BeanUtils.populate(jbdJdSendRecord, request.getParameterMap());
        
       
        
        
	//List jbdJdSendRecords = jbdJdSendRecordManager.getJbdJdSendRecords(jbdJdSendRecord);
	/**** auto pagination ***/
        CommonRecord crm=RequestUtil.toCommonRecord(request);
	
	
        Pager pager = new Pager("jbdYdRebateListTable",request, 20);
        
        
        List jbdYdRebateLists = jbdYdRebateListManager.getVJbdYdRebateListsByCrm(crm,pager);
        request.setAttribute("jbdYdRebateListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdYdRebateLists", "jbdYdRebateLists", jbdYdRebateLists);
    }
}
