package com.joymain.jecs.al.dao.hibernate;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.al.dao.AlCharacterCodingDao;
import com.joymain.jecs.al.model.AlCharacterCoding;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;

public class AlCharacterCodingDaoHibernate extends BaseDaoHibernate implements AlCharacterCodingDao {

	/**
	 * @see com.joymain.jecs.al.dao.AlCharacterCodingDao#getAlCharacterCodings(com.joymain.jecs.al.model.AlCharacterCoding)
	 */
	public List getAlCharacterCodings(final AlCharacterCoding alCharacterCoding) {
		return getHibernateTemplate().find("from AlCharacterCoding order by characterCode");
	}

	/**
	 * @see com.joymain.jecs.al.dao.AlCharacterCodingDao#getAlCharacterCoding(Long characterId)
	 */
	public AlCharacterCoding getAlCharacterCoding(final Long characterId) {
		AlCharacterCoding alCharacterCoding = (AlCharacterCoding) getHibernateTemplate().get(AlCharacterCoding.class, characterId);
		if (alCharacterCoding == null) {
			log.warn("uh oh, alCharacterCoding with characterId '" + characterId + "' not found...");
			throw new ObjectRetrievalFailureException(AlCharacterCoding.class, characterId);
		}

		return alCharacterCoding;
	}

	/**
	 * @see com.joymain.jecs.al.dao.AlCharacterCodingDao#saveAlCharacterCoding(AlCharacterCoding alCharacterCoding)
	 */
	public void saveAlCharacterCoding(final AlCharacterCoding alCharacterCoding) {
		getHibernateTemplate().saveOrUpdate(alCharacterCoding);
	}

	/**
	 * @see com.joymain.jecs.al.dao.AlCharacterCodingDao#removeAlCharacterCoding(Long characterId)
	 */
	public void removeAlCharacterCoding(final Long characterId) {
		getHibernateTemplate().delete(getAlCharacterCoding(characterId));
	}

	/**
	 * 根据编码获取对应的语言资料
	 * @param companyCode
	 * @return
	 */
	public AlCharacterCoding getAlCharacterCodingByCode(final String characterCode) {
		return (AlCharacterCoding) this.getObjectByHqlQuery("from AlCharacterCoding where characterCode='" + characterCode + "'");
	}
}
