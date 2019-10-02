package com.joymain.jecs.fi.webapp.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.fi.model.FiBankbookTemp;
import com.joymain.jecs.fi.service.FiBankbookTempManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 新增存折条目表单
 * @author Alvin*
 *
 */
public class FiBankbookTempFormController extends BaseFormController {
    private FiBankbookTempManager fiBankbookTempManager = null;
    private SysUserManager sysUserManager = null;

    public SysUserManager getSysUserManager() {
		return sysUserManager;
	}
	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}
	public void setFiBankbookTempManager(FiBankbookTempManager fiBankbookTempManager) {
        this.fiBankbookTempManager = fiBankbookTempManager;
    }
    public FiBankbookTempFormController() {
        setCommandName("fiBankbookTemp");
        setCommandClass(FiBankbookTemp.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String tempId = request.getParameter("tempId");
        FiBankbookTemp fiBankbookTemp = null;

        if (!StringUtils.isEmpty(tempId)) {
            fiBankbookTemp = fiBankbookTempManager.getFiBankbookTemp(tempId);
            if(fiBankbookTemp.getStatus()==2){
				//如果已确认,则不能修改
				throw new AppException(LocaleUtil.getLocalText("error.fiBankbookTemp.has.verified"));
			}
        } else {
            fiBankbookTemp = new FiBankbookTemp();
            fiBankbookTemp.setSysUser(new SysUser());
        }
		
		Map moneyTypes=ListUtil.getListOptions(SessionLogin.getLoginUser(request).getCompanyCode(), "fibankbooktemp.moneytype");
		request.setAttribute("moneyTypes", moneyTypes);

        return fiBankbookTemp;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
		if("C".equals(loginSysUser.getUserType())){
    		if(RequestUtil.saveOperationSession(request,"editFiBankbookTemp",10l)!=0){
    			return new ModelAndView("redirect:editFiBankbookTemp.html");
    		}
    	}else if("M".equals(loginSysUser.getUserType())){
    		if(RequestUtil.saveOperationSession(request,"editFiBankbookTemp",10l)!=0){
    			return new ModelAndView("redirect:editFiBankbookTemp.html");
    		}
    	}

		FiBankbookTemp fiBankbookTemp = (FiBankbookTemp) command;
		boolean isNew = (fiBankbookTemp.getTempId() == null);

		if ("deleteFiBankbookTemp".equalsIgnoreCase(request.getParameter("strAction"))) {
			fiBankbookTempManager.removeFiBankbookTemp(fiBankbookTemp.getTempId().toString());

			saveMessage(request, getText("fiBankbookTemp.deleted"));
		} else {
			//验证用户是否存在
			SysUser sysUser=this.sysUserManager.getSysUser(fiBankbookTemp.getSysUser().getUserCode());
			if(sysUser==null || (!SessionLogin.getLoginUser(request).getCompanyCode().equals("AA") && !SessionLogin.getLoginUser(request).getCompanyCode().equals(sysUser.getCompanyCode()))){
				//如果不存在或者当前操作用户与被操作用户的公司编码不一致(操作用户为管理中心除外)
				errors.rejectValue("sysUser.userCode", "error.sysUser.not.existed");
				return showForm(request, response, errors);
			}
			if(StringUtil.isEmpty(fiBankbookTemp.getBankbookType())){
				errors.rejectValue("bankbookType", "error.bankbookType.not.null");
				return showForm(request, response, errors);
			}
			if ("addFiBankbookTempJJ".equalsIgnoreCase(request.getParameter("strAction"))) {
				fiBankbookTemp.setSysUser(sysUser);
				fiBankbookTemp.setCompanyCode(sysUser.getCompanyCode());
				fiBankbookTemp.setCreaterCode(SessionLogin.getOperatorUser(request).getUserCode());
				fiBankbookTemp.setCreaterName(SessionLogin.getOperatorUser(request).getUserName());
				fiBankbookTemp.setCreateTime(new Date());
				fiBankbookTemp.setStatus(1);
				long totalCount=this.fiBankbookTempManager.getCountByDate(SessionLogin.getLoginUser(request).getCompanyCode(),fiBankbookTemp.getSysUser().getUserCode(),fiBankbookTemp.getBankbookType());
				fiBankbookTemp.setSerial(totalCount+1);
			}else if("editFiBankbookTempJJ".equalsIgnoreCase(request.getParameter("strAction"))){
				fiBankbookTemp.setLastUpdaterCode(SessionLogin.getOperatorUser(request).getUserCode());
				fiBankbookTemp.setLastUpdaterName(SessionLogin.getOperatorUser(request).getUserName());
				fiBankbookTemp.setLastUpdateTime(new Date());
			}
			fiBankbookTempManager.saveFiBankbookTemp(fiBankbookTemp);

			String key = (isNew) ? "fiBankbookTemp.added" : "fiBankbookTemp.updated";
			saveMessage(request, getText(key));
		}

		ModelAndView mv=new ModelAndView(getSuccessView());
		mv.addObject("strAction", "listfiBankbookTempsJJ");
		mv.addObject("needReload", "1");
		return mv;
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
    	String[] allowedFields = {
    			"sysUser.userCode",
    			"dealType",
    			"moneyType",
    			"money",
    			"bankbookType",
    			"notes",
    			"description"
    			};
    	binder.setAllowedFields(allowedFields);
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
