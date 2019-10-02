package com.joymain.jecs.util.chinapnr;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.fi.model.JfiChinapnrLog;

public interface ChinapnrUtil {
	
	public JfiChinapnrLog getJfiChinapnrLog(HttpServletRequest request,String userCode,String companyCode);
}
