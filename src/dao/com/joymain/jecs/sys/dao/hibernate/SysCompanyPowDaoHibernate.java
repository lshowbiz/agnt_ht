
package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.dao.SysCompanyPowDao;
import com.joymain.jecs.sys.model.SysCompanyPow;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysCompanyPowDaoHibernate extends BaseDaoHibernate implements SysCompanyPowDao {
    /**
     * @see com.joymain.jecs.sys.dao.SysCompanyPowDao#getSysCompanyPows(com.joymain.jecs.sys.model.SysCompanyPow)
     */
    public List getSysCompanyPows(final SysCompanyPow sysCompanyPow) {
        return getHibernateTemplate().find("from SysCompanyPow");
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysCompanyPowDao#getSysCompanyPow(Long id)
     */
    public SysCompanyPow getSysCompanyPow(final Long id) {
        SysCompanyPow sysCompanyPow = (SysCompanyPow) getHibernateTemplate().get(SysCompanyPow.class, id);
        if (sysCompanyPow == null) {
            log.warn("uh oh, sysCompanyPow with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(SysCompanyPow.class, id);
        }

        return sysCompanyPow;
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysCompanyPowDao#saveSysCompanyPow(SysCompanyPow sysCompanyPow)
     */    
    public void saveSysCompanyPow(final SysCompanyPow sysCompanyPow) {
        getHibernateTemplate().saveOrUpdate(sysCompanyPow);
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysCompanyPowDao#removeSysCompanyPow(Long id)
     */
    public void removeSysCompanyPow(final Long id) {
        getHibernateTemplate().delete(getSysCompanyPow(id));
    }
    //added for getSysCompanyPowsByCrm
    public List getSysCompanyPowsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from SysCompanyPow sysCompanyPow where 1=1";
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
    public void removeSysCompanyPows(final Long moduleId){
    	this.executeUpdate("delete from SysCompanyPow where sysModule.moduleId='"+moduleId+"'");
    }
    
    /**
     * 删除某模块不在所对应公司中的权限
     * @param moduleId
     * @param companyCodes
     */
    public void removeSysCompanyPowsNotInCompany(final Long moduleId, final String[] companyCodes){
    	String hqlQuery="delete from SysCompanyPow where sysModule.moduleId='"+moduleId+"'";
    	
    	if(companyCodes!=null && companyCodes.length>0){
    		hqlQuery+=" and companyCode not in(";
    		int j=0;
    		for(int i=0;i<companyCodes.length;i++){
    			if(j>0){
    				hqlQuery+=",";
    			}
    			if(!StringUtils.isEmpty(companyCodes[i])){
    				hqlQuery+="'"+companyCodes[i]+"'";
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
     * @param companyCode
     * @return
     */
    public SysCompanyPow getSysCompanyPow(final Long moduleId, final String companyCode) {
    	return (SysCompanyPow)this.getObjectByHqlQuery("from SysCompanyPow where sysModule.moduleId='"+moduleId+"' and companyCode='"+companyCode+"'");
    }
    
    /**
     * 获取某公司所能使用的所有权限
     * @param companyCode
     * @return
     */
    public List getSysCompanyPowsByCompany(final String companyCode) {
    	return this.getHibernateTemplate().find("from SysCompanyPow where companyCode=?",companyCode);
    }
    
    /**
     * 批量保存公司权限
     * @param sysCompanyPows
     */
    public void saveSysCompanyPows(final List sysCompanyPows){
    	this.getHibernateTemplate().saveOrUpdateAll(sysCompanyPows);
    }
}