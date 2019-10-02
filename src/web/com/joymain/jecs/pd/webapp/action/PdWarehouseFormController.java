package com.joymain.jecs.pd.webapp.action;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import com.joymain.jecs.pd.model.PdWarehouse;
import com.joymain.jecs.pd.service.PdWarehouseManager;
import com.joymain.jecs.service.PdLogisticsService;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class PdWarehouseFormController extends BaseFormController {
	private PdWarehouseManager pdWarehouseManager = null;
	private PdLogisticsService starsExpressService = null;
	
	public void setStarsExpressService(PdLogisticsService starsExpressService) {
		this.starsExpressService = starsExpressService;
	}

	public void setPdWarehouseManager(PdWarehouseManager pdWarehouseManager) {
		this.pdWarehouseManager = pdWarehouseManager;
	}

	public PdWarehouseFormController() {
		setCommandName("pdWarehouse");
		setCommandClass(PdWarehouse.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		String warehouseNo = request.getParameter("warehouseNo");
		PdWarehouse pdWarehouse = null;
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		if (!StringUtils.isEmpty(warehouseNo)) {
			pdWarehouse = pdWarehouseManager.getPdWarehouse(warehouseNo);
		}
		if (pdWarehouse == null) {
			pdWarehouse = new PdWarehouse();
			
			if(!"AA".equalsIgnoreCase(sessionLogin.getCompanyCode())){
				pdWarehouse.setCompanyCode(sessionLogin.getCompanyCode());
			}
		}

		initStateCodeParem(request);
		return pdWarehouse;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		PdWarehouse pdWarehouse = (PdWarehouse) command;
		boolean isNew = (pdWarehouse.getWarehouseNo() == null);
		Locale locale = request.getLocale();
		String key = null;
		String strAction = request.getParameter("strAction");
		String view ="redirect:pdWarehouses.html?strAction=listPdWarehouses";
		
		 if("addPdWarehouse".equals(strAction)){
	        	key = "menu.pd.addPdWarehouse";
	        	if(pdWarehouseManager.existWarehouseNo(pdWarehouse.getWarehouseNo())){
	        		errors.reject("pdWarehouse.pdWarehouseExist", new Object[] {},LocaleUtil.getLocalText("pdWarehouse.pdWarehouseExist"));
					return showForm(request, response, errors);
	        	}
	        	addWarehouse(request,response,pdWarehouse);
	        }else if("editPdWarehouse".equals(strAction)){
	        	key ="menu.pd.editPdWarehouse";
	        	pdWarehouseManager.savePdWarehouse(pdWarehouse);
	        }
		 
		 //同步仓库
		 if("STARS".equalsIgnoreCase(pdWarehouse.getShNo())){
			 log.info("同步仓库>>>>>>>>>>>");
			 sendStarsPdWarehouse(pdWarehouse);
		 }
		 saveMessage(request, getText(sessionLogin.getDefCharacterCoding(),key)+getText(sessionLogin.getDefCharacterCoding(),"success")); 
		return new ModelAndView(view);
	}

	private void addWarehouse(HttpServletRequest request,
			HttpServletResponse response, PdWarehouse pdWarehouse) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		if(!"AA".equalsIgnoreCase(sessionLogin.getCompanyCode())){
			pdWarehouse.setCompanyCode(sessionLogin.getCompanyCode());
		}
//		pdWarehouse.setCreateCode(sessionLogin.getUserCode());
//		pdWarehouse.setCreateTime(new Date());
		pdWarehouseManager.savePdWarehouse(pdWarehouse);
	}

	private void sendStarsPdWarehouse(PdWarehouse pdWarehouse){
		try {
			boolean ret=starsExpressService.sendWareHouseInfo(pdWarehouse);
			if(!ret){
				log.info("仓库同步失败,warehouseno="+pdWarehouse.getWarehouseNo());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("仓库同步错误,warehouseno="+pdWarehouse.getWarehouseNo()+","+e.getMessage());
		}
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
