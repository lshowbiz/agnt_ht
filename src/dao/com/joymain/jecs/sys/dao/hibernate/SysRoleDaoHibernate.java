
package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.dao.SysRoleDao;
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysRoleDaoHibernate extends BaseDaoHibernate implements SysRoleDao {

    /**
     * @see com.joymain.jecs.sys.dao.SysRoleDao#getSysRoles(com.joymain.jecs.sys.model.SysRole)
     */
    public List getSysRoles(final SysRole sysRole) {
        return getHibernateTemplate().find("from SysRole");
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysRoleDao#getSysRole(Long roleId)
     */
    public SysRole getSysRole(final Long roleId) {
        SysRole sysRole = (SysRole) getHibernateTemplate().get(SysRole.class, roleId);

        return sysRole;
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysRoleDao#saveSysRole(SysRole sysRole)
     */    
    public void saveSysRole(final SysRole sysRole) {
        getHibernateTemplate().saveOrUpdate(sysRole);
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysRoleDao#removeSysRole(Long roleId)
     */
    public void removeSysRole(final Long roleId) {
    	this.executeUpdate("delete from SysRolePower where roleId="+roleId);
    	this.executeUpdate("delete from SysUserRole where roleId="+roleId);
        getHibernateTemplate().delete(getSysRole(roleId));
    }
    //added for getSysRolesByCrm
    public List getSysRolesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from SysRole sysRole where 1=1";
    	//add by lihao,20170119,角色名称 不显示包含“禁用”的角色名
    	hql += " and roleName not like '%禁用%' ";
    	String companyCode = crm.getString("companyCode", "");
		if (StringUtils.isNotEmpty(companyCode) && !"AA".equals(companyCode)) {
			hql += " and companyCode='" + companyCode + "'";
		}
		
		String userType = crm.getString("userType", "");
		if (StringUtils.isNotEmpty(userType)) {
			hql += " and userType='" + userType + "'";
		}
		
		String roleCode = crm.getString("roleCode", "");
		if (StringUtils.isNotEmpty(roleCode)) {
			hql += " and roleCode like '%" + roleCode + "%'";
		}
    	// ��������
		if (!pager.getLimit().getSort().isSorted()) {
			//ȱʡ����
			hql += " order by roleId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    /**
     * 根据角色编码获取对应的角色记录
     * @param roleCode
     * @return
     */
    public SysRole getSysRoleByCode(final String roleCode) {
    	return (SysRole)this.getObjectByHqlQuery("from SysRole where roleCode='"+roleCode+"'");
    }
}