package com.joymain.jecs.webapp.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import com.joymain.jecs.Constants;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.util.bean.ContextUtil;

public class ListUtil {
	protected static JdbcTemplate jdbcTemplate;
	/**
	 * 根据键值获取对应的字符值<value, title>
	 * @param msgKey
	 * @return
	 */
	public static final LinkedHashMap<String, String> getListOptions(String companyCode, String listCode) {
		Set valueSets = Constants.sysListMap.get(listCode).entrySet();
		LinkedHashMap<String, String> optionMap=new LinkedHashMap<String, String>();
		if (valueSets != null) {
			Iterator iter = valueSets.iterator();
			while (iter.hasNext()) {
				Map.Entry entry=(Map.Entry)iter.next();
				String[] values = (String[])entry.getValue();

				if(StringUtils.contains(values[1],companyCode)){
					//如果当前用户所属公司在排除公司之内,则不显示
					continue;
				}else{
					optionMap.put(entry.getKey().toString(), values[0]);
				}
			}
		}
		
		return optionMap;
	}
	
	private static void updateUsedFlag(String msgKey){
		jdbcTemplate = (JdbcTemplate) ContextUtil.getSpringBeanByName(Constants.context, "jdbcTemplate");
		String sql = "update jsys_list_key set USED_FLAG='1' where LIST_CODE='"+msgKey+"'";
		jdbcTemplate.execute(sql);
		
	}
	
	public static final String getListValue(String characterCode, String listCode, String listValue){
		if(Constants.TEST_FLAG){
			updateUsedFlag(listCode);
		}
		
		
		String[] values = Constants.sysListMap.get(listCode).get(listValue);
		if (values != null){
			return LocaleUtil.getLocalText(characterCode, values[0]);
		}else{
			return null;
		}
	}
	
	
	/**
	 * @Description:	根据键值获取对应的字符值<value, title>,如果有翻译的取中文翻译
	 * @author:			侯忻宇
	 * @date:		    2016-10-14
	 * @param companyCode
	 * @param listCode
	 * @return:
	 * @exception:
	 * @return:
	 */
	public static final LinkedHashMap<String, String> getListOptionsByLocal(String companyCode, String listCode) {
		Set valueSets = Constants.sysListMap.get(listCode).entrySet();
		LinkedHashMap<String, String> optionMap=new LinkedHashMap<String, String>();
		String localValue = "";
		if (valueSets != null) {
			Iterator iter = valueSets.iterator();
			while (iter.hasNext()) {
				Map.Entry entry=(Map.Entry)iter.next();
				String[] values = (String[])entry.getValue();

				if(StringUtils.contains(values[1],companyCode)){
					//如果当前用户所属公司在排除公司之内,则不显示
					continue;
				}else{
					localValue = LocaleUtil.getLocalText(values[0]);
					if(MeteorUtil.isBlank(localValue)){
						optionMap.put(entry.getKey().toString(), values[0]);
					}else{
						optionMap.put(entry.getKey().toString(), localValue);
					}
					
				}
			}
		}
		
		return optionMap;
	}
}