
package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.dao.SysConfigValueDao;
import com.joymain.jecs.sys.model.SysConfigValue;

public class SysConfigValueDaoHibernate extends BaseDaoHibernate implements SysConfigValueDao {

/**
		public List getPiProductsByHql(String hql){
			return getHibernateTemplate().find(hql);
		}

		public List getPiProductsBySql(String sql){
		}
**/		
    /**
     * @see com.joymain.jecs.sys.dao.SysConfigValueDao#getSysConfigValues(com.joymain.jecs.sys.model.SysConfigValue)
     */
    public List getSysConfigValues(final SysConfigValue sysConfigValue) {
        return getHibernateTemplate().find("from SysConfigValue");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (sysConfigValue == null) {
            return getHibernateTemplate().find("from SysConfigValue");
        } else {
            // filter on properties set in the sysConfigValue
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(sysConfigValue).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(SysConfigValue.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysConfigValueDao#getSysConfigValue(Long valueId)
     */
    public SysConfigValue getSysConfigValue(final Long valueId) {
        SysConfigValue sysConfigValue = (SysConfigValue) getHibernateTemplate().get(SysConfigValue.class, valueId);
        if (sysConfigValue == null) {
            log.warn("uh oh, sysConfigValue with valueId '" + valueId + "' not found...");
            throw new ObjectRetrievalFailureException(SysConfigValue.class, valueId);
        }

        return sysConfigValue;
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysConfigValueDao#saveSysConfigValue(SysConfigValue sysConfigValue)
     */    
    public void saveSysConfigValue(final SysConfigValue sysConfigValue) {
        getHibernateTemplate().saveOrUpdate(sysConfigValue);
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysConfigValueDao#removeSysConfigValue(Long valueId)
     */
    public void removeSysConfigValue(final Long valueId) {
        getHibernateTemplate().delete(getSysConfigValue(valueId));
    }
    
    /**
     * 根据公司编码获取对应的参数集
     * @param companyCode
     * @return List
     */
    public List getSysConfigValuesByCode(final String companyCode){
    	return this.getHibernateTemplate().find("from SysConfigValue where companyCode=?", companyCode);
    }
    
    /**
     * 根据公司编码获取对应的参数集(SQL),用于初始化Listener
     * @param companyCode
     * @return
     */
    public List getSysConfigValuesByCodeSQL(final String companyCode){
    	return this.findObjectsBySQL("select a.config_code, a.default_value, b.config_value from jsys_config_key a" +
    			" left join jsys_config_value b on (a.key_id=b.key_id and b.company_code='"+companyCode+"')");
    }
    
    /**
     * 根据键值ID获取对应的参数值,对应所有公司
     * @param keyId
     * @return
     */
    public List getSysConfigValuesAll(final Long keyId){
    	return this.findObjectsBySQL("select a.company_code, a.company_name, b.value_id, b.config_value from jal_company a " +
    			"left join jsys_config_value b on (b.company_code=a.company_code and b.key_id='"+keyId+"')");
    }
    
    /**
     * 批量保存语言值
     * @param sysConfigValues
     */
    public void saveSysConfigValues(List sysConfigValues){
    	this.getHibernateTemplate().saveOrUpdateAll(sysConfigValues);
    }
}
