package com.joymain.jecs.pd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.pd.model.PdSendInfoDetail;
import com.joymain.jecs.pd.service.PdSendInfoDetailManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdSendInfoDetailFormController extends BaseFormController {
    private PdSendInfoDetailManager pdSendInfoDetailManager = null;

    public void setPdSendInfoDetailManager(PdSendInfoDetailManager pdSendInfoDetailManager) {
        this.pdSendInfoDetailManager = pdSendInfoDetailManager;
    }
    public PdSendInfoDetailFormController() {
        setCommandName("pdSendInfoDetail");
        setCommandClass(PdSendInfoDetail.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String uniNo = request.getParameter("uniNo");
        PdSendInfoDetail pdSendInfoDetail = null;

        if (!StringUtils.isEmpty(uniNo)) {
            pdSendInfoDetail = pdSendInfoDetailManager.getPdSendInfoDetail(uniNo);
        } else {
            pdSendInfoDetail = new PdSendInfoDetail();
        }

        return pdSendInfoDetail;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        PdSendInfoDetail pdSendInfoDetail = (PdSendInfoDetail) command;
        boolean isNew = (pdSendInfoDetail.getUniNo() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deletePdSendInfoDetail".equals(strAction)  ) {
		pdSendInfoDetailManager.removePdSendInfoDetail(pdSendInfoDetail.getUniNo().toString());
		key="pdSendInfoDetail.delete";
	}else{
		pdSendInfoDetailManager.savePdSendInfoDetail(pdSendInfoDetail);
		key="pdSendInfoDetail.update";
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
