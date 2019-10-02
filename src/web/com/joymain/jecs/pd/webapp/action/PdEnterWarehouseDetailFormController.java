package com.joymain.jecs.pd.webapp.action;

import java.util.List;
import java.util.Locale;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.sys.model.SysUser;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.pd.model.PdEnterWarehouse;
import com.joymain.jecs.pd.model.PdEnterWarehouseDetail;
import com.joymain.jecs.pd.service.PdEnterWarehouseDetailManager;
import com.joymain.jecs.pd.service.PdEnterWarehouseManager;


import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdEnterWarehouseDetailFormController extends BaseFormController {
    private PdEnterWarehouseDetailManager pdEnterWarehouseDetailManager = null;
//    private PiProductManager piProductManager = null;
    private PdEnterWarehouseManager pdEnterWarehouseManager = null;
    public void setPdEnterWarehouseDetailManager(PdEnterWarehouseDetailManager pdEnterWarehouseDetailManager) {
        this.pdEnterWarehouseDetailManager = pdEnterWarehouseDetailManager;
    }
    public PdEnterWarehouseDetailFormController() {
        setCommandName("pdEnterWarehouseDetail");
        setCommandClass(PdEnterWarehouseDetail.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String uniNo = request.getParameter("uniNo");
        String ewNo = request.getParameter("ewNo");
        log.info("input,ewNo="+ewNo);
//        String strAction=request.getParameter("strAction");
        PdEnterWarehouseDetail pdEnterWarehouseDetail = null;

        String strAction=request.getParameter("strAction");
        if (!StringUtils.isEmpty(uniNo)) {
            pdEnterWarehouseDetail = pdEnterWarehouseDetailManager.getPdEnterWarehouseDetail(uniNo);
        } else {
            pdEnterWarehouseDetail = new PdEnterWarehouseDetail();
            pdEnterWarehouseDetail.setEwNo(ewNo);
        }
//        request.setAttribute(arg0, arg1);
        initAttributes(request);
        request.setAttribute("strAction", strAction);
        return pdEnterWarehouseDetail;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        
        PdEnterWarehouseDetail pdEnterWarehouseDetail = (PdEnterWarehouseDetail) command;
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        String strAction = request.getParameter("strAction");
        String key=null;
//        boolean isNew = (pdEnterWarehouseDetail.getUniNo() == null);
        String ewNo = pdEnterWarehouseDetail.getEwNo();
//        Locale locale = request.getLocale();
        log.info("ewNo="+pdEnterWarehouseDetail.getEwNo());
        String view = "redirect:editPdEnterWarehouse.html?strAction=editPdEnterWarehouse&ewNo="+ewNo;
        if("addPdEnterWarehouseDetail".equals(strAction)){
        	key="productDetail.added";
        	
        	if(pdEnterWarehouseDetailManager.existPdEnterWarehouseDetail(pdEnterWarehouseDetail.getProductNo(), ewNo)){
        		errors.reject("pd.productNoExits", new Object[] {},LocaleUtil.getLocalText("pd.productNoExits"));
				return showForm(request, response, errors);
        	}else{
        		pdEnterWarehouseDetailManager.savePdEnterWarehouseDetail(pdEnterWarehouseDetail);
//        		pdEnterWarehouseManager.updatePdEnterWarehouseAmount(pdEnterWarehouseDetail.getEwNo());//更新金额
        	}
        	
        	
//        	pdEnterWarehouseManager.updatePdEnterWarehouseAmount(pdEnterWarehouseDetail.getEwNo());
//        	view = "redirect:editPdEnterWarehouse.html?strAction=editEnterWarehouse&ewNo="+ewNo;
        }else  if("editPdEnterWarehouseDetail".equals(strAction)){
        	key="productDetail.update";
        	pdEnterWarehouseDetailManager.savePdEnterWarehouseDetail(pdEnterWarehouseDetail);
//        	pdEnterWarehouseManager.updatePdEnterWarehouseAmount(pdEnterWarehouseDetail.getEwNo());//更新金额
//        	view = "redirect:editPdEnterWarehouse.html?strAction=editEnterWarehouse&ewNo="+ewNo;
        }else  if("deletePdEnterWarehouseDetail".equals(strAction)){
        	key="productDetail.delete";
        	pdEnterWarehouseDetailManager.removePdEnterWarehouseDetail(pdEnterWarehouseDetail.getUniNo().toString());
//        	pdEnterWarehouseManager.updatePdEnterWarehouseAmount(pdEnterWarehouseDetail.getEwNo());//更新金额
//        	view = "redirect:editPdEnterWarehouse.html?strAction=editEnterWarehouse&ewNo="+ewNo;
        }
        saveMessage(request, getText(sessionLogin.getDefCharacterCoding(),key));
        
//        request.setAttribute("checkFlag", "0");

        return new ModelAndView(view);
    }
    public void initAttributes(HttpServletRequest request) throws IllegalAccessException, InvocationTargetException{
    
    }
	public void setPdEnterWarehouseManager(
			PdEnterWarehouseManager pdEnterWarehouseManager) {
		this.pdEnterWarehouseManager = pdEnterWarehouseManager;
	}
    
    
    
	
	
	
	
}
