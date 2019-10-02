
package com.joymain.jecs.sys.dao;

import java.util.List;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.sys.model.SysConfigValue;

public interface SysConfigValueDao extends Dao {

	//	public List getPiProductsByHql(String hql);

	//	public List getPiProductsBySql(String sql);
    /**
     * Retrieves all of the sysConfigValues
     */
    public List getSysConfigValues(SysConfigValue sysConfigValue);

    /**
     * Gets sysConfigValue's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param valueId the sysConfigValue's valueId
     * @return sysConfigValue populated sysConfigValue object
     */
    public SysConfigValue getSysConfigValue(final Long valueId);

    /**
     * Saves a sysConfigValue's information
     * @param sysConfigValue the object to be saved
     */    
    public void saveSysConfigValue(SysConfigValue sysConfigValue);

    /**
     * Removes a sysConfigValue from the database by valueId
     * @param valueId the sysConfigValue's valueId
     */
    public void removeSysConfigValue(final Long valueId);
    
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

