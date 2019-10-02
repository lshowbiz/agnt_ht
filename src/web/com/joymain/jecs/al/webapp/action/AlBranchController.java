package com.joymain.jecs.al.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlBranch;
import com.joymain.jecs.al.service.AlBranchManager;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class AlBranchController implements Controller {
    private final Log log = LogFactory.getLog(AlBranchController.class);
    private AlBranchManager alBranchManager = null;

    public void setAlBranchManager(AlBranchManager alBranchManager) {
        this.alBranchManager = alBranchManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        AlBranch alBranch = new AlBranch();
        // populate object with request parameters
        BeanUtils.populate(alBranch, request.getParameterMap());

        List alBranchs = alBranchManager.getAlBranchs(alBranch);

        return new ModelAndView("al/alBranchList", Constants.ALBRANCH_LIST, alBranchs);
    }
}
