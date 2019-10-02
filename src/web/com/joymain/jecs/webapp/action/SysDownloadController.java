package com.joymain.jecs.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.sys.model.SysReportLog;
import com.joymain.jecs.sys.service.SysReportLogManager;
import com.joymain.jecs.util.io.FileUtil;

/**
 * This class is used to reload the drop-downs initialized in the
 * StartupListener.
 *
 * <p>
 * <a href="ReloadController.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class SysDownloadController implements Controller {
	private transient final Log log = LogFactory.getLog(SysDownloadController.class);
	private SysReportLogManager sysReportLogManager = null;

    public void setSysReportLogManager(SysReportLogManager sysReportLogManager) {
        this.sysReportLogManager = sysReportLogManager;
    }

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'execute' method");
		}
		
		///WEB-INF/report_tmp/d
		String realPath=request.getSession().getServletContext().getRealPath("/");
		//String filePath=request.getParameter("filePath");
		SysReportLog sysReportLog=this.sysReportLogManager.getSysReportLog(request.getParameter("reportId"));
		FileUtil.downloadFile(realPath+Constants.REPORT_TMP_PATH, sysReportLog.getFileName(),FileUtil.encodeFileName(sysReportLog.getReportName(), request), response);
		
		return null;
	}

}
