package com.joymain.jecs.mi.webapp.action;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysDataLogManager;
import com.joymain.jecs.util.ConfigUtil;
import com.joymain.jecs.util.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import net.sf.json.JSONObject;
import utils.Base64;

public class CloudshopController extends BaseFormController {

	private final Log log = LogFactory.getLog(CloudshopController.class);

	/**
	 * 类似于service
	 */
	private JmiMemberManager jmiMemberManager = null;

	private SysDataLogManager sysDataLogManager = null;

	public JmiMemberManager getJmiMemberManager() {
		return jmiMemberManager;
	}

	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	public SysDataLogManager getSysDataLogManager() {
		return sysDataLogManager;
	}

	public void setSysDataLogManager(SysDataLogManager sysDataLogManager) {
		this.sysDataLogManager = sysDataLogManager;
	}

	/**
	 * 添加时from表单数据
	 */
	public CloudshopController() {
		setCommandName("jmiMember");
		setCommandClass(JmiMember.class);
	}

	/**
	 * 暂时不知道是初始化什么东西
	 */
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		super.initBinder(request, binder);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String result = request.getParameter("userCode");
		// 创建表单对象
		JmiMember jmiMember = new JmiMember();
		jmiMember.setUserCode(result);
		return jmiMember;
	}

	/**
	 * 提交方法
	 */
	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
		// 获得当前登陆人
		SysUser defSysUser = SessionLogin.getLoginUser(request);
		// 操作对象
		JmiMember jmiMember = (JmiMember) command;

		int errorCode = 0;
		// 原手机号码
		String cloudshop = "";
		log.info(defSysUser.getUserCode()+"修改云店手机号码，手机号码："+jmiMember.getCloudshopPhone());
		if (jmiMember.getUserCode() == null || jmiMember.getUserCode().equals("")) {
			// 会员编码不能为空
			errors.reject("error.jmiMember.userCode.notNull");
		} else {
			cloudshop = jmiMemberManager.getCheckUserCodeCloudshop(jmiMember.getUserCode());
			if (cloudshop == null || cloudshop.equals("")) {
				// 该会员编号没有云店资格
				errors.reject("error.jmiMember.cloudshopPhone.isnotYD");
			} else {
				errorCode += 1;
			}

		}
		if (jmiMember.getCloudshopPhone() == null || jmiMember.getCloudshopPhone().equals("")) {
			// 云店手机号码不能为空
			errors.reject("error.jmiMember.cloudshopPhone.notNull");
			return showForm(request, response, errors);
		} else {
			Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
			Matcher m = p.matcher(jmiMember.getCloudshopPhone());
			if (!m.matches()) {
				// 无效手机号码
				errors.reject("error.mobilephone.format");
			} else {
				if (jmiMember.getCloudshopPhone().equals(cloudshop)) {
					// 云店原手机号码与新手机号码一致
					errors.reject("error.jmiMember.cloudshopPhone.identical");
				} else {
					String result = jmiMemberManager.getCheckUserCodeCloudshoPhone(jmiMember.getCloudshopPhone(),jmiMember.getUserCode());
					if (result.equals("error")) {
						// 重复手机号码
						errors.reject("error.mobilephone.identical");
					} else {
						errorCode += 1;
					}
				}
			}
		}
		if (errorCode != 2) {
			return showForm(request, response, errors);
		} else {
			// 接口地址
			String url = ConfigUtil.getConfigValue(defSysUser.getCompanyCode(), "cloudshop.update.url");

			// 传参
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("sourceCode", "AJ");
			paramMap.put("userCode", jmiMember.getUserCode());
			paramMap.put("mobile", jmiMember.getCloudshopPhone());
			JSONObject joParamMap = JSONObject.fromObject(paramMap);

			// 编码
			String companyCode = defSysUser.getCompanyCode();

			JSONObject httpPost = httpPost(url, joParamMap, companyCode);
			
			if (httpPost == null) {
				log.info(defSysUser.getUserCode()+"修改云店手机号码，接口返回："+httpPost.get("status"));
				errors.reject("bdsendrecord.sendlateremark.15");// 系统异常
				return showForm(request, response, errors);
			}
			if (!String.valueOf(httpPost.get("status")).equals("1")) {
				String temp = String.valueOf(httpPost.get("status"));
				if (temp.equals("0")) {
					errors.reject("bdsendrecord.sendlateremark.15");// 系统异常
				}else if (temp.equals("2")) {
					errors.reject("error.userCode.is.not.exist");// 用户编码不存在
				}else if (temp.equals("3")) {
					errors.reject("error.sysConfigValue");// 参数非法
				}else if (temp.equals("4")) {
					errors.reject("error.sign.not");// 签名错误
				}else if (temp.equals("5")) {
					errors.reject("error.jmiMember.cloudshopPhone.identical");// 当前用户的手机号和本次传来的相同
				}else if (temp.equals("6")) {
					errors.reject("error.jmiMember.cloudshopPhone.ydPhoneTwo");
				}else{
					errors.reject("error.jmiMember.cloudshopPhone.ydError");
				}
				log.info(defSysUser.getUserCode()+"修改云店手机号码，接口返回：error");
				return showForm(request, response, errors);
			} else {
				// 修改数据
				jmiMemberManager.updateJmiMemberCloudshopPhone(jmiMember);
			}
		}
		MessageUtil.saveMessage(request, "云店手机号码更新成功...");
		return new ModelAndView("redirect:cloudshop.html?userCode=" + jmiMember.getUserCode());
	}

	/**
	 * 接口
	 * 
	 * @param url
	 * @param jsonParam
	 * @param companyCode
	 * @return
	 * @throws Exception
	 */
	private JSONObject httpPost(String url, JSONObject jsonParam, String companyCode) throws Exception {
		// 约定KEY
		String key = ConfigUtil.getConfigValue(companyCode, "cloudshop.key");

		// post请求返回结果
		DefaultHttpClient httpClient = new DefaultHttpClient();
		JSONObject jResult = null;
		HttpPost method = new HttpPost(url);

		// 签名
		Iterator it = jsonParam.keys();
		String linkedStr = "";
		while (it.hasNext()) {
			String key1 = (String) it.next();
			linkedStr += key1 + "=" + jsonParam.get(key1) + ",";
		}
		linkedStr = linkedStr.substring(0, linkedStr.length() - 1);
		String linkedStrMd5 = StringUtil.encodePassword(linkedStr, "md5");
		String linkedStrMd52 = "sign=" + linkedStrMd5 + "," + linkedStr;
		linkedStrMd52 = linkedStrMd52 + key;
		String base64 = Base64.encode(linkedStrMd52.getBytes());
		String sign = StringUtil.encodePassword(base64, "md5");

		if (null != jsonParam) {
			/* 添加签名 */
			jsonParam.element("sign", sign);
			// 解决中文乱码问题
			StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			method.setEntity(entity);
		}
		HttpResponse result = httpClient.execute(method);
		url = URLDecoder.decode(url, "UTF-8");
		// 请求发送成功,并得到响应
		if (result.getStatusLine().getStatusCode() == 200) {
			String temp = "";
			// 读取服务器返回过来的json字符串数据
			temp = EntityUtils.toString(result.getEntity());
			// 把json字符串转换成json对象
			jResult = JSONObject.fromObject(temp);
		}
		return jResult;
	}

}
