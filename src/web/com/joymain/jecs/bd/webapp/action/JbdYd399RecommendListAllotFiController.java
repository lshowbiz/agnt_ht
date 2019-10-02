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
import com.joymain.jecs.bd.model.JbdYd399RecommendList;
import com.joymain.jecs.bd.service.JbdYd399RecommendListManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdYd399RecommendListAllotFiController extends BaseController implements Controller{
    private final Log log = LogFactory.getLog(JbdYd399RecommendListAllotFiController.class);
   
    private JbdYd399RecommendListManager jbdYd399RecommendListManager = null;

    

    public void setJbdYd399RecommendListManager(
			JbdYd399RecommendListManager jbdYd399RecommendListManager) {
		this.jbdYd399RecommendListManager = jbdYd399RecommendListManager;
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
        Pager pager = new Pager("JbdYd399RecommendListAllotFiListTable",request, 0);

        crm.addField("freezeStatus", "0");
        crm.addField("sendStatus", "0");
        String userCode = request.getParameter("userCode");
        String startWeek = request.getParameter("startCreateTime");
        String endWeek = request.getParameter("endCreateTime");
        List jbdYd399RecommendList =new ArrayList();
    	

        if ("post".equalsIgnoreCase(request.getMethod()) &&"jbdYd399RecommendListAllotFiAll".equals(request.getParameter("strAction"))) {
            if((!StringUtil.isEmpty(userCode))||(!StringUtil.isEmpty(startWeek))||(!StringUtil.isEmpty(endWeek))){
	        	jbdYd399RecommendList = jbdYd399RecommendListManager.getJbdYd399RecommendListsByCrm2(crm, pager);
	        	if(jbdYd399RecommendList == null){
	        		this.saveMessage(request, "请选择数据!");
	        	}else{
		        	for (int i = 0; i < jbdYd399RecommendList.size(); i++) {
		        		JbdYd399RecommendList jbdYd399RecommendList2=(JbdYd399RecommendList) jbdYd399RecommendList.get(i);
						String id=jbdYd399RecommendList2.getId().toString();
						jbdYd399RecommendListManager.saveInJdFiBook(defSysUser, id);
					}
	        	this.saveMessage(request, "执行完成");
	        	}
            }else{
            	this.saveMessage(request, "请选择数据!");
            }
        }
        


       if((!StringUtil.isEmpty(userCode))||(!StringUtil.isEmpty(startWeek))||(!StringUtil.isEmpty(endWeek))){
    	   
    	   jbdYd399RecommendList = jbdYd399RecommendListManager.getJbdYd399RecommendListsByCrm2(crm, pager);
       
       }

        request.setAttribute("JbdYd399RecommendListAllotFiListTable_totalRows", pager.getTotalObjects());
        return new ModelAndView("bd/jbdYd399RecommendListAllotFi", "JbdYd399RecommendListAllotFiList", jbdYd399RecommendList);
    }



}





