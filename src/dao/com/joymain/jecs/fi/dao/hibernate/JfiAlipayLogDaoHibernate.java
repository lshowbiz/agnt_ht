
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiAlipayLog;
import com.joymain.jecs.fi.dao.JfiAlipayLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiAlipayLogDaoHibernate extends BaseDaoHibernate implements JfiAlipayLogDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiAlipayLogDao#getJfiAlipayLogs(com.joymain.jecs.fi.model.JfiAlipayLog)
     */
    public List getJfiAlipayLogs(final JfiAlipayLog jfiAlipayLog) {
    	return getHibernateTemplate().findByExample(jfiAlipayLog);

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiAlipayLog == null) {
            return getHibernateTemplate().find("from JfiAlipayLog");
        } else {
            // filter on properties set in the jfiAlipayLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiAlipayLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiAlipayLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiAlipayLogDao#getJfiAlipayLog(Long logId)
     */
    public JfiAlipayLog getJfiAlipayLog(final Long logId) {
        JfiAlipayLog jfiAlipayLog = (JfiAlipayLog) getHibernateTemplate().get(JfiAlipayLog.class, logId);
        if (jfiAlipayLog == null) {
            log.warn("uh oh, jfiAlipayLog with logId '" + logId + "' not found...");
            throw new ObjectRetrievalFailureException(JfiAlipayLog.class, logId);
        }

        return jfiAlipayLog;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiAlipayLogDao#saveJfiAlipayLog(JfiAlipayLog jfiAlipayLog)
     */    
    public void saveJfiAlipayLog(final JfiAlipayLog jfiAlipayLog) {
        getHibernateTemplate().saveOrUpdate(jfiAlipayLog);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiAlipayLogDao#removeJfiAlipayLog(Long logId)
     */
    public void removeJfiAlipayLog(final Long logId) {
        getHibernateTemplate().delete(getJfiAlipayLog(logId));
    }
    //added for getJfiAlipayLogsByCrm
    public List getJfiAlipayLogsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiAlipayLog jfiAlipayLog where 1=1";

		String userCode = crm.getString("userCode", "");
		if(StringUtils.isNotEmpty(userCode)){
			hql += " and userCode = '" + userCode + "'";
		}

		String inc = crm.getString("inc", "");
		if(StringUtils.isNotEmpty(inc)){
			hql += " and inc = '" + inc + "'";
		}

		String tradeNo = crm.getString("tradeNo", "");
		if(StringUtils.isNotEmpty(tradeNo)){
			hql += " and tradeNo = '" + tradeNo + "'";
		}

		String bankSeqNo = crm.getString("bankSeqNo", "");
		if(StringUtils.isNotEmpty(bankSeqNo)){
			hql += " and bankSeqNo = '" + bankSeqNo + "'";
		}

		String tradeStatus = crm.getString("tradeStatus", "");
		if(StringUtils.isNotEmpty(tradeStatus)){
			hql += " and tradeStatus = '" + tradeStatus + "'";
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
			hql += " order by logId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
