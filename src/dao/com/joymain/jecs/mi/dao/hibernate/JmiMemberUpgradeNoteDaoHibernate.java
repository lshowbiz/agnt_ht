
package com.joymain.jecs.mi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.model.JmiMemberUpgradeNote;
import com.joymain.jecs.mi.dao.JmiMemberUpgradeNoteDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JmiMemberUpgradeNoteDaoHibernate extends BaseDaoHibernate implements JmiMemberUpgradeNoteDao {

    /**
     * @see com.joymain.jecs.mi.dao.JmiMemberUpgradeNoteDao#getJmiMemberUpgradeNotes(com.joymain.jecs.mi.model.JmiMemberUpgradeNote)
     */
    public List getJmiMemberUpgradeNotes(final JmiMemberUpgradeNote jmiMemberUpgradeNote) {
        return getHibernateTemplate().find("from JmiMemberUpgradeNote");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jmiMemberUpgradeNote == null) {
            return getHibernateTemplate().find("from JmiMemberUpgradeNote");
        } else {
            // filter on properties set in the jmiMemberUpgradeNote
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jmiMemberUpgradeNote).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JmiMemberUpgradeNote.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiMemberUpgradeNoteDao#getJmiMemberUpgradeNote(Long munId)
     */
    public JmiMemberUpgradeNote getJmiMemberUpgradeNote(final Long munId) {
        JmiMemberUpgradeNote jmiMemberUpgradeNote = (JmiMemberUpgradeNote) getHibernateTemplate().get(JmiMemberUpgradeNote.class, munId);
        if (jmiMemberUpgradeNote == null) {
            log.warn("uh oh, jmiMemberUpgradeNote with munId '" + munId + "' not found...");
            throw new ObjectRetrievalFailureException(JmiMemberUpgradeNote.class, munId);
        }

        return jmiMemberUpgradeNote;
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiMemberUpgradeNoteDao#saveJmiMemberUpgradeNote(JmiMemberUpgradeNote jmiMemberUpgradeNote)
     */    
    public void saveJmiMemberUpgradeNote(final JmiMemberUpgradeNote jmiMemberUpgradeNote) {
        getHibernateTemplate().saveOrUpdate(jmiMemberUpgradeNote);
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiMemberUpgradeNoteDao#removeJmiMemberUpgradeNote(Long munId)
     */
    public void removeJmiMemberUpgradeNote(final Long munId) {
        getHibernateTemplate().delete(getJmiMemberUpgradeNote(munId));
    }
    //added for getJmiMemberUpgradeNotesByCrm
    public List getJmiMemberUpgradeNotesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JmiMemberUpgradeNote jmiMemberUpgradeNote where 1=1";

		String userCode = crm.getString("userCode", "");
		if(!StringUtil.isEmpty(userCode)){
			hql += " and jmiMember.userCode='"+userCode+"'";
		}
		String companyCode = crm.getString("companyCode", "");
		if(!StringUtil.isEmpty(companyCode)){
			hql += " and companyCode='"+companyCode+"'";
		}
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by munId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    /**
     * 根据订单号返回升级记录
     * @param orderId
     * @return
     */
    public List getJmiMemberUpgradeNoteByOrderId(String orderId){
    	String hql = "from JmiMemberUpgradeNote where memberOrderNo='" + orderId + "'";
    	return this.findObjectsByHqlQuery(hql);
    }
}
