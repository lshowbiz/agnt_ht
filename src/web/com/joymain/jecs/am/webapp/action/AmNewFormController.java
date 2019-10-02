package com.joymain.jecs.am.webapp.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.am.model.AmNew;
import com.joymain.jecs.am.service.AmNewManager;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.multipart.support.StringMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

public class AmNewFormController extends BaseFormController {
    private AmNewManager amNewManager = null;

    public void setAmNewManager(AmNewManager amNewManager) {
        this.amNewManager = amNewManager;
    }
    public AmNewFormController() {
        setCommandName("amNew");
        setCommandClass(AmNew.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String newId = request.getParameter("newId");
        AmNew amNew = null;

        if (!StringUtils.isEmpty(newId)) {
            amNew = amNewManager.getAmNew(newId);
        } else {
            amNew = new AmNew();
        }

        return amNew;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        AmNew amNew = (AmNew) command;
        boolean isNew = (amNew.getNewId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteAmNew".equals(strAction)  ) {
		amNewManager.removeAmNew(amNew.getNewId().toString());
		key="amNew.delete";
	}else{
		amNewManager.saveAmNew(amNew);
		key="amNew.update";
	}

        return new ModelAndView(getSuccessView());
    }
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
//		binder.setAllowedFields(allowedFields);
//		binder.setDisallowedFields(disallowedFields);
//		binder.setRequiredFields(requiredFields);
		binder.registerCustomEditor(Integer.class, null, new CustomNumberEditor(Integer.class, null, true));
		binder.registerCustomEditor(Long.class, null, new CustomNumberEditor(Long.class, null, true));
		
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());//BLOB   
		binder.registerCustomEditor(String.class, new StringMultipartFileEditor());//CLOB
		//SimpleDateFormat dateFormat = new SimpleDateFormat(getText("date.format"));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
}
