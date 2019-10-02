package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.FiMovieOrder;
import com.joymain.jecs.fi.service.FiMovieOrderManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class FiMovieOrderFormController extends BaseFormController {
    private FiMovieOrderManager fiMovieOrderManager = null;

    public void setFiMovieOrderManager(FiMovieOrderManager fiMovieOrderManager) {
        this.fiMovieOrderManager = fiMovieOrderManager;
    }
    public FiMovieOrderFormController() {
        setCommandName("fiMovieOrder");
        setCommandClass(FiMovieOrder.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String orderId = request.getParameter("orderId");
        FiMovieOrder fiMovieOrder = null;

        if (!StringUtils.isEmpty(orderId)) {
            fiMovieOrder = fiMovieOrderManager.getFiMovieOrder(orderId);
        } else {
            fiMovieOrder = new FiMovieOrder();
        }

        return fiMovieOrder;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        FiMovieOrder fiMovieOrder = (FiMovieOrder) command;
        boolean isNew = (fiMovieOrder.getOrderId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteFiMovieOrder".equals(strAction)  ) {
		fiMovieOrderManager.removeFiMovieOrder(fiMovieOrder.getOrderId().toString());
		key="fiMovieOrder.delete";
	}else{
		fiMovieOrderManager.saveFiMovieOrder(fiMovieOrder);
		key="fiMovieOrder.update";
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
