package com.joymain.jecs.pd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.pd.model.PdLogisticsBaseDetail;
import com.joymain.jecs.pd.service.PdLogisticsBaseDetailManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdLogisticsBaseDetailFormController extends BaseFormController {
    private PdLogisticsBaseDetailManager pdLogisticsBaseDetailManager = null;

    public void setPdLogisticsBaseDetailManager(PdLogisticsBaseDetailManager pdLogisticsBaseDetailManager) {
        this.pdLogisticsBaseDetailManager = pdLogisticsBaseDetailManager;
    }
    public PdLogisticsBaseDetailFormController() {
        setCommandName("pdLogisticsBaseDetail");
        setCommandClass(PdLogisticsBaseDetail.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String detailId = request.getParameter("detailId");
        PdLogisticsBaseDetail pdLogisticsBaseDetail = null;

        if (!StringUtils.isEmpty(detailId)) {
            pdLogisticsBaseDetail = pdLogisticsBaseDetailManager.getPdLogisticsBaseDetail(detailId);
        } else {
            pdLogisticsBaseDetail = new PdLogisticsBaseDetail();
        }

        return pdLogisticsBaseDetail;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        PdLogisticsBaseDetail pdLogisticsBaseDetail = (PdLogisticsBaseDetail) command;
        boolean isNew = (pdLogisticsBaseDetail.getDetailId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deletePdLogisticsBaseDetail".equals(strAction)  ) {
		pdLogisticsBaseDetailManager.removePdLogisticsBaseDetail(pdLogisticsBaseDetail.getDetailId().toString());
		key="pdLogisticsBaseDetail.delete";
	}else{
		pdLogisticsBaseDetailManager.savePdLogisticsBaseDetail(pdLogisticsBaseDetail);
		key="pdLogisticsBaseDetail.update";
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
