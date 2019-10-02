package com.joymain.jecs.pd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.pd.model.PdLogisticsBase;
import com.joymain.jecs.pd.service.PdLogisticsBaseManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdLogisticsBaseFormController extends BaseFormController {
    private PdLogisticsBaseManager pdLogisticsBaseManager = null;

    public void setPdLogisticsBaseManager(PdLogisticsBaseManager pdLogisticsBaseManager) {
        this.pdLogisticsBaseManager = pdLogisticsBaseManager;
    }
    public PdLogisticsBaseFormController() {
        setCommandName("pdLogisticsBase");
        setCommandClass(PdLogisticsBase.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String baseId = request.getParameter("baseId");
        PdLogisticsBase pdLogisticsBase = null;

        if (!StringUtils.isEmpty(baseId)) {
            pdLogisticsBase = pdLogisticsBaseManager.getPdLogisticsBase(baseId);
        } else {
            pdLogisticsBase = new PdLogisticsBase();
        }

        return pdLogisticsBase;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        PdLogisticsBase pdLogisticsBase = (PdLogisticsBase) command;
        boolean isNew = (pdLogisticsBase.getBaseId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deletePdLogisticsBase".equals(strAction)  ) {
		pdLogisticsBaseManager.removePdLogisticsBase(pdLogisticsBase.getBaseId().toString());
		key="pdLogisticsBase.delete";
	}else{
		pdLogisticsBaseManager.savePdLogisticsBase(pdLogisticsBase);
		key="pdLogisticsBase.update";
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
