
package com.joymain.jecs.pd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.dao.ItemsDao;
import com.joymain.jecs.pd.model.Items;
import com.joymain.jecs.pd.service.ItemsManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class ItemsManagerImpl extends BaseManager implements ItemsManager {
    private ItemsDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setItemsDao(ItemsDao dao) {
        this.dao = dao;
    }


    /**
     * @see com.joymain.jecs.pd.service.ItemsManager#getItems(String itemsId)
     */
    public Items getItems(final String itemsId) {
        return dao.getItems(Long.parseLong(itemsId));
    }

    /**
     * @see com.joymain.jecs.pd.service.ItemsManager#saveItems(Items items)
     */
    public void saveItems(Items items) {
        dao.saveItems(items);
    }

    /**
     * @see com.joymain.jecs.pd.service.ItemsManager#removeItems(Long itemsId)
     */
    public void removeItems(final Long itemsId) {
        dao.removeItems(itemsId);
    }
    //added for getItemsByCrm
    public List getItemsByCrm(CommonRecord crm, Pager pager){
	return dao.getItemsByCrm(crm,pager);
    }


	@Override
	public List getItems(Items items) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
     * 根据statusId获取items的集合(定时器用到)
     * @author gw 2015-06-16
     * @param statusId
     * @return
     */
	public List<Items> getItemsList(Long statusId) {
		return dao.getItemsList(statusId);
	}
}
