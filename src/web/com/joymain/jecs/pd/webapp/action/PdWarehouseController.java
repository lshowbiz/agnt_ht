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
import com.joymain.jecs.pd.model.PdWarehouse;
import com.joymain.jecs.pd.service.PdWarehouseManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdWarehouseController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdWarehouseController.class);
    private PdWarehouseManager pdWarehouseManager = null;

    public void setPdWarehouseManager(PdWarehouseManager pdWarehouseManager) {
        this.pdWarehouseManager = pdWarehouseManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        super.initStateCodeParem(request);
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        String strAction = request.getParameter("strAction");
        String selectControl = request.getParameter("selectControl");
        String elementId = request.getParameter("elementId");
//        String  = request.getParameter("strAction");
        String view = "pd/pdWarehouseList";
        CommonRecord crm = new CommonRecord();
        String levelFlag = request.getParameter("levelFlag");
        String selfWarehouseFlag = request.getParameter("selfWarehouseFlag");
        
        
        
        if("pdWarehouseTree".equals(strAction)){//展示仓库的树结构
        	crm = initCommonRecord(request);
//        	if (!sessionLogin.getIsManager()) {
    			crm.setValue("companyCode", sessionLogin.getCompanyCode());
//    		}
        	view = "pd/pdWarehouseTree";
			
			List warehouseList = pdWarehouseManager.getPdWarehousesByCrm(crm, null);
			request.setAttribute("warehouseList", warehouseList);
			request.setAttribute("companyCode", sessionLogin.getCompanyCode());
			return new ModelAndView(view); 
        }else if("pdWarehouseTree2".equals(strAction)){//EC需求优化5、仓库权限新增以员工账号为单位勾选仓库信息
        	crm = initCommonRecord(request);
			crm.setValue("companyCode", sessionLogin.getCompanyCode());
        	view = "pd/pdWarehouseTree2";
			
			List warehouseList = pdWarehouseManager.getPdWarehousesByCrm(crm);
			request.setAttribute("warehouseList", warehouseList);
			request.setAttribute("companyCode", sessionLogin.getCompanyCode());
			request.setAttribute("userCode", crm.getString("userCode"));
			return new ModelAndView(view); 
        }else if("selectWarehouse".equals(strAction)){
        	crm=RequestUtil.toCommonRecord(request);
        	if("01".equals(levelFlag)){
            	crm.setValue("warehouseLevel","0','1" );
            }
        	crm.setValue("companyCode",sessionLogin.getCompanyCode() );
        	//Modify By WuCF 20131114 仓库权限管理修改，查询时过滤 
        	if(!"root".equals(sessionLogin.getUserCode())){
        		crm.setValue("userCode", sessionLogin.getUserCode());
        	}
        	view = "pd/pdSelectWarehouseList";
        	request.setAttribute("elementId", elementId);
        	request.setAttribute("selectControl", selectControl);
        }else if("selectWarehouse2".equals(strAction)){//Modify By WuCF 20130503 多选仓库
        	crm=RequestUtil.toCommonRecord(request);
        	if("01".equals(levelFlag)){
            	crm.setValue("warehouseLevel","0','1" );
            }
        	crm.setValue("companyCode",sessionLogin.getCompanyCode() );
        	//Modify By WuCF 20131114 仓库权限管理修改，查询时过滤 
        	if(!"root".equals(sessionLogin.getUserCode())){
        		crm.setValue("userCode", sessionLogin.getUserCode());
        	}
        	view = "pd/pdSelectWarehouseList2";
        	log.info("===============selectWarehouse2:"+elementId+"-"+selectControl);
        	request.setAttribute("elementId", elementId);
        	request.setAttribute("selectControl", selectControl);
        }else if("selectAllWarehouse".equals(strAction)){
        	crm=RequestUtil.toCommonRecord(request);
        	if("01".equals(levelFlag)){
            	crm.setValue("warehouseLevel","0','1" );
            }
        	
        	//Modify By WuCF 20131114 仓库权限管理修改，查询时过滤 
        	if(!"root".equals(sessionLogin.getUserCode())){
        		crm.setValue("userCode", sessionLogin.getUserCode());
        	}
        	
        	crm.setValue("companyCode",sessionLogin.getCompanyCode());
        	view = "pd/pdSelectWarehouseList";
        	request.setAttribute("elementId", elementId);
        	request.setAttribute("selectControl", selectControl);
        }else{
        	 crm = initCommonRecord(request);
        	 if("01".equals(levelFlag)){
             	crm.setValue("warehouseLevel","0','1" );
             }
        	 if(!"AA".equalsIgnoreCase(sessionLogin.getCompanyCode())){
     			crm.setValue("companyCode",sessionLogin.getCompanyCode() );
     		}
        }
        
        Pager pager = new Pager("pdWarehouseListTable",request, 20);
        
		
       if("1".equals(selfWarehouseFlag)){
    	   crm.setValue("userCode",sessionLogin.getUserCode());
       }
        
//        PdWarehouse pdWarehouse = new PdWarehouse();
//        // populate object with request parameters
//        BeanUtils.populate(pdWarehouse, request.getParameterMap());

//        List pdWarehouses = pdWarehouseManager.getPdWarehouses(pdWarehouse);
        
        List pdWarehouses = pdWarehouseManager.getPdWarehousesByCrm(crm, pager);
        //request.setAttribute("strAction", strAction);
        request.setAttribute("pdWarehouseListTable_totalRows", pager.getTotalObjects());
        
        
        
        
        
       /* PdWarehouse pdWarehouse = new PdWarehouse();
        // populate object with request parameters
        BeanUtils.populate(pdWarehouse, request.getParameterMap());

	//List pdWarehouses = pdWarehouseManager.getPdWarehouses(pdWarehouse);
	 
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("pdWarehouseListTable",request, 20);
        List pdWarehouses = pdWarehouseManager.getPdWarehousesByCrm(crm,pager);
        request.setAttribute("pdWarehouseListTable_totalRows", pager.getTotalObjects());
    */

        return new ModelAndView(view, Constants.PDWAREHOUSE_LIST, pdWarehouses);
    }
}
