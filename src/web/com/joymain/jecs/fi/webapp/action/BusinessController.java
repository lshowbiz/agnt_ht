package com.joymain.jecs.fi.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.fi.service.FiBillAccountManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;

@SuppressWarnings({ "rawtypes" })
public class BusinessController extends BaseController implements Controller {

	private FiBillAccountManager fiBillAccountManager = null;
	private AlCountryManager alCountryManager;

	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	public void setFiBillAccountManager(FiBillAccountManager fiBillAccountManager) {
		this.fiBillAccountManager = fiBillAccountManager;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		Pager pager = new Pager("fiBillAccountListTable", request, 20);
		List fiBillAccounts = fiBillAccountManager.getFiBillAccountsByCrmSql(crm, pager);
		request.setAttribute("fiBillAccountListTable_totalRows", pager.getTotalObjects());
		AlCountry alCountry = new AlCountry();//获取地区
		alCountry = alCountryManager.getAlCountryByCode("CN");
		alCountry.getAlStateProvinces().iterator();
		request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
		return new ModelAndView("fi/fiBillAccountList2", "fiBillAccountList", fiBillAccounts);
	}

}
