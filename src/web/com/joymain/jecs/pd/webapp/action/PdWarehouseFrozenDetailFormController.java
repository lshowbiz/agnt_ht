package com.joymain.jecs.pd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.pd.model.PdWarehouseFrozenDetail;
import com.joymain.jecs.pd.service.PdWarehouseFrozenDetailManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdWarehouseFrozenDetailFormController extends BaseFormController {
    private PdWarehouseFrozenDetailManager pdWarehouseFrozenDetailManager = null;

    public void setPdWarehouseFrozenDetailManager(PdWarehouseFrozenDetailManager pdWarehouseFrozenDetailManager) {
        this.pdWarehouseFrozenDetailManager = pdWarehouseFrozenDetailManager;
    }
    public PdWarehouseFrozenDetailFormController() {
        setCommandName("pdWarehouseFrozenDetail");
        setCommandClass(PdWarehouseFrozenDetail.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String uniNo = request.getParameter("uniNo");
        PdWarehouseFrozenDetail pdWarehouseFrozenDetail = null;

        if (!StringUtils.isEmpty(uniNo)) {
            pdWarehouseFrozenDetail = pdWarehouseFrozenDetailManager.getPdWarehouseFrozenDetail(uniNo);
        } else {
            pdWarehouseFrozenDetail = new PdWarehouseFrozenDetail();
        }

        return pdWarehouseFrozenDetail;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        PdWarehouseFrozenDetail pdWarehouseFrozenDetail = (PdWarehouseFrozenDetail) command;
        boolean isNew = (pdWarehouseFrozenDetail.getUniNo() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deletePdWarehouseFrozenDetail".equals(strAction)  ) {
		pdWarehouseFrozenDetailManager.removePdWarehouseFrozenDetail(pdWarehouseFrozenDetail.getUniNo().toString());
		key="pdWarehouseFrozenDetail.delete";
	}else{
		pdWarehouseFrozenDetailManager.savePdWarehouseFrozenDetail(pdWarehouseFrozenDetail);
		key="pdWarehouseFrozenDetail.update";
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
