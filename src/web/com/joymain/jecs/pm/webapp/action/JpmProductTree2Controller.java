package com.joymain.jecs.pm.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.pm.service.JpmProductManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JpmProductTree2Controller extends BaseController implements
		Controller {
	private JpmProductManager jpmProductManager = null;
	public void setJpmProductManager(JpmProductManager jpmProductManager) {
		this.jpmProductManager = jpmProductManager;
	}
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String strAction=request.getParameter("strAction");
		String view = "/pm/jpmProductTree2";
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		//如果不是全球，则设置区域
		String companyCode = request.getParameter("companyCode");
		SysUser loginUser = SessionLogin.getLoginUser(request);
		if(StringUtils.isNotEmpty(companyCode)){
			crm.setValue("companyCode", companyCode);
		}else{
			if (!"AA".equalsIgnoreCase(loginUser.getCompanyCode())) {
				crm.setValue("companyCode",loginUser.getCompanyCode());
	
			} 
		}
		
		List productSaleNews = jpmProductManager.getJpmProductsByCrm2(crm, null);
		request.setAttribute("productSaleNews", productSaleNews);	 
		
		return new ModelAndView(view);
	}

}
