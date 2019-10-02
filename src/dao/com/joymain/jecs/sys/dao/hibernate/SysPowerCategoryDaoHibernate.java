package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.dao.SysPowerCategoryDao;
import com.joymain.jecs.sys.model.SysPowerCategory;

public class SysPowerCategoryDaoHibernate extends BaseDaoHibernate implements SysPowerCategoryDao {



	public List getAllSysPowerCategorys() {
		DetachedCriteria ca = DetachedCriteria.forClass(SysPowerCategory.class);
		return getHibernateTemplate().findByCriteria(ca);
	}

	public int getSubPowerCategoryCounts(String powerCategoryId) {
		String hqlQuery = "from SysPowerCategory where parentId=" + powerCategoryId;
		return getTotalCountByHQL(hqlQuery);
	}

	public SysPowerCategory getSysPowerCategory(String powerCategoryId) throws ObjectRetrievalFailureException {
		SysPowerCategory sysPowerCategory = (SysPowerCategory) getHibernateTemplate().get(SysPowerCategory.class, new Long(powerCategoryId));
		if (sysPowerCategory == null) {
			log.warn("uh oh, sysPowerCategory with powerCategoryId '" + powerCategoryId + "' not found...");
			throw new ObjectRetrievalFailureException(SysPowerCategory.class, powerCategoryId);
		}
		return sysPowerCategory;
	}

	public void saveSysPowerCategory(SysPowerCategory sysPowerCategory) {

		getHibernateTemplate().saveOrUpdate(sysPowerCategory);
	}

	public void removeSysPowerCategory(String powerCategoryId) throws ObjectRetrievalFailureException {
		try {
			getHibernateTemplate().delete(getSysPowerCategory(powerCategoryId));
		} catch (ObjectRetrievalFailureException e) {
			throw e;
		}
	}
}
