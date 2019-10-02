
package com.joymain.jecs.bd.service.impl;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.bd.model.BdOutwardBank;
import com.joymain.jecs.bd.model.JbdBonusBalance;
import com.joymain.jecs.bd.model.JbdSendNote;
import com.joymain.jecs.bd.model.JbdSendRecordHist;
import com.joymain.jecs.bd.dao.JbdSendNoteDao;
import com.joymain.jecs.bd.service.JbdSendNoteManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
public class JbdSendNoteManagerImpl extends BaseManager implements JbdSendNoteManager {
    private JbdSendNoteDao dao;
    private JfiBankbookJournalManager jfiBankbookJournalManager = null;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdSendNoteDao(JbdSendNoteDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSendNoteManager#getJbdSendNotes(com.joymain.jecs.bd.model.JbdSendNote)
     */
    public List getJbdSendNotes(final JbdSendNote jbdSendNote) {
        return dao.getJbdSendNotes(jbdSendNote);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSendNoteManager#getJbdSendNote(String id)
     */
    public JbdSendNote getJbdSendNote(final String id) {
        return dao.getJbdSendNote(new Long(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSendNoteManager#saveJbdSendNote(JbdSendNote jbdSendNote)
     */
    public void saveJbdSendNote(JbdSendNote jbdSendNote) {
        dao.saveJbdSendNote(jbdSendNote);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSendNoteManager#removeJbdSendNote(String id)
     */
    public void removeJbdSendNote(final String id) {
        dao.removeJbdSendNote(new Long(id));
    }
    //added for getJbdSendNotesByCrm
    public List getJbdSendNotesByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdSendNotesByCrm(crm,pager);
    }

	public BigDecimal getSumRemittanceMoney(CommonRecord crm) {
		return dao.getSumRemittanceMoney(crm);
	}

	/**
	 * 奖金提现
	 * @param companyCode
	 * @param sysUser
	 * @param operaterCode
	 * @param operaterName
	 * @param amount
	 */
	public void saveJbdSendNoteAndDeductBankbook(final String companyCode, final SysUser sysUser,String operaterCode, final String operaterName, final BigDecimal amount, final String uniqueCode){
		JbdSendNote jbdSendNote = new JbdSendNote();
		jbdSendNote.setCompanyCode(companyCode);
		jbdSendNote.setCreateNo(operaterCode);
		jbdSendNote.setCreateTime(new Date());
		jbdSendNote.setJmiMember(sysUser.getJmiMember());
		jbdSendNote.setRegisterStatus("1");
		jbdSendNote.setReissueStatus("1");
		BigDecimal fee = new BigDecimal("3");
		if(amount.compareTo(new BigDecimal("500"))!=-1){
			fee = new BigDecimal("1");
		}
		BigDecimal subtractAmount = amount.subtract(fee);
		jbdSendNote.setRemittanceMoney(subtractAmount);
		jbdSendNote.setFee(fee);
		dao.saveObject(jbdSendNote);
		Integer[] moneyType = {55,56};
		BigDecimal[] moneyArray = {fee,subtractAmount};
		String[] notes = {"奖金提现手续费","奖金提现"};
		jfiBankbookJournalManager.saveJfiBankbookDeduct(companyCode, sysUser, moneyType, moneyArray, operaterCode, operaterName, uniqueCode, notes,"1");
	}
	
	  /**
     * 分配
     */
	public void allotJbdSendNote(String[] adviceCodes, String remittanceBNum,SysUser defSysUserCode) throws AppException{
		for (int i = 0; i < adviceCodes.length; i++) {
			if (!StringUtils.isEmpty(adviceCodes[i])) {

				JbdSendNote jbdSendNote=dao.getJbdSendNote(new Long(adviceCodes[i]));
				if(null!=jbdSendNote.getOperaterCode()||!StringUtil.isEmpty(jbdSendNote.getRemittanceBNum())){
					throw new AppException("operation.notice.js.bdSendRecord.alloted");
				}else{
					
					jbdSendNote.setRemittanceBNum(remittanceBNum);
					jbdSendNote.setOperaterCode(defSysUserCode.getUserCode());
					dao.saveJbdSendNote(jbdSendNote);
					
				}
			}
			
		}
	}
	/**
	 * 取消分配
	 */
	public void unallotJbdSendNote(String[] adviceCodes) {
		for (int i = 0; i < adviceCodes.length; i++) {
			if (!StringUtils.isEmpty(adviceCodes[i])) {
				JbdSendNote jbdSendNote=dao.getJbdSendNote(new Long(adviceCodes[i]));
				if(StringUtil.isEmpty(jbdSendNote.getOperaterCode())&&StringUtil.isEmpty(jbdSendNote.getRemittanceBNum())){
					throw new AppException("operation.notice.js.bdSendRecord.unAllot");
				}else{
	
					jbdSendNote.setRemittanceBNum("");
					jbdSendNote.setOperaterCode("");
					dao.saveJbdSendNote(jbdSendNote);
				}
			}
		}
	}
	/**
	 * 登记成功
	 */
	public void saveJbdSendNoteRegister(Date date, String[] strCodes,String sendRemark,SysUser defSysUser) {

		Date curDate=new Date();
		for (int i = 0; i < strCodes.length; i++) {
			if (!StringUtils.isEmpty(strCodes[i])) {
				JbdSendNote jbdSendNote= dao.getJbdSendNote(new Long(strCodes[i]));
				if(null!=jbdSendNote.getSendDate()){
					throw new AppException("bd.send.sended");
				}
				String sendRemarkStr="";
				sendRemarkStr+=sendRemark;
				jbdSendNote.setRegisterStatus("2");
				jbdSendNote.setSendDate(date);
				jbdSendNote.setSendRemark(sendRemarkStr);
				jbdSendNote.setOperaterTime(curDate);
				
				dao.saveJbdSendNote(jbdSendNote);
			}
		}
	}
	/**
	 * 转待补发
	 */
	public void saveJbdSendNoteSupply(String[] strCodes, String sendLateCause, String sendLateRemark) {

		Date curDate=new Date();
		for (int i = 0; i < strCodes.length; i++) {
			if (!StringUtils.isEmpty(strCodes[i])) {
				JbdSendNote jbdSendNote= dao.getJbdSendNote(new Long(strCodes[i]));
				
				jbdSendNote.setReissueStatus("2");//补发为2待补发
				jbdSendNote.setRegisterStatus("3");//登记状态为3失败
				jbdSendNote.setSendLateCause(sendLateCause);
				jbdSendNote.setSendLateRemark(sendLateRemark);
				jbdSendNote.setOperaterTime(curDate);

				dao.saveJbdSendNote(jbdSendNote);
			}
		}
	}
	/**
	 * 批量补发成功
	 */
	public void supplyJbdSendNote(Date date, String[] strCodes, String sendRemark, SysUser defSysUser,BdOutwardBank bdOutwardBank) {

		Date curDate=new Date();
		for (int i = 0; i < strCodes.length; i++) {
			if (!StringUtils.isEmpty(strCodes[i])) {
				JbdSendNote jbdSendNote = dao.getJbdSendNote(new Long(strCodes[i]));
				if(null!=jbdSendNote.getSendDate()){
					throw new AppException("bd.send.sended");
				}
				String sendRemarkStr="";
				sendRemarkStr+=sendRemark;
				
				jbdSendNote.setRemittanceBNum(bdOutwardBank.getBankCode());
				jbdSendNote.setOperaterCode(defSysUser.getUserCode());
				jbdSendNote.setSendDate(date);
				jbdSendNote.setReissueStatus("3");
				jbdSendNote.setRegisterStatus("2");
				jbdSendNote.setOperaterTime(curDate);
		    	jbdSendNote.setSupplyTime(date);
		    	jbdSendNote.setSendRemark(sendRemarkStr);
		    	
		    	this.saveJbdSendNote(jbdSendNote);
		    	
			}
		}
	}
	/**
	 * 更改发放登记
	 */
	public void cancalJbdSendNote(String[] strAdvicesCodes,SysUser defSysUser) {
		for (int i = 0; i < strAdvicesCodes.length; i++) {
			if (!StringUtils.isEmpty(strAdvicesCodes[i])) {
				JbdSendNote jbdSendNote = dao.getJbdSendNote(new Long(strAdvicesCodes[i]));
				
		    	
	    		if(StringUtil.isEmpty(jbdSendNote.getRemittanceBNum())){
					throw new AppException("bd.send.operator.2");
	    		}
	    		
	    		jbdSendNote.setOperaterCode("");
	    		jbdSendNote.setRegisterStatus("1");
	    		jbdSendNote.setReissueStatus("1");
	    		jbdSendNote.setSendLateCause("");
	    		jbdSendNote.setSendLateRemark("");
	    		jbdSendNote.setSendRemark("");
	    		jbdSendNote.setSendDate(null);
	    		jbdSendNote.setOperaterTime(null);
	    		jbdSendNote.setRemittanceBNum(null);
	    		jbdSendNote.setSupplyTime(null);

		    	this.saveJbdSendNote(jbdSendNote);
			}
		}
	}
	
	/**
	 * 转不发
	 */
	public void failJbdSendRecordHist(String[] strAdvicesCodes) {

		Date curDate=new Date();
		for (int i = 0; i < strAdvicesCodes.length; i++) {
			if (!StringUtils.isEmpty(strAdvicesCodes[i])) {
				JbdSendNote jbdSendNote = dao.getJbdSendNote(new Long(strAdvicesCodes[i]));
				if("4".equals(jbdSendNote.getRegisterStatus())){
					throw new AppException("bd.send.operator.2");
				}
				jbdSendNote.setRegisterStatus("4");
				jbdSendNote.setOperaterTime(curDate);

		    	dao.saveJbdSendNote(jbdSendNote);
		    	
			}
		}
	}

	public void setJfiBankbookJournalManager(
			JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}

}
