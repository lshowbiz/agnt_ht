package com.joymain.jecs.fi.webapp.action;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.fi.model.FiFundbookTemp;
import com.joymain.jecs.fi.service.FiFundbookTempManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class FiFundbookTempFormController extends BaseFormController {
    private FiFundbookTempManager fiFundbookTempManager = null;
    private SysUserManager sysUserManager = null;

    public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}
	public void setFiFundbookTempManager(FiFundbookTempManager fiFundbookTempManager) {
        this.fiFundbookTempManager = fiFundbookTempManager;
    }
    public FiFundbookTempFormController() {
        setCommandName("fiFundbookTemp");
        setCommandClass(FiFundbookTemp.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
    	
        String tempId = request.getParameter("tempId");
        FiFundbookTemp fiFundbookTemp = null;
        SysUser sysUser = null;

        if (!StringUtils.isEmpty(tempId)) {
        	
            fiFundbookTemp = fiFundbookTempManager.getFiFundbookTemp(tempId);
            
            if(fiFundbookTemp.getStatus()==2){
				//如果已确认,则不能修改
				throw new AppException(LocaleUtil.getLocalText("error.fiBankbookTemp.has.verified"));
			}
            sysUser= sysUserManager.getSysUser(fiFundbookTemp.getUserCode());
        } else {
            fiFundbookTemp = new FiFundbookTemp();
            sysUser = new SysUser();
        }
        
        request.setAttribute("sysUser", sysUser);

        return fiFundbookTemp;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command,
            BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
//
//        FiFundbookTemp fiFundbookTemp = (FiFundbookTemp) command;
//        boolean isNew = (fiFundbookTemp.getTempId() == null);
//        Locale locale = request.getLocale();
//	String key=null;
//	String strAction = request.getParameter("strAction");
//	if ("deleteFiFundbookTemp".equals(strAction)  ) {
//		fiFundbookTempManager.removeFiFundbookTemp(fiFundbookTemp.getTempId().toString());
//		key="fiFundbookTemp.delete";
//	}else{
//		fiFundbookTempManager.saveFiFundbookTemp(fiFundbookTemp);
//		key="fiFundbookTemp.update";
//	}
//
//        return new ModelAndView(getSuccessView());
        
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
		if("C".equals(loginSysUser.getUserType())){
    		if(RequestUtil.saveOperationSession(request,"editFiFundbookTemp",10l)!=0){
    			return new ModelAndView("redirect:editFiFundbookTemp.html");
    		}
    	}else if("M".equals(loginSysUser.getUserType())){
    		if(RequestUtil.saveOperationSession(request,"editFiFundbookTemp",10l)!=0){
    			return new ModelAndView("redirect:editFiFundbookTemp.html");
    		}
    	}

		FiFundbookTemp fiFundbookTemp = (FiFundbookTemp) command;
		boolean isNew = (fiFundbookTemp.getTempId() == null);

		if ("deleteFiFundbookTempJJ".equalsIgnoreCase(request.getParameter("strAction"))) {
			fiFundbookTempManager.removeFiFundbookTemp(fiFundbookTemp.getTempId().toString());

			saveMessage(request, getText("fiFundbookTemp.deleted"));
		} else {
			//验证用户是否存在
			SysUser sysUser=this.sysUserManager.getSysUser(fiFundbookTemp.getUserCode());
			if(sysUser==null || (!SessionLogin.getLoginUser(request).getCompanyCode().equals("AA") && !SessionLogin.getLoginUser(request).getCompanyCode().equals(sysUser.getCompanyCode()))){
				//如果不存在或者当前操作用户与被操作用户的公司编码不一致(操作用户为管理中心除外)
				//errors.rejectValue("sysUser.userCode", "error.sysUser.not.existed");
				saveMessage(request, "会员编号不存在!");
				return showForm(request, response, errors);
			}
			if(StringUtil.isEmpty(fiFundbookTemp.getBankbookType())){
				//errors.rejectValue("bankbookType", "error.bankbookType.not.null");
				saveMessage(request, "资金类别不能为空!");
				return showForm(request, response, errors);
			}
			if ("addFiFundbookTempJJ".equalsIgnoreCase(request.getParameter("strAction"))) {
				fiFundbookTemp.setUserCode(sysUser.getUserCode());
				fiFundbookTemp.setCompanyCode(sysUser.getCompanyCode());
				fiFundbookTemp.setCreaterCode(SessionLogin.getOperatorUser(request).getUserCode());
				fiFundbookTemp.setCreaterName(SessionLogin.getOperatorUser(request).getUserName());
				fiFundbookTemp.setCreateTime(new Date());
				fiFundbookTemp.setStatus(1);
				long totalCount=this.fiFundbookTempManager.getCountByDate(SessionLogin.getLoginUser(request).getCompanyCode(),fiFundbookTemp.getUserCode(),fiFundbookTemp.getBankbookType());
				fiFundbookTemp.setSerial(totalCount+1);
			}else if("editFiFundbookTempJJ".equalsIgnoreCase(request.getParameter("strAction"))){
				fiFundbookTemp.setLastUpdaterCode(SessionLogin.getOperatorUser(request).getUserCode());
				fiFundbookTemp.setLastUpdaterName(SessionLogin.getOperatorUser(request).getUserName());
				fiFundbookTemp.setLastUpdateTime(new Date());
			}
			fiFundbookTempManager.saveFiFundbookTemp(fiFundbookTemp);

			String key = (isNew) ? "fiBankbookTemp.added" : "fiBankbookTemp.updated";
			saveMessage(request, getText(key));
		}

		ModelAndView mv=new ModelAndView(getSuccessView());
		mv.addObject("strAction", "listfiFundbookTempsJJ");
		mv.addObject("needReload", "1");
		return mv;
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
    	
    	String[] allowedFields = {
    			"userCode",
    			"dealType",
    			"moneyType",
    			"money",
    			"bankbookType",
    			"notes"
    			};
    	binder.setAllowedFields(allowedFields);
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
