package com.joymain.jecs.po.webapp.action;

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
import com.joymain.jecs.po.model.JpoMemberOrderFee;
import com.joymain.jecs.po.service.JpoMemberOrderFeeManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 物流费列表
 * @author Alvin
 *
 */
public class JpoMemberOrderFeeController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoMemberOrderFeeController.class);
    private JpoMemberOrderFeeManager jpoMemberOrderFeeManager = null;

    public void setJpoMemberOrderFeeManager(JpoMemberOrderFeeManager jpoMemberOrderFeeManager) {
        this.jpoMemberOrderFeeManager = jpoMemberOrderFeeManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JpoMemberOrderFee jpoMemberOrderFee = new JpoMemberOrderFee();
        // populate object with request parameters
        BeanUtils.populate(jpoMemberOrderFee, request.getParameterMap());

	//List jpoMemberOrderFees = jpoMemberOrderFeeManager.getJpoMemberOrderFees(jpoMemberOrderFee);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jpoMemberOrderFeeListTable",request, 20);
        List jpoMemberOrderFees = jpoMemberOrderFeeManager.getJpoMemberOrderFeesByCrm(crm,pager);
        request.setAttribute("jpoMemberOrderFeeListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("po/jpoMemberOrderFeeList", Constants.JPOMEMBERORDERFEE_LIST, jpoMemberOrderFees);
    }
}
