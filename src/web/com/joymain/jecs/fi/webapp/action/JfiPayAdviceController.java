package com.joymain.jecs.fi.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.JfiPayAdvice;
import com.joymain.jecs.fi.service.JfiPayAdviceManager;
import com.joymain.jecs.fi.service.JfiPayBankManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 付款通知列表
 * @author Alvin
 *
 */
public class JfiPayAdviceController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiPayAdviceController.class);
    private JfiPayAdviceManager jfiPayAdviceManager = null;
    private JfiPayBankManager jfiPayBankManager = null;
    private JmiMemberManager jmiMemberManager = null;

    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	public void setJfiPayBankManager(JfiPayBankManager jfiPayBankManager) {
		this.jfiPayBankManager = jfiPayBankManager;
	}

	public void setJfiPayAdviceManager(JfiPayAdviceManager jfiPayAdviceManager) {
        this.jfiPayAdviceManager = jfiPayAdviceManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

		if ("doNotDealPayAdvice".equals(request.getParameter("strAction"))) {
			//转为不处理
			String[] adviceCodes = request.getParameter("strAdviceCodes").split(",");
			List<JfiPayAdvice> fiPayAdvices = new ArrayList<JfiPayAdvice>();
			for (int i = 0; i < adviceCodes.length; i++) {
				if (!StringUtils.isEmpty(adviceCodes[i])) {
					JfiPayAdvice fiPayAdvice = this.jfiPayAdviceManager.getJfiPayAdvice(adviceCodes[i]);
					if (fiPayAdvice.getStatus() != 2 && fiPayAdvice.getStatus() != 4) {
						fiPayAdvice.setStatus(3);
						fiPayAdvices.add(fiPayAdvice);
					}
				}
			}
			this.jfiPayAdviceManager.saveJfiPayAdvices(fiPayAdvices);
			saveMessage(request, LocaleUtil.getLocalText("fiPayAdvice.doNotDealed"));
			ModelAndView mv = new ModelAndView("redirect:jfiPayAdvices.html");
			mv.addObject("strAction", "listFiPayAdvices");
			mv.addObject("needReload", "1");
			return mv;
		} else if ("notVerifiedPayAdvice".equals(request.getParameter("strAction"))) {
			//转为未核实
			String[] adviceCodes = request.getParameter("strAdviceCodes").split(",");
			List<JfiPayAdvice> fiPayAdvices = new ArrayList<JfiPayAdvice>();
			for (int i = 0; i < adviceCodes.length; i++) {
				if (!StringUtils.isEmpty(adviceCodes[i])) {
					JfiPayAdvice fiPayAdvice = this.jfiPayAdviceManager.getJfiPayAdvice(adviceCodes[i]);
					if (fiPayAdvice.getStatus() != 2 && fiPayAdvice.getStatus() != 4) {
						fiPayAdvice.setStatus(1);
						fiPayAdvices.add(fiPayAdvice);
					}
				}
			}
			this.jfiPayAdviceManager.saveJfiPayAdvices(fiPayAdvices);
			saveMessage(request, LocaleUtil.getLocalText("fiPayAdvice.notVerified"));

			ModelAndView mv = new ModelAndView("redirect:jfiPayAdvices.html");
			mv.addObject("strAction", "listFiPayAdvices");
			mv.addObject("needReload", "1");
			return mv;
		}
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		if (!SessionLogin.getLoginUser(request).getIsManager() && !SessionLogin.getLoginUser(request).getIsCompany()) {
			crm.addField(new CustomField("sysUser.userCode", 1111, SessionLogin.getLoginUser(request).getUserCode()));
		}
		crm.addField(new CustomField("companyCode", 1111, SessionLogin.getLoginUser(request).getCompanyCode()));

		List fiPayBanks = new ArrayList();
		if (SessionLogin.getLoginUser(request).getIsManager()) {
			fiPayBanks = this.jfiPayBankManager.getJfiPayBanks(null);
		} else if (SessionLogin.getLoginUser(request).getIsCompany()) {
			fiPayBanks = this.jfiPayBankManager.getJfiPayBanksByCompany(SessionLogin.getLoginUser(request).getCompanyCode());
		}else if (SessionLogin.getLoginUser(request).getIsMember()) {
			JmiMember jmiMember = this.jmiMemberManager.getJmiMember(SessionLogin.getLoginUser(request).getUserCode());
			fiPayBanks = this.jfiPayBankManager.getJfiPayBanksWithStr(new String[] { jmiMember.getPbNo(), jmiMember.getPbNo1(), jmiMember.getPbNo2() });
		}

		request.setAttribute("fiPayBanks", fiPayBanks);

		Pager pager = new Pager("jfiPayAdviceListTable", request, 20);
		List jfiPayAdvices = jfiPayAdviceManager.getJfiPayAdvicesByCrm(crm, pager);
		//根据数据重新设置分页数据
		request.setAttribute("jfiPayAdviceListTable_totalRows", pager.getTotalObjects());

		request.setAttribute(Constants.JFIPAYADVICE_LIST, jfiPayAdvices);
		ModelAndView mv = new ModelAndView("fi/jfiPayAdviceList");
		mv.addObject("strAction", "listFiPayAdvices");
		return mv;
    }
}
