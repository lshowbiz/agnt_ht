package com.joymain.jecs.util.chanjet;

import java.io.UnsupportedEncodingException;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.fi.model.JfiChanjetLog;

public interface ChanjetUtil {

	/**
	 * 生成ChanjetUtil实例
	 * @param ChanjetUtil
	 * @param flag 0表示直接存款进存折 1表示订单存款
	 * @return
	 */
	public Chanjet getChanjet(Chanjet chanjet, int flag) throws UnsupportedEncodingException;
	
	/**
	 * 付款通知验签
	 * @param request
	 * @return
	 */
	public JfiChanjetLog getJfiChanjetLog(HttpServletRequest request);
}