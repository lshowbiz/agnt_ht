
package com.joymain.jecs.pm.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pm.model.JpmProductCombination;
import com.joymain.jecs.pm.dao.JpmProductCombinationDao;
import com.joymain.jecs.pm.service.JpmProductCombinationManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpmProductCombinationManagerImpl extends BaseManager implements JpmProductCombinationManager {
    private JpmProductCombinationDao dao;

    public boolean existCombinationProduct(String subProductNo, String productNo) {
		// TODO Auto-generated method stub
    	List list = dao.getJpmProductCombinations(subProductNo,productNo);
    	if(list.size()>0){
    		return true;
    	}
		return false;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpmProductCombinationDao(JpmProductCombinationDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductCombinationManager#getJpmProductCombinations(com.joymain.jecs.pm.model.JpmProductCombination)
     */
    public List getJpmProductCombinations(final JpmProductCombination jpmProductCombination) {
        return dao.getJpmProductCombinations(jpmProductCombination);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductCombinationManager#getJpmProductCombination(String jpcId)
     */
    public JpmProductCombination getJpmProductCombination(final String jpcId) {
        return dao.getJpmProductCombination(new Long(jpcId));
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductCombinationManager#saveJpmProductCombination(JpmProductCombination jpmProductCombination)
     */
    public void saveJpmProductCombination(JpmProductCombination jpmProductCombination) {
        dao.saveJpmProductCombination(jpmProductCombination);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductCombinationManager#removeJpmProductCombination(String jpcId)
     */
    public void removeJpmProductCombination(final String jpcId) {
        dao.removeJpmProductCombination(new Long(jpcId));
    }
    //added for getJpmProductCombinationsByCrm
    public List getJpmProductCombinationsByCrm(CommonRecord crm, Pager pager){
    	//modify by lihao for BUG #3047
    	//return dao.getJpmProductCombinationsByCrm(crm,pager);
    	return dao.getJpmProductCombinationsByCrmSql(crm,pager);
    }
    
    /**
	 * 查询组合商品下所有的子商品及数量
	 * @author fx 2015-01-11
	 * @param productNo
	 * @return list
	 */
	public List getCombinationProduct(String productNo){
		return dao.getCombinationProduct(productNo);
	}
}
