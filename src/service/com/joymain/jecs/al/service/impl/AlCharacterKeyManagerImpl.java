
package com.joymain.jecs.al.service.impl;

import java.util.List;

import com.joymain.jecs.al.dao.AlCharacterKeyDao;
import com.joymain.jecs.al.model.AlCharacterKey;
import com.joymain.jecs.al.service.AlCharacterKeyManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class AlCharacterKeyManagerImpl extends BaseManager implements AlCharacterKeyManager {
    

	private AlCharacterKeyDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setAlCharacterKeyDao(AlCharacterKeyDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.al.service.AlCharacterKeyManager#getAlCharacterKeys(com.joymain.jecs.al.model.AlCharacterKey)
     */
    public List getAlCharacterKeys(final AlCharacterKey alCharacterKey) {
        return dao.getAlCharacterKeys(alCharacterKey);
    }

    /**
     * @see com.joymain.jecs.al.service.AlCharacterKeyManager#getAlCharacterKey(String keyId)
     */
    public AlCharacterKey getAlCharacterKey(final String keyId) {
        return dao.getAlCharacterKey(new Long(keyId));
    }

    /**
     * @see com.joymain.jecs.al.service.AlCharacterKeyManager#saveAlCharacterKey(AlCharacterKey alCharacterKey)
     */
    public void saveAlCharacterKey(AlCharacterKey alCharacterKey) {
        dao.saveAlCharacterKey(alCharacterKey);
    }

    /**
     * @see com.joymain.jecs.al.service.AlCharacterKeyManager#removeAlCharacterKey(String keyId)
     */
    public void removeAlCharacterKey(final String keyId) {
        dao.removeAlCharacterKey(new Long(keyId));
    }
    
    /**
	 * 根据外部参数分页获取对应的键值列表
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getAlCharacterKeysByPage(CommonRecord crm, Pager pager) {
    	return dao.getAlCharacterKeysByPage(crm, pager);
    }
	
	/**
	 * 根据编码键值获取对应的字符键
	 * @param characterKey
	 * @return
	 */
	public AlCharacterKey getAlCharacterKeyByKey(final String characterKey){
		return dao.getAlCharacterKeyByKey(characterKey);
	}
	
	/**
	 * 批量保存多个字符键
	 * @param alCharacterKeys
	 */
	public void saveAlCharacterKeys(List alCharacterKeys){
		dao.saveAlCharacterKeys(alCharacterKeys);
	}
}