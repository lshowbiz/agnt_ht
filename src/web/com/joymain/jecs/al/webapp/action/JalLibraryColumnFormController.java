package com.joymain.jecs.al.webapp.action;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
import com.joymain.jecs.al.model.JalLibraryPlate;
import com.joymain.jecs.al.service.JalLibraryColumnManager;
import com.joymain.jecs.al.service.JalLibraryFileManager;
import com.joymain.jecs.al.service.JalLibraryPlateManager;

import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

public class JalLibraryColumnFormController extends BaseFormController {
	private JalLibraryColumnManager jalLibraryColumnManager = null;
	private JalLibraryFileManager jalLibraryFileManager = null;
	private JalLibraryPlateManager jalLibraryPlateManager = null;

	public void setJalLibraryPlateManager(
			JalLibraryPlateManager jalLibraryPlateManager) {
		this.jalLibraryPlateManager = jalLibraryPlateManager;
	}

	public void setJalLibraryFileManager(
			JalLibraryFileManager jalLibraryFileManager) {
		this.jalLibraryFileManager = jalLibraryFileManager;
	}

	public void setJalLibraryColumnManager(
			JalLibraryColumnManager jalLibraryColumnManager) {
		this.jalLibraryColumnManager = jalLibraryColumnManager;
	}

	public JalLibraryColumnFormController() {
		setCommandName("jalLibraryColumn");
		setCommandClass(JalLibraryColumn.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		String columnId = request.getParameter("columnId");
		JalLibraryColumn jalLibraryColumn = null;

		String strAction = request.getParameter("strAction");
		if ("delJalLibraryFile".equals(strAction)) {
			
			String fileId=request.getParameter("fileId");
			JalLibraryFile jalLibraryFile=jalLibraryFileManager.getJalLibraryFile(fileId);
			
			columnId=jalLibraryFile.getColumnId().toString();
	        
	        //删除文件
	        //boolean flag = FtpClientUtil.delFile("74.84.128.97", 2962,"guoyong", "guoyong2962", "jecs_files", jalLibraryFile.getFileUrl());
	        
	        //if(flag){
	        	//删除数据
		        jalLibraryFileManager.removeJalLibraryFile(fileId);
	        //}
	        
		}
		
		if (!StringUtils.isEmpty(columnId)) {
			jalLibraryColumn = jalLibraryColumnManager
					.getJalLibraryColumn(columnId);

			// 查询所属资料
			List jalLibraryFiles = jalLibraryFileManager
					.getJalLibraryFileByCulumnId(columnId);
			request.setAttribute("jalLibraryFiles", jalLibraryFiles);
		} else {
			jalLibraryColumn = new JalLibraryColumn();
		}

		// 查询板块
		List jalLibraryPlates = jalLibraryPlateManager.getAllJalLibraryPlates();
		request.setAttribute("jalLibraryPlates", jalLibraryPlates);

		// 图片读取路径
		SysUser loginSysUser = SessionLogin.getLoginUser(request);
		//String FILE_URL = ListUtil.getListValue(loginSysUser.getCompanyCode().toUpperCase(), "jpmproductsaleimage.url", "1");
		String FILE_URL =  ConfigUtil.getConfigValue(loginSysUser.getCompanyCode().toUpperCase(), "column.image.url")+"column/";
		
		request.setAttribute("fileUrl", FILE_URL);
		
		return jalLibraryColumn;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		JalLibraryColumn jalLibraryColumn = (JalLibraryColumn) command;
		boolean isNew = (jalLibraryColumn.getColumnId() == null);
		Locale locale = request.getLocale();
		String key = null;
		String strAction = request.getParameter("strAction");
		if ("deleteJalLibraryColumn".equals(strAction)) {
			jalLibraryColumnManager.removeJalLibraryColumn(jalLibraryColumn
					.getColumnId().toString());
			key = "jalLibraryColumn.delete";
		}else {

			// if(isNew){

			// 上传图片
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest
					.getFile("file");

			if (file.getSize() > 0) {
				
				// 当前用户
				SysUser loginSysUser = SessionLogin.getLoginUser(request);
				
				// 上传目标地址(需配置)
				// String uploadDir=getServletContext().getRealPath("/images") + "/column/";
				 String uploadDir = ConfigUtil.getConfigValue(loginSysUser.getCompanyCode().toUpperCase(), "jpmproductsaleimage.column")+System.getProperty("file.separator");

				// 上传目标地址(需配置)
				//String filePath = ConfigUtil.getConfigValue(loginSysUser.getCompanyCode().toUpperCase(), "file.filepath") + System.getProperty("file.separator");

				//文件名
				String fileName=getFileName();

				File dirPath = new File(uploadDir);

				if (!dirPath.exists()) {
					dirPath.mkdirs();
				}

				InputStream stream = file.getInputStream();

				OutputStream bos = new FileOutputStream(uploadDir + fileName);
				int bytesRead = 0;
				byte[] buffer = new byte[8192];

				while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
					bos.write(buffer, 0, bytesRead);
				}

				bos.close();
				stream.close();

				// 保存、更新
				jalLibraryColumn.setColumnImgs(fileName);
			}
			
			JalLibraryPlate jalLibraryPlate = jalLibraryPlateManager
			.getJalLibraryPlate(jalLibraryColumn.getPlateId() + "");

			jalLibraryColumn.setPlateName(jalLibraryPlate.getPlateName());
			jalLibraryColumnManager.saveJalLibraryColumn(jalLibraryColumn);
			key = "jalLibraryColumn.update";
			// }
		}
		return new ModelAndView(getSuccessView());
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		// binder.setAllowedFields(allowedFields);
		// binder.setDisallowedFields(disallowedFields);
		// binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
	
	/**
	 * 得到图片的名称
	 * @return：时间+1000以内的随机数.jpg
	 */
	public String getFileName(){
		return String.valueOf(new Date().getTime())+"_"+new Random().nextInt(1000)+".jpg";
	}

}
