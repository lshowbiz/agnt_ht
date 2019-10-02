
package com.joymain.jecs.mi.dao;

import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.JmiSpecialList;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiSpecialListDao extends Dao {

    /**
     * Retrieves all of the jmiSpecialLists
     */
    public List getJmiSpecialLists(JmiSpecialList jmiSpecialList);

    /**
     * Gets jmiSpecialList's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jmiSpecialList's id
     * @return jmiSpecialList populated jmiSpecialList object
     */
    public JmiSpecialList getJmiSpecialList(final Long id);

    /**
     * Saves a jmiSpecialList's information
     * @param jmiSpecialList the object to be saved
     */    
    public void saveJmiSpecialList(JmiSpecialList jmiSpecialList);

    /**
     * Removes a jmiSpecialList from the database by id
     * @param id the jmiSpecialList's id
     */
    public void removeJmiSpecialList(final Long id);
    //added for getJmiSpecialListsByCrm
    public List getJmiSpecialListsByCrm(CommonRecord crm, Pager pager);
    public void saveJmiSpecialList(List<JmiSpecialList> jmiSpecialList);
    public boolean getJmiSpecialExist(String userCode);
}

