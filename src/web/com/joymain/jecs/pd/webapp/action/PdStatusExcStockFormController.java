package com.joymain.jecs.pd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.pd.model.PdStatusExcStock;
import com.joymain.jecs.pd.service.PdStatusExcStockManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdStatusExcStockFormController extends BaseFormController {
    private PdStatusExcStockManager pdStatusExcStockManager = null;

    public void setPdStatusExcStockManager(PdStatusExcStockManager pdStatusExcStockManager) {
        this.pdStatusExcStockManager = pdStatusExcStockManager;
    }
    public PdStatusExcStockFormController() {
        setCommandName("pdStatusExcStock");
        setCommandClass(PdStatusExcStock.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String seNo = request.getParameter("seNo");
        PdStatusExcStock pdStatusExcStock = null;

        if (!StringUtils.isEmpty(seNo)) {
            pdStatusExcStock = pdStatusExcStockManager.getPdStatusExcStock(seNo);
        } else {
            pdStatusExcStock = new PdStatusExcStock();
        }

        return pdStatusExcStock;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        PdStatusExcStock pdStatusExcStock = (PdStatusExcStock) command;
        boolean isNew = (pdStatusExcStock.getSeNo() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deletePdStatusExcStock".equals(strAction)  ) {
		pdStatusExcStockManager.removePdStatusExcStock(pdStatusExcStock.getSeNo().toString());
		key="pdStatusExcStock.delete";
	}else{
		pdStatusExcStockManager.savePdStatusExcStock(pdStatusExcStock);
		key="pdStatusExcStock.update";
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
