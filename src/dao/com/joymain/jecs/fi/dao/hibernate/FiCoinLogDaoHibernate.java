
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.FiCoinLog;
import com.joymain.jecs.fi.dao.FiCoinLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class FiCoinLogDaoHibernate extends BaseDaoHibernate implements FiCoinLogDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiCoinLogDao#getFiCoinLogs(com.joymain.jecs.fi.model.FiCoinLog)
     */
    public List getFiCoinLogs(final FiCoinLog fiCoinLog) {
        return getHibernateTemplate().findByExample(fiCoinLog);

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiCoinLog == null) {
            return getHibernateTemplate().find("from FiCoinLog");
        } else {
            // filter on properties set in the fiCoinLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiCoinLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiCoinLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiCoinLogDao#getFiCoinLog(Long fclId)
     */
    public FiCoinLog getFiCoinLog(final Long fclId) {
        FiCoinLog fiCoinLog = (FiCoinLog) getHibernateTemplate().get(FiCoinLog.class, fclId);
        if (fiCoinLog == null) {
            log.warn("uh oh, fiCoinLog with fclId '" + fclId + "' not found...");
            throw new ObjectRetrievalFailureException(FiCoinLog.class, fclId);
        }

        return fiCoinLog;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiCoinLogDao#saveFiCoinLog(FiCoinLog fiCoinLog)
     */    
    public void saveFiCoinLog(final FiCoinLog fiCoinLog) {
        getHibernateTemplate().saveOrUpdate(fiCoinLog);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiCoinLogDao#removeFiCoinLog(Long fclId)
     */
    public void removeFiCoinLog(final Long fclId) {
        getHibernateTemplate().delete(getFiCoinLog(fclId));
    }
    //added for getFiCoinLogsByCrm
    public List getFiCoinLogsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FiCoinLog fiCoinLog where 1=1";
    	
    	String userCode = crm.getString("userCode","");
    	if(!StringUtil.isEmpty(userCode)){
    		hql += " and userCode ='" + userCode + "'";
    	}
    	
    	String uniqueCode = crm.getString("uniqueCode","");
    	if(!StringUtil.isEmpty(uniqueCode)){
    		hql += " and uniqueCode ='" + uniqueCode + "'";
    	}
    	
    	String status = crm.getString("status","");
    	if(!StringUtil.isEmpty(status)){
    		hql += " and status ='" + status + "'";
    	}
    	
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by fclId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

    /**
     * 获取赠送的所有积分
     * @param userCode
     * @param coinType
     * @return
     */
    public BigDecimal getFiCoinLogAmtByUserCode(final String userCode, final String coinType){
    	String sqlQuery = "select nvl(sum(coin),0) amt from fi_Coin_Log where user_Code = '" + userCode +"' and coin_Type='" + coinType +"'";
    	Map coinMap = (Map)this.findObjectsBySQL(sqlQuery).get(0);
    	return new BigDecimal(coinMap.get("amt").toString());
    }
}
