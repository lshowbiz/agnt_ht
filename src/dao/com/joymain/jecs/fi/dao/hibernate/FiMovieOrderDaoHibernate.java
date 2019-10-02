
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.FiMovieOrder;
import com.joymain.jecs.fi.dao.FiMovieOrderDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class FiMovieOrderDaoHibernate extends BaseDaoHibernate implements FiMovieOrderDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiMovieOrderDao#getFiMovieOrders(com.joymain.jecs.fi.model.FiMovieOrder)
     */
    public List getFiMovieOrders(final FiMovieOrder fiMovieOrder) {
        return getHibernateTemplate().find("from FiMovieOrder");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiMovieOrder == null) {
            return getHibernateTemplate().find("from FiMovieOrder");
        } else {
            // filter on properties set in the fiMovieOrder
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiMovieOrder).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiMovieOrder.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiMovieOrderDao#getFiMovieOrder(String orderId)
     */
    public FiMovieOrder getFiMovieOrder(final String orderId) {
        FiMovieOrder fiMovieOrder = (FiMovieOrder) getHibernateTemplate().get(FiMovieOrder.class, orderId);
        if (fiMovieOrder == null) {
            log.warn("uh oh, fiMovieOrder with orderId '" + orderId + "' not found...");
            throw new ObjectRetrievalFailureException(FiMovieOrder.class, orderId);
        }

        return fiMovieOrder;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiMovieOrderDao#saveFiMovieOrder(FiMovieOrder fiMovieOrder)
     */    
    public void saveFiMovieOrder(final FiMovieOrder fiMovieOrder) {
        getHibernateTemplate().saveOrUpdate(fiMovieOrder);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiMovieOrderDao#removeFiMovieOrder(String orderId)
     */
    public void removeFiMovieOrder(final String orderId) {
        getHibernateTemplate().delete(getFiMovieOrder(orderId));
    }
    //added for getFiMovieOrdersByCrm
    public List getFiMovieOrdersByCrm(CommonRecord crm, Pager pager){
    	
    	String hql = "from FiMovieOrder fiMovieOrder where 1=1";
   
		String userCode = crm.getString("userCode", "");
		if(StringUtils.isNotEmpty(userCode)){
			hql += " and userCode = '"+userCode+"'";
		}

    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by createTime desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
