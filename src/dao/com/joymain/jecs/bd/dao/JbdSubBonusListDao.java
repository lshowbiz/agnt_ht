
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdSubBonusList;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdSubBonusListDao extends Dao {

    /**
     * Retrieves all of the jbdSubBonusLists
     */
    public List getJbdSubBonusLists(JbdSubBonusList jbdSubBonusList);

    /**
     * Gets jbdSubBonusList's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdSubBonusList's id
     * @return jbdSubBonusList populated jbdSubBonusList object
     */
    public JbdSubBonusList getJbdSubBonusList(final Long id);

    /**
     * Saves a jbdSubBonusList's information
     * @param jbdSubBonusList the object to be saved
     */    
    public void saveJbdSubBonusList(JbdSubBonusList jbdSubBonusList);

    /**
     * Removes a jbdSubBonusList from the database by id
     * @param id the jbdSubBonusList's id
     */
    public void removeJbdSubBonusList(final Long id);
    //added for getJbdSubBonusListsByCrm
    public List getJbdSubBonusListsByCrm(CommonRecord crm, Pager pager);
    public List getJbdSubBonusListsBySql(CommonRecord crm, Pager pager);
}

