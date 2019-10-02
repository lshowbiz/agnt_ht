package com.joymain.jecs.pd.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pd.model.PdWarehouseStockTrace;
import com.joymain.jecs.pd.service.PdWarehouseStockTraceManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdWarehouseStockTraceController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdWarehouseStockTraceController.class);
    private PdWarehouseStockTraceManager pdWarehouseStockTraceManager = null;

    public void setPdWarehouseStockTraceManager(PdWarehouseStockTraceManager pdWarehouseStockTraceManager) {
        this.pdWarehouseStockTraceManager = pdWarehouseStockTraceManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        super.initPmProductMap(request);
        
        PdWarehouseStockTrace pdWarehouseStockTrace = new PdWarehouseStockTrace();
        // populate object with request parameters
        BeanUtils.populate(pdWarehouseStockTrace, request.getParameterMap());

//				List pdWarehouseStockTraces = pdWarehouseStockTraceManager.getPdWarehouseStockTraces(pdWarehouseStockTrace);
//			�Լ��ſ�
		CommonRecord crm = initCommonRecord(request);//����ѯ���д��session
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		String searchFlag = request.getParameter("searchFlag");
		 if(!sessionLogin.getIsManager()){
	        	crm.setValue("companyCode", sessionLogin.getCompanyCode());
	        }
		 List pdWarehouseStockTraces = new ArrayList();
        Pager pager = new Pager("pdWarehouseStockTraceListTable",request, 20);
        
        Map requestMap = RequestUtil.populateMap(request);
        if("searchTrace".equals(searchFlag)){
        	pdWarehouseStockTraces = pdWarehouseStockTraceManager.getPdWarehouseStockTracesByCrm(crm,pager);
        	request.setAttribute("pdWarehouseStockTraceListTable_totalRows", pager.getTotalObjects());
        }else{
        	request.setAttribute("pdWarehouseStockTraceListTable_totalRows", 0);
        }
        
        
//        request.setAttribute("pdWarehouseStockTraceExample", requestMap);
        
        
        
//        PdWarehouseStockTrace pdWarehouseStockTrace = new PdWarehouseStockTrace();
        

	//List pdWarehouseStockTraces = pdWarehouseStockTraceManager.getPdWarehouseStockTraces(pdWarehouseStockTrace);
	/**** auto pagination ***/
/*	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("pdWarehouseStockTraceListTable",request, 20);
        List pdWarehouseStockTraces = pdWarehouseStockTraceManager.getPdWarehouseStockTracesByCrm(crm,pager);
        request.setAttribute("pdWarehouseStockTraceListTable_totalRows", pager.getTotalObjects());*/
        /*****/

        return new ModelAndView("pd/pdWarehouseStockTraceList", Constants.PDWAREHOUSESTOCKTRACE_LIST, pdWarehouseStockTraces);
    }
}
