package com.joymain.jecs.pd.webapp.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.pd.service.PdShUrlManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdShUrlController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdShUrlController.class);
    private PdShUrlManager pdShUrlManager = null;

    public void setPdShUrlManager(PdShUrlManager pdShUrlManager) {
        this.pdShUrlManager = pdShUrlManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

	    CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("pdShUrlListTable",request, 20);
        List pdShUrls = pdShUrlManager.getPdShUrlsByCrm(crm,pager);
        request.setAttribute("pdShUrlListTable_totalRows", pager.getTotalObjects());  
        return new ModelAndView("pd/pdShUrlList", "pdShUrlList", pdShUrls);
        
    }
}
