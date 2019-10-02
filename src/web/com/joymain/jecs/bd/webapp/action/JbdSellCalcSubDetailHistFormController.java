package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.bd.model.JbdSellCalcSubDetailHist;
import com.joymain.jecs.bd.service.JbdSellCalcSubDetailHistManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdSellCalcSubDetailHistFormController extends BaseFormController {
    private JbdSellCalcSubDetailHistManager jbdSellCalcSubDetailHistManager = null;

    public void setJbdSellCalcSubDetailHistManager(JbdSellCalcSubDetailHistManager jbdSellCalcSubDetailHistManager) {
        this.jbdSellCalcSubDetailHistManager = jbdSellCalcSubDetailHistManager;
    }
    public JbdSellCalcSubDetailHistFormController() {
        setCommandName("jbdSellCalcSubDetailHist");
        setCommandClass(JbdSellCalcSubDetailHist.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdSellCalcSubDetailHist jbdSellCalcSubDetailHist = null;

        if (!StringUtils.isEmpty(id)) {
            jbdSellCalcSubDetailHist = jbdSellCalcSubDetailHistManager.getJbdSellCalcSubDetailHist(id);
        } else {
            jbdSellCalcSubDetailHist = new JbdSellCalcSubDetailHist();
        }

        return jbdSellCalcSubDetailHist;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdSellCalcSubDetailHist jbdSellCalcSubDetailHist = (JbdSellCalcSubDetailHist) command;
        boolean isNew = (jbdSellCalcSubDetailHist.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdSellCalcSubDetailHist".equals(strAction)  ) {
		jbdSellCalcSubDetailHistManager.removeJbdSellCalcSubDetailHist(jbdSellCalcSubDetailHist.getId().toString());
		key="jbdSellCalcSubDetailHist.delete";
	}else{
		jbdSellCalcSubDetailHistManager.saveJbdSellCalcSubDetailHist(jbdSellCalcSubDetailHist);
		key="jbdSellCalcSubDetailHist.update";
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
