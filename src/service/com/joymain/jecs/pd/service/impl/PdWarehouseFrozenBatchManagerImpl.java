package com.joymain.jecs.pd.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdWarehouseFrozenBatch;
import com.joymain.jecs.pd.dao.PdWarehouseFrozenBatchDao;
import com.joymain.jecs.pd.service.PdWarehouseFrozenBatchManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class PdWarehouseFrozenBatchManagerImpl extends BaseManager implements
		PdWarehouseFrozenBatchManager {
	private PdWarehouseFrozenBatchDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setPdWarehouseFrozenBatchDao(PdWarehouseFrozenBatchDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdWarehouseFrozenBatchManager#getPdWarehouseFrozenBatchs(com.joymain.jecs.pd.model.PdWarehouseFrozenBatch)
	 */
	public List getPdWarehouseFrozenBatchs(
			final PdWarehouseFrozenBatch pdWarehouseFrozenBatch) {
		return dao.getPdWarehouseFrozenBatchs(pdWarehouseFrozenBatch);
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdWarehouseFrozenBatchManager#getPdWarehouseFrozenBatch(String
	 *      batchId)
	 */
	public PdWarehouseFrozenBatch getPdWarehouseFrozenBatch(final String batchId) {
		return dao.getPdWarehouseFrozenBatch(new Long(batchId));
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdWarehouseFrozenBatchManager#savePdWarehouseFrozenBatch(PdWarehouseFrozenBatch
	 *      pdWarehouseFrozenBatch)
	 */
	public void savePdWarehouseFrozenBatch(
			PdWarehouseFrozenBatch pdWarehouseFrozenBatch) {

		dao.savePdWarehouseFrozenBatch(pdWarehouseFrozenBatch);
		dao.addFrozenDetail(pdWarehouseFrozenBatch.getBatchId());
	}
	
	

	/**
	 * @see com.joymain.jecs.pd.service.PdWarehouseFrozenBatchManager#removePdWarehouseFrozenBatch(String
	 *      batchId)
	 */
	public void removePdWarehouseFrozenBatch(final String batchId) {
		dao.removePdWarehouseFrozenBatch(new Long(batchId));
	}

	// added for getPdWarehouseFrozenBatchsByCrm
	public List getPdWarehouseFrozenBatchsByCrm(CommonRecord crm, Pager pager) {
		return dao.getPdWarehouseFrozenBatchsByCrm(crm, pager);
	}

	/* (non-Javadoc)
	 * @see com.joymain.jecs.pd.service.PdWarehouseFrozenBatchManager#excuteFrozen()
	 */
	public void excuteFrozen() {
		// TODO Auto-generated method stub
		log.info("执行冻结库存批处理开始...................");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String batchCode = sdf.format(new Date());
		PdWarehouseFrozenBatch pdWarehouseFrozenBatch=new PdWarehouseFrozenBatch();
		pdWarehouseFrozenBatch.setBatchCode(batchCode);
		
		this.savePdWarehouseFrozenBatch(pdWarehouseFrozenBatch);
	}
	
}
