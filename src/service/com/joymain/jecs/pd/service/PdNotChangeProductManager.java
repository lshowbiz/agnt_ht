
package com.joymain.jecs.pd.service;

import java.util.List;

import org.springframework.validation.BindException;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdNotChangeProduct;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdNotChangeProductManager extends Manager {
    /**
     * Retrieves all of the pdNotChangeProducts
     */
    public List getPdNotChangeProducts(PdNotChangeProduct pdNotChangeProduct);

    /**
     * Gets pdNotChangeProduct's information based on id.
     * @param id the pdNotChangeProduct's id
     * @return pdNotChangeProduct populated pdNotChangeProduct object
     */
    public PdNotChangeProduct getPdNotChangeProduct(final String id);

    /**
     * Saves a pdNotChangeProduct's information
     * @param pdNotChangeProduct the object to be saved
     */
    public void savePdNotChangeProduct(PdNotChangeProduct pdNotChangeProduct);

    /**
     * Removes a pdNotChangeProduct from the database by id
     * @param id the pdNotChangeProduct's id
     */
    public void removePdNotChangeProduct(final String id);
    //added for getPdNotChangeProductsByCrm
    public List getPdNotChangeProductsByCrm(CommonRecord crm, Pager pager);

    /**
     * 不可换商品编辑之前数据合法性校验
     * @author 2016-04-05 fu 
     * @param pdNotChangeProduct
     * @param errors
     * @param defCharacterCoding
     * @return boolean
     */
	public boolean getPdNotChangeProductCheckResutl(PdNotChangeProduct pdNotChangeProduct,BindException errors, String defCharacterCoding);

    /**
     * 根据商品编号获取不可换商品的对象
     * @author fu 2016-09-27
     * @param productNo
     * @return 
     */
	public PdNotChangeProduct getpdNotChangeProductByProductNo(String productNo);
	
}

