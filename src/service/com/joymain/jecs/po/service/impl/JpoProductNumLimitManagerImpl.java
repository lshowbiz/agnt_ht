
package com.joymain.jecs.po.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.po.model.JpoProductNumLimit;
import com.joymain.jecs.po.dao.JpoProductNumLimitDao;
import com.joymain.jecs.po.service.JpoProductNumLimitManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpoProductNumLimitManagerImpl extends BaseManager implements JpoProductNumLimitManager {
    private JpoProductNumLimitDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpoProductNumLimitDao(JpoProductNumLimitDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.po.service.JpoProductNumLimitManager#getJpoProductNumLimits(com.joymain.jecs.po.model.JpoProductNumLimit)
     */
    public List getJpoProductNumLimits(final JpoProductNumLimit jpoProductNumLimit) {
        return dao.getJpoProductNumLimits(jpoProductNumLimit);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoProductNumLimitManager#getJpoProductNumLimit(String id)
     */
    public JpoProductNumLimit getJpoProductNumLimit(final String id) {
        return dao.getJpoProductNumLimit(new Long(id));
    }

    /**
     * @see com.joymain.jecs.po.service.JpoProductNumLimitManager#saveJpoProductNumLimit(JpoProductNumLimit jpoProductNumLimit)
     */
    public void saveJpoProductNumLimit(JpoProductNumLimit jpoProductNumLimit) {
        dao.saveJpoProductNumLimit(jpoProductNumLimit);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoProductNumLimitManager#removeJpoProductNumLimit(String id)
     */
    public void removeJpoProductNumLimit(final String id) {
        dao.removeJpoProductNumLimit(new Long(id));
    }
    //added for getJpoProductNumLimitsByCrm
    public List getJpoProductNumLimitsByCrm(CommonRecord crm, Pager pager){
	return dao.getJpoProductNumLimitsByCrm(crm,pager);
    }
    
    public JpoProductNumLimit getByProductNo(String productNo){
    	return dao.getByProductNo(productNo);
    }
    
    public List<JpoProductNumLimit>  getAll(){
    	return dao.getAll();
    }
    
    public JpoProductNumLimit getNum(String productNo){
    	return dao.getNum(productNo);
    }
	
	public List getAllPros(){
		return dao.getAllPros();
	}
}
