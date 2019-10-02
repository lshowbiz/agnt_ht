
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.dao.JfiBankbookBalanceDao;
import com.joymain.jecs.fi.model.JfiBankbookBalance;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.hibernate.LockMode;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiBankbookBalanceDaoHibernate extends BaseDaoHibernate implements JfiBankbookBalanceDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiBankbookBalanceDao#getJfiBankbookBalances(com.joymain.jecs.fi.model.JfiBankbookBalance)
     */
    public List getJfiBankbookBalances(final JfiBankbookBalance jfiBankbookBalance) {
        return getHibernateTemplate().find("from JfiBankbookBalance");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiBankbookBalance == null) {
            return getHibernateTemplate().find("from JfiBankbookBalance");
        } else {
            // filter on properties set in the jfiBankbookBalance
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiBankbookBalance).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiBankbookBalance.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiBankbookBalanceDao#getJfiBankbookBalance(String userCode)
     */
    public JfiBankbookBalance getJfiBankbookBalance(final String userCode) {
        JfiBankbookBalance jfiBankbookBalance = (JfiBankbookBalance) getHibernateTemplate().get(JfiBankbookBalance.class, userCode);
        if (jfiBankbookBalance == null) {
            log.warn("uh oh, jfiBankbookBalance with userCode '" + userCode + "' not found...");
            throw new ObjectRetrievalFailureException(JfiBankbookBalance.class, userCode);
        }

        return jfiBankbookBalance;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiBankbookBalanceDao#saveJfiBankbookBalance(JfiBankbookBalance jfiBankbookBalance)
     */    
    public void saveJfiBankbookBalance(final JfiBankbookBalance jfiBankbookBalance) {
        getHibernateTemplate().saveOrUpdate(jfiBankbookBalance);
    }
    
    /**
     * @see com.joymain.jecs.fi.dao.FiBankbookBalanceDao#getFiBankbookBalanceForUpdate(String userCode)
     */
    public JfiBankbookBalance getJfiBankbookBalanceForUpdate(final String userCode) {
    	JfiBankbookBalance jfiBankbookBalance = (JfiBankbookBalance) getHibernateTemplate().get(JfiBankbookBalance.class, userCode, LockMode.UPGRADE);

        return jfiBankbookBalance;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiBankbookBalanceDao#removeJfiBankbookBalance(String userCode)
     */
    public void removeJfiBankbookBalance(final String userCode) {
        getHibernateTemplate().delete(getJfiBankbookBalance(userCode));
    }
    //added for getJfiBankbookBalancesByCrm
    public List getJfiBankbookBalancesByCrm(CommonRecord crm, Pager pager){
    	String sqlQuery = this.buildSqlString("list", crm);
    	
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			sqlQuery += " order by user_code desc";
		} else {
			sqlQuery += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsBySQL(sqlQuery, pager);
    }

	/**
	 * 生成存折余额查询列表或者统计的SQL
	 * @param buildType
	 * @param crm
	 * @return
	 */
	private String buildSqlString(final String buildType, CommonRecord crm) {
		String companyCode = crm.getString("companyCode", "");

		String sqlQuery = "select a.user_code, a.user_name, fi.balance ";
		if ("stat".equals(buildType)) {
			sqlQuery = "select sum(fi.balance) as sum_balance ";
		}
		sqlQuery += "  from jsys_user a " + " left join (select  b.user_code, b.balance, Rownum As rownum_fi"
		        + " from  jfi_bankbook_journal b where b.journal_id=(select max(journal_id) from jfi_bankbook_journal c "
		        + " where c.user_code=b.user_code and c.company_code='" + companyCode + "'";
		String endDate = crm.getString("endDate", "");
		if (!StringUtils.isEmpty(endDate)) {
			sqlQuery += " and c.create_time<=to_date('" + endDate + " 23:59:59','yyyy-MM-dd hh24:mi:ss') ";
		}
		sqlQuery += ")) fi on fi.user_code=a.user_code ";
		sqlQuery += " where a.user_type!='C' and a.company_code='" + companyCode
		        + "' and fi.balance > 0 ";

		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			sqlQuery += " and a.user_code='" + userCode + "'";
		}
		/*
		String sqlQuery = "select a.user_code, a.user_name, fi.balance, po.total_count, po.total_money ";
		if ("stat".equals(buildType)) {
			sqlQuery = "select sum(fi.balance) as sum_balance, sum(po.total_count) as sum_count, sum(po.total_money) as sum_money ";
		}
		sqlQuery += "  from jsys_user a " + " left join (select  b.user_code, b.balance, Rownum As rownum_fi"
		        + " from  jfi_bankbook_journal b where b.journal_id=(select max(journal_id) from jfi_bankbook_journal c "
		        + " where c.user_code=b.user_code and c.company_code='" + companyCode + "'";
		String endDate = crm.getString("endDate", "");
		if (!StringUtils.isEmpty(endDate)) {
			sqlQuery += " and c.create_time<=to_date('" + endDate + " 23:59:59','yyyy-MM-dd hh24:mi:ss') ";
		}
		sqlQuery += ")) fi on fi.user_code=a.user_code " + " left join ("
		        + " select poo.*, Rownum As rownum_fi from(select count(d.member_order_no) as total_count,sum(d.amount) as total_money, d.user_code "
		        + " from jpo_member_order d " + " where d.company_code='" + companyCode
		        + "' and d.status=1 ";
		if (!StringUtils.isEmpty(endDate)) {
			sqlQuery += " and d.ORDER_TIME<=to_date('" + endDate + " 23:59:59','yyyy-MM-dd hh24:mi:ss') ";
		}
		sqlQuery += " group by d.user_code) poo) po on po.user_code=a.user_code " + " where a.user_type!='C' and a.company_code='" + companyCode
		        + "' and (fi.balance is not null or po.total_count is not null or po.total_money is not null)";

		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			sqlQuery += " and a.user_code='" + userCode + "'";
		}
*/
		return sqlQuery;
	}
}
