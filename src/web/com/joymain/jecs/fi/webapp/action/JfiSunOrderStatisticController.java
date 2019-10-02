package com.joymain.jecs.fi.webapp.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.Constants;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.fi.service.JfiSunOrderManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.webapp.util.LocaleUtil;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 订单统计
 * @author Alvin
 *
 */
public class JfiSunOrderStatisticController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiSunOrderStatisticController.class);
    private JfiSunOrderManager jfiSunOrderManager = null;
    private AlCountryManager alCountryManager = null;
    private BdPeriodManager bdPeriodManager = null;

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

        //收货地区
        AlCountry alCountry = (AlCountry) alCountryManager.getAlCountrysByCompany(loginSysUser.getCompanyCode()).get(0);
    	alCountry.getAlStateProvinces().iterator();
    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
        
        if(request.getParameter("search")==null){
            Pager pager = new Pager("jpoMemberOrderListTable",request, 20);
            request.setAttribute("jpoMemberOrderListTable_totalRows", pager.getTotalObjects());
            return new ModelAndView("fi/jfiSunOrderStatistic", Constants.JPOMEMBERORDER_LIST, null);
        }

        CommonRecord crm = RequestUtil.toCommonRecord(request);
    	crm.addField("companyCode", loginSysUser.getCompanyCode());
    	crm.addField("isDisable", "0");
    	crm.addField("logType", "BC");
    	
    	String period = request.getParameter("period");
    	if(!StringUtil.isEmpty(period)){
    		if(period.length()!=6){
                Pager pager = new Pager("jpoMemberOrderListTable",request, 20);
                request.setAttribute("jpoMemberOrderListTable_totalRows", pager.getTotalObjects());
                return new ModelAndView("fi/jfiSunOrderPeriodStatistic", Constants.JPOMEMBERORDER_LIST, null);
    		}else{
    			period = WeekFormatUtil.getFormatWeek("f",period);
            	Integer wyear;
            	Integer wweek;
            	try{
            		wyear = Integer.parseInt(period.substring(0, 4));
            		wweek = Integer.parseInt(period.substring(4, 6));
                	BdPeriod bdPeriod = bdPeriodManager.getBdPeriodByWeek(wyear, wweek, null);
                	if(bdPeriod==null){
                        Pager pager = new Pager("jpoMemberOrderListTable",request, 20);
                        request.setAttribute("jpoMemberOrderListTable_totalRows", pager.getTotalObjects());
                        return new ModelAndView("fi/jfiSunOrderPeriodStatistic", Constants.JPOMEMBERORDER_LIST, null);
                	}
                	crm.addField("startLogTime", new SimpleDateFormat("yyyy-MM-dd").format(bdPeriod.getStartTime()));
                	crm.addField("endLogTime", new SimpleDateFormat("yyyy-MM-dd").format(bdPeriod.getEndTime()));
            	}catch(Exception e){
                    Pager pager = new Pager("jpoMemberOrderListTable",request, 20);
                    request.setAttribute("jpoMemberOrderListTable_totalRows", pager.getTotalObjects());
                    return new ModelAndView("fi/jfiSunOrderPeriodStatistic", Constants.JPOMEMBERORDER_LIST, null);
            	}
    		}
    	}
        
        Pager pager = new Pager("jpoMemberOrderListTable",request, 20);
        List jpoMemberOrders = jfiSunOrderManager.getJfiSunOrdersByCrm(crm,pager);
        List products = jfiSunOrderManager.getStatisticProductSale(crm);
        request.setAttribute("jpoMemberOrderListTable_totalRows", pager.getTotalObjects());
        
        return new ModelAndView("fi/jfiSunOrderStatistic","jpoMemberOrderList",jpoMemberOrders).addObject("pmProductList", products);
    }

	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
}
