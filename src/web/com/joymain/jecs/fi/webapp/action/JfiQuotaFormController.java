package com.joymain.jecs.fi.webapp.action;

import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.fi.model.JfiQuota;
import com.joymain.jecs.fi.service.JfiQuotaManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JfiQuotaFormController extends BaseFormController {
    private JfiQuotaManager jfiQuotaManager = null;

    public void setJfiQuotaManager(JfiQuotaManager jfiQuotaManager) {
        this.jfiQuotaManager = jfiQuotaManager;
    }
    public JfiQuotaFormController() {
        setCommandName("jfiQuota");
        setCommandClass(JfiQuota.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String quotaId = request.getParameter("quotaId");
        JfiQuota jfiQuota = null;

        if (!StringUtils.isEmpty(quotaId)) {
            jfiQuota = jfiQuotaManager.getJfiQuota(quotaId);
        } else {
            jfiQuota = new JfiQuota();
        }

        return jfiQuota;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        SysUser loginUser = SessionLogin.getLoginUser(request);
        JfiQuota jfiQuota = (JfiQuota) command;
        boolean isNew = (jfiQuota.getQuotaId() == null);
        Locale locale = request.getLocale();
		String key=null;
		String strAction = request.getParameter("strAction");
		if ("deleteJfiQuota".equals(strAction)  ) {
			jfiQuotaManager.removeJfiQuota(jfiQuota.getQuotaId().toString());
			key="jfiQuota.delete";
		}else if("addJfiQuota".equals(strAction)){
			jfiQuotaManager.saveJfiQuota(jfiQuota);
			jfiQuota.setOperateName(loginUser.getUserCode());
			jfiQuota.setOperateTime(new Date());//新增数据
			key="bdSendRegister.operaterSuccess";
		}else if("editJfiQuota".equals(strAction)){
			String oldMaxMoney = request.getParameter("oldMaxMoney");
			System.out.println("oldMaxMoney:"+oldMaxMoney);
			if(oldMaxMoney.equals(String.valueOf(jfiQuota.getMaxMoney()))){
				jfiQuota.setOperateName(loginUser.getUserCode());
				jfiQuota.setOperateTime(new Date());
				jfiQuotaManager.saveJfiQuota(jfiQuota);
			}else{
				//设置为禁用
				jfiQuota.setStatus("1");
				jfiQuota.setMaxMoney(Long.parseLong(oldMaxMoney));//原始金额
				jfiQuotaManager.saveJfiQuota(jfiQuota);
				
				//新生成一条记录
				JfiQuota jfiQuotaT = new JfiQuota();
				jfiQuotaT.setAccountId(jfiQuota.getAccountId());
				jfiQuotaT.setMaxMoney(jfiQuota.getOldMaxMoney());
				jfiQuotaT.setOperateName(loginUser.getUserCode());
				jfiQuotaT.setOperateTime(new Date());
				jfiQuotaT.setRemark(jfiQuota.getRemark());
				jfiQuotaT.setStatus("0");
				jfiQuotaT.setValidityPeriod(jfiQuota.getValidityPeriod());
				jfiQuotaManager.saveJfiQuota(jfiQuotaT);
			}
			key="bdSendRegister.operaterSuccess";
		}
		
		saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(), key)); 
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
