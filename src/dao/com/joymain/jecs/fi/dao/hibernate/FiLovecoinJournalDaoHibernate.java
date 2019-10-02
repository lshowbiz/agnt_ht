
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.dao.FiLovecoinJournalDao;
import com.joymain.jecs.fi.model.FiLovecoinJournal;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class FiLovecoinJournalDaoHibernate extends BaseDaoHibernate implements FiLovecoinJournalDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiLovecoinJournalDao#getFiLovecoinJournals(com.joymain.jecs.fi.model.FiLovecoinJournal)
     */
    public List getFiLovecoinJournals(final FiLovecoinJournal fiLovecoinJournal) {
        return getHibernateTemplate().find("from FiLovecoinJournal");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiLovecoinJournal == null) {
            return getHibernateTemplate().find("from FiLovecoinJournal");
        } else {
            // filter on properties set in the fiLovecoinJournal
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiLovecoinJournal).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiLovecoinJournal.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiLovecoinJournalDao#getFiLovecoinJournal(Long journalId)
     */
    public FiLovecoinJournal getFiLovecoinJournal(final Long journalId) {
        FiLovecoinJournal fiLovecoinJournal = (FiLovecoinJournal) getHibernateTemplate().get(FiLovecoinJournal.class, journalId);
        if (fiLovecoinJournal == null) {
            log.warn("uh oh, fiLovecoinJournal with journalId '" + journalId + "' not found...");
            throw new ObjectRetrievalFailureException(FiLovecoinJournal.class, journalId);
        }

        return fiLovecoinJournal;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiLovecoinJournalDao#saveFiLovecoinJournal(FiLovecoinJournal fiLovecoinJournal)
     */    
    public void saveFiLovecoinJournal(final FiLovecoinJournal fiLovecoinJournal) {
        getHibernateTemplate().saveOrUpdate(fiLovecoinJournal);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiLovecoinJournalDao#removeFiLovecoinJournal(Long journalId)
     */
    public void removeFiLovecoinJournal(final Long journalId) {
        getHibernateTemplate().delete(getFiLovecoinJournal(journalId));
    }
    //added for getFiLovecoinJournalsByCrm
    public List getFiLovecoinJournalsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FiLovecoinJournal fiLovecoinJournal where 1=1";
    	String userCode = crm.getString("userCode", "");
    	if (!userCode.equals("")) {
    		hql += " and fiLovecoinJournal.sysUser.userCode = '" + userCode + "'";
    	}
    	/*String dealType = crm.getString("dealType", "");
    	if (!dealType.equals("") && !dealType.equals("ALL")) {
    		hql += " and fiLovecoinJournal.dealType = '" + dealType + "'";
    	}
    	String moneyType = crm.getString("moneyType", "");
    	if (!moneyType.equals("") && !moneyType.equals("ALL")) {
    		hql += " and fiLovecoinJournal.moneyType = " + moneyType;
    	}
    	String createTimeStart = crm.getString("createTimeStart", "");
    	if (!createTimeStart.equals("")) {
    		hql += " and fiLovecoinJournal.createTime >= to_date('" + createTimeStart + "', 'yyyy-MM-dd HH24:mi:ss')";
    	}
    	String createTimeEnd = crm.getString("createTimeEnd", "");
    	if (!createTimeEnd.equals("")) {
    		hql += " and fiLovecoinJournal.createTime <= to_date('" + createTimeEnd + "', 'yyyy-MM-dd HH24:mi:ss')";
    	}*/
    	String dealDateStart = crm.getString("dealDateStart", "");
    	if (!dealDateStart.equals("")) {
    		hql += " and fiLovecoinJournal.dealDate >= to_date('" + dealDateStart + "', 'yyyy-MM-dd HH24:mi:ss')";
    	}
    	String dealDateEnd = crm.getString("dealDateEnd", "");
    	if (!dealDateEnd.equals("")) {
    		hql += " and fiLovecoinJournal.dealDate <= to_date('" + dealDateEnd + "', 'yyyy-MM-dd HH24:mi:ss')";
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
	 * 获取验证ID对应的最后一条存折记录
	 * @param uniqueCode
	 * @return
	 */
	public FiLovecoinJournal getLastFiLovecoinJournalByUnique(final String uniqueCode,final String dealType) {
		try{
			return (FiLovecoinJournal) this.getObjectByHqlQuery("from FiLovecoinJournal where uniqueCode='" + uniqueCode
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
	public FiLovecoinJournal getLastFiLovecoinJournal(final String userCode) {
		return (FiLovecoinJournal) this.getObjectByHqlQuery("from FiLovecoinJournal where sysUser.userCode='" + userCode
		        + "' order by journalId desc");
	}
	/**
	 * 获取某用户的存折流水条数
	 * @param companyCode
	 * @param agentNo
	 * @return
	 */
	public long getCountByDate(final String userCode) {
		return this.getTotalByHql("select count(*) as totalCount from FiLovecoinJournal where sysUser.userCode='" + userCode + "'");
	}
}
