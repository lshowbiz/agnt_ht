
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.MailStatus;
import com.joymain.jecs.pd.model.PdMailReceipt;
import com.joymain.jecs.pd.model.PdSendInfo;
import com.joymain.jecs.pd.model.RspEntity;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface MailStatusManager extends Manager {
    /**
     * Retrieves all of the mailStatuss
     */
    public List getMailStatuss(MailStatus mailStatus);

    /**
     * Gets mailStatus's information based on statusId.
     * @param statusId the mailStatus's statusId
     * @return mailStatus populated mailStatus object
     */
    public MailStatus getMailStatus(final Long statusId);

    /**
     * 物流跟踪单号信息保存的功能
     * @author gw 2014-12-03
     * @param mailStatusJson 接口传递过来的json字符串
     * @return String  
     */
    //返回数据类型说明：
    //正常返回的值INTERFACE_NORMAL = "interfaceNormal";
    //接口传递过来的数据保存是报异常INTERFACE_SAVE_EXCEPTION = "interfaceSaveException";
    //接口传递过来的数据是非法的，比如传个空值，传个无效字符等INTERFACE_ILLEGAL_DATA = "interfaceIllegalData";
    //接口传递过来的数据为空的INTERFACE_NULL = "interfaceNull"
    public RspEntity saveMailStatuss(String mailStatusJson);

    /**
     * Removes a mailStatus from the database by statusId
     * @param statusId the mailStatus's statusId
     */
    public void removeMailStatus(final Long statusId);
    //added for getMailStatussByCrm
    public List getMailStatussByCrm(CommonRecord crm, Pager pager);
    
    /**
	 * 根据物流跟踪单号查询物流的实时信息
	 * @author fu   2015-11-17
	 * @param  mailNo 物流跟踪单号的组合字符串(各个物流单号之间用逗号隔开)
	 * @param siNo,memberOrderNo modify by fu 2015-11-27
	 * @param pdSendInfo 发货单  modify by fu 2016-1-18 
	 * @return List
	 */
	public List sendInterfaceByMailStatus(String mailNo,String siNo,String memberOrderNo,PdSendInfo pdSendInfo);

	/**
	 * 保存或更新物流单号实时信息(定时器用到)
	 * @author gw 2015
	 * @param mailStatus json转化过来的对象mailStatus
	 */
	public boolean sOrUMailStatus(MailStatus mailStatus,Long numId); 
	
	/**
	 * 获取主键
	 */
	public Long getId();

	/**
	 * 在容灾库保存pdMailReceipt
	 * @author modify by fu 2016-01-28
	 * @param pdMailReceipt
     * @return 
	 */
	public void ssavePdMailReceiptReport(PdMailReceipt pdMailReceipt);
	
}

