package com.joymain.jecs.fi.webapp.action;

import java.util.List;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.JfiDepositMoney;
import com.joymain.jecs.fi.service.JfiDepositMoneyManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JfiDepositMoneyController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiDepositMoneyController.class);
    private JfiDepositMoneyManager jfiDepositMoneyManager = null;

    public void setJfiDepositMoneyManager(JfiDepositMoneyManager jfiDepositMoneyManager) {
        this.jfiDepositMoneyManager = jfiDepositMoneyManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

 
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        
        

    	WeekFormatUtil.setSearchFweek(crm);
        
        
        Pager pager = new Pager("jfiDepositMoneyListTable",request, 20);
        List jfiDepositMoneys = jfiDepositMoneyManager.getJfiDepositMoneysByCrm(crm,pager);
        

        BigDecimal depositMoneySum=jfiDepositMoneyManager.getSumDepositMoney(crm);
        request.setAttribute("depositMoneySum", depositMoneySum);
        
        
        
        request.setAttribute("jfiDepositMoneyListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/jfiDepositMoneyList","jfiDepositMoneyList", jfiDepositMoneys);
    }
}
