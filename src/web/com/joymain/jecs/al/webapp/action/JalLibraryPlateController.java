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
import com.joymain.jecs.al.model.JalLibraryPlate;
import com.joymain.jecs.al.service.JalLibraryPlateManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JalLibraryPlateController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JalLibraryPlateController.class);
    private JalLibraryPlateManager jalLibraryPlateManager = null;

    public void setJalLibraryPlateManager(JalLibraryPlateManager jalLibraryPlateManager) {
        this.jalLibraryPlateManager = jalLibraryPlateManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JalLibraryPlate jalLibraryPlate = new JalLibraryPlate();
        // populate object with request parameters
        BeanUtils.populate(jalLibraryPlate, request.getParameterMap());

	//List jalLibraryPlates = jalLibraryPlateManager.getJalLibraryPlates(jalLibraryPlate);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jalLibraryPlateListTable",request, 20);
        List jalLibraryPlates = jalLibraryPlateManager.getJalLibraryPlatesByCrm(crm,pager);
        request.setAttribute("jalLibraryPlateListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("al/jalLibraryPlateList", Constants.JALLIBRARYPLATE_LIST, jalLibraryPlates);
    }
}
