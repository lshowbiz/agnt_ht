package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiSunOrderList;
import com.joymain.jecs.fi.service.JfiSunOrderListManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiSunOrderListFormController extends BaseFormController {
    private JfiSunOrderListManager jfiSunOrderListManager = null;

    public void setJfiSunOrderListManager(JfiSunOrderListManager jfiSunOrderListManager) {
        this.jfiSunOrderListManager = jfiSunOrderListManager;
    }
    public JfiSunOrderListFormController() {
        setCommandName("jfiSunOrderList");
        setCommandClass(JfiSunOrderList.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String molId = request.getParameter("molId");
        JfiSunOrderList jfiSunOrderList = null;

        if (!StringUtils.isEmpty(molId)) {
            jfiSunOrderList = jfiSunOrderListManager.getJfiSunOrderList(molId);
        } else {
            jfiSunOrderList = new JfiSunOrderList();
        }

        return jfiSunOrderList;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiSunOrderList jfiSunOrderList = (JfiSunOrderList) command;
        boolean isNew = (jfiSunOrderList.getMolId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiSunOrderList".equals(strAction)  ) {
		jfiSunOrderListManager.removeJfiSunOrderList(jfiSunOrderList.getMolId().toString());
		key="jfiSunOrderList.delete";
	}else{
		jfiSunOrderListManager.saveJfiSunOrderList(jfiSunOrderList);
		key="jfiSunOrderList.update";
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
