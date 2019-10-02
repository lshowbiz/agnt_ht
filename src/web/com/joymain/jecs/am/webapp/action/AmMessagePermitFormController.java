package com.joymain.jecs.am.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.am.model.AmMessagePermit;
import com.joymain.jecs.am.service.AmMessagePermitManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class AmMessagePermitFormController extends BaseFormController {
    private AmMessagePermitManager amMessagePermitManager = null;

    public void setAmMessagePermitManager(AmMessagePermitManager amMessagePermitManager) {
        this.amMessagePermitManager = amMessagePermitManager;
    }
    public AmMessagePermitFormController() {
        setCommandName("amMessagePermit");
        setCommandClass(AmMessagePermit.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String ampNo = request.getParameter("ampNo");
        AmMessagePermit amMessagePermit = null;
        
        String msgClassNo = request.getParameter("msgClassNo");

        if (!StringUtils.isEmpty(ampNo)) {
            amMessagePermit = amMessagePermitManager.getAmMessagePermit(ampNo);
        } else {
            amMessagePermit = new AmMessagePermit();
            amMessagePermit.setMsgClassNo(msgClassNo);
        }

        return amMessagePermit;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        AmMessagePermit amMessagePermit = (AmMessagePermit) command;

        String view ="redirect:amMessagePermits.html?strAction=amMessagePermitUserContent&msgClassNo="+amMessagePermit.getMsgClassNo();
        
        
        
		String strAction = request.getParameter("strAction");
		if ("deleteAmMessagePermit".equals(strAction)  ) {
			
			amMessagePermitManager.removeAmMessagePermit(amMessagePermit.getAmpNo().toString());

		}else if("addAmMessagePermit".equals(strAction)){
			
			amMessagePermitManager.saveAmMessagePermit(amMessagePermit);

		}else if("editAmMessagePermit".equals(strAction)){
			
			amMessagePermitManager.saveAmMessagePermit(amMessagePermit);

		}
		this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
        return new ModelAndView(view);
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
