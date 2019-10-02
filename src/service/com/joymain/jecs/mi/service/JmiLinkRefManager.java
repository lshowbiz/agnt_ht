
package com.joymain.jecs.mi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.mi.model.JmiLinkRef;
import com.joymain.jecs.mi.dao.JmiLinkRefDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiLinkRefManager extends Manager {
    /**
     * Retrieves all of the jmiLinkRefs
     */
    public List getJmiLinkRefs(JmiLinkRef jmiLinkRef);

    /**
     * Gets jmiLinkRef's information based on memberNo.
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
    public List getMiLinkRefManagersByTree(CommonRecord crm) ;
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
	/**
	 * 判断会员是否在CN18303490会员下
	 * 如果是返回TRUE，否则返回FALSE
	 * @param userCode
	 * @return
	 */
	public boolean getJmiLinkRefIsM(String userCode);
	public List getLinkRefbyLinkNo(String linkNo,String sortField) ;
	public List getLinkRefbyLinkNoByWeekGroupPv(String linkNo,String curWeek) ;
	public int getLinkRefCount(String treeIndex);
	public void changeStateByLink(JmiLinkRef jmiLinkRef,String state,String remark, String excludeVal) ;
}

