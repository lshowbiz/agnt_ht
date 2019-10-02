package com.joymain.jecs.pd.webapp.action;

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
import com.joymain.jecs.pd.model.PdLogisticsBase;
import com.joymain.jecs.pd.service.PdLogisticsBaseManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdLogisticsBaseController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdLogisticsBaseController.class);
    private PdLogisticsBaseManager pdLogisticsBaseManager = null;

    public void setPdLogisticsBaseManager(PdLogisticsBaseManager pdLogisticsBaseManager) {
        this.pdLogisticsBaseManager = pdLogisticsBaseManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        String strAction = request.getParameter("strAction");
        //modify by fu 2016-11-03 发货单编辑页面输入发货单号弹出页面 ----begin
        if("pdsiNoTextarea".equals(strAction)){
        	return new ModelAndView("pd/pdsiNoTextarea");
        }
        //modify by fu 2016-11-03 发货单编辑页面输入发货单号弹出页面 ----end
        
        PdLogisticsBase pdLogisticsBase = new PdLogisticsBase();
        // populate object with request parameters
        BeanUtils.populate(pdLogisticsBase, request.getParameterMap());

	//List pdLogisticsBases = pdLogisticsBaseManager.getPdLogisticsBases(pdLogisticsBase);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("pdLogisticsBaseListTable",request, 20);
        List pdLogisticsBases = pdLogisticsBaseManager.getPdLogisticsBasesByCrm(crm,pager);
        request.setAttribute("pdLogisticsBaseListTable_totalRows", pager.getTotalObjects());
        /*****/
        //"Constants.PDLOGISTICSBASE_LIST"
        return new ModelAndView("pd/pdLogisticsBaseList", "pdLogisticsBaseList", pdLogisticsBases);
    }
}
