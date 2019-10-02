
package com.joymain.jecs.fi.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.fi.dao.FiProductPointBalanceDao;
import com.joymain.jecs.fi.dao.FiProductPointJournalDao;
import com.joymain.jecs.fi.dao.FiProductPointTempDao;
import com.joymain.jecs.fi.model.FiProductPointBalance;
import com.joymain.jecs.fi.model.FiProductPointJournal;
import com.joymain.jecs.fi.model.FiProductPointTemp;
import com.joymain.jecs.fi.model.JfiPosImport;
import com.joymain.jecs.fi.service.FiProductPointJournalManager;
import com.joymain.jecs.fi.service.JfiPosImportManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;

public class FiProductPointJournalManagerImpl extends BaseManager implements FiProductPointJournalManager {
    private FiProductPointJournalDao dao;
	private FiProductPointTempDao fiProductPointTempDao;
	private FiProductPointBalanceDao fiProductPointBalanceDao;
    private JfiPosImportManager jfiPosImportManager = null;

    public void setJfiPosImportManager(JfiPosImportManager jfiPosImportManager) {
        this.jfiPosImportManager = jfiPosImportManager;
    }

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiProductPointJournalDao(FiProductPointJournalDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.sp.spms.fi.service.FiProductPointJournalManager#getFiProductPointJournals(com.sp.spms.fi.model.FiProductPointJournal)
     */
    public List getFiProductPointJournals(final FiProductPointJournal fiProductPointJournal) {
        return dao.getFiProductPointJournals(fiProductPointJournal);
    }

    /**
     * @see com.sp.spms.fi.service.FiProductPointJournalManager#getFiProductPointJournal(String journalId)
     */
    public FiProductPointJournal getFiProductPointJournal(final String journalId) {
        return dao.getFiProductPointJournal(new Long(journalId));
    }

    /**
     * @see com.sp.spms.fi.service.FiProductPointJournalManager#saveFiProductPointJournal(FiProductPointJournal fiProductPointJournal)
     */
    public void saveFiProductPointJournal(FiProductPointJournal fiProductPointJournal) {
        dao.saveFiProductPointJournal(fiProductPointJournal);
    }

    /**
     * @see com.sp.spms.fi.service.FiProductPointJournalManager#removeFiProductPointJournal(String journalId)
     */
    public void removeFiProductPointJournal(final String journalId) {
        dao.removeFiProductPointJournal(new Long(journalId));
    }
    //added for getFiProductPointJournalsByCrm
    public List getFiProductPointJournalsByCrm(CommonRecord crm, Pager pager){
	return dao.getFiProductPointJournalsByCrm(crm,pager);
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
     * 会员对会员转账
     * @param companyCode
     * @param ucMember
     * @param moneyType
     * @param money
     * @param operaterCode
     * @param operaterName
     * @param uniqueCode
     * @param notes
     * @param ReceiveUcMember
     */
    public void saveTransferMemberToMember(final String companyCode, final SysUser sysUser, final Integer moneyType, final BigDecimal money,
	        String operaterCode, final String operaterName, final String uniqueCode, final String note, final SysUser receiveSysUser){
    	if(sysUser.getUserCode().equals(receiveSysUser.getUserCode())){
    		throw new AppException("不能自己转账给自己。");
    	}
    	if(!sysUser.getCompanyCode().equals(companyCode) || !sysUser.getCompanyCode().equals(receiveSysUser.getCompanyCode())){
    		throw new AppException("国别不同。");
    	}
    	BigDecimal[] moneyArray = {money};
    	String[] notes = {note};
    	Integer[] moneyTypes = {moneyType};
    	this.saveFiProductPointDeduct(companyCode, sysUser, moneyTypes, moneyArray, operaterCode, operaterName, uniqueCode, notes, "1","1"); 	
    	this.saveFiPayDataVerify(companyCode, receiveSysUser, moneyTypes, moneyArray, operaterCode, operaterName, uniqueCode, notes, "1","1");
    }
    
	/**
	 * 验证存折条目
	 * @param tempId
	 * @param sysUser
	 * @return
	 */
	public void saveFiProductPointTempCheck(final String tempId, final SysUser sysUser) throws AppException{
		try {
			Map resultMap = dao.callProcCheckFiProductPointTemp(new Long(tempId), sysUser);
			if ("1".equals(resultMap.get("Vi_Errno").toString())) {
				//余额不足
				throw new AppException("此用户产品积分不存在或余额不足！");
			} else if ("2".equals(resultMap.get("Vi_Errno").toString())) {
				throw new AppException("fiProductPointTemp.verified");
			} else if ("3".equals(resultMap.get("Vi_Errno").toString())) {
				throw new AppException("未知错误");
			} else if ("4".equals(resultMap.get("Vi_Errno").toString())) {
				throw new AppException("此产品积分条目不存在");
			}
		} catch (Exception ex) {
			throw new AppException(ex);
		}
	}

	/**
	 * 获取收支统计金额 incTotal:收入 expTotal:支出
	 * @param crm
	 * @return
	 */
	public Map getIncExpStatMap(CommonRecord crm) {
		return dao.getIncExpStatMap(crm);
	}

	/**
	 * 获取公司截止至某一日期时(包含此日期)的用户电子存折余额总计
	 * @param companyCode
	 * @param queryDate yyyy-MM-dd
	 * @return
	 */
	public BigDecimal getTotalBalanceByDate(final String companyCode, final String queryDate, final String ProductPointType) {
		return dao.getTotalBalanceByDate(companyCode, queryDate,ProductPointType);
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
	public List getFiProductPointJournalsStat(final String companyCode, final String dealType, final Integer[] moneyTypes,
	        boolean inverseMoneyType, final String startDate, final String endDate, final String ProductPointType) {
		return dao.getFiProductPointJournalsStat(companyCode, dealType, moneyTypes, inverseMoneyType, startDate, endDate,ProductPointType);
	}

	/**
	 * 存入资金至发展基金帐户
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
	        String operaterCode, final String operaterName, final String uniqueCode, final String[] notes, final String ProductPointType, final String dataType){
		//判断用户存折是否存在
		FiProductPointBalance fiProductPointBalanceTmp = this.fiProductPointBalanceDao.getFiProductPointBalanceByUserCodeAndProductPointType(sysUser.getUserCode(), ProductPointType);
		FiProductPointBalance fiProductPointBalance = this.fiProductPointBalanceDao.getFiProductPointBalanceForUpdate(fiProductPointBalanceTmp.getFbbId());
		if (fiProductPointBalance == null) {
			throw new AppException("发展基金帐户不存在!");
		}
		//判断是否重复审单
		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
			FiProductPointJournal lastFiProductPointJournal = dao.getLastFiProductPointJournalByUnique(uniqueCode,"A");
			if (lastFiProductPointJournal != null && "A".equals(lastFiProductPointJournal.getDealType())) {
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
		FiProductPointJournal lastFiProductPointJournal = this.dao.getLastFiProductPointJournal(sysUser.getUserCode(),ProductPointType);
		if (lastFiProductPointJournal == null) {
			remainMoney = new BigDecimal(0);
			tempMoney =  new BigDecimal(0);
		} else {
			remainMoney = lastFiProductPointJournal.getBalance();
			tempMoney = lastFiProductPointJournal.getBalance();
		}

		Date currentDate = new Date();
		Date currentTime = new Date();
		for(int i = 0 ; i < moneyArray.length ; i++){

			//插入存折临时记录
			FiProductPointTemp fiProductPointTemp = new FiProductPointTemp();
			fiProductPointTemp.setCompanyCode(companyCode);
			fiProductPointTemp.setSysUser(sysUser);
			long totalCount = this.fiProductPointTempDao.getCountByDate(companyCode, sysUser.getUserCode(), ProductPointType);
			fiProductPointTemp.setSerial(totalCount + 1);
			fiProductPointTemp.setDealType("A");
			fiProductPointTemp.setDealDate(currentDate);
			fiProductPointTemp.setMoneyType(moneyType[i]);
			fiProductPointTemp.setMoney(moneyArray[i]);
			fiProductPointTemp.setNotes(notes[i]);
			fiProductPointTemp.setStatus(2);
			fiProductPointTemp.setCreaterCode(operaterCode);
			fiProductPointTemp.setCreaterName(operaterName);
			fiProductPointTemp.setCreateTime(currentTime);
			fiProductPointTemp.setCheckerCode(operaterCode);
			fiProductPointTemp.setCheckerName(operaterName);
			fiProductPointTemp.setCheckeTime(currentTime);
			fiProductPointTemp.setCheckMsg("OK");
			fiProductPointTemp.setCheckType(1);
			fiProductPointTemp.setProductPointType(ProductPointType);

			fiProductPointTempDao.saveFiProductPointTemp(fiProductPointTemp);
			//插入存折流水记录
			FiProductPointJournal fiProductPointJournal = new FiProductPointJournal();
			fiProductPointJournal.setFiProductPointTemp(fiProductPointTemp);
			fiProductPointJournal.setCompanyCode(companyCode);
			fiProductPointJournal.setSysUser(sysUser);
			long todayCount = this.dao.getCountByDate(companyCode, sysUser.getUserCode());
			fiProductPointJournal.setSerial(todayCount + 1);
			fiProductPointJournal.setDealType("A");
			fiProductPointJournal.setDealDate(currentDate);
			fiProductPointJournal.setMoneyType(moneyType[i]);
			fiProductPointJournal.setMoney(moneyArray[i]);
			fiProductPointJournal.setNotes(notes[i]);
			tempMoney = tempMoney.add(moneyArray[i]);
			fiProductPointJournal.setBalance(tempMoney);
			fiProductPointJournal.setCreaterCode(operaterCode);
			fiProductPointJournal.setCreaterName(operaterName);
			fiProductPointJournal.setCreateTime(currentTime);
			fiProductPointJournal.setLastJournalId(lastFiProductPointJournal == null ? 0 : lastFiProductPointJournal.getJournalId());
			fiProductPointJournal.setLastMoney(lastFiProductPointJournal == null ? new BigDecimal(0) : lastFiProductPointJournal.getBalance());
			fiProductPointJournal.setUniqueCode(uniqueCode);
			fiProductPointJournal.setProductPointType(ProductPointType);
			fiProductPointJournal.setDataType(dataType);

			dao.saveFiProductPointJournal(fiProductPointJournal);
		}

		fiProductPointBalance.setBalance(remainMoney.add(money));
		fiProductPointBalanceDao.saveFiProductPointBalance(fiProductPointBalance);
	}

	/**
	 * 从发展基金帐户扣取金额
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
	public void saveFiProductPointDeduct(final String companyCode, final SysUser sysUser, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode,  final String[] notes, final String ProductPointType, final String dataType) throws AppException{
		//判断用户存折是否存在
		FiProductPointBalance fiProductPointBalanceTmp = this.fiProductPointBalanceDao.getFiProductPointBalanceByUserCodeAndProductPointType(sysUser.getUserCode(), ProductPointType);
		FiProductPointBalance fiProductPointBalance = this.fiProductPointBalanceDao.getFiProductPointBalanceForUpdate(fiProductPointBalanceTmp.getFbbId());
		if (fiProductPointBalance == null) {
			throw new AppException("发展基金帐户不存在!");
		}
		//判断是否重复审单
		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
			FiProductPointJournal lastFiProductPointJournal = dao.getLastFiProductPointJournalByUnique(uniqueCode,"D");
			if (lastFiProductPointJournal != null && "D".equals(lastFiProductPointJournal.getDealType())) {
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
		FiProductPointJournal lastFiProductPointJournal = this.dao.getLastFiProductPointJournal(sysUser.getUserCode(),ProductPointType);
		if (lastFiProductPointJournal == null || lastFiProductPointJournal.getBalance().compareTo(money) == -1) {
			throw new AppException("发展基金帐户余额不足！");
		}

		BigDecimal remainMoney;
		BigDecimal tempMoney;
		if (lastFiProductPointJournal == null) {
			remainMoney = new BigDecimal(0);
			tempMoney =  new BigDecimal(0);
		} else {
			remainMoney = lastFiProductPointJournal.getBalance();
			tempMoney = lastFiProductPointJournal.getBalance();
		}
		if (remainMoney.compareTo(money) == -1) {
			throw new AppException("发展基金帐户余额不足！");
		}
		
		for(int i = 0 ; i < moneyArray.length ; i++){
			//插入存折临时记录
			FiProductPointTemp fiProductPointTemp = new FiProductPointTemp();
			fiProductPointTemp.setCompanyCode(companyCode);
			fiProductPointTemp.setSysUser(sysUser);
			long totalCount = this.fiProductPointTempDao.getCountByDate(companyCode, sysUser.getUserCode(), ProductPointType);
			fiProductPointTemp.setSerial(totalCount + 1);
			fiProductPointTemp.setDealType("D");
			fiProductPointTemp.setDealDate(currentDate);
			fiProductPointTemp.setMoneyType(moneyType[i]);
			fiProductPointTemp.setMoney(moneyArray[i]);
			fiProductPointTemp.setNotes(notes[i]);
			fiProductPointTemp.setStatus(2);
			fiProductPointTemp.setCreaterCode(operaterCode);
			fiProductPointTemp.setCreaterName(operaterName);
			fiProductPointTemp.setCreateTime(currentTime);
			fiProductPointTemp.setCheckerCode(operaterCode);
			fiProductPointTemp.setCheckerName(operaterName);
			fiProductPointTemp.setCheckeTime(currentTime);
			fiProductPointTemp.setCheckMsg("OK");
			fiProductPointTemp.setCheckType(1);
			fiProductPointTemp.setProductPointType(ProductPointType);
			fiProductPointTempDao.saveFiProductPointTemp(fiProductPointTemp);

			//插入存折流水记录
			FiProductPointJournal fiProductPointJournal = new FiProductPointJournal();
			fiProductPointJournal.setFiProductPointTemp(fiProductPointTemp);
			fiProductPointJournal.setCompanyCode(companyCode);
			fiProductPointJournal.setSysUser(sysUser);
			long todayCount = this.dao.getCountByDate(companyCode, sysUser.getUserCode());
			fiProductPointJournal.setSerial(todayCount + 1);
			fiProductPointJournal.setDealType("D");
			fiProductPointJournal.setDealDate(currentDate);
			fiProductPointJournal.setMoneyType(moneyType[i]);
			fiProductPointJournal.setMoney(moneyArray[i]);
			fiProductPointJournal.setNotes(notes[i]);
			tempMoney = tempMoney.subtract(moneyArray[i]);
			fiProductPointJournal.setBalance(tempMoney);
			fiProductPointJournal.setCreaterCode(operaterCode);
			fiProductPointJournal.setCreaterName(operaterName);
			fiProductPointJournal.setCreateTime(currentTime);
			fiProductPointJournal.setLastJournalId(lastFiProductPointJournal == null ? 0 : lastFiProductPointJournal.getJournalId());
			fiProductPointJournal.setLastMoney(lastFiProductPointJournal == null ? new BigDecimal(0) : lastFiProductPointJournal.getBalance());
			fiProductPointJournal.setUniqueCode(uniqueCode);
			fiProductPointJournal.setProductPointType(ProductPointType);
			fiProductPointJournal.setDataType(dataType);
			
			this.dao.saveFiProductPointJournal(fiProductPointJournal);
		}

		fiProductPointBalance.setBalance(remainMoney.subtract(money));
		fiProductPointBalanceDao.saveFiProductPointBalance(fiProductPointBalance);
	}



	/**
	 * POS数据进存折
	 */
	public void saveJfiPayDataVerifyByPosImport(final String companyCode, final SysUser sysUser, String operaterCode, final String operaterName, JfiPosImport jfiPosImport){
		jfiPosImportManager.saveJfiPosImport(jfiPosImport);
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = jfiPosImport.getAmount();
		
		Integer[] moneyType = new Integer[1];
		moneyType[0] = 89;//POS现场刷卡支付(快钱) 
		
		String[] notes = new String[1];
		notes[0] = "POS现场刷卡支付,会员手机验证";
		this.saveFiPayDataVerify(companyCode, sysUser, moneyType, moneyArray, operaterCode, operaterName,jfiPosImport.getPosNo(),notes,"1","1");
	}

	public void setFiProductPointBalanceDao(FiProductPointBalanceDao fiProductPointBalanceDao) {
		this.fiProductPointBalanceDao = fiProductPointBalanceDao;
	}

	public void setFiProductPointTempDao(FiProductPointTempDao fiProductPointTempDao) {
		this.fiProductPointTempDao = fiProductPointTempDao;
	}

	/**
	 * 查询退单的退基金情况
	 * @author gw 2015-07-10
	 * @param roNo
	 * @return bigDecimal
	 */
	public BigDecimal getBeforeJprRefundJj(String roNo) {
		return dao.getBeforeJprRefundJj(roNo);
	}
	
	/**
	 * 查看这张退单之前退了多少基金
	 * @author gw 2015-07-13
	 * @param roNo
	 * @return bigDecimal
	 */
	public BigDecimal getJprRefundJjBack(String roNo) {
		return dao.getJprRefundJjBack(roNo);
	}
}
