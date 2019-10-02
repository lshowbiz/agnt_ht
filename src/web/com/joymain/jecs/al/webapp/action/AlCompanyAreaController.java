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
import com.joymain.jecs.al.model.AlCompanyArea;
import com.joymain.jecs.al.service.AlCompanyAreaManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class AlCompanyAreaController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(AlCompanyAreaController.class);
    private AlCompanyAreaManager alCompanyAreaManager = null;

    public void setAlCompanyAreaManager(AlCompanyAreaManager alCompanyAreaManager) {
        this.alCompanyAreaManager = alCompanyAreaManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        AlCompanyArea alCompanyArea = new AlCompanyArea();
        // populate object with request parameters
        BeanUtils.populate(alCompanyArea, request.getParameterMap());

				//List alCompanyAreas = alCompanyAreaManager.getAlCompanyAreas(alCompanyArea);
				/**** auto pagination ***/
				CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("alCompanyAreaListTable",request, 20);
        List alCompanyAreas = alCompanyAreaManager.getAlCompanyAreasByCrm(crm,pager);
        request.setAttribute("alCompanyAreaListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("al/alCompanyAreaList", Constants.ALCOMPANYAREA_LIST, alCompanyAreas);
    }
}
