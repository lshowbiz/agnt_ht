package com.joymain.jecs.util.uspay;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.fi.model.JfiUsCreditCardLog;

public interface UsCreditCardUtil {

	/**
	 * 付款
	 * @param request
	 * @param uniqueCode
	 * @param payCause
	 * @return
	 */
	public JfiUsCreditCardLog payAndLogCreditCard(HttpServletRequest request,String amount, String uniqueCode);
	/**
	 * 付款
	 * @param usCreditCard
	 * @param uniqueCode
	 * @param payCause
	 * @return
	 */
	public JfiUsCreditCardLog payAndLogCreditCard(UsCreditCard usCreditCard,String amount, String uniqueCode);
}
