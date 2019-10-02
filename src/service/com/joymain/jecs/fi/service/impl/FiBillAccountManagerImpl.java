
package com.joymain.jecs.fi.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.joymain.jecs.fi.dao.FiBillAccountDao;
import com.joymain.jecs.fi.dao.FiCommonAddrDao;
import com.joymain.jecs.fi.model.FiBillAccount;
import com.joymain.jecs.fi.model.FiBillAccountWarning;
import com.joymain.jecs.fi.model.FiCommonAddr;
import com.joymain.jecs.fi.service.FiBillAccountManager;
import com.joymain.jecs.fi.service.FiBillAccountWarningManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.bill99.Bill99Constants;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
@SuppressWarnings("rawtypes")
public class FiBillAccountManagerImpl extends BaseManager implements FiBillAccountManager {
    private FiBillAccountDao dao;
    private FiCommonAddrDao fiCommonAddrDao;
    private FiBillAccountWarningManager fiBillAccountWarningManager = null;//预警

    public void setFiBillAccountWarningManager(FiBillAccountWarningManager fiBillAccountWarningManager) {
        this.fiBillAccountWarningManager = fiBillAccountWarningManager;
    }
    
    public void setFiCommonAddrDao(FiCommonAddrDao fiCommonAddrDao) {
		this.fiCommonAddrDao = fiCommonAddrDao;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiBillAccountDao(FiBillAccountDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBillAccountManager#getFiBillAccounts(com.joymain.jecs.fi.model.FiBillAccount)
     */
    public List getFiBillAccounts(final FiBillAccount fiBillAccount) {
        return dao.getFiBillAccounts(fiBillAccount);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBillAccountManager#getFiBillAccount(String accountId)
     */
    public FiBillAccount getFiBillAccount(final String accountId) {
        return dao.getFiBillAccount(new Long(accountId));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBillAccountManager#saveFiBillAccount(FiBillAccount fiBillAccount)
     */
    public void saveFiBillAccount(FiBillAccount fiBillAccount) {
    	
    	boolean isNew = (fiBillAccount.getAccountId() == null);
    	
        dao.saveFiBillAccount(fiBillAccount);
                
        //添加预警表记录
        if(isNew){
        	
            //预警表添加初始化记录
			FiBillAccountWarning fiBillAccountWarning = new FiBillAccountWarning();
			fiBillAccountWarning.setBillAccountCode(fiBillAccount.getBillAccountCode());
            fiBillAccountWarning.setNowTotalAmount(new BigDecimal(0));
            fiBillAccountWarningManager.saveFiBillAccountWarning(fiBillAccountWarning);
        }
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBillAccountManager#removeFiBillAccount(String accountId)
     */
    public void removeFiBillAccount(final String accountId) {
    	
    	//删除商户
        dao.removeFiBillAccount(new Long(accountId));
    }
    //added for getFiBillAccountsByCrm
	public List getFiBillAccountsByCrm(CommonRecord crm, Pager pager) {
		return dao.getFiBillAccountsByCrm(crm, pager);
	}

	public List getFiBillAccountsByCrmSql(CommonRecord crm, Pager pager) {
		return dao.getFiBillAccountsByCrmSql(crm, pager);
	}
    /**
     * 验证同省份下是否有其他默认商户号
     * @param billAccountCode
     * @param province
     * @return
     */
	@Override
	public List getFiBillAccountsByIsDefault(String billAccountCode) {
		// TODO Auto-generated method stub
		return dao.getFiBillAccountsByIsDefault(billAccountCode);
	}

	/**
	 * 验证经销商编号是否重复
	 * @param billAccountCode
	 * @param userCode
	 * @return
	 */
	@Override
	public List getFiBillAccountsByUserCode(String billAccountCode,
			String userCode) {
		// TODO Auto-generated method stub
		return dao.getFiBillAccountsByUserCode(billAccountCode, userCode);
	}

	/**
	 * 验证商户号是否重复
	 * @param billAccountCode
	 * @return
	 */
	@Override
	public List getFiBillAccountsByBillAccountCode(String billAccountCode) {
		// TODO Auto-generated method stub
		return dao.getFiBillAccountsByBillAccountCode(billAccountCode);
	}
	
	/**
	 * 根据会议编号获取商户号
	 * @param userCode 会员编号
	 * @return 快钱商户对象
	 */
	@Override
	public FiBillAccount getFiBillAccountByUserCode(String userCode) {
		
		//快钱商户对象
		FiBillAccount fiBillAccount=null;
		
		//获取会员常用收货地址
		FiCommonAddr fiCommonAddr = fiCommonAddrDao.getFiCommonAddr(userCode);
		
		//如果常用收货地址为空
		if(fiCommonAddr==null || ("").equals(fiCommonAddr.getProvince())){

			return this.getFiBillAccountByOld(null);
		}
		
		if(("163712").equals(fiCommonAddr.getProvince())){
			
			
			//查询该省份下面差额最大的商户号，同时差额必须大于0
			fiBillAccount = dao.getFiBillAccountsByProvince(fiCommonAddr.getProvince());
			
			//如果找不到匹配的商户号
			if(fiBillAccount==null){
				
				//查询该省份下面的默认商户号
				fiBillAccount = dao.getDefaultFiBillAccountByProvince(fiCommonAddr.getProvince());
			}
			
			//如果找不到匹配的商户号
			if(fiBillAccount==null){
				
				return this.getFiBillAccountByOld(fiCommonAddr.getProvince());
			}
		}else{
			
			return this.getFiBillAccountByOld(fiCommonAddr.getProvince());
		}
	
		
		return fiBillAccount;
	}
	
	public FiBillAccount getFiBillAccountByOld(String province){
		
		FiBillAccount fiBillAccount=null;
		String accId ="";
		if(province==null){
			
			accId = Bill99Constants.account.get("null").get("memberCode");//按照旧规则
		}else{
			
			accId = Bill99Constants.account.get(province).get("memberCode");//按照旧规则
		}
		fiBillAccount = new FiBillAccount();
		fiBillAccount.setBillAccountCode(accId);
		fiBillAccount.setAccountName("旧规则");
		return fiBillAccount;
	}

	/**
	 * 批量保存
	 */
	@Override
	public void saveFiBillAccounts(List<FiBillAccount> fiBillAccounts) {
		// TODO Auto-generated method stub
		
		for(int i=0;i<fiBillAccounts.size();i++){
			
			FiBillAccount fiBillAccount = fiBillAccounts.get(i);
			
			dao.saveFiBillAccount(fiBillAccount);
		}
	}
}
