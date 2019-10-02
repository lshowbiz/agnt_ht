package com.joymain.jecs.pd.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pd.model.PdWarehouseStock;
import com.joymain.jecs.pd.service.PdWarehouseStockManager;
import com.joymain.jecs.pd.service.PdWarehouseUserManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class PdWarehouseStockController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdWarehouseStockController.class);
    private PdWarehouseStockManager pdWarehouseStockManager = null;
    private PdWarehouseUserManager pdWarehouseUserManager=null;
    public void setPdWarehouseUserManager(
			PdWarehouseUserManager pdWarehouseUserManager) {
		this.pdWarehouseUserManager = pdWarehouseUserManager;
	}

	public void setPdWarehouseStockManager(PdWarehouseStockManager pdWarehouseStockManager) {
        this.pdWarehouseStockManager = pdWarehouseStockManager;
    }
 
    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
    	String strAction = request.getParameter("strAction");
    	//展示警戒库存
    	if("showStorageCordon".equals(strAction)){
    		System.out.println("警戒库存！");
    		CommonRecord crm = initCommonRecord(request);
 	        SysUser sessionLogin = SessionLogin.getLoginUser(request);
 	        String loginCompanyCode = sessionLogin.getCompanyCode();
 	        
 	        PdWarehouseStock pdWarehouseStock = new PdWarehouseStock();
 	        BeanUtils.populate(pdWarehouseStock, request.getParameterMap());
 			 
 	        
 	        Pager pager = new Pager("pdWarehouseStockListTable",request, 20);
 	        if(!"AA".equalsIgnoreCase(loginCompanyCode)){
 	        	crm.setValue("companyCode", loginCompanyCode);
 	        }
 	        String warehouseNoStr = pdWarehouseUserManager
 			.getStringWarehouseByUser(sessionLogin.getUserCode());
 	        
 	    	//新增仓库管理权限控制 	        
 	        crm.setValue("defaultWarehouse", warehouseNoStr);
 	        List pdWarehouseStocks = pdWarehouseStockManager.getPdWarehouseStocksByCrm(crm,pager);
 			request.setAttribute("pdWarehouseStockListTable_totalRows", pager.getTotalObjects());
 	       
 	        return new ModelAndView("pd/pdWarehouseStockList", Constants.PDWAREHOUSESTOCK_LIST, pdWarehouseStocks);
// 	        CommonRecord crm = initCommonRecord(request);
// 	        SysUser sessionLogin = SessionLogin.getLoginUser(request);
// 	        String loginCompanyCode = sessionLogin.getCompanyCode();
// 	        
// 	        PdWarehouseStock pdWarehouseStock = new PdWarehouseStock();
// 	        BeanUtils.populate(pdWarehouseStock, request.getParameterMap());
// 			 
// 	        
// 	        Pager pager = new Pager("pdWarehouseStockListTable",request, 20);
// 	        if(!"AA".equalsIgnoreCase(loginCompanyCode)){
// 	        	crm.setValue("companyCode", loginCompanyCode);
// 	        }
// 	        String warehouseNoStr = pdWarehouseUserManager
// 			.getStringWarehouseByUser(sessionLogin.getUserCode());
// 	        crm.setValue("defaultWarehouse", warehouseNoStr);
// 	        List pdWarehouseStocks = pdWarehouseStockManager.getPdWarehouseStocksByCrm(crm,pager);
// 			request.setAttribute("pdWarehouseStockListTable_totalRows", pager.getTotalObjects());
// 	       
// 	        return new ModelAndView("pd/pdWarehouseStockList", Constants.PDWAREHOUSESTOCK_LIST, pdWarehouseStocks);
    	}else{
	        if (log.isDebugEnabled()) {
	            log.debug("entering 'handleRequest' method...");
	        }
	        CommonRecord crm = initCommonRecord(request);
	        //点击第一次菜单，默认不查询，如果是N：不查询
			String cxFlag = request.getParameter("cxFlag");
			crm.setValue("cxFlag", cxFlag);
			if(!"n".equals(cxFlag)){
				super.initPmProductMap(request);
			}
	//        initAttributes(request);
	        SysUser sessionLogin = SessionLogin.getLoginUser(request);
	        String loginCompanyCode = sessionLogin.getCompanyCode();
	        
	        PdWarehouseStock pdWarehouseStock = new PdWarehouseStock();
	        // populate object with request parameters
	        BeanUtils.populate(pdWarehouseStock, request.getParameterMap());
	        
	
	//				List pdWarehouseStocks = pdWarehouseStockManager.getPdWarehouseStocks(pdWarehouseStock);	�Լ��ſ�
			 
	        
	        Pager pager = new Pager("pdWarehouseStockListTable",request, 20);
	        if(!"AA".equalsIgnoreCase(loginCompanyCode)){
	        	crm.setValue("companyCode", loginCompanyCode);
	        }
	        
	      //Modify By WuCF 20140603 对于root账号不用限制仓库权限
			String warehouseNoStr = "";
			if(!"root".equals(sessionLogin.getUserCode())){
				warehouseNoStr = pdWarehouseUserManager.getStringWarehouseByUser(sessionLogin.getUserCode());
			}
			
			//新增仓库管理权限控制
	    	crm.setValue("defaultWarehouse", warehouseNoStr);
	        
	        List pdWarehouseStocks = pdWarehouseStockManager.getPdWarehouseStocksByCrm(crm,pager);
	//        request.setAttribute("pdWarehouseStockExample", pdWarehouseStock);
			request.setAttribute("pdWarehouseStockListTable_totalRows", pager.getTotalObjects());
	       
	        
	        
	        
	       
	      
	
		//List pdWarehouseStocks = pdWarehouseStockManager.getPdWarehouseStocks(pdWarehouseStock);
	        /**** auto pagination ***/
	      /*  CommonRecord crm=RequestUtil.toCommonRecord(request);
	        Pager pager = new Pager("pdWarehouseStockListTable",request, 20);
	        List pdWarehouseStocks = pdWarehouseStockManager.getPdWarehouseStocksByCrm(crm,pager);
	        request.setAttribute("pdWarehouseStockListTable_totalRows", pager.getTotalObjects());*/
	        /*****/
	
	        return new ModelAndView("pd/pdWarehouseStockList", Constants.PDWAREHOUSESTOCK_LIST, pdWarehouseStocks);
    	}
    }
}
