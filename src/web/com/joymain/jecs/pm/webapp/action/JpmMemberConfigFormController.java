package com.joymain.jecs.pm.webapp.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.pm.model.JpmMemberConfig;
import com.joymain.jecs.pm.service.JpmConfigDetailedManager;
import com.joymain.jecs.pm.service.JpmMemberConfigManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JpmMemberConfigFormController extends BaseFormController {
    private JpmMemberConfigManager jpmMemberConfigManager = null;
    
    private JpmConfigDetailedManager jpmConfigDetailedManager;

    public void setJpmConfigDetailedManager(JpmConfigDetailedManager jpmConfigDetailedManager)
    {
        this.jpmConfigDetailedManager = jpmConfigDetailedManager;
    }
    public void setJpmMemberConfigManager(JpmMemberConfigManager jpmMemberConfigManager) {
        this.jpmMemberConfigManager = jpmMemberConfigManager;
    }
    public JpmMemberConfigFormController() {
        setCommandName("jpmMemberConfig");
        setCommandClass(JpmMemberConfig.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String configNo = request.getParameter("configNo");
        JpmMemberConfig jpmMemberConfig = null;

        if (!StringUtils.isEmpty(configNo)) {
            jpmMemberConfig = jpmMemberConfigManager.getJpmMemberConfig(configNo);
        } else {
//            jpmMemberConfig = new JpmM2emberConfig();
        }

        return jpmMemberConfig;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        //        JpmMemberConfig jpmMemberConfig = (JpmMemberConfig) command;
        //        boolean isNew = (jpmMemberConfig.getConfigNo() == null);
        //        Locale locale = request.getLocale();
        //	String key=null;
        //	String strAction = request.getParameter("strAction");
        //	if ("deleteJpmMemberConfig".equals(strAction)  ) {
        //		jpmMemberConfigManager.removeJpmMemberConfig(jpmMemberConfig.getConfigNo().toString());
        //		key="jpmMemberConfig.delete";
        //	}else{
        //		jpmMemberConfigManager.saveJpmMemberConfig(jpmMemberConfig);
        //		key="jpmMemberConfig.update";
        //	}
        //用户信息
        SysUser jsysUser = (SysUser) SessionLogin.getLoginUser(request);
        String molId = request.getParameter("molId");
        String productNo = request.getParameter("productNo");
        jpmConfigDetailedManager.addJpmConfigDetailed(request,jsysUser);
        Map<String,String> map = new HashMap<String,String>();
        request.setAttribute("model", new HashMap<String, String>(map));
        return new ModelAndView("redirect:/jpoWineMemberOrders/orderConfigList?molId="+molId+"&productNo="+productNo);
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
