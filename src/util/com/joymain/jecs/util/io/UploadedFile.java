package com.joymain.jecs.util.io;

public class UploadedFile {
	private String name;
	private String originalFileName;
	private String contentType;
	private long size;
	private String suffixName;

	/**
	 * 获取文件类型
	 * 
	 * @return String
	 */
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * 获取文件在源表单里所对应的输入框名称
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取文件的物理名称
	 * 
	 * @return String
	 */
	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	/**
	 * 获取文件大小
	 * 
	 * @return long
	 */
	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	/**
	 * 获取文件的后缀名称,如gif,jpg
	 * 
	 * @return String
	 */
	public String getSuffixName() {
		return suffixName;
	}

	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}
}
