package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.FiTransferFundbook;
import com.joymain.jecs.fi.service.FiTransferFundbookManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class FiTransferFundbookFormController extends BaseFormController {
    private FiTransferFundbookManager fiTransferFundbookManager = null;

    public void setFiTransferFundbookManager(FiTransferFundbookManager fiTransferFundbookManager) {
        this.fiTransferFundbookManager = fiTransferFundbookManager;
    }
    public FiTransferFundbookFormController() {
        setCommandName("fiTransferFundbook");
        setCommandClass(FiTransferFundbook.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String taId = request.getParameter("taId");
        FiTransferFundbook fiTransferFundbook = null;

        if (!StringUtils.isEmpty(taId)) {
            fiTransferFundbook = fiTransferFundbookManager.getFiTransferFundbook(taId);
        } else {
            fiTransferFundbook = new FiTransferFundbook();
        }

        return fiTransferFundbook;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        FiTransferFundbook fiTransferFundbook = (FiTransferFundbook) command;
        boolean isNew = (fiTransferFundbook.getTaId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteFiTransferFundbook".equals(strAction)  ) {
		fiTransferFundbookManager.removeFiTransferFundbook(fiTransferFundbook.getTaId().toString());
		key="fiTransferFundbook.delete";
	}else{
		fiTransferFundbookManager.saveFiTransferFundbook(fiTransferFundbook);
		key="fiTransferFundbook.update";
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
