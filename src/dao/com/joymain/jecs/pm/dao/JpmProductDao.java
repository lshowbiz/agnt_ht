
package com.joymain.jecs.pm.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pm.model.JpmProduct;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmProductDao extends Dao {

    /**
     * Retrieves all of the jpmProducts
     */
    public List getJpmProducts(JpmProduct jpmProduct);

    /**
     * Gets jpmProduct's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param productNo the jpmProduct's productNo
     * @return jpmProduct populated jpmProduct object
     */
    public JpmProduct getJpmProduct(final String productNo);

    /**
     * Saves a jpmProduct's information
     * @param jpmProduct the object to be saved
     */    
    public void saveJpmProduct(JpmProduct jpmProduct);

    /**
     * Removes a jpmProduct from the database by productNo
     * @param productNo the jpmProduct's productNo
     */
    public void removeJpmProduct(final String productNo);
    //added for getJpmProductsByCrm
    public List getJpmProductsByCrm(CommonRecord crm, Pager pager);

    //added for getJpmProductsByCrm
    public List getJpmProductsByCrm2(CommonRecord crm, Pager pager);

    /**
	 * 判断商品是否是组合商品
	 * @author fx 2015-08-11
	 * @param productNo
	 * @return list
	 */
	public boolean getCombinationJudgmentResult(String productNo);

	
}

