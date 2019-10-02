package com.joymain.jecs.bd.dao;

import java.util.List;

import com.joymain.jecs.bd.model.JbdDayCustRecommend;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;


public interface JbdDayCustRecommendDao extends Dao {

	//	public List getPiProductsByHql(String hql);

	//	public List getPiProductsBySql(String sql);
	/**
	 * Retrieves all of the jbdDayCustRecommends
	 */
	public List getJbdDayCustRecommends(JbdDayCustRecommend jbdDayCustRecommend);

	/**
	 * Gets jbdDayCustRecommend's information based on primary key. An ObjectRetrievalFailureException Runtime Exception is thrown if nothing is found.
	 * 
	 * @param id the jbdDayCustRecommend's id
	 * @return jbdDayCustRecommend populated jbdDayCustRecommend object
	 */
	public JbdDayCustRecommend getJbdDayCustRecommend(final Long id);

	/**
	 * Saves a jbdDayCustRecommend's information
	 * @param jbdDayCustRecommend the object to be saved
	 */
	public void saveJbdDayCustRecommend(JbdDayCustRecommend jbdDayCustRecommend);

	public void saveJbdDayCustRecommends(List jbdDayCustRecommends);


	//added for getJbdDayCustRecommendsByCrm
	public List getJbdDayCustRecommendsByCrm(CommonRecord crm, Pager pager);


	public List getJbdDayCustRecommendsByCrmBySql(CommonRecord crm, Pager pager);
}
