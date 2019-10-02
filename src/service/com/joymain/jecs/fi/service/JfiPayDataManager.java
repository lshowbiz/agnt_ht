
package com.joymain.jecs.fi.service;

import java.util.HashMap;
import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiPayData;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
public interface JfiPayDataManager extends Manager {
    /**
     * Retrieves all of the jfiPayDatas
     */
    public List getJfiPayDatas(JfiPayData jfiPayData);

    /**
     * Gets jfiPayData's information based on dataId.
     * @param dataId the jfiPayData's dataId
     * @return jfiPayData populated jfiPayData object
     */
    public JfiPayData getJfiPayData(final String dataId);

    /**
     * Saves a jfiPayData's information
     * @param jfiPayData the object to be saved
     */
    public void saveJfiPayData(JfiPayData jfiPayData);

    /**
     * Removes a jfiPayData from the database by dataId
     * @param dataId the jfiPayData's dataId
     */
    public void removeJfiPayData(final String dataId);
    //added for getJfiPayDatasByCrm
    public List getJfiPayDatasByCrm(CommonRecord crm, Pager pager);

	/**
	 * 核实银行到款数据
	 * @param dataId
	 * @param userCode
	 * @param adviceCode
	 * @param operaterCode
	 * @param operaterName
	 * @param noticeMsgPattern
	 */
	public void saveJfiPayDataVerify(final String dataId, final String userCode, String adviceCode, final String operaterCode,
	        final String operaterName, final String noticeMsgPattern) throws Exception;

	/**
	 * 取消核实银行到款数据
	 * @param fiPayData
	 * @param userCode
	 * @param operaterCode
	 * @param operaterName
	 * @param noticeMsgPattern
	 */
	public void saveJfiPayDataUnVerify(final String dataId, final String userCode, final String operaterCode,
	        final String operaterName, final String unVerifyRemark, final String noticeMsgPattern) throws AppException;

	/**
	 * 根据外部条件获取对应的到款数据统计
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getJfiPayDataStatsByCrm(CommonRecord crm, Pager pager);

	/**
	 * 要看外部条件获取对应的到款数总额
	 * @param crm
	 * @return
	 */
	public HashMap getJfiPayDataSum(CommonRecord crm);

	/**
	 * 批量保存多条记录
	 * @param fiPayDatas
	 */
	public void saveJfiPayDatas(List jfiPayDatas);
	
	/**
	 * 获取各银行(未)到款对应的统计
	 * @param companyCode
	 * @param startDealDate
	 * @param endDealDate
	 * @return
	 */
	public List getJfiPayDataGroupTotal(final String companyCode, final String startDealDate, final String endDealDate);
}

