
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdLogisticsBaseNum;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdLogisticsBaseNumDao extends Dao {

    /**
     * Retrieves all of the pdLogisticsBaseNums
     */
    public List getPdLogisticsBaseNums(PdLogisticsBaseNum pdLogisticsBaseNum);

    /**
     * Gets pdLogisticsBaseNum's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param numId the pdLogisticsBaseNum's numId
     * @return pdLogisticsBaseNum populated pdLogisticsBaseNum object
     */
    public PdLogisticsBaseNum getPdLogisticsBaseNum(final BigDecimal numId);

    /**
     * Saves a pdLogisticsBaseNum's information
     * @param pdLogisticsBaseNum the object to be saved
     */    
    public void savePdLogisticsBaseNum(PdLogisticsBaseNum pdLogisticsBaseNum);

    /**
     * Removes a pdLogisticsBaseNum from the database by numId
     * @param numId the pdLogisticsBaseNum's numId
     */
    public void removePdLogisticsBaseNum(final BigDecimal numId);
    //added for getPdLogisticsBaseNumsByCrm
    public List getPdLogisticsBaseNumsByCrm(CommonRecord crm, Pager pager);
    
    /**
     * @author gw 2014-12-01 根据baseId,PdLogisticsBaseNum_no查询
     * @param baseId 关联到物流状态接口物流信息表主键（本表外键）
     * @param PdLogisticsBaseNum_no  物流单号
     * @param otherOne do单号+wmsDo+物流单号  modify fu 2015-11-26
     * @return pdLogisticsBaseNum
     */
    public  PdLogisticsBaseNum getPdLogisticsBaseNumByBaseIdAndNo(String baseId,String PdLogisticsBaseNum_no,String otherOne);

	public List getPdLogisticsBaseNumByInter(Long baseId);

	/**
	 * 根据物流单号查询物流信息
	 * @author gw 2015-02-01
	 * @param pdLogisticsBaseNumNo 物流单号
	 * @return pdLogisticsBaseNum
	 */
	public PdLogisticsBaseNum getPdLogisticsBaseNumByPdLogisticsBaseNumno(String pdLogisticsBaseNumNo);

	/**
	 * 获取未完整获取物流单号信息的PdLogisticsBaseNum-定时器用到
	 * @author gw 2015-06-15
	 * @return
	 */
	public List<PdLogisticsBaseNum> getPdLogisticsBaseNumForPdMailReceipt();

	/**
	 * 根据物流单号和订单号号或发货单号获取物流信息
	 * @author gw 2015-11-27
	 * @param pdLogisticsBaseNumNo 物流单号
	 * @return List
	 */
	public List<PdLogisticsBaseNum> getPdLogisticsBaseNumByParams(String mailNob,String siNo, String memberOrderNo);
	
	/**
	 * 物流查询定时器用到---获取DO相关的信息
	 * @author fu 2016-02-16
	 * @return list
	 */
	public List getDoForSql();
    
}

