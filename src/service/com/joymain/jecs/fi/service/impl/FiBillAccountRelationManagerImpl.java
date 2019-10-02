
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.FiBillAccountRelation;
import com.joymain.jecs.fi.dao.FiBillAccountRelationDao;
import com.joymain.jecs.fi.service.FiBillAccountRelationManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class FiBillAccountRelationManagerImpl extends BaseManager implements FiBillAccountRelationManager {
    private FiBillAccountRelationDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiBillAccountRelationDao(FiBillAccountRelationDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBillAccountRelationManager#getFiBillAccountRelations(com.joymain.jecs.fi.model.FiBillAccountRelation)
     */
    public List getFiBillAccountRelations(final FiBillAccountRelation fiBillAccountRelation) {
        return dao.getFiBillAccountRelations(fiBillAccountRelation);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBillAccountRelationManager#getFiBillAccountRelation(String relationId)
     */
    public FiBillAccountRelation getFiBillAccountRelation(final String relationId) {
        return dao.getFiBillAccountRelation(new Long(relationId));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBillAccountRelationManager#saveFiBillAccountRelation(FiBillAccountRelation fiBillAccountRelation)
     */
    public void saveFiBillAccountRelation(FiBillAccountRelation fiBillAccountRelation) {
        dao.saveFiBillAccountRelation(fiBillAccountRelation);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBillAccountRelationManager#removeFiBillAccountRelation(String relationId)
     */
    public void removeFiBillAccountRelation(final String relationId) {
        dao.removeFiBillAccountRelation(new Long(relationId));
    }
    //added for getFiBillAccountRelationsByCrm
    public List getFiBillAccountRelationsByCrm(CommonRecord crm, Pager pager){
    	return dao.getFiBillAccountRelationsByCrm(crm,pager);
    }

    /**
	 * 根据省市区三个条件查询商户list,用于验证是否重叠
	 * @param mfiBillAccountRelation
	 * @return 列表
	 */
	@Override
	public List getFiBillAccountRelationsByConditions(
			FiBillAccountRelation fiBillAccountRelation) {
		
		return dao.getFiBillAccountRelationsByConditions(fiBillAccountRelation);
	}
	
	/**
     * 根据商户号查询对应关系
     * @param billAccountCode
     * @return
     */
	@Override
	public List getFiBillAccountRelationsByBillAccountCode(String billAccountCode){
		
		return dao.getFiBillAccountRelationsByBillAccountCode(billAccountCode);
	}
}
