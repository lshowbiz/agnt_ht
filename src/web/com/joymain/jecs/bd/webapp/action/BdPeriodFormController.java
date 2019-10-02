package com.joymain.jecs.bd.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.webapp.action.BaseFormController;

public class BdPeriodFormController extends BaseFormController {
	private BdPeriodManager bdPeriodManager = null;

	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public BdPeriodFormController() {
		setCommandName("bdPeriod");
		setCommandClass(BdPeriod.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String wid = request.getParameter("wid");
		BdPeriod bdPeriod = null;

		if (!StringUtils.isEmpty(wid)) {
			bdPeriod = bdPeriodManager.getBdPeriod(wid);
		} else {
			bdPeriod = new BdPeriod();
		}

		return bdPeriod;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		BdPeriod bdPeriod = (BdPeriod) command;
		boolean isNew = (bdPeriod.getWid() == null);

		if ("deleteBdPeriod".equalsIgnoreCase(request.getParameter("strAction"))) {
			bdPeriodManager.removeBdPeriod(bdPeriod.getWid().toString());

			saveMessage(request, getText("bdPeriod.deleted"));
		} else {
			//验证是否重复
			if ("addBdPeriod".equalsIgnoreCase(request.getParameter("strAction"))) {
				bdPeriod.setBonusSend(new Integer(0));
				// 判断是否存在
				BdPeriod oldBdPeriod = this.bdPeriodManager.getBdPeriodByWeek(bdPeriod.getWyear(), bdPeriod.getWweek(), null);
				if (oldBdPeriod != null) {
					// 存在
					errors.rejectValue("wweek", "error.wweek.existed");
					return showForm(request, response, errors);
				}
				//判断时间是否重合
				oldBdPeriod = this.bdPeriodManager.getBdPeriodByTime(bdPeriod.getStartTime(), null);
				if (oldBdPeriod != null) {
					// 存在
					errors.rejectValue("startTime", "error.startTime.existed");
					return showForm(request, response, errors);
				}
				oldBdPeriod = this.bdPeriodManager.getBdPeriodByTime(bdPeriod.getEndTime(), null);
				if (oldBdPeriod != null) {
					// 存在
					errors.rejectValue("endTime", "error.endTime.existed");
					return showForm(request, response, errors);
				}
			} else {
				//如果是更新

				BdPeriod oldBdPeriod = this.bdPeriodManager.getBdPeriodByWeek(bdPeriod.getWyear(), bdPeriod.getWweek(), bdPeriod.getWid());
				if (oldBdPeriod != null) {
					// 存在
					errors.rejectValue("wweek", "error.wweek.existed");
					return showForm(request, response, errors);
				}

				//判断时间是否重合

				oldBdPeriod = this.bdPeriodManager.getBdPeriodByTime(bdPeriod.getStartTime(), bdPeriod.getWid());
				if (oldBdPeriod != null) {
					// 存在
					errors.rejectValue("startTime", "error.startTime.existed");
					return showForm(request, response, errors);
				}

				oldBdPeriod = this.bdPeriodManager.getBdPeriodByTime(bdPeriod.getEndTime(), bdPeriod.getWid());
				if (oldBdPeriod != null) {
					// 存在
					errors.rejectValue("endTime", "error.endTime.existed");
					return showForm(request, response, errors);
				}

			}
			bdPeriodManager.saveBdPeriod(bdPeriod);

			String key = (isNew) ? "bdPeriod.added" : "bdPeriod.updated";
			saveMessage(request, getText(key));
		}

		return new ModelAndView(getSuccessView());
	}
}
