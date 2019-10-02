
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.FiFundbookBalance;
import com.joymain.jecs.fi.dao.FiFundbookBalanceDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.hibernate.LockMode;
import org.springframework.orm.ObjectRetrievalFailureException;

public class FiFundbookBalanceDaoHibernate extends BaseDaoHibernate implements FiFundbookBalanceDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiFundbookBalanceDao#getFiFundbookBalances(com.joymain.jecs.fi.model.FiFundbookBalance)
     */
    public List getFiFundbookBalances(final FiFundbookBalance fiFundbookBalance) {
        return getHibernateTemplate().find("from FiFundbookBalance");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiFundbookBalance == null) {
            return getHibernateTemplate().find("from FiFundbookBalance");
        } else {
            // filter on properties set in the fiFundbookBalance
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiFundbookBalance).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiFundbookBalance.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiFundbookBalanceDao#getFiFundbookBalance(Long fbbId)
     */
    public FiFundbookBalance getFiFundbookBalance(final Long fbbId) {
        FiFundbookBalance fiFundbookBalance = (FiFundbookBalance) getHibernateTemplate().get(FiFundbookBalance.class, fbbId);
        if (fiFundbookBalance == null) {
            log.warn("uh oh, fiFundbookBalance with fbbId '" + fbbId + "' not found...");
            throw new ObjectRetrievalFailureException(FiFundbookBalance.class, fbbId);
        }

        return fiFundbookBalance;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiFundbookBalanceDao#saveFiFundbookBalance(FiFundbookBalance fiFundbookBalance)
     */    
    public void saveFiFundbookBalance(final FiFundbookBalance fiFundbookBalance) {
        getHibernateTemplate().saveOrUpdate(fiFundbookBalance);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiFundbookBalanceDao#removeFiFundbookBalance(Long fbbId)
     */
    public void removeFiFundbookBalance(final Long fbbId) {
        getHibernateTemplate().delete(getFiFundbookBalance(fbbId));
    }
    
//    public List getFiFundbookBalancesByCrm(CommonRecord crm, Pager pager){
//    	String hql = "from FiFundbookBalance fiFundbookBalance where 1=1";
//    	/** 
//	---example---
//	String userCode = crm.getString("userCode", "");
//	if(StringUtils.isNotEmpty(userCode)){
//		hql += "???????????";
//	}
//	 ***/
//    	// 
//		if (!pager.getLimit().getSort().isSorted()) {
//			//sort
//			hql += " order by fbbId desc";
//		} else {
//			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
//		}
//		return this.findObjectsByHqlQuery(hql, pager);
//    }
    
    /**
     * 获取银行记录
     * @param UserCode
     * @param bankbookType 基金类型：1，分红基金；2，定向基金
     * @return
     */
    public FiFundbookBalance getFiFundbookBalanceByUserCodeAndFundbookType(final String userCode, final String bankbookType){
    	
    	return (FiFundbookBalance)this.getObjectByHqlQuery("from FiFundbookBalance where userCode='" + userCode + "' and bankbookType='" + bankbookType + "'");
    }
    
    /**
     * 根据会员编号和基金类型，进行锁定一条表记录操作
     * @param fbbId
     * @return
     */
    public FiFundbookBalance getFiFundbookBalanceForUpdate(final Long fbbId) {
    	
    	FiFundbookBalance fiFundbookBalance = (FiFundbookBalance) getHibernateTemplate().get(FiFundbookBalance.class, fbbId, LockMode.UPGRADE);

        return fiFundbookBalance;
    }
    
    /**
     * 根据条件进行查询产业基金余额统计报表
     * @param crm
     * @param pager
     * @return
     */
    public List getFiFundbookBalancesByCrm(CommonRecord crm, Pager pager){
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
		String bankbookType = crm.getString("bankbookType", "");

		String sqlQuery = "select a.user_code, a.user_name, fi.balance ";
		if ("stat".equals(buildType)) {
			sqlQuery = "select sum(fi.balance) as sum_balance ";
		}
		sqlQuery += "  from jsys_user a " + " left join (select  b.user_code, b.balance, Rownum As rownum_fi"
		        + " from  fi_fundbook_journal b where b.journal_id=(select max(journal_id) from fi_fundbook_journal c "
		        + " where c.user_code=b.user_code and c.company_code='" + companyCode + "' and c.bankbook_type = '" + bankbookType + "'";
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
		String userCodeLike = crm.getString("userCodeLike", "");
		if (!StringUtils.isEmpty(userCodeLike)) {
			sqlQuery += " and a.user_code like '%" + userCodeLike + "%'";
		}
		String userNameLike = crm.getString("userNameLike", "");
		if (!StringUtils.isEmpty(userNameLike)) {
			sqlQuery += " and a.user_name like '%" + userNameLike + "%'";
		}
		String begin = crm.getString("begin", "");
		if (!StringUtils.isEmpty(begin)) {
			sqlQuery += " and fi.balance >=" + begin + "";
		}
		String end = crm.getString("end", "");
		if (!StringUtils.isEmpty(end)) {
			sqlQuery += " and fi.balance <=" + end + "";
		}
		return sqlQuery;
	}
}
