package com.joymain.jecs.pd.service;

import java.util.List;
import java.util.Map;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;

public interface PdJpReportManager extends Manager {
	public List getJpReportByCrm(CommonRecord crm);
	public List getPhReportByCrm(CommonRecord crm);
	public Map<String, Long> getQty();
}
