package com.joymain.jecs.po.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.po.model.JpoProductNumLimit;
import com.joymain.jecs.po.service.JpoProductNumLimitManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;

public class JpoProductNumLimitController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoProductNumLimitController.class);
    private JpoProductNumLimitManager jpoProductNumLimitManager = null;

    public void setJpoProductNumLimitManager(JpoProductNumLimitManager jpoProductNumLimitManager) {
        this.jpoProductNumLimitManager = jpoProductNumLimitManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JpoProductNumLimit jpoProductNumLimit = new JpoProductNumLimit();
        // populate object with request parameters
        BeanUtils.populate(jpoProductNumLimit, request.getParameterMap());

	//List jpoProductNumLimits = jpoProductNumLimitManager.getJpoProductNumLimits(jpoProductNumLimit);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jpoProductNumLimitListTable",request, 20);
        List jpoProductNumLimits = jpoProductNumLimitManager.getJpoProductNumLimitsByCrm(crm,pager);
        request.setAttribute("jpoProductNumLimitListTable_totalRows", pager.getTotalObjects());
        request.setAttribute(Constants.JPOPRODUCTNUMLIMIT_LIST, jpoProductNumLimits);
        /*****/

        return new ModelAndView("po/jpoProductNumLimitList", Constants.JPOPRODUCTNUMLIMIT_LIST, jpoProductNumLimits);
    }
}
