package com.joymain.jecs.am.webapp.action;

import java.util.Date;
import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.mi.model.JmiAddrBook;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.am.model.JamDownload;
import com.joymain.jecs.am.service.JamDownloadManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JamDownloadFormController extends BaseFormController {
    private JamDownloadManager jamDownloadManager = null;

    public void setJamDownloadManager(JamDownloadManager jamDownloadManager) {
        this.jamDownloadManager = jamDownloadManager;
    }
    public JamDownloadFormController() {
        setCommandName("jamDownload");
        setCommandClass(JamDownload.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String dlId = request.getParameter("dlId");
        JamDownload jamDownload = null;

        if (!StringUtils.isEmpty(dlId)) {
            jamDownload = jamDownloadManager.getJamDownload(dlId);
        } else {
            jamDownload = new JamDownload();
        }

        return jamDownload;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

    	SysUser defSysUser=SessionLogin.getLoginUser(request);
        JamDownload jamDownload = (JamDownload) command;
        
        
        

		String strAction = request.getParameter("strAction");
		if ("deleteJamDownload".equals(strAction)  ) {
			jamDownloadManager.removeJamDownload(jamDownload.getDlId().toString());
			MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
		}else{

			if(checkJamDownload(jamDownload, errors)){
	    		return showForm(request, response, errors);
			}
			if("addJamDownload".equals(strAction)){
				jamDownload.setCreateNo(defSysUser.getUserCode());
				jamDownload.setCreateTime(new Date());
				jamDownload.setCompanyCode(defSysUser.getCompanyCode());
			}
			
			
			
			jamDownloadManager.saveJamDownload(jamDownload);
			MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
			
		}
        
        
        
        
        
        
        


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
    

    private boolean checkJamDownload(JamDownload jamDownload,BindException errors){
    	boolean isNotPass=false;
    	if (StringUtil.isEmpty(jamDownload.getFileType())) {
			errors.rejectValue("fileType", "isNotNull",new Object[] { LocaleUtil.getLocalText("jamDownload.fileType") }, "");
			isNotPass = true;
    	}

    	if (StringUtil.isEmpty(jamDownload.getFileName())) {
			errors.rejectValue("fileName", "isNotNull",new Object[] { LocaleUtil.getLocalText("uploadForm.name") }, "");
			isNotPass = true;
    	}
    	if (StringUtil.isEmpty(jamDownload.getFileLink())) {
			errors.rejectValue("fileLink", "isNotNull",new Object[] { LocaleUtil.getLocalText("jamDownload.fileLink") }, "");
			isNotPass = true;
    	}
    	
    	return isNotPass;
    }
}
