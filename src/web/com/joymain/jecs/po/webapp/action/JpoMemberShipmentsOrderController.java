package com.joymain.jecs.po.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

//允许用户把订单改成暂不发货
public class JpoMemberShipmentsOrderController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoMemberFOrderController.class);
    private JpoMemberOrderManager jpoMemberOrderManager = null;

    public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
        this.jpoMemberOrderManager = jpoMemberOrderManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
  /*      if("C".equals(loginSysUser.getUserType())){
            RequestUtil.freshSession(request,"poMemberFOrders",3l);
    	}else if("M".equals(loginSysUser.getUserType())){
            RequestUtil.freshSession(request,"poMemberFOrders",3l);
    	}*/
        String orderIdStr = request.getParameter("orderId");
    	String submitType = request.getParameter("submitType");
		if (!StringUtils.isEmpty(orderIdStr)) {
			if("C".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"poMemberShipmentsOrders",10l)!=0){
        			return new ModelAndView("redirect:/jpoMemberShipmentsOrders.html");
        		}
        	}
			List successList = new ArrayList();
			List errorList = new ArrayList();
			Exception exception = null;
			AppException appException = null;
			String[] orderId = orderIdStr.split(",");
		
			if ("2".equals(submitType)) {
				if (orderId != null && orderId.length > 0) {
					for (int i = 0; i < orderId.length; i++) {
						JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager
								.getJpoMemberOrder(orderId[i]);
						if ("2".equals(submitType)) {//修改会员发货状态允许批量修改
							try {
								jpoMemberOrderManager.editJpoMemberOrderShipments(jpoMemberOrder, operatorSysUser);
							
								
								successList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo")+ ":"+ jpoMemberOrder.getMemberOrderNo()+ LocaleUtil.getLocalText("poOrder.editeShipmentsdSuc"));
							} catch (AppException app) {
								app.printStackTrace();
								appException = app;
								errorList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo")+ ":"+ jpoMemberOrder.getMemberOrderNo()+ LocaleUtil.getLocalText("poOrder.editedShipmentsFail")+ "," + app.getMessage());
								
							} catch (Exception e) {
								e.printStackTrace();
								exception = e;
								errorList.add(LocaleUtil.getLocalText("checkError.Code.111")+ ":"+ jpoMemberOrder.getMemberOrderNo()+ LocaleUtil.getLocalText("poOrder.editedShipmentsFail"));
							}
							
					
						}
					}
				}
			}
			request.setAttribute("successList", successList);
			request.setAttribute("errorList", errorList);
			request.setAttribute("exception", exception);
			request.setAttribute("appException", appException);
		}
		
		
		
		
        CommonRecord crm = RequestUtil.toCommonRecord(request);
    	crm.addField("companyCode", loginSysUser.getCompanyCode());
    	//crm.addField("orderType", "1");
    	crm.addField("isSpecial", "0");
    	crm.addField("status","1");//待审
    	crm.addField("isPay","0");//未支付
    	
    	if ("2".equals(submitType)) {
    	      crm.addField("isShipments","2");//已经为暂不发货标识
    	}
    	else
    	{
        	crm.addField("isShipments",request.getParameter("isShipments"));//已经为暂不发货标识
    	}
    	
    	
    	
        
        if(loginSysUser.getIsMember()){
        	crm.addField("sysUser.userCode", loginSysUser.getUserCode());
        }
        
        Pager pager = new Pager("jpoMemberOrderListTable",request, 20);
        List jpoMemberOrders = null;
        if(!StringUtils.isEmpty(crm.getString("search","")) || !StringUtils.isEmpty(crm.getString("sysUser.userCode",""))){
       /* 	if("C".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"poMemberFOrders",3l)!=0){
        			return new ModelAndView("redirect:jpoMemberShipmentsOrders.html");
        		}
        	}else if("M".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"poMemberFOrders",3l)!=0){
        			return new ModelAndView("redirect:jpoMemberShipmentsOrders.html");
        		}
        	}*/
            jpoMemberOrders = jpoMemberOrderManager.getJpoMemberOrdersByCrm(crm,pager);
        }
        request.setAttribute("jpoMemberOrderListTable_totalRows", pager.getTotalObjects());
	
        return new ModelAndView("po/jpoMemberShipmentsOrderList", Constants.JPOMEMBERORDER_LIST, jpoMemberOrders);
    }
}
