package com.joymain.jecs.al.dao;

import java.util.List;

import com.joymain.jecs.al.model.AlCharacterCoding;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface AlCharacterCodingDao extends Dao {

	/**
	 * Retrieves all of the alCharacterCodings
	 */
	public List getAlCharacterCodings(AlCharacterCoding alCharacterCoding);

	/**
	 * Gets alCharacterCoding's information based on primary key. An ObjectRetrievalFailureException Runtime Exception is thrown if nothing is found.
	 * 
	 * @param characterId the alCharacterCoding's characterId
	 * @return alCharacterCoding populated alCharacterCoding object
	 */
	public AlCharacterCoding getAlCharacterCoding(final Long characterId);

	/**
	 * Saves a alCharacterCoding's information
	 * @param alCharacterCoding the object to be saved
	 */
	public void saveAlCharacterCoding(AlCharacterCoding alCharacterCoding);

	/**
	 * Removes a alCharacterCoding from the database by characterId
	 * @param characterId the alCharacterCoding's characterId
	 */
	public void removeAlCharacterCoding(final Long characterId);

	/**
	 * 根据编码获取对应的语言资料
	 * @param companyCode
	 * @return
	 */
	public AlCharacterCoding getAlCharacterCodingByCode(final String characterCode);
}
