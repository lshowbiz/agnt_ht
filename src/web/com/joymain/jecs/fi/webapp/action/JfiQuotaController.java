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
import com.joymain.jecs.fi.model.JfiQuota;
import com.joymain.jecs.fi.service.JfiQuotaManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JfiQuotaController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiQuotaController.class);
    private JfiQuotaManager jfiQuotaManager = null;

    public void setJfiQuotaManager(JfiQuotaManager jfiQuotaManager) {
        this.jfiQuotaManager = jfiQuotaManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JfiQuota jfiQuota = new JfiQuota();
        // populate object with request parameters
        BeanUtils.populate(jfiQuota, request.getParameterMap());

	//List jfiQuotas = jfiQuotaManager.getJfiQuotas(jfiQuota);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jfiQuotaListTable",request, 20);
        List jfiQuotas = jfiQuotaManager.getJfiQuotasByCrm(crm,pager);
        request.setAttribute("jfiQuotaListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/jfiQuotaList", Constants.JFIQUOTA_LIST, jfiQuotas);
    }
}
