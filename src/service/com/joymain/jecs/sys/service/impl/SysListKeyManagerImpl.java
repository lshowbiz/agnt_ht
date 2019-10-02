
package com.joymain.jecs.sys.service.impl;

import java.util.List;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysListKeyDao;
import com.joymain.jecs.sys.model.SysListKey;
import com.joymain.jecs.sys.service.SysListKeyManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysListKeyManagerImpl extends BaseManager implements SysListKeyManager {
    private SysListKeyDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setSysListKeyDao(SysListKeyDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.sys.service.SysListKeyManager#getSysListKeys(com.joymain.jecs.sys.model.SysListKey)
     */
    public List getSysListKeys(final SysListKey sysListKey) {
        return dao.getSysListKeys(sysListKey);
    }

    /**
     * @see com.joymain.jecs.sys.service.SysListKeyManager#getSysListKey(String keyId)
     */
    public SysListKey getSysListKey(final String keyId) {
        return dao.getSysListKey(new Long(keyId));
    }

    /**
     * @see com.joymain.jecs.sys.service.SysListKeyManager#saveSysListKey(SysListKey sysListKey)
     */
    public void saveSysListKey(SysListKey sysListKey) {
        dao.saveSysListKey(sysListKey);
    }

    /**
     * @see com.joymain.jecs.sys.service.SysListKeyManager#removeSysListKey(String keyId)
     */
    public void removeSysListKey(final String keyId) {
        dao.removeSysListKey(new Long(keyId));
    }
    
    /**
	 * 根据外部参数分页获取对应的List键值列表
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getSysListKeysByPage(CommonRecord crm, Pager pager) {
		return dao.getSysListKeysByPage(crm, pager);
	}
	
	/**
	 * 根据编码键值获取对应的字符键
	 * @param listCode
	 * @return
	 */
	public SysListKey getSysListKeyByCode(final String listCode){
		return dao.getSysListKeyByCode(listCode);
	}
	
	/**
	 * 根据SQL获取对应的LIST键及值
	 * @return
	 */
	public List getSysListBySQL(){
		return dao.getSysListBySQL();
	}
}
