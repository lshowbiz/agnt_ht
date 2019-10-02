package com.joymain.jecs.pm.webapp.action;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.pm.model.JpmWineOrderInterface;
import com.joymain.jecs.pm.model.JpmWineOrderListInterface;
import com.joymain.jecs.pm.service.JpmWineOrderInterfaceManager;
import com.joymain.jecs.util.wine.WineInterfaceUtil;
import com.joymain.jecs.webapp.action.BaseFormController;

public class JpmWineOrderInterfaceFormController extends BaseFormController {
    private JpmWineOrderInterfaceManager jpmWineOrderInterfaceManager = null;

    public void setJpmWineOrderInterfaceManager(JpmWineOrderInterfaceManager jpmWineOrderInterfaceManager) {
        this.jpmWineOrderInterfaceManager = jpmWineOrderInterfaceManager;
    }

    public JpmWineOrderInterfaceFormController() {
        setCommandName("jpmWineOrderInterface");
        setCommandClass(JpmWineOrderInterface.class);
    }

    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        String moId = request.getParameter("moId");
        JpmWineOrderInterface jpmWineOrderInterface = null;

        if (!StringUtils.isEmpty(moId)) {
            String strAction = request.getParameter("strAction");
            if ("rePushJpmWineOrderInterface".equals(strAction)) {
                Integer ret = jpmWineOrderInterfaceManager.rePushOrderToERP(moId);
                request.setAttribute("repush", ret);
            }

            jpmWineOrderInterface = jpmWineOrderInterfaceManager.getJpmWineOrderInterface(moId);
        } else {
            jpmWineOrderInterface = new JpmWineOrderInterface();
        }

        return jpmWineOrderInterface;
    }

    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JpmWineOrderInterface jpmWineOrderInterface = (JpmWineOrderInterface) command;
        boolean isNew = (jpmWineOrderInterface.getMoId() == null);
        Locale locale = request.getLocale();
        String key = null;
        String strAction = request.getParameter("strAction");
        if ("deleteJpmWineOrderInterface".equals(strAction)) {
            jpmWineOrderInterfaceManager.removeJpmWineOrderInterface(jpmWineOrderInterface.getMoId().toString());
            key = "jpmWineOrderInterface.delete";
        }/* else {
            JpmWineOrderInterface o = testInit();
            new WineInterfaceUtil().saveAndSendOrder(o, 0);
            key = "jpmWineOrderInterface.update";
         }*/

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
