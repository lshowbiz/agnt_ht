package com.joymain.jecs.al.service;

import java.util.List;

import com.joymain.jecs.al.model.AlCharacterKey;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface AlCharacterKeyManager extends Manager {
	/**
	 * Retrieves all of the alCharacterKeys
	 */
	public List getAlCharacterKeys(AlCharacterKey alCharacterKey);

	/**
	 * Gets alCharacterKey's information based on keyId.
	 * @param keyId the alCharacterKey's keyId
	 * @return alCharacterKey populated alCharacterKey object
	 */
	public AlCharacterKey getAlCharacterKey(final String keyId);

	/**
	 * Saves a alCharacterKey's information
	 * @param alCharacterKey the object to be saved
	 */
	public void saveAlCharacterKey(AlCharacterKey alCharacterKey);

	/**
	 * Removes a alCharacterKey from the database by keyId
	 * @param keyId the alCharacterKey's keyId
	 */
	public void removeAlCharacterKey(final String keyId);

	/**
	 * 根据外部参数分页获取对应的键值列表
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getAlCharacterKeysByPage(CommonRecord crm, Pager pager);
	
	/**
	 * 根据编码键值获取对应的字符键
	 * @param characterKey
	 * @return
	 */
	public AlCharacterKey getAlCharacterKeyByKey(final String characterKey);
	
	/**
	 * 批量保存多个字符键
	 * @param alCharacterKeys
	 */
	public void saveAlCharacterKeys(List alCharacterKeys);
	
	
}
