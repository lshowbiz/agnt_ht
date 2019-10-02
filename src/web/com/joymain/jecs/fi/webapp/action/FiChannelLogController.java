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
import com.joymain.jecs.fi.model.FiChannelLog;
import com.joymain.jecs.fi.service.FiChannelLogManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 盛付通支付记录
 * @author Alvin
 *
 */
public class FiChannelLogController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiChannelLogController.class);
    private FiChannelLogManager fiChannelLogManager = null;

    public void setFiChannelLogManager(FiChannelLogManager fiChannelLogManager) {
        this.fiChannelLogManager = fiChannelLogManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("fiChannelLogListTable",request, 20);
        List fiChannelLogs = fiChannelLogManager.getFiChannelLogsByCrm(crm,pager);
        request.setAttribute("fiChannelLogListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("fi/fiChannelLogList", "fiChannelLogList", fiChannelLogs);
    }
}
