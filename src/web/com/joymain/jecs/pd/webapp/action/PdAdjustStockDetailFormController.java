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
import com.joymain.jecs.pd.model.PdAdjustStockDetail;
import com.joymain.jecs.pd.service.PdAdjustStockDetailManager;
import com.joymain.jecs.sys.model.SysUser;


import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdAdjustStockDetailFormController extends BaseFormController {
    private PdAdjustStockDetailManager pdAdjustStockDetailManager = null;

    public void setPdAdjustStockDetailManager(PdAdjustStockDetailManager pdAdjustStockDetailManager) {
        this.pdAdjustStockDetailManager = pdAdjustStockDetailManager;
    }
    public PdAdjustStockDetailFormController() {
        setCommandName("pdAdjustStockDetail");
        setCommandClass(PdAdjustStockDetail.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String uniNo = request.getParameter("uniNo");
        PdAdjustStockDetail pdAdjustStockDetail = null;

        if (!StringUtils.isEmpty(uniNo)) {
            pdAdjustStockDetail = pdAdjustStockDetailManager.getPdAdjustStockDetail(uniNo);
        } else {
            pdAdjustStockDetail = new PdAdjustStockDetail();
        }

        return pdAdjustStockDetail;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        String strAction = request.getParameter("strAction");
//        String view = getSuccessView();
        PdAdjustStockDetail pdAdjustStockDetail = (PdAdjustStockDetail) command;
        boolean isNew = (pdAdjustStockDetail.getUniNo() == null);
        Locale locale = request.getLocale();
        String view = "redirect:editPdAdjustStock.html?strAction=editPdAdjustStock&asNo="+pdAdjustStockDetail.getAsNo();
        String key="null";
        
        if("addPdAdjustStockDetail".equals(strAction)){
        	key="productDetail.added";
        	if(pdAdjustStockDetailManager.existPdAdjustStockDetail(pdAdjustStockDetail.getProductNo(), pdAdjustStockDetail.getAsNo())){
        		errors.reject("pd.productNoExits", new Object[] {},LocaleUtil.getLocalText("pd.productNoExits"));
    			return showForm(request, response, errors);
        	}else{
        		pdAdjustStockDetailManager.savePdAdjustStockDetail(pdAdjustStockDetail);
        	}
        	
        	
        }else  if("editPdAdjustStockDetail".equals(strAction)){
        	key="productDetail.update";
        	pdAdjustStockDetailManager.savePdAdjustStockDetail(pdAdjustStockDetail);

        }else  if("deletePdAdjustStockDetail".equals(strAction)){
        	key="productDetail.delete";
        	pdAdjustStockDetailManager.removePdAdjustStockDetail(pdAdjustStockDetail.getUniNo().toString());
        }
//        pdAdjustStockManager.updatePdAdjustStockAmount(pdAdjustStockDetail.getAsNo());
        saveMessage(request, getText(sessionLogin.getDefCharacterCoding(),key));
        
        
       
        return new ModelAndView(view);
        /*PdAdjustStockDetail pdAdjustStockDetail = (PdAdjustStockDetail) command;
        boolean isNew = (pdAdjustStockDetail.getUniNo() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deletePdAdjustStockDetail".equals(strAction)  ) {
		pdAdjustStockDetailManager.removePdAdjustStockDetail(pdAdjustStockDetail.getUniNo().toString());
		key="pdAdjustStockDetail.delete";
	}else{
		pdAdjustStockDetailManager.savePdAdjustStockDetail(pdAdjustStockDetail);
		key="pdAdjustStockDetail.update";
	}

        return new ModelAndView(getSuccessView());*/
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
