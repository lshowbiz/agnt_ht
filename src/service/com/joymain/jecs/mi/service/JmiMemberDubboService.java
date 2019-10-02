package com.joymain.jecs.mi.service;

import java.util.List;
import java.util.Map;

public interface JmiMemberDubboService {
	public String getTest();
	public List getCheckJmiMember(Map<String,String> map);
	public String getTeamType(String recommendNo);
	
	public String teamStr(String userCode);
}
