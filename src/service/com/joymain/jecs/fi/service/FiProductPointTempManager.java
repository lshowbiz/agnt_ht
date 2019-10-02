
package com.joymain.jecs.fi.service;

import java.util.List;
import java.util.Map;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.FiProductPointTemp;
import com.joymain.jecs.fi.dao.FiProductPointTempDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface FiProductPointTempManager extends Manager {
    /**
     * Retrieves all of the fiProductPointTemps
     */
    public List getFiProductPointTemps(FiProductPointTemp fiProductPointTemp);

    /**
     * Gets fiProductPointTemp's information based on tempId.
     * @param tempId the fiProductPointTemp's tempId
     * @return fiProductPointTemp populated fiProductPointTemp object
     */
    public FiProductPointTemp getFiProductPointTemp(final String tempId);

    /**
     * Saves a fiProductPointTemp's information
     * @param fiProductPointTemp the object to be saved
     */
    public void saveFiProductPointTemp(FiProductPointTemp fiProductPointTemp);

    /**
     * Removes a fiProductPointTemp from the database by tempId
     * @param tempId the fiProductPointTemp's tempId
     */
    public void removeFiProductPointTemp(final String tempId);
    //added for getFiProductPointTempsByCrm
    public List getFiProductPointTempsByCrm(CommonRecord crm, Pager pager);
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
	public long getCountByDate(final String companyCode, final String userCode, final String ProductPointType);

	/**
	 * 批量保存多条记录
	 * @param fiProductPointTemps
	 */
	public void saveFiProductPointTemps(List fiProductPointTemps);
}

