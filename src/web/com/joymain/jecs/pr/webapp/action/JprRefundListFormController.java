package com.joymain.jecs.pr.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.pr.model.JprRefundList;
import com.joymain.jecs.pr.service.JprRefundListManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JprRefundListFormController extends BaseFormController {
    private JprRefundListManager jprRefundListManager = null;

    public void setJprRefundListManager(JprRefundListManager jprRefundListManager) {
        this.jprRefundListManager = jprRefundListManager;
    }
    public JprRefundListFormController() {
        setCommandName("jprRefundList");
        setCommandClass(JprRefundList.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String prlId = request.getParameter("prlId");
        JprRefundList jprRefundList = null;

        if (!StringUtils.isEmpty(prlId)) {
            jprRefundList = jprRefundListManager.getJprRefundList(prlId);
        } else {
            jprRefundList = new JprRefundList();
        }

        return jprRefundList;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JprRefundList jprRefundList = (JprRefundList) command;
        boolean isNew = (jprRefundList.getPrlId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJprRefundList".equals(strAction)  ) {
		jprRefundListManager.removeJprRefundList(jprRefundList.getPrlId().toString());
		key="jprRefundList.delete";
	}else{
		jprRefundListManager.saveJprRefundList(jprRefundList);
		key="jprRefundList.update";
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
