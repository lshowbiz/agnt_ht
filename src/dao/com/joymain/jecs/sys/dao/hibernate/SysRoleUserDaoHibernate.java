package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.dao.SysRoleUserDao;
import com.joymain.jecs.sys.model.SysRoleUser;

public class SysRoleUserDaoHibernate extends BaseDaoHibernate implements SysRoleUserDao {

	public List getAllAvailableRolesOfUser(String loginCode) {
		DetachedCriteria ca = DetachedCriteria.forClass(SysRoleUser.class);
		ca.add(Expression.eq("loginCode", loginCode));
		return getHibernateTemplate().findByCriteria(ca);
	}

	public void saveSysUserOfRole(SysRoleUser sysRoleUser) {
		getHibernateTemplate().saveOrUpdate(sysRoleUser);

	}

	public List getSysRolesOfUser(String loginCode) {
		DetachedCriteria ca = DetachedCriteria.forClass(SysRoleUser.class);
		ca.add(Expression.eq("loginCode", loginCode));
		return getHibernateTemplate().findByCriteria(ca);
	}

	public List getSysUsersOfRole(String roleId) {
		DetachedCriteria ca = DetachedCriteria.forClass(SysRoleUser.class);
		ca.add(Expression.eq("roleId", new Long(roleId)));
		return getHibernateTemplate().findByCriteria(ca);
	}

	public int getUseingCountsOfRoleByUser(String userCode) {
		String hqlQuery = "from SysRoleUser where loginCode='" + userCode+"'";
		return this.getTotalCountByHQL(hqlQuery);
	}

	public int getUseingCountsOfUserByRole(String roleId) {
		String hqlQuery = "from SysRoleUser where roleId=" + roleId;
		return this.getTotalCountByHQL(hqlQuery);
	}

	public void removeAllSysRoleOfUser(String userCode) {
		String hqlQuery = "delete from SysRoleUser where loginCode='" + userCode+"'";
		executeUpdate(hqlQuery);
	}

	public void removeAllSysUserOfRole(String roleId) {
		String hqlQuery = "delete from SysRoleUser where roleId=" + roleId;
		executeUpdate(hqlQuery);

	}

}
