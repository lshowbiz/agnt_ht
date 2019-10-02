package com.joymain.jecs.sun.webapp.action;

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
import com.joymain.jecs.sun.model.SunDistShip;
import com.joymain.jecs.sun.service.SunDistShipManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class SunDistShipController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(SunDistShipController.class);
    private SunDistShipManager sunDistShipManager = null;

    public void setSunDistShipManager(SunDistShipManager sunDistShipManager) {
        this.sunDistShipManager = sunDistShipManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        super.initPmProductMap(request);
        super.initStateCodeParem(request);
        CommonRecord crm=super.initCommonRecord(request);
        String view = "sun/sunDistShipList";
        String strAction = request.getParameter("strAction");
        String exeFlag = request.getParameter("exeFlag");
        if("createSunShipData".equals(strAction)){
        	view = "sun/createSunShipData";
        	if("1".equals(exeFlag)){
        		sunDistShipManager.createSunShipData(crm);
        		
        	}
        	
        }
//        SunDistShip sunDistShip = new SunDistShip();
        // populate object with request parameters
//        BeanUtils.populate(sunDistShip, request.getParameterMap());

	//List sunDistShips = sunDistShipManager.getSunDistShips(sunDistShip);
	/**** auto pagination ***/
        
	
        Pager pager = new Pager("sunDistShipListTable",request, 20);
        List sunDistShips = sunDistShipManager.getSunDistShipsByCrm(crm,pager);
        request.setAttribute("sunDistShipListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView(view, Constants.SUNDISTSHIP_LIST, sunDistShips);
    }
}
