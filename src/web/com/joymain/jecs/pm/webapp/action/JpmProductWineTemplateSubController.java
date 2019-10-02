package com.joymain.jecs.pm.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pm.model.JpmProductWineTemplateSub;
import com.joymain.jecs.pm.service.JpmProductWineTemplateSubManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JpmProductWineTemplateSubController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpmProductWineTemplateSubController.class);

    private JpmProductWineTemplateSubManager jpmProductWineTemplateSubManager = null;

    public void setJpmProductWineTemplateSubManager(JpmProductWineTemplateSubManager jpmProductWineTemplateSubManager) {
        this.jpmProductWineTemplateSubManager = jpmProductWineTemplateSubManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        String strAction = request.getParameter("strAction");
        String viewName = "pm/jpmProductWineTemplateSubList";
        JpmProductWineTemplateSub jpmProductWineTemplateSub = new JpmProductWineTemplateSub();
        // populate object with request parameters
        BeanUtils.populate(jpmProductWineTemplateSub, request.getParameterMap());

        //List jpmProductWineTemplateSubs = jpmProductWineTemplateSubManager.getJpmProductWineTemplateSubs(jpmProductWineTemplateSub);
        /**** auto pagination ***/
        CommonRecord crm = RequestUtil.toCommonRecord(request);

        if (strAction.equals("selectProduct")) {
            viewName = "pm/jpmProductWineTemplateSubSelect";
            String isInvalid = request.getParameter("isInvalid");
            crm.setValue("isInvalid", isInvalid);
        }

        Pager pager = new Pager("jpmProductWineTemplateSubListTable", request, 20);
        List jpmProductWineTemplateSubs = jpmProductWineTemplateSubManager.getJpmProductWineTemplateSubsByCrm(crm, pager);
        request.setAttribute("jpmProductWineTemplateSubListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView(viewName, "jpmProductWineTemplateSubList", jpmProductWineTemplateSubs);
    }
}
