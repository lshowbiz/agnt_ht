package com.joymain.jecs.pd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.pd.model.PdWarehouseArea;
import com.joymain.jecs.pd.service.PdWarehouseAreaManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdWarehouseAreaFormController extends BaseFormController {
	private PdWarehouseAreaManager pdWarehouseAreaManager = null;

	public void setPdWarehouseAreaManager(
			PdWarehouseAreaManager pdWarehouseAreaManager) {
		this.pdWarehouseAreaManager = pdWarehouseAreaManager;
	}

	public PdWarehouseAreaFormController() {
		setCommandName("pdWarehouseArea");
		setCommandClass(PdWarehouseArea.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		String waId = request.getParameter("waId");
		String warehouseNo = request.getParameter("warehouseNo");
		PdWarehouseArea pdWarehouseArea = null;

		if (!StringUtils.isEmpty(waId)) {
			pdWarehouseArea = pdWarehouseAreaManager.getPdWarehouseArea(waId);
		} else {
			pdWarehouseArea = new PdWarehouseArea();
			pdWarehouseArea.setWarehouseNo(warehouseNo);
		}
		super.initStateCodeParem(request);
		return pdWarehouseArea;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
		PdWarehouseArea pdWarehouseArea = (PdWarehouseArea) command;
		String view ="redirect:pdWarehouseAreas.html?strAction=pdWarehouseAreaContent&warehouseNo="+pdWarehouseArea.getWarehouseNo();
		
		boolean isNew = (pdWarehouseArea.getWaId() == null);
		Locale locale = request.getLocale();
		String key = null;
		String strAction = request.getParameter("strAction");
		
		if("addPdWarehouseArea".equals(strAction)){
         	key ="saveOrUpdate.success";
         	pdWarehouseAreaManager.savePdWarehouseArea(pdWarehouseArea);
         }else if ("deletePdWarehouseArea".equals(strAction)  ) {
         	key ="menu.pd.deletePdWarehouseUser";
         	pdWarehouseAreaManager.removePdWarehouseArea(pdWarehouseArea.getWaId().toString());
          }else if("editPdWarehouseArea".equals(strAction) ){
        	  pdWarehouseAreaManager.savePdWarehouseArea(pdWarehouseArea);
        	  key = "operation.button.save";
          }
//         saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(),key)+getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(),"success"));
         saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(), key));  
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
