package com.joymain.jecs.util.data;

import java.io.UnsupportedEncodingException;

import org.apache.commons.dbcp.BasicDataSource;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;



public class JecsDataSource extends BasicDataSource {
	private String passwordKey="jecs";
	//设置系统运行的加密令牌，目前所有系统统一设置为jecs，不用修改（稽核&美体的也可以设置为jecs），
	//只要保证运行的main方法里面加密与这个passwordKey配置一致即可
	//applicationContext-resource.xml连接池会通过com.joymain.jecs.util.data.JecsDataSource读取此 
	
	public String getPasswordKey() {
		return passwordKey;
	}

	public void setPasswordKey(String passwordKey) {
		this.passwordKey = passwordKey;
	}

	public synchronized void setPassword(String password) {
		
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(passwordKey);                     // we HAVE TO set a password
		encryptor.setAlgorithm("PBEWithMD5AndDES");
		String decryPassword = encryptor.decrypt(password);
		this.password = decryPassword;
//		TreeUtil oc = new TreeUtil();
//		String decryPassword = oc.getCrackHex(password);
//        this.password = decryPassword;
    }
	/**
	 * @param s
	 * @throws UnsupportedEncodingException 
	 */
	public static void  main(String[]  s) throws UnsupportedEncodingException{
	    StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
	    //mian方法运行需要的加密令牌passwordKey，必须与系统运行生成的编译文件JecsDataSource.class
	    //中的加密令牌private String passwordKey="jecs";一致
        String passwordKey = "jecs";
	    encryptor.setPassword(passwordKey);                     // we HAVE TO set a password
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        String orgPassword = "jecs321";//需要加密的原始密码字符串：password
        String encryPassword = encryptor.encrypt(orgPassword);
        System.out.println("key:"+passwordKey+"\n原始密码 password:"+orgPassword+"\n加密密码 password:"+encryPassword);
        String decryPassword = encryptor.decrypt(encryPassword);
        System.out.println("");
        System.out.println("解密");
        System.out.println("key:"+passwordKey+"\n加密密码password:"+encryPassword+"\n还原密码password:"+decryPassword);
	}
}