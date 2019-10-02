
package com.joymain.jecs.fi.dao.hibernate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiBankbookJournal;
import com.joymain.jecs.fi.dao.JfiBankbookJournalDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.SpringStoredProcedure;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiBankbookJournalDaoHibernate extends BaseDaoHibernate implements JfiBankbookJournalDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiBankbookJournalDao#getJfiBankbookJournals(com.joymain.jecs.fi.model.JfiBankbookJournal)
     */
    public List getJfiBankbookJournals(final JfiBankbookJournal jfiBankbookJournal) {
        return getHibernateTemplate().find("from JfiBankbookJournal");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiBankbookJournal == null) {
            return getHibernateTemplate().find("from JfiBankbookJournal");
        } else {
            // filter on properties set in the jfiBankbookJournal
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiBankbookJournal).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiBankbookJournal.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiBankbookJournalDao#getJfiBankbookJournal(Long journalId)
     */
    public JfiBankbookJournal getJfiBankbookJournal(final Long journalId) {
        JfiBankbookJournal jfiBankbookJournal = (JfiBankbookJournal) getHibernateTemplate().get(JfiBankbookJournal.class, journalId);
        if (jfiBankbookJournal == null) {
            log.warn("uh oh, jfiBankbookJournal with journalId '" + journalId + "' not found...");
            throw new ObjectRetrievalFailureException(JfiBankbookJournal.class, journalId);
        }

        return jfiBankbookJournal;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiBankbookJournalDao#saveJfiBankbookJournal(JfiBankbookJournal jfiBankbookJournal)
     */    
    public void saveJfiBankbookJournal(final JfiBankbookJournal jfiBankbookJournal) {
        getHibernateTemplate().saveOrUpdate(jfiBankbookJournal);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiBankbookJournalDao#removeJfiBankbookJournal(Long journalId)
     */
    public void removeJfiBankbookJournal(final Long journalId) {
        getHibernateTemplate().delete(getJfiBankbookJournal(journalId));
    }
    //added for getJfiBankbookJournalsByCrm
    public List getJfiBankbookJournalsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiBankbookJournal jfiBankbookJournal where 1=1";

    	hql += this.buildListSqlQuery(crm, null);

		if (!pager.getLimit().getSort().isSorted()) {
			//sort
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

		String userCode = crm.getString("sysUser.userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and sysUser.userCode='" + userCode + "'";
		}

		String moneyType = crm.getString("moneyType", "");
		if (!StringUtils.isEmpty(moneyType)) {
			hql += " and moneyType='" + moneyType + "'";
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
			hql += " and createTime<=to_date('" + endCreateDate + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		
		String dataType = crm.getString("dataType", "");
		if (!StringUtils.isEmpty(dataType)) {
			hql += " and dataType='" + dataType + "'";
		}
		return hql;
	}
	
	/**
	 * 审批存折临时条目
	 * @param tempId
	 * @param sysUser
	 * @return
	 */
	public Map callProcCheckJfiBankbookTemp(final Long tempId, final SysUser sysUser) {
		/*
		   1 - 余额不足
		   2 - 已审核
		   3 - 其它错误
		   4 - 不存在
		   0 - 正确
		  */
		SpringStoredProcedure spComp = new SpringStoredProcedure(jdbcTemplate, "Pack_Financial_Process.Jfi_Check_Bankbook_Temp", false);

		//注册参数类型,输入参数和输出参数同时注册,否则不能正确编译存储过程
		spComp.setParameter("p_temp_id", java.sql.Types.NUMERIC);
		spComp.setParameter("p_Operater_Code", java.sql.Types.VARCHAR);
		spComp.setParameter("p_Operater_Name", java.sql.Types.VARCHAR);

		spComp.setOutParameter("Vi_Errno", oracle.jdbc.OracleTypes.INTEGER);
		spComp.setOutParameter("Vc_Errmsg", oracle.jdbc.OracleTypes.VARCHAR);

		spComp.compile();

		// 传入输入参数值
		Map<String, Object> inComp = new HashMap<String, Object>();
		inComp.put("p_temp_id", tempId);
		inComp.put("p_Operater_Code", sysUser.getUserCode());
		inComp.put("p_Operater_Name", sysUser.getUserName());

		spComp.SetInParam(inComp);
		// 执行存储过程
		Map resultComp = spComp.execute();

		return resultComp;
	}

	/**
	 * 获取收支统计金额 incTotal:收入 expTotal:支出
	 * @param crm
	 * @return
	 */
	public Map getIncExpStatMap(CommonRecord crm) {
		String incHql = "select sum(money) as inMoney from JfiBankbookJournal where 1=1 " + this.buildListSqlQuery(crm, "A");
		BigDecimal incTotal = (BigDecimal) this.getObjectByHqlQuery(incHql);

		String expHql = "select sum(money) as inMoney from JfiBankbookJournal where 1=1 " + this.buildListSqlQuery(crm, "D");
		BigDecimal expTotal = (BigDecimal) this.getObjectByHqlQuery(expHql);

		Map<String, BigDecimal> incExpStatMap = new HashMap<String, BigDecimal>();
		incExpStatMap.put("incTotal", incTotal);
		incExpStatMap.put("expTotal", expTotal);

		return incExpStatMap;
	}
	
	/**
	 * 获取验证ID对应的最后一条存折记录
	 * @param uniqueCode
	 * @return
	 */
	public JfiBankbookJournal getLastJfiBankbookJournalByUnique(final String uniqueCode,final String dealType) {
		try{
			return (JfiBankbookJournal) this.getObjectByHqlQuery("from JfiBankbookJournal where uniqueCode='" + uniqueCode
		        + "' and dealType='" + dealType + "' order by journalId desc");
		}catch(ClassCastException ex){
			return null;
		}
	}

	/**
	 * 获取某用户的存折流水条数
	 * @param companyCode
	 * @param agentNo
	 * @return
	 */
	public long getCountByDate(final String companyCode, final String userCode) {
		return this.getTotalByHql("select count(*) as totalCount from JfiBankbookJournal where companyCode='" + companyCode
		        + "' and sysUser.userCode='" + userCode + "'");
	}

	/**
	 * 获取公司截止至某一日期时(包含此日期)的用户电子存折余额总计
	 * @param companyCode
	 * @param queryDate yyyy-MM-dd
	 * @return
	 */
	public BigDecimal getTotalBalanceByDate(final String companyCode, final String queryDate) {
		String sqlQuery = "select sum( b.balance) as total_balance from jfi_bankbook_journal b "
		        + " where b.journal_id = (select max(journal_id) from jfi_bankbook_journal c where c.user_code = b.user_code "
		        + " and c.company_code = '" + companyCode + "' and c.create_time <= to_date('" + queryDate + " 23:59:59','yyyy-mm-dd hh24:mi:ss')) "
		        + " and b.user_code in (select user_code from jsys_user where user_type != 'C' and company_code = '" + companyCode + "')";
		BigDecimal totalBalance = new BigDecimal(0);
		Map queryMap = (Map) this.findObjectsBySQL(sqlQuery).get(0);
		if (queryMap.get("total_balance") != null && !StringUtils.isEmpty(queryMap.get("total_balance").toString())) {
			totalBalance = new BigDecimal(queryMap.get("total_balance").toString());
		}

		return totalBalance;
	}

	/**
	 * 获取用户对应的最后一条存折记录
	 * @param userCode
	 * @return
	 */
	public JfiBankbookJournal getLastJfiBankbookJournal(final String userCode) {
		return (JfiBankbookJournal) this.getObjectByHqlQuery("from JfiBankbookJournal where sysUser.userCode='" + userCode
		        + "' order by journalId desc");
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
	public List getJfiBankbookJournalsStat(final String companyCode, final String dealType, final Integer[] moneyTypes,
	        boolean inverseMoneyType, final String startDate, final String endDate) {
		String sqlQuery = "select deal_type, money_type, sum(money) as total_money " + " from jfi_bankbook_journal "
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

		sqlQuery += " and create_time>=to_date('" + startDate + " 00:00:00','yyyy-mm-dd hh24:mi:ss') and create_time<=to_date('" + endDate + " 23:59:59','yyyy-mm-dd hh24:mi:ss') "
		        + " group by deal_type, money_type order by deal_type, money_type";
		return this.findObjectsBySQL(sqlQuery);
	}

	@Override
	public JfiBankbookJournal getBankbookJournal(String userCode, String type,
			String orderNo) {
		String sql = "from JfiBankbookJournal t where t.sysUser.userCode=? " +
				" and t.dealType=? and t.notes like '%"+orderNo+"' order by journalId desc";
		
		Query query = this.getSession().createQuery(sql);
		query.setParameter(0, userCode);
		query.setParameter(1, type);
		
		List<JfiBankbookJournal> list = query.list();
		
		if(list !=null && list.size()>0)
			return list.get(0);
		else 
			return null;
		
	}
}
