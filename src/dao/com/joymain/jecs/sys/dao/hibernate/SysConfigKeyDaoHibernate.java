
package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.dao.SysConfigKeyDao;
import com.joymain.jecs.sys.model.SysConfigKey;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysConfigKeyDaoHibernate extends BaseDaoHibernate implements SysConfigKeyDao {
    /**
     * @see com.joymain.jecs.sys.dao.SysConfigKeyDao#getSysConfigKeys(com.joymain.jecs.sys.model.SysConfigKey)
     */
    public List getSysConfigKeys(final SysConfigKey sysConfigKey) {
        return getHibernateTemplate().find("from SysConfigKey");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (sysConfigKey == null) {
            return getHibernateTemplate().find("from SysConfigKey");
        } else {
            // filter on properties set in the sysConfigKey
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(sysConfigKey).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(SysConfigKey.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysConfigKeyDao#getSysConfigKey(Long keyId)
     */
    public SysConfigKey getSysConfigKey(final Long keyId) {
        SysConfigKey sysConfigKey = (SysConfigKey) getHibernateTemplate().get(SysConfigKey.class, keyId);
        if (sysConfigKey == null) {
            log.warn("uh oh, sysConfigKey with keyId '" + keyId + "' not found...");
            throw new ObjectRetrievalFailureException(SysConfigKey.class, keyId);
        }

        return sysConfigKey;
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysConfigKeyDao#saveSysConfigKey(SysConfigKey sysConfigKey)
     */    
    public void saveSysConfigKey(final SysConfigKey sysConfigKey) {
        getHibernateTemplate().saveOrUpdate(sysConfigKey);
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysConfigKeyDao#removeSysConfigKey(Long keyId)
     */
    public void removeSysConfigKey(final Long keyId) {
    	this.executeUpdate("delete from SysConfigValue where sysConfigKey.keyId='"+keyId+"'");
        getHibernateTemplate().delete(getSysConfigKey(keyId));
    }
    
    /**
	 * 根据外部参数分页获取对应的List键值列表
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getSysConfigKeysByPage(CommonRecord crm, Pager pager) {
		String hqlQuery = "from SysConfigKey where 1=1";
		
		String configCode = crm.getString("configCode", "");
		if (!StringUtils.isEmpty(configCode)) {
			hqlQuery += " and configCode like '%" + configCode.toLowerCase() + "%'";
		}
		
		String keyDesc = crm.getString("keyDesc", "");
		if (!StringUtils.isEmpty(keyDesc)) {
			hqlQuery += " and keyDesc like '%" + keyDesc + "%'";
		}

		// 设置排序
		if (!pager.getLimit().getSort().isSorted()) {
			//缺省排序
			hqlQuery += " order by keyId desc";
		} else {
			hqlQuery += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		//

		List sysConfigKeys = this.findObjectsByHqlQuery(hqlQuery, pager);

		return sysConfigKeys;
	}
	
	/**
	 * 根据参数键值获取对应的参数
	 * @param listCode
	 * @return
	 */
	public SysConfigKey getSysConfigKeyByCode(final String configCode){
		return (SysConfigKey)this.getObjectByHqlQuery("from SysConfigKey where configCode='"+configCode+"'");
	}
}
