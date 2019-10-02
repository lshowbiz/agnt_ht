
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiFundbookTemp;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiFundbookTempDao extends Dao {

    /**
     * Retrieves all of the fiFundbookTemps
     */
    public List getFiFundbookTemps(FiFundbookTemp fiFundbookTemp);

    /**
     * Gets fiFundbookTemp's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param tempId the fiFundbookTemp's tempId
     * @return fiFundbookTemp populated fiFundbookTemp object
     */
    public FiFundbookTemp getFiFundbookTemp(final Long tempId);

    /**
     * Saves a fiFundbookTemp's information
     * @param fiFundbookTemp the object to be saved
     */    
    public void saveFiFundbookTemp(FiFundbookTemp fiFundbookTemp);

    /**
     * Removes a fiFundbookTemp from the database by tempId
     * @param tempId the fiFundbookTemp's tempId
     */
    public void removeFiFundbookTemp(final Long tempId);
    //added for getFiFundbookTempsByCrm
    public List getFiFundbookTempsByCrm(CommonRecord crm, Pager pager);
    
    /**
	 * 获取某用户的临时基金条数
	 * @param companyCode
	 * @param agentNo
	 * @return
	 */
	public long getCountByDate(final String companyCode, final String userCode, final String bankbookType);
	
	/**
	 * 获取收支统计金额 incTotal:收入  expTotal:支出
	 * @param crm
	 * @return
	 */
	public Map getIncExpStatMap(CommonRecord crm);
	
	/**
	 * 批量保存多条记录
	 * @param fiBankbookTemps
	 */
	public void saveFiFundbookTemps(List fiFundbookTemps);

}

