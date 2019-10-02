
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.dao.FoundationItemDao;
import com.joymain.jecs.fi.model.FoundationItem;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class FoundationItemDaoHibernate extends BaseDaoHibernate implements FoundationItemDao {

    /**
     * @see com.joymain.jecs.fi.dao.FoundationItemDao#getFoundationItems(com.joymain.jecs.fi.model.FoundationItem)
     */
    public List getFoundationItems(final FoundationItem foundationItem) {
        return getHibernateTemplate().find("from FoundationItem");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (foundationItem == null) {
            return getHibernateTemplate().find("from FoundationItem");
        } else {
            // filter on properties set in the foundationItem
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(foundationItem).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FoundationItem.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FoundationItemDao#getFoundationItem(Long fiId)
     */
    public FoundationItem getFoundationItem(final Long fiId) {
        FoundationItem foundationItem = (FoundationItem) getHibernateTemplate().get(FoundationItem.class, fiId);
        if (foundationItem == null) {
            log.warn("uh oh, foundationItem with fiId '" + fiId + "' not found...");
            throw new ObjectRetrievalFailureException(FoundationItem.class, fiId);
        }

        return foundationItem;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FoundationItemDao#saveFoundationItem(FoundationItem foundationItem)
     */    
    public void saveFoundationItem(final FoundationItem foundationItem) {
        getHibernateTemplate().saveOrUpdate(foundationItem);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FoundationItemDao#removeFoundationItem(Long fiId)
     */
    public void removeFoundationItem(final Long fiId) {
        getHibernateTemplate().delete(getFoundationItem(fiId));
    }
    //added for getFoundationItemsByCrm
    public List getFoundationItemsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FoundationItem foundationItem where 1=1";
    	
    	String status = crm.getString("status", "");
    	if(StringUtils.isNotEmpty(status)){
    		hql += " and foundationItem.status='"+status+"' ";
    	}
    	String type = crm.getString("type", "");
    	if(StringUtils.isNotEmpty(type)){
    		hql += " and foundationItem.type='"+type+"' ";
    	}
    	 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by foundationItem.type,foundationItem.field2 desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

    /**
     * 根据项目类型查询公益项目
     */
	public FoundationItem getFoundationItemByType(String type) {

		String hqlQuery = "from FoundationItem foundationItem where foundationItem.type='"+type+"'";
		return (FoundationItem)this.getObjectByHqlQuery(hqlQuery);
	}

	public String get365FoundationTitle() {
		// TODO Auto-generated method stub
		String hqlQuery = "select prompt from FoundationItem foundationItem where foundationItem.type='1'";
		return (String)this.getObjectByHqlQuery(hqlQuery);
	}
}
