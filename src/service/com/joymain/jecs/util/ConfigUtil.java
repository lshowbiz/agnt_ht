package com.joymain.jecs.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.Constants;



public class ConfigUtil {
	private static Log log = LogFactory.getLog(ConfigUtil.class);

	/**
	 * 根据键值获取对应的字符值
	 * @param msgKey
	 * @return
	 */
	public static final String getConfigValue(String companyCode, String configKey) {
		try {
			return (String) Constants.sysConfigMap.get(companyCode).get(configKey);
		} catch (Exception e) {
			log.error("Error(companyCode:"+companyCode+" configKey:"+configKey+"): ", e);
			return null;
		}
	}
}
