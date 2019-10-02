package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.bd.model.JbdSendRecordHist;
import com.joymain.jecs.bd.service.JbdSendRecordHistManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdSendRecordHistFormController extends BaseFormController {
    private JbdSendRecordHistManager jbdSendRecordHistManager = null;

    public void setJbdSendRecordHistManager(JbdSendRecordHistManager jbdSendRecordHistManager) {
        this.jbdSendRecordHistManager = jbdSendRecordHistManager;
    }
    public JbdSendRecordHistFormController() {
        setCommandName("jbdSendRecordHist");
        setCommandClass(JbdSendRecordHist.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdSendRecordHist jbdSendRecordHist = null;

        if (!StringUtils.isEmpty(id)) {
            jbdSendRecordHist = jbdSendRecordHistManager.getJbdSendRecordHist(id);
        } else {
            jbdSendRecordHist = new JbdSendRecordHist();
        }

        return jbdSendRecordHist;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdSendRecordHist jbdSendRecordHist = (JbdSendRecordHist) command;
        boolean isNew = (jbdSendRecordHist.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdSendRecordHist".equals(strAction)  ) {
		jbdSendRecordHistManager.removeJbdSendRecordHist(jbdSendRecordHist.getId().toString());
		key="jbdSendRecordHist.delete";
	}else{
		jbdSendRecordHistManager.saveJbdSendRecordHist(jbdSendRecordHist);
		key="jbdSendRecordHist.update";
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
