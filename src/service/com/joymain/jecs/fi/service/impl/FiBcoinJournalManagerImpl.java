
package com.joymain.jecs.fi.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.fi.dao.FiBcoinBalanceDao;
import com.joymain.jecs.fi.dao.FiBcoinJournalDao;
import com.joymain.jecs.fi.model.FiBcoinBalance;
import com.joymain.jecs.fi.model.FiBcoinJournal;
import com.joymain.jecs.fi.service.FiBcoinJournalManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;

@SuppressWarnings("rawtypes")
public class FiBcoinJournalManagerImpl extends BaseManager implements FiBcoinJournalManager {
    private FiBcoinJournalDao dao;
    
    private FiBcoinBalanceDao fiBcoinBalanceDao;

    public void setFiBcoinBalanceDao(FiBcoinBalanceDao fiBcoinBalanceDao) {
		this.fiBcoinBalanceDao = fiBcoinBalanceDao;
	}

	public void setDao(FiBcoinJournalDao dao) {
		this.dao = dao;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiBcoinJournalDao(FiBcoinJournalDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBcoinJournalManager#getFiBcoinJournals(com.joymain.jecs.fi.model.FiBcoinJournal)
     */
	public List getFiBcoinJournals(final FiBcoinJournal fiBcoinJournal) {
        return dao.getFiBcoinJournals(fiBcoinJournal);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBcoinJournalManager#getFiBcoinJournal(String journalId)
     */
    public FiBcoinJournal getFiBcoinJournal(final String journalId) {
        return dao.getFiBcoinJournal(new Long(journalId));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBcoinJournalManager#saveFiBcoinJournal(FiBcoinJournal fiBcoinJournal)
     */
    public void saveFiBcoinJournal(FiBcoinJournal fiBcoinJournal) {
        dao.saveFiBcoinJournal(fiBcoinJournal);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBcoinJournalManager#removeFiBcoinJournal(String journalId)
     */
    public void removeFiBcoinJournal(final String journalId) {
        dao.removeFiBcoinJournal(new Long(journalId));
    }
    //added for getFiBcoinJournalsByCrm
    public List getFiBcoinJournalsByCrm(CommonRecord crm, Pager pager){
    	return dao.getFiBcoinJournalsByCrm(crm,pager);
    }
    
    /**
     * Add By WuCF 20140320
	 * 欢乐积分查询总计
	 * @param crm
	 * @return
	 */
	public Map getSumAmountByCrm(CommonRecord crm){
		return dao.getSumAmountByCrm(crm);
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
	        String operaterCode, final String operaterName, final String uniqueCode, final String[] notes, final Long[] appId, final Date[] moneyDate, final String dataType){
		//判断用户存折是否存在
		FiBcoinBalance fiBcoinBalance = this.fiBcoinBalanceDao.getFiBcoinBalanceForUpdate(sysUser.getUserCode());
		if (fiBcoinBalance == null) {
			throw new AppException("B分账户记录不存在,用户编码："+sysUser.getUserCode());
		}
		//判断是否重复审单
		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
			FiBcoinJournal lastFiBcoinJournal = dao.getLastFiBcoinJournalByUnique(uniqueCode,"A");
			if (lastFiBcoinJournal != null && "A".equals(lastFiBcoinJournal.getDealType())) {
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
		FiBcoinJournal lastFiBcoinJournal = dao.getLastFiBcoinJournal(sysUser.getUserCode());
		if (lastFiBcoinJournal == null) {
			remainMoney = new BigDecimal(0);
			tempMoney =  new BigDecimal(0);
		} else {
			remainMoney = lastFiBcoinJournal.getBalance();
			tempMoney = lastFiBcoinJournal.getBalance();
		}

		Date currentDate = new Date();
		Date currentTime = new Date();
		for(int i = 0 ; i < moneyArray.length ; i++){
			//插入存折流水记录
			FiBcoinJournal fiBcoinJournal = new FiBcoinJournal();
			fiBcoinJournal.setSysUser(sysUser);
			long todayCount = dao.getCountByDate(sysUser.getUserCode());
			fiBcoinJournal.setSerial((int) (todayCount + 1));
			fiBcoinJournal.setDealType("A");
			fiBcoinJournal.setDealDate(currentDate);
			fiBcoinJournal.setCoin(moneyArray[i]);
			fiBcoinJournal.setNotes(notes[i]);
			if(notes[i].contains("手工修改积分=====")){
				fiBcoinJournal.setDescription(notes[i].substring(0,notes[i].indexOf("手工修改积分=====")));
				fiBcoinJournal.setNotes(notes[i].substring(notes[i].indexOf("手工修改积分=====")));
			}
			tempMoney = tempMoney.add(moneyArray[i]);
			fiBcoinJournal.setBalance(tempMoney);
			fiBcoinJournal.setCreaterCode(operaterCode);
			fiBcoinJournal.setCreaterName(operaterName);
			fiBcoinJournal.setCreateTime(currentTime);
			fiBcoinJournal.setMoneyType(moneyType[i]);
			fiBcoinJournal.setUniqueCode(uniqueCode);
			fiBcoinJournal.setAppId(appId[i]);
			fiBcoinJournal.setDataType(dataType);
			
			dao.saveFiBcoinJournal(fiBcoinJournal);
			
			//折合存入另外两张表
			//fiBcoinJournalDetailManager.saveFiPayDetailDataVerify(companyCode, ucMember, moneyType[i], moneyArray[i], operaterCode, operaterName, uniqueCode, notes[i], appId[i], moneyDate[i]);
			//moneyDate[i];
		}

		fiBcoinBalance.setBalance(remainMoney.add(money));
		fiBcoinBalanceDao.saveFiBcoinBalance(fiBcoinBalance);
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
	        String operaterCode, final String operaterName, final String uniqueCode,  final String[] notes, final Long[] appId, final String dataType) throws AppException{
		//判断用户存折是否存在
		FiBcoinBalance fiBcoinBalance = this.fiBcoinBalanceDao.getFiBcoinBalanceForUpdate(sysUser.getUserCode());
		if (fiBcoinBalance == null) {
			throw new AppException("存折记录不存在");
		}
		//判断是否重复审单
		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
			FiBcoinJournal lastFiBcoinJournal = dao.getLastFiBcoinJournalByUnique(uniqueCode,"D");
			if (lastFiBcoinJournal != null && "D".equals(lastFiBcoinJournal.getDealType())) {
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
		FiBcoinJournal lastFiBcoinJournal = this.dao.getLastFiBcoinJournal(sysUser.getUserCode());
		if (lastFiBcoinJournal == null || lastFiBcoinJournal.getBalance().compareTo(money) == -1) {
			throw new AppException("error.fiBcoinJournal.balance.not.enough");
		}

		BigDecimal remainMoney;
		BigDecimal tempMoney;
		if (lastFiBcoinJournal == null) {
			remainMoney = new BigDecimal(0);
			tempMoney =  new BigDecimal(0);
		} else {
			remainMoney = lastFiBcoinJournal.getBalance();
			tempMoney = lastFiBcoinJournal.getBalance();
		}
		
		for(int i = 0 ; i < moneyArray.length ; i++){
			//插入存折流水记录
			FiBcoinJournal fiBcoinJournal = new FiBcoinJournal();
			fiBcoinJournal.setSysUser(sysUser);
			long todayCount = this.dao.getCountByDate(sysUser.getUserCode());
			fiBcoinJournal.setSerial((int) (todayCount + 1));
			fiBcoinJournal.setDealType("D");
			fiBcoinJournal.setDealDate(currentDate);
			fiBcoinJournal.setMoneyType(moneyType[i]);
			fiBcoinJournal.setCoin(moneyArray[i]);
			fiBcoinJournal.setNotes(notes[i]);
			if(notes[i].contains("手工修改积分=====")){
				fiBcoinJournal.setDescription(notes[i].substring(0,notes[i].indexOf("手工修改积分=====")));
				fiBcoinJournal.setNotes(notes[i].substring(notes[i].indexOf("手工修改积分=====")));
			}
			tempMoney = tempMoney.subtract(moneyArray[i]);
			fiBcoinJournal.setBalance(tempMoney);
			fiBcoinJournal.setCreaterCode(operaterCode);
			fiBcoinJournal.setCreaterName(operaterName);
			fiBcoinJournal.setCreateTime(currentTime);
			fiBcoinJournal.setUniqueCode(uniqueCode);
			fiBcoinJournal.setAppId(appId[i]);
			fiBcoinJournal.setDataType(dataType);
			
			this.dao.saveFiBcoinJournal(fiBcoinJournal);
			
			//折合存入另外两张表
		}

		fiBcoinBalance.setBalance(remainMoney.subtract(money));
		fiBcoinBalanceDao.saveFiBcoinBalance(fiBcoinBalance);
	}
}
