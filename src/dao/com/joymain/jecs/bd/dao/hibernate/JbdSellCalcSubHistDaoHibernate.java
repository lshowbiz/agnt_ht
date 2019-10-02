
package com.joymain.jecs.bd.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdSellCalcSubHist;
import com.joymain.jecs.bd.dao.JbdSellCalcSubHistDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdSellCalcSubHistDaoHibernate extends BaseDaoHibernate implements JbdSellCalcSubHistDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdSellCalcSubHistDao#getJbdSellCalcSubHists(com.joymain.jecs.bd.model.JbdSellCalcSubHist)
     */
    public List getJbdSellCalcSubHists(final JbdSellCalcSubHist jbdSellCalcSubHist) {
        return getHibernateTemplate().find("from JbdSellCalcSubHist");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdSellCalcSubHist == null) {
            return getHibernateTemplate().find("from JbdSellCalcSubHist");
        } else {
            // filter on properties set in the jbdSellCalcSubHist
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdSellCalcSubHist).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdSellCalcSubHist.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdSellCalcSubHistDao#getJbdSellCalcSubHist(BigDecimal id)
     */
    public JbdSellCalcSubHist getJbdSellCalcSubHist(final BigDecimal id) {
        JbdSellCalcSubHist jbdSellCalcSubHist = (JbdSellCalcSubHist) getHibernateTemplate().get(JbdSellCalcSubHist.class, id);
        if (jbdSellCalcSubHist == null) {
            log.warn("uh oh, jbdSellCalcSubHist with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdSellCalcSubHist.class, id);
        }

        return jbdSellCalcSubHist;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdSellCalcSubHistDao#saveJbdSellCalcSubHist(JbdSellCalcSubHist jbdSellCalcSubHist)
     */    
    public void saveJbdSellCalcSubHist(final JbdSellCalcSubHist jbdSellCalcSubHist) {
        getHibernateTemplate().saveOrUpdate(jbdSellCalcSubHist);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdSellCalcSubHistDao#removeJbdSellCalcSubHist(BigDecimal id)
     */
    public void removeJbdSellCalcSubHist(final BigDecimal id) {
        getHibernateTemplate().delete(getJbdSellCalcSubHist(id));
    }
    //added for getJbdSellCalcSubHistsByCrm
    public List getJbdSellCalcSubHistsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdSellCalcSubHist jbdSellCalcSubHist where 1=1";
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

	public List getJbdSellCalcSubHistsByLink(String linkNo, Integer wweek) {
		String hql=" from VJbdSellCalcSub where linkNo='"+linkNo+"' and wweek="+wweek+" order by serialNumber";
		return this.findObjectsByHqlQuery(hql);
	}
	public List getJbdSellCalcSubHistsByLinkHist(String linkNo, Integer wweek) {
		String hql=" from JbdSellCalcSubHist where linkNo='"+linkNo+"' and wweek="+wweek+" order by serialNumber";
		return this.findObjectsByHqlQuery(hql);
	}
}
