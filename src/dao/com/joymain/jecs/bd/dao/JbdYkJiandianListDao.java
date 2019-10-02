
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdYkJiandianList;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdYkJiandianListDao extends Dao {

    /**
     * Retrieves all of the jbdYkJiandianLists
     */
    public List getJbdYkJiandianLists(JbdYkJiandianList jbdYkJiandianList);

    /**
     * Gets jbdYkJiandianList's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdYkJiandianList's id
     * @return jbdYkJiandianList populated jbdYkJiandianList object
     */
    public JbdYkJiandianList getJbdYkJiandianList(final Long id);

    /**
     * Saves a jbdYkJiandianList's information
     * @param jbdYkJiandianList the object to be saved
     */    
    public void saveJbdYkJiandianList(JbdYkJiandianList jbdYkJiandianList);

    /**
     * Removes a jbdYkJiandianList from the database by id
     * @param id the jbdYkJiandianList's id
     */
    public void removeJbdYkJiandianList(final Long id);
    //added for getJbdYkJiandianListsByCrm
    public List getJbdYkJiandianListsByCrm(CommonRecord crm, Pager pager);
}

