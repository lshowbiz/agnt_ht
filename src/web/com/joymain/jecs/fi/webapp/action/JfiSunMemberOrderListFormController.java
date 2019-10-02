package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiSunMemberOrderList;
import com.joymain.jecs.fi.service.JfiSunMemberOrderListManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiSunMemberOrderListFormController extends BaseFormController {
    private JfiSunMemberOrderListManager jfiSunMemberOrderListManager = null;

    public void setJfiSunMemberOrderListManager(JfiSunMemberOrderListManager jfiSunMemberOrderListManager) {
        this.jfiSunMemberOrderListManager = jfiSunMemberOrderListManager;
    }
    public JfiSunMemberOrderListFormController() {
        setCommandName("jfiSunMemberOrderList");
        setCommandClass(JfiSunMemberOrderList.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String molId = request.getParameter("molId");
        JfiSunMemberOrderList jfiSunMemberOrderList = null;

        if (!StringUtils.isEmpty(molId)) {
            jfiSunMemberOrderList = jfiSunMemberOrderListManager.getJfiSunMemberOrderList(molId);
        } else {
            jfiSunMemberOrderList = new JfiSunMemberOrderList();
        }

        return jfiSunMemberOrderList;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiSunMemberOrderList jfiSunMemberOrderList = (JfiSunMemberOrderList) command;
        boolean isNew = (jfiSunMemberOrderList.getMolId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiSunMemberOrderList".equals(strAction)  ) {
		jfiSunMemberOrderListManager.removeJfiSunMemberOrderList(jfiSunMemberOrderList.getMolId().toString());
		key="jfiSunMemberOrderList.delete";
	}else{
		jfiSunMemberOrderListManager.saveJfiSunMemberOrderList(jfiSunMemberOrderList);
		key="jfiSunMemberOrderList.update";
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
