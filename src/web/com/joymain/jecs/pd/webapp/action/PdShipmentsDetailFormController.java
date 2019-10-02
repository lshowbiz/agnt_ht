package com.joymain.jecs.pd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.pd.model.PdShipmentsDetail;
import com.joymain.jecs.pd.service.PdShipmentsDetailManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdShipmentsDetailFormController extends BaseFormController {
    private PdShipmentsDetailManager pdShipmentsDetailManager = null;

    public void setPdShipmentsDetailManager(PdShipmentsDetailManager pdShipmentsDetailManager) {
        this.pdShipmentsDetailManager = pdShipmentsDetailManager;
    }
    public PdShipmentsDetailFormController() {
        setCommandName("pdShipmentsDetail");
        setCommandClass(PdShipmentsDetail.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String sdId = request.getParameter("sdId");
        PdShipmentsDetail pdShipmentsDetail = null;

        if (!StringUtils.isEmpty(sdId)) {
            pdShipmentsDetail = pdShipmentsDetailManager.getPdShipmentsDetail(sdId);
        } else {
            pdShipmentsDetail = new PdShipmentsDetail();
        }

        return pdShipmentsDetail;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        PdShipmentsDetail pdShipmentsDetail = (PdShipmentsDetail) command;
        boolean isNew = (pdShipmentsDetail.getSdId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deletePdShipmentsDetail".equals(strAction)  ) {
		pdShipmentsDetailManager.removePdShipmentsDetail(pdShipmentsDetail.getSdId().toString());
		key="pdShipmentsDetail.delete";
	}else{
		pdShipmentsDetailManager.savePdShipmentsDetail(pdShipmentsDetail);
		key="pdShipmentsDetail.update";
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
