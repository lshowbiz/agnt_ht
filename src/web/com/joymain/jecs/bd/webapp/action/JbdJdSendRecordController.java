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
import com.joymain.jecs.bd.service.JbdYkJiandianListManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import yspay.util.StringUtil;

public class JbdJdSendRecordController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdJdSendRecordController.class);
    private JbdJdSendRecordManager jbdJdSendRecordManager = null;
    private JbdYkJiandianListManager jbdYkJiandianListManager = null;

    public void setJbdJdSendRecordManager(JbdJdSendRecordManager jbdJdSendRecordManager) {
        this.jbdJdSendRecordManager = jbdJdSendRecordManager;
    }
    
    public void setJbdYkJiandianListManager(
			JbdYkJiandianListManager jbdYkJiandianListManager) {
		this.jbdYkJiandianListManager = jbdYkJiandianListManager;
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
	
		 //modify by fu 2017-4-5 云客推荐奖励查询明细------begin
	    String strAction = request.getParameter("strAction");
	    if((!StringUtil.isEmpty(strAction))&&("jbdJdSendRecordList".equals(strAction))){
	    	String userCodeDetail = request.getParameter("userCodeDetail");
	    	String wweekDetail = request.getParameter("wweekDetail");
	    	String freezestatus=request.getParameter("freezestatus");
	        Pager pagerOne = new Pager("jbdYkJiandianListListTable",request, 20);
	    	if((!StringUtil.isEmpty(userCodeDetail))&&(!StringUtil.isEmpty(wweekDetail))){
	    		crm.addField("userCodeDetail", userCodeDetail);
	    		crm.addField("wweekDetail", wweekDetail);
	    		crm.addField("freezestatus",freezestatus);
	            List jbdJdSendRecordListDetail = jbdYkJiandianListManager.getJbdYkJiandianListsByCrm(crm,pagerOne);
	            request.setAttribute("jbdJdSendRecordListDetailTable_totalRows", pagerOne.getTotalObjects());
	            return new ModelAndView("bd/jbdJdSendRecordListDetail","jbdJdSendRecordListDetail", jbdJdSendRecordListDetail);
	    	}
	    }
	  //modify by fu 2017-4-5 云客推荐奖励查询明细------end
	
        Pager pager = new Pager("jbdJdSendRecordListTable",request, 20);
        
        
        List jbdJdSendRecords = jbdJdSendRecordManager.getJbdJdSendRecordsByCrm(crm,pager);
        request.setAttribute("jbdJdSendRecordListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdJdSendRecordList", "jbdJdSendRecordList", jbdJdSendRecords);
    }
}
