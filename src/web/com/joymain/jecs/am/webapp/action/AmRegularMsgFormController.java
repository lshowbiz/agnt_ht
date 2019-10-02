package com.joymain.jecs.am.webapp.action;

import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.am.model.AmRegularMsg;
import com.joymain.jecs.am.service.AmRegularMsgManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class AmRegularMsgFormController extends BaseFormController {
    private AmRegularMsgManager amRegularMsgManager = null;

    public void setAmRegularMsgManager(AmRegularMsgManager amRegularMsgManager) {
        this.amRegularMsgManager = amRegularMsgManager;
    }
    public AmRegularMsgFormController() {
        setCommandName("amRegularMsg");
        setCommandClass(AmRegularMsg.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String uniNo = request.getParameter("uniNo");
        AmRegularMsg amRegularMsg = null;

        if (!StringUtils.isEmpty(uniNo)) {
            amRegularMsg = amRegularMsgManager.getAmRegularMsg(uniNo);
        } else {
            amRegularMsg = new AmRegularMsg();
        }

        return amRegularMsg;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        AmRegularMsg amRegularMsg = (AmRegularMsg) command;
        boolean isNew = (amRegularMsg.getUniNo() == null);
        Locale locale = request.getLocale();
        
        String strAction = request.getParameter("strAction");
        String returnView = request.getParameter("returnView");
        String view = "redirect:amRegularMsgs.html?strAction=regularMsgSelect";
        String key = null;
        
        if ("deleteAmRegularMsg".equals(request.getParameter("strAction"))  ) {
        	key ="amregularmsg.deleted";
            amRegularMsgManager.removeAmRegularMsg(amRegularMsg.getUniNo().toString());

           // saveMessage(request, getText("amRegularMsg.deleted", locale));
         //  saveMessage(request, getText(SessionLogin.getLoginUser(request).getCharacterCoding(),"amRegularMsg.deleted"));
        } else if("addAmRegularMsg".equals(request.getParameter("strAction"))) {//新增常用语
        	key ="amregularmsg.added";
            
            SysUser sessionLogin = SessionLogin.getLoginUser(request);
            amRegularMsg.setUniNo(null);    		
    		amRegularMsg.setAddNo(sessionLogin.getUserCode());
    		amRegularMsg.setAddTime(new Date());
    		amRegularMsg.setModifyTime(new Date());
    		amRegularMsg.setCompanyCode(sessionLogin.getCompanyCode());
            
            amRegularMsgManager.saveAmRegularMsg(amRegularMsg);
           // String key = (isNew) ? "amRegularMsg.added" : "amRegularMsg.updated";
           // saveMessage(request, getText(key, locale));
					//	saveMessage(request, getText(SessionLogin.getLoginUser(request).getCharacterCoding(),key));
        }else if("editAmRegularMsg".equals(request.getParameter("strAction"))){//编辑常用语
        	key ="amregularmsg.edited";
        	
        	amRegularMsg.setModifyTime(new Date());
        	amRegularMsgManager.saveAmRegularMsg(amRegularMsg);
        }
        
        saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(),key));
        return new ModelAndView(view);
    }
}
