package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.bd.model.JbdJdSendRecord;
import com.joymain.jecs.bd.service.JbdJdSendRecordManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdJdSendRecordFormController extends BaseFormController {
    private JbdJdSendRecordManager jbdJdSendRecordManager = null;

    public void setJbdJdSendRecordManager(JbdJdSendRecordManager jbdJdSendRecordManager) {
        this.jbdJdSendRecordManager = jbdJdSendRecordManager;
    }
    public JbdJdSendRecordFormController() {
        setCommandName("jbdJdSendRecord");
        setCommandClass(JbdJdSendRecord.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdJdSendRecord jbdJdSendRecord = null;

        if (!StringUtils.isEmpty(id)) {
            jbdJdSendRecord = jbdJdSendRecordManager.getJbdJdSendRecord(id);
        } else {
            jbdJdSendRecord = new JbdJdSendRecord();
        }

        return jbdJdSendRecord;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdJdSendRecord jbdJdSendRecord = (JbdJdSendRecord) command;
        boolean isNew = (jbdJdSendRecord.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdJdSendRecord".equals(strAction)  ) {
		jbdJdSendRecordManager.removeJbdJdSendRecord(jbdJdSendRecord.getId().toString());
		key="jbdJdSendRecord.delete";
	}else{
		jbdJdSendRecordManager.saveJbdJdSendRecord(jbdJdSendRecord);
		key="jbdJdSendRecord.update";
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
