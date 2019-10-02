package com.joymain.jecs.pm.webapp.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.pm.model.JpmWineSettingInf;
import com.joymain.jecs.pm.model.JpmWineSettingListInf;
import com.joymain.jecs.pm.service.JpmWineSettingInfManager;
import com.joymain.jecs.util.wine.WineInterfaceUtil;
import com.joymain.jecs.webapp.action.BaseFormController;

public class JpmWineSettingInfFormController extends BaseFormController {
    private JpmWineSettingInfManager jpmWineSettingInfManager = null;

    public void setJpmWineSettingInfManager(JpmWineSettingInfManager jpmWineSettingInfManager) {
        this.jpmWineSettingInfManager = jpmWineSettingInfManager;
    }

    public JpmWineSettingInfFormController() {
        setCommandName("jpmWineSettingInf");
        setCommandClass(JpmWineSettingInf.class);
    }

    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        String settingId = request.getParameter("settingId");
        JpmWineSettingInf jpmWineSettingInf = null;

        if (!StringUtils.isEmpty(settingId)) {
            jpmWineSettingInf = jpmWineSettingInfManager.getJpmWineSettingInf(settingId);
        } else {
            jpmWineSettingInf = new JpmWineSettingInf();
        }

        return jpmWineSettingInf;
    }

    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JpmWineSettingInf jpmWineSettingInf = (JpmWineSettingInf) command;
        boolean isNew = (jpmWineSettingInf.getSettingId() == null);
        Locale locale = request.getLocale();
        String key = null;
        String strAction = request.getParameter("strAction");
        if ("deleteJpmWineSettingInf".equals(strAction)) {
            jpmWineSettingInfManager.removeJpmWineSettingInf(jpmWineSettingInf.getSettingId().toString());
            key = "jpmWineSettingInf.delete";
        } else {
            //new WineInterfaceUtil().saveAndSendSettingBill(testInit(), 0);
            jpmWineSettingInfManager.saveJpmWineSettingInf(jpmWineSettingInf);
            key = "jpmWineSettingInf.update";
        }

        return new ModelAndView(getSuccessView());
    }

    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        // TODO Auto-generated method stub
        //		binder.setAllowedFields(allowedFields);
        //		binder.setDisallowedFields(disallowedFields);
        //		binder.setRequiredFields(requiredFields);
        super.initBinder(request, binder);
    }

}
