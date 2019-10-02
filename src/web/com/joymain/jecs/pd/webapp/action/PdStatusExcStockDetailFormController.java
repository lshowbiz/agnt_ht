package com.joymain.jecs.pd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.pd.model.PdStatusExcStockDetail;
import com.joymain.jecs.pd.service.PdStatusExcStockDetailManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdStatusExcStockDetailFormController extends BaseFormController {
    private PdStatusExcStockDetailManager pdStatusExcStockDetailManager = null;

    public void setPdStatusExcStockDetailManager(PdStatusExcStockDetailManager pdStatusExcStockDetailManager) {
        this.pdStatusExcStockDetailManager = pdStatusExcStockDetailManager;
    }
    public PdStatusExcStockDetailFormController() {
        setCommandName("pdStatusExcStockDetail");
        setCommandClass(PdStatusExcStockDetail.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String uniNo = request.getParameter("uniNo");
        PdStatusExcStockDetail pdStatusExcStockDetail = null;

        if (!StringUtils.isEmpty(uniNo)) {
            pdStatusExcStockDetail = pdStatusExcStockDetailManager.getPdStatusExcStockDetail(uniNo);
        } else {
            pdStatusExcStockDetail = new PdStatusExcStockDetail();
        }

        return pdStatusExcStockDetail;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        PdStatusExcStockDetail pdStatusExcStockDetail = (PdStatusExcStockDetail) command;
        boolean isNew = (pdStatusExcStockDetail.getUniNo() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deletePdStatusExcStockDetail".equals(strAction)  ) {
		pdStatusExcStockDetailManager.removePdStatusExcStockDetail(pdStatusExcStockDetail.getUniNo().toString());
		key="pdStatusExcStockDetail.delete";
	}else{
		pdStatusExcStockDetailManager.savePdStatusExcStockDetail(pdStatusExcStockDetail);
		key="pdStatusExcStockDetail.update";
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
