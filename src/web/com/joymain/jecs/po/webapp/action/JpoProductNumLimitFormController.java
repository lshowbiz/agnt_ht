package com.joymain.jecs.po.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.po.model.JpoProductNumLimit;
import com.joymain.jecs.po.service.JpoProductNumLimitManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JpoProductNumLimitFormController extends BaseFormController {
    private JpoProductNumLimitManager jpoProductNumLimitManager = null;

    public void setJpoProductNumLimitManager(JpoProductNumLimitManager jpoProductNumLimitManager) {
        this.jpoProductNumLimitManager = jpoProductNumLimitManager;
    }
    public JpoProductNumLimitFormController() {
        setCommandName("jpoProductNumLimit");
        setCommandClass(JpoProductNumLimit.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JpoProductNumLimit jpoProductNumLimit = null;

        if (!StringUtils.isEmpty(id)) {
            jpoProductNumLimit = jpoProductNumLimitManager.getJpoProductNumLimit(id);
        } else {
            jpoProductNumLimit = new JpoProductNumLimit();
        }

        return jpoProductNumLimit;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JpoProductNumLimit jpoProductNumLimit = (JpoProductNumLimit) command;
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	String startime = request.getParameter("startime");
	String endtime = request.getParameter("endtime");
	if ("deleteJpoProductNumLimit".equals(strAction)  ) {
		jpoProductNumLimitManager.removeJpoProductNumLimit(jpoProductNumLimit.getId().toString());
		key="jpoProductNumLimit.delete";
	}else{
		
		jpoProductNumLimit.setStartime(
				DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", startime+" 00:00:00"));
		jpoProductNumLimit.setEndtime(
				DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", endtime+" 23:59:59"));
		
		jpoProductNumLimitManager.saveJpoProductNumLimit(jpoProductNumLimit);
		key="jpoProductNumLimit.update";
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
