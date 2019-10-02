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
import com.joymain.jecs.pd.model.PdShipmentsDetail;
import com.joymain.jecs.pd.service.PdShipmentsDetailManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdShipmentsDetailController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdShipmentsDetailController.class);
    private PdShipmentsDetailManager pdShipmentsDetailManager = null;

    public void setPdShipmentsDetailManager(PdShipmentsDetailManager pdShipmentsDetailManager) {
        this.pdShipmentsDetailManager = pdShipmentsDetailManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        
        String view = "pd/pdShipmentsDetailList";
        CommonRecord crm = initCommonRecord(request);//����ѯ���д��session
        Pager pager = new Pager("pdShipmentsDetailListTable",request, 20);
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        
        String strAction = request.getParameter("strAction");
        
        crm.setValue("control", strAction);

//        initAttribute(request);
//        Map map = RequestUtil.populateMap(request);
//        Map productMap = pmProductSaleManager.getCompanyProductMap(sessionLogin.getCompanyCode());
        if(sessionLogin.getIsManager()){
        	
        }else if(sessionLogin.getIsCompany()){
        	crm.setValue("companyCode",sessionLogin.getCompanyCode() );
        }else{
			crm.setValue("userCode", sessionLogin.getUserCode());
		}
//        if("C".equalsIgnoreCase(sessionLogin.getUserType())){
//			if(!"AA".equalsIgnoreCase(sessionLogin.getCompanyCode())){
//				crm.setValue("companyCode",sessionLogin.getCompanyCode() );
//			}
//		}else{
//			crm.setValue("userCode", sessionLogin.getUserCode());
//		}
//		List pdShipmentsDetails = pdShipmentsDetailManager.getPdShipmentsDetails(pdShipmentsDetail);�Լ��ſ�
		
       /* if("totalPdShipmentsDetail".equals(strAction)){
        	List totalPdShipmentsDetails = pdShipmentsDetailManager.getTotalPdShipmentsDetails(crm);
        	request.setAttribute("shipmentsDetailTot", totalPdShipmentsDetails);
        	log.info("totalShipmentsDetails.size="+totalPdShipmentsDetails.size());
        	List readyForDeliveryList = pdShipmentsDetailManager.getReadyForDelivery(crm, pager);
        	request.setAttribute("pdShipmentsDetailListTable_totalRows", pager.getTotalObjects());
        	return new ModelAndView("pd/pdReadyForDeliveryList", "readyForDeliveryList", readyForDeliveryList);
//        	view = "pd/pdReadyForDeliveryList";
        }else if("readyForDelivery".equals(strAction)){
        	List readyForDeliveryList = pdShipmentsDetailManager.getReadyForDelivery(crm, pager);
        	request.setAttribute("pdShipmentsDetailListTable_totalRows", pager.getTotalObjects());
        	
        	return new ModelAndView("pd/pdReadyForDeliveryList", "readyForDeliveryList", readyForDeliveryList);
        	
        }else if("readyForDeliveryNew".equals(strAction)){
        	List totalPdShipmentsDetails = pdShipmentsDetailManager.getTotalPdShipmentsDetails(crm);
        	request.setAttribute("shipmentsDetailTot", totalPdShipmentsDetails);
        	log.info("totalShipmentsDetails.size="+totalPdShipmentsDetails.size());
        	
        	List adReadDeliveryList = pdShipmentsDetailManager.getADForDelivery(crm, pager);
        	request.setAttribute("pdShipmentsDetailListTable_totalRows", pager.getTotalObjects());
        	return new ModelAndView("pd/pdReadyForDeliveryNewList", "readyForDeliveryList", adReadDeliveryList);
        	
        }else if("batchAutoShip".equals(strAction)){
        	List retList = new ArrayList();
        	List list = pdShipmentsDetailManager.getOrderList(crm);
//        	retList=pdSendInfoManager.batchAutoShipByOrderNos(list);
        	
        	for(int i=0;i<list.size();i++){
    			Map mapR = (Map) list.get(i);
    			String orderNo = (String) mapR.get("ORDER_NO");
    			PoMemberOrder poMemberOrder = poMemberOrderManager.getPoMemberOrderByNo(orderNo);
    			
    			
    			try {
    				String ret = pdSendInfoManager.addAutoPdSendInfo(poMemberOrder);
    				retList.add("success->ordrNo:"+poMemberOrder.getMemberOrderNo()+";siNo:"+ret);
    			} catch (Exception e) {
    				// TODO: handle exception
    				log.warn("batchship"+e.getMessage());
    				log.info("batchship"+e.getMessage());
    				retList.add("fail->ordrNo:"+poMemberOrder.getMemberOrderNo());
//    				continue;
    			}
    			
    		}
        	
        	request.setAttribute("errors", retList);
        }*/
        List pdShipmentsDetails = pdShipmentsDetailManager.getPdShipmentsDetailsByCrm(crm,pager);
        request.setAttribute("pdShipmentsDetailListTable_totalRows", pager.getTotalObjects());
//        request.setAttribute("pdShipmentsDetailExample", map);
        //request.setAttribute("strAction", strAction);
//        request.setAttribute("productMap", productMap);
        
        super.initPmProductMap(request);
        return new ModelAndView(view, Constants.PDSHIPMENTSDETAIL_LIST, pdShipmentsDetails);
        
        
        
        
        
        
//        PdShipmentsDetail pdShipmentsDetail = new PdShipmentsDetail();
//        // populate object with request parameters
//        BeanUtils.populate(pdShipmentsDetail, request.getParameterMap());
//
//	//List pdShipmentsDetails = pdShipmentsDetailManager.getPdShipmentsDetails(pdShipmentsDetail);
//	/**** auto pagination ***/
//	CommonRecord crm=RequestUtil.toCommonRecord(request);
//        Pager pager = new Pager("pdShipmentsDetailListTable",request, 20);
//        List pdShipmentsDetails = pdShipmentsDetailManager.getPdShipmentsDetailsByCrm(crm,pager);
//        request.setAttribute("pdShipmentsDetailListTable_totalRows", pager.getTotalObjects());
//        /*****/

        
    }
}
