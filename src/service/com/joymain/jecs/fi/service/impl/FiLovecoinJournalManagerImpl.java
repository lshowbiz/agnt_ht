
package com.joymain.jecs.fi.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.fi.dao.FiLovecoinBalanceDao;
import com.joymain.jecs.fi.dao.FiLovecoinJournalDao;
import com.joymain.jecs.fi.model.FiLovecoinBalance;
import com.joymain.jecs.fi.model.FiLovecoinJournal;
import com.joymain.jecs.fi.service.FiLovecoinJournalManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
public class FiLovecoinJournalManagerImpl extends BaseManager implements FiLovecoinJournalManager {
    private FiLovecoinJournalDao dao;
    
    private FiLovecoinBalanceDao fiLovecoinBalanceDao;

    public void setFiLovecoinBalanceDao(FiLovecoinBalanceDao fiLovecoinBalanceDao) {
		this.fiLovecoinBalanceDao = fiLovecoinBalanceDao;
	}

	public void setDao(FiLovecoinJournalDao dao) {
		this.dao = dao;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiLovecoinJournalDao(FiLovecoinJournalDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiLovecoinJournalManager#getFiLovecoinJournals(com.joymain.jecs.fi.model.FiLovecoinJournal)
     */
    public List getFiLovecoinJournals(final FiLovecoinJournal fiLovecoinJournal) {
        return dao.getFiLovecoinJournals(fiLovecoinJournal);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiLovecoinJournalManager#getFiLovecoinJournal(String journalId)
     */
    public FiLovecoinJournal getFiLovecoinJournal(final String journalId) {
        return dao.getFiLovecoinJournal(new Long(journalId));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiLovecoinJournalManager#saveFiLovecoinJournal(FiLovecoinJournal fiLovecoinJournal)
     */
    public void saveFiLovecoinJournal(FiLovecoinJournal fiLovecoinJournal) {
        dao.saveFiLovecoinJournal(fiLovecoinJournal);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiLovecoinJournalManager#removeFiLovecoinJournal(String journalId)
     */
    public void removeFiLovecoinJournal(final String journalId) {
        dao.removeFiLovecoinJournal(new Long(journalId));
    }
    //added for getFiLovecoinJournalsByCrm
    public List getFiLovecoinJournalsByCrm(CommonRecord crm, Pager pager){
    	return dao.getFiLovecoinJournalsByCrm(crm,pager);
    }
    
    /**
	 * 爱心积分存钱进账
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
	        String operaterCode, final String operaterName, final String uniqueCode, final String[] notes, final Long[] appId, final Date[] moneyDate) throws AppException{
		
		//判断爱心积分账户是否存在
		FiLovecoinBalance fiLovecoinBalance = this.fiLovecoinBalanceDao.getFiLovecoinBalanceForUpdate(sysUser.getUserCode());
		if (fiLovecoinBalance == null) {
			throw new AppException("爱心积分账户不存在!");
		}
		//判断是否重复审单
		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
			FiLovecoinJournal lastFiLovecoinJournal = dao.getLastFiLovecoinJournalByUnique(uniqueCode,"A");
			if (lastFiLovecoinJournal != null && "A".equals(lastFiLovecoinJournal.getDealType())) {
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
		FiLovecoinJournal lastFiLovecoinJournal = dao.getLastFiLovecoinJournal(sysUser.getUserCode());
		if (lastFiLovecoinJournal == null) {
			remainMoney = new BigDecimal(0);
			tempMoney =  new BigDecimal(0);
		} else {
			remainMoney = lastFiLovecoinJournal.getBalance();
			tempMoney = lastFiLovecoinJournal.getBalance();
		}

		Date currentDate = new Date();
		Date currentTime = new Date();
		for(int i = 0 ; i < moneyArray.length ; i++){
			//插入存折流水记录
			FiLovecoinJournal fiLovecoinJournal = new FiLovecoinJournal();
			fiLovecoinJournal.setSysUser(sysUser);
			long todayCount = dao.getCountByDate(sysUser.getUserCode());
			fiLovecoinJournal.setSerial((int) (todayCount + 1));
			fiLovecoinJournal.setDealType("A");
			fiLovecoinJournal.setDealDate(currentDate);
			fiLovecoinJournal.setCoin(moneyArray[i]);
			fiLovecoinJournal.setNotes(notes[i]);
			tempMoney = tempMoney.add(moneyArray[i]);
			fiLovecoinJournal.setBalance(tempMoney);
			fiLovecoinJournal.setCreaterCode(operaterCode);
			fiLovecoinJournal.setCreaterName(operaterName);
			fiLovecoinJournal.setCreateTime(currentTime);
			fiLovecoinJournal.setMoneyType(moneyType[i]);
			fiLovecoinJournal.setUniqueCode(uniqueCode);
			fiLovecoinJournal.setAppId(appId[i]);
			dao.saveFiLovecoinJournal(fiLovecoinJournal);
			
			//折合存入另外两张表
			//fiLovecoinJournalDetailManager.saveFiPayDetailDataVerify(companyCode, ucMember, moneyType[i], moneyArray[i], operaterCode, operaterName, uniqueCode, notes[i], appId[i], moneyDate[i]);
			//moneyDate[i];
		}

		fiLovecoinBalance.setBalance(remainMoney.add(money));
		fiLovecoinBalanceDao.saveFiLovecoinBalance(fiLovecoinBalance);
	}
    
    /**
	 * 从用户爱心积分帐户扣取金额
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
		FiLovecoinBalance fiLovecoinBalance = this.fiLovecoinBalanceDao.getFiLovecoinBalanceForUpdate(sysUser.getUserCode());
		if (fiLovecoinBalance == null) {
			throw new AppException("爱心积分账户不存在");
		}
		//判断是否重复审单
		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
			FiLovecoinJournal lastFiLovecoinJournal = dao.getLastFiLovecoinJournalByUnique(uniqueCode,"D");
			if (lastFiLovecoinJournal != null && "D".equals(lastFiLovecoinJournal.getDealType())) {
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
		FiLovecoinJournal lastFiLovecoinJournal = this.dao.getLastFiLovecoinJournal(sysUser.getUserCode());
		if (lastFiLovecoinJournal == null || lastFiLovecoinJournal.getBalance().compareTo(money) == -1) {
			throw new AppException("error.fiLovecoinJournal.balance.not.enough");
		}

		BigDecimal remainMoney;
		BigDecimal tempMoney;
		if (lastFiLovecoinJournal == null) {
			remainMoney = new BigDecimal(0);
			tempMoney =  new BigDecimal(0);
		} else {
			remainMoney = lastFiLovecoinJournal.getBalance();
			tempMoney = lastFiLovecoinJournal.getBalance();
		}
		
		for(int i = 0 ; i < moneyArray.length ; i++){
			//插入存折流水记录
			FiLovecoinJournal fiLovecoinJournal = new FiLovecoinJournal();
			fiLovecoinJournal.setSysUser(sysUser);
			long todayCount = this.dao.getCountByDate(sysUser.getUserCode());
			fiLovecoinJournal.setSerial((int) (todayCount + 1));
			fiLovecoinJournal.setDealType("D");
			fiLovecoinJournal.setDealDate(currentDate);
			fiLovecoinJournal.setMoneyType(moneyType[i]);
			fiLovecoinJournal.setCoin(moneyArray[i]);
			fiLovecoinJournal.setNotes(notes[i]);
			tempMoney = tempMoney.subtract(moneyArray[i]);
			fiLovecoinJournal.setBalance(tempMoney);
			fiLovecoinJournal.setCreaterCode(operaterCode);
			fiLovecoinJournal.setCreaterName(operaterName);
			fiLovecoinJournal.setCreateTime(currentTime);
			fiLovecoinJournal.setUniqueCode(uniqueCode);
			fiLovecoinJournal.setAppId(appId[i]);
			this.dao.saveFiLovecoinJournal(fiLovecoinJournal);
			
			//折合存入另外两张表
		}

		fiLovecoinBalance.setBalance(remainMoney.subtract(money));
		fiLovecoinBalanceDao.saveFiLovecoinBalance(fiLovecoinBalance);
	}
	
	/**
	 * 爱心积分账户开户
	 * @param userCode
	 */
	public void createLovecoin(String userCode){
		
		//判断账户是否存在
		FiLovecoinBalance tempFiLovecoinBalance = this.fiLovecoinBalanceDao.getFiLovecoinBalanceForUpdate(userCode);
		
		//不存在，则创建
		if (tempFiLovecoinBalance == null) {
			
			FiLovecoinBalance fiLovecoinBalance=new FiLovecoinBalance();
			fiLovecoinBalance.setUserCode(userCode);
			fiLovecoinBalance.setBalance(BigDecimal.ZERO);
			
			fiLovecoinBalanceDao.saveFiLovecoinBalance(fiLovecoinBalance);
		}
	}
}
