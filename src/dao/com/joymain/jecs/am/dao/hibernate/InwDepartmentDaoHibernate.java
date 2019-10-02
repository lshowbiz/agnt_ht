
package com.joymain.jecs.am.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.am.model.InwDepartment;
import com.joymain.jecs.am.dao.InwDepartmentDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.validation.BindException;

public class InwDepartmentDaoHibernate extends BaseDaoHibernate implements InwDepartmentDao {

    /**
     * @see com.joymain.jecs.am.dao.InwDepartmentDao#getInwDepartments(com.joymain.jecs.am.model.InwDepartment)
     */
    public List getInwDepartments(final InwDepartment inwDepartment) {
        return getHibernateTemplate().find("from InwDepartment");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (inwDepartment == null) {
            return getHibernateTemplate().find("from InwDepartment");
        } else {
            // filter on properties set in the inwDepartment
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(inwDepartment).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(InwDepartment.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.am.dao.InwDepartmentDao#getInwDepartment(BigDecimal id)
     */
    public InwDepartment getInwDepartment(final Long id) {
        InwDepartment inwDepartment = (InwDepartment) getHibernateTemplate().get(InwDepartment.class, id);
        if (inwDepartment == null) {
            log.warn("uh oh, inwDepartment with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(InwDepartment.class, id);
        }

        return inwDepartment;
    }

    /**
     * @see com.joymain.jecs.am.dao.InwDepartmentDao#saveInwDepartment(InwDepartment inwDepartment)
     */    
    public void saveInwDepartment(final InwDepartment inwDepartment) {
        getHibernateTemplate().saveOrUpdate(inwDepartment);
    }

    /**
     * @see com.joymain.jecs.am.dao.InwDepartmentDao#removeInwDepartment(BigDecimal id)
     */
    public void removeInwDepartment(final BigDecimal id) {
    }
    
    /**
     * 创新共赢的部门---查询
     * @author gw 2014-05-21
     * @param  crm
     * @param  pager
     * @return list
     */
    public List getInwDepartmentsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from InwDepartment inwDepartment where 1=1";
	
		String name = crm.getString("name", "");
		if(!StringUtil.isEmpty(name)){
			hql += " and inwDepartment.name = '"+name+"'";
		}
		
		String createTimeBegin = crm.getString("createTimeBegin", "");
	    if(!StringUtil.isEmpty(createTimeBegin)){
	    	hql += " and inwDepartment.createTime >=to_date('"+createTimeBegin+" 00:00:00 ','yyyy-MM-dd hh24:mi:ss ') ";
	    }
	    
	    String createTimeEnd = crm.getString("createTimeEnd", "");
	    if(!StringUtil.isEmpty(createTimeEnd)){
	    	hql += " and inwDepartment.createTime <=to_date('"+createTimeEnd+" 23:59:59 ','yyyy-MM-dd hh24:mi:ss ') ";
	    }
	    
	    String higerDepartName = crm.getString("higerDepartName", "");
		if(!StringUtil.isEmpty(higerDepartName)){
			hql += " and inwDepartment.higerDepartName = '"+higerDepartName+"'";
		}
		
		//查询上级部门(录入或编辑时查询用到)----开始
	    String departCategory = crm.getString("departCategory", "");
	    if(!StringUtil.isEmpty(departCategory)){
	    	int a = Integer.parseInt(departCategory)-1;
	    	String category = String.valueOf(a);
			hql += " and inwDepartment.category = '"+category+"'";
		}
		//查询上级部门(录入或编辑时查询用到)----结束
    	 
		if (!pager.getLimit().getSort().isSorted()) {
			hql += " order by inwDepartment.id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
		
    }

    /**
     * 创新共赢的部门录入或编辑之前的部门名称唯一性校验
	 * @author gw  2014-05-21
	 * @param inwDepartment
	 * @return boolean
	 */
	public boolean getNameUniqueCheckResult(InwDepartment inwDepartment) {
		String name = inwDepartment.getName();
		String sql = " select * from inw_department where 1=1 and name='"+name+"'";
		String hql = " from InwDepartment inwDepartment where inwDepartment.name='"+name+"'";
		// <>HQL语句中的不等于号
		if(!(null==inwDepartment.getId())){
			   hql += " and inwDepartment.id <> '"+inwDepartment.getId()+"' ";
		}
		List<InwDepartment> list = this.findObjectsByHqlQuery(hql);
		if(!(null==list)){
			if(list.size()>0){
				return true;
			}
		}
		return false;
	}

	/**
     * 创新共赢的部门-----删除
	 * @author gw  2014-05-21
	 * @param inwDepartment
	 * @return boolean
	 */
	public void inwDepartmentRemove(InwDepartment inwDepartment) {
        getHibernateTemplate().delete(inwDepartment);
	}

	/**
	 * 创新共赢----指定部门---获取部门查询建议的权限(需求)
	 * @author gw 2014-05-30
	 * @param String
	 * @return string 
	 */
	public String getInwDepartmentIdListById(String departmentId) {
		
		String sql = " select b.demand_id demandId from inw_department a,inw_depart_competence b where a.id=b.department_id ";
		String departmentIdList = "";
		if(!StringUtil.isEmpty(departmentId)){
			   sql += " and a.id= '"+departmentId+"' ";
			   List list = this.findObjectsBySQL(sql);
			   String idList = "";
			   if(null!=list){
				   if(list.size()>0){
					     for(int i=0;i<list.size();i++){
					    	 idList += list.get(i).toString()+",";
					     }
					    departmentIdList = idList.substring(0, idList.length()-1);
				   }
			   }
			return departmentIdList;
		}else{
		    return null;
		}
	}

	/**
	 * 查询出所有部门的信息列表
	 * @author yxzz 2014-07-02
	 * @return list 
	 */
	public List getInwDepartmentList() {
		String hql = " from InwDepartment ";
		return this.findObjectsByHqlQuery(hql);
	}
	
}
