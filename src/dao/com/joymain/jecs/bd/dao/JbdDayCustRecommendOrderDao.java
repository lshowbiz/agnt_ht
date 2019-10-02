package com.joymain.jecs.bd.dao;

import java.util.List;

import com.joymain.jecs.bd.model.JbdDayCustRecommendOrder;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;


public interface JbdDayCustRecommendOrderDao extends Dao {

	//	public List getPiProductsByHql(String hql);

	//	public List getPiProductsBySql(String sql);
	/**
	 * Retrieves all of the jbdDayCustRecommendOrders
	 */
	public List getJbdDayCustRecommendOrders(JbdDayCustRecommendOrder jbdDayCustRecommendOrder);

	/**
	 * Gets jbdDayCustRecommendOrder's information based on primary key. An ObjectRetrievalFailureException Runtime Exception is thrown if nothing is found.
	 * 
	 * @param id the jbdDayCustRecommendOrder's id
	 * @return jbdDayCustRecommendOrder populated jbdDayCustRecommendOrder object
	 */
	public JbdDayCustRecommendOrder getJbdDayCustRecommendOrder(final Long id);


	//added for getJbdDayCustRecommendOrdersByCrm
//	public List getJbdDayCustRecommendOrdersByCrm(CommonRecord crm, Pager pager);


	public List getJbdDayCustRecommendOrdersByCrmBySql(CommonRecord crm, Pager pager);
	public List getJbdDayCustRecommendOrdersByCrmByHql(CommonRecord crm, Pager pager);
}
