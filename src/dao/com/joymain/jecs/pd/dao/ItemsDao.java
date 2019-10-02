
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;

import com.joymain.jecs.pd.model.Items;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface ItemsDao extends Dao {

    /**
     * Retrieves all of the items
     */
    public List getItems(Items items);

    /**
     * Gets items's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param itemsId the items's itemsId
     * @return items populated items object
     */
    public Items getItems(final Long itemsId);

    /**
     * Saves a items's information
     * @param items the object to be saved
     */    
    public void saveItems(Items items);

    /**
     * Removes a items from the database by itemsId
     * @param itemsId the items's itemsId
     */
    public void removeItems(final Long itemsId);
    //added for getItemsByCrm
    public List getItemsByCrm(CommonRecord crm, Pager pager);

    /**
     * 根据statusId获取items的集合(定时器用到)
     * @author gw 2015-06-16
     * @param statusId
     * @return
     */
	public List<Items> getItemsList(Long statusId);
}

