package com.joymain.jecs.pm.dao.hibernate;

import java.util.Collection;
import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.dao.JpmWineOrderListInterfaceDao;
import com.joymain.jecs.pm.model.JpmWineOrderListInterface;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JpmWineOrderListInterfaceDaoHibernate extends BaseDaoHibernate implements JpmWineOrderListInterfaceDao {

    /**
     * @see com.joymain.jecs.pm.dao.JpmWineOrderListInterfaceDao#getJpmWineOrderListInterfaces(com.joymain.jecs.pm.model.JpmWineOrderListInterface)
     */
    public List getJpmWineOrderListInterfaces(final JpmWineOrderListInterface jpmWineOrderListInterface) {
        return getHibernateTemplate().find("from JpmWineOrderListInterface");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpmWineOrderListInterface == null) {
            return getHibernateTemplate().find("from JpmWineOrderListInterface");
        } else {
            // filter on properties set in the jpmWineOrderListInterface
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpmWineOrderListInterface).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpmWineOrderListInterface.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmWineOrderListInterfaceDao#getJpmWineOrderListInterface(Long
     *      idf)
     */
    public JpmWineOrderListInterface getJpmWineOrderListInterface(final Long idf) {
        JpmWineOrderListInterface jpmWineOrderListInterface = (JpmWineOrderListInterface) getHibernateTemplate().get(JpmWineOrderListInterface.class, idf);
        if (jpmWineOrderListInterface == null) {
            log.warn("uh oh, jpmWineOrderListInterface with idf '" + idf + "' not found...");
            throw new ObjectRetrievalFailureException(JpmWineOrderListInterface.class, idf);
        }

        return jpmWineOrderListInterface;
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmWineOrderListInterfaceDao#saveJpmWineOrderListInterface(JpmWineOrderListInterface
     *      jpmWineOrderListInterface)
     */
    public void saveJpmWineOrderListInterface(final JpmWineOrderListInterface jpmWineOrderListInterface) {
        getHibernateTemplate().saveOrUpdate(jpmWineOrderListInterface);
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmWineOrderListInterfaceDao#removeJpmWineOrderListInterface(Long
     *      idf)
     */
    public void removeJpmWineOrderListInterface(final Long idf) {
        getHibernateTemplate().delete(getJpmWineOrderListInterface(idf));
    }

    //added for getJpmWineOrderListInterfacesByCrm
    public List getJpmWineOrderListInterfacesByCrm(CommonRecord crm, Pager pager) {
        String hql = "from JpmWineOrderListInterface jpmWineOrderListInterface where 1=1";
        /**
         * ---example---
         * String userCode = crm.getString("userCode", "");
         * if(StringUtils.isNotEmpty(userCode)){
         * hql += "???????????";
         * }
         ***/
        // 
        if (!pager.getLimit().getSort().isSorted()) {
            //sort
            hql += " order by idf desc";
        } else {
            hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
        }
        return this.findObjectsByHqlQuery(hql, pager);
    }

    @Override
    public void saveJpmWineOrderListInterfaceAll(Collection<JpmWineOrderListInterface> jpmWineOrderListInterfaceSet) {
        this.getHibernateTemplate().saveOrUpdateAll(jpmWineOrderListInterfaceSet);
    }
}
