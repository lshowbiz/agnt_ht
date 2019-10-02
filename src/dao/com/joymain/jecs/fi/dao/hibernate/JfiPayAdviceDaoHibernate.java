
package com.joymain.jecs.fi.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiPayAdvice;
import com.joymain.jecs.fi.dao.JfiPayAdviceDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiPayAdviceDaoHibernate extends BaseDaoHibernate implements JfiPayAdviceDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiPayAdviceDao#getJfiPayAdvices(com.joymain.jecs.fi.model.JfiPayAdvice)
     */
    public List getJfiPayAdvices(final JfiPayAdvice jfiPayAdvice) {
        return getHibernateTemplate().find("from JfiPayAdvice");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiPayAdvice == null) {
            return getHibernateTemplate().find("from JfiPayAdvice");
        } else {
            // filter on properties set in the jfiPayAdvice
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiPayAdvice).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiPayAdvice.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiPayAdviceDao#getJfiPayAdvice(String adviceCode)
     */
    public JfiPayAdvice getJfiPayAdvice(final String adviceCode) {
        JfiPayAdvice jfiPayAdvice = (JfiPayAdvice) getHibernateTemplate().get(JfiPayAdvice.class, adviceCode);
        if (jfiPayAdvice == null) {
            log.warn("uh oh, jfiPayAdvice with adviceCode '" + adviceCode + "' not found...");
            throw new ObjectRetrievalFailureException(JfiPayAdvice.class, adviceCode);
        }

        return jfiPayAdvice;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiPayAdviceDao#saveJfiPayAdvice(JfiPayAdvice jfiPayAdvice)
     */    
    public void saveJfiPayAdvice(final JfiPayAdvice jfiPayAdvice) {
        getHibernateTemplate().saveOrUpdate(jfiPayAdvice);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiPayAdviceDao#removeJfiPayAdvice(String adviceCode)
     */
    public void removeJfiPayAdvice(final String adviceCode) {
        getHibernateTemplate().delete(getJfiPayAdvice(adviceCode));
    }
    //added for getJfiPayAdvicesByCrm
    public List getJfiPayAdvicesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiPayAdvice jfiPayAdvice where 1=1" + this.buildHqlQuery(crm);
    	
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by adviceCode desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	/**
	 * 批量保存或更新多个付款通知
	 * @param fiPayAdvices
	 */
	public void saveJfiPayAdvices(List jfiPayAdvices){
		this.getHibernateTemplate().saveOrUpdateAll(jfiPayAdvices);
	}
    
    /**
     * 根据外部参数生成对应的HQL条件语句
     * @param crm
     * @return
     */
    private String buildHqlQuery(CommonRecord crm){
    	String hql="";
    	String companyCode = crm.getString("companyCode", "");
    	if (!"AA".equals(companyCode) && !StringUtils.isEmpty(companyCode)) {
			hql += " and companyCode='" + companyCode + "'";
		}
		
    	String adviceCode = crm.getString("adviceCode", "");
		if (!StringUtils.isEmpty(adviceCode)) {
			hql += " and adviceCode like '%" + adviceCode + "%'";
		}
		
		String userCode = crm.getString("sysUser.userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and sysUser.userCode='" + userCode + "'";
		}
		
		String accountCode = crm.getString("accountCode", "");
		if (!StringUtils.isEmpty(accountCode)) {
			hql += " and accountCode='" + accountCode + "'";
		}
		
		String startPayDate = crm.getString("startPayDate", "");
		if (!StringUtils.isEmpty(startPayDate)) {
			hql += " and payDate>=to_date('" + startPayDate + " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}
		
		String endPayDate = crm.getString("endPayDate", "");
		if (!StringUtils.isEmpty(endPayDate)) {
			hql += " and payDate<=to_date('" + endPayDate + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		
		String startCheckDate= crm.getString("startCheckDate", "");
		if (!StringUtils.isEmpty(startCheckDate)) {
			hql += " and checkTime>=to_date('" + startCheckDate + " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}
		
		String endCheckDate= crm.getString("endCheckDate", "");
		if (!StringUtils.isEmpty(endCheckDate)) {
			hql += " and checkTime<=to_date('" + endCheckDate + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		
		String payDate = crm.getString("payDate", "");
		if (!StringUtils.isEmpty(payDate)) {
			hql += " and payDate=to_date('" + payDate + "','yyyy-mm-dd')";
		}
		
		String notice = crm.getString("notice", "");
		if (!StringUtils.isEmpty(notice)) {
			hql += " and notice like '%" + notice + "%'";
		}
		
		String remark = crm.getString("remark", "");
		if (!StringUtils.isEmpty(remark)) {
			hql += " and remark like '%" + remark + "%'";
		}
		
		String dealType = crm.getString("dealType", "");
		if (!StringUtils.isEmpty(dealType)) {
			hql += " and dealType like '%" + dealType + "%'";
		}
		
		String status = crm.getString("status", "");
		if (!StringUtils.isEmpty(status)) {
			hql += " and status in (" + status + ")";
		}
		
		String checkerName = crm.getString("checkerName", "");
		if (!StringUtils.isEmpty(checkerName)) {
			hql += " and checkerName like '%" + checkerName + "%'";
		}
		
		String payMoney = crm.getString("payMoney", "");
		if (!StringUtils.isEmpty(payMoney)) {
			hql += " and payMoney='" + payMoney + "'";
		}
		
		String startCreateDate= crm.getString("startCreateDate", "");
		if (!StringUtils.isEmpty(startCreateDate)) {
			hql += " and createTime>=to_date('" + startCreateDate + " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}
		
		String endCreateDate= crm.getString("endCreateDate", "");
		if (!StringUtils.isEmpty(endCreateDate)) {
			hql += " and createTime<=to_date('" + endCreateDate + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		
		String isForVerify = crm.getString("isForVerify", "");
		if ("1".equals(isForVerify)) {
			//如果做为核实到款用,则读出到款数据为空或者小于付款数据的记录
			hql += " and (arrivedMoney is null or arrivedMoney<payMoney) and status in (1,4)";
		}
		
		return hql;
    }
	
	/**
     * 获取存折查询统计
     * @param crm
     * @return
     */
    public Map getJfiPayAdviceStatMap(CommonRecord crm){
		String totalHql = "select sum(payMoney) as totalTradeMoney, sum(arrivedMoney) as totalArrivedMoney from JfiPayAdvice where 1=1 " + this.buildHqlQuery(crm);
		Object[] totalResult = (Object[]) this.getObjectByHqlQuery(totalHql);
		
		Map<String, BigDecimal> incExpStatMap = new HashMap<String, BigDecimal>();
		if(totalResult!=null){
			incExpStatMap.put("totalTradeMoney", (BigDecimal)totalResult[0]);
			incExpStatMap.put("totalArrivedMoney", (BigDecimal)totalResult[1]);
		}
		
		return incExpStatMap;
    }
	
	/**
	 * 代理商分组查询付款通知
	 * @param crm
	 * @return
	 */
	public List getJfiPayAdvicesStatGroup(CommonRecord crm){
		String sqlQuery="select a.user_code, c.user_name, sum(a.pay_money) as total_pay_money, " +
		" sum(a.arrived_money) as total_arrived_money, count(a.advice_code) as  total_advice " +
		" from jfi_pay_advice a " +
		" left join jsys_user c on c.user_code = a.user_code ";
		sqlQuery+=buildJfiPayAdvicesStatSql(crm)+" group by a.user_code, c.user_name order by a.user_code";
		return this.findObjectsBySQL(sqlQuery);
	}
	
	/**
	 * 根据外部参数生成SQL
	 * @param crm
	 * @return
	 */
	private String buildJfiPayAdvicesStatSql(CommonRecord crm){
		String sqlQuery=" where 1=1 ";
		String companyCode = crm.getString("companyCode", "");
		if (!"AA".equals(companyCode) && !StringUtils.isEmpty(companyCode)) {
			sqlQuery += " and a.company_code='" + companyCode + "'";
		}
		
		String accountCode = crm.getString("accountCode", "");
		if (!StringUtils.isEmpty(accountCode)) {
			sqlQuery += " and a.account_code='" + accountCode + "'";
		}
		
		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			sqlQuery += " and a.user_code='" + userCode + "'";
		}
		
		String startPayDate = crm.getString("startPayDate", "");
		if (!StringUtils.isEmpty(startPayDate)) {
			sqlQuery += " and a.pay_date>=to_date('" + startPayDate + " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}
		
		String endPayDate = crm.getString("endPayDate", "");
		if (!StringUtils.isEmpty(endPayDate)) {
			sqlQuery += " and a.pay_date<=to_date('" + endPayDate + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		
		String status = crm.getString("status", "");
		if (!StringUtils.isEmpty(status)) {
			sqlQuery += " and a.status in (" + status + ")";
		}
		
		String startCreateDate = crm.getString("startCreateDate", "");
		if (!StringUtils.isEmpty(startCreateDate)) {
			sqlQuery += " and a.create_time>=to_date('" + startCreateDate + "','yyyy-mm-dd hh24:mi:ss')";
		}

		String endCreateDate = crm.getString("endCreateDate", "");
		if (!StringUtils.isEmpty(endCreateDate)) {
			sqlQuery += " and a.create_time<=to_date('" + endCreateDate + "','yyyy-mm-dd hh24:mi:ss')";
		}
		return sqlQuery;
	}
	
	/**
	 * 银行提款报表
	 * @param crm
	 * @return
	 */
	public List getJfiPayAdvicesStat(CommonRecord crm){
		String sqlQuery="select a.*,b.bank_name,b.account_name,b.account_no,b.bank_city,b.serial_no " +
		" from jfi_pay_advice a " +
		" LEFT JOIN jfi_pay_bank b ON a.company_code = b.company_code and a.account_code = b.account_code ";

		sqlQuery+=buildJfiPayAdvicesStatSql(crm)+" order by a.user_code,b.bank_name,b.account_code,a.advice_code";
		
		return this.findObjectsBySQL(sqlQuery);
	}
}
