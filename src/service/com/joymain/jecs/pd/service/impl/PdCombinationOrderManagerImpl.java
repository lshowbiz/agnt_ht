
package com.joymain.jecs.pd.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.pd.model.PdCombinationDetail;
import com.joymain.jecs.pd.model.PdCombinationOrder;
import com.joymain.jecs.pd.dao.PdCombinationOrderDao;
import com.joymain.jecs.pd.service.PdCombinationDetailManager;
import com.joymain.jecs.pd.service.PdCombinationOrderManager;
import com.joymain.jecs.pd.service.PdWarehouseStockManager;
import com.joymain.jecs.pm.model.JpmProduct;
import com.joymain.jecs.pm.model.JpmProductCombination;
import com.joymain.jecs.pm.service.JpmProductManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class PdCombinationOrderManagerImpl extends BaseManager implements PdCombinationOrderManager {
    private PdCombinationOrderDao dao;
    private JpmProductManager jpmProductManager;
    private PdCombinationDetailManager pdCombinationDetailManager;
    private PdWarehouseStockManager pdWarehouseStockManager;
    public void setPdWarehouseStockManager(
			PdWarehouseStockManager pdWarehouseStockManager) {
		this.pdWarehouseStockManager = pdWarehouseStockManager;
	}
    

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdCombinationOrderDao(PdCombinationOrderDao dao) {
        this.dao = dao;
    }

    public void setJpmProductManager(JpmProductManager jpmProductManager) {
		this.jpmProductManager = jpmProductManager;
	}



	public void setPdCombinationDetailManager(
			PdCombinationDetailManager pdCombinationDetailManager) {
		this.pdCombinationDetailManager = pdCombinationDetailManager;
	}

	/**
     * @see com.joymain.jecs.pd.service.PdCombinationOrderManager#getPdCombinationOrders(com.joymain.jecs.pd.model.PdCombinationOrder)
     */
    public List getPdCombinationOrders(final PdCombinationOrder pdCombinationOrder) {
        return dao.getPdCombinationOrders(pdCombinationOrder);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdCombinationOrderManager#getPdCombinationOrder(String pcNo)
     */
    public PdCombinationOrder getPdCombinationOrder(final String pcNo) {
        return dao.getPdCombinationOrder(new String(pcNo));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdCombinationOrderManager#savePdCombinationOrder(PdCombinationOrder pdCombinationOrder)
     */
    public void savePdCombinationOrder(PdCombinationOrder pdCombinationOrder) {
        dao.savePdCombinationOrder(pdCombinationOrder);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdCombinationOrderManager#removePdCombinationOrder(String pcNo)
     */
    public void removePdCombinationOrder(final String pcNo) {
        dao.removePdCombinationOrder(new String(pcNo));
    }
    //added for getPdCombinationOrdersByCrm
    public List getPdCombinationOrdersByCrm(CommonRecord crm, Pager pager){
	return dao.getPdCombinationOrdersByCrm(crm,pager);
    }

    public void addPdCombinationOrder(PdCombinationOrder pdCombinationOrder){
//    	pdCombinationOrder.setCreateTime(new Date());
//		pdCombinationOrder.setOrderFlag(-1);
//		pdCombinationOrder.setCreateUsrCode(sessionLogin.getUserCode());
		dao.savePdCombinationOrder(pdCombinationOrder);
		addPdCombinationDetails(pdCombinationOrder);
    }
	public void submitPdCombinationOrder(PdCombinationOrder pdCombinationOrder,SysUser sessionLogin) {
		// TODO Auto-generated method stub
//		dao.removePdCombinationDetails(pdCombinationOrder);
//		pdCombinationOrder.setCreateTime(new Date());
		pdCombinationOrder.setOrderFlag(0);
//		pdCombinationOrder.setCreateUsrCode(sessionLogin.getUserCode());
		dao.savePdCombinationOrder(pdCombinationOrder);
//		addPdCombinationDetails(pdCombinationOrder);
	}
	public void confirmPdCombinationOrder(
			PdCombinationOrder pdCombinationOrder, SysUser sessionLogin) {
		// TODO Auto-generated method stub
		
		if(!"1".equals(pdCombinationOrder.getStockFlag())){
			log.info("confirmPdCombinationOrderM..1.");
			pdWarehouseStockManager.updatePdWarehouseStock(pdCombinationOrder);
			log.info("confirmPdCombinationOrderM..3.");
			/*pdCombinationOrder.setOrderFlag(2);
			pdCombinationOrder.setOkTime(new Date());
			pdCombinationOrder.setOkUsrCode(sessionLogin.getUserCode());*/
			pdCombinationOrder.setStockFlag("1");
			dao.savePdCombinationOrder(pdCombinationOrder);
			log.info("confirmPdCombinationOrderM..3.");
		}
		
	}

	private void addPdCombinationDetails(PdCombinationOrder pdCombinationOrder){
		JpmProduct  pmProduct =jpmProductManager.getJpmProduct(pdCombinationOrder.getProductNo());
		
		PdCombinationDetail pdCombinationDetailP = new PdCombinationDetail();
		pdCombinationDetailP.setPcNo(pdCombinationOrder.getPcNo());
		pdCombinationDetailP.setProductNo(pdCombinationOrder.getProductNo());
		pdCombinationDetailP.setQty(pdCombinationOrder.getQty());
		pdCombinationDetailManager.savePdCombinationDetail(pdCombinationDetailP);
		
		Set combinations = pmProduct.getJpmProductCombinations();
		Iterator iterator =combinations.iterator();
		while(iterator.hasNext()){
			JpmProductCombination jpmProductCombination=	(JpmProductCombination) iterator.next();
			PdCombinationDetail pdCombinationDetail = new PdCombinationDetail();
			pdCombinationDetail.setPcNo(pdCombinationOrder.getPcNo());
			pdCombinationDetail.setProductNo(jpmProductCombination.getSubProductNo());
			pdCombinationDetail.setQty(-jpmProductCombination.getQty()*pdCombinationOrder.getQty());
			pdCombinationDetailManager.savePdCombinationDetail(pdCombinationDetail);
//			pdCombinationDetail.setPrice(price)
		}
		
	} 
    
}
