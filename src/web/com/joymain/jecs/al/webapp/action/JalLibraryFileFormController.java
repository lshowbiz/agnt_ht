package com.joymain.jecs.al.webapp.action;

import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.al.model.JalLibraryColumn;
import com.joymain.jecs.al.model.JalLibraryFile;
import com.joymain.jecs.al.service.JalLibraryColumnManager;
import com.joymain.jecs.al.service.JalLibraryFileManager;
import com.joymain.jecs.al.service.JalLibraryPlateManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JalLibraryFileFormController extends BaseFormController {
	private JalLibraryFileManager jalLibraryFileManager = null;
	private JalLibraryColumnManager jalLibraryColumnManager = null;
	private JalLibraryPlateManager jalLibraryPlateManager = null;

	public void setJalLibraryPlateManager(
			JalLibraryPlateManager jalLibraryPlateManager) {
		this.jalLibraryPlateManager = jalLibraryPlateManager;
	}

	public void setJalLibraryColumnManager(
			JalLibraryColumnManager jalLibraryColumnManager) {
		this.jalLibraryColumnManager = jalLibraryColumnManager;
	}

	public void setJalLibraryFileManager(
			JalLibraryFileManager jalLibraryFileManager) {
		this.jalLibraryFileManager = jalLibraryFileManager;
	}

	public JalLibraryFileFormController() {
		setCommandName("jalLibraryFile");
		setCommandClass(JalLibraryFile.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		String fileId = request.getParameter("fileId");
		JalLibraryFile jalLibraryFile = null;

		if (!StringUtils.isEmpty(fileId)) {
			jalLibraryFile = jalLibraryFileManager.getJalLibraryFile(fileId);
			request.setAttribute("jalLibraryFile", jalLibraryFile);
		} else {
			jalLibraryFile = new JalLibraryFile();
		}

		// 所属栏目
		String columnId = request.getParameter("columnId");

		if (!StringUtils.isEmpty(columnId)) {

			JalLibraryColumn jalLibraryColumn = jalLibraryColumnManager
					.getJalLibraryColumn(columnId);
			if (jalLibraryColumn != null) {

				request.setAttribute("jalLibraryColumn", jalLibraryColumn);
			}
		}
		
		//获取IP、端口、账号、密码、目录
//		SysUser loginSysUser = SessionLogin.getLoginUser(request);
//		String upload_id = ConfigUtil.getConfigValue(loginSysUser.getCompanyCode().toUpperCase(), "upload.ip");
//		int upload_port = Integer.parseInt(ConfigUtil.getConfigValue(loginSysUser.getCompanyCode().toUpperCase(), "upload.port"));
//		String upload_name = ConfigUtil.getConfigValue(loginSysUser.getCompanyCode().toUpperCase(), "upload.name");
//		String upload_password = ConfigUtil.getConfigValue(loginSysUser.getCompanyCode().toUpperCase(), "upload.password");

		return jalLibraryFile;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		String columnId = request.getParameter("columnId");
		if (StringUtils.isEmpty(columnId)) {
			return new ModelAndView("jalLibraryColumns.html");
		}

		try {
		
				// 保存数据
				JalLibraryFile jalLibraryFile = (JalLibraryFile) command;
			
				//JalLibraryFile jalLibraryFile = new JalLibraryFile();
				jalLibraryFile.setColumnId(new Long(columnId));
				JalLibraryColumn jalLibraryColumn = jalLibraryColumnManager.getJalLibraryColumn(columnId);
				jalLibraryFile.setColumnName(jalLibraryColumn.getColumnName());
				
//				String fileName = request.getParameter("fileName");
//				String fileUrl = request.getParameter("fileUrl");
//				jalLibraryFile.setFileName(fileName);
//				jalLibraryFile.setFileUrl(fileUrl);
				
				jalLibraryFileManager.saveJalLibraryFile(jalLibraryFile);

		} catch (Exception e) {
			
			e.printStackTrace();
		}

		JalLibraryColumn jalLibraryColumn = jalLibraryColumnManager
				.getJalLibraryColumn(columnId);
		// 查询板块
		List jalLibraryPlates = jalLibraryPlateManager.getAllJalLibraryPlates();
		request.setAttribute("jalLibraryPlates", jalLibraryPlates);
		// 查询所属资料
		List jalLibraryFiles = jalLibraryFileManager
				.getJalLibraryFileByCulumnId(columnId);
		request.setAttribute("jalLibraryFiles", jalLibraryFiles);

		
		return new ModelAndView("al/jalLibraryColumnForm", "jalLibraryColumn",
				jalLibraryColumn);
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {

		super.initBinder(request, binder);
	}

	/**
	 * 得到图片的名称
	 * 
	 * @return：时间+1000以内的随机数.jpg
	 */
	public String getFileName() {
		return String.valueOf(new Date().getTime()) + "_"
				+ new Random().nextInt(1000);
	}
}
