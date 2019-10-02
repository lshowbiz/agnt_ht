package com.joymain.jecs.am.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.am.model.ScheduleManage;
import com.joymain.jecs.am.service.ScheduleManageManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class ScheduleManageFormController extends BaseFormController {
    private ScheduleManageManager scheduleManageManager = null;

    public void setScheduleManageManager(ScheduleManageManager scheduleManageManager) {
        this.scheduleManageManager = scheduleManageManager;
    }
    public ScheduleManageFormController() {
        setCommandName("scheduleManage");
        setCommandClass(ScheduleManage.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        ScheduleManage scheduleManage = null;

        if (!StringUtils.isEmpty(id)) {
            scheduleManage = scheduleManageManager.getScheduleManage(id);
        } else {
            scheduleManage = new ScheduleManage();
        }

        return scheduleManage;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        ScheduleManage scheduleManage = (ScheduleManage) command;
        boolean isNew = (scheduleManage.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteScheduleManage".equals(strAction)  ) {
		scheduleManageManager.removeScheduleManage(scheduleManage.getId().toString());
		key="scheduleManage.delete";
	}else{
		scheduleManageManager.saveScheduleManage(scheduleManage);
		key="scheduleManage.update";
	}

        return new ModelAndView(getSuccessView());
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
