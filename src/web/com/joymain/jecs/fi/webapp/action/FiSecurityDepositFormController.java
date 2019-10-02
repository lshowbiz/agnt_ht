package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.fi.model.FiSecurityDeposit;
import com.joymain.jecs.fi.service.FiSecurityDepositManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class FiSecurityDepositFormController extends BaseFormController {
    private FiSecurityDepositManager fiSecurityDepositManager = null;
    private SysUserManager sysUserManager = null;

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

    public void setFiSecurityDepositManager(FiSecurityDepositManager fiSecurityDepositManager) {
        this.fiSecurityDepositManager = fiSecurityDepositManager;
    }
    public FiSecurityDepositFormController() {
        setCommandName("fiSecurityDeposit");
        setCommandClass(FiSecurityDeposit.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String fsdId = request.getParameter("fsdId");
        FiSecurityDeposit fiSecurityDeposit = null;

        if (!StringUtils.isEmpty(fsdId)) {
            fiSecurityDeposit = fiSecurityDepositManager.getFiSecurityDeposit(fsdId);
        } else {
            fiSecurityDeposit = new FiSecurityDeposit();
        }

        return fiSecurityDeposit;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        FiSecurityDeposit fiSecurityDeposit = (FiSecurityDeposit) command;

		String strAction = request.getParameter("strAction");
		
		if ("deleteFiSecurityDeposit".equals(strAction)  ) {
			
			fiSecurityDepositManager.removeFiSecurityDeposit(fiSecurityDeposit.getFsdId().toString());
			//key="fiSecurityDeposit.delete";
		}else{
		
			//验证用户编码是否存在
			SysUser destinationUser=this.sysUserManager.getSysUser(fiSecurityDeposit.getUserCode());
			if(destinationUser==null || !destinationUser.getUserType().equals("M")){
				
				//如果不存在
				MessageUtil.saveLocaleMessage(request, LocaleUtil.getLocalText("error.sysUser.not.existed"));
				return showForm(request, response, errors);	
			}
			
			//新增
			if(fiSecurityDeposit.getFsdId()==null){
				
				//验证是否重复
				Integer sCount=fiSecurityDepositManager.getCountOfSecurityDepositByUserCode(fiSecurityDeposit.getUserCode());
				if(sCount>0){
					
					//如果重复
					MessageUtil.saveLocaleMessage(request, "该会员的保证金帐户已经存在!");
					return showForm(request, response, errors);	
				}
				
				fiSecurityDeposit.setBalance(new BigDecimal(0));
			}
			
			//保存
			fiSecurityDepositManager.saveFiSecurityDeposit(fiSecurityDeposit);
		}

        return new ModelAndView(getSuccessView());
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
