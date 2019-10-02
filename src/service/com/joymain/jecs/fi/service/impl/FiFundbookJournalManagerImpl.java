
package com.joymain.jecs.fi.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.fi.model.FiFundbookBalance;
import com.joymain.jecs.fi.model.FiFundbookJournal;
import com.joymain.jecs.fi.model.FiFundbookTemp;
import com.joymain.jecs.fi.dao.FiFundbookBalanceDao;
import com.joymain.jecs.fi.dao.FiFundbookJournalDao;
import com.joymain.jecs.fi.dao.FiFundbookTempDao;
import com.joymain.jecs.fi.service.FiFundbookJournalManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
public class FiFundbookJournalManagerImpl extends BaseManager implements FiFundbookJournalManager {
    private FiFundbookJournalDao dao;
    private FiFundbookBalanceDao fiFundbookBalanceDao;
    private FiFundbookTempDao fiFundbookTempDao;

    public void setFiFundbookTempDao(FiFundbookTempDao fiFundbookTempDao) {
		this.fiFundbookTempDao = fiFundbookTempDao;
	}

	public void setFiFundbookBalanceDao(FiFundbookBalanceDao fiFundbookBalanceDao) {
		this.fiFundbookBalanceDao = fiFundbookBalanceDao;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiFundbookJournalDao(FiFundbookJournalDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiFundbookJournalManager#getFiFundbookJournals(com.joymain.jecs.fi.model.FiFundbookJournal)
     */
    public List getFiFundbookJournals(final FiFundbookJournal fiFundbookJournal) {
        return dao.getFiFundbookJournals(fiFundbookJournal);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiFundbookJournalManager#getFiFundbookJournal(String journalId)
     */
    public FiFundbookJournal getFiFundbookJournal(final String journalId) {
        return dao.getFiFundbookJournal(new Long(journalId));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiFundbookJournalManager#saveFiFundbookJournal(FiFundbookJournal fiFundbookJournal)
     */
    public void saveFiFundbookJournal(FiFundbookJournal fiFundbookJournal) {
        dao.saveFiFundbookJournal(fiFundbookJournal);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiFundbookJournalManager#removeFiFundbookJournal(String journalId)
     */
    public void removeFiFundbookJournal(final String journalId) {
        dao.removeFiFundbookJournal(new Long(journalId));
    }
    //added for getFiFundbookJournalsByCrm
    public List getFiFundbookJournalsByCrm(CommonRecord crm, Pager pager){
    	return dao.getFiFundbookJournalsByCrm(crm,pager);
    }
    
    /**
	 * 存入资金至产业基金帐户
	 * @param companyCode 公司编码
	 * @param userCode 用户编码
	 * @param moneyType 资金类别
	 * @param money 资金金额
	 * @param operaterCode 操作人编码
	 * @param operaterName 操作人姓名
	 * @param uniqueCode 唯一码，用于防止重复,0为没有
	 * @param bankbookType 基金类型：1，分红基金；2，定向基金
	 * @param notes 存款说明
	 */
	public void saveFiPayDataVerify(final String companyCode, final String userCode, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode, final String[] notes, final String bankbookType, final String dataType){
		
		//判断用户帐户是否存在
		FiFundbookBalance fiFundbookBalanceTmp = this.fiFundbookBalanceDao.getFiFundbookBalanceByUserCodeAndFundbookType(userCode, bankbookType);
		if (fiFundbookBalanceTmp == null) {
			
			throw new AppException("账户余额不足!");
		}
		FiFundbookBalance fiFundbookBalance = this.fiFundbookBalanceDao.getFiFundbookBalanceForUpdate(fiFundbookBalanceTmp.getFbbId());
		if (fiFundbookBalance == null) {
			
			throw new AppException("基金帐户不存在!");
		}
		//判断是否重复审单
		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
			FiFundbookJournal lastFiFundbookJournal = dao.getLastFiFundbookJournalByUnique(uniqueCode,"A");
			if (lastFiFundbookJournal != null && "A".equals(lastFiFundbookJournal.getDealType())) {
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
		FiFundbookJournal lastFiFundbookJournal = this.dao.getLastFiFundbookJournal(userCode,bankbookType);
		if (lastFiFundbookJournal == null) {
			remainMoney = new BigDecimal(0);
			tempMoney =  new BigDecimal(0);
		} else {
			remainMoney = lastFiFundbookJournal.getBalance();
			tempMoney = lastFiFundbookJournal.getBalance();
		}

		Date currentDate = new Date();
		Date currentTime = new Date();
		for(int i = 0 ; i < moneyArray.length ; i++){

//			//插入存折临时记录
//			FiFundbookTemp fiFundbookTemp = new FiFundbookTemp();
//			fiFundbookTemp.setCompanyCode(companyCode);
//			fiFundbookTemp.setUserCode(sysUser.getUserCode());
//			long totalCount = this.fiFundbookTempDao.getCountByDate(companyCode, sysUser.getUserCode(), bankbookType);
//			fiFundbookTemp.setSerial(totalCount + 1);
//			fiFundbookTemp.setDealType("A");
//			fiFundbookTemp.setDealDate(currentDate);
//			fiFundbookTemp.setMoneyType(moneyType[i]);
//			fiFundbookTemp.setMoney(moneyArray[i]);
//			fiFundbookTemp.setNotes(notes[i]);
//			fiFundbookTemp.setStatus(2);
//			fiFundbookTemp.setCreaterCode(operaterCode);
//			fiFundbookTemp.setCreaterName(operaterName);
//			fiFundbookTemp.setCreateTime(currentTime);
//			fiFundbookTemp.setCheckerCode(operaterCode);
//			fiFundbookTemp.setCheckerName(operaterName);
//			fiFundbookTemp.setCheckeTime(currentTime);
//			fiFundbookTemp.setCheckMsg("OK");
//			fiFundbookTemp.setCheckType(1);
//			fiFundbookTemp.setBankbookType(bankbookType);
//
//			fiFundbookTempDao.saveFiFundbookTemp(fiFundbookTemp);
			//插入产业基金流水记录
			FiFundbookJournal fiFundbookJournal = new FiFundbookJournal();
			//fiFundbookJournal.setFiFundbookTemp(fiFundbookTemp);
			fiFundbookJournal.setCompanyCode(companyCode);
			fiFundbookJournal.setUserCode(userCode);
			long todayCount = this.dao.getCountByDate(companyCode, userCode);
			fiFundbookJournal.setSerial(todayCount + 1);
			fiFundbookJournal.setDealType("A");
			fiFundbookJournal.setDealDate(currentDate);
			fiFundbookJournal.setMoneyType(moneyType[i]);
			fiFundbookJournal.setMoney(moneyArray[i]);
			fiFundbookJournal.setNotes(notes[i]);
			tempMoney = tempMoney.add(moneyArray[i]);
			fiFundbookJournal.setBalance(tempMoney);
			fiFundbookJournal.setCreaterCode(operaterCode);
			fiFundbookJournal.setCreaterName(operaterName);
			fiFundbookJournal.setCreateTime(currentTime);
			fiFundbookJournal.setLastJournalId(lastFiFundbookJournal == null ? 0 : lastFiFundbookJournal.getJournalId());
			fiFundbookJournal.setLastMoney(lastFiFundbookJournal == null ? new BigDecimal(0) : lastFiFundbookJournal.getBalance());
			fiFundbookJournal.setUniqueCode(uniqueCode);
			fiFundbookJournal.setBankbookType(bankbookType);
			fiFundbookJournal.setDataType(dataType);

			dao.saveFiFundbookJournal(fiFundbookJournal);
		}

		fiFundbookBalance.setBalance(remainMoney.add(money));
		fiFundbookBalanceDao.saveFiFundbookBalance(fiFundbookBalance);
	}

	/**
	 * 从产业基金帐户扣取金额
	 * @param companyCode 公司编码
	 * @param userCode 用户编码
	 * @param moneyType 资金类别
	 * @param money 资金金额
	 * @param operaterCode 操作人编码
	 * @param operaterName 操作人姓名
	 * @param uniqueCode 业务唯一码,0为没有
	 * @param notes 存款说明
	 * @param bankbookType 基金类型：1，分红基金；2，定向基金
	 * @throws AppException 存款不够的错误
	 */
	public void saveFiBankbookDeduct(final String companyCode, final String userCode, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode,  final String[] notes, final String bankbookType, final String dataType) throws AppException{
		
		//判断用户帐户是否存在
		FiFundbookBalance fiFundbookBalanceTmp = this.fiFundbookBalanceDao.getFiFundbookBalanceByUserCodeAndFundbookType(userCode, bankbookType);
		if (fiFundbookBalanceTmp == null) {
			throw new AppException("账户余额不足!");
		}
		FiFundbookBalance fiFundbookBalance = this.fiFundbookBalanceDao.getFiFundbookBalanceForUpdate(fiFundbookBalanceTmp.getFbbId());
		if (fiFundbookBalance == null) {
			throw new AppException("基金帐户不存在!");
		}
		//判断是否重复审单
		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
			FiFundbookJournal lastFiFundbookJournal = dao.getLastFiFundbookJournalByUnique(uniqueCode,"D");
			if (lastFiFundbookJournal != null && "D".equals(lastFiFundbookJournal.getDealType())) {
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
		FiFundbookJournal lastFiFundbookJournal = this.dao.getLastFiFundbookJournal(userCode,bankbookType);
		if (lastFiFundbookJournal == null || lastFiFundbookJournal.getBalance().compareTo(money) == -1) {
			throw new AppException("发展基金帐户余额不足！");
		}

		BigDecimal remainMoney;
		BigDecimal tempMoney;
		if (lastFiFundbookJournal == null) {
			remainMoney = new BigDecimal(0);
			tempMoney =  new BigDecimal(0);
		} else {
			remainMoney = lastFiFundbookJournal.getBalance();
			tempMoney = lastFiFundbookJournal.getBalance();
		}
		if (remainMoney.compareTo(money) == -1) {
			throw new AppException("发展基金帐户余额不足！");
		}
		
		for(int i = 0 ; i < moneyArray.length ; i++){
//			//插入存折临时记录
//			FiFundbookTemp fiFundbookTemp = new FiFundbookTemp();
//			fiFundbookTemp.setCompanyCode(companyCode);
//			fiFundbookTemp.setSysUser(sysUser);
//			long totalCount = this.fiFundbookTempDao.getCountByDate(companyCode, sysUser.getUserCode(), bankbookType);
//			fiFundbookTemp.setSerial(totalCount + 1);
//			fiFundbookTemp.setDealType("D");
//			fiFundbookTemp.setDealDate(currentDate);
//			fiFundbookTemp.setMoneyType(moneyType[i]);
//			fiFundbookTemp.setMoney(moneyArray[i]);
//			fiFundbookTemp.setNotes(notes[i]);
//			fiFundbookTemp.setStatus(2);
//			fiFundbookTemp.setCreaterCode(operaterCode);
//			fiFundbookTemp.setCreaterName(operaterName);
//			fiFundbookTemp.setCreateTime(currentTime);
//			fiFundbookTemp.setCheckerCode(operaterCode);
//			fiFundbookTemp.setCheckerName(operaterName);
//			fiFundbookTemp.setCheckeTime(currentTime);
//			fiFundbookTemp.setCheckMsg("OK");
//			fiFundbookTemp.setCheckType(1);
//			fiFundbookTemp.setBankbookType(bankbookType);
//			fiFundbookTempDao.saveFiFundbookTemp(fiFundbookTemp);

			//插入存折流水记录
			FiFundbookJournal fiFundbookJournal = new FiFundbookJournal();
			//fiFundbookJournal.setFiFundbookTemp(fiFundbookTemp);
			fiFundbookJournal.setCompanyCode(companyCode);
			fiFundbookJournal.setUserCode(userCode);
			long todayCount = this.dao.getCountByDate(companyCode, userCode);
			fiFundbookJournal.setSerial(todayCount + 1);
			fiFundbookJournal.setDealType("D");
			fiFundbookJournal.setDealDate(currentDate);
			fiFundbookJournal.setMoneyType(moneyType[i]);
			fiFundbookJournal.setMoney(moneyArray[i]);
			fiFundbookJournal.setNotes(notes[i]);
			tempMoney = tempMoney.subtract(moneyArray[i]);
			fiFundbookJournal.setBalance(tempMoney);
			fiFundbookJournal.setCreaterCode(operaterCode);
			fiFundbookJournal.setCreaterName(operaterName);
			fiFundbookJournal.setCreateTime(currentTime);
			fiFundbookJournal.setLastJournalId(lastFiFundbookJournal == null ? 0 : lastFiFundbookJournal.getJournalId());
			fiFundbookJournal.setLastMoney(lastFiFundbookJournal == null ? new BigDecimal(0) : lastFiFundbookJournal.getBalance());
			fiFundbookJournal.setUniqueCode(uniqueCode);
			fiFundbookJournal.setBankbookType(bankbookType);
			fiFundbookJournal.setDataType(dataType);
			
			this.dao.saveFiFundbookJournal(fiFundbookJournal);
		}

		fiFundbookBalance.setBalance(remainMoney.subtract(money));
		fiFundbookBalanceDao.saveFiFundbookBalance(fiFundbookBalance);
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

	/**
	 * 临时条目审批通过，将临时条目插入基金流水表
	 * @param tempId
	 * @param sysUser
	 * @return
	 */
	@Override
	public void saveFiFundbookTempCheck(String tempId, SysUser sysUser) throws AppException {
		
		//获取临时条目对象
		FiFundbookTemp fiFundbookTemp = fiFundbookTempDao.getFiFundbookTemp(new Long(tempId));
		
		if(fiFundbookTemp!=null){
			
			//转账金额
			BigDecimal[] moneyArray = new BigDecimal[1];
			moneyArray[0] = fiFundbookTemp.getMoney();
			
			//资金类型
			Integer[] moneyType = new Integer[1];
			moneyType[0] = fiFundbookTemp.getMoneyType();

			//说明
			String[] notes = new String[1];
			
			if(("1").equals(fiFundbookTemp.getBankbookType())){
				
				if(!StringUtil.isEmpty(fiFundbookTemp.getNotes())){
					notes[0] = "分红基金条目审批确认,"+fiFundbookTemp.getNotes();
				}else{
					notes[0] = "分红基金条目审批确认";
				}
			}
			if(("2").equals(fiFundbookTemp.getBankbookType())){
				
				if(!StringUtil.isEmpty(fiFundbookTemp.getNotes())){
					notes[0] = "定向基金条目审批确认,"+fiFundbookTemp.getNotes();
				}else{
					notes[0] = "定向基金条目审批确认";
				}
			}
			
			if(("A").equals(fiFundbookTemp.getDealType())){
				//存钱入产业化基金账户
				this.saveFiPayDataVerify("CN", fiFundbookTemp.getUserCode(), moneyType, moneyArray, sysUser.getUserCode(), sysUser.getUserName(), fiFundbookTemp.getTempId().toString(), notes, fiFundbookTemp.getBankbookType(), "1");
			}
			if(("D").equals(fiFundbookTemp.getDealType())){
				//从产业化基金账户扣钱
				this.saveFiBankbookDeduct("CN", fiFundbookTemp.getUserCode(), moneyType, moneyArray, sysUser.getUserCode(), sysUser.getUserName(), fiFundbookTemp.getTempId().toString(), notes, fiFundbookTemp.getBankbookType(), "1");
			}
			
			//修改临时条目状态
			fiFundbookTemp.setStatus(2);
			fiFundbookTemp.setCheckerCode(sysUser.getUserCode());
			fiFundbookTemp.setCheckerName(sysUser.getUserName());
			fiFundbookTemp.setCheckeTime(new Date());
			fiFundbookTemp.setCheckType(0);//核准方式 0:手工 1:自动
			fiFundbookTempDao.saveFiFundbookTemp(fiFundbookTemp);
		}
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
	public List getFiFundbookJournalsStat(final String companyCode, final String dealType, final Integer[] moneyTypes,
	        boolean inverseMoneyType, final String startDate, final String endDate, final String bankbookType) {
		return dao.getFiFundbookJournalsStat(companyCode, dealType, moneyTypes, inverseMoneyType, startDate, endDate,bankbookType);
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
	 * 获取收支统计金额 incTotal:收入 expTotal:支出
	 * @param crm
	 * @return
	 */
	@Override
	public Map getIncExpStatMap(CommonRecord crm) {
		// TODO Auto-generated method stub
		return dao.getIncExpStatMap(crm);
	}
}
