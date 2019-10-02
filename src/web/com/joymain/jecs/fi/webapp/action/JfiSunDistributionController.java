package com.joymain.jecs.fi.webapp.action;

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
import com.joymain.jecs.fi.model.JfiSunDistribution;
import com.joymain.jecs.fi.service.JfiSunDistributionManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JfiSunDistributionController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiSunDistributionController.class);
    private JfiSunDistributionManager jfiSunDistributionManager = null;

    public void setJfiSunDistributionManager(JfiSunDistributionManager jfiSunDistributionManager) {
        this.jfiSunDistributionManager = jfiSunDistributionManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JfiSunDistribution jfiSunDistribution = new JfiSunDistribution();
        // populate object with request parameters
        BeanUtils.populate(jfiSunDistribution, request.getParameterMap());

	//List jfiSunDistributions = jfiSunDistributionManager.getJfiSunDistributions(jfiSunDistribution);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jfiSunDistributionListTable",request, 20);
        List jfiSunDistributions = jfiSunDistributionManager.getJfiSunDistributionsByCrm(crm,pager);
        request.setAttribute("jfiSunDistributionListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/jfiSunDistributionList", Constants.JFISUNDISTRIBUTION_LIST, jfiSunDistributions);
    }
}
