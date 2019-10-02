package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiAmountDetail;
import com.joymain.jecs.fi.service.JfiAmountDetailManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiAmountDetailFormController extends BaseFormController {
    private JfiAmountDetailManager jfiAmountDetailManager = null;

    public void setJfiAmountDetailManager(JfiAmountDetailManager jfiAmountDetailManager) {
        this.jfiAmountDetailManager = jfiAmountDetailManager;
    }
    public JfiAmountDetailFormController() {
        setCommandName("jfiAmountDetail");
        setCommandClass(JfiAmountDetail.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JfiAmountDetail jfiAmountDetail = null;

        if (!StringUtils.isEmpty(id)) {
            jfiAmountDetail = jfiAmountDetailManager.getJfiAmountDetail(id);
        } else {
            jfiAmountDetail = new JfiAmountDetail();
        }

        return jfiAmountDetail;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiAmountDetail jfiAmountDetail = (JfiAmountDetail) command;
        boolean isNew = (jfiAmountDetail.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiAmountDetail".equals(strAction)  ) {
		jfiAmountDetailManager.removeJfiAmountDetail(jfiAmountDetail.getId().toString());
		key="jfiAmountDetail.delete";
	}else{
		jfiAmountDetailManager.saveJfiAmountDetail(jfiAmountDetail);
		key="jfiAmountDetail.update";
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
