package com.joymain.jecs.util.io;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 多个文件上传类
 * @author 
 *
 */
public class MultiFileUpload {
	private MultipartHttpServletRequest multipartRequest;
	private String uploadRealPath;
	private List fileList;
	
	public MultiFileUpload(MultipartHttpServletRequest multipartRequest, String uploadRealPath){
		this.multipartRequest=multipartRequest;
		this.uploadRealPath=uploadRealPath;
	}
	
	/**
	 * 保存表单里所有文件输入框所对应的文件
	 *
	 */
	public void saveAll(){
		this.fileList=new ArrayList(); 
		for (Iterator it = multipartRequest.getFileNames(); it.hasNext();) {
			String key = (String) it.next();
			MultipartFile file = multipartRequest.getFile(key);
			try {
				if (saveEachFile(this.uploadRealPath, file)) {
					UploadedFile uploadedFile=new UploadedFile();
					uploadedFile.setName(file.getName());
					uploadedFile.setContentType(file.getContentType());
					uploadedFile.setOriginalFileName(file.getOriginalFilename());
					uploadedFile.setSize(file.getSize());
					if(file.getOriginalFilename().lastIndexOf(".")!=-1){
						uploadedFile.setSuffixName(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
					}else{
						uploadedFile.setSuffixName("");
					}
					this.fileList.add(uploadedFile);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 获取上传成功的文件列表
	 * @return List
	 */
	public List getUploadedFileList(){
		return this.fileList;
	}
	
	/**
	 * 保存单个文件
	 * @param path String
	 * @param file MultipartFile
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveEachFile(String path, MultipartFile file) throws Exception {
		boolean result = false;
		if (file != null && !file.isEmpty()) {
			// 如果文件夹不存在则新建
			File dirPath = new File(path);
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}
			String fullPath = path + file.getOriginalFilename();
			DataOutputStream out = null;
			InputStream is = null;
			try {
				out = new DataOutputStream(new FileOutputStream(fullPath));
				is = file.getInputStream();
				byte[] buffer = new byte[8192];
				while (is.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (is != null) {
					is.close();
				}
				if (out != null) {
					out.close();
				}
			}
			result = true;
		}
		return result;
	}
}
