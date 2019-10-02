package com.joymain.jecs.util.reapal;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.fi.model.JfiReapalLog;

/**
 * 融宝支付
 * @author WuCF
 * date:2015-09-23
 */
public interface ReapalUtil {

	/**
	 * 融宝支付付款通知处理
	 * @param request
	 * @return
	 */
	public JfiReapalLog getJfiReapalLog(HttpServletRequest request);
}
