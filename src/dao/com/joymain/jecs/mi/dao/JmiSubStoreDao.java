
package com.joymain.jecs.mi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.JmiSubStore;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiSubStoreDao extends Dao {

    /**
     * Retrieves all of the jmiSubStores
     */
    public List getJmiSubStores(JmiSubStore jmiSubStore);

    /**
     * Gets jmiSubStore's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jmiSubStore's id
     * @return jmiSubStore populated jmiSubStore object
     */
    public JmiSubStore getJmiSubStore(final Long id);

    /**
     * Saves a jmiSubStore's information
     * @param jmiSubStore the object to be saved
     */    
    public void saveJmiSubStore(JmiSubStore jmiSubStore);

    /**
     * Removes a jmiSubStore from the database by id
     * @param id the jmiSubStore's id
     */
    public void removeJmiSubStore(final Long id);
    //added for getJmiSubStoresByCrm
    public List getJmiSubStoresByCrm(CommonRecord crm, Pager pager);
    
    public JmiSubStore getJmiSubStoresByUserCode(String userCode);
}

