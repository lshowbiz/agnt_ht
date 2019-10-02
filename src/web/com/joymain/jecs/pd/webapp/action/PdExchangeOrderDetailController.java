package com.joymain.jecs.pd.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pd.model.PdExchangeOrderDetail;
import com.joymain.jecs.pd.service.PdExchangeOrderDetailManager;
import com.joymain.jecs.pm.service.JmiMemberTeamManager;
import com.joymain.jecs.pm.service.JpmProductSaleManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdExchangeOrderDetailController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdExchangeOrderDetailController.class);
    private PdExchangeOrderDetailManager pdExchangeOrderDetailManager = null;
    private JpmProductSaleManager jpmProductSaleManager = null;
    private JpoMemberOrderManager jpoMemberOrderManager;
    
    //获取会员最新的团队 modify by fu 2016-10-25 
    private JmiMemberTeamManager jmiMemberTeamManager;
    private SysUserManager sysUserManager;
    
    public void setPdExchangeOrderDetailManager(PdExchangeOrderDetailManager pdExchangeOrderDetailManager) {
        this.pdExchangeOrderDetailManager = pdExchangeOrderDetailManager;
    }
    
    public void setJpmProductSaleManager(JpmProductSaleManager jpmProductSaleManager) {
		this.jpmProductSaleManager = jpmProductSaleManager;
	}

	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	public void setJmiMemberTeamManager(JmiMemberTeamManager jmiMemberTeamManager) {
		this.jmiMemberTeamManager = jmiMemberTeamManager;
	}
	
	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        String strAction = request.getParameter("strAction");
        if((!StringUtil.isEmpty(strAction))&&("selectProduct".equals(strAction))){
        	        String view = "pd/pdProductSelect";
        	        String orderNo = request.getParameter("orderNo");
        	        if(!StringUtil.isEmpty(orderNo)){
        	        	JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrderByInterface(orderNo);
        	        	
        	        	Pager pager = new Pager("pdProductSelectTable",request, 20);
        	        	
        				String companyCode = jpoMemberOrder.getCompanyCode();
        				String orderType = jpoMemberOrder.getOrderType();
        				// modify by fu 2016-10-25 需求变更：换货单选择商品时，取会员所属的最新团队，不再取订单下的团队编号----begin
        				SysUser  sysUserJpo = sysUserManager.getSysUser(jpoMemberOrder.getSysUser().getUserCode());
        				String teamCode = jmiMemberTeamManager.teamStr(sysUserJpo);
        				//String teamCode = jpoMemberOrder.getTeamCode();
        				// modify by fu 2016-10-25 需求变更：换货单选择商品时，取会员所属的最新团队，不再取订单下的团队编号----end
        				crm.setValue("companyCode", companyCode);
        				crm.setValue("orderType", orderType);
        				crm.setValue("teamCode", teamCode);
        				
        				//List pdProductSaleList = jpmProductSaleManager.getExProductSales(jpoMemberOrder.getCompanyCode(),jpoMemberOrder.getOrderType(), jpoMemberOrder.getTeamCode());//默认取值中脉
        				List pdProductSaleList = jpmProductSaleManager.getExProductSalesTwo(crm,pager);//默认取值中脉

        				request.setAttribute("strAction", strAction);
        				request.setAttribute("orderNo", orderNo);
        				request.setAttribute("pdProductSelectTable_totalRows", pager.getTotalObjects());
        				return new ModelAndView(view, "pdProductSaleList", pdProductSaleList);
        				
        	        }else{
        	        	return null;
        	        }
        	       
        }else{
			        PdExchangeOrderDetail pdExchangeOrderDetail = new PdExchangeOrderDetail();
			        BeanUtils.populate(pdExchangeOrderDetail, request.getParameterMap());
				    
			        Pager pager = new Pager("pdExchangeOrderDetailListTable",request, 20);
			        List pdExchangeOrderDetails = pdExchangeOrderDetailManager.getPdExchangeOrderDetailsByCrm(crm,pager);
			        request.setAttribute("pdExchangeOrderDetailListTable_totalRows", pager.getTotalObjects());
			        return new ModelAndView("pd/pdExchangeOrderDetailList", Constants.PDEXCHANGEORDERDETAIL_LIST, pdExchangeOrderDetails);
			  }
    }
    
    
}
