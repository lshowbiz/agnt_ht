
package com.joymain.jecs.bd.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.dao.JbdBonusBalanceDao;
import com.joymain.jecs.bd.dao.JbdSendRecordHistDao;
import com.joymain.jecs.bd.model.BdOutwardBank;
import com.joymain.jecs.bd.model.JbdBonusBalance;
import com.joymain.jecs.bd.model.JbdMemberLinkCalcHist;
import com.joymain.jecs.bd.model.JbdSendRecordHist;
import com.joymain.jecs.bd.model.JbdSendRecordNote;
import com.joymain.jecs.bd.service.JbdMemberLinkCalcHistManager;
import com.joymain.jecs.bd.service.JbdSendRecordHistManager;
import com.joymain.jecs.bd.service.JbdSendRecordNoteManager;
import com.joymain.jecs.fi.service.FiBankbookJournalManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.mi.dao.JmiSpecialListDao;
import com.joymain.jecs.po.dao.JpoBankBookPayListDao;
import com.joymain.jecs.po.dao.hibernate.JpoBankBookPayListDaoHibernate;
import com.joymain.jecs.po.model.JpoBankBookPayList;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
public class JbdSendRecordHistManagerImpl extends BaseManager implements JbdSendRecordHistManager {
    private JbdSendRecordHistDao dao;
    private JbdBonusBalanceDao jbdBonusBalanceDao;
    private JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager;
    private JfiBankbookJournalManager jfiBankbookJournalManager;
    private FiBankbookJournalManager fiBankbookJournalManager;
    private JbdSendRecordNoteManager jbdSendRecordNoteManager;
    private JmiSpecialListDao JmiSpecialListDao;
    private JpoBankBookPayListDao jpoBankBookPayListDao;
    public void setJpoBankBookPayListDao(JpoBankBookPayListDao jpoBankBookPayListDao) {
		this.jpoBankBookPayListDao = jpoBankBookPayListDao;
	}

	public void setJmiSpecialListDao(JmiSpecialListDao jmiSpecialListDao) {
		JmiSpecialListDao = jmiSpecialListDao;
	}

	public void setJbdSendRecordNoteManager(
			JbdSendRecordNoteManager jbdSendRecordNoteManager) {
		this.jbdSendRecordNoteManager = jbdSendRecordNoteManager;
	}

	public void setFiBankbookJournalManager(
			FiBankbookJournalManager fiBankbookJournalManager) {
		this.fiBankbookJournalManager = fiBankbookJournalManager;
	}

	public void setJfiBankbookJournalManager(
			JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}

	public void setJbdMemberLinkCalcHistManager(
			JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager) {
		this.jbdMemberLinkCalcHistManager = jbdMemberLinkCalcHistManager;
	}

