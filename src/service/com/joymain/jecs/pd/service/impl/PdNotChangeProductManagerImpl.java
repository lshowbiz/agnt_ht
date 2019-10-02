
package com.joymain.jecs.pd.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;



import com.joymain.jecs.Constants;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdNotChangeProduct;
import com.joymain.jecs.pd.dao.PdNotChangeProductDao;
import com.joymain.jecs.pd.service.PdNotChangeProductManager;
import com.joymain.jecs.pm.dao.JpmProductDao;
import com.joymain.jecs.pm.model.JpmProduct;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
public class PdNotChangeProductManagerImpl extends BaseManager implements PdNotChangeProductManager {
    private PdNotChangeProductDao dao;
    private JpmProductDao jpmProductDao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdNotChangeProductDao(PdNotChangeProductDao dao) {
        this.dao = dao;
    }

    public void setJpmProductDao(JpmProductDao jpmProductDao) {
		this.jpmProductDao = jpmProductDao;
	}

	/**
     * @see com.joymain.jecs.pd.service.PdNotChangeProductManager#getPdNotChangeProducts(com.joymain.jecs.pd.model.PdNotChangeProduct)
     */
    public List getPdNotChangeProducts(final PdNotChangeProduct pdNotChangeProduct) {
        return dao.getPdNotChangeProducts(pdNotChangeProduct);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdNotChangeProductManager#getPdNotChangeProduct(Long id)
     */
    public PdNotChangeProduct getPdNotChangeProduct(final String id) {
        return dao.getPdNotChangeProduct(new Long(id));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdNotChangeProductManager#savePdNotChangeProduct(PdNotChangeProduct pdNotChangeProduct)
     */
    public void savePdNotChangeProduct(PdNotChangeProduct pdNotChangeProduct) {
    	Long id = pdNotChangeProduct.getId();
    	if(null==id){
    		pdNotChangeProduct.setCompanyCode("CN");
    		pdNotChangeProduct.setCreateTime(new Date());
    	}
        dao.savePdNotChangeProduct(pdNotChangeProduct);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdNotChangeProductManager#removePdNotChangeProduct(Long id)
     */
    public void removePdNotChangeProduct(final String id) {
        dao.removePdNotChangeProduct(new Long(id));
    }
    //added for getPdNotChangeProductsByCrm
    public List getPdNotChangeProductsByCrm(CommonRecord crm, Pager pager){
	return dao.getPdNotChangeProductsByCrm(crm,pager);
    }
    
    /**
     * 不可换商品编辑之前数据合法性校验
     * @author 2016-04-05 fu 
     * @param pdNotChangeProduct
     * @param errors
     * @param defCharacterCoding
     * @return boolean
     */
    public boolean getPdNotChangeProductCheckResutl(PdNotChangeProduct pdNotChangeProduct,BindException errors, String defCharacterCoding){
    	boolean result = true;
    	String procuctNo = pdNotChangeProduct.getProductNo();
    	if(StringUtil.isEmpty(procuctNo)){
			errors.rejectValue("productNo", "isNotNull",new Object[] { this.getCharacterValue(defCharacterCoding, "jpmProductSaleNew.productNo") }, this.getCharacterValue(defCharacterCoding, "isNotNull"));
			result = false;
    	}else{
        	JpmProduct jpmProduct = jpmProductDao.getJpmProduct(procuctNo);
        	if(null==jpmProduct){
    			errors.rejectValue("productNo", "pd.notchangeproduct.no",new Object[] { this.getCharacterValue(defCharacterCoding, "jpmProductSaleNew.productNo") }, this.getCharacterValue(defCharacterCoding, "pd.notchangeproduct.no"));
    			result = false;
        	}
    	}
    	return result;
    }
    
    /**
	 * @author gw 2013-09-10
	 * @param characterCoding
	 * @param msgKey
	 * @return String
	 */
	private String getCharacterValue(String characterCoding,String msgKey){
		String characterValue=(String) Constants.localLanguageMap.get(characterCoding).get(msgKey);
		if(!StringUtils.isEmpty(characterValue)){
			return characterValue;
		}else{
			return msgKey;
		}
	}
	
	/**
     * 根据商品编号获取不可换商品的对象
     * @author fu 2016-09-27
     * @param productNo
     * @return 
     */
	public PdNotChangeProduct getpdNotChangeProductByProductNo(String productNo){
		return dao.getpdNotChangeProductByProductNo(productNo);
	}
	
	
}
