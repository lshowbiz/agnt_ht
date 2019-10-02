package com.joymain.jecs.pm.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.pm.model.JpmProductCombination;
import com.joymain.jecs.pm.service.JpmProductCombinationManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JpmProductCombinationFormController extends BaseFormController {
	private JpmProductCombinationManager jpmProductCombinationManager = null;

	public void setJpmProductCombinationManager(
			JpmProductCombinationManager jpmProductCombinationManager) {
		this.jpmProductCombinationManager = jpmProductCombinationManager;
	}

	public JpmProductCombinationFormController() {
		setCommandName("jpmProductCombination");
		setCommandClass(JpmProductCombination.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		String jpcId = request.getParameter("jpcId");
		JpmProductCombination jpmProductCombination = null;
		String productNo = request.getParameter("parentProductNo");
		if (!StringUtils.isEmpty(jpcId)) {
			jpmProductCombination = jpmProductCombinationManager
					.getJpmProductCombination(jpcId);
		} else {
			jpmProductCombination = new JpmProductCombination();
			jpmProductCombination.setProductNo(productNo);
		}

		return jpmProductCombination;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		JpmProductCombination jpmProductCombination = (JpmProductCombination) command;
		boolean isNew = (jpmProductCombination.getJpcId() == null);
		Locale locale = request.getLocale();
		String key = null;
		String strAction = request.getParameter("strAction");
		String view = "redirect:jpmProductCombinations.html?strAction=jpmProductCombination" +
				"&productNo="
				+ jpmProductCombination.getProductNo();
		if ("deleteJpmProductCombination".equals(strAction)) {
			jpmProductCombinationManager
					.removeJpmProductCombination(jpmProductCombination
							.getJpcId().toString());
			key = "jpmProductCombination.delete";
		} else if ("addJpmProductCombination".equals(strAction)){
			//是否选择商品
        	if(jpmProductCombination.getSubProductNo()==null || "".equals(jpmProductCombination.getSubProductNo())){ 
        		errors.reject("po.selectProduct", new Object[] {},LocaleUtil.getLocalText("po.selectProduct"));
				return showForm(request, response, errors);
        	}
        	
        	//是否填写批发价并满足是数字
        	if(!StringUtils.isNumeric(String.valueOf(jpmProductCombination.getQty())) || jpmProductCombination.getQty()<0){ 
        		errors.reject("operation.notice.js.newQtySmallerThanZero", new Object[] {},LocaleUtil.getLocalText("operation.notice.js.newQtySmallerThanZero"));
				return showForm(request, response, errors);
        	}
			
			if(jpmProductCombinationManager.existCombinationProduct(jpmProductCombination.getSubProductNo(), jpmProductCombination.getProductNo())){
        		errors.reject("pd.productNoExits", new Object[] {},LocaleUtil.getLocalText("pd.productNoExits"));
				return showForm(request, response, errors);
        	}else{
        		jpmProductCombinationManager.saveJpmProductCombination(jpmProductCombination);
//        		pdEnterWarehouseManager.updatePdEnterWarehouseAmount(pdEnterWarehouseDetail.getEwNo());//更新金额
        	}
			 
			key = "jpmProductCombination.add";
		}else  if ("editJpmProductCombination".equals(strAction)){
			//是否填写批发价并满足是数字
        	if(!StringUtils.isNumeric(String.valueOf(jpmProductCombination.getQty())) || jpmProductCombination.getQty()<0){ 
        		errors.reject("operation.notice.js.newQtySmallerThanZero", new Object[] {},LocaleUtil.getLocalText("operation.notice.js.newQtySmallerThanZero"));
				return showForm(request, response, errors);
        	}
			
			jpmProductCombinationManager.saveJpmProductCombination(jpmProductCombination);
		}

		return new ModelAndView(view);
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
//		 binder.setAllowedFields(allowedFields);
//		String[] disallowedFields = new String[]{"productNo"};
//		 binder.setDisallowedFields(disallowedFields);
		// binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
