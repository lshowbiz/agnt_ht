
package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.dao.ItemsDao;
import com.joymain.jecs.pd.model.Items;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class ItemsDaoHibernate extends BaseDaoHibernate implements ItemsDao {

    /**
     * @see com.joymain.jecs.pd.dao.ItemsDao#getItems(com.joymain.jecs.pd.model.Items)
     */
    public List getItems(final Items items) {
        return getHibernateTemplate().find("from Items");
    }

    /**
     * @see com.joymain.jecs.pd.dao.ItemsDao#getItems(Long itemsId)
     */
    public Items getItems(final Long itemsId) {
        Items items = (Items) getHibernateTemplate().get(Items.class, itemsId);
        if (items == null) {
            log.warn("uh oh, items with itemsId '" + itemsId + "' not found...");
            throw new ObjectRetrievalFailureException(Items.class, itemsId);
        }

        return items;
    }

    /**
     * @see com.joymain.jecs.pd.dao.ItemsDao#saveItems(Items items)
     */    
    public void saveItems(final Items items) {
        getHibernateTemplate().saveOrUpdate(items);
    }

    /**
     * @see com.joymain.jecs.pd.dao.ItemsDao#removeItems(Long itemsId)
     */
    public void removeItems(final Long itemsId) {
        getHibernateTemplate().delete(getItems(itemsId));
    }
    //added for getItemsByCrm
    public List getItemsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from Items items where 1=1";
    	
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by itemsId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

    
    /**
     * 根据statusId获取items的集合(定时器用到)
     * @author gw 2015-06-16
     * @param statusId
     * @return
     */
	public List<Items> getItemsList(Long statusId) {
		String hql = "from Items items where items.mailStatus.statusId = '"+statusId+"'";
		List<Items> list = this.findObjectsByHqlQuery(hql);
		if(null!=list){
			if(list.size()>0){
				return list;
			}
		}
		return null;
	}


}
