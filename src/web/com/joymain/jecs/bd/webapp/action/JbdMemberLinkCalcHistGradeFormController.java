package com.joymain.jecs.bd.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.bd.model.JbdMemberLinkCalcHist;
import com.joymain.jecs.bd.model.VJbdMemberLinkCalc;
import com.joymain.jecs.bd.service.JbdMemberLinkCalcHistManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdMemberLinkCalcHistGradeFormController extends BaseFormController {
    private JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager = null;
	public void setJbdMemberLinkCalcHistManager(JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager) {
        this.jbdMemberLinkCalcHistManager = jbdMemberLinkCalcHistManager;
    }
    public JbdMemberLinkCalcHistGradeFormController() {
        setCommandName("jbdMemberLinkCalcHist");
        setCommandClass(JbdMemberLinkCalcHist.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {

        String id = request.getParameter("id");
        SysUser defSysUser = SessionLogin.getLoginUser(request);
      
        
        JbdMemberLinkCalcHist jbdMemberLinkCalcHist = jbdMemberLinkCalcHistManager.getJbdMemberLinkCalcHist(id);

        
        
        request.setAttribute("oldHonorStar", jbdMemberLinkCalcHist.getHonorStar());
        request.setAttribute("oldPassStar", jbdMemberLinkCalcHist.getPassStar());
        request.setAttribute("oldQualifyStar", jbdMemberLinkCalcHist.getQualifyStar());
        request.setAttribute("oldQualifyRemark", jbdMemberLinkCalcHist.getQualifyRemark());

        return jbdMemberLinkCalcHist;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdMemberLinkCalcHist jbdMemberLinkCalcHist =(JbdMemberLinkCalcHist) command;
        SysUser defSysUser = SessionLogin.getLoginUser(request);

		String oldHonorStar=request.getParameter("oldHonorStar");
		String oldPassStar=request.getParameter("oldPassStar");
		String oldQualifyStar = request.getParameter("oldQualifyStar");
		String oldQualifyRemark = request.getParameter("oldQualifyRemark");
        
		if(!oldHonorStar.equals(jbdMemberLinkCalcHist.getHonorStar().toString()) || !oldPassStar.equals(jbdMemberLinkCalcHist.getPassStar().toString()) || !oldQualifyStar.equals(jbdMemberLinkCalcHist.getQualifyStar().toString()) || !oldQualifyRemark.equals(jbdMemberLinkCalcHist.getQualifyRemark())){
			jbdMemberLinkCalcHistManager.saveJbdMemberLinkCalcHistGrade(jbdMemberLinkCalcHist, request, defSysUser);
			this.saveMessage(request, "修改成功");
		}
	

		return new ModelAndView("redirect:jbdMemberLinkCalcHistGrade.html?userCode="+jbdMemberLinkCalcHist.getUserCode());
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
