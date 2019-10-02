package com.joymain.jecs.al.webapp.action;

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
import com.joymain.jecs.al.model.JalTown;
import com.joymain.jecs.al.service.JalTownManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JalTownController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JalTownController.class);
    private JalTownManager jalTownManager = null;

    public void setJalTownManager(JalTownManager jalTownManager) {
        this.jalTownManager = jalTownManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JalTown jalTown = new JalTown();
        // populate object with request parameters
        BeanUtils.populate(jalTown, request.getParameterMap());

	//List jalTowns = jalTownManager.getJalTowns(jalTown);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jalTownListTable",request, 20);
        List jalTowns = jalTownManager.getJalTownsByCrm(crm,pager);
        request.setAttribute("jalTownListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("al/jalTownList", Constants.JALTOWN_LIST, jalTowns);
    }
}
