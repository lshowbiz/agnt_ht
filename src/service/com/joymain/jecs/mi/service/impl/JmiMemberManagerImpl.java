
package com.joymain.jecs.mi.service.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindException;

import com.ecap.activemq.EcRegisterSender;
import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.am.model.JamMsnDetail;
import com.joymain.jecs.am.model.JamMsnType;
import com.joymain.jecs.am.service.JamMsnDetailManager;
import com.joymain.jecs.am.service.JamMsnTypeManager;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.model.JbdBonusBalance;
import com.joymain.jecs.bd.model.JbdSummaryList;
import com.joymain.jecs.bd.model.JbdTravelPoint;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.bd.service.JbdBonusBalanceManager;
import com.joymain.jecs.bd.service.JbdSummaryListManager;
import com.joymain.jecs.bd.service.JbdTravelPointManager;
import com.joymain.jecs.bd.service.JbdUserCardListManager;
import com.joymain.jecs.fi.dao.FiBankbookJournalDao;
import com.joymain.jecs.fi.dao.FiBcoinBalanceDao;
import com.joymain.jecs.fi.dao.FiBcoinJournalDao;
import com.joymain.jecs.fi.dao.FiCcoinBalanceDao;
import com.joymain.jecs.fi.dao.FiCcoinJournalDao;
import com.joymain.jecs.fi.dao.FiProductPointBalanceDao;
import com.joymain.jecs.fi.dao.JfiBankbookJournalDao;
import com.joymain.jecs.fi.model.FiBankbookBalance;
import com.joymain.jecs.fi.model.FiBankbookJournal;
import com.joymain.jecs.fi.model.FiBcoinBalance;
import com.joymain.jecs.fi.model.FiBcoinJournal;
import com.joymain.jecs.fi.model.FiCcoinBalance;
import com.joymain.jecs.fi.model.FiCcoinJournal;
import com.joymain.jecs.fi.model.FiCommonAddr;
import com.joymain.jecs.fi.model.FiFundbookBalance;
import com.joymain.jecs.fi.model.FiLovecoinBalance;
import com.joymain.jecs.fi.model.FiProductPointBalance;
import com.joymain.jecs.fi.model.JfiBankbookBalance;
import com.joymain.jecs.fi.model.JfiBankbookJournal;
import com.joymain.jecs.fi.service.FiBankbookBalanceManager;
import com.joymain.jecs.fi.service.FiCommonAddrManager;
import com.joymain.jecs.fi.service.FiFundbookBalanceManager;
import com.joymain.jecs.fi.service.FiLovecoinBalanceManager;
import com.joymain.jecs.fi.service.JfiBankbookBalanceManager;
import com.joymain.jecs.fi.service.JfiDepositBalanceManager;
import com.joymain.jecs.fi.service.JfiPayBankManager;
import com.joymain.jecs.mi.dao.JmiLinkRefDao;
import com.joymain.jecs.mi.dao.JmiMemberDao;
import com.joymain.jecs.mi.dao.JmiMemberLogDao;
import com.joymain.jecs.mi.dao.JmiRecommendRefDao;
import com.joymain.jecs.mi.model.JmiAddrBook;
import com.joymain.jecs.mi.model.JmiDealList;
import com.joymain.jecs.mi.model.JmiLevelNote;
import com.joymain.jecs.mi.model.JmiLinkRef;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiMemberForGHB;
import com.joymain.jecs.mi.model.JmiMemberLog;
import com.joymain.jecs.mi.model.JmiMemberUpgradeNote;
import com.joymain.jecs.mi.model.JmiRecommendRef;
import com.joymain.jecs.mi.model.JmiStateLog;
import com.joymain.jecs.mi.model.JmiSubStore;
import com.joymain.jecs.mi.model.JmiYdSendList;
import com.joymain.jecs.mi.service.JmiAddrBookManager;
import com.joymain.jecs.mi.service.JmiBlacklistManager;
import com.joymain.jecs.mi.service.JmiDealListManager;
import com.joymain.jecs.mi.service.JmiLevelNoteManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.service.JmiMemberUpgradeNoteManager;
import com.joymain.jecs.mi.service.JmiStateLogManager;
import com.joymain.jecs.mi.service.JmiSubStoreManager;
import com.joymain.jecs.mi.service.JmiYdSendListManager;
import com.joymain.jecs.pd.model.RspEntity;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.model.SysUserRole;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.sys.service.SysRoleManager;
import com.joymain.jecs.sys.service.SysUserRoleManager;
import com.joymain.jecs.util.LocaleUtil;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.json.InterfaceJsonUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
public class JmiMemberManagerImpl extends BaseManager implements JmiMemberManager {
    private JmiMemberDao dao;
    private SysIdManager sysIdManager;
    private SysRoleManager sysRoleManager;
    private JmiLinkRefDao jmiLinkRefDao;
    private JmiRecommendRefDao jmiRecommendRefDao;
    private JfiBankbookBalanceManager jfiBankbookBalanceManager;
    private SysUserRoleManager sysUserRoleManager;
    private JmiMemberUpgradeNoteManager jmiMemberUpgradeNoteManager;
    private AlCompanyManager alCompanyManager;
    private JbdBonusBalanceManager jbdBonusBalanceManager;
    private JbdUserCardListManager jbdUserCardListManager;
    private JpoMemberOrderManager jpoMemberOrderManager;
    private BdPeriodManager bdPeriodManager;
    private JamMsnDetailManager jamMsnDetailManager;
    private JamMsnTypeManager jamMsnTypeManager;
    private JfiPayBankManager jfiPayBankManager;
    private EcRegisterSender ecRegisterSender;
    private JmiSubStoreManager jmiSubStoreManager;
    private JmiBlacklistManager jmiBlacklistManager;
    private JbdTravelPointManager jbdTravelPointManager;
    private JbdSummaryListManager jbdSummaryListManager;
    private FiBcoinBalanceDao fiBcoinBalanceDao;
    private FiCcoinBalanceDao fiCcoinBalanceDao;
    private AlCityManager alCityManager;
    private FiBankbookBalanceManager fiBankbookBalanceManager;
    private JfiBankbookJournalDao jfiBankbookJournalDao;
    private FiBcoinJournalDao fiBcoinJournalDao;
    private FiCcoinJournalDao fiCcoinJournalDao;
    private FiBankbookJournalDao fiBankbookJournalDao;
    private JmiLevelNoteManager jmiLevelNoteManager;
    private JdbcTemplate jdbcTemplate;

    private FiFundbookBalanceManager fiFundbookBalanceManager;
    private FiLovecoinBalanceManager fiLovecoinBalanceManager;

    private JfiDepositBalanceManager jfiDepositBalanceManager;
    private JmiMemberLogDao jmiMemberLogDao;//日志记录
    private FiProductPointBalanceDao fiProductPointBalanceDao;
    private FiCommonAddrManager fiCommonAddrManager;
    private JmiAddrBookManager jmiAddrBookManager;
    private JmiYdSendListManager jmiYdSendListManager;
    
    public void setJmiYdSendListManager(JmiYdSendListManager jmiYdSendListManager) {
		this.jmiYdSendListManager = jmiYdSendListManager;
	}
	public void setFiCommonAddrManager(FiCommonAddrManager fiCommonAddrManager) {
		this.fiCommonAddrManager = fiCommonAddrManager;
	}

	public void setJmiAddrBookManager(JmiAddrBookManager jmiAddrBookManager) {
		this.jmiAddrBookManager = jmiAddrBookManager;
	}
    
	public void setFiProductPointBalanceDao(
			FiProductPointBalanceDao fiProductPointBalanceDao) {
		this.fiProductPointBalanceDao = fiProductPointBalanceDao;
	}
	public void setJmiMemberLogDao(JmiMemberLogDao jmiMemberLogDao) {
		this.jmiMemberLogDao = jmiMemberLogDao;
	}
	public void setJfiDepositBalanceManager(
			JfiDepositBalanceManager jfiDepositBalanceManager) {
		this.jfiDepositBalanceManager = jfiDepositBalanceManager;
	}
	private JmiStateLogManager jmiStateLogManager;
	public void setJmiStateLogManager(JmiStateLogManager jmiStateLogManager) {
		this.jmiStateLogManager = jmiStateLogManager;
	}
	private JmiDealListManager jmiDealListManager;
    
    public void setJmiDealListManager(JmiDealListManager jmiDealListManager) {
		this.jmiDealListManager = jmiDealListManager;
	}
	public void setFiFundbookBalanceManager(
			FiFundbookBalanceManager fiFundbookBalanceManager) {
		this.fiFundbookBalanceManager = fiFundbookBalanceManager;
	}
	public void setFiLovecoinBalanceManager(
			FiLovecoinBalanceManager fiLovecoinBalanceManager) {
		this.fiLovecoinBalanceManager = fiLovecoinBalanceManager;
	}
    
    
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

	public void setJmiLevelNoteManager(JmiLevelNoteManager jmiLevelNoteManager) {
		this.jmiLevelNoteManager = jmiLevelNoteManager;
	}

	public void setFiBankbookJournalDao(FiBankbookJournalDao fiBankbookJournalDao) {
		this.fiBankbookJournalDao = fiBankbookJournalDao;
	}

	public void setFiBcoinJournalDao(FiBcoinJournalDao fiBcoinJournalDao) {
		this.fiBcoinJournalDao = fiBcoinJournalDao;
	}

	public void setFiCcoinJournalDao(FiCcoinJournalDao fiCcoinJournalDao) {
		this.fiCcoinJournalDao = fiCcoinJournalDao;
	}

	public void setJfiBankbookJournalDao(JfiBankbookJournalDao jfiBankbookJournalDao) {
		this.jfiBankbookJournalDao = jfiBankbookJournalDao;
	}

	public void setFiBankbookBalanceManager(
			FiBankbookBalanceManager fiBankbookBalanceManager) {
		this.fiBankbookBalanceManager = fiBankbookBalanceManager;
	}

	public void setAlCityManager(AlCityManager alCityManager) {
		this.alCityManager = alCityManager;
	}

	public void setJbdSummaryListManager(JbdSummaryListManager jbdSummaryListManager) {
		this.jbdSummaryListManager = jbdSummaryListManager;
	}

	public void setJmiBlacklistManager(JmiBlacklistManager jmiBlacklistManager) {
		this.jmiBlacklistManager = jmiBlacklistManager;
	}

	public void setEcRegisterSender(EcRegisterSender ecRegisterSender) {
		this.ecRegisterSender = ecRegisterSender;
	}

	public void setJamMsnTypeManager(JamMsnTypeManager jamMsnTypeManager) {
		this.jamMsnTypeManager = jamMsnTypeManager;
	}

	public void setJamMsnDetailManager(JamMsnDetailManager jamMsnDetailManager) {
		this.jamMsnDetailManager = jamMsnDetailManager;
	}

	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	public void setJbdUserCardListManager(
			JbdUserCardListManager jbdUserCardListManager) {
		this.jbdUserCardListManager = jbdUserCardListManager;
	}

	public void setJbdBonusBalanceManager(
			JbdBonusBalanceManager jbdBonusBalanceManager) {
		this.jbdBonusBalanceManager = jbdBonusBalanceManager;
	}

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public void setJmiMemberUpgradeNoteManager(
			JmiMemberUpgradeNoteManager jmiMemberUpgradeNoteManager) {
		this.jmiMemberUpgradeNoteManager = jmiMemberUpgradeNoteManager;
	}

	public void setSysUserRoleManager(SysUserRoleManager sysUserRoleManager) {
		this.sysUserRoleManager = sysUserRoleManager;
	}

	public void setJfiBankbookBalanceManager(
			JfiBankbookBalanceManager jfiBankbookBalanceManager) {
		this.jfiBankbookBalanceManager = jfiBankbookBalanceManager;
	}

	public void setJmiLinkRefDao(JmiLinkRefDao jmiLinkRefDao) {
		this.jmiLinkRefDao = jmiLinkRefDao;
	}

	public void setJmiRecommendRefDao(JmiRecommendRefDao jmiRecommendRefDao) {
		this.jmiRecommendRefDao = jmiRecommendRefDao;
	}

