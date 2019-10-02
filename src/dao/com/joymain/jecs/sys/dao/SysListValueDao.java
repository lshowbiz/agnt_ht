
package com.joymain.jecs.sys.dao;

import java.util.List;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.sys.model.SysListValue;

public interface SysListValueDao extends Dao {

	//	public List getPiProductsByHql(String hql);

	//	public List getPiProductsBySql(String sql);
    /**
     * Retrieves all of the sysListValues
     */
    public List getSysListValues(SysListValue sysListValue);

    /**
     * Gets sysListValue's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param valueId the sysListValue's valueId
     * @return sysListValue populated sysListValue object
     */
    public SysListValue getSysListValue(final Long valueId);

    /**
     * Saves a sysListValue's information
     * @param sysListValue the object to be saved
     */    
    public void saveSysListValue(SysListValue sysListValue);

    /**
     * Removes a sysListValue from the database by valueId
     * @param valueId the sysListValue's valueId
     */
    public void removeSysListValue(final Long valueId);
    
    /**
	 * 根据List值编码获取对应的List值
	 * @param keyId
	 * @param listCode
	 * @return
	 */
	public SysListValue getSysListValueByCode(final String keyId, final String valueCode);
	
	/**
	 * 根据键值获取对应的LIST值列表
	 * @param keyId
	 * @return
	 */
	public List getSysListValuesByKeyId(String keyId);
	
	/**
	 * 根据List编码获取对应的List值列表
	 * @param listCode
	 * @param companyCode
	 * @return
	 */
	public List getSysListValuesByCode(final String listCode, final String companyCode);
}

