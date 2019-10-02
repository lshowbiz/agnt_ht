package com.joymain.jecs.util.date;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.jdbc.core.JdbcTemplate;

import com.joymain.jecs.util.bean.ContextUtil;
import com.joymain.jecs.util.string.StringUtil;


/**
 * Date Utility Class This is used to convert Strings to Dates and Timestamps
 * 
 * <p>
 * <a href="DateUtil.java.html"><i>View Source</i></a>
 * </p>
 * 
 * <p>
 * Description: 日期工具类
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 * 
 * <p>
 * Company: sunrise
 * </p>
 * 
 * @author islph
 * @version 1.0
 */
public class DateUtil {
	// ~ Static fields/initializers
	// =============================================

	private static Log log = LogFactory.getLog(DateUtil.class);

	private static String defaultDatePattern = null;

	private static String timePattern = "HH:mm:ss";

	// ~ Methods
	// ================================================================

	/**
	 * 返回缺省的日期格式 Return default datePattern (yyyy-MM-dd)
	 * 
	 * @return a string representing the date pattern on the UI
	 */
	public static String getDatePattern() {
		Locale locale = LocaleContextHolder.getLocale();
		/*try {
			defaultDatePattern = ResourceBundle.getBundle(Constants.BUNDLE_KEY,
					locale).getString("date.format");
		} catch (MissingResourceException mse) {
			defaultDatePattern = "yyyy-MM-dd";
		}*/
		defaultDatePattern = "yyyy-MM-dd";

		return defaultDatePattern;
	}
	
	/**
	 * 返回缺省的时间格式 yyyy-MM-dd HH:mm:ss.S
	 * @return
	 */
	public static String getDateTimePattern() {
        return DateUtil.getDatePattern() + " HH:mm:ss.S";
    }

