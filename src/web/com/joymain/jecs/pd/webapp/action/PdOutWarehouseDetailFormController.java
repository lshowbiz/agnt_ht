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
import com.joymain.jecs.pd.model.PdOutWarehouseDetail;
import com.joymain.jecs.pd.service.PdOutWarehouseDetailManager;
import com.joymain.jecs.sys.model.SysUser;



import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdOutWarehouseDetailFormController extends BaseFormController {
    private PdOutWarehouseDetailManager pdOutWarehouseDetailManager = null;

    public void setPdOutWarehouseDetailManager(PdOutWarehouseDetailManager pdOutWarehouseDetailManager) {
        this.pdOutWarehouseDetailManager = pdOutWarehouseDetailManager;
    }
    public PdOutWarehouseDetailFormController() {
        setCommandName("pdOutWarehouseDetail");
        setCommandClass(PdOutWarehouseDetail.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {

        String uniNo = request.getParameter("uniNo");
        String owNo = request.getParameter("owNo");
//        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        PdOutWarehouseDetail pdOutWarehouseDetail = null;

        if (!StringUtils.isEmpty(uniNo)) {
            pdOutWarehouseDetail = pdOutWarehouseDetailManager.getPdOutWarehouseDetail(uniNo);
        } else {
            pdOutWarehouseDetail = new PdOutWarehouseDetail();
            pdOutWarehouseDetail.setOwNo(owNo);
           
        }

        return pdOutWarehouseDetail;
    
        
    /*    String uniNo = request.getParameter("uniNo");
        PdOutWarehouseDetail pdOutWarehouseDetail = null;

        if (!StringUtils.isEmpty(uniNo)) {
            pdOutWarehouseDetail = pdOutWarehouseDetailManager.getPdOutWarehouseDetail(uniNo);
        } else {
            pdOutWarehouseDetail = new PdOutWarehouseDetail();
        }

        return pdOutWarehouseDetail;*/
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

       /* PdOutWarehouseDetail pdOutWarehouseDetail = (PdOutWarehouseDetail) command;
        boolean isNew = (pdOutWarehouseDetail.getUniNo() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deletePdOutWarehouseDetail".equals(strAction)  ) {
		pdOutWarehouseDetailManager.removePdOutWarehouseDetail(pdOutWarehouseDetail.getUniNo().toString());
		key="pdOutWarehouseDetail.delete";
	}else{
		pdOutWarehouseDetailManager.savePdOutWarehouseDetail(pdOutWarehouseDetail);
		key="pdOutWarehouseDetail.update";
	}

        return new ModelAndView(getSuccessView());
        */
        SysUser sessionLogin = SessionLogin.getLoginUser(request);

        String view = getSuccessView();
        PdOutWarehouseDetail pdOutWarehouseDetail = (PdOutWarehouseDetail) command;
        boolean isNew = (pdOutWarehouseDetail.getUniNo() == null);
        Locale locale = request.getLocale();
        String strAction = request.getParameter("strAction");
        String key=null;
        String owNo = pdOutWarehouseDetail.getOwNo();
        view = "redirect:editPdOutWarehouse.html?strAction=editPdOutWarehouse&owNo="+owNo;
        
        if("addPdOutWarehouseDetail".equals(strAction)){
        	key="productDetail.added";
        	if(pdOutWarehouseDetailManager.existPdOutWarehouseDetail(pdOutWarehouseDetail.getProductNo(), owNo)){
        		errors.reject("pd.productNoExits", new Object[] {},LocaleUtil.getLocalText("pd.productNoExits"));
    			return showForm(request, response, errors);
        	}else{
        		pdOutWarehouseDetailManager.savePdOutWarehouseDetail(pdOutWarehouseDetail);
        	}
        	
        	
        	
        	
//        	pdEnterWarehouseManager.updatePdEnterWarehouseAmount(pdEnterWarehouseDetail.getEwNo());
//        	view = "redirect:editPdEnterWarehouse.html?strAction=editEnterWarehouse&ewNo="+ewNo;
        }else  if("editPdOutWarehouseDetail".equals(strAction)){
        	key="productDetail.update";
        	if(pdOutWarehouseDetailManager.existPdOutWarehouseDetail(pdOutWarehouseDetail.getProductNo(), owNo,pdOutWarehouseDetail.getUniNo())){
        		errors.reject("pd.productNoExits", new Object[] {},LocaleUtil.getLocalText("pd.productNoExits"));
    			return showForm(request, response, errors);
        	}else{
        		pdOutWarehouseDetailManager.savePdOutWarehouseDetail(pdOutWarehouseDetail);
        	}
        	
        	
//        	pdEnterWarehouseManager.updatePdEnterWarehouseAmount(pdEnterWarehouseDetail.getEwNo());
//        	view = "redirect:editPdEnterWarehouse.html?strAction=editEnterWarehouse&ewNo="+ewNo;
        }else  if("deletePdOutWarehouseDetail".equals(strAction)){
        	key="productDetail.delete";
        	pdOutWarehouseDetailManager.removePdOutWarehouseDetail(pdOutWarehouseDetail.getUniNo().toString());
//        	view = "redirect:editPdEnterWarehouse.html?strAction=editEnterWarehouse&ewNo="+ewNo;
        }
        
        
        
//      view = "redirect:editPdOutWarehouse.html?owNo="+pdOutWarehouseDetail.getOwNo();
//      request.setAttribute("strAction","edit");
       request.setAttribute("owNo", pdOutWarehouseDetail.getOwNo());
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
