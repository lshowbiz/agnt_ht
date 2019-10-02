
package com.joymain.jecs.pm.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pm.model.JpmProduct;
import com.joymain.jecs.pm.dao.JpmProductDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpmProductManager extends Manager {
	public Boolean checkProductNo(String productNo);
    /**
     * Retrieves all of the jpmProducts
     */
    public List getJpmProducts(JpmProduct jpmProduct);

    /**
     * Gets jpmProduct's information based on productNo.
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
    
    //added for getJpmProductsByCrm2 
    public List getJpmProductsByCrm2(CommonRecord crm, Pager pager);
    
    /**
	 * 判断商品是否是组合商品
	 * @author fx 2015-04-29
	 * @param productNo
	 * @return list
	 */
	public boolean getCombinationJudgmentResult(String productOrderDetail);
	
}

