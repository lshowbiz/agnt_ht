
package com.joymain.jecs.mi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.JmiRecommendRef;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiRecommendRefDao extends Dao {

    /**
     * Retrieves all of the jmiRecommendRefs
     */
    public List getJmiRecommendRefs(JmiRecommendRef jmiRecommendRef);

    /**
     * Gets jmiRecommendRef's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
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
    public JmiRecommendRef getNewMiLinkRefManagersByRecommendNo(JmiRecommendRef jmiRecommendRef);
    public List getMiRecommendRefManagersByTree(CommonRecord crm);
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
	/**
	 * 获取推荐人数
	 * @param treeIndex
	 * @return
	 */
	public List getRecommendRefByTreeIndex(String treeIndex);
	/**
	 * 通过IN USER_CODE查找出会员
	 * @param userCodes
	 * @return
	 */
	public List getRecommendRefByUserCodes(String userCodes);
	/**
	 * 查询会员直推三代的升级单首购单
	 * @param treeIndex
	 * @return
	 */
	public List getJmiRecommendRefOrder(String treeIndex);
	
	public List getMembersByUserCode(String userCode);
}

