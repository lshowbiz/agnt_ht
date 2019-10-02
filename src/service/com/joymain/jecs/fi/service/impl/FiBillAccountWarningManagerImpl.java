
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.FiBillAccountWarning;
import com.joymain.jecs.fi.dao.FiBillAccountWarningDao;
import com.joymain.jecs.fi.service.FiBillAccountWarningManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class FiBillAccountWarningManagerImpl extends BaseManager implements FiBillAccountWarningManager {
    private FiBillAccountWarningDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiBillAccountWarningDao(FiBillAccountWarningDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBillAccountWarningManager#getFiBillAccountWarnings(com.joymain.jecs.fi.model.FiBillAccountWarning)
     */
    public List getFiBillAccountWarnings(final FiBillAccountWarning fiBillAccountWarning) {
        return dao.getFiBillAccountWarnings(fiBillAccountWarning);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBillAccountWarningManager#getFiBillAccountWarning(String billAccountCode)
     */
    public FiBillAccountWarning getFiBillAccountWarning(final String billAccountCode) {
        return dao.getFiBillAccountWarning(new String(billAccountCode));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBillAccountWarningManager#saveFiBillAccountWarning(FiBillAccountWarning fiBillAccountWarning)
     */
    public void saveFiBillAccountWarning(FiBillAccountWarning fiBillAccountWarning) {
        dao.saveFiBillAccountWarning(fiBillAccountWarning);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBillAccountWarningManager#removeFiBillAccountWarning(String billAccountCode)
     */
    public void removeFiBillAccountWarning(final String billAccountCode) {
        dao.removeFiBillAccountWarning(new String(billAccountCode));
    }
    //added for getFiBillAccountWarningsByCrm
    public List getFiBillAccountWarningsByCrm(CommonRecord crm, Pager pager){
	return dao.getFiBillAccountWarningsByCrm(crm,pager);
    }
    
    /**
	 * 进账记录叠加
	 * @param amout
	 * @param billAccountCode
	 */
	@Override
	public void addTotalAmount(String amount, String billAccountCode) {

		FiBillAccountWarning mfiBillAccountWarning = this.dao.getFiBillAccountWarningByBillAccountCode(billAccountCode);
		
		if(mfiBillAccountWarning!=null && !("").equals(mfiBillAccountWarning.getBillAccountCode())){
			
			//累加
			BigDecimal tempAmount = mfiBillAccountWarning.getNowTotalAmount().add(new BigDecimal(amount).divide(new BigDecimal(100)));
			mfiBillAccountWarning.setNowTotalAmount(tempAmount);
			
			this.saveFiBillAccountWarning(mfiBillAccountWarning);
		}
	}

	/**
	 * 重新统计进账额度
	 */
	@Override
	public void refComFiBillAccountWarnings() {
		// TODO Auto-generated method stub
		
		dao.refComFiBillAccountWarnings();
	}
}
