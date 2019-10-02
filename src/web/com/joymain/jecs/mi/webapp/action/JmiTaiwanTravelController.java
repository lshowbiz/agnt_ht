package com.joymain.jecs.mi.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.mi.model.JmiTaiwanTravel;
import com.joymain.jecs.mi.service.JmiTaiwanTravelManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JmiTaiwanTravelController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiTaiwanTravelController.class);
    private JmiTaiwanTravelManager jmiTaiwanTravelManager = null;

    public void setJmiTaiwanTravelManager(JmiTaiwanTravelManager jmiTaiwanTravelManager) {
        this.jmiTaiwanTravelManager = jmiTaiwanTravelManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

    	SysUser defSysUser=SessionLogin.getLoginUser(request);
        CommonRecord crm=RequestUtil.toCommonRecord(request);
    	if("M".equals(defSysUser.getUserType())){
    		crm.addField("createUser", defSysUser.getUserCode());
    	}
        Pager pager = new Pager("jmiTaiwanTravelListTable",request, 20);
        List jmiTaiwanTravels = jmiTaiwanTravelManager.getJmiTaiwanTravelsByCrm(crm,pager);
        request.setAttribute("jmiTaiwanTravelListTable_totalRows", pager.getTotalObjects());
        

        return new ModelAndView("mi/jmiTaiwanTravelList", Constants.JMITAIWANTRAVEL_LIST, jmiTaiwanTravels);
    }
}
