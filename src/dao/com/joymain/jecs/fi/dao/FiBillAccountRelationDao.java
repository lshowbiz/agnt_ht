
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiBillAccountRelation;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiBillAccountRelationDao extends Dao {

    /**
     * Retrieves all of the fiBillAccountRelations
     */
    public List getFiBillAccountRelations(FiBillAccountRelation fiBillAccountRelation);

    /**
     * Gets fiBillAccountRelation's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param relationId the fiBillAccountRelation's relationId
     * @return fiBillAccountRelation populated fiBillAccountRelation object
     */
    public FiBillAccountRelation getFiBillAccountRelation(final Long relationId);

    /**
     * Saves a fiBillAccountRelation's information
     * @param fiBillAccountRelation the object to be saved
     */    
    public void saveFiBillAccountRelation(FiBillAccountRelation fiBillAccountRelation);

    /**
     * Removes a fiBillAccountRelation from the database by relationId
     * @param relationId the fiBillAccountRelation's relationId
     */
    public void removeFiBillAccountRelation(final Long relationId);
    //added for getFiBillAccountRelationsByCrm
    public List getFiBillAccountRelationsByCrm(CommonRecord crm, Pager pager);
    
    /**
	 * 根据省市区三个条件查询商户list,用于验证是否重叠
	 * @param mfiBillAccountRelation
	 * @return 列表
	 */
    public List getFiBillAccountRelationsByConditions(FiBillAccountRelation fiBillAccountRelation);
    
    /**
	 * 根据条件匹配一个对应的商户,用于快钱支付时候选择商户号
	 * @param mfiBillAccountRelation
	 * @return 商户对象
	 */
    public FiBillAccountRelation getFiBillAccountRelationByConditions(FiBillAccountRelation mfiBillAccountRelation);
    
    /**
     * 根据商户号查询对应关系
     * @param billAccountCode
     * @return
     */
    public List getFiBillAccountRelationsByBillAccountCode(String billAccountCode);
}

