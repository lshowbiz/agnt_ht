
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.MailStatus;
import com.joymain.jecs.pd.model.PdMailReceipt;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface MailStatusDao extends Dao {

    /**
     * Retrieves all of the mailStatuss
     */
    public List getMailStatuss(MailStatus mailStatus);

    /**
     * Gets mailStatus's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param statusId the mailStatus's statusId
     * @return mailStatus populated mailStatus object
     */
    public MailStatus getMailStatus(final Long statusId);

    /**
     * Saves a mailStatus's information
     * @param mailStatus the object to be saved
     */    
    public String saveMailStatus(MailStatus mailStatus);

    /**
     * Removes a mailStatus from the database by statusId
     * @param statusId the mailStatus's statusId
     */
    public void removeMailStatus(final Long statusId);
    //added for getMailStatussByCrm
    public List getMailStatussByCrm(CommonRecord crm, Pager pager);

    /**
     * 根据物流单号查询物流跟踪的信息
     * @author gw 2015-01-10
     * @param mailNo 物流单号
     * @return mailStatus
     */
	public MailStatus getMailStatusByMailNo(String mailNo);
	
	/**
	 * 获取主键
	 */
	public Long getId();

	/**
     * 根据物流单号查询物流跟踪的信息----容灾库查询
     * @author gw 2016-01-27
     * @param mailNo 物流单号
     * @return Long
     */
	public Long getMailStatusByMailNoReport(String mailNo);

	
	/**
     * 容灾库保存物流实时信息
     * @author gw 2016-01-27
     * @param mailStatus
     * @return String
     */
	public String sOrUMailStatusReport(MailStatus mailStatus,Long numId);

	/**
	 * 在容灾库保存pdMailReceipt
	 * @author modify by fu 2016-01-28
	 * @param pdMailReceipt
     * @return 
	 */
	public void ssavePdMailReceiptReport(PdMailReceipt pdMailReceipt);
	
}

