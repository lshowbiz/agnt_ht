package com.joymain.jecs.fi.webapp.action;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.fi.model.JfiInvoice;
import com.joymain.jecs.fi.service.JfiInvoiceManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JfiInvoiceFormController extends BaseFormController {
    private JfiInvoiceManager jfiInvoiceManager = null;

    private JmiMemberManager jmiMemberManager;
    
    private BdPeriodManager bdPeriodManager;
    
    public BdPeriodManager getBdPeriodManager() {
		return bdPeriodManager;
	}
	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
	public JmiMemberManager getJmiMemberManager() {
		return jmiMemberManager;
	}
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	public void setJfiInvoiceManager(JfiInvoiceManager jfiInvoiceManager) {
        this.jfiInvoiceManager = jfiInvoiceManager;
    }
    public JfiInvoiceFormController() {
        setCommandName("jfiInvoice");
        setCommandClass(JfiInvoice.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JfiInvoice jfiInvoice = null;

        if (!StringUtils.isEmpty(id)) {
            jfiInvoice = jfiInvoiceManager.getJfiInvoice(id);
        } else {
            jfiInvoice = new JfiInvoice();
        }

        return jfiInvoice;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

//        JfiInvoice jfiInvoice = (JfiInvoice) command;
//        boolean isNew = (jfiInvoice.getId() == null);
//        Locale locale = request.getLocale();
//	String key=null;
	String strAction = request.getParameter("strAction");
//	if ("deleteJfiInvoice".equals(strAction)  ) {
//		jfiInvoiceManager.removeJfiInvoice(jfiInvoice.getId().toString());
//		key="jfiInvoice.delete";
//	}else{
//		jfiInvoiceManager.saveJfiInvoice(jfiInvoice);
//		key="jfiInvoice.update";
//	}
	
	
    if(strAction.equals("importJfiInvoice")){}

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
