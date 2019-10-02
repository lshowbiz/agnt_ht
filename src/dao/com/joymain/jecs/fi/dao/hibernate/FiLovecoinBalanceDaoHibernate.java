
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;

import org.hibernate.LockMode;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.dao.FiLovecoinBalanceDao;
import com.joymain.jecs.fi.model.FiLovecoinBalance;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.apache.commons.lang.StringUtils;

public class FiLovecoinBalanceDaoHibernate extends BaseDaoHibernate implements FiLovecoinBalanceDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiLovecoinBalanceDao#getFiLovecoinBalances(com.joymain.jecs.fi.model.FiLovecoinBalance)
     */
    public List getFiLovecoinBalances(final FiLovecoinBalance fiLovecoinBalance) {
        return getHibernateTemplate().find("from FiLovecoinBalance");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiLovecoinBalance == null) {
            return getHibernateTemplate().find("from FiLovecoinBalance");
        } else {
            // filter on properties set in the fiLovecoinBalance
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiLovecoinBalance).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiLovecoinBalance.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiLovecoinBalanceDao#getFiLovecoinBalance(String userCode)
     */
    public FiLovecoinBalance getFiLovecoinBalance(final String userCode) {
        FiLovecoinBalance fiLovecoinBalance = (FiLovecoinBalance) getHibernateTemplate().get(FiLovecoinBalance.class, userCode);
//        if (fiLovecoinBalance == null) {
//            log.warn("uh oh, fiLovecoinBalance with userCode '" + userCode + "' not found...");
//            throw new ObjectRetrievalFailureException(FiLovecoinBalance.class, userCode);
//        }

        return fiLovecoinBalance;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiLovecoinBalanceDao#saveFiLovecoinBalance(FiLovecoinBalance fiLovecoinBalance)
     */    
    public void saveFiLovecoinBalance(final FiLovecoinBalance fiLovecoinBalance) {
        getHibernateTemplate().saveOrUpdate(fiLovecoinBalance);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiLovecoinBalanceDao#removeFiLovecoinBalance(String userCode)
     */
    public void removeFiLovecoinBalance(final String userCode) {
        getHibernateTemplate().delete(getFiLovecoinBalance(userCode));
    }
    //added for getFiLovecoinBalancesByCrm
    public List getFiLovecoinBalancesByCrm(CommonRecord crm, Pager pager){
    	
    	String sqlQuery = this.buildSqlString("list", crm);
    	if (!pager.getLimit().getSort().isSorted()) {

			sqlQuery += " order by user_code desc";
		} else {
			sqlQuery += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsBySQL(sqlQuery, pager);
    }
    
	private String buildSqlString(final String buildType, CommonRecord crm) {
		
		String sql = "select f.user_code,a.user_name,f.balance from FI_LOVECOIN_BALANCE f left join jsys_user a on a.user_code=f.user_code where 1=1";
		
		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			sql += " and a.user_code='" + userCode + "'";
		}
		
		return sql;
	}
    
    public FiLovecoinBalance getFiLovecoinBalanceForUpdate(final String userCode){
    	FiLovecoinBalance fiLovecoinBalance = (FiLovecoinBalance) getHibernateTemplate().get(FiLovecoinBalance.class, userCode, LockMode.UPGRADE);

        return fiLovecoinBalance;
    }
}
