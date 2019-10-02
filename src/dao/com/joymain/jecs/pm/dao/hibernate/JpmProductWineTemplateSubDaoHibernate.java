package com.joymain.jecs.pm.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.dao.JpmProductWineTemplateSubDao;
import com.joymain.jecs.pm.model.JpmProductWineTemplateSub;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JpmProductWineTemplateSubDaoHibernate extends BaseDaoHibernate implements JpmProductWineTemplateSubDao {

    /**
     * @see com.joymain.jecs.pm.dao.JpmProductWineTemplateSubDao#getJpmProductWineTemplateSubs(com.joymain.jecs.pm.model.JpmProductWineTemplateSub)
     */
    public List getJpmProductWineTemplateSubs(final JpmProductWineTemplateSub jpmProductWineTemplateSub) {
        return getHibernateTemplate().find("from JpmProductWineTemplateSub");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpmProductWineTemplateSub == null) {
            return getHibernateTemplate().find("from JpmProductWineTemplateSub");
        } else {
            // filter on properties set in the jpmProductWineTemplateSub
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpmProductWineTemplateSub).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpmProductWineTemplateSub.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmProductWineTemplateSubDao#getJpmProductWineTemplateSub(String
     *      subNo)
     */
    public JpmProductWineTemplateSub getJpmProductWineTemplateSub(final String subNo) {
        JpmProductWineTemplateSub jpmProductWineTemplateSub = (JpmProductWineTemplateSub) getHibernateTemplate().get(JpmProductWineTemplateSub.class, subNo);
        if (jpmProductWineTemplateSub == null) {
            log.warn("uh oh, jpmProductWineTemplateSub with subNo '" + subNo + "' not found...");
            throw new ObjectRetrievalFailureException(JpmProductWineTemplateSub.class, subNo);
        }

        return jpmProductWineTemplateSub;
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmProductWineTemplateSubDao#saveJpmProductWineTemplateSub(JpmProductWineTemplateSub
     *      jpmProductWineTemplateSub)
     */
    public void saveJpmProductWineTemplateSub(final JpmProductWineTemplateSub jpmProductWineTemplateSub) {
        getHibernateTemplate().saveOrUpdate(jpmProductWineTemplateSub);
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmProductWineTemplateSubDao#removeJpmProductWineTemplateSub(String
     *      subNo)
     */
    public void removeJpmProductWineTemplateSub(final String subNo) {
        getHibernateTemplate().delete(getJpmProductWineTemplateSub(subNo));
    }

    //added for getJpmProductWineTemplateSubsByCrm
    public List getJpmProductWineTemplateSubsByCrm(CommonRecord crm, Pager pager) {
        String hql = "from JpmProductWineTemplateSub jpmProductWineTemplateSub where 1=1";
        /**
         * ---example---
         * String userCode = crm.getString("userCode", "");
         * if(StringUtils.isNotEmpty(userCode)){
         * hql += "???????????";
         * }
         ***/
        // 
        String isInvalid = crm.getString("isInvalid", "");
        if (StringUtils.isNotEmpty(isInvalid)) {
            hql += " and jpmProductWineTemplateSub.isInvalid='" + isInvalid + "'";
        }
        if (!pager.getLimit().getSort().isSorted()) {
            //sort
            hql += " order by subNo desc";
        } else {
            hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
        }
        return this.findObjectsByHqlQuery(hql, pager);
    }

    @Override
    public void saveJpmProductWineTemplateSubList(List<JpmProductWineTemplateSub> jpmProductWineTemplateSubList) {
        this.getHibernateTemplate().saveOrUpdateAll(jpmProductWineTemplateSubList);
    }
    
    @Override
    public List<JpmProductWineTemplateSub> getJpmProductWineTemplateSubListByProductTemplateNo(
        Map<String, String> map)
    {
        StringBuffer hql = new StringBuffer("from JpmProductWineTemplateSub j where j.jpmProductWineTemplate.productTemplateNo='"+map.get("productTemplateNo")+"'");
        Query query = getSession().createQuery(hql.toString());
        return query.list();
    }

    @Override
    public JpmProductWineTemplateSub getJpmProductWineTemplateSubBySubNo(String subNo)
    {
        StringBuffer hql = new StringBuffer("from JpmProductWineTemplateSub where subNo = '" + subNo +"'");
        return (JpmProductWineTemplateSub)this.getObjectByHqlQuery(hql.toString());
    }
}
