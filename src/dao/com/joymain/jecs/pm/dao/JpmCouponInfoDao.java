
package com.joymain.jecs.pm.dao;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pm.model.JpmCouponInfo;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmCouponInfoDao extends Dao {

    /**
     * Retrieves all of the jpmCouponInfos
     */
    public List getJpmCouponInfos(JpmCouponInfo jpmCouponInfo);

    /**
     * Gets jpmCouponInfo's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param cpId the jpmCouponInfo's cpId
     * @return jpmCouponInfo populated jpmCouponInfo object
     */
    public JpmCouponInfo getJpmCouponInfo(final long cpId);

    /**
     * Saves a jpmCouponInfo's information
     * @param jpmCouponInfo the object to be saved
     */    
    public void saveJpmCouponInfo(JpmCouponInfo jpmCouponInfo);

    /**
     * Removes a jpmCouponInfo from the database by cpId
     * @param cpId the jpmCouponInfo's cpId
     */
    public void removeJpmCouponInfo(final long cpId);
    //added for getJpmCouponInfosByCrm
    public List getJpmCouponInfosByCrm(CommonRecord crm, Pager pager);
    
    public int batchAuditCouponInfo(String uniNoStr,String status,String userCode);

    /**
     * 统计reportDate当天代金券的使用面额总数
     * 2017-7-17 
     * @param reportDate 时间（天）
     * @return Map
     */
	public Map getJpmCouponInfoCpValueS(String reportDate);

	/**
     * 统计reportDate当天订单实际使用的代金券总额
     * 2017-7-17
     * @param reportDate 时间（天）
     * @return Map
     */
	public Map getJpoMemberOrderCpValueS(String reportDate);

	/**
     * 统计截止到reportDate那天会员使用或未用代金券的总额
     * @param reportDate jsp传到后台的时间（天）
     * @param status 状态 0：未用  1：已用
     * @return Map
     */
	public Map getJpmCoumponInfoCpValueS(String reportDate, String status);
	public boolean serachJpmCouponInfosByParams(CommonRecord crm);
	/**
     * 根据名称查询代金券
     * @param name
     * @return
     */
    public JpmCouponInfo getByCouponName(String name);

    /**
     * 获取reportDate当天升级单赠送的代金券的总面额
     * modify by fu 2017-7-19 
     * @param reportDate jsp传到后台的时间（天）
     * @return Map
     */
	public Map getUpgradeSheetCpValueS(String reportDate);

	
	/**
	 * 统计截止到reportDate之前的时间赠送的代金券，但是这部分代金券在reportDate时间之后使用的
	 * modify by fu 2017-7-27
	 * @param reportDate 时间（天）
	 * @param status 状态
	 * @return Map
	 */
	public Map getJpmCoumponInfoReportDateYsy(String reportDate, String status);
	
}

