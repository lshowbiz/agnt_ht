
package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.dao.SysUsrTypePowDao;
import com.joymain.jecs.sys.model.SysUsrTypePow;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysUsrTypePowDaoHibernate extends BaseDaoHibernate implements SysUsrTypePowDao {
    /**
     * @see com.joymain.jecs.sys.dao.SysUsrTypePowDao#getSysUsrTypePows(com.joymain.jecs.sys.model.SysUsrTypePow)
     */
    public List getSysUsrTypePows(final SysUsrTypePow sysUsrTypePow) {
        return getHibernateTemplate().find("from SysUsrTypePow");
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysUsrTypePowDao#getSysUsrTypePow(Long id)
     */
    public SysUsrTypePow getSysUsrTypePow(final Long id) {
        SysUsrTypePow sysUsrTypePow = (SysUsrTypePow) getHibernateTemplate().get(SysUsrTypePow.class, id);
        if (sysUsrTypePow == null) {
            log.warn("uh oh, sysUsrTypePow with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(SysUsrTypePow.class, id);
        }

        return sysUsrTypePow;
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysUsrTypePowDao#saveSysUsrTypePow(SysUsrTypePow sysUsrTypePow)
     */    
    public void saveSysUsrTypePow(final SysUsrTypePow sysUsrTypePow) {
        getHibernateTemplate().saveOrUpdate(sysUsrTypePow);
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysUsrTypePowDao#removeSysUsrTypePow(Long id)
     */
    public void removeSysUsrTypePow(final Long id) {
        getHibernateTemplate().delete(getSysUsrTypePow(id));
    }
    //added for getSysUsrTypePowsByCrm
    public List getSysUsrTypePowsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from SysUsrTypePow sysUsrTypePow where 1=1";
    	// ��������
		if (!pager.getLimit().getSort().isSorted()) {
			//ȱʡ����
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    /**
     * 删除某模块下的记录
     * @param moduleId
     */
    public void removeSysUsrTypePows(final Long moduleId){
    	this.executeUpdate("delete from SysUsrTypePow where sysModule.moduleId='"+moduleId+"'");
    }
    
    /**
     * 删除某模块不在所对应用户类别中的权限
     * @param moduleId
     * @param userTypes
     */
    public void removeSysUsrTypePowsNotInUsrType(final Long moduleId, final String[] userTypes){
    	String hqlQuery="delete from SysUsrTypePow where sysModule.moduleId='"+moduleId+"'";
    	
    	if(userTypes!=null && userTypes.length>0){
    		hqlQuery+=" and userType not in(";
    		int j=0;
    		for(int i=0;i<userTypes.length;i++){
    			if(j>0){
    				hqlQuery+=",";
    			}
    			if(!StringUtils.isEmpty(userTypes[i])){
    				hqlQuery+="'"+userTypes[i]+"'";
    				j++;
    			}
    		}
    		hqlQuery+=")";
    	}
    	this.executeUpdate(hqlQuery);
    }
    
    /**
     * 根据模块ID和公司获取对应的唯一的权限
     * @param moduleId
     * @param userType
     * @return
     */
    public SysUsrTypePow getSysUsrTypePow(final Long moduleId, final String userType) {
    	return (SysUsrTypePow)this.getObjectByHqlQuery("from SysUsrTypePow where sysModule.moduleId='"+moduleId+"' and userType='"+userType+"'");
    }
}