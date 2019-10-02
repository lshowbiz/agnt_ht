package com.joymain.jecs.po.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.po.model.JpoMemberOrderFee;
import com.joymain.jecs.po.service.JpoMemberOrderFeeManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
/**
 * 物流费表单
 * @author Alvin
 *
 */
public class JpoMemberOrderFeeFormController extends BaseFormController {
    private JpoMemberOrderFeeManager jpoMemberOrderFeeManager = null;

    public void setJpoMemberOrderFeeManager(JpoMemberOrderFeeManager jpoMemberOrderFeeManager) {
        this.jpoMemberOrderFeeManager = jpoMemberOrderFeeManager;
    }
    public JpoMemberOrderFeeFormController() {
        setCommandName("jpoMemberOrderFee");
        setCommandClass(JpoMemberOrderFee.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String mofId = request.getParameter("mofId");
        JpoMemberOrderFee jpoMemberOrderFee = null;

        if (!StringUtils.isEmpty(mofId)) {
            jpoMemberOrderFee = jpoMemberOrderFeeManager.getJpoMemberOrderFee(mofId);
        } else {
            jpoMemberOrderFee = new JpoMemberOrderFee();
        }

        return jpoMemberOrderFee;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JpoMemberOrderFee jpoMemberOrderFee = (JpoMemberOrderFee) command;
        boolean isNew = (jpoMemberOrderFee.getMofId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJpoMemberOrderFee".equals(strAction)  ) {
		jpoMemberOrderFeeManager.removeJpoMemberOrderFee(jpoMemberOrderFee.getMofId().toString());
		key="jpoMemberOrderFee.delete";
	}else{
		jpoMemberOrderFeeManager.saveJpoMemberOrderFee(jpoMemberOrderFee);
		key="jpoMemberOrderFee.update";
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
