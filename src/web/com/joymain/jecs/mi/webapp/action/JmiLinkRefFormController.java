package com.joymain.jecs.mi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.mi.model.JmiLinkRef;
import com.joymain.jecs.mi.service.JmiLinkRefManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JmiLinkRefFormController extends BaseFormController {
    private JmiLinkRefManager jmiLinkRefManager = null;

    public void setJmiLinkRefManager(JmiLinkRefManager jmiLinkRefManager) {
        this.jmiLinkRefManager = jmiLinkRefManager;
    }
    public JmiLinkRefFormController() {
        setCommandName("jmiLinkRef");
        setCommandClass(JmiLinkRef.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String memberNo = request.getParameter("memberNo");
        JmiLinkRef jmiLinkRef = null;

        if (!StringUtils.isEmpty(memberNo)) {
            jmiLinkRef = jmiLinkRefManager.getJmiLinkRef(memberNo);
        } else {
            jmiLinkRef = new JmiLinkRef();
        }

        return jmiLinkRef;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JmiLinkRef jmiLinkRef = (JmiLinkRef) command;


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
