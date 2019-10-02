
package com.joymain.jecs.pd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdFlitWarehouseDetail;
import com.joymain.jecs.pd.dao.PdFlitWarehouseDetailDao;
import com.joymain.jecs.pd.service.PdFlitWarehouseDetailManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class PdFlitWarehouseDetailManagerImpl extends BaseManager implements PdFlitWarehouseDetailManager {
	private PdFlitWarehouseDetailDao dao;
	public boolean existPdFlitWarehouseDetail(String productNo, String fwNo) {
		// TODO Auto-generated method stub
    	List list = dao.getPdFlitWarehouseDetails(productNo, fwNo);
    	if(list.size()>0){
    		return true;
    	}else{
    		return false;
    	}
	}

	
	public List getOnWayStaticsByCrm(CommonRecord crm) {
		// TODO Auto-generated method stub
		return dao.getOnWayStaticsByCrm(crm);
	}


	public List getFlitWarehouseStaticsByCrm(CommonRecord crm,String flag) {
		// TODO Auto-generated method stub
		return dao.getFlitWarehouseStaticsByCrm(crm,flag);
	}

	public List getTotalPdFlitWarehouseDetails(CommonRecord crm) {
		// TODO Auto-generated method stub
		return dao.getTotalPdFlitWarehouseDetails(crm);
	}
	

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdFlitWarehouseDetailDao(PdFlitWarehouseDetailDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdFlitWarehouseDetailManager#getPdFlitWarehouseDetails(com.joymain.jecs.pd.model.PdFlitWarehouseDetail)
     */
    public List getPdFlitWarehouseDetails(final PdFlitWarehouseDetail pdFlitWarehouseDetail) {
        return dao.getPdFlitWarehouseDetails(pdFlitWarehouseDetail);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdFlitWarehouseDetailManager#getPdFlitWarehouseDetail(String uniNo)
     */
    public PdFlitWarehouseDetail getPdFlitWarehouseDetail(final String uniNo) {
        return dao.getPdFlitWarehouseDetail(new Long(uniNo));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdFlitWarehouseDetailManager#savePdFlitWarehouseDetail(PdFlitWarehouseDetail pdFlitWarehouseDetail)
     */
    public void savePdFlitWarehouseDetail(PdFlitWarehouseDetail pdFlitWarehouseDetail) {
        dao.savePdFlitWarehouseDetail(pdFlitWarehouseDetail);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdFlitWarehouseDetailManager#removePdFlitWarehouseDetail(String uniNo)
     */
    public void removePdFlitWarehouseDetail(final String uniNo) {
        dao.removePdFlitWarehouseDetail(new Long(uniNo));
    }
    //added for getPdFlitWarehouseDetailsByCrm
    public List getPdFlitWarehouseDetailsByCrm(CommonRecord crm, Pager pager){
	return dao.getPdFlitWarehouseDetailsByCrm(crm,pager);
    }
}
