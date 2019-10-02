
package com.joymain.jecs.pd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdSendInfoDetail;
import com.joymain.jecs.pd.dao.PdSendInfoDetailDao;
import com.joymain.jecs.pd.service.PdSendInfoDetailManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class PdSendInfoDetailManagerImpl extends BaseManager implements PdSendInfoDetailManager {
	
	
    public List getShipDetailsByTown(CommonRecord crm) {
		// TODO Auto-generated method stub
		return dao.getShipDetailsByTown(crm);
	}

	public List getShipDetails(CommonRecord crm) {
		// TODO Auto-generated method stub
		return dao.getShipDetails(crm);
	}

	public List getShipDetailsByState(CommonRecord crm) {
		// TODO Auto-generated method stub
		return dao.getShipDetailsByState(crm);
	}

	public List getTotalPdSendInfoDetails(CommonRecord crm) {
		// TODO Auto-generated method stub
		return dao.getTotalPdSendInfoDetails(crm);
	}
	private PdSendInfoDetailDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdSendInfoDetailDao(PdSendInfoDetailDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdSendInfoDetailManager#getPdSendInfoDetails(com.joymain.jecs.pd.model.PdSendInfoDetail)
     */
    public List getPdSendInfoDetails(final PdSendInfoDetail pdSendInfoDetail) {
        return dao.getPdSendInfoDetails(pdSendInfoDetail);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdSendInfoDetailManager#getPdSendInfoDetail(String uniNo)
     */
    public PdSendInfoDetail getPdSendInfoDetail(final String uniNo) {
        return dao.getPdSendInfoDetail(new BigDecimal(uniNo));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdSendInfoDetailManager#savePdSendInfoDetail(PdSendInfoDetail pdSendInfoDetail)
     */
    public void savePdSendInfoDetail(PdSendInfoDetail pdSendInfoDetail) {
        dao.savePdSendInfoDetail(pdSendInfoDetail);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdSendInfoDetailManager#removePdSendInfoDetail(String uniNo)
     */
    public void removePdSendInfoDetail(final String uniNo) {
        dao.removePdSendInfoDetail(new BigDecimal(uniNo));
    }
    //added for getPdSendInfoDetailsByCrm
    public List getPdSendInfoDetailsByCrm(CommonRecord crm, Pager pager){
	return dao.getPdSendInfoDetailsByCrm(crm,pager);
    }
}
