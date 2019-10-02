
package com.joymain.jecs.fi.service.impl;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.FiAvailableInvoice;
import com.joymain.jecs.fi.model.FiInvoiceBalance;
import com.joymain.jecs.fi.dao.FiAvailableInvoiceDao;
import com.joymain.jecs.fi.service.FiAvailableInvoiceManager;
import com.joymain.jecs.fi.service.FiInvoiceBalanceManager;
import com.joymain.jecs.fi.service.FiInvoiceChangeManager;
import com.joymain.jecs.fi.service.JfiDepositJournalManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class FiAvailableInvoiceManagerImpl extends BaseManager implements FiAvailableInvoiceManager {
    private FiAvailableInvoiceDao dao;
    private FiInvoiceChangeManager fiInvoiceChangeManager;
    private FiInvoiceBalanceManager fiInvoiceBalanceManager;
    private JfiDepositJournalManager jfiDepositJournalManager;
    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiAvailableInvoiceDao(FiAvailableInvoiceDao dao) {
        this.dao = dao;
    }
    
    public void setFiInvoiceChangeManager(FiInvoiceChangeManager fiInvoiceChangeManager) {
		this.fiInvoiceChangeManager = fiInvoiceChangeManager;
	}

    public void setFiInvoiceBalanceManager(FiInvoiceBalanceManager fiInvoiceBalanceManager) {
		this.fiInvoiceBalanceManager = fiInvoiceBalanceManager;
	}

	public void setJfiDepositJournalManager(JfiDepositJournalManager jfiDepositJournalManager) {
		this.jfiDepositJournalManager = jfiDepositJournalManager;
	}

	/**
     * @see com.joymain.jecs.fi.service.FiAvailableInvoiceManager#getFiAvailableInvoices(com.joymain.jecs.fi.model.FiAvailableInvoice)
     */
    public List getFiAvailableInvoices(final FiAvailableInvoice fiAvailableInvoice) {
        return dao.getFiAvailableInvoices(fiAvailableInvoice);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiAvailableInvoiceManager#getFiAvailableInvoice(String id)
     */
    public FiAvailableInvoice getFiAvailableInvoice(final String id) {
        return dao.getFiAvailableInvoice(new String(id));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiAvailableInvoiceManager#saveFiAvailableInvoice(FiAvailableInvoice fiAvailableInvoice)
     */
    public void saveFiAvailableInvoice(FiAvailableInvoice fiAvailableInvoice) {
        dao.saveFiAvailableInvoice(fiAvailableInvoice);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiAvailableInvoiceManager#removeFiAvailableInvoice(String id)
     */
    public void removeFiAvailableInvoice(final String id) {
        dao.removeFiAvailableInvoice(new String(id));
    }
    //added for getFiAvailableInvoicesByCrm
    public List getFiAvailableInvoicesByCrm(CommonRecord crm, Pager pager){	
	   return dao.getFiAvailableInvoicesByCrm(crm,pager);
    }

	/**
	 * 合规可用发票审核
	 * @author 2015-11-30 fu
	 * @param fiAvailableInvoice
	 * @param userCodeLogin
	 */
    public void checkFiAvailableInvoice(FiAvailableInvoice fiAvailableInvoice,String userCodeLogin){
    	  dao.saveFiAvailableInvoice(fiAvailableInvoice);
    	 
		  //审核成功后，向可用发票余额表插入数据
		  FiInvoiceBalance fiInvoiceBalance = fiInvoiceBalanceManager.getFiInvoiceBalanceByUserCode(fiAvailableInvoice.getUserCode());
		  
		  //审核成功后，向应退保证金表传入数据---moidfy fu 2015-12-2-----begin
		  String companyCode = "CN";
		  String userCode = fiAvailableInvoice.getUserCode();
		  Integer[] moneyType = { 10 };//金额类型
		  BigDecimal[] moneyArray = { fiAvailableInvoice.getBond() };//应退保证金  
		  String operaterCode = userCodeLogin;//建立者编号
		  String uniqueCode = "fiAvailableInvoiceTemporarily"+fiAvailableInvoice.getId();//唯一码
		  String[] notes = { "可用发票对应的暂扣保证金:"+fiAvailableInvoice.getId().toString() };//摘要
		  String depositType = "1";// 1.暂扣保证金结余 2.应退保证金结余
		  jfiDepositJournalManager.saveFiBankbookDeduct(companyCode, userCode, moneyType, moneyArray,operaterCode, uniqueCode,notes,depositType);
		  //审核成功后，向应退保证金表传入数据---moidfy fu 2015-12-2-----end
		  
		 /* saveFiPayDataVerify(final String companyCode, final String userCode, final Integer[] moneyType, final BigDecimal[] moneyArray,
		  	        String operaterCode, final String uniqueCode, final String[] notes, final String depositType);*/
		  
		  //modify 2015-12-18 fu -------begin
		  String depositTypeTwo = "2";//depositType 2.应退保证金结余
		  String uniqueCodeTwo = "fiAvailableInvoiceRefundable"+fiAvailableInvoice.getId();//唯一码
		  String[] notesTwo = { "可用发票对应的应退保证金:"+fiAvailableInvoice.getId().toString() };//摘要
		  jfiDepositJournalManager.saveFiPayDataVerify(companyCode,userCode,moneyType,moneyArray,operaterCode, uniqueCodeTwo,notesTwo,depositTypeTwo);
		  //modify 2015-12-18 fu -------end

		  if(null == fiInvoiceBalance){
        	  FiInvoiceBalance fiInvoiceBalanceO = new  FiInvoiceBalance();
        	  fiInvoiceBalanceO.setUserCode(fiAvailableInvoice.getUserCode());
        	  fiInvoiceBalanceO.setBalance(new BigDecimal("-"+fiAvailableInvoice.getInvoiceValue().toString()));
        	  fiInvoiceBalanceO.setCreateUserCode(userCodeLogin);
        	  fiInvoiceBalanceO.setCreateTime(new Date());
        	  fiInvoiceBalanceO.setRemark("");
        	  fiInvoiceBalanceManager.saveFiInvoiceBalance(fiInvoiceBalanceO);
        	  //审核成功后，向可用发票流水表插入数据
    		  fiInvoiceChangeManager.scSavefiInvoiceChange(fiAvailableInvoice,userCodeLogin,fiInvoiceBalanceO.getBalance());
          }else{
        	  BigDecimal balanceOld = fiInvoiceBalance.getBalance();
        	  BigDecimal balanceChange = fiAvailableInvoice.getInvoiceValue();
        	  BigDecimal balanceNew = balanceOld.subtract(balanceChange);
        	  fiInvoiceBalance.setBalance(balanceNew);
        	  fiInvoiceBalanceManager.saveFiInvoiceBalance(fiInvoiceBalance);
    		  fiInvoiceChangeManager.scSavefiInvoiceChange(fiAvailableInvoice,userCodeLogin,fiInvoiceBalance.getBalance());
          }
          
        
    
    }
}
