package com.joymain.jecs.mi.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.mi.model.JmiRecommendRef;
import com.joymain.jecs.mi.service.JmiRecommendRefManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JmiRecommendRefPointController implements Controller {
    private JmiRecommendRefManager jmiRecommendRefManager;

    public void setJmiRecommendRefManager(JmiRecommendRefManager jmiRecommendRefManager) {
        this.jmiRecommendRefManager = jmiRecommendRefManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
    	

        SysUser defSysUser = SessionLogin.getLoginUser(request);
		
		String userCode  = request.getParameter("userCode");

		List points=new ArrayList();
		if(!StringUtil.isEmpty(userCode)){

			JmiRecommendRef jmiRecommendRef=jmiRecommendRefManager.getJmiRecommendRef(userCode);
			if(jmiRecommendRef!=null&&defSysUser.getCompanyCode().equals(jmiRecommendRef.getJmiMember().getCompanyCode())){
				points=jmiRecommendRefManager.getJmiRecommendRefOrder(jmiRecommendRef.getTreeIndex());
			}
		}
		
    	request.setAttribute("points", points);

    	request.setAttribute("jmiRecommendRefPointListTable_totalRows", points.size());
		return new ModelAndView("mi/jmiRecommendRefPoint");
	}
    
   
    
}
