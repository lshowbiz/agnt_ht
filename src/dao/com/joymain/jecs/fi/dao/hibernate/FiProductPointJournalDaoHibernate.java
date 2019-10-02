
package com.joymain.jecs.fi.dao.hibernate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.dao.FiProductPointJournalDao;
import com.joymain.jecs.fi.model.FiProductPointJournal;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.data.SpringStoredProcedure;

public class FiProductPointJournalDaoHibernate extends BaseDaoHibernate implements FiProductPointJournalDao {

    /**
     * @see com.sp.spms.fi.dao.FiProductPointJournalDao#getFiProductPointJournals(com.sp.spms.fi.model.FiProductPointJournal)
     */
    public List getFiProductPointJournals(final FiProductPointJournal fiProductPointJournal) {
        return getHibernateTemplate().find("from FiProductPointJournal");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiProductPointJournal == null) {
            return getHibernateTemplate().find("from FiProductPointJournal");
        } else {
            // filter on properties set in the fiProductPointJournal
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiProductPointJournal).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiProductPointJournal.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.sp.spms.fi.dao.FiProductPointJournalDao#getFiProductPointJournal(Long journalId)
     */
    public FiProductPointJournal getFiProductPointJournal(final Long journalId) {
        FiProductPointJournal fiProductPointJournal = (FiProductPointJournal) getHibernateTemplate().get(FiProductPointJournal.class, journalId);
        if (fiProductPointJournal == null) {
            log.warn("uh oh, fiProductPointJournal with journalId '" + journalId + "' not found...");
            throw new ObjectRetrievalFailureException(FiProductPointJournal.class, journalId);
        }

        return fiProductPointJournal;
    }

    /**
     * @see com.sp.spms.fi.dao.FiProductPointJournalDao#saveFiProductPointJournal(FiProductPointJournal fiProductPointJournal)
     */    
    public void saveFiProductPointJournal(final FiProductPointJournal fiProductPointJournal) {
        getHibernateTemplate().saveOrUpdate(fiProductPointJournal);
    }

