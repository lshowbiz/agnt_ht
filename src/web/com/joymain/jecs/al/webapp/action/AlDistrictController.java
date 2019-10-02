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
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class AlDistrictController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(AlDistrictController.class);
    private AlDistrictManager alDistrictManager = null;

    public void setAlDistrictManager(AlDistrictManager alDistrictManager) {
        this.alDistrictManager = alDistrictManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("alDistrictListTable",request, 20);
        List alDistricts = alDistrictManager.getAlDistrictsByCrm(crm,pager);
        request.setAttribute("alDistrictListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("al/alDistrictList", Constants.ALDISTRICT_LIST, alDistricts);
    }
}
