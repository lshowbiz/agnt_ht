
package com.joymain.jecs.al.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.al.model.AlBranch;
import com.joymain.jecs.al.dao.AlBranchDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class AlBranchDaoHibernate extends BaseDaoHibernate implements AlBranchDao {

    /**
     * @see com.joymain.jecs.al.dao.AlBranchDao#getAlBranchs(com.joymain.jecs.al.model.AlBranch)
     */
    public List getAlBranchs(final AlBranch alBranch) {
        return getHibernateTemplate().find("from AlBranch");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (alBranch == null) {
            return getHibernateTemplate().find("from AlBranch");
        } else {
            // filter on properties set in the alBranch
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(alBranch).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(AlBranch.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.al.dao.AlBranchDao#getAlBranch(Long branchId)
     */
    public AlBranch getAlBranch(final Long branchId) {
        AlBranch alBranch = (AlBranch) getHibernateTemplate().get(AlBranch.class, branchId);
        if (alBranch == null) {
            log.warn("uh oh, alBranch with branchId '" + branchId + "' not found...");
            throw new ObjectRetrievalFailureException(AlBranch.class, branchId);
        }

        return alBranch;
    }

    /**
     * @see com.joymain.jecs.al.dao.AlBranchDao#saveAlBranch(AlBranch alBranch)
     */    
    public void saveAlBranch(final AlBranch alBranch) {
        getHibernateTemplate().saveOrUpdate(alBranch);
    }

    /**
     * @see com.joymain.jecs.al.dao.AlBranchDao#removeAlBranch(Long branchId)
     */
    public void removeAlBranch(final Long branchId) {
        getHibernateTemplate().delete(getAlBranch(branchId));
    }
}
