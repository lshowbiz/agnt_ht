
package com.joymain.jecs.fi.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.fi.dao.FiCcoinBalanceDao;
import com.joymain.jecs.fi.dao.FiCcoinJournalDao;
import com.joymain.jecs.fi.model.FiCcoinBalance;
import com.joymain.jecs.fi.model.FiCcoinJournal;
import com.joymain.jecs.fi.service.FiCcoinJournalManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
public class FiCcoinJournalManagerImpl extends BaseManager implements FiCcoinJournalManager {
    private FiCcoinJournalDao dao;
    private FiCcoinBalanceDao fiCcoinBalanceDao;

    public void setFiCcoinBalanceDao(FiCcoinBalanceDao fiCcoinBalanceDao) {
		this.fiCcoinBalanceDao = fiCcoinBalanceDao;
	}

	public void setDao(FiCcoinJournalDao dao) {
		this.dao = dao;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiCcoinJournalDao(FiCcoinJournalDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiCcoinJournalManager#getFiCcoinJournals(com.joymain.jecs.fi.model.FiCcoinJournal)
     */
    public List getFiCcoinJournals(final FiCcoinJournal fiCcoinJournal) {
        return dao.getFiCcoinJournals(fiCcoinJournal);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiCcoinJournalManager#getFiCcoinJournal(String journalId)
     */
    public FiCcoinJournal getFiCcoinJournal(final String journalId) {
        return dao.getFiCcoinJournal(new Long(journalId));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiCcoinJournalManager#saveFiCcoinJournal(FiCcoinJournal fiCcoinJournal)
     */
    public void saveFiCcoinJournal(FiCcoinJournal fiCcoinJournal) {
        dao.saveFiCcoinJournal(fiCcoinJournal);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiCcoinJournalManager#removeFiCcoinJournal(String journalId)
     */
    public void removeFiCcoinJournal(final String journalId) {
        dao.removeFiCcoinJournal(new Long(journalId));
    }
    //added for getFiCcoinJournalsByCrm
    public List getFiCcoinJournalsByCrm(CommonRecord crm, Pager pager){
	return dao.getFiCcoinJournalsByCrm(crm,pager);
    }
    
    /**
	 * 存入资金至用户帐户
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
	        String operaterCode, final String operaterName, final String uniqueCode, final String[] notes, final Long[] appId, final Date[] moneyDate){
		//判断用户存折是否存在
		FiCcoinBalance fiCcoinBalance = this.fiCcoinBalanceDao.getFiCcoinBalanceForUpdate(sysUser.getUserCode());
		if (fiCcoinBalance == null) {
			throw new AppException("C分账户记录不存在");
		}
		//判断是否重复审单
		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
			FiCcoinJournal lastFiCcoinJournal = dao.getLastFiCcoinJournalByUnique(uniqueCode,"A");
			if (lastFiCcoinJournal != null && "A".equals(lastFiCcoinJournal.getDealType())) {
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
		FiCcoinJournal lastFiCcoinJournal = dao.getLastFiCcoinJournal(sysUser.getUserCode());
		if (lastFiCcoinJournal == null) {
			remainMoney = new BigDecimal(0);
			tempMoney =  new BigDecimal(0);
		} else {
			remainMoney = lastFiCcoinJournal.getBalance();
			tempMoney = lastFiCcoinJournal.getBalance();
		}

		Date currentDate = new Date();
		Date currentTime = new Date();
		for(int i = 0 ; i < moneyArray.length ; i++){
			//插入存折流水记录
			FiCcoinJournal fiCcoinJournal = new FiCcoinJournal();
			fiCcoinJournal.setSysUser(sysUser);
			long todayCount = dao.getCountByDate(sysUser.getUserCode());
			fiCcoinJournal.setSerial((int) (todayCount + 1));
			fiCcoinJournal.setDealType("A");
			fiCcoinJournal.setDealDate(currentDate);
			fiCcoinJournal.setCoin(moneyArray[i]);
			fiCcoinJournal.setNotes(notes[i]);
			tempMoney = tempMoney.add(moneyArray[i]);
			fiCcoinJournal.setBalance(tempMoney);
			fiCcoinJournal.setCreaterCode(operaterCode);
			fiCcoinJournal.setCreaterName(operaterName);
			fiCcoinJournal.setCreateTime(currentTime);
			fiCcoinJournal.setMoneyType(moneyType[i]);
			fiCcoinJournal.setUniqueCode(uniqueCode);
			fiCcoinJournal.setAppId(appId[i]);
			dao.saveFiCcoinJournal(fiCcoinJournal);
			
			//折合存入另外两张表
			//fiCcoinJournalDetailManager.saveFiPayDetailDataVerify(companyCode, sysUser, moneyType[i], moneyArray[i], operaterCode, operaterName, uniqueCode, notes[i], appId[i], moneyDate[i]);
			//moneyDate[i];
		}

		fiCcoinBalance.setBalance(remainMoney.add(money));
		fiCcoinBalanceDao.saveFiCcoinBalance(fiCcoinBalance);
	}

	/**
	 * 从用户帐户扣取金额
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
	public void saveJfiBankbookDeduct(final String companyCode, final SysUser sysUser, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode,  final String[] notes, final Long[] appId) throws AppException{
		//判断用户存折是否存在
		FiCcoinBalance fiCcoinBalance = this.fiCcoinBalanceDao.getFiCcoinBalanceForUpdate(sysUser.getUserCode());
		if (fiCcoinBalance == null) {
			throw new AppException("存折记录不存在");
		}
		//判断是否重复审单
		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
			FiCcoinJournal lastFiCcoinJournal = dao.getLastFiCcoinJournalByUnique(uniqueCode,"D");
			if (lastFiCcoinJournal != null && "D".equals(lastFiCcoinJournal.getDealType())) {
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
		FiCcoinJournal lastFiCcoinJournal = this.dao.getLastFiCcoinJournal(sysUser.getUserCode());
		if (lastFiCcoinJournal == null || lastFiCcoinJournal.getBalance().compareTo(money) == -1) {
			throw new AppException("error.fiCcoinJournal.balance.not.enough");
		}

		BigDecimal remainMoney;
		BigDecimal tempMoney;
		if (lastFiCcoinJournal == null) {
			remainMoney = new BigDecimal(0);
			tempMoney =  new BigDecimal(0);
		} else {
			remainMoney = lastFiCcoinJournal.getBalance();
			tempMoney = lastFiCcoinJournal.getBalance();
		}
		
		for(int i = 0 ; i < moneyArray.length ; i++){
			//插入存折流水记录
			FiCcoinJournal fiCcoinJournal = new FiCcoinJournal();
			fiCcoinJournal.setSysUser(sysUser);
			long todayCount = this.dao.getCountByDate(sysUser.getUserCode());
			fiCcoinJournal.setSerial((int) (todayCount + 1));
			fiCcoinJournal.setDealType("D");
			fiCcoinJournal.setDealDate(currentDate);
			fiCcoinJournal.setMoneyType(moneyType[i]);
			fiCcoinJournal.setCoin(moneyArray[i]);
			fiCcoinJournal.setNotes(notes[i]);
			tempMoney = tempMoney.subtract(moneyArray[i]);
			fiCcoinJournal.setBalance(tempMoney);
			fiCcoinJournal.setCreaterCode(operaterCode);
			fiCcoinJournal.setCreaterName(operaterName);
			fiCcoinJournal.setCreateTime(currentTime);
			fiCcoinJournal.setUniqueCode(uniqueCode);
			fiCcoinJournal.setAppId(appId[i]);
			this.dao.saveFiCcoinJournal(fiCcoinJournal);
			
			//折合存入另外两张表
		}

		fiCcoinBalance.setBalance(remainMoney.subtract(money));
		fiCcoinBalanceDao.saveFiCcoinBalance(fiCcoinBalance);
	}
}
