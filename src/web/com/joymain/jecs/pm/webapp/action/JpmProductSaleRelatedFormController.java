package com.joymain.jecs.pm.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.pm.model.JpmProductSaleRelated;
import com.joymain.jecs.pm.service.JpmProductSaleRelatedManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JpmProductSaleRelatedFormController extends BaseFormController {
	private JpmProductSaleRelatedManager jpmProductSaleRelatedManager = null;

	public void setJpmProductSaleRelatedManager(JpmProductSaleRelatedManager jpmProductSaleRelatedManager) {
		this.jpmProductSaleRelatedManager = jpmProductSaleRelatedManager;
	}
	public JpmProductSaleRelatedFormController() {
		setCommandName("jpmProductSaleRelated");
		setCommandClass(JpmProductSaleRelated.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
	throws Exception {
		String id = request.getParameter("id");
		JpmProductSaleRelated jpmProductSaleRelated = null;

		if (!StringUtils.isEmpty(id)) {
			jpmProductSaleRelated = jpmProductSaleRelatedManager.getJpmProductSaleRelated(id);
		} else {
			jpmProductSaleRelated = new JpmProductSaleRelated();
		}

		return jpmProductSaleRelated;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command,
			BindException errors)
	throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		JpmProductSaleRelated jpmProductSaleRelated = (JpmProductSaleRelated) command;
		boolean isNew = (jpmProductSaleRelated.getId() == null);
		Locale locale = request.getLocale();
		String key=null;
		String strAction = request.getParameter("strAction");
		if ("deleteJpmProductSaleRelated".equals(strAction)  ) {
			jpmProductSaleRelatedManager.removeJpmProductSaleRelated(jpmProductSaleRelated.getId().toString());
			key="jpmProductSaleRelated.delete";
		}else if("addJpmProductSaleRelated".equals(strAction)  ) {
			CommonRecord crm=RequestUtil.toCommonRecord(request); 
			crm.setValue("uniNo", jpmProductSaleRelated.getUniNo());
			crm.setValue("relationUniNo",jpmProductSaleRelated.getRelationUniNo()); 
			
			//是否选择商品
        	if(jpmProductSaleRelated.getRelationUniNo()==null || "".equals(jpmProductSaleRelated.getRelationUniNo())){ 
        		errors.reject("po.selectProduct", new Object[] {},LocaleUtil.getLocalText("po.selectProduct"));
				return showForm(request, response, errors);
        	}
        	
        	//判断是否已经存在
			boolean isExist = jpmProductSaleRelatedManager.isExist(crm, "0");
        	
			if(isExist){
				errors.rejectValue("relationUniNo","error.jpmproductsalerelated.existed");
				return showForm(request, response, errors); 
			} 
			   
			jpmProductSaleRelatedManager.saveJpmProductSaleRelated(jpmProductSaleRelated);
			key="jpmProductSaleRelated.add";
		}else if("editJpmProductSaleRelated".equals(strAction)  ) {
			jpmProductSaleRelatedManager.saveJpmProductSaleRelated(jpmProductSaleRelated);
			key="jpmProductSaleRelated.update"; 
		}
		saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(), key)); 
		//redirect:jpmProductSaleRelateds.html  
		return new ModelAndView(getSuccessView()+"?strAction=jpmProductSaleRelated&uniNo="+jpmProductSaleRelated.getUniNo()+"&productNo="+request.getParameter("productNo"));
	}
	
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		String uniNo = request.getParameter("uniNo");
		String productNo = request.getParameter("productNo");
		request.setAttribute("uniNo",uniNo);
		request.setAttribute("productNo",productNo); 
		super.initBinder(request, binder);
	}
}
