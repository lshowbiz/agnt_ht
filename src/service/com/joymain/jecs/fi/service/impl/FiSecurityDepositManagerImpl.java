
package com.joymain.jecs.fi.service.impl;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.fi.model.FiSecurityDeposit;
import com.joymain.jecs.fi.model.FiSecurityDepositJournal;
import com.joymain.jecs.fi.model.JfiBankbookBalance;
import com.joymain.jecs.fi.dao.FiSecurityDepositDao;
import com.joymain.jecs.fi.service.FiSecurityDepositJournalManager;
import com.joymain.jecs.fi.service.FiSecurityDepositManager;
import com.joymain.jecs.fi.service.JfiBankbookBalanceManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;

public class FiSecurityDepositManagerImpl extends BaseManager implements FiSecurityDepositManager {
    private FiSecurityDepositDao dao;
    private JfiBankbookJournalManager jfiBankbookJournalManager;
    private SysUserManager sysUserManager = null;
    private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;
    private FiSecurityDepositJournalManager fiSecurityDepositJournalManager = null;

    public void setFiSecurityDepositJournalManager(FiSecurityDepositJournalManager fiSecurityDepositJournalManager) {
        this.fiSecurityDepositJournalManager = fiSecurityDepositJournalManager;
    }
    public void setJfiBankbookBalanceManager(JfiBankbookBalanceManager jfiBankbookBalanceManager) {
        this.jfiBankbookBalanceManager = jfiBankbookBalanceManager;
    }
    
