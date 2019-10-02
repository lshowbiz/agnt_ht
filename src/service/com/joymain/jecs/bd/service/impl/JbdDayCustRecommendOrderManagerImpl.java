package com.joymain.jecs.bd.service.impl;

import java.util.List;

import com.joymain.jecs.bd.dao.JbdDayCustRecommendOrderDao;
import com.joymain.jecs.bd.model.JbdDayCustRecommendOrder;
import com.joymain.jecs.bd.service.JbdDayCustRecommendOrderManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JbdDayCustRecommendOrderManagerImpl extends BaseManager implements JbdDayCustRecommendOrderManager {
	private JbdDayCustRecommendOrderDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * @param dao
	 */
	public void setJbdDayCustRecommendOrderDao(JbdDayCustRecommendOrderDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.bd.service.JbdDayCustRecommendOrderManager#getJbdDayCustRecommendOrders(com.joymain.jecs.bd.model.JbdDayCustRecommendOrder)
	 */
	public List getJbdDayCustRecommendOrders(final JbdDayCustRecommendOrder jbdDayCustRecommendOrder) {
		return dao.getJbdDayCustRecommendOrders(jbdDayCustRecommendOrder);
	}

	/**
	 * @see com.joymain.jecs.bd.service.JbdDayCustRecommendOrderManager#getJbdDayCustRecommendOrder(String id)
	 */
	public JbdDayCustRecommendOrder getJbdDayCustRecommendOrder(final String id) {
		return dao.getJbdDayCustRecommendOrder(new Long(id));
	}

	public List getJbdDayCustRecommendOrdersByCrmBySql(CommonRecord crm, Pager pager) {
		return dao.getJbdDayCustRecommendOrdersByCrmBySql(crm, pager);
	}

	@Override
	public List getJbdDayCustRecommendOrdersByCrmByHql(CommonRecord crm, Pager pager) {
		return dao.getJbdDayCustRecommendOrdersByCrmByHql(crm, pager);
	}
}
