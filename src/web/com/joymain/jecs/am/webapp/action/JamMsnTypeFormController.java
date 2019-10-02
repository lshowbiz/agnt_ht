package com.joymain.jecs.am.webapp.action;

import java.util.List;
import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.am.model.JamMsnType;
import com.joymain.jecs.am.service.JamMsnTypeManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JamMsnTypeFormController extends BaseFormController {
    private JamMsnTypeManager jamMsnTypeManager = null;

    public void setJamMsnTypeManager(JamMsnTypeManager jamMsnTypeManager) {
        this.jamMsnTypeManager = jamMsnTypeManager;
    }
    public JamMsnTypeFormController() {
        setCommandName("jamMsnType");
        setCommandClass(JamMsnType.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String mtId = request.getParameter("mtId");
        JamMsnType jamMsnType = null;

        if (!StringUtils.isEmpty(mtId)) {
            jamMsnType = jamMsnTypeManager.getJamMsnType(mtId);
        } else {
            jamMsnType = new JamMsnType();
        }

        return jamMsnType;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JamMsnType jamMsnType = (JamMsnType) command;
//        boolean isNew = (jamMsnType.getMtId() == null);
//        Locale locale = request.getLocale();
//		String key=null;
		String strAction = request.getParameter("strAction");
		
		if ("addJamMsnType".equals(strAction)) {

	    	if (StringUtils.isEmpty(jamMsnType.getMsnKey())) {
				errors.rejectValue("msnKey", "isNotNull",new Object[] { this.getText("bdOutwardBank.bankCode") }, "");
				return showForm(request, response, errors);
	    	}
	    	JamMsnType searchJamMsnType=new JamMsnType();
	    	searchJamMsnType.setMsnKey(jamMsnType.getMsnKey());
	    	List list=jamMsnTypeManager.getJamMsnTypes(searchJamMsnType);
	    	if(list.size()>0){
				this.saveMessage(request, LocaleUtil.getLocalText("JbdManuallyAdjustStar.exist"));
				return showForm(request, response, errors);
	    	}
	    	jamMsnType.setMsnStatus("0");
		}else if("editJamMsnType".equals(strAction)){
			
		}
    	if (StringUtils.isEmpty(jamMsnType.getMsnName())) {
			errors.rejectValue("msnName", "isNotNull",new Object[] { this.getText("jamMsnType.msnName") }, "");
			return showForm(request, response, errors);
    	}
		jamMsnTypeManager.saveJamMsnType(jamMsnType);
		MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
		
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
