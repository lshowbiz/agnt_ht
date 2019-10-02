package com.joymain.jecs.util.math;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 *
 * <p>Title: is a Class</p>
 *
 * <p>Description:大数字以及数学方面的运算工具类 </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: sunrise</p>
 *
 * @author wangquan
 * @version 1.0
 */
public class MathUtil {

    private static final int DEF_DIV_SCALE = 10;
    /**
     * 取一个浮点数a的小数点后b位的四舍五入
     * @param v      3.14159
     * @param scale      2
     * @return double  3.14
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精确度必须是正大于或等于零的整型数");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

    }
    /**
     * 提供精确的加法运算。
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */

    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }
    /**
     * 提供精确的减法运算。
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */

    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }
    /**
     * 提供精确的乘法运算。
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }
    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到小数点以后10位，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */

    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);

    }
    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */

    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精确度必须是正大于或等于零的整型数");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    //bit是从每0位开始计算
    public static boolean GetBit(int Value, int bit) {

        int newValue = (Value >> bit) & 1;
        return (newValue == 1);

    }
    /**
     *    translante an int to 人民币大写形式
     *	@param	money , money want to translate
     *	@return String[] the return array . 从"分" 到 "亿"
     **/
    public static String[] moneyToRMB(long money) {
        String[] returnStr = {
                "", "", "", "", "", "", "", "", "", ""};
        int i, j, index;
        long mod, result;
        String[] RMB = {
                "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};

        for (i = 0; i < 10; i++) {
            try {
                byte[] byteTmp = RMB[i].getBytes("GB2312");
                RMB[i] = new String(byteTmp, "8859_1");
            }
            catch (Exception e) {
                System.out.println(" MathUtil.java.moneyToRMB() error" +
                        e.toString());
                e.printStackTrace();
            }
        }

        for (i = 0; i < 10; i++) {
            mod = 1;
            for (j = 0; j < (9 - i); j++)
                mod = mod * 10;

            result = money / mod;
            Long tmp = new Long(result);
            index = tmp.intValue();

            if (result == 0) {
                returnStr[9 - i] = RMB[0];
            }
            else {
                try {
                    returnStr[9 - i] = RMB[index];
                    money = money - mod * result;
                }
                catch (Exception e) {
                    System.out.println(" MathUtil.java.moneyToRMB() error" +
                            e.toString());
                    e.printStackTrace();
                }
            }
        }
        return returnStr;
    }

    /**
     * 将数字字符转换为大写字符。
     * @param lower “3”
     * @return      “参”
     */
    public static String lowerToUpper(String lower) {
	if (lower.equals("1")) {
	    return "壹";
	}
	if (lower.equals("2")) {
	    return "贰";
	}
	if (lower.equals("3")) {
	    return "叁";
	}
	if (lower.equals("4")) {
	    return "肆";
	}
	if (lower.equals("5")) {
	    return "伍";
	}
	if (lower.equals("6")) {
	    return "陆";
	}
	if (lower.equals("7")) {
	    return "柒";
	}
	if (lower.equals("8")) {
	    return "捌";
	}
	if (lower.equals("9")) {
	    return "玖";
	}
	if (lower.equals("0")) {
	    return "零";
	}

	return "";
    }
    public static String getUnit4(String str4) throws Exception {
	if (str4.length() > 4)
	    throw new Exception("数据长度必须小于等于4");
	String rStr = "";
	if (str4.equals("0000"))
	    return "零";
	int str4Len = str4.length();
	int i = 0;
	while (str4Len > 0) {
	    char ch = str4.charAt(i);
	    String chStr = lowerToUpper("" + ch);
	    switch (str4Len) {
		case 1:
		    if (chStr.equals("零"))
			chStr = "";
		    break;
		case 2:
		    if (!chStr.equals("零"))
			chStr = chStr + "拾";
		    break;
		case 3:
		    if (!chStr.equals("零"))
			chStr = chStr + "佰";
		    break;
		case 4:
		    if (!chStr.equals("零"))
			chStr = chStr + "千";
		    break;
	    }
	    rStr = rStr + chStr;
	    str4Len--;
	    i++;
	}
	return rStr;
    }

    public static String toBig(String str) throws Exception {
	String rStr = "";
	String str4Upper = "";
	int dot = str.indexOf(".");
	String xiao = "";
	String zheng = "";
	if (dot != -1) {
	    zheng = str.substring(0, dot);
	    xiao = str.substring(dot + 1);
	}
	else {
	    zheng = str;
	    xiao = "";
	}
	int zhengLen = zheng.length();
	int phase = zhengLen / 4;
	if (zhengLen % 4 != 0)
	    phase++;
	int i = 0;
	int endDot = zhengLen;
	int startDot = 0;
	int lastEndDot = -1;
	while (phase > 0) {
	    if (lastEndDot != -1) {
		endDot = lastEndDot - 4;
		if (endDot < 0)
		    endDot = 0;
	    }
	    else {
		endDot = phase * 4;
		if (endDot > zhengLen)
		    endDot = zhengLen;
	    }
	    lastEndDot = endDot;
	    startDot = endDot - 4;
	    if (startDot < 0)
		startDot = 0;
	    String str4 = zheng.substring(startDot, endDot);
	    str4Upper = getUnit4(str4);
	    int yi = i / 2;
	    String wan = "";
	    if (i % 2 == 1)
		wan = "万";
	    String yiStr = "";
	    while (yi > 0) {
		yiStr = yiStr + "亿";
		yi--;
	    }
	    yiStr = wan + yiStr;
	    while (str4Upper.endsWith("零") && str4Upper.length() > 1) {
		str4Upper = str4Upper.substring(0, str4Upper.length() - 1);
	    }
	    if (!str4Upper.equals("零")) {
		str4Upper = str4Upper + yiStr;
	    }
	    rStr = str4Upper + rStr;
	    i++;
	    phase--;
	}
	int xiaoLen = xiao.length();
	if (xiaoLen > 4)
	    xiao = xiao.substring(0, 4);
	xiaoLen = xiao.length();
	String xiaoUpper = "";
	for (i = 0; i < xiaoLen; i++) {
	    char ch = xiao.charAt(i);
	    String chStr = lowerToUpper("" + ch);
	    if (!chStr.equals("零"))
		switch (i) {
		    case 0:
			chStr = chStr + "角";
			break;
		    case 1:
			chStr = chStr + "分";
			break;
		    case 2:
			chStr = chStr + "厘";
			break;
		    case 3:
			chStr = chStr + "毫";
			break;
		}
	    else
		chStr = "";
	    xiaoUpper = xiaoUpper + chStr;
	}
	while (rStr.endsWith("零"))
	    rStr = rStr.substring(0, rStr.length() - 1);
	while (rStr.startsWith("零"))
	    rStr = rStr.substring(1, rStr.length());
	rStr = rStr + "元" + xiaoUpper;
	int lastYi = rStr.lastIndexOf("亿");

	if (lastYi != -1) {
	    String tempStr = rStr.substring(0, lastYi);
	    String tempStr2 = rStr.substring(lastYi);
	    while (tempStr.endsWith("亿")) {
		tempStr2 = "亿" + tempStr2;
		tempStr = rStr.substring(0, tempStr.length() - 1);
	    }
	    tempStr = delOneSameChar(tempStr, '1');
	    rStr = tempStr + tempStr2;
	}
	while (rStr.indexOf("零零") != -1)
	    rStr = rStr.replaceAll("零零", "零");
	if (rStr.startsWith("元"))
	    rStr = "零" + rStr;
	return rStr;
    }
    public static String delOneSameChar(String str, char innerCh) {
	String rStr = "";
	String tempStr = str;
	StringBuffer sb = new StringBuffer(tempStr);
	char ch;
	boolean delCh = true;
	for (int i = 0; i < sb.length(); i++) {
	    ch = sb.charAt(i);
	    if (ch == innerCh && delCh) {
		sb.deleteCharAt(i);
		delCh = false;
	    }
	    else {
		if (ch == innerCh && !delCh)
		    delCh = false;
		else
		    delCh = true;
	    }
	}
	rStr = sb.toString();
	return rStr;
    }
    public static void main(String[] args){
        long l = 123456776;
        System.out.println(moneyToRMB(l));
    }
  //判断是否小数点后2为的数字
  	public static Boolean isDecimal(String amount) {
  		Pattern pDate = Pattern.compile("^(([1-9]\\d{0,9})|0)(\\.\\d{1,2})?$");
  		return pDate.matcher(amount).matches();
  	}
}
