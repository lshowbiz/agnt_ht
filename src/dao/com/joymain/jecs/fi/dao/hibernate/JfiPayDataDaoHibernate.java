
package com.joymain.jecs.fi.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiPayData;
import com.joymain.jecs.fi.dao.JfiPayDataDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.data.SpringStoredProcedure;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiPayDataDaoHibernate extends BaseDaoHibernate implements JfiPayDataDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiPayDataDao#getJfiPayDatas(com.joymain.jecs.fi.model.JfiPayData)
     */
    public List getJfiPayDatas(final JfiPayData jfiPayData) {
        return getHibernateTemplate().find("from JfiPayData");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiPayData == null) {
            return getHibernateTemplate().find("from JfiPayData");
        } else {
            // filter on properties set in the jfiPayData
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiPayData).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiPayData.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

	/**
	 * 批量保存多条记录
	 * @param fiPayDatas
	 */
	public void saveJfiPayDatas(List jfiPayDatas){
		this.getHibernateTemplate().saveOrUpdateAll(jfiPayDatas);
	}

    /**
     * @see com.joymain.jecs.fi.dao.JfiPayDataDao#getJfiPayData(Long dataId)
     */
    public JfiPayData getJfiPayData(final Long dataId) {
        JfiPayData jfiPayData = (JfiPayData) getHibernateTemplate().get(JfiPayData.class, dataId);
        if (jfiPayData == null) {
            log.warn("uh oh, jfiPayData with dataId '" + dataId + "' not found...");
            throw new ObjectRetrievalFailureException(JfiPayData.class, dataId);
        }

        return jfiPayData;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiPayDataDao#saveJfiPayData(JfiPayData jfiPayData)
     */    
    public void saveJfiPayData(final JfiPayData jfiPayData) {
        getHibernateTemplate().saveOrUpdate(jfiPayData);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiPayDataDao#removeJfiPayData(Long dataId)
     */
    public void removeJfiPayData(final Long dataId) {
        getHibernateTemplate().delete(getJfiPayData(dataId));
    }
    //added for getJfiPayDatasByCrm
    public List getJfiPayDatasByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiPayData jfiPayData where 1=1";

		String companyCode = crm.getString("companyCode", "");
		if (!"AA".equals(companyCode)) {
			hql += " and companyCode='" + companyCode + "'";
		}

		String accountCode = crm.getString("accountCode", "");
		if (!StringUtils.isEmpty(accountCode)) {
			hql += " and accountCode='" + accountCode + "'";
		}

		String userCode = crm.getString("sysUser.userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and sysUser.userCode='" + userCode + "'";
		}

		String incomeMoney = crm.getString("incomeMoney", "");
		if (!StringUtils.isEmpty(incomeMoney)) {
			hql += " and incomeMoney=" + incomeMoney + "";
		}

		String status = crm.getString("status", "");
		if (!StringUtils.isEmpty(status)) {
			hql += " and status='" + status + "'";
		}
		//excerpt
		String excerpt = crm.getString("excerpt", "");
		if (!StringUtils.isEmpty(excerpt)) {
			hql += " and excerpt like '%" + excerpt + "%'";
		}

		String createrCode = crm.getString("createrCode", "");
		if (!StringUtils.isEmpty(createrCode)) {
			hql += " and createrCode='" + createrCode + "'";
		}

		String startDealDate = crm.getString("startDealDate", "");
		if (!StringUtils.isEmpty(startDealDate)) {
			hql += " and dealDate>=to_date('" + startDealDate + " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}

		String endDealDate = crm.getString("endDealDate", "");
		if (!StringUtils.isEmpty(endDealDate)) {
			hql += " and dealDate<=to_date('" + endDealDate + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}

		String createNo = crm.getString("createNo", "");
		if (!StringUtils.isEmpty(createNo)) {
			hql += " and createNo='" + createNo + "'";
		}

    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by dataId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
	
	/**
	 * 通过存储过程审核到款数据
	 * @param dataId
	 * @param adviceCode
	 * @param operaterCode
	 * @param operaterName
	 * @param notice
	 * @return
	 */
	public Map saveJfiPayDataVerifyProc(final String dataId, String adviceCode, final String operaterCode, final String operaterName,
	        final String notice) {
		SpringStoredProcedure spComp = new SpringStoredProcedure(jdbcTemplate, "Pack_Financial_Process.Jfi_Verify_Pay_Data", false);
		/*
		 * p_Data_Id Integer, p_User_Code Char, p_Advice_Code Char, p_Operater_Code Char, p_Operater_Name Char, p_Notice Char, Vi_Errno Out Integer, Vc_Errmsg Out Char
		 */
		//注册参数类型,输入参数和输出参数同时注册,否则不能正确编译存储过程
		spComp.setParameter("p_Data_Id", java.sql.Types.INTEGER);
		spComp.setParameter("p_Advice_Code", java.sql.Types.VARCHAR);
		spComp.setParameter("p_Operater_Code", java.sql.Types.VARCHAR);
		spComp.setParameter("p_Operater_Name", java.sql.Types.VARCHAR);
		spComp.setParameter("p_Notice", java.sql.Types.VARCHAR);

		spComp.setOutParameter("Vi_Errno", oracle.jdbc.OracleTypes.INTEGER);
		spComp.setOutParameter("p_out_code", oracle.jdbc.OracleTypes.VARCHAR);

		spComp.compile();

		// 传入输入参数值
		Map<String, Object> inComp = new HashMap<String, Object>();
		inComp.put("p_Data_Id", new Long(dataId));
		inComp.put("p_Advice_Code", adviceCode);
		inComp.put("p_Operater_Code", operaterCode);
		inComp.put("p_Operater_Name", operaterName);
		inComp.put("p_Notice", notice);

		spComp.SetInParam(inComp);
		// 执行存储过程
		Map resultComp = spComp.execute();

		return resultComp;
	}
	
	/**
	 * 通过存储过程取消审核到款数据
	 * @param dataId
	 * @param operaterCode
	 * @param operaterName
	 * @param unVerifyRemark
	 * @param notice
	 * @return
	 */
	public Map saveJfiPayDataUnverifyProc(final String dataId, final String operaterCode, final String operaterName,
	        final String unVerifyRemark, final String notice) {
		/*
		  Errorno: 1 - 存折或到款数据不存在
		           2 - 存折或到款数据未审核
		           3 - 存折余额不够
		           4 - 其它错误
		           0 - 正确
		  */
		SpringStoredProcedure spComp = new SpringStoredProcedure(jdbcTemplate, "Pack_Financial_Process.Jfi_Unverify_Pay_Data", false);

		//注册参数类型,输入参数和输出参数同时注册,否则不能正确编译存储过程
		spComp.setParameter("p_data_Id", java.sql.Types.INTEGER);
		spComp.setParameter("p_Operater_Code", java.sql.Types.VARCHAR);
		spComp.setParameter("p_Operater_Name", java.sql.Types.VARCHAR);
		spComp.setParameter("p_unverify_remark", java.sql.Types.VARCHAR);
		spComp.setParameter("p_Notice", java.sql.Types.VARCHAR);

		spComp.setOutParameter("Vi_Errno", oracle.jdbc.OracleTypes.INTEGER);
		spComp.setOutParameter("p_out_code", oracle.jdbc.OracleTypes.VARCHAR);

		spComp.compile();

		// 传入输入参数值
		Map<String, Object> inComp = new HashMap<String, Object>();
		inComp.put("p_data_Id", new Long(dataId));
		inComp.put("p_Operater_Code", operaterCode);
		inComp.put("p_Operater_Name", operaterName);
		inComp.put("p_unverify_remark", unVerifyRemark);
		inComp.put("p_Notice", notice);

		spComp.SetInParam(inComp);
		// 执行存储过程
		Map resultComp = spComp.execute();

		return resultComp;
	}

	/**
	 * 根据外部条件获取对应的到款数据统计
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getJfiPayDataStatsByCrm(CommonRecord crm, Pager pager) {
		String sqlQuery = "select a.*,b.account_code as advice_account_code, b.pay_date, b.pay_money,b.user_code as pay_user_code, c.bank_name, d.user_name as pay_user_name "
	        + " from jfi_pay_data a "
	        + " LEFT JOIN jfi_pay_advice b ON (a.company_code = b.company_code and a.advice_code = b.advice_code) "
	        + " left join jfi_pay_bank c on a.account_code=c.account_code "
	        + " left join jsys_user d on a.user_code=d.user_code "
	        + " where 1=1 " + this.buildFiPayDataStatSql(crm);

	if (!pager.getLimit().getSort().isSorted()) {
		//ȱʡ����
		sqlQuery += " order by data_id desc";
	} else {
		sqlQuery += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
	}

	return this.findObjectsBySQL(sqlQuery, pager);
	}

	/**
	 * 生成到款数据统计用SQL
	 * @param crm
	 * @return
	 */
	private String buildFiPayDataStatSql(CommonRecord crm) {
		String sqlQuery = "";
		String companyCode = crm.getString("companyCode", "");
		if (!"AA".equals(companyCode) && !StringUtils.isEmpty(companyCode)) {
			sqlQuery += " and a.company_code='" + companyCode + "'";
		}

		String accountCode = crm.getString("accountCode", "");
		if (!StringUtils.isEmpty(accountCode)) {
			sqlQuery += " and a.account_code='" + accountCode + "'";
		}

		String startDealDate = crm.getString("startDealDate", "");
		if (!StringUtils.isEmpty(startDealDate)) {
			sqlQuery += " and a.deal_date>=to_date('" + startDealDate + "','yyyy-mm-dd hh24:mi:ss')";
		}

		String endDealDate = crm.getString("endDealDate", "");
		if (!StringUtils.isEmpty(endDealDate)) {
			sqlQuery += " and a.deal_date<=to_date('" + endDealDate + "','yyyy-mm-dd hh24:mi:ss')";
		}

		String status = crm.getString("status", "");
		if (!StringUtils.isEmpty(status)) {
			sqlQuery += " and a.status in (" + status + ")";
		}

		String createNo = crm.getString("createNo", "");
		if (!StringUtils.isEmpty(createNo)) {
			sqlQuery += " and a.create_no='" + createNo + "'";
		}

		String checkerName = crm.getString("checkerName", "");
		if (!StringUtils.isEmpty(checkerName)) {
			sqlQuery += " and a.checker_name='" + checkerName + "'";
		}

		return sqlQuery;
	}

	/**
	 * 要看外部条件获取对应的到款数总额
	 * @param crm
	 * @return
	 */
	public HashMap getJfiPayDataSum(CommonRecord crm) {
		String sqlQuery = "select sum(a.income_money) as total_income_money, sum(b.pay_money) as total_pay_money from jfi_pay_data a "
	        + " LEFT JOIN jfi_pay_advice b ON (a.company_code = b.company_code and a.advice_code = b.advice_code) " + " where 1=1 "
	        + this.buildFiPayDataStatSql(crm);
		return this.findOneObjectBySQL(sqlQuery);
	}

	/**
	 * 获取各银行(未)到款对应的统计
	 * @param companyCode
	 * @param startDealDate
	 * @param endDealDate
	 * @return
	 */
	public List getJfiPayDataGroupTotal(final String companyCode, final String startDealDate, final String endDealDate) {
		String sqlQuery = "select a.*, nvl(c.income_money_1,0) income_money_1, nvl(c.count_1,0) count_1, nvl(e.income_money_2,0) income_money_2, nvl(e.count_2,0) count_2 from jfi_pay_bank a " + " left join ("
		        + " select b.account_code,count(b.data_id) as count_1, sum(b.income_money) as income_money_1 " + " from jfi_pay_data b "
		        + " where b.status=1 and b.deal_date>=to_date('" + startDealDate + " 00:00:00','yyyy-mm-dd hh24:mi:ss') and b.deal_date<=to_date('" + endDealDate + " 23:59:59','yyyy-mm-dd hh24:mi:ss') "
		        + " group by b.account_code) c on c.account_code=a.account_code " + " left join ("
		        + " select d.account_code,count(d.data_id) as count_2, sum(d.income_money) as income_money_2 " + " from jfi_pay_data d "
		        + " where d.status=2 and d.deal_date>=to_date('" + startDealDate + " 00:00:00','yyyy-mm-dd hh24:mi:ss') and d.deal_date<=to_date('" + endDealDate + " 23:59:59','yyyy-mm-dd hh24:mi:ss') "
		        + " group by d.account_code) e on e.account_code=a.account_code " + " where a.company_code='" + companyCode
		        + "' order by a.account_code";
		return this.findObjectsBySQL(sqlQuery);
	}
}
