package com.joymain.jecs.fi.webapp.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.webapp.action.BaseFormController;
/**
 * 快钱POS会员登陆
 * @author Alvin
 *
 */
public class Jfi99billPosLogFormController extends BaseFormController {
	private SysUserManager sysUserManager = null;

    public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}
    public Jfi99billPosLogFormController() {
        setCommandName("sysUser");
        setCommandClass(SysUser.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        SysUser sysUser = new SysUser();
        return sysUser;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        SysUser sysUser = (SysUser) command;
		String verifyCode = request.getParameter("verifyCode");
		if (StringUtils.isEmpty(verifyCode) || request.getSession().getAttribute("verifyCode")==null || !verifyCode.equals(request.getSession().getAttribute("verifyCode").toString())) {
			errors.rejectValue("userCode", "error.verifycodeInputError");
			return showForm(request, response, errors);
		}
		try{
			if(StringUtils.isEmpty(sysUser.getUserCode()) || StringUtils.isEmpty(sysUser.getPassword())){
				errors.rejectValue("password", "sys.message.userNameOrPasswordWrong");
				return showForm(request, response, errors);
			}
			sysUser = sysUserManager.login(sysUser.getUserCode(), sysUser.getPassword());
			if(!"M".equals(sysUser.getUserType())){
				errors.rejectValue("password", "sys.message.userNameOrPasswordWrong");
				return showForm(request, response, errors);
			}
			if ("0".equals(sysUser.getState()) || "2".equals(sysUser.getState())) {
				errors.rejectValue("userCode", "sys.message.userIsRestrict");
				return showForm(request, response, errors);
			}
			Map map = new HashMap(0);
			String isCheckOrder = request.getParameter("isCheckOrder");
			if(isCheckOrder==null){
				map.put("isCheckOrder", false);
			}else{
				map.put("isCheckOrder", true);
			}
			map.put("sysUser", sysUser);
			map.put("login", true);
			request.getSession().setAttribute("map", map);
	        return new ModelAndView(getSuccessView()+"?strAction=addJfi99billPosLog");
		}catch (AppException e) {
			log.info(e.getMessage());
			errors.rejectValue("userCode", e.getErrMsg());
			return showForm(request, response, errors);
		}
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
