package com.joymain.jecs.pd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.pd.model.PdWarehouseUser;
import com.joymain.jecs.pd.service.PdWarehouseUserManager;


import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdWarehouseUserFormController extends BaseFormController {
    private PdWarehouseUserManager pdWarehouseUserManager = null;

    public void setPdWarehouseUserManager(PdWarehouseUserManager pdWarehouseUserManager) {
        this.pdWarehouseUserManager = pdWarehouseUserManager;
    }
    public PdWarehouseUserFormController() {
        setCommandName("pdWarehouseUser");
        setCommandClass(PdWarehouseUser.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String wuId = request.getParameter("wuId");
      

        String warehouseNo = request.getParameter("warehouseNo");
        PdWarehouseUser pdWarehouseUser = null;

        if (!StringUtils.isEmpty(wuId)) {
            pdWarehouseUser = pdWarehouseUserManager.getPdWarehouseUser(wuId);
        } else {
            pdWarehouseUser = new PdWarehouseUser();
            pdWarehouseUser.setWarehouseNo(warehouseNo);
        }
        return pdWarehouseUser;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        PdWarehouseUser pdWarehouseUser = (PdWarehouseUser) command;
        // boolean isNew = (pdWarehouseUser.getWuId() == null);
         
         String view ="redirect:pdWarehouseUsers.html?strAction=pdWarehouseUserContent&warehouseNo="+pdWarehouseUser.getWarehouseNo();
         Locale locale = request.getLocale();
         String strAction = request.getParameter("strAction");
         String key =null;
         if("addPdWarehouseUser".equals(strAction)){
         	key ="menu.pd.addPdWarehouseUser";
         	 pdWarehouseUserManager.savePdWarehouseUser(pdWarehouseUser);
         }else if ("deletePdWarehouseUser".equals(strAction)  ) {
         	key ="menu.pd.deletePdWarehouseUser ";
             pdWarehouseUserManager.removePdWarehouseUser(pdWarehouseUser.getWuId().toString());
          }else if("editPdWarehouseUser".equals(strAction) ){
         	 pdWarehouseUserManager.savePdWarehouseUser(pdWarehouseUser);
          }
         saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(),key)+getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(),"success"));

        return new ModelAndView(view);
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