    public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public void setJfiBankbookJournalManager(
			JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiSecurityDepositDao(FiSecurityDepositDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiSecurityDepositManager#getFiSecurityDeposits(com.joymain.jecs.fi.model.FiSecurityDeposit)
     */
    public List getFiSecurityDeposits(final FiSecurityDeposit fiSecurityDeposit) {
        return dao.getFiSecurityDeposits(fiSecurityDeposit);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiSecurityDepositManager#getFiSecurityDeposit(String fsdId)
     */
    public FiSecurityDeposit getFiSecurityDeposit(final String fsdId) {
        return dao.getFiSecurityDeposit(new Long(fsdId));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiSecurityDepositManager#saveFiSecurityDeposit(FiSecurityDeposit fiSecurityDeposit)
     */
    public void saveFiSecurityDeposit(FiSecurityDeposit fiSecurityDeposit) {
        dao.saveFiSecurityDeposit(fiSecurityDeposit);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiSecurityDepositManager#removeFiSecurityDeposit(String fsdId)
     */
    public void removeFiSecurityDeposit(final String fsdId) {
        dao.removeFiSecurityDeposit(new Long(fsdId));
    }
    //added for getFiSecurityDepositsByCrm
    public List getFiSecurityDepositsByCrm(CommonRecord crm, Pager pager){
	return dao.getFiSecurityDepositsByCrm(crm,pager);
    }

    /**
     * 补齐所有保证金不足的帐户
     */
	@Override
	public void makeUpAllFiSecurityDeposit(SysUser operationSysUser, String limit) throws AppException{
		// TODO Auto-generated method stub
		
		//查询保证金余额不足的所有帐户
		List list=dao.getFiSecurityDepositsByLimit(limit);
		
		for(int i=0;i<list.size();i++){
			
			//迭代取出每个保证金帐户对象
			FiSecurityDeposit fiSecurityDeposit=(FiSecurityDeposit) list.get(i);
			
			//调用单个补齐的方法
			this.makeUpFiSecurityDeposit(fiSecurityDeposit.getFsdId().toString(), operationSysUser, limit);
		}
	}

	/**
	 * 单个补齐保证金
	 */
	@Override
	public void makeUpFiSecurityDeposit(String fsdId, SysUser operationSysUser, String limit) throws AppException{
		
		//查询
		FiSecurityDeposit fiSecurityDeposit=this.getFiSecurityDeposit(fsdId);
		
		//系统规定的保证金额度
        BigDecimal securityDepositLimit = new BigDecimal(limit);

        //如果保证金帐户额度不够
        if(fiSecurityDeposit.getBalance().compareTo(securityDepositLimit)==-1){
        	
        	//计算扣款
            BigDecimal deductMoney=securityDepositLimit.subtract(fiSecurityDeposit.getBalance());//系统规定的保证金额度-当前保证金帐户余额
            
            //当前保证金账户用户
        	SysUser currentUser=sysUserManager.getSysUser(fiSecurityDeposit.getUserCode());
        	
            //验证电子存折余额是否足够
            JfiBankbookBalance jfiBankbookBalance=jfiBankbookBalanceManager.getJfiBankbookBalance(currentUser.getUserCode());
            
            //如果电子存折余额大于或等于扣款，则继续
            if(jfiBankbookBalance.getBalance().compareTo(deductMoney)==1 || jfiBankbookBalance.getBalance().compareTo(deductMoney)==0){

            	// ****扣款****
            	
            	//公司编码
        		String companyCode = currentUser.getCompanyCode();
        		
        		//资金类别
        		Integer[] moneyType = new Integer[1];
        		moneyType[0] = 100;//100代表保证金扣款
        		
        		//要扣的金额
        		BigDecimal[] moneyArray = new BigDecimal[1];
        		moneyArray[0] = deductMoney;
        		
        		//摘要
        		String[] notes = new String[1];
        		notes[0] = "转款至保证金帐户";
        		
        		//电子存折扣钱
        		jfiBankbookJournalManager.saveJfiBankbookDeduct(companyCode, currentUser, moneyType, moneyArray, operationSysUser.getUserCode(), operationSysUser.getUserName(), "0", notes,"1");
        		
            	//****扣取成功，则更新保证金帐户余额信息****
        		fiSecurityDeposit.setBalance(securityDepositLimit);//更新余额
            	this.saveFiSecurityDeposit(fiSecurityDeposit);
            	
            	//保存保证金进账明细
        		FiSecurityDepositJournal fiSecurityDepositJournal=new FiSecurityDepositJournal();
        		fiSecurityDepositJournal.setUserCode(fiSecurityDeposit.getUserCode());
        		fiSecurityDepositJournal.setDealType("A");
        		fiSecurityDepositJournal.setCreaterCode(operationSysUser.getUserCode());
        		fiSecurityDepositJournal.setCreaterName(operationSysUser.getUserName());
        		fiSecurityDepositJournal.setCreateTime(new Date());
        		fiSecurityDepositJournal.setAmount(deductMoney);
        		fiSecurityDepositJournal.setNotes("补齐保证金!");
        		fiSecurityDepositJournal.setBalance(securityDepositLimit);

        		this.fiSecurityDepositJournalManager.saveFiSecurityDepositJournal(fiSecurityDepositJournal);
            }
        }
	}

	@Override
	public Integer getCountOfSecurityDepositByUserCode(String userCode) {
		// TODO Auto-generated method stub
		return dao.getCountOfSecurityDepositByUserCode(userCode);
	}

	/**
	 * 扣保证金
	 */
	@Override
	public void downFiSecurityDeposit(String fsdId, SysUser operationSysUser, String amount, String remark) throws AppException{
		
		//查询
		FiSecurityDeposit fiSecurityDeposit=this.getFiSecurityDeposit(fsdId);
		
		//更新扣减后的余额
		BigDecimal downAmount = new BigDecimal(amount);
		BigDecimal comMoney = fiSecurityDeposit.getBalance().subtract(downAmount);//当前保证金余额-扣减金额
		
		if(comMoney.compareTo(BigDecimal.ZERO)==-1){
			throw new AppException("保证金余额不足！");
		}
		
		fiSecurityDeposit.setBalance(comMoney);
		
		this.saveFiSecurityDeposit(fiSecurityDeposit);
		
		//保存保证金扣减明细
		FiSecurityDepositJournal fiSecurityDepositJournal=new FiSecurityDepositJournal();
		fiSecurityDepositJournal.setUserCode(fiSecurityDeposit.getUserCode());
		fiSecurityDepositJournal.setDealType("D");
		fiSecurityDepositJournal.setCreaterCode(operationSysUser.getUserCode());
		fiSecurityDepositJournal.setCreaterName(operationSysUser.getUserName());
		fiSecurityDepositJournal.setCreateTime(new Date());
		fiSecurityDepositJournal.setAmount(downAmount);
		fiSecurityDepositJournal.setBalance(comMoney);
		fiSecurityDepositJournal.setNotes(remark);
		this.fiSecurityDepositJournalManager.saveFiSecurityDepositJournal(fiSecurityDepositJournal);
	}
}
