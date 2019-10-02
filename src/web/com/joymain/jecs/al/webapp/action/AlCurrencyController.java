package com.joymain.jecs.al.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCurrency;
import com.joymain.jecs.al.service.AlCurrencyManager;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class AlCurrencyController implements Controller {
    private final Log log = LogFactory.getLog(AlCurrencyController.class);
    private AlCurrencyManager alCurrencyManager = null;

    public void setAlCurrencyManager(AlCurrencyManager alCurrencyManager) {
        this.alCurrencyManager = alCurrencyManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        AlCurrency alCurrency = new AlCurrency();
        // populate object with request parameters
        BeanUtils.populate(alCurrency, request.getParameterMap());

        List alCurrencys = alCurrencyManager.getAlCurrencys(alCurrency);

        return new ModelAndView("al/alCurrencyList", Constants.ALCURRENCY_LIST, alCurrencys);
    }
}
