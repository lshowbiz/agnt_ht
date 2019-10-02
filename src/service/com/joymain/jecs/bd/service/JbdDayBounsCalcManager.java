
package com.joymain.jecs.bd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.bd.model.JbdDayBounsCalc;
import com.joymain.jecs.bd.dao.JbdDayBounsCalcDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdDayBounsCalcManager extends Manager {
    /**
     * Retrieves all of the jbdDayBounsCalcs
     */
    public List getJbdDayBounsCalcs(JbdDayBounsCalc jbdDayBounsCalc);

    /**
     * Gets jbdDayBounsCalc's information based on id.
     * @param id the jbdDayBounsCalc's id
     * @return jbdDayBounsCalc populated jbdDayBounsCalc object
     */
    public JbdDayBounsCalc getJbdDayBounsCalc(final String id);

    /**
     * Saves a jbdDayBounsCalc's information
     * @param jbdDayBounsCalc the object to be saved
     */
    public void saveJbdDayBounsCalc(JbdDayBounsCalc jbdDayBounsCalc);

    /**
     * Removes a jbdDayBounsCalc from the database by id
     * @param id the jbdDayBounsCalc's id
     */
    public void removeJbdDayBounsCalc(final String id);
    //added for getJbdDayBounsCalcsByCrm
    public List getJbdDayBounsCalcsByCrm(CommonRecord crm, Pager pager);
	/**
	 * 获取会员在某一期的日结算记录,如果没有则各值为空
	 * @param memberNo
	 * @param wweek
	 * @return
	 */
	public HashMap getBdDayBounsCalcBySql(final String memberNo, final Integer wweek);
	/**
	 * 获取会员的下层接点在某一期的日结算记录
	 * @param miMember
	 * @param wweek
	 * @return
	 */
	public List getChildBdDayBounsCalcBySql(final JmiMember jmiMember, final Integer wweek);
	public Map getBdDayBounsCalcByUserCode(final String userCode,final String wweek);

	public List getBdDayBounsCalcByTop(String netType, String userCode, String wweek) ;
}

