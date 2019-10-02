
package com.joymain.jecs.fi.dao.hibernate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.FiBcoinPayconfig;
import com.joymain.jecs.fi.dao.FiBcoinPayconfigDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class FiBcoinPayconfigDaoHibernate extends BaseDaoHibernate implements FiBcoinPayconfigDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiBcoinPayconfigDao#getFiBcoinPayconfigs(com.joymain.jecs.fi.model.FiBcoinPayconfig)
     */
    public List getFiBcoinPayconfigs(final FiBcoinPayconfig fiBcoinPayconfig) {
        return getHibernateTemplate().find("from FiBcoinPayconfig");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiBcoinPayconfig == null) {
            return getHibernateTemplate().find("from FiBcoinPayconfig");
        } else {
            // filter on properties set in the fiBcoinPayconfig
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiBcoinPayconfig).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiBcoinPayconfig.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiBcoinPayconfigDao#getFiBcoinPayconfig(Long configId)
     */
    public FiBcoinPayconfig getFiBcoinPayconfig(final Long configId) {
        FiBcoinPayconfig fiBcoinPayconfig = (FiBcoinPayconfig) getHibernateTemplate().get(FiBcoinPayconfig.class, configId);
        if (fiBcoinPayconfig == null) {
            log.warn("uh oh, fiBcoinPayconfig with configId '" + configId + "' not found...");
            throw new ObjectRetrievalFailureException(FiBcoinPayconfig.class, configId);
        }

        return fiBcoinPayconfig;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiBcoinPayconfigDao#saveFiBcoinPayconfig(FiBcoinPayconfig fiBcoinPayconfig)
     */    
    public void saveFiBcoinPayconfig(final FiBcoinPayconfig fiBcoinPayconfig) {
        getHibernateTemplate().saveOrUpdate(fiBcoinPayconfig);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiBcoinPayconfigDao#removeFiBcoinPayconfig(Long configId)
     */
    public void removeFiBcoinPayconfig(final Long configId) {
        getHibernateTemplate().delete(getFiBcoinPayconfig(configId));
    }
    //added for getFiBcoinPayconfigsByCrm
    public List getFiBcoinPayconfigsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FiBcoinPayconfig fiBcoinPayconfig where 1=1";
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
			hql += " order by configId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	@Override
	public FiBcoinPayconfig getFiBcoinPayconfigByNowTime() {
		
		//创建格式化的当前时间
//		DateFormat df = new SimpleDateFormat("yyyy-mm-dd hh24:mi:ss");
//		String nowTime = df.format(new Date());
		
		Date d = new Date();
		DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String nowTime = format1.format(new Date());
		
		//查询当前时间是否在积分换购期间
		String hql = "from FiBcoinPayconfig fiBcoinPayconfig where";
		//hql += " startTime<=to_date('" + nowTime + "','yyyy-mm-dd hh24:mi:ss')";
		//hql += " and endTime>=to_date('" + nowTime + "','yyyy-mm-dd hh24:mi:ss')";
		
		hql += " startTime<='" + nowTime + "'";
		hql += " and endTime>='" + nowTime + "'";
		
		return (FiBcoinPayconfig) this.getObjectByHqlQuery(hql);
	}
}
