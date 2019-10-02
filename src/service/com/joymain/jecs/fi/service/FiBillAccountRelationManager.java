
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.FiBillAccountRelation;
import com.joymain.jecs.fi.dao.FiBillAccountRelationDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface FiBillAccountRelationManager extends Manager {
    /**
     * Retrieves all of the fiBillAccountRelations
     */
    public List getFiBillAccountRelations(FiBillAccountRelation fiBillAccountRelation);

    /**
     * Gets fiBillAccountRelation's information based on relationId.
     * @param relationId the fiBillAccountRelation's relationId
     * @return fiBillAccountRelation populated fiBillAccountRelation object
     */
    public FiBillAccountRelation getFiBillAccountRelation(final String relationId);

    /**
     * Saves a fiBillAccountRelation's information
     * @param fiBillAccountRelation the object to be saved
     */
    public void saveFiBillAccountRelation(FiBillAccountRelation fiBillAccountRelation);

    /**
     * Removes a fiBillAccountRelation from the database by relationId
     * @param relationId the fiBillAccountRelation's relationId
     */
    public void removeFiBillAccountRelation(final String relationId);
    //added for getFiBillAccountRelationsByCrm
    public List getFiBillAccountRelationsByCrm(CommonRecord crm, Pager pager);
    
    /**
	 * 根据省市区三个条件查询商户list,用于验证是否重叠
	 * @param mfiBillAccountRelation
	 * @return 列表
	 */
    public List getFiBillAccountRelationsByConditions(FiBillAccountRelation fiBillAccountRelation);
    
    /**
     * 根据商户号查询对应关系
     * @param billAccountCode
     * @return
     */
    public List getFiBillAccountRelationsByBillAccountCode(String billAccountCode);
}

