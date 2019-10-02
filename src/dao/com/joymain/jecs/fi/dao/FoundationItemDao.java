
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FoundationItem;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FoundationItemDao extends Dao {

    /**
     * Retrieves all of the foundationItems
     */
    public List getFoundationItems(FoundationItem foundationItem);

    /**
     * Gets foundationItem's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param fiId the foundationItem's fiId
     * @return foundationItem populated foundationItem object
     */
    public FoundationItem getFoundationItem(final Long fiId);

    /**
     * Saves a foundationItem's information
     * @param foundationItem the object to be saved
     */    
    public void saveFoundationItem(FoundationItem foundationItem);

    /**
     * Removes a foundationItem from the database by fiId
     * @param fiId the foundationItem's fiId
     */
    public void removeFoundationItem(final Long fiId);
    //added for getFoundationItemsByCrm
    public List getFoundationItemsByCrm(CommonRecord crm, Pager pager);
    
    public FoundationItem getFoundationItemByType(final String type);
    
    public String get365FoundationTitle();
}

