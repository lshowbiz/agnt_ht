package com.joymain.jecs.webapp.util.msgUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SignGenerater {
	public static String genSign(Map<String, Object> params) {
		return MD5Helper.MD5(MD5Helper.MD5(assemble(params)).toUpperCase() + getToken()).toUpperCase();
	}

	private static String getToken() {
		return "token";
	}

	private static String assemble(Map<String, Object> params) {
		// 1.sort
		Object[] keyArr = new Object[params.size()];
		params.keySet().toArray(keyArr);
		Arrays.sort(keyArr);

		// 2.make up query string
		StringBuffer queryString = new StringBuffer();
		for (Object key : keyArr) {
			Object val = params.get(key);
			if (val != null) {
				queryString.append(key);
				if (val instanceof Boolean) {
					boolean flag = (Boolean) val;
					queryString.append(flag ? 1 : 0);
				} else if (val instanceof Map) {
					queryString.append(assemble((Map<String, Object>) val));
				}else{
					queryString.append(val);
				}
			}
		}
		return queryString.toString();
	}
	
	public static void main(String[] args) {
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("a", "我们");
		map.put("b", "都是");
		map.put("c", true);
		
		Map<String, Object> valMap = new HashMap<String, Object>();
		valMap.put("d", "中国人");
		valMap.put("e", false);
		
		map.put("f", valMap);
		SignGenerater.genSign(map);
	}
}
