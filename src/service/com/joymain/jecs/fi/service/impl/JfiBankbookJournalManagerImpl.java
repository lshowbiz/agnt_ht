
package com.joymain.jecs.fi.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.fi.dao.JfiBankbookBalanceDao;
import com.joymain.jecs.fi.dao.JfiBankbookJournalDao;
import com.joymain.jecs.fi.dao.JfiBankbookTempDao;
import com.joymain.jecs.fi.model.FiChannelLog;
import com.joymain.jecs.fi.model.Jfi99billLog;
import com.joymain.jecs.fi.model.Jfi99billmsLog;
import com.joymain.jecs.fi.model.JfiAlipayLog;
import com.joymain.jecs.fi.model.JfiBankbookBalance;
import com.joymain.jecs.fi.model.JfiBankbookJournal;
import com.joymain.jecs.fi.model.JfiBankbookTemp;
import com.joymain.jecs.fi.model.JfiChanjetLog;
import com.joymain.jecs.fi.model.JfiChinapnrLog;
import com.joymain.jecs.fi.model.JfiHiTrustLog;
import com.joymain.jecs.fi.model.JfiPayLog;
import com.joymain.jecs.fi.model.JfiReapalLog;
import com.joymain.jecs.fi.model.JfiTenpayLog;
import com.joymain.jecs.fi.model.JfiUmbpayLog;
import com.joymain.jecs.fi.model.JfiUsCreditCardLog;
import com.joymain.jecs.fi.model.JfiYeepayLog;
import com.joymain.jecs.fi.service.FiChannelLogManager;
import com.joymain.jecs.fi.service.Jfi99billLogManager;
import com.joymain.jecs.fi.service.Jfi99billmsLogManager;
import com.joymain.jecs.fi.service.JfiAlipayLogManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.fi.service.JfiChanjetLogManager;
import com.joymain.jecs.fi.service.JfiChinapnrLogManager;
import com.joymain.jecs.fi.service.JfiHiTrustLogManager;
import com.joymain.jecs.fi.service.JfiPayLogManager;
import com.joymain.jecs.fi.service.JfiReapalLogManager;
import com.joymain.jecs.fi.service.JfiTenpayLogManager;
import com.joymain.jecs.fi.service.JfiUmbpayLogManager;
import com.joymain.jecs.fi.service.JfiUsCreditCardLogManager;
import com.joymain.jecs.fi.service.JfiYeepayLogManager;
import com.joymain.jecs.po.dao.JpoBankBookPayListDao;
import com.joymain.jecs.po.model.JpoBankBookPayList;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
@SuppressWarnings({"rawtypes","unused"})
public class JfiBankbookJournalManagerImpl extends BaseManager implements JfiBankbookJournalManager {
    private final Log log = LogFactory.getLog(JfiBankbookJournalManagerImpl.class);
    private JfiBankbookJournalDao dao;
	private JfiBankbookTempDao jfiBankbookTempDao;
	private JfiBankbookBalanceDao jfiBankbookBalanceDao;
	private Jfi99billLogManager jfi99billLogManager;
	private JfiAlipayLogManager jfiAlipayLogManager;
	private JfiHiTrustLogManager jfiHiTrustLogManager;
	private JfiUsCreditCardLogManager jfiUsCreditCardLogManager;
	private JfiTenpayLogManager jfiTenpayLogManager;
	private Jfi99billmsLogManager jfi99billmsLogManager;
	private JfiChanjetLogManager jfiChanjetLogManager;
	private JfiUmbpayLogManager jfiUmbpayLogManager;
	private FiChannelLogManager fiChannelLogManager;
	private JfiChinapnrLogManager chinapnrLogManager;
	private JfiYeepayLogManager jfiYeepayLogManager;
	
	private JfiReapalLogManager jfiReapalLogManager;//Modify By WuCF 20150923融宝支付
	private JfiPayLogManager jfiPayLogManager;//支付接口
	private JpoBankBookPayListDao jpoBankBookPayListDao;
	
	public void setJpoBankBookPayListDao(JpoBankBookPayListDao jpoBankBookPayListDao) {
		this.jpoBankBookPayListDao = jpoBankBookPayListDao;
	}


	public void setJfiPayLogManager(JfiPayLogManager jfiPayLogManager) {
		this.jfiPayLogManager = jfiPayLogManager;
	}


	public void setJfiReapalLogManager(JfiReapalLogManager jfiReapalLogManager) {
		this.jfiReapalLogManager = jfiReapalLogManager;
	}


	public void setJfiYeepayLogManager(JfiYeepayLogManager jfiYeepayLogManager) {
        this.jfiYeepayLogManager = jfiYeepayLogManager;
    }
	

	public void setChinapnrLogManager(JfiChinapnrLogManager chinapnrLogManager) {
		this.chinapnrLogManager = chinapnrLogManager;
	}
	
	
	public void setFiChannelLogManager(FiChannelLogManager fiChannelLogManager) {
		this.fiChannelLogManager = fiChannelLogManager;
	}

	public void setJfiUmbpayLogManager(JfiUmbpayLogManager jfiUmbpayLogManager) {
		this.jfiUmbpayLogManager = jfiUmbpayLogManager;
	}
	
    public void setJfiChanjetLogManager(JfiChanjetLogManager jfiChanjetLogManager) {
		this.jfiChanjetLogManager = jfiChanjetLogManager;
	}

