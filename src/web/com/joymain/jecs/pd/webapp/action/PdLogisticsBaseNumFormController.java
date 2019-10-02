package com.joymain.jecs.pd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.pd.model.PdLogisticsBaseNum;
import com.joymain.jecs.pd.service.PdLogisticsBaseNumManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdLogisticsBaseNumFormController extends BaseFormController {
    private PdLogisticsBaseNumManager pdLogisticsBaseNumManager = null;

    public void setPdLogisticsBaseNumManager(PdLogisticsBaseNumManager pdLogisticsBaseNumManager) {
        this.pdLogisticsBaseNumManager = pdLogisticsBaseNumManager;
    }
    public PdLogisticsBaseNumFormController() {
        setCommandName("pdLogisticsBaseNum");
        setCommandClass(PdLogisticsBaseNum.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String numId = request.getParameter("numId");
        PdLogisticsBaseNum pdLogisticsBaseNum = null;

        if (!StringUtils.isEmpty(numId)) {
            pdLogisticsBaseNum = pdLogisticsBaseNumManager.getPdLogisticsBaseNum(numId);
        } else {
            pdLogisticsBaseNum = new PdLogisticsBaseNum();
        }

        return pdLogisticsBaseNum;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        PdLogisticsBaseNum pdLogisticsBaseNum = (PdLogisticsBaseNum) command;
        boolean isNew = (pdLogisticsBaseNum.getNumId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deletePdLogisticsBaseNum".equals(strAction)  ) {
		pdLogisticsBaseNumManager.removePdLogisticsBaseNum(pdLogisticsBaseNum.getNumId().toString());
		key="pdLogisticsBaseNum.delete";
	}else{
		pdLogisticsBaseNumManager.savePdLogisticsBaseNum(pdLogisticsBaseNum);
		key="pdLogisticsBaseNum.update";
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
