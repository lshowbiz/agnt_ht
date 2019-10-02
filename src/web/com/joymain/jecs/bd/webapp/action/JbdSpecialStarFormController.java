package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.bd.model.JbdSpecialStar;
import com.joymain.jecs.bd.service.JbdSpecialStarManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdSpecialStarFormController extends BaseFormController {
    private JbdSpecialStarManager jbdSpecialStarManager = null;

    public void setJbdSpecialStarManager(JbdSpecialStarManager jbdSpecialStarManager) {
        this.jbdSpecialStarManager = jbdSpecialStarManager;
    }
    public JbdSpecialStarFormController() {
        setCommandName("jbdSpecialStar");
        setCommandClass(JbdSpecialStar.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String userCode = request.getParameter("userCode");
        JbdSpecialStar jbdSpecialStar = null;

        if (!StringUtils.isEmpty(userCode)) {
            jbdSpecialStar = jbdSpecialStarManager.getJbdSpecialStar(userCode);
        } else {
            jbdSpecialStar = new JbdSpecialStar();
        }

        return jbdSpecialStar;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdSpecialStar jbdSpecialStar = (JbdSpecialStar) command;
        boolean isNew = (jbdSpecialStar.getUserCode() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdSpecialStar".equals(strAction)  ) {
		jbdSpecialStarManager.removeJbdSpecialStar(jbdSpecialStar.getUserCode().toString());
		key="jbdSpecialStar.delete";
	}else{
		jbdSpecialStarManager.saveJbdSpecialStar(jbdSpecialStar);
		key="jbdSpecialStar.update";
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
