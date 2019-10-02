
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdUserCardList;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdUserCardListDao extends Dao {

    /**
     * Retrieves all of the jbdUserCardLists
     */
    public List getJbdUserCardLists(JbdUserCardList jbdUserCardList);

    /**
     * Gets jbdUserCardList's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdUserCardList's id
     * @return jbdUserCardList populated jbdUserCardList object
     */
    public JbdUserCardList getJbdUserCardList(final Long id);

    /**
     * Saves a jbdUserCardList's information
     * @param jbdUserCardList the object to be saved
     */    
    public void saveJbdUserCardList(JbdUserCardList jbdUserCardList);

    /**
     * Removes a jbdUserCardList from the database by id
     * @param id the jbdUserCardList's id
     */
    public void removeJbdUserCardList(final Long id);
    //added for getJbdUserCardListsByCrm
    public List getJbdUserCardListsByCrm(CommonRecord crm, Pager pager);
    /**
     * 根据会员编号期别，找出当期记录，如果没有就返回NULL
     * @param userCode
     * @param wweek
     * @return
     */
    public JbdUserCardList getJbdUserCardListByUserCodeAndWeek(String userCode,Integer wweek);
    /**
     * 获取某一期的上一期
     * @param wweek
     * @return
     */
    public JbdUserCardList getPreviousJbdUserCardList(String userCode,Integer wweek);
	public List getJbdUserCardListByRange(Integer sweek, Integer eweek,String userCode);
	public List getJbdUserCardListByUserCode(String userCode);
	public List getNextJbdUserCardList(String userCode,Integer wweek);
}