    public void setJfi99billLogManager(Jfi99billLogManager jfi99billLogManager) {
		this.jfi99billLogManager = jfi99billLogManager;
	}

	public void setJfiBankbookTempDao(JfiBankbookTempDao jfiBankbookTempDao) {
		this.jfiBankbookTempDao = jfiBankbookTempDao;
	}

	public void setJfiBankbookBalanceDao(JfiBankbookBalanceDao jfiBankbookBalanceDao) {
		this.jfiBankbookBalanceDao = jfiBankbookBalanceDao;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiBankbookJournalDao(JfiBankbookJournalDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiBankbookJournalManager#getJfiBankbookJournals(com.joymain.jecs.fi.model.JfiBankbookJournal)
     */
	public List getJfiBankbookJournals(final JfiBankbookJournal jfiBankbookJournal) {
        return dao.getJfiBankbookJournals(jfiBankbookJournal);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiBankbookJournalManager#getJfiBankbookJournal(String journalId)
     */
    public JfiBankbookJournal getJfiBankbookJournal(final String journalId) {
        return dao.getJfiBankbookJournal(new Long(journalId));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiBankbookJournalManager#saveJfiBankbookJournal(JfiBankbookJournal jfiBankbookJournal)
     */
    public void saveJfiBankbookJournal(JfiBankbookJournal jfiBankbookJournal) {
        dao.saveJfiBankbookJournal(jfiBankbookJournal);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiBankbookJournalManager#removeJfiBankbookJournal(String journalId)
     */
    public void removeJfiBankbookJournal(final String journalId) {
        dao.removeJfiBankbookJournal(new Long(journalId));
    }
    //added for getJfiBankbookJournalsByCrm
    public List getJfiBankbookJournalsByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiBankbookJournalsByCrm(crm,pager);
    }
    
	/**
	 * 验证存折条目
	 * @param tempId
	 * @param sysUser
	 * @return
	 */
	public void saveJfiBankbookTempCheck(final String tempId, final SysUser sysUser) throws AppException{
		try {
			//审批
			Map resultMap = dao.callProcCheckJfiBankbookTemp(new Long(tempId), sysUser);
			if ("1".equals(resultMap.get("Vi_Errno").toString())) {
				//余额不足
				throw new AppException("error.fiBankbookJournal.balance.not.enough");
			} else if ("2".equals(resultMap.get("Vi_Errno").toString())) {
				throw new AppException("fiBankbookTemp.verified");
			} else if ("3".equals(resultMap.get("Vi_Errno").toString())) {
				throw new AppException("未知错误");
			} else if ("4".equals(resultMap.get("Vi_Errno").toString())) {
				throw new AppException("此存折临时条目不存在");
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
	public BigDecimal getTotalBalanceByDate(final String companyCode, final String queryDate) {
		return dao.getTotalBalanceByDate(companyCode, queryDate);
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
	public List getJfiBankbookJournalsStat(final String companyCode, final String dealType, final Integer[] moneyTypes,
	        boolean inverseMoneyType, final String startDate, final String endDate) {
		return dao.getJfiBankbookJournalsStat(companyCode, dealType, moneyTypes, inverseMoneyType, startDate, endDate);
	}

	/**
	 * 存入资金至电子存折帐户帐户
	 * @param companyCode 公司编码
	 * @param userCode 用户编码
	 * @param moneyType 资金类别
	 * @param money 资金金额
	 * @param operaterCode 操作人编码
	 * @param operaterName 操作人姓名
	 * * @param uniqueCode 业务唯一码,0为没有
	 * @param notes 存款说明
	 */
	public void saveJfiPayDataVerify(final String companyCode, final SysUser sysUser, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode, final String[] notes,String dataType){
	    Date date1=new Date();
	    log.info("jfiBankBookJournalManager method saveJfiPayDataVerify");
		//判断用户存折是否存在
		JfiBankbookBalance jfiBankbookBalance = this.jfiBankbookBalanceDao.getJfiBankbookBalanceForUpdate(sysUser.getUserCode());
		if (jfiBankbookBalance == null) {
		    log.error("jfiBankBookJournalManager method saveJfiPayDataVerify jfiBankBook is not exist");
			throw new AppException("电子存折帐户不存在");
		}
	    Date date2=new Date();
		//判断是否重复审单
		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
			JfiBankbookJournal lastJfiBankbookJournal = dao.getLastJfiBankbookJournalByUnique(uniqueCode,"A");
			if (lastJfiBankbookJournal != null && "A".equals(lastJfiBankbookJournal.getDealType())) {
			    log.error("jfiBankBookJournalManager method saveJfiPayDataVerify Repeat the operation");
				throw new AppException("重复操作");
			}
		}

	    Date date3=new Date();
		BigDecimal money = new BigDecimal(0);
		for(int i = 0 ; i < moneyArray.length ; i++){
			if(moneyArray[i].compareTo(new BigDecimal("0"))==-1){
			    log.error("jfiBankBookJournalManager method saveJfiPayDataVerify insufficient in amount");
				throw new AppException("金额参数必须大于或等于零" + moneyArray[i]);
			}
			money = money.add(moneyArray[i]);
		}

	    Date date4=new Date();
		BigDecimal remainMoney;
		BigDecimal tempMoney;
		JfiBankbookJournal lastJfiBankbookJournal = this.dao.getLastJfiBankbookJournal(sysUser.getUserCode());
		if (lastJfiBankbookJournal == null) {
			remainMoney = new BigDecimal(0);
			tempMoney =  new BigDecimal(0);
		} else {
			remainMoney = lastJfiBankbookJournal.getBalance();
			tempMoney = lastJfiBankbookJournal.getBalance();
		}

	    Date date5=new Date();
		Date currentDate = new Date();
		Date currentTime = new Date();
		for(int i = 0 ; i < moneyArray.length ; i++){

			//插入存折临时记录
			JfiBankbookTemp jfiBankbookTemp = new JfiBankbookTemp();
			jfiBankbookTemp.setCompanyCode(companyCode);
			jfiBankbookTemp.setSysUser(sysUser);
			long totalCount = this.jfiBankbookTempDao.getCountByDate(companyCode, sysUser.getUserCode());
			jfiBankbookTemp.setSerial(totalCount + 1);
			jfiBankbookTemp.setDealType("A");
			jfiBankbookTemp.setDealDate(currentDate);
			jfiBankbookTemp.setMoneyType(moneyType[i]);
			jfiBankbookTemp.setMoney(moneyArray[i]);
			jfiBankbookTemp.setNotes(notes[i]);
			jfiBankbookTemp.setStatus(2);
			jfiBankbookTemp.setCreaterCode(operaterCode);
			jfiBankbookTemp.setCreaterName(operaterName);
			jfiBankbookTemp.setCreateTime(currentTime);
			jfiBankbookTemp.setCheckerCode(operaterCode);
			jfiBankbookTemp.setCheckerName(operaterName);
			jfiBankbookTemp.setCheckeTime(currentTime);
			jfiBankbookTemp.setCheckMsg("OK");
			jfiBankbookTemp.setCheckType(1);

			jfiBankbookTempDao.saveJfiBankbookTemp(jfiBankbookTemp);
			//插入存折流水记录
			JfiBankbookJournal jfiBankbookJournal = new JfiBankbookJournal();
			jfiBankbookJournal.setJfiBankbookTemp(jfiBankbookTemp);
			jfiBankbookJournal.setCompanyCode(companyCode);
			jfiBankbookJournal.setSysUser(sysUser);
			long todayCount = this.dao.getCountByDate(companyCode, sysUser.getUserCode());
			jfiBankbookJournal.setSerial(todayCount + 1);
			jfiBankbookJournal.setDealType("A");
			jfiBankbookJournal.setDealDate(currentDate);
			jfiBankbookJournal.setMoneyType(moneyType[i]);
			jfiBankbookJournal.setMoney(moneyArray[i]);
			jfiBankbookJournal.setNotes(notes[i]);
			tempMoney = tempMoney.add(moneyArray[i]);
			jfiBankbookJournal.setBalance(tempMoney);
			jfiBankbookJournal.setCreaterCode(operaterCode);
			jfiBankbookJournal.setCreaterName(operaterName);
			jfiBankbookJournal.setCreateTime(currentTime);
			jfiBankbookJournal.setLastJournalId(lastJfiBankbookJournal == null ? 0 : lastJfiBankbookJournal.getJournalId());
			jfiBankbookJournal.setLastMoney(lastJfiBankbookJournal == null ? new BigDecimal(0) : lastJfiBankbookJournal.getBalance());
			jfiBankbookJournal.setUniqueCode(uniqueCode);
			jfiBankbookJournal.setDataType(dataType);

			dao.saveJfiBankbookJournal(jfiBankbookJournal);
		}


	    Date date6=new Date();
		
		jfiBankbookBalance.setBalance(remainMoney.add(money));
		jfiBankbookBalanceDao.saveJfiBankbookBalance(jfiBankbookBalance);
	    Date date7=new Date();
	}

	/**
	 * 从电子存折帐户扣取金额
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
	        String operaterCode, final String operaterName, final String uniqueCode,  final String[] notes,String dataType) throws AppException{
	    log.info("jfiBankBookJournalManager method saveJfiBankbookDeduct");
		//判断用户存折是否存在
		JfiBankbookBalance jfiBankbookBalance = this.jfiBankbookBalanceDao.getJfiBankbookBalanceForUpdate(sysUser.getUserCode());
		if (jfiBankbookBalance == null) {
		    log.error("jfiBankBookJournalManager method saveJfiBankbookDeduct jfiBankBook is not exist");
			throw new AppException("电子存折帐户不存在");
		}
		//判断是否重复审单
		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
			JfiBankbookJournal lastJfiBankbookJournal = dao.getLastJfiBankbookJournalByUnique(uniqueCode,"D");
			if (lastJfiBankbookJournal != null && "D".equals(lastJfiBankbookJournal.getDealType())) {
			    log.error("jfiBankBookJournalManager method saveJfiBankbookDeduct Repeat the operation");
				throw new AppException("重复操作");
			}
		}
		
		BigDecimal money = new BigDecimal(0);
		for(int i = 0 ; i < moneyArray.length ; i++){
			if(moneyArray[i].compareTo(new BigDecimal("0"))==-1){
			    log.error("jfiBankBookJournalManager method saveJfiBankbookDeduct insufficient in amount");
				throw new AppException("金额参数必须大于或等于零"+moneyArray[i]);
			}
			money = money.add(moneyArray[i]);
		}

		Date currentDate = new Date();
		Date currentTime = new Date();

		//首先验证余额是否足够
		JfiBankbookJournal lastJfiBankbookJournal = this.dao.getLastJfiBankbookJournal(sysUser.getUserCode());
/*		if (lastJfiBankbookJournal == null || lastJfiBankbookJournal.getBalance().compareTo(money) == -1) {
			throw new AppException("error.fiBankbookJournal.balance.not.enough");
		}*/
		if (jfiBankbookBalance.getBalance().compareTo(money) == -1) {
		    log.error("jfiBankBookJournalManager method saveJfiBankbookDeduct bankbook insufficient in money");
			throw new AppException("电子存折帐户余额不足！");
		}

		BigDecimal remainMoney;
		BigDecimal tempMoney;
		if (lastJfiBankbookJournal == null) {
			remainMoney = new BigDecimal(0);
			tempMoney =  new BigDecimal(0);
		} else {
			remainMoney = lastJfiBankbookJournal.getBalance();
			tempMoney = lastJfiBankbookJournal.getBalance();
		}
		if (remainMoney.compareTo(money) == -1) {
		    log.error("jfiBankBookJournalManager method saveJfiBankbookDeduct bankbook insufficient in money");
			throw new AppException("电子存折帐户余额不足！");
		}
		
		for(int i = 0 ; i < moneyArray.length ; i++){
			//插入存折临时记录
			JfiBankbookTemp jfiBankbookTemp = new JfiBankbookTemp();
			jfiBankbookTemp.setCompanyCode(companyCode);
			jfiBankbookTemp.setSysUser(sysUser);
			long totalCount = this.jfiBankbookTempDao.getCountByDate(companyCode, sysUser.getUserCode());
			jfiBankbookTemp.setSerial(totalCount + 1);
			jfiBankbookTemp.setDealType("D");
			jfiBankbookTemp.setDealDate(currentDate);
			jfiBankbookTemp.setMoneyType(moneyType[i]);
			jfiBankbookTemp.setMoney(moneyArray[i]);
			jfiBankbookTemp.setNotes(notes[i]);
			jfiBankbookTemp.setStatus(2);
			jfiBankbookTemp.setCreaterCode(operaterCode);
			jfiBankbookTemp.setCreaterName(operaterName);
			jfiBankbookTemp.setCreateTime(currentTime);
			jfiBankbookTemp.setCheckerCode(operaterCode);
			jfiBankbookTemp.setCheckerName(operaterName);
			jfiBankbookTemp.setCheckeTime(currentTime);
			jfiBankbookTemp.setCheckMsg("OK");
			jfiBankbookTemp.setCheckType(1);
			jfiBankbookTempDao.saveJfiBankbookTemp(jfiBankbookTemp);

			//插入存折流水记录
			JfiBankbookJournal fiBankbookJournal = new JfiBankbookJournal();
			fiBankbookJournal.setJfiBankbookTemp(jfiBankbookTemp);
			fiBankbookJournal.setCompanyCode(companyCode);
			fiBankbookJournal.setSysUser(sysUser);
			long todayCount = this.dao.getCountByDate(companyCode, sysUser.getUserCode());
			fiBankbookJournal.setSerial(todayCount + 1);
			fiBankbookJournal.setDealType("D");
			fiBankbookJournal.setDealDate(currentDate);
			fiBankbookJournal.setMoneyType(moneyType[i]);
			fiBankbookJournal.setMoney(moneyArray[i]);
			fiBankbookJournal.setNotes(notes[i]);
			tempMoney = tempMoney.subtract(moneyArray[i]);
			fiBankbookJournal.setBalance(tempMoney);
			fiBankbookJournal.setCreaterCode(operaterCode);
			fiBankbookJournal.setCreaterName(operaterName);
			fiBankbookJournal.setCreateTime(currentTime);
			fiBankbookJournal.setLastJournalId(lastJfiBankbookJournal == null ? 0 : lastJfiBankbookJournal.getJournalId());
			fiBankbookJournal.setLastMoney(lastJfiBankbookJournal == null ? new BigDecimal(0) : lastJfiBankbookJournal.getBalance());
			fiBankbookJournal.setUniqueCode(uniqueCode);
			fiBankbookJournal.setDataType(dataType);
			this.dao.saveJfiBankbookJournal(fiBankbookJournal);
		}

		jfiBankbookBalance.setBalance(remainMoney.subtract(money));
		jfiBankbookBalanceDao.saveJfiBankbookBalance(jfiBankbookBalance);
	}
	
	public void saveJfiBankbookDeduct1(final String companyCode, final SysUser sysUser, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode,  final String[] notes,String dataType) throws AppException{
	    log.info("jfiBankBookJournalManager method saveJfiBankbookDeduct");
		//判断用户存折是否存在
		JfiBankbookBalance jfiBankbookBalance = this.jfiBankbookBalanceDao.getJfiBankbookBalanceForUpdate(sysUser.getUserCode());
		if (jfiBankbookBalance == null) {
		    log.error("jfiBankBookJournalManager method saveJfiBankbookDeduct jfiBankBook is not exist");
			throw new AppException("电子存折帐户不存在");
		}
		//判断是否重复审单
		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
			JfiBankbookJournal lastJfiBankbookJournal = dao.getLastJfiBankbookJournalByUnique(uniqueCode,"D");
			if (lastJfiBankbookJournal != null && "D".equals(lastJfiBankbookJournal.getDealType())) {
			    log.error("jfiBankBookJournalManager method saveJfiBankbookDeduct Repeat the operation");
				throw new AppException("重复操作");
			}
		}
		
		BigDecimal money = new BigDecimal(0);
		for(int i = 0 ; i < moneyArray.length ; i++){
			if(moneyArray[i].compareTo(new BigDecimal("0"))==-1){
			    log.error("jfiBankBookJournalManager method saveJfiBankbookDeduct insufficient in amount");
				throw new AppException("金额参数必须大于或等于零"+moneyArray[i]);
			}
			money = money.add(moneyArray[i]);
		}

		Date currentDate = new Date();
		Date currentTime = new Date();

		//首先验证余额是否足够
		JfiBankbookJournal lastJfiBankbookJournal = this.dao.getLastJfiBankbookJournal(sysUser.getUserCode());
/*		if (lastJfiBankbookJournal == null || lastJfiBankbookJournal.getBalance().compareTo(money) == -1) {
			throw new AppException("error.fiBankbookJournal.balance.not.enough");
		}*/
		if (jfiBankbookBalance.getBalance().compareTo(money) == -1) {
		    log.error("jfiBankBookJournalManager method saveJfiBankbookDeduct bankbook insufficient in money");
			throw new AppException("电子存折帐户余额不足！");
		}

		BigDecimal remainMoney;
		BigDecimal tempMoney;
		if (lastJfiBankbookJournal == null) {
			remainMoney = new BigDecimal(0);
			tempMoney =  new BigDecimal(0);
		} else {
			remainMoney = lastJfiBankbookJournal.getBalance();
			tempMoney = lastJfiBankbookJournal.getBalance();
		}
		if (remainMoney.compareTo(money) == -1) {
		    log.error("jfiBankBookJournalManager method saveJfiBankbookDeduct bankbook insufficient in money");
			throw new AppException("电子存折帐户余额不足！");
		}
		
/*		for(int i = 0 ; i < moneyArray.length ; i++){
			//插入存折临时记录
			JfiBankbookTemp jfiBankbookTemp = new JfiBankbookTemp();
			jfiBankbookTemp.setCompanyCode(companyCode);
			jfiBankbookTemp.setSysUser(sysUser);
			long totalCount = this.jfiBankbookTempDao.getCountByDate(companyCode, sysUser.getUserCode());
			jfiBankbookTemp.setSerial(totalCount + 1);
			jfiBankbookTemp.setDealType("D");
			jfiBankbookTemp.setDealDate(currentDate);
			jfiBankbookTemp.setMoneyType(moneyType[i]);
			jfiBankbookTemp.setMoney(moneyArray[i]);
			jfiBankbookTemp.setNotes(notes[i]);
			jfiBankbookTemp.setStatus(2);
			jfiBankbookTemp.setCreaterCode(operaterCode);
			jfiBankbookTemp.setCreaterName(operaterName);
			jfiBankbookTemp.setCreateTime(currentTime);
			jfiBankbookTemp.setCheckerCode(operaterCode);
			jfiBankbookTemp.setCheckerName(operaterName);
			jfiBankbookTemp.setCheckeTime(currentTime);
			jfiBankbookTemp.setCheckMsg("OK");
			jfiBankbookTemp.setCheckType(1);
			jfiBankbookTempDao.saveJfiBankbookTemp(jfiBankbookTemp);

			//插入存折流水记录
			JfiBankbookJournal fiBankbookJournal = new JfiBankbookJournal();
			fiBankbookJournal.setJfiBankbookTemp(jfiBankbookTemp);
			fiBankbookJournal.setCompanyCode(companyCode);
			fiBankbookJournal.setSysUser(sysUser);
			long todayCount = this.dao.getCountByDate(companyCode, sysUser.getUserCode());
			fiBankbookJournal.setSerial(todayCount + 1);
			fiBankbookJournal.setDealType("D");
			fiBankbookJournal.setDealDate(currentDate);
			fiBankbookJournal.setMoneyType(moneyType[i]);
			fiBankbookJournal.setMoney(moneyArray[i]);
			fiBankbookJournal.setNotes(notes[i]);
			tempMoney = tempMoney.subtract(moneyArray[i]);
			fiBankbookJournal.setBalance(tempMoney);
			fiBankbookJournal.setCreaterCode(operaterCode);
			fiBankbookJournal.setCreaterName(operaterName);
			fiBankbookJournal.setCreateTime(currentTime);
			fiBankbookJournal.setLastJournalId(lastJfiBankbookJournal == null ? 0 : lastJfiBankbookJournal.getJournalId());
			fiBankbookJournal.setLastMoney(lastJfiBankbookJournal == null ? new BigDecimal(0) : lastJfiBankbookJournal.getBalance());
			fiBankbookJournal.setUniqueCode(uniqueCode);
			fiBankbookJournal.setDataType(dataType);
			this.dao.saveJfiBankbookJournal(fiBankbookJournal);
		}

		jfiBankbookBalance.setBalance(remainMoney.subtract(money));
		jfiBankbookBalanceDao.saveJfiBankbookBalance(jfiBankbookBalance);*/
	}
	/**
	 * 台湾信用卡
	 */
	public BigDecimal saveJfiPayDataVerifyByHiTrust(final String companyCode, final SysUser sysUser, final BigDecimal totalMoney,
	        String operaterCode, final String operaterName, final String uniqueCode, JfiHiTrustLog jfiHiTrustLog){
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = totalMoney;
		
		Integer[] moneyType = new Integer[1];
		moneyType[0]= 50;
		
		String[] notes = new String[1];
		notes[0] = "台湾信用卡";
		
		this.saveJfiPayDataVerify(companyCode, sysUser, moneyType, moneyArray, operaterCode, operaterName,uniqueCode,notes,"1");
		jfiHiTrustLog.setInc("1");
		jfiHiTrustLog.setReturnMsg("S1");
		jfiHiTrustLogManager.saveJfiHiTrustLog(jfiHiTrustLog);
		return moneyArray[0];
	}

	/**
	 * 美国信用卡
	 */
	public BigDecimal saveJfiPayDataVerifyByUsCreditCard(final String companyCode, final SysUser sysUser, final BigDecimal totalMoney,
	        String operaterCode, final String operaterName, final String uniqueCode, JfiUsCreditCardLog jfiUsCreditCardLog){
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = totalMoney;
		
		Integer[] moneyType = new Integer[1];
		moneyType[0]= 60;
		
		String[] notes = new String[1];
		notes[0] = "美国信用卡";
		
		this.saveJfiPayDataVerify(companyCode, sysUser, moneyType, moneyArray, operaterCode, operaterName,uniqueCode,notes,"1");
		jfiUsCreditCardLog.setInc("1");
		jfiUsCreditCardLogManager.saveJfiUsCreditCardLog(jfiUsCreditCardLog);
		return moneyArray[0];
	}

	/**
	 * 支付宝数据进存折
	 */
	public BigDecimal saveJfiPayDataVerifyByAlipay(final String companyCode, final SysUser sysUser, final BigDecimal totalMoney,
	        String operaterCode, final String operaterName, final String uniqueCode, JfiAlipayLog jfiAlipayLog){
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = totalMoney;
		
		Integer[] moneyType = new Integer[1];
		moneyType[0]= 39;
		
		String[] notes = new String[1];
		notes[0] = "支付宝支付";
		
		this.saveJfiPayDataVerify(companyCode, sysUser, moneyType, moneyArray, operaterCode, operaterName,uniqueCode,notes,"1");
		jfiAlipayLog.setInc("1");
		jfiAlipayLog.setReturnMsg("10");
		jfiAlipayLogManager.saveJfiAlipayLog(jfiAlipayLog);
		return moneyArray[0];
	}


	/**
	 * 财付通数据进存折
	 */
	public BigDecimal saveJfiPayDataVerifyByTenpay(final String companyCode, final SysUser sysUser, final BigDecimal totalMoney,
	        String operaterCode, final String operaterName, final String uniqueCode, JfiTenpayLog jfiTenpayLog){
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = totalMoney;
		
		Integer[] moneyType = new Integer[1];
		moneyType[0]= 49;
		
		String[] notes = new String[1];
		notes[0] = "财付通支付";
		
		this.saveJfiPayDataVerify(companyCode, sysUser, moneyType, moneyArray, operaterCode, operaterName,uniqueCode,notes,"1");
		jfiTenpayLog.setInc("1");
		jfiTenpayLog.setReturnMsg("10");
		jfiTenpayLogManager.saveJfiTenpayLog(jfiTenpayLog);
		return moneyArray[0];
	}

	/**
	 * 快钱分润数据进存折
	 */
	public BigDecimal saveJfiPayDataVerifyByBill99ms(final String companyCode, final SysUser sysUser, final BigDecimal totalMoney,
	        String operaterCode, final String operaterName, final String uniqueCode, Jfi99billmsLog jfi99billmsLog,final BigDecimal fee){
		BigDecimal[] moneyArray = new BigDecimal[2];
		moneyArray[0] = totalMoney.subtract(fee);
		moneyArray[1] = fee;
		
		Integer[] moneyType = new Integer[2];
		moneyType[0] = 29;
		moneyType[1] = 31;
		
		String[] notes = new String[2];
		notes[0] = "快钱支付";
		notes[1] = "快钱手续费";
		
		this.saveJfiPayDataVerify(companyCode, sysUser, moneyType, moneyArray, operaterCode, operaterName,uniqueCode,notes,"1");
		jfi99billmsLog.setInc("1");
		jfi99billmsLogManager.saveJfi99billmsLog(jfi99billmsLog);
		return moneyArray[1];
	}

	/**
	 * 快钱数据进存折
	 */
	public BigDecimal saveJfiPayDataVerifyByBill99(final String companyCode, final SysUser sysUser, final BigDecimal totalMoney,
	        String operaterCode, final String operaterName, final String uniqueCode, Jfi99billLog jfi99billLog,final BigDecimal fee){
		BigDecimal[] moneyArray = new BigDecimal[2];
		moneyArray[0] = totalMoney.subtract(fee);
		moneyArray[1] = fee;
		
		Integer[] moneyType = new Integer[2];
		moneyType[0] = 29;
		moneyType[1] = 31;
		
		String[] notes = new String[2];
		notes[0] = "快钱支付";
		notes[1] = "快钱手续费";
		
		this.saveJfiPayDataVerify(companyCode, sysUser, moneyType, moneyArray, operaterCode, operaterName,uniqueCode,notes,"1");
		jfi99billLog.setInc("1");
		jfi99billLogManager.saveJfi99billLog(jfi99billLog);
		return moneyArray[1];
	}

	/**
	 * 手机支付快钱数据进存折
	 */
	public BigDecimal saveJfiPayDataVerifyByMobil(final String companyCode, final SysUser sysUser, final BigDecimal totalMoney,
	        String operaterCode, final String operaterName, final String uniqueCode, Jfi99billLog jfi99billLog,final BigDecimal fee){
		BigDecimal[] moneyArray = new BigDecimal[2];
		moneyArray[0] = totalMoney.subtract(fee);
		moneyArray[1] = fee;
		
		Integer[] moneyType = new Integer[2];
		moneyType[0] = 29;
		moneyType[1] = 31;
		
		String[] notes = new String[2];
		notes[0] = "手机支付";
		notes[1] = "手机支付手续费";
		
		this.saveJfiPayDataVerify(companyCode, sysUser, moneyType, moneyArray, operaterCode, operaterName,uniqueCode,notes,"2");
		
		jfi99billLog.setInc("1");
		jfi99billLogManager.saveJfi99billLog(jfi99billLog);
		
		return moneyArray[1];
	}
	
	/**
	 * 宝易互通进存折
	 * @throws Exception 
	 */
	@Override
	public BigDecimal saveJfiPayDataVerifyByUmbpay(final String companyCode, final SysUser sysUser, final BigDecimal totalMoney,
	        String operaterCode, final String operaterName, final String uniqueCode, JfiUmbpayLog jfiUmbpayLog) throws AppException{
		
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = totalMoney;
		
		Integer[] moneyType = new Integer[1];
		moneyType[0] = 120;//120：宝易互通支付费用
		
		String[] notes = new String[1];
		notes[0] = "宝易互通";
		
		this.saveJfiPayDataVerify(companyCode, sysUser, moneyType, moneyArray, operaterCode, operaterName,uniqueCode,notes,"1");
		jfiUmbpayLog.setInc("1");
		jfiUmbpayLogManager.saveJfiUmbpayLog(jfiUmbpayLog);
		return moneyArray[0];
	}
	
	/**
	 * 汇付天下进存折
	 * @throws Exception 
	 */
	@Override
	public BigDecimal saveJfiPayDataVerifyByChinapnr(final String companyCode, final SysUser sysUser, final BigDecimal totalMoney,
	        String operaterCode, final String operaterName, final String uniqueCode, JfiChinapnrLog entity) throws AppException{
		
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = totalMoney;
		
		Integer[] moneyType = new Integer[1];
		moneyType[0] = 130;//120：汇付天下支付费用
		
		String[] notes = new String[1];
		notes[0] = "汇付天下";
		
		this.saveJfiPayDataVerify(companyCode, sysUser, moneyType, moneyArray, operaterCode, operaterName,uniqueCode,notes,"1");
		entity.setInc("1");
		chinapnrLogManager.saveJfiChinapnrLog(entity);
		return moneyArray[0];
	}
	
	/**
	 * 畅捷通数据进存折
	 * @throws Exception 
	 */
	@Override
	public BigDecimal saveJfiPayDataVerifyByChanjet(final String companyCode, final SysUser sysUser, final BigDecimal totalMoney,
	        String operaterCode, final String operaterName, final String uniqueCode, JfiChanjetLog jfiChanjetLog) throws AppException{
		
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = totalMoney;
		
		Integer[] moneyType = new Integer[1];
		moneyType[0] = 90;//90：畅捷支付费用
		
		String[] notes = new String[1];
		notes[0] = "畅捷支付";
		
		this.saveJfiPayDataVerify(companyCode, sysUser, moneyType, moneyArray, operaterCode, operaterName,uniqueCode,notes,"1");
		jfiChanjetLog.setInc("1");
		jfiChanjetLogManager.saveJfiChanjetLog(jfiChanjetLog);
		return moneyArray[0];
	}

	public void setJfiAlipayLogManager(JfiAlipayLogManager jfiAlipayLogManager) {
		this.jfiAlipayLogManager = jfiAlipayLogManager;
	}

	public void setJfiHiTrustLogManager(JfiHiTrustLogManager jfiHiTrustLogManager) {
		this.jfiHiTrustLogManager = jfiHiTrustLogManager;
	}

	public void setJfiUsCreditCardLogManager(
			JfiUsCreditCardLogManager jfiUsCreditCardLogManager) {
		this.jfiUsCreditCardLogManager = jfiUsCreditCardLogManager;
	}

	public void setJfiTenpayLogManager(JfiTenpayLogManager jfiTenpayLogManager) {
		this.jfiTenpayLogManager = jfiTenpayLogManager;
	}

	public void setJfi99billmsLogManager(Jfi99billmsLogManager jfi99billmsLogManager) {
		this.jfi99billmsLogManager = jfi99billmsLogManager;
	}

	@Override
	public JfiBankbookJournal getBankbookJournal(String userCode,
			String type, String nodes) {
		
		return dao.getBankbookJournal(userCode, type, nodes);
	}
	
	/**
	 * 盛付通进存折
	 * @param companyCode
	 * @param sysUser
	 * @param totalMoney
	 * @param operaterCode
	 * @param operaterName
	 * @param uniqueCode
	 * @param fiChannelLog
	 * @return
	 */
	@Override
	public BigDecimal saveFiPayDataVerifyByChannel(String companyCode,
			SysUser sysUser, BigDecimal totalMoney, String operaterCode,
			String operaterName, String uniqueCode, FiChannelLog fiChannelLog) {
		
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = totalMoney;
		
		Integer[] moneyType = new Integer[1];
		moneyType[0] = 101;
		
		String[] notes = new String[1];
		notes[0] = "盛付通支付";
		
		this.saveJfiPayDataVerify(companyCode, sysUser, moneyType, moneyArray, operaterCode, operaterName, uniqueCode, notes, "1");
		
		fiChannelLog.setInc("1");
		fiChannelLogManager.saveFiChannelLog(fiChannelLog);
		return moneyArray[1];
	}
	
	/**
	 * 易宝支付存折
	 * @param companyCode
	 * @param sysUser
	 * @param totalMoney
	 * @param operaterCode
	 * @param operaterName
	 * @param uniqueCode
	 * @param jfiYeepayLog
	 * @return
	 * @throws AppException
	 */
	@Override
	public BigDecimal saveJfiPayDataVerifyByYeePay(String companyCode,
			SysUser sysUser, BigDecimal totalMoney, String operaterCode,
			String operaterName, String uniqueCode, JfiYeepayLog jfiYeepayLog)
			throws AppException {
		
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = totalMoney;
		
		Integer[] moneyType = new Integer[1];
		moneyType[0] = 140;//140:易宝支付费用
		
		String[] notes = new String[1];
		notes[0] = "易宝支付";
		
		this.saveJfiPayDataVerify(companyCode, sysUser, moneyType, moneyArray, operaterCode, operaterName,uniqueCode,notes,"1");
		jfiYeepayLog.setInc("1");
		jfiYeepayLogManager.saveJfiYeepayLog(jfiYeepayLog);
		return moneyArray[0];
	}
	
	/**
	 * Modify By WuCF 20150923
	 * 融宝支付存折
	 * @param companyCode
	 * @param sysUser
	 * @param totalMoney
	 * @param operaterCode
	 * @param operaterName
	 * @param uniqueCode
	 * @param jfiYeepayLog
	 * @return
	 * @throws AppException
	 */
	@Override
	public BigDecimal saveJfiPayDataVerifyByReapal(String companyCode,
			SysUser sysUser, BigDecimal totalMoney, String operaterCode,
			String operaterName, String uniqueCode, JfiReapalLog jfiReapalLog)
			throws AppException {
		
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = totalMoney;
		
		Integer[] moneyType = new Integer[1];
		moneyType[0] = 150;//140:融宝支付费用  ?????140
		
		String[] notes = new String[1];
		notes[0] = "融宝支付";
		
		this.saveJfiPayDataVerify(companyCode, sysUser, moneyType, moneyArray, operaterCode, operaterName,uniqueCode,notes,"1");
		jfiReapalLog.setInc("1");
		jfiReapalLogManager.saveJfiReapalLog(jfiReapalLog);
		return moneyArray[0];
	}


	@Override
	public BigDecimal saveJfiPayDataVerify(SysUser sysUser, BigDecimal totalMoney, String uniqueCode, JfiPayLog entity,String[] config)
			throws AppException {
		
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = totalMoney;
		
		Integer[] moneyType = new Integer[1];
		moneyType[0] = Integer.parseInt(config[1]);
		
		String[] notes = new String[1];
		notes[0] = config[0];
		
		this.saveJfiPayDataVerify(entity.getCompanyCode(), sysUser, moneyType, moneyArray, sysUser.getUserCode(), sysUser.getUserName(),uniqueCode,notes,"1");
		entity.setInc("1");
		jfiPayLogManager.saveJfiPayLog(entity);
		return moneyArray[0];
	}


	/**
	 * Modify By WuCF 20160816
	 * 保存支付中间表数据
	 * @param jpoBankBookPayList
	 */
	public void createJpoBankBookPayList(String tempId,String checkUserCode) {
		JfiBankbookTemp jfiBankbookTemp = jfiBankbookTempDao.getJfiBankbookTemp(Long.parseLong(tempId));
		
		JpoBankBookPayList bbp = new JpoBankBookPayList();
		bbp.setAmount(jfiBankbookTemp.getMoney());//金额
		bbp.setDzAmt(jfiBankbookTemp.getMoney());//金额
		bbp.setCreateTime(jpoBankBookPayListDao.getDBdate());
		bbp.setDataType("1");//平台来源 1：PC   2：手机
		
		bbp.setNotes(jfiBankbookTemp.getNotes());//摘要
		bbp.setInType(15);//类型
		bbp.setMemberOrderNo(String.valueOf(jfiBankbookTemp.getTempId()));//订单号
		bbp.setMoneyType(jfiBankbookTemp.getMoneyType());//资金类别
		bbp.setMoneyType1(jfiBankbookTemp.getMoneyType());
		bbp.setCheckUserCode(checkUserCode);//审核者
		bbp.setUserCode(jfiBankbookTemp.getSysUser().getUserCode());//
		bbp.setUserSPcode(jfiBankbookTemp.getSysUser().getUserCode());//
		
		jpoBankBookPayListDao.saveObject(bbp);
	}
}
