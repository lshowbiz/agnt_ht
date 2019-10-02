package com.joymain.jecs.bd.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import yspay.util.StringUtil;

import com.joymain.jecs.bd.model.JbdCalcDayFb;
import com.joymain.jecs.bd.model.JbdDayCustRecommend;
import com.joymain.jecs.bd.service.JbdDayCustRecommendManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdDayCustRecommendAllotFiController extends BaseController implements Controller{
    private final Log log = LogFactory.getLog(JbdDayCustRecommendAllotFiController.class);
   
    private JbdDayCustRecommendManager jbdDayCustRecommendManager = null;

    

    public void setJbdDayCustRecommendManager(
			JbdDayCustRecommendManager jbdDayCustRecommendManager) {
		this.jbdDayCustRecommendManager = jbdDayCustRecommendManager;
	}



	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        String companyCode="";
        final SysUser defSysUser = SessionLogin.getLoginUser(request);
        if("C".equals(defSysUser.getUserType())){
        	companyCode=defSysUser.getCompanyCode();  
    		if("AA".equals(defSysUser.getCompanyCode())){
    			companyCode = request.getParameter("company");
    		}
    	}
        
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("JbdDayCustRecommendAllotFiListTable",request, 0);

        crm.addField("freezeStatus", "0");
        crm.addField("status", "0");
     

    	List errorList=new ArrayList();

        
        String startAllotMoney=request.getParameter("startAllotMoney");
        String endAllotMoney=request.getParameter("endAllotMoney");

        List jbdDayCustRecommend =new ArrayList();
  
    	

        if ("post".equalsIgnoreCase(request.getMethod()) &&"jbdDayCustRecommendAllotFiAll".equals(request.getParameter("strAction"))) {

        	jbdDayCustRecommend = jbdDayCustRecommendManager.getJbdDayCustRecommendsByCrm(crm, pager);
        	for (int i = 0; i < jbdDayCustRecommend.size(); i++) {
        		JbdDayCustRecommend jbdDayCustRecommend2=(JbdDayCustRecommend) jbdDayCustRecommend.get(i);
				String id=jbdDayCustRecommend2.getId().toString();
				jbdDayCustRecommendManager.saveInJdFiBook(defSysUser, id);
			}
        	this.saveMessage(request, "执行完成");
    	
    		
        }
        
        String userCode = request.getParameter("userCode");
        String startWeek = request.getParameter("startCreateTime");
        String endWeek = request.getParameter("endCreateTime");

       if((!StringUtil.isEmpty(userCode))||(!StringUtil.isEmpty(startWeek))||(!StringUtil.isEmpty(endWeek))){
    	   
    	   jbdDayCustRecommend = jbdDayCustRecommendManager.getJbdDayCustRecommendsByCrm(crm, pager);
       
       }

        request.setAttribute("JbdDayCustRecommendAllotFiListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("bd/jbdDayCustRecommendAllotFi", "JbdDayCustRecommendAllotFiList", jbdDayCustRecommend);
    }



}





