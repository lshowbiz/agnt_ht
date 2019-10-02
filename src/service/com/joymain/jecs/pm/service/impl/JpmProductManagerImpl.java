
package com.joymain.jecs.pm.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pm.model.JpmProduct;
import com.joymain.jecs.pm.dao.JpmProductDao;
import com.joymain.jecs.pm.service.JpmProductManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
 
public class JpmProductManagerImpl extends BaseManager implements JpmProductManager {
    public Boolean checkProductNo(String productNo) {
		// TODO Auto-generated method stub
    	JpmProduct pmProduct = dao.getJpmProduct(productNo);
		if (pmProduct == null)
			return false;
		else
			return true;
		
	}
	private JpmProductDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpmProductDao(JpmProductDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductManager#getJpmProducts(com.joymain.jecs.pm.model.JpmProduct)
     */
    public List getJpmProducts(final JpmProduct jpmProduct) {
        return dao.getJpmProducts(jpmProduct);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductManager#getJpmProduct(String productNo)
     */
    public JpmProduct getJpmProduct(final String productNo) {
        return dao.getJpmProduct(new String(productNo));
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductManager#saveJpmProduct(JpmProduct jpmProduct)
     */
    public void saveJpmProduct(JpmProduct jpmProduct) {
        dao.saveJpmProduct(jpmProduct);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductManager#removeJpmProduct(String productNo)
     */
    public void removeJpmProduct(final String productNo) {
        dao.removeJpmProduct(new String(productNo));
    }
    //added for getJpmProductsByCrm
    public List getJpmProductsByCrm(CommonRecord crm, Pager pager){
	return dao.getJpmProductsByCrm(crm,pager);
    }
    
    //added for getJpmProductsByCrm
    public List getJpmProductsByCrm2(CommonRecord crm, Pager pager){
	return dao.getJpmProductsByCrm2(crm,pager);
    }

    /**
	 * 判断商品是否是组合商品
	 * @author fx 2015-08-11
	 * @param productNo
	 * @return list
	 */
	public boolean getCombinationJudgmentResult(String productNo) {
		return dao.getCombinationJudgmentResult(productNo);
	}
  
}
