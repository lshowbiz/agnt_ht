
package com.joymain.jecs.fi.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.fi.dao.FiBankbookBalanceDao;
import com.joymain.jecs.fi.dao.FiBankbookJournalDao;
import com.joymain.jecs.fi.dao.FiBankbookTempDao;
import com.joymain.jecs.fi.model.FiBankbookBalance;
import com.joymain.jecs.fi.model.FiBankbookJournal;
import com.joymain.jecs.fi.model.FiBankbookTemp;
import com.joymain.jecs.fi.model.FiProductPointJournal;
import com.joymain.jecs.fi.model.JfiPosImport;
import com.joymain.jecs.fi.service.FiBankbookJournalManager;
import com.joymain.jecs.fi.service.JfiPosImportManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;

public class FiBankbookJournalManagerImpl extends BaseManager implements FiBankbookJournalManager {
    private FiBankbookJournalDao dao;
	private FiBankbookTempDao fiBankbookTempDao;
	private FiBankbookBalanceDao fiBankbookBalanceDao;
    private JfiPosImportManager jfiPosImportManager = null;

    public void setJfiPosImportManager(JfiPosImportManager jfiPosImportManager) {
        this.jfiPosImportManager = jfiPosImportManager;
    }

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiBankbookJournalDao(FiBankbookJournalDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.sp.spms.fi.service.FiBankbookJournalManager#getFiBankbookJournals(com.sp.spms.fi.model.FiBankbookJournal)
     */
    public List getFiBankbookJournals(final FiBankbookJournal fiBankbookJournal) {
        return dao.getFiBankbookJournals(fiBankbookJournal);
    }

    /**
     * @see com.sp.spms.fi.service.FiBankbookJournalManager#getFiBankbookJournal(String journalId)
     */
    public FiBankbookJournal getFiBankbookJournal(final String journalId) {
        return dao.getFiBankbookJournal(new Long(journalId));
    }

    /**
     * @see com.sp.spms.fi.service.FiBankbookJournalManager#saveFiBankbookJournal(FiBankbookJournal fiBankbookJournal)
     */
    public void saveFiBankbookJournal(FiBankbookJournal fiBankbookJournal) {
        dao.saveFiBankbookJournal(fiBankbookJournal);
    }

    /**
     * @see com.sp.spms.fi.service.FiBankbookJournalManager#removeFiBankbookJournal(String journalId)
     */
    public void removeFiBankbookJournal(final String journalId) {
        dao.removeFiBankbookJournal(new Long(journalId));
    }
    //added for getFiBankbookJournalsByCrm
    public List getFiBankbookJournalsByCrm(CommonRecord crm, Pager pager){
	return dao.getFiBankbookJournalsByCrm(crm,pager);
    }
    
    /**
     * Add By WuCF 20140320
	 * 基金余额查询总计
	 * @param crm
	 * @return
	 */
	public Map getSumAmountByCrm(CommonRecord crm){
		return dao.getSumAmountByCrm(crm);
	}
	
	public Map getSumPPByCrm(CommonRecord crm){
		return dao.getSumPPByCrm(crm);
	}
	
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
	        String operaterCode, final String operaterName, final String uniqueCode, final String note, final SysUser receiveSysUser){
    	if(sysUser.getUserCode().equals(receiveSysUser.getUserCode())){
    		throw new AppException("不能自己转账给自己。");
    	}
    	if(!sysUser.getCompanyCode().equals(companyCode) || !sysUser.getCompanyCode().equals(receiveSysUser.getCompanyCode())){
    		throw new AppException("国别不同。");
    	}
    	BigDecimal[] moneyArray = {money};
    	String[] notes = {note};
    	Integer[] moneyTypes = {moneyType};
    	this.saveFiBankbookDeduct(companyCode, sysUser, moneyTypes, moneyArray, operaterCode, operaterName, uniqueCode, notes, "1","1"); 	
    	this.saveFiPayDataVerify(companyCode, receiveSysUser, moneyTypes, moneyArray, operaterCode, operaterName, uniqueCode, notes, "1","1");
    }
    
	/**
	 * 验证存折条目
	 * @param tempId
	 * @param sysUser
	 * @return
	 */
	public void saveFiBankbookTempCheck(final String tempId, final SysUser sysUser) throws AppException{
		try {
			Map resultMap = dao.callProcCheckFiBankbookTemp(new Long(tempId), sysUser);
			if ("1".equals(resultMap.get("Vi_Errno").toString())) {
				//余额不足
				throw new AppException("error.fiBankbookJournal.balance.not.enough");
			} else if ("2".equals(resultMap.get("Vi_Errno").toString())) {
				throw new AppException("fiBankbookTemp.verified");
			} else if ("3".equals(resultMap.get("Vi_Errno").toString())) {
				throw new AppException("未知错误");
			} else if ("4".equals(resultMap.get("Vi_Errno").toString())) {
				throw new AppException("此存折临时条目不存在");
			}
		} catch (Exception ex) {
			throw new AppException(ex);
		}
	}

