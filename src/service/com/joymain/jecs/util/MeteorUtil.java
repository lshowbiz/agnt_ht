package com.joymain.jecs.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public final class MeteorUtil {
	Logger logger = Logger.getLogger(MeteorUtil.class);
	
	private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	private static final String DATE_PATTERN = "yyyy-MM-dd";

	private static final String MONTH_PATTERN = "yyyy-MM";

	private static final SimpleDateFormat DATE_FMT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat DATE_YEAR_FMT = new SimpleDateFormat("yyyy");
	private static final SimpleDateFormat DATE_MONTH_FMT = new SimpleDateFormat("yyyy-MM");
	private static final SimpleDateFormat DATE_YMD_FMT = new SimpleDateFormat("yyyy-MM-dd");
	
	
	/**
	 * MAP to  BEAN方法
	 * @param map
	 * @param classType
	 * @return
	 * @throws Exception
	 */
	public static <T> T maptoBean(Map<String, Object> map,Class<T> classType) throws Exception{
		// 取类实例
//		Class classType = Order.class;
		T t = classType.newInstance();
		Field[] fields = classType.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String fieldName = field.getName();
			String fieldValue = null; //页面的字段值数组
			if (map.get(fieldName) != null) {
				fieldValue = String.valueOf(map.get(fieldName));
			} else {
				continue;
			}

			PropertyDescriptor pd = new PropertyDescriptor(field.getName(), classType);
			Method writeMethod = pd.getWriteMethod();
			
			if (StringUtils.isNotBlank(fieldValue)) {
				Object[] args = convertValue(field,fieldValue);
				//执行对象的set方法
				writeMethod.invoke(t, args);
			}
		}
		return t;
	}
	
	/**
	 * 类型换
	 */
	private static Object[] convertValue(Field field, String fieldValue)
			throws Exception {
		Object[] args = null;
		if (field.getType().getName().equals("java.lang.String")) {
			args = new Object[] {fieldValue };
		} else if (field.getType().getName().equals("java.lang.Integer")) {
			args = new Object[] {Integer.parseInt(fieldValue) };
		} else if (field.getType().getName().equals("java.math.BigDecimal")) {
			args = new Object[] {new BigDecimal(fieldValue) };
		} else if (field.getType().getName().equals("java.lang.Double")) {
			args = new Object[] {new Double(fieldValue) };
		} else if (field.getType().getName().equals("java.util.Date")) {
			args = new Object[] {doConvertToDate(fieldValue) };
		}
		return args;
	}
	
	/**
     * 将Json对象转换成Map
     * 
     * @param jsonObject
     *            json对象
     * @return Map对象
     * @throws JSONException
     */
    public static Map toMap(String jsonString) throws JSONException {

        JSONObject jsonObject = new JSONObject(jsonString);
        
        Map result = new HashMap();
        Iterator iterator = jsonObject.keys();
        String key = null;
        String value = null;
        
        while (iterator.hasNext()) {

            key = (String) iterator.next();
            value = jsonObject.getString(key);
            System.out.println(key+"============"+value);
            result.put(key, value);

        }
        return result;
    }
    
	/**
	 * String 转换为日期类型
	 * @param value
	 * @return
	 **/
	public static Date doConvertToDateObject(Object value) throws Exception {
		Date result = null;

		if (value instanceof String) {
			result = DateUtils.parseDate((String) value, new String[] {
					DATE_PATTERN, DATETIME_PATTERN, MONTH_PATTERN });
			if (result == null && StringUtils.isNotEmpty((String) value)) {
				try {
					result = new Date(new Long((String) value).longValue());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if (value instanceof Object[]) {
			Object[] array = (Object[]) value;
			if ((array != null) && (array.length >= 1)) {
				value = array[0];
				result = doConvertToDateObject(value);
			}
		} else if (Date.class.isAssignableFrom(value.getClass())) {
			result = (Date) value;
		}

		return result;
	}
	
	/**
	 *  日期类型 转换为 String 年月日,时分秒
	 * @param value
	 * @return
	 **/
	public static String doDateToConvert(Date date) throws Exception {
		return DATE_FMT.format(date);
	}
	/**
	 *  日期类型 转换为 String 年
	 * @param value
	 * @return
	 **/
	public static String doDateToConvertYear(Date date) throws Exception {
		return DATE_YEAR_FMT.format(date);
	}
	/**
	 *  日期类型 转换为 String 年-月
	 * @param value
	 * @return
	 **/
	public static String doDateToConvertMonth(Date date) throws Exception {
		return DATE_MONTH_FMT.format(date);
	}
	
	/**
	 *   String转换为日期类型  年月日
	 * @param value
	 * @return
	 **/
	public static Date doConvertToDateYMD(String str) throws Exception {
		return DATE_YMD_FMT.parse(str);
	}
	
	/**
	 *   String转换为日期类型  年月日,时分秒
	 * @param value
	 * @return
	 **/
	public static Date doConvertToDate(String str) throws Exception {
		return DATE_FMT.parse(str);
	}
	
	/**
	 *  String转换为日期类型 年
	 * @param value
	 * @return
	 **/
	public static Date doConvertToDateYear(String str) throws Exception {
		return DATE_YEAR_FMT.parse(str);
	}
	/**
	 *  String转换为日期类型   年-月
	 * @param value
	 * @return
	 **/
	public static Date doConvertToDateMonth(String str) throws Exception {
		return DATE_MONTH_FMT.parse(str);
	}
	
	/**
	 *  判断字符串是否为空
	 * @param value
	 * @return
	 **/
	public static boolean isBlank(String str){
		if(null==str || "".equals(str)){
			return true;
		}
		return false;
	}
	
	/**
	 * @Description 字符串去除最后以为
	 * @author houxyu
	 * @date 2016-8-4
	 * @param str
	 * @return
	 */
	public static String rvLastOne(String str){
		if(null==str || "".equals(str)){
			return "";
		}
		return  str.substring(0,str.length()-1);
	}
	
	/**
	 *  判断字符串是否为空
	 * @param value
	 * @return
	 **/
	public static boolean isBlankByList(List list){
		if(null==list || list.size()==0){
			return true;
		}
		return false;
	}
	
	/**
	 * 把值转换为百分比
	 * @param d	转换值
	 * @param precision 精度
	 * @return
	 */
	public static String getMinimumFractionDigit(Double d,Integer precision){
		//获取格式化对象
	    NumberFormat nt = NumberFormat.getPercentInstance();
	    //设置百分数精确度2即保留两位小数
	    nt.setMinimumFractionDigits(precision);
	    
	    return nt.format(d);
	}
	
	/**
     * 判断数组中是否包含这个值
     * @param arr
     * @param targetValue
     * @return
     */
    public static boolean useList(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }
    
    /**
     * 判断list是否为空，为空返回真
     * @param list
     * @return
     */
    public static boolean isEmptyList(List list){
    	if(null!=list && list.size()>0){
    		return false;
    	}
    	return true;
    }
    /**
     * 判断字符串长度是否超出，超出为假
     * @param str
     * @param len
     * @return
     */
    public static boolean isStrLen(String str,Integer len){
    	if(MeteorUtil.isNull(str)){
    		return true;//字符串为空，不验证长度 返回真
    	}
    	if(MeteorUtil.length(str)<=len){
    		return true;
    	}
    	return false;
    }
    
    /**  
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1  
     * @param String s 需要得到长度的字符串  
     * @return int 得到的字符串长度  
     */   
    public static int length(String s) {  
        if (s == null)  
            return 0;  
        char[] c = s.toCharArray();  
        int len = 0;  
        for (int i = 0; i < c.length; i++) {  
            len++;  
            if (!isLetter(c[i])) {  
                len++;  
            }  
        }  
        return len;  
    }
    
    /** 
     * 判断字符串是否为空 
     * @param str 
     * @return 
     */  
    public static boolean isNull(String str){  
        if(str==null||str.trim().equals("")||str.trim().equalsIgnoreCase("null")){  
            return true;  
        }else{  
            return false;  
        }  
    }
    
    /** 
     * 判断集合是否为空 
     * @param str 
     * @return 
     */  
    public static boolean isListNull(List list){  
        if(null!=list && list.size()>0){  
            return false;  
        }else{  
            return true;  
        }  
    }
    
    /**
     * 判断是否为中文，中文返回false
     * @param c
     * @return
     */
    public static boolean isLetter(char c) {   
        int k = 0x80;   
        return c / k == 0 ? true : false;   
    }
    
    /**
     * @Description 判断数组中字符串元素是否有重复
     * @author houxyu
     * @date 2016-3-28
     * @param arry
     * @return
     */
	public static boolean isRepeatByArray(String[] arry) {
		boolean flag = false;
		for (int i = 0; i < arry.length; i++) {
			String temp = arry[i];
			int count = 0;
			for (int j = 0; j < arry.length; j++) {
				String temp2 = arry[j];
				// 有重复值就count+1
				if (temp.equals(temp2)) {
					count++;
				}
			}
			// 由于中间又一次会跟自己本身比较所有这里要判断count>=2
			if (count >= 2) {
				flag = true;
			}
		}
		
		return flag;
	}
	
	/**
	 * @Description 去除数组重复记录
	 * @author houxyu
	 * @date 2016-6-3
	 * @param a
	 * @return
	 */
	public static String[] array_unique(String[] a) {  
	    // array_unique  
	    List<String> list = new LinkedList<String>();  
	    for(int i = 0; i < a.length; i++) {  
	        if(!list.contains(a[i])) {  
	            list.add(a[i]);  
	        }  
	    }  
	    return (String[])list.toArray(new String[list.size()]);  
	}
	
	
	public static boolean isInteger(Object o){
		try{
			Integer.valueOf(String.valueOf(o));
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	/**
	 * @Description 最多2位小数
	 * @author houxyu
	 * @date 2016-3-31
	 * @param o
	 * @return
	 */
	public static boolean is2xiaoshu(Object o){
		String regEx1 = "^[0-9]+(.[0-9]{0,2})?$";
		try{
			Float.valueOf(String.valueOf(o));
			
			return isMatch(regEx1,String.valueOf(o));
		}catch(Exception e){
			return false;
		}
	}
	/**
	 * @Description 验证数据是否合法
	 * @author houxyu
	 * @date 2016-3-31
	 * @param regex 正则表达式
	 * @param orginal 输入值
	 * @return
	 */
	private static boolean isMatch(String regex, String orginal){  
        if (orginal == null || orginal.trim().equals("")) {  
            return false;  
        }  
        Pattern pattern = Pattern.compile(regex);  
        Matcher isNum = pattern.matcher(orginal);  
        return isNum.matches();  
    }

	public static void main(String[] args) {
//		System.out.println(getMinimumFractionDigit(0d,2));
//		System.out.println(Double.valueOf(0)/Double.valueOf(0));
		try {
//			System.out.println(MeteorUtil.doConvertToDateObject("2013-03-14 12:12:13"));
//			String s = "P04010300101CN0,P04020100101CN0,P04020200101CN0,P04030300101CN0,P04030300101CN0,P04030400101CN0,P05050100101CN0,P19010100101CN0,P04010115001CN0,P04010118001CN0,P04010119001CN0,";
			String s = "1,2,3,4,5,67,7,6,";
			System.out.println(MeteorUtil.isRepeatByArray(s.split(",")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

