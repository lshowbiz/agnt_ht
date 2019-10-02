package com.joymain.jecs.am.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysListValue;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysListValueManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.am.model.AmMessagePermit;
import com.joymain.jecs.am.service.AmMessagePermitManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class AmMessagePermitController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(AmMessagePermitController.class);
    private AmMessagePermitManager amMessagePermitManager = null;
    private SysListValueManager sysListValueManager;
    public void setSysListValueManager(SysListValueManager sysListValueManager) {
		this.sysListValueManager = sysListValueManager;
	}

	public void setAmMessagePermitManager(AmMessagePermitManager amMessagePermitManager) {
        this.amMessagePermitManager = amMessagePermitManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		
        String view = "am/amMessagePermitMain";
        
        CommonRecord crm = initCommonRecord(request);
        
		if (!sessionLogin.getIsManager()) {
			
		}
		String strAction = request.getParameter("strAction");
		
		if ("amMessagePermitMain".equals(strAction)) {
			view = "am/amMessagePermitMain";
		} else if ("amMessagePermitTree".equals(strAction)) {
			view = "am/amMessagePermitTree";

			List<SysListValue> msgTypes = sysListValueManager.getSysListValuesByCode("msgclassno",sessionLogin.getCompanyCode());
			request.setAttribute("msgTypes", msgTypes);
		}else if ("amMessagePermitUserContent".equals(strAction)) {
			view = "am/amMessagePermitList";
			
	        Pager pager = new Pager("amMessagePermitListTable",request, 20);
	        List amMessagePermitList = amMessagePermitManager.getAmMessagePermitsByCrm(crm,pager);
	        request.setAttribute("amMessagePermitListTable_totalRows", pager.getTotalObjects());
	        request.setAttribute("amMessagePermitList", amMessagePermitList);
		}
		
		
        
        
        
        

        return new ModelAndView(view);
    }
}
