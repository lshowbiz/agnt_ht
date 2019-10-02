package com.joymain.jecs.util.mobil;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.fi.model.Jfi99billLog;

public interface MobilWebUtil {

	/**
	 * 1.获取快钱返回信息，验签，保存快钱支付记录
	 * @param request
	 * @return
	 */
	public Jfi99billLog getJfi99billLog(HttpServletRequest request);
}