package com.joymain.jecs.pm.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JpmSaleIntegralController extends SaleBaseController implements Controller {
    private final Log log = LogFactory.getLog(JpmSaleIntegralController.class);
   
    public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response)throws Exception {
    
        String strAction = request.getParameter("strAction");
        
        if(! StringUtil.blankOrNull(strAction)){
        	log.info("strAction is:"+strAction);
        	if ("deleteIntegral".equals(strAction)  ){
        		return deleteJpmSalePro(request);
        	}
        }
        
		/**** auto pagination ***/
		CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jpmSaleIntegralListTable",request, 20);
        
        String stime = request.getParameter("stime");
        String etime = request.getParameter("endTime");
        String discount = request.getParameter("discount");
        String integral = request.getParameter("integral");
        String proName = request.getParameter("proName");
        String proNo = request.getParameter("presentno");
        String status = request.getParameter("status");
        crm.addField("isactiva", status);
    	crm.addField("startime",stime);
    	crm.addField("endtime",etime);
    	crm.addField("discount",discount);
    	crm.addField("integral", integral);
    	crm.addField("proName",proName);
    	crm.addField("proNo",proNo);
    	
    	if(log.isDebugEnabled())
    		log.debug("stime is:"+stime +" and endTime is:"+etime+" " +
    				"and integral is:"+integral);
    
        //策略类型:1折价促销, 2赠品促销,3积分促销
        crm.addField("saleType", "3");
        List jpmSalePromoters = jpmSalePromoterManager.getJpmSalePromotersByCrm(crm,pager);
        request.setAttribute("jpmSaleIntegralListTable_totalRows", pager.getTotalObjects());
        request.setAttribute("stime", stime);
    	request.setAttribute("endtime", etime);
    	request.setAttribute("integral", integral);
    	request.setAttribute("status", status);
       
        return new ModelAndView("pm/jpmSaleIntegralList","jpmSaleIntegralList", jpmSalePromoters);
    }
    
	
}
