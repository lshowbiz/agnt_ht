
package com.joymain.jecs.pm.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.model.JpmSendConsignment;
import com.joymain.jecs.pm.dao.JpmSendConsignmentDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JpmSendConsignmentDaoHibernate extends BaseDaoHibernate implements JpmSendConsignmentDao {

    /**
     * @see com.joymain.jecs.pm.dao.JpmSendConsignmentDao#getJpmSendConsignments(com.joymain.jecs.pm.model.JpmSendConsignment)
     */
    public List getJpmSendConsignments(final JpmSendConsignment jpmSendConsignment) {
        return getHibernateTemplate().find("from JpmSendConsignment");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpmSendConsignment == null) {
            return getHibernateTemplate().find("from JpmSendConsignment");
        } else {
            // filter on properties set in the jpmSendConsignment
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpmSendConsignment).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpmSendConsignment.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmSendConsignmentDao#getJpmSendConsignment(BigDecimal consignmentNo)
     */
    public JpmSendConsignment getJpmSendConsignment(final BigDecimal consignmentNo) {
        JpmSendConsignment jpmSendConsignment = (JpmSendConsignment) getHibernateTemplate().get(JpmSendConsignment.class, consignmentNo);
        if (jpmSendConsignment == null) {
            log.warn("uh oh, jpmSendConsignment with consignmentNo '" + consignmentNo + "' not found...");
            throw new ObjectRetrievalFailureException(JpmSendConsignment.class, consignmentNo);
        }

        return jpmSendConsignment;
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmSendConsignmentDao#saveJpmSendConsignment(JpmSendConsignment jpmSendConsignment)
     */    
    public void saveJpmSendConsignment(final JpmSendConsignment jpmSendConsignment) {
        getHibernateTemplate().saveOrUpdate(jpmSendConsignment);
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmSendConsignmentDao#removeJpmSendConsignment(BigDecimal consignmentNo)
     */
    public void removeJpmSendConsignment(final BigDecimal consignmentNo) {
        getHibernateTemplate().delete(getJpmSendConsignment(consignmentNo));
    }
    //added for getJpmSendConsignmentsByCrm
    public List getJpmSendConsignmentsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpmSendConsignment jpmSendConsignment where 1=1";
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
			hql += " order by consignmentNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    @Override
    public List<JpmSendConsignment> getJpmSendConsignmentListBySpecNo(Long specNo)
    {
        StringBuffer hql = new StringBuffer("from JpmSendConsignment j where j.specNo = " + specNo);
        Query query = getSession().createQuery(hql.toString());
        return query.list();
    }

    @Override
    public void delJpmSendConsignmentByConsignmentNo(Long consignmentNo)
    {
        StringBuffer sql = new StringBuffer("delete from jpm_send_consignment where CONSIGNMENT_NO = " + consignmentNo);
        this.getJdbcTemplate().execute(sql.toString());
    }

    @Override
    public JpmSendConsignment getJpmSendConsignmentByConsignmentNo(Long consignmentNo)
    {
        StringBuffer hql = new StringBuffer("from JpmSendConsignment j where j.consignmentNo = " + consignmentNo);
        return (JpmSendConsignment)this.getObjectByHqlQuery(hql.toString());
    }
}
