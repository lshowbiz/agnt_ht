package com.joymain.jecs.pd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.pd.model.PdReturnPurchaseDetail;
import com.joymain.jecs.pd.service.PdReturnPurchaseDetailManager;
import com.joymain.jecs.pm.model.JpmProductSaleNew;
import com.joymain.jecs.pm.service.JpmProductSaleManager;


import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdReturnPurchaseDetailFormController extends BaseFormController {
	private PdReturnPurchaseDetailManager pdReturnPurchaseDetailManager = null;
	private JpmProductSaleManager jpmProductSaleManager =null;

	public void setPdReturnPurchaseDetailManager(
			PdReturnPurchaseDetailManager pdReturnPurchaseDetailManager) {
		this.pdReturnPurchaseDetailManager = pdReturnPurchaseDetailManager;
	}
	
	public void setJpmProductSaleManager(JpmProductSaleManager jpmProductSaleManager) {
		this.jpmProductSaleManager = jpmProductSaleManager;
	}

	public PdReturnPurchaseDetailFormController() {
		setCommandName("pdReturnPurchaseDetail");
		setCommandClass(PdReturnPurchaseDetail.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		String uniNo = request.getParameter("uniNo");
		PdReturnPurchaseDetail pdReturnPurchaseDetail = null;
		String rpNo = request.getParameter("rpNo");

		if (!StringUtils.isEmpty(uniNo)) {
			pdReturnPurchaseDetail = pdReturnPurchaseDetailManager
					.getPdReturnPurchaseDetail(uniNo);
		} else {
			pdReturnPurchaseDetail = new PdReturnPurchaseDetail();
			pdReturnPurchaseDetail.setRpNo(rpNo);
		}

		return pdReturnPurchaseDetail;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		String rpNo = request.getParameter("rpNo");
		String view = "redirect:editPdReturnPurchase.html?strAction=editPdReturnPurchase&rpNo="
				+ rpNo;
		PdReturnPurchaseDetail pdReturnPurchaseDetail = (PdReturnPurchaseDetail) command;
		boolean isNew = (pdReturnPurchaseDetail.getUniNo() == null);
		Locale locale = request.getLocale();
		String strAction = request.getParameter("strAction");
		String key = null;
		if ("addPdReturnPurchaseDetail".equals(strAction)) {
			String productNo = pdReturnPurchaseDetail.getProductNo();
			 SysUser sessionLogin = SessionLogin.getLoginUser(request);
			 String companyCode = sessionLogin.getCompanyCode();
			 JpmProductSaleNew jpmProductSaleNew = jpmProductSaleManager.getJpmProductSaleNew(companyCode,productNo);
			 if(null==jpmProductSaleNew){
				 key="jpmproductsalenew.productno.notexist";
				 saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(), key)+","+getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(), "please.ReEnter"));
				 return new ModelAndView(view);
			 }
			
			key = "notice.product.detail.add";
			if (pdReturnPurchaseDetailManager.existPdReturnPurchaseDetail(
					pdReturnPurchaseDetail.getProductNo(), rpNo)) {
				errors.reject("pd.productNoExits", new Object[] {}, LocaleUtil
						.getLocalText("pd.productNoExits"));
				return showForm(request, response, errors);
			} else {
				pdReturnPurchaseDetailManager
						.savePdReturnPurchaseDetail(pdReturnPurchaseDetail);
//				pdReturnPurchaseManager.updateAmount(rpNo);
			}
		} else if ("editPdReturnPurchaseDetail".equals(strAction)) {
			key = "saveOrUpdate.success";
			pdReturnPurchaseDetailManager
					.savePdReturnPurchaseDetail(pdReturnPurchaseDetail);
//			pdReturnPurchaseManager.updateAmount(rpNo);
		}

		else if ("deletePdReturnPurchaseDetail".equals(strAction)) {
			key = "bdOutWardBank.deleteSuccess";
			pdReturnPurchaseDetailManager
					.removePdReturnPurchaseDetail(pdReturnPurchaseDetail
							.getUniNo().toString());
//			pdReturnPurchaseManager.updateAmount(rpNo);
			// saveMessage(request, getText("pdReturnPurchaseDetail.deleted",
			// locale));

		}
		saveMessage(request, getText(SessionLogin.getLoginUser(request)
				.getDefCharacterCoding(), key));
		return new ModelAndView(view);
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		// binder.setAllowedFields(allowedFields);
		// binder.setDisallowedFields(disallowedFields);
		// binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
