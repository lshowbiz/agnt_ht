package com.joymain.jecs.fi.webapp.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.fi.model.FiAvailableInvoice;
import com.joymain.jecs.fi.service.FiAvailableInvoiceManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import yspay.util.StringUtil;

public class FiAvailableInvoiceController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiAvailableInvoiceController.class);
    private FiAvailableInvoiceManager fiAvailableInvoiceManager = null;

    public void setFiAvailableInvoiceManager(FiAvailableInvoiceManager fiAvailableInvoiceManager) {
        this.fiAvailableInvoiceManager = fiAvailableInvoiceManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

	    CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("fiAvailableInvoiceListTable",request, 20);
        
        
        
        //modify by fu 2015-12-02  将财政周转化为工作周 -------begin 
        //将财政周转化为工作周
        String jmonth = crm.getString("jmonth", "");
        if(!StringUtil.isEmpty(jmonth)){
        	//存入数据库的是工作周(例如:201510),在页面显示的是财政周
	        String qb = WeekFormatUtil.getFormatWeek("f", jmonth);
	        crm.setValue("jmonth", qb);
        }
        //modify by fu 2015-12-02  将财政周转化为工作周 -------begin 

        List fiAvailableInvoices = fiAvailableInvoiceManager.getFiAvailableInvoicesByCrm(crm,pager);
        request.setAttribute("fiAvailableInvoiceListTable_totalRows", pager.getTotalObjects());
        return new ModelAndView("fi/fiAvailableInvoiceList", "fiAvailableInvoiceList", fiAvailableInvoices);
    }
}
