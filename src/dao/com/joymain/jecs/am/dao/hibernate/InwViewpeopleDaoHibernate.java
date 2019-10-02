
package com.joymain.jecs.am.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.am.model.InwViewpeople;
import com.joymain.jecs.am.dao.InwViewpeopleDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class InwViewpeopleDaoHibernate extends BaseDaoHibernate implements InwViewpeopleDao {

    /**
     * @see com.joymain.jecs.am.dao.InwViewpeopleDao#getInwViewpeoples(com.joymain.jecs.am.model.InwViewpeople)
     */
    public List getInwViewpeoples(final InwViewpeople inwViewpeople) {
        return getHibernateTemplate().find("from InwViewpeople");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (inwViewpeople == null) {
            return getHibernateTemplate().find("from InwViewpeople");
        } else {
            // filter on properties set in the inwViewpeople
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(inwViewpeople).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(InwViewpeople.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.am.dao.InwViewpeopleDao#getInwViewpeople(BigDecimal id)
     */
    public InwViewpeople getInwViewpeople(final Long id) {
        InwViewpeople inwViewpeople = (InwViewpeople) getHibernateTemplate().get(InwViewpeople.class, id);
        if (inwViewpeople == null) {
            log.warn("uh oh, inwViewpeople with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(InwViewpeople.class, id);
        }

        return inwViewpeople;
    }

    /**
     * 创新共赢的建议-查询-向inwViewpeople表中录入数据
     * @author gw 2013-08-21 
     * @param inwViewpeople
     */    
    public void saveInwViewpeople(final InwViewpeople inwViewpeople) {
        getHibernateTemplate().saveOrUpdate(inwViewpeople);
    }

    /**
     * @see com.joymain.jecs.am.dao.InwViewpeopleDao#removeInwViewpeople(BigDecimal id)
     */
    public void removeInwViewpeople(final Long id) {
        getHibernateTemplate().delete(getInwViewpeople(id));
    }
    //added for getInwViewpeoplesByCrm
    public List getInwViewpeoplesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from InwViewpeople inwViewpeople where 1=1";
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
     *  通过建议表的ID获得建议查看的对象
     *  @author 2013-09-13
     * @param string
     * @return InwViewpeople
     */
	public InwViewpeople getInwViewpeopleBySuggestionId(String suggestionId) {
		String hql = " from InwViewpeople where suggestionId  = '"+suggestionId+"' ";
		return (InwViewpeople) this.getObjectByHqlQuery(hql);
	}
}
