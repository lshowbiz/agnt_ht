
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.FiInvoiceChange;
import com.joymain.jecs.fi.dao.FiInvoiceChangeDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class FiInvoiceChangeDaoHibernate extends BaseDaoHibernate implements FiInvoiceChangeDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiInvoiceChangeDao#getFiInvoiceChanges(com.joymain.jecs.fi.model.FiInvoiceChange)
     */
    public List getFiInvoiceChanges(final FiInvoiceChange fiInvoiceChange) {
        return getHibernateTemplate().find("from FiInvoiceChange");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiInvoiceChange == null) {
            return getHibernateTemplate().find("from FiInvoiceChange");
        } else {
            // filter on properties set in the fiInvoiceChange
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiInvoiceChange).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiInvoiceChange.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiInvoiceChangeDao#getFiInvoiceChange(BigDecimal id)
     */
    public FiInvoiceChange getFiInvoiceChange(final BigDecimal id) {
        FiInvoiceChange fiInvoiceChange = (FiInvoiceChange) getHibernateTemplate().get(FiInvoiceChange.class, id);
        if (fiInvoiceChange == null) {
            log.warn("uh oh, fiInvoiceChange with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(FiInvoiceChange.class, id);
        }

        return fiInvoiceChange;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiInvoiceChangeDao#saveFiInvoiceChange(FiInvoiceChange fiInvoiceChange)
     */    
    public void saveFiInvoiceChange(final FiInvoiceChange fiInvoiceChange) {
        getHibernateTemplate().saveOrUpdate(fiInvoiceChange);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiInvoiceChangeDao#removeFiInvoiceChange(BigDecimal id)
     */
    public void removeFiInvoiceChange(final BigDecimal id) {
        getHibernateTemplate().delete(getFiInvoiceChange(id));
    }
    //added for getFiInvoiceChangesByCrm
    public List getFiInvoiceChangesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FiInvoiceChange fiInvoiceChange where 1=1";
    	
    	String userCode = crm.getString("userCode", "");
 	    if(!StringUtil.isEmpty(userCode)){
 	    	   hql += " and fiInvoiceChange.userCode = '"+userCode+"'";
 	    }
 	    
 	    String createTimeStart = crm.getString("createTimeStart", "");
		if (StringUtils.isNotEmpty(createTimeStart)) {
			hql += " and fiInvoiceChange.createTime >=to_date('"+createTimeStart+" 00:00:00','yyyy-MM-dd hh24:mi:ss')";
		}
		String createTimeEnd = crm.getString("createTimeEnd", "");
		if (StringUtils.isNotEmpty(createTimeEnd)) {
			hql += " and fiInvoiceChange.createTime <=to_date('"+createTimeEnd+" 23:59:59','yyyy-MM-dd hh24:mi:ss')";
		}
		
		    hql += "  order by createTime desc ";
 	    
		/*if (!pager.getLimit().getSort().isSorted()) {
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}*/
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
