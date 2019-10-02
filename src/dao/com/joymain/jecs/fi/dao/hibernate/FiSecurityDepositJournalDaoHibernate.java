
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.FiSecurityDepositJournal;
import com.joymain.jecs.fi.dao.FiSecurityDepositJournalDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class FiSecurityDepositJournalDaoHibernate extends BaseDaoHibernate implements FiSecurityDepositJournalDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiSecurityDepositJournalDao#getFiSecurityDepositJournals(com.joymain.jecs.fi.model.FiSecurityDepositJournal)
     */
    public List getFiSecurityDepositJournals(final FiSecurityDepositJournal fiSecurityDepositJournal) {
        return getHibernateTemplate().find("from FiSecurityDepositJournal");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiSecurityDepositJournal == null) {
            return getHibernateTemplate().find("from FiSecurityDepositJournal");
        } else {
            // filter on properties set in the fiSecurityDepositJournal
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiSecurityDepositJournal).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiSecurityDepositJournal.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiSecurityDepositJournalDao#getFiSecurityDepositJournal(Long journalId)
     */
    public FiSecurityDepositJournal getFiSecurityDepositJournal(final Long journalId) {
        FiSecurityDepositJournal fiSecurityDepositJournal = (FiSecurityDepositJournal) getHibernateTemplate().get(FiSecurityDepositJournal.class, journalId);
        if (fiSecurityDepositJournal == null) {
            log.warn("uh oh, fiSecurityDepositJournal with journalId '" + journalId + "' not found...");
            throw new ObjectRetrievalFailureException(FiSecurityDepositJournal.class, journalId);
        }

        return fiSecurityDepositJournal;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiSecurityDepositJournalDao#saveFiSecurityDepositJournal(FiSecurityDepositJournal fiSecurityDepositJournal)
     */    
    public void saveFiSecurityDepositJournal(final FiSecurityDepositJournal fiSecurityDepositJournal) {
        getHibernateTemplate().saveOrUpdate(fiSecurityDepositJournal);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiSecurityDepositJournalDao#removeFiSecurityDepositJournal(Long journalId)
     */
    public void removeFiSecurityDepositJournal(final Long journalId) {
        getHibernateTemplate().delete(getFiSecurityDepositJournal(journalId));
    }
    //added for getFiSecurityDepositJournalsByCrm
    public List getFiSecurityDepositJournalsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FiSecurityDepositJournal fiSecurityDepositJournal where 1=1";
 
		String userCode = crm.getString("userCode", "");
		if(StringUtils.isNotEmpty(userCode)){
			hql += " and fiSecurityDepositJournal.userCode='"+userCode+"'";
		}
	
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by journalId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
