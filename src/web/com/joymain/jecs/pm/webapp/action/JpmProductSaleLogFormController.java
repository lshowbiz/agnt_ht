package com.joymain.jecs.pm.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.pm.model.JpmProductSaleLog;
import com.joymain.jecs.pm.service.JpmProductSaleLogManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JpmProductSaleLogFormController extends BaseFormController {
    private JpmProductSaleLogManager jpmProductSaleLogManager = null;

    public void setJpmProductSaleLogManager(JpmProductSaleLogManager jpmProductSaleLogManager) {
        this.jpmProductSaleLogManager = jpmProductSaleLogManager;
    }
    public JpmProductSaleLogFormController() {
        setCommandName("jpmProductSaleLog");
        setCommandClass(JpmProductSaleLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String uniNo = request.getParameter("uniNo");
        JpmProductSaleLog jpmProductSaleLog = null;

        if (!StringUtils.isEmpty(uniNo)) {
            jpmProductSaleLog = jpmProductSaleLogManager.getJpmProductSaleLog(uniNo);
        } else {
            jpmProductSaleLog = new JpmProductSaleLog();
        }

        return jpmProductSaleLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JpmProductSaleLog jpmProductSaleLog = (JpmProductSaleLog) command;
        boolean isNew = (jpmProductSaleLog.getUniNo() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJpmProductSaleLog".equals(strAction)  ) {
		jpmProductSaleLogManager.removeJpmProductSaleLog(jpmProductSaleLog.getUniNo().toString());
		key="jpmProductSaleLog.delete";
	}else{
		jpmProductSaleLogManager.saveJpmProductSaleLog(jpmProductSaleLog);
		key="jpmProductSaleLog.update";
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
