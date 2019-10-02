package com.joymain.jecs.webapp.util;

import java.security.MessageDigest;

/**
 * MD5加密类 <br>
 * 创建日期：Jul 25, 2011 <br>
 * <b>Copyright 2011　银联商务有限公司　All Rights Reserved</b>
 * 
 * @author xychen
 * @since 1.0
 * @version 1.0
 */
public class MD5 {

    private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    /**
     * MD5加密
     * 
     * @since 1.0
     * @param res
     * @return <br>
     *         <b>作者： xychen</b> <br>
     *         创建时间：Jul 25, 2011 1:31:02 PM
     */
    public static String md5(String res) {
        try {
            byte[] strTemp = res.getBytes("UTF-8");
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            String dd = new String(str);
            return dd;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static void main(String[] args	){
    	System.out.println(md5("tranCode=Province&pageno=1&pagesize=20"));
    }

}
