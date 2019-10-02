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
import com.joymain.jecs.Constants;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JpmSalePromoterController extends SaleBaseController implements Controller {
    private final Log log = LogFactory.getLog(JpmSalePromoterController.class);
   
    public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response)throws Exception {
    	
    	String strAction = request.getParameter("strAction");
        String flag = request.getParameter("flag");
        
        if(log.isDebugEnabled())
        	log.debug("strAction is:["+strAction +"] and flag is:["+flag+"]");
        
        if(StringUtils.isNotBlank(flag) && 
    			flag.equalsIgnoreCase("searchProduct")){
    		return searchProduct(request);
    	}
        if(! StringUtil.blankOrNull(strAction)){
        	
        	if(strAction.equalsIgnoreCase("showproductPage")){
        		return showproductPage(request);
        	}else if(strAction.equalsIgnoreCase("bindProduct")){
        		return bindProduct(request);
        	}else if ("deleteJpmSalePromoter".equals(strAction)  ){
        		return deleteJpmSalePro(request);
        	}else if(strAction.equalsIgnoreCase("showDisountProductList")){
        		return showSaleProductList(request);
        	}else if(strAction.equalsIgnoreCase("unBindSalePromoter")){
        		return unBindProduct(request);
        	}
        	
        }

		/**** auto pagination ***/
		CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jpmSalePromoterListTable",request, 20);
        
        String stime = request.getParameter("stime");
        String etime = request.getParameter("endTime");
        String discount = request.getParameter("discount");
        String proName = request.getParameter("proName");
        String proNo = request.getParameter("presentno");
        String status = request.getParameter("status");
    	crm.addField("startime",stime);
    	crm.addField("endtime",etime);
    	crm.addField("discount",discount);
    	crm.addField("integral","");
    	crm.addField("proName",proName);
    	crm.addField("proNo",proNo);
    	crm.addField("isactiva", status);
    	log.info("stime is:"+stime +" and endTime is:"+etime+" and discount is:"+discount);
    
        //策略类型:1折价促销, 2赠品促销,3积分促销
        crm.addField("saleType", "1");
        List jpmSalePromoters = jpmSalePromoterManager.getJpmSalePromotersByCrm(crm,pager);
        
        request.setAttribute("stime", stime);
    	request.setAttribute("endtime", etime);
    	request.setAttribute("discount", discount);
    	request.setAttribute("jpmSalePromoterListTable_totalRows", pager.getTotalObjects());
        request.setAttribute("servletPath", request.getServletPath());
        
        return new ModelAndView("pm/jpmSalepromoterList",Constants.JPMSALEPROMOTER_LIST, jpmSalePromoters);
    }
 
}
