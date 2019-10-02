package com.joymain.jecs.pd.webapp.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.pd.service.PdShipStrategyManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class PdShipStrategyController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdShipStrategyController.class);
    private PdShipStrategyManager pdShipStrategyManager = null;

    public void setPdShipStrategyManager(PdShipStrategyManager pdShipStrategyManager) {
        this.pdShipStrategyManager = pdShipStrategyManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        String view = "pd/pdShipStrategyMain";
        String strAction = request.getParameter("strAction");
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
		if("pdShipStrategyList".equals(strAction)){
			Map<String,String> map = ListUtil.getListOptions(sessionLogin.getCompanyCode(), "ship.strategy");
			request.setAttribute("strategyMap", map);
			view  = "pd/pdShipStrategyList";
		}
		
			
		
		return new ModelAndView(view);
    }
}
