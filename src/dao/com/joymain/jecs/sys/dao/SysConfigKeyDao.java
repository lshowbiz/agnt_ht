
package com.joymain.jecs.sys.dao;

import java.util.List;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.sys.model.SysConfigKey;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysConfigKeyDao extends Dao {

	//	public List getPiProductsByHql(String hql);

	//	public List getPiProductsBySql(String sql);
    /**
     * Retrieves all of the sysConfigKeys
     */
    public List getSysConfigKeys(SysConfigKey sysConfigKey);

    /**
     * Gets sysConfigKey's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param keyId the sysConfigKey's keyId
     * @return sysConfigKey populated sysConfigKey object
     */
    public SysConfigKey getSysConfigKey(final Long keyId);

    /**
     * Saves a sysConfigKey's information
     * @param sysConfigKey the object to be saved
     */    
    public void saveSysConfigKey(SysConfigKey sysConfigKey);

    /**
     * Removes a sysConfigKey from the database by keyId
     * @param keyId the sysConfigKey's keyId
     */
    public void removeSysConfigKey(final Long keyId);
    
    /**
	 * 根据外部参数分页获取对应的List键值列表
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getSysConfigKeysByPage(CommonRecord crm, Pager pager);
	
	/**
	 * 根据参数键值获取对应的参数
	 * @param listCode
	 * @return
	 */
	public SysConfigKey getSysConfigKeyByCode(final String configCode);
}

