package com.joymain.jecs.util.alipay;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.fi.model.JfiAlipayLog;

public interface AlipayUtil {
	/**
	 * 生成Alipay实例
	 * @param alipay
	 * @param flag 0表示直接存款进存折 1表示订单存款
	 * @return
	 */
	public Alipay getAlipay(Alipay alipay, int flag);

	/**
	 * @param request
	 * @param userCode
	 * @param companyCode
	 * @return
	 */
	public JfiAlipayLog getJfiAlipayLog(HttpServletRequest request,String userCode,String companyCode);
}
