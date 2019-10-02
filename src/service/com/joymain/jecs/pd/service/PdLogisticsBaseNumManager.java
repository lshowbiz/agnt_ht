
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdLogisticsBaseNum;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdLogisticsBaseNumManager extends Manager {
    /**
     * Retrieves all of the pdLogisticsBaseNums
     */
    public List getPdLogisticsBaseNums(PdLogisticsBaseNum pdLogisticsBaseNum);

    /**
     * Gets pdLogisticsBaseNum's information based on numId.
     * @param numId the pdLogisticsBaseNum's numId
     * @return pdLogisticsBaseNum populated pdLogisticsBaseNum object
     */
    public PdLogisticsBaseNum getPdLogisticsBaseNum(final String numId);

    /**
     * Saves a pdLogisticsBaseNum's information
     * @param pdLogisticsBaseNum the object to be saved
     */
    public void savePdLogisticsBaseNum(PdLogisticsBaseNum pdLogisticsBaseNum);

    /**
     * Removes a pdLogisticsBaseNum from the database by numId
     * @param numId the pdLogisticsBaseNum's numId
     */
    public void removePdLogisticsBaseNum(final String numId);
    //added for getPdLogisticsBaseNumsByCrm
    public List getPdLogisticsBaseNumsByCrm(CommonRecord crm, Pager pager);
    
    /**
	 * 获取未完整获取物流单号信息的PdLogisticsBaseNum-定时器用到
	 * @author gw 2016-02-16
	 * @return
	 */
	public List<PdLogisticsBaseNum> getPdLogisticsBaseNumForPdMailReceipt();

	/**
	 * 物流查询定时器用到---获取DO相关的信息
	 * @author fu 2016-02-16
	 * @return list
	 */
	public List getDoForSql();
	
}

