
package com.joymain.jecs.bd.service;

import java.util.Date;
import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.bd.model.JbdUserCardList;
import com.joymain.jecs.bd.dao.JbdUserCardListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdUserCardListManager extends Manager {
    /**
     * Retrieves all of the jbdUserCardLists
     */
    public List getJbdUserCardLists(JbdUserCardList jbdUserCardList);

    /**
     * Gets jbdUserCardList's information based on id.
     * @param id the jbdUserCardList's id
     * @return jbdUserCardList populated jbdUserCardList object
     */
    public JbdUserCardList getJbdUserCardList(final String id);

    /**
     * Saves a jbdUserCardList's information
     * @param jbdUserCardList the object to be saved
     */
    public void saveJbdUserCardList(JbdUserCardList jbdUserCardList);

    /**
     * Removes a jbdUserCardList from the database by id
     * @param id the jbdUserCardList's id
     */
    public void removeJbdUserCardList(final String id);
    //added for getJbdUserCardListsByCrm
    public List getJbdUserCardListsByCrm(CommonRecord crm, Pager pager);
    
    public void saveJbdUserCardList(String userCode,Date operatDate,String newCard,String updateType,String operaterType);
	public void saveJbdUserCardList(String userCode,Integer wweek,String newCard,String updateType,String operaterType);
    
    public boolean getPreviousJbdUserCardList(Date tDate,String userCode);
	public List getJbdUserCardListByUserCode(String userCode);
	
	//** 显示财政周**//
	public List getJbdUserCardListByUserCodeFweek(String userCode);
	
	
}

