package com.joymain.jecs.util.hitrust;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.fi.model.JfiHiTrustLog;

public interface HiTrustUtil {

	/**
	 * 获取url
	 * @param moId
	 * @return
	 */
	public String getHiTrustUrl(String moId);

}