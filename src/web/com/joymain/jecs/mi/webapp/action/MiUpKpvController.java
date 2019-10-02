package com.joymain.jecs.mi.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.service.JbdMemberLinkCalcHistManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;
public class MiUpKpvController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(MiUpKpvController.class);
    private JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager;

	public void setJbdMemberLinkCalcHistManager(
			JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager) {
		this.jbdMemberLinkCalcHistManager = jbdMemberLinkCalcHistManager;
	}
	private JmiMemberManager jmiMemberManager;
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        String companyCode="";
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        if("C".equals(defSysUser.getUserType())){
        	companyCode=defSysUser.getCompanyCode();  
    		if("AA".equals(defSysUser.getCompanyCode())){
    			companyCode = null;
    		}
    	}
    	CommonRecord crm=RequestUtil.toCommonRecord(request);
        crm.addField("companyCode", companyCode);
       
        Pager pager = new Pager("miUpKpvListTable",request, 20);      
        String userCode=request.getParameter("userCode");
        String wweek=request.getParameter("wweek");
        
        WeekFormatUtil.setSearchFweek(crm);

        RequestUtil.freshSession(request,"miUpKpv", Constants.COMPANY_TIME);

    	if(StringUtil.isEmpty(userCode)||!StringUtil.isInteger(wweek)){
    		request.setAttribute("miUpKpvListTable_totalRows", pager.getTotalObjects());
    		return new ModelAndView("mi/miUpKpvList");
    	}else{


			if(RequestUtil.saveOperationSession(request, "miUpKpv", Constants.COMPANY_TIME)!=0){
	       		  return new ModelAndView("redirect:miUpKpv.html");
	       	 }
			if(null==jmiMemberManager.getJmiMember(userCode)){
				return new ModelAndView("redirect:miUpKpv.html");
			}
			
    		List list=jbdMemberLinkCalcHistManager.getBdMemberBounsCalcLinkBySql(crm.getString("userCode", ""), Integer.parseInt(crm.getString("wweek", "")), "8888888888");
    		List bdMemberBounsCalcs=new ArrayList();
    		for (int i = list.size()-1; i>0; i--) {
    			bdMemberBounsCalcs.add(list.get(i));
			}
    		return new ModelAndView("mi/miUpKpvList", "bdMemberBounsCalcs", bdMemberBounsCalcs);
    	}
    		
    }
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
}
