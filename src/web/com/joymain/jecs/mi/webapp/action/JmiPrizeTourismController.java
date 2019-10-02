package com.joymain.jecs.mi.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.mi.model.JmiPrizeTourism;
import com.joymain.jecs.mi.service.JmiPrizeTourismManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
 
public class JmiPrizeTourismController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiPrizeTourismController.class);
    private JmiPrizeTourismManager jmiPrizeTourismManager = null;

    public void setJmiPrizeTourismManager(JmiPrizeTourismManager jmiPrizeTourismManager) {
        this.jmiPrizeTourismManager = jmiPrizeTourismManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JmiPrizeTourism jmiPrizeTourism = new JmiPrizeTourism();
        // populate object with request parameters
        BeanUtils.populate(jmiPrizeTourism, request.getParameterMap());

	//List jmiPrizeTourisms = jmiPrizeTourismManager.getJmiPrizeTourisms(jmiPrizeTourism);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jmiPrizeTourismListTable",request, 20);
        List jmiPrizeTourisms = jmiPrizeTourismManager.getJmiPrizeTourismsByCrm(crm,pager);
        request.setAttribute("jmiPrizeTourismListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("mi/jmiPrizeTourismList", "jmiPrizeTourismList", jmiPrizeTourisms);
    }
}
