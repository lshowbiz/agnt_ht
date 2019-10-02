package com.joymain.jecs.po.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.po.model.JpoCounterOrderDetail;
import com.joymain.jecs.po.service.JpoCounterOrderDetailManager;
import com.joymain.jecs.po.service.JpoCounterOrderManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JpoCounterOrderDetailFormController extends BaseFormController {
    private JpoCounterOrderDetailManager jpoCounterOrderDetailManager = null;
    private JpoCounterOrderManager jpoCounterOrderManager;
    public void setJpoCounterOrderManager(
			JpoCounterOrderManager jpoCounterOrderManager) {
		this.jpoCounterOrderManager = jpoCounterOrderManager;
	}
	public void setJpoCounterOrderDetailManager(JpoCounterOrderDetailManager jpoCounterOrderDetailManager) {
        this.jpoCounterOrderDetailManager = jpoCounterOrderDetailManager;
    }
    public JpoCounterOrderDetailFormController() {
        setCommandName("jpoCounterOrderDetail");
        setCommandClass(JpoCounterOrderDetail.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String codNo = request.getParameter("codNo");
        JpoCounterOrderDetail jpoCounterOrderDetail = null;

        String coNo = request.getParameter("poCounterOrder.coNo");
        if (!StringUtils.isEmpty(codNo)) {
            jpoCounterOrderDetail = jpoCounterOrderDetailManager.getJpoCounterOrderDetail(codNo);
        } else {
            jpoCounterOrderDetail = new JpoCounterOrderDetail();
            jpoCounterOrderDetail.setJpoCounterOrder(jpoCounterOrderManager.getJpoCounterOrder(coNo));
        }

        return jpoCounterOrderDetail;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JpoCounterOrderDetail jpoCounterOrderDetail = (JpoCounterOrderDetail) command;
        String view = "redirect:editJpoCounterOrder.html?strAction=editPoCounterOrder&coNo="+jpoCounterOrderDetail.getJpoCounterOrder().getCoNo();
        boolean isNew = (jpoCounterOrderDetail.getCodNo() == null);
        Locale locale = request.getLocale();
        String strAction = request.getParameter("strAction");
        String key = null;
        String coNo = jpoCounterOrderDetail.getJpoCounterOrder().getCoNo();
        if("addPoCounterOrderDetail".equals(strAction)){


/*        	if(jpoCounterOrderDetailManager.getExistPoCounterOrderDetail(coNo, jpoCounterOrderDetail.getJpmProductSale().getUniNo())){
>>>>>>> .r5180
        		errors.reject("pd.productNoExits", new Object[] {},LocaleUtil.getLocalText("pd.productNoExits"));
				return showForm(request, response, errors);
        	}else{
        		jpoCounterOrderDetailManager.saveJpoCounterOrderDetail(jpoCounterOrderDetail);
        		jpoCounterOrderManager.updateAmount(coNo);
        	}*/
        	
        	
        	
        }else if ("deleteJpoCounterOrderDetail".equals(request.getParameter("strAction"))  ) {
        	jpoCounterOrderDetailManager.removeJpoCounterOrderDetail(jpoCounterOrderDetail.getCodNo().toString());
        	jpoCounterOrderManager.updateAmount(coNo);
            key="productDetail.delete";
          
          
        } else if ("editPoCounterOrderDetail".equals(request.getParameter("strAction"))  ) {
        	jpoCounterOrderDetailManager.saveJpoCounterOrderDetail(jpoCounterOrderDetail);
        	jpoCounterOrderManager.updateAmount(coNo);
            key="productDetail.update";
   
            
         }
//        saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(),key));
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
