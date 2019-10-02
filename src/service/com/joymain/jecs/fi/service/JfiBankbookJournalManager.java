package com.joymain.jecs.fi.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.fi.model.FiChannelLog;
import com.joymain.jecs.fi.model.Jfi99billLog;
import com.joymain.jecs.fi.model.Jfi99billmsLog;
import com.joymain.jecs.fi.model.JfiAlipayLog;
import com.joymain.jecs.fi.model.JfiBankbookJournal;
import com.joymain.jecs.fi.model.JfiChanjetLog;
import com.joymain.jecs.fi.model.JfiChinapnrLog;
import com.joymain.jecs.fi.model.JfiHiTrustLog;
import com.joymain.jecs.fi.model.JfiPayLog;
import com.joymain.jecs.fi.model.JfiReapalLog;
import com.joymain.jecs.fi.model.JfiTenpayLog;
import com.joymain.jecs.fi.model.JfiUmbpayLog;
import com.joymain.jecs.fi.model.JfiUsCreditCardLog;
import com.joymain.jecs.fi.model.JfiYeepayLog;
import com.joymain.jecs.po.model.JpoBankBookPayList;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;

@SuppressWarnings("rawtypes")
public interface JfiBankbookJournalManager extends Manager {
	/**
	 * Retrieves all of the jfiBankbookJournals
	 */
	public List getJfiBankbookJournals(JfiBankbookJournal jfiBankbookJournal);

	/**
	 * Gets jfiBankbookJournal's information based on journalId.
	 * 
	 * @param journalId
	 *            the jfiBankbookJournal's journalId
	 * @return jfiBankbookJournal populated jfiBankbookJournal object
	 */
	public JfiBankbookJournal getJfiBankbookJournal(final String journalId);

	/**
	 * Saves a jfiBankbookJournal's information
	 * 
	 * @param jfiBankbookJournal
	 *            the object to be saved
	 */
	public void saveJfiBankbookJournal(JfiBankbookJournal jfiBankbookJournal);

	/**
	 * Removes a jfiBankbookJournal from the database by journalId
	 * 
	 * @param journalId
	 *            the jfiBankbookJournal's journalId
	 */
	public void removeJfiBankbookJournal(final String journalId);

	// added for getJfiBankbookJournalsByCrm
	public List getJfiBankbookJournalsByCrm(CommonRecord crm, Pager pager);

	/**
	 * 验证存折条目
	 * 
	 * @param tempId
	 * @param sysUser
	 * @return
	 */
	public void saveJfiBankbookTempCheck(final String tempId, final SysUser sysUser) throws AppException;

	/**
	 * 获取收支统计金额 incTotal:收入 expTotal:支出
	 * 
	 * @param crm
	 * @return
	 */
	public Map getIncExpStatMap(CommonRecord crm);

	/**
	 * 获取公司截止至某一日期时(包含此日期)的用户电子存折余额总计
	 * 
	 * @param companyCode
	 * @param queryDate
	 *            yyyy-MM-dd
	 * @return
	 */
	public BigDecimal getTotalBalanceByDate(final String companyCode, final String queryDate);

	/**
	 * 获取某段日期内,公司在指定资金类别所对应的进出金额
	 * 
	 * @param companyCode
	 * @param dealType
	 * @param moneyTypes
	 * @param inverseMoneyType
	 *            如果为true,则查询不包含在moneyTypes里的记录,如果为false,则为包含在moneyTypes的记录
	 * @param startDate
	 *            yyyy-MM-dd
	 * @param endDate
	 *            yyyy-MM-dd
	 * @return
	 */
	public List getJfiBankbookJournalsStat(final String companyCode, final String dealType, final Integer[] moneyTypes, boolean inverseMoneyType, final String startDate,
			final String endDate);

	/**
	 * 宝易互通进存折
	 * 
	 * @throws Exception
	 */
	public BigDecimal saveJfiPayDataVerifyByUmbpay(final String companyCode, final SysUser sysUser, final BigDecimal totalMoney, String operaterCode, final String operaterName,
			final String uniqueCode, JfiUmbpayLog entity) throws AppException;

	/**
	 * 汇付天下进存折
	 * 
	 * @throws Exception
	 */
	public BigDecimal saveJfiPayDataVerifyByChinapnr(final String companyCode, final SysUser sysUser, final BigDecimal totalMoney, String operaterCode, final String operaterName,
			final String uniqueCode, JfiChinapnrLog entity) throws AppException;

	/**
	 * 快钱数据进存折
	 */
	public BigDecimal saveJfiPayDataVerifyByBill99(final String companyCode, final SysUser sysUser, final BigDecimal totalMoney, String operaterCode, final String operaterName,
			final String uniqueCode, Jfi99billLog jfi99billLog, final BigDecimal fee);

	/**
	 * 快钱分润数据进存折
	 */
	public BigDecimal saveJfiPayDataVerifyByBill99ms(final String companyCode, final SysUser sysUser, final BigDecimal totalMoney, String operaterCode, final String operaterName,
			final String uniqueCode, Jfi99billmsLog jfi99billmsLog, final BigDecimal fee);

	/**
	 * 支付宝数据进存折
	 */
	public BigDecimal saveJfiPayDataVerifyByAlipay(final String companyCode, final SysUser sysUser, final BigDecimal totalMoney, String operaterCode, final String operaterName,
			final String uniqueCode, JfiAlipayLog jfiAlipayLog);

