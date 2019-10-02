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
import com.joymain.jecs.sun.model.SunProductInfo;
import com.joymain.jecs.sun.service.SunProductInfoManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class SunProductInfoController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(SunProductInfoController.class);
    private SunProductInfoManager sunProductInfoManager = null;

    public void setSunProductInfoManager(SunProductInfoManager sunProductInfoManager) {
        this.sunProductInfoManager = sunProductInfoManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        SunProductInfo sunProductInfo = new SunProductInfo();
        // populate object with request parameters
        BeanUtils.populate(sunProductInfo, request.getParameterMap());

	//List sunProductInfos = sunProductInfoManager.getSunProductInfos(sunProductInfo);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("sunProductInfoListTable",request, 20);
        List sunProductInfos = sunProductInfoManager.getSunProductInfosByCrm(crm,pager);
        request.setAttribute("sunProductInfoListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("sun/sunProductInfoList", Constants.SUNPRODUCTINFO_LIST, sunProductInfos);
    }
}
