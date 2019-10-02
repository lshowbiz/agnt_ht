package com.joymain.jecs.sys.dao;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.sys.model.SysPowerCategory;

public interface SysPowerCategoryDao extends Dao {
	
	public List getAllSysPowerCategorys();

	public int getSubPowerCategoryCounts(String powerCategoryId);

	public SysPowerCategory getSysPowerCategory(String powerCategoryId) throws ObjectRetrievalFailureException;

	public void saveSysPowerCategory(SysPowerCategory sysPowerCategory);

	public void removeSysPowerCategory(String powerCategoryId) throws ObjectRetrievalFailureException;
}
