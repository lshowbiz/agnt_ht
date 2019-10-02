
package com.joymain.jecs.al.dao;

import java.util.List;

import com.joymain.jecs.al.model.AlCharacterType;
import com.joymain.jecs.dao.Dao;

public interface AlCharacterTypeDao extends Dao {

	//	public List getPiProductsByHql(String hql);

	//	public List getPiProductsBySql(String sql);
    /**
     * Retrieves all of the alCharacterTypes
     */
    public List getAlCharacterTypes(AlCharacterType alCharacterType);

    /**
     * Gets alCharacterType's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param typeId the alCharacterType's typeId
     * @return alCharacterType populated alCharacterType object
     */
    public AlCharacterType getAlCharacterType(final Long typeId);

    /**
     * Saves a alCharacterType's information
     * @param alCharacterType the object to be saved
     */    
    public void saveAlCharacterType(AlCharacterType alCharacterType);

    /**
     * Removes a alCharacterType from the database by typeId
     * @param typeId the alCharacterType's typeId
     */
    public void removeAlCharacterType(final Long typeId);
    
    /**
     * 获取分类下的子分类列表
     * @param typeId
     * @return
     */
    public List getDirectChildAlCharacterTypes(final String typeId);
}

