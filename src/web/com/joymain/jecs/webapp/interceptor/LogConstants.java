package com.joymain.jecs.webapp.interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.sys.model.SysClickLog;
import com.joymain.jecs.sys.model.SysDataLog;
import com.joymain.jecs.sys.model.SysId;
import com.joymain.jecs.sys.model.SysOperationLog;
import com.joymain.jecs.sys.model.SysVisitLog;

public class LogConstants {
	public static List black_list=new ArrayList();
	public static Map tableMap = new HashMap();
	public static Map<String,String> TABLE_PID_MAP = new HashMap();
	public static Map<String,String> TABLE_BID_MAP = new HashMap();
	
	static{
		black_list.add(SysDataLog.class);
		black_list.add(SysClickLog.class);
		black_list.add(SysId.class);
		black_list.add(SysOperationLog.class);
		black_list.add(SysVisitLog.class);
		
	}
}
