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
import com.joymain.jecs.am.model.ScheduleManage;
import com.joymain.jecs.am.service.ScheduleManageManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ScheduleManageController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(ScheduleManageController.class);
    private ScheduleManageManager scheduleManageManager = null;

    public void setScheduleManageManager(ScheduleManageManager scheduleManageManager) {
        this.scheduleManageManager = scheduleManageManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        ScheduleManage scheduleManage = new ScheduleManage();
        // populate object with request parameters
        BeanUtils.populate(scheduleManage, request.getParameterMap());

	//List scheduleManages = scheduleManageManager.getScheduleManages(scheduleManage);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("scheduleManageListTable",request, 20);
        List scheduleManages = scheduleManageManager.getScheduleManagesByCrm(crm,pager);
        request.setAttribute("scheduleManageListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("am/scheduleManageList", Constants.SCHEDULEMANAGE_LIST, scheduleManages);
    }
}
