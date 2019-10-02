package com.joymain.jecs.fi.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.Constants;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.fi.model.JfiSunOrder;
import com.joymain.jecs.fi.service.JfiSunOrderManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.webapp.util.LocaleUtil;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 订单退货
 * @author Alvin
 *
 */
public class JfiSunOrderReturnController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiSunOrderReturnController.class);
    private JfiSunOrderManager jfiSunOrderManager = null;
    private AlCountryManager alCountryManager = null;

    public void setJfiSunOrderManager(JfiSunOrderManager jfiSunOrderManager) {
        this.jfiSunOrderManager = jfiSunOrderManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
        
        String moId = request.getParameter("moId");
        if(!StringUtil.isEmpty(moId)){
        	JfiSunOrder jfiSunOrder = jfiSunOrderManager.getJfiSunOrder(moId);
        	if(jfiSunOrder==null){
        		return null;
        	}else{
        		if("0".equals(jfiSunOrder.getIsDisable())){
        			jfiSunOrder.setIsDisable("1");
        			jfiSunOrder.setReturnTime(new Date());
            		saveMessage(request, "退货成功");
        		}else{
            		jfiSunOrder.setIsDisable("0");
        			jfiSunOrder.setReturnTime(null);
            		saveMessage(request, "取消退货成功");
        		}
        		jfiSunOrderManager.saveJfiSunOrder(jfiSunOrder);
        	}
        	return new ModelAndView("redirect:jfiSunOrderReturn.html");
        }

        //收货地区
        AlCountry alCountry = (AlCountry) alCountryManager.getAlCountrysByCompany(loginSysUser.getCompanyCode()).get(0);
    	alCountry.getAlStateProvinces().iterator();
    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
        
        if(request.getParameter("search")==null){
            Pager pager = new Pager("jpoMemberOrderListTable",request, 20);
            request.setAttribute("jpoMemberOrderListTable_totalRows", pager.getTotalObjects());
            return new ModelAndView("fi/jfiSunOrderReturn", Constants.JPOMEMBERORDER_LIST, null);
        }

        CommonRecord crm = RequestUtil.toCommonRecord(request);
    	crm.addField("companyCode", loginSysUser.getCompanyCode());
    	crm.addField("logType", "BC");
        
        Pager pager = new Pager("jpoMemberOrderListTable",request, 20);
        List jpoMemberOrders = jfiSunOrderManager.getJfiSunOrdersByCrm(crm,pager);
        List products = jfiSunOrderManager.getStatisticProductSale(crm);
        request.setAttribute("jpoMemberOrderListTable_totalRows", pager.getTotalObjects());
        
        return new ModelAndView("fi/jfiSunOrderReturn","jpoMemberOrderList",jpoMemberOrders).addObject("pmProductList", products);
    }

	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}
}
