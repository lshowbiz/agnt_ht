
package com.joymain.jecs.al.service;

import java.util.List;

import com.joymain.jecs.al.model.AlCharacterType;
import com.joymain.jecs.service.Manager;

public interface AlCharacterTypeManager extends Manager {
    /**
     * Retrieves all of the alCharacterTypes
     */
    public List getAlCharacterTypes(AlCharacterType alCharacterType);

    /**
     * Gets alCharacterType's information based on typeId.
     * @param typeId the alCharacterType's typeId
     * @return alCharacterType populated alCharacterType object
     */
    public AlCharacterType getAlCharacterType(final String typeId);

    /**
     * Saves a alCharacterType's information
     * @param alCharacterType the object to be saved
     */
    public void saveAlCharacterType(AlCharacterType alCharacterType);

    /**
     * Removes a alCharacterType from the database by typeId
     * @param typeId the alCharacterType's typeId
     */
    public void removeAlCharacterType(final String typeId);
    
    /**
     * 获取分类下的子分类列表
     * @param typeId
     * @return
     */
    public List getDirectChildAlCharacterTypes(final String typeId);
}

