
package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.dao.SysListValueDao;
import com.joymain.jecs.sys.model.SysListValue;

public class SysListValueDaoHibernate extends BaseDaoHibernate implements SysListValueDao {

/**
		public List getPiProductsByHql(String hql){
			return getHibernateTemplate().find(hql);
		}

		public List getPiProductsBySql(String sql){
		}
**/		
    /**
     * @see com.joymain.jecs.sys.dao.SysListValueDao#getSysListValues(com.joymain.jecs.sys.model.SysListValue)
     */
    public List getSysListValues(final SysListValue sysListValue) {
        return getHibernateTemplate().find("from SysListValue order by orderNo");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (sysListValue == null) {
            return getHibernateTemplate().find("from SysListValue");
        } else {
            // filter on properties set in the sysListValue
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(sysListValue).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(SysListValue.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysListValueDao#getSysListValue(Long valueId)
     */
    public SysListValue getSysListValue(final Long valueId) {
        SysListValue sysListValue = (SysListValue) getHibernateTemplate().get(SysListValue.class, valueId);
        if (sysListValue == null) {
            log.warn("uh oh, sysListValue with valueId '" + valueId + "' not found...");
            throw new ObjectRetrievalFailureException(SysListValue.class, valueId);
        }

        return sysListValue;
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysListValueDao#saveSysListValue(SysListValue sysListValue)
     */    
    public void saveSysListValue(final SysListValue sysListValue) {
        getHibernateTemplate().saveOrUpdate(sysListValue);
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysListValueDao#removeSysListValue(Long valueId)
     */
    public void removeSysListValue(final Long valueId) {
        getHibernateTemplate().delete(getSysListValue(valueId));
    }
    
    /**
	 * 根据List值编码获取对应的List值
	 * @param keyId
	 * @param listCode
	 * @return
	 */
	public SysListValue getSysListValueByCode(final String keyId, final String valueCode){
		return (SysListValue)this.getObjectByHqlQuery("from SysListValue where sysListKey.keyId='"+keyId+"' and valueCode='"+valueCode+"'");
	}
	
	/**
	 * 根据键值获取对应的LIST值列表
	 * @param keyId
	 * @return
	 */
	public List getSysListValuesByKeyId(String keyId){
		return this.getHibernateTemplate().find("from SysListValue where sysListKey.keyId='"+keyId+"' order by orderNo, valueId");
	}
	
	/**
	 * 根据List编码获取对应的List值列表
	 * @param listCode
	 * @param companyCode
	 * @return
	 */
	public List getSysListValuesByCode(final String listCode, final String companyCode){
    	return this.getHibernateTemplate().find("from SysListValue where sysListKey.listCode='"+listCode+"' and (exCompanyCode is null or INSTR(exCompanyCode,'"+companyCode+"',1,1)=0)   order by orderNo");
    }
}
