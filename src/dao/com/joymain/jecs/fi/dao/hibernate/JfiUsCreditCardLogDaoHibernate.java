
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiUsCreditCardLog;
import com.joymain.jecs.fi.dao.JfiUsCreditCardLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiUsCreditCardLogDaoHibernate extends BaseDaoHibernate implements JfiUsCreditCardLogDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiUsCreditCardLogDao#getJfiUsCreditCardLogs(com.joymain.jecs.fi.model.JfiUsCreditCardLog)
     */
    public List getJfiUsCreditCardLogs(final JfiUsCreditCardLog jfiUsCreditCardLog) {
        return getHibernateTemplate().find("from JfiUsCreditCardLog");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiUsCreditCardLog == null) {
            return getHibernateTemplate().find("from JfiUsCreditCardLog");
        } else {
            // filter on properties set in the jfiUsCreditCardLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiUsCreditCardLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiUsCreditCardLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiUsCreditCardLogDao#getJfiUsCreditCardLog(Long jucclId)
     */
    public JfiUsCreditCardLog getJfiUsCreditCardLog(final Long jucclId) {
        JfiUsCreditCardLog jfiUsCreditCardLog = (JfiUsCreditCardLog) getHibernateTemplate().get(JfiUsCreditCardLog.class, jucclId);
        if (jfiUsCreditCardLog == null) {
            log.warn("uh oh, jfiUsCreditCardLog with jucclId '" + jucclId + "' not found...");
            throw new ObjectRetrievalFailureException(JfiUsCreditCardLog.class, jucclId);
        }

        return jfiUsCreditCardLog;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiUsCreditCardLogDao#saveJfiUsCreditCardLog(JfiUsCreditCardLog jfiUsCreditCardLog)
     */    
    public void saveJfiUsCreditCardLog(final JfiUsCreditCardLog jfiUsCreditCardLog) {
        getHibernateTemplate().saveOrUpdate(jfiUsCreditCardLog);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiUsCreditCardLogDao#removeJfiUsCreditCardLog(Long jucclId)
     */
    public void removeJfiUsCreditCardLog(final Long jucclId) {
        getHibernateTemplate().delete(getJfiUsCreditCardLog(jucclId));
    }
    //added for getJfiUsCreditCardLogsByCrm
    public List getJfiUsCreditCardLogsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiUsCreditCardLog jfiUsCreditCardLog where 1=1";
    	
    	String userCode = crm.getString("userCode", "");
    	if(!StringUtil.isEmpty(userCode)){
    		hql += " and userCode='" + userCode + "'";
    	}

		String inc = crm.getString("inc", "");
		if(StringUtils.isNotEmpty(inc)){
			hql += " and inc = '" + inc + "'";
		}

		String startCreateTime = crm.getString("startCreateTime", "");
		if(StringUtils.isNotEmpty(startCreateTime)){
			hql += " and createTime >= to_date('" + startCreateTime + "','yyyy-mm-dd hh24:mi:ss')";
		}

		String endCreateTime = crm.getString("endCreateTime", "");
		if(StringUtils.isNotEmpty(endCreateTime)){
			hql += " and createTime < to_date('" + endCreateTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
    	
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by jucclId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
