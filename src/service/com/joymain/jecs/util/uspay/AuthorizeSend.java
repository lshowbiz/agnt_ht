package com.joymain.jecs.util.uspay;

import java.util.HashMap;
import java.util.Map;

public class AuthorizeSend {
	//测试
	//public static String SEND_URL = "https://test.authorize.net/gateway/transact.dll";
	//正式运营
	public static String SEND_URL = "https://secure.authorize.net/gateway/transact.dll";
	public static Map<String, String> POST_DATA = new HashMap<String, String>();

	static {
		POST_DATA.put("x_login", "3Jpjy96vCJK");
		POST_DATA.put("x_tran_key", "34KYjs46zKj8C9N2");
		POST_DATA.put("x_version", "3.1");
		POST_DATA.put("x_test_request", "FALSE");
		POST_DATA.put("x_method", "CC");
		POST_DATA.put("x_type", "AUTH_CAPTURE");
		POST_DATA.put("x_delim_data", "TRUE");
		POST_DATA.put("x_delim_char", "|");
		POST_DATA.put("x_relay_response", "FALSE");
	}
}
