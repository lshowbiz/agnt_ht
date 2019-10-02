package com.joymain.jecs.util.uspay;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.JfiUsCreditCardLog;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.util.alipay.Md5Encrypt;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;

public class UsCreditCardUtilImpl implements UsCreditCardUtil {
	private JpoMemberOrderManager jpoMemberOrderManager;
	private SysIdManager sysIdManager;
	public static void main(String[] Args) {
		String key = "joylifeus1115";
		String url = "http://ec.joylifeglobal.com/jecs/jfiUsPayRequest.html";
		long time = new Date().getTime();
		String moId = "152008";
		String requestUrl = "http://www.baidu.com";
		System.out.println("moId：" + moId);
		System.out.println("time：" + time);
		System.out.println("url：" + url);
		System.out.println("requestUrl：" + requestUrl);
		System.out.println("key：" + Md5Encrypt.md5(moId + key + time + requestUrl));
		UsCreditCard usCreditCard = new UsCreditCard();
		usCreditCard.setType(UsCreditCardConstants.type);
		usCreditCard.setUsername(UsCreditCardConstants.userName);
		usCreditCard.setPassword(UsCreditCardConstants.password);
		usCreditCard.setCcnumber("4111111111111111");
		usCreditCard.setCcexp("1010");
		usCreditCard.setAmount("10.00");
		usCreditCard.setCvv("999");
		usCreditCard.setPayment(UsCreditCardConstants.payment);
		usCreditCard.setFirstname("0000");
		usCreditCard.setLastname("0000");
		usCreditCard.setAddress1("0000");
		usCreditCard.setCity("0000");
		usCreditCard.setState("0000");
		usCreditCard.setZip("77777");
		usCreditCard.setCountry("US");
		usCreditCard.setPhone("0000");
		usCreditCard.setEmail("0000");
		usCreditCard.setOrderId("0000");
		
		usCreditCard=postPayment(usCreditCard);
		
		System.out.print("Response: ");
		System.out.println(usCreditCard.getResponse());
		System.out.print("Human Readable Code: ");
		System.out.println(usCreditCard.getResponseText());
		System.out.print("authcode Code: ");
		System.out.println(usCreditCard.getAuthCode());
		System.out.print("Trans ID: ");
		System.out.println(usCreditCard.getTransactionId());
		System.out.print("getAvsResponse: ");
		System.out.println(usCreditCard.getAvsResponse());
		System.out.print("getCvvResponse: ");
		System.out.println(usCreditCard.getCvvResponse());
		System.out.print("getOrderId: ");
		System.out.println(usCreditCard.getOrderId());
		System.out.print("getTypeResponse: ");
		System.out.println(usCreditCard.getTypeResponse());
		System.out.print("getResponseCode: ");
		System.out.println(usCreditCard.getResponseCode());
	}
	/**
	 * 付款
	 * @param request
	 * @param uniqueCode
	 * @param payCause
	 * @return
	 */
	public JfiUsCreditCardLog payAndLogCreditCard(HttpServletRequest request,String amount, String uniqueCode) {
		if (Constants.creditCardUniqueCode.containsKey(uniqueCode)) {
			throw new AppException("请勿重复支付(Duplicate purchase is not allowed.)!");
		}
		Constants.creditCardUniqueCode.put(uniqueCode, "1");
		
		UsCreditCard usCreditCard = new UsCreditCard();
		usCreditCard.setType(UsCreditCardConstants.type);
		usCreditCard.setUsername(UsCreditCardConstants.userName);
		usCreditCard.setPassword(UsCreditCardConstants.password);
		usCreditCard.setPayment(UsCreditCardConstants.payment);
		usCreditCard.setCountry("US");
		usCreditCard.setOrderId(uniqueCode);
		usCreditCard.setAmount(amount);
		usCreditCard.setInc("0");
		
		String ccNumber = request.getParameter("ccNumber");
		if(!StringUtil.isEmpty(ccNumber)){
			long ccNumberL = Long.parseLong(ccNumber);
			usCreditCard.setCcnumber(ccNumberL+UsCreditCardConstants.key + "");
		}
		
		String ccexp = request.getParameter("MM") + request.getParameter("YY");
		if(!StringUtil.isEmpty(ccexp)){
			usCreditCard.setCcexp(ccexp);
		}
		
		String cvv = request.getParameter("cvv");
		if(!StringUtil.isEmpty(cvv)){
			usCreditCard.setCvv(cvv);
		}
		
		String firstName = request.getParameter("firstName");
		if(!StringUtil.isEmpty(firstName)){
		usCreditCard.setFirstname(firstName);
		}
		
		String lastName = request.getParameter("lastName");
		if(!StringUtil.isEmpty(lastName)){
		usCreditCard.setLastname(lastName);
		}
		
		String address1 = request.getParameter("address");
		if(!StringUtil.isEmpty(address1)){
		usCreditCard.setAddress1(address1);
		}
		
		String city = request.getParameter("city");
		if(!StringUtil.isEmpty(city)){
		usCreditCard.setCity(city);
		}
		
		String state = request.getParameter("state");
		if(!StringUtil.isEmpty(state)){
		usCreditCard.setState(state);
		}
		
		String zip = request.getParameter("zip");
		if(!StringUtil.isEmpty(zip)){
		usCreditCard.setZip(zip);
		}
		
		String phone = request.getParameter("phone");
		if(!StringUtil.isEmpty(phone)){
		usCreditCard.setPhone(phone);
		}
		
		String email = request.getParameter("email");
		if(!StringUtil.isEmpty(email)){
		usCreditCard.setEmail(email);
		}
		
		usCreditCard = postPayment(usCreditCard);
		
		JfiUsCreditCardLog jfiUsCreditCardLog = setJfiUsCreditCardLog(usCreditCard);
		
		Constants.creditCardUniqueCode.remove(uniqueCode);
		return jfiUsCreditCardLog;
	}
	
