
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdLogisticsBase;
import com.joymain.jecs.pd.model.PdLogisticsBaseNum;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdLogisticsBaseDao extends Dao {

    /**
     * Retrieves all of the pdLogisticsBases
     */
    public List getPdLogisticsBases(PdLogisticsBase pdLogisticsBase);

    /**
     * Gets pdLogisticsBase's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param baseId the pdLogisticsBase's baseId
     * @return pdLogisticsBase populated pdLogisticsBase object
     */
    public PdLogisticsBase getPdLogisticsBase(final BigDecimal baseId);

    /**
     * Saves a pdLogisticsBase's information
     * @param pdLogisticsBase the object to be saved
     */    
    public void savePdLogisticsBase(PdLogisticsBase pdLogisticsBase);

    /**
     * Removes a pdLogisticsBase from the database by baseId
     * @param baseId the pdLogisticsBase's baseId
     */
    public void removePdLogisticsBase(final BigDecimal baseId);
    //added for getPdLogisticsBasesByCrm
    public List getPdLogisticsBasesByCrm(CommonRecord crm, Pager pager);

	/**
	 * 根据发货单号和do号查询
	 * @author gw 2014-12-03
	 * @param pdLogisticsBase
	 * @return list
	 */
	public List getPdLogisticsBaseListByBin(PdLogisticsBase pdLogisticsBase);

	/**
	 * DO的仓内作业的保存方法
	 * @author gw 2014-12-03
	 * @param  pdLogisticsBaseList 根据发货单号和wmsDo查询pdLogisticsBase的结果集
	 * @param  pdLogisticsBase
	 * @return string
	 */
	public String getBinResult(List pdLogisticsBaseList,PdLogisticsBase pdLogisticsBase);

	/**
	 * 判断数据库中是否存在这条非仓内作业的数据
	 * @author gw 2014-12-03
	 * @param pdLogisticsBase
	 * @return pdLogisticsBase
	 */
	public PdLogisticsBase getPdLogisticsBaseFirstNotBin(PdLogisticsBase pdLogisticsBase);

	/**
	 * 接口数据保存
	 * @author gw 2014-12-03
	 * @param pdLogisticsBase
	 * @return
	 */
	public String saveFirstNotBinPdLogisticsBase(PdLogisticsBase pdLogisticsBase);

	/**
	 * 后面几次非仓内作业的DO接口数据的保存
	 * @author 2014-12-03
	 * @param pdLogisticsBaseThree
	 * @return string
	 */
	public String savePdLogisticsBaseTheSecondNotBin(PdLogisticsBase pdLogisticsBaseThree);

	/**
	 * 保存合法LO的数据
	 * @author gw 2014-12-03
	 * @param listOne
	 * @return
	 */
	public String saveLoAll(List<PdLogisticsBase> listOne);
	
	/**
	 * Add By WuCF 201411
	 * 中心物流状态结构数据接收并修改本地订单明细数据：仓内作业状态、物流跟踪单号、确认收货状态；
	 * @param pdLogisticsBase
	 */
	public void updateJpoMemberOrderList(PdLogisticsBase pdLogisticsBase);

	/**
	 * 定义ID
	 * @author gw 2014-12-25
	 * @return Long
	 */
	public Long definitionIdGenerate();

	/**
	 * 修改仓内作业的状态
	 * @author gw 2015-01-25
	 * @param pdLogisticsBase
	 */
	public void updatePdLogisticsBaseStatus(PdLogisticsBase pdLogisticsBase);

	/**
	 * 修改物流单号信息---吃方法弃用
	 * @author gw 2015-01-25
	 * @param pdLogisticsBaseT
	 */
	public void updatePdLogisticsBaseNumno(PdLogisticsBaseNum pdLogisticsBaseNum);

	/**
	 * 确认收货
	 * @author WuCF
	 * 2015-01-30
	 */
	public void confirmReceipt(PdLogisticsBase pdLogisticsBase);
	
	/**
     * 判断发货单有没有关联的DO单传过来
     * @author fu 2015-09-16
     * @param siNo
     * @return boolean
     */
	public boolean getDoResult(String siNo);

	/**
	 * 给表的pd_logistics_base字段other_one赋值为Y，表示Y表示已经查询过物流实时信息查询了,不需要进行再查询的
	 * @author fu 2016-05-05
	 * @param baseId  
	 * @return int 
	 */
	public int reSetOtherOne(String baseId);
}

