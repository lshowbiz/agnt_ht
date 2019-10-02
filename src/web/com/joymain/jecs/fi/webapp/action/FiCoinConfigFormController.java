package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.FiCoinConfig;
import com.joymain.jecs.fi.service.FiCoinConfigManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
/**
 * 积分换购活动配置
 * @author Administrator
 *
 */
public class FiCoinConfigFormController extends BaseFormController {
    private FiCoinConfigManager fiCoinConfigManager = null;

    public void setFiCoinConfigManager(FiCoinConfigManager fiCoinConfigManager) {
        this.fiCoinConfigManager = fiCoinConfigManager;
    }
    public FiCoinConfigFormController() {
        setCommandName("fiCoinConfig");
        setCommandClass(FiCoinConfig.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String configId = request.getParameter("configId");
        FiCoinConfig fiCoinConfig = null;

        if (!StringUtils.isEmpty(configId)) {
            fiCoinConfig = fiCoinConfigManager.getFiCoinConfig(configId);
        } else {
            fiCoinConfig = new FiCoinConfig();
        }

        return fiCoinConfig;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        FiCoinConfig fiCoinConfig = (FiCoinConfig) command;
        boolean isNew = (fiCoinConfig.getConfigId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteFiCoinConfig".equals(strAction)  ) {
		fiCoinConfigManager.removeFiCoinConfig(fiCoinConfig.getConfigId().toString());
		key="fiCoinConfig.delete";
	}else{
		fiCoinConfigManager.saveFiCoinConfig(fiCoinConfig);
		key="fiCoinConfig.update";
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
