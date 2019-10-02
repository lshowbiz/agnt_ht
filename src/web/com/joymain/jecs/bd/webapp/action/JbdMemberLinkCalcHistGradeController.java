package com.joymain.jecs.bd.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.bd.model.JbdMemberLinkCalcHist;
import com.joymain.jecs.bd.service.JbdMemberLinkCalcHistManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdMemberLinkCalcHistGradeController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdMemberLinkCalcHistGradeController.class);
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

        CommonRecord crm=RequestUtil.toCommonRecord(request);
        String companyCode="";
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        if("C".equals(defSysUser.getUserType())){
        	companyCode=defSysUser.getCompanyCode();  
    		if("AA".equals(defSysUser.getCompanyCode())){
    			companyCode=request.getParameter("companyCode");
    		}
    	}
        
        List jbdMemberLinkCalcHistGradeList=null;
        Pager pager = new Pager("jbdMemberLinkCalcHistListTable",request, 20);
        
        String memberNo= crm.getString("userCode",request.getParameter("userCode"));
        String wweek= crm.getString("wweek",request.getParameter("wweek"));
        String name= crm.getString("name",request.getParameter("name"));
        
        WeekFormatUtil.setSearchFweek(crm);
 
    	if (StringUtil.isEmpty(memberNo)&&!StringUtil.isInteger(wweek)&&StringUtil.isEmpty(name)){
        	request.setAttribute("jbdMemberLinkCalcHistListTable_totalRows", pager.getTotalObjects());
			return new ModelAndView("bd/jbdMemberLinkCalcHistGradeList", "jbdMemberLinkCalcHistGradeList", null);	
		}else{
			
			 if ("post".equalsIgnoreCase(request.getMethod()) &&"editJbdMemberLinkCalcHistGrade2".equals(request.getParameter("strAction"))) {
				 	String honorStar=request.getParameter("honorStar");
				 	String qualifyStar=request.getParameter("qualifyStar");
		         	String[] adviceCodes = request.getParameter("strAdviceCodes").split(",");
		         	try {
		         		if(adviceCodes.length>1){
		         			for (int j = 1; j < adviceCodes.length; j++) {

		         				JbdMemberLinkCalcHist jbdMemberLinkCalcHist = jbdMemberLinkCalcHistManager.getJbdMemberLinkCalcHist(adviceCodes[j]);
		         				jbdMemberLinkCalcHist.setHonorStar(Integer.valueOf(honorStar));
		         				jbdMemberLinkCalcHist.setQualifyStar(Integer.valueOf(qualifyStar));
		         				jbdMemberLinkCalcHistManager.saveJbdMemberLinkCalcHistGrade(jbdMemberLinkCalcHist, request, defSysUser);
							}
		         		}
		 				this.saveMessage(request, "修改成功");
		 			} catch (AppException e) {
		 				this.saveMessage(request, LocaleUtil.getLocalText(e.getErrMsg()));
		 			}
		 		}
			
			crm.addField("companyCode", companyCode);
			
			jbdMemberLinkCalcHistGradeList=jbdMemberLinkCalcHistManager.getJbdMemberLinkCalcHistsByCrm(crm, pager);
			request.setAttribute("jbdMemberLinkCalcHistListTable_totalRows", pager.getTotalObjects());

	        return new ModelAndView("bd/jbdMemberLinkCalcHistGradeList", "jbdMemberLinkCalcHistGradeList", jbdMemberLinkCalcHistGradeList);
		}
    
    	
	
	}

}
