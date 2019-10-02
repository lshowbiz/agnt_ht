package com.joymain.jecs.util.umbpay;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.fi.model.JfiUmbpayLog;

public interface UmbpayUtil {
	/**
	 * 生成Alipay实例
	 * @param alipay
	 * @param flag 0表示直接存款进存折 1表示订单存款
	 * @return
	 */
	public Umbpay getUmbpay(Umbpay alipay, int flag);

	/**
	 * @param request
	 * @param userCode
	 * @param companyCode
	 * @return
	 */
	public JfiUmbpayLog getJfiUmbpayLog(HttpServletRequest request,String userCode,String companyCode);
}
