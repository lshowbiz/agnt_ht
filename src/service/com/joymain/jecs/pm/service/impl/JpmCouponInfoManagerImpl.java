
package com.joymain.jecs.pm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pm.model.JpmCouponInfo;
import com.joymain.jecs.pm.dao.JpmCouponInfoDao;
import com.joymain.jecs.pm.service.JpmCouponInfoManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpmCouponInfoManagerImpl extends BaseManager implements JpmCouponInfoManager {
    private JpmCouponInfoDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpmCouponInfoDao(JpmCouponInfoDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmCouponInfoManager#getJpmCouponInfos(com.joymain.jecs.pm.model.JpmCouponInfo)
     */
    public List getJpmCouponInfos(final JpmCouponInfo jpmCouponInfo) {
        return dao.getJpmCouponInfos(jpmCouponInfo);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmCouponInfoManager#getJpmCouponInfo(String cpId)
     */
    public JpmCouponInfo getJpmCouponInfo(final String cpId) {
        return dao.getJpmCouponInfo(Long.parseLong(cpId));
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmCouponInfoManager#saveJpmCouponInfo(JpmCouponInfo jpmCouponInfo)
     */
    public void saveJpmCouponInfo(JpmCouponInfo jpmCouponInfo) {
        dao.saveJpmCouponInfo(jpmCouponInfo);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmCouponInfoManager#removeJpmCouponInfo(String cpId)
     */
    public void removeJpmCouponInfo(final String cpId) {
        dao.removeJpmCouponInfo(Long.parseLong(cpId));
    }
    //added for getJpmCouponInfosByCrm
    public List getJpmCouponInfosByCrm(CommonRecord crm, Pager pager){
	return dao.getJpmCouponInfosByCrm(crm,pager);
    }

	@Override
	public int batchAuditCouponInfo(String uniNoStr, String status,String userCode) {
		return dao.batchAuditCouponInfo(uniNoStr,status,userCode);
	}

	/**
     * 统计reportDate当天代金券的使用面额总数
     * 2017-7-17 
     * @param reportDate 时间（天）
     * @return BigDecimal
     */
    public BigDecimal getJpmCouponInfoCpValueS(String reportDate){
    	log.info("在类JpmCouponInfoManagerImpl的方法getJpmCouponInfoCpValueS中运行：代金券面额总数");
    	Map map = dao.getJpmCouponInfoCpValueS(reportDate);
    	if(null!=map){
        	log.info("在类JpmCouponInfoManagerImpl的方法getJpmCouponInfoCpValueS中运行：代金券面额总数-获取cpvalueSum");
    		BigDecimal cpvalueSum = (BigDecimal) map.get("cpvalueSum");
    		return cpvalueSum;
    	}
    	return new BigDecimal(0);
    }
    
    /**
     * 统计reportDate当天订单实际使用的代金券总额
     * 2017-7-17
     * @param reportDate 时间（天）
     * @return BigDecimal
     */
    public BigDecimal getJpoMemberOrderCpValueS(String reportDate){
    	log.info("在类JpmCouponInfoManagerImpl的方法getJpoMemberOrderCpValueS中运行");
    	Map map = dao.getJpoMemberOrderCpValueS(reportDate);
    	if(null!=map){
        	log.info("在类JpmCouponInfoManagerImpl的方法getJpoMemberOrderCpValueS中运行：获取cpvalueSum");
    		BigDecimal cpvalueSum = (BigDecimal) map.get("cpvalueSum");
    		return cpvalueSum;
    	}
    	return new BigDecimal(0);
    	
    }
    
    /**
     * 统计截止到reportDate那天会员使用或未用代金券的总额
     * @param reportDate jsp传到后台的时间（天）
     * @param status 状态 0：未用  1：已用
     * @return BigDecimal
     */
    public BigDecimal getJpmCoumponInfoCpValueS(String reportDate,String status){
    	log.info("在类JpmCouponInfoManagerImpl的方法getJpmCoumponInfoCpValueS中开始运行");
    	Map map = dao.getJpmCoumponInfoCpValueS(reportDate,status);
    	BigDecimal cpvalueSumOne= new BigDecimal(0);
    	BigDecimal cpvalueSumTwo= new BigDecimal(0);
    	if(null!=map){
        	log.info("在类JpmCouponInfoManagerImpl的方法getJpmCoumponInfoCpValueS中运行：获取cpvalueSumOne");
        	BigDecimal cpvalueSumO = (BigDecimal) map.get("cpvalueSum");
        	if(null!=cpvalueSumO){
        		cpvalueSumOne = cpvalueSumO;
        	}
    	}
    	
    	//截止在统计日期reportDate之前，但是在reportDate之后说使用的代金券
    	Map mapYsy = dao.getJpmCoumponInfoReportDateYsy(reportDate,status);
    	if(null!=mapYsy){
        	log.info("在类JpmCouponInfoManagerImpl的方法getJpmCoumponInfoCpValueS中运行：获取cpvalueSumTwo");
        	BigDecimal cpvalueSumT = (BigDecimal) mapYsy.get("cpvalueSum");
        	if(null!=cpvalueSumT){
        		cpvalueSumTwo = cpvalueSumT;
        	}
    	}
    	
    	return cpvalueSumOne.add(cpvalueSumTwo);
    }
	@Override
	public boolean serachJpmCouponInfosByParams(CommonRecord crm) {
		return dao.serachJpmCouponInfosByParams(crm);
	}

	public JpmCouponInfo getByCouponName(String name) {
		return dao.getByCouponName(name);
	}
	
	/**
     * 获取reportDate的前一天
     * modify by fu 2017-7-19 
     * @param reportDate jsp传到后台的时间（天）
     * @return String
     */
    public String getReportDateBefore(String reportDate){
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
    	Calendar c = Calendar.getInstance(); 
    	Date date=null; 
    	String dayBefore = "";
    	try { 
        	log.info("在类JpmCouponInfoManagerImpl的方法getReportDateBefore中运行");
	    	date = simpleDateFormat.parse(reportDate); 
	    	c.setTime(date); 
	    	int day=c.get(Calendar.DATE); 
	    	c.set(Calendar.DATE,day-1); 
	    	dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	    	return dayBefore; 
    	}catch (Exception e) { 
        	log.info("在类JpmCouponInfoManagerImpl的方法getReportDateBefore中运行异常："+e.toString());
    	    e.printStackTrace();
    	    return dayBefore; 
    	} 
    	
    }
    
    /**
     * 获取reportDate当天升级单赠送的代金券的总面额
     * modify by fu 2017-7-19 
     * @param reportDate jsp传到后台的时间（天）
     * @return BigDecimal
     */
    public BigDecimal getUpgradeSheetCpValueS(String reportDate){
    	log.info("在类JpmCouponInfoManagerImpl的方法getUpgradeSheetCpValueS中开始运行");
    	Map map = dao.getUpgradeSheetCpValueS(reportDate);
    	if(null!=map){
        	log.info("在类JpmCouponInfoManagerImpl的方法getUpgradeSheetCpValueS中运行：获取cpvalueSum");
    		BigDecimal cpvalueSum = (BigDecimal) map.get("cpvalueSum");
    		return cpvalueSum;
    	}
    	return new BigDecimal(0);
    }

}
