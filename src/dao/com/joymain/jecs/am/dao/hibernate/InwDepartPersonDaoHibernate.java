
package com.joymain.jecs.am.dao.hibernate;

import java.util.List;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.am.model.InwDepartPerson;
import com.joymain.jecs.am.model.InwDepartment;
import com.joymain.jecs.am.dao.InwDepartPersonDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class InwDepartPersonDaoHibernate extends BaseDaoHibernate implements InwDepartPersonDao {

    /**
     * @see com.joymain.jecs.am.dao.InwDepartPersonDao#getInwDepartPersons(com.joymain.jecs.am.model.InwDepartPerson)
     */
    public List getInwDepartPersons(final InwDepartPerson inwDepartPerson) {
        return getHibernateTemplate().find("from InwDepartPerson");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (inwDepartPerson == null) {
            return getHibernateTemplate().find("from InwDepartPerson");
        } else {
            // filter on properties set in the inwDepartPerson
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(inwDepartPerson).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(InwDepartPerson.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.am.dao.InwDepartPersonDao#getInwDepartPerson(Long id)
     */
    public InwDepartPerson getInwDepartPerson(final Long id) {
        InwDepartPerson inwDepartPerson = (InwDepartPerson) getHibernateTemplate().get(InwDepartPerson.class, id);
        if (inwDepartPerson == null) {
            log.warn("uh oh, inwDepartPerson with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(InwDepartPerson.class, id);
        }

        return inwDepartPerson;
    }

    /**
     * @see com.joymain.jecs.am.dao.InwDepartPersonDao#saveInwDepartPerson(InwDepartPerson inwDepartPerson)
     */    
    public void saveInwDepartPerson(final InwDepartPerson inwDepartPerson) {
        getHibernateTemplate().saveOrUpdate(inwDepartPerson);
    }

    /**
     * @see com.joymain.jecs.am.dao.InwDepartPersonDao#removeInwDepartPerson(Long id)
     */
    public void removeInwDepartPerson(final Long id) {
        getHibernateTemplate().delete(getInwDepartPerson(id));
    }
   
    /**
     * 查询部门人员
     * @author yxzz 2014-07-02
     * @param crm
     * @param pager
     * @return list
     */
    public List getInwDepartPersonsByCrm(CommonRecord crm, Pager pager){
    	String sql = " select a.user_code user_code,b.name name,a.create_time create_time,a.id id,b.id department_id  " +
    			" from inw_depart_person a,inw_department b where a.department_id = b.id ";
    	String userCode = crm.getString("userCode", "");
    	if(!StringUtil.isEmpty(userCode)){
    		   sql += " and a.user_code = '"+userCode+"'";
    	}
    	String name = crm.getString("name", "");
    	if(!StringUtil.isEmpty(name)){
    	       sql += " and b.name like '%"+name+"%'";
    	}
    	       sql += " order by a.create_time desc ";
		
		return this.findObjectsBySQL(sql, pager);
    }

    /**
	 * 获取部门表的主键
	 * @author yxzz 2014-07-02
	 * @param userCodeHD  会员编号(登录系统的账号)
	 * @return string 
	 */
	public String getDepartmentId(String userCodeHD) {
		String hql = " from InwDepartPerson inwDepartPerson where inwDepartPerson.userCode='"+userCodeHD+"'";
		List list = this.findObjectsByHqlQuery(hql);
		if(!(null==list)){
			if(list.size()>0){
				InwDepartPerson inwDepartPerson = (InwDepartPerson) list.get(0);
				return inwDepartPerson.getDepartmentId();
			}
		}	
		return null;
	}

	/**
	 * 会员编号唯一性校验
	 * @author yxzz 2014-07-02
	 * @param inwDepartPerson
	 * @return boolean 
	 */
	public boolean getUserCodeUniqueCheckResult(InwDepartPerson inwDepartPerson) {
		String userCode = inwDepartPerson.getUserCode();
		String hql = " from InwDepartPerson inwDepartPerson where inwDepartPerson.userCode='"+userCode+"'";
		// <>HQL语句中的不等于号
		if(!(null==inwDepartPerson.getId())){
			   hql += " and inwDepartPerson.id <> '"+inwDepartPerson.getId()+"' ";
		}
		List<InwDepartment> list = this.findObjectsByHqlQuery(hql);
		if(!(null==list)){
			if(list.size()>0){
				return true;
			}
		}
		return false;
	}
    
    
}
