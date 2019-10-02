package com.joymain.jecs.util.channel;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.fi.model.FiChannelLog;

public interface ChannelUtil {

	/**
	 * 盛付通支付数据准备
	 * @param channel
	 * @param flag
	 * @return
	 */
	public ChannelBean getChannelBean(ChannelBean channel, int flag);
	
	/**
	 * 支付验签校验
	 * @param request
	 * @return
	 */
	public FiChannelLog getFiChannelLog(HttpServletRequest request, String userCode);

}