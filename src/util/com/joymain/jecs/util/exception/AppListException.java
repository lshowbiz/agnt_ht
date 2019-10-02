package com.joymain.jecs.util.exception;

import java.util.List;

public class AppListException extends RuntimeException {
	private List errorList;
	private String errMsg = null;
	
	public AppListException(String errMsg,List errorList){
		this.errMsg=errMsg;
		this.errorList = errorList;
	}
	public List getErrorList() {
		return errorList;
	}
	public void setErrorList(List errorList) {
		this.errorList = errorList;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	
}
