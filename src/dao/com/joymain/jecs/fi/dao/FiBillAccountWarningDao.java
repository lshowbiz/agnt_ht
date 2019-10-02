
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiBillAccountWarning;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiBillAccountWarningDao extends Dao {

    /**
     * Retrieves all of the fiBillAccountWarnings
     */
    public List getFiBillAccountWarnings(FiBillAccountWarning fiBillAccountWarning);

    /**
     * Gets fiBillAccountWarning's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
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
	 * 根据商户号查询预警记录
	 * @param billAccountCode
	 * @return
	 */
    public FiBillAccountWarning getFiBillAccountWarningByBillAccountCode(final String billAccountCode);
    
    /**
	 * 重新统计进账额度
	 */
	public void refComFiBillAccountWarnings();
}

