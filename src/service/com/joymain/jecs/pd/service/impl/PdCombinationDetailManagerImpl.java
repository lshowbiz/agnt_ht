package com.joymain.jecs.pd.service.impl;

import java.util.List;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdCombinationDetail;
import com.joymain.jecs.pd.dao.PdCombinationDetailDao;
import com.joymain.jecs.pd.service.PdCombinationDetailManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class PdCombinationDetailManagerImpl extends BaseManager implements
		PdCombinationDetailManager {

	private PdCombinationDetailDao dao;

	public List getPdCombinationDetailTotals(CommonRecord crm) {
		// TODO Auto-generated method stub
		
		return dao.getPdCombinationDetailTotals(crm);
	}

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setPdCombinationDetailDao(PdCombinationDetailDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdCombinationDetailManager#getPdCombinationDetails(com.joymain.jecs.pd.model.PdCombinationDetail)
	 */
	public List getPdCombinationDetails(
			final PdCombinationDetail pdCombinationDetail) {
		return dao.getPdCombinationDetails(pdCombinationDetail);
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdCombinationDetailManager#getPdCombinationDetail(String
	 *      uniNo)
	 */
	public PdCombinationDetail getPdCombinationDetail(final String uniNo) {
		return dao.getPdCombinationDetail(new Long(uniNo));
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdCombinationDetailManager#savePdCombinationDetail(PdCombinationDetail
	 *      pdCombinationDetail)
	 */
	public void savePdCombinationDetail(PdCombinationDetail pdCombinationDetail) {
		dao.savePdCombinationDetail(pdCombinationDetail);
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdCombinationDetailManager#removePdCombinationDetail(String
	 *      uniNo)
	 */
	public void removePdCombinationDetail(final String uniNo) {
		dao.removePdCombinationDetail(new Long(uniNo));
	}

	// added for getPdCombinationDetailsByCrm
	public List getPdCombinationDetailsByCrm(CommonRecord crm, Pager pager) {
		return dao.getPdCombinationDetailsByCrm(crm, pager);
	}
}
