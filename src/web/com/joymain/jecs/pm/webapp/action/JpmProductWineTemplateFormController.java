package com.joymain.jecs.pm.webapp.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.pm.model.JpmProductWineTemplate;
import com.joymain.jecs.pm.model.JpmProductWineTemplateSub;
import com.joymain.jecs.pm.service.JpmProductWineTemplateManager;
import com.joymain.jecs.pm.service.JpmProductWineTemplateSubManager;
import com.joymain.jecs.webapp.action.BaseFormController;

public class JpmProductWineTemplateFormController extends BaseFormController {
    private JpmProductWineTemplateManager jpmProductWineTemplateManager = null;

    private JpmProductWineTemplateSubManager jpmProductWineTemplateSubManager = null;

    public void setJpmProductWineTemplateManager(JpmProductWineTemplateManager jpmProductWineTemplateManager) {
        this.jpmProductWineTemplateManager = jpmProductWineTemplateManager;
    }

    public JpmProductWineTemplateFormController() {
        setCommandName("jpmProductWineTemplate");
        setCommandClass(JpmProductWineTemplate.class);
    }

    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        String productTemplateNo = request.getParameter("productTemplateNo");
        JpmProductWineTemplate jpmProductWineTemplate = null;

        if (!StringUtils.isEmpty(productTemplateNo)) {
            jpmProductWineTemplate = jpmProductWineTemplateManager.getJpmProductWineTemplate(productTemplateNo);
        } else {
            jpmProductWineTemplate = new JpmProductWineTemplate();
        }

        return jpmProductWineTemplate;
    }

    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JpmProductWineTemplate jpmProductWineTemplate = (JpmProductWineTemplate) command;
        boolean isNew = (jpmProductWineTemplate.getProductTemplateNo() == null);
        Locale locale = request.getLocale();
        String key = null;
        String strAction = request.getParameter("strAction");
        if ("deleteJpmProductWineTemplate".equals(strAction)) {
            jpmProductWineTemplateManager.removeJpmProductWineTemplate(jpmProductWineTemplate.getProductTemplateNo().toString());
            key = "jpmProductWineTemplate.delete";
        } else {
            jpmProductWineTemplate.setCreateTime(new Date());
            jpmProductWineTemplateManager.saveJpmProductWineTemplateAndSub(request, jpmProductWineTemplate);
            key = "jpmProductWineTemplate.update";
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

    public void setJpmProductWineTemplateSubManager(JpmProductWineTemplateSubManager jpmProductWineTemplateSubManager) {
        this.jpmProductWineTemplateSubManager = jpmProductWineTemplateSubManager;
    }

}
