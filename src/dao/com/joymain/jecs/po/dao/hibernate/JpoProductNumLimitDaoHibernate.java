
package com.joymain.jecs.po.dao.hibernate;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.po.dao.JpoProductNumLimitDao;
import com.joymain.jecs.po.model.JpoProductNumLimit;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JpoProductNumLimitDaoHibernate extends BaseDaoHibernate implements JpoProductNumLimitDao {

    /**
     * @see com.joymain.jecs.po.dao.JpoProductNumLimitDao#getJpoProductNumLimits(com.joymain.jecs.po.model.JpoProductNumLimit)
     */
    public List getJpoProductNumLimits(final JpoProductNumLimit jpoProductNumLimit) {
        return getHibernateTemplate().find("from JpoProductNumLimit");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpoProductNumLimit == null) {
            return getHibernateTemplate().find("from JpoProductNumLimit");
        } else {
            // filter on properties set in the jpoProductNumLimit
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpoProductNumLimit).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpoProductNumLimit.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoProductNumLimitDao#getJpoProductNumLimit(BigDecimal id)
     */
    public JpoProductNumLimit getJpoProductNumLimit(final Long id) {
        JpoProductNumLimit jpoProductNumLimit = (JpoProductNumLimit) getHibernateTemplate().get(JpoProductNumLimit.class, id);
        if (jpoProductNumLimit == null) {
            log.warn("uh oh, jpoProductNumLimit with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JpoProductNumLimit.class, id);
        }

        return jpoProductNumLimit;
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoProductNumLimitDao#saveJpoProductNumLimit(JpoProductNumLimit jpoProductNumLimit)
     */    
    public void saveJpoProductNumLimit(final JpoProductNumLimit jpoProductNumLimit) {
        getHibernateTemplate().saveOrUpdate(jpoProductNumLimit);
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoProductNumLimitDao#removeJpoProductNumLimit(BigDecimal id)
     */
    public void removeJpoProductNumLimit(final Long id) {
        getHibernateTemplate().delete(getJpoProductNumLimit(id));
    }
    //added for getJpoProductNumLimitsByCrm
    public List getJpoProductNumLimitsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpoProductNumLimit jpoProductNumLimit where 1=1";
    	
    	String productNo = crm.getString("productNo", "");
    	if(StringUtils.isNotEmpty(productNo)){
    		hql += " and jpoProductNumLimit.productNo ='" + productNo +"'";
    	}
    	
    	String productName = crm.getString("productName", "");
    	if(StringUtils.isNotEmpty(productName)){
    		hql += " and jpoProductNumLimit.productName like '%" + productName +"%'";
    	}
    	
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
    
    public JpoProductNumLimit getByProductNo(String productNo){
    	String hql = "from JpoProductNumLimit jpoProductNumLimit where jpoProductNumLimit.productNo = '" + productNo +"'";
    	List list = this.findObjectsByHqlQuery(hql);
    	if(list.size()!=0){
    		return (JpoProductNumLimit)list.get(0);
    	}
    	return  null;
    }
    
    public List<JpoProductNumLimit>  getAll(){
    	String sql = "select * from JPO_PRODUCT_NUM_LIMIT";
    	return this.jdbcTemplate.queryForList(sql);
    	
    }
    
   public JpoProductNumLimit getNum(String productNo){
    	
    	String hql = " from JpoProductNumLimit  where productNo = '"+ productNo +"'";
    	List<JpoProductNumLimit> list = this.getSession().createQuery(hql).list();
    	if(list!=null && list.size()>=1){	    	   
	    	return (JpoProductNumLimit)list.get(0);
	    } 
    	return null;
    }
    
    public List getAllPros(){
    	return getSession().createQuery(" from JpoProductNumLimit ").list();
    }
}