	/**
	 * 转换oracle格式的日期从dd-MMM-yyyy到 yyyy-MM-dd This method attempts to convert an
	 * Oracle-formatted date in the form dd-MMM-yyyy to yyyy-MM-dd.
	 * 
	 * @param aDate
	 *            date from database as a string
	 * @return formatted string for the ui
	 */
	public static final String getDate(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(getDatePattern());
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}
	public static String getDate(Date aDate,String format) {
        SimpleDateFormat df;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(format);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }
	/**
	 * 用指定的日期格式把String类型转换为Date类型 This method generates a string representation
	 * of a date/time in the format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param strDate
	 *            a string representation of a date
	 * @return a converted Date object
	 * @see java.text.SimpleDateFormat
	 */
	public static final Date convertStringToDate(String aMask, String strDate) {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);

		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '"
					+ aMask + "'");
		}

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			log.error("ParseException: " + pe);
			log.debug("aMask:" + aMask + "strDate" + strDate);
			// throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}

	/**
	 * 以yyyy-MM-dd HH:MM格式返回当前的日期时间 This method returns the current date time in
	 * the format: yyyy-MM-dd HH:MM a
	 * 
	 * @param theTime
	 *            the current time
	 * @return the current date/time
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(timePattern, theTime);
	}

	/**
	 * 以yyyy-MM-dd格式返回当前的日期 This method returns the current date in the format:
	 * yyyy-MM-dd
	 * 
	 * @return the current date
	 */
	public static Calendar getToday() {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

		// This seems like quite a hack (date -> string -> date),
		// but it works ;-)
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	/**
	 * 取得服务器的当前时间，format格式的字符串
	 * 
	 * @param format
	 *            参数“yyyy-MM-dd HH:mm:ss”
	 * @return 当前时间返回的格式 返回：2005-12-11 10:35:40
	 */
	public static String getToday(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date currentDateTime = new Date();
		return sdf.format(currentDateTime);
	}
	/**
	 * 取得服务器的当前时间
	 
	 * 
	 * @return 当前时间返回的格式 返回：2007年8月23日
	 */
	public static String getCNToday() {
		Date currentDateTime = new Date();
		return (1900+currentDateTime.getYear())+"年"+(1+currentDateTime.getMonth())+"月"+currentDateTime.getDate()+"日";
	}
	/**
	 * 以指定的日期格式String将Date类型转换为String This method generates a string
	 * representation of a date's date/time in the format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param aDate
	 *            a date object
	 * @return a formatted string representation of the date
	 * 
	 * @see java.text.SimpleDateFormat
	 */
	public static final String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			log.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * 以默认的日期格式yyyy-MM-dd将日期Date转换为String This method generates a string
	 * representation of a date based on the System Property 'dateFormat' in the
	 * format you specify on input
	 * 
	 * @param aDate
	 *            A date to convert
	 * @return a string representation of the date
	 */
	public static final String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}

	/**
	 * 以缺省的日期格式yyyy-MM-dd把String转换为Date This method converts a String to a date
	 * using the datePattern
	 * 
	 * @param strDate
	 *            the date to convert (in format MM/dd/yyyy)
	 * @return a date object
	 */
	public static Date convertStringToDate(String strDate) {
		Date aDate = null;

		if (log.isDebugEnabled()) {
			log.debug("converting date with pattern: " + getDatePattern());
		}
		aDate = convertStringToDate(getDatePattern(), strDate);

		return aDate;
	}

	/**
	 * 字符串转换成 Timestamp
	 * 
	 * @param strDate
	 *            2006-08-08 12:12:12.0
	 * @return Date
	 */
	public static Timestamp getTimestamp(String strDate) {
		Timestamp timestamp = null;
		String val = "";
		if (strDate == null) {
			return null;
		}
		if (strDate.length() > 0) {
			if (strDate.length() == 10) {
				val = strDate + " 00:00:00";
			} else if (strDate.indexOf(".0") != -1) {
				val = strDate.substring(0, strDate.length() - 2);
			}
			timestamp = Timestamp.valueOf(val);
		}
		return timestamp;
	}

	/**
	 * 比较当前日期和指定日期 return boolean 如果当前日期在指定日期之前返回true否则返回flase
	 * 
	 * @param str
	 *            String
	 * @return boolean
	 */
	public static boolean dateCompare(String str) {
		boolean bea = false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String isDate = sdf.format(new java.util.Date());
		java.util.Date date1;
		java.util.Date date0;
		try {
			date1 = sdf.parse(str);
			date0 = sdf.parse(isDate);
			if (date0.after(date1)) {
				bea = true;
			}
		} catch (ParseException e) {
			bea = false;
		}
		return bea;
	}

	/**
	 * @return Timestamp
	 */
	public static Timestamp now() {
		Date now = new Date();
		return new Timestamp(now.getTime());
	}

	/**
	 * @return Timestamp
	 */
	public static Timestamp getTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	/**
	 * 取的当前月的上一个月
	 * 
	 * @param curmonth
	 *            200608
	 * @return 200607
	 */
	public static String getLastMonth(String curmonth) {
		String lastMonth = "";
		String month = curmonth.substring(curmonth.length() - 2);
		String year = curmonth.substring(0, 4);
		int temp = StringUtil.formatInt(month) - 1;
		if (temp == 0) {
			month = "12";
			year = (StringUtil.formatInt(year) - 1) + "";
		} else {
			month = StringUtil.leadZeros(temp + "", 2);
		}
		lastMonth = year + curmonth.substring(4, curmonth.length() - 2) + month;
		return lastMonth;
	}

	/**
	 * 取的当前月的下一个月
	 * 
	 * @param curmonth
	 *            200608
	 * @return 200607
	 */
	public static String getNextMonth(String curmonth) {
		String nextMonth = "";
		String month = curmonth.substring(curmonth.length() - 2);
		String year = curmonth.substring(0, 4);
		int temp = StringUtil.formatInt(month) + 1;
		if (temp == 13) {
			month = "01";
			year = (StringUtil.formatInt(year) + 1) + "";
		} else {
			month = StringUtil.leadZeros(temp + "", 2);
		}
		nextMonth = year + curmonth.substring(4, curmonth.length() - 2) + month;
		return nextMonth;
	}

	/**
	 * 得到两个日期之间相差的天数
	 * 
	 * @param newDate
	 *            2006-05-01
	 * @param oldDate
	 *            2006-03-01
	 * @return n 61
	 */
	public static int daysBetweenDates(Date newDate, Date oldDate) {
		int days = 0;
		Calendar calo = Calendar.getInstance();
		Calendar caln = Calendar.getInstance();
		calo.setTime(oldDate);
		caln.setTime(newDate);
		int oday = calo.get(Calendar.DAY_OF_YEAR);
		int nyear = caln.get(Calendar.YEAR);
		int oyear = calo.get(Calendar.YEAR);
		while (nyear > oyear) {
			calo.set(Calendar.MONTH, 11);
			calo.set(Calendar.DATE, 31);
			days = days + calo.get(Calendar.DAY_OF_YEAR);
			oyear = oyear + 1;
			calo.set(Calendar.YEAR, oyear);
			// System.out.println("YEAR:"+calo.get(Calendar.YEAR)+"MONTH:"+calo.get(Calendar.MONTH)+"DAY:"+calo.get(Calendar.DATE));

		}
		int nday = caln.get(Calendar.DAY_OF_YEAR);
		days = days + nday - oday;
		return days;
	}

	/**
	 * 函数体没有完成 两个日期相隔工作日数
	 * 
	 * @param endDate
	 *            2006-05-01
	 * @param beginDate
	 *            2006-03-01
	 * @return n
	 */
	public static int daysBetweenWorkDates(String endDate, String beginDate) {
		//TODO:完成方法体
		return 0;
	}
	
	public static List<String> getMonthsBetween2Mon(String begin, String end,
			String mask) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(mask);
		List<String> list = new ArrayList<String>();
		Calendar calendarBegin = Calendar.getInstance();
		calendarBegin.setTime(convertStringToDate(mask, begin));
		Calendar calendarEnd = Calendar.getInstance();
		calendarEnd.setTime(convertStringToDate(mask, end));
