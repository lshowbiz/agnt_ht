package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;

public interface PdChnReportManager extends Manager {
	public List getChnReportByCrm(CommonRecord crm);
}
