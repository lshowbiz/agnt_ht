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
import com.joymain.jecs.al.model.JalPostalcode;
import com.joymain.jecs.al.service.JalPostalcodeManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JalPostalcodeController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JalPostalcodeController.class);
    private JalPostalcodeManager jalPostalcodeManager = null;

    public void setJalPostalcodeManager(JalPostalcodeManager jalPostalcodeManager) {
        this.jalPostalcodeManager = jalPostalcodeManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JalPostalcode jalPostalcode = new JalPostalcode();
        // populate object with request parameters
        BeanUtils.populate(jalPostalcode, request.getParameterMap());

	//List jalPostalcodes = jalPostalcodeManager.getJalPostalcodes(jalPostalcode);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jalPostalcodeListTable",request, 20);
        List jalPostalcodes = jalPostalcodeManager.getJalPostalcodesByCrm(crm,pager);
        request.setAttribute("jalPostalcodeListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("al/jalPostalcodeList", Constants.JALPOSTALCODE_LIST, jalPostalcodes);
    }
}
