
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdNotChangeProduct;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdNotChangeProductDao extends Dao {

    /**
     * Retrieves all of the pdNotChangeProducts
     */
    public List getPdNotChangeProducts(PdNotChangeProduct pdNotChangeProduct);

    /**
     * Gets pdNotChangeProduct's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the pdNotChangeProduct's id
     * @return pdNotChangeProduct populated pdNotChangeProduct object
     */
    public PdNotChangeProduct getPdNotChangeProduct(final Long id);

    /**
     * Saves a pdNotChangeProduct's information
     * @param pdNotChangeProduct the object to be saved
     */    
    public void savePdNotChangeProduct(PdNotChangeProduct pdNotChangeProduct);

    /**
     * Removes a pdNotChangeProduct from the database by id
     * @param id the pdNotChangeProduct's id
     */
    public void removePdNotChangeProduct(final Long id);
    //added for getPdNotChangeProductsByCrm
    public List getPdNotChangeProductsByCrm(CommonRecord crm, Pager pager);
    
    /**
     * 根据商品编号获取不可换商品的对象
     * @author fu 2016-09-27
     * @param productNo
     * @return 
     */
	public PdNotChangeProduct getpdNotChangeProductByProductNo(String productNo);
}

