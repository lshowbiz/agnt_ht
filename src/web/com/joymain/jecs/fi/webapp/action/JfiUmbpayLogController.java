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
import com.joymain.jecs.fi.model.JfiUmbpayLog;
import com.joymain.jecs.fi.service.JfiUmbpayLogManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JfiUmbpayLogController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiUmbpayLogController.class);
    private JfiUmbpayLogManager jfiUmbpayLogManager = null;

    public void setJfiUmbpayLogManager(JfiUmbpayLogManager jfiUmbpayLogManager) {
        this.jfiUmbpayLogManager = jfiUmbpayLogManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JfiUmbpayLog jfiUmbpayLog = new JfiUmbpayLog();
        // populate object with request parameters
        BeanUtils.populate(jfiUmbpayLog, request.getParameterMap());

	//List jfiUmbpayLogs = jfiUmbpayLogManager.getJfiUmbpayLogs(jfiUmbpayLog);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jfiUmbpayLogListTable",request, 20);
        List jfiUmbpayLogs = jfiUmbpayLogManager.getJfiUmbpayLogsByCrm(crm,pager);
        request.setAttribute("jfiUmbpayLogListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/jfiUmbpayLogList", "jfiUmbpayLogList", jfiUmbpayLogs);
    }
}
