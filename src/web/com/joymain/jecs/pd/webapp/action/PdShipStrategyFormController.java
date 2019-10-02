package com.joymain.jecs.pd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.pd.model.PdShipStrategy;
import com.joymain.jecs.pd.service.PdShipStrategyManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdShipStrategyFormController extends BaseFormController {
    private PdShipStrategyManager pdShipStrategyManager = null;

    public void setPdShipStrategyManager(PdShipStrategyManager pdShipStrategyManager) {
        this.pdShipStrategyManager = pdShipStrategyManager;
    }
    public PdShipStrategyFormController() {
        setCommandName("pdShipStrategy");
        setCommandClass(PdShipStrategy.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String ssId = request.getParameter("ssId");
        PdShipStrategy pdShipStrategy = null;

        if (!StringUtils.isEmpty(ssId)) {
            pdShipStrategy = pdShipStrategyManager.getPdShipStrategy(ssId);
        } else {
            pdShipStrategy = new PdShipStrategy();
        }

        return pdShipStrategy;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        PdShipStrategy pdShipStrategy = (PdShipStrategy) command;
        boolean isNew = (pdShipStrategy.getSsId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deletePdShipStrategy".equals(strAction)  ) {
		pdShipStrategyManager.removePdShipStrategy(pdShipStrategy.getSsId().toString());
		key="pdShipStrategy.delete";
	}else{
		pdShipStrategyManager.savePdShipStrategy(pdShipStrategy);
		key="pdShipStrategy.update";
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
