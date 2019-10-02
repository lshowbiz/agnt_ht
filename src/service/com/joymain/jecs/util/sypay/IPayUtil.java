package com.joymain.jecs.util.sypay;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.fi.model.JfiPayLog;

/** 
 * 支付 接口
 * @author lizg
 * date:2015-10-23
 */
public interface IPayUtil {
	
	final String datFmt = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 支付付款通知处理
	 * companyCode 默认="CN"
	 * @param request
	 * @return
	 */
	public JfiPayLog getJfiPayLog(HttpServletRequest request,String companyCode);
	
	/**
	 * 该接口的基础配置为数组为三
	 * ｛"支付平台中文名称"，"支付平台对应的金额类型{字典表：fibankbooktemp.moneytype}"，"打印控制台的表示关闭的"｝
	 * @return
	 */
	public String[] getConfiguration();
}
