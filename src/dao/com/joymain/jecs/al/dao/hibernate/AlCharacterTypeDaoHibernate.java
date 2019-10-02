
package com.joymain.jecs.al.dao.hibernate;

import java.util.List;

import com.joymain.jecs.al.dao.AlCharacterTypeDao;
import com.joymain.jecs.al.model.AlCharacterType;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;

public class AlCharacterTypeDaoHibernate extends BaseDaoHibernate implements AlCharacterTypeDao {

    /**
     * @see com.joymain.jecs.al.dao.AlCharacterTypeDao#getAlCharacterTypes(com.joymain.jecs.al.model.AlCharacterType)
     */
    public List getAlCharacterTypes(final AlCharacterType alCharacterType) {
        return getHibernateTemplate().find("from AlCharacterType order by typeId");
    }

    /**
     * @see com.joymain.jecs.al.dao.AlCharacterTypeDao#getAlCharacterType(Long typeId)
     */
    public AlCharacterType getAlCharacterType(final Long typeId) {
        AlCharacterType alCharacterType = (AlCharacterType) getHibernateTemplate().get(AlCharacterType.class, typeId);

        return alCharacterType;
    }

    /**
     * @see com.joymain.jecs.al.dao.AlCharacterTypeDao#saveAlCharacterType(AlCharacterType alCharacterType)
     */    
    public void saveAlCharacterType(final AlCharacterType alCharacterType) {
        getHibernateTemplate().saveOrUpdate(alCharacterType);
    }

    /**
     * @see com.joymain.jecs.al.dao.AlCharacterTypeDao#removeAlCharacterType(Long typeId)
     */
    public void removeAlCharacterType(final Long typeId) {
    	this.jdbcTemplate.execute("delete from jal_character_value where key_id in (select key_id from jal_character_key where type_id='"+typeId+"')");
    	this.jdbcTemplate.execute("delete from jal_character_key where type_id='"+typeId+"'");
        getHibernateTemplate().delete(getAlCharacterType(typeId));
    }
    
    /**
     * 获取分类下的子分类列表
     * @param typeId
     * @return
     */
    public List getDirectChildAlCharacterTypes(final String typeId){
    	return this.getHibernateTemplate().find("from AlCharacterType where parentId='"+typeId+"'");
    }
}