//		System.out.println("calendarEnd.get(Calendar.YEAR)="+calendarEnd.get(Calendar.YEAR));
//		System.out.println("calendarEnd.get(Calendar.MONTH)="+calendarEnd.get(Calendar.MONTH));
		int diff = (calendarEnd.get(Calendar.YEAR) - calendarBegin
				.get(Calendar.YEAR))
				* 12
				+ (calendarEnd.get(Calendar.MONTH) - calendarBegin
						.get(Calendar.MONTH));
//		System.out.println("diff="+diff);
		list.add(begin);
		for(int i=0;i<diff;i++){
			calendarBegin.add(Calendar.MONTH, 1);
			list.add(getDateTime(mask,calendarBegin.getTime()));
		}
		
		return list;
	}

	/**
	 * 取得基础日期date按一定标准偏移后的日期
	 * 
	 * @param date
	 *            基础日期
	 * @param type
	 *            指定日期偏移的标准类型： year-1, Month-2, week-3, Date-5
	 * @param how
	 *            在基日期上偏移多少，正数对应后移 负数对应前移
	 * @return 偏移后的日期
	 */
	public static java.util.Date getDateOffset(java.util.Date date, int type,
			int how) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(type, how);
		return calendar.getTime();
	}

	/**
	 * 
	 * @param dateStr
	 *            ,yyyyMMdd
	 * @param model
	 *            year-1, Month-2, week-3, Date-5
	 * @param n
	 * @return yyyyMMdd
	 */
	public static String add(String dateStr, int model, int n) {
		String reVal = "";

		Date date1 = convertStringToDate("yyyyMMdd", dateStr);
		Date date2 = getDateOffset(date1, model, n);
		reVal = getDateTime("yyyyMMdd", date2);

		return reVal;
	}

	/**
	 * 20060506 -> 2006-05-06
	 */
	public static String str8to10(String date) {
		String datetmp = date.substring(0, 4) + "-" + date.substring(4, 6)
				+ "-" + date.substring(6, 8);
		return datetmp;
	}

	public static String str6to8(String date) {
		if (null != date) {
			return date.substring(0, 2) + ":" + date.substring(2, 4) + ":"
					+ date.substring(4, 6);
		}
		return "";
	}

	/**
	 * 2006-05-06 -> 20060506
	 */
	public static String str10to8(String date) {
		String datetmp = date.substring(0, 4) + date.substring(5, 7)
				+ date.substring(8, 10);
		return datetmp;
	}

	/**
	 * 格式化字符串类型日期, 如:
	 * dateStrFormat("20070309044018","yyyyMMddHHmmss","yyyy-MM-dd hh:mm:ss")
	 * 返回2007-03-09 04:40:18
	 * 
	 * @param dateStr
	 * @param orgFormat
	 * @param newFormat
	 * @return str
	 */
	public static String dateStrFormat(String dateStr, String orgFormat,
			String newFormat) {
		Date date = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat(orgFormat);
			date = format.parse(dateStr);
		} catch (ParseException pe) {
			log.error(pe.getMessage(), pe);
			return dateStr;
		}
		return DateUtil.getDateTime(newFormat, date);
	}
	
	/**
	 * 20070808 -> 20070808000000
	 * 
	 * @param date
	 * @return
	 */
	public static String combineDateTime( String date) {
		String rlt = date;
		
		if ( !StringUtil.blankOrNull( date)) {
			if ( date.length() == 14) {
				rlt = date;
			} else if ( date.length() == 8){
				rlt += "000000";
			}
		}
		
		return rlt;
	}

	/**
	 * 得到两个日期之间相差的天数
	 * 
	 * @param newDate
	 *            2006-05-01
	 * @param oldDate
	 *            2006-03-01
	 * @return n 0
	 */
	public static int dispersionMonth(String strDate1, String strDate2) {
		int iMonth = 0;
		try {
			int nYear, nMonth, nDay;
			nYear = Integer.parseInt(strDate1.substring(0, 4));
			nMonth = Integer.parseInt(strDate1.substring(5, 7));
			nDay = Integer.parseInt(strDate1.substring(8));
			Calendar objCalendarDate1 = Calendar.getInstance();
			objCalendarDate1.set(nYear, nMonth, nDay);
			
			nYear = Integer.parseInt(strDate2.substring(0, 4));
			nMonth = Integer.parseInt(strDate2.substring(5, 7));
			nDay = Integer.parseInt(strDate2.substring(8));
			Calendar objCalendarDate2 = Calendar.getInstance();
			objCalendarDate2.set(nYear, nMonth, nDay);
			
			if (objCalendarDate2.equals(objCalendarDate1))
				return 0;
			if (objCalendarDate2.after(objCalendarDate1)){// 如果strDate2>strDate1
				if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR)) {
					while (objCalendarDate1.get(Calendar.MONTH) != objCalendarDate2.get(Calendar.MONTH)) {
						objCalendarDate1.add(objCalendarDate1.MONTH, 1);
						iMonth++;
					}
					if (objCalendarDate2.get(Calendar.DATE) > objCalendarDate1.get(Calendar.DATE)) {
						iMonth = iMonth + 1;
					}
					iMonth = iMonth + (objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR)) * 12;
				} else {
					while (objCalendarDate1.get(Calendar.MONTH) != objCalendarDate2.get(Calendar.MONTH)) {
						objCalendarDate1.add(objCalendarDate1.MONTH, 1);
						iMonth++;
					}
					if (objCalendarDate2.get(Calendar.DATE) > objCalendarDate1.get(Calendar.DATE)) {
						iMonth = iMonth + 1;
					}
				}
			} else{// 如果strDate1>strDate2
				if (objCalendarDate1.get(Calendar.YEAR) > objCalendarDate2.get(Calendar.YEAR)) {
					while (objCalendarDate1.get(Calendar.MONTH) != objCalendarDate2.get(Calendar.MONTH)) {
						objCalendarDate2.add(objCalendarDate2.MONTH, 1);
						iMonth++;
					}
					if (objCalendarDate1.get(Calendar.DATE) > objCalendarDate2.get(Calendar.DATE)) {
						iMonth = iMonth + 1;
					}
					iMonth = iMonth + (objCalendarDate1.get(Calendar.YEAR) - objCalendarDate2.get(Calendar.YEAR)) * 12;
				} else {
					while (objCalendarDate1.get(Calendar.MONTH) != objCalendarDate2.get(Calendar.MONTH)) {
						objCalendarDate2.add(objCalendarDate2.MONTH, 1);
						iMonth++;
					}
					if (objCalendarDate1.get(Calendar.DATE) > objCalendarDate2.get(Calendar.DATE)) {
						iMonth = iMonth + 1;
					}
				}
				iMonth = iMonth * -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iMonth;
	}

	public List<String> getMonthsBetween2Days(String begainDay,String endDay,String pattern) throws ParseException{
		List<String> ret = new ArrayList();
		/*SimpleDateFormat df = new SimpleDateFormat(pattern);
		Long begain = df.parse(begainDay).getTime();
		Long end = df.parse(endDay).getTime();
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(end-begain);
		cal.get(Calendar.MONTH);*/
//		cal.add(Calendar.DST_OFFSET, end);
		return ret;
	} 
	
	  /**
     * Date 转换为XMLGregorianCalendar类型 
     */
    public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
    	
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        javax.xml.datatype.DatatypeFactory dtf = null;
        try {
            dtf = javax.xml.datatype.DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        XMLGregorianCalendar dateArgs =  dtf.newXMLGregorianCalendar(
        calendar.get(calendar.YEAR),
        calendar.get(calendar.MONTH)+1,
        calendar.get(calendar.DAY_OF_MONTH),
        calendar.get(calendar.HOUR_OF_DAY),
        calendar.get(calendar.MINUTE),
        calendar.get(calendar.SECOND),
        calendar.get(calendar.MILLISECOND),
        calendar.get(calendar.ZONE_OFFSET)/(1000*60));
        return dateArgs;
    }

}
