
package com.joymain.jecs.mi.service;

import java.util.List;
import java.util.Map;

import com.joymain.jecs.mi.model.JmiRecommendRef;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiRecommendRefManager extends Manager {
    /**
     * Retrieves all of the jmiRecommendRefs
     */
    public List getJmiRecommendRefs(JmiRecommendRef jmiRecommendRef);

    /**
     * Gets jmiRecommendRef's information based on memberNo.
     * @param memberNo the jmiRecommendRef's memberNo
     * @return jmiRecommendRef populated jmiRecommendRef object
     */
    public JmiRecommendRef getJmiRecommendRef(final String memberNo);

    /**
     * Saves a jmiRecommendRef's information
     * @param jmiRecommendRef the object to be saved
     */
    public void saveJmiRecommendRef(JmiRecommendRef jmiRecommendRef);

    /**
     * Removes a jmiRecommendRef from the database by memberNo
     * @param memberNo the jmiRecommendRef's memberNo
     */
    public void removeJmiRecommendRef(final String memberNo);
    //added for getJmiRecommendRefsByCrm
    public List getJmiRecommendRefsByCrm(CommonRecord crm, Pager pager);
	/**
	 * 获取推荐人
	 * @param treeIndex
	 * @return
	 */
	public List getRecommendRefbyRecommendNo(String recommendNo);
	/**
	 * 执行存储过程,接点转移
	 * @param memberNo
	 * @param linkNo
	 * @param noCheck
	 * @return
	 */
	public String getCallProcChangeRecommend(final String memberNo, final String recommendNo, final String userCode, final String noCheck);

	/**
	 * 获取某推荐下的会员
	 * @param linkNo
	 */
	public List getJmiRecommendRefsByRecommendNo(String recommendNo);
	/**
	 * 获取推荐人数
	 * @param treeIndex
	 * @return
	 */
	public int getRecommendRefCount(String treeIndex);
	
	public void changeStateByRecommend(JmiRecommendRef jmiRecommendRef,String state,String remark,String excludeVal);
	
	public Map<String,String> getLeaderMembers();
	/**
	 * 查询会员直推三代的升级单首购单
	 * @param treeIndex
	 * @return
	 */
	public List getJmiRecommendRefOrder(String treeIndex);
	public List getMembersByUserCode(String userCode) ;
}

