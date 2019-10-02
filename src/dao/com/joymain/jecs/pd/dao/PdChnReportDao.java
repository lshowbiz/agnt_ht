package com.joymain.jecs.pd.dao;

import java.util.List;

import com.joymain.jecs.dao.Dao;

public interface PdChnReportDao extends Dao {
	public List getChnReport();
	public List getChnQtyReport();

}
