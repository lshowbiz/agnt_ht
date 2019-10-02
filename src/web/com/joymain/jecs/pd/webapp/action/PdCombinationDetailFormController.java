package com.joymain.jecs.pd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.pd.model.PdCombinationDetail;
import com.joymain.jecs.pd.service.PdCombinationDetailManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdCombinationDetailFormController extends BaseFormController {
    private PdCombinationDetailManager pdCombinationDetailManager = null;

    public void setPdCombinationDetailManager(PdCombinationDetailManager pdCombinationDetailManager) {
        this.pdCombinationDetailManager = pdCombinationDetailManager;
    }
    public PdCombinationDetailFormController() {
        setCommandName("pdCombinationDetail");
        setCommandClass(PdCombinationDetail.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String uniNo = request.getParameter("uniNo");
        PdCombinationDetail pdCombinationDetail = null;

        if (!StringUtils.isEmpty(uniNo)) {
            pdCombinationDetail = pdCombinationDetailManager.getPdCombinationDetail(uniNo);
        } else {
            pdCombinationDetail = new PdCombinationDetail();
        }

        return pdCombinationDetail;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        PdCombinationDetail pdCombinationDetail = (PdCombinationDetail) command;
        boolean isNew = (pdCombinationDetail.getUniNo() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deletePdCombinationDetail".equals(strAction)  ) {
		pdCombinationDetailManager.removePdCombinationDetail(pdCombinationDetail.getUniNo().toString());
		key="pdCombinationDetail.delete";
	}else{
		pdCombinationDetailManager.savePdCombinationDetail(pdCombinationDetail);
		key="pdCombinationDetail.update";
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
