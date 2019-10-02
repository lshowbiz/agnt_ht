package com.joymain.jecs.pd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.pd.model.PdWarehouseStock;
import com.joymain.jecs.pd.service.PdWarehouseStockManager;
import com.joymain.jecs.sys.model.SysUser;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
             
public class PdWarehouseStockFormController extends BaseFormController {
    private PdWarehouseStockManager pdWarehouseStockManager = null;

    public void setPdWarehouseStockManager(PdWarehouseStockManager pdWarehouseStockManager) {
        this.pdWarehouseStockManager = pdWarehouseStockManager;
    }
    public PdWarehouseStockFormController() {
        setCommandName("pdWarehouseStock");
        setCommandClass(PdWarehouseStock.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String uniNo = request.getParameter("uniNo");
        PdWarehouseStock pdWarehouseStock = null;
        if (!StringUtils.isEmpty(uniNo)) {
            pdWarehouseStock = pdWarehouseStockManager.getPdWarehouseStock(uniNo);
        } else {
            pdWarehouseStock = new PdWarehouseStock();
        }

        return pdWarehouseStock;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        PdWarehouseStock pdWarehouseStock = (PdWarehouseStock) command;
        boolean isNew = (pdWarehouseStock.getUniNo() == null);
        Locale locale = request.getLocale();
		String key=null;
		String strAction = request.getParameter("strAction");
		if ("deletePdWarehouseStock".equals(strAction)  ) {
			pdWarehouseStockManager.removePdWarehouseStock(pdWarehouseStock.getUniNo().toString());
			key="pdWarehouseStock.delete";
		}else if("editPdWarehouseStock".equals(strAction)  ){
			pdWarehouseStockManager.savePdWarehouseStock(pdWarehouseStock);
			key="success.update";
		}
		saveMessage(request, getText(sessionLogin.getDefCharacterCoding(), key));
        return new ModelAndView(getSuccessView());
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