	/**
	 * 获取收支统计金额 incTotal:收入 expTotal:支出
	 * @param crm
	 * @return
	 */
	public Map getIncExpStatMap(CommonRecord crm) {
		return dao.getIncExpStatMap(crm);
	}

	/**
	 * 获取公司截止至某一日期时(包含此日期)的用户电子存折余额总计
	 * @param companyCode
	 * @param queryDate yyyy-MM-dd
	 * @return
	 */
	public BigDecimal getTotalBalanceByDate(final String companyCode, final String queryDate, final String bankbookType) {
		return dao.getTotalBalanceByDate(companyCode, queryDate,bankbookType);
	}

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
	        boolean inverseMoneyType, final String startDate, final String endDate, final String bankbookType) {
		return dao.getFiBankbookJournalsStat(companyCode, dealType, moneyTypes, inverseMoneyType, startDate, endDate,bankbookType);
	}

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
	        String operaterCode, final String operaterName, final String uniqueCode, final String[] notes, final String bankbookType, final String dataType){
		//判断用户存折是否存在
		FiBankbookBalance fiBankbookBalanceTmp = this.fiBankbookBalanceDao.getFiBankbookBalanceByUserCodeAndBankbookType(sysUser.getUserCode(), bankbookType);
		FiBankbookBalance fiBankbookBalance = this.fiBankbookBalanceDao.getFiBankbookBalanceForUpdate(fiBankbookBalanceTmp.getFbbId());
		if (fiBankbookBalance == null) {
			throw new AppException("发展基金帐户不存在!");
		}
		//判断是否重复审单
		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
			FiBankbookJournal lastFiBankbookJournal = dao.getLastFiBankbookJournalByUnique(uniqueCode,"A");
			if (lastFiBankbookJournal != null && "A".equals(lastFiBankbookJournal.getDealType())) {
				throw new AppException("重复操作");
			}
		}
		
		BigDecimal money = new BigDecimal(0);
		for(int i = 0 ; i < moneyArray.length ; i++){
			if(moneyArray[i].compareTo(new BigDecimal("0"))==-1){
				throw new AppException("金额参数必须大于零");
			}
			money = money.add(moneyArray[i]);
		}

		BigDecimal remainMoney;
		BigDecimal tempMoney;
		FiBankbookJournal lastFiBankbookJournal = this.dao.getLastFiBankbookJournal(sysUser.getUserCode(),bankbookType);
		if (lastFiBankbookJournal == null) {
			remainMoney = new BigDecimal(0);
			tempMoney =  new BigDecimal(0);
		} else {
			remainMoney = lastFiBankbookJournal.getBalance();
			tempMoney = lastFiBankbookJournal.getBalance();
		}

		Date currentDate = new Date();
		Date currentTime = new Date();
		for(int i = 0 ; i < moneyArray.length ; i++){

			//插入存折临时记录
			FiBankbookTemp fiBankbookTemp = new FiBankbookTemp();
			fiBankbookTemp.setCompanyCode(companyCode);
			fiBankbookTemp.setSysUser(sysUser);
			long totalCount = this.fiBankbookTempDao.getCountByDate(companyCode, sysUser.getUserCode(), bankbookType);
			fiBankbookTemp.setSerial(totalCount + 1);
			fiBankbookTemp.setDealType("A");
			fiBankbookTemp.setDealDate(currentDate);
			fiBankbookTemp.setMoneyType(moneyType[i]);
			fiBankbookTemp.setMoney(moneyArray[i]);
			fiBankbookTemp.setNotes(notes[i]);
			fiBankbookTemp.setStatus(2);
			fiBankbookTemp.setCreaterCode(operaterCode);
			fiBankbookTemp.setCreaterName(operaterName);
			fiBankbookTemp.setCreateTime(currentTime);
			fiBankbookTemp.setCheckerCode(operaterCode);
			fiBankbookTemp.setCheckerName(operaterName);
			fiBankbookTemp.setCheckeTime(currentTime);
			fiBankbookTemp.setCheckMsg("OK");
			fiBankbookTemp.setCheckType(1);
			fiBankbookTemp.setBankbookType(bankbookType);

			fiBankbookTempDao.saveFiBankbookTemp(fiBankbookTemp);
			//插入存折流水记录
			FiBankbookJournal fiBankbookJournal = new FiBankbookJournal();
			fiBankbookJournal.setFiBankbookTemp(fiBankbookTemp);
			fiBankbookJournal.setCompanyCode(companyCode);
			fiBankbookJournal.setSysUser(sysUser);
			long todayCount = this.dao.getCountByDate(companyCode, sysUser.getUserCode());
			fiBankbookJournal.setSerial(todayCount + 1);
			fiBankbookJournal.setDealType("A");
			fiBankbookJournal.setDealDate(currentDate);
			fiBankbookJournal.setMoneyType(moneyType[i]);
			fiBankbookJournal.setMoney(moneyArray[i]);
			fiBankbookJournal.setNotes(notes[i]);
			tempMoney = tempMoney.add(moneyArray[i]);
			fiBankbookJournal.setBalance(tempMoney);
			fiBankbookJournal.setCreaterCode(operaterCode);
			fiBankbookJournal.setCreaterName(operaterName);
			fiBankbookJournal.setCreateTime(currentTime);
			fiBankbookJournal.setLastJournalId(lastFiBankbookJournal == null ? 0 : lastFiBankbookJournal.getJournalId());
			fiBankbookJournal.setLastMoney(lastFiBankbookJournal == null ? new BigDecimal(0) : lastFiBankbookJournal.getBalance());
			fiBankbookJournal.setUniqueCode(uniqueCode);
			fiBankbookJournal.setBankbookType(bankbookType);
			fiBankbookJournal.setDataType(dataType);

			dao.saveFiBankbookJournal(fiBankbookJournal);
		}

		fiBankbookBalance.setBalance(remainMoney.add(money));
		fiBankbookBalanceDao.saveFiBankbookBalance(fiBankbookBalance);
	}

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
	        String operaterCode, final String operaterName, final String uniqueCode,  final String[] notes, final String bankbookType, final String dataType) throws AppException{
		//判断用户存折是否存在
		FiBankbookBalance fiBankbookBalanceTmp = this.fiBankbookBalanceDao.getFiBankbookBalanceByUserCodeAndBankbookType(sysUser.getUserCode(), bankbookType);
		FiBankbookBalance fiBankbookBalance = this.fiBankbookBalanceDao.getFiBankbookBalanceForUpdate(fiBankbookBalanceTmp.getFbbId());
		if (fiBankbookBalance == null) {
			throw new AppException("发展基金帐户不存在!");
		}
		//判断是否重复审单
		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
			FiBankbookJournal lastFiBankbookJournal = dao.getLastFiBankbookJournalByUnique(uniqueCode,"D");
			if (lastFiBankbookJournal != null && "D".equals(lastFiBankbookJournal.getDealType())) {
				throw new AppException("重复操作");
			}
		}
		
		BigDecimal money = new BigDecimal(0);
		for(int i = 0 ; i < moneyArray.length ; i++){
			if(moneyArray[i].compareTo(new BigDecimal("0"))==-1){
				throw new AppException("金额参数必须大于零");
			}
			money = money.add(moneyArray[i]);
		}

		Date currentDate = new Date();
		Date currentTime = new Date();

		//首先验证余额是否足够
		FiBankbookJournal lastFiBankbookJournal = this.dao.getLastFiBankbookJournal(sysUser.getUserCode(),bankbookType);
		if (lastFiBankbookJournal == null || lastFiBankbookJournal.getBalance().compareTo(money) == -1) {
			throw new AppException("发展基金帐户余额不足！");
		}

		BigDecimal remainMoney;
		BigDecimal tempMoney;
		if (lastFiBankbookJournal == null) {
			remainMoney = new BigDecimal(0);
			tempMoney =  new BigDecimal(0);
		} else {
			remainMoney = lastFiBankbookJournal.getBalance();
			tempMoney = lastFiBankbookJournal.getBalance();
		}
		if (remainMoney.compareTo(money) == -1) {
			throw new AppException("发展基金帐户余额不足！");
		}
		
		for(int i = 0 ; i < moneyArray.length ; i++){
			//插入存折临时记录
			FiBankbookTemp fiBankbookTemp = new FiBankbookTemp();
			fiBankbookTemp.setCompanyCode(companyCode);
			fiBankbookTemp.setSysUser(sysUser);
			long totalCount = this.fiBankbookTempDao.getCountByDate(companyCode, sysUser.getUserCode(), bankbookType);
			fiBankbookTemp.setSerial(totalCount + 1);
			fiBankbookTemp.setDealType("D");
			fiBankbookTemp.setDealDate(currentDate);
			fiBankbookTemp.setMoneyType(moneyType[i]);
			fiBankbookTemp.setMoney(moneyArray[i]);
			fiBankbookTemp.setNotes(notes[i]);
			fiBankbookTemp.setStatus(2);
			fiBankbookTemp.setCreaterCode(operaterCode);
			fiBankbookTemp.setCreaterName(operaterName);
			fiBankbookTemp.setCreateTime(currentTime);
			fiBankbookTemp.setCheckerCode(operaterCode);
			fiBankbookTemp.setCheckerName(operaterName);
			fiBankbookTemp.setCheckeTime(currentTime);
			fiBankbookTemp.setCheckMsg("OK");
			fiBankbookTemp.setCheckType(1);
			fiBankbookTemp.setBankbookType(bankbookType);
			fiBankbookTempDao.saveFiBankbookTemp(fiBankbookTemp);

			//插入存折流水记录
			FiBankbookJournal fiBankbookJournal = new FiBankbookJournal();
			fiBankbookJournal.setFiBankbookTemp(fiBankbookTemp);
			fiBankbookJournal.setCompanyCode(companyCode);
			fiBankbookJournal.setSysUser(sysUser);
			long todayCount = this.dao.getCountByDate(companyCode, sysUser.getUserCode());
			fiBankbookJournal.setSerial(todayCount + 1);
			fiBankbookJournal.setDealType("D");
			fiBankbookJournal.setDealDate(currentDate);
			fiBankbookJournal.setMoneyType(moneyType[i]);
			fiBankbookJournal.setMoney(moneyArray[i]);
			fiBankbookJournal.setNotes(notes[i]);
			tempMoney = tempMoney.subtract(moneyArray[i]);
			fiBankbookJournal.setBalance(tempMoney);
			fiBankbookJournal.setCreaterCode(operaterCode);
			fiBankbookJournal.setCreaterName(operaterName);
			fiBankbookJournal.setCreateTime(currentTime);
			fiBankbookJournal.setLastJournalId(lastFiBankbookJournal == null ? 0 : lastFiBankbookJournal.getJournalId());
			fiBankbookJournal.setLastMoney(lastFiBankbookJournal == null ? new BigDecimal(0) : lastFiBankbookJournal.getBalance());
			fiBankbookJournal.setUniqueCode(uniqueCode);
			fiBankbookJournal.setBankbookType(bankbookType);
			fiBankbookJournal.setDataType(dataType);
			
			this.dao.saveFiBankbookJournal(fiBankbookJournal);
		}

		fiBankbookBalance.setBalance(remainMoney.subtract(money));
		fiBankbookBalanceDao.saveFiBankbookBalance(fiBankbookBalance);
	}



	/**
	 * POS数据进存折
	 */
	public void saveJfiPayDataVerifyByPosImport(final String companyCode, final SysUser sysUser, String operaterCode, final String operaterName, JfiPosImport jfiPosImport){
		jfiPosImportManager.saveJfiPosImport(jfiPosImport);
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = jfiPosImport.getAmount();
		
		Integer[] moneyType = new Integer[1];
		moneyType[0] = 89;//POS现场刷卡支付(快钱) 
		
		String[] notes = new String[1];
		notes[0] = "POS现场刷卡支付,会员手机验证";
		this.saveFiPayDataVerify(companyCode, sysUser, moneyType, moneyArray, operaterCode, operaterName,jfiPosImport.getPosNo(),notes,"1","1");
	}

	public void setFiBankbookBalanceDao(FiBankbookBalanceDao fiBankbookBalanceDao) {
		this.fiBankbookBalanceDao = fiBankbookBalanceDao;
	}

	public void setFiBankbookTempDao(FiBankbookTempDao fiBankbookTempDao) {
		this.fiBankbookTempDao = fiBankbookTempDao;
	}

	/**
	 * 查询退单的退基金情况
	 * @author gw 2015-07-10
	 * @param roNo
	 * @return bigDecimal
	 */
	public BigDecimal getBeforeJprRefundJj(String roNo) {
		return dao.getBeforeJprRefundJj(roNo);
	}
	
	/**
	 * 查看这张退单之前退了多少基金
	 * @author gw 2015-07-13
	 * @param roNo
	 * @return bigDecimal
	 */
	public BigDecimal getJprRefundJjBack(String roNo) {
		return dao.getJprRefundJjBack(roNo);
	}

	@Override
	public List getFiProductPointJournalsByCrm(CommonRecord crm, Pager pager) {
		return dao.getFiProductPointJournalsByCrm(crm, pager);
	}

	@Override
	public Map getPpIncExpStatMap(CommonRecord crm) {
		return dao.getPpIncExpStatMap(crm);
	}

	@Override
	public BigDecimal getPpTotalBalanceByDate(String companyCode,
			String queryDate, String bankbookType) {
		return dao.getPpTotalBalanceByDate(companyCode, queryDate, bankbookType);
	}

	@Override
	public List getFiProductPointJournalsStat(String companyCode,
			String dealType, Integer[] moneyTypes, boolean inverseMoneyType,
			String startDate, String endDate, String bankbookType) {
		return dao.getFiProductPointJournalsStat(companyCode, dealType, moneyTypes, inverseMoneyType, startDate, endDate, bankbookType);
	}

	@Override
	public FiProductPointJournal getFiProductPointJournal(String journalId) {
		return dao.getFiProductPointJournal(new Long(journalId));
	}
}
