package com.joymain.jecs.am.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.am.model.JamMsnDetail;
import com.joymain.jecs.am.service.JamMsnDetailManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JamMsnDetailFormController extends BaseFormController {
    private JamMsnDetailManager jamMsnDetailManager = null;

    public void setJamMsnDetailManager(JamMsnDetailManager jamMsnDetailManager) {
        this.jamMsnDetailManager = jamMsnDetailManager;
    }
    public JamMsnDetailFormController() {
        setCommandName("jamMsnDetail");
        setCommandClass(JamMsnDetail.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String mdId = request.getParameter("mdId");
        JamMsnDetail jamMsnDetail = null;

        if (!StringUtils.isEmpty(mdId)) {
            jamMsnDetail = jamMsnDetailManager.getJamMsnDetail(mdId);
        } else {
            jamMsnDetail = new JamMsnDetail();
        }

        return jamMsnDetail;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JamMsnDetail jamMsnDetail = (JamMsnDetail) command;
        boolean isNew = (jamMsnDetail.getMdId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJamMsnDetail".equals(strAction)  ) {
		jamMsnDetailManager.removeJamMsnDetail(jamMsnDetail.getMdId().toString());
		key="jamMsnDetail.delete";
	}else{
		jamMsnDetailManager.saveJamMsnDetail(jamMsnDetail);
		key="jamMsnDetail.update";
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
