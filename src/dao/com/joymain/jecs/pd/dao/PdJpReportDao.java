package com.joymain.jecs.pd.dao;

import java.util.List;

import com.joymain.jecs.dao.Dao;

public interface PdJpReportDao extends Dao {
	public List getPhReport(String sCreateTime, String eCreateTime);
	public List getProNo(String sCreateTime, String eCreateTime);
	public List getJpReport(String sOrderTime, String eOrderTime);
	public List getTimeList(String sOrderTime, String eOrderTime);
	public List getFeeList(String sOrderTime, String eOrderTime);
}
