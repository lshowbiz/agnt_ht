package com.joymain.jecs.pd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdOutWarehouseDetail;
import com.joymain.jecs.pd.dao.PdOutWarehouseDetailDao;
import com.joymain.jecs.pd.service.PdOutWarehouseDetailManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class PdOutWarehouseDetailManagerImpl extends BaseManager implements
		PdOutWarehouseDetailManager {

	private PdOutWarehouseDetailDao dao;

	public List getTotalPdOutWarehouseDetails(CommonRecord crm) {
		// TODO Auto-generated method stub
		return dao.getTotalPdOutWarehouseDetails(crm);
	}

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setPdOutWarehouseDetailDao(PdOutWarehouseDetailDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdOutWarehouseDetailManager#getPdOutWarehouseDetails(com.joymain.jecs.pd.model.PdOutWarehouseDetail)
	 */
	public List getPdOutWarehouseDetails(
			final PdOutWarehouseDetail pdOutWarehouseDetail) {
		return dao.getPdOutWarehouseDetails(pdOutWarehouseDetail);
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdOutWarehouseDetailManager#getPdOutWarehouseDetail(String
	 *      uniNo)
	 */
	public PdOutWarehouseDetail getPdOutWarehouseDetail(final String uniNo) {
		return dao.getPdOutWarehouseDetail(new Long(uniNo));
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdOutWarehouseDetailManager#savePdOutWarehouseDetail(PdOutWarehouseDetail
	 *      pdOutWarehouseDetail)
	 */
	public void savePdOutWarehouseDetail(
			PdOutWarehouseDetail pdOutWarehouseDetail) {
		dao.savePdOutWarehouseDetail(pdOutWarehouseDetail);
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdOutWarehouseDetailManager#removePdOutWarehouseDetail(String
	 *      uniNo)
	 */
	public void removePdOutWarehouseDetail(final String uniNo) {
		dao.removePdOutWarehouseDetail(new Long(uniNo));
	}

	// added for getPdOutWarehouseDetailsByCrm
	public List getPdOutWarehouseDetailsByCrm(CommonRecord crm, Pager pager) {
		return dao.getPdOutWarehouseDetailsByCrm(crm, pager);
	}

	public boolean existPdOutWarehouseDetail(String productNo, String owNo) {
		// TODO Auto-generated method stub
		List list = dao.getPdOutWarehouseDetails(productNo, owNo,null);
    	if(list.size()>0){
    		return true;
    	}else{
    		return false;
    	}
	}

	public boolean existPdOutWarehouseDetail(String productNo, String owNo,
			Long uniNo) {
		// TODO Auto-generated method stub
		List list= dao.getPdOutWarehouseDetails(productNo, owNo,uniNo);
		if(list.size()>0){
    		return true;
    	}else{
    		return false;
    	}
	}
	
	
}
