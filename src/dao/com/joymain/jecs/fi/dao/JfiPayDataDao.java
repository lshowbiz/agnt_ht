
package com.joymain.jecs.fi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiPayData;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiPayDataDao extends Dao {

    /**
     * Retrieves all of the jfiPayDatas
     */
    public List getJfiPayDatas(JfiPayData jfiPayData);

    /**
     * Gets jfiPayData's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param dataId the jfiPayData's dataId
     * @return jfiPayData populated jfiPayData object
     */
    public JfiPayData getJfiPayData(final Long dataId);

    /**
     * Saves a jfiPayData's information
     * @param jfiPayData the object to be saved
     */    
    public void saveJfiPayData(JfiPayData jfiPayData);

    /**
     * Removes a jfiPayData from the database by dataId
     * @param dataId the jfiPayData's dataId
     */
    public void removeJfiPayData(final Long dataId);
    //added for getJfiPayDatasByCrm
    public List getJfiPayDatasByCrm(CommonRecord crm, Pager pager);

	/**
	 * 批量保存多条记录
	 * @param fiPayDatas
	 */
	public void saveJfiPayDatas(List jfiPayDatas);
	
	/**
	 * 通过存储过程审核到款数据
	 * @param dataId
	 * @param adviceCode
	 * @param operaterCode
	 * @param operaterName
	 * @param notice
	 * @return
	 */
	public Map saveJfiPayDataVerifyProc(final String dataId, String adviceCode, final String operaterCode, final String operaterName,
	        final String notice);
	
	/**
	 * 通过存储过程取消审核到款数据
	 * @param dataId
	 * @param operaterCode
	 * @param operaterName
	 * @param unVerifyRemark
	 * @param notice
	 * @return
	 */
	public Map saveJfiPayDataUnverifyProc(final String dataId, final String operaterCode, final String operaterName,
	        final String unVerifyRemark, final String notice);

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
	 * 获取各银行(未)到款对应的统计
	 * @param companyCode
	 * @param startDealDate
	 * @param endDealDate
	 * @return
	 */
	public List getJfiPayDataGroupTotal(final String companyCode, final String startDealDate, final String endDealDate);
}

