
package com.joymain.jecs.pm.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.dao.JpmSalepromoterProDao;
import com.joymain.jecs.pm.model.JpmSalepromoterPro;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JpmSalepromoterProDaoHibernate extends BaseDaoHibernate implements JpmSalepromoterProDao {

    /**
     * @see com.joymain.jecs.pm.dao.JpmSalepromoterProDao#getJpmSalepromoterPros(com.joymain.jecs.pm.model.JpmSalepromoterPro)
     */
    public List getJpmSalepromoterPros(final JpmSalepromoterPro jpmSalepromoterPro) {
        return getHibernateTemplate().find("from JpmSalepromoterPro");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpmSalepromoterPro == null) {
            return getHibernateTemplate().find("from JpmSalepromoterPro");
        } else {
            // filter on properties set in the jpmSalepromoterPro
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpmSalepromoterPro).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpmSalepromoterPro.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmSalepromoterProDao#getJpmSalepromoterPro(BigDecimal id)
     */
    public JpmSalepromoterPro getJpmSalepromoterPro(final Long id) {
        JpmSalepromoterPro jpmSalepromoterPro = (JpmSalepromoterPro) getHibernateTemplate().get(JpmSalepromoterPro.class, id);
        if (jpmSalepromoterPro == null) {
            log.warn("uh oh, jpmSalepromoterPro with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JpmSalepromoterPro.class, id);
        }

        return jpmSalepromoterPro;
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmSalepromoterProDao#saveJpmSalepromoterPro(JpmSalepromoterPro jpmSalepromoterPro)
     */    
    public void saveJpmSalepromoterPro(final JpmSalepromoterPro jpmSalepromoterPro) {
        getHibernateTemplate().saveOrUpdate(jpmSalepromoterPro);
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmSalepromoterProDao#removeJpmSalepromoterPro(BigDecimal id)
     */
    public void removeJpmSalepromoterPro(final Long id) {
        getHibernateTemplate().delete(getJpmSalepromoterPro(id));
    }
    //added for getJpmSalepromoterProsByCrm
    public List getJpmSalepromoterProsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpmSalepromoterPro jpmSalepromoterPro where 1=1";
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
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public JpmSalepromoterPro getJpmSaleProByproductId(Long spno, String proNo) {
		String hql = "from JpmSalepromoterPro t where t.prono=? and spno=?";
		List<JpmSalepromoterPro> list = getHibernateTemplate().
				find(hql, new Object[]{proNo,spno}); 
		if(list !=null && list.size()>0)
			return list.get(0);
		else
			return null;
	}

	public List<JpmSalepromoterPro> getJpmSaleProBySpno(String spno) {
		String hql = "from JpmSalepromoterPro t where spno="+spno;
		return findObjectsByHqlQuery(hql);
	}

	public void delJpmSalepromoterProBySpno(String spno) {
		String hql = "from JpmSalepromoterPro p where p.spno="+spno;
		List<JpmSalepromoterPro> saleProList = getHibernateTemplate().find(hql);
		for(JpmSalepromoterPro sp :saleProList){
			this.getHibernateTemplate().delete(sp);
		}
	}
}
