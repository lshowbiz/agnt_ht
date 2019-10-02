
package com.joymain.jecs.pm.service;

import java.math.BigDecimal;
import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pm.model.JpmCouponInfo;
import com.joymain.jecs.pm.dao.JpmCouponInfoDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpmCouponInfoManager extends Manager {
    /**
     * Retrieves all of the jpmCouponInfos
     */
    public List getJpmCouponInfos(JpmCouponInfo jpmCouponInfo);

    /**
     * Gets jpmCouponInfo's information based on cpId.
     * @param cpId the jpmCouponInfo's cpId
     * @return jpmCouponInfo populated jpmCouponInfo object
     */
    public JpmCouponInfo getJpmCouponInfo(final String cpId);

    /**
     * Saves a jpmCouponInfo's information
     * @param jpmCouponInfo the object to be saved
     */
    public void saveJpmCouponInfo(JpmCouponInfo jpmCouponInfo);

    /**
     * 批量审核代金券
     * @param uniNoStr:代金券ID字符串：用逗号隔开
     * @return
     */
    public int batchAuditCouponInfo(String cpId,String status,String userCode);
    
    /**
     * Removes a jpmCouponInfo from the database by cpId
     * @param cpId the jpmCouponInfo's cpId
     */
    public void removeJpmCouponInfo(final String cpId);
    //added for getJpmCouponInfosByCrm
    public List getJpmCouponInfosByCrm(CommonRecord crm, Pager pager);
    
    /**
     * 统计reportDate当天代金券的使用面额总数
     * 2017-7-17 
     * @param reportDate 时间（天）
     * @return BigDecimal
     */
    public BigDecimal getJpmCouponInfoCpValueS(String reportDate);
    
    /**
     * 统计reportDate当天订单实际使用的代金券总额
     * 2017-7-17
     * @param reportDate 时间（天）
     * @return BigDecimal
     */
    public BigDecimal getJpoMemberOrderCpValueS(String reportDate);
    
    /**
     * 统计截止到reportDate那天会员使用或未用代金券的总额
     * @param reportDate jsp传到后台的时间（天）
     * @param status 状态 0：未用  1：已用
     * @return BigDecimal
     */
    public BigDecimal getJpmCoumponInfoCpValueS(String reportDate,String status);
    public boolean serachJpmCouponInfosByParams(CommonRecord crm);
    /**
     * 根据名称查询
     * @param name
     * @return
     */
    public JpmCouponInfo getByCouponName(String name);
    
    /**
     * 获取reportDate的前一天
     * modify by fu 2017-7-19 
     * @param reportDate jsp传到后台的时间（天）
     * @return String
     */
    public String getReportDateBefore(String reportDate);
    
    /**
     * 获取reportDate当天升级单赠送的代金券的总面额
     * modify by fu 2017-7-19 
     * @param reportDate jsp传到后台的时间（天）
     * @return BigDecimal
     */
    public BigDecimal getUpgradeSheetCpValueS(String reportDate);

    
}

