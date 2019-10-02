
package com.joymain.jecs.fi.service.impl;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysUserDao;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.fi.model.FiTransferFundbook;
import com.joymain.jecs.fi.dao.FiTransferFundbookDao;
import com.joymain.jecs.fi.service.FiBankbookJournalManager;
import com.joymain.jecs.fi.service.FiFundbookJournalManager;
import com.joymain.jecs.fi.service.FiTransferFundbookManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
public class FiTransferFundbookManagerImpl extends BaseManager implements FiTransferFundbookManager {
    private FiTransferFundbookDao dao;
    private FiFundbookJournalManager fiFundbookJournalManager = null;//产业化基金管理服务
    private FiBankbookJournalManager fiBankbookJournalManager = null;//发展基金管理服务
    private SysUserDao sysUserDao;
    
    
    public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}
    
	public void setFiBankbookJournalManager(FiBankbookJournalManager fiBankbookJournalManager) {
        this.fiBankbookJournalManager = fiBankbookJournalManager;
    }
	
    public void setFiFundbookJournalManager(FiFundbookJournalManager fiFundbookJournalManager) {
        this.fiFundbookJournalManager = fiFundbookJournalManager;
    }
    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiTransferFundbookDao(FiTransferFundbookDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiTransferFundbookManager#getFiTransferFundbooks(com.joymain.jecs.fi.model.FiTransferFundbook)
     */
    public List getFiTransferFundbooks(final FiTransferFundbook fiTransferFundbook) {
        return dao.getFiTransferFundbooks(fiTransferFundbook);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiTransferFundbookManager#getFiTransferFundbook(String taId)
     */
    public FiTransferFundbook getFiTransferFundbook(final String taId) {
        return dao.getFiTransferFundbook(new Long(taId));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiTransferFundbookManager#saveFiTransferFundbook(FiTransferFundbook fiTransferFundbook)
     */
    public void saveFiTransferFundbook(FiTransferFundbook fiTransferFundbook) {
        dao.saveFiTransferFundbook(fiTransferFundbook);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiTransferFundbookManager#removeFiTransferFundbook(String taId)
     */
    public void removeFiTransferFundbook(final String taId) {
        dao.removeFiTransferFundbook(new Long(taId));
    }
    //added for getFiTransferFundbooksByCrm
    public List getFiTransferFundbooksByCrm(CommonRecord crm, Pager pager){
	return dao.getFiTransferFundbooksByCrm(crm,pager);
    }
    
    /**
	 * 审核通过转账单
	 * 审核转账 (其中包括1.转账成功，目标账户存入资金；2.修改转账单状态。在同一个事务里面)
	 */
	public void checkFiTransferFundbooks(List<FiTransferFundbook> fiTransferFundbookList) {

		for (int i = 0; i < fiTransferFundbookList.size(); i++) {

			FiTransferFundbook fiTransferFundbook = fiTransferFundbookList.get(i);
			
			Integer nowStatus = this.dao.getTransferFundbookStatus(fiTransferFundbook.getTaId());
				
			// 判断转账单状态,新单则继续
			if (nowStatus == 1) {
				
				//如果审核人信息未填
				if(StringUtil.isEmpty(fiTransferFundbook.getCheckerCode())){
					
					//set核准人信息
					fiTransferFundbook.setCheckerCode("root");
					fiTransferFundbook.setCheckerName("系统自审");
					fiTransferFundbook.setCheckeTime(new Date());
					fiTransferFundbook.setDealDate(new Date());
				}
				
				//目标用户
				String userCode = fiTransferFundbook.getDestinationUserCode();
				
				//转账金额
				BigDecimal[] moneyArray = new BigDecimal[1];
				moneyArray[0] = fiTransferFundbook.getMoney();
				
				//进行判断，如果是分红基金转入定向基金，则按照1;5的比例计算
				if(("1").equals(fiTransferFundbook.getBankbookType()) && ("2").equals(fiTransferFundbook.getTransferType())){
					
					moneyArray[0] = fiTransferFundbook.getMoney().multiply(new BigDecimal(5));
				}
				
				//资金类型
				Integer[] moneyType = new Integer[1];				
				
				//唯一标示
				String uniqueCode="CJSH"+fiTransferFundbook.getTaId();
				
				//说明
				String[] notes = new String[1];
				
				if(("1").equals(fiTransferFundbook.getBankbookType()) && ("1").equals(fiTransferFundbook.getTransferType())){
					
					moneyType[0] = 2;//分红基金转他人分红基金 
					notes[0] = "会员"+fiTransferFundbook.getTransferUserCode()+"分红基金转入";
				}
				if(("1").equals(fiTransferFundbook.getBankbookType()) && ("2").equals(fiTransferFundbook.getTransferType())){
					
					moneyType[0] = 3;//分红基金转定向基金 
					notes[0] = "分红基金帐户转款"+fiTransferFundbook.getMoney()+"，按照5倍比例转入定向基金帐户";
				}
				if(("2").equals(fiTransferFundbook.getBankbookType()) && ("2").equals(fiTransferFundbook.getTransferType())){
					
					moneyType[0] = 6;//定向基金转入他人定向基金
					notes[0] = "会员"+fiTransferFundbook.getTransferUserCode()+"定向基金帐户转入";
				}
				if(("3").equals(fiTransferFundbook.getTransferType())){
					
					moneyType[0] = 77;//分红基金转发展基金
					notes[0] = "分红基金转入发展基金";
				}
				
    			/**1.审核通过，更新转账单状态*/
				fiTransferFundbook.setStatus(2);//修改转账单状态:已核准
				//更新转账单
				this.dao.saveFiTransferFundbook(fiTransferFundbook);
				
				/**2.审核通过，把自己转入目标帐户*/
				
				//转入定分红金账户、转入定向基金账户
				if(("1").equals(fiTransferFundbook.getTransferType()) || ("2").equals(fiTransferFundbook.getTransferType())){
					
					this.fiFundbookJournalManager.saveFiPayDataVerify("CN", userCode, moneyType, moneyArray, fiTransferFundbook.getCheckerCode(), fiTransferFundbook.getCheckerName(), uniqueCode, notes, fiTransferFundbook.getTransferType(), "1");
				}
				
				//转入发展基金账户
				if(("3").equals(fiTransferFundbook.getTransferType())){
					
					//转账目标用户
					SysUser destinationUser = this.sysUserDao.getSysUser(userCode);
					
					fiBankbookJournalManager.saveFiPayDataVerify("CN", destinationUser, moneyType, moneyArray, fiTransferFundbook.getCheckerCode(), fiTransferFundbook.getCheckerName(), uniqueCode, notes, "1", "1");
				}
			}
		}
	}
	
	/**
	 * 撤销转账单
	 * 业务规则：撤销转账单 (其中包括1.撤销成功，资金退回转账用户；2.修改转账单状态。在同一个事务里面)
	 */
	public void revokeFiTransferFundbooks(List<FiTransferFundbook> fiTransferFundbookList) {
		
		for (int i = 0; i < fiTransferFundbookList.size(); i++) {

			FiTransferFundbook fiTransferFundbook = fiTransferFundbookList.get(i);
			
			Integer nowStatus = this.dao.getTransferFundbookStatus(fiTransferFundbook.getTaId());
				
			// 判断转账单状态,新单则继续
			if (nowStatus == 1) {
				
				//转账退款用户
				String userCode = fiTransferFundbook.getTransferUserCode();
				
				//转账金额
				BigDecimal[] moneyArray = new BigDecimal[1];
				moneyArray[0] = fiTransferFundbook.getMoney();
				
				//资金类型，退款
				Integer[] moneyType = new Integer[1];
 
				//说明
				String[] notes = new String[1];
    			
    			if(("1").equals(fiTransferFundbook.getBankbookType())){
    				
    				notes[0] = "分红基金转账撤销退款";
    				moneyType[0] = 1;//分红基金存入
    			}
    			if(("2").equals(fiTransferFundbook.getBankbookType())){
    				
    				notes[0] = "定向基金转账撤销退款";
    				moneyType[0] = 5;//定向基金存入 
    			}
    			
    			// 修改转账单状态：已撤销
				fiTransferFundbook.setStatus(3);
				//更新转账单
				this.dao.saveFiTransferFundbook(fiTransferFundbook);
				
				String uniqueCode="JZTK"+fiTransferFundbook.getTaId().toString();
				
				//把资金退还给转账方的产业基金帐户
				fiFundbookJournalManager.saveFiPayDataVerify("CN", userCode, moneyType, moneyArray, fiTransferFundbook.getCheckerCode(), fiTransferFundbook.getCheckerName(), uniqueCode, notes, fiTransferFundbook.getBankbookType(), "1");
			}
		}
	}

	/**
	 * 自动审核产业化基金转账单
	 */
	@Override
	public void autoCheckFiTransferFundbooks() {
		
		log.info("定时任务执行产业化基金转账单批量自动审核开始...................");

		//查询待审核转账单
		List<FiTransferFundbook> fiTransferFundbookList = dao.getToCheckFiTransferFundbookList();
		
		try{
			
			//批量审核
			this.checkFiTransferFundbooks(fiTransferFundbookList);
		}catch(Exception e){
			
			log.info("定时任务执行产业化基金转账单批量自动审核出错:"+e.getMessage());
		}
		
		log.info("定时任务执行产业化基金转账单批量自动审核结束...................");
	}
}
