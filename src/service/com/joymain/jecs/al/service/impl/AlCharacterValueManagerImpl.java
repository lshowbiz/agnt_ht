
package com.joymain.jecs.al.service.impl;

import java.util.List;

import com.joymain.jecs.al.dao.AlCharacterValueDao;
import com.joymain.jecs.al.model.AlCharacterValue;
import com.joymain.jecs.al.service.AlCharacterValueManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class AlCharacterValueManagerImpl extends BaseManager implements AlCharacterValueManager {
    private AlCharacterValueDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setAlCharacterValueDao(AlCharacterValueDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.al.service.AlCharacterValueManager#getAlCharacterValues(com.joymain.jecs.al.model.AlCharacterValue)
     */
    public List getAlCharacterValues(final AlCharacterValue alCharacterValue) {
        return dao.getAlCharacterValues(alCharacterValue);
    }

    /**
     * @see com.joymain.jecs.al.service.AlCharacterValueManager#getAlCharacterValue(String valueId)
     */
    public AlCharacterValue getAlCharacterValue(final String valueId) {
        return dao.getAlCharacterValue(new Long(valueId));
    }

    /**
     * @see com.joymain.jecs.al.service.AlCharacterValueManager#saveAlCharacterValue(AlCharacterValue alCharacterValue)
     */
    public void saveAlCharacterValue(AlCharacterValue alCharacterValue) {
        dao.saveAlCharacterValue(alCharacterValue);
    }

    /**
     * @see com.joymain.jecs.al.service.AlCharacterValueManager#removeAlCharacterValue(String valueId)
     */
    public void removeAlCharacterValue(final String valueId) {
        dao.removeAlCharacterValue(new Long(valueId));
    }
    
    /**
     * 根据键值ID及字符编码获取对应的语言值
     * @param keyId
     * @param characterCode
     * @return
     */
    public AlCharacterValue getAlCharacterValue(final Long keyId, final String characterCode){
    	return dao.getAlCharacterValue(keyId, characterCode);
    }
    
    /**
     * 根据键值ID及字符编码获取对应的语言值
     * @param characterKey
     * @param characterCode
     * @return
     */
    public AlCharacterValue getAlCharacterValue(final String characterKey, final String characterCode){
    	return dao.getAlCharacterValue(characterKey, characterCode);
    }
    
    /**
     * 根据字符编码获取对应的语言集
     * @param characterCode
     * @return List
     */
    public List getAlCharacterValuesByCode(final String characterCode){
    	return dao.getAlCharacterValuesByCode(characterCode);
    }
    
    /**
     * 根据字符编码获取对应的语言集(SQL),用于初始化Listener
     * @param characterCode
     * @return
     */
    public List getAlCharacterValuesByCodeSQL(final String characterCode){
    	return dao.getAlCharacterValuesByCodeSQL(characterCode);
    }
    
    /**
     * 根据键值ID获取对应的语言值,对应所有字符编码
     * @param keyId
     * @param userCode
     * @return
     */
    public List getAlCharacterValuesAll(final Long keyId, final String userCode){
    	return dao.getAlCharacterValuesAll(keyId, userCode);
    }
    
    /**
     * 根据键值ID获取对应的语言值,对应所有字符编码
     * @param keyId
     * @return
     */
    public List getAlCharacterValuesAll(final Long keyId){
    	return dao.getAlCharacterValuesAll(keyId, null);
    }
    
    /**
     * 批量保存语言值
     * @param alCharacterValues
     */
    public void saveAlCharacterValues(List alCharacterValues){
    	dao.saveAlCharacterValues(alCharacterValues);
    }
    
    /**
     * 删除某字符集下所有的翻译
     * @param characterCode
     */
    public void removeAlCharacterValues(final String characterCode){
    	dao.removeAlCharacterValues(characterCode);
    }

	public List getAlCharacterValuesByTypeSQL(CommonRecord crm, Pager pager) {
		return dao.getAlCharacterValuesByTypeSQL(crm, pager);
	}
}