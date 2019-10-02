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
import com.joymain.jecs.fi.model.JfiSunMemberOrderFee;
import com.joymain.jecs.fi.service.JfiSunMemberOrderFeeManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JfiSunMemberOrderFeeController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiSunMemberOrderFeeController.class);
    private JfiSunMemberOrderFeeManager jfiSunMemberOrderFeeManager = null;

    public void setJfiSunMemberOrderFeeManager(JfiSunMemberOrderFeeManager jfiSunMemberOrderFeeManager) {
        this.jfiSunMemberOrderFeeManager = jfiSunMemberOrderFeeManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JfiSunMemberOrderFee jfiSunMemberOrderFee = new JfiSunMemberOrderFee();
        // populate object with request parameters
        BeanUtils.populate(jfiSunMemberOrderFee, request.getParameterMap());

	//List jfiSunMemberOrderFees = jfiSunMemberOrderFeeManager.getJfiSunMemberOrderFees(jfiSunMemberOrderFee);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jfiSunMemberOrderFeeListTable",request, 20);
        List jfiSunMemberOrderFees = jfiSunMemberOrderFeeManager.getJfiSunMemberOrderFeesByCrm(crm,pager);
        request.setAttribute("jfiSunMemberOrderFeeListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/jfiSunMemberOrderFeeList", Constants.JFISUNMEMBERORDERFEE_LIST, jfiSunMemberOrderFees);
    }
}
