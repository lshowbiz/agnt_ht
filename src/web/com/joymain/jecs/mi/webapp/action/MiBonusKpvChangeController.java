package com.joymain.jecs.mi.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.bd.service.JbdMemberLinkCalcHistManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;


public class MiBonusKpvChangeController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(MiBonusKpvChangeController.class);
    private JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager;
    public void setJbdMemberLinkCalcHistManager(
			JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager) {
		this.jbdMemberLinkCalcHistManager = jbdMemberLinkCalcHistManager;
	}


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
        String userCode=request.getParameter("userCode");
        String wweek=request.getParameter("wweek");
        
        WeekFormatUtil.setSearchFweek(crm);
        
        Pager pager = new Pager("miBonusKpvChangeListTable",request, 20);      
    	if(StringUtil.isEmpty(userCode) && StringUtil.isEmpty(wweek)){
    		request.setAttribute("miBonusKpvChangeListTable_totalRows", pager.getTotalObjects());
    		return new ModelAndView("mi/miBonusKpvChangeList");
    	}else{
    		List miBonusKpvChangeList=jbdMemberLinkCalcHistManager.getJbdMemberLinkCalcHistsByCrm(crm, pager);
    		request.setAttribute("miBonusKpvChangeListTable_totalRows", pager.getTotalObjects());
    		return new ModelAndView("mi/miBonusKpvChangeList", "miBonusKpvChangeList", miBonusKpvChangeList);
    	}
    		
    }
}
