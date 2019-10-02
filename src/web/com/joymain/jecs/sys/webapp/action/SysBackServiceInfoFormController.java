package com.joymain.jecs.sys.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.sys.model.SysBackServiceInfo;
import com.joymain.jecs.sys.service.SysBackServiceInfoManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class SysBackServiceInfoFormController extends BaseFormController {
    private SysBackServiceInfoManager sysBackServiceInfoManager = null;

    public void setSysBackServiceInfoManager(SysBackServiceInfoManager sysBackServiceInfoManager) {
        this.sysBackServiceInfoManager = sysBackServiceInfoManager;
    }
    public SysBackServiceInfoFormController() {
        setCommandName("sysBackServiceInfo");
        setCommandClass(SysBackServiceInfo.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String bsiId = request.getParameter("bsiId");
        SysBackServiceInfo sysBackServiceInfo = null;

        if (!StringUtils.isEmpty(bsiId)) {
            sysBackServiceInfo = sysBackServiceInfoManager.getSysBackServiceInfo(bsiId);
        } else {
            sysBackServiceInfo = new SysBackServiceInfo();
        }

        return sysBackServiceInfo;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        SysBackServiceInfo sysBackServiceInfo = (SysBackServiceInfo) command;
        boolean isNew = (sysBackServiceInfo.getBsiId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteSysBackServiceInfo".equals(strAction)  ) {
		sysBackServiceInfoManager.removeSysBackServiceInfo(sysBackServiceInfo.getBsiId().toString());
		key="sysBackServiceInfo.delete";
	}else{
		sysBackServiceInfoManager.saveSysBackServiceInfo(sysBackServiceInfo);
		key="sysBackServiceInfo.update";
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
