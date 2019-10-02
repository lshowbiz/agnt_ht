
package com.joymain.jecs.mi.dao;

import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.JmiValidLevelList;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiValidLevelListDao extends Dao {

    /**
     * Retrieves all of the jmiValidLevelLists
     */
    public List getJmiValidLevelLists(JmiValidLevelList jmiValidLevelList);

    /**
     * Gets jmiValidLevelList's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jmiValidLevelList's id
     * @return jmiValidLevelList populated jmiValidLevelList object
     */
    public JmiValidLevelList getJmiValidLevelList(final Long id);

    /**
     * Saves a jmiValidLevelList's information
     * @param jmiValidLevelList the object to be saved
     */    
    public void saveJmiValidLevelList(JmiValidLevelList jmiValidLevelList);

    /**
     * Removes a jmiValidLevelList from the database by id
     * @param id the jmiValidLevelList's id
     */
    public void removeJmiValidLevelList(final Long id);
    //added for getJmiValidLevelListsByCrm
    public List getJmiValidLevelListsByCrm(CommonRecord crm, Pager pager);
	public JmiValidLevelList getJmiValidLevelListsByUserCode(String userCode, Integer wweek);
}

