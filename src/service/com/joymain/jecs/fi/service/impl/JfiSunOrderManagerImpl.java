
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiSunOrder;
import com.joymain.jecs.fi.dao.JfiSunOrderDao;
import com.joymain.jecs.fi.service.JfiSunOrderManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiSunOrderManagerImpl extends BaseManager implements JfiSunOrderManager {
    private JfiSunOrderDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiSunOrderDao(JfiSunOrderDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunOrderManager#getJfiSunOrders(com.joymain.jecs.fi.model.JfiSunOrder)
     */
    public List getJfiSunOrders(final JfiSunOrder jfiSunOrder) {
        return dao.getJfiSunOrders(jfiSunOrder);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunOrderManager#getJfiSunOrder(String moId)
     */
    public JfiSunOrder getJfiSunOrder(final String moId) {
        return dao.getJfiSunOrder(new Long(moId));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunOrderManager#saveJfiSunOrder(JfiSunOrder jfiSunOrder)
     */
    public void saveJfiSunOrder(JfiSunOrder jfiSunOrder) {
        dao.saveJfiSunOrder(jfiSunOrder);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunOrderManager#removeJfiSunOrder(String moId)
     */
    public void removeJfiSunOrder(final String moId) {
        dao.removeJfiSunOrder(new Long(moId));
    }
    //added for getJfiSunOrdersByCrm
    public List getJfiSunOrdersByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiSunOrdersByCrm(crm,pager);
    }
	
	/**
	 * 根据条件统计商品销售量
	 * @param crm
	 * @return
	 */
	public List getStatisticProductSale(CommonRecord crm){
		return dao.getStatisticProductSale(crm);
	}
}
