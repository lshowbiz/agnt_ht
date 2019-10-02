package com.joymain.jecs.util.bill99;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
@SuppressWarnings({"unchecked","rawtypes"})
public class PKIUtil {
	//private static String signType = "MD5WithRSA";
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
		Map memberAccount = Bill99Constants.account.get(userCode);
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
					Signature signature = Signature.getInstance("SHA1withRSA");
					//Signature signature = Signature.getInstance(signType, "BC");//初始化加密方式
					signature.initSign(privateKey);
					signature.update(orgstr.getBytes(charSet));
					sign = encoder.encode(signature.sign());//生成加密串
				}else{
					//System.out.println("pfx读取失败");
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
				//signature = Signature.getInstance(signType, "BC");//初始化加密方式
				signature = Signature.getInstance("SHA1withRSA");
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
	public boolean verifyData(String orgdata, String sign,String pubKeyNames) {
		BASE64Decoder decoder = new BASE64Decoder();
		boolean flag = false;
		
		if(Bill99Constants.publicKey==null){
			InputStream inputStream = null;
			String pubKeyName =  Bill99Constants.pubKeyName;
			if (StringUtils.isNotEmpty(pubKeyNames)) {
				pubKeyName = pubKeyNames;
			}
			try {
				//String file = "F:/cert/" + pubKeyName;
				//inputStream = new FileInputStream(new File(file));
				String file = this.getClass().getClassLoader().getResource(pubKeyName).getPath();//.replaceAll("%20", " ");
				file= URLDecoder.decode(file, "utf-8");
				inputStream = new FileInputStream(new File(file));
				//inputStream=this.getClass().getClassLoader().getResourceAsStream(pubKeyName);
				CertificateFactory certificateFactory = CertificateFactory.getInstance("X509");//初始化密钥
				Certificate certificate = certificateFactory.generateCertificate(inputStream);//加载公钥
				PublicKey publicKey = certificate.getPublicKey();//获取公钥
				Bill99Constants.publicKey = publicKey;
				byte[] signData = decoder.decodeBuffer(sign);
				
				//Signature s = Signature.getInstance(signType, "BC");//初始化加密方法
				Signature s = Signature.getInstance("SHA1withRSA");
				s.initVerify(publicKey);
				s.update(orgdata.getBytes(charSet));
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
				
				//Signature s = Signature.getInstance(signType, "BC");//初始化加密方法
				Signature s = Signature.getInstance("SHA1withRSA");
				s.initVerify(Bill99Constants.publicKey);
				s.update(orgdata.getBytes(charSet));
				flag = s.verify(signData);//验签字符串
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}


}