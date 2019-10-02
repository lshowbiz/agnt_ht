
package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.dao.SysListKeyDao;
import com.joymain.jecs.sys.model.SysListKey;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysListKeyDaoHibernate extends BaseDaoHibernate implements SysListKeyDao {

    /**
     * @see com.joymain.jecs.sys.dao.SysListKeyDao#getSysListKeys(com.joymain.jecs.sys.model.SysListKey)
     */
    public List getSysListKeys(final SysListKey sysListKey) {
    	return getHibernateTemplate().find("from SysListKey s");
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysListKeyDao#getSysListKey(Long keyId)
     */
    public SysListKey getSysListKey(final Long keyId) {
        SysListKey sysListKey = (SysListKey) getHibernateTemplate().get(SysListKey.class, keyId);
        if (sysListKey == null) {
            log.warn("uh oh, sysListKey with keyId '" + keyId + "' not found...");
            throw new ObjectRetrievalFailureException(SysListKey.class, keyId);
        }

        return sysListKey;
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysListKeyDao#saveSysListKey(SysListKey sysListKey)
     */    
    public void saveSysListKey(final SysListKey sysListKey) {
        getHibernateTemplate().saveOrUpdate(sysListKey);
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysListKeyDao#removeSysListKey(Long keyId)
     */
    public void removeSysListKey(final Long keyId) {
        getHibernateTemplate().delete(getSysListKey(keyId));
    }
    
    /**
	 * 根据外部参数分页获取对应的List键值列表
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getSysListKeysByPage(CommonRecord crm, Pager pager) {
		String hqlQuery = "from SysListKey where 1=1";
		
		String listCode = crm.getString("listCode", "");
		if (!StringUtils.isEmpty(listCode)) {
			hqlQuery += " and listCode like '%" + listCode + "%'";
		}
		
		String listName = crm.getString("listName", "");
		if (!StringUtils.isEmpty(listName)) {
			hqlQuery += " and listName like '%" + listName + "%'";
		}

		// 设置排序
		if (!pager.getLimit().getSort().isSorted()) {
			//缺省排序
			hqlQuery += " order by keyId desc";
		} else {
			hqlQuery += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		//

		List sysListKeys = this.findObjectsByHqlQuery(hqlQuery, pager);

		return sysListKeys;
	}
	
	/**
	 * 根据编码键值获取对应的字符键
	 * @param listCode
	 * @return
	 */
	public SysListKey getSysListKeyByCode(final String listCode){
		return (SysListKey)this.getObjectByHqlQuery("from SysListKey where listCode='"+listCode+"'");
	}
	
	/**
	 * 根据SQL获取对应的LIST键及值
	 * @return
	 */
	public List getSysListBySQL(){
		String sql="select a.list_code, b.value_code, b.value_title, b.ex_company_code " +
				"from jsys_list_key a, jsys_list_value b where a.key_id=b.key_id order by b.order_no";
		return this.findObjectsBySQL(sql);
	}
}
