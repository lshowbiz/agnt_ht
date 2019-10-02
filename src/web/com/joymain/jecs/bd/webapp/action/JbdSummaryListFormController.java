package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.bd.model.JbdSummaryList;
import com.joymain.jecs.bd.service.JbdSummaryListManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdSummaryListFormController extends BaseFormController {
    private JbdSummaryListManager jbdSummaryListManager = null;

    public void setJbdSummaryListManager(JbdSummaryListManager jbdSummaryListManager) {
        this.jbdSummaryListManager = jbdSummaryListManager;
    }
    public JbdSummaryListFormController() {
        setCommandName("jbdSummaryList");
        setCommandClass(JbdSummaryList.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdSummaryList jbdSummaryList = null;

        if (!StringUtils.isEmpty(id)) {
            jbdSummaryList = jbdSummaryListManager.getJbdSummaryList(id);
        } else {
            jbdSummaryList = new JbdSummaryList();
        }

        return jbdSummaryList;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdSummaryList jbdSummaryList = (JbdSummaryList) command;
        boolean isNew = (jbdSummaryList.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdSummaryList".equals(strAction)  ) {
		jbdSummaryListManager.removeJbdSummaryList(jbdSummaryList.getId().toString());
		key="jbdSummaryList.delete";
	}else{
		jbdSummaryListManager.saveJbdSummaryList(jbdSummaryList);
		key="jbdSummaryList.update";
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
