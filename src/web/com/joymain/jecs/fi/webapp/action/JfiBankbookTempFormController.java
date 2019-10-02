package com.joymain.jecs.fi.webapp.action;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.fi.model.JfiBankbookTemp;
import com.joymain.jecs.fi.service.JfiBankbookTempManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 新增存折条目表单
 * @author Alvin
 *
 */
@SuppressWarnings("rawtypes")
public class JfiBankbookTempFormController extends BaseFormController {
    private JfiBankbookTempManager jfiBankbookTempManager = null;
    private SysUserManager sysUserManager = null;

    public SysUserManager getSysUserManager() {
		return sysUserManager;
	}
	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}
	public void setJfiBankbookTempManager(JfiBankbookTempManager jfiBankbookTempManager) {
        this.jfiBankbookTempManager = jfiBankbookTempManager;
    }
    public JfiBankbookTempFormController() {
        setCommandName("jfiBankbookTemp");
        setCommandClass(JfiBankbookTemp.class);
    }

    //页面初始化
	protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
    	
    	//存折ID 
        String tempId = request.getParameter("tempId");
        JfiBankbookTemp jfiBankbookTemp = null;

        //判断是新增还是修改过来的
        if (!StringUtils.isEmpty(tempId)) {
            jfiBankbookTemp = jfiBankbookTempManager.getJfiBankbookTemp(tempId);
            if(jfiBankbookTemp.getStatus()==2){
				//如果已确认,则不能修改
				throw new AppException(LocaleUtil.getLocalText("error.fiBankbookTemp.has.verified"));
			}
        } else {
        	//NEW
            jfiBankbookTemp = new JfiBankbookTemp();
            jfiBankbookTemp.setSysUser(new SysUser());
        }
		//资金类别
		Map moneyTypes=ListUtil.getListOptions(SessionLogin.getLoginUser(request).getCompanyCode(), "fibankbooktemp.moneytype");
		request.setAttribute("moneyTypes", moneyTypes);

        return jfiBankbookTemp;
    }

    //提交
    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        //获取SESSION用户
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        
        String description =request.getParameter("description");
       //获取页面输入
		JfiBankbookTemp jfiBankbookTemp = (JfiBankbookTemp) command;
		jfiBankbookTemp.setDescription(description);
		String billpospayNum=request.getParameter("billpospayNum");
		boolean isNew = (jfiBankbookTemp.getTempId() == null);
		
		//判断用户类型
		if("C".equals(loginSysUser.getUserType())){
    		if(RequestUtil.saveOperationSession(request,"addFiBankbookTemp",10l)!=0){
    			return new ModelAndView("redirect:jfiBankbookTemps.html");
    		}
    	}else if("M".equals(loginSysUser.getUserType())){
    		if(RequestUtil.saveOperationSession(request,"addFiBankbookTemp",10l)!=0){
    			return new ModelAndView("redirect:jfiBankbookTemps.html");
    		}
    	}

		//如果是删除
		if ("deleteFiBankbookTemp".equalsIgnoreCase(request.getParameter("strAction"))) {
			jfiBankbookTempManager.removeJfiBankbookTemp(jfiBankbookTemp.getTempId().toString());

			saveMessage(request, getText("fiBankbookTemp.deleted"));
		} else {
			//否则就是新增或者修改
			
			//验证用户是否存在
			SysUser sysUser=this.sysUserManager.getSysUser(jfiBankbookTemp.getSysUser().getUserCode());
			if(sysUser==null || (!SessionLogin.getLoginUser(request).getCompanyCode().equals("AA") && !SessionLogin.getLoginUser(request).getCompanyCode().equals(sysUser.getCompanyCode()))){
				//如果不存在或者当前操作用户与被操作用户的公司编码不一致(操作用户为管理中心除外)
				errors.rejectValue("sysUser.userCode", "error.sysUser.not.existed");
				return showForm(request, response, errors);
			}
			if ("addFiBankbookTemp".equalsIgnoreCase(request.getParameter("strAction"))) {
				jfiBankbookTemp.setSysUser(sysUser);
				jfiBankbookTemp.setCompanyCode(sysUser.getCompanyCode());
				jfiBankbookTemp.setCreaterCode(SessionLogin.getOperatorUser(request).getUserCode());
				jfiBankbookTemp.setCreaterName(SessionLogin.getOperatorUser(request).getUserName());
				jfiBankbookTemp.setCreateTime(new Date());
				jfiBankbookTemp.setStatus(1);
				long totalCount=this.jfiBankbookTempManager.getCountByDate(SessionLogin.getLoginUser(request).getCompanyCode(),jfiBankbookTemp.getSysUser().getUserCode());
				jfiBankbookTemp.setSerial(totalCount+1);
			}else if("editFiBankbookTemp".equalsIgnoreCase(request.getParameter("strAction"))){
				jfiBankbookTemp.setLastUpdaterCode(SessionLogin.getOperatorUser(request).getUserCode());
				jfiBankbookTemp.setLastUpdaterName(SessionLogin.getOperatorUser(request).getUserName());
				jfiBankbookTemp.setLastUpdateTime(new Date());
			}
			
			jfiBankbookTemp.setBillpospayNum(billpospayNum);
			jfiBankbookTempManager.saveJfiBankbookTemp(jfiBankbookTemp);

			String key = (isNew) ? "fiBankbookTemp.added" : "fiBankbookTemp.updated";
			saveMessage(request, getText(key));
		}

		ModelAndView mv=new ModelAndView(getSuccessView());
		mv.addObject("strAction", "listfiBankbookTemps");
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
