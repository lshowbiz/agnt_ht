
package com.joymain.jecs.am.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.am.model.InwIntegration;
import com.joymain.jecs.am.dao.InwIntegrationDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class InwIntegrationDaoHibernate extends BaseDaoHibernate implements InwIntegrationDao {

    /**
     * @see com.joymain.jecs.am.dao.InwIntegrationDao#getInwIntegrations(com.joymain.jecs.am.model.InwIntegration)
     */
    public List getInwIntegrations(final InwIntegration inwIntegration) {
        return getHibernateTemplate().find("from InwIntegration");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (inwIntegration == null) {
            return getHibernateTemplate().find("from InwIntegration");
        } else {
            // filter on properties set in the inwIntegration
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(inwIntegration).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(InwIntegration.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.am.dao.InwIntegrationDao#getInwIntegration(BigDecimal id)
     */
    public InwIntegration getInwIntegration(final BigDecimal id) {
        InwIntegration inwIntegration = (InwIntegration) getHibernateTemplate().get(InwIntegration.class, id);
        if (inwIntegration == null) {
            log.warn("uh oh, inwIntegration with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(InwIntegration.class, id);
        }

        return inwIntegration;
    }

    /**
     * 向创新共赢的创新积分表中添加数据
     * @author gw 2013-09-05
     * @param inwIntegration
     */   
    public void saveInwIntegration(final InwIntegration inwIntegration) {
        getHibernateTemplate().saveOrUpdate(inwIntegration);
    }

    /**
     * @see com.joymain.jecs.am.dao.InwIntegrationDao#removeInwIntegration(BigDecimal id)
     */
    public void removeInwIntegration(final BigDecimal id) {
        getHibernateTemplate().delete(getInwIntegration(id));
    }
    //added for getInwIntegrationsByCrm
    public List getInwIntegrationsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from InwIntegration inwIntegration where 1=1";
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

    /**
     * 在增加创新积分前,先去数据库中查询.如果该条建议已经为会员增加了创新积分,那么不再为该会员添加创新积分
     * @author 2013-09-13 
     * @param suggestionUserCode
     * @param suggestionid
     * @return InwIntegration
     */
	public InwIntegration getInwIntegrationByParam(String suggestionUserCode,
			String suggestionid) {
		String hql = " from InwIntegration where suggestionUserCode='"+suggestionUserCode+"' and suggestionid = '"+suggestionid+"'";
		return (InwIntegration)this.getObjectByHqlQuery(hql);
	}

	/**
	 * 在扣除积分之前,首先进行放重复提交的校验
	 * @author 2014-06-10
	 * @param uniqueCode
	 * @return boolean
	 */
	public boolean getCheckExist(String uniqueCode) {
		String hql = " from InwIntegration inwIntegration where other='"+uniqueCode+"'";
		List list = this.findObjectsByHqlQuery(hql);
		if(null!=list){
			if(list.size()>0){
				return false;
			}else{
				return true;
			}
		}
		return true;
	}
	
}
