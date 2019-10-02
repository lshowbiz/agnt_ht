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
import com.joymain.jecs.fi.model.JfiAlipayLog;
import com.joymain.jecs.fi.service.JfiAlipayLogManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 支付宝支付记录
 * @author Alvin
 *
 */
public class JfiAlipayLogController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiAlipayLogController.class);
    private JfiAlipayLogManager jfiAlipayLogManager = null;

    public void setJfiAlipayLogManager(JfiAlipayLogManager jfiAlipayLogManager) {
        this.jfiAlipayLogManager = jfiAlipayLogManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jfiAlipayLogListTable",request, 20);
        crm.addField("tradeStatus", "TRADE_SUCCESS");
        List jfiAlipayLogs = jfiAlipayLogManager.getJfiAlipayLogsByCrm(crm,pager);
        request.setAttribute("jfiAlipayLogListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("fi/jfiAlipayLogList", Constants.JFIALIPAYLOG_LIST, jfiAlipayLogs);
    }
}
