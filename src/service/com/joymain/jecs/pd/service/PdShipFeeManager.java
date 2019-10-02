
package com.joymain.jecs.pd.service;

import java.math.BigDecimal;
import java.util.List;

import com.joymain.jecs.pd.model.PdShipFee;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdShipFeeManager extends Manager {
	/**
	 * 会员首购单，升级单实付金额<=6000元，辅消品<=500支付相应收货省份的物流费用
	 * @param amout（订单实付金额）
	 * @param orderType（订单类型）
	 * @param provinceName（省份编码）
	 * @return
	 */
	public BigDecimal countFee(String amout, String criticalPoint, String provinceName,boolean b);
    /**
     * Retrieves all of the pdShipFees
     */
    public List getPdShipFees(PdShipFee pdShipFee);

    /**
     * Gets pdShipFee's information based on psfId.
     * @param psfId the pdShipFee's psfId
     * @return pdShipFee populated pdShipFee object
     */
    public PdShipFee getPdShipFee(final String psfId);

    /**
     * Saves a pdShipFee's information
     * @param pdShipFee the object to be saved
     */
    public void savePdShipFee(PdShipFee pdShipFee);

    /**
     * Removes a pdShipFee from the database by psfId
     * @param psfId the pdShipFee's psfId
     */
    public void removePdShipFee(final String psfId);
    //added for getPdShipFeesByCrm
    public List getPdShipFeesByCrm(CommonRecord crm, Pager pager);
    
    //Add By WuCF 20140813 shell脚本调用存储过程
    public List getPdExportLogByCrm(CommonRecord crm, Pager pager);
    
    //Add By WuCF 20140909 短信查询功能
    public List getJpmSmssendInfoByCrm(CommonRecord crm, Pager pager);
	
    /**
     * 保存之前先做同一个地区的唯一性校验
     * @author gw 2015-01-20
     * @param pdShipFee
     */
    public boolean getUniqueResult(PdShipFee pdShipFee);
    
  //Add By WuCF 20141118_接口日志数据
    public List getJocsInterfaceLogByCrm(CommonRecord crm, Pager pager);
    
    //Add By WuCF 20141208_接口日志数据重发
    public List getJocsInterfaceRetransmisionByCrm(CommonRecord crm, Pager pager);
}

