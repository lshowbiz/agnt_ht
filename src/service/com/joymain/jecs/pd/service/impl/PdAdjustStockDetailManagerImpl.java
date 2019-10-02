
package com.joymain.jecs.pd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdAdjustStockDetail;
import com.joymain.jecs.pd.dao.PdAdjustStockDetailDao;
import com.joymain.jecs.pd.service.PdAdjustStockDetailManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class PdAdjustStockDetailManagerImpl extends BaseManager implements PdAdjustStockDetailManager {
   
	private PdAdjustStockDetailDao dao;
	 public boolean existPdAdjustStockDetail(String productNo, String asNo) {
			// TODO Auto-generated method stub
		 List list = dao.getPdAdjustStockDetails(productNo, asNo);
			if(list.size()>0){
				return true;
			}else{
				return false;
			}
		}
    public List getTotalPdAdjustStockDetails(CommonRecord crm) {
		// TODO Auto-generated method stub
		return dao.getTotalPdAdjustStockDetails(crm);
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdAdjustStockDetailDao(PdAdjustStockDetailDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdAdjustStockDetailManager#getPdAdjustStockDetails(com.joymain.jecs.pd.model.PdAdjustStockDetail)
     */
    public List getPdAdjustStockDetails(final PdAdjustStockDetail pdAdjustStockDetail) {
        return dao.getPdAdjustStockDetails(pdAdjustStockDetail);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdAdjustStockDetailManager#getPdAdjustStockDetail(String uniNo)
     */
    public PdAdjustStockDetail getPdAdjustStockDetail(final String uniNo) {
        return dao.getPdAdjustStockDetail(new Long(uniNo));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdAdjustStockDetailManager#savePdAdjustStockDetail(PdAdjustStockDetail pdAdjustStockDetail)
     */
    public void savePdAdjustStockDetail(PdAdjustStockDetail pdAdjustStockDetail) {
        dao.savePdAdjustStockDetail(pdAdjustStockDetail);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdAdjustStockDetailManager#removePdAdjustStockDetail(String uniNo)
     */
    public void removePdAdjustStockDetail(final String uniNo) {
        dao.removePdAdjustStockDetail(new Long(uniNo));
    }
    //added for getPdAdjustStockDetailsByCrm
    public List getPdAdjustStockDetailsByCrm(CommonRecord crm, Pager pager){
	return dao.getPdAdjustStockDetailsByCrm(crm,pager);
    }
}
