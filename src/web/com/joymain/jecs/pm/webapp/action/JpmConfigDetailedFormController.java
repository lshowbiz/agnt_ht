package com.joymain.jecs.pm.webapp.action;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.pm.model.JpmConfigDetailed;
import com.joymain.jecs.pm.service.JpmConfigDetailedManager;
import com.joymain.jecs.sys.model.SysUser;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JpmConfigDetailedFormController extends BaseFormController {
    private JpmConfigDetailedManager jpmConfigDetailedManager = null;

    public void setJpmConfigDetailedManager(JpmConfigDetailedManager jpmConfigDetailedManager) {
        this.jpmConfigDetailedManager = jpmConfigDetailedManager;
    }
    public JpmConfigDetailedFormController() {
        setCommandName("jpmConfigDetailed");
        setCommandClass(JpmConfigDetailed.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String configdetailedNo = request.getParameter("configdetailedNo");
        JpmConfigDetailed jpmConfigDetailed = null;

        if (!StringUtils.isEmpty(configdetailedNo)) {
            jpmConfigDetailed = jpmConfigDetailedManager.getJpmConfigDetailed(configdetailedNo);
        } else {
            jpmConfigDetailed = new JpmConfigDetailed();
        }

        return jpmConfigDetailed;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        SysUser jsysUser = (SysUser) SessionLogin.getLoginUser(request);
        String molId = request.getParameter("molId");
        String productNo = request.getParameter("productNo");
        jpmConfigDetailedManager.addJpmConfigDetailed(request,jsysUser);
        Map<String,String> map = new HashMap<String,String>();
        request.setAttribute("model", new HashMap<String, String>(map));
        return  new ModelAndView("redirect:/jpmWineMemberOrders.html?strAction=orderConfigList&molId="+molId+"&productNo="+productNo);

//        return new ModelAndView(getSuccessView());
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
