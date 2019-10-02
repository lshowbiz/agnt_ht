
package com.joymain.jecs.sys.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysConfigValue;

public interface SysConfigValueManager extends Manager {
    /**
     * Retrieves all of the sysConfigValues
     */
    public List getSysConfigValues(SysConfigValue sysConfigValue);

    /**
     * Gets sysConfigValue's information based on valueId.
     * @param valueId the sysConfigValue's valueId
     * @return sysConfigValue populated sysConfigValue object
     */
    public SysConfigValue getSysConfigValue(final String valueId);

    /**
     * Saves a sysConfigValue's information
     * @param sysConfigValue the object to be saved
     */
    public void saveSysConfigValue(SysConfigValue sysConfigValue);

    /**
     * Removes a sysConfigValue from the database by valueId
     * @param valueId the sysConfigValue's valueId
     */
    public void removeSysConfigValue(final String valueId);
    
    /**
     * 根据公司编码获取对应的参数集
     * @param companyCode
     * @return List
     */
    public List getSysConfigValuesByCode(final String companyCode);
    
    /**
     * 根据公司编码获取对应的参数集(SQL),用于初始化Listener
     * @param companyCode
     * @return
     */
    public List getSysConfigValuesByCodeSQL(final String companyCode);
    
    /**
     * 根据键值ID获取对应的参数值,对应所有公司
     * @param keyId
     * @return
     */
    public List getSysConfigValuesAll(final Long keyId);
    
    /**
     * 批量保存语言值
     * @param sysConfigValues
     */
    public void saveSysConfigValues(List sysConfigValues);
}