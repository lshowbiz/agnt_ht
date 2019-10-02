package com.joymain.jecs.webapp.action;


public class MobileController {/*
	private final Log log = LogFactory.getLog(MobileController.class);
	private AmMessageManager amMessageManager = null;
	private JpmProductSaleManager jpmProductSaleManager = null;

	public void setAmMessageManager(AmMessageManager amMessageManager) {
		this.amMessageManager = amMessageManager;
	}

	public void setJpmProductSaleManager(
			JpmProductSaleManager jpmProductSaleManager) {
		this.jpmProductSaleManager = jpmProductSaleManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		CommonRecord crm = RequestUtil.toCommonRecord(request);
		Pager pager = new Pager("amMessageListTable", request, 10);

		List amMessages = null;

		JSONObject json = new JSONObject();
		String callback = "";
		if (request.getParameter("jsoncallback") != null) {
			callback = request.getParameter("jsoncallback");
		}

		if (request.getParameter("mlogin") != null) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			json.put("result", "success");
			this.outJsonString(response, callback + "([" + json.toString()
					+ "])");
		} else if (request.getParameter("am") != null) {
			amMessages = amMessageManager.getAmMessagesByCrm(crm, pager);
			JSONArray json2 = new JSONArray();
			json2.addAll(amMessages);
			json2.listIterator();
			this.outJsonString(response, callback + "([" + json2.toString()
					+ "])");
		}

		if (request.getParameter("orders") != null) {
			crm.setValue("companyCode", "CN");
			crm.setValue("status", "1");
			List jpmProductSales = jpmProductSaleManager
					.getJpmProductSalesByCrm(crm, pager);

			JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件
			jsonConfig.setIgnoreDefaultExcludes(false); // 设置默认忽略
			jsonConfig.setExcludes(new String[] {"jpmProduct" });
			JSONArray json2 = new JSONArray();
			try {
				json2 = JSONArray.fromObject(jpmProductSales, jsonConfig); // 加载配置文件
				// json2.addAll(jpmProductSales);
				// json2.listIterator();
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.outJsonString(response, callback + "([" + json2.toString()
					+ "])");
		}
		
		if (request.getParameter("pdetails") != null) {
			
			crm.setValue("companyCode", "CN");
			crm.setValue("status", "1");
			JpmProductSale jpmProductSale = jpmProductSaleManager
					.getJpmProductSale((String)request.getParameter("pdetails"));

			JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件
			jsonConfig.setIgnoreDefaultExcludes(false); // 设置默认忽略
			jsonConfig.setExcludes(new String[] {"jpmProduct" });
			JSONObject json2 = new JSONObject();
			try {
				json2 = JSONObject.fromObject(jpmProductSale, jsonConfig); // 加载配置文件
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.outJsonString(response, callback + "([" + json2.toString()
					+ "])");
		}


		return null;
	}

	public void outJsonString(HttpServletResponse response, String json) {
		response.setContentType("application/json;charset=utf-8");
		outString(response, json);
	}

	// public void outJson(HttpServletResponse response, Object obj) {
	// outJsonString(response, JSONObject.fromObject(obj).toString());
	// }
	//
	// public void outJsonArray(HttpServletResponse response, Object array) {
	// outJsonArray(response, JSONArray.fromObject(array).toString());
	// }

	public void outString(HttpServletResponse response, String json) {
		try {
			PrintWriter out = response.getWriter();
			out.write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void outXMLString(HttpServletResponse response, String xmlStr) {
		response.setContentType("application/xml;charset=UTF-8");
		outString(response, xmlStr);
	}*/

}
