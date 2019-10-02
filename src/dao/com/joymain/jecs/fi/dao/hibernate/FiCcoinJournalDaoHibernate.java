
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.dao.FiCcoinJournalDao;
import com.joymain.jecs.fi.model.FiCcoinJournal;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class FiCcoinJournalDaoHibernate extends BaseDaoHibernate implements FiCcoinJournalDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiCcoinJournalDao#getFiCcoinJournals(com.joymain.jecs.fi.model.FiCcoinJournal)
     */
    public List getFiCcoinJournals(final FiCcoinJournal fiCcoinJournal) {
        return getHibernateTemplate().find("from FiCcoinJournal");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiCcoinJournal == null) {
            return getHibernateTemplate().find("from FiCcoinJournal");
        } else {
            // filter on properties set in the fiCcoinJournal
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiCcoinJournal).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiCcoinJournal.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiCcoinJournalDao#getFiCcoinJournal(Long journalId)
     */
    public FiCcoinJournal getFiCcoinJournal(final Long journalId) {
        FiCcoinJournal fiCcoinJournal = (FiCcoinJournal) getHibernateTemplate().get(FiCcoinJournal.class, journalId);
        if (fiCcoinJournal == null) {
            log.warn("uh oh, fiCcoinJournal with journalId '" + journalId + "' not found...");
            throw new ObjectRetrievalFailureException(FiCcoinJournal.class, journalId);
        }

        return fiCcoinJournal;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiCcoinJournalDao#saveFiCcoinJournal(FiCcoinJournal fiCcoinJournal)
     */    
    public void saveFiCcoinJournal(final FiCcoinJournal fiCcoinJournal) {
        getHibernateTemplate().saveOrUpdate(fiCcoinJournal);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiCcoinJournalDao#removeFiCcoinJournal(Long journalId)
     */
    public void removeFiCcoinJournal(final Long journalId) {
        getHibernateTemplate().delete(getFiCcoinJournal(journalId));
    }
    //added for getFiCcoinJournalsByCrm
    public List getFiCcoinJournalsByCrm(CommonRecord crm, Pager pager){
		String hql = "from FiCcoinJournal fiCcoinJournal where 1=1";
    	String userCode = crm.getString("userCode", "");
    	if (!userCode.equals("")) {
    		hql += " and fiCcoinJournal.sysUser.userCode like '%" + userCode + "%'";
    	}
    	/*String dealType = crm.getString("dealType", "");
    	if (!dealType.equals("") && !dealType.equals("ALL")) {
    		hql += " and fiCcoinJournal.dealType = '" + dealType + "'";
    	}
    	String moneyType = crm.getString("moneyType", "");
    	if (!moneyType.equals("") && !moneyType.equals("ALL")) {
    		hql += " and fiCcoinJournal.moneyType = " + moneyType;
    	}
    	String createTimeStart = crm.getString("createTimeStart", "");
    	if (!createTimeStart.equals("")) {
    		hql += " and fiCcoinJournal.createTime >= to_date('" + createTimeStart + "', 'yyyy-MM-dd HH24:mi:ss')";
    	}
    	String createTimeEnd = crm.getString("createTimeEnd", "");
    	if (!createTimeEnd.equals("")) {
    		hql += " and fiCcoinJournal.createTime <= to_date('" + createTimeEnd + "', 'yyyy-MM-dd HH24:mi:ss')";
    	}*/
    	
    	String dealDateStart = crm.getString("dealDateStart", "");
    	if (!dealDateStart.equals("")) {
    		hql += " and fiCcoinJournal.dealDate >= to_date('" + dealDateStart + "', 'yyyy-MM-dd')";
    	}
    	String dealDateEnd = crm.getString("dealDateEnd", "");
    	if (!dealDateEnd.equals("")) {
    		hql += " and fiCcoinJournal.dealDate <= to_date('" + dealDateEnd + "', 'yyyy-MM-dd')";
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
			hql += " order by fiCcoinJournal.dealDate";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    /**
	 * 获取验证ID对应的最后一条存折记录
	 * @param uniqueCode
	 * @return
	 */
	public FiCcoinJournal getLastFiCcoinJournalByUnique(final String uniqueCode,final String dealType) {
		try{
			return (FiCcoinJournal) this.getObjectByHqlQuery("from FiCcoinJournal where uniqueCode='" + uniqueCode
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
	public FiCcoinJournal getLastFiCcoinJournal(final String userCode) {
		return (FiCcoinJournal) this.getObjectByHqlQuery("from FiCcoinJournal where sysUser.userCode='" + userCode
		        + "' order by journalId desc");
	}

	/**
	 * 获取某用户的存折流水条数
	 * @param companyCode
	 * @param agentNo
	 * @return
	 */
	public long getCountByDate(final String userCode) {
		return this.getTotalByHql("select count(*) as totalCount from FiCcoinJournal where sysUser.userCode='" + userCode + "'");
	}

}
