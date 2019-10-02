
package com.joymain.jecs.am.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.am.model.InwDemand;
import com.joymain.jecs.am.model.InwProblem;
import com.joymain.jecs.am.dao.InwProblemDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class InwProblemDaoHibernate extends BaseDaoHibernate implements InwProblemDao {

    /**
     * @see com.joymain.jecs.am.dao.InwProblemDao#getInwProblems(com.joymain.jecs.am.model.InwProblem)
     */
    public List getInwProblems(final InwProblem inwProblem) {
        return getHibernateTemplate().find("from InwProblem");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (inwProblem == null) {
            return getHibernateTemplate().find("from InwProblem");
        } else {
            // filter on properties set in the inwProblem
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(inwProblem).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(InwProblem.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * 创新共赢的共赢帮助的详细查询---或---创新共赢的共赢帮助的修改初始化查询
     * @author gw 2013-08-16
     * @see com.joymain.jecs.am.dao.InwProblemDao#getInwProblem(String id)
     */
    public InwProblem getInwProblem(final Long id) {
        InwProblem inwProblem = (InwProblem) getHibernateTemplate().get(InwProblem.class, id);
        if (inwProblem == null) {
            log.warn("uh oh, inwProblem with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(InwProblem.class, id);
        }

        return inwProblem;
    }

    /**
     * 创新共赢的共赢帮助的添加或者修改操作
     * @author gw 2013-08-26 
     * @see com.joymain.jecs.am.dao.InwProblemDao#saveInwProblem(InwProblem inwProblem)
     */    
    public void saveInwProblem(final InwProblem inwProblem) {
        getHibernateTemplate().saveOrUpdate(inwProblem);
    }

    /**
     * 创新共赢的共赢帮助的删除
     * @author gw 2013-08-26
     * @see com.joymain.jecs.am.dao.InwProblemDao#removeInwProblem(String id)
     */
    public void removeInwProblem(final Long id) {
        getHibernateTemplate().delete(getInwProblem(id));
    }
    //added for getInwProblemsByCrm
    public List getInwProblemsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from InwProblem inwProblem where 1=1";
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
     * 创新共赢的共赢帮助的查询
     * @author gw  2013-08-26
     * @param crm
     * @param pager
     * @return　list
     */
	public List getInwProblemList(CommonRecord crm, Pager pager) {
		String sql = " select * from  inw_problem where 1=1 ";
		String species = crm.getString("species", "");
		if(!StringUtil.isEmpty(species)){
			sql += " and species = '"+species+"'";
		}
		return this.findObjectsBySQL(sql,pager);
	}

	/**
     * 创新共赢的共赢帮助的审核(审核过的那些问题可以在前台显示)
     * @author gw  2013-08-28
     * @param idList
     */
	public void inwProblemAudit(String ids) {
        String[] idsList = ids.split(",");
    	for(int i=0;i<idsList.length;i++){
    		InwProblem inwProblem = (InwProblem) getHibernateTemplate().get(InwProblem.class, Long.parseLong(idsList[i]) );
    		//inw_problem字段中other字段表示审核(公示与隐藏--审核过的那些问题,就可以在前台显示了)  Y表示审核过了
    		inwProblem.setOther("Y");
    		this.getHibernateTemplate().saveOrUpdate(inwProblem);
    	}
		
	}
}
