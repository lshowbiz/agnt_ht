package com.joymain.jecs.util.uspay;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.fi.model.JfiCreditCardLog;

public class AuthorizeUtil {
	public static void main(String[] Args) {
		BigDecimal td=new BigDecimal(1.23);
		JfiCreditCardLog fiCreditCardLog=new JfiCreditCardLog();
		fiCreditCardLog.setCardNumber("");
		fiCreditCardLog.setExpireDate("0509");
		fiCreditCardLog.setPayAmount(td.doubleValue());
		fiCreditCardLog.setUserCode("CN3419760");
		fiCreditCardLog.setMemberOrderNo("M01200806170000003");
		fiCreditCardLog.setPayCause(1);
		
		String cardNumber="4007000000027";
		System.out.println(StringUtils.leftPad(StringUtils.right(cardNumber, 4), cardNumber.length(), "*"));
		
		AuthorizeResponse authorizeResponse=postPayment(fiCreditCardLog,"4007000000027","123");
		
		System.out.print("Response Code: ");
		System.out.println(authorizeResponse.getResponseCode());
		System.out.print("Human Readable Response Code: ");
		System.out.println(authorizeResponse.getResponseReasonText());
		System.out.print("Approval Code: ");
		System.out.println(authorizeResponse.getAuthorizationCode());
		System.out.print("Trans ID: ");
		System.out.println(authorizeResponse.getTransactionId());
		System.out.print("MD5 Hash Server: ");
		System.out.println(authorizeResponse.getMd5Hash());
	}
	
	public static AuthorizeResponse postPayment(JfiCreditCardLog fiCreditCardLog, String cardNumber, String cvv2){
		try {
			StringBuffer sb = new StringBuffer();
			// mandatory name/value pairs for all AIM CC transactions as well as some "good to have" values
			Iterator<String> ite=AuthorizeSend.POST_DATA.keySet().iterator();
			while(ite.hasNext()){
				String theKey=ite.next();
				sb.append(theKey);
				sb.append("=");
				sb.append(AuthorizeSend.POST_DATA.get(theKey));
				sb.append("&");
			}
			//金额
			sb.append("x_amount=");
			sb.append(fiCreditCardLog.getPayAmount().toString());
			sb.append("&");
			//信用卡信息
			sb.append("x_card_num=");
			sb.append(cardNumber);
			sb.append("&");
			
			sb.append("x_exp_date=");
			sb.append(fiCreditCardLog.getExpireDate());
			sb.append("&");
			
			sb.append("x_card_code=");
			sb.append(cvv2);
			sb.append("&");

			sb.append("x_cust_id=");
			sb.append(fiCreditCardLog.getUserCode());
			sb.append("&");
			
			sb.append("x_first_name=");
			sb.append(fiCreditCardLog.getFirstName());
			sb.append("&");
			
			sb.append("x_last_name=");
			sb.append(fiCreditCardLog.getLastName());
			sb.append("&");
			
			sb.append("x_company=");
			sb.append(fiCreditCardLog.getCompany());
			sb.append("&");
			
			sb.append("x_address=");
			sb.append(fiCreditCardLog.getAddress());
			sb.append("&");
			
			sb.append("x_city=");
			sb.append(fiCreditCardLog.getCity());
			sb.append("&");
			
			sb.append("x_state=");
			sb.append(fiCreditCardLog.getState());
			sb.append("&");
			
			sb.append("x_zip=");
			sb.append(fiCreditCardLog.getZip());
			sb.append("&");
			
			sb.append("x_country=");
			sb.append(fiCreditCardLog.getCountry());
			sb.append("&");
			
			//
			sb.append("x_ship_to_first_name=");
			sb.append(fiCreditCardLog.getSFirstName());
			sb.append("&");
			
			sb.append("x_ship_to_last_name=");
			sb.append(fiCreditCardLog.getSLastName());
			sb.append("&");
			
			sb.append("x_ship_to_company=");
			sb.append(fiCreditCardLog.getSCompany());
			sb.append("&");
			
			sb.append("x_ship_to_address=");
			sb.append(fiCreditCardLog.getSAddress());
			sb.append("&");
			
			sb.append("x_ship_to_city=");
			sb.append(fiCreditCardLog.getSCity());
			sb.append("&");
			
			sb.append("x_ship_to_state=");
			sb.append(fiCreditCardLog.getSState());
			sb.append("&");
			
			sb.append("x_ship_to_zip=");
			sb.append(fiCreditCardLog.getSZip());
			sb.append("&");
			
			sb.append("x_ship_to_country=");
			sb.append(fiCreditCardLog.getSCountry());
			sb.append("&");
			//
			
			sb.append("x_phone=");
			sb.append(fiCreditCardLog.getPhone());
			sb.append("&");
			
			sb.append("x_fax=");
			sb.append(fiCreditCardLog.getFax());
			sb.append("&");
			
			sb.append("x_email=");
			sb.append(fiCreditCardLog.getEmail());
			sb.append("&");
			
			sb.append("x_customer_ip =");
			sb.append(fiCreditCardLog.getCustomerIp());
			sb.append("&");
			
			sb.append("x_invoice_num=");
			sb.append(fiCreditCardLog.getMemberOrderNo());
			sb.append("&");
			
			sb.append("x_description=");
			sb.append(fiCreditCardLog.getPayCause().toString());
			sb.append("&");
			
			//测试
			URL url = new URL(AuthorizeSend.SEND_URL);

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
			
			//封装结果
			AuthorizeResponse authorizeResponse=new AuthorizeResponse(line);
			
			return authorizeResponse;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}