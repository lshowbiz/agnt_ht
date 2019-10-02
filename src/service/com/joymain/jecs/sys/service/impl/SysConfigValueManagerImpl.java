
package com.joymain.jecs.sys.service.impl;

import java.util.List;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysConfigValueDao;
import com.joymain.jecs.sys.model.SysConfigValue;
import com.joymain.jecs.sys.service.SysConfigValueManager;

public class SysConfigValueManagerImpl extends BaseManager implements SysConfigValueManager {
    private SysConfigValueDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setSysConfigValueDao(SysConfigValueDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.sys.service.SysConfigValueManager#getSysConfigValues(com.joymain.jecs.sys.model.SysConfigValue)
     */
    public List getSysConfigValues(final SysConfigValue sysConfigValue) {
        return dao.getSysConfigValues(sysConfigValue);
    }

    /**
     * @see com.joymain.jecs.sys.service.SysConfigValueManager#getSysConfigValue(String valueId)
     */
    public SysConfigValue getSysConfigValue(final String valueId) {
        return dao.getSysConfigValue(new Long(valueId));
    }

    /**
     * @see com.joymain.jecs.sys.service.SysConfigValueManager#saveSysConfigValue(SysConfigValue sysConfigValue)
     */
    public void saveSysConfigValue(SysConfigValue sysConfigValue) {
        dao.saveSysConfigValue(sysConfigValue);
    }

    /**
     * @see com.joymain.jecs.sys.service.SysConfigValueManager#removeSysConfigValue(String valueId)
     */
    public void removeSysConfigValue(final String valueId) {
        dao.removeSysConfigValue(new Long(valueId));
    }
    
    /**
     * 根据公司编码获取对应的参数集
     * @param companyCode
     * @return List
     */
    public List getSysConfigValuesByCode(final String companyCode){
    	return dao.getSysConfigValuesByCode(companyCode);
    }
    /**
     * 根据公司编码获取对应的参数集(SQL),用于初始化Listener
     * @param companyCode
     * @return
     */
    public List getSysConfigValuesByCodeSQL(final String companyCode){
    	return dao.getSysConfigValuesByCodeSQL(companyCode);
    }
    
    /**
     * 根据键值ID获取对应的参数值,对应所有公司
     * @param keyId
     * @return
     */
    public List getSysConfigValuesAll(final Long keyId){
    	return dao.getSysConfigValuesAll(keyId);
    }
    
    /**
     * 批量保存语言值
     * @param sysConfigValues
     */
    public void saveSysConfigValues(List sysConfigValues){
    	dao.saveSysConfigValues(sysConfigValues);
    }
}
