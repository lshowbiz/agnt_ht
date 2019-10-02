
package com.joymain.jecs.al.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.al.model.JalLibraryColumn;
import com.joymain.jecs.al.dao.JalLibraryColumnDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JalLibraryColumnDaoHibernate extends BaseDaoHibernate implements JalLibraryColumnDao {

    /**
     * @see com.joymain.jecs.al.dao.JalLibraryColumnDao#getJalLibraryColumns(com.joymain.jecs.al.model.JalLibraryColumn)
     */
    public List getJalLibraryColumns(final JalLibraryColumn jalLibraryColumn) {
        return getHibernateTemplate().find("from JalLibraryColumn");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jalLibraryColumn == null) {
            return getHibernateTemplate().find("from JalLibraryColumn");
        } else {
            // filter on properties set in the jalLibraryColumn
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jalLibraryColumn).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JalLibraryColumn.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.al.dao.JalLibraryColumnDao#getJalLibraryColumn(Long columnId)
     */
    public JalLibraryColumn getJalLibraryColumn(final Long columnId) {
        JalLibraryColumn jalLibraryColumn = (JalLibraryColumn) getHibernateTemplate().get(JalLibraryColumn.class, columnId);
        if (jalLibraryColumn == null) {
            log.warn("uh oh, jalLibraryColumn with columnId '" + columnId + "' not found...");
            throw new ObjectRetrievalFailureException(JalLibraryColumn.class, columnId);
        }

        return jalLibraryColumn;
    }

    /**
     * @see com.joymain.jecs.al.dao.JalLibraryColumnDao#saveJalLibraryColumn(JalLibraryColumn jalLibraryColumn)
     */    
    public void saveJalLibraryColumn(final JalLibraryColumn jalLibraryColumn) {
        getHibernateTemplate().saveOrUpdate(jalLibraryColumn);
    }

    /**
     * @see com.joymain.jecs.al.dao.JalLibraryColumnDao#removeJalLibraryColumn(Long columnId)
     */
    public void removeJalLibraryColumn(final Long columnId) {
        getHibernateTemplate().delete(getJalLibraryColumn(columnId));
    }
    //added for getJalLibraryColumnsByCrm
    public List getJalLibraryColumnsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JalLibraryColumn jalLibraryColumn where 1=1";
    	/** 
	---example---
	String userCode = crm.getString("userCode", "");
	if(StringUtils.isNotEmpty(userCode)){
		hql += "???????????";
	}
	 ***/
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by columnId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public List getJalLibraryColumnsByPlateId(String plateId) {
		String hql = "from JalLibraryColumn jalLibraryColumn where plateId="+plateId;
		return this.findObjectsByHqlQuery(hql);
	}
}
