package com.joymain.jecs.util.mpos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import sun.misc.BASE64Decoder;

import com.joymain.jecs.util.bill99.Bill99Constants;

/**
 * 手机支付收款的验签方法
 * @author Administrator
 *
 */
public class CerEncode {

	public boolean enCodeByCer(String val, String msg) {
		boolean flag = false;
		try {
//			String fStr = CerEncode.class.getResource("mgw.cer").getPath();
//			fStr = fStr.substring(1);
//			InputStream inStream = new FileInputStream(fStr);
			
			InputStream inStream=this.getClass().getClassLoader().getResourceAsStream("mgw.cer");
			
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			X509Certificate cert = (X509Certificate) cf.generateCertificate(inStream);
			PublicKey pk = cert.getPublicKey();
			Signature signature = Signature.getInstance("SHA1withRSA");
			signature.initVerify(pk);
			signature.update(val.getBytes());
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			flag = signature.verify(decoder.decodeBuffer(msg));
		} catch (Exception e) {
			
			System.out.println("文件找不到");
		}
		return flag;
	}
}
