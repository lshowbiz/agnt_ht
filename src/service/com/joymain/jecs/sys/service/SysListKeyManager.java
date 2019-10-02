
package com.joymain.jecs.sys.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysListKey;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysListKeyManager extends Manager {
    /**
     * Retrieves all of the sysListKeys
     */
    public List getSysListKeys(SysListKey sysListKey);

    /**
     * Gets sysListKey's information based on keyId.
     * @param keyId the sysListKey's keyId
     * @return sysListKey populated sysListKey object
     */
    public SysListKey getSysListKey(final String keyId);

    /**
     * Saves a sysListKey's information
     * @param sysListKey the object to be saved
     */
    public void saveSysListKey(SysListKey sysListKey);

    /**
     * Removes a sysListKey from the database by keyId
     * @param keyId the sysListKey's keyId
     */
    public void removeSysListKey(final String keyId);
    
    /**
	 * 根据外部参数分页获取对应的List键值列表
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getSysListKeysByPage(CommonRecord crm, Pager pager);
	
	/**
	 * 根据编码键值获取对应的字符键
	 * @param listCode
	 * @return
	 */
	public SysListKey getSysListKeyByCode(final String listCode);
	
	/**
	 * 根据SQL获取对应的LIST键及值
	 * @return
	 */
	public List getSysListBySQL();
}

