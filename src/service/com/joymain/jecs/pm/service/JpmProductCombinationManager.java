
package com.joymain.jecs.pm.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pm.model.JpmProductCombination;
import com.joymain.jecs.pm.dao.JpmProductCombinationDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpmProductCombinationManager extends Manager {
    /**
     * Retrieves all of the jpmProductCombinations
     */
    public List getJpmProductCombinations(JpmProductCombination jpmProductCombination);

    /**
     * Gets jpmProductCombination's information based on jpcId.
     * @param jpcId the jpmProductCombination's jpcId
     * @return jpmProductCombination populated jpmProductCombination object
     */
    public JpmProductCombination getJpmProductCombination(final String jpcId);

    /**
     * Saves a jpmProductCombination's information
     * @param jpmProductCombination the object to be saved
     */
    public void saveJpmProductCombination(JpmProductCombination jpmProductCombination);

    /**
     * Removes a jpmProductCombination from the database by jpcId
     * @param jpcId the jpmProductCombination's jpcId
     */
    public void removeJpmProductCombination(final String jpcId);
    //added for getJpmProductCombinationsByCrm
    public List getJpmProductCombinationsByCrm(CommonRecord crm, Pager pager);

	public boolean existCombinationProduct(String subProductNo, String productNo);

	/**
	 * 查询组合商品下所有的子商品及数量
	 * @author fx 2015-08-11
	 * @param productNo
	 * @return list
	 */
	public List getCombinationProduct(String productOrderDetail);
}

