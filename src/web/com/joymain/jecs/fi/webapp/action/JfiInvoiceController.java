package com.joymain.jecs.fi.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.JfiInvoice;
import com.joymain.jecs.fi.service.JfiInvoiceManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JfiInvoiceController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiInvoiceController.class);
    private JfiInvoiceManager jfiInvoiceManager = null;

    public void setJfiInvoiceManager(JfiInvoiceManager jfiInvoiceManager) {
        this.jfiInvoiceManager = jfiInvoiceManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JfiInvoice jfiInvoice = new JfiInvoice();
        // populate object with request parameters
        BeanUtils.populate(jfiInvoice, request.getParameterMap());

	//List jfiInvoices = jfiInvoiceManager.getJfiInvoices(jfiInvoice);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
	
//	String search = request.getParameter("search");
//	String userCode = request.getParameter("userCode");
//	String wyear = request.getParameter("wyear");
//	String wweek = request.getParameter("wweek");
//	wweek = WeekFormatUtil.getFormatWeek("f", wweek);
	
	String wweek=crm.getString("wweek", "");
	if(!StringUtil.isEmpty(wweek)){
		crm.addField("wweek", WeekFormatUtil.getFormatWeek("f", wweek));
	}
	
	List jfiInvoices = new ArrayList();
	Pager pager = new Pager("jfiInvoiceListTable",request, 20);
//	if(null != search && !"".equals(search) && !(userCode.equals("") && wyear.equals("") && wweek.equals(""))){
		
		 jfiInvoices = jfiInvoiceManager.getJfiInvoicesByCrm(crm,pager);
//	}
	 
     request.setAttribute("jfiInvoiceListTable_totalRows", pager.getTotalObjects());
       
        /*****/

        return new ModelAndView("fi/jfiInvoiceList", "jfiInvoiceList", jfiInvoices);
    }
}
