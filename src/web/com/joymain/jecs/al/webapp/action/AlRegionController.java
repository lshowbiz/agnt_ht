package com.joymain.jecs.al.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlRegion;
import com.joymain.jecs.al.service.AlRegionManager;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class AlRegionController implements Controller {
    private final Log log = LogFactory.getLog(AlRegionController.class);
    private AlRegionManager alRegionManager = null;

    public void setAlRegionManager(AlRegionManager alRegionManager) {
        this.alRegionManager = alRegionManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        AlRegion alRegion = new AlRegion();
        // populate object with request parameters
        BeanUtils.populate(alRegion, request.getParameterMap());

        List alRegions = alRegionManager.getAlRegions(alRegion);

        return new ModelAndView("al/alRegionList", Constants.ALREGION_LIST, alRegions);
    }
}
