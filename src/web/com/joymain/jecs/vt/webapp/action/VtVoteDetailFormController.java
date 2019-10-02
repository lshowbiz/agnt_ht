package com.joymain.jecs.vt.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.vt.model.VtVoteDetail;
import com.joymain.jecs.vt.service.VtVoteDetailManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class VtVoteDetailFormController extends BaseFormController {
    private VtVoteDetailManager vtVoteDetailManager = null;

    public void setVtVoteDetailManager(VtVoteDetailManager vtVoteDetailManager) {
        this.vtVoteDetailManager = vtVoteDetailManager;
    }
    public VtVoteDetailFormController() {
        setCommandName("vtVoteDetail");
        setCommandClass(VtVoteDetail.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String vdId = request.getParameter("vdId");
        VtVoteDetail vtVoteDetail = null;

        if (!StringUtils.isEmpty(vdId)) {
            vtVoteDetail = vtVoteDetailManager.getVtVoteDetail(vdId);
        } else {
            vtVoteDetail = new VtVoteDetail();
        }

        return vtVoteDetail;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        VtVoteDetail vtVoteDetail = (VtVoteDetail) command;
        boolean isNew = (vtVoteDetail.getVdId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteVtVoteDetail".equals(strAction)  ) {
		vtVoteDetailManager.removeVtVoteDetail(vtVoteDetail.getVdId().toString());
		key="vtVoteDetail.delete";
	}else{
		vtVoteDetailManager.saveVtVoteDetail(vtVoteDetail);
		key="vtVoteDetail.update";
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
