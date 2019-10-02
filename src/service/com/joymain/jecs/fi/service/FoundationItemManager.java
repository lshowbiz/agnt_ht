
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.FoundationItem;
import com.joymain.jecs.fi.dao.FoundationItemDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface FoundationItemManager extends Manager {
    /**
     * Retrieves all of the foundationItems
     */
    public List getFoundationItems(FoundationItem foundationItem);

    /**
     * Gets foundationItem's information based on fiId.
     * @param fiId the foundationItem's fiId
     * @return foundationItem populated foundationItem object
     */
    public FoundationItem getFoundationItem(final String fiId);
    
    public FoundationItem getFoundationItemByType(final String type);

    /**
     * Saves a foundationItem's information
     * @param foundationItem the object to be saved
     */
    public void saveFoundationItem(FoundationItem foundationItem);

    /**
     * Removes a foundationItem from the database by fiId
     * @param fiId the foundationItem's fiId
     */
    public void removeFoundationItem(final String fiId);
    //added for getFoundationItemsByCrm
    public List getFoundationItemsByCrm(CommonRecord crm, Pager pager);
    
    public String get365FoundationTitle();
}

