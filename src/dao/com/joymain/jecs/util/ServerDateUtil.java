package com.joymain.jecs.util;

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

import com.joymain.jecs.Constants;
import com.joymain.jecs.util.bean.ContextUtil;
import com.joymain.jecs.util.string.StringUtil;
/**
 * 获取数据库服务器当前时间工具类
 * @author hywen
 *
 */
public class ServerDateUtil {
	
	protected static JdbcTemplate jdbcTemplate;

	/**
	 * 获取数据库的当前时间
	 * @return
	 */
	public static Date getNowTimeFromDateServer(){
		
		jdbcTemplate = (JdbcTemplate) ContextUtil.getSpringBeanByName(Constants.context, "jdbcTemplate");
		
		Map map = null;
		List recordSet = jdbcTemplate.queryForList("SELECT SYSDATE FROM DUAL");
		
		if (recordSet != null && recordSet.size() > 0) {
			map = (Map) recordSet.get(0);
		}
		
		Date nowDate=(Date) map.get("SYSDATE");
		
		return nowDate;
	}
}
