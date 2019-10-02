package com.joymain.jecs.po.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.po.service.JpoCounterOrderDetailManager;
import com.joymain.jecs.po.service.JpoCounterOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JpoCounterOrderController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoCounterOrderController.class);
    private JpoCounterOrderManager jpoCounterOrderManager = null;
    private JpoCounterOrderDetailManager jpoCounterOrderDetailManager;
    public void setJpoCounterOrderDetailManager(
			JpoCounterOrderDetailManager jpoCounterOrderDetailManager) {
		this.jpoCounterOrderDetailManager = jpoCounterOrderDetailManager;
	}

	public void setJpoCounterOrderManager(JpoCounterOrderManager jpoCounterOrderManager) {
        this.jpoCounterOrderManager = jpoCounterOrderManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        SysUser sessionLogin = SessionLogin.getLoginUser(request);

        String strAction = request.getParameter("strAction");
        CommonRecord crm = initCommonRecord(request);

        Map requestMap = RequestUtil.populateMap(request);
        
        String listFlag = "-1";
        if("C".equalsIgnoreCase(sessionLogin.getUserType())){
			if(!"AA".equalsIgnoreCase(sessionLogin.getCompanyCode())){
				crm.setValue("companyCode",sessionLogin.getCompanyCode() );
			}
		}else {
			crm.setValue("userCode", sessionLogin.getUserCode());
		}
//        if("discountOrder".equals(strAction)){//打折销售
//        	listFlag="1";
//        	crm.setValue("discountFlag", "1");
//        }else 
       if("editPoCounterOrder".equals(strAction)){//新增，删除，编辑
        	listFlag="1";
        }else if("paymentPoCounterOrder".equals(strAction)){//付款
        	crm.setValue("csStatus", "0");
        }else if("repealPoCounterOrder".equals(strAction)){//作废
        	crm.setValue("csStatus", "2,3");
        }else if("statisticPoCounterOrder".equals(strAction)){//统计

        	List poCounterOrderTotal =jpoCounterOrderDetailManager.getTotolPoCounterOrderDetails(crm);
        	request.setAttribute("poCounterOrderTotal", poCounterOrderTotal);
        }else if("shipPoCounterOrder".equals(strAction)){
        	crm.setValue("csStatus", "1,2");
        }

        Pager pager = new Pager("jpoCounterOrderListTable",request, 20);
        List jpoCounterOrders = jpoCounterOrderManager.getJpoCounterOrdersByCrm(crm,pager);
    	List poCounterOrderSumTotal = jpoCounterOrderManager.getJpoCounterOrdersByCrmSum(crm);
    	request.setAttribute("poCounterOrderSumTotal", poCounterOrderSumTotal);
        request.setAttribute("jpoCounterOrderListTable_totalRows", pager.getTotalObjects());

        request.setAttribute("listFlag", listFlag);
        request.setAttribute("poCounterOrderExample", requestMap);
       
        return new ModelAndView("po/jpoCounterOrderList", Constants.JPOCOUNTERORDER_LIST, jpoCounterOrders);
    }
}
