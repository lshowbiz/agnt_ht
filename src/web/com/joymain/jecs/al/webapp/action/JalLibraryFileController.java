package com.joymain.jecs.al.webapp.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.JalLibraryFile;
import com.joymain.jecs.al.service.JalLibraryColumnManager;
import com.joymain.jecs.al.service.JalLibraryFileManager;
import com.joymain.jecs.al.service.JalLibraryPlateManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JalLibraryFileController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JalLibraryFileController.class);
    private JalLibraryFileManager jalLibraryFileManager = null;
    private JalLibraryPlateManager jalLibraryPlateManager = null;
    private JalLibraryColumnManager jalLibraryColumnManager = null;

    public void setJalLibraryColumnManager(JalLibraryColumnManager jalLibraryColumnManager) {
        this.jalLibraryColumnManager = jalLibraryColumnManager;
    }
	public void setJalLibraryPlateManager(
			JalLibraryPlateManager jalLibraryPlateManager) {
		this.jalLibraryPlateManager = jalLibraryPlateManager;
	}
    public void setJalLibraryFileManager(JalLibraryFileManager jalLibraryFileManager) {
        this.jalLibraryFileManager = jalLibraryFileManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {

        JalLibraryFile jalLibraryFile = new JalLibraryFile();
        BeanUtils.populate(jalLibraryFile, request.getParameterMap());
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jalLibraryFileListTable",request, 20);
        List jalLibraryFiles = jalLibraryFileManager.getJalLibraryFilesByCrm(crm,pager);
        request.setAttribute("jalLibraryFileListTable_totalRows", pager.getTotalObjects());
        
        // 查询板块
		List jalLibraryPlates = jalLibraryPlateManager.getAllJalLibraryPlates();
		request.setAttribute("jalLibraryPlates", jalLibraryPlates);
		
		//查询栏目
		String plateId=request.getParameter("plateId");
		if(!StringUtils.isEmpty(plateId)){
			List jalLibraryColumns = jalLibraryColumnManager.getJalLibraryColumnsByPlateId(plateId);
		    request.setAttribute("jalLibraryColumns", jalLibraryColumns);
		}
		
		//获取下载地址
		SysUser loginSysUser = SessionLogin.getLoginUser(request);
		String upload_ip = ConfigUtil.getConfigValue(loginSysUser.getCompanyCode().toUpperCase(), "upload.ip");
		
		String httpUrl=upload_ip+"/jecs_files";
		request.setAttribute("httpUrl", httpUrl);
		
        return new ModelAndView("al/jalLibraryFileList", Constants.JALLIBRARYFILE_LIST, jalLibraryFiles);
    }
}
