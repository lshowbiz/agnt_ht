package com.joymain.jecs.pd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.pd.model.PdShipStrategyMain;
import com.joymain.jecs.pd.service.PdShipStrategyMainManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdShipStrategyMainFormController extends BaseFormController {
	private PdShipStrategyMainManager pdShipStrategyMainManager = null;

	public void setPdShipStrategyMainManager(PdShipStrategyMainManager pdShipStrategyMainManager) {
		this.pdShipStrategyMainManager = pdShipStrategyMainManager;
	}
	public PdShipStrategyMainFormController() {
		setCommandName("pdShipStrategyMain");
		setCommandClass(PdShipStrategyMain.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
	throws Exception {
		String valueId = request.getParameter("valueId");
		PdShipStrategyMain pdShipStrategyMain = null;

		if (!StringUtils.isEmpty(valueId)) {
			pdShipStrategyMain = pdShipStrategyMainManager.getPdShipStrategyMain(valueId);
		} else {
			pdShipStrategyMain = new PdShipStrategyMain();
		}

		return pdShipStrategyMain;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command,
			BindException errors)
	throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		PdShipStrategyMain pdShipStrategyMain = (PdShipStrategyMain) command;
		boolean isNew = (pdShipStrategyMain.getValueId() == null);
		Locale locale = request.getLocale();
		String key=null;
		String strAction = request.getParameter("strAction");
		if ("deletePdShipStrategyMain".equals(strAction)  ) {
			pdShipStrategyMainManager.removePdShipStrategyMain(pdShipStrategyMain.getValueId().toString());
			key="saveOrUpdate.success";
		}else if("addPdShipStrategyMain".equals(strAction)  ) {
			pdShipStrategyMainManager.savePdShipStrategyMain(pdShipStrategyMain);
			key="saveOrUpdate.success";
		}else if("editPdShipStrategyMain".equals(strAction)  ) {
			Integer priority = pdShipStrategyMainManager.getPriorityIsExists(pdShipStrategyMain);
			if(priority>0){
				String strKey = "pdShipStrategyMain.priority.exist";//未选择团队订单类型
				errors.reject(strKey, new Object[] {},LocaleUtil.getLocalText(strKey));
				return showForm(request, response, errors);
			}
			key="saveOrUpdate.success";
			pdShipStrategyMainManager.savePdShipStrategyMain(pdShipStrategyMain);
		}
		saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(), key));  
		return new ModelAndView(getSuccessView());
	}
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
