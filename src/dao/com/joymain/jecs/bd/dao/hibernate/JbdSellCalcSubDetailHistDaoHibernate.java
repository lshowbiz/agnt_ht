
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdSellCalcSubDetailHist;
import com.joymain.jecs.bd.dao.JbdSellCalcSubDetailHistDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdSellCalcSubDetailHistDaoHibernate extends BaseDaoHibernate implements JbdSellCalcSubDetailHistDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdSellCalcSubDetailHistDao#getJbdSellCalcSubDetailHists(com.joymain.jecs.bd.model.JbdSellCalcSubDetailHist)
     */
    public List getJbdSellCalcSubDetailHists(final JbdSellCalcSubDetailHist jbdSellCalcSubDetailHist) {
        return getHibernateTemplate().find("from JbdSellCalcSubDetailHist");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdSellCalcSubDetailHist == null) {
            return getHibernateTemplate().find("from JbdSellCalcSubDetailHist");
        } else {
            // filter on properties set in the jbdSellCalcSubDetailHist
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdSellCalcSubDetailHist).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdSellCalcSubDetailHist.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdSellCalcSubDetailHistDao#getJbdSellCalcSubDetailHist(Long id)
     */
    public JbdSellCalcSubDetailHist getJbdSellCalcSubDetailHist(final Long id) {
        JbdSellCalcSubDetailHist jbdSellCalcSubDetailHist = (JbdSellCalcSubDetailHist) getHibernateTemplate().get(JbdSellCalcSubDetailHist.class, id);
        if (jbdSellCalcSubDetailHist == null) {
            log.warn("uh oh, jbdSellCalcSubDetailHist with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdSellCalcSubDetailHist.class, id);
        }

        return jbdSellCalcSubDetailHist;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdSellCalcSubDetailHistDao#saveJbdSellCalcSubDetailHist(JbdSellCalcSubDetailHist jbdSellCalcSubDetailHist)
     */    
    public void saveJbdSellCalcSubDetailHist(final JbdSellCalcSubDetailHist jbdSellCalcSubDetailHist) {
        getHibernateTemplate().saveOrUpdate(jbdSellCalcSubDetailHist);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdSellCalcSubDetailHistDao#removeJbdSellCalcSubDetailHist(Long id)
     */
    public void removeJbdSellCalcSubDetailHist(final Long id) {
        getHibernateTemplate().delete(getJbdSellCalcSubDetailHist(id));
    }
    //added for getJbdSellCalcSubDetailHistsByCrm
    public List getJbdSellCalcSubDetailHistsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from VJbdSellCalcSubDetail jbdSellCalcSubDetailHist where 1=1";
    	hql+=returnHql(crm);
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public List getJbdSellCalcRecommendBouns(String userCode, Integer wweek,String bounsType) {
		String hql="from VJbdSellCalcSubDetail where userCode='"+userCode+"' and wweek ='"+wweek+"'  ";
		if(!StringUtil.isEmpty(bounsType)){
			hql+=" and bounsType='02' ";
		}
		return this.getHibernateTemplate().find(hql);
	}

	public BigDecimal getSumJbdSellCalcRecommendBouns(CommonRecord crm) {
		String hql="select nvl(sum(pv),0) from VJbdSellCalcSubDetail jbdSellCalcSubDetailHist where 1=1";
		hql+=returnHql(crm);
		return (BigDecimal)this.getHibernateTemplate().find(hql).get(0);
		
	}
	
	private String returnHql(CommonRecord crm){
		String hql="";
		String userCode = crm.getString("userCode", "");
		if(!StringUtil.isEmpty(userCode)){
			hql += " and userCode='"+userCode+"'";
		}
		String wweek = crm.getString("wweek", "");
		if(!StringUtil.isEmpty(userCode)){
			hql += " and wweek='"+wweek+"'";
		}
		hql+= " and bounsType='01' ";
		return hql;
	}
}
