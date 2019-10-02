package com.joymain.jecs.am.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.am.model.JamDownload;
import com.joymain.jecs.am.service.JamDownloadManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JamDownloadController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JamDownloadController.class);
    private JamDownloadManager jamDownloadManager = null;

    public void setJamDownloadManager(JamDownloadManager jamDownloadManager) {
        this.jamDownloadManager = jamDownloadManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        SysUser defSysUser = SessionLogin.getLoginUser(request);
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        crm.addField("companyCode", defSysUser.getCompanyCode());
        
        Pager pager = new Pager("jamDownloadListTable",request, 20);
        List jamDownloads = jamDownloadManager.getJamDownloadsByCrm(crm,pager);
        request.setAttribute("jamDownloadListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("am/jamDownloadList", Constants.JAMDOWNLOAD_LIST, jamDownloads);
    }
}
