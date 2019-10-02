package com.joymain.jecs.util.string;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 货币转换类 This class is converts a Double to a double-digit String (and vise-versa) by BeanUtils when copying properties. Registered for use in BaseAction.
 * 
 * <p>
 * <a href="CurrencyConverter.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class CurrencyConverter implements Converter {
	protected final Log log = LogFactory.getLog(CurrencyConverter.class);
	protected final DecimalFormat formatter = new DecimalFormat("###,###.00");

	/**
	 * Convert a String to a Double and a Double to a String
	 * 
	 * @param type the class type to output
	 * @param value the object to convert
	 * @return object the converted object (Double or String)
	 */
	public final Object convert(final Class type, final Object value) {
		// for a null value, return null
		if (value == null) {
			return null;
		} else {
			if (value instanceof String) {
				if (log.isDebugEnabled()) {
					log.debug("value (" + value + ") instance of String");
				}

				try {
					if (StringUtils.isBlank(String.valueOf(value))) {
						return null;
					}

					if (log.isDebugEnabled()) {
						log.debug("converting '" + value + "' to a decimal");
					}

					//formatter.setDecimalSeparatorAlwaysShown(true);
					Number num = formatter.parse(String.valueOf(value));

					return new Double(num.doubleValue());
				} catch (ParseException pe) {
					pe.printStackTrace();
				}
			} else if (value instanceof Double) {
				if (log.isDebugEnabled()) {
					log.debug("value (" + value + ") instance of Double");
					log.debug("returning double: " + formatter.format(value));
				}

				return formatter.format(value);
			}
		}

		throw new ConversionException("Could not convert " + value + " to " + type.getName() + "!");
	}

	/**
	 * 将数字转化为泰文格式,只支持小数点后两位
	 * @param inNumber
	 * @return
	 */
	public static String bahttext(String inNumber) {
		DecimalFormat fmt = new DecimalFormat("#####0.00");
		inNumber=fmt.format(new BigDecimal(inNumber));
		String[] txtnum1 = new String[] { "ศูนย์", "หนึ่ง", "สอง", "สาม", "สี่", "ห้า", "หก", "เจ็ด", "แปด", "เก้า", "สิบ" };
		String[] txtnum2 = new String[] { "", "สิบ", "ร้อย", "พัน", "หมื่น", "แสน", "ล้าน" };
		inNumber = StringUtils.replace(inNumber, ",", "");
		inNumber = StringUtils.replace(inNumber, " ", "");
		inNumber = StringUtils.replace(inNumber, "บาท", "");

		String[] numbers = StringUtils.split(inNumber, ".");
		if (numbers.length > 2) {
			return "ทศนิยมมีได้เพียง 2 ตัวนะจ๊ะ";
		}
		int strlen = numbers[0].length();
		String convert = "";
		for (int i = 0; i < strlen; i++) {
			int n = Integer.parseInt(StringUtils.substring(numbers[0], i, i + 1));
			if (n != 0) {
				if (i == (strlen - 1) && n == 1) {
					convert += "เอ็ด";
				} else if (i == (strlen - 2) && n == 2) {
					convert += "ยี่";
				} else if (i == (strlen - 2) && n == 1) {
					convert += "";
				} else {
					convert += txtnum1[n];
				}
				if ((strlen - i - 1) > txtnum2.length - 1) {
					convert += "";
				} else {
					convert += txtnum2[strlen - i - 1];
				}
			}
		}
		convert += "บาท";
		if ("0".equals(numbers[1]) || "00".equals(numbers[1]) || "".equals(numbers[1])) {
			convert += "ถ้วน";
		} else {
			strlen = numbers[1].length();
			for (int i = 0; i < strlen; i++) {
				int n = Integer.parseInt(StringUtils.substring(numbers[1], i, i + 1));
				if (n != 0) {
					if (i == (strlen - 1) && n == 1) {
						convert += "เอ็ด";
					} else if (i == (strlen - 2) && n == 2) {
						convert += "ยี่";
					} else if (i == (strlen - 2) && n == 1) {
						convert += "";
					} else {
						convert += txtnum1[n];
					}
					convert += txtnum2[strlen - i - 1];
				}
			}
			if ("0".equals(StringUtils.substring(numbers[1], 1, 2)))
				convert += "สิบ";
			convert += "สตางค์";
		}
		return convert;
	}
}
