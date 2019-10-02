package com.joymain.jecs.po.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.po.service.JpoMemberOrderListManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JpoMemberOrderListFormController extends BaseFormController {
    private JpoMemberOrderListManager jpoMemberOrderListManager = null;

    public void setJpoMemberOrderListManager(JpoMemberOrderListManager jpoMemberOrderListManager) {
        this.jpoMemberOrderListManager = jpoMemberOrderListManager;
    }
    public JpoMemberOrderListFormController() {
        setCommandName("jpoMemberOrderList");
        setCommandClass(JpoMemberOrderList.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String molId = request.getParameter("molId");
        JpoMemberOrderList jpoMemberOrderList = null;

        if (!StringUtils.isEmpty(molId)) {
            jpoMemberOrderList = jpoMemberOrderListManager.getJpoMemberOrderList(molId);
        } else {
            jpoMemberOrderList = new JpoMemberOrderList();
        }

        return jpoMemberOrderList;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) command;
        boolean isNew = (jpoMemberOrderList.getMolId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJpoMemberOrderList".equals(strAction)  ) {
		jpoMemberOrderListManager.removeJpoMemberOrderList(jpoMemberOrderList.getMolId().toString());
		key="jpoMemberOrderList.delete";
	}else{
		jpoMemberOrderListManager.saveJpoMemberOrderList(jpoMemberOrderList);
		key="jpoMemberOrderList.update";
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
