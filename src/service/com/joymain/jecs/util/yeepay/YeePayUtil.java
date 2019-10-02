package com.joymain.jecs.util.yeepay;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.fi.model.JfiYeepayLog;

public interface YeePayUtil {

	/**
	 * 易宝支付付款通知处理
	 * @param request
	 * @return
	 */
	public JfiYeepayLog getJfiYeepayLog(HttpServletRequest request);
}