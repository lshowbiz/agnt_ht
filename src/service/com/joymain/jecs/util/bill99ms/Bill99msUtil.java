package com.joymain.jecs.util.bill99ms;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.fi.model.Jfi99billLog;
import com.joymain.jecs.fi.model.Jfi99billmsLog;

public interface Bill99msUtil {

	/**
	 * jfi99billLog.getReturnMsg()
	 * 0表示数据被篡改
	 * 1表示扣款失败
	 * 2自定义MD5签名被篡改(快钱签名被破解)
	 * 3支付数据重新发送
	 * 10表示成功校验
	 * @param request
	 * @param userCode
	 * @param companyCode
	 * @return
	 */
	public Jfi99billmsLog getJfi99billmsLog(HttpServletRequest request,String userCode,String companyCode);

	/**
	 * 生成BILL99实例
	 * @param bill99
	 * @param flag 0表示直接存款进存折 1表示订单存款
	 * @return
	 */
	public Bill99ms getBill99ms(Bill99ms bill99ms, int flag);

}