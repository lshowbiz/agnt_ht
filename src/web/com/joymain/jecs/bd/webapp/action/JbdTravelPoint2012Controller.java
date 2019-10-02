package com.joymain.jecs.bd.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.JbdTravelPoint2012;
import com.joymain.jecs.bd.service.JbdTravelPoint2012Manager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdTravelPoint2012Controller extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdTravelPoint2012Controller.class);
    private JbdTravelPoint2012Manager jbdTravelPoint2012Manager = null;

    public void setJbdTravelPoint2012Manager(JbdTravelPoint2012Manager jbdTravelPoint2012Manager) {
        this.jbdTravelPoint2012Manager = jbdTravelPoint2012Manager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdTravelPoint2012ListTable",request, 20);
        


        String userCode=request.getParameter("userCode");
        
        List jbdTravelPoint2012s = null;
        if(!StringUtil.isEmpty(userCode) ){
        	jbdTravelPoint2012s = jbdTravelPoint2012Manager.getJbdTravelPoint2012sByCrm(crm,pager);
        }
        
        
        request.setAttribute("jbdTravelPoint2012ListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdTravelPoint2012List", Constants.JBDTRAVELPOINT2012_LIST, jbdTravelPoint2012s);
    }
}
