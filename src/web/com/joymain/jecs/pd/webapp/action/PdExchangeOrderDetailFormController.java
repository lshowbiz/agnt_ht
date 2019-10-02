package com.joymain.jecs.pd.webapp.action;

import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.pd.model.PdExchangeOrderDetail;
import com.joymain.jecs.pd.service.PdExchangeOrderDetailManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdExchangeOrderDetailFormController extends BaseFormController {
    private PdExchangeOrderDetailManager pdExchangeOrderDetailManager = null;

    public void setPdExchangeOrderDetailManager(PdExchangeOrderDetailManager pdExchangeOrderDetailManager) {
        this.pdExchangeOrderDetailManager = pdExchangeOrderDetailManager;
    }
    public PdExchangeOrderDetailFormController() {
        setCommandName("pdExchangeOrderDetail");
        setCommandClass(PdExchangeOrderDetail.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String uniNo = request.getParameter("uniNo");
        PdExchangeOrderDetail pdExchangeOrderDetail = null;

        if (!StringUtils.isEmpty(uniNo)) {
            pdExchangeOrderDetail = pdExchangeOrderDetailManager.getPdExchangeOrderDetail(uniNo);
        } else {
            pdExchangeOrderDetail = new PdExchangeOrderDetail();
        }

        return pdExchangeOrderDetail;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        PdExchangeOrderDetail pdExchangeOrderDetail = (PdExchangeOrderDetail) command;
        boolean isNew = (pdExchangeOrderDetail.getUniNo() == null);
		String key=null;
		String erpProductNo = request.getParameter("erpProductNo");
		String erpPrice = request.getParameter("erpPrice");
		String eoNo = request.getParameter("eoNo");
		pdExchangeOrderDetail.setErpProductNo(erpProductNo);
		if(!StringUtil.isEmpty(erpPrice)){
			pdExchangeOrderDetail.setErpPrice(new BigDecimal(erpPrice));
		}else{
			pdExchangeOrderDetail.setErpPrice(new BigDecimal(0));
		}
		String view = "";
		String strAction = request.getParameter("strAction");
		//删除
		if ("deletePdExchangeOrderDetail".equals(strAction)  ) {
			    view = "redirect:editPdExchangeOrder.html?strAction=editPdExchangeOrder&eoNo="+pdExchangeOrderDetail.getEoNo();
				pdExchangeOrderDetailManager.removePdExchangeOrderDetail(pdExchangeOrderDetail.getUniNo().toString());
				key="bdOutWardBank.deleteSuccess";
		}else{
					 if(StringUtil.isEmpty(pdExchangeOrderDetail.getEoNo())){
			        	 pdExchangeOrderDetail.setEoNo(eoNo);
			        }
			   //不为空的校验
				   String emptyCheck  = pdExchangeOrderDetailManager.getEmptyCheck(pdExchangeOrderDetail,errors,sessionLogin.getDefCharacterCoding());
				   if(!StringUtil.isEmpty(emptyCheck)){
					    key = emptyCheck;
				        view = "redirect:editPdExchangeOrder.html?strAction=editPdExchangeOrder&eoNo="+pdExchangeOrderDetail.getEoNo();
				    	//return showForm(request, response, errors);
				   }else{
						     //新增
					        if(isNew){
					        	key = "notice.product.detail.add";
							}
					        //编辑
					        else{
								key = "sys.message.updateSuccess";
							}
					        view = "redirect:editPdExchangeOrder.html?strAction=editPdExchangeOrder&eoNo="+pdExchangeOrderDetail.getEoNo();
						    pdExchangeOrderDetailManager.savePdExchangeOrderDetail(pdExchangeOrderDetail);
				     }
			   }
		saveMessage(request, getText(sessionLogin.getDefCharacterCoding(),key)); 
		return new ModelAndView(view);
    }
    
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		super.initBinder(request, binder);
	}
}