	public JfiUsCreditCardLog payAndLogCreditCard(UsCreditCard usCreditCard,String amount, String uniqueCode) { 

		if (Constants.creditCardUniqueCode.containsKey(uniqueCode)) {
			throw new AppException("请勿重复支付(Duplicate purchase is not allowed.)!");
		}
		Constants.creditCardUniqueCode.put(uniqueCode, "1");
		usCreditCard.setType(UsCreditCardConstants.type);
		usCreditCard.setUsername(UsCreditCardConstants.userName);
		usCreditCard.setPassword(UsCreditCardConstants.password);
		usCreditCard.setPayment(UsCreditCardConstants.payment);
		usCreditCard.setCountry("US");
		usCreditCard.setOrderId(uniqueCode);
		usCreditCard.setAmount(amount);
		usCreditCard.setInc("0");

		usCreditCard = postPayment(usCreditCard);
		
		JfiUsCreditCardLog jfiUsCreditCardLog = setJfiUsCreditCardLog(usCreditCard);
		Constants.creditCardUniqueCode.remove(uniqueCode);
		return jfiUsCreditCardLog;
	}
	
	private static JfiUsCreditCardLog setJfiUsCreditCardLog(UsCreditCard usCreditCard){
		JfiUsCreditCardLog jfiUsCreditCardLog = new JfiUsCreditCardLog();
		jfiUsCreditCardLog.setAddress(usCreditCard.getAddress1());
		jfiUsCreditCardLog.setAmount(usCreditCard.getAmount());
		jfiUsCreditCardLog.setAuthCode(usCreditCard.getAuthCode());
		jfiUsCreditCardLog.setAvsResponse(usCreditCard.getAvsResponse());
		jfiUsCreditCardLog.setCcExp(usCreditCard.getCcexp());
		jfiUsCreditCardLog.setCcNumber(usCreditCard.getCcnumber());
		jfiUsCreditCardLog.setCity(usCreditCard.getCity());
		jfiUsCreditCardLog.setCountry(usCreditCard.getCountry());
		jfiUsCreditCardLog.setCvv(usCreditCard.getCvv());
		jfiUsCreditCardLog.setCvvResponse(usCreditCard.getCvvResponse());
		jfiUsCreditCardLog.setEmail(usCreditCard.getEmail());
		jfiUsCreditCardLog.setFirstName(usCreditCard.getFirstname());
		jfiUsCreditCardLog.setInc(usCreditCard.getInc());
		jfiUsCreditCardLog.setJiType(usCreditCard.getType());
		jfiUsCreditCardLog.setLastName(usCreditCard.getLastname());
		jfiUsCreditCardLog.setOrderId(usCreditCard.getOrderId());
		jfiUsCreditCardLog.setPassword(usCreditCard.getPassword());
		jfiUsCreditCardLog.setPayment(usCreditCard.getPayment());
		jfiUsCreditCardLog.setPhone(usCreditCard.getPhone());
		jfiUsCreditCardLog.setResponse(usCreditCard.getResponse());
		jfiUsCreditCardLog.setResponseCode(usCreditCard.getResponseCode());
		jfiUsCreditCardLog.setResponseStr(usCreditCard.getResponseStr());
		jfiUsCreditCardLog.setResponseText(usCreditCard.getResponseText());
		jfiUsCreditCardLog.setState(usCreditCard.getState());
		jfiUsCreditCardLog.setTransactionId(usCreditCard.getTransactionId());
		jfiUsCreditCardLog.setTypeResponse(usCreditCard.getTypeResponse());
		jfiUsCreditCardLog.setUserName(usCreditCard.getUsername());
		jfiUsCreditCardLog.setZip(usCreditCard.getZip());
		return jfiUsCreditCardLog;
	}
	
