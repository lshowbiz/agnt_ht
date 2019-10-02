
package com.joymain.jecs.fi.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.FiFundbookJournal;
import com.joymain.jecs.fi.model.JfiDepositJournal;
import com.joymain.jecs.fi.dao.JfiDepositJournalDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiDepositJournalDaoHibernate extends BaseDaoHibernate implements JfiDepositJournalDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiDepositJournalDao#getJfiDepositJournals(com.joymain.jecs.fi.model.JfiDepositJournal)
     */
    public List getJfiDepositJournals(final JfiDepositJournal jfiDepositJournal) {
        return getHibernateTemplate().find("from JfiDepositJournal");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiDepositJournal == null) {
            return getHibernateTemplate().find("from JfiDepositJournal");
        } else {
            // filter on properties set in the jfiDepositJournal
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiDepositJournal).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiDepositJournal.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiDepositJournalDao#getJfiDepositJournal(BigDecimal journalId)
     */
    public JfiDepositJournal getJfiDepositJournal(final Long journalId) {
        JfiDepositJournal jfiDepositJournal = (JfiDepositJournal) getHibernateTemplate().get(JfiDepositJournal.class, journalId);
        if (jfiDepositJournal == null) {
            log.warn("uh oh, jfiDepositJournal with journalId '" + journalId + "' not found...");
            throw new ObjectRetrievalFailureException(JfiDepositJournal.class, journalId);
        }

        return jfiDepositJournal;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiDepositJournalDao#saveJfiDepositJournal(JfiDepositJournal jfiDepositJournal)
     */    
    public void saveJfiDepositJournal(final JfiDepositJournal jfiDepositJournal) {
        getHibernateTemplate().saveOrUpdate(jfiDepositJournal);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiDepositJournalDao#removeJfiDepositJournal(BigDecimal journalId)
     */
    public void removeJfiDepositJournal(final Long journalId) {
        getHibernateTemplate().delete(getJfiDepositJournal(journalId));
    }
    //added for getJfiDepositJournalsByCrm
    public List getJfiDepositJournalsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiDepositJournal jfiDepositJournal where 1=1";
   
    	
    	hql+=buildListSqlQuery(crm);
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by journalId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
	public Map getSumAmountByCrm(CommonRecord crm) {
		String hql = "select nvl(sum(case when dealType='A' then money end),0) as addMoney,nvl(sum(case when dealType='D' then money end),0) as decMoney,nvl(sum(balance),0) as balance from JfiDepositJournal  where 1=1 ";

		hql += this.buildListSqlQuery(crm);
		
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
	private String buildListSqlQuery(CommonRecord crm) {
		String hql = "";
		String companyCode = crm.getString("companyCode", "");
		if (!"AA".equals(companyCode) && !StringUtils.isEmpty(companyCode)) {
			hql += " and companyCode='" + companyCode + "'";
		}

		String dealType = crm.getString("dealType", "");
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

		String depositType = crm.getString("depositType", "");
		if (!StringUtils.isEmpty(depositType)) {
			hql += " and depositType='" + depositType + "'";
		}

		String createrNo = crm.getString("createrNo", "");
		if (!StringUtils.isEmpty(createrNo)) {
			hql += " and createrNo='" + createrNo + "'";
		}
	


		String createBTime = crm.getString("createBTime", "");
		if (!StringUtils.isEmpty(createBTime)) {
			hql += " and createTime>=to_date('" + createBTime + "','yyyy-mm-dd hh24:mi:ss')";
		}

		String createETime = crm.getString("createETime", "");
		if (!StringUtils.isEmpty(createETime)) {
			hql += " and createTime<=to_date('" + createETime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		return hql;
	}
    
    
    
    
    
	/**
	 * 获取验证ID对应的最后一条存折记录
	 * @param uniqueCode 唯一码，用于防止重复
	 * @param dealType 交易类别，A：存入；D：取出
	 * @return
	 */
	public JfiDepositJournal getLastJfiDepositJournalByUnique(final String uniqueCode,final String dealType) {
	
			
		return (JfiDepositJournal) this.getObjectByHqlQuery("from JfiDepositJournal where uniqueCode='" + uniqueCode+ "' and dealType='" + dealType + "' order by journalId desc");
	
	}
	
	/**
	 * 获取用户对应的最后一条产业基金流水记录
	 * @param userCode
	 * @return
	 */
	public JfiDepositJournal getLastJfiDepositJournal(final String userCode,final String dealType) {
		return (JfiDepositJournal) this.getObjectByHqlQuery("from JfiDepositJournal where userCode='" + userCode
		        + "' and depositType = '" + dealType + "' order by journalId desc");
	}
}
