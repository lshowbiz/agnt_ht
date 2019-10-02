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

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JpmSalePresentController extends SaleBaseController implements Controller {
    private final Log log = LogFactory.getLog(JpmSalePresentController.class);
    
    public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response)throws Exception {
    	
        String strAction = request.getParameter("strAction");
        String flag = request.getParameter("flag");
        
        if(log.isDebugEnabled())
        	log.debug("strAction is:["+strAction +"] and flag is:["+flag+"]");
        
        if(StringUtils.isNotBlank(flag) &&
    			flag.equalsIgnoreCase("searchProductPresent")){
    		return searchProduct(request);
    	}
        
        if(! StringUtil.blankOrNull(strAction)){
        	log.info("strAction is:"+strAction);
        	if(strAction.equalsIgnoreCase("showPresentPage")){
        		return showproductPage(request);
        	}else if(strAction.equalsIgnoreCase("presentBindProduct")){
        		return bindProduct(request);
        	}else if ("deleteSalePresent".equals(strAction)  ){
        		return deleteJpmSalePro(request);
        	}else if(strAction.equalsIgnoreCase("showPresentList")){
        		return showSaleProductList(request);
        	}else if(strAction.equalsIgnoreCase("unBindSalePresent")){
        		return unBindProduct(request);
        	}
        }
       
		/**** auto pagination ***/
		CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jpmSalepresentListTable",request, 20);
        
        String stime = request.getParameter("stime");
        String etime = request.getParameter("endTime");
        String discount = request.getParameter("discount");
        String presentno = request.getParameter("presentno");
        String proName = request.getParameter("proName");
        String proNo = request.getParameter("presentno");
        String status = request.getParameter("status"); 
        if("".equals(status) || status == null){
        	status = "1";
        }
        
    	crm.addField("startime",stime);
    	crm.addField("endtime",etime);
    	crm.addField("discount",discount);
    	crm.addField("presentno", presentno);
    	crm.addField("integral","");
    	crm.addField("proName",proName);
    	crm.addField("proNo",proNo);
    	crm.addField("isactiva", status);
    	
    	if(log.isDebugEnabled())
    		log.debug("stime is:"+stime +" and endTime is:"+etime);
    
        //策略类型:1折价促销, 2赠品促销,3积分促销
        crm.addField("saleType", "2");
        List jpmSalePromoters = jpmSalePromoterManager.getJpmSalePromotersByCrm(crm,pager);
        request.setAttribute("jpmSalepresentListTable_totalRows", pager.getTotalObjects());
        request.setAttribute("stime", stime);
    	request.setAttribute("endtime", etime);
    	request.setAttribute("presentno", proNo);
    	request.setAttribute("proName", proName);
    	request.setAttribute("status", status);
    	
        return new ModelAndView("pm/jpmSalepresentList","jpmSalepresentList", jpmSalePromoters);
    }
 
}
