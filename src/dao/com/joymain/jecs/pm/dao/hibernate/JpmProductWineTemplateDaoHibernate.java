package com.joymain.jecs.pm.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.model.JpmProductWineTemplate;
import com.joymain.jecs.pm.dao.JpmProductWineTemplateDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JpmProductWineTemplateDaoHibernate extends BaseDaoHibernate implements JpmProductWineTemplateDao {

    /**
     * @see com.joymain.jecs.pm.dao.JpmProductWineTemplateDao#getJpmProductWineTemplates(com.joymain.jecs.pm.model.JpmProductWineTemplate)
     */
    public List getJpmProductWineTemplates(final JpmProductWineTemplate jpmProductWineTemplate) {
        return getHibernateTemplate().find("from JpmProductWineTemplate");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpmProductWineTemplate == null) {
            return getHibernateTemplate().find("from JpmProductWineTemplate");
        } else {
            // filter on properties set in the jpmProductWineTemplate
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpmProductWineTemplate).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpmProductWineTemplate.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmProductWineTemplateDao#getJpmProductWineTemplate(Long
     *      productTemplateNo)
     */
    public JpmProductWineTemplate getJpmProductWineTemplate(final Long productTemplateNo) {
        JpmProductWineTemplate jpmProductWineTemplate = (JpmProductWineTemplate) getHibernateTemplate().get(JpmProductWineTemplate.class, productTemplateNo);
        if (jpmProductWineTemplate == null) {
            log.warn("uh oh, jpmProductWineTemplate with productTemplateNo '" + productTemplateNo + "' not found...");
            throw new ObjectRetrievalFailureException(JpmProductWineTemplate.class, productTemplateNo);
        }

        return jpmProductWineTemplate;
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmProductWineTemplateDao#saveJpmProductWineTemplate(JpmProductWineTemplate
     *      jpmProductWineTemplate)
     */
    public void saveJpmProductWineTemplate(final JpmProductWineTemplate jpmProductWineTemplate) {
        getHibernateTemplate().saveOrUpdate(jpmProductWineTemplate);
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmProductWineTemplateDao#removeJpmProductWineTemplate(String
     *      productTemplateNo)
     */
    public void removeJpmProductWineTemplate(final Long productTemplateNo) {
        getHibernateTemplate().delete(getJpmProductWineTemplate(productTemplateNo));
    }

    //added for getJpmProductWineTemplatesByCrm
    public List getJpmProductWineTemplatesByCrm(CommonRecord crm, Pager pager) {
        String hql = "from JpmProductWineTemplate jpmProductWineTemplate where 1=1";
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
            hql += " order by productTemplateNo desc";
        } else {
            hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
        }
        return this.findObjectsByHqlQuery(hql, pager);
    }

    public int updateInvalidStatus(final String productTemplateNo, final String invalidSatus) {
        StringBuffer sqlBuf = new StringBuffer();
        if (productTemplateNo != null) {
            sqlBuf.append(" update JpmProductWineTemplate jpwt set jpwt.isInvalid='");
            sqlBuf.append(invalidSatus);
            sqlBuf.append("' where jpwt.productTemplateNo='");
            sqlBuf.append(productTemplateNo);
            sqlBuf.append("'");
        }
        return this.executeUpdate(sqlBuf.toString());
    }
    
    /**
     * 查询出所有可用模版
     * @param map
     * @return
     */
    @Override
    public List<JpmProductWineTemplate> getTemplateList()
    {
        StringBuffer hql = new StringBuffer("from JpmProductWineTemplate j where j.isInvalid = '0'");
        Query query = getSession().createQuery(hql.toString());
        return query.list();
    }

    @Override
    public List<JpmProductWineTemplate> getTemplateListByProductNo(String productNo)
    {
        StringBuffer hql = new StringBuffer("from JpmProductWineTemplate j where j.isInvalid = '0' and j.productNo = '"+productNo+"'");
        Query query = getSession().createQuery(hql.toString());
        return query.list();
    }

}
