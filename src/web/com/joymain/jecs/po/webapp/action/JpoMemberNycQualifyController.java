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
import com.joymain.jecs.po.model.JpoMemberNycQualify;
import com.joymain.jecs.po.service.JpoMemberNycQualifyManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JpoMemberNycQualifyController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoMemberNycQualifyController.class);
    private JpoMemberNycQualifyManager jpoMemberNycQualifyManager = null;

    public void setJpoMemberNycQualifyManager(JpoMemberNycQualifyManager jpoMemberNycQualifyManager) {
        this.jpoMemberNycQualifyManager = jpoMemberNycQualifyManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
//        if (log.isDebugEnabled()) {
//            log.debug("entering 'handleRequest' method...");
//        }
//
//        JpoMemberNycQualify jpoMemberNycQualify = new JpoMemberNycQualify();
//        // populate object with request parameters
//        BeanUtils.populate(jpoMemberNycQualify, request.getParameterMap());
//
//	//List jpoMemberNycQualifys = jpoMemberNycQualifyManager.getJpoMemberNycQualifys(jpoMemberNycQualify);
//	/**** auto pagination ***/
//	CommonRecord crm=RequestUtil.toCommonRecord(request);
//        Pager pager = new Pager("jpoMemberNycQualifyListTable",request, 20);
//        List jpoMemberNycQualifys = jpoMemberNycQualifyManager.getJpoMemberNycQualifysByCrm(crm,pager);
//        request.setAttribute("jpoMemberNycQualifyListTable_totalRows", pager.getTotalObjects());
//        /*****/
//
//        return new ModelAndView("po/jpoMemberNycQualifyList", Constants.ADMIN_ROLE, jpoMemberNycQualifys);
        return null;
    }
}
