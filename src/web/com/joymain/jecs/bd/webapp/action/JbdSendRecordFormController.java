package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.bd.model.JbdSendRecord;
import com.joymain.jecs.bd.service.JbdSendRecordManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdSendRecordFormController extends BaseFormController {
    private JbdSendRecordManager jbdSendRecordManager = null;

    public void setJbdSendRecordManager(JbdSendRecordManager jbdSendRecordManager) {
        this.jbdSendRecordManager = jbdSendRecordManager;
    }
    public JbdSendRecordFormController() {
        setCommandName("jbdSendRecord");
        setCommandClass(JbdSendRecord.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdSendRecord jbdSendRecord = null;

        if (!StringUtils.isEmpty(id)) {
            jbdSendRecord = jbdSendRecordManager.getJbdSendRecord(id);
        } else {
            jbdSendRecord = new JbdSendRecord();
        }

        return jbdSendRecord;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdSendRecord jbdSendRecord = (JbdSendRecord) command;
        boolean isNew = (jbdSendRecord.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdSendRecord".equals(strAction)  ) {
		jbdSendRecordManager.removeJbdSendRecord(jbdSendRecord.getId().toString());
		key="jbdSendRecord.delete";
	}else{
		jbdSendRecordManager.saveJbdSendRecord(jbdSendRecord);
		key="jbdSendRecord.update";
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
