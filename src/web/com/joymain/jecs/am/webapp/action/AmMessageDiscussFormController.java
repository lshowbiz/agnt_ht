package com.joymain.jecs.am.webapp.action;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.am.model.AmMessage;
import com.joymain.jecs.am.service.AmMessageManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;


public class AmMessageDiscussFormController extends BaseFormController {
    private AmMessageManager amMessageManager = null;
    private SysUserManager sysUserManager = null;
    private JmiMemberManager jmiMemberManager;
  
    public void setAmMessageManager(AmMessageManager amMessageManager) {
        this.amMessageManager = amMessageManager;
    }
    public AmMessageDiscussFormController() {
        setCommandName("amMessage");
        setCommandClass(AmMessage.class);
    }
    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
    	String uniNo=request.getParameter("uniNo");
    	AmMessage amMessage = null;
    	if (StringUtil.isEmpty(uniNo)) {
        	amMessage = new AmMessage();
    	}else{
    		amMessage = amMessageManager.getAmMessage(uniNo);
    	}
        
        return amMessage;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        AmMessage amMessage = (AmMessage) command;
        
    	amMessageManager.saveAmMessage(amMessage);
    	this.saveMessage(request, getText("amMessage.discuss.success"));
        
      
        return showForm(request, response, errors);
    }
}
