package com.joymain.jecs.sys.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.sys.service.SysBankManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;

public class SysBankController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(SysBankController.class);
    private SysBankManager sysBankManager = null;

    public void setSysBankManager(SysBankManager sysBankManager) {
        this.sysBankManager = sysBankManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

		String companyCode=request.getParameter("companyCode");
		
		CommonRecord crm=RequestUtil.toCommonRecord(request);
        crm.addField("companyCode", companyCode);
       
        Pager pager = new Pager("sysBankListTable",request, 20);
		
		
		if(StringUtil.isEmpty(companyCode)){
    		request.setAttribute("sysBankListTable_totalRows", pager.getTotalObjects());
    		return new ModelAndView("sys/sysBankList", Constants.SYSBANK_LIST, null);
    	}else{
    		List sysBankList=sysBankManager.getSysBanksByCrm(crm, pager);
    		request.setAttribute("sysBankListTable_totalRows", pager.getTotalObjects());
    		return new ModelAndView("sys/sysBankList", Constants.SYSBANK_LIST, sysBankList);
    	}
    }
}
