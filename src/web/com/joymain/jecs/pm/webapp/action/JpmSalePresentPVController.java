package com.joymain.jecs.pm.webapp.action;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.pm.model.JpmSalePromoter;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JpmSalePresentPVController extends SaleBaseController implements Controller {
    private final Log log = LogFactory.getLog(JpmSalePresentPVController.class);
    
    public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response)throws Exception {
    	
        String flag = request.getParameter("flag");
        String strAction = request.getParameter("strAction");
        
        if(log.isDebugEnabled())
        	log.debug("strAction is:["+strAction +"] and flag is:["+flag+"]");
        
        if(StringUtils.isNotBlank(flag) && 
    			flag.equalsIgnoreCase("searchProductPV")){
    		return searchProduct(request);
    	}
        
        if(! StringUtil.blankOrNull(strAction)){
        	log.info("strAction is:"+strAction);
        	if(strAction.equalsIgnoreCase("showOrderPVBindPage")){
        		return showproductPage(request);
        	}else if(strAction.equalsIgnoreCase("presintorderPV")){
        		return bindProduct(request);
        	}else if ("delOrderPV".equals(strAction)){
        		return deleteJpmSalePro(request);
        	}else if(strAction.equalsIgnoreCase("showOrderPVList")){
        		return showSaleProductList(request);
        	}else if(strAction.equalsIgnoreCase("unBindOrderPV")){
        		return unBindProduct(request);
        	}
        	
        }
        
		/**** auto pagination ***/
		CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jpmSalePromoterListTable",request, 20);
        
        String servletPath = request.getServletPath();
        String stime = request.getParameter("stime");
        String etime = request.getParameter("endTime");
        String discount = request.getParameter("discount");
        String amount = request.getParameter("amount");
        String pv = request.getParameter("pv");
        String status = request.getParameter("status"); 
        
    	crm.addField("startime",stime);
    	crm.addField("endtime",etime);
    	crm.addField("discount",discount);
    	crm.addField("amount", amount);
    	crm.addField("integral","");
    	crm.addField("pv",pv);
    	crm.addField("isactiva", status);
    	
    	if(log.isDebugEnabled())
    		log.debug("stime is:"+stime +" and endTime is:"+etime);
    
    	/*
		 * 策略类型:
		 * 1折价促销, 2赠品促销,3积分促销
		 * 4根据订单总金额或PV送赠品
		 */
        crm.addField("saleType", "4");
        List<JpmSalePromoter> jpmSalePromoters = jpmSalePromoterManager.
        		getJpmSalePromotersByCrm(crm,pager);
        
        request.setAttribute("stime", stime);
    	request.setAttribute("endtime", etime);
    	request.setAttribute("amount", amount);
    	request.setAttribute("pv", pv);
    	request.setAttribute("status", status);
    	
        request.setAttribute("jpmSalePromoterListTable_totalRows", 
        		pager.getTotalObjects());
        request.setAttribute("servletPath", servletPath);
        
        return new ModelAndView("pm/jpmSalepresentPVList","jpmSalePromoterList", jpmSalePromoters);
    }
}
