
package com.joymain.jecs.po.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.po.model.JpoMemberOrderTask;
import com.joymain.jecs.po.dao.JpoMemberOrderTaskDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JpoMemberOrderTaskDaoHibernate extends BaseDaoHibernate implements JpoMemberOrderTaskDao {

    /**
     * @see com.joymain.jecs.po.dao.JpoMemberOrderTaskDao#getJpoMemberOrderTasks(com.joymain.jecs.po.model.JpoMemberOrderTask)
     */
    public List getJpoMemberOrderTasks(final JpoMemberOrderTask jpoMemberOrderTask) {
        return getHibernateTemplate().findByExample(jpoMemberOrderTask);

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpoMemberOrderTask == null) {
            return getHibernateTemplate().find("from JpoMemberOrderTask");
        } else {
            // filter on properties set in the jpoMemberOrderTask
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpoMemberOrderTask).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpoMemberOrderTask.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoMemberOrderTaskDao#getJpoMemberOrderTask(Long motId)
     */
    public JpoMemberOrderTask getJpoMemberOrderTask(final Long motId) {
        JpoMemberOrderTask jpoMemberOrderTask = (JpoMemberOrderTask) getHibernateTemplate().get(JpoMemberOrderTask.class, motId);
        if (jpoMemberOrderTask == null) {
            log.warn("uh oh, jpoMemberOrderTask with motId '" + motId + "' not found...");
            throw new ObjectRetrievalFailureException(JpoMemberOrderTask.class, motId);
        }

        return jpoMemberOrderTask;
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoMemberOrderTaskDao#saveJpoMemberOrderTask(JpoMemberOrderTask jpoMemberOrderTask)
     */    
    public void saveJpoMemberOrderTask(final JpoMemberOrderTask jpoMemberOrderTask) {
        getHibernateTemplate().saveOrUpdate(jpoMemberOrderTask);
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoMemberOrderTaskDao#removeJpoMemberOrderTask(Long motId)
     */
    public void removeJpoMemberOrderTask(final Long motId) {
        getHibernateTemplate().delete(getJpoMemberOrderTask(motId));
    }
    //added for getJpoMemberOrderTasksByCrm
    public List getJpoMemberOrderTasksByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpoMemberOrderTask jpoMemberOrderTask where 1=1";

		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and userCode = '" + userCode + "'";
		}
		String taskType = crm.getString("taskType", "");
		if (!StringUtils.isEmpty(taskType)) {
			hql += " and taskType = '" + taskType + "'";
		}

		String companyCode = crm.getString("companyCode", "");
		if (!"AA".equals(companyCode)&&!StringUtil.isEmpty(companyCode)) {
			hql += " and companyCode = '" + companyCode + "'";
		}
		
		
		
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by motId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public JpoMemberOrderTask getMainJpoMemberOrderTask(String userCode) {
		return (JpoMemberOrderTask) this.getObjectByHqlQuery("from JpoMemberOrderTask where userCode='"+userCode+"' and taskType='0' ");
	}
}
