
package com.joymain.jecs.po.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.service.PdWarehouseStockManager;
import com.joymain.jecs.po.model.JpoCounterOrder;
import com.joymain.jecs.po.model.JpoCounterOrderDetail;
import com.joymain.jecs.po.dao.JpoCounterOrderDao;
import com.joymain.jecs.po.service.JpoCounterOrderManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpoCounterOrderManagerImpl extends BaseManager implements JpoCounterOrderManager {
    private JpoCounterOrderDao dao;
    private PdWarehouseStockManager pdWarehouseStockManager;


	public void setPdWarehouseStockManager(
			PdWarehouseStockManager pdWarehouseStockManager) {
		this.pdWarehouseStockManager = pdWarehouseStockManager;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpoCounterOrderDao(JpoCounterOrderDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.po.service.JpoCounterOrderManager#getJpoCounterOrders(com.joymain.jecs.po.model.JpoCounterOrder)
     */
    public List getJpoCounterOrders(final JpoCounterOrder jpoCounterOrder) {
        return dao.getJpoCounterOrders(jpoCounterOrder);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoCounterOrderManager#getJpoCounterOrder(String coNo)
     */
    public JpoCounterOrder getJpoCounterOrder(final String coNo) {
        return dao.getJpoCounterOrder(coNo);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoCounterOrderManager#saveJpoCounterOrder(JpoCounterOrder jpoCounterOrder)
     */
    public void saveJpoCounterOrder(JpoCounterOrder jpoCounterOrder) {
        dao.saveJpoCounterOrder(jpoCounterOrder);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoCounterOrderManager#removeJpoCounterOrder(String coNo)
     */
    public void removeJpoCounterOrder(final String coNo) {
        dao.removeJpoCounterOrder(coNo);
    }
    //added for getJpoCounterOrdersByCrm
    public List getJpoCounterOrdersByCrm(CommonRecord crm, Pager pager){
	return dao.getJpoCounterOrdersByCrm(crm,pager);
    }

	public void updateAmount(String coNo) {
		JpoCounterOrder jpoCounterOrder = dao.getJpoCounterOrder(coNo);
		Set sets = jpoCounterOrder.getJpoCounterOrderDetails();
		Iterator iterator =sets.iterator();
		BigDecimal amount = new BigDecimal(0);
		while (iterator.hasNext()){
			JpoCounterOrderDetail  jpoCounterOrderDetail = (JpoCounterOrderDetail) iterator.next();
			amount=amount.add(new BigDecimal(jpoCounterOrderDetail.getPrice().floatValue()*jpoCounterOrderDetail.getQty()));
			log.info("amount="+amount);
		}
		jpoCounterOrder.setAmount(amount);
		dao.saveJpoCounterOrder(jpoCounterOrder);
	}

	public void shipJpoCounterOrder(JpoCounterOrder jpoCounterOrder) {
		if ("0".equals(jpoCounterOrder.getStockFlag())) {
			jpoCounterOrder.setStockFlag("1");
			pdWarehouseStockManager.updatePdWarehouseStock(jpoCounterOrder);
		}
		dao.saveJpoCounterOrder(jpoCounterOrder);
	}

	public void repealPoCounterOrder(JpoCounterOrder jpoCounterOrder) {
		if ("1".equals(jpoCounterOrder.getStockFlag())) {
			jpoCounterOrder.setStockFlag("2");
			pdWarehouseStockManager.updatePdWarehouseStock(jpoCounterOrder);
		}
		dao.saveJpoCounterOrder(jpoCounterOrder);
		
	}

	public List getJpoCounterOrdersByCrmSum(CommonRecord crm) {
		return dao.getJpoCounterOrdersByCrmSum(crm);
	}
}
