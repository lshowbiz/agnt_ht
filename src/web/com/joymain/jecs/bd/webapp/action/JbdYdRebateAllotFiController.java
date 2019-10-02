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

import com.joymain.jecs.bd.model.JbdYdRebateList;
import com.joymain.jecs.bd.service.JbdYdRebateListManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdYdRebateAllotFiController extends BaseController implements Controller{
    private final Log log = LogFactory.getLog(JbdYdRebateAllotFiController.class);
   
    private JbdYdRebateListManager jbdYdRebateListManager = null;

    

    public void setJbdYdRebateListManager(
			JbdYdRebateListManager jbdYdRebateListManager) {
		this.jbdYdRebateListManager = jbdYdRebateListManager;
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
        Pager pager = new Pager("JbdJdRecordAllotFiListTable",request, 0);

        crm.addField("freezeStatus", "0");
        crm.addField("sendStatus", "0");
        String userCode = request.getParameter("userCode");
        String startCalcTime = request.getParameter("startCalcTime");
        String endCalcTime = request.getParameter("endCalcTime");

    	List errorList=new ArrayList();

        List jbdJdRecordLists =new ArrayList();
  
    	

        if ("post".equalsIgnoreCase(request.getMethod()) &&"jbdYdRebateAllotFiAll".equals(request.getParameter("strAction"))) {

        	jbdJdRecordLists = jbdYdRebateListManager.getJbdYdRebateListsByCrm(crm, pager);
        	for (int i = 0; i < jbdJdRecordLists.size(); i++) {
        		JbdYdRebateList jbdYdRebateList=(JbdYdRebateList) jbdJdRecordLists.get(i);
				String id=jbdYdRebateList.getId().toString();
				jbdYdRebateListManager.saveInJdFiBook(defSysUser, id);
			}
        	this.saveMessage(request, "执行完成");
    	
    		
        }
        
        

       if((!StringUtil.isEmpty(userCode))||(!StringUtil.isEmpty(startCalcTime))||(!StringUtil.isEmpty(endCalcTime))){
    	   
    	   jbdJdRecordLists = jbdYdRebateListManager.getJbdYdRebateListsByCrm(crm, pager);
       
       }

        request.setAttribute("JbdYdRebateListAllotFiListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("bd/jbdYdRebateAllotFi", "jbdYdRebateLists", jbdJdRecordLists);
    }



}





