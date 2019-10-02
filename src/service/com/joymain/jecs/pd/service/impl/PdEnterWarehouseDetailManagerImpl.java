
package com.joymain.jecs.pd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdEnterWarehouseDetail;
import com.joymain.jecs.pd.dao.PdEnterWarehouseDetailDao;
import com.joymain.jecs.pd.service.PdEnterWarehouseDetailManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class PdEnterWarehouseDetailManagerImpl extends BaseManager implements PdEnterWarehouseDetailManager {
	public List getTotolPdEnterWarehouseDetails(CommonRecord crm) {
		// TODO Auto-generated method stub
		return dao.getTotolPdEnterWarehouseDetails(crm);
	}

	public boolean existPdEnterWarehouseDetail(String productNo, String ewNo) {
		// TODO Auto-generated method stub
    	List list = dao.getPdEnterWarehouseDetails(productNo, ewNo);
    	if(list.size()>0){
    		return true;
    	}else{
    		return false;
		}
	}
	public List getPdEnterWarehouseDetails(String productNo, String ewNo) {
		// TODO Auto-generated method stub
		return dao.getPdEnterWarehouseDetails(productNo, ewNo);
	}
    private PdEnterWarehouseDetailDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdEnterWarehouseDetailDao(PdEnterWarehouseDetailDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdEnterWarehouseDetailManager#getPdEnterWarehouseDetails(com.joymain.jecs.pd.model.PdEnterWarehouseDetail)
     */
    public List getPdEnterWarehouseDetails(final PdEnterWarehouseDetail pdEnterWarehouseDetail) {
        return dao.getPdEnterWarehouseDetails(pdEnterWarehouseDetail);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdEnterWarehouseDetailManager#getPdEnterWarehouseDetail(String uniNo)
     */
    public PdEnterWarehouseDetail getPdEnterWarehouseDetail(final String uniNo) {
        return dao.getPdEnterWarehouseDetail(new Long(uniNo));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdEnterWarehouseDetailManager#savePdEnterWarehouseDetail(PdEnterWarehouseDetail pdEnterWarehouseDetail)
     */
    public void savePdEnterWarehouseDetail(PdEnterWarehouseDetail pdEnterWarehouseDetail) {
        dao.savePdEnterWarehouseDetail(pdEnterWarehouseDetail);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdEnterWarehouseDetailManager#removePdEnterWarehouseDetail(String uniNo)
     */
    public void removePdEnterWarehouseDetail(final String uniNo) {
        dao.removePdEnterWarehouseDetail(new Long(uniNo));
    }
    //added for getPdEnterWarehouseDetailsByCrm
    public List getPdEnterWarehouseDetailsByCrm(CommonRecord crm, Pager pager){
	return dao.getPdEnterWarehouseDetailsByCrm(crm,pager);
    }
}
