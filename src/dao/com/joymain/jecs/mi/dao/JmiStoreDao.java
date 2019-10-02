
package com.joymain.jecs.mi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.JmiStore;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiStoreDao extends Dao {

    /**
     * Retrieves all of the jmiStores
     */
    public List getJmiStores(JmiStore jmiStore);

    /**
     * Gets jmiStore's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jmiStore's id
     * @return jmiStore populated jmiStore object
     */
    public JmiStore getJmiStore(final Long id);

    /**
     * Saves a jmiStore's information
     * @param jmiStore the object to be saved
     */    
    public void saveJmiStore(JmiStore jmiStore);

    /**
     * Removes a jmiStore from the database by id
     * @param id the jmiStore's id
     */
    public void removeJmiStore(final Long id);
    //added for getJmiStoresByCrm
    public List getJmiStoresByCrm(CommonRecord crm, Pager pager);
    public JmiStore getJmiStoreByUserCode(String userCode);
}

