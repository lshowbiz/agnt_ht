package com.joymain.jecs.util.bean;

public class PasswordGenerator {
	/**
	 * 随机生成8位密码
	 * @return
	 */
	public static String generateCode(int length){
		java.util.Random   r   =   new   java.util.Random();   
	     StringBuffer   sb   =   new StringBuffer();
	     if(length<=0){
	    	 length=6;
	     }
	     for   (int   i=0;i<length;i++){   
	             sb.append(Integer.toString(r.nextInt(36)   ,36))   ;
//	             System.out.println(Integer.toString(r.nextInt(36)   ,36)); 
	     }   
//	     System.out.println(sb.toString());  
	     return sb.toString();
	}
	
}
