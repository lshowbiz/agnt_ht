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
import com.joymain.jecs.pd.model.PdFlitWarehouseDetail;
import com.joymain.jecs.pd.service.PdFlitWarehouseDetailManager;
import com.joymain.jecs.sys.model.SysUser;


import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdFlitWarehouseDetailFormController extends BaseFormController {
    private PdFlitWarehouseDetailManager pdFlitWarehouseDetailManager = null;

    public void setPdFlitWarehouseDetailManager(PdFlitWarehouseDetailManager pdFlitWarehouseDetailManager) {
        this.pdFlitWarehouseDetailManager = pdFlitWarehouseDetailManager;
    }
    public PdFlitWarehouseDetailFormController() {
        setCommandName("pdFlitWarehouseDetail");
        setCommandClass(PdFlitWarehouseDetail.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String uniNo = request.getParameter("uniNo");
        PdFlitWarehouseDetail pdFlitWarehouseDetail = null;

        if (!StringUtils.isEmpty(uniNo)) {
            pdFlitWarehouseDetail = pdFlitWarehouseDetailManager.getPdFlitWarehouseDetail(uniNo);
        } else {
            pdFlitWarehouseDetail = new PdFlitWarehouseDetail();
        }

        return pdFlitWarehouseDetail;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        PdFlitWarehouseDetail pdFlitWarehouseDetail = (PdFlitWarehouseDetail) command;
        boolean isNew = (pdFlitWarehouseDetail.getUniNo() == null);
        Locale locale = request.getLocale();
        String fwNo = pdFlitWarehouseDetail.getFwNo();
        
        String strAction = request.getParameter("strAction");
        String key=null;
        
        String view = "redirect:editPdFlitWarehouse.html?strAction=editPdFlitWarehouse&fwNo="+fwNo;
        
        if("addPdFlitWarehouseDetail".equals(strAction)){
        	key="productDetail.added";
        	if(pdFlitWarehouseDetailManager.existPdFlitWarehouseDetail(pdFlitWarehouseDetail.getProductNo(), fwNo)){
        		errors.reject("pd.productNoExits", new Object[] {},LocaleUtil.getLocalText("pd.productNoExits"));
				return showForm(request, response, errors);
        	}else{
        		pdFlitWarehouseDetailManager.savePdFlitWarehouseDetail(pdFlitWarehouseDetail);
        	}
        	
 
        }else  if("editPdFlitWarehouseDetail".equals(strAction)){
        	key="productDetail.update";
        	pdFlitWarehouseDetailManager.savePdFlitWarehouseDetail(pdFlitWarehouseDetail);

        }else  if("deletePdFlitWarehouseDetail".equals(strAction)){
        	key="productDetail.delete";
        	 pdFlitWarehouseDetailManager.removePdFlitWarehouseDetail(pdFlitWarehouseDetail.getUniNo().toString());
        	

        }
        saveMessage(request, getText(sessionLogin.getDefCharacterCoding(),key));
        
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
