package com.joymain.jecs.pd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.pd.model.PdWarehouseStockTrace;
import com.joymain.jecs.pd.service.PdWarehouseStockTraceManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
             
public class PdWarehouseStockTraceFormController extends BaseFormController {
    private PdWarehouseStockTraceManager pdWarehouseStockTraceManager = null;

    public void setPdWarehouseStockTraceManager(PdWarehouseStockTraceManager pdWarehouseStockTraceManager) {
        this.pdWarehouseStockTraceManager = pdWarehouseStockTraceManager;
    }
    public PdWarehouseStockTraceFormController() {
        setCommandName("pdWarehouseStockTrace");
        setCommandClass(PdWarehouseStockTrace.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String uniNo = request.getParameter("uniNo");
        PdWarehouseStockTrace pdWarehouseStockTrace = null;

        if (!StringUtils.isEmpty(uniNo)) {
            pdWarehouseStockTrace = pdWarehouseStockTraceManager.getPdWarehouseStockTrace(uniNo);
        } else {
            pdWarehouseStockTrace = new PdWarehouseStockTrace();
        }

        return pdWarehouseStockTrace;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        PdWarehouseStockTrace pdWarehouseStockTrace = (PdWarehouseStockTrace) command;
        boolean isNew = (pdWarehouseStockTrace.getUniNo() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deletePdWarehouseStockTrace".equals(strAction)  ) {
		pdWarehouseStockTraceManager.removePdWarehouseStockTrace(pdWarehouseStockTrace.getUniNo().toString());
		key="pdWarehouseStockTrace.delete";
	}else{
		pdWarehouseStockTraceManager.savePdWarehouseStockTrace(pdWarehouseStockTrace);
		key="pdWarehouseStockTrace.update";
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
