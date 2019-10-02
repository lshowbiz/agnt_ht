package com.joymain.jecs.am.webapp.action;

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
import com.joymain.jecs.am.model.InwViewpeople;
import com.joymain.jecs.am.service.InwViewpeopleManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class InwViewpeopleController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(InwViewpeopleController.class);
    private InwViewpeopleManager inwViewpeopleManager = null;

    public void setInwViewpeopleManager(InwViewpeopleManager inwViewpeopleManager) {
        this.inwViewpeopleManager = inwViewpeopleManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        InwViewpeople inwViewpeople = new InwViewpeople();
        // populate object with request parameters
        BeanUtils.populate(inwViewpeople, request.getParameterMap());

	//List inwViewpeoples = inwViewpeopleManager.getInwViewpeoples(inwViewpeople);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("inwViewpeopleListTable",request, 20);
        List inwViewpeoples = inwViewpeopleManager.getInwViewpeoplesByCrm(crm,pager);
        request.setAttribute("inwViewpeopleListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("am/inwViewpeopleList", "inwViewpeopleList", inwViewpeoples);
    }
}
