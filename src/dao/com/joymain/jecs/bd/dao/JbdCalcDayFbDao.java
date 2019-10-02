
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdCalcDayFb;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdCalcDayFbDao extends Dao {

    /**
     * Retrieves all of the jbdCalcDayFbs
     */
    public List getJbdCalcDayFbs(JbdCalcDayFb jbdCalcDayFb);

    /**
     * Gets jbdCalcDayFb's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdCalcDayFb's id
     * @return jbdCalcDayFb populated jbdCalcDayFb object
     */
    public JbdCalcDayFb getJbdCalcDayFb(final Long id);

    /**
     * Saves a jbdCalcDayFb's information
     * @param jbdCalcDayFb the object to be saved
     */    
    public void saveJbdCalcDayFb(JbdCalcDayFb jbdCalcDayFb);

    /**
     * Removes a jbdCalcDayFb from the database by id
     * @param id the jbdCalcDayFb's id
     */
    public void removeJbdCalcDayFb(final Long id);
    //added for getJbdCalcDayFbsByCrm
    public List getJbdCalcDayFbsByCrm(CommonRecord crm, Pager pager);
    public List getJbdCalcDayFbList(String userCode,String wweek);
}

