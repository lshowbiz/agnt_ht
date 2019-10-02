package com.joymain.jecs.pm.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.pm.service.JpmProductManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JpmProductTreeController extends BaseController implements
		Controller {
	private JpmProductManager jpmProductManager = null;
	public void setJpmProductManager(JpmProductManager jpmProductManager) {
		this.jpmProductManager = jpmProductManager;
	}
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String strAction=request.getParameter("strAction");
		String view = "/pm/jpmProductTree";
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		if("combineProduct".equals(strAction)){
			crm.setValue("combineFlag", "1");
		}
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		crm.setValue("companyCode", sessionLogin.getCompanyCode());
		
		List products = jpmProductManager.getJpmProductsByCrm(crm, null);
		request.setAttribute("products", products);
		return new ModelAndView(view);
	}

}
