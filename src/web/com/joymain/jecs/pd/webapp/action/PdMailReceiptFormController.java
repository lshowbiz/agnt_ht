package com.joymain.jecs.pd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.pd.model.PdMailReceipt;
import com.joymain.jecs.pd.service.PdMailReceiptManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdMailReceiptFormController extends BaseFormController {
    private PdMailReceiptManager pdMailReceiptManager = null;

    public void setPdMailReceiptManager(PdMailReceiptManager pdMailReceiptManager) {
        this.pdMailReceiptManager = pdMailReceiptManager;
    }
    public PdMailReceiptFormController() {
        setCommandName("pdMailReceipt");
        setCommandClass(PdMailReceipt.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String mailReceiptId = request.getParameter("mailReceiptId");
        PdMailReceipt pdMailReceipt = null;

        if (!StringUtils.isEmpty(mailReceiptId)) {
            pdMailReceipt = pdMailReceiptManager.getPdMailReceipt(mailReceiptId);
        } else {
            pdMailReceipt = new PdMailReceipt();
        }

        return pdMailReceipt;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        PdMailReceipt pdMailReceipt = (PdMailReceipt) command;
        boolean isNew = (pdMailReceipt.getMailReceiptId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deletePdMailReceipt".equals(strAction)  ) {
		pdMailReceiptManager.removePdMailReceipt(pdMailReceipt.getMailReceiptId().toString());
		key="pdMailReceipt.delete";
	}else{
		pdMailReceiptManager.savePdMailReceipt(pdMailReceipt);
		key="pdMailReceipt.update";
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
