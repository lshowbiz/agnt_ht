package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.bd.model.JbdSellCalcSubHist;
import com.joymain.jecs.bd.service.JbdSellCalcSubHistManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdSellCalcSubHistFormController extends BaseFormController {
    private JbdSellCalcSubHistManager jbdSellCalcSubHistManager = null;

    public void setJbdSellCalcSubHistManager(JbdSellCalcSubHistManager jbdSellCalcSubHistManager) {
        this.jbdSellCalcSubHistManager = jbdSellCalcSubHistManager;
    }
    public JbdSellCalcSubHistFormController() {
        setCommandName("jbdSellCalcSubHist");
        setCommandClass(JbdSellCalcSubHist.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdSellCalcSubHist jbdSellCalcSubHist = null;

        if (!StringUtils.isEmpty(id)) {
            jbdSellCalcSubHist = jbdSellCalcSubHistManager.getJbdSellCalcSubHist(id);
        } else {
            jbdSellCalcSubHist = new JbdSellCalcSubHist();
        }

        return jbdSellCalcSubHist;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdSellCalcSubHist jbdSellCalcSubHist = (JbdSellCalcSubHist) command;
        boolean isNew = (jbdSellCalcSubHist.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdSellCalcSubHist".equals(strAction)  ) {
		jbdSellCalcSubHistManager.removeJbdSellCalcSubHist(jbdSellCalcSubHist.getId().toString());
		key="jbdSellCalcSubHist.delete";
	}else{
		jbdSellCalcSubHistManager.saveJbdSellCalcSubHist(jbdSellCalcSubHist);
		key="jbdSellCalcSubHist.update";
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
