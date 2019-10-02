
package com.joymain.jecs.pm.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.model.JpmConfigSpecDetailed;
import com.joymain.jecs.pm.dao.JpmConfigSpecDetailedDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JpmConfigSpecDetailedDaoHibernate extends BaseDaoHibernate implements JpmConfigSpecDetailedDao {

    /**
     * @see com.joymain.jecs.pm.dao.JpmConfigSpecDetailedDao#getJpmConfigSpecDetaileds(com.joymain.jecs.pm.model.JpmConfigSpecDetailed)
     */
    public List getJpmConfigSpecDetaileds(final JpmConfigSpecDetailed jpmConfigSpecDetailed) {
        return getHibernateTemplate().find("from JpmConfigSpecDetailed");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpmConfigSpecDetailed == null) {
            return getHibernateTemplate().find("from JpmConfigSpecDetailed");
        } else {
            // filter on properties set in the jpmConfigSpecDetailed
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpmConfigSpecDetailed).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpmConfigSpecDetailed.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmConfigSpecDetailedDao#getJpmConfigSpecDetailed(Long specNo)
     */
    public JpmConfigSpecDetailed getJpmConfigSpecDetailed(final Long specNo) {
        JpmConfigSpecDetailed jpmConfigSpecDetailed = (JpmConfigSpecDetailed) getHibernateTemplate().get(JpmConfigSpecDetailed.class, specNo);
        if (jpmConfigSpecDetailed == null) {
            log.warn("uh oh, jpmConfigSpecDetailed with specNo '" + specNo + "' not found...");
            throw new ObjectRetrievalFailureException(JpmConfigSpecDetailed.class, specNo);
        }

        return jpmConfigSpecDetailed;
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmConfigSpecDetailedDao#saveJpmConfigSpecDetailed(JpmConfigSpecDetailed jpmConfigSpecDetailed)
     */    
    public void saveJpmConfigSpecDetailed(final JpmConfigSpecDetailed jpmConfigSpecDetailed) {
        getHibernateTemplate().saveOrUpdate(jpmConfigSpecDetailed);
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmConfigSpecDetailedDao#removeJpmConfigSpecDetailed(Long specNo)
     */
    public void removeJpmConfigSpecDetailed(final Long specNo) {
        getHibernateTemplate().delete(getJpmConfigSpecDetailed(specNo));
    }
    //added for getJpmConfigSpecDetailedsByCrm
    public List getJpmConfigSpecDetailedsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpmConfigSpecDetailed jpmConfigSpecDetailed where 1=1";
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
			hql += " order by specNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    @Override
    public void insertJpmConfigSpecDetailed(JpmConfigSpecDetailed jpmConfigSpecDetailed)
    {
        this.getSession().saveOrUpdate(jpmConfigSpecDetailed);
    }

    @Override
    public void modifyJpmConfigSpecDetailed(JpmConfigSpecDetailed jpmConfigSpecDetailed)
    {
        this.getSession().update(jpmConfigSpecDetailed);
    }

    @Override
    public JpmConfigSpecDetailed getJpmConfigSpecDetailedBySpecNo(Long specNo)
    {
        StringBuffer hql = new StringBuffer("from JpmConfigSpecDetailed j where j.specNo = "+ specNo);
        return (JpmConfigSpecDetailed)this.getObjectByHqlQuery(hql.toString());
    }

    @Override
    public Long getJpmConfigSpecDetailedWeightByConfigNo(Long configNo)
    {
        StringBuffer hql = new StringBuffer("select sum(j.complateWeight) from JpmConfigSpecDetailed j where j.jpmMemberConfig.configNo = " + configNo);
        return (Long)getSession().createQuery(hql.toString()).uniqueResult();
    }

    @Override
    public void delJpmConfigSpecDetailedBySpecNo(Long specNo)
    {
        StringBuffer sql = new StringBuffer("delete from jpm_config_detailed where spec_no = " + specNo);
        this.getJdbcTemplate().execute(sql.toString());
        sql = new StringBuffer("delete from jpm_config_spec_detailed where spec_no = " +specNo);
        this.getJdbcTemplate().execute(sql.toString());
    }

    @Override
    public List<JpmConfigSpecDetailed> getJpmConfigSpecDetailedListByConfigNo(Long configNo)
    {
        StringBuffer hql = new StringBuffer("from  JpmConfigSpecDetailed j where j.jpmMemberConfig.configNo = " + configNo);
        Query query = getSession().createQuery(hql.toString());
        return query.list();
    }

    @Override
    public Long getPriceByConfigNo(String configNo)
    {
        StringBuffer hql = new StringBuffer("select sum(j.price) from JpmConfigSpecDetailed j where j.jpmMemberConfig.configNo = " + configNo);
        return ((BigDecimal)getSession().createQuery(hql.toString()).uniqueResult()).longValue();
    }
}
