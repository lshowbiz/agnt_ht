package com.joymain.jecs.pd.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pd.model.PdSendInfo;
import com.joymain.jecs.pd.service.PdSendInfoDetailManager;
import com.joymain.jecs.pd.service.PdSendInfoManager;
import com.joymain.jecs.pd.service.PdWarehouseStockManager;
import com.joymain.jecs.pd.service.PdWarehouseUserManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class PdBatchSendInfoController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(PdBatchSendInfoController.class);
	private PdSendInfoManager pdSendInfoManager = null;
	private PdWarehouseUserManager pdWarehouseUserManager = null;
	private PdSendInfoDetailManager pdSendInfoDetailManager =null;
	private PdWarehouseStockManager pdWarehouseStockManager = null;
	private JpoMemberOrderManager jpoMemberOrderManager = null;
	
	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	public void setPdSendInfoDetailManager(
			PdSendInfoDetailManager pdSendInfoDetailManager) {
		this.pdSendInfoDetailManager = pdSendInfoDetailManager;
	}

	public void setPdWarehouseStockManager(
			PdWarehouseStockManager pdWarehouseStockManager) {
		this.pdWarehouseStockManager = pdWarehouseStockManager;
	}

	public void setPdWarehouseUserManager(
			PdWarehouseUserManager pdWarehouseUserManager) {
		this.pdWarehouseUserManager = pdWarehouseUserManager;
	}

	public void setPdSendInfoManager(PdSendInfoManager pdSendInfoManager) {
		this.pdSendInfoManager = pdSendInfoManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
	
		super.initStateCodeParem(request);
		super.initPmProductMap(request);
		String mvc = "welcome";
		String strAction = request.getParameter("strAction");
		CommonRecord crm = initCommonRecord(request);
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		String listFlag = "-1";
		Pager pager = new Pager("pdSendInfoListTable", request, 20);

		//Modify By WuCF 20140603 对于root账号不用限制仓库权限
		String warehouseNoStr = "";
		if(!"root".equals(sessionLogin.getUserCode())){
			warehouseNoStr = pdWarehouseUserManager.getStringWarehouseByUser(sessionLogin.getUserCode());
		}

		//新增仓库管理权限控制
    	crm.setValue("defaultWarehouse", warehouseNoStr);
		
		// 当不是公司用户 时 只能看到自己的单,管理中心可以看到所有公司的单
		if (sessionLogin.getIsCompany()) {
			crm.setValue("companyCode", sessionLogin.getCompanyCode());
		} else if (sessionLogin.getIsManager()) {

		} else {
			crm.setValue("customCode", sessionLogin.getUserCode());
		}

		
		
		if("editPdSendInfo".equals(strAction)){//编辑发货单
        	listFlag = "1";

        	mvc="pd/pdSendInfoList";
         } else if("checkPdSendInfo".equals(strAction)){//初审
	        	listFlag = "0";
//	        	crm.setValue("checkUsrCode", sessionLogin.getUserCode());
	        	crm.setValue("hasCheckUsrCodeBlank", "1");
	        	crm.setValue("orderFlagDefault","0,1,2,3,4");

	        	mvc="pd/pdSendInfoList";

	        }else if("sendPdSendInfo".equals(strAction)){//发货确认
	        	//新增仓库管理权限控制

	        	listFlag = "0";
	        	crm.setValue("orderFlagDefault","1,2,3,4"); 
	        	
	        	crm.setValue("okUsrCode", sessionLogin.getUserCode());
	        	crm.setValue("hasCheckUsrCodeBlank", "1");

	        	mvc="pd/pdSendInfoList";
	        }else if("acceptPdSendInfo".equals(strAction)){//代理商收货确认
	        	crm.setValue("orderFlagDefault","1,2,3,4");
	        	mvc="pd/pdSendInfoList";
	        }else if("receiptPdSendInfo".equals(strAction)){//回单确认
	        	crm.setValue("orderFlagDefault","2,3,4");
	        	mvc="pd/pdSendInfoList";
	        }else if("searchPdSendInfo".equals(strAction)){//发货单查询
	        	listFlag = "0";
	        	
	        	mvc="pd/pdSendInfoList";
	        }else if("statisticPdSendInfo".equals(strAction)){
	        	List pdSendInfoTotal = pdSendInfoDetailManager.getTotalPdSendInfoDetails(crm);
	        	request.setAttribute("pdSendInfoTotal", pdSendInfoTotal);
	        	mvc="pd/pdSendInfoList";
	        }else if("batchShipOrder".equals(strAction)){//查询出需要发货的订单
//	        	crm.setValue("isPickup", "0");
//	        	crm.setValue("withoutAgent", "1");
	        	
	        	crm.setValue("orderFlag", "1");
	        	
//	        	crm.setValue("orderFlagDefault","1,2,3,4"); 
	        	
//	        	crm.setValue("okUsrCode", sessionLogin.getUserCode());
//	        	crm.setValue("hasCheckUsrCodeBlank", "1");

	        	
	        	List pdSendInfoList = new ArrayList();
	        	String confirmFlag = request.getParameter("confirmFlag");
	        	if("search".equals(confirmFlag)){
	        		CommonRecord crmA = new CommonRecord();
	        		Pager pagerA =  new Pager("pdBatchSendInfoListTable", request, 20);
	        		pdSendInfoList = jpoMemberOrderManager.getShipOrder(crmA, pagerA);//通过小罗提供的方法查出符合发货条件的订单
	        	}
	        	 
//	        	String siNoList = this.getSiNoByAll(pdSendInfoList);
	        	String siNoList = "";
	        	request.setAttribute("pdBatchSendInfoList", pdSendInfoList);
	        	
	        	if("batchConfirm".equals(confirmFlag)){//点击页面上的“生成发货单”
	        		siNoList=request.getParameter("siNoList");
	        		if(siNoList != null){
		        		JpoMemberOrder order = new JpoMemberOrder();
	        			String[] siNos = siNoList.split(",");
	        			for(int i=0;i<siNos.length;i++){
	        				order = jpoMemberOrderManager.getJpoMemberOrder(siNos[i]);
	        				//1.修改jpoMemberOrder的状态
	        				order.setIsShipping("1");
	        				
	        				
	        				//修改jpoMemberOrder的状态后，将其保存
	        				//jpoMemberOrderManager.saveJpoMemberOrder(order);
	        				
	        				//2.调用小吴的发货方法发货
	        				pdSendInfoManager.doWhileOrderConfirmed(order);
	        			}
	         		}
	        		
	        		/*log.info("batchConfirm");
	        		siNoList=request.getParameter("siNoList");
	        		batchConfirmShip(siNoList,request,response);
	        		siNoList="";*/
	        		
	        	}
	        	request.setAttribute("siNoList", siNoList);
	        	mvc="pd/pdBatchSendInfoList";
	        	return new ModelAndView(mvc);
	        }else if("reupdatePdWarehouseStock".equals(strAction)){
	        	reupdatePdWarehouseStock(request,response);
	        }
		
		
		// PdSendInfo pdSendInfo = new PdSendInfo();
		// // populate object with request parameters
		// BeanUtils.populate(pdSendInfo, request.getParameterMap());

		/**** auto pagination ***/

		List pdSendInfos = pdSendInfoManager.getPdSendInfosByCrm(crm, pager);
		request.setAttribute("pdSendInfoListTable_totalRows", pager
				.getTotalObjects());
		/*****/
		
		
		return new ModelAndView(mvc, Constants.PDSENDINFO_LIST,
				pdSendInfos);
	}
	
	
	private void reupdatePdWarehouseStock(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String siNos = request.getParameter("siNos");
		String[] siNo = siNos.split(",");
		for(int i=0;i<siNo.length;i++){
			PdSendInfo pdSendInfo = pdSendInfoManager.getPdSendInfo(siNo[i]);
			pdWarehouseStockManager.reupdatePdWarehouseStock(pdSendInfo);
		}
		
	}

	private void batchConfirmShip(String siNoList, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
    	SysUser sessionLogin = SessionLogin.getLoginUser(request);
    	List retList = new ArrayList();
		if(siNoList != null){
			String[] siNos = siNoList.split(",");
			for(int i=0;i<siNos.length;i++){
				PdSendInfo pdSendInfo = pdSendInfoManager.getPdSendInfo(siNos[i]);
				try {
					
					pdSendInfo.setOkTime(new Date());
					pdSendInfo.setOkUsrCode(sessionLogin.getUserCode());
					pdSendInfo.setOrderFlag(2);
					pdSendInfoManager.confirmSendInfo(pdSendInfo, sessionLogin);
					retList.add("<font color='green'>success:" + pdSendInfo.getSiNo()+","+pdSendInfo.getCustomer().getUserCode()+","+pdSendInfo.getOrderNo()+"</font>");
				} catch (Exception e) {
					// TODO: handle exception
					retList.add("<font color='red'>error:" + pdSendInfo.getSiNo()+","+pdSendInfo.getCustomer().getUserCode()+","+pdSendInfo.getOrderNo()+"</font>");
				}
			}
 		}
		request.setAttribute("errors", retList);
	}

	private String  getSiNoByAll(List pdSendInfoList){
    	StringBuffer sb = new StringBuffer();
    	if(pdSendInfoList != null){
    		for(int i=0;i<pdSendInfoList.size();i++){
        		if(i==0){
        			sb.append(((PdSendInfo)pdSendInfoList.get(i)).getSiNo());
        		}else{
        			sb.append(","+((PdSendInfo)pdSendInfoList.get(i)).getSiNo());
        		}
        		
        	}
    	}
    	
    	return sb.toString();
    }
}
