package com.joymain.jecs.webapp.taglib;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.util.string.StringUtil;
/**
 * Tag for creating multiple &lt;select&gt; options for displaying a list of
 * company names.
 *
 * <p>
 * <b>NOTE</b> - This tag requires a Java2 (JDK 1.2 or later) platform.
 * </p>
 *
 * @author Alvin
 * @version 
 *
 * @jsp.tag name="period" bodycontent="empty"
 */
public class PeriodTag extends TagSupport {
	private String dateStr;

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		if(StringUtil.isEmpty(this.dateStr)){
			return this.SKIP_BODY;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = simpleDateFormat.parse(this.dateStr);
			long dateLong = date.getTime();
			for (int i = 0 ; i < Constants.periodList.size() ; i++){
				BdPeriod bdPeriod = (BdPeriod)Constants.periodList.get(i);
				long startTime = bdPeriod.getStartTime().getTime();
				long endTime = bdPeriod.getEndTime().getTime();
				if(dateLong>=startTime && dateLong<endTime){
					String strPeriod = "";
					String Fyeer =  bdPeriod.getFyear().toString();
					String Fmonth =  bdPeriod.getFmonth().toString();
					String Fweek =  bdPeriod.getFweek().toString();
					if(Fmonth.length() == 1){
						Fmonth = "0"+Fmonth;
					}
					if(Fweek.length() == 1){
						Fweek = "0"+Fweek;
					}
					strPeriod = Fyeer+Fmonth+Fweek;
				
					this.pageContext.getOut().write(strPeriod);
					return this.SKIP_BODY;
				}
			}
		} catch (IOException io) {
			// TODO Auto-generated catch block
			throw new JspException(io);
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.SKIP_BODY;
	}
	
	
}
