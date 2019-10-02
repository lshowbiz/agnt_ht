package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.fi.model.FiAvailableInvoice;
import com.joymain.jecs.fi.service.FiAvailableInvoiceManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class FiAvailableInvoiceFormController extends BaseFormController {
	
    private FiAvailableInvoiceManager fiAvailableInvoiceManager = null;
   
    public void setFiAvailableInvoiceManager(FiAvailableInvoiceManager fiAvailableInvoiceManager) {
        this.fiAvailableInvoiceManager = fiAvailableInvoiceManager;
    }

	public FiAvailableInvoiceFormController() {
        setCommandName("fiAvailableInvoice");
        setCommandClass(FiAvailableInvoice.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        FiAvailableInvoice fiAvailableInvoice = null;

        if (!StringUtils.isEmpty(id)) {
            fiAvailableInvoice = fiAvailableInvoiceManager.getFiAvailableInvoice(id);
        } else {
            fiAvailableInvoice = new FiAvailableInvoice();
        }

        return fiAvailableInvoice;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        FiAvailableInvoice fiAvailableInvoice = (FiAvailableInvoice) command;
        boolean isNew = (fiAvailableInvoice.getId() == null);
        Locale locale = request.getLocale();
		String key=null;
		String strAction = request.getParameter("strAction");
		SysUser defUser = SessionLogin.getLoginUser(request);
        String userCodeLogin = defUser.getUserCode();
		//逻辑删除
		if ("deleteFiAvailableInvoice".equals(strAction)){
			/*fiAvailableInvoiceManager.removeFiAvailableInvoice(fiAvailableInvoice.getId().toString());
			key="fiAvailableInvoice.delete";*/
			fiAvailableInvoice.setLogicalDelete("Y");
			fiAvailableInvoiceManager.saveFiAvailableInvoice(fiAvailableInvoice);
			this.saveMessage(request, "删除成功！");
		}
		//审核
		else if("checkFiAvailableInvoice".equals(strAction)){
			try{
			    fiAvailableInvoice.setStatus(1);
			    fiAvailableInvoiceManager.checkFiAvailableInvoice(fiAvailableInvoice,userCodeLogin);
			}catch(AppException e){
				e.printStackTrace();
				log.info("合规可用发票审核失败:"+e.toString());
				this.saveMessage(request, "审核失败！"+e.toString());
				return new ModelAndView(getSuccessView());
			}
			this.saveMessage(request, "审核成功！");
		}
		else{
			fiAvailableInvoiceManager.saveFiAvailableInvoice(fiAvailableInvoice);
			key="fiAvailableInvoice.update";
		}

        return new ModelAndView(getSuccessView());
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		super.initBinder(request, binder);
	}
}
