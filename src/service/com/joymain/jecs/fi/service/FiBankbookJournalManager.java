
package com.joymain.jecs.fi.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.fi.model.FiBankbookJournal;
import com.joymain.jecs.fi.model.FiProductPointJournal;
import com.joymain.jecs.fi.model.JfiPosImport;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
public interface FiBankbookJournalManager extends Manager {
    /**
     * Retrieves all of the fiBankbookJournals
     */
    public List getFiBankbookJournals(FiBankbookJournal fiBankbookJournal);

    /**
     * Gets fiBankbookJournal's information based on journalId.
     * @param journalId the fiBankbookJournal's journalId
     * @return fiBankbookJournal populated fiBankbookJournal object
     */
    public FiBankbookJournal getFiBankbookJournal(final String journalId);

    /**
     * Saves a fiBankbookJournal's information
     * @param fiBankbookJournal the object to be saved
     */
    public void saveFiBankbookJournal(FiBankbookJournal fiBankbookJournal);

    /**
     * Removes a fiBankbookJournal from the database by journalId
     * @param journalId the fiBankbookJournal's journalId
     */
    public void removeFiBankbookJournal(final String journalId);
    
    public Map getSumPPByCrm(CommonRecord crm);
    
    //added for getFiBankbookJournalsByCrm
    public List getFiBankbookJournalsByCrm(CommonRecord crm, Pager pager);
    
    /**
     * Add By WuCF 20140320
	 * 基金余额查询总计
	 * @param crm
	 * @return
	 */
	public Map getSumAmountByCrm(CommonRecord crm);
    
    /**
     * 会员对会员转账
     * @param companyCode
     * @param ucMember
     * @param moneyType
     * @param money
     * @param operaterCode
     * @param operaterName
     * @param uniqueCode
     * @param notes
     * @param ReceiveUcMember
     */
    public void saveTransferMemberToMember(final String companyCode, final SysUser sysUser, final Integer moneyType, final BigDecimal money,
	        String operaterCode, final String operaterName, final String uniqueCode, final String note, final SysUser receiveSysUser);
    
	/**
	 * 验证存折条目
	 * @param tempId
	 * @param sysUser
	 * @return
	 */
	public void saveFiBankbookTempCheck(final String tempId, final SysUser sysUser) throws AppException;

	/**
	 * 获取收支统计金额 incTotal:收入 expTotal:支出
	 * @param crm
	 * @return
	 */
	public Map getIncExpStatMap(CommonRecord crm);

	/**
	 * 获取公司截止至某一日期时(包含此日期)的用户电子存折余额总计
	 * @param companyCode
	 * @param queryDate yyyy-MM-dd
	 * @return
	 */
	public BigDecimal getTotalBalanceByDate(final String companyCode, final String queryDate, final String bankbookType);

	/**
	 * 获取某段日期内,公司在指定资金类别所对应的进出金额
	 * @param companyCode
	 * @param dealType
	 * @param moneyTypes
	 * @param inverseMoneyType 如果为true,则查询不包含在moneyTypes里的记录,如果为false,则为包含在moneyTypes的记录
	 * @param startDate yyyy-MM-dd
	 * @param endDate yyyy-MM-dd
	 * @return
	 */
	public List getFiBankbookJournalsStat(final String companyCode, final String dealType, final Integer[] moneyTypes,
	        boolean inverseMoneyType, final String startDate, final String endDate, final String bankbookType);


	/**
	 * POS数据进存折
	 */
	public void saveJfiPayDataVerifyByPosImport(final String companyCode, final SysUser sysUser, String operaterCode, final String operaterName, JfiPosImport jfiPosImport);

	/**
	 * 存入资金至发展基金帐户
	 * @param companyCode 公司编码
	 * @param userCode 用户编码
	 * @param moneyType 资金类别
	 * @param money 资金金额
	 * @param operaterCode 操作人编码
	 * @param operaterName 操作人姓名
	 * * @param uniqueCode 业务唯一码,0为没有
	 * @param notes 存款说明
	 */
	public void saveFiPayDataVerify(final String companyCode, final SysUser sysUser, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode, final String[] notes, final String bankbookType, final String dataType);

	/**
	 * 从发展基金帐户扣取金额
	 * @param companyCode 公司编码
	 * @param userCode 用户编码
	 * @param moneyType 资金类别
	 * @param money 资金金额
	 * @param operaterCode 操作人编码
	 * @param operaterName 操作人姓名
	 * * @param uniqueCode 业务唯一码,0为没有
	 * @param notes 存款说明
	 * @throws AppException 存款不够的错误
	 */
	public void saveFiBankbookDeduct(final String companyCode, final SysUser sysUser, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode,  final String[] notes, final String bankbookType, final String dataType) throws AppException;

	/**
	 * 查询退单的退基金情况
	 * @author gw 2015-07-10
	 * @param roNo
	 * @return bigDecimal
	 */
	public BigDecimal getBeforeJprRefundJj(String roNo);
	
	
	/**
	 * 查看这张退单之前退了多少基金
	 * @author gw 2015-07-13
	 * @param roNo
	 * @return bigDecimal
	 */
	public BigDecimal getJprRefundJjBack(String roNo);
    public List getFiProductPointJournalsByCrm(CommonRecord crm, Pager pager);
	public Map getPpIncExpStatMap(CommonRecord crm);
	public BigDecimal getPpTotalBalanceByDate(final String companyCode, final String queryDate, final String bankbookType) ;
	public List getFiProductPointJournalsStat(final String companyCode, final String dealType, final Integer[] moneyTypes,
	        boolean inverseMoneyType, final String startDate, final String endDate, final String bankbookType);
	   public FiProductPointJournal getFiProductPointJournal(final String journalId);

	
}

