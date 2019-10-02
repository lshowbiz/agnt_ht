
package com.joymain.jecs.fi.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.FiFundbookJournal;
import com.joymain.jecs.fi.dao.FiFundbookJournalDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class FiFundbookJournalDaoHibernate extends BaseDaoHibernate implements FiFundbookJournalDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiFundbookJournalDao#getFiFundbookJournals(com.joymain.jecs.fi.model.FiFundbookJournal)
     */
    public List getFiFundbookJournals(final FiFundbookJournal fiFundbookJournal) {
        return getHibernateTemplate().find("from FiFundbookJournal");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiFundbookJournal == null) {
            return getHibernateTemplate().find("from FiFundbookJournal");
        } else {
            // filter on properties set in the fiFundbookJournal
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiFundbookJournal).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiFundbookJournal.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiFundbookJournalDao#getFiFundbookJournal(Long journalId)
     */
    public FiFundbookJournal getFiFundbookJournal(final Long journalId) {
        FiFundbookJournal fiFundbookJournal = (FiFundbookJournal) getHibernateTemplate().get(FiFundbookJournal.class, journalId);
        if (fiFundbookJournal == null) {
            log.warn("uh oh, fiFundbookJournal with journalId '" + journalId + "' not found...");
            throw new ObjectRetrievalFailureException(FiFundbookJournal.class, journalId);
        }

        return fiFundbookJournal;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiFundbookJournalDao#saveFiFundbookJournal(FiFundbookJournal fiFundbookJournal)
     */    
    public void saveFiFundbookJournal(final FiFundbookJournal fiFundbookJournal) {
        getHibernateTemplate().saveOrUpdate(fiFundbookJournal);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiFundbookJournalDao#removeFiFundbookJournal(Long journalId)
     */
    public void removeFiFundbookJournal(final Long journalId) {
        getHibernateTemplate().delete(getFiFundbookJournal(journalId));
    }

    /**
     * 根据条件进行查询产业基金流水列表
     */
    public List getFiFundbookJournalsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FiFundbookJournal fiFundbookJournal where 1=1";
    	
    	hql += this.buildListSqlQuery(crm, null);
		if (!pager.getLimit().getSort().isSorted()) {

			hql += " order by journalId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
	/**
	 * 根据外部参数生成查询语句
	 * @param crm
	 * @return
	 */
	private String buildListSqlQuery(CommonRecord crm, String dealType) {
		String hql = "";
		String companyCode = crm.getString("companyCode", "");
		if (!"AA".equals(companyCode) && !StringUtils.isEmpty(companyCode)) {
			hql += " and companyCode='" + companyCode + "'";
		}

		if (!StringUtils.isEmpty(dealType)) {
			hql += " and dealType='" + dealType + "'";
		}

		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and userCode='" + userCode + "'";
		}

		String moneyType = crm.getString("moneyType", "");
		if (!StringUtils.isEmpty(moneyType)) {
			hql += " and moneyType='" + moneyType + "'";
		}

		String bankbookType = crm.getString("bankbookType", "");
		if (!StringUtils.isEmpty(bankbookType)) {
			hql += " and bankbookType='" + bankbookType + "'";
		}

		String createrCode = crm.getString("createrCode", "");
		if (!StringUtils.isEmpty(createrCode)) {
			hql += " and createrCode='" + createrCode + "'";
		}
		
		String filter = crm.getString("filter", "");
		if(!StringUtils.isEmpty(filter)){
			hql += " and moneyType not in (30,31)";
		}

		String createrName = crm.getString("createrName", "");
		if (!StringUtils.isEmpty(createrName)) {
			hql += " and createrName='" + createrName + "'";
		}

		String startDealDate = crm.getString("startDealDate", "");
		if (!StringUtils.isEmpty(startDealDate)) {
			hql += " and dealDate>=to_date('" + startDealDate + "','yyyy-mm-dd hh24:mi:ss')";
		}

		String endDealDate = crm.getString("endDealDate", "");
		if (!StringUtils.isEmpty(endDealDate)) {
			hql += " and dealDate<=to_date('" + endDealDate + "','yyyy-mm-dd hh24:mi:ss')";
		}

		String startCreateDate = crm.getString("startCreateDate", "");
		if (!StringUtils.isEmpty(startCreateDate)) {
			hql += " and createTime>=to_date('" + startCreateDate + "','yyyy-mm-dd hh24:mi:ss')";
		}

		String endCreateDate = crm.getString("endCreateDate", "");
		if (!StringUtils.isEmpty(endCreateDate)) {
			hql += " and createTime<=to_date('" + endCreateDate + "','yyyy-mm-dd hh24:mi:ss')";
		}
		return hql;
	}
	
	/**
	 * 获取收支统计金额 incTotal:收入 expTotal:支出
	 * @param crm
	 * @return
	 */
	public Map getIncExpStatMap(CommonRecord crm) {
		String incHql = "select sum(money) as inMoney from FiFundbookJournal where 1=1 " + this.buildListSqlQuery(crm, "A");
		BigDecimal incTotal = (BigDecimal) this.getObjectByHqlQuery(incHql);

		String expHql = "select sum(money) as inMoney from FiFundbookJournal where 1=1 " + this.buildListSqlQuery(crm, "D");
		BigDecimal expTotal = (BigDecimal) this.getObjectByHqlQuery(expHql);

		Map<String, BigDecimal> incExpStatMap = new HashMap<String, BigDecimal>();
		incExpStatMap.put("incTotal", incTotal);
		incExpStatMap.put("expTotal", expTotal);

		return incExpStatMap;
	}
	
	/**
	 * 获取验证ID对应的最后一条存折记录
	 * @param uniqueCode 唯一码，用于防止重复
	 * @param dealType 交易类别，A：存入；D：取出
	 * @return
	 */
	public FiFundbookJournal getLastFiFundbookJournalByUnique(final String uniqueCode,final String dealType) {
		try{
			
			return (FiFundbookJournal) this.getObjectByHqlQuery("from FiFundbookJournal where uniqueCode='" + uniqueCode+ "' and dealType='" + dealType + "' order by journalId desc");
		}catch(ClassCastException ex){
			return null;
		}
	}
	
	/**
	 * 获取某用户的产业基金流水条数
	 * @param companyCode
	 * @param agentNo
	 * @return
	 */
	public long getCountByDate(final String companyCode, final String userCode) {
		return this.getTotalByHql("select count(*) as totalCount from FiFundbookJournal where companyCode='" + companyCode + "' and userCode='" + userCode + "'");
	}
	
	/**
	 * 获取公司截止至某一日期时(包含此日期)的用户产业基金余额总计
	 * @param companyCode
	 * @param queryDate yyyy-MM-dd
	 * @return
	 */
	public BigDecimal getTotalBalanceByDate(final String companyCode, final String queryDate, final String bankbookType) {
		String sqlQuery = "select sum( b.balance) as total_balance from fi_fundbook_journal b "
		        + " where b.journal_id = (select max(journal_id) from fi_fundbook_journal c where c.user_code = b.user_code "
		        + " and c.bankbook_type = '" + bankbookType + "' and c.company_code = '" + companyCode + "' and c.create_time <= to_date('" + queryDate + " 23:59:59','yyyy-mm-dd hh24:mi:ss')) "
		        + " and b.user_code in (select user_code from jsys_user where user_type != 'C' and company_code = '" + companyCode + "')";
		BigDecimal totalBalance = new BigDecimal(0);
		Map queryMap = (Map) this.findObjectsBySQL(sqlQuery).get(0);
		if (queryMap.get("total_balance") != null && !StringUtils.isEmpty(queryMap.get("total_balance").toString())) {
			totalBalance = new BigDecimal(queryMap.get("total_balance").toString());
		}

		return totalBalance;
	}
	
	/**
	 * 获取用户对应的最后一条产业基金流水记录
	 * @param userCode
	 * @return
	 */
	public FiFundbookJournal getLastFiFundbookJournal(final String userCode,final String dealType) {
		return (FiFundbookJournal) this.getObjectByHqlQuery("from FiFundbookJournal where userCode='" + userCode
		        + "' and bankbookType = '" + dealType + "' order by journalId desc");
	}
	
	/**
	 * 获取某段日期内,公司在指定资金类别所对应的进出金额
	 * @param companyCode
	 * @param dealType
	 * @param moneyTypes
	 * @param inverseMoneyType 如果为true,则查询不包含在moneyTypes里的记录,如果为false,则为包含在moneyTypes的记录
	 * @param startDate yyyy-MM-dd
	 * @param endDate yyyy-MM-dd
	 * @return
	 */
	public List getFiFundbookJournalsStat(final String companyCode, final String dealType, final Integer[] moneyTypes,
	        boolean inverseMoneyType, final String startDate, final String endDate, final String bankbookType) {
		String sqlQuery = "select deal_type, money_type, sum(money) as total_money " + " from Fi_fundbook_journal "
		        + " where company_code='" + companyCode + "'";
		if (!StringUtils.isEmpty(dealType)) {
			sqlQuery += " and deal_type='" + dealType + "'";
		}
		if (inverseMoneyType) {
			if (moneyTypes != null && moneyTypes.length > 0) {
				sqlQuery += " and money_type not in (";
				for (int i = 0; i < moneyTypes.length; i++) {
					if (i > 0) {
						sqlQuery += ",";
					}
					sqlQuery += moneyTypes[i].toString();
				}
				sqlQuery += ") ";
			}
		} else {
			if (moneyTypes != null && moneyTypes.length > 0) {
				sqlQuery += " and money_type in (";
				for (int i = 0; i < moneyTypes.length; i++) {
					if (i > 0) {
						sqlQuery += ",";
					}
					sqlQuery += moneyTypes[i].toString();
				}
				sqlQuery += ") ";
			} else {
				return null;
			}
		}

		sqlQuery += " and bankbook_type = '" + bankbookType + "' and create_time>=to_date('" + startDate + " 00:00:00','yyyy-mm-dd hh24:mi:ss') and create_time<=to_date('" + endDate + " 23:59:59','yyyy-mm-dd hh24:mi:ss') "
		        + " group by deal_type, money_type order by deal_type, money_type";
		return this.findObjectsBySQL(sqlQuery);
	}
	
	/**
     * Add By WuCF 20140320 查询基金查询统计数据
	 * @param crm
	 * @return
	 */
	public Map getSumAmountByCrm(CommonRecord crm) {
		String hql = "select nvl(sum(case when fiFundbookJournal.dealType='A' then fiFundbookJournal.money end),0) as addMoney,nvl(sum(case when fiFundbookJournal.dealType='D' then fiFundbookJournal.money end),0) as decMoney,nvl(sum(fiFundbookJournal.balance),0) as balance from FiFundbookJournal fiFundbookJournal where 1=1 ";

		hql += this.buildListSqlQuery(crm,null);
		
		Object[] sum = (Object[])this.getObjectByHqlQuery(hql);
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		map.put("addMoney", (BigDecimal) sum[0]);
		map.put("decMoney", (BigDecimal) sum[1]);
		map.put("balance", (BigDecimal) sum[2]);
		
		return map;
	}
}
