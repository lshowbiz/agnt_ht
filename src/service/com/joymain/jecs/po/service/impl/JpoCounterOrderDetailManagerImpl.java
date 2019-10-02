
package com.joymain.jecs.po.service.impl;

import java.util.List;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pm.model.JpmProductSaleNew;
import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.pm.service.JpmProductSaleManager;
import com.joymain.jecs.po.dao.JpoCounterOrderDetailDao;
import com.joymain.jecs.po.model.JpoCounterOrder;
import com.joymain.jecs.po.model.JpoCounterOrderDetail;
import com.joymain.jecs.po.service.JpoCounterOrderDetailManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpoCounterOrderDetailManagerImpl extends BaseManager implements JpoCounterOrderDetailManager {
    private JpoCounterOrderDetailDao dao;
    private JpmProductSaleManager jpmProductSaleManager;
    public void setJpmProductSaleManager(JpmProductSaleManager jpmProductSaleManager) {
		this.jpmProductSaleManager = jpmProductSaleManager;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpoCounterOrderDetailDao(JpoCounterOrderDetailDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.po.service.JpoCounterOrderDetailManager#getJpoCounterOrderDetails(com.joymain.jecs.po.model.JpoCounterOrderDetail)
     */
    public List getJpoCounterOrderDetails(final JpoCounterOrderDetail jpoCounterOrderDetail) {
        return dao.getJpoCounterOrderDetails(jpoCounterOrderDetail);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoCounterOrderDetailManager#getJpoCounterOrderDetail(String codNo)
     */
    public JpoCounterOrderDetail getJpoCounterOrderDetail(final String codNo) {
        return dao.getJpoCounterOrderDetail(new Long(codNo));
    }

    /**
     * @see com.joymain.jecs.po.service.JpoCounterOrderDetailManager#saveJpoCounterOrderDetail(JpoCounterOrderDetail jpoCounterOrderDetail)
     */
    public void saveJpoCounterOrderDetail(JpoCounterOrderDetail jpoCounterOrderDetail) {
        dao.saveJpoCounterOrderDetail(jpoCounterOrderDetail);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoCounterOrderDetailManager#removeJpoCounterOrderDetail(String codNo)
     */
    public void removeJpoCounterOrderDetail(final String codNo) {
        dao.removeJpoCounterOrderDetail(new Long(codNo));
    }
    //added for getJpoCounterOrderDetailsByCrm
    public List getJpoCounterOrderDetailsByCrm(CommonRecord crm, Pager pager){
	return dao.getJpoCounterOrderDetailsByCrm(crm,pager);
    }

	public void savePreDetails(String companyCode, JpoCounterOrder jpoCounterOrder) throws Exception{
		List list = jpmProductSaleManager.getJpoCounterProductSales(companyCode);
    	if(list != null){
    		for(int i=0;i<list.size();i++){
    			JpmProductSaleTeamType jpmProductSaleTeamType = (JpmProductSaleTeamType) list.get(i);
    			JpoCounterOrderDetail jpoCounterOrderDetail = new JpoCounterOrderDetail();
    			
    			jpoCounterOrderDetail.setJpmProductSaleNew(jpmProductSaleTeamType.getJpmProductSaleNew());
  
    			jpoCounterOrderDetail.setPrice(jpmProductSaleTeamType.getPrice());
    			
    			jpoCounterOrderDetail.setQty(0);
    			jpoCounterOrderDetail.setJpoCounterOrder(jpoCounterOrder);
    			dao.saveJpoCounterOrderDetail(jpoCounterOrderDetail);
        	}
    	}
	}	
	public boolean getExistPoCounterOrderDetail(String coNo, Long productId) {
		// TODO Auto-generated method stub
    	List list =dao.getJpoCounterOrderDetails(coNo, productId);
    	if(list.size()>0){
    		return true;
    	}else{
    		return false;
		}
		
	}

	public List getPoCounterSumByCrm(CommonRecord crm) {
		return dao.getPoCounterSumByCrm(crm);
	}

	public List getTotolPoCounterOrderDetails(CommonRecord crm) {
		return dao.getTotolPoCounterOrderDetails(crm);
	}
}
