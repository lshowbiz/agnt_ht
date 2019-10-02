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

import com.joymain.jecs.fi.model.FiProductPointTemp;
import com.joymain.jecs.fi.service.FiProductPointTempManager;
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
public class FiProductPointTempFormController extends BaseFormController {
    private FiProductPointTempManager fiProductPointTempManager = null;
    private SysUserManager sysUserManager = null;

    public SysUserManager getSysUserManager() {
		return sysUserManager;
	}
	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}
	public void setFiProductPointTempManager(FiProductPointTempManager fiProductPointTempManager) {
        this.fiProductPointTempManager = fiProductPointTempManager;
    }
    public FiProductPointTempFormController() {
        setCommandName("fiProductPointTemp");
        setCommandClass(FiProductPointTemp.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String tempId = request.getParameter("tempId");
        FiProductPointTemp fiProductPointTemp = null;

        if (!StringUtils.isEmpty(tempId)) {
            fiProductPointTemp = fiProductPointTempManager.getFiProductPointTemp(tempId);
            if(fiProductPointTemp.getStatus()==2){
				//如果已确认,则不能修改
				throw new AppException(LocaleUtil.getLocalText("error.fiProductPointTemp.has.verified"));
			}
        } else {
            fiProductPointTemp = new FiProductPointTemp();
            fiProductPointTemp.setSysUser(new SysUser());
        }
		
		Map moneyTypes=ListUtil.getListOptions(SessionLogin.getLoginUser(request).getCompanyCode(), "fibankbooktemp.moneytype");
		request.setAttribute("moneyTypes", moneyTypes);

        return fiProductPointTemp;
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
    		if(RequestUtil.saveOperationSession(request,"editFiProductPointTemp",10l)!=0){
    			return new ModelAndView("redirect:editFiProductPointTemp.html");
    		}
    	}else if("M".equals(loginSysUser.getUserType())){
    		if(RequestUtil.saveOperationSession(request,"editFiProductPointTemp",10l)!=0){
    			return new ModelAndView("redirect:editFiProductPointTemp.html");
    		}
    	}

		FiProductPointTemp fiProductPointTemp = (FiProductPointTemp) command;
		boolean isNew = (fiProductPointTemp.getTempId() == null);

		if ("deleteFiProductPointTemp".equalsIgnoreCase(request.getParameter("strAction"))) {
			fiProductPointTempManager.removeFiProductPointTemp(fiProductPointTemp.getTempId().toString());

			saveMessage(request, getText("jfiBankbookTemp.deleted"));
		} else {
			//验证用户是否存在
			SysUser sysUser=this.sysUserManager.getSysUser(fiProductPointTemp.getSysUser().getUserCode());
			if(sysUser==null || (!SessionLogin.getLoginUser(request).getCompanyCode().equals("AA") && !SessionLogin.getLoginUser(request).getCompanyCode().equals(sysUser.getCompanyCode()))){
				//如果不存在或者当前操作用户与被操作用户的公司编码不一致(操作用户为管理中心除外)
				errors.rejectValue("sysUser.userCode", "error.sysUser.not.existed");
				return showForm(request, response, errors);
			}
			if(StringUtil.isEmpty(fiProductPointTemp.getProductPointType())){
				errors.rejectValue("productPointType", "error.productPointType.not.null");
				return showForm(request, response, errors);
			}
			if ("addFiProductPointTempJJ".equalsIgnoreCase(request.getParameter("strAction"))) {
				fiProductPointTemp.setSysUser(sysUser);
				fiProductPointTemp.setCompanyCode(sysUser.getCompanyCode());
				fiProductPointTemp.setCreaterCode(SessionLogin.getOperatorUser(request).getUserCode());
				fiProductPointTemp.setCreaterName(SessionLogin.getOperatorUser(request).getUserName());
				fiProductPointTemp.setCreateTime(new Date());
				fiProductPointTemp.setStatus(1);
				long totalCount=this.fiProductPointTempManager.getCountByDate(SessionLogin.getLoginUser(request).getCompanyCode(),fiProductPointTemp.getSysUser().getUserCode(),fiProductPointTemp.getProductPointType());
				fiProductPointTemp.setSerial(totalCount+1);
			}else if("editFiProductPointTempJJ".equalsIgnoreCase(request.getParameter("strAction"))){
				fiProductPointTemp.setLastUpdaterCode(SessionLogin.getOperatorUser(request).getUserCode());
				fiProductPointTemp.setLastUpdaterName(SessionLogin.getOperatorUser(request).getUserName());
				fiProductPointTemp.setLastUpdateTime(new Date());
			}
			fiProductPointTempManager.saveFiProductPointTemp(fiProductPointTemp);

			String key = (isNew) ? "saveOrUpdate.success" : "saveOrUpdate.success";
			saveMessage(request, getText(key));
		}

		ModelAndView mv=new ModelAndView(getSuccessView());
		mv.addObject("strAction", "listfiProductPointTempsJJ");
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
    			"productPointType",
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
