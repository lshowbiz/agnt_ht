package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.bd.model.JbdDayCustRecommend;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdDayCustRecommendManager extends Manager {
	/**
	 * Retrieves all of the jbdDayCustRecommends
	 */
	public List getJbdDayCustRecommends(JbdDayCustRecommend jbdDayCustRecommend);

	/**
	 * Gets jbdDayCustRecommend's information based on id.
	 * @param id the jbdDayCustRecommend's id
	 * @return jbdDayCustRecommend populated jbdDayCustRecommend object
	 */
	public JbdDayCustRecommend getJbdDayCustRecommend(final String id);

	/**
	 * Saves a jbdDayCustRecommend's information
	 * @param jbdDayCustRecommend the object to be saved
	 */
	public void saveJbdDayCustRecommend(JbdDayCustRecommend jbdDayCustRecommend);

	public void saveJbdDayCustRecommends(List jbdDayCustRecommends);

	//added for getJbdDayCustRecommendsByCrm
	public List getJbdDayCustRecommendsByCrm(CommonRecord crm, Pager pager);

	public List getJbdDayCustRecommendsByCrmBySql(CommonRecord crm, Pager pager) ;
	
    public void saveInJdFiBook(SysUser defSysUser,String id);
}
