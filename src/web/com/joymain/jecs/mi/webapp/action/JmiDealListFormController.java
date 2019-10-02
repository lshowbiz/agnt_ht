package com.joymain.jecs.mi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.mi.model.JmiDealList;
import com.joymain.jecs.mi.service.JmiDealListManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JmiDealListFormController extends BaseFormController {
    private JmiDealListManager jmiDealListManager = null;

    public void setJmiDealListManager(JmiDealListManager jmiDealListManager) {
        this.jmiDealListManager = jmiDealListManager;
    }
    public JmiDealListFormController() {
        setCommandName("jmiDealList");
        setCommandClass(JmiDealList.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JmiDealList jmiDealList = null;

        if (!StringUtils.isEmpty(id)) {
            jmiDealList = jmiDealListManager.getJmiDealList(id);
        } else {
            jmiDealList = new JmiDealList();
        }

        return jmiDealList;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JmiDealList jmiDealList = (JmiDealList) command;
        boolean isNew = (jmiDealList.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJmiDealList".equals(strAction)  ) {
		jmiDealListManager.removeJmiDealList(jmiDealList.getId().toString());
		key="jmiDealList.delete";
	}else{
		jmiDealListManager.saveJmiDealList(jmiDealList);
		key="jmiDealList.update";
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
