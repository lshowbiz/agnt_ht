package com.joymain.jecs.sys.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.webapp.action.BaseFormController;



public class SysBackServerFormController extends BaseFormController {
	private BdPeriodManager bdPeriodManager = null;
	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
	public SysBackServerFormController(){
		setCommandName("bdPeriod");
		setCommandClass(BdPeriod.class);
	}
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		String strAction = request.getParameter("strAction");
		// TODO Auto-generated method stub
		return super.onSubmit(request, response, command, errors);
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		// TODO Auto-generated method stub
		return super.formBackingObject(request);
	}
	
}
