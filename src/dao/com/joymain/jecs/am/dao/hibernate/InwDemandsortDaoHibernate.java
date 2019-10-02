
package com.joymain.jecs.am.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.am.model.InwDemandsort;
import com.joymain.jecs.am.dao.InwDemandsortDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class InwDemandsortDaoHibernate extends BaseDaoHibernate implements InwDemandsortDao {

    /**
     * @see com.joymain.jecs.am.dao.InwDemandsortDao#getInwDemandsorts(com.joymain.jecs.am.model.InwDemandsort)
     */
    public List getInwDemandsorts(final InwDemandsort inwDemandsort) {
        return getHibernateTemplate().find("from InwDemandsort");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (inwDemandsort == null) {
            return getHibernateTemplate().find("from InwDemandsort");
        } else {
            // filter on properties set in the inwDemandsort
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(inwDemandsort).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(InwDemandsort.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.am.dao.InwDemandsortDao#getInwDemandsort(BigDecimal id)
     */
    public InwDemandsort getInwDemandsort(final Long id) {
        InwDemandsort inwDemandsort = (InwDemandsort) getHibernateTemplate().get(InwDemandsort.class, id);
        if (inwDemandsort == null) {
            log.warn("uh oh, inwDemandsort with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(InwDemandsort.class, id);
        }

        return inwDemandsort;
    }

    /**
     * @see com.joymain.jecs.am.dao.InwDemandsortDao#saveInwDemandsort(InwDemandsort inwDemandsort)
     */    
    public void saveInwDemandsort(final InwDemandsort inwDemandsort) {
        getHibernateTemplate().saveOrUpdate(inwDemandsort);
    }

    /**
     * 删除
     * @see com.joymain.jecs.am.dao.InwDemandsortDao#removeInwDemandsort(BigDecimal id)
     */
    public void removeInwDemandsort(final Long id) {
        getHibernateTemplate().delete(getInwDemandsort(id));
    }
    
    
    /**
     * 创新共赢-需求分类表初始化(有条件)分页查询
     * @author gw  2013-11-04
     * @param crm
     * @param  pager
     * @return  list
     */
    public List getInwDemandsortsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from InwDemandsort inwDemandsort where 1=1";
    	String name = crm.getString("name", "");
    	if(!StringUtil.isEmpty("name")){
    		hql += " and  name like '%"+name+"%'";
    	}
		return this.findObjectsByHqlQuery(hql, pager);
    }

    /**
	 * 查询创新共赢需求分类表的所有数据
	 * @author gw  2013-11-05
	 * @return
	 */
	public List getDemandsortList() {
	    String hql = " from InwDemandsort where 1=1  ";
		return this.findObjectsByHqlQuery(hql);
	}

	/**
	 * 创新共赢-------------需求分类------新需求--------通过ID获取需求分类的名字
	 * @author gw 2013-13-14
	 * @return
	 */
	public String getInwDemandsortById(String id) {
		Long idl = Long.parseLong(id);
		InwDemandsort inwDemandsort = (InwDemandsort) getHibernateTemplate().get(InwDemandsort.class, idl);
		return inwDemandsort.getName();
	}
}
