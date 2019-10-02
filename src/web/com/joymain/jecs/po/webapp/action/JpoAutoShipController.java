package com.joymain.jecs.po.webapp.action;

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
import com.joymain.jecs.po.model.JpoAutoShip;
import com.joymain.jecs.po.service.JpoAutoShipManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JpoAutoShipController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoAutoShipController.class);
    private JpoAutoShipManager jpoAutoShipManager = null;

    public void setJpoAutoShipManager(JpoAutoShipManager jpoAutoShipManager) {
        this.jpoAutoShipManager = jpoAutoShipManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        SysUser loginSysUser = SessionLogin.getLoginUser(request);

        //List jpoAutoShips = jpoAutoShipManager.getJpoAutoShips(jpoAutoShip);
        /**** auto pagination ***/
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        crm.addField("companyCode", loginSysUser.getCompanyCode());
        Pager pager = new Pager("jpoAutoShipListTable",request, 20);
        List jpoAutoShips = jpoAutoShipManager.getJpoAutoShipsByCrm(crm,pager);
        request.setAttribute("jpoAutoShipListTable_totalRows", pager.getTotalObjects());
        /*****/
        
        String autoship = jpoAutoShipManager.getAutoShipConfigValue("autoship");
        request.setAttribute("autoship", autoship);
        
        return new ModelAndView("po/jpoAutoShipList", Constants.JPOAUTOSHIP_LIST, jpoAutoShips);
    }
}
