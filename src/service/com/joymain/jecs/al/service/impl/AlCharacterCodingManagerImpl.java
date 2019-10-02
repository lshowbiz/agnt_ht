package com.joymain.jecs.al.service.impl;

import java.util.List;

import com.joymain.jecs.al.dao.AlCharacterCodingDao;
import com.joymain.jecs.al.model.AlCharacterCoding;
import com.joymain.jecs.al.service.AlCharacterCodingManager;
import com.joymain.jecs.service.impl.BaseManager;

public class AlCharacterCodingManagerImpl extends BaseManager implements AlCharacterCodingManager {
	private AlCharacterCodingDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * @param dao
	 */
	public void setAlCharacterCodingDao(AlCharacterCodingDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.al.service.AlCharacterCodingManager#getAlCharacterCodings(com.joymain.jecs.al.model.AlCharacterCoding)
	 */
	public List getAlCharacterCodings(final AlCharacterCoding alCharacterCoding) {
		return dao.getAlCharacterCodings(alCharacterCoding);
	}

	/**
	 * @see com.joymain.jecs.al.service.AlCharacterCodingManager#getAlCharacterCoding(String characterId)
	 */
	public AlCharacterCoding getAlCharacterCoding(final String characterId) {
		return dao.getAlCharacterCoding(new Long(characterId));
	}

	/**
	 * @see com.joymain.jecs.al.service.AlCharacterCodingManager#saveAlCharacterCoding(AlCharacterCoding alCharacterCoding)
	 */
	public void saveAlCharacterCoding(AlCharacterCoding alCharacterCoding) {
		dao.saveAlCharacterCoding(alCharacterCoding);
	}

	/**
	 * @see com.joymain.jecs.al.service.AlCharacterCodingManager#removeAlCharacterCoding(String characterId)
	 */
	public void removeAlCharacterCoding(final String characterId) {
		dao.removeAlCharacterCoding(new Long(characterId));
	}

	/**
	 * 根据编码获取对应的语言资料
	 * @param companyCode
	 * @return
	 */
	public AlCharacterCoding getAlCharacterCodingByCode(final String characterCode) {
		return dao.getAlCharacterCodingByCode(characterCode);
	}
}
