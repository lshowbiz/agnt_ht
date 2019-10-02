
package com.joymain.jecs.fi.service;

import java.math.BigDecimal;
import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.FiBillAccountWarning;
import com.joymain.jecs.fi.dao.FiBillAccountWarningDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface FiBillAccountWarningManager extends Manager {
    /**
     * Retrieves all of the fiBillAccountWarnings
     */
    public List getFiBillAccountWarnings(FiBillAccountWarning fiBillAccountWarning);

    /**
     * Gets fiBillAccountWarning's information based on billAccountCode.
     * @param billAccountCode the fiBillAccountWarning's billAccountCode
     * @return fiBillAccountWarning populated fiBillAccountWarning object
     */
    public FiBillAccountWarning getFiBillAccountWarning(final String billAccountCode);

    /**
     * Saves a fiBillAccountWarning's information
     * @param fiBillAccountWarning the object to be saved
     */
    public void saveFiBillAccountWarning(FiBillAccountWarning fiBillAccountWarning);

    /**
     * Removes a fiBillAccountWarning from the database by billAccountCode
     * @param billAccountCode the fiBillAccountWarning's billAccountCode
     */
    public void removeFiBillAccountWarning(final String billAccountCode);
    //added for getFiBillAccountWarningsByCrm
    public List getFiBillAccountWarningsByCrm(CommonRecord crm, Pager pager);
    
    /**
	 * 进账记录叠加
	 * @param amout
	 * @param billAccountCode
	 */
	public void addTotalAmount(String amount, String billAccountCode);
	
	/**
	 * 重新统计进账额度
	 */
	public void refComFiBillAccountWarnings();
	
}

