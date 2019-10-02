package com.joymain.jecs.fi.webapp.action;

import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.pm.model.JpmProductSale;
import com.joymain.jecs.pm.service.JpmProductSaleManager;
import com.joymain.jecs.pr.model.JprRefund;
import com.joymain.jecs.pr.model.JprRefundList;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.fi.model.JfiSunOrder;
import com.joymain.jecs.fi.model.JfiSunOrderList;
import com.joymain.jecs.fi.service.JfiSunOrderListManager;
import com.joymain.jecs.fi.service.JfiSunOrderManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiSunOrderFormController extends BaseFormController {
    private JfiSunOrderManager jfiSunOrderManager = null;
    private JfiSunOrderListManager jfiSunOrderListManager = null;

    public void setJfiSunOrderManager(JfiSunOrderManager jfiSunOrderManager) {
        this.jfiSunOrderManager = jfiSunOrderManager;
    }
    public JfiSunOrderFormController() {
        setCommandName("jfiSunOrder");
        setCommandClass(JfiSunOrder.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String moId = request.getParameter("moId");
        JfiSunOrder jfiSunOrder = null;

        if (!StringUtils.isEmpty(moId)) {
            jfiSunOrder = jfiSunOrderManager.getJfiSunOrder(moId);
        } else {
            jfiSunOrder = new JfiSunOrder();
        }

        return jfiSunOrder;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
    	SysUser defSysUser = SessionLogin.getLoginUser(request);

        JfiSunOrder jfiSunOrder = (JfiSunOrder) command;
        boolean isNew = (jfiSunOrder.getMoId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiSunOrder".equals(strAction)  ) {
		jfiSunOrderManager.removeJfiSunOrder(jfiSunOrder.getMoId().toString());
		key="jfiSunOrder.delete";
	}else{

		//String[] jpmProductSaleUniNos = request.getParameterValues("g_no");
		String[] prices = request.getParameterValues("price");
		String[] qtys = request.getParameterValues("qty");
		String[] molId = request.getParameterValues("mol_id");
		BigDecimal sramount = new BigDecimal(0);
		for (int i = 0; i < molId.length; i++) {
			JfiSunOrderList jfiSunOrderList = jfiSunOrderListManager.getJfiSunOrderList(molId[i]);
			int rqty = Integer.parseInt(qtys[i]);
			BigDecimal price = new BigDecimal(prices[i]);
			jfiSunOrderList.setRqty(rqty);
			jfiSunOrderList.setSrprice(price);
			sramount = sramount.add(new BigDecimal(prices[i]).multiply(new BigDecimal(qtys[i])));
		}
		if(sramount.compareTo(new BigDecimal("0"))==0){
			jfiSunOrder.setIsDisable("0");
			jfiSunOrder.setReturnTime(null);
		}else{
			jfiSunOrder.setIsDisable("1");
			jfiSunOrder.setReturnTime(new Date());
		}
		jfiSunOrder.setSramount(sramount);
		jfiSunOrderManager.saveJfiSunOrder(jfiSunOrder);
		key="jfiSunOrder.update";
	}

        return new ModelAndView("redirect:jfiSunOrderReturn.html");
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
	public void setJfiSunOrderListManager(
			JfiSunOrderListManager jfiSunOrderListManager) {
		this.jfiSunOrderListManager = jfiSunOrderListManager;
	}
}
