package com.joymain.jecs.po.dao;

import java.util.Date;
import java.util.List;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.po.model.JpoBankBookPayList;

public interface JpoBankBookPayListDao extends Dao{
	/**
	 * get entity by orderNo
	 * @param orderNo
	 * @return
	 */
	public List<JpoBankBookPayList> getBankBookPayListByOrderNo(String orderNo);
	
	public Date getDBdate();
	public void saveJpoBankBookPayListList(List<JpoBankBookPayList> list);
}
