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
import com.joymain.jecs.pm.model.JpmProductWineTemplate;
import com.joymain.jecs.pm.service.JpmProductWineTemplateManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JpmProductWineTemplateController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpmProductWineTemplateController.class);

    private JpmProductWineTemplateManager jpmProductWineTemplateManager = null;

    public void setJpmProductWineTemplateManager(JpmProductWineTemplateManager jpmProductWineTemplateManager) {
        this.jpmProductWineTemplateManager = jpmProductWineTemplateManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JpmProductWineTemplate jpmProductWineTemplate = new JpmProductWineTemplate();
        // populate object with request parameters
        BeanUtils.populate(jpmProductWineTemplate, request.getParameterMap());

        //List jpmProductWineTemplates = jpmProductWineTemplateManager.getJpmProductWineTemplates(jpmProductWineTemplate);
        /**** auto pagination ***/
        CommonRecord crm = RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jpmProductWineTemplateListTable", request, 20);
        List jpmProductWineTemplates = jpmProductWineTemplateManager.getJpmProductWineTemplatesByCrm(crm, pager);
        request.setAttribute("jpmProductWineTemplateListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("pm/jpmProductWineTemplateList", "jpmProductWineTemplateList", jpmProductWineTemplates);
    }
}
