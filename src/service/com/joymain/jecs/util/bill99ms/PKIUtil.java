package com.joymain.jecs.util.bill99ms;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Map;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class PKIUtil {
	private static String signType = "SHA1withRSA";
	private static String charSet = "UTF-8";
	
	/**
	 * 下面用来获取加密所需要的参数
	 * */
	static {
		//加载默认加密提供商
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	}
	
	/**
	 * 该函数用来生成加密串，用于MD、SHA1WithRSA、DSA
	 * orgstr：要进行加密的原字符串
	 * 返回加密后的字符串
	 * */
	public String generateSign(String orgstr,String userCode) {
		Map memberAccount = Bill99msConstants.account.get(userCode);
		String sign = null;
		BASE64Encoder encoder = new BASE64Encoder();

		if(memberAccount.get("privateKey")==null){
			InputStream inputStream=this.getClass().getClassLoader().getResourceAsStream(memberAccount.get("priKeyName").toString());
			try {
				if(inputStream!=null){
					KeyStore keyStore = KeyStore.getInstance("PKCS12");//初始化密钥库
					keyStore.load(inputStream, memberAccount.get("password").toString().toCharArray());//加载密钥库文件
					String alias = keyStore.aliases().nextElement();
					PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, memberAccount.get("password").toString().toCharArray());//获取私钥
					
					memberAccount.put("privateKey", privateKey);
					
					Signature signature = Signature.getInstance(signType);//初始化加密方式
					signature.initSign(privateKey);
					signature.update(orgstr.getBytes(charSet));
					sign = encoder.encode(signature.sign());//生成加密串
				}else{
					System.out.println("pfx读取失败");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (inputStream != null) {
						inputStream.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else{
			PrivateKey privateKey = (PrivateKey)memberAccount.get("privateKey");
			Signature signature;
			try {
				signature = Signature.getInstance(signType);//初始化加密方式
				signature.initSign(privateKey);
				signature.update(orgstr.getBytes(charSet));
				sign = encoder.encode(signature.sign());//生成加密串
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sign;
	}

	/**
	 * 验签类，该方法不能用于MD验签
	 * orgdata：原字符串；sign：收到快钱返回的加密串
	 * 返回一个boolean值
	 * */
	public boolean verifyData(String orgdata, String sign) {
		BASE64Decoder decoder = new BASE64Decoder();
		boolean flag = false;
		
		if(Bill99msConstants.publicKey==null){
			InputStream inputStream=this.getClass().getClassLoader().getResourceAsStream(Bill99msConstants.pubKeyName);
			
			try {
				CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");//初始化密钥
				Certificate certificate = certificateFactory.generateCertificate(inputStream);//加载公钥
				PublicKey publicKey = certificate.getPublicKey();//获取公钥
				Bill99msConstants.publicKey = publicKey;
				byte[] signData = decoder.decodeBuffer(sign);
				
				Signature s = Signature.getInstance(signType);//初始化加密方法
				s.initVerify(publicKey);
				s.update(orgdata.getBytes());
				flag = s.verify(signData);//验签字符串
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (inputStream != null) {
						inputStream.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else{
			byte[] signData;
			try {
				signData = decoder.decodeBuffer(sign);
				
				Signature s = Signature.getInstance(signType, "BC");//初始化加密方法
				s.initVerify(Bill99msConstants.publicKey);
				s.update(orgdata.getBytes(charSet));
				flag = s.verify(signData);//验签字符串
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String signMsgVal="";
		signMsgVal=appendParam(signMsgVal,"inputCharset",Bill99msConstants.inputCharset);
		//signMsgVal=appendParam(signMsgVal,"pageUrl",Bill99msConstants.pageUrl);
		//signMsgVal=appendParam(signMsgVal,"bgUrl",Bill99msConstants.bgUrl);
		signMsgVal=appendParam(signMsgVal,"pageUrl","http://ec.joymaingroup.com/jecs/jfiPay99billReceivePage.html");
		signMsgVal=appendParam(signMsgVal,"bgUrl","http://ec.joymaingroup.com/jecs/jfiPay99billReceive.html");
		signMsgVal=appendParam(signMsgVal,"version",Bill99msConstants.version);
		signMsgVal=appendParam(signMsgVal,"language",Bill99msConstants.language);
		signMsgVal=appendParam(signMsgVal,"signType",Bill99msConstants.signType4);

		signMsgVal=appendParam(signMsgVal,"payeeContactType",Bill99msConstants.payeeContactType);
		signMsgVal=appendParam(signMsgVal,"payeeContact","afraiddie@163.com");
		
		signMsgVal=appendParam(signMsgVal,"payerName","123名");
		signMsgVal=appendParam(signMsgVal,"payerContactType","");
		signMsgVal=appendParam(signMsgVal,"payerContact","");
		signMsgVal=appendParam(signMsgVal,"orderId","CN20120210000026");
		signMsgVal=appendParam(signMsgVal,"orderAmount","100000");
		signMsgVal=appendParam(signMsgVal,"payeeAmount","35000");
		signMsgVal=appendParam(signMsgVal,"orderTime","20120210160200");
		signMsgVal=appendParam(signMsgVal,"productName","");
		signMsgVal=appendParam(signMsgVal,"productNum","");
		signMsgVal=appendParam(signMsgVal,"productDesc","");
		//signMsgVal=appendParam(signMsgVal,"ext1","d33a21a0bf3472fd9339ed83659b1b66");
		signMsgVal=appendParam(signMsgVal,"ext1","");
		signMsgVal=appendParam(signMsgVal,"ext2","CN21736826,0,0.00");
		signMsgVal=appendParam(signMsgVal,"payType",Bill99msConstants.payType10);
		signMsgVal=appendParam(signMsgVal,"bankId","ICBC");
		signMsgVal=appendParam(signMsgVal,"pid","10011813334");
		signMsgVal=appendParam(signMsgVal,"sharingData","1^jian852003@163.com^65000^0^ms1");
		signMsgVal=appendParam(signMsgVal,"sharingPayFlag",Bill99msConstants.sharingPayFlag);
		PKIUtil pkiUtil=new PKIUtil();
		System.out.println(signMsgVal);
		String signMsg=pkiUtil.generateSign(signMsgVal,"afraiddie@163.com");
		System.out.println(signMsg);
		System.out.println(URLEncoder.encode(signMsg));

		//生成加密串。必须保持如下顺序。
/*		String merchantSignMsgVal="";
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"merchantAcctId","1001801954401");
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"version","v2.0");
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"language","1");
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"signType","4");
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"payType","10");
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"bankId","CCB");
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"orderId","607663");
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"orderTime","20100827092250");
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"orderAmount","1500000");
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"dealId","214145056");
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"bankDealId","100827050578");
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"dealTime","20100827093048");
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"payAmount","1500000");
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"fee","7500");
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"ext1","fe14274596ed44d822ba32ffaf3599a7");
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"ext2","CN50369049,1,0.00");
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"payResult","10");
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"errCode","");
		boolean verifyData = pkiUtil.verifyData(merchantSignMsgVal,"D5KMudzUuxeq+ISDDdyZT4emp9Vu5RwcknRMg1s2r6Fc8YkP/iGltVFufsASiyZEeykH03j29QCIcsTBPlcdop5QgtysXx+iH3dpL3aTYQyiKdH+c5GQV43+4dQUA2T8kINzdOGOETWzhajkPQvpN0ciniztWRVf9ATskH3GAL5pTfre9Qk5T+7dyyb5FzFVW6mBICTFyFPPtbeHRgaQ8877TcnaxSh/LDTvOMWRKhI+IsEY9lung9/P4L/MOi2CsEwjzblXBsUGDu/3hmbqFCsZMMDx2oTc2k6xKyKV5L6VCBrwQyAQ48qnonIKid4ymZ9RoujDCIuZeXhFOo0owg==");
		System.out.println(verifyData);*/
	}

	
	//功能函数。将变量值不为空的参数组成字符串
	private static String appendParam(String returnStr,String paramId,String paramValue)
	{
		if(!returnStr.equals(""))
		{
			if(!paramValue.equals(""))
			{
				returnStr=returnStr+"&"+paramId+"="+paramValue;
			}
		}
		else
		{
			if(!paramValue.equals(""))
			{
			returnStr=paramId+"="+paramValue;
			}
		}	
		return returnStr;
	}
}