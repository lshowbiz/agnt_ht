package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.FiCommonAddr;
import com.joymain.jecs.fi.service.FiCommonAddrManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class FiCommonAddrFormController extends BaseFormController {
    private FiCommonAddrManager fiCommonAddrManager = null;

    public void setFiCommonAddrManager(FiCommonAddrManager fiCommonAddrManager) {
        this.fiCommonAddrManager = fiCommonAddrManager;
    }
    public FiCommonAddrFormController() {
        setCommandName("fiCommonAddr");
        setCommandClass(FiCommonAddr.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String userCode = request.getParameter("userCode");
        FiCommonAddr fiCommonAddr = null;

        if (!StringUtils.isEmpty(userCode)) {
            fiCommonAddr = fiCommonAddrManager.getFiCommonAddr(userCode);
        } else {
            fiCommonAddr = new FiCommonAddr();
        }

        return fiCommonAddr;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        FiCommonAddr fiCommonAddr = (FiCommonAddr) command;
        boolean isNew = (fiCommonAddr.getUserCode() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteFiCommonAddr".equals(strAction)  ) {
		fiCommonAddrManager.removeFiCommonAddr(fiCommonAddr.getUserCode().toString());
		key="fiCommonAddr.delete";
	}else{
		fiCommonAddrManager.saveFiCommonAddr(fiCommonAddr);
		key="fiCommonAddr.update";
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
