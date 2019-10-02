
package com.joymain.jecs.pm.dao.hibernate;

import java.util.Date;
import java.util.List;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.dao.JpmSalePromoterDao;
import com.joymain.jecs.pm.model.JpmSalePromoter;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JpmSalePromoterDaoHibernate extends BaseDaoHibernate implements JpmSalePromoterDao {

    /**
     * @see com.joymain.jecs.pm.dao.JpmSalePromoterDao#getJpmSalePromoters(com.joymain.jecs.pm.model.JpmSalePromoter)
     */
    public List getJpmSalePromoters(final JpmSalePromoter jpmSalePromoter) {
        return getHibernateTemplate().find("from JpmSalePromoter");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpmSalePromoter == null) {
            return getHibernateTemplate().find("from JpmSalePromoter");
        } else {
            // filter on properties set in the jpmSalePromoter
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpmSalePromoter).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpmSalePromoter.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmSalePromoterDao#getJpmSalePromoter(Long spno)
     */
    public JpmSalePromoter getJpmSalePromoter(final Long spno) {
        JpmSalePromoter jpmSalePromoter = (JpmSalePromoter) getHibernateTemplate().get(JpmSalePromoter.class, spno);
        if (jpmSalePromoter == null) {
            log.warn("uh oh, jpmSalePromoter with spno '" + spno + "' not found...");
            throw new ObjectRetrievalFailureException(JpmSalePromoter.class, spno);
        }

        return jpmSalePromoter;
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmSalePromoterDao#saveJpmSalePromoter(JpmSalePromoter jpmSalePromoter)
     */    
    public void saveJpmSalePromoter(final JpmSalePromoter jpmSalePromoter) {
        getHibernateTemplate().saveOrUpdate(jpmSalePromoter);
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmSalePromoterDao#removeJpmSalePromoter(Long spno)
     */
    public void removeJpmSalePromoter(final Long spno) {
        getHibernateTemplate().delete(getJpmSalePromoter(spno));
    }
    //added for getJpmSalePromotersByCrm
    public List getJpmSalePromotersByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpmSalePromoter jpmSalePromoter where 1=1";
    	/** 
	---example---
	String userCode = crm.getString("userCode", "");
	if(StringUtils.isNotEmpty(userCode)){
		hql += "???????????";
	}
	 ***/
    	
    	String stime = crm.getString("startime");
    	String etime = crm.getString("endtime");
    	String discount = crm.getString("discount");
    	String saleType = crm.getString("saleType");
    	String integral = crm.getString("integral");
    	String proName = crm.getString("proName");
    	String proNo = crm.getString("proNo");
    	String isactiva = crm.getString("isactiva");
    	
    	String pv = crm.getString("pv");
    	String amount = crm.getString("amount");
    	
    	
    	
    	if(org.apache.commons.lang.StringUtils.isNotBlank(stime)){
    		hql += " and jpmSalePromoter.startime>= to_date('"+stime+"','yyyy-MM-dd') ";
    	}
    	if(org.apache.commons.lang.StringUtils.isNotBlank(etime)){
    		hql += " and jpmSalePromoter.endtime <= to_date('"+etime+"','yyyy-MM-dd') ";
    	}
    	if(org.apache.commons.lang.StringUtils.isNotBlank(saleType)){
    		if(saleType.equals("1") || saleType.equals("5")){
    			hql += " and (jpmSalePromoter.saleType ='1' or jpmSalePromoter.saleType='5') ";
    		} else {
    			hql += " and jpmSalePromoter.saleType ='"+saleType+"' ";
    		}
    	}
    	if(org.apache.commons.lang.StringUtils.isNotBlank(discount)){
    		hql += " and jpmSalePromoter.discount="+discount+" ";
    	}
    	if(org.apache.commons.lang.StringUtils.isNotBlank(pv)){
    		hql += " and jpmSalePromoter.pv="+pv+" ";
    	}
    	if(org.apache.commons.lang.StringUtils.isNotBlank(amount)){
    		hql += " and jpmSalePromoter.amount="+amount+" ";
    	}
    	
    	
    	if(org.apache.commons.lang.StringUtils.isNotBlank(integral)){
    		hql += " and jpmSalePromoter.integral="+integral+" ";
    	}
    	if(org.apache.commons.lang.StringUtils.isNotBlank(proName)){
    		hql += " and jpmSalePromoter.presentname='"+proName+"' ";
    	}
    	if(org.apache.commons.lang.StringUtils.isNotBlank(proNo)){
    		hql += " and jpmSalePromoter.presentno='"+proNo+"' ";
    	}
    	if(org.apache.commons.lang.StringUtils.isNotBlank(isactiva)){
    		hql += " and jpmSalePromoter.isactiva='"+isactiva+"' ";
    	}
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by spno desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public List<JpmSalePromoter> getSaleByDate(String stime,String isActiva,String flag) {
		String hql = "";
		
		if(StringUtils.isNotBlank(flag)){
			hql = "from JpmSalePromoter t where t.startime <= to_date('"+stime+"','yyyy-MM-dd hh24:mi:ss') " +
					"and t.endtime >=to_date('"+stime+"','yyyy-MM-dd hh24:mi:ss') " +
					"and t.isactiva='"+isActiva+"' and t.saleType='"+flag+"'  order by t.ordertype";
		} else {
			hql = "from JpmSalePromoter t where t.startime <= to_date('"+stime+"','yyyy-MM-dd hh24:mi:ss') " +
					"and t.endtime >=to_date('"+stime+"','yyyy-MM-dd hh24:mi:ss') " +
					"and t.isactiva='"+isActiva+"' order by t.ordertype";
		}
		return this.findObjectsByHqlQuery(hql);
	}
}
