
package com.joymain.jecs.po.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.po.model.JpoMemberOrderListTask;
import com.joymain.jecs.po.dao.JpoMemberOrderListTaskDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JpoMemberOrderListTaskDaoHibernate extends BaseDaoHibernate implements JpoMemberOrderListTaskDao {

    /**
     * @see com.joymain.jecs.po.dao.JpoMemberOrderListTaskDao#getJpoMemberOrderListTasks(com.joymain.jecs.po.model.JpoMemberOrderListTask)
     */
    public List getJpoMemberOrderListTasks(final JpoMemberOrderListTask jpoMemberOrderListTask) {
        return getHibernateTemplate().find("from JpoMemberOrderListTask");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpoMemberOrderListTask == null) {
            return getHibernateTemplate().find("from JpoMemberOrderListTask");
        } else {
            // filter on properties set in the jpoMemberOrderListTask
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpoMemberOrderListTask).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpoMemberOrderListTask.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoMemberOrderListTaskDao#getJpoMemberOrderListTask(Long moltId)
     */
    public JpoMemberOrderListTask getJpoMemberOrderListTask(final Long moltId) {
        JpoMemberOrderListTask jpoMemberOrderListTask = (JpoMemberOrderListTask) getHibernateTemplate().get(JpoMemberOrderListTask.class, moltId);
        if (jpoMemberOrderListTask == null) {
            log.warn("uh oh, jpoMemberOrderListTask with moltId '" + moltId + "' not found...");
            throw new ObjectRetrievalFailureException(JpoMemberOrderListTask.class, moltId);
        }

        return jpoMemberOrderListTask;
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoMemberOrderListTaskDao#saveJpoMemberOrderListTask(JpoMemberOrderListTask jpoMemberOrderListTask)
     */    
    public void saveJpoMemberOrderListTask(final JpoMemberOrderListTask jpoMemberOrderListTask) {
        getHibernateTemplate().saveOrUpdate(jpoMemberOrderListTask);
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoMemberOrderListTaskDao#removeJpoMemberOrderListTask(Long moltId)
     */
    public void removeJpoMemberOrderListTask(final Long moltId) {
        getHibernateTemplate().delete(getJpoMemberOrderListTask(moltId));
    }
    //added for getJpoMemberOrderListTasksByCrm
    public List getJpoMemberOrderListTasksByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpoMemberOrderListTask jpoMemberOrderListTask where 1=1";
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
			hql += " order by moltId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
