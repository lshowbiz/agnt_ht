
package com.joymain.jecs.pm.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.model.JpmWineOrderInterface;
import com.joymain.jecs.pm.dao.JpmWineOrderInterfaceDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JpmWineOrderInterfaceDaoHibernate extends BaseDaoHibernate implements JpmWineOrderInterfaceDao {

    /**
     * @see com.joymain.jecs.pm.dao.JpmWineOrderInterfaceDao#getJpmWineOrderInterfaces(com.joymain.jecs.pm.model.JpmWineOrderInterface)
     */
    public List getJpmWineOrderInterfaces(final JpmWineOrderInterface jpmWineOrderInterface) {
        return getHibernateTemplate().find("from JpmWineOrderInterface");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpmWineOrderInterface == null) {
            return getHibernateTemplate().find("from JpmWineOrderInterface");
        } else {
            // filter on properties set in the jpmWineOrderInterface
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpmWineOrderInterface).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpmWineOrderInterface.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmWineOrderInterfaceDao#getJpmWineOrderInterface(Long moId)
     */
    public JpmWineOrderInterface getJpmWineOrderInterface(final Long moId) {
        JpmWineOrderInterface jpmWineOrderInterface = (JpmWineOrderInterface) getHibernateTemplate().get(JpmWineOrderInterface.class, moId);
        if (jpmWineOrderInterface == null) {
            log.warn("uh oh, jpmWineOrderInterface with moId '" + moId + "' not found...");
            throw new ObjectRetrievalFailureException(JpmWineOrderInterface.class, moId);
        }

        return jpmWineOrderInterface;
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmWineOrderInterfaceDao#saveJpmWineOrderInterface(JpmWineOrderInterface jpmWineOrderInterface)
     */    
    public void saveJpmWineOrderInterface(final JpmWineOrderInterface jpmWineOrderInterface) {
        getHibernateTemplate().saveOrUpdate(jpmWineOrderInterface);
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmWineOrderInterfaceDao#removeJpmWineOrderInterface(Long moId)
     */
    public void removeJpmWineOrderInterface(final Long moId) {
        getHibernateTemplate().delete(getJpmWineOrderInterface(moId));
    }
    //added for getJpmWineOrderInterfacesByCrm
    public List getJpmWineOrderInterfacesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpmWineOrderInterface jpmWineOrderInterface where 1=1";
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
			hql += " order by moId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
