
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.FiCommonAddr;
import com.joymain.jecs.fi.dao.FiCommonAddrDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class FiCommonAddrDaoHibernate extends BaseDaoHibernate implements FiCommonAddrDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiCommonAddrDao#getFiCommonAddrs(com.joymain.jecs.fi.model.FiCommonAddr)
     */
    public List getFiCommonAddrs(final FiCommonAddr fiCommonAddr) {
        return getHibernateTemplate().find("from FiCommonAddr");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiCommonAddr == null) {
            return getHibernateTemplate().find("from FiCommonAddr");
        } else {
            // filter on properties set in the fiCommonAddr
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiCommonAddr).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiCommonAddr.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiCommonAddrDao#getFiCommonAddr(Object userCode)
     */
    public FiCommonAddr getFiCommonAddr(final String userCode) {
        FiCommonAddr fiCommonAddr = (FiCommonAddr) getHibernateTemplate().get(FiCommonAddr.class, userCode);
        if (fiCommonAddr == null) {
            return null;
        }

        return fiCommonAddr;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiCommonAddrDao#saveFiCommonAddr(FiCommonAddr fiCommonAddr)
     */    
    public void saveFiCommonAddr(final FiCommonAddr fiCommonAddr) {
        getHibernateTemplate().saveOrUpdate(fiCommonAddr);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiCommonAddrDao#removeFiCommonAddr(Object userCode)
     */
    public void removeFiCommonAddr(final String userCode) {
        getHibernateTemplate().delete(getFiCommonAddr(userCode));
    }
    //added for getFiCommonAddrsByCrm
    public List getFiCommonAddrsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FiCommonAddr fiCommonAddr where 1=1";
    	
		String userCode = crm.getString("userCode", "");
		if(StringUtils.isNotEmpty(userCode)){
			hql += " and userCode='"+userCode+"'";
		}
		
		String province = crm.getString("province", "");
		if(StringUtils.isNotEmpty(province)){
			hql += " and province='"+province+"'";
		}
	 
	 
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by userCode desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
