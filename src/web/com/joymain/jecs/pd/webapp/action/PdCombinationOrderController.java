package com.joymain.jecs.pd.webapp.action;

import java.util.List;
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
import com.joymain.jecs.pd.model.PdCombinationOrder;
import com.joymain.jecs.pd.service.PdCombinationDetailManager;
import com.joymain.jecs.pd.service.PdCombinationOrderManager;
import com.joymain.jecs.pd.service.PdWarehouseUserManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdCombinationOrderController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdCombinationOrderController.class);
    private PdCombinationOrderManager pdCombinationOrderManager = null;
    private PdCombinationDetailManager pdCombinationDetailManager = null;
    private PdWarehouseUserManager pdWarehouseUserManager = null;
    public void setPdWarehouseUserManager(
			PdWarehouseUserManager pdWarehouseUserManager) {
		this.pdWarehouseUserManager = pdWarehouseUserManager;
	}

	public void setPdCombinationDetailManager(
			PdCombinationDetailManager pdCombinationDetailManager) {
		this.pdCombinationDetailManager = pdCombinationDetailManager;
	}

	public void setPdCombinationOrderManager(PdCombinationOrderManager pdCombinationOrderManager) {
        this.pdCombinationOrderManager = pdCombinationOrderManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        super.initPmProductMap(request);
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        CommonRecord crm=this.initCommonRecord(request);
        //Modify By WuCF 20140603 对于root账号不用限制仓库权限
		String warehouseNoStr = "";
		if(!"root".equals(sessionLogin.getUserCode())){
			warehouseNoStr = pdWarehouseUserManager.getStringWarehouseByUser(sessionLogin.getUserCode());
		}
		
		//新增仓库管理权限控制
    	crm.setValue("defaultWarehouse", warehouseNoStr);
        
        PdCombinationOrder pdCombinationOrder = new PdCombinationOrder();
        // populate object with request parameters
//        BeanUtils.populate(pdCombinationOrder, request.getParameterMap());
        String strAction = request.getParameter("strAction");
        
	//List pdCombinationOrders = pdCombinationOrderManager.getPdCombinationOrders(pdCombinationOrder);
	/**** auto pagination ***/
        
        if (!sessionLogin.getIsManager()) {
			crm.setValue("companyCode", sessionLogin.getCompanyCode());
		}
        if("checkPdCombinationOrder".equals(strAction)){
        	crm.setValue("hasCheckUsrCodeBlank", "1");
//			crm.setValue("checkUsrCode", sessionLogin.getUserCode());
        	crm.setValue("orderFlagDefault", "0,1,2");
        }else if("confirmPdCombinationOrder".equals(strAction)){
        	
			

			crm.setValue("hasCheckUsrCodeBlank", "1");
			crm.setValue("okUsrCode", sessionLogin.getUserCode());
        	crm.setValue("orderFlagDefault", "1,2");
        }else if("statisticPdCombinationOrder".equals(strAction)){
        	List pdCombinationDetailTotal = pdCombinationDetailManager.getPdCombinationDetailTotals(crm);
        	request.setAttribute("pdCombinationDetailTotal", pdCombinationDetailTotal);
        	
        }
        Pager pager = new Pager("pdCombinationOrderListTable",request, 20);
        List pdCombinationOrders = pdCombinationOrderManager.getPdCombinationOrdersByCrm(crm,pager);
        request.setAttribute("pdCombinationOrderListTable_totalRows", pager.getTotalObjects());
        
        String pcNo = crm.getString("pcNo","");
        request.setAttribute("owNo", pcNo);
        String warehouseNo = crm.getString("warehouseNo","");
        request.setAttribute("warehouseNo", warehouseNo);
        String createTimeStart = crm.getString("createTimeStart","");
        request.setAttribute("createTimeStart", createTimeStart);
        String createTimeEnd = crm.getString("createTimeEnd","");
        request.setAttribute("createTimeEnd", createTimeEnd);
        String okTimeStart = crm.getString("okTimeStart","");
        request.setAttribute("okTimeStart", okTimeStart);
        String okTimeEnd = crm.getString("okTimeEnd","");
        request.setAttribute("okTimeEnd", okTimeEnd);
        
        /*****/

        return new ModelAndView("pd/pdCombinationOrderList", Constants.PDCOMBINATIONORDER_LIST, pdCombinationOrders);
    }
}
