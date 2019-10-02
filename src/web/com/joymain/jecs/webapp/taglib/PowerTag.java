package com.joymain.jecs.webapp.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.joymain.jecs.Constants;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysModuleManager;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 权限判定
 * 
 * @author Asii
 * @jsp.tag name="power" bodycontent="empty"
 */
public class PowerTag extends TagSupport {

	private String powerCode;
	private String authority="true";

	/**
	 * @jsp.attribute required="false" rtexprvalue="true"
	 * 
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public int doStartTag() throws JspException {
		if(Constants.AUTHORITY_IGNORE){
			return this.EVAL_BODY_INCLUDE;
		}
		SysUser sysUser = SessionLogin.getLoginUser((HttpServletRequest)this.pageContext.getRequest());
		if (sysUser.getIsAdmin()) {
			return this.EVAL_BODY_INCLUDE;
		}
		
		ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(this.pageContext.getServletContext());
		SysModuleManager sysModuleManager = (SysModuleManager) context.getBean("sysModuleManager");
		boolean hasPower=sysModuleManager.getSysUserPowerByCode(sysUser, powerCode);
		boolean authorityFlag=true;
		if("false".equals(authority)){
			authorityFlag = false;
		}
		//Map<String, String> powerMap = SessionLogin.getLoginUser((HttpServletRequest)this.pageContext.getRequest()).getPowerMap();
		/*if (!hasPower) {
			return this.SKIP_BODY;
		}*/
		if(hasPower==authorityFlag){
			return this.EVAL_BODY_INCLUDE;
		}else{
			return this.SKIP_BODY;
		}
		
	}

	/**
	 * @jsp.attribute required="true" rtexprvalue="true"
	 * 
	 */
	public void setPowerCode(String powerCode) {
		try {
			this.powerCode = (String) ExpressionEvaluatorManager.evaluate("powerCode", powerCode, String.class, this, pageContext);
		} catch (JspException e) {
		} catch (NullPointerException e) {
			this.powerCode = "";
		}
	}
}
