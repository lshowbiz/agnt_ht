
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.LockMode;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.dao.FiProductPointBalanceDao;
import com.joymain.jecs.fi.model.FiProductPointBalance;
import com.joymain.jecs.fi.model.FiProductPointJournal;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class FiProductPointBalanceDaoHibernate extends BaseDaoHibernate implements FiProductPointBalanceDao {

    /**
     * @see com.sp.spms.fi.dao.FiProductPointBalanceDao#getFiProductPointBalances(com.sp.spms.fi.model.FiProductPointBalance)
     */
    public List getFiProductPointBalances(final FiProductPointBalance fiProductPointBalance) {
        return getHibernateTemplate().find("from FiProductPointBalance");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiProductPointBalance == null) {
            return getHibernateTemplate().find("from FiProductPointBalance");
        } else {
            // filter on properties set in the fiProductPointBalance
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiProductPointBalance).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiProductPointBalance.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.sp.spms.fi.dao.FiProductPointBalanceDao#getFiProductPointBalance(Long fbbId)
     */
    public FiProductPointBalance getFiProductPointBalance(final Long fbbId) {
    	FiProductPointBalance fiProductPointBalance = (FiProductPointBalance) getHibernateTemplate().get(FiProductPointBalance.class, fbbId);
        if (fiProductPointBalance == null) {
            log.warn("uh oh, fiProductPointJournal with fbbId '" + fbbId + "' not found...");
            throw new ObjectRetrievalFailureException(FiProductPointJournal.class, fbbId);
        }

        return fiProductPointBalance;
    }
    
    public FiProductPointBalance getFiProductPointBalance(final String userCode,final String backbookType) {
        FiProductPointBalance fiProductPointBalance = (FiProductPointBalance)this.getObjectByHqlQuery("from FiProductPointBalance where userCode = '" +userCode+ "' and productPointType='"+backbookType+"'");
        if (fiProductPointBalance == null) {
            log.warn("uh oh, fiProductPointBalance not found...");
        }
        return fiProductPointBalance;
    }

    /**
     * @see com.sp.spms.fi.dao.FiProductPointBalanceDao#saveFiProductPointBalance(FiProductPointBalance fiProductPointBalance)
     */    
    public void saveFiProductPointBalance(final FiProductPointBalance fiProductPointBalance) {
        getHibernateTemplate().saveOrUpdate(fiProductPointBalance);
    }
    
    /**
     * @see com.joymain.jecs.fi.dao.FiProductPointBalanceDao#getFiProductPointBalanceForUpdate(String userCode)
     */
    public FiProductPointBalance getFiProductPointBalanceForUpdate(final Long fbbId) {
    	FiProductPointBalance fiProductPointBalance = (FiProductPointBalance) getHibernateTemplate().get(FiProductPointBalance.class, fbbId, LockMode.UPGRADE);

        return fiProductPointBalance;
    }

    /**
     * @see com.sp.spms.fi.dao.FiProductPointBalanceDao#removeFiProductPointBalance(Long fbbId)
     */
    public void removeFiProductPointBalance(final Long fbbId) {
        getHibernateTemplate().delete(getFiProductPointBalance(fbbId));
    }
    //added for getFiProductPointBalancesByCrm
    public List getFiProductPointBalancesByCrm(CommonRecord crm, Pager pager){
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
		String productPointType = crm.getString("productPointType", "");

		String sqlQuery = "select a.user_code, a.user_name, fi.balance ";
		if ("stat".equals(buildType)) {
			sqlQuery = "select sum(fi.balance) as sum_balance ";
		}
		sqlQuery += "  from jsys_user a " + " left join (select  b.user_code, b.balance, Rownum As rownum_fi"
		        + " from  FI_PRODUCT_POINT_BALANCE b where b.journal_id=(select max(journal_id) from FI_PRODUCT_POINT_BALANCE c "
		        + " where c.user_code=b.user_code and c.company_code='" + companyCode + "' and c.ProductPoint_type = '" + productPointType + "'";
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
     * @param ProductPointType
     * @return
     */
    public FiProductPointBalance getFiProductPointBalanceByUserCodeAndProductPointType(final String userCode, final String productPointType){
    	return (FiProductPointBalance)this.getObjectByHqlQuery("from FiProductPointBalance where userCode='" + userCode + "' and productPointType='" + productPointType + "'");
    }
}
