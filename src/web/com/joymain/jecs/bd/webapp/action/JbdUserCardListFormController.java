package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.bd.model.JbdUserCardList;
import com.joymain.jecs.bd.service.JbdUserCardListManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdUserCardListFormController extends BaseFormController {
    private JbdUserCardListManager jbdUserCardListManager = null;

    public void setJbdUserCardListManager(JbdUserCardListManager jbdUserCardListManager) {
        this.jbdUserCardListManager = jbdUserCardListManager;
    }
    public JbdUserCardListFormController() {
        setCommandName("jbdUserCardList");
        setCommandClass(JbdUserCardList.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdUserCardList jbdUserCardList = null;

        if (!StringUtils.isEmpty(id)) {
            jbdUserCardList = jbdUserCardListManager.getJbdUserCardList(id);
        } else {
            jbdUserCardList = new JbdUserCardList();
        }

        return jbdUserCardList;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdUserCardList jbdUserCardList = (JbdUserCardList) command;
        boolean isNew = (jbdUserCardList.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdUserCardList".equals(strAction)  ) {
		jbdUserCardListManager.removeJbdUserCardList(jbdUserCardList.getId().toString());
		key="jbdUserCardList.delete";
	}else{
		jbdUserCardListManager.saveJbdUserCardList(jbdUserCardList);
		key="jbdUserCardList.update";
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