	public void setJbdBonusBalanceDao(JbdBonusBalanceDao jbdBonusBalanceDao) {
		this.jbdBonusBalanceDao = jbdBonusBalanceDao;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdSendRecordHistDao(JbdSendRecordHistDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSendRecordHistManager#getJbdSendRecordHists(com.joymain.jecs.bd.model.JbdSendRecordHist)
     */
    public List getJbdSendRecordHists(final JbdSendRecordHist jbdSendRecordHist) {
        return dao.getJbdSendRecordHists(jbdSendRecordHist);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSendRecordHistManager#getJbdSendRecordHist(String id)
     */
    public JbdSendRecordHist getJbdSendRecordHist(final String id) {
        return dao.getJbdSendRecordHist(new Long(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSendRecordHistManager#saveJbdSendRecordHist(JbdSendRecordHist jbdSendRecordHist)
     */
    public void saveJbdSendRecordHist(JbdSendRecordHist jbdSendRecordHist) {
        dao.saveJbdSendRecordHist(jbdSendRecordHist);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSendRecordHistManager#removeJbdSendRecordHist(String id)
     */
    public void removeJbdSendRecordHist(final String id) {
        dao.removeJbdSendRecordHist(new Long(id));
    }
    //added for getJbdSendRecordHistsByCrm
    public List getJbdSendRecordHistsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdSendRecordHistsByCrm(crm,pager);
    }
    /**
     * 分配
     */
	public void allotBdSendRecord(String[] adviceCodes, String remittanceBNum,SysUser defSysUserCode) throws AppException{
		for (int i = 0; i < adviceCodes.length; i++) {
			if (!StringUtils.isEmpty(adviceCodes[i])) {

				JbdSendRecordHist jbdSendRecordHist=dao.getJbdSendRecordHist(new Long(adviceCodes[i]));
				if(null!=jbdSendRecordHist.getOperaterSysUser()||!StringUtil.isEmpty(jbdSendRecordHist.getRemittanceBNum())){
					throw new AppException("operation.notice.js.bdSendRecord.alloted");
				}else{
					JbdBonusBalance jbdBonusBalance = jbdBonusBalanceDao.getJbdBonusBalance(jbdSendRecordHist.getJmiMember().getUserCode());
					if(jbdBonusBalance==null){
						throw new AppException("bdSend.not.exist1");
					}
					//分配时，累加已分配记录
					jbdBonusBalance.setAssignedBonus(jbdBonusBalance.getAssignedBonus().add(jbdSendRecordHist.getRemittanceMoney()));
					jbdBonusBalanceDao.saveJbdBonusBalance(jbdBonusBalance);
					
					jbdSendRecordHist.setRemittanceBNum(remittanceBNum);
					jbdSendRecordHist.setOperaterSysUser(defSysUserCode);
					dao.saveJbdSendRecordHist(jbdSendRecordHist);
					
					//更新中间表
					JbdSendRecordNote jbdSendRecordNote=jbdSendRecordNoteManager.getJbdSendRecordNote(jbdSendRecordHist.getId().toString());
					jbdSendRecordNote.setRemittanceBNum(remittanceBNum);
					jbdSendRecordNote.setOperaterSysUser(defSysUserCode);
					jbdSendRecordNoteManager.saveJbdSendRecordNote(jbdSendRecordNote);
					
				}
			}
			
		}
	}
	/**
	 * 取消分配
	 */
	public void unallotBdSendRecord(String[] adviceCodes) {
		for (int i = 0; i < adviceCodes.length; i++) {
			if (!StringUtils.isEmpty(adviceCodes[i])) {
				JbdSendRecordHist jbdSendRecordHist=dao.getJbdSendRecordHist(new Long(adviceCodes[i]));
				if(null==jbdSendRecordHist.getOperaterSysUser()&&StringUtil.isEmpty(jbdSendRecordHist.getRemittanceBNum())){
					throw new AppException("operation.notice.js.bdSendRecord.unAllot");
				}else{
					

					JbdBonusBalance jbdBonusBalance = jbdBonusBalanceDao.getJbdBonusBalance(jbdSendRecordHist.getJmiMember().getUserCode());
					if(jbdBonusBalance==null){
						throw new AppException("bdSend.not.exist1");
					}
					
					
					//取消分配时，减累加分配
					jbdBonusBalance.setAssignedBonus(jbdBonusBalance.getAssignedBonus().subtract(jbdSendRecordHist.getRemittanceMoney()));
					jbdBonusBalanceDao.saveJbdBonusBalance(jbdBonusBalance);
					
					jbdSendRecordHist.setRemittanceBNum("");
					jbdSendRecordHist.setOperaterSysUser(null);
					dao.saveJbdSendRecordHist(jbdSendRecordHist);
					
					

					//更新中间表
					JbdSendRecordNote jbdSendRecordNote=jbdSendRecordNoteManager.getJbdSendRecordNote(jbdSendRecordHist.getId().toString());
					jbdSendRecordNote.setRemittanceBNum("");
					jbdSendRecordNote.setOperaterSysUser(null);
					jbdSendRecordNoteManager.saveJbdSendRecordNote(jbdSendRecordNote);
					
				}
			}
		}
	}
	/**
	 * 登记成功
	 */
	public void saveBdSendRegister(Date date, String[] strCodes,String sendRemark,SysUser defSysUser,String remarkTitle[]) {

		for (int i = 0; i < strCodes.length; i++) {
			if (!StringUtils.isEmpty(strCodes[i])) {
				JbdSendRecordHist jbdSendRecordHist= dao.getJbdSendRecordHist(new Long(strCodes[i]));
				if(null!=jbdSendRecordHist.getSendDate()){
					throw new AppException("bd.send.sended");
				}
				String sendRemarkStr="";
				JbdBonusBalance jbdBonusBalance = jbdBonusBalanceDao.getJbdBonusBalance(jbdSendRecordHist.getJmiMember().getUserCode());
				sendRemarkStr+=sendRemark;
				sendRemarkStr+=saveRemark(jbdSendRecordHist.getJmiMember().getUserCode());
				jbdSendRecordHist.setRegisterStatus("2");
				jbdSendRecordHist.setSendDate(date);
				jbdSendRecordHist.setOperaterTime(new Date());
				jbdSendRecordHist.setSendRemark(sendRemarkStr);
//		    	[登記成功] 2007-06-16 21:11:16 - zhuruijiang [匯出銀行] ghcy 
//		    	[匯款銀行] 中国工商银行 [開戶城市]  [戶名] 何慧娟 [帳號] 9558804000165141959 
//		    	[匯款金額] 82.7442 [作業者帳號] zhuruijiang [作業時間] 2007-06-16 21:11:16 <br>
				
				String sendTrace="";
		    	sendTrace+=" ["+remarkTitle[0]+"] "+DateUtil.getToday("yyyy-MM-dd HH:mm:ss")+" - "+defSysUser.getUserCode();
		    	sendTrace+=" ["+remarkTitle[1]+"] "+jbdSendRecordHist.getRemittanceBNum();
		    	sendTrace+=" ["+remarkTitle[2]+"] "+jbdSendRecordHist.getBank();
		    	sendTrace+=" ["+remarkTitle[3]+"] "+jbdSendRecordHist.getBankaddress();
		    	sendTrace+=" ["+remarkTitle[4]+"] "+jbdSendRecordHist.getBankbook();
		    	sendTrace+=" ["+remarkTitle[5]+"] "+jbdSendRecordHist.getBankcard();
		    	sendTrace+=" ["+remarkTitle[6]+"] "+jbdSendRecordHist.getRemittanceMoney();
		    	sendTrace+=" ["+remarkTitle[7]+"] "+defSysUser.getUserCode();
		    	sendTrace+=" ["+remarkTitle[8]+"] "+DateUtil.getToday("yyyy-MM-dd HH:mm:ss");
		    	sendTrace+=" <br> ";
		    	
		    	String resSendTrace=jbdSendRecordHist.getSendTrace();
		    	if(resSendTrace==null){
		    		resSendTrace="";
		    	}
		    	resSendTrace+=sendTrace;
		    	jbdSendRecordHist.setSendTrace(resSendTrace);
				
		    	//登记成功时，减累加奖金，减累加分配
		    	dao.saveJbdSendRecordHist(jbdSendRecordHist);
		    	jbdBonusBalance.setBonusBalance(jbdBonusBalance.getBonusBalance().subtract(jbdSendRecordHist.getRemittanceMoney()));
		    	jbdBonusBalance.setAssignedBonus(jbdBonusBalance.getAssignedBonus().subtract(jbdSendRecordHist.getRemittanceMoney()));
				jbdBonusBalanceDao.saveJbdBonusBalance(jbdBonusBalance);
				
		    	this.saveJbdBonusBalance(jbdSendRecordHist.getJmiMember().getUserCode());
		    	
		    	

				//更新中间表
				JbdSendRecordNote jbdSendRecordNote=jbdSendRecordNoteManager.getJbdSendRecordNote(jbdSendRecordHist.getId().toString());
				jbdSendRecordNote.setRegisterStatus("2");
				jbdSendRecordNote.setSendDate(date);
				jbdSendRecordNote.setOperaterTime(new Date());
				jbdSendRecordNote.setSendRemark(sendRemarkStr);
				jbdSendRecordNote.setSendTrace(resSendTrace);
				jbdSendRecordNoteManager.saveJbdSendRecordNote(jbdSendRecordNote);
			}
		}
	}
	/**
	 * 转待补发
	 */
	public void saveBdSendSupply(String[] strCodes, String sendLateCause, String sendLateRemark) {
		for (int i = 0; i < strCodes.length; i++) {
			if (!StringUtils.isEmpty(strCodes[i])) {
				JbdSendRecordHist jbdSendRecordHist= dao.getJbdSendRecordHist(new Long(strCodes[i]));
				
				jbdSendRecordHist.setReissueStatus("2");//补发为2待补发
				jbdSendRecordHist.setRegisterStatus("3");//登记状态为3失败
				jbdSendRecordHist.setSendLateCause(sendLateCause);
				jbdSendRecordHist.setSendLateRemark(sendLateRemark);
				jbdSendRecordHist.setOperaterTime(new Date());
				
		    	dao.saveJbdSendRecordHist(jbdSendRecordHist);
		    	

				//更新中间表
				JbdSendRecordNote jbdSendRecordNote=jbdSendRecordNoteManager.getJbdSendRecordNote(jbdSendRecordHist.getId().toString());

				jbdSendRecordNote.setReissueStatus("2");//补发为2待补发
				jbdSendRecordNote.setRegisterStatus("3");//登记状态为3失败
				jbdSendRecordNote.setSendLateCause(sendLateCause);
				jbdSendRecordNote.setSendLateRemark(sendLateRemark);
				jbdSendRecordNote.setOperaterTime(new Date());
				
				jbdSendRecordNoteManager.saveJbdSendRecordNote(jbdSendRecordNote);
			}
		}
	}

	public BigDecimal getSumRemittanceMoney(CommonRecord crm) {
		return dao.getSumRemittanceMoney(crm);
	}
	public List getJbdSendRecordsBySqlByCrm(CommonRecord crm, Pager pager) {
		return dao.getJbdSendRecordsBySqlByCrm(crm, pager);
	}
	/**
	 * 补发成功
	 */
	public void supplyJbdSendRecordHist(JbdSendRecordHist jbdSendRecordHist) {
		JbdBonusBalance jbdBonusBalance = jbdBonusBalanceDao.getJbdBonusBalance(jbdSendRecordHist.getJmiMember().getUserCode());
		jbdSendRecordHist.setSendRemark(jbdSendRecordHist.getSendRemark()+saveRemark(jbdSendRecordHist.getJmiMember().getUserCode()));
		//补发成功，减累加，减分配
    	jbdBonusBalance.setBonusBalance(jbdBonusBalance.getBonusBalance().subtract(jbdSendRecordHist.getRemittanceMoney()));
    	jbdBonusBalance.setAssignedBonus(jbdBonusBalance.getAssignedBonus().subtract(jbdSendRecordHist.getRemittanceMoney()));
		jbdBonusBalanceDao.saveJbdBonusBalance(jbdBonusBalance);
    	dao.saveJbdSendRecordHist(jbdSendRecordHist);
    	this.saveJbdBonusBalance(jbdSendRecordHist.getJmiMember().getUserCode());
    	

		//更新中间表

		JbdSendRecordNote jbdSendRecordNote=jbdSendRecordNoteManager.getJbdSendRecordNote(jbdSendRecordHist.getId().toString());
		jbdSendRecordNote.setSendRemark(jbdSendRecordHist.getSendRemark());
		
		jbdSendRecordNote.setRemittanceBNum(jbdSendRecordHist.getRemittanceBNum());
		jbdSendRecordNote.setOperaterSysUser(jbdSendRecordHist.getOperaterSysUser());
		jbdSendRecordNote.setSendDate(jbdSendRecordHist.getSendDate());
		jbdSendRecordNote.setReissueStatus(jbdSendRecordHist.getReissueStatus());
		jbdSendRecordNote.setRegisterStatus(jbdSendRecordHist.getRegisterStatus());
		jbdSendRecordNote.setOperaterTime(jbdSendRecordHist.getOperaterTime());
		jbdSendRecordNote.setSupplyTime(jbdSendRecordHist.getSupplyTime());
		jbdSendRecordNote.setSendRemark(jbdSendRecordHist.getSendRemark());
		
		jbdSendRecordNote.setSendTrace(jbdSendRecordHist.getSendTrace());
		jbdSendRecordNote.setBankbook(jbdSendRecordHist.getBankbook());
		jbdSendRecordNote.setBankcard(jbdSendRecordHist.getBankcard());
		jbdSendRecordNote.setBank(jbdSendRecordHist.getBank());
		jbdSendRecordNote.setName(jbdSendRecordHist.getName());
		jbdSendRecordNote.setPetName(jbdSendRecordHist.getPetName());
    	
    	
		jbdSendRecordNoteManager.saveJbdSendRecordNote(jbdSendRecordNote);
    	
		
	}
	/**
	 * 批量补发成功
	 */
	public void supplyJbdSendRecordHists(Date date, String[] strCodes, String sendRemark, SysUser defSysUser,BdOutwardBank bdOutwardBank) {
		for (int i = 0; i < strCodes.length; i++) {
			if (!StringUtils.isEmpty(strCodes[i])) {
				JbdSendRecordHist jbdSendRecordHist= dao.getJbdSendRecordHist(new Long(strCodes[i]));
				if(null!=jbdSendRecordHist.getSendDate()){
					throw new AppException("bd.send.sended");
				}
				String sendRemarkStr="";
				sendRemarkStr+=sendRemark;
				sendRemarkStr+=saveRemark(jbdSendRecordHist.getJmiMember().getUserCode());
				
				jbdSendRecordHist.setRemittanceBNum(bdOutwardBank.getBankCode());
		    	jbdSendRecordHist.setOperaterSysUser(defSysUser);
		    	jbdSendRecordHist.setSendDate(date);
		    	jbdSendRecordHist.setReissueStatus("3");
		    	jbdSendRecordHist.setRegisterStatus("2");
		    	jbdSendRecordHist.setOperaterTime(new Date());
		    	jbdSendRecordHist.setSupplyTime(date);
		    	jbdSendRecordHist.setSendRemark(sendRemarkStr);
		    	
		    	String sendTrace="";
		    	sendTrace+=" ["+getLocalLanguageByKey("bdSendSupply.supplySuccess",defSysUser)+"] "+DateUtil.getToday("yyyy-MM-dd HH:mm:ss")+" - "+defSysUser.getUserCode();
		    	sendTrace+=" ["+getLocalLanguageByKey("bdSendRecordAllotReport.remittanceBankCH",defSysUser)+"] "+bdOutwardBank.getBankCode();
		    	sendTrace+=" ["+getLocalLanguageByKey("bdSendRecord.openBank",defSysUser)+"] "+jbdSendRecordHist.getJmiMember().getBank();
		    	sendTrace+=" ["+getLocalLanguageByKey("bdSendRemittanceReport.openCityCH",defSysUser)+"] "+jbdSendRecordHist.getJmiMember().getBankaddress();
		    	sendTrace+=" ["+getLocalLanguageByKey("bdSendRecordToBankReport.bankNameCH",defSysUser)+"] "+jbdSendRecordHist.getJmiMember().getBankbook();
		    	sendTrace+=" ["+getLocalLanguageByKey("bdSendRecord.bnum",defSysUser)+"] "+jbdSendRecordHist.getJmiMember().getBankcard();
		    	sendTrace+=" ["+getLocalLanguageByKey("bdSendRecord.remittanceMoney",defSysUser)+"] "+jbdSendRecordHist.getRemittanceMoney();
		    	sendTrace+=" ["+getLocalLanguageByKey("bdSendRecord.sysUser.userName",defSysUser)+"] "+defSysUser.getUserCode();
		    	sendTrace+=" ["+getLocalLanguageByKey("bdSendRecord.operaterTime",defSysUser)+"] "+DateUtil.getToday("yyyy-MM-dd HH:mm:ss");
		    	sendTrace+=" <br> ";
		    	
		    	String resSendTrace=jbdSendRecordHist.getSendTrace();
		    	resSendTrace+=sendTrace;
		    	jbdSendRecordHist.setSendTrace(resSendTrace);
		    	
		    	jbdSendRecordHist.setBankbook(jbdSendRecordHist.getJmiMember().getBankaddress());
		    	jbdSendRecordHist.setBankcard(jbdSendRecordHist.getJmiMember().getBankcard());
		    	jbdSendRecordHist.setBank(jbdSendRecordHist.getJmiMember().getBank());
		    	jbdSendRecordHist.setBankbook(jbdSendRecordHist.getJmiMember().getBankbook());
		    	jbdSendRecordHist.setName(jbdSendRecordHist.getJmiMember().getSysUser().getUserName());
		    	jbdSendRecordHist.setPetName(jbdSendRecordHist.getJmiMember().getPetName());
		    	
		    	this.supplyJbdSendRecordHist(jbdSendRecordHist);
		    	this.saveJbdBonusBalance(jbdSendRecordHist.getJmiMember().getUserCode());
		    	
		    	
			}
		}
	}
	/**
	 * 更改发放登记
	 */
	public void cancalJbdSendRecordHist(String[] strAdvicesCodes,SysUser defSysUser) {
		for (int i = 0; i < strAdvicesCodes.length; i++) {
			if (!StringUtils.isEmpty(strAdvicesCodes[i])) {
				JbdSendRecordHist jbdSendRecordHist= dao.getJbdSendRecordHist(new Long(strAdvicesCodes[i]));
				
				//先处理余额表
		    	JbdBonusBalance jbdBonusBalance = jbdBonusBalanceDao.getJbdBonusBalance(jbdSendRecordHist.getJmiMember().getUserCode());
		    	
	    		if(StringUtil.isEmpty(jbdSendRecordHist.getRemittanceBNum())){
					throw new AppException("bd.send.operator.2");
	    		}
		    	String status="";
		    	if("2".equals(jbdSendRecordHist.getRegisterStatus())||"4".equals(jbdSendRecordHist.getRegisterStatus())){
		    		//登记成功，补发成功，不发  加上奖金可发余额
		        	jbdBonusBalance.setBonusBalance(jbdBonusBalance.getBonusBalance().add(jbdSendRecordHist.getRemittanceMoney()));
		        	status="1";
		    	}else if("3".equals(jbdSendRecordHist.getRegisterStatus())){
		    		//待补发状态转待分配，减去已分配余额
		        	jbdBonusBalance.setAssignedBonus(jbdBonusBalance.getAssignedBonus().subtract(jbdSendRecordHist.getRemittanceMoney()));
		        	status="2";
		    	}
	    		jbdBonusBalanceDao.saveJbdBonusBalance(jbdBonusBalance);
	    		
	    		
				jbdSendRecordHist.setOperaterSysUser(null);
				jbdSendRecordHist.setRegisterStatus("1");
				jbdSendRecordHist.setReissueStatus("1");
				jbdSendRecordHist.setSendLateCause("");
				jbdSendRecordHist.setSendLateRemark("");
				jbdSendRecordHist.setSendRemark("");
				jbdSendRecordHist.setSendDate(null);
				jbdSendRecordHist.setOperaterTime(null);
				jbdSendRecordHist.setRemittanceBNum(null);
				jbdSendRecordHist.setSupplyTime(null);
//		   		[取消登記] 2007-06-19 15:29:39 - hulanjun 
//		    	[匯出銀行]  [匯款銀行]  [開戶城市]  [戶名]  [帳號]  [匯款金額]  [作業者帳號]  
//		    	[作業時間]  <br>
				
				String sendTrace="";
		    	sendTrace+=" ["+getLocalLanguageByKey("function.menu.cancalRegister",defSysUser)+"] "+DateUtil.getToday("yyyy-MM-dd HH:mm:ss")+" - "+defSysUser.getUserCode();
		    	sendTrace+=" ["+getLocalLanguageByKey("bdSendRecordAllotReport.remittanceBankCH",defSysUser)+"] ";
		    	sendTrace+=" ["+getLocalLanguageByKey("bdSendRecord.openBank",defSysUser)+"] "+jbdSendRecordHist.getBank();
		    	sendTrace+=" ["+getLocalLanguageByKey("bdSendRemittanceReport.openCityCH",defSysUser)+"] "+jbdSendRecordHist.getBankaddress();
		    	sendTrace+=" ["+getLocalLanguageByKey("bdSendRecordToBankReport.bankNameCH",defSysUser)+"] "+jbdSendRecordHist.getBankbook();
		    	sendTrace+=" ["+getLocalLanguageByKey("bdSendRecord.bnum",defSysUser)+"] "+jbdSendRecordHist.getBankcard();
		    	sendTrace+=" ["+getLocalLanguageByKey("bdSendRecord.remittanceMoney",defSysUser)+"] "+jbdSendRecordHist.getRemittanceMoney();
		    	sendTrace+=" ["+getLocalLanguageByKey("bdSendRecord.sysUser.userName",defSysUser)+"] ";
		    	sendTrace+=" ["+getLocalLanguageByKey("bdSendRecord.operaterTime",defSysUser)+"] ";
		    	sendTrace+=" <br> ";
		    	
		    	String resSendTrace=jbdSendRecordHist.getSendTrace();
		    	resSendTrace+=sendTrace;
		    	jbdSendRecordHist.setSendTrace(resSendTrace);
				
		    	
		    	dao.saveJbdSendRecordHist(jbdSendRecordHist);
		    	
		    	if("1".equals(status)){
		    		//登记成功，补发成功，不发  加上奖金可发余额
		    		//更新中间表
		    		JbdSendRecordNote jbdSendRecordNote=new JbdSendRecordNote(jbdSendRecordHist);
		    		jbdSendRecordNoteManager.saveJbdSendRecordNote(jbdSendRecordNote);
		    	}else if("2".equals(status)){
		    		//待补发状态转待分配，减去已分配余额
					JbdSendRecordNote jbdSendRecordNote=jbdSendRecordNoteManager.getJbdSendRecordNote(jbdSendRecordHist.getId().toString());
					jbdSendRecordNote.setOperaterSysUser(null);
					jbdSendRecordNote.setRegisterStatus("1");
					jbdSendRecordNote.setReissueStatus("1");
					jbdSendRecordNote.setSendLateCause("");
					jbdSendRecordNote.setSendLateRemark("");
					jbdSendRecordNote.setSendRemark("");
					jbdSendRecordNote.setSendDate(null);
					jbdSendRecordNote.setOperaterTime(null);
					jbdSendRecordNote.setRemittanceBNum(null);
					jbdSendRecordNote.setSupplyTime(null);
					jbdSendRecordNote.setSendTrace(jbdSendRecordHist.getSendTrace());
					jbdSendRecordNoteManager.saveJbdSendRecordNote(jbdSendRecordNote);
		    	}
			}
		}
	}
	private String getLocalLanguageByKey(String key,SysUser user){
		String notes = "";
		String defaultMessage = key;
		if ("AA".equals(user.getCompanyCode())) {
			notes = (String) Constants.localLanguageMap.get("zh_CN").get(defaultMessage);
		} else {
			notes = (String) Constants.localLanguageMap.get(user.getDefCharacterCoding()).get(defaultMessage);
		}
		return notes;
	}
	/**
	 * 转不发
	 */
	public void failJbdSendRecordHist(String[] strAdvicesCodes) {
		Date curDate=new Date();
		for (int i = 0; i < strAdvicesCodes.length; i++) {
			if (!StringUtils.isEmpty(strAdvicesCodes[i])) {
				JbdSendRecordHist jbdSendRecordHist= dao.getJbdSendRecordHist(new Long(strAdvicesCodes[i]));
				if("4".equals(jbdSendRecordHist.getRegisterStatus())){
					throw new AppException("bd.send.operator.2");
				}
				jbdSendRecordHist.setRegisterStatus("4");
				jbdSendRecordHist.setOperaterTime(curDate);

		    	dao.saveJbdSendRecordHist(jbdSendRecordHist);
		    	//转不发时，减累加，减分配
				JbdBonusBalance jbdBonusBalance = jbdBonusBalanceDao.getJbdBonusBalance(jbdSendRecordHist.getJmiMember().getUserCode());
		    	jbdBonusBalance.setBonusBalance(jbdBonusBalance.getBonusBalance().subtract(jbdSendRecordHist.getRemittanceMoney()));
		    	jbdBonusBalance.setAssignedBonus(jbdBonusBalance.getAssignedBonus().subtract(jbdSendRecordHist.getRemittanceMoney()));
				jbdBonusBalanceDao.saveJbdBonusBalance(jbdBonusBalance);
				//更新中间表
				


				JbdSendRecordNote jbdSendRecordNote=jbdSendRecordNoteManager.getJbdSendRecordNote(jbdSendRecordHist.getId().toString());

				jbdSendRecordNote.setRegisterStatus("4");
				jbdSendRecordNote.setOperaterTime(curDate);
				
				jbdSendRecordNoteManager.saveJbdSendRecordNote(jbdSendRecordNote);
				
			}
		}
	}

	public List getBdSendRecordsByCrmBySqlForView(CommonRecord crm) {
		return dao.getBdSendRecordsByCrmBySqlForView(crm);
	}

	public BigDecimal getJbdSendRecordsBySqlByCrmSum(CommonRecord crm) {
		return dao.getJbdSendRecordsBySqlByCrmSum(crm);
	}

	public void activeJbdSendRecord(String[] strAdvicesCodes) {
		
		for (int i = 0; i < strAdvicesCodes.length; i++) {
			if (!StringUtils.isEmpty(strAdvicesCodes[i])) {
				JbdSendRecordHist jbdSendRecordHist= dao.getJbdSendRecordHist(new Long(strAdvicesCodes[i]));

				JbdMemberLinkCalcHist jbdMemberLinkCalcHist = jbdMemberLinkCalcHistManager.getJJbdMemberLinkCalcHistByUserCodeWeek(jbdSendRecordHist.getJmiMember().getUserCode(), jbdSendRecordHist.getWweek().toString());
	            
				if("0".equals(jbdSendRecordHist.getActive())||jbdMemberLinkCalcHist==null){
					throw new AppException("bd.send.operator.2");
				}
				jbdSendRecordHist.setActive("0");
		    	dao.saveJbdSendRecordHist(jbdSendRecordHist);
		    	
		    	jbdMemberLinkCalcHist.setActive("0");
		    	jbdMemberLinkCalcHistManager.saveJbdMemberLinkCalcHist(jbdMemberLinkCalcHist);
		    	
		    	
		    	
		    	//重新激活奖金时，加上累加数
				JbdBonusBalance jbdBonusBalance = jbdBonusBalanceDao.getJbdBonusBalance(jbdSendRecordHist.getJmiMember().getUserCode());
				
	        	jbdBonusBalance.setBonusBalance(jbdBonusBalance.getBonusBalance().add(jbdSendRecordHist.getRemittanceMoney()));
				jbdBonusBalanceDao.saveJbdBonusBalance(jbdBonusBalance);
				
				//更新中间表
				JbdSendRecordNote jbdSendRecordNote=jbdSendRecordNoteManager.getJbdSendRecordNote(jbdSendRecordHist.getId().toString());
				jbdSendRecordNote.setActive("0");
				jbdSendRecordNoteManager.saveJbdSendRecordNote(jbdSendRecordNote);
			}
		}
	}
	/**
	 * 奖金发放时，奖申请发放重置
	 * @param userCode
	 */
	private void saveJbdBonusBalance(String userCode){
		JbdBonusBalance jbdBonusBalance=jbdBonusBalanceDao.getJbdBonusBalance(userCode);
		if("1".equals(jbdBonusBalance.getFlag())){
			jbdBonusBalance.setFlag("0");
			jbdBonusBalance.setFlagTime(null);
			jbdBonusBalanceDao.saveJbdBonusBalance(jbdBonusBalance);
		}
		
	}
	private String saveRemark(String userCode){
		JbdBonusBalance jbdBonusBalance=jbdBonusBalanceDao.getJbdBonusBalance(userCode);
		if("1".equals(jbdBonusBalance.getFlag())){
			return " 扣除小额奖金发放手续费共计3元";
		}
		return "";
	}
	
	
	/**
	 * freezeStatus 1 冻结奖金，0 解冻奖金
	 */
	public void freezeJbdSendRecord(String[] strAdvicesCodes,int freezeStatus) {
		
		for (int i = 0; i < strAdvicesCodes.length; i++) {
			if (!StringUtils.isEmpty(strAdvicesCodes[i])) {
				JbdSendRecordHist jbdSendRecordHist= dao.getJbdSendRecordHist(new Long(strAdvicesCodes[i]));

				JbdMemberLinkCalcHist jbdMemberLinkCalcHist = jbdMemberLinkCalcHistManager.getJJbdMemberLinkCalcHistByUserCodeWeek(jbdSendRecordHist.getJmiMember().getUserCode(), jbdSendRecordHist.getWweek().toString());
	            
				
				if(0==freezeStatus&&(0==jbdSendRecordHist.getFreezeStatus()||jbdMemberLinkCalcHist==null)){
					throw new AppException("奖金已解冻");
					
				}else if(1==freezeStatus&&(1==jbdSendRecordHist.getFreezeStatus()||jbdMemberLinkCalcHist==null)){
					throw new AppException("奖金已冻结");
				}
				
				
				
				
				
				jbdSendRecordHist.setFreezeStatus(freezeStatus);
		    	dao.saveJbdSendRecordHist(jbdSendRecordHist);
		    	
		    	jbdMemberLinkCalcHist.setFreezeStatus(freezeStatus);
		    	jbdMemberLinkCalcHistManager.saveJbdMemberLinkCalcHist(jbdMemberLinkCalcHist);

		    	//重新激活奖金时，加上累加数
				JbdBonusBalance jbdBonusBalance = jbdBonusBalanceDao.getJbdBonusBalance(jbdSendRecordHist.getJmiMember().getUserCode());
				

				
		    	if(0==freezeStatus){
		        	jbdBonusBalance.setBonusBalance(jbdBonusBalance.getBonusBalance().add(jbdSendRecordHist.getRemittanceMoney()));
		    		
				}else if(1==freezeStatus){
					jbdBonusBalance.setBonusBalance(jbdBonusBalance.getBonusBalance().subtract(jbdSendRecordHist.getRemittanceMoney()));
				}

				JbdSendRecordNote jbdSendRecordNote=jbdSendRecordNoteManager.getJbdSendRecordNote(jbdSendRecordHist.getId().toString());
	        	jbdSendRecordNote.setFreezeStatus(freezeStatus);
				jbdSendRecordNoteManager.saveJbdSendRecordNote(jbdSendRecordNote);
				
				jbdBonusBalanceDao.saveJbdBonusBalance(jbdBonusBalance);
			}
		}
	}
	
	

	/**
	 * freezeStatus 1 冻结奖金，0 解冻奖金 批量冻结
	 */
	public void freezeJbdSendRecord(List<JbdSendRecordHist> jbdSendRecordHists) {
		
		
		for (JbdSendRecordHist hist : jbdSendRecordHists) {
			
			JbdMemberLinkCalcHist jbdMemberLinkCalcHist = jbdMemberLinkCalcHistManager.getJJbdMemberLinkCalcHistByUserCodeWeek(hist.getJmiMember().getUserCode(), hist.getWweek().toString());
            
			Integer freezeStatus=null;
			if(jbdMemberLinkCalcHist.getFreezeStatus()==0){
				freezeStatus=1;
			}else{
				freezeStatus=0;
			}

	    	jbdMemberLinkCalcHist.setFreezeStatus(freezeStatus);
	    	jbdMemberLinkCalcHistManager.saveJbdMemberLinkCalcHist(jbdMemberLinkCalcHist);

	    	hist.setFreezeStatus(freezeStatus);
	    	dao.saveJbdSendRecordHist(hist);
	    	
	    	
//	    	重新激活奖金时，加上累加数
			JbdBonusBalance jbdBonusBalance = jbdBonusBalanceDao.getJbdBonusBalance(hist.getJmiMember().getUserCode());
	    	if(0==freezeStatus){
	        	jbdBonusBalance.setBonusBalance(jbdBonusBalance.getBonusBalance().add(hist.getRemittanceMoney()));
			}else if(1==freezeStatus){
				jbdBonusBalance.setBonusBalance(jbdBonusBalance.getBonusBalance().subtract(hist.getRemittanceMoney()));
			}

			JbdSendRecordNote jbdSendRecordNote=jbdSendRecordNoteManager.getJbdSendRecordNote(hist.getId().toString());
        	jbdSendRecordNote.setFreezeStatus(freezeStatus);
			jbdSendRecordNoteManager.saveJbdSendRecordNote(jbdSendRecordNote);
			
			jbdBonusBalanceDao.saveJbdBonusBalance(jbdBonusBalance);
		}
		

				
				
//				if(0==freezeStatus&&(0==jbdSendRecordHist.getFreezeStatus()||jbdMemberLinkCalcHist==null)){
//					throw new AppException("奖金已解冻");
//					
//				}else if(1==freezeStatus&&(1==jbdSendRecordHist.getFreezeStatus()||jbdMemberLinkCalcHist==null)){
//					throw new AppException("奖金已冻结");
//				}
				
				
				
				
				
		    	

		    	
				
	}
	
	public void saveInFiBookAll(String[] adviceCodes ,SysUser defSysUser,List jbdSendRecords){
		List<JpoBankBookPayList> list=new ArrayList<JpoBankBookPayList>();
		
		if(jbdSendRecords==null){
			for (int i = 0; i < adviceCodes.length; i++) {
				if(!StringUtils.isEmpty(adviceCodes[i])){
	        		this.saveInFiBook(defSysUser, adviceCodes[i], list);
				}
			}
		}else{
			for (int i = 0; i < jbdSendRecords.size(); i++) {
				Map map=(Map) jbdSendRecords.get(i);
				String id=map.get("id").toString();
				this.saveInFiBook(defSysUser, id, list);
 			 }
		}
		
		
			
		jpoBankBookPayListDao.saveJpoBankBookPayListList(list);
	}
	
	public void saveInFiBook(SysUser defSysUser,String id,List<JpoBankBookPayList> list){

	    
	    Date curDate=new Date();
	    //String remittanceBNum="200";
	    
	    
	    JbdSendRecordHist jbdSendRecordHist=dao.getJbdSendRecordHist(new Long(id));
	    
	    if(jbdSendRecordHist.getSendDate()==null){
	 	    
	 		/*jbdSendRecordHist.setRegisterStatus("2");
	 		jbdSendRecordHist.setSendDate(curDate);
	 		jbdSendRecordHist.setOperaterTime(curDate);
	 	    

	 		jbdSendRecordHist.setRemittanceBNum(remittanceBNum);
	 		jbdSendRecordHist.setOperaterSysUser(defSysUser);
	 		
	 		
	 		dao.saveJbdSendRecordHist(jbdSendRecordHist);

	 		JbdBonusBalance jbdBonusBalance = null;
	 		if("1".equals(type)){
	 			jbdBonusBalance = jbdBonusBalanceDao.getJbdBonusBalance(jbdSendRecordHist.getJmiMember().getUserCode());
	 		}else{
	 			jbdBonusBalance = jbdBonusBalanceDao.getJbdBonusBalanceForUpdate(jbdSendRecordHist.getJmiMember().getUserCode());
	 		}
	 		
	 		
	 		
	 		
	     	jbdBonusBalance.setBonusBalance(jbdBonusBalance.getBonusBalance().subtract(jbdSendRecordHist.getRemittanceMoney()));
	 		jbdBonusBalanceDao.saveJbdBonusBalance(jbdBonusBalance);*/

	 		
	 	    //10%服务费
	 	   BigDecimal scMoney=new BigDecimal(0);
	 	   
	 	   if(jbdSendRecordHist.getWweek()>=201601){
	 		  if(jbdSendRecordHist.getRemittanceMoney().compareTo(new BigDecimal(800))==1){
		 		   if(!JmiSpecialListDao.getJmiSpecialExist(jbdSendRecordHist.getJmiMember().getUserCode())){
		 			   String scRate= (String) Constants.sysConfigMap.get("AA").get("sc.rate");
				 	   scMoney=jbdSendRecordHist.getRemittanceMoney().multiply(new BigDecimal(scRate)).setScale(2, BigDecimal.ROUND_HALF_UP);
		 		   }
		 	   }
	 	   }
	 	   //2016-8-12 改为插中间表
			JpoBankBookPayList jpoBankBookPayList=new JpoBankBookPayList();
			jpoBankBookPayList.setUserCode(jbdSendRecordHist.getJmiMember().getUserCode());
			jpoBankBookPayList.setUserSPcode(jbdSendRecordHist.getJmiMember().getUserCode());
			jpoBankBookPayList.setMemberOrderNo(jbdSendRecordHist.getId().toString());
			jpoBankBookPayList.setInType(12);
			jpoBankBookPayList.setCreateTime(curDate);
			jpoBankBookPayList.setDzAmt(jbdSendRecordHist.getRemittanceMoney());
			jpoBankBookPayList.setFee(scMoney);
			jpoBankBookPayList.setDataType("1");
			jpoBankBookPayList.setMoneyType1(40);
			jpoBankBookPayList.setMoneyType2(190);
			jpoBankBookPayList.setNotes("奖金转电子存折");
			jpoBankBookPayList.setCheckUserCode(defSysUser.getUserCode());
			list.add(jpoBankBookPayList);
			
			//
	 	    //
	 	    
	 		/*Integer[] moneyType = new Integer[1];
	 		moneyType[0]=40;
	 		
	 		BigDecimal[] moneyArray = new BigDecimal[1];
	 		moneyArray[0]=jbdSendRecordHist.getRemittanceMoney();

	 		String[] notes = new String[1];
	 		notes[0]="奖金转电子存折"; 

	 		//更新中间表
	 		JbdSendRecordNote jbdSendRecordNote=jbdSendRecordNoteManager.getJbdSendRecordNoteForUpdate(jbdSendRecordHist.getId().toString());

	 		
	 		
	 		
	 		jbdSendRecordNote.setRegisterStatus("2");
	 		jbdSendRecordNote.setSendDate(curDate);
	 		jbdSendRecordNote.setOperaterTime(curDate);
	 		jbdSendRecordNote.setRemittanceBNum(remittanceBNum);
	 		jbdSendRecordNote.setOperaterSysUser(defSysUser);
	 		jbdSendRecordNoteManager.saveJbdSendRecordNote(jbdSendRecordNote);
	 		

	 	   
	 	  
	 		jfiBankbookJournalManager.saveJfiPayDataVerify(jbdSendRecordHist.getJmiMember().getCompanyCode(), jbdSendRecordHist.getJmiMember().getSysUser(), moneyType,moneyArray, defSysUser.getUserCode(), defSysUser.getUserName(), "bd"+jbdSendRecordHist.getId().toString(), notes,"1");
*/
	 	 
	 	    
	 	    if(scMoney.compareTo(new BigDecimal(0))==1){
	 		   //	moneyType[0]=190;
	 		   //	moneyArray[0]=scMoney;
	 		   //notes[0]="服务费";
		 		//jfiBankbookJournalManager.saveJfiBankbookDeduct(jbdSendRecordHist.getJmiMember().getCompanyCode(), jbdSendRecordHist.getJmiMember().getSysUser(), moneyType, moneyArray, defSysUser.getUserCode(), defSysUser.getUserName(), "sc"+jbdSendRecordHist.getId().toString(), notes, "1");
		 	}
	 	   
	 	  
	 	    
	    }
	    
	    
	   
	}

	public BigDecimal getJbdSendRecordsBySqlByCrmSumDev(CommonRecord crm) {
		return dao.getJbdSendRecordsBySqlByCrmSumDev(crm);
	}
	
	public void saveInDevFiBookAll(SysUser defSysUser,List jbdSendRecords){
		List<JpoBankBookPayList> list=new ArrayList<JpoBankBookPayList>();
		for (int i = 0; i < jbdSendRecords.size(); i++) {
			Map map=(Map) jbdSendRecords.get(i);
			String id=map.get("id").toString();
			this.saveInDevFiBook(defSysUser, id, list);
		}
		jpoBankBookPayListDao.saveJpoBankBookPayListList(list);
	}
	public void saveInDevFiBook(SysUser defSysUser,String id,List<JpoBankBookPayList> list){

	    Date curDate=new Date();
	    
	    
	    JbdSendRecordHist jbdSendRecordHist=dao.getJbdSendRecordHist(new Long(id));
		
	    if(!"2".equals(jbdSendRecordHist.getSendStatusDev())){
	    	
	    	
	    	  //2016-8-12 改为插中间表
			JpoBankBookPayList jpoBankBookPayList=new JpoBankBookPayList();
			jpoBankBookPayList.setUserCode(jbdSendRecordHist.getJmiMember().getUserCode());
			jpoBankBookPayList.setUserSPcode(jbdSendRecordHist.getJmiMember().getUserCode());
			jpoBankBookPayList.setMemberOrderNo(jbdSendRecordHist.getId().toString());
			jpoBankBookPayList.setInType(14);
			jpoBankBookPayList.setCreateTime(curDate);
			jpoBankBookPayList.setJjAmt(jbdSendRecordHist.getCurrentDev());

			jpoBankBookPayList.setDataType("1");
			jpoBankBookPayList.setMoneyType1(67);
			jpoBankBookPayList.setNotes("奖金转入发展基金");
			jpoBankBookPayList.setCheckUserCode(defSysUser.getUserCode());
			list.add(jpoBankBookPayList);
			
			//
	    	
	    	
	    	/*jbdSendRecordHist.setSendDateDev(curDate);
			jbdSendRecordHist.setSendUserDev(defSysUser.getUserCode());
			jbdSendRecordHist.setSendStatusDev("2");
			
			
			dao.saveJbdSendRecordHist(jbdSendRecordHist);


			//更新中间表
			JbdSendRecordNote jbdSendRecordNote=jbdSendRecordNoteManager.getJbdSendRecordNoteForUpdate(jbdSendRecordHist.getId().toString());
			jbdSendRecordNote.setSendDateDev(curDate);
			jbdSendRecordNote.setSendUserDev(defSysUser.getUserCode());
			jbdSendRecordNote.setSendStatusDev("2");
			jbdSendRecordNoteManager.saveJbdSendRecordNote(jbdSendRecordNote);
			

			Integer[] moneyType = new Integer[1];
			moneyType[0]=67;
			
			BigDecimal[] moneyArray = new BigDecimal[1];
			moneyArray[0]=jbdSendRecordHist.getCurrentDev();

			String[] notes = new String[1];
			notes[0]="奖金转入发展基金";
			
			fiBankbookJournalManager.saveFiPayDataVerify(jbdSendRecordHist.getJmiMember().getCompanyCode(), jbdSendRecordHist.getJmiMember().getSysUser(), moneyType, moneyArray, defSysUser.getUserCode(), defSysUser.getUserName(), "bd_dev_"+jbdSendRecordHist.getId().toString(), notes, "1", "1");
			*/
	    }
	    
		
		
	}

	public List getJbdSendRecordsBySqlByCrmNew(CommonRecord crm, Pager pager) {
		return dao.getJbdSendRecordsBySqlByCrmNew(crm, pager);
	}

	public BigDecimal getJbdSendRecordsBySqlByCrmSumNew(CommonRecord crm) {
		return dao.getJbdSendRecordsBySqlByCrmSumNew(crm);
	}

	public List getJbdBonusBalanceUserCodes(String startAllotMoney, String endAllotMoney, String flag, String status) {
		return dao.getJbdBonusBalanceUserCodes(startAllotMoney, endAllotMoney, flag, status);
	}

	public List getJbdSendRecordsByUserCode(String userCode, String companyCode,String fi) {
		return dao.getJbdSendRecordsByUserCode(userCode, companyCode,fi);
	}

	public JbdSendRecordHist getJbdSendRecordHistByUserCodeAndWeek(String userCode, String wweek) {
		// TODO Auto-generated method stub
		return dao.getJbdSendRecordHistByUserCodeAndWeek(userCode, wweek);
	}

	@Override
	public Object[] getSumBonus(CommonRecord crm) {
		return dao.getSumBonus(crm);
	}
	
	
}
