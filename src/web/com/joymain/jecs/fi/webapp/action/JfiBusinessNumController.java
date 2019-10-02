package com.joymain.jecs.fi.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.JfiBusinessNum;
import com.joymain.jecs.fi.service.JfiBusinessNumManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JfiBusinessNumController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiBusinessNumController.class);
    private JfiBusinessNumManager jfiBusinessNumManager = null;

    public void setJfiBusinessNumManager(JfiBusinessNumManager jfiBusinessNumManager) {
        this.jfiBusinessNumManager = jfiBusinessNumManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JfiBusinessNum jfiBusinessNum = new JfiBusinessNum();
        // populate object with request parameters
        BeanUtils.populate(jfiBusinessNum, request.getParameterMap());

	//List jfiBusinessNums = jfiBusinessNumManager.getJfiBusinessNums(jfiBusinessNum);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jfiBusinessNumListTable",request, 20);
        List jfiBusinessNums = jfiBusinessNumManager.getJfiBusinessNumsByCrm(crm,pager);
        request.setAttribute("jfiBusinessNumListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/jfiBusinessNumList", Constants.JFIBUSINESSNUM_LIST, jfiBusinessNums);
    }
}