	/**
	 * 财付通数据进存折
	 */
	public BigDecimal saveJfiPayDataVerifyByTenpay(final String companyCode, final SysUser sysUser, final BigDecimal totalMoney, String operaterCode, final String operaterName,
			final String uniqueCode, JfiTenpayLog jfiTenpayLog);

	/**
	 * 台湾信用卡
	 */
	public BigDecimal saveJfiPayDataVerifyByHiTrust(final String companyCode, final SysUser sysUser, final BigDecimal totalMoney, String operaterCode, final String operaterName,
			final String uniqueCode, JfiHiTrustLog jfiHiTrustLog);

	/**
	 * 美国信用卡
	 */
	public BigDecimal saveJfiPayDataVerifyByUsCreditCard(final String companyCode, final SysUser sysUser, final BigDecimal totalMoney, String operaterCode,
			final String operaterName, final String uniqueCode, JfiUsCreditCardLog jfiUsCreditCardLog);

	/**
	 * 存入资金至电子存折帐户
	 * 
	 * @param companyCode
	 *            公司编码
	 * @param userCode
	 *            用户编码
	 * @param moneyType
	 *            资金类别
	 * @param money
	 *            资金金额
	 * @param operaterCode
	 *            操作人编码
	 * @param operaterName
	 *            操作人姓名 * @param uniqueCode 业务唯一码,0为没有
	 * @param notes
	 *            存款说明
	 */
	public void saveJfiPayDataVerify(final String companyCode, final SysUser sysUser, final Integer[] moneyType, final BigDecimal[] moneyArray, String operaterCode,
			final String operaterName, final String uniqueCode, final String[] notes, String dataType);

	/**
	 * 从电子存折帐户扣取金额
	 * 
	 * @param companyCode
	 *            公司编码
	 * @param userCode
	 *            用户编码
	 * @param moneyType
	 *            资金类别
	 * @param money
	 *            资金金额
	 * @param operaterCode
	 *            操作人编码
	 * @param operaterName
	 *            操作人姓名 * @param uniqueCode 业务唯一码,0为没有
	 * @param notes
	 *            存款说明
	 * @throws AppException
	 *             存款不够的错误
	 */
	public void saveJfiBankbookDeduct(final String companyCode, final SysUser sysUser, final Integer[] moneyType, final BigDecimal[] moneyArray, String operaterCode,
			final String operaterName, final String uniqueCode, final String[] notes, String dataType) throws AppException;
	/**
	 * 支付改造添加
	 */
	public void saveJfiBankbookDeduct1(final String companyCode, final SysUser sysUser, final Integer[] moneyType, final BigDecimal[] moneyArray, String operaterCode,
			final String operaterName, final String uniqueCode, final String[] notes, String dataType) throws AppException;
	/**
	 * 手机支付快钱数据进存折
	 */
	public BigDecimal saveJfiPayDataVerifyByMobil(final String companyCode, final SysUser sysUser, final BigDecimal totalMoney, String operaterCode, final String operaterName,
			final String uniqueCode, Jfi99billLog jfi99billLog, final BigDecimal fee);

	/**
	 * 畅捷通数据进存折
	 * 
	 * @throws Exception
	 */
	public BigDecimal saveJfiPayDataVerifyByChanjet(final String companyCode, final SysUser sysUser, final BigDecimal totalMoney, String operaterCode, final String operaterName,
			final String uniqueCode, JfiChanjetLog jfiChanjetLog) throws AppException;

	public JfiBankbookJournal getBankbookJournal(String userCode, String type, String nodes);

	/**
	 * 盛付通进存折
	 * 
	 * @param companyCode
	 * @param sysUser
	 * @param totalMoney
	 * @param operaterCode
	 * @param operaterName
	 * @param uniqueCode
	 * @param fiChannelLog
	 * @return
	 */
	public BigDecimal saveFiPayDataVerifyByChannel(String companyCode, SysUser sysUser, BigDecimal totalMoney, String operaterCode, String operaterName, String uniqueCode,
			FiChannelLog fiChannelLog);
	
	/**
	 * 易宝支付存折
	 * @param companyCode
	 * @param sysUser
	 * @param totalMoney
	 * @param operaterCode
	 * @param operaterName
	 * @param uniqueCode
	 * @param jfiYeepayLog
	 * @return
	 * @throws AppException
	 */
	public BigDecimal saveJfiPayDataVerifyByYeePay(final String companyCode, final SysUser sysUser, final BigDecimal totalMoney, String operaterCode, final String operaterName, final String uniqueCode, JfiYeepayLog jfiYeepayLog) throws AppException;
	
	/**
	 * Modify By WuCF 20150923 
	 * 融宝支付存折
	 * @param companyCode
	 * @param sysUser
	 * @param totalMoney
	 * @param operaterCode
	 * @param operaterName
	 * @param uniqueCode
	 * @param jfiYeepayLog
	 * @return
	 * @throws AppException
	 */
	public BigDecimal saveJfiPayDataVerifyByReapal(final String companyCode, final SysUser sysUser, final BigDecimal totalMoney, String operaterCode, final String operaterName, final String uniqueCode, JfiReapalLog jfiReapalLog) throws AppException;


	public BigDecimal saveJfiPayDataVerify(final SysUser sysUser,final BigDecimal totalMoney,final String uniqueCode, JfiPayLog entity,String[] config) throws AppException;

	/**
	 * Modify By WuCF 20160816
	 * 保存支付中间表数据
	 * @param jpoBankBookPayList
	 */
	public void createJpoBankBookPayList(String tempId,String checkUserCode);
}
