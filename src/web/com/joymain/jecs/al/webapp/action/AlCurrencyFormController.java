package com.joymain.jecs.al.webapp.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.model.AlCurrency;
import com.joymain.jecs.al.service.AlCurrencyManager;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class AlCurrencyFormController extends BaseFormController {
    private AlCurrencyManager alCurrencyManager = null;

    public void setAlCurrencyManager(AlCurrencyManager alCurrencyManager) {
        this.alCurrencyManager = alCurrencyManager;
    }
    public AlCurrencyFormController() {
        setCommandName("alCurrency");
        setCommandClass(AlCurrency.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String currencyId = request.getParameter("currencyId");
        AlCurrency alCurrency = null;

        if (!StringUtils.isEmpty(currencyId)) {
            alCurrency = alCurrencyManager.getAlCurrency(currencyId);
        } else {
            alCurrency = new AlCurrency();
        }

        return alCurrency;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        AlCurrency alCurrency = (AlCurrency) command;
        boolean isNew = (alCurrency.getCurrencyId() == null);
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            alCurrencyManager.removeAlCurrency(alCurrency.getCurrencyId().toString());

            saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(),"alCurrency.deleted"));
        } else {
            alCurrencyManager.saveAlCurrency(alCurrency);

            String key = (isNew) ? "alCurrency.added" : "alCurrency.updated";
            saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(),key));

            if (!isNew) {
                return new ModelAndView("redirect:editAlCurrency.html", "currencyId", alCurrency.getCurrencyId());
            }
        }

        return new ModelAndView(getSuccessView());
    }
}
