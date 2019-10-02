package com.joymain.jecs.util;

import com.joymain.jecs.Constants;
import com.joymain.jecs.util.bean.ContextUtil;
import com.joymain.jecs.util.sypay.IPayUtil;
import com.joymain.jecs.util.sypay.SypayUtilImpl;
import com.joymain.jecs.util.umbpay.UmbpayUtilImpl2;
import com.joymain.jecs.util.yspay.YspayUtilImpl;

public class PayFactory {

	public static IPayUtil payFirm(String payType) throws Exception {
		if ("sypay".equals(payType)) { //
			return (SypayUtilImpl)ContextUtil.getSpringBeanByName(Constants.context, "sypayUtil");
		}
		if ("yspay".equals(payType)) { //
			return (YspayUtilImpl)ContextUtil.getSpringBeanByName(Constants.context, "yspayUtil");
		}
		if ("umbpay".equals(payType)) { //
			return (UmbpayUtilImpl2)ContextUtil.getSpringBeanByName(Constants.context, "umbpayUtil2");
		}
		return null;
	}
}
