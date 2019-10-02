
package com.joymain.jecs.pd.service;

import java.util.List;


import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdLogisticsBase;
import com.joymain.jecs.pd.model.RspEntity;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdLogisticsBaseManager extends Manager {
    /**
     * Retrieves all of the pdLogisticsBases
     */
    public List getPdLogisticsBases(PdLogisticsBase pdLogisticsBase);

    /**
     * Gets pdLogisticsBase's information based on baseId.
     * @param baseId the pdLogisticsBase's baseId
     * @return pdLogisticsBase populated pdLogisticsBase object
     */
    public PdLogisticsBase getPdLogisticsBase(final String baseId);

    /**
     * Saves a pdLogisticsBase's information
     * @param pdLogisticsBase the object to be saved
     */
    public void savePdLogisticsBase(PdLogisticsBase pdLogisticsBase);

    /**
     * Removes a pdLogisticsBase from the database by baseId
     * @param baseId the pdLogisticsBase's baseId
     */
    public void removePdLogisticsBase(final String baseId);
    //added for getPdLogisticsBasesByCrm
    public List getPdLogisticsBasesByCrm(CommonRecord crm, Pager pager);
    
    
    /**
     * 保存\更新JOCS发货单的真实发货数据
     * @author gw 2014-11-26
     * @param pdLogisticsBaseList 传递过来的接口数据转换成的list集合
     */
    public RspEntity savePdLogisticsBaseList(String jsonString);

    
    /**
     * 保存或修改DO（WMS的do)的信息--保存或修改接口的信息数据
     * @author gw 2014-11-26
     * @param pdLogisticsBase 传递过来的接口数据转换成的pdLogisticsBase对象
     */
    public RspEntity savePdLogisticsBaseByInter(String jsonString);
    
    /**
	 * 修改物流单号信息
	 * @author gw 2015-01-25
	 * @param pdLogisticsBase
	 */
    public void updateMaillist(PdLogisticsBase pdLogisticsBase);
    
    /**
	 * 更新的LO单（发货单）数据后，同步的将相关的发货单状态改为已发货,将符合条件的订单转换成已发货状态
	 * @author gw 2014-12-02
	 * @param barCode 条形码 modify by fu 
	 * @param 
	 */
    public String setPdSendInfoShipped(List<PdLogisticsBase> pdLogisticsBaseList,String barCode);
    
    /**
	 * 确认收货状态的修改
	 * @author gw 2015-02-01
	 * @param pdLogisticsBase
	 * @return 
	 */
    public void confirmReceiptTotal(PdLogisticsBase pdLogisticsBase);
    
    /**
     * 定时器-根据物流单号获取物流信息(每小时查询并保存一次）
     * @author gw 2015-06-15
     * 
     */
    public void gainMailStatus();

    /**
     * 判断发货单有没有关联的DO单传过来
     * @author fu 2015-09-16
     * @param siNo
     * @return boolean
     */
	public boolean getDoResult(String siNo);

	/**
	 * DO保存成功，发送短信
	 * @author gw 2015-09-24
	 * @param content
	 */
	public void sendMessage(String content);
	
	/**
     * 定时器-根据物流单号获取物流信息(每小时查询并保存一次）
     * @author gw 2016-02-16
     * 
     */
	public boolean suMailStatus(String jsonString,String status,Long numId,String baseId);
    
}

