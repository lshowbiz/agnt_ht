package com.joymain.jecs.vt.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.vt.model.VtVotePow;
import com.joymain.jecs.vt.service.VtVotePowManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class VtVotePowFormController extends BaseFormController {
    private VtVotePowManager vtVotePowManager = null;

    public void setVtVotePowManager(VtVotePowManager vtVotePowManager) {
        this.vtVotePowManager = vtVotePowManager;
    }
    public VtVotePowFormController() {
        setCommandName("vtVotePow");
        setCommandClass(VtVotePow.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String vpId = request.getParameter("vpId");
        VtVotePow vtVotePow = null;

        if (!StringUtils.isEmpty(vpId)) {
            vtVotePow = vtVotePowManager.getVtVotePow(vpId);
        } else {
            vtVotePow = new VtVotePow();
        }

        return vtVotePow;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        VtVotePow vtVotePow = (VtVotePow) command;
        boolean isNew = (vtVotePow.getVpId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteVtVotePow".equals(strAction)  ) {
		vtVotePowManager.removeVtVotePow(vtVotePow.getVpId().toString());
		key="vtVotePow.delete";
	}else{
		vtVotePowManager.saveVtVotePow(vtVotePow);
		key="vtVotePow.update";
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
