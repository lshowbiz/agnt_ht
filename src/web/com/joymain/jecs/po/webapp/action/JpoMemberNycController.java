package com.joymain.jecs.po.webapp.action;

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
import com.joymain.jecs.po.model.JpoMemberNyc;
import com.joymain.jecs.po.service.JpoMemberNycManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JpoMemberNycController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoMemberNycController.class);
    private JpoMemberNycManager jpoMemberNycManager = null;

    public void setJpoMemberNycManager(JpoMemberNycManager jpoMemberNycManager) {
        this.jpoMemberNycManager = jpoMemberNycManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }


        JpoMemberNyc jpoMemberNyc = new JpoMemberNyc();
        // populate object with request parameters
        BeanUtils.populate(jpoMemberNyc, request.getParameterMap());

	//List jpoMemberNycs = jpoMemberNycManager.getJpoMemberNycs(jpoMemberNyc);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jpoMemberNycListTable",request, 20);
        List jpoMemberNycs = jpoMemberNycManager.getJpoMemberNycsByCrm(crm,pager);
        request.setAttribute("jpoMemberNycListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("po/jpoMemberNycList", Constants.JPOMEMBERNYC_LIST, jpoMemberNycs);
    }
}
