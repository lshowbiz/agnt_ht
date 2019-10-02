
package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.dao.SysRolePowerDao;
import com.joymain.jecs.sys.model.SysRolePower;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysRolePowerDaoHibernate extends BaseDaoHibernate implements SysRolePowerDao {
    /**
     * @see com.joymain.jecs.sys.dao.SysRolePowerDao#getSysRolePowers(com.joymain.jecs.sys.model.SysRolePower)
     */
    public List getSysRolePowers(final SysRolePower sysRolePower) {
        return getHibernateTemplate().find("from SysRolePower");
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysRolePowerDao#getSysRolePower(Long rpId)
     */
    public SysRolePower getSysRolePower(final Long rpId) {
        SysRolePower sysRolePower = (SysRolePower) getHibernateTemplate().get(SysRolePower.class, rpId);
        if (sysRolePower == null) {
            log.warn("uh oh, sysRolePower with rpId '" + rpId + "' not found...");
            throw new ObjectRetrievalFailureException(SysRolePower.class, rpId);
        }

        return sysRolePower;
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysRolePowerDao#saveSysRolePower(SysRolePower sysRolePower)
     */    
    public void saveSysRolePower(final SysRolePower sysRolePower) {
        getHibernateTemplate().saveOrUpdate(sysRolePower);
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysRolePowerDao#removeSysRolePower(Long rpId)
     */
    public void removeSysRolePower(final Long rpId) {
        getHibernateTemplate().delete(getSysRolePower(rpId));
    }
    //added for getSysRolePowersByCrm
    public List getSysRolePowersByCrm(CommonRecord crm, Pager pager){
    	String hql = "from SysRolePower sysRolePower where 1=1";
    	// ��������
		if (!pager.getLimit().getSort().isSorted()) {
			//ȱʡ����
			hql += " order by rpId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    /**
     * 获取某一角色对应的记录列表
     * @param roleId
     * @return
     */
    public List getSysRolePowersByRoleId(final Long roleId){
    	return this.getHibernateTemplate().find("from SysRolePower where roleId=?",roleId);
    }
    
    /**
     * 删除角色ID对应的模块权限
     * @param roleId
     */
    public void removeSysRolePowersByRoleId(final Long roleId){
    	this.executeUpdate("delete from SysRolePower where roleId="+roleId);
    }
    
    /**
     * 删除角色指定的模块以外的所有权限
     * @param roleId
     * @param moduleId
     */
    public void removeSysRolePowersNotIn(final Long roleId, final String[] moduleId){
    	String hqlQuery="delete from SysRolePower where roleId='"+roleId+"'";
    	if(moduleId!=null && moduleId.length>0){
    		hqlQuery+=" and moduleId not in (";
    		for(int i=0;i<moduleId.length;i++){
    			if(i>0){
    				hqlQuery+=",";
    			}
    			hqlQuery+=moduleId[i];
    		}
    		hqlQuery+=")";
    	}
    	this.executeUpdate(hqlQuery);
    }
    
    /**
     * 批量保存多条记录
     * @param sysRolePowers
     */
    public void saveSysRolePowers(List sysRolePowers){
    	this.getHibernateTemplate().saveOrUpdateAll(sysRolePowers);
    }
    
    /**
     * 根据角色和模块获取对应的唯一的记录
     * @param roleId
     * @param moduleId
     * @return
     */
    public SysRolePower getSysRolePower(final Long roleId, final Long moduleId) {
    	return (SysRolePower)this.getObjectByHqlQuery("from SysRolePower where roleId='"+roleId+"' and moduleId='"+moduleId+"'");
    }
}