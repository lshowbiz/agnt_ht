
package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdWarehouseUser;
import com.joymain.jecs.pd.dao.PdWarehouseUserDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class PdWarehouseUserDaoHibernate extends BaseDaoHibernate implements PdWarehouseUserDao {

public List getPdWarehouseByUser(String userCode) {
		// TODO Auto-generated method stub
		String queryString ="from PdWarehouseUser pdWarehouseUser where pdWarehouseUser.userCode='"+userCode+"'";
		
		return getHibernateTemplate().find(queryString);
	}

	/**
		public List getPiProductsByHql(String hql){
			return getHibernateTemplate().find(hql);
		}

		public List getPiProductsBySql(String sql){
		}
**/		
    /**
     * @see com.joymain.jecs.pd.dao.PdWarehouseUserDao#getPdWarehouseUsers(com.joymain.jecs.pd.model.PdWarehouseUser)
     */
    public List getPdWarehouseUsers(final PdWarehouseUser pdWarehouseUser) {
        return getHibernateTemplate().find("from PdWarehouseUser");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (pdWarehouseUser == null) {
            return getHibernateTemplate().find("from PdWarehouseUser");
        } else {
            // filter on properties set in the pdWarehouseUser
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(pdWarehouseUser).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(PdWarehouseUser.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdWarehouseUserDao#getPdWarehouseUser(Long wuId)
     */
    public PdWarehouseUser getPdWarehouseUser(final Long wuId) {
        PdWarehouseUser pdWarehouseUser = (PdWarehouseUser) getHibernateTemplate().get(PdWarehouseUser.class, wuId);
       /* if (pdWarehouseUser == null) {
            log.warn("uh oh, pdWarehouseUser with wuId '" + wuId + "' not found...");
            throw new ObjectRetrievalFailureException(PdWarehouseUser.class, wuId);
        }*/

        return pdWarehouseUser;
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdWarehouseUserDao#savePdWarehouseUser(PdWarehouseUser pdWarehouseUser)
     */    
    public void savePdWarehouseUser(final PdWarehouseUser pdWarehouseUser) {
        getHibernateTemplate().saveOrUpdate(pdWarehouseUser);
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdWarehouseUserDao#removePdWarehouseUser(Long wuId)
     */
    public void removePdWarehouseUser(final Long wuId) {
        getHibernateTemplate().delete(getPdWarehouseUser(wuId));
    }
    //added for getPdWarehouseUsersByCrm
    public List getPdWarehouseUsersByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PdWarehouseUser pdWarehouseUser where 1=1";
    	String warehouseNo = crm.getString("warehouseNo", "");
    	if(StringUtils.isNotEmpty(warehouseNo)){
    		hql +=" and pdWarehouseUser.warehouseNo='"+warehouseNo+"'";
    	}
    	String userCode = crm.getString("userCode", "");
    	if(StringUtils.isNotEmpty(userCode)){
    		hql +=" and pdWarehouseUser.userCode='"+userCode+"'";
    	}
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by wuId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
