
package com.joymain.jecs.al.service.impl;

import java.util.List;

import com.joymain.jecs.al.dao.AlCharacterTypeDao;
import com.joymain.jecs.al.model.AlCharacterType;
import com.joymain.jecs.al.service.AlCharacterTypeManager;
import com.joymain.jecs.service.impl.BaseManager;

public class AlCharacterTypeManagerImpl extends BaseManager implements AlCharacterTypeManager {
    private AlCharacterTypeDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setAlCharacterTypeDao(AlCharacterTypeDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.al.service.AlCharacterTypeManager#getAlCharacterTypes(com.joymain.jecs.al.model.AlCharacterType)
     */
    public List getAlCharacterTypes(final AlCharacterType alCharacterType) {
        return dao.getAlCharacterTypes(alCharacterType);
    }

    /**
     * @see com.joymain.jecs.al.service.AlCharacterTypeManager#getAlCharacterType(String typeId)
     */
    public AlCharacterType getAlCharacterType(final String typeId) {
        return dao.getAlCharacterType(new Long(typeId));
    }

    /**
     * @see com.joymain.jecs.al.service.AlCharacterTypeManager#saveAlCharacterType(AlCharacterType alCharacterType)
     */
    public void saveAlCharacterType(AlCharacterType alCharacterType) {
        dao.saveAlCharacterType(alCharacterType);
    }

    /**
     * @see com.joymain.jecs.al.service.AlCharacterTypeManager#removeAlCharacterType(String typeId)
     */
    public void removeAlCharacterType(final String typeId) {
        dao.removeAlCharacterType(new Long(typeId));
    }
    
    /**
     * 获取分类下的子分类列表
     * @param typeId
     * @return
     */
    public List getDirectChildAlCharacterTypes(final String typeId){
    	return dao.getDirectChildAlCharacterTypes(typeId);
    }
}
