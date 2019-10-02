package com.joymain.jecs.fi.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.fi.model.JfiPayAdvice;
import com.joymain.jecs.fi.model.JfiPayData;
import com.joymain.jecs.fi.service.JfiPayAdviceManager;
import com.joymain.jecs.fi.service.JfiPayDataManager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 银行到款条目(转未核实)
 * @author Alvin
 *
 */
public class JfiPayDataUnVerifyController extends BaseFormController {
	private JfiPayDataManager jfiPayDataManager = null;
	private JfiPayAdviceManager jfiPayAdviceManager = null;

	public void setJfiPayAdviceManager(JfiPayAdviceManager jfiPayAdviceManager) {
		this.jfiPayAdviceManager = jfiPayAdviceManager;
	}

	public void setJfiPayDataManager(JfiPayDataManager jfiPayDataManager) {
		this.jfiPayDataManager = jfiPayDataManager;
	}

	public JfiPayDataUnVerifyController() {
		setCommandName("jfiPayData");
		setCommandClass(JfiPayData.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String dataId = request.getParameter("dataId");
		JfiPayData jfiPayData = null;

		if (!StringUtils.isEmpty(dataId)) {
			jfiPayData = jfiPayDataManager.getJfiPayData(dataId);
			if (!StringUtils.isEmpty(jfiPayData.getRemark())) {
				jfiPayData.setRemark(StringUtils.replace(jfiPayData.getRemark(), "\n", "<br>"));
			}
			if (!StringUtils.isEmpty(jfiPayData.getTraceLog())) {
				jfiPayData.setTraceLog(StringUtils.replace(jfiPayData.getTraceLog(), "\n", "<br>"));
			}
			if (!StringUtils.isEmpty(jfiPayData.getExcerpt())) {
				jfiPayData.setExcerpt(StringUtils.replace(jfiPayData.getExcerpt(), "\n", "<br>"));
			}
		} else {
			jfiPayData = new JfiPayData();
		}

		return jfiPayData;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		JfiPayData jfiPayData = (JfiPayData) command;
		if (jfiPayData.getDataId() == null) {
			throw new AppException(LocaleUtil.getLocalText("fiPayData.not.exists"));
		}
		if (2 != jfiPayData.getStatus()) {
			saveMessage(request, getText("error.fiPayData.unVerified"));
		} else {
			if ("unVerifyFiPayData".equals(request.getParameter("strAction"))) {
				try {
					JfiPayAdvice jfiPayAdvice = this.jfiPayAdviceManager.getJfiPayAdvice(jfiPayData.getAdviceCode());
					if (jfiPayAdvice.getStatus() != 2 && jfiPayAdvice.getStatus() != 4) {
						saveMessage(request, getText("error.fiPayAdvice.unVerified"));
					} else {
						String cancelRemark = request.getParameter("cancelRemark");
						this.jfiPayDataManager.saveJfiPayDataUnVerify(jfiPayData.getDataId().toString(), jfiPayAdvice.getSysUser().getUserCode(), SessionLogin.getOperatorUser(request).getUserCode(), SessionLogin.getOperatorUser(request).getUserName(), LocaleUtil.getLocalText("fiPayData.cancelRemark.red") + "\n" + StringUtils.defaultIfEmpty(cancelRemark, ""), LocaleUtil.getLocalText("fiPayData.unVerify.notice"));

						saveMessage(request, getText("fiPayData.unVerfied.sucess"));
					}
				} catch (AppException ae) {
					saveMessage(request, getText(ae.getMessage()));
				}
			}
		}

		ModelAndView mv = new ModelAndView(getSuccessView());
		mv.addObject("needReload", "1");
		return mv;
	}
}