	public void setSysRoleManager(SysRoleManager sysRoleManager) {
		this.sysRoleManager = sysRoleManager;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJmiMemberDao(JmiMemberDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiMemberManager#getJmiMembers(com.joymain.jecs.mi.model.JmiMember)
     */
    public List getJmiMembers(final JmiMember jmiMember) {
        return dao.getJmiMembers(jmiMember);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiMemberManager#getJmiMember(String userCode)
     */
    public JmiMember getJmiMember(final String userCode) {
    	if(StringUtils.isEmpty(userCode)){
    		return null;
    	}
        return dao.getJmiMember(new String(userCode));
    }
    public List findJmiMemberById(String userCode){
    	if(StringUtils.isEmpty(userCode)){
    		return null;
    	}
        return dao.findJmiMemberById(new String(userCode));
    }
    /**
     * @see com.joymain.jecs.mi.service.JmiMemberManager#saveJmiMember(JmiMember jmiMember)
     */
    public void saveJmiMember(JmiMember jmiMember) {
    	
    	if("US".equals(jmiMember.getCompanyCode()) && !StringUtil.isEmpty(jmiMember.getCity())){
    		AlCity alCity=alCityManager.getAlCity(jmiMember.getCity());
    		jmiMember.setTownAddr(alCity.getCityName());
    	}
    	
        dao.saveJmiMember(jmiMember);
    }
    
    public void saveJmiMember(JmiMember jmiMember,JmiMemberLog JmiMemberLog) {
    	
    	if("US".equals(jmiMember.getCompanyCode()) && !StringUtil.isEmpty(jmiMember.getCity())){
    		AlCity alCity=alCityManager.getAlCity(jmiMember.getCity());
    		jmiMember.setTownAddr(alCity.getCityName());
    	}
    	
    	jmiMemberLogDao.saveJmiMemberLog(JmiMemberLog);
        dao.saveJmiMember(jmiMember);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiMemberManager#removeJmiMember(String userCode)
     */
    public void removeJmiMember(final String userCode) {
    	
    	JmiMember jmiMember = dao.getJmiMember(userCode);
    	
    	if(jmiMember.getMemberLevel()==1){
			throw new AppException("member.remove.0");
    	}
    	
    	CommonRecord crm = new CommonRecord();
    	
    	crm.addField("linkNo", jmiMember.getUserCode());
    	List miLinkRefs=dao.getJmiMembersByCrm(crm);
    	
    	crm = new CommonRecord();
    	crm.addField("recommendNo", jmiMember.getUserCode());
    	List miRecommendRefs=dao.getJmiMembersByCrm(crm);
    	
    	
    	JfiBankbookJournal jfiBankbookJournal=jfiBankbookJournalDao.getLastJfiBankbookJournal(userCode);
    	if(jfiBankbookJournal!=null){
    		throw new AppException("会员存折存在记录");
    	}
    	FiBcoinJournal fiBcoinJournal=fiBcoinJournalDao.getLastFiBcoinJournal(userCode);
    	if(fiBcoinJournal!=null){
    		throw new AppException("B分存在记录");
    	}

    	FiCcoinJournal fiCcoinJournal=fiCcoinJournalDao.getLastFiCcoinJournal(userCode);
    	if(fiCcoinJournal!=null){
    		throw new AppException("C分存在记录");
    	}
    	FiBankbookJournal fiBankbookJournal= fiBankbookJournalDao.getLastFiBankbookJournal(userCode, "1");
    	if(fiBankbookJournal!=null){
    		throw new AppException("发展基金存在记录");
    	}
    	
    	
    	JfiBankbookBalance jfiBankbookBalance=jfiBankbookBalanceManager.getJfiBankbookBalance(userCode);
    	if(jfiBankbookBalance.getBalance().compareTo(new BigDecimal(0))==1){
			throw new AppException("jfiBankbookBalance.greatthan.0");
    	}else{
    		
    	}
    	JpoMemberOrder jpoMemberOrder=new JpoMemberOrder();
    	jpoMemberOrder.setSysUser(jmiMember.getSysUser());
    	List<JpoMemberOrder> jpoMemberOrders=jpoMemberOrderManager.getJpoMemberOrdersByMiMember(jpoMemberOrder);
    	
    	if(jpoMemberOrders.size()>0){
			throw new AppException("miMember.deleteFail");
    	}
    	
//    	for (JpoMemberOrder order : jpoMemberOrders) {
//			if("2".equals(order.getStatus())){
//				throw new AppException("miMember.deleteFail");
//			}else{
//				jpoMemberOrderManager.removeJpoMemberOrder(order.getMoId().toString());
//			}
//		}
    	
//		Set poMemberOrders = miMember.getPoMemberOrder();
//		Iterator its = poMemberOrders.iterator();
//		while(its.hasNext()){
//			PoMemberOrder poMemberOrder = (PoMemberOrder)its.next();
//			
//			if(fiCreditCardLog == null && "1".equals(poMemberOrder.getStatus()) && "1".equals(poMemberOrder.getCheckStatus())){
//				poMemberOrderDao.removePoMemberOrderAndBak(poMemberOrder,sysUser);
//			}else{
//				throw new AppException("miMember.deleteFail");
//			}
//		}
		if (miLinkRefs.size() == 0 && miRecommendRefs.size() == 0) {
			dao.removeJmiMember(userCode);
			jbdBonusBalanceManager.removeJbdBonusBalance(userCode);

			//插入每日计算表
			Date curDate=new Date();
			JbdSummaryList jbdSummaryList=new JbdSummaryList();
			jbdSummaryList.setUserCode(jmiMember.getUserCode());
			jbdSummaryList.setCardType(jmiMember.getCardType());
			jbdSummaryList.setInType(11);
			jbdSummaryList.setCreateTime(curDate);
			jbdSummaryList.setNewLinkNo(jmiMember.getLinkNo());
			jbdSummaryList.setNewRecommendNo(jmiMember.getRecommendNo());
			jbdSummaryList.setNewCompanyCode(jmiMember.getCompanyCode());
			jbdSummaryList.setUserCreateTime(curDate);
			jbdSummaryList.setWweek(bdPeriodManager.getBdPeriodByTimeFormated(curDate, null));
			jbdSummaryListManager.saveJbdSummaryList(jbdSummaryList);
			JmiDealList jmiDealList=new JmiDealList();
			jmiDealList.setCreateTime(curDate);
			jmiDealList.setInType(2);
			jmiDealList.setLinkNo(jmiMember.getRecommendNo());
			jmiDealList.setUserCode(userCode);
			jmiDealListManager.saveJmiDealList(jmiDealList);
			//
		} else {
			throw new AppException("miLinkRef.hasMember");
		}
    	
    	
       // dao.removeJmiMember(new String(userCode));
    }
    //added for getJmiMembersByCrm
    public List getJmiMembersByCrm(CommonRecord crm, Pager pager){
	return dao.getJmiMembersByCrm(crm,pager);
    }

	public List getLinkRefbyLinkNoOrderByCreateTime(String linkNo) {
		return dao.getLinkRefbyLinkNoOrderByCreateTime(linkNo);
	}

	public String saveNewJmiMember(JmiMember jmiMember,SysUser defSysUser,HttpServletRequest request) {
		
		
		//推荐人不存在
		 JmiRecommendRef miRecommendRef = jmiRecommendRefDao.getJmiRecommendRef(jmiMember.getJmiRecommendRef().getRecommendJmiMember().getUserCode());
		if (miRecommendRef == null) {
			throw new AppException("miRecommendRef.isNotFound");
		}


		if("M".equals(defSysUser.getUserType())){
			JmiRecommendRef defRecommendRef = jmiRecommendRefDao.getJmiRecommendRef(defSysUser.getUserCode());
			//判断推荐人是否在当前会员下
			String rdefIndex=defRecommendRef.getTreeIndex();
			String rIndex=miRecommendRef.getTreeIndex();
			if(rIndex.length()<rdefIndex.length()|| !rdefIndex.equals(StringUtil.getLeft(rIndex, rdefIndex.length()))){
				throw new AppException("miRecommendRef.isNotFound");
			}
		}

		// 判断接点人是否存在
		JmiLinkRef miLinkRef =jmiLinkRefDao.getJmiLinkRef(jmiMember.getJmiLinkRef().getLinkJmiMember().getUserCode());
		if (miLinkRef == null) {
			throw new AppException("miLinkRef.isNotFound");
		}

		// 接点人不是推荐人的下线
		String rTreeIndex = miRecommendRef.getJmiMember().getJmiLinkRef().getTreeIndex();
		String lTreeIndex = miLinkRef.getTreeIndex();
		if (lTreeIndex.length() < rTreeIndex.length() || !rTreeIndex.equals(StringUtil.getLeft(lTreeIndex, rTreeIndex.length()))) {
			throw new AppException("miLinkRefMiRecommendRef.isNotEquals");

		}


/*		CommonRecord crm = new CommonRecord();
		
		int maxLink=6;
		crm.addField("linkNo", jmiMember.getJmiLinkRef().getLinkJmiMember().getUserCode());
		List miLinkRefs = jmiLinkRefDao.getMiLinkRefManagersByTree(crm);
		if(miLinkRef.getNum().compareTo(new BigDecimal(0))!=0){
			maxLink=miLinkRef.getNum().intValue()+2;
		}
		if(miLinkRefs.size()<6){
			maxLink=6;
		}
		if (miLinkRefs.size() >= maxLink) {// 接点体系最多只有6个下属部门
			throw new AppException("miLinkRef.isFull");
		}*/

		String newMemberNo=sysIdManager.buildIdStr("member_no");
		newMemberNo="AJ"+newMemberNo;
		JmiMember existMember=dao.getJmiMember(newMemberNo);
		if(existMember!=null){
			throw new AppException("member.exist");
		}
		jmiMember.setUserCode(newMemberNo);

		
		// 接点体系入库
		JmiLinkRef miNewLinkRef = jmiLinkRefDao.getNewMiLinkRefManagersByLinkNo(miLinkRef, 9999);
		if (miNewLinkRef == null) {
			throw new AppException("miLinkRef.isFull");
		}
		miNewLinkRef.setJmiMember(jmiMember);
		miNewLinkRef.setUserCode(newMemberNo);
		jmiMember.setJmiLinkRef(miNewLinkRef);
		jmiMember.setLinkNo(miNewLinkRef.getLinkJmiMember().getUserCode());
		miNewLinkRef.setDepartmentPv(new BigDecimal(0));
		miNewLinkRef.setNum(new BigDecimal(0));
		// 推荐体系入库
		JmiRecommendRef miNewRecommendRef = jmiRecommendRefDao.getNewMiLinkRefManagersByRecommendNo(miRecommendRef);
		miNewRecommendRef.setJmiMember(jmiMember);
		miNewRecommendRef.setUserCode(newMemberNo);
		jmiMember.setJmiRecommendRef(miNewRecommendRef);
		jmiMember.setRecommendNo(miNewRecommendRef.getRecommendJmiMember().getUserCode());

		Date curDate=dao.getDsDate();
    	jmiMember.setCardType("0");
		if(!StringUtil.isEmpty(defSysUser.getUserCode())){
	    	jmiMember.setCreateNo(defSysUser.getUserCode());
    	}else{
	    	jmiMember.setCreateNo(newMemberNo);
    	}
    	jmiMember.setCreateTime(curDate);
    	jmiMember.setActiveTime(curDate);
    	jmiMember.setActive("0");
    	jmiMember.getSysUser().setJmiMember(jmiMember);

    	
		//生成会员账号
		jmiMember.getSysUser().setUserCode(newMemberNo);
		jmiMember.getSysUser().setUserType("M");
		jmiMember.getSysUser().setUserCode(jmiMember.getUserCode());
    	jmiMember.getSysUser().setFirstName(jmiMember.getFirstName());
    	jmiMember.getSysUser().setLastName(jmiMember.getLastName());
    	jmiMember.getSysUser().setCompanyCode(defSysUser.getCompanyCode());

    	jmiMember.setCompanyCode(defSysUser.getCompanyCode());

    	jmiMember.setMemberLevel(0);
    	jmiMember.setNotFirst(0);
    	jmiMember.setGradeType(0);
    	//
    	jmiMember.setIsstore("0");
    	jmiMember.setIsCloudshop("0");

		jmiMember.setMemberUserType("1");
    	this.getSetUserName(jmiMember);
    	
        //设置银行户名 暂时中国
    	if(!"JP".equals(jmiMember.getCompanyCode())){
        	jmiMember.setBankbook(jmiMember.getSysUser().getUserName());
    	}
    	jmiMember.getSysUser().setState("1");
    	jmiMember.getSysUser().setCompanyCode(jmiMember.getCompanyCode()); 
    	//默认语言跟分公司
    	jmiMember.getSysUser().setDefCharacterCoding(defSysUser.getDefCharacterCoding());

    	//
    	jmiMember.setFreezeStatus(0);
    	jmiMember.setBeforeFreezeStatus(0);
    	//
    	jmiMember.setCustomerLevel("0");
    	jmiMember.setIsCloudshop("0");
    	//加入电子存折
    	JfiBankbookBalance jfiBankbookBalance=new JfiBankbookBalance();
    	jfiBankbookBalance.setUserCode(newMemberNo);
    	jfiBankbookBalance.setBalance(new BigDecimal(0));
    	jfiBankbookBalanceManager.saveJfiBankbookBalance(jfiBankbookBalance);
    	

    	//插入发展基金
    	FiBankbookBalance fiBankbookBalance=new FiBankbookBalance();
    	fiBankbookBalance.setBalance(new BigDecimal(0));
    	fiBankbookBalance.setBankbookType("1");
    	fiBankbookBalance.setUserCode(newMemberNo);
    	fiBankbookBalanceManager.saveFiBankbookBalance(fiBankbookBalance);
    	
    	//插入奖金累加表
    	JbdBonusBalance jbdBonusBalance=new JbdBonusBalance();
    	jbdBonusBalance.setUserCode(newMemberNo);
    	jbdBonusBalance.setBonusBalance(new BigDecimal(0));
    	jbdBonusBalance.setAssignedBonus(new BigDecimal(0));
    	jbdBonusBalance.setFlag("0");
    	if("TW".equals(jmiMember.getCompanyCode())){//台湾
    		jbdBonusBalance.setStatus("1");
    	}else{
    		jbdBonusBalance.setStatus("0");
    	}
    	jbdBonusBalanceManager.saveJbdBonusBalance(jbdBonusBalance);
		//设置会员角色
		String memberRoleId = Constants.sysConfigMap.get(jmiMember.getCompanyCode()).get("jocs.member.role.0");
		SysRole memberSysRole=sysRoleManager.getSysRoleByCode(memberRoleId);
		SysUserRole sysUserRole=new SysUserRole();
		sysUserRole.setRoleId(memberSysRole.getRoleId());
		sysUserRole.setUserCode(jmiMember.getUserCode());

		sysUserRoleManager.saveSysUserRole(sysUserRole);
		
		//发送
		if("CN".equals(defSysUser.getCompanyCode())){
			try {
				HashMap memberMap=new HashMap();
				memberMap.put("userCode", jmiMember.getUserCode().substring(2, jmiMember.getUserCode().length()));
				memberMap.put("email", jmiMember.getEmail());
				memberMap.put("realname", jmiMember.getMiUserName());
				memberMap.put("password", jmiMember.getSysUser().getPassword());
				memberMap.put("mobile", jmiMember.getMobiletele());
				memberMap.put("phone", jmiMember.getPhone());
				memberMap.put("countryCode", jmiMember.getCountryCode());
				memberMap.put("provice", jmiMember.getProvince());
				memberMap.put("city", jmiMember.getCity());
				memberMap.put("district", jmiMember.getDistrict());
				memberMap.put("companyCode", jmiMember.getCompanyCode());
				memberMap.put("address", jmiMember.getAddress());
				memberMap.put("postcode", jmiMember.getPostalcode());
				memberMap.put("papertype", jmiMember.getPapertype());
				memberMap.put("papernumber", jmiMember.getPapernumber());
				memberMap.put("regIp", RequestUtil.getIpAddr(request));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				memberMap.put("regDate", sdf.format(jmiMember.getCreateTime()));
				ecRegisterSender.send(memberMap);
			} catch (Exception e) {
				jmiMember.getSysUser().setSendStatus("1");
				e.printStackTrace();
			}
		//发送 PCN
			
			//dao.sendPcn(jmiMember, "NewAccount","","",null,"");
			//
			
			
		}
		
		
		//团队配对membertype
		this.getTeamType(jmiMember);
/*		List teams=dao.getJmiTeamType();
		
		for (int i = 0; i < teams.size(); i++) {
			Map map=(Map) teams.get(i);
			String user_code=map.get("user_code").toString();
			String member_type=map.get("member_type").toString();
			
			JmiRecommendRef topMemberRecommendRef=jmiRecommendRefDao.getJmiRecommendRef(user_code);
			JmiRecommendRef registerNoRecommendRef=jmiRecommendRefDao.getJmiRecommendRef(jmiMember.getRecommendNo());
			
			if(topMemberRecommendRef!=null){
				
				String topIndex=topMemberRecommendRef.getTreeIndex();
				String registerNoRecommendRefIndex=registerNoRecommendRef.getTreeIndex();

				String rmemberIndexTmp = StringUtil.getLeft(registerNoRecommendRefIndex, topIndex.length());
				
				if(registerNoRecommendRefIndex.length()>=topIndex.length() && topIndex.equals(rmemberIndexTmp) ){
					jmiMember.setMemberType(member_type);
					break;
				}
			}
		}*/
		
		
		
		//
		
		
		
	/*	
		//注册为CN17500332 判断是三青团队

		JmiRecommendRef team9RecommendRef = jmiRecommendRefDao.getJmiRecommendRef("CN40449939");//指定团队
		JmiRecommendRef registerNoRecommendRef = jmiRecommendRefDao.getJmiRecommendRef(jmiMember.getRecommendNo());//注册人的推荐人
		if(team9RecommendRef!=null){

			String team9Index=team9RecommendRef.getTreeIndex();
			String registerNoRecommendRefIndex=registerNoRecommendRef.getTreeIndex();

			String rmemberIndexTmp = StringUtil.getLeft(registerNoRecommendRefIndex, team9Index.length());
			
			if(registerNoRecommendRefIndex.length()>=team9Index.length() && team9Index.equals(rmemberIndexTmp) ){
				jmiMember.setMemberType("9");
			}
		}
		//刘建强CN18645446的 团队标识  用YN
		
		JmiRecommendRef team10RecommendRef = jmiRecommendRefDao.getJmiRecommendRef("CN18645446");//指定团队
		JmiRecommendRef registerNo10RecommendRef = jmiRecommendRefDao.getJmiRecommendRef(jmiMember.getRecommendNo());//注册人的推荐人
		if(team10RecommendRef!=null){

			String team10Index=team10RecommendRef.getTreeIndex();
			String registerNo10RecommendRefIndex=registerNo10RecommendRef.getTreeIndex();

			String rmemberIndexTmp10 = StringUtil.getLeft(registerNo10RecommendRefIndex, team10Index.length());
			
			if(registerNo10RecommendRefIndex.length()>=team10Index.length() && team10Index.equals(rmemberIndexTmp10) ){
				jmiMember.setMemberType("10");
			}
		}*/
		
		
		//
		
		this.saveJmiMember(jmiMember);
		
		if("CN".equals(defSysUser.getCompanyCode())){
			//发货时默认发短信设置 
			JamMsnType jamMsnType=jamMsnTypeManager.getJamMsnType("125986");
			JamMsnDetail jamMsnDetail=new JamMsnDetail();
			jamMsnDetail.setJamMsnType(jamMsnType);
			jamMsnDetail.setStatus("1");
			jamMsnDetail.setUserCode(newMemberNo);
			jamMsnDetailManager.saveJamMsnDetail(jamMsnDetail);
			//
			
			//活动旅游积分 
			JbdTravelPoint jbdTravelPoint=new JbdTravelPoint();
			jbdTravelPoint.setUserCode(newMemberNo);
			jbdTravelPoint.setTotal(new BigDecimal(0));
			jbdTravelPoint.setPassStar(0);
			jbdTravelPointManager.saveJbdTravelPoint(jbdTravelPoint);
			//
			
		}
		//插入每日计算表
		JbdSummaryList jbdSummaryList=new JbdSummaryList();
		jbdSummaryList.setUserCode(newMemberNo);
		jbdSummaryList.setCardType("0");
		jbdSummaryList.setInType(1);
		jbdSummaryList.setCreateTime(curDate);
		jbdSummaryList.setNewLinkNo(jmiMember.getLinkNo());
		jbdSummaryList.setNewRecommendNo(jmiMember.getRecommendNo());
		jbdSummaryList.setNewCompanyCode(defSysUser.getCompanyCode());
		jbdSummaryList.setUserCreateTime(curDate);
		jbdSummaryList.setWweek(bdPeriodManager.getBdPeriodByTimeFormated(curDate, null));
		jbdSummaryListManager.saveJbdSummaryList(jbdSummaryList);
		//
		
		
		//B C 分
		FiBcoinBalance fiBcoinBalance=new FiBcoinBalance();
		fiBcoinBalance.setUserCode(newMemberNo);
		fiBcoinBalance.setBalance(new BigDecimal(0));
		fiBcoinBalanceDao.saveFiBcoinBalance(fiBcoinBalance);
		
		FiCcoinBalance fiCcoinBalance=new FiCcoinBalance();
		fiCcoinBalance.setUserCode(newMemberNo);
		fiCcoinBalance.setBalance(new BigDecimal(0));
		fiCcoinBalanceDao.saveFiCcoinBalance(fiCcoinBalance);

		
		FiFundbookBalance fiFundbookBalance=new FiFundbookBalance();
		fiFundbookBalance.setBalance(new BigDecimal(0));
		fiFundbookBalance.setBankbookType("1");
		fiFundbookBalance.setUserCode(newMemberNo);
		fiFundbookBalanceManager.saveFiFundbookBalance(fiFundbookBalance);
		

		FiFundbookBalance fiFundbookBalance1=new FiFundbookBalance();
		fiFundbookBalance1.setBalance(new BigDecimal(0));
		fiFundbookBalance1.setBankbookType("2");
		fiFundbookBalance1.setUserCode(newMemberNo);
		fiFundbookBalanceManager.saveFiFundbookBalance(fiFundbookBalance1);
		
		FiLovecoinBalance fiLovecoinBalance=new FiLovecoinBalance();
		fiLovecoinBalance.setBalance(new BigDecimal(0));
		fiLovecoinBalance.setUserCode(newMemberNo);
		fiLovecoinBalanceManager.saveFiLovecoinBalance(fiLovecoinBalance);
		
		

		FiProductPointBalance fiProductPointBalance=new FiProductPointBalance();
		fiProductPointBalance.setProductPointType("1");
		fiProductPointBalance.setUserCode(newMemberNo);
		fiProductPointBalance.setBalance(new BigDecimal(0));
		fiProductPointBalanceDao.saveFiProductPointBalance(fiProductPointBalance);
		
		JmiDealList jmiDealList=new JmiDealList();
		jmiDealList.setCreateTime(curDate);
		jmiDealList.setInType(1);
		jmiDealList.setLinkNo(jmiMember.getRecommendNo());
		jmiDealList.setUserCode(newMemberNo);
		jmiDealListManager.saveJmiDealList(jmiDealList);
		
		
		//推送给云店的临时表
		JmiYdSendList jmiYdSendList=new JmiYdSendList();
		jmiYdSendList.setCreateTime(curDate);
		jmiYdSendList.setRecommendNo(jmiMember.getRecommendNo());
		jmiYdSendList.setSourceCode("AJ");
		jmiYdSendList.setUserCode(newMemberNo);
		jmiYdSendList.setSendNum(new BigDecimal(0));
		jmiYdSendListManager.saveJmiYdSendList(jmiYdSendList);
		
		/*JfiDepositBalance jfiDepositBalance=new JfiDepositBalance();
		jfiDepositBalance.setBalance(new BigDecimal(0));
		jfiDepositBalance.setUserCode(newMemberNo);
		jfiDepositBalance.setDepositType("1");
		
		jfiDepositBalanceManager.saveJfiDepositBalance(jfiDepositBalance);


		JfiDepositBalance jfiDepositBalance1=new JfiDepositBalance();
		jfiDepositBalance1.setBalance(new BigDecimal(0));
		jfiDepositBalance1.setUserCode(newMemberNo);
		jfiDepositBalance1.setDepositType("2");
		
		jfiDepositBalanceManager.saveJfiDepositBalance(jfiDepositBalance1);*/
		
		/*
		if("2".equals(jmiMember.getMemberUserType())){
			String province="163702";//北京
			String city="17356";
			String district="17361";
			String address="虚拟地址";
			String firstName="虚拟姓";
			String lastName="虚拟名";
			String mobiletele="12345678901";
			
			FiCommonAddr fiCommonAddr=new FiCommonAddr();
			fiCommonAddr.setAddress(address);
			fiCommonAddr.setProvince(province);
			fiCommonAddr.setCity(city);
			fiCommonAddr.setUserCode(newMemberNo);
			fiCommonAddr.setDistrict(district);
			fiCommonAddrManager.saveFiCommonAddr(fiCommonAddr);
			
			JmiAddrBook jmiAddrBook=new JmiAddrBook();
			jmiAddrBook.setAddress(address);
			jmiAddrBook.setCity(city);
			jmiAddrBook.setDistrict(district);
			jmiAddrBook.setProvince(province);
			jmiAddrBook.setFirstName(firstName);
			jmiAddrBook.setLastName(lastName);
			jmiAddrBook.setMobiletele(mobiletele);
			jmiAddrBook.setIsDefault("1");
			jmiAddrBook.setJmiMember(jmiMember);
			jmiAddrBook.setPostalcode("12345");
			jmiAddrBookManager.saveJmiAddrBook(jmiAddrBook);
		}*/
		
		
		return newMemberNo;
	}

	public List getJmiMembersByCrm(CommonRecord crm) {
		return dao.getJmiMembersByCrm(crm);
	}

	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}

	public Map callProcCheckRef(String checkType) {
		return dao.callProcCheckRef(checkType);
	}

	public boolean getCheckMiMemberIdNoByMiMember(JmiMember miMember) {
		return dao.getCheckMiMemberIdNoByMiMember(miMember);
	}
	/**
	 * 
	 * type 1.会员窗新建会员 2.公司编辑会员 3.会员修改自己资料 4.快速注册
	 */
	public boolean getCheckMiMember(HttpServletRequest request, JmiMember jmiMember,BindException errors,String characterCoding,String type){
		 	boolean isNotPass = false;

	    	SysUser defSysUser = (SysUser) request.getSession().getAttribute(Constants.SESSION_CURRENT_USER);
	    	
		 	String companyCode=jmiMember.getCompanyCode();
		 	if(!StringUtil.isEmpty(defSysUser.getUserCode())){
		 		companyCode=defSysUser.getCompanyCode();
		 	}
		 	
		 	
		 	if("1".equals(type)||"2".equals(type)||"4".equals(type)){
		 		
		 		if(!"ID".equals(companyCode)){//印尼姓不必填 潘洁 2012-4-16
			    	if (StringUtils.isEmpty(jmiMember.getFirstName())) {
						errors.rejectValue("firstName", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.firstName") }, "");
						isNotPass = true;
			    	}
		 		}
		    	
		    	if (StringUtils.isEmpty(jmiMember.getLastName())) {
					errors.rejectValue("lastName", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.lastName") }, "");
					isNotPass = true;
		    	}
		    	if ("JP".equals(companyCode)) {
		    		if (StringUtils.isEmpty(jmiMember.getFirstNameKana())) {
						errors.rejectValue("firstNameKana", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.firstNameKana") }, "");
						isNotPass = true;
			    	}
			    	if (StringUtils.isEmpty(jmiMember.getLastNameKana())) {
						errors.rejectValue("lastNameKana", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.lastNameKana") }, "");
						isNotPass = true;
			    	}
		    	}
		    	
//		    	if ("TW".equals(companyCode)&&StringUtils.isEmpty(jmiMember.getMiUserName())) {
//					errors.rejectValue("miUserName", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "bdCalculatingSubDetail.name") }, "");
//					isNotPass = true;
//		    	}
		    	//俄罗斯代办处
//		    	if ("RU".equals(companyCode)) {
//			    	if (StringUtils.isEmpty(jmiMember.getPbNo())) {
//						errors.rejectValue("pbNo", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "aiAgent.pbNo") }, "");
//						isNotPass = true;
//			    	}else{
//			    		JfiPayBank jfiPayBank=jfiPayBankManager.getJfiPayBank(jmiMember.getPbNo());
//			    		if(jfiPayBank==null||!"RU".equals(jfiPayBank.getCompanyCode())){
//							errors.rejectValue("pbNo", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "aiAgent.pbNo") }, "");
//							isNotPass = true;
//			    		}
//			    	}
//		    	}
		 	}
		 	

		 	if("1".equals(type)||"2".equals(type)||"3".equals(type)||"4".equals(type)){
		    	if(!"JP".equals(companyCode)){
		    		//9.29后财年开始
		    		java.util.Calendar startc=java.util.Calendar.getInstance();
		    		startc.set(2012, 8, 29, 00, 00, 00);
		    		java.util.Date papernumberDate=startc.getTime();
		    		
		    		//判断身份证与姓名一致，同名的时候
		    		if("CN".equals(companyCode) && !StringUtils.isEmpty(jmiMember.getLastName()) &&!StringUtils.isEmpty(jmiMember.getFirstName()) && new Date().after(papernumberDate)
		    				&&!StringUtils.isEmpty(jmiMember.getPapernumber()) && !StringUtil.isEmpty(jmiMember.getJmiLinkRef().getLinkJmiMember().getUserCode()) ){
		    			String papernumberUserCode=dao.getPapernumberUserCode(jmiMember);
		    			if(!StringUtil.isEmpty(papernumberUserCode)){
		    				JmiMember jmiMemberPapernumber=dao.getJmiMember(papernumberUserCode);
		    				String curRegisterUserName=(jmiMember.getFirstName()+jmiMember.getLastName());
		    				if(!curRegisterUserName.equals(jmiMemberPapernumber.getMiUserName())){
								errors.reject("idNo.linkNo.userName.no");
								isNotPass = true;
		    				}
		    			}
		    		}
		    		
		    		//
		    		
			    	if (StringUtils.isEmpty(jmiMember.getPapernumber())) {
						errors.rejectValue("papernumber", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.papernumber") }, "");
						isNotPass = true;
					}else if(dao.getCheckMiMemberIdNoByMiMember(jmiMember)){
				    	//验证身份证的唯一性
						errors.rejectValue("papernumber", this.getCharacterValue(characterCoding, "miMember.idNo.isIn"),this.getCharacterValue(characterCoding, "miMember.idNo.isIn"));
						isNotPass = true;
					}
					else if( new Date().after(papernumberDate) && "1".equals(type) && "1".equals(jmiMember.getPapertype())&&"CN".equals(companyCode)&&!getIdCardFormatCheckRegisterCN(jmiMember.getPapernumber())){
						errors.reject("idNo.format");
						isNotPass = true;
			    	}
					else if("1".equals(jmiMember.getPapertype())&&"CN".equals(companyCode)&&!getIdCardCheck(jmiMember.getPapernumber())){
						errors.reject("idNo.format");
						isNotPass = true;
			    	}else if("1".equals(jmiMember.getPapertype())&&"TW".equals(companyCode)&&!getIdCardCheckTw(jmiMember.getPapernumber())){
						errors.reject("idNo.format");
						isNotPass = true;
			    	}else if("1".equals(jmiMember.getPapertype())&&"RU".equals(companyCode)){
			    		//RU 身份证验证
//						String regEx = "^[A-Za-z0-9]{10}$";
//						if(!StringUtil.isSpecialCharsIn(regEx, jmiMember.getPapernumber())){
//							errors.reject("idNo.format");
//							isNotPass = true;
//						}
//			    		if(jmiMember.getPapernumber().length()!=10){
//			    			errors.reject("idNo.format");
//							isNotPass = true;
//			    		}
			    		
			    		
			    	}else if(!jmiBlacklistManager.getCheckJmiBlacklist(jmiMember.getPapertype(), jmiMember.getPapernumber())){
						errors.reject("idNo.blacklist");
						isNotPass = true;
			    	}else if("US".equals(companyCode)&&("1".equals(jmiMember.getPapertype())||"6".equals(jmiMember.getPapertype()))){
			    		Pattern pattern = Pattern.compile("^[0-9]{9}$");
			    		Matcher matcher = pattern.matcher(jmiMember.getPapernumber());
			    		if(!matcher.find()){
							errors.rejectValue("papernumber", "errors.invalid",new Object[] { this.getCharacterValue(characterCoding, "miMember.papernumber") }, "");
							isNotPass = true;
				    	}
			    	}else if("ID".equals(companyCode)&&"1".equals(jmiMember.getPapertype())){
			    		Pattern pattern = Pattern.compile("^[0-9]{16}$");
			    		Matcher matcher = pattern.matcher(jmiMember.getPapernumber());
			    		if(!matcher.find()){
							errors.rejectValue("papernumber", "errors.invalid",new Object[] { this.getCharacterValue(characterCoding, "miMember.papernumber") }, "");
							isNotPass = true;
				    	}
			    	}else if("M".equals(defSysUser.getUserType())&&"CN".equals(companyCode)&&"1".equals(jmiMember.getPapertype())&&!StringUtil.isEmpty(getIdCardBirthday(jmiMember.getPapernumber()))){
			    			//判断是否满18岁
			    		if(!StringUtil.isEmpty(getCheckBirthday(errors, getIdCardBirthday(jmiMember.getPapernumber()), null,characterCoding,18))){
							isNotPass = true;
			    		}
			    	}
			    	
		    	}
		 	}
		 	
		 	
		 	if("1".equals(type)||"2".equals(type)||"3".equals(type)){

//		 		if (null==jmiMember.getBirthday()) {
//					errors.rejectValue("birthday", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.birthday") }, "");
//					isNotPass = true;
//				}

		    	if ("CN".equals(companyCode)) {

						if(!StringUtil.isEmpty(jmiMember.getFirstNamePy())){
							if(this.getPattern("([A-Z]|\\s)*", jmiMember.getFirstNamePy())){
								errors.reject("firstNamePy.UpPy");
								isNotPass = true;
							}
						}
						if(!StringUtil.isEmpty(jmiMember.getLastNamePy())){
							if(this.getPattern("([A-Z]|\\s)*", jmiMember.getLastNamePy())){
								errors.reject("lastNamePy.UpPy");
								isNotPass = true;
							}
						}
		    	}
				
		    	if ("PH".equals(companyCode)) {
			    	Pattern pattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
			    	if (StringUtils.isEmpty(jmiMember.getEmail())) {
						errors.rejectValue("email", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.email") }, "");
						isNotPass = true;
					}else{
	    	    		Matcher matcher = pattern.matcher(jmiMember.getEmail());
	    	    		if(!matcher.matches()){
	    	    			errors.rejectValue("email", "errors.email",new Object[] {this.getCharacterValue(characterCoding,"miMember.email") }, "");
	    	    			isNotPass = true;
	    	    		}
			    	}
		    		
		    	}
		    	if (!StringUtils.isEmpty(jmiMember.getEmail())) {
		    		Pattern pattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		    		Matcher matcher = pattern.matcher(jmiMember.getEmail());
    	    		if(!matcher.matches()){
    	    			errors.rejectValue("email", "errors.email",new Object[] {this.getCharacterValue(characterCoding,"miMember.email") }, "");
    	    			isNotPass = true;
    	    		}
		    	}
		    	
		    	if(!StringUtils.isEmpty(jmiMember.getSpouseIdno())&&"CN".equals(companyCode)/*&&!getIdCardCheck(jmiMember.getSpouseIdno())*/){
		    		/*errors.reject("miMember.spouseIdno.error");
					isNotPass = true;*/
		    		 if(dao.getCheckMiMemberSpouseIdNoByMiMember(jmiMember)){
					    	//验证身份证的唯一性
							errors.rejectValue("spouseIdno", this.getCharacterValue(characterCoding, "miMember.idNo.isIn"),this.getCharacterValue(characterCoding, "miMember.idNo.isIn"));
							isNotPass = true;
						}else if(!jmiBlacklistManager.getCheckJmiBlacklist("1", jmiMember.getSpouseIdno())){
							errors.reject("idNo.blacklist");
							isNotPass = true;
				    	}
		    	}
		    	if("US".equals(companyCode)&&"6".equals(jmiMember.getPapertype())){
		    		if (StringUtils.isEmpty(jmiMember.getCompanyName())) {
						errors.rejectValue("companyName", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.companyName") }, "");
						isNotPass = true;
					}
		    	}
		    	
		 	}
		 	if("2".equals(type)){
//		    	if (StringUtils.isEmpty(jmiMember.getBankbook())) {
//					errors.rejectValue("bankbook", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.bname") }, "");
//					isNotPass = true;
//		    	}
//		    	if ("1".equals(jmiMember.getIsstore())&&null==jmiMember.getDeadlineDate()) {
//					errors.rejectValue("deadlineDate", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "aiAgent.deadlineDate") }, "");
//					isNotPass = true;
//		    	}
		    	if (!StringUtils.isEmpty(jmiMember.getSubRecommendStore())) {
		    		if(null==this.getJmiMember(jmiMember.getSubRecommendStore())){
		    			errors.reject("miMember.subRecommendStore.notExist");
		    			isNotPass = true;
		    		}
		    	}
		    	if (!StringUtils.isEmpty(jmiMember.getRecommendStore())) {
		    		if(null==this.getJmiMember(jmiMember.getRecommendStore())){
		    			errors.reject("miMember.recommendStore.notExist");
		    			isNotPass = true;
		    		}
		    	}
		 	}

		 	if("1".equals(type)||"2".equals(type)||"3".equals(type)){
		 		if (!"TW".equals(companyCode)&&(StringUtils.isEmpty(jmiMember.getPetName()))) {
					errors.rejectValue("petName", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.petName") }, "");
					isNotPass = true;
				}
		 		//美国固话
		 		if("US".equals(companyCode)){
		 			if (StringUtils.isEmpty(jmiMember.getPhone())) {
					errors.rejectValue("mobiletele", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.phone") }, "");
					isNotPass = true;
		 			}else {
			    		Pattern pattern = Pattern.compile("^[0-9]{10}$");
			    		Matcher matcher = pattern.matcher(jmiMember.getPhone());
			    		if(!matcher.find()){
							errors.rejectValue("phone", "errors.invalid",new Object[] { this.getCharacterValue(characterCoding, "miMember.phone") }, "");
							isNotPass = true;
				    	}
			    	}
		 		}
		 		
		 		//日本固话
		 		if("JP".equals(companyCode)){
	//		 		日本固话
			 		//普通号码有03-XXXX-XXXX,043-XXXX-XXXX，0120-XXX-XXXX，0120-XX-XXXX几种形式，“-”是必须填写的
			    	if (null==jmiMember.getDeadlineDate()) {
						errors.rejectValue("deadlineDate", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "pd.createTime") }, "");
						isNotPass = true;
			    	}
		 			
		 			
		 			if(!StringUtils.isEmpty(jmiMember.getPhone())){
//						errors.rejectValue("phone", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.phone") }, "");
//						isNotPass = true;
//		 			}else {
		 				boolean phoneMatcher=false;
			    		Pattern pattern = Pattern.compile("^([0-9]{2})[-]([0-9]{4})[-]([0-9]{4})$");
			    		Matcher matcher = pattern.matcher(jmiMember.getPhone());
			    		if(!matcher.find()){
			    			phoneMatcher=false;
				    	}else{
				    		phoneMatcher=true;
				    	}
			    		if(!phoneMatcher){
			    			pattern = Pattern.compile("^([0-9]{4})[-]([0-9]{2})[-]([0-9]{4})$");
			    			matcher = pattern.matcher(jmiMember.getPhone());
			    			if(!matcher.find()){
				    			phoneMatcher=false;
					    	}else{
					    		phoneMatcher=true;
					    	}
			    		}
			    		if(!phoneMatcher){
			    			pattern = Pattern.compile("^([0-9]{3})[-]([0-9]{3})[-]([0-9]{4})$");
			    			matcher = pattern.matcher(jmiMember.getPhone());
			    			if(!matcher.find()){
				    			phoneMatcher=false;
					    	}else{
					    		phoneMatcher=true;
					    	}
			    		}
			    		if(!phoneMatcher){
							errors.rejectValue("phone", "errors.invalid",new Object[] { this.getCharacterValue(characterCoding, "miMember.phone") }, "");
							isNotPass = true;
			    		}
			    		
			    	}
			 			
		 		}
		 		
		 		
		    	//=========
		 		if(!"US".equals(companyCode)){
		 			if (StringUtils.isEmpty(jmiMember.getMobiletele())) {
						errors.rejectValue("mobiletele", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.mobiletele") }, "");
						isNotPass = true;
			    	}else if("TW".equals(companyCode)&&!StringUtils.isEmpty(jmiMember.getMobiletele())){
			    		Pattern pattern = Pattern.compile("^[0][9]([0-9]{8})$");
			    		Matcher matcher = pattern.matcher(jmiMember.getMobiletele());
			    		if(!matcher.find()){
			    			errors.rejectValue("mobiletele", "errors.phone",new Object[] {this.getCharacterValue(characterCoding, "miMember.mobiletele") }, "");
			    			isNotPass = true;
			    		}
			    	}else if("PH".equals(companyCode)&&!StringUtils.isEmpty(jmiMember.getMobiletele())){
			    		Pattern pattern = Pattern.compile("^[0][9]([0-9]{9})$");
			    		Matcher matcher = pattern.matcher(jmiMember.getMobiletele());
			    		if(!matcher.find()){
			    			errors.rejectValue("mobiletele", "errors.phone",new Object[] {this.getCharacterValue(characterCoding, "miMember.mobiletele") }, "");
			    			isNotPass = true;
			    		}
			    	}else if("JP".equals(companyCode)){
				    		Pattern pattern = Pattern.compile("^([0-9]{3})[-]([0-9]{4})[-]([0-9]{4})$");
				    		Matcher matcher = pattern.matcher(jmiMember.getMobiletele());
				    		if(!matcher.find()){
				    			errors.rejectValue("mobiletele", "errors.phone",new Object[] {this.getCharacterValue(characterCoding, "miMember.mobiletele") }, "");
				    			isNotPass = true;
				    	}
			    	}else if("HK".equals(companyCode) && jmiMember.getMobiletele().length()>20){
			    			errors.rejectValue("mobiletele", "errors.phone",new Object[] {this.getCharacterValue(characterCoding, "miMember.mobiletele") }, "");
			    			isNotPass = true;
			    	}
			    	else if("ID".equals(companyCode)&&!StringUtils.isEmpty(jmiMember.getMobiletele())){
			    		Pattern pattern = Pattern.compile("^[0]([0-9]*)$");
			    		Matcher matcher = pattern.matcher(jmiMember.getMobiletele());
			    		if(!matcher.find()){
			    			errors.rejectValue("mobiletele", "errors.phone",new Object[] {this.getCharacterValue(characterCoding, "miMember.mobiletele") }, "");
			    			isNotPass = true;
			    		}
			    	}
			    	if("PH".equals(companyCode) &&!StringUtils.isEmpty(jmiMember.getMobiletele()) && dao.getCheckMiMemberMobileByMiMember(jmiMember)){
			    		errors.reject("mobiletele.isExist");
			    		isNotPass = true;
			    	}
		 		}
		 		if("US".equals(companyCode)&&!StringUtils.isEmpty(jmiMember.getMobiletele())){
		    		Pattern pattern = Pattern.compile("^[0-9]{10}$");
		    		Matcher matcher = pattern.matcher(jmiMember.getMobiletele());
		    		if(!matcher.find()){
		    			errors.rejectValue("mobiletele", "errors.phone",new Object[] {this.getCharacterValue(characterCoding, "miMember.mobiletele") }, "");
		    			isNotPass = true;
		    		}
		    	}
		    	
		    
		    	if (("TW".equals(companyCode)||"PH".equals(companyCode)||"JP".equals(companyCode)||"ID".equals(companyCode))&&null==jmiMember.getBirthday()) {
					errors.rejectValue("birthday", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.birthday") }, "");
					isNotPass = true;
		    	}else if(("TW".equals(companyCode)||"PH".equals(companyCode)||"JP".equals(companyCode)||"RU".equals(companyCode)||"ID".equals(companyCode))&&null!=jmiMember.getBirthday()){
		    		int age=18;
		    		if("JP".equals(companyCode)){
		    			age=20;
		    		}
		    		if("ID".equals(companyCode)){
		    			age=17;
		    		}
		    		if(!StringUtil.isEmpty(getCheckBirthday(errors, "", jmiMember.getBirthday(),characterCoding,age))){
						isNotPass = true;
		    		}
		    	}
//		    	if (StringUtils.isEmpty(jmiMember.getDistrict())) {
//					errors.rejectValue("district", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.district") }, "");
//					isNotPass = true;
//		    	}
		    	

		    	//0本国 null || "" 中国 
		    	if(StringUtil.isEmpty(jmiMember.getIdentityType())||"0".equals(jmiMember.getIdentityType())|| "JP".equals(companyCode) ){

			    	if (StringUtils.isEmpty(jmiMember.getProvince())) {
			    		String provinceCharacter="";
			    		if("PH".equals(companyCode)){
			    			provinceCharacter="miMember.island";
			    		}else{
			    			provinceCharacter="miMember.province";
			    		}
			    		errors.rejectValue("province", "isNotNull",new Object[] { this.getCharacterValue(characterCoding,provinceCharacter ) }, "");
						isNotPass = true;
			    	}
			    	if(!"RU".equals(companyCode)){
				    	if (StringUtils.isEmpty(jmiMember.getCity())) {
				    		String cityCharacter="";
				    		if("PH".equals(companyCode)){
				    			cityCharacter="miMember.region";
				    		}else{
				    			cityCharacter="busi.city";
				    		}
							errors.rejectValue("city", "isNotNull",new Object[] { this.getCharacterValue(characterCoding,cityCharacter ) }, "");
							isNotPass = true;
				    	}
			    	}

			    	if("TW".equals(companyCode)||"PH".equals(companyCode)||"CN".equals(companyCode)){
			    		String districtCharacter="";
			    		if("PH".equals(companyCode)){
			    			districtCharacter="miMember.province";
			    		}else{
			    			districtCharacter="miMember.district";
			    		}
				    	if (StringUtils.isEmpty(jmiMember.getDistrict())) {
							errors.rejectValue("district", "isNotNull",new Object[] { this.getCharacterValue(characterCoding,districtCharacter ) }, "");
							isNotPass = true;
				    	}
			    	}

			    	if("PH".equals(companyCode)){
				    	if (StringUtils.isEmpty(jmiMember.getDistrict())) {
							errors.rejectValue("town", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "busi.city") }, "");
							isNotPass = true;
				    	}
			    	}
			    	
			    	if("TW".equals(companyCode)){

				    	
				 		if ((StringUtils.isEmpty(jmiMember.getTownAddr()))) {
							errors.rejectValue("townAddr", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.townAddr") }, "");
							isNotPass = true;
						}
				 		if (StringUtils.isEmpty(jmiMember.getVillageAddr())) {
							errors.rejectValue("villageAddr", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.villageAddr") }, "");
							isNotPass = true;
						}
				 		if (StringUtils.isEmpty(jmiMember.getPostalcode())) {
							errors.rejectValue("postalcode", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.postalcode") }, "");
							isNotPass = true;
				    	}
			    	}
			    	
			    	//日本,villageAddr 电话 mail

//			    	if("JP".equals(companyCode)){
//				 		if (StringUtils.isEmpty(jmiMember.getVillageAddr())) {
//							errors.rejectValue("villageAddr", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.mobile.mail") }, "");
//							isNotPass = true;
//						}
//			    	}
			    	
			    	//
			    	if (StringUtils.isEmpty(jmiMember.getAddress())) {
						errors.rejectValue("address", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "busi.address") }, "");
						isNotPass = true;
			    	}
			    	if("JP".equals(companyCode)){
//			    		if (StringUtils.isEmpty(jmiMember.getBuilding())) {
//							errors.rejectValue("building", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.building") }, "");
//							isNotPass = true;
//			    		}
			    	}
			    	
			    	if(!StringUtils.isEmpty(jmiMember.getPostalcode())&&jmiMember.getPostalcode().length()>10){
			    		errors.reject("member.postalcode.too.long");
						isNotPass = true;
			    	}
			    	//美国验证邮编
			    	if("JP".equals(companyCode)||"US".equals(companyCode)){
				    	if(StringUtils.isEmpty(jmiMember.getPostalcode())){
							errors.rejectValue("postalcode", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.postalcode") }, "");
							isNotPass = true;
				    	}else if("US".equals(companyCode)){
				    		Pattern pattern = Pattern.compile("^[0-9]{5}$");
				    		Matcher matcher = pattern.matcher(jmiMember.getPostalcode());
				    		if(!matcher.find()){
								errors.rejectValue("postalcode", "errors.invalid",new Object[] { this.getCharacterValue(characterCoding, "miMember.postalcode") }, "");
								isNotPass = true;
					    	}
				    	}else if("JP".equals(companyCode)){
				    		Pattern pattern = Pattern.compile("^([0-9]{3})[-]([0-9]{4})$");
				    		Matcher matcher = pattern.matcher(jmiMember.getPostalcode());
				    		if(!matcher.find()){
								errors.rejectValue("postalcode", "errors.invalid",new Object[] { this.getCharacterValue(characterCoding, "miMember.postalcode") }, "");
								isNotPass = true;
					    	}
				    	}
			    	}
			    	//=======
		    	}
		    	

		    	if("TW".equals(companyCode)||"JP".equals(companyCode)){
		    		if("2".equals(jmiMember.getIdentityType())){
				 		if (StringUtils.isEmpty(jmiMember.getCompanyName())) {
							errors.rejectValue("companyName", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.companyName") }, "");
							isNotPass = true;
						}
				 		if (StringUtils.isEmpty(jmiMember.getPersonCharge())) {
							errors.rejectValue("personCharge", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.personCharge") }, "");
							isNotPass = true;
						}
				 		if (StringUtils.isEmpty(jmiMember.getCompanyAddr())) {
							errors.rejectValue("companyAddr", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.companyAddr") }, "");
							isNotPass = true;
						}
				 		if (StringUtils.isEmpty(jmiMember.getUniteNumber())) {
							errors.rejectValue("uniteNumber", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.uniteNumber") }, "");
							isNotPass = true;
						}else if("TW".equals(companyCode)){
				    		Pattern pattern = Pattern.compile("^([0-9]{8})$");
				    		Matcher matcher = pattern.matcher(jmiMember.getUniteNumber());
				    		if(!matcher.find()){
			        			errors.reject("papernumber.format.4" );
								isNotPass = true;
				    		}
						}
		    		}
		    	}
		    	if("TW".equals(companyCode)){

		    			
				    	
			 		if ((StringUtils.isEmpty(jmiMember.getCommProvince()))) {
						errors.rejectValue("commProvince", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.commProvince") }, "");
						isNotPass = true;
					}
			 		if ((StringUtils.isEmpty(jmiMember.getCommCity()))) {
						errors.rejectValue("commCity", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.commCity") }, "");
						isNotPass = true;
					}
			 		if ((StringUtils.isEmpty(jmiMember.getCommDistrict()))) {
						errors.rejectValue("commDistrict", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.commDistrict") }, "");
						isNotPass = true;
					}
			 		if ((StringUtils.isEmpty(jmiMember.getCommAddr()))) {
						errors.rejectValue("commAddr", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.commAddr") }, "");
						isNotPass = true;
					}
			 		if ((StringUtils.isEmpty(jmiMember.getCommPostalcode()))) {
						errors.rejectValue("commPostalcode", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.commPostalcode") }, "");
						isNotPass = true;
					}
		    	}
		    	
		    	
		    	
		 	}
		 	if("3".equals(type)){


		    	if (StringUtils.isEmpty(jmiMember.getBank())) {
					errors.rejectValue("bank", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.openBank") }, "");
					isNotPass = true;
		    	}
		    	
		    	if (StringUtils.isEmpty(jmiMember.getBankaddress())) {
					errors.rejectValue("bankaddress", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.bcity") }, "");
					isNotPass = true;
		    	}
		    	String mPower="";
		    	if(!StringUtil.isEmpty(jmiMember.getCompanyCode())){
		    		mPower=(String) Constants.sysConfigMap.get(jmiMember.getCompanyCode()).get("m.bankcardno.power");
		    	}
		    	
//		    	如果MemberType=2 且mPower =0 0不允许改，1允许改 2为M公司
		    	if("2".equals(jmiMember.getMemberType())&&"0".equals(mPower)){
		    		//不做任何动作
		    	}else{
			    	if (!"2".equals(jmiMember.getMemberType())&&StringUtils.isEmpty(jmiMember.getBankcard())) {
						errors.rejectValue("bankcard", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.bnum") }, "");
						isNotPass = true;
			    	}
		    	}
		    	
		    	if (StringUtils.isEmpty(jmiMember.getBankProvince())) {
					errors.rejectValue("bankProvince", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.bankProvince") }, "");
					isNotPass = true;
		    	}
		    	if (StringUtils.isEmpty(jmiMember.getBankCity())) {
					errors.rejectValue("bankCity", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.bankCity") }, "");
					isNotPass = true;
		    	}
		 	}

		 	if("1".equals(type)||"4".equals(type)){
		 		if(StringUtils.isEmpty(jmiMember.getJmiRecommendRef().getRecommendJmiMember().getUserCode())){
					errors.rejectValue("jmiRecommendRef.recommendJmiMember.userCode", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.recommendNo") }, "");
					isNotPass = true;
		        }else if(("TW".equals(companyCode)||"PH".equals(companyCode))&&!StringUtils.isEmpty(jmiMember.getJmiRecommendRef().getRecommendJmiMember().getUserCode())){
		        	JmiMember curJmiMember=this.getJmiMember(jmiMember.getJmiRecommendRef().getRecommendJmiMember().getUserCode());
		        	if(null!=curJmiMember&&("0".equals(curJmiMember.getCardType())||"5".equals(curJmiMember.getCardType()))){
						errors.reject("mi.recommendRef.notcheck");
						isNotPass = true;
		        	}
		        }
				if (StringUtil.isEmpty(jmiMember.getJmiLinkRef().getLinkJmiMember().getUserCode())) {
					errors.rejectValue("jmiLinkRef.linkJmiMember.userCode", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.linkNo") }, "");
					isNotPass = true;
				}
		 	}
		 	if("1".equals(type)){
		 		
				
				if(StringUtils.isEmpty(jmiMember.getSysUser().getPassword())){
					errors.rejectValue("sysUser.password", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.pwd1") }, "");
					isNotPass = true;
				}
				if(!"TW".equals(companyCode)&&!"JP".equals(companyCode)){
					if(StringUtils.isEmpty(jmiMember.getSysUser().getPassword2())){
						errors.rejectValue("sysUser.password2", "isNotNull",new Object[] { this.getCharacterValue(characterCoding, "miMember.pwd2") }, "");
						isNotPass = true;
					}
				}
	
		    	/*发货信息*/
//		    	String shippingFirstName=request.getParameter("shippingFirstName");
//		    	String shippingLastName=request.getParameter("shippingLastName");
//		    	String shippingProvince=request.getParameter("shippingProvince");
//		    	String shippingCity=request.getParameter("shippingCity");
//		    	String shippingAddress=request.getParameter("shippingAddress");
//		    	String shippingPostalcode=request.getParameter("shippingPostalcode");
//		    	String shippingPhone=request.getParameter("shippingPhone");
		    	//String shippingEmail=request.getParameter("shippingEmail");
//		    	String shippingMobiletele=request.getParameter("shippingMobiletele");
		    	
		    	
//		    	if (StringUtils.isEmpty(shippingFirstName)) {
//					errors.reject("shippingFirstName.isNotNull");
//					isNotPass = true;
//		    	}
//		    	if (StringUtils.isEmpty(shippingLastName)) {
//					errors.reject("shippingLastName.isNotNull");
//					isNotPass = true;
//		    	}
//		    	if (StringUtils.isEmpty(shippingProvince)) {
//					errors.reject("shippingProvince.isNotNull");
//					isNotPass = true;
//		    	}
//		    	if (StringUtils.isEmpty(shippingCity)) {
//					errors.reject("shippingCity.isNotNull");
//					isNotPass = true;
//		    	}
//		    	if (StringUtils.isEmpty(shippingAddress)) {
//					errors.reject("shippingAddress.isNotNull");
//					isNotPass = true;
//		    	}
//		    	if (StringUtils.isEmpty(shippingPostalcode)) {
//					errors.reject("shippingPostalcode.isNotNull");
//					isNotPass = true;
//		    	}else if(shippingPostalcode.length()>10){
//		    		errors.reject("member.postalcode.too.long");
//					isNotPass = true;
//		    	}
//		    	if (StringUtils.isEmpty(shippingPhone)) {
//					errors.reject("shippingPhone.isNotNull");
//					isNotPass = true;
//		    	}
//		    	if (StringUtils.isEmpty(shippingEmail)) {
//					errors.reject("shippingEmail.isNotNull");
//					isNotPass = true;
//		    	}else if(!StringUtil.isEmail(shippingEmail)){
//					errors.reject("error.email");
//					isNotPass = true;
//		    	}
		    	
//		    	if (StringUtils.isEmpty(shippingMobiletele)) {
//					errors.reject("shippingMobiletele.isNotNull");
//					isNotPass = true;
//		    	}
			
		 	}
	    	
	    	return isNotPass;
	}
	private String getCharacterValue(String characterCoding,String msgKey){
		String characterValue=(String) Constants.localLanguageMap.get(characterCoding).get(msgKey);
		if(!StringUtils.isEmpty(characterValue)){
			return characterValue;
		}else{
			return msgKey;
		}
	}
	//更改姓名
	public void getSetUserName(JmiMember jmiMember) {

		String format=(String) Constants.sysConfigMap.get(jmiMember.getCompanyCode()).get("member.name.format");
		String space="";
		if("0".equals(format)){
			space="";
		}else{
			space=" ";
		}
		if((StringUtil.isEmpty(jmiMember.getFirstName())&&StringUtil.isEmpty(jmiMember.getLastName()))){
			String name=jmiMember.getMiUserName();
			jmiMember.getSysUser().setFirstName("");
			jmiMember.getSysUser().setLastName(name);
			jmiMember.setFirstName("");
			jmiMember.setLastName(name);
			jmiMember.getSysUser().setUserName(name);
		}else{
			jmiMember.getSysUser().setUserName((jmiMember.getFirstName()==null?"":jmiMember.getFirstName())+space+jmiMember.getLastName());
			jmiMember.getSysUser().setFirstName(jmiMember.getFirstName());
			jmiMember.getSysUser().setLastName(jmiMember.getLastName());
			jmiMember.setMiUserName(jmiMember.getSysUser().getUserName());
		}
		jmiMember.setBankbook(jmiMember.getMiUserName());
		if("TW".equals(jmiMember.getCompanyCode())){
			//台湾昵称与名称一样
			jmiMember.setPetName(jmiMember.getMiUserName());
		}
			
	}

	public List getMiRecommendRefsCountList(String memberNo, String startTime, String endTime) {
		return dao.getMiRecommendRefsCountList(memberNo, startTime, endTime);
	}

	public List getMiRecommendRefsCounts(CommonRecord crm, Pager pager) {
		return dao.getMiRecommendRefsCounts(crm, pager);
	}

	public void saveMemberCompany(JmiMember jmiMember,HttpServletRequest request) {
		//修改会员级别，更新权限 
/*		String oldCardType=request.getParameter("oldCardType");
		if(!oldCardType.equals(jmiMember.getCardType())){
			SysUserRole sysUserRole= sysUserRoleManager.getSysUserRoleByUserCode(jmiMember.getUserCode());
			String memberRoleId =(String) Constants.sysConfigMap.get(jmiMember.getCompanyCode()).get("member_role_id." + jmiMember.getCardType());

			SysRole memberSysRole=sysRoleManager.getSysRoleByCode(memberRoleId);
			sysUserRole.setRoleId(memberSysRole.getRoleId());
			sysUserRoleManager.saveSysUserRole(sysUserRole);
			
			//更新会员级别时，同时记录升级记录
			Date curDate=new Date();
			JmiMemberUpgradeNote miMemberUpgradeNote = new JmiMemberUpgradeNote();
			miMemberUpgradeNote.setJmiMember(jmiMember);
			miMemberUpgradeNote.setOldCard(oldCardType);
			miMemberUpgradeNote.setNewCard(jmiMember.getCardType());
			miMemberUpgradeNote.setCompanyCode(jmiMember.getCompanyCode());
			miMemberUpgradeNote.setMemberOrderNo("-");
			miMemberUpgradeNote.setUpdateType("3");
			miMemberUpgradeNote.setUpdateDate(curDate);
			jmiMemberUpgradeNoteManager.saveJmiMemberUpgradeNote(miMemberUpgradeNote);
			
			//更新奖金级别表
			jbdUserCardListManager.saveJbdUserCardList(jmiMember.getUserCode(), curDate, jmiMember.getCardType(),"3","1");
		}*/	
		String oldIsstore=request.getParameter("oldIsstore");
		//设定会员角色---update gw
		if(!oldIsstore.equals(jmiMember.getIsstore())){

			String memberRoleIdStr=getRole(jmiMember);
			log.info("memberRoleIdStr:"+memberRoleIdStr);
			System.out.println("memberRoleIdStr:"+memberRoleIdStr);
			//获取该会员的角色对象
			SysUserRole sysUserRole= sysUserRoleManager.getSysUserRoleByUserCode(jmiMember.getUserCode());
			//String memberRoleId =(String) Constants.sysConfigMap.get(jmiMember.getCompanyCode()).get(memberRoleIdStr);

			SysRole memberSysRole=sysRoleManager.getSysRoleByCode(memberRoleIdStr);
			sysUserRole.setRoleId(memberSysRole.getRoleId());
			sysUserRoleManager.saveSysUserRole(sysUserRole);
			
		}
		

		String oldFreezeStatus=request.getParameter("oldFreezeStatus");
		if(!oldFreezeStatus.equals(jmiMember.getFreezeStatus().toString())){

			SysRole memberSysRole=null;
			if(jmiMember.getFreezeStatus()==1){
				memberSysRole=sysRoleManager.getSysRoleByCode("jocs.member.role.freeze");
			}else{

				String memberRoleIdStr=getRole(jmiMember);
				//memberRoleIdStr =(String) Constants.sysConfigMap.get(jmiMember.getCompanyCode()).get(memberRoleIdStr);
				
				
				memberSysRole=sysRoleManager.getSysRoleByCode(memberRoleIdStr);
			}

			SysUserRole sysUserRole= sysUserRoleManager.getSysUserRoleByUserCode(jmiMember.getUserCode());
			sysUserRole.setRoleId(memberSysRole.getRoleId());
			sysUserRoleManager.saveSysUserRole(sysUserRole);
			
		}
/*		//更新国别时，同时更新角色
		String oldCompanyCode=request.getParameter("oldCompanyCode");
		if(!oldCompanyCode.equals(jmiMember.getCompanyCode())){
			//判断会员是否存待审订单
			CommonRecord crm=new CommonRecord();

			Pager pager = new Pager("jfiBankbookJournalListTable", request, 0);
			crm.addField("sysUser.userCode", jmiMember.getUserCode());
			crm.addField("status", "1");
			List list=jpoMemberOrderManager.getJpoMemberOrdersByCrm(crm, pager);
			if(!list.isEmpty()){
				throw new AppException("not.audit.exsit");
			}
			//
			jmiMember.getSysUser().setCompanyCode(jmiMember.getCompanyCode());
			//更新contrycode
			AlCompany alCompany = alCompanyManager.getAlCompanyByCode(jmiMember.getCompanyCode());
			jmiMember.setCountryCode(alCompany.getCountryCode());
			
			String memberRoleId =(String) Constants.sysConfigMap.get(jmiMember.getCompanyCode()).get("member_role_id." + jmiMember.getCardType());

			
			SysRole memberSysRole=sysRoleManager.getSysRoleByCode(memberRoleId);
			SysUserRole sysUserRole=sysUserRoleManager.getSysUserRoleByUserCode(jmiMember.getUserCode());
			if(sysUserRole==null){
				sysUserRole=new SysUserRole();
			}
			sysUserRole.setRoleId(memberSysRole.getRoleId());
			sysUserRole.setUserCode(jmiMember.getUserCode());
			sysUserRoleManager.saveSysUserRole(sysUserRole);
		}*/
		
		
		/**
		 * 台湾店铺
		 */

		String oldShopType=request.getParameter("oldShopType");
		if(  !(StringUtil.isEmpty(oldShopType) && StringUtil.isEmpty(jmiMember.getShopType()))  &&  !oldShopType.equals(jmiMember.getShopType())){
			String memberRoleIdStr="";
			if("1".equals(jmiMember.getShopType())){
				memberRoleIdStr="member_role_id.shop.1";
			}else if("2".equals(jmiMember.getShopType())){
				memberRoleIdStr="member_role_id.shop.2";
			}
			if(StringUtil.isEmpty(jmiMember.getShopType())){
				memberRoleIdStr="member_role_id." + jmiMember.getCardType();
			}
			
			 
			SysUserRole sysUserRole= sysUserRoleManager.getSysUserRoleByUserCode(jmiMember.getUserCode());
			String memberRoleId =(String) Constants.sysConfigMap.get(jmiMember.getCompanyCode()).get(memberRoleIdStr);

			SysRole memberSysRole=sysRoleManager.getSysRoleByCode(memberRoleId);
			sysUserRole.setRoleId(memberSysRole.getRoleId());
			sysUserRoleManager.saveSysUserRole(sysUserRole);
			
			
		}
		
		
		this.getSetUserName(jmiMember);
		//财政周
    	if(jmiMember.getValidWeek()!=null){
        	jmiMember.setValidWeek(StringUtil.formatInt(WeekFormatUtil.getFormatMonth("f", jmiMember.getValidWeek().toString())));
    	} 
    	if(jmiMember.getStartWeek()!=null){
        	jmiMember.setStartWeek(StringUtil.formatInt(WeekFormatUtil.getFormatMonth("f", jmiMember.getStartWeek().toString())));
    	}
    	//当修改瓜藤网激活手机号时，如果为空，则清空激活状态
    	
    	if(StringUtil.isEmpty(jmiMember.getEcMallPhone()) && "1".equals(jmiMember.getEcMallStatus())){
    		jmiMember.setEcMallStatus("");
    	}else if(!StringUtil.isEmpty(jmiMember.getEcMallPhone()) && StringUtil.isEmpty(jmiMember.getEcMallStatus())){
    		jmiMember.setEcMallStatus("1");
    	}
    	

		String oldIsCloudshop=request.getParameter("oldIsCloudshop");
    	if( !oldIsCloudshop.equals(jmiMember.getIsCloudshop()) && "1".equals(jmiMember.getIsCloudshop())){
    		java.util.Calendar startc=java.util.Calendar.getInstance();
    		startc.set(2017, 6, 1, 00, 00, 00);
    		java.util.Date cloud_date=startc.getTime();
    		java.util.Date cloud_end_date=null;
    		Date curDate=new Date();
    		
    		if(curDate.after(cloud_date)){//7月1号后云店资格为1年
    			String validity=(String) Constants.sysConfigMap.get("AA").get("validity.period");
    			cloud_end_date=DateUtil.getDateOffset(curDate, 2, StringUtil.formatInt(validity));
    		}else{//7月1号前云店资格为2018.6.30
    			java.util.Calendar cloudEndCalendar=java.util.Calendar.getInstance();
    			cloudEndCalendar.set(2018, 5, 30, 23, 59, 59);
	    		cloud_end_date=cloudEndCalendar.getTime();
    		}
    		

	    	if(jmiMember.getMemberLevel().intValue()>=2500 && jmiMember.getMemberLevel().intValue()<=3000){
	    		if(jmiMember.getStandardTime()==null){
	    			jmiMember.setStandardTime(curDate);
	    		}
	    		jmiMember.setMemberUserType("2");
	    	}else if(jmiMember.getMemberLevel().intValue() > 3000) {
	    		if(jmiMember.getStandardMkTime()==null){
	    			jmiMember.setStandardMkTime(curDate);
	    		}
	    		jmiMember.setMemberUserType("1");
	    	}

	    	if(jmiMember.getMemberLevel().intValue()>=2500){
	    		if(jmiMember.getCloudStartdate()==null){
		    		jmiMember.setCloudStartdate(curDate);
	    		}
		    	if(jmiMember.getCloudEnddate()==null){
		    		jmiMember.setCloudEnddate(cloud_end_date);
		    	}
	    		
	    	}
	    	
    		
    	}
    	//
		this.saveJmiMember(jmiMember);
	}
	/**
	 * 验证身份证有效性
	 * @param id
	 * @return
	 */
	private String getIdCardBirthday(String id){
		id=id.toUpperCase();
		String regEx = "(^\\d{15}$)|(^\\d{17}([0-9]|[X])$)";
		//身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。
		if(!StringUtil.isSpecialCharsIn(regEx, id)){
			return "";
		}
		int len=id.length();
		if(len==15){
			String year=id.substring(6, 8);
			String month=id.substring(8, 10);
			String day=id.substring(10, 12);
			if(StringUtil.isDate("19"+year+"-"+month+"-"+day)){
				return "19"+year+"-"+month+"-"+day;
			}else{
				return "";
			}
			
		}else if(len==18){
			String year=id.substring(6, 10);
			String month=id.substring(10, 12);
			String day=id.substring(12, 14);

			if(StringUtil.isDate(year+"-"+month+"-"+day)){
				return year+"-"+month+"-"+day;
			}else{
				return "";
			}
		}
		return "";
	}
	/**
	 * 验证身份证有效性
	 * @param id
	 * @return
	 */
	public boolean getIdCardCheck(String id){
		id=id.toUpperCase();
		String regEx = "(^\\d{15}$)|(^\\d{17}([0-9]|[X])$)";
		//身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。
		if(!StringUtil.isSpecialCharsIn(regEx, id)){
			return false;
		}
		int len=id.length();
		if(len==15){
			String year=id.substring(6, 8);
			String month=id.substring(8, 10);
			String day=id.substring(10, 12);
			return StringUtil.isDate("19"+year+"-"+month+"-"+day);
		}else if(len==18){
			String year=id.substring(6, 10);
			String month=id.substring(10, 12);
			String day=id.substring(12, 14);

			return StringUtil.isDate(year+"-"+month+"-"+day);
		}
		return true;
	}

	/**台湾
	 * 验证身份证有效性
	 * @param id
	 * @return
	 */
	public boolean getIdCardCheckTw(String idNo){
		idNo=idNo.toUpperCase();
		if(idNo.length()!=10){
			//System.out.println("10位");
			return false;
		}
		Object idIput[]=new Object[10];
		for (int i = 0; i < 10; i++) {
			
			idIput[i]=idNo.charAt(i);
		}
		String EngString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		idIput[0]=EngString.indexOf(idIput[0].toString());
		
		if(idIput[0].toString().equals("-1")){
			//System.out.println("无字母开头");
			return false;
		}
		if(idIput[1].toString().equals("1")&&idIput[1].toString().equals("2")){
			//System.out.println("身份证号码错误，无法识别性别");
			return false;
		}
		int NumArray[]  = new int[26];
		NumArray[0]   = 1 ; NumArray[1]  = 10; NumArray[2]  = 19;
		NumArray[3]   = 28; NumArray[4]  = 37; NumArray[5]  = 46;
		NumArray[6]   = 55; NumArray[7]  = 64; NumArray[8]  = 39;
		NumArray[9]   = 73; NumArray[10] = 82; NumArray[11] = 2 ;
		NumArray[12]  = 11; NumArray[13] = 20; NumArray[14] = 48;
		NumArray[15]  = 29; NumArray[16] = 38; NumArray[17] = 47;
		NumArray[18]  = 56; NumArray[19] = 65; NumArray[20] = 74;
		NumArray[21]  = 83; NumArray[22] = 21; NumArray[23] = 3 ;
		NumArray[24]  = 12; NumArray[25] = 30;
		
		int result = NumArray[new Integer(idIput[0].toString())];
		for (int i=1; i<10; i++){
			String NumString = "0123456789";
			if(NumString.indexOf(idIput[i].toString())==-1){
				//System.out.println("身份证号码错误，数字检验错误");
				return false;
			}else{
				result += new Integer(idIput[i].toString()) * (9-i);
			}
		}
		result += 1 * new Integer(idIput[9].toString());
		if (result % 10 != 0){
		   	//System.out.println("身份证号码错误，加总检查错误");
			return false;
		}
		return true;
	}
	/**
	 * 修改会员卡别
	 * @param newCardType
	 * @param miMember
	 */
	public void changeMiMemberCardType(String newCardType, JmiMember jmiMember,Integer wweek,SysUser defSysUser){
		if(!newCardType.equals(jmiMember.getCardType())){
			
			
			SysUserRole sysUserRole= sysUserRoleManager.getSysUserRoleByUserCode(jmiMember.getUserCode());
			String memberRoleId =(String) Constants.sysConfigMap.get(jmiMember.getCompanyCode()).get("member_role_id." + newCardType);

			SysRole memberSysRole=sysRoleManager.getSysRoleByCode(memberRoleId);
			sysUserRole.setRoleId(memberSysRole.getRoleId());
			sysUserRoleManager.saveSysUserRole(sysUserRole);
			
			//更新会员级别时，同时记录升级记录
			Date curDate=new Date();
			JmiMemberUpgradeNote miMemberUpgradeNote = new JmiMemberUpgradeNote();
			miMemberUpgradeNote.setJmiMember(jmiMember);
			miMemberUpgradeNote.setOldCard(jmiMember.getCardType());
			miMemberUpgradeNote.setNewCard(newCardType);
			miMemberUpgradeNote.setCompanyCode(jmiMember.getCompanyCode());
			miMemberUpgradeNote.setMemberOrderNo("-");
			miMemberUpgradeNote.setUpdateType("3");
			miMemberUpgradeNote.setUpdateDate(curDate);
			miMemberUpgradeNote.setRemark("手工定级:"+wweek.toString());
			jmiMemberUpgradeNoteManager.saveJmiMemberUpgradeNote(miMemberUpgradeNote);
			
			//更新奖金级别表
			jbdUserCardListManager.saveJbdUserCardList(jmiMember.getUserCode(), wweek, newCardType,"3","1");

			//如果为joyme 会员,且为手动修改将ori_card置0
			if("2".equals(jmiMember.getMemberType())){
				jmiMember.setOriPv(new BigDecimal(0));
			}
			
			//中国续约功能，如果都是未冻结状态，未曾经冻结，卡别由0更新为其他卡别，则更新续约时间
			if(0==jmiMember.getFreezeStatus()&&0==jmiMember.getBeforeFreezeStatus()&&"0".equals(jmiMember.getCardType())){
				BdPeriod bdPeriod = bdPeriodManager.getBdPeriodByFormatedWeek(wweek.toString());	
				Integer startMonth = bdPeriod.getWyear()*100 + bdPeriod.getWmonth();
				String year = startMonth.toString().substring(0, 4);
				String month = startMonth.toString().substring(4, 6);
				String period = bdPeriodManager.getFutureBdYearMonth(year, month, 13);
				jmiMember.setStartWeek(startMonth);
				jmiMember.setValidWeek(Integer.parseInt(period));
			}
			//
			
			//checkdate为空，待审会员改成正式会员
			if(null==jmiMember.getCheckDate()&&"0".equals(jmiMember.getCardType())){
				jmiMember.setCheckDate(curDate);
			}
			if("0".equals(newCardType)){
				jmiMember.setCheckDate(null);
			}
			
			
			jmiMember.setCardType(newCardType);
			
			
			
			this.saveJmiMember(jmiMember);
			
			

			//插入每日计算表
			JbdSummaryList jbdSummaryList=new JbdSummaryList();
			jbdSummaryList.setUserCode(jmiMember.getUserCode());
			jbdSummaryList.setCardType(newCardType);
			jbdSummaryList.setInType(5);
			jbdSummaryList.setCreateTime(curDate);
			jbdSummaryList.setWweek(wweek);
			jbdSummaryListManager.saveJbdSummaryList(jbdSummaryList);
			//
			
			
/*			if("0".equals(jmiMember.getCustomerLevel())&&"CN".equals(jmiMember.getCompanyCode())&&("3".equals(newCardType)||"4".equals(newCardType)||"6".equals(newCardType))){
				dao.sendPcn(jmiMember, "ModifyLevel", "1", "手工更改级别赠送PCN VIP用户", defSysUser, "");
			}*/
			
		}
	}

	public void saveJmiMemberAndName(JmiMember miMember) {

    	if(!"JP".equals(miMember.getCompanyCode())){
    		miMember.setBankbook(miMember.getSysUser().getUserName());
    	}
		

    	SysUserRole cn6SysUserRole= sysUserRoleManager.getSysUserRoleByUserCode(miMember.getUserCode());
		SysRole cn6CurSysRole=sysRoleManager.getSysRole(cn6SysUserRole.getRoleId().toString());
		
		if("cn.member.6".equals(cn6CurSysRole.getRoleCode())){
//			如果为J公司的会员，更新角色到激活角色
	        if("2".equals(miMember.getMemberType())||"3".equals(miMember.getMemberType())||"4".equals(miMember.getMemberType())||"6".equals(miMember.getMemberType())){

	    		SysUserRole sysUserRole= sysUserRoleManager.getSysUserRoleByUserCode(miMember.getUserCode());
	    		SysRole curSysRole=sysRoleManager.getSysRole(sysUserRole.getRoleId().toString());
	        	if("cn.member.6".equals(curSysRole.getRoleCode())){
	        		//设置角色
	        		String aa=miMember.getOriCard();
	        		if("4".equals(miMember.getMemberType())){
	        			aa="0";
	        		}
	        		String memberRoleId =(String) Constants.sysConfigMap.get(miMember.getCompanyCode()).get("member_role_id."+aa);
	        		SysRole memberSysRole=sysRoleManager.getSysRoleByCode(memberRoleId);
	        		sysUserRole.setRoleId(memberSysRole.getRoleId());
	        		sysUserRoleManager.saveSysUserRole(sysUserRole);
	        		miMember.setChangeStatus("1");
	        	}

	    		this.getSetUserName(miMember);
	    		//
	        }
	        
			Date curDate=new Date();
			BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByTime(curDate, null);
			String bdWeek= bdPeriod.getWyear()+""+ (bdPeriod.getWweek()<10?"0" + bdPeriod.getWweek():bdPeriod.getWweek());
			
	        if("3".equals(miMember.getMemberType()) || "6".equals(miMember.getMemberType())){
	        	//        	更新会员级别时，同时记录升级记录
				JmiMemberUpgradeNote miMemberUpgradeNote = new JmiMemberUpgradeNote();
				miMemberUpgradeNote.setJmiMember(miMember);
				miMemberUpgradeNote.setOldCard(miMember.getCardType());
				miMemberUpgradeNote.setNewCard(miMember.getOriCard());
				miMemberUpgradeNote.setCompanyCode(miMember.getCompanyCode());
				miMemberUpgradeNote.setMemberOrderNo("-");
				miMemberUpgradeNote.setUpdateType("3");
				miMemberUpgradeNote.setUpdateDate(curDate);
				miMemberUpgradeNote.setRemark("3会员激活:"+bdWeek);
				jmiMemberUpgradeNoteManager.saveJmiMemberUpgradeNote(miMemberUpgradeNote);

				//更新奖金级别表
				jbdUserCardListManager.saveJbdUserCardList(miMember.getUserCode(), curDate,  miMember.getOriCard(),"3","1");
				
				//插入每日计算表
 				JbdSummaryList jbdSummaryList=new JbdSummaryList();
 				jbdSummaryList.setUserCode(miMember.getUserCode());
 				jbdSummaryList.setCardType(miMember.getOriCard());
 				jbdSummaryList.setInType(5);
 				jbdSummaryList.setCreateTime(curDate);
 				jbdSummaryList.setWweek(new Integer(bdWeek));
 				jbdSummaryListManager.saveJbdSummaryList(jbdSummaryList);
 				//	
 				
				miMember.setCardType( miMember.getOriCard());
				
				miMember.setCheckDate(curDate);
				miMember.setCheckNo(miMember.getUserCode());
				
				
				//中国续约功能，如果都是未冻结状态，未曾经冻结，卡别由0更新为其他卡别，则更新续约时间
					Integer startMonth = bdPeriod.getWyear()*100 + bdPeriod.getWmonth();
					String year = startMonth.toString().substring(0, 4);
					String month = startMonth.toString().substring(4, 6);
					String period = bdPeriodManager.getFutureBdYearMonth(year, month, 13);
					miMember.setStartWeek(startMonth);
					miMember.setValidWeek(Integer.parseInt(period));
				//
				
				//1.专卖店 2.服务中心
 				if("1".equals(miMember.getIsSubStore())||"2".equals(miMember.getIsSubStore())){
					JmiSubStore jmiSubStore=new JmiSubStore();
					miMember.setSubRecommendStore(miMember.getRecommendNo());
					jmiSubStore.setJmiMember(miMember);
					jmiSubStoreManager.saveJmiSubStoreCheck(jmiSubStore,miMember);
					
					miMember.setIsstore("2");
					miMember.setSubStoreStatus("2");
					miMember.setSubStoreCheckDate(curDate);

		    		SysUserRole sysUserRole = sysUserRoleManager.getSysUserRoleByUserCode(miMember.getUserCode());
	        		String memberRoleId =(String) Constants.sysConfigMap.get(miMember.getCompanyCode()).get("member_role_id.store2");
	        		SysRole memberSysRole=sysRoleManager.getSysRoleByCode(memberRoleId);
	        		sysUserRole.setRoleId(memberSysRole.getRoleId());
	        		sysUserRoleManager.saveSysUserRole(sysUserRole);
				}

 				
					
	        }
		}
		

        dao.saveJmiMember(miMember);
        //

        if("3".equals(miMember.getMemberType())||"4".equals(miMember.getMemberType())){
        	this.saveEcpp(miMember);
        }
	}

	public void saveJmiMemberActive(String userCode) {
		JmiMember jmiMember=dao.getJmiMember(userCode);
		Date curDate=new Date();

//		jmiMember.setCreateTime(curDate);
		
		//设置角色
		SysUserRole sysUserRole= sysUserRoleManager.getSysUserRoleByUserCode(jmiMember.getUserCode());
		String memberRoleId =(String) Constants.sysConfigMap.get(jmiMember.getCompanyCode()).get("member_role_id.j0");
		SysRole memberSysRole=sysRoleManager.getSysRoleByCode(memberRoleId);
		sysUserRole.setRoleId(memberSysRole.getRoleId());
		sysUserRoleManager.saveSysUserRole(sysUserRole);
		
		//
		
		
		BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByTime(new Date(), null);
		String bdWeek= bdPeriod.getWyear()+""+ (bdPeriod.getWweek()<10?"0" + bdPeriod.getWweek():bdPeriod.getWweek());
		
		if("0".equals(jmiMember.getOriCard())){
			
		}else{

			//更新会员级别时，同时记录升级记录
			JmiMemberUpgradeNote miMemberUpgradeNote = new JmiMemberUpgradeNote();
			miMemberUpgradeNote.setJmiMember(jmiMember);
			miMemberUpgradeNote.setOldCard(jmiMember.getCardType());
			miMemberUpgradeNote.setNewCard(jmiMember.getOriCard());
			miMemberUpgradeNote.setCompanyCode(jmiMember.getCompanyCode());
			miMemberUpgradeNote.setMemberOrderNo("-");
			miMemberUpgradeNote.setUpdateType("3");
			miMemberUpgradeNote.setUpdateDate(curDate);
			miMemberUpgradeNote.setRemark("会员激活:"+bdWeek);
			jmiMemberUpgradeNoteManager.saveJmiMemberUpgradeNote(miMemberUpgradeNote);

			//更新奖金级别表
			jbdUserCardListManager.saveJbdUserCardList(jmiMember.getUserCode(), curDate,  jmiMember.getOriCard(),"3","1");
			
			jmiMember.setCardType( jmiMember.getOriCard());
			
		}
		
		dao.saveJmiMember(jmiMember);
	}

	public boolean getIsJumper(String userCode) {
		// TODO Auto-generated method stub
		return dao.getIsJumper(userCode);
	}

	public Map getMemberInfo(String userCode, String flag) {
		// TODO Auto-generated method stub
		return dao.getMemberInfo(userCode, flag);
	}

	public List getJWSMemberInfos(String userCode, String phone,String userName,String member_upgrade_day) {
		// TODO Auto-generated method stub
		return dao.getJWSMemberInfos( userCode,  phone, userName, member_upgrade_day);
	}

	public String getCheckBirthday(BindException errors, String birthday, Date birthdayDate,String characterCoding,int age) {
		String errorStr="";
		if(!StringUtil.isEmpty(birthday)){
			birthdayDate=DateUtil.convertStringToDate(birthday);
		}

		if(this.getAge(birthdayDate)<age){
/*    		errorStr=this.getCharacterValue(characterCoding,"member.age.tw");*/
    		errorStr=MessageFormat.format(this.getCharacterValue(characterCoding,"member.age.tw"), new String[]{age+""});
    		if(errors!=null){
				errors.rejectValue("birthday",errorStr,errorStr);
			}
		}
		return errorStr;
	}
	private int getAge(Date birthday){
		  Calendar cal = new GregorianCalendar();
		  cal.setTime(birthday);
		  Calendar now = new GregorianCalendar();
		  int res = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
		  if ((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH))|| (cal.get(Calendar.MONTH) == now.get(Calendar.MONTH) && cal.get(Calendar.DAY_OF_MONTH) > now.get(Calendar.DAY_OF_MONTH))) {
		      res--;
		  }
		  return res;
	}

	public void setJfiPayBankManager(JfiPayBankManager jfiPayBankManager) {
		this.jfiPayBankManager = jfiPayBankManager;
	}
	
	
	public String getCheckRecommendNo(BindException errors,String recommendNo,String defUserCode,String characterCoding) {
        JmiRecommendRef miRecommendRef = jmiRecommendRefDao.getJmiRecommendRef(recommendNo);
        String errorStr="";
		if (miRecommendRef == null) {
			errorStr=this.getCharacterValue(characterCoding,"miRecommendRef.isNotFound");
			if(errors!=null){
				errors.rejectValue("jmiRecommendRef.recommendJmiMember.userCode", errorStr);
			}
			return errorStr;
		}
		if(!StringUtil.isEmpty(defUserCode)){

			JmiRecommendRef defRecommendRef = jmiRecommendRefDao.getJmiRecommendRef(defUserCode);
			//判断推荐人是否在当前会员下
			String rdefIndex=defRecommendRef.getTreeIndex();
			String rIndex=miRecommendRef.getTreeIndex();
			if(rIndex.length()<rdefIndex.length()){
				errorStr=this.getCharacterValue(characterCoding,"miRecommendRef.isNotFound");
				if(errors!=null){
					errors.rejectValue("jmiRecommendRef.recommendJmiMember.userCode", errorStr);
				}
				return errorStr;
			}
			String rmemberIndexTmp = StringUtil.getLeft(rIndex, rdefIndex.length());
			if(!rdefIndex.equals(rmemberIndexTmp)){//不为会员的下级组织
				errorStr=this.getCharacterValue(characterCoding,"miRecommendRef.isNotFound");
				if(errors!=null){
					errors.rejectValue("jmiRecommendRef.recommendJmiMember.userCode",errorStr);
				}
				return errorStr;
			}
		}
		return errorStr;
	}
	
	public String getCheckLinkNo(BindException errors, String recommendNo, String linkNo,String defUserCode,String characterCoding) {
		JmiLinkRef miLinkRef = jmiLinkRefDao.getJmiLinkRef(linkNo);
        String errorStr="";
		if (miLinkRef == null) {
			errorStr=this.getCharacterValue(characterCoding,"miLinkRef.isNotFound");
			if(errors!=null){
				errors.rejectValue("jmiLinkRef.linkJmiMember.userCode", errorStr);
			}
			return errorStr;
		}
		//取推荐人
        JmiRecommendRef miRecommendRef = jmiRecommendRefDao.getJmiRecommendRef(recommendNo);
		if (miRecommendRef == null) {
			errorStr=this.getCharacterValue(characterCoding,"miRecommendRef.isNotFound");
			if(errors!=null){
				errors.rejectValue("jmiLinkRef.linkJmiMember.userCode", errorStr);
			}
			return errorStr;
		}
		// 接点人不是推荐人的下线
		String rTreeIndex = miRecommendRef.getJmiMember().getJmiLinkRef().getTreeIndex();
		String lTreeIndex = miLinkRef.getTreeIndex();
		if (lTreeIndex.length() < rTreeIndex.length() || !rTreeIndex.equals(StringUtil.getLeft(lTreeIndex, rTreeIndex.length()))) {
			errorStr=this.getCharacterValue(characterCoding,"miLinkRefMiRecommendRef.isNotEquals");
			if(errors!=null){
				errors.rejectValue("jmiLinkRef.linkJmiMember.userCode", errorStr, errorStr);
			}
			return errorStr;
		}
		
		//检查接点体系是否已满
		int maxLink = 6;//生成最大接点人数
		CommonRecord crm = new CommonRecord();
		crm.addField("linkNo",  linkNo);
		List miLinkRefs = jmiLinkRefDao.getMiLinkRefManagersByTree(crm);
		if(miLinkRef.getNum().compareTo(new BigDecimal(0))!=0){
			maxLink=miLinkRef.getNum().intValue()+2;
		}
		if(miLinkRefs.size()<6){
			maxLink=6;
		}
		if (miLinkRefs.size() >= maxLink) {// 接点体系最多只有6个下属部门
			errorStr=this.getCharacterValue(characterCoding,"miLinkRef.isFull");
			if(errors!=null){
				errors.rejectValue("jmiLinkRef.linkJmiMember.userCode", errorStr,errorStr);
			}
			return errorStr;
		}
		return errorStr;
		
	}
	
	//验证证件号
	public  String  getCheckIdNo(BindException errors,String papernumber,String defUserCode,String papertype,String companyCode,String characterCoding){
		JmiMember jmiMember=new JmiMember();
		jmiMember.setUserCode(defUserCode);
		jmiMember.setPapernumber(papernumber);
		jmiMember.setPapertype(papertype);
		String errorStr="";
		if (StringUtils.isEmpty(papernumber)) {
			errorStr= this.getCharacterValue(characterCoding,"miMember.papernumber");
			if(errors!=null){
				errors.rejectValue("papernumber", "isNotNull",new Object[] {errorStr }, "");
			}
		}else if(this.getCheckMiMemberIdNoByMiMember(jmiMember)){
	    	//验证身份证的唯一性
			errorStr=this.getCharacterValue(characterCoding,"miMember.idNo.isIn");
			if(errors!=null){
				errors.rejectValue("papernumber",errorStr);
			}

		}else if("1".equals(jmiMember.getPapertype())&&"CN".equals(companyCode)&&!getIdCardCheck(jmiMember.getPapernumber())){
			errorStr=this.getCharacterValue(characterCoding,"idNo.format");
			if(errors!=null){
				errors.rejectValue("papernumber",errorStr);
			}
    	}else if(!jmiBlacklistManager.getCheckJmiBlacklist(jmiMember.getPapertype(), jmiMember.getPapernumber())){
			errorStr=this.getCharacterValue(characterCoding,"idNo.blacklist");
			if(errors!=null){
				errors.rejectValue("papernumber",errorStr);
			}
    	}
		return errorStr;
	}

	public void setJmiSubStoreManager(JmiSubStoreManager jmiSubStoreManager) {
		this.jmiSubStoreManager = jmiSubStoreManager;
	}

	public String reSendJmiMember(String[] strCodes) {
		String res="";
		for (int i = 0; i < strCodes.length; i++) {
			if (!StringUtils.isEmpty(strCodes[i])) {
				JmiMember jmiMember=this.getJmiMember(strCodes[i]);
				String aa=saveEcpp(jmiMember);
				if(!StringUtil.isEmpty(aa)){
					res+=aa+"/r/n";
				}
			}
		}
		return res;
		
	}
	private String saveEcpp(JmiMember jmiMember){
			String res="";
			try {
				HashMap memberMap=new HashMap();
				memberMap.put("userCode", jmiMember.getUserCode().substring(2, jmiMember.getUserCode().length()));
				memberMap.put("email", jmiMember.getEmail());
				memberMap.put("realname", jmiMember.getMiUserName());
				memberMap.put("password", jmiMember.getSysUser().getPassword());
				memberMap.put("mobile", jmiMember.getMobiletele());
				memberMap.put("phone", jmiMember.getPhone());
				memberMap.put("countryCode", jmiMember.getCountryCode());
				memberMap.put("provice", jmiMember.getProvince());
				memberMap.put("city", jmiMember.getCity());
				memberMap.put("district", jmiMember.getDistrict());
				memberMap.put("companyCode", jmiMember.getCompanyCode());
				memberMap.put("address", jmiMember.getAddress());
				memberMap.put("postcode", jmiMember.getPostalcode());
				memberMap.put("papertype", jmiMember.getPapertype());
				memberMap.put("papernumber", jmiMember.getPapernumber());
				//memberMap.put("regIp", RequestUtil.getIpAddr(request));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				memberMap.put("regDate", sdf.format(jmiMember.getCreateTime()));
				ecRegisterSender.send(memberMap);
				jmiMember.getSysUser().setSendStatus(null);
			} catch (Exception e) {
				jmiMember.getSysUser().setSendStatus("1");
				e.printStackTrace();
				res=jmiMember.getUserCode().substring(2, jmiMember.getUserCode().length())+"-"+e.getMessage();
			}
			this.saveJmiMember(jmiMember);
			return res;
			
	}

	public void setJbdTravelPointManager(JbdTravelPointManager jbdTravelPointManager) {
		this.jbdTravelPointManager = jbdTravelPointManager;
	}

	public void changeMiMemberCardTypeAll(List<Map> jmiMembers,SysUser defSysUser) {
		for (Map map : jmiMembers) {
			String newCard=(String) map.get("newCard");
			JmiMember miMember= (JmiMember) map.get("jmiMember");
			Integer wweek=(Integer) map.get("wweek");
			String remark=(String) map.get("remark");
			miMember.setRemark((miMember.getRemark()==null?"":miMember.getRemark())+" "+remark);
    		this.changeMiMemberCardType(newCard,miMember,wweek,defSysUser);
		}
	}
	/**
	 * 批量修改会员级别
	 */
	public void changeMiMemberMemberLevelAll(List<Map> jmiMembers,SysUser defSysUser) {
		for (Map map : jmiMembers) {
			String newMember=(String) map.get("newMember");
			JmiMember miMember= (JmiMember) map.get("jmiMember");
			Integer wweek=(Integer) map.get("wweek");
			String remark=(String) map.get("remark");
			miMember.setRemark((miMember.getRemark()==null?"":miMember.getRemark())+" "+remark);
			//this.changeMiMemberCardType(newMember,miMember,wweek,defSysUser);
			
			this.changeMemberLevel(miMember, wweek, defSysUser, "1", Integer.parseInt(newMember), miMember.getMemberLevel());
		}
	}
	

	
	
	public void sendPcnsNewMember(String[] strCodes) {
		for (int i = 0; i < strCodes.length; i++) {
			if (!StringUtils.isEmpty(strCodes[i])) {
				JmiMember jmiMember=this.getJmiMember(strCodes[i]);
				dao.sendPcn(jmiMember, "NewAccount","","",null,"");
			}
		}
		
	}

	public void sendPcnMananger(JmiMember jmiMember, String operateType, String newCustomerLevel, String remark, SysUser defSysUser, String customerLevelNoteId) {
		dao.sendPcn(jmiMember, operateType, newCustomerLevel, remark, defSysUser, customerLevelNoteId);
		
	}

	public List getTWPromotions(Date calEndDate) {
		return dao.getTWPromotions(calEndDate);
	}

	public void activeMembers(List<Map> jmiMembers) {
		Date curDate=new Date();
		for (Map map : jmiMembers) {
			String newActive=(String) map.get("newActive");
			JmiMember miMember= (JmiMember) map.get("jmiMember");
			String remark=(String) map.get("remark");
			miMember.setRemark((miMember.getRemark()==null?"":miMember.getRemark())+" "+remark);
			
			if("0".equals(newActive)){
				miMember.setActive(newActive);//激活
				miMember.getSysUser().setState("1");
				miMember.setActiveTime(curDate);
			}else{
				miMember.setActive(newActive);
				miMember.getSysUser().setState("0");
			}
			this.saveJmiMember(miMember);
		}	
	}
	
	public void saveStateMembers(List<Map> jmiMembers,SysUser defSysUser) {
		Date curDate=new Date();
		for (Map map : jmiMembers) {
			String newState=(String) map.get("newState");
			JmiMember miMember= (JmiMember) map.get("jmiMember");
			
			miMember.getSysUser().setState(newState);
	
			
			String remark=(String) map.get("remark");
			miMember.setRemark((miMember.getRemark()==null?"":miMember.getRemark())+" "+remark);
			
			JmiStateLog jmiStateLog=new JmiStateLog();
			jmiStateLog.setCreateNo(defSysUser.getUserCode());
			jmiStateLog.setCreateTime(curDate);
			jmiStateLog.setLogType("2");
			jmiStateLog.setState(newState);
			jmiStateLog.setUserCode(miMember.getUserCode());
			jmiStateLogManager.saveJmiStateLog(jmiStateLog);
			
			this.saveJmiMember(miMember);
		}	
	}

	// add by gw 2014-05-14 更改加盟店类型
	public void saveIsstoreMembers(List<Map> jmiMembers) {
		for (Map map : jmiMembers) {
			String newIsstore=(String) map.get("newIsstore");
			JmiMember miMember= (JmiMember) map.get("jmiMember");
			
			//在更改会员的加盟店类型之前,先更改会员的角色   ----开始
			String oldIsstore=miMember.getIsstore();
			if(!oldIsstore.equals(newIsstore)){

				miMember.setIsstore(newIsstore);
				String memberRoleIdStr=getRole(miMember);   
				
				//获取该会员的角色对象
				SysUserRole sysUserRole= sysUserRoleManager.getSysUserRoleByUserCode(miMember.getUserCode());
				//String memberRoleId =(String) Constants.sysConfigMap.get(miMember.getCompanyCode()).get(memberRoleIdStr);

				SysRole memberSysRole=sysRoleManager.getSysRoleByCode(memberRoleIdStr);
				sysUserRole.setRoleId(memberSysRole.getRoleId());
				sysUserRoleManager.saveSysUserRole(sysUserRole);
				
			}
			//在更改会员的加盟店类型之前,先更改会员的角色   ----结束

			
			String remark=(String) map.get("remark");
			miMember.setRemark((miMember.getRemark()==null?"":miMember.getRemark())+" "+remark);
			
			
			this.saveJmiMember(miMember);
		}	
		
	}
	
	public void saveRemarkMembers(List<Map> jmiMembers) {
		for (Map map : jmiMembers) {
			JmiMember miMember= (JmiMember) map.get("jmiMember");
			String remark=(String) map.get("remark");
			miMember.setRemark((miMember.getRemark()==null?"":miMember.getRemark())+" "+remark);
			this.saveJmiMember(miMember);
		}	
	}
	public void freezeMembers(List<Map> jmiMembers){


		for (Map map : jmiMembers) {
			String newFreezeStatus=(String) map.get("newFreezeStatus");
			JmiMember miMember= (JmiMember) map.get("jmiMember");
			String remark=(String) map.get("remark");
			miMember.setRemark((miMember.getRemark()==null?"":miMember.getRemark())+" "+remark);
			SysRole memberSysRole=null;
			if("1".equals(newFreezeStatus)){
				memberSysRole=sysRoleManager.getSysRoleByCode("jocs.member.role.freeze");
			}else{
				String memberRoleIdStr=getRole(miMember);
				memberSysRole=sysRoleManager.getSysRoleByCode(memberRoleIdStr);
			}

			
			miMember.setFreezeStatus(StringUtil.formatInt(newFreezeStatus));
			SysUserRole sysUserRole= sysUserRoleManager.getSysUserRoleByUserCode(miMember.getUserCode());
			sysUserRole.setRoleId(memberSysRole.getRoleId());
			sysUserRoleManager.saveSysUserRole(sysUserRole);
			
			
			this.saveJmiMember(miMember);
		}	
		
		
	}
	public void setFiBcoinBalanceDao(FiBcoinBalanceDao fiBcoinBalanceDao) {
		this.fiBcoinBalanceDao = fiBcoinBalanceDao;
	}

	public void setFiCcoinBalanceDao(FiCcoinBalanceDao fiCcoinBalanceDao) {
		this.fiCcoinBalanceDao = fiCcoinBalanceDao;
	}
	

	/**
	 * 验证身份证有效性
	 * @param id
	 * @return
	 */
	public boolean getIdCardFormatCheckRegisterCN(String idcard) {
		if (idcard.length() == 15) {
			idcard = uptoeighteen(idcard);
		}
		if (idcard.length() != 18) {
			return false;
		}
		String verify = idcard.substring(17, 18);
		if (verify.equals(getVerify(idcard))) {
			return true;
		}
		return false;
	} 
	
	public String getVerify(String eightcardid) {
		
		int[] wi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
		int[] vi = { 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 };
		int[] ai = new int[18];
		
		
		int remaining = 0;
		if (eightcardid.length() == 18) {
			eightcardid = eightcardid.substring(0, 17);
		}
		if (eightcardid.length() == 17) {
			int sum = 0;
			for (int i = 0; i < 17; i++) {
				String k = eightcardid.substring(i, i + 1);
				ai[i] = Integer.parseInt(k);
			}
			for (int i = 0; i < 17; i++) {
				sum = sum + wi[i] * ai[i];
			}
			remaining = sum % 11;
		}
		return remaining == 2 ? "X" : String.valueOf(vi[remaining]);
	}
	public String uptoeighteen(String fifteencardid) {
		String eightcardid = fifteencardid.substring(0, 6);
		eightcardid = eightcardid + "19";
		eightcardid = eightcardid + fifteencardid.substring(6, 15);
		eightcardid = eightcardid + getVerify(eightcardid);
		return eightcardid;
	}

	public Map getZcwMemberByUserCode(String userCode) {
		return dao.getZcwMemberByUserCode(userCode);
	}

	public List getJmiTeamType() {
		return dao.getJmiTeamType();
	}
	private boolean getPattern(String expressions,String str){
		Pattern pattern = Pattern.compile(expressions);
		Matcher matcher = pattern.matcher(str);
		if(!matcher.matches()){
			return true;
		}
		return false;
	}

	public List getjbdUserStateList(CommonRecord crm, Pager pager) {
		return dao.getjbdUserStateList(crm, pager);
	}

	@Override
	public List getJmiClubs(CommonRecord crm, Pager pager) {
		return dao.getJmiClubs(crm, pager);
	}

    @Override
    public void updateStarRemark(List<JmiMember> lists)
    {
        List<String> sqls = new ArrayList<String>();
        for(JmiMember jmiMember:lists) {
            String spouseName = StringUtils.isNotEmpty(jmiMember.getSpouseName())?jmiMember.getSpouseName():"";
            String spouseIdno = StringUtils.isNotEmpty(jmiMember.getSpouseIdno())?jmiMember.getSpouseIdno():"";
            String titleRemark = StringUtils.isNotEmpty(jmiMember.getTitleRemark())?jmiMember.getTitleRemark():"";
            String userCode = jmiMember.getUserCode();
            String sql = "update jmi_member m set m.spouse_name='"+spouseName+"' , m.spouse_idno='"+spouseIdno+"' , m.title_remark='"+titleRemark+"' where m.user_code='"+userCode+"'";
            sqls.add(sql);
//            this.dao.saveObject(jmiMember);
        }
        if(sqls.size()!=0){
            String[] sqlArr = new String[sqls.size()];
            this.jdbcTemplate.batchUpdate(sqls.toArray(sqlArr));
        }
    }

    //接口JOCS
    public List getCheckJmiMemberDubbo(Map<String,String> map){
    	
    	//boolean isNotPass = false;
    	String recommendNo=map.get("recommendNo");
    	String linkNo=map.get("linkNo");
    	String miUserName=map.get("miUserName");
    	String papertype=map.get("papertype");
    	String papernumber=map.get("papernumber");
    	String defSysUserCode=map.get("defSysUserCode");
    	String defCharacterCoding=map.get("defCharacterCoding");
    	
    	
    	String errorStr="";
    	List list=new ArrayList();
		if(!StringUtils.isEmpty(miUserName) &&!StringUtils.isEmpty(papernumber) && !StringUtil.isEmpty(linkNo) ){
			String papernumberUserCode=dao.getPapernumberUserCode(papernumber,recommendNo);
			if(!StringUtil.isEmpty(papernumberUserCode)){
				JmiMember jmiMemberPapernumber=dao.getJmiMember(papernumberUserCode);
				if(!miUserName.equals(jmiMemberPapernumber.getMiUserName())){
					errorStr=this.getErrorsCode("idNo.linkNo.userName.no", "",defCharacterCoding);
					if(!StringUtil.isEmpty(errorStr)){
						list.add(errorStr);
					}
				}
			}
		}
		errorStr=getCheckRecommendNo(recommendNo, defSysUserCode,defCharacterCoding);

		if(!StringUtil.isEmpty(errorStr)){
			list.add(errorStr);
		}
    	errorStr=getCheckLinkNo(recommendNo, linkNo, defSysUserCode, defCharacterCoding);

		if(!StringUtil.isEmpty(errorStr)){
			list.add(errorStr);
		}
    	errorStr=getCheckIdNo(papernumber, papertype, defSysUserCode, defCharacterCoding);

		if(!StringUtil.isEmpty(errorStr)){
			list.add(errorStr);
		}
		
    	return list;
    }
   /* 	*//**
	 * 检验推荐人
	 * @param errors
	 * @param recommendNo
	 * @param defUserCode
	 * @return
	 *//*/*/
	public String getCheckRecommendNo(String recommendNo,String defUserCode,String defCharacterCoding) {
        String errorStr="";
        if(StringUtil.isEmpty(recommendNo)){
        	errorStr=this.getErrorsFormatCode( "isNotNull", "jmiRecommendRef.recommendJmiMember.userCode", "miMember.recommendNo",defCharacterCoding);
        }else if(jmiRecommendRefDao.getJmiRecommendRef(recommendNo)==null){
        	errorStr=this.getErrorsCode( "miRecommendRef.isNotFound", "jmiRecommendRef.recommendJmiMember.userCode",defCharacterCoding);
        }else{
            if(!StringUtil.isEmpty(defUserCode)&&!StringUtil.isEmpty(recommendNo)){//判断推荐人是否在当前登陆会员下
    			JmiRecommendRef defRecommendRef = jmiRecommendRefDao.getJmiRecommendRef(defUserCode);
    			JmiRecommendRef miRecommendRef = jmiRecommendRefDao.getJmiRecommendRef(recommendNo);
    			
    			
    			if(!StringUtil.getCheckIsDownline(defRecommendRef.getTreeIndex(), miRecommendRef.getTreeIndex())){
    				errorStr=this.getErrorsCode( "miRecommendRef.isNotFound", "jmiRecommendRef.recommendJmiMember.userCode",defCharacterCoding);
    			}
    		}
        }
		return errorStr;
	}
	
/*		*//**
	 * 验证安置人
	 * @param errors
	 * @param recommendNo
	 * @param linkNo
	 * @param defUserCode
	 * @return
	 *//*/*/
	public String getCheckLinkNo(String recommendNo, String linkNo,String defUserCode,String defCharacterCoding) {
		String errorStr="";
		if(StringUtil.isEmpty(linkNo)){
			errorStr=this.getErrorsFormatCode( "isNotNull", "jmiLinkRef.linkJmiMember.userCode", "miMember.linkNo",defCharacterCoding);
		}else if(jmiLinkRefDao.getJmiLinkRef(linkNo)==null){
			errorStr=this.getErrorsCode( "miLinkRef.isNotFound", "jmiLinkRef.linkJmiMember.userCode",defCharacterCoding);
		}else if(StringUtil.isEmpty(getCheckRecommendNo(null, recommendNo, defUserCode,defCharacterCoding))){
			JmiRecommendRef miRecommendRef = jmiRecommendRefDao.getJmiRecommendRef(recommendNo);
			JmiLinkRef miLinkRef =jmiLinkRefDao.getJmiLinkRef(linkNo);
			String rTreeIndex = miRecommendRef.getJmiMember().getJmiLinkRef().getTreeIndex();
			String lTreeIndex = miLinkRef.getTreeIndex();
			if (lTreeIndex.length() < rTreeIndex.length() || !rTreeIndex.equals(StringUtil.getLeft(lTreeIndex, rTreeIndex.length()))) {
				errorStr=this.getErrorsCode( "miLinkRefMiRecommendRef.isNotEquals", "jmiLinkRef.linkJmiMember.userCode",defCharacterCoding);
			}
		}
		return errorStr;
	}
	
	private int getMaxLinkNo(JmiLinkRef miLinkRef,String linkNo){
		int maxLink = 6;//生成最大接点人数
		List miLinkRefs =jmiLinkRefDao.getLinkRefbyLinkNo(linkNo);
		if(miLinkRef.getNum().compareTo(new BigDecimal(0))!=0){
			maxLink=miLinkRef.getNum().intValue()+2;
		}
		if(miLinkRefs.size()<6){
			maxLink=6;
		}
		return maxLink;
	}
	
	
	/**
	 * 检查身份证的唯一性
	 * @param errors
	 * @param papernumber
	 * @param defUserCode
	 * @param papertype
	 * @param companyCode
	 * @return
	 */
	public String getCheckIdNo(String  papernumber,String papertype,String defUserCode,String defCharacterCoding){
		JmiMember jmiMember=new JmiMember();
		jmiMember.setUserCode(defUserCode);
		jmiMember.setPapernumber(papernumber);
		jmiMember.setPapertype(papertype);
		String errorStr="";
		
		if (StringUtils.isEmpty(jmiMember.getPapernumber())) {//是否为空 
			errorStr=this.getErrorsFormatCode( "isNotNull", "papernumber", "miMember.papernumber",defCharacterCoding);
		}else if(dao.getCheckMiMemberIdNoByMiMember(jmiMember)){//验证身份证的唯一性
			errorStr=this.getErrorsCode( "miMember.idNo.isIn", "papernumber",defCharacterCoding);
		}
		return errorStr;
	}
	
	
	public static String getErrorsFormatCode(String msg,String field,String fieldMsg,String defCharacterCoding){
		String errorStr=MessageFormat.format(LocaleUtil.getLocalText(defCharacterCoding,msg), new String[]{LocaleUtil.getLocalText(defCharacterCoding,fieldMsg)});
		return errorStr;
	}
	public static String getErrorsCode(String msg,String field,String defCharacterCoding){
		String errorStr="";
		errorStr=LocaleUtil.getLocalText(defCharacterCoding,msg);
		return errorStr;
	}

/*	public String getTeamType(String recommendNo){
		List teams=dao.getJmiTeamType();
		for (int i = 0; i < teams.size(); i++) {
			Map map=(Map) teams.get(i);
			String user_code=map.get("user_code").toString();
			String member_type=map.get("member_type").toString();
			
			JmiRecommendRef topMemberRecommendRef=jmiRecommendRefDao.getJmiRecommendRef(user_code);
			JmiRecommendRef registerNoRecommendRef=jmiRecommendRefDao.getJmiRecommendRef(recommendNo);
			
			if(topMemberRecommendRef!=null){
				String topIndex=topMemberRecommendRef.getTreeIndex();
				String registerNoRecommendRefIndex=registerNoRecommendRef.getTreeIndex();
				String rmemberIndexTmp = StringUtil.getLeft(registerNoRecommendRefIndex, topIndex.length());
				if(registerNoRecommendRefIndex.length()>=topIndex.length() && topIndex.equals(rmemberIndexTmp) ){
					return member_type;
				}
			}
		}
		return "";
	}*/
	public void getTeamType(JmiMember jmiMember){
		
		
		List list=jdbcTemplate.queryForList("select fn_get_member_type('"+jmiMember.getRecommendNo()+"') as member_type from dual");
		
		if(!list.isEmpty()){
			Map map=(Map) list.get(0);
			Object member_type=map.get("member_type");
			if(member_type!=null){
				jmiMember.setMemberType(member_type.toString());
			}
			
		}
	
	}
    //

	public void changeMemberLevel(JmiMember jmiMember,Integer wweek,SysUser defSysUser,String isValid,Integer newMemberLevel,Integer oldMemberLevel){
			Date curDate=new Date();
			
			
			
			//中国续约功能，如果都是未冻结状态，未曾经冻结，卡别由0更新为其他卡别，则更新续约时间
			if(0==jmiMember.getFreezeStatus()&&0==jmiMember.getBeforeFreezeStatus()&& 0==jmiMember.getMemberLevel()){
				BdPeriod bdPeriod = bdPeriodManager.getBdPeriodByFormatedWeek(wweek.toString());	
				Integer startMonth = bdPeriod.getWyear()*100 + bdPeriod.getWmonth();
				String year = startMonth.toString().substring(0, 4);
				String month = startMonth.toString().substring(4, 6);
				String period = bdPeriodManager.getFutureBdYearMonth(year, month, 12);
				jmiMember.setStartWeek(startMonth);
				jmiMember.setValidWeek(Integer.parseInt(period));
			}
			//
			
			//checkdate为空，待审会员改成正式会员
	    	if(null==jmiMember.getCheckDate()&&0==jmiMember.getMemberLevel() &&  newMemberLevel >500 && "1".equals(isValid)){
	    		jmiMember.setCheckNo(defSysUser.getUserCode());
				jmiMember.setCheckDate(curDate);
				jmiMember.setNotFirst(1);
			}
	    	/**
	    	 * 新增
	    	 */
	    	//手工定级从待审定成云客时jmiMember的checkDate为空时再从云客定云客以上级别时给checkDate赋值
	    	if(jmiMember.getMemberLevel()==500 && newMemberLevel>500 && "1".equals(isValid)){
	    		jmiMember.setCheckNo(defSysUser.getUserCode());
				jmiMember.setCheckDate(curDate);
				jmiMember.setNotFirst(1);
	    	}

    		java.util.Calendar startc=java.util.Calendar.getInstance();
    		startc.set(2017, 6, 1, 00, 00, 00);
    		java.util.Date cloud_date=startc.getTime();
    		java.util.Date cloud_end_date=null;
    		
	    	if(  newMemberLevel >1500 || newMemberLevel==500){
	    		//9.29后财年开始
	    		if(curDate.after(cloud_date)){//7月1号后云店资格为1年
	    			String validity=(String) Constants.sysConfigMap.get("AA").get("validity.period");
	    			cloud_end_date=DateUtil.getDateOffset(curDate, 2, StringUtil.formatInt(validity));
	    		}else{//7月1号前云店资格为2018.6.30
	    			java.util.Calendar cloudEndCalendar=java.util.Calendar.getInstance();
	    			cloudEndCalendar.set(2018, 5, 30, 23, 59, 59);
		    		cloud_end_date=cloudEndCalendar.getTime();
	    		}
	    		if(jmiMember.getCloudStartdate()==null){
	    			jmiMember.setCloudStartdate(curDate);
	    		}

	    		if(jmiMember.getCloudEnddate()==null){
	    			jmiMember.setCloudEnddate(cloud_end_date);
	    		}
	    		
	    		
	    		if(newMemberLevel>1500){
		    		if(jmiMember.getStandardMkTime()==null){
		    			jmiMember.setStandardMkTime(curDate);
		    		}
	    		}else {
		    		if(jmiMember.getStandardTime()==null){
		    			jmiMember.setStandardTime(curDate);
		    		}
	    			
	    		}
	    		jmiMember.setIsCloudshop("1");
/*	    		if(newMemberLevel>500){
	    			jmiMember.setMemberUserType("1");
	    		}*/
	    		
	    	}
	    	
	    	
	    	
	    	if(newMemberLevel==500){
	    		jmiMember.setMemberUserType("2");
	    	}else if(newMemberLevel >=1500){
	    		jmiMember.setMemberUserType("1");
	    	}else{
	    		jmiMember.setMemberUserType("3");
	    	}
	    	
	    	
	    	SysUserRole sysUserRole= sysUserRoleManager.getSysUserRoleByUserCode(jmiMember.getUserCode());
	    	String memberRoleId ="";
	    	SysRole sysUserSysRole=sysRoleManager.getSysRole(sysUserRole.getRoleId().toString());
	    	
	    	if("jocs.member.role.0".equals(sysUserSysRole.getRoleCode()) || "cn.member.41".equals(sysUserSysRole.getRoleCode()) 
	    			|| "cn.member.1000".equals(sysUserSysRole.getRoleCode())    || "cn.member.1500".equals(sysUserSysRole.getRoleCode())
	    			|| "cn.member.2000".equals(sysUserSysRole.getRoleCode())  	|| "cn.member.3000".equals(sysUserSysRole.getRoleCode())
	    			|| "cn.member.4000".equals(sysUserSysRole.getRoleCode()) 	|| "cn.member.5000".equals(sysUserSysRole.getRoleCode())
	    			|| "cn.member.41.0".equals(sysUserSysRole.getRoleCode())){
	    		
	    		if(newMemberLevel.intValue()==0){
		    		memberRoleId = Constants.sysConfigMap.get(jmiMember.getCompanyCode()).get("jocs.member.role.0");
		    	}else if(newMemberLevel==500){
		    		memberRoleId ="cn.member.41.0";
		    	}else{
		    		memberRoleId ="cn.member."+newMemberLevel;
		    	}
		    	
	    		//String memberRoleId =(String) Constants.sysConfigMap.get(jmiMember.getCompanyCode()).get("jocs.member.role.normal");

				SysRole memberSysRole=sysRoleManager.getSysRoleByCode(memberRoleId);
				sysUserRole.setRoleId(memberSysRole.getRoleId());
				sysUserRoleManager.saveSysUserRole(sysUserRole);
	    		
	    	}
	    	
	    	
	    	
			
	    	jmiMember.setMemberLevel(newMemberLevel);
			this.saveJmiMember(jmiMember);
			JmiLevelNote jmiLevelNote=new JmiLevelNote();
			jmiLevelNote.setCreateNo(defSysUser.getUserCode());
			jmiLevelNote.setCreateTime(curDate);
			jmiLevelNote.setNewMemberLevel(newMemberLevel);
			jmiLevelNote.setOldMemberLevel(oldMemberLevel);
			jmiLevelNote.setUpdateType("1");
			jmiLevelNote.setUserCode(jmiMember.getUserCode());
			jmiLevelNoteManager.saveJmiLevelNote(jmiLevelNote);

			//插入每日计算表
			JbdSummaryList jbdSummaryList=new JbdSummaryList();
			jbdSummaryList.setUserCode(jmiMember.getUserCode());
			jbdSummaryList.setCardType(newMemberLevel+"");
			jbdSummaryList.setInType(5);
			jbdSummaryList.setCreateTime(curDate);
			jbdSummaryList.setWweek(wweek);
			jbdSummaryListManager.saveJbdSummaryList(jbdSummaryList);
		
	}
	
	private String getRole(JmiMember jmiMember){
		String memberRoleIdStr="";
		if("1".equals(jmiMember.getIsstore())||"2".equals(jmiMember.getIsstore())){
			memberRoleIdStr="jocs.member.role.store"+jmiMember.getIsstore();
		}else{
			if(jmiMember.getMemberLevel().intValue()==0){
				memberRoleIdStr="jocs.member.role.0";
			}else{
				memberRoleIdStr="cn.member."+jmiMember.getMemberLevel();
			}
		} 
		return memberRoleIdStr;
	}
	@Override
	public List getjbdUserDelList(CommonRecord crm, Pager pager) {
		return dao.getjbdUserDelList(crm, pager);
	}
	
	@Override
    public RspEntity getJmiMemberForGHB(String jsonString)
    {
        log.info("接收接收过河兵查询会员信息开始：");
        RspEntity rspEntity = new RspEntity();
        if(StringUtil.isEmpty(jsonString)) {
            log.info("接收过河兵查询会员信息发生错误：json string is null!");
            rspEntity.setSub_msg(" json string is null ");
            return this.getRspEntityString(rspEntity);
        }
        try{
            //将json字符串转换成java对象
            JmiMemberForGHB jmiMemberForGHB = InterfaceJsonUtil.returnnoJsonToModel(jsonString,JmiMemberForGHB.class,null);
            if(null==jmiMemberForGHB){
                  log.info("接收过河兵查询会员信息发生错误：json to jmiMemberForGHB object is null!");
                  rspEntity.setSub_msg(" json to jmiMemberForGHB object is null! ");
                  return this.getRspEntityString(rspEntity);
            } else if(StringUtil.isEmpty(jmiMemberForGHB.getUserCode())&&StringUtil.isEmpty(jmiMemberForGHB.getMobiletele())){
                log.info("接收过河兵查询会员信息发生错误：userCode and mobiletele is all null!");
                rspEntity.setSub_msg(" userCode and mobiletele is all null!! ");
                return this.getRspEntityString(rspEntity);
            } else{
                String sql="select t.user_code,t.mi_user_name,t.mobiletele,t.sex,t.papernumber,t.exit_date,t.active,t.isstore from jmi_member t where t.user_code=? or t.MOBILETELE=?";
                List result = this.jdbcTemplate.queryForList(sql,new Object[]{jmiMemberForGHB.getUserCode(),jmiMemberForGHB.getMobiletele()});
                JSONArray jsonArray = JSONArray.fromObject(result);
                rspEntity.setContent(jsonArray.toString());
                rspEntity.setSub_msg(Constants.INTERFACE_NORMAL);//正常结果返回
                log.info("接收过河兵查询会员信息成功,返回json："+jsonArray.toString());
                return this.getRspEntityString(rspEntity);
            }
        } catch(Exception e){
            log.error("接收过河兵查询会员信息发生错误："+e);
            rspEntity.setSub_msg(Constants.INTERFACE_JSON_FORMAT);
            return this.getRspEntityString(rspEntity);
        }
    }
	
	private RspEntity getRspEntityString(RspEntity rspEntity){
        String sub_msg = rspEntity.getSub_msg();
        if(!StringUtil.isEmpty(sub_msg) && Constants.INTERFACE_NORMAL.equals(sub_msg)){
            rspEntity.setRsp(Constants.SUCCESS_STRING);//succ表明是接口数据保存成功
        }else{
            rspEntity.setCode(Constants.NO_SUCCESS_STRING);//e000006表明应用参数错误
        }
        return rspEntity;
    }
	@Override
	public void saveInfoMembers(List<Map> list) {
		
		for (Map map : list) {
			String userCode=map.get("userCode").toString();
			String firstName=map.get("firstName").toString();
			String lastName=map.get("lastName").toString();
			String mobiletele=map.get("mobiletele").toString();
			String papertype=map.get("papertype").toString();
			String papernumber=map.get("papernumber").toString();
			JmiMember jmiMember=dao.getJmiMember(userCode);
			if(!StringUtil.isEmpty(firstName)){
				jmiMember.setFirstName(firstName);
			}
			if(!StringUtil.isEmpty(lastName)){
				jmiMember.setLastName(lastName);
			}
			if(!StringUtil.isEmpty(mobiletele)){
				jmiMember.setMobiletele(mobiletele);
			}
			if(!StringUtil.isEmpty(papertype)){
				jmiMember.setPapertype(papertype);
			}
			if(!StringUtil.isEmpty(papernumber)){
				jmiMember.setPapernumber(papernumber);
			}
			getSetUserName(jmiMember);
			
			dao.saveJmiMember(jmiMember);	
		}
		
	}
	

	public void exitDateMembers(List<Map> jmiMembers) {
		Date curDate=new Date();
		for (Map map : jmiMembers) {
			String newExitDate=(String) map.get("newExitDate");
			JmiMember miMember= (JmiMember) map.get("jmiMember");
			String remark=(String) map.get("remark");
			miMember.setRemark((miMember.getRemark()==null?"":miMember.getRemark())+" "+remark);
			
			if("0".equals(newExitDate)){
				miMember.setExitDate(null);
				miMember.getSysUser().setState("1");
			}else{
				miMember.setExitDate(curDate);
				miMember.getSysUser().setState("0");
			}
			this.saveJmiMember(miMember);
		}	
	}
	@Override
	public List getJmiMemberUpdate() {
		return dao.getJmiMemberUpdate();
	}
	

    public RspEntity getUpRecommendNo(String jsonString) {
    	RspEntity rspEntity = new RspEntity();
        if(StringUtil.isEmpty(jsonString)) {
            rspEntity.setSub_msg(" json string is null ");
            return this.getRspEntityString(rspEntity);
        }
        try{
        	net.sf.json.JSONObject jsonStu = net.sf.json.JSONObject.fromObject(jsonString);
        	String userCode=jsonStu.getString("user_code");
        	
        	JmiMember jmiMember=this.getJmiMember(userCode);
        	String recommendNo=jmiMember.getRecommendNo();
        	List list=new ArrayList();
        	int layer=1;
        	if(!"8888888888".equals(userCode)){
        		while (!"8888888888".equals(recommendNo) && layer<=2 ) {
    				Map map=new HashMap();
    				jmiMember=this.getJmiMember(recommendNo);
    				map.put("user_code", jmiMember.getUserCode());
    				map.put("phone", jmiMember.getMobiletele());
    				map.put("layer", layer);
    				list.add(map);
    				layer++;
    				recommendNo=jmiMember.getRecommendNo();
    			}
        	}
        	JSONArray jsonArray = JSONArray.fromObject(list);
        	rspEntity.setContent(jsonArray.toString());
            rspEntity.setSub_msg(Constants.INTERFACE_NORMAL);
            return this.getRspEntityString(rspEntity);
        	
        } catch(Exception e){
            rspEntity.setSub_msg(Constants.INTERFACE_JSON_FORMAT);
            return this.getRspEntityString(rspEntity);
        }
        
    }
    
    @Override
	public String getCheckUserCodeCloudshop(String userCode) {
		return dao.getCheckUserCodeCloudshop(userCode);
	}

	@Override
	public void updateJmiMemberCloudshopPhone(JmiMember member) {
		dao.updateJmiMemberCloudshopPhone(member);
	}

	@Override
	public String getCheckUserCodeCloudshoPhone(String phone,String userCode) {
		
		return dao.getCheckUserCodeCloudshoPhone(phone,userCode);
	}
	
	public void saveExitMimember(JmiMember member,JbdSummaryList jbdSummaryList){
		dao.saveJmiMember(member);
		jbdSummaryListManager.saveJbdSummaryList(jbdSummaryList);
	}
}
