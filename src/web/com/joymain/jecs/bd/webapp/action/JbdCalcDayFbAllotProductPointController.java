package com.joymain.jecs.bd.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.bd.model.JbdCalcDayFb;
import com.joymain.jecs.bd.service.JbdCalcDayFbManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdCalcDayFbAllotProductPointController extends BaseController implements Controller{
    private final Log log = LogFactory.getLog(JbdCalcDayFbAllotProductPointController.class);
   
    private JbdCalcDayFbManager jbdCalcDayFbManager = null;

    public void setJbdCalcDayFbManager(JbdCalcDayFbManager jbdCalcDayFbManager) {
        this.jbdCalcDayFbManager = jbdCalcDayFbManager;
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
        Pager pager = new Pager("jbdCalcDayFbListTable",request, 0);

        crm.addField("freezeStatus", "0");
        crm.addField("status", "0");
     

    	List errorList=new ArrayList();

        
        String startAllotMoney=request.getParameter("startAllotMoney");
        String endAllotMoney=request.getParameter("endAllotMoney");

        List jbdCalcDayFbList =new ArrayList();
  
    	

        if ("post".equalsIgnoreCase(request.getMethod()) &&"allotSelectedProductPointAll".equals(request.getParameter("strAction"))) {

        	jbdCalcDayFbList = jbdCalcDayFbManager.getJbdCalcDayFbsByCrm(crm, pager);
        	for (int i = 0; i < jbdCalcDayFbList.size(); i++) {
        		JbdCalcDayFb jbdCalcDayFb=(JbdCalcDayFb) jbdCalcDayFbList.get(i);
				String id=jbdCalcDayFb.getId().toString();
				jbdCalcDayFbManager.saveInProductPointFiBook(defSysUser, id);
			}
        	this.saveMessage(request, "执行完成");
    	
    		
        }
        
      
       
		
        String userCode=request.getParameter("userCode");
        
        
        if(userCode!=null){
        	jbdCalcDayFbList = jbdCalcDayFbManager.getJbdCalcDayFbsByCrm(crm, pager);

        	
        	
        }
        request.setAttribute("jbdCalcDayFbListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("bd/jbdCalcDayFbAllotProductPoint", "jbdCalcDayFbList", jbdCalcDayFbList);
    }



}





