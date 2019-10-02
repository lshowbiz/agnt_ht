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
import com.joymain.jecs.am.model.PublicSchedule;
import com.joymain.jecs.am.service.PublicScheduleManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PublicScheduleController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PublicScheduleController.class);
    private PublicScheduleManager publicScheduleManager = null;

    public void setPublicScheduleManager(PublicScheduleManager publicScheduleManager) {
        this.publicScheduleManager = publicScheduleManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        PublicSchedule publicSchedule = new PublicSchedule();
        // populate object with request parameters
        BeanUtils.populate(publicSchedule, request.getParameterMap());

	//List publicSchedules = publicScheduleManager.getPublicSchedules(publicSchedule);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("publicScheduleListTable",request, 20);
        List publicSchedules = publicScheduleManager.getPublicSchedulesByCrm(crm,pager);
        request.setAttribute("publicScheduleListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("am/publicScheduleList", Constants.PUBLICSCHEDULE_LIST, publicSchedules);
    }
}
