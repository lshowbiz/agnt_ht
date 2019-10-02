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
import com.joymain.jecs.fi.model.Jfi99billmsLog;
import com.joymain.jecs.fi.service.Jfi99billmsLogManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 快钱分润支付记录
 * @author Alvin
 *
 */
public class Jfi99billmsLogController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(Jfi99billmsLogController.class);
    private Jfi99billmsLogManager jfi99billmsLogManager = null;

    public void setJfi99billmsLogManager(Jfi99billmsLogManager jfi99billmsLogManager) {
        this.jfi99billmsLogManager = jfi99billmsLogManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jfi99billmsLogListTable",request, 20);
        List jfi99billmsLogs = jfi99billmsLogManager.getJfi99billmsLogsByCrm(crm,pager);
        request.setAttribute("jfi99billmsLogListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("fi/jfi99billmsLogList", Constants.JFI99BILLMSLOG_LIST, jfi99billmsLogs);
    }
}
