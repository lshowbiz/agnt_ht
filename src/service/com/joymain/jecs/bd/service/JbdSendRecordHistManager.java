
package com.joymain.jecs.bd.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.po.model.JpoBankBookPayList;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.bd.model.BdOutwardBank;
import com.joymain.jecs.bd.model.JbdSendRecordHist;
import com.joymain.jecs.bd.dao.JbdSendRecordHistDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdSendRecordHistManager extends Manager {
    /**
     * Retrieves all of the jbdSendRecordHists
     */
    public List getJbdSendRecordHists(JbdSendRecordHist jbdSendRecordHist);

    /**
     * Gets jbdSendRecordHist's information based on id.
     * @param id the jbdSendRecordHist's id
     * @return jbdSendRecordHist populated jbdSendRecordHist object
     */
    public JbdSendRecordHist getJbdSendRecordHist(final String id);

    /**
     * Saves a jbdSendRecordHist's information
     * @param jbdSendRecordHist the object to be saved
     */
    public void saveJbdSendRecordHist(JbdSendRecordHist jbdSendRecordHist);

    /**
     * Removes a jbdSendRecordHist from the database by id
     * @param id the jbdSendRecordHist's id
     */
    public void removeJbdSendRecordHist(final String id);
    //added for getJbdSendRecordHistsByCrm
    public List getJbdSendRecordHistsByCrm(CommonRecord crm, Pager pager);
    public void allotBdSendRecord(String[] adviceCodes,String remittanceBNum,SysUser defSysUserCode);
    public void unallotBdSendRecord(String[] adviceCodes);
    /**
     * 奖金发放
     * @param date
     * @param strCodes
     * @param sendRemark
     * @param defSysUser
     * @param remarkTitle
     */
	public void saveBdSendRegister(Date date, String[] strCodes,String sendRemark,SysUser defSysUser,String remarkTitle[]);
	/**
	 * 奖金转补发
	 * @param strCodes
	 * @param sendLateCause
	 * @param sendLateRemark
	 */
	public void saveBdSendSupply(String[] strCodes, String sendLateCause, String sendLateRemark); 
	public BigDecimal getSumRemittanceMoney(CommonRecord crm);
    public List getJbdSendRecordsBySqlByCrm(CommonRecord crm, Pager pager);
    /**
     * 奖金补发
     * @param jbdSendRecordHist
     */
    public void supplyJbdSendRecordHist(JbdSendRecordHist jbdSendRecordHist);
    /**
     * 更改发放登记
     * @param strAdvicesCodes
     */
    public void cancalJbdSendRecordHist(String[] strAdvicesCodes,SysUser defSysUser);
    /**
     * 转不发
     * @param strAdvicesCodes
     */
    public void failJbdSendRecordHist(String[] strAdvicesCodes);
	public List getBdSendRecordsByCrmBySqlForView(CommonRecord crm);
	public BigDecimal getJbdSendRecordsBySqlByCrmSum(CommonRecord crm) ;
	public void activeJbdSendRecord(String[] strAdvicesCodes);
    /**
     * 批量奖金补发
     * @param jbdSendRecordHist
     */
    public void supplyJbdSendRecordHists(Date date, String[] strCodes,String sendRemark,SysUser defSysUser,BdOutwardBank bdOutwardBank);
	public void freezeJbdSendRecord(String[] strAdvicesCodes,int freezeStatus) ;
	

	public void saveInFiBook(SysUser defSysUser,String id,List<JpoBankBookPayList> list);
	public BigDecimal getJbdSendRecordsBySqlByCrmSumDev(CommonRecord crm);
	public void saveInDevFiBook(SysUser defSysUser,String id,List<JpoBankBookPayList> list);
	public List getJbdSendRecordsBySqlByCrmNew(CommonRecord crm, Pager pager) ;
	public BigDecimal getJbdSendRecordsBySqlByCrmSumNew(CommonRecord crm);
    public List getJbdBonusBalanceUserCodes(String startAllotMoney,String endAllotMoney,String flag,String status);
    public List getJbdSendRecordsByUserCode(String userCode,String companyCode,String fi);
	public JbdSendRecordHist getJbdSendRecordHistByUserCodeAndWeek(String userCode, String wweek) ;
	/**
	 * freezeStatus 1 冻结奖金，0 解冻奖金 批量冻结
	 */
	public void freezeJbdSendRecord(List<JbdSendRecordHist> jbdSendRecordHists) ;
    public Object[] getSumBonus(CommonRecord crm);
	public void saveInFiBookAll(String[] adviceCodes ,SysUser defSysUser,List jbdSendRecords);
	public void saveInDevFiBookAll(SysUser defSysUser,List jbdSendRecords);
    
}

