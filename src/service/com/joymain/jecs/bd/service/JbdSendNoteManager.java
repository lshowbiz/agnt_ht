
package com.joymain.jecs.bd.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.bd.model.BdOutwardBank;
import com.joymain.jecs.bd.model.JbdSendNote;
import com.joymain.jecs.bd.dao.JbdSendNoteDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
public interface JbdSendNoteManager extends Manager {
    /**
     * Retrieves all of the jbdSendNotes
     */
    public List getJbdSendNotes(JbdSendNote jbdSendNote);

    /**
     * Gets jbdSendNote's information based on id.
     * @param id the jbdSendNote's id
     * @return jbdSendNote populated jbdSendNote object
     */
    public JbdSendNote getJbdSendNote(final String id);

    /**
     * Saves a jbdSendNote's information
     * @param jbdSendNote the object to be saved
     */
    public void saveJbdSendNote(JbdSendNote jbdSendNote);

    /**
     * Removes a jbdSendNote from the database by id
     * @param id the jbdSendNote's id
     */
    public void removeJbdSendNote(final String id);
    //added for getJbdSendNotesByCrm
    public List getJbdSendNotesByCrm(CommonRecord crm, Pager pager);

	public BigDecimal getSumRemittanceMoney(CommonRecord crm) ;
	/**
	 * 奖金提现
	 * @param companyCode
	 * @param sysUser
	 * @param operaterCode
	 * @param operaterName
	 */
	public void saveJbdSendNoteAndDeductBankbook(final String companyCode, final SysUser sysUser,String operaterCode, final String operaterName, final BigDecimal amount, final String uniqueCode);
	  /**
     * 分配
     */
	public void allotJbdSendNote(String[] adviceCodes, String remittanceBNum,SysUser defSysUserCode) throws AppException;
	/**
	 * 取消分配
	 */
	public void unallotJbdSendNote(String[] adviceCodes) ;
	/**
	 * 登记成功
	 */
	public void saveJbdSendNoteRegister(Date date, String[] strCodes,String sendRemark,SysUser defSysUser) ;
	/**
	 * 转待补发
	 */
	public void saveJbdSendNoteSupply(String[] strCodes, String sendLateCause, String sendLateRemark) ;
	/**
	 * 批量补发成功
	 */
	public void supplyJbdSendNote(Date date, String[] strCodes, String sendRemark, SysUser defSysUser,BdOutwardBank bdOutwardBank) ;
	/**
	 * 更改发放登记
	 */
	public void cancalJbdSendNote(String[] strAdvicesCodes,SysUser defSysUser) ;
	/**
	 * 转不发
	 */
	public void failJbdSendRecordHist(String[] strAdvicesCodes);
}

