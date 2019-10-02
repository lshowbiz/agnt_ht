package com.joymain.jecs.bd.webapp.action;

import java.util.List;
import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.bd.model.JbdTravelPoint;
import com.joymain.jecs.bd.service.JbdTravelPointManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdTravelPointFormController extends BaseFormController {
    private JbdTravelPointManager jbdTravelPointManager = null;

    public void setJbdTravelPointManager(JbdTravelPointManager jbdTravelPointManager) {
        this.jbdTravelPointManager = jbdTravelPointManager;
    }
    public JbdTravelPointFormController() {
        setCommandName("jbdTravelPoint");
        setCommandClass(JbdTravelPoint.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String userCode = request.getParameter("userCode");
        JbdTravelPoint jbdTravelPoint = null;

    	
    	
        if (!StringUtils.isEmpty(userCode)) {
            jbdTravelPoint = jbdTravelPointManager.getJbdTravelPoint(userCode);
        } else {
            jbdTravelPoint = new JbdTravelPoint();
        }

        
        
        
        return jbdTravelPoint;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

    	SysUser defSysUser=SessionLogin.getLoginUser(request);
    	
        JbdTravelPoint jbdTravelPoint = (JbdTravelPoint) command;
        
        
        
        
        

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
