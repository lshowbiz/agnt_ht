package com.joymain.jecs.am.webapp.action;

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
import com.joymain.jecs.am.model.AmNew;
import com.joymain.jecs.am.service.AmNewManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class AmNewController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(AmNewController.class);
    private AmNewManager amNewManager = null;

    public void setAmNewManager(AmNewManager amNewManager) {
        this.amNewManager = amNewManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        AmNew amNew = new AmNew();
        // populate object with request parameters
        BeanUtils.populate(amNew, request.getParameterMap());

	//List amNews = amNewManager.getAmNews(amNew);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("amNewListTable",request, 20);
        List amNews = amNewManager.getAmNewsByCrm(crm,pager);
        request.setAttribute("amNewListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("am/amNewList", Constants.AMNEW_LIST, amNews);
    }
}
