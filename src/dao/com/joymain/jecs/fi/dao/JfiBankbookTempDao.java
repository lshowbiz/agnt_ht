
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiBankbookTemp;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiBankbookTempDao extends Dao {

    /**
     * Retrieves all of the jfiBankbookTemps
     */
    public List getJfiBankbookTemps(JfiBankbookTemp jfiBankbookTemp);

    /**
     * Gets jfiBankbookTemp's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param tempId the jfiBankbookTemp's tempId
     * @return jfiBankbookTemp populated jfiBankbookTemp object
     */
    public JfiBankbookTemp getJfiBankbookTemp(final Long tempId);

    /**
     * Saves a jfiBankbookTemp's information
     * @param jfiBankbookTemp the object to be saved
     */    
    public void saveJfiBankbookTemp(JfiBankbookTemp jfiBankbookTemp);

    /**
     * Removes a jfiBankbookTemp from the database by tempId
     * @param tempId the jfiBankbookTemp's tempId
     */
    public void removeJfiBankbookTemp(final Long tempId);
    //added for getJfiBankbookTempsByCrm
    public List getJfiBankbookTempsByCrm(CommonRecord crm, Pager pager);
    
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
	public long getCountByDate(final String companyCode, final String userCode);

	/**
	 * 批量保存多条记录
	 * @param fiBankbookTemps
	 */
	public void saveJfiBankbookTemps(List jfiBankbookTemps);
	
	public List checkPosNum(final String posNum);
	
	public List checkAllPosNumFromJfiBankBookTemp();
	
	public List checkAllPosNumFromJfiPosImport();
}

