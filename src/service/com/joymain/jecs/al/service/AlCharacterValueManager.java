package com.joymain.jecs.al.service;

import java.util.List;

import com.joymain.jecs.al.model.AlCharacterValue;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface AlCharacterValueManager extends Manager {
	/**
	 * Retrieves all of the alCharacterValues
	 */
	public List getAlCharacterValues(AlCharacterValue alCharacterValue);

	/**
	 * Gets alCharacterValue's information based on valueId.
	 * @param valueId the alCharacterValue's valueId
	 * @return alCharacterValue populated alCharacterValue object
	 */
	public AlCharacterValue getAlCharacterValue(final String valueId);

	/**
	 * Saves a alCharacterValue's information
	 * @param alCharacterValue the object to be saved
	 */
	public void saveAlCharacterValue(AlCharacterValue alCharacterValue);

	/**
	 * Removes a alCharacterValue from the database by valueId
	 * @param valueId the alCharacterValue's valueId
	 */
	public void removeAlCharacterValue(final String valueId);

	/**
	 * 根据键值ID及字符编码获取对应的语言值
	 * @param keyId
	 * @param characterCode
	 * @return
	 */
	public AlCharacterValue getAlCharacterValue(final Long keyId, final String characterCode);

	/**
	 * 根据键值ID及字符编码获取对应的语言值
	 * @param characterKey
	 * @param characterCode
	 * @return
	 */
	public AlCharacterValue getAlCharacterValue(final String characterKey, final String characterCode);
	
	/**
     * 根据字符编码获取对应的语言集
     * @param characterCode
     * @return List
     */
    public List getAlCharacterValuesByCode(final String characterCode);
    
    /**
     * 根据字符编码获取对应的语言集(SQL),用于初始化Listener
     * @param characterCode
     * @return
     */
    public List getAlCharacterValuesByCodeSQL(final String characterCode);
    
    /**
     * 根据键值ID获取对应的语言值,对应所有字符编码
     * @param keyId
     * @param userCode
     * @return
     */
    public List getAlCharacterValuesAll(final Long keyId, final String userCode);
    
    /**
     * 根据键值ID获取对应的语言值,对应所有字符编码
     * @param keyId
     * @return
     */
    public List getAlCharacterValuesAll(final Long keyId);
    
    /**
     * 批量保存语言值
     * @param alCharacterValues
     */
    public void saveAlCharacterValues(List alCharacterValues);
    
    /**
     * 删除某字符集下所有的翻译
     * @param characterCode
     */
    public void removeAlCharacterValues(final String characterCode);
    public List getAlCharacterValuesByTypeSQL(CommonRecord crm, Pager pager);
}