
package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.dao.SysUserRoleDao;
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.sys.model.SysUserRole;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysUserRoleDaoHibernate extends BaseDaoHibernate implements SysUserRoleDao {
    public int removeSysUserRoleBatch(String ruIds) {
		// TODO Auto-generated method stub
    	String hql = "delete from jsys_User_Role sysUserRole where sysUserRole.ru_Id in("+ruIds+")";
    	
    	this.getJdbcTemplate().execute(hql);
		return 0;
	}
    
    public int removeSysUserRoleByUcode(String userCode){
    	String hql = "delete from jsys_User_Role sysUserRole where sysUserRole.user_Code ='"+userCode+"' ";
    	
    	this.getJdbcTemplate().execute(hql);
		return 0;
    }
    
    public void saveSysUserRoleBatch(String users,String roleId){
    	
    	String[] userList = users.split(",");
    	for(int i=0;i<userList.length;i++){
    		SysUserRole sysUserRole = new SysUserRole();
    		sysUserRole.setRoleId(new Long(roleId));
    		String userCode = userList[i];
    		sysUserRole.setUserCode(userList[i]);
    		log.info("usercode="+sysUserRole.getUserCode());
    		this.getHibernateTemplate().saveOrUpdate(sysUserRole);
    	}
    }
    
    public void saveSysUserRoleByUcode(String roles,String userCode){
    	String hql = "delete from jsys_User_Role sysUserRole where sysUserRole.user_Code ='"+userCode+"' ";
    	
    	this.getJdbcTemplate().execute(hql);
    	
    	String[] roleList = roles.split(",");
    	for(int i=0;i<roleList.length;i++){
    		SysUserRole sysUserRole = new SysUserRole();
    		String roleId = roleList[i];
    		sysUserRole.setRoleId(new Long(roleId));    		
    		sysUserRole.setUserCode(userCode);
    		log.info("usercode="+sysUserRole.getUserCode());
    		this.getHibernateTemplate().saveOrUpdate(sysUserRole);
    	}
    	
    }
	/**
     * @see com.joymain.jecs.sys.dao.SysUserRoleDao#getSysUserRoles(com.joymain.jecs.sys.model.SysUserRole)
     */
    public List getSysUserRoles(final SysUserRole sysUserRole) {
        return getHibernateTemplate().find("from SysUserRole");
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysUserRoleDao#getSysUserRole(Long ruId)
     */
    public SysUserRole getSysUserRole(final Long ruId) {
        SysUserRole sysUserRole = (SysUserRole) getHibernateTemplate().get(SysUserRole.class, ruId);
        if (sysUserRole == null) {
            log.warn("uh oh, sysUserRole with ruId '" + ruId + "' not found...");
            throw new ObjectRetrievalFailureException(SysUserRole.class, ruId);
        }

        return sysUserRole;
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysUserRoleDao#saveSysUserRole(SysUserRole sysUserRole)
     */    
    public void saveSysUserRole(final SysUserRole sysUserRole) {
        getHibernateTemplate().saveOrUpdate(sysUserRole);
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysUserRoleDao#removeSysUserRole(Long ruId)
     */
    public void removeSysUserRole(final Long ruId) {
        getHibernateTemplate().delete(getSysUserRole(ruId));
    }
    //added for getSysUserRolesByCrm
    public List getSysUserRolesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from SysUserRole sysUserRole where 1=1";	
    	
    	String userCode = crm.getString("userCode","");
    	if(StringUtils.isNotEmpty(userCode)){
    		hql +=" and userCode='"+userCode+"'";
    		
    		/*** ����Լ��Ĳ�ѯ���������***/
        	// ��������
    		if (!pager.getLimit().getSort().isSorted()) {
    			//ȱʡ����
    			hql += " order by ruId desc";
    		} else {
    			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
    		}
    		return this.findObjectsByHqlQuery(hql, pager);
    	}else{
    		
    		String table1="select * from jsys_user u where 1=1 ";
    		String table2="select * from jsys_user_role ur where 1=1 ";
    		
			String companyCode = crm.getString("companyCode", "");
			if(StringUtils.isNotEmpty(companyCode)){
			table1 +=" and u.company_code='"+companyCode+"'";
			}
			String userType = crm.getString("userType","");
        	if(StringUtils.isNotEmpty(userType)){
        		
        		if("M".equals(userType))
        			table1 +=" and u.user_type='"+userType+"'";
    			else
    				table1 +=" and u.user_type in ('P','Q')";
        	} 
    		
    		String hasrole = crm.getString("hasrole","");
    		String roleId = crm.getString("roleId","");    		
        	if(StringUtils.isNotEmpty(roleId)){
        		table2 +=" and ur.role_id ='"+roleId+"'";
        	}
    		
    		String searchusercode = crm.getString("searchusercode","");
        	if(StringUtils.isNotEmpty(searchusercode)){
        		table1 +=" and u.user_code='"+searchusercode+"'";
        	}
    		
    		String state = crm.getString("state","");
        	if(StringUtils.isNotEmpty(state)){
        		table1 +=" and u.state='"+state+"'";        		
        	}    	
	        
        	hql = "select t2.ru_id, t2.role_id ,t1.user_code,t1.user_name,t1.user_type,t1.state,t1.company_code,t3.role_name from("+table1+") t1," +
			"("+table2+") t2 ,jsys_role t3 where t3.role_id=t2.role_id ";
        	
	        if(StringUtils.isNotEmpty(hasrole)){
    			if("1".equals(hasrole)){    				
                		hql +=" and t1.user_code=t2.user_code ";
        		}else{
        			table2 = " select distinct ur.user_code,ur.ru_id,ur.role_id from jsys_user_role ur where 1=1 ";
        			hql = "select t2.ru_id, t2.role_id ,t1.user_code,t1.user_name,t1.user_type,t1.state,t1.company_code from("+table1+") t1," +
    				"("+table2+") t2 where 1=1 ";
        			
                		hql +=" and t1.user_code =t2.user_code(+) and t2.user_code is null ";
        		}
    		}else{
    			hql +=" and t1.user_code=t2.user_code ";
    		}
	        
        	/*** ����Լ��Ĳ�ѯ���������***/
        	// ��������
    		if (!pager.getLimit().getSort().isSorted()) {
    			//ȱʡ����
    			hql += " order by ru_id desc";
    		} else {
    			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
    		}
    		return super.findObjectsBySQL(hql, pager);
    		
    	}
    	
    	
    	
    	
    	
    }

	public SysUserRole getSysUserRoleByUserCode(String userCode) {
		return (SysUserRole)this.getObjectByHqlQuery("from SysUserRole where userCode='"+userCode+"'");
	}
}
