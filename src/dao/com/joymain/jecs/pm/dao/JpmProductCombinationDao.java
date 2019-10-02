
package com.joymain.jecs.pm.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pm.model.JpmProductCombination;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmProductCombinationDao extends Dao {

    /**
     * Retrieves all of the jpmProductCombinations
     */
    public List getJpmProductCombinations(JpmProductCombination jpmProductCombination);

    /**
     * Gets jpmProductCombination's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param jpcId the jpmProductCombination's jpcId
     * @return jpmProductCombination populated jpmProductCombination object
     */
    public JpmProductCombination getJpmProductCombination(final Long jpcId);

    /**
     * Saves a jpmProductCombination's information
     * @param jpmProductCombination the object to be saved
     */    
    public void saveJpmProductCombination(JpmProductCombination jpmProductCombination);

    /**
     * Removes a jpmProductCombination from the database by jpcId
     * @param jpcId the jpmProductCombination's jpcId
     */
    public void removeJpmProductCombination(final Long jpcId);
    //added for getJpmProductCombinationsByCrm
    public List getJpmProductCombinationsByCrm(CommonRecord crm, Pager pager);

	public List getJpmProductCombinations(String subProductNo,String productNo);

	/**
	 * 查询组合商品下所有的子商品及数量
	 * @author fx 2015-08-11
	 * @param productNo
	 * @return list
	 */
	public List getCombinationProduct(String productNo);
	
	//add by lihao for BUG #3047 
	public List getJpmProductCombinationsByCrmSql(CommonRecord crm, Pager pager);
}

