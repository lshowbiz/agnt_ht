
package com.joymain.jecs.sys.service.impl;

import java.util.List;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysListValueDao;
import com.joymain.jecs.sys.model.SysListValue;
import com.joymain.jecs.sys.service.SysListValueManager;

public class SysListValueManagerImpl extends BaseManager implements SysListValueManager {
    private SysListValueDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setSysListValueDao(SysListValueDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.sys.service.SysListValueManager#getSysListValues(com.joymain.jecs.sys.model.SysListValue)
     */
    public List getSysListValues(final SysListValue sysListValue) {
        return dao.getSysListValues(sysListValue);
    }

    /**
     * @see com.joymain.jecs.sys.service.SysListValueManager#getSysListValue(String valueId)
     */
    public SysListValue getSysListValue(final String valueId) {
        return dao.getSysListValue(new Long(valueId));
    }

    /**
     * @see com.joymain.jecs.sys.service.SysListValueManager#saveSysListValue(SysListValue sysListValue)
     */
    public void saveSysListValue(SysListValue sysListValue) {
        dao.saveSysListValue(sysListValue);
    }

    /**
     * @see com.joymain.jecs.sys.service.SysListValueManager#removeSysListValue(String valueId)
     */
    public void removeSysListValue(final String valueId) {
        dao.removeSysListValue(new Long(valueId));
    }
    
    /**
	 * 根据List值编码获取对应的List值
	 * @param keyId
	 * @param listCode
	 * @return
	 */
	public SysListValue getSysListValueByCode(final String keyId, final String valueCode){
		return dao.getSysListValueByCode(keyId, valueCode);
	}
	
	/**
	 * 根据键值获取对应的LIST值列表
	 * @param keyId
	 * @return
	 */
	public List getSysListValuesByKeyId(String keyId){
		return dao.getSysListValuesByKeyId(keyId);
	}
	
	/**
	 * 根据List编码获取对应的List值列表
	 * @param listCode
	 * @param companyCode
	 * @return
	 */
	public List getSysListValuesByCode(final String listCode, final String companyCode){
		return dao.getSysListValuesByCode(listCode, companyCode);
	}
}
