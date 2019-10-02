package com.joymain.jecs.fi.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.JfiPayBank;
import com.joymain.jecs.fi.service.JfiPayBankManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 收款银行列表
 * @author Alvin
 *
 */
public class JfiPayBankController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiPayBankController.class);
    private JfiPayBankManager jfiPayBankManager = null;

    public void setJfiPayBankManager(JfiPayBankManager jfiPayBankManager) {
        this.jfiPayBankManager = jfiPayBankManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

		CommonRecord crm = RequestUtil.toCommonRecord(request);
		Pager pager = new Pager("jfiPayBankListTable", request, 20);
		
		if(!"AA".equals(SessionLogin.getLoginUser(request).getCompanyCode())){
			crm.addField("companyCode", SessionLogin.getLoginUser(request).getCompanyCode());
		}
		List jfiPayBanks = jfiPayBankManager.getJfiPayBanksByCrm(crm, pager);
		
		//根据数据重新设置分页数据
		request.setAttribute("jfiPayBankListTable_totalRows", pager.getTotalObjects());
        return new ModelAndView("fi/jfiPayBankList", Constants.JFIPAYBANK_LIST, jfiPayBanks);
    }
}
