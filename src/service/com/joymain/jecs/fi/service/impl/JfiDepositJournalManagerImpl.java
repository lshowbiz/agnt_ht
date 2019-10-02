
package com.joymain.jecs.fi.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.fi.dao.JfiDepositBalanceDao;
import com.joymain.jecs.fi.dao.JfiDepositJournalDao;
import com.joymain.jecs.fi.model.JfiDepositJournal;
import com.joymain.jecs.fi.model.JfiDepositBalance;
import com.joymain.jecs.fi.model.JfiDepositJournal;
import com.joymain.jecs.fi.service.JfiDepositJournalManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;

public class JfiDepositJournalManagerImpl extends BaseManager implements JfiDepositJournalManager {
    private JfiDepositJournalDao dao;
    private JfiDepositBalanceDao jfiDepositBalanceDao;
    public void setJfiDepositBalanceDao(JfiDepositBalanceDao jfiDepositBalanceDao) {
		this.jfiDepositBalanceDao = jfiDepositBalanceDao;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiDepositJournalDao(JfiDepositJournalDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiDepositJournalManager#getJfiDepositJournals(com.joymain.jecs.fi.model.JfiDepositJournal)
     */
    public List getJfiDepositJournals(final JfiDepositJournal jfiDepositJournal) {
        return dao.getJfiDepositJournals(jfiDepositJournal);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiDepositJournalManager#getJfiDepositJournal(String journalId)
     */
    public JfiDepositJournal getJfiDepositJournal(final String journalId) {
        return dao.getJfiDepositJournal(new Long(journalId));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiDepositJournalManager#saveJfiDepositJournal(JfiDepositJournal jfiDepositJournal)
     */
    public void saveJfiDepositJournal(JfiDepositJournal jfiDepositJournal) {
        dao.saveJfiDepositJournal(jfiDepositJournal);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiDepositJournalManager#removeJfiDepositJournal(String journalId)
     */
    public void removeJfiDepositJournal(final String journalId) {
        dao.removeJfiDepositJournal(new Long(journalId));
    }
    //added for getJfiDepositJournalsByCrm
    public List getJfiDepositJournalsByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiDepositJournalsByCrm(crm,pager);
    }
    
    

  	public void saveFiPayDataVerify(final String companyCode, final String userCode, final Integer[] moneyType, final BigDecimal[] moneyArray,
  	        String operaterCode, final String uniqueCode, final String[] notes, final String depositType){
  		
  		//判断用户帐户是否存在
  		JfiDepositBalance JfiDepositBalanceTmp = this.jfiDepositBalanceDao.getJfiDepositBalanceByUserCodeAndDepositType(userCode, depositType);
  		
  	    //modify fu 2015-12-21----begin
  		if (JfiDepositBalanceTmp == null) {
  			throw new AppException(userCode+":帐户不存在!");
  		}
  	   //modify fu 2015-12-21----end
  		
  		JfiDepositBalance JfiDepositBalance = this.jfiDepositBalanceDao.getJfiDepositBalanceForUpdate(JfiDepositBalanceTmp.getFdbId());
  		if (JfiDepositBalance == null) {
  			
  			throw new AppException(userCode+":帐户不存在!");
  		}
  		//判断是否重复审单
  		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
  			JfiDepositJournal lastJfiDepositJournal = dao.getLastJfiDepositJournalByUnique(uniqueCode,"A");
  			if (lastJfiDepositJournal != null && "A".equals(lastJfiDepositJournal.getDealType())) {
  				throw new AppException(userCode+":重复操作");
  			}
  		}
  		
  		BigDecimal money = new BigDecimal(0);
  		for(int i = 0 ; i < moneyArray.length ; i++){
  			if(moneyArray[i].compareTo(new BigDecimal("0"))==-1){
  				throw new AppException(userCode+":金额参数必须大于零");
  			}
  			money = money.add(moneyArray[i]);
  		}

  		BigDecimal remainMoney;
  		BigDecimal tempMoney;
  		JfiDepositJournal lastJfiDepositJournal = this.dao.getLastJfiDepositJournal(userCode,depositType);
  		if (lastJfiDepositJournal == null) {
  			remainMoney = new BigDecimal(0);
  			tempMoney =  new BigDecimal(0);
  		} else {
  			remainMoney = lastJfiDepositJournal.getBalance();
  			tempMoney = lastJfiDepositJournal.getBalance();
  		}

  		Date currentTime = new Date();
  		for(int i = 0 ; i < moneyArray.length ; i++){

  			//插入产业基金流水记录
  			JfiDepositJournal JfiDepositJournal = new JfiDepositJournal();

  			JfiDepositJournal.setCompanyCode(companyCode);
  			JfiDepositJournal.setUserCode(userCode);
  
  			JfiDepositJournal.setDealType("A");
  			JfiDepositJournal.setMoneyType(moneyType[i]);
  			JfiDepositJournal.setMoney(moneyArray[i]);
  			JfiDepositJournal.setNotes(notes[i]);
  			tempMoney = tempMoney.add(moneyArray[i]);
  			JfiDepositJournal.setBalance(tempMoney);
  			JfiDepositJournal.setCreaterNo(operaterCode);
  			JfiDepositJournal.setCreateTime(currentTime);
  			JfiDepositJournal.setUniqueCode(uniqueCode);
  			JfiDepositJournal.setDepositType(depositType);

  			dao.saveJfiDepositJournal(JfiDepositJournal);
  		}

  		JfiDepositBalance.setBalance(remainMoney.add(money));
  		jfiDepositBalanceDao.saveJfiDepositBalance(JfiDepositBalance);
  	}


  	public void saveFiBankbookDeduct(final String companyCode, final String userCode, final Integer[] moneyType, final BigDecimal[] moneyArray,
  	        String operaterCode, final String uniqueCode,  final String[] notes, final String depositType){
  		
  		//判断用户帐户是否存在
  		JfiDepositBalance JfiDepositBalanceTmp = this.jfiDepositBalanceDao.getJfiDepositBalanceByUserCodeAndDepositType(userCode, depositType);
  		
  		//modify fu 2015-12-21----begin
  		if (JfiDepositBalanceTmp == null) {
  			throw new AppException(userCode+":帐户不存在!");
  		}
  	   //modify fu 2015-12-21----end
  		JfiDepositBalance JfiDepositBalance = this.jfiDepositBalanceDao.getJfiDepositBalanceForUpdate(JfiDepositBalanceTmp.getFdbId());
  		if (JfiDepositBalance == null) {
  			throw new AppException(userCode+":帐户不存在!");
  		}
  		//判断是否重复审单
  		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
  			JfiDepositJournal lastJfiDepositJournal = dao.getLastJfiDepositJournalByUnique(uniqueCode,"D");
  			if (lastJfiDepositJournal != null && "D".equals(lastJfiDepositJournal.getDealType())) {
  				throw new AppException(userCode+":重复操作");
  			}
  		}
  		
  		BigDecimal money = new BigDecimal(0);
  		for(int i = 0 ; i < moneyArray.length ; i++){
  			if(moneyArray[i].compareTo(new BigDecimal("0"))==-1){
  				throw new AppException(userCode+":金额参数必须大于零");
  			}
  			money = money.add(moneyArray[i]);
  		}

  		Date currentTime = new Date();

  		//首先验证余额是否足够
  		JfiDepositJournal lastJfiDepositJournal = this.dao.getLastJfiDepositJournal(userCode,depositType);
  		if (lastJfiDepositJournal == null || lastJfiDepositJournal.getBalance().compareTo(money) == -1) {
  			throw new AppException(userCode+":帐户余额不足！");
  		}

  		BigDecimal remainMoney;
  		BigDecimal tempMoney;
  		if (lastJfiDepositJournal == null) {
  			remainMoney = new BigDecimal(0);
  			tempMoney =  new BigDecimal(0);
  		} else {
  			remainMoney = lastJfiDepositJournal.getBalance();
  			tempMoney = lastJfiDepositJournal.getBalance();
  		}
  		if (remainMoney.compareTo(money) == -1) {
  			throw new AppException(userCode+":帐户余额不足！");
  		}
  		
  		for(int i = 0 ; i < moneyArray.length ; i++){


  			//插入存折流水记录
  			JfiDepositJournal JfiDepositJournal = new JfiDepositJournal();
  			JfiDepositJournal.setCompanyCode(companyCode);
  			JfiDepositJournal.setUserCode(userCode);
  			JfiDepositJournal.setDealType("D");
  			JfiDepositJournal.setMoneyType(moneyType[i]);
  			JfiDepositJournal.setMoney(moneyArray[i]);
  			JfiDepositJournal.setNotes(notes[i]);
  			tempMoney = tempMoney.subtract(moneyArray[i]);
  			JfiDepositJournal.setBalance(tempMoney);
  			JfiDepositJournal.setCreaterNo(operaterCode);
  			JfiDepositJournal.setCreateTime(currentTime);
  			JfiDepositJournal.setUniqueCode(uniqueCode);
  			JfiDepositJournal.setDepositType(depositType);
  			
  			this.dao.saveJfiDepositJournal(JfiDepositJournal);
  		}

  		JfiDepositBalance.setBalance(remainMoney.subtract(money));
  		jfiDepositBalanceDao.saveJfiDepositBalance(JfiDepositBalance);
  	}

	@Override
	public Map getSumAmountByCrm(CommonRecord crm) {
		
		return dao.getSumAmountByCrm(crm);
	}
    
    
    
}
