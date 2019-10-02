
package com.joymain.jecs.pm.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.dao.JpmCombinationRelatedDao;
import com.joymain.jecs.pm.model.JpmCombinationRelated;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JpmCombinationRelatedDaoHibernate extends BaseDaoHibernate implements JpmCombinationRelatedDao {

    /**
     * @see com.joymain.jecs.pm.dao.JpmCombinationRelatedDao#getJpmCombinationRelateds(com.joymain.jecs.pm.model.JpmCombinationRelated)
     */
    public List getJpmCombinationRelateds(final JpmCombinationRelated jpmCombinationRelated) {
        return null;

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpmCombinationRelated == null) {
            return getHibernateTemplate().find("from JpmCombinationRelated");
        } else {
            // filter on properties set in the jpmCombinationRelated
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpmCombinationRelated).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpmCombinationRelated.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmCombinationRelatedDao#getJpmCombinationRelated(Long uniNo)
     */
    public JpmCombinationRelated getJpmCombinationRelated(final Long uniNo) {
        JpmCombinationRelated jpmCombinationRelated = (JpmCombinationRelated) getHibernateTemplate().get(JpmCombinationRelated.class, uniNo);
        if (jpmCombinationRelated == null) {
            log.warn("uh oh, jpmCombinationRelated with uniNo '" + uniNo + "' not found...");
            throw new ObjectRetrievalFailureException(JpmCombinationRelated.class, uniNo);
        }

        return jpmCombinationRelated;
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmCombinationRelatedDao#saveJpmCombinationRelated(JpmCombinationRelated jpmCombinationRelated)
     */    
    public void saveJpmCombinationRelated(final JpmCombinationRelated jpmCombinationRelated) {
//        getHibernateTemplate().saveOrUpdate(jpmCombinationRelated);
        getHibernateTemplate().merge(jpmCombinationRelated);
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmCombinationRelatedDao#removeJpmCombinationRelated(Long uniNo)
     */
    public void removeJpmCombinationRelated(final Long uniNo) {
        getHibernateTemplate().delete(getJpmCombinationRelated(uniNo));
    }
    //added for getJpmCombinationRelatedsByCrm
    public List getJpmCombinationRelatedsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpmCombinationRelated jpmCombinationRelated where 1=1";
    	/** 
	---example---
	String userCode = crm.getString("userCode", "");
	if(StringUtils.isNotEmpty(userCode)){
		hql += "???????????";
	}
	 ***/
    	//套餐编码
    	String productNo = crm.getString("productNo", "");
    	if(StringUtils.isNotEmpty(productNo)){
    		hql += " and jpmCombinationRelated.productNo ='"+productNo.trim()+"' ";
    	}
    	
    	//套餐名称
    	String productName = crm.getString("productName", "");
    	if(StringUtils.isNotEmpty(productName)){
    		hql += " and jpmCombinationRelated.productName like '%"+productName.trim()+"%' ";
    	}
    	
    	//子商品编码
    	String subProductNo = crm.getString("subProductNo", "");
    	if(StringUtils.isNotEmpty(subProductNo)){
    		hql += " and jpmCombinationRelated.subProductNo ='"+subProductNo.trim()+"' ";
    	}
    	
    	//子商品名称
    	String subProductName = crm.getString("subProductName", "");
    	if(StringUtils.isNotEmpty(subProductName)){
    		hql += " and jpmCombinationRelated.subProductName like '%"+subProductName.trim()+"%' ";
    	}
    	
    	//期别
    	String productDate = crm.getString("productDate", "");
    	if(StringUtils.isNotEmpty(productDate)){
    		hql += " and jpmCombinationRelated.productDate ='"+productDate.trim()+"' ";
    	}
    	
    	//是否赠品
    	String isFree = crm.getString("isFree", "");
    	if(StringUtils.isNotEmpty(isFree)){
    		hql += " and jpmCombinationRelated.isFree ='"+isFree.trim()+"' ";
    	}
    	
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by uniNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public List getJpmCombinationRelateds(String packageCode,
			String productDate, String subProductNo) {
		StringBuffer hql = new StringBuffer("from JpmCombinationRelated jpmCombinationRelated where 1=1");
		hql.append(" and jpmCombinationRelated.productNo ='"+packageCode.trim()+"' ");
		hql.append(" and jpmCombinationRelated.productDate ='"+productDate.trim()+"' ");
		hql.append(" and jpmCombinationRelated.subProductNo ='"+subProductNo.trim()+"' ");
		
		return this.findObjectsByHqlQuery(hql.toString());
	}
}
