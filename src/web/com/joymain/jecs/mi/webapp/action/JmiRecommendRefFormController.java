package com.joymain.jecs.mi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.mi.model.JmiRecommendRef;
import com.joymain.jecs.mi.service.JmiRecommendRefManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JmiRecommendRefFormController extends BaseFormController {
    private JmiRecommendRefManager jmiRecommendRefManager = null;

    public void setJmiRecommendRefManager(JmiRecommendRefManager jmiRecommendRefManager) {
        this.jmiRecommendRefManager = jmiRecommendRefManager;
    }
    public JmiRecommendRefFormController() {
        setCommandName("jmiRecommendRef");
        setCommandClass(JmiRecommendRef.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String memberNo = request.getParameter("memberNo");
        JmiRecommendRef jmiRecommendRef = null;

        if (!StringUtils.isEmpty(memberNo)) {
            jmiRecommendRef = jmiRecommendRefManager.getJmiRecommendRef(memberNo);
        } else {
            jmiRecommendRef = new JmiRecommendRef();
        }

        return jmiRecommendRef;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JmiRecommendRef jmiRecommendRef = (JmiRecommendRef) command;


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