	public static UsCreditCard postPayment(UsCreditCard usCreditCard){
		try {
			StringBuffer sb = new StringBuffer();
			
			if(!StringUtil.isEmpty(usCreditCard.getPayment())){
				sb.append("payment=");
				sb.append(usCreditCard.getPayment());
				sb.append("&");
			}

			if(!StringUtil.isEmpty(usCreditCard.getFirstname())){
				sb.append("firstname=");
				sb.append(usCreditCard.getFirstname());
				sb.append("&");
			}

			if(!StringUtil.isEmpty(usCreditCard.getLastname())){
				sb.append("lastname=");
				sb.append(usCreditCard.getLastname());
				sb.append("&");
			}

			if(!StringUtil.isEmpty(usCreditCard.getAddress1())){
				sb.append("address1=");
				sb.append(usCreditCard.getAddress1());
				sb.append("&");
			}

			if(!StringUtil.isEmpty(usCreditCard.getCity())){
				sb.append("city=");
				sb.append(usCreditCard.getCity());
				sb.append("&");
			}

			if(!StringUtil.isEmpty(usCreditCard.getState())){
				sb.append("state=");
				sb.append(usCreditCard.getState());
				sb.append("&");
			}

			if(!StringUtil.isEmpty(usCreditCard.getZip())){
				sb.append("zip=");
				sb.append(usCreditCard.getZip());
				sb.append("&");
			}

			if(!StringUtil.isEmpty(usCreditCard.getCountry())){
				sb.append("country=");
				sb.append(usCreditCard.getCountry());
				sb.append("&");
			}

			if(!StringUtil.isEmpty(usCreditCard.getPhone())){
				sb.append("phone=");
				sb.append(usCreditCard.getPhone());
				sb.append("&");
			}

			if(!StringUtil.isEmpty(usCreditCard.getEmail())){
				sb.append("email=");
				sb.append(usCreditCard.getEmail());
				sb.append("&");
			}
			//金额
			sb.append("orderid=");
			sb.append(usCreditCard.getOrderId());
			sb.append("&");
			
			sb.append("username=");
			sb.append(usCreditCard.getUsername());
			sb.append("&");
			//信用卡信息
			sb.append("password=");
			sb.append(usCreditCard.getPassword());
			sb.append("&");
			
			sb.append("type=");
			sb.append(usCreditCard.getType());
			sb.append("&");

			sb.append("ccnumber=");
			long ccNumberL = Long.parseLong(usCreditCard.getCcnumber());
			sb.append(ccNumberL-UsCreditCardConstants.key);
			sb.append("&");

			sb.append("ccexp=");
			sb.append(usCreditCard.getCcexp());
			sb.append("&");
			
			sb.append("cvv=");
			sb.append(usCreditCard.getCvv());
			sb.append("&");
			
			sb.append("amount=");
			sb.append(usCreditCard.getAmount());
			
			//测试
			URL url = new URL(UsCreditCardConstants.url);

			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			//URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			// not necessarily required but fixes a bug with some servers
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// POST the data in the string buffer
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.write(sb.toString().getBytes());
			out.flush();
			out.close();

			// process and read the gateway response
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			line = in.readLine();
			in.close(); 
			
			//System.err.println(line);
			//System.out.println("**: "+line.split("\\|").length);

			// ONLY FOR THOSE WHO WANT TO CAPTURE GATEWAY RESPONSE INFORMATION
			// make the reply readable (be sure to use the x_delim_char for the split operation)
			
			String[] str = line.split("&");
			usCreditCard.setResponse(str[0].split("=")[1]);
			usCreditCard.setResponseText(str[1].split("=")[1]);
			usCreditCard.setAuthCode(str[2].split("=")[1]);
			usCreditCard.setTransactionId(str[3].split("=")[1]);
			usCreditCard.setAvsResponse(str[4].split("=")[1]);
			usCreditCard.setCvvResponse(str[5].split("=")[1]);
			usCreditCard.setOrderId(str[6].split("=")[1]);
			usCreditCard.setTypeResponse(str[7].split("=")[1]);
			usCreditCard.setResponseCode(str[8].split("=")[1]);
			usCreditCard.setResponseStr(line);
			
			return usCreditCard;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usCreditCard;
	}
	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}
	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}
}
