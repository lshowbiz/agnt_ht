package com.joymain.jecs.pd.webapp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pd.model.PdEnterWarehouse;
import com.joymain.jecs.pd.service.PdEnterWarehouseDetailManager;
import com.joymain.jecs.pd.service.PdEnterWarehouseManager;
import com.joymain.jecs.pd.service.PdWarehouseUserManager;
import com.joymain.jecs.pm.service.JpmProductSaleManager;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.sys.model.SysUser;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdEnterWarehouseController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdEnterWarehouseController.class);
    private PdEnterWarehouseManager pdEnterWarehouseManager = null;
    private PdEnterWarehouseDetailManager pdEnterWarehouseDetailManager = null;
//    private JpmProductSaleManager jpmProductSaleManager = null;
    private PdWarehouseUserManager pdWarehouseUserManager=null;
    public void setPdWarehouseUserManager(
			PdWarehouseUserManager pdWarehouseUserManager) {
		this.pdWarehouseUserManager = pdWarehouseUserManager;
	}

	public void setPdEnterWarehouseManager(PdEnterWarehouseManager pdEnterWarehouseManager) {
        this.pdEnterWarehouseManager = pdEnterWarehouseManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
//        initAttributes(request);
        super.initPmProductMap(request);
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        String view = "pd/pdEnterWarehouseList";
        String strAction = request.getParameter("strAction");
        String ewNo = (String) request.getAttribute("ewNo");
        request.setAttribute("ewNo", ewNo);
//        PdEnterWarehouse pdEnterWarehouse = new PdEnterWarehouse();
//        // populate object with request parameters
//        BeanUtils.populate(pdEnterWarehouse, request.getParameterMap());
        
        Map map = RequestUtil.populateMap(request);
        String listFlag = "-1";
        CommonRecord crm = initCommonRecord(request);//����ѯ���д��session
        Pager pager = new Pager("pdEnterWarehouseListTable",request, 20);

        //Modify By WuCF 20131114 仓库权限管理修改，查询时过滤
  		crm.setValue("userCodeT", sessionLogin.getUserCode());
        
  		//Modify By WuCF 20140603 对于root账号不用限制仓库权限
		String warehouseNoStr = "";
		if(!"root".equals(sessionLogin.getUserCode())){
			warehouseNoStr = pdWarehouseUserManager.getStringWarehouseByUser(sessionLogin.getUserCode());
		}
		
		//新增仓库管理权限控制
    	crm.setValue("defaultWarehouse", warehouseNoStr);
		
        if(!"AA".equalsIgnoreCase(sessionLogin.getCompanyCode())){
        	crm.setValue("companyCode", sessionLogin.getCompanyCode());
        }
        
        if("checkPdEnterWarehouse".equals(strAction)){
//        	crm.setValue("checkUsrCode", sessionLogin.getUserCode());
        	crm.setValue("hasCheckUsrCodeBlank", "1");
        	crm.setValue("orderFlagDefault", "0,1,2");
//        	crm.setValue("checkStatus", "1");
        	listFlag = "0";

        }else if("confirmPdEnterWarehouse".equals(strAction)){
//        	crm.setValue("checkStatus", "2");
        	crm.setValue("orderFlagDefault", "1,2");
        	crm.setValue("hasCheckUsrCodeBlank", "1");
        	crm.setValue("okUsrCode", sessionLogin.getUserCode());
        	listFlag = "0";
        }else if("editPdEnterWarehouse".equals(strAction)){//只能编辑自己的单
//        	crm.setValue("checkStatus", "1");
        	//只能查看自己的单，现去掉
//        	crm.setValue("createUsrCode", sessionLogin.getUserCode());
        	listFlag = "1";
        }else if("statisticPdEnterWarehouse".equals(strAction)){
        	List pdEnterWarehouseTotal = pdEnterWarehouseDetailManager.getTotolPdEnterWarehouseDetails(crm);
        	request.setAttribute("pdEnterWarehouseTotal", pdEnterWarehouseTotal);
        }
        
       
        List pdEnterWarehouses = pdEnterWarehouseManager.getPdEnterWarehousesByCrm(crm, pager);
        request.setAttribute("listFlag", listFlag);
//        List pdEnterWarehouses = pdEnterWarehouseManager.getPdEnterWarehouses(pdEnterWarehouse);
//        //request.setAttribute("strAction", strAction);
//        request.setAttribute("pdEnterWarehouseExample", map);
        request.setAttribute("pdEnterWarehouseListTable_totalRows", pager.getTotalObjects());
        
        return new ModelAndView(view,Constants.PDENTERWAREHOUSE_LIST,pdEnterWarehouses);
    }

	public void setPdEnterWarehouseDetailManager(
			PdEnterWarehouseDetailManager pdEnterWarehouseDetailManager) {
		this.pdEnterWarehouseDetailManager = pdEnterWarehouseDetailManager;
	}

	
	/**
	private void initAttributes(HttpServletRequest request){
		Map pdCheckStatus = new HashMap();
		pdCheckStatus.put("1", "未初审");
		pdCheckStatus.put("2", "已初审");
		pdCheckStatus.put("3", "不初审");
		Map pdOkStatus = new HashMap();
		pdOkStatus.put("1", "未确认");
		pdOkStatus.put("2", "已确认");
		pdOkStatus.put("3", "不确认");
		request.setAttribute("pdCheckStatus",pdCheckStatus);
		request.setAttribute("pdOkStatus",pdOkStatus);
	}**/

	

	
    
}
