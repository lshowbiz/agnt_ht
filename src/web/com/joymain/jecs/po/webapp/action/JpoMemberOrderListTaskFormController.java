package com.joymain.jecs.po.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.po.model.JpoMemberOrderListTask;
import com.joymain.jecs.po.service.JpoMemberOrderListTaskManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JpoMemberOrderListTaskFormController extends BaseFormController {
    private JpoMemberOrderListTaskManager jpoMemberOrderListTaskManager = null;

    public void setJpoMemberOrderListTaskManager(JpoMemberOrderListTaskManager jpoMemberOrderListTaskManager) {
        this.jpoMemberOrderListTaskManager = jpoMemberOrderListTaskManager;
    }
    public JpoMemberOrderListTaskFormController() {
        setCommandName("jpoMemberOrderListTask");
        setCommandClass(JpoMemberOrderListTask.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String moltId = request.getParameter("moltId");
        JpoMemberOrderListTask jpoMemberOrderListTask = null;

        if (!StringUtils.isEmpty(moltId)) {
            jpoMemberOrderListTask = jpoMemberOrderListTaskManager.getJpoMemberOrderListTask(moltId);
        } else {
            jpoMemberOrderListTask = new JpoMemberOrderListTask();
        }

        return jpoMemberOrderListTask;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JpoMemberOrderListTask jpoMemberOrderListTask = (JpoMemberOrderListTask) command;
        boolean isNew = (jpoMemberOrderListTask.getMoltId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJpoMemberOrderListTask".equals(strAction)  ) {
		jpoMemberOrderListTaskManager.removeJpoMemberOrderListTask(jpoMemberOrderListTask.getMoltId().toString());
		key="jpoMemberOrderListTask.delete";
	}else{
		jpoMemberOrderListTaskManager.saveJpoMemberOrderListTask(jpoMemberOrderListTask);
		key="jpoMemberOrderListTask.update";
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
