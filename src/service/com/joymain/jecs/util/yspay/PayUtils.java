package com.joymain.jecs.util.yspay;

import java.math.BigDecimal;

import com.joymain.jecs.util.exception.AppException;

import net.sf.json.JSONObject;

public class PayUtils {
	/**
	 * 将第三方支付的自定义字段转换成RemarkBean对象
	 * @param reserved 备注信息
	 * @return
	 */
	public static RemarkBean getRemarkBean(String reserved) {
		RemarkBean entity = null;
		try {
			entity = getRemarkBeanToJson(reserved);//json格式转换
			if (entity == null) {
				entity = getRemarkBeanToArr(reserved);//数组转换
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return entity;
	}

	public static RemarkBean getRemarkBeanToJson(String reserved)  {
		RemarkBean entity = new RemarkBean();
		try {
			String json = reserved.substring(reserved.indexOf("[") + 1, reserved.indexOf("]"));
			JSONObject jsonObj = JSONObject.fromObject(json);
			entity = (RemarkBean) JSONObject.toBean(jsonObj, RemarkBean.class);
		} catch (Exception e) {
			System.out.println("getRemarkBeanToJson--解析错误--可以忽略" + reserved);
			e.printStackTrace();
			return null;
		}
		return entity;
	}

	public static RemarkBean getRemarkBeanToArr(String arrStr) throws AppException {
		RemarkBean entity = new RemarkBean();
		try {
			arrStr = arrStr.substring(arrStr.indexOf("[") + 1, arrStr.indexOf("]"));
			String[] reserved = arrStr.split(",");
			entity.setUserCode(reserved[0]);// 用户编号
			entity.setPayType(reserved[1]);// 支付类型0：充值，1支付
			entity.setZmType(reserved[2]);// 用于判断为那家支付公司
			entity.setDataType(reserved[3]);// 支付平台1:pc,2:手机
			entity.setMerchantId(reserved[4]);// 商户号
			entity.setIsFull(Boolean.valueOf(reserved[5]));// 是否全额支付
			entity.setPayFee(new BigDecimal(reserved[6])); // 手续费
		} catch (Exception e) {
			throw new AppException("getRemarkBeanToArr--解析错误" + arrStr);
		}
		return entity;
	}

	public static void main(String[] args) {

		String s = "CN43019748,1,yspay,1,cqpy,false,0.00";
		//s = "[{\"dataType\":\"1\",\"isFull\":false,\"merchantId\":\"\",\"payFee\":0,\"payType\":\"0\",\"userCode\":\"CN19147464\",\"zmType\":\"kqpay\"}]";
		System.out.println(PayUtils.getRemarkBean(s).getUserCode());
		;

	}

}
