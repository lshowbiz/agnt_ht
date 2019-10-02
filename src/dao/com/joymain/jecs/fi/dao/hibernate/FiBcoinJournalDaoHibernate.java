
package com.joymain.jecs.fi.dao.hibernate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.dao.FiBcoinJournalDao;
import com.joymain.jecs.fi.model.FiBcoinJournal;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class FiBcoinJournalDaoHibernate extends BaseDaoHibernate implements FiBcoinJournalDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiBcoinJournalDao#getFiBcoinJournals(com.joymain.jecs.fi.model.FiBcoinJournal)
     */
    public List getFiBcoinJournals(final FiBcoinJournal fiBcoinJournal) {
        return getHibernateTemplate().find("from FiBcoinJournal");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiBcoinJournal == null) {
            return getHibernateTemplate().find("from FiBcoinJournal");
        } else {
            // filter on properties set in the fiBcoinJournal
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiBcoinJournal).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiBcoinJournal.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiBcoinJournalDao#getFiBcoinJournal(Long journalId)
     */
    public FiBcoinJournal getFiBcoinJournal(final Long journalId) {
        FiBcoinJournal fiBcoinJournal = (FiBcoinJournal) getHibernateTemplate().get(FiBcoinJournal.class, journalId);
        if (fiBcoinJournal == null) {
            log.warn("uh oh, fiBcoinJournal with journalId '" + journalId + "' not found...");
            throw new ObjectRetrievalFailureException(FiBcoinJournal.class, journalId);
        }

        return fiBcoinJournal;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiBcoinJournalDao#saveFiBcoinJournal(FiBcoinJournal fiBcoinJournal)
     */    
    public void saveFiBcoinJournal(final FiBcoinJournal fiBcoinJournal) {
        getHibernateTemplate().saveOrUpdate(fiBcoinJournal);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiBcoinJournalDao#removeFiBcoinJournal(Long journalId)
     */
    public void removeFiBcoinJournal(final Long journalId) {
        getHibernateTemplate().delete(getFiBcoinJournal(journalId));
    }
    //added for getFiBcoinJournalsByCrm
    public List getFiBcoinJournalsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FiBcoinJournal fiBcoinJournal where 1=1";
    	String userCode = crm.getString("userCode", "");
    	if (!userCode.equals("")) {
    		hql += " and fiBcoinJournal.sysUser.userCode = '" + userCode + "'";
    	}
    	/*String dealType = crm.getString("dealType", "");
    	if (!dealType.equals("") && !dealType.equals("ALL")) {
    		hql += " and fiBcoinJournal.dealType = '" + dealType + "'";
    	}
    	String moneyType = crm.getString("moneyType", "");
    	if (!moneyType.equals("") && !moneyType.equals("ALL")) {
    		hql += " and fiBcoinJournal.moneyType = " + moneyType;
    	}
    	String createTimeStart = crm.getString("createTimeStart", "");
    	if (!createTimeStart.equals("")) {
    		hql += " and fiBcoinJournal.createTime >= to_date('" + createTimeStart + "', 'yyyy-MM-dd HH24:mi:ss')";
    	}
    	String createTimeEnd = crm.getString("createTimeEnd", "");
    	if (!createTimeEnd.equals("")) {
    		hql += " and fiBcoinJournal.createTime <= to_date('" + createTimeEnd + "', 'yyyy-MM-dd HH24:mi:ss')";
    	}*/
    	String dealDateStart = crm.getString("dealDateStart", "");
    	if (!dealDateStart.equals("")) {
    		hql += " and fiBcoinJournal.dealDate >= to_date('" + dealDateStart + "', 'yyyy-MM-dd HH24:mi:ss')";
    	}
    	String dealDateEnd = crm.getString("dealDateEnd", "");
    	if (!dealDateEnd.equals("")) {
    		hql += " and fiBcoinJournal.dealDate <= to_date('" + dealDateEnd + "', 'yyyy-MM-dd HH24:mi:ss')";
    	}
    	/** 
	---example---
	String userCode = crm.getString("userCode", "");
	if(StringUtils.isNotEmpty(userCode)){
		hql += "???????????";
	}
	 ***/
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by journalId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    /**
     * Add By WuCF 20140320 查询欢乐积分统计数据
	 * 订单总金额
	 * @param crm
	 * @return
	 */
    public Map getSumAmountByCrm(CommonRecord crm){
    	String hql = "select nvl(sum(case when fiBcoinJournal.dealType='A' then fiBcoinJournal.coin end),0) as addMoney,nvl(sum(case when fiBcoinJournal.dealType='D' then fiBcoinJournal.coin end),0) as decMoney,nvl(sum(fiBcoinJournal.balance),0) as balance from FiBcoinJournal fiBcoinJournal where 1=1";
    	String userCode = crm.getString("userCode", "");
    	if (!userCode.equals("")) {
    		hql += " and fiBcoinJournal.sysUser.userCode = '" + userCode + "'";
    	}
    	String dealDateStart = crm.getString("dealDateStart", "");
    	if (!dealDateStart.equals("")) {
    		hql += " and fiBcoinJournal.dealDate >= to_date('" + dealDateStart + "', 'yyyy-MM-dd HH24:mi:ss')";
    	}
    	String dealDateEnd = crm.getString("dealDateEnd", "");
    	if (!dealDateEnd.equals("")) {
    		hql += " and fiBcoinJournal.dealDate <= to_date('" + dealDateEnd + "', 'yyyy-MM-dd HH24:mi:ss')";
    	}
    	
    	Object[] sum = (Object[])this.getObjectByHqlQuery(hql);
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		map.put("addMoney", (BigDecimal) sum[0]);
		map.put("decMoney", (BigDecimal) sum[1]);
		map.put("balance", (BigDecimal) sum[2]);
		
		return map;
    }
    
    
    /**
	 * 获取验证ID对应的最后一条存折记录
	 * @param uniqueCode
	 * @return
	 */
	public FiBcoinJournal getLastFiBcoinJournalByUnique(final String uniqueCode,final String dealType) {
		try{
			return (FiBcoinJournal) this.getObjectByHqlQuery("from FiBcoinJournal where uniqueCode='" + uniqueCode
		        + "' and dealType='" + dealType +  "' order by journalId desc");
		}catch(ClassCastException ex){
			return null;
		}
	}
	/**
	 * 获取用户对应的最后一条存折记录
	 * @param userCode
	 * @return
	 */
	public FiBcoinJournal getLastFiBcoinJournal(final String userCode) {
		return (FiBcoinJournal) this.getObjectByHqlQuery("from FiBcoinJournal where sysUser.userCode='" + userCode
		        + "' order by journalId desc");
	}
	/**
	 * 获取某用户的存折流水条数
	 * @param companyCode
	 * @param agentNo
	 * @return
	 */
	public long getCountByDate(final String userCode) {
		return this.getTotalByHql("select count(*) as totalCount from FiBcoinJournal where sysUser.userCode='" + userCode + "'");
	}
}