    /**
     * @see com.sp.spms.fi.dao.FiProductPointJournalDao#removeFiProductPointJournal(Long journalId)
     */
    public void removeFiProductPointJournal(final Long journalId) {
        getHibernateTemplate().delete(getFiProductPointJournal(journalId));
    }
    //added for getFiProductPointJournalsByCrm
    public List getFiProductPointJournalsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FiProductPointJournal fiProductPointJournal where 1=1";

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
     * Add By WuCF 20140320 查询基金查询统计数据
	 * @param crm
	 * @return
	 */
	public Map getSumAmountByCrm(CommonRecord crm) {
		String hql = "select nvl(sum(case when fiProductPointJournal.dealType='A' then fiProductPointJournal.money end),0) as addMoney,nvl(sum(case when fiProductPointJournal.dealType='D' then fiProductPointJournal.money end),0) as decMoney,nvl(sum(fiProductPointJournal.balance),0) as balance from FiProductPointJournal fiProductPointJournal where 1=1 ";
//		String hql = "select nvl(sum(fiProductPointJournal.addMoney),0) as addMoney,nvl(sum(fiProductPointJournal.decMoney),0) as decMoney,nvl(sum(fiProductPointJournal.balance),0) as balance from FiProductPointJournal fiProductPointJournal where 1=1 ";

		hql += this.buildListSqlQuery(crm,null);
		
		Object[] sum = (Object[])this.getObjectByHqlQuery(hql);
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		map.put("addMoney", (BigDecimal) sum[0]);
		map.put("decMoney", (BigDecimal) sum[1]);
		map.put("balance", (BigDecimal) sum[2]);
		
		return map;
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

		String userCodeLike = crm.getString("sysUser.userCodeLike", "");
		if (!StringUtils.isEmpty(userCodeLike)) {
			hql += " and sysUser.userCode like '%" + userCodeLike + "%'";
		}

		String userNameLike = crm.getString("sysUser.userNameLike", "");
		if (!StringUtils.isEmpty(userNameLike)) {
			hql += " and sysUser.userName like '%" + userNameLike + "%'";
		}

		String userCode = crm.getString("sysUser.userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and sysUser.userCode='" + userCode + "'";
		}

		String userName = crm.getString("sysUser.userName", "");
		if (!StringUtils.isEmpty(userName)) {
			hql += " and sysUser.userName='" + userName + "'";
		}

		String moneyType = crm.getString("moneyType", "");
		if (!StringUtils.isEmpty(moneyType)) {
			hql += " and moneyType='" + moneyType + "'";
		}

		String productPointType = crm.getString("productPointType", "");
		if (!StringUtils.isEmpty(productPointType)) {
			hql += " and productPointType='" + productPointType + "'";
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
	 * 审批存折临时条目
	 * @param tempId
	 * @param sysUser
	 * @return
	 */
	public Map callProcCheckFiProductPointTemp(final Long tempId, final SysUser sysUser) {
		/*
		   1 - 余额不足
		   2 - 已审核
		   3 - 其它错误
		   4 - 不存在
		   0 - 正确
		  */
		SpringStoredProcedure spComp = new SpringStoredProcedure(jdbcTemplate, "Pack_Financial_Process.Fi_Check_Productpoint_Temp", false);

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
		String incHql = "select sum(money) as inMoney from FiProductPointJournal where 1=1 " + this.buildListSqlQuery(crm, "A");
		BigDecimal incTotal = (BigDecimal) this.getObjectByHqlQuery(incHql);

		String expHql = "select sum(money) as inMoney from FiProductPointJournal where 1=1 " + this.buildListSqlQuery(crm, "D");
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
	public FiProductPointJournal getLastFiProductPointJournalByUnique(final String uniqueCode,final String dealType) {
		try{
			return (FiProductPointJournal) this.getObjectByHqlQuery("from FiProductPointJournal where uniqueCode='" + uniqueCode
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
		return this.getTotalByHql("select count(*) as totalCount from FiProductPointJournal where companyCode='" + companyCode
		        + "' and sysUser.userCode='" + userCode + "'");
	}

	/**
	 * 获取公司截止至某一日期时(包含此日期)的用户电子存折余额总计
	 * @param companyCode
	 * @param queryDate yyyy-MM-dd
	 * @return
	 */
	public BigDecimal getTotalBalanceByDate(final String companyCode, final String queryDate, final String productPointType) {
		String sqlQuery = "select sum( b.balance) as total_balance from fi_Product_Point_journal b "
		        + " where b.journal_id = (select max(journal_id) from FI_PRODUCT_POINT_JOURNAL c where c.user_code = b.user_code "
		        + " and c.product_Point_type = '" + productPointType + "' and c.company_code = '" + companyCode + "' and c.create_time <= to_date('" + queryDate + " 23:59:59','yyyy-mm-dd hh24:mi:ss')) "
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
	public FiProductPointJournal getLastFiProductPointJournal(final String userCode,final String dealType) {
		return (FiProductPointJournal) this.getObjectByHqlQuery("from FiProductPointJournal where sysUser.userCode='" + userCode
		        + "' and productPointType = '" + dealType + "' order by journalId desc");
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
	public List getFiProductPointJournalsStat(final String companyCode, final String dealType, final Integer[] moneyTypes,
	        boolean inverseMoneyType, final String startDate, final String endDate, final String productPointType) {
		String sqlQuery = "select deal_type, money_type, sum(money) as total_money " + " from FI_PRODUCT_POINT_JOURNAL "
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

		sqlQuery += " and PRODUCT_POINT_TYPE = '" + productPointType + "' and create_time>=to_date('" + startDate + " 00:00:00','yyyy-mm-dd hh24:mi:ss') and create_time<=to_date('" + endDate + " 23:59:59','yyyy-mm-dd hh24:mi:ss') "
		        + " group by deal_type, money_type order by deal_type, money_type";
		return this.findObjectsBySQL(sqlQuery);
	}
	
	/**
	 * 查询退单的退基金情况
	 * @author gw 2015-07-10
	 * @param roNo
	 * @return bigDecimal
	 */
	public BigDecimal getBeforeJprRefundJj(String roNo) {
		BigDecimal moneyJiA = new BigDecimal(0);
		BigDecimal moneyJiD = new BigDecimal(0);
		
		//moneyType=15表明的是退款的基金----存入
		String hqlA = " from FiProductPointJournal fiProductPointJournal where fiProductPointJournal.moneyType=15 and fiProductPointJournal.uniqueCode='"+roNo+"' ";
		List<FiProductPointJournal> listA = this.findObjectsByHqlQuery(hqlA);
		if(null!=listA){
			if(listA.size()>0){
				    moneyJiA = listA.get(0).getMoney();
					//moneyType=16表明的是退款的基金----支出
					String hqlD = " from FiProductPointJournal fiProductPointJournal where fiProductPointJournal.moneyType=16 and fiProductPointJournal.uniqueCode='"+roNo+"' ";
					List<FiProductPointJournal> listD = this.findObjectsByHqlQuery(hqlD);
					if(null!=listD){
						if(listD.size()>0){
							moneyJiD = listD.get(0).getMoney();
						}
					}
			}
		}
		return moneyJiA.subtract(moneyJiD);
	}
	
	/**
	 * 查看这张退单之前退了多少基金
	 * @author gw 2015-07-13
	 * @param roNo
	 * @return bigDecimal
	 */
	public BigDecimal getJprRefundJjBack(String roNo) {
		String hql = " from FiProductPointJournal fiProductPointJournal where fiProductPointJournal.moneyType=15 and fiProductPointJournal.uniqueCode='"+roNo+"' ";
		List<FiProductPointJournal> list = this.findObjectsByHqlQuery(hql);
		if(null!=list){
			if(list.size()>0){
				return list.get(0).getMoney();
			}
		}
		return null;
	}
}
