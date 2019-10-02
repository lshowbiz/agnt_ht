
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiBankbookTemp;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiBankbookTempDao extends Dao {

    /**
     * Retrieves all of the fiBankbookTemps
     */
    public List getFiBankbookTemps(FiBankbookTemp fiBankbookTemp);

    /**
     * Gets fiBankbookTemp's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param tempId the fiBankbookTemp's tempId
     * @return fiBankbookTemp populated fiBankbookTemp object
     */
    public FiBankbookTemp getFiBankbookTemp(final Long tempId);

    /**
     * Saves a fiBankbookTemp's information
     * @param fiBankbookTemp the object to be saved
     */    
    public void saveFiBankbookTemp(FiBankbookTemp fiBankbookTemp);

    /**
     * Removes a fiBankbookTemp from the database by tempId
     * @param tempId the fiBankbookTemp's tempId
     */
    public void removeFiBankbookTemp(final Long tempId);
    //added for getFiBankbookTempsByCrm
    public List getFiBankbookTempsByCrm(CommonRecord crm, Pager pager);
    
	/**
	 * 获取收支统计金额 incTotal:收入  expTotal:支出
	 * @param crm
	 * @return
	 */
	public Map getIncExpStatMap(CommonRecord crm);
	
	/**
	 * 获取某用户的存折条数
	 * @param companyCode
	 * @param agentNo
	 * @return
	 */
	public long getCountByDate(final String companyCode, final String userCode, final String bankbookType);

	/**
	 * 批量保存多条记录
	 * @param fiBankbookTemps
	 */
	public void saveFiBankbookTemps(List fiBankbookTemps);
    public List getFiProductPointTempsByCrm(CommonRecord crm, Pager pager);
	public Map getPpIncExpStatMap(CommonRecord crm);
}

