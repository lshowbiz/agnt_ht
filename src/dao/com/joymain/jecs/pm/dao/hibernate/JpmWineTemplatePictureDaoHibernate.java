package com.joymain.jecs.pm.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.dao.JpmWineTemplatePictureDao;
import com.joymain.jecs.pm.model.JpmWineTemplatePicture;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JpmWineTemplatePictureDaoHibernate extends BaseDaoHibernate implements JpmWineTemplatePictureDao {

    /**
     * @see com.joymain.jecs.pm.dao.JpmWineTemplatePictureDao#getJpmWineTemplatePictures(com.joymain.jecs.pm.model.JpmWineTemplatePicture)
     */
    public List getJpmWineTemplatePictures(final JpmWineTemplatePicture jpmWineTemplatePicture) {
        return getHibernateTemplate().find("from JpmWineTemplatePicture");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpmWineTemplatePicture == null) {
            return getHibernateTemplate().find("from JpmWineTemplatePicture");
        } else {
            // filter on properties set in the jpmWineTemplatePicture
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpmWineTemplatePicture).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpmWineTemplatePicture.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmWineTemplatePictureDao#getJpmWineTemplatePicture(Long
     *      idf)
     */
    public JpmWineTemplatePicture getJpmWineTemplatePicture(final Long idf) {
        JpmWineTemplatePicture jpmWineTemplatePicture = (JpmWineTemplatePicture) getHibernateTemplate().get(JpmWineTemplatePicture.class, idf);
        if (jpmWineTemplatePicture == null) {
            log.warn("uh oh, jpmWineTemplatePicture with idf '" + idf + "' not found...");
            throw new ObjectRetrievalFailureException(JpmWineTemplatePicture.class, idf);
        }

        return jpmWineTemplatePicture;
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmWineTemplatePictureDao#saveJpmWineTemplatePicture(JpmWineTemplatePicture
     *      jpmWineTemplatePicture)
     */
    public void saveJpmWineTemplatePicture(final JpmWineTemplatePicture jpmWineTemplatePicture) {
        getHibernateTemplate().saveOrUpdate(jpmWineTemplatePicture);
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmWineTemplatePictureDao#removeJpmWineTemplatePicture(Long
     *      idf)
     */
    public void removeJpmWineTemplatePicture(final Long idf) {
        getHibernateTemplate().delete(getJpmWineTemplatePicture(idf));
    }

    //added for getJpmWineTemplatePicturesByCrm
    public List getJpmWineTemplatePicturesByCrm(CommonRecord crm, Pager pager) {
        String hql = "from JpmWineTemplatePicture jpmWineTemplatePicture where 1=1";
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
    public List<JpmWineTemplatePicture> getJpmWineTemplatePictureListBySubNo(String subNo)
    {
        StringBuffer hql = new StringBuffer("from JpmWineTemplatePicture j where j.jpmProductWineTemplateSub.subNo = " + subNo);
        Query query = getSession().createQuery(hql.toString());
        return query.list();
    }
}
