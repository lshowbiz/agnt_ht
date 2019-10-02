
package com.joymain.jecs.pm.dao.hibernate;

import java.util.List;
import java.util.Set;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.model.JpmConfigDetailed;
import com.joymain.jecs.pm.dao.JpmConfigDetailedDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JpmConfigDetailedDaoHibernate extends BaseDaoHibernate implements JpmConfigDetailedDao {

    /**
     * @see com.joymain.jecs.pm.dao.JpmConfigDetailedDao#getJpmConfigDetaileds(com.joymain.jecs.pm.model.JpmConfigDetailed)
     */
    public List getJpmConfigDetaileds(final JpmConfigDetailed jpmConfigDetailed) {
        return getHibernateTemplate().find("from JpmConfigDetailed");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpmConfigDetailed == null) {
            return getHibernateTemplate().find("from JpmConfigDetailed");
        } else {
            // filter on properties set in the jpmConfigDetailed
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpmConfigDetailed).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpmConfigDetailed.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmConfigDetailedDao#getJpmConfigDetailed(Long configdetailedNo)
     */
    public JpmConfigDetailed getJpmConfigDetailed(final Long configdetailedNo) {
        JpmConfigDetailed jpmConfigDetailed = (JpmConfigDetailed) getHibernateTemplate().get(JpmConfigDetailed.class, configdetailedNo);
        if (jpmConfigDetailed == null) {
            log.warn("uh oh, jpmConfigDetailed with configdetailedNo '" + configdetailedNo + "' not found...");
            throw new ObjectRetrievalFailureException(JpmConfigDetailed.class, configdetailedNo);
        }

        return jpmConfigDetailed;
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmConfigDetailedDao#saveJpmConfigDetailed(JpmConfigDetailed jpmConfigDetailed)
     */    
    public void saveJpmConfigDetailed(final JpmConfigDetailed jpmConfigDetailed) {
        getHibernateTemplate().saveOrUpdate(jpmConfigDetailed);
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmConfigDetailedDao#removeJpmConfigDetailed(Long configdetailedNo)
     */
    public void removeJpmConfigDetailed(final Long configdetailedNo) {
        getHibernateTemplate().delete(getJpmConfigDetailed(configdetailedNo));
    }
    //added for getJpmConfigDetailedsByCrm
    public List getJpmConfigDetailedsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpmConfigDetailed jpmConfigDetailed where 1=1";
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
			hql += " order by configdetailedNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    @Override
    public Integer addJpmConfigDetailed(JpmConfigDetailed jpmConfigDetailed)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Integer delJpmConfigDetailed(Long detailedId)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Integer modifyJpmConfigDetailed(JpmConfigDetailed jpmConfigDetailed)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public List<JpmConfigDetailed> getJpmConfigDetailedBySpecNo(String specNo)
    {
        StringBuffer hql =
            new StringBuffer("from JpmConfigDetailed j where j.specNo = " + Long.parseLong(specNo));
        Query query = getSession().createQuery(hql.toString());
        return query.list();
    }
    
    @Override
    public void saveJpmConfigDetailedList(Set<JpmConfigDetailed> jpmConfigDetailedList)
    {
        for (JpmConfigDetailed jpmConfigDetailed : jpmConfigDetailedList)
        {
            this.getSession().saveOrUpdate(jpmConfigDetailed);
        }
        
    }
    
    @Override
    public void delJpmConfigDetailedBySpecNo(Long specNo)
    {
        String sql = "delete from jpm_config_detailed j where j.spec_no =" + specNo;
        this.jdbcTemplate.execute(sql);
    }

    @Override
    public JpmConfigDetailed getJpmConfigDetailedNumBySpecNo(String specNo)
    {
        StringBuffer hql = new StringBuffer("from JpmConfigDetailed where isMainMaterial = '1' and specNo = "+specNo );
        return (JpmConfigDetailed)this.getObjectByHqlQuery(hql.toString());
    }
    
}
