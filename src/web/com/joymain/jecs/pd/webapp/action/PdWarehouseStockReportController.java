package com.joymain.jecs.pd.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pd.service.PdWarehouseStockManager;
import com.joymain.jecs.pd.service.PdWarehouseUserManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class PdWarehouseStockReportController extends BaseController implements
		Controller {

	private PdWarehouseStockManager pdWarehouseStockManager = null;
	private PdWarehouseUserManager pdWarehouseUserManager=null;
    public void setPdWarehouseUserManager(
			PdWarehouseUserManager pdWarehouseUserManager) {
		this.pdWarehouseUserManager = pdWarehouseUserManager;
	}
	public void setPdWarehouseStockManager(
			PdWarehouseStockManager pdWarehouseStockManager) {
		this.pdWarehouseStockManager = pdWarehouseStockManager;
	}

	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		super.initPmProductMap(request);
		CommonRecord crm = initCommonRecord(request);
		Pager pager = new Pager("pdWarehouseStockListTable",request, 20);
		String strAction = request.getParameter("strAction");
		String groupType = request.getParameter("groupType");
		String view = "pd/pdWarehouseStockReport";
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		if(!"AA".equalsIgnoreCase(sessionLogin.getCompanyCode())){
        	crm.setValue("companyCode", sessionLogin.getCompanyCode());
        }
	      
		//新增仓库管理权限控制  Modify By WuCF 20150708
		if(!"root".equals(sessionLogin.getUserCode())){
			String warehouseNoStr = pdWarehouseUserManager.getStringWarehouseByUser(sessionLogin.getUserCode());
	        crm.setValue("defaultWarehouse", warehouseNoStr);
		}
	    	
		
		List pdWarehouseStocks = pdWarehouseStockManager
				.getPdWarehouseStocksByGroup(crm, groupType);
		request.setAttribute("pdWarehouseStockListTable_totalRows", pager.getTotalObjects());
		
		
		return new ModelAndView(view, Constants.PDWAREHOUSESTOCK_LIST, pdWarehouseStocks);
	}

}
