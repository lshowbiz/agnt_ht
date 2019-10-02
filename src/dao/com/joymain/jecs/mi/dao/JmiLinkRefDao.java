
package com.joymain.jecs.mi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.JmiLinkRef;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiLinkRefDao extends Dao {

    /**
     * Retrieves all of the jmiLinkRefs
     */
    public List getJmiLinkRefs(JmiLinkRef jmiLinkRef);

    /**
     * Gets jmiLinkRef's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param memberNo the jmiLinkRef's memberNo
     * @return jmiLinkRef populated jmiLinkRef object
     */
    public JmiLinkRef getJmiLinkRef(final String memberNo);

    /**
     * Saves a jmiLinkRef's information
     * @param jmiLinkRef the object to be saved
     */    
    public void saveJmiLinkRef(JmiLinkRef jmiLinkRef);

    /**
     * Removes a jmiLinkRef from the database by memberNo
     * @param memberNo the jmiLinkRef's memberNo
     */
    public void removeJmiLinkRef(final String memberNo);
    //added for getJmiLinkRefsByCrm
    public List getJmiLinkRefsByCrm(CommonRecord crm, Pager pager);
    public JmiLinkRef getNewMiLinkRefManagersByLinkNo(JmiLinkRef miLinkRef, int maxLink);/**
	 * 根据外部参数分页获取对应的键值列表
	 * 
	 * @param crm
	 * @return
	 */
	public List getMiLinkRefManagersByTree(CommonRecord crm);
	public List getLinkRefbyLinkNoOrderByCreateTime(String linkNo);
	public List getLinkRefbyLinkNo(String linkNo);
	 /**
	 * 执行存储过程,接点转移
	 * @param memberNo
	 * @param linkNo
	 * @param noCheck
	 * @return
	 */
	public String getCallProcChangeLink(final String memberNo, final String linkNo, final String userCode, final String noCheck);
	/**
	 * 获取某接点下的会员
	 * @param linkNo
	 */
	public List getLinkRefsbyLinkNo(String linkNo);

	public List getLinkRefbyLinkNo(String linkNo,String sortField) ;
	
	public List getLinkRefbyLinkNoByWeekGroupPv(String linkNo,String curWeek) ;
	public int getLinkRefCount(String treeIndex);
	/**
	 * 获取安置人数
	 * @param treeIndex
	 * @return
	 */
	public List getLinkRefByTreeIndex(String treeIndex);
}


