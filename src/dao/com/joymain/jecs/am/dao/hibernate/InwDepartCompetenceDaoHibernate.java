
package com.joymain.jecs.am.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.am.model.InwDepartCompetence;
import com.joymain.jecs.am.dao.InwDepartCompetenceDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class InwDepartCompetenceDaoHibernate extends BaseDaoHibernate implements InwDepartCompetenceDao {

    /**
     * @see com.joymain.jecs.am.dao.InwDepartCompetenceDao#getInwDepartCompetences(com.joymain.jecs.am.model.InwDepartCompetence)
     */
    public List getInwDepartCompetences(final InwDepartCompetence inwDepartCompetence) {
        return getHibernateTemplate().find("from InwDepartCompetence");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (inwDepartCompetence == null) {
            return getHibernateTemplate().find("from InwDepartCompetence");
        } else {
            // filter on properties set in the inwDepartCompetence
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(inwDepartCompetence).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(InwDepartCompetence.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.am.dao.InwDepartCompetenceDao#getInwDepartCompetence(BigDecimal id)
     */
    public InwDepartCompetence getInwDepartCompetence(final Long id) {
        InwDepartCompetence inwDepartCompetence = (InwDepartCompetence) getHibernateTemplate().get(InwDepartCompetence.class, id);
        if (inwDepartCompetence == null) {
            log.warn("uh oh, inwDepartCompetence with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(InwDepartCompetence.class, id);
        }

        return inwDepartCompetence;
    }

    /**
     * @see com.joymain.jecs.am.dao.InwDepartCompetenceDao#saveInwDepartCompetence(InwDepartCompetence inwDepartCompetence)
     */    
    public void saveInwDepartCompetence(final InwDepartCompetence inwDepartCompetence) {
        getHibernateTemplate().saveOrUpdate(inwDepartCompetence);
    }

    /**
     * @see com.joymain.jecs.am.dao.InwDepartCompetenceDao#removeInwDepartCompetence(BigDecimal id)
     */
    public void removeInwDepartCompetence(final Long id) {
        getHibernateTemplate().delete(getInwDepartCompetence(id));
    }
   
    /**
     * 部门权限的查询(初始化查询或有条件查询)
     * @author gw  2014-05-27 
     * @param crm
     * @param pager
     * @return list
     */
    public List getInwDepartCompetencesByCrm(CommonRecord crm, Pager pager){
    	
    	String sql = " select a.id id,b.id departmentId,c.id demandId,b.name departmentName ,c.name demandName,a.create_time,a.create_user_code "+
                            " from inw_depart_competence a,inw_department b, inw_demand c "+
                            " where a.department_id = b.id and a.demand_id = c.id ";
	   
    	String departmentName = crm.getString("departmentName", "");
		if(!StringUtil.isEmpty(departmentName)){
			sql += " and b.name = '"+departmentName+"'";
		}
		
		String demandName = crm.getString("demandName", "");
		if(!StringUtil.isEmpty(demandName)){
			sql += " and c.name = '"+demandName+"'";
		}
		
		String createTimeBegin = crm.getString("createTimeBegin", "");
	    if(!StringUtil.isEmpty(createTimeBegin)){
	    	sql += " and a.create_time >=to_date('"+createTimeBegin+" 00:00:00 ','yyyy-MM-dd hh24:mi:ss ') ";
	    }
	    
	    String createTimeEnd = crm.getString("createTimeEnd", "");
	    if(!StringUtil.isEmpty(createTimeEnd)){
	    	sql += " and a.create_time <=to_date('"+createTimeEnd+" 23:59:59 ','yyyy-MM-dd hh24:mi:ss ') ";
	    }
	    
	    //创建时间倒序排序
	    sql += " order by a.create_time desc ";
    	
		return this.findObjectsBySQL(sql, pager);
		
    }

    /**
	 * 根据部门表的主键departmentId查询该部门所拥有建议查看的权限
	 * @author yxzz 2014-07-01
	 * @param departmentId
	 * @return String
	 */
	public String getDemandIdListByDepartmentId(String departmentId) {
		
		String hql = " from InwDepartCompetence inwDepartCompetence where inwDepartCompetence.departmentId= '"+departmentId+"'";
		List<InwDepartCompetence> inwDepartCompetenceList = this.findObjectsByHqlQuery(hql);
		String a = "";
		if(null!=inwDepartCompetenceList){
			if(inwDepartCompetenceList.size()>0){
				for(int i=0;i<inwDepartCompetenceList.size();i++){
					InwDepartCompetence inwDepartCompetence = inwDepartCompetenceList.get(i);
					a = a+inwDepartCompetence.getDemandId()+",";
				}
				   return a;
			}
		}
		
		
		
		return null;
	}
    
}
