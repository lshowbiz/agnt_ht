package com.joymain.jecs.fi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysUserDao;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.fi.model.FiTransferAccount;
import com.joymain.jecs.fi.model.FiTransferFundbook;
import com.joymain.jecs.fi.dao.FiTransferAccountDao;
import com.joymain.jecs.fi.service.FiTransferAccountManager;
import com.joymain.jecs.fi.service.FiTransferFundbookManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;

public class FiTransferAccountManagerImpl extends BaseManager implements
		FiTransferAccountManager {
	
	private FiTransferAccountDao dao;
	private SysUserDao sysUserDao;
	
	//存折服务管理对象
	private JfiBankbookJournalManager jfiBankbookJournalManager;
	
	//产业化基金转账管理对象
	private FiTransferFundbookManager fiTransferFundbookManager;
	
	public void setJfiBankbookJournalManager(
			JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}

	public void setFiTransferFundbookManager(
			FiTransferFundbookManager fiTransferFundbookManager) {
		this.fiTransferFundbookManager = fiTransferFundbookManager;
	}

	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}

	public void setDao(FiTransferAccountDao dao) {
		this.dao = dao;
	}

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setFiTransferAccountDao(FiTransferAccountDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.fi.service.FiTransferAccountManager#getFiTransferAccounts(com.joymain.jecs.fi.model.FiTransferAccount)
	 */
	public List getFiTransferAccounts(final FiTransferAccount fiTransferAccount) {
		return dao.getFiTransferAccounts(fiTransferAccount);
	}

	/**
	 * @see com.joymain.jecs.fi.service.FiTransferAccountManager#getFiTransferAccount(String
	 *      taId)
	 */
	public FiTransferAccount getFiTransferAccount(final String taId) {
		return dao.getFiTransferAccount(new Long(taId));
	}

	/**
	 * @see com.joymain.jecs.fi.service.FiTransferAccountManager#saveFiTransferAccount(FiTransferAccount
	 *      fiTransferAccount)
	 */
	public void saveFiTransferAccount(FiTransferAccount fiTransferAccount) {
		dao.saveFiTransferAccount(fiTransferAccount);
	}

	/**
	 * @see com.joymain.jecs.fi.service.FiTransferAccountManager#removeFiTransferAccount(String
	 *      taId)
	 */
	public void removeFiTransferAccount(final String taId) {
		dao.removeFiTransferAccount(new Long(taId));
	}

	// added for getFiTransferAccountsByCrm
	public List getFiTransferAccountsByCrm(CommonRecord crm, Pager pager) {
		return dao.getFiTransferAccountsByCrm(crm, pager);
	}

	/**
	 * 创建转账单
	 * 业务规则：1.从转账用户账户里面扣钱，成功则 ：2.创建转账单
	 */
	public void addTransferAccounts(FiTransferAccount fiTransferAccount, SysUser transferUser){
		// TODO Auto-generated method stub
		
		//1.转账用户扣钱
		
		//转账金额
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = fiTransferAccount.getMoney();
		//资金类型
		Integer[] moneyType = new Integer[1];
		moneyType[0] = 9;//转账 
		//说明
		String[] notes = new String[1];
		notes[0] = "转账支出";

		//从用户帐户扣取金额
		jfiBankbookJournalManager.saveJfiBankbookDeduct("CN",transferUser,moneyType,moneyArray,fiTransferAccount.getCreaterCode(),fiTransferAccount.getCreaterName(),"0",notes,"1");

		//转账单状态 ： 新单
		fiTransferAccount.setStatus(1);
		//生成转账单
		this.dao.saveFiTransferAccount(fiTransferAccount);
	}

	/**
	 * 审核通过转账单
	 * 审核转账 (其中包括1.转账成功，目标账户存入资金；2.修改转账单状态。在同一个事务里面)
	 */
	public void checkTransferAccounts(List<FiTransferAccount> fiTransferAccountList) {

		for (int i = 0; i < fiTransferAccountList.size(); i++) {

			FiTransferAccount fiTransferAccount = fiTransferAccountList.get(i);
			
			Integer nowStatus = this.dao.getTransferAccountStatus(fiTransferAccount.getTaId());
				
			// 判断转账单状态,新单则继续
			if (nowStatus == 1) {
				
				//如果审核人信息未填
				if(StringUtil.isEmpty(fiTransferAccount.getCheckerCode())){
					
					//set核准人信息
					fiTransferAccount.setCheckerCode("root");
					fiTransferAccount.setCheckerName("系统自审");
					fiTransferAccount.setCheckeTime(new Date());
					fiTransferAccount.setDealDate(new Date());
				}

				//转账目标用户
				SysUser destinationUser = this.sysUserDao.getSysUser(fiTransferAccount.getDestinationUserCode());
				//转账金额
				BigDecimal[] moneyArray = new BigDecimal[1];
				moneyArray[0] = fiTransferAccount.getMoney();
				//资金类型
				Integer[] moneyType = new Integer[1];
				moneyType[0] = 9;//转账
				//说明
				String[] notes = new String[1];
    			notes[0] = fiTransferAccount.getTransferUserCode()+"转账";
    			
    			// 修改转账单状态:已核准
				fiTransferAccount.setStatus(2);
				//更新转账单
				this.dao.saveFiTransferAccount(fiTransferAccount);
				
				String uniqueCode="ZZ"+fiTransferAccount.getTaId().toString();
				
    			//存入资金至转账目标用户帐户
    			jfiBankbookJournalManager.saveJfiPayDataVerify("CN", destinationUser, moneyType, moneyArray, fiTransferAccount.getCheckerCode(), fiTransferAccount.getCheckerName(), uniqueCode, notes,"1");
			}
		}
	}
	
	/**
	 * 撤销转账单
	 * 业务规则：撤销转账单 (其中包括1.撤销成功，资金退回转账用户；2.修改转账单状态。在同一个事务里面)
	 */
	public void revokeTransferAccounts(List<FiTransferAccount> fiTransferAccountList) {
		
		for (int i = 0; i < fiTransferAccountList.size(); i++) {

			FiTransferAccount fiTransferAccount = fiTransferAccountList.get(i);
			
			Integer nowStatus = this.dao.getTransferAccountStatus(fiTransferAccount.getTaId());
				
			// 判断转账单状态,新单则继续
			if (nowStatus == 1) {
				
				//转账用户
				SysUser transferUser = this.sysUserDao.getSysUser(fiTransferAccount.getTransferUserCode());
				//转账金额
				BigDecimal[] moneyArray = new BigDecimal[1];
				moneyArray[0] = fiTransferAccount.getMoney();
				//资金类型，退款
				Integer[] moneyType = new Integer[1];
				moneyType[0] = 70;//电子存折转账撤销退款
				//说明
				String[] notes = new String[1];
    			notes[0] = "转账撤销退款";
    			
    			// 修改转账单状态：已撤销
				fiTransferAccount.setStatus(3);
				//更新转账单
				this.dao.saveFiTransferAccount(fiTransferAccount);
				
				String uniqueCode="ZZTK"+fiTransferAccount.getTaId().toString();
				
				//把资金退还给转账方
    			//存入资金至用户帐户
    			jfiBankbookJournalManager.saveJfiPayDataVerify("CN", transferUser, moneyType, moneyArray, fiTransferAccount.getCheckerCode(), fiTransferAccount.getCheckerName(), uniqueCode, notes,"1");
			}
		}
	}


	//获取单日转账总额
	public BigDecimal getSumTransferValueByTransferCode(String transferCode) {
		// TODO Auto-generated method stub
		return this.dao.getSumTransferValueByTransferCode(transferCode);
	}

	/**
	 * 电子存折转账自动审单
	 */
	public void autoCheckTransferAccounts(){
		
		log.info("定时任务执行电子存折转账单批量自动审核开始...................");
		
		//查询待审的转账单
		List<FiTransferAccount> toCheckfiTransferAccountList = dao.getToCheckTransferAccountList();
		
		try{
			
			//审核通过转账单
			this.checkTransferAccounts(toCheckfiTransferAccountList);
		}catch(Exception e){
			
			log.info("定时任务执行电子存折转账单批量自动审核出错:"+e.getMessage());
		}
		
		
		log.info("定时任务执行电子存折转账单批量自动审核结束...................");
	}
	
	/**
	 * 定时任务，自动审单,每天晚上23点50开始执行
	 */
	public void checkTransferTask(){
		
		//电子存折转账自动审单
//		this.autoCheckTransferAccounts();

		//产业化基金转账自动审单
		fiTransferFundbookManager.autoCheckFiTransferFundbooks();
	}
}
