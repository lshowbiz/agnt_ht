
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.FoundationItem;
import com.joymain.jecs.fi.dao.FoundationItemDao;
import com.joymain.jecs.fi.service.FoundationItemManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class FoundationItemManagerImpl extends BaseManager implements FoundationItemManager {
    private FoundationItemDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFoundationItemDao(FoundationItemDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FoundationItemManager#getFoundationItems(com.joymain.jecs.fi.model.FoundationItem)
     */
    public List getFoundationItems(final FoundationItem foundationItem) {
        return dao.getFoundationItems(foundationItem);
    }

    /**
     * @see com.joymain.jecs.fi.service.FoundationItemManager#getFoundationItem(String fiId)
     */
    public FoundationItem getFoundationItem(final String fiId) {
    	if(fiId!=null){
    		return dao.getFoundationItem(new Long(fiId));
    	}
    	return new FoundationItem(); 
    }

    /**
     * @see com.joymain.jecs.fi.service.FoundationItemManager#saveFoundationItem(FoundationItem foundationItem)
     */
    public void saveFoundationItem(FoundationItem foundationItem) {
        dao.saveFoundationItem(foundationItem);
    }

    /**
     * @see com.joymain.jecs.fi.service.FoundationItemManager#removeFoundationItem(String fiId)
     */
    public void removeFoundationItem(final String fiId) {
        dao.removeFoundationItem(new Long(fiId));
    }
    //added for getFoundationItemsByCrm
    public List getFoundationItemsByCrm(CommonRecord crm, Pager pager){
	return dao.getFoundationItemsByCrm(crm,pager);
    }

	public FoundationItem getFoundationItemByType(String type) {
		// TODO Auto-generated method stub
		return dao.getFoundationItemByType(type);
	}

	public String get365FoundationTitle() {
		// TODO Auto-generated method stub
		return dao.get365FoundationTitle();
	}
}
