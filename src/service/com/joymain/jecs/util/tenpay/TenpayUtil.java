package com.joymain.jecs.util.tenpay;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.fi.model.JfiTenpayLog;
import com.joymain.jecs.util.tenpay.Tenpay;

public interface TenpayUtil {

	/**
	 * 生成Tenpay实例
	 * @param tenpay
	 * @param flag 0表示直接存款进存折 1表示订单存款
	 * @return
	 */
	public Tenpay getTenpay(Tenpay tenpay, int flag);

	/**
	 * @param request
	 * @param userCode
	 * @param companyCode
	 * @return
	 */
	public JfiTenpayLog getJfiTenpayLog(HttpServletRequest request,String userCode,String companyCode);
}
