
package com.joymain.jecs.sys.service.impl;

import java.util.List;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysConfigKeyDao;
import com.joymain.jecs.sys.model.SysConfigKey;
import com.joymain.jecs.sys.service.SysConfigKeyManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysConfigKeyManagerImpl extends BaseManager implements SysConfigKeyManager {
    private SysConfigKeyDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setSysConfigKeyDao(SysConfigKeyDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.sys.service.SysConfigKeyManager#getSysConfigKeys(com.joymain.jecs.sys.model.SysConfigKey)
     */
    public List getSysConfigKeys(final SysConfigKey sysConfigKey) {
        return dao.getSysConfigKeys(sysConfigKey);
    }

    /**
     * @see com.joymain.jecs.sys.service.SysConfigKeyManager#getSysConfigKey(String keyId)
     */
    public SysConfigKey getSysConfigKey(final String keyId) {
        return dao.getSysConfigKey(new Long(keyId));
    }

    /**
     * @see com.joymain.jecs.sys.service.SysConfigKeyManager#saveSysConfigKey(SysConfigKey sysConfigKey)
     */
    public void saveSysConfigKey(SysConfigKey sysConfigKey) {
        dao.saveSysConfigKey(sysConfigKey);
    }

    /**
     * @see com.joymain.jecs.sys.service.SysConfigKeyManager#removeSysConfigKey(String keyId)
     */
    public void removeSysConfigKey(final String keyId) {
        dao.removeSysConfigKey(new Long(keyId));
    }
    
    /**
	 * 根据外部参数分页获取对应的List键值列表
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getSysConfigKeysByPage(CommonRecord crm, Pager pager) {
		return dao.getSysConfigKeysByPage(crm, pager);
	}
	
	/**
	 * 根据参数键值获取对应的参数
	 * @param listCode
	 * @return
	 */
	public SysConfigKey getSysConfigKeyByCode(final String configCode){
		return dao.getSysConfigKeyByCode(configCode);
	}
}
