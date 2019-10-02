
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.LockMode;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.dao.FiBankbookBalanceDao;
import com.joymain.jecs.fi.model.FiBankbookBalance;
import com.joymain.jecs.fi.model.FiBankbookJournal;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class FiBankbookBalanceDaoHibernate extends BaseDaoHibernate implements FiBankbookBalanceDao {

    /**
     * @see com.sp.spms.fi.dao.FiBankbookBalanceDao#getFiBankbookBalances(com.sp.spms.fi.model.FiBankbookBalance)
     */
    public List getFiBankbookBalances(final FiBankbookBalance fiBankbookBalance) {
        return getHibernateTemplate().find("from FiBankbookBalance");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiBankbookBalance == null) {
            return getHibernateTemplate().find("from FiBankbookBalance");
        } else {
            // filter on properties set in the fiBankbookBalance
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiBankbookBalance).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiBankbookBalance.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.sp.spms.fi.dao.FiBankbookBalanceDao#getFiBankbookBalance(Long fbbId)
     */
    public FiBankbookBalance getFiBankbookBalance(final Long fbbId) {
    	FiBankbookBalance fiBankbookBalance = (FiBankbookBalance) getHibernateTemplate().get(FiBankbookBalance.class, fbbId);
        if (fiBankbookBalance == null) {
            log.warn("uh oh, fiBankbookJournal with fbbId '" + fbbId + "' not found...");
            throw new ObjectRetrievalFailureException(FiBankbookJournal.class, fbbId);
        }

        return fiBankbookBalance;
    }
    
    public FiBankbookBalance getFiBankbookBalance(final String userCode,final String backbookType) {
        FiBankbookBalance fiBankbookBalance = (FiBankbookBalance)this.getObjectByHqlQuery("from FiBankbookBalance where userCode = '" +userCode+ "' and bankbookType='"+backbookType+"'");
        if (fiBankbookBalance == null) {
            log.warn("uh oh, fiBankbookBalance not found...");
        }
        return fiBankbookBalance;
    }

    /**
     * @see com.sp.spms.fi.dao.FiBankbookBalanceDao#saveFiBankbookBalance(FiBankbookBalance fiBankbookBalance)
     */    
    public void saveFiBankbookBalance(final FiBankbookBalance fiBankbookBalance) {
        getHibernateTemplate().saveOrUpdate(fiBankbookBalance);
    }
    
    /**
     * @see com.joymain.jecs.fi.dao.FiBankbookBalanceDao#getFiBankbookBalanceForUpdate(String userCode)
     */
    public FiBankbookBalance getFiBankbookBalanceForUpdate(final Long fbbId) {
    	FiBankbookBalance fiBankbookBalance = (FiBankbookBalance) getHibernateTemplate().get(FiBankbookBalance.class, fbbId, LockMode.UPGRADE);

        return fiBankbookBalance;
    }

    /**
     * @see com.sp.spms.fi.dao.FiBankbookBalanceDao#removeFiBankbookBalance(Long fbbId)
     */
    public void removeFiBankbookBalance(final Long fbbId) {
        getHibernateTemplate().delete(getFiBankbookBalance(fbbId));
    }
    //added for getFiBankbookBalancesByCrm
    public List getFiBankbookBalancesByCrm(CommonRecord crm, Pager pager){
    	String sqlQuery = this.buildSqlString("list", crm);
    	
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			sqlQuery += " order by user_code desc";
		} else {
			sqlQuery += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsBySQL(sqlQuery, pager);
    }

    public List getFiProductPointBalancesByCrm(CommonRecord crm, Pager pager){
    	String sqlQuery = this.buildSqlStringProductPoint("list", crm);
    	
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
		        + " from  fi_bankbook_journal b where b.journal_id=(select max(journal_id) from fi_bankbook_journal c "
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
    /**
     * 获取银行记录
     * @param UserCode
     * @param bankbookType
     * @return
     */
    public FiBankbookBalance getFiBankbookBalanceByUserCodeAndBankbookType(final String userCode, final String bankbookType){
    	return (FiBankbookBalance)this.getObjectByHqlQuery("from FiBankbookBalance where userCode='" + userCode + "' and bankbookType='" + bankbookType + "'");
    }
    
    /**
	 * 生成  产品积分余额查询列表或者统计的SQL
	 * @param buildType
	 * @param crm
	 * @return
	 */
	private String buildSqlStringProductPoint(final String buildType, CommonRecord crm) {
		String companyCode = crm.getString("companyCode", "");
		String productPointType = crm.getString("productPointType", "");

		String sqlQuery = "select a.user_code, a.user_name, fi.balance ";
		if ("stat".equals(buildType)) {
			sqlQuery = "select sum(fi.balance) as sum_balance ";
		}
		sqlQuery += "  from jsys_user a " + " left join (select  b.user_code, b.balance, Rownum As rownum_fi"
		        + " from  fi_product_point_journal b where b.journal_id=(select max(journal_id) from fi_product_point_journal c "
		        + " where c.user_code=b.user_code and c.company_code='" + companyCode + "' and c.product_point_type = '" + productPointType + "'";
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
		System.out.println(sqlQuery);
		return sqlQuery;
	}
}
