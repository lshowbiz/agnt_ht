package com.joymain.jecs.pd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.pd.model.PdCheckstockOrderDetail;
import com.joymain.jecs.pd.service.PdCheckstockOrderDetailManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdCheckstockOrderDetailFormController extends BaseFormController {
    private PdCheckstockOrderDetailManager pdCheckstockOrderDetailManager = null;

    public void setPdCheckstockOrderDetailManager(PdCheckstockOrderDetailManager pdCheckstockOrderDetailManager) {
        this.pdCheckstockOrderDetailManager = pdCheckstockOrderDetailManager;
    }
    public PdCheckstockOrderDetailFormController() {
        setCommandName("pdCheckstockOrderDetail");
        setCommandClass(PdCheckstockOrderDetail.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String uniNo = request.getParameter("uniNo");
        PdCheckstockOrderDetail pdCheckstockOrderDetail = null;

        if (!StringUtils.isEmpty(uniNo)) {
            pdCheckstockOrderDetail = pdCheckstockOrderDetailManager.getPdCheckstockOrderDetail(uniNo);
        } else {
            pdCheckstockOrderDetail = new PdCheckstockOrderDetail();
        }

        return pdCheckstockOrderDetail;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        PdCheckstockOrderDetail pdCheckstockOrderDetail = (PdCheckstockOrderDetail) command;
        boolean isNew = (pdCheckstockOrderDetail.getUniNo() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deletePdCheckstockOrderDetail".equals(strAction)  ) {
		pdCheckstockOrderDetailManager.removePdCheckstockOrderDetail(pdCheckstockOrderDetail.getUniNo().toString());
		key="pdCheckstockOrderDetail.delete";
	}else{
		pdCheckstockOrderDetailManager.savePdCheckstockOrderDetail(pdCheckstockOrderDetail);
		key="pdCheckstockOrderDetail.update";
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
