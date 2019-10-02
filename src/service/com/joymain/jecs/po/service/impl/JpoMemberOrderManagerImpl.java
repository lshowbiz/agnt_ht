
package com.joymain.jecs.po.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.ecap.activemq.EcCoinSender;
import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.model.JbdSummaryList;
import com.joymain.jecs.bd.model.JbdUserValidList;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.bd.service.JbdSummaryListManager;
import com.joymain.jecs.bd.service.JbdUserCardListManager;
import com.joymain.jecs.fi.dao.JfiBankbookBalanceDao;
import com.joymain.jecs.fi.dao.JfiBankbookJournalDao;
import com.joymain.jecs.fi.model.FiBcoinBalance;
import com.joymain.jecs.fi.model.FiCoinLog;
import com.joymain.jecs.fi.model.JfiBankbookBalance;
import com.joymain.jecs.fi.model.JfiBankbookJournal;
import com.joymain.jecs.fi.service.FiBankbookJournalManager;
import com.joymain.jecs.fi.service.FiBcoinBalanceManager;
import com.joymain.jecs.fi.service.FiBcoinJournalManager;
import com.joymain.jecs.fi.service.FiCoinLogManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.mi.dao.JmiMemberDao;
import com.joymain.jecs.mi.dao.JmiMemberUpgradeNoteDao;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiMemberUpgradeNote;
import com.joymain.jecs.mi.model.JmiStore;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.service.JmiStoreManager;
import com.joymain.jecs.pd.service.PdSendInfoManager;
import com.joymain.jecs.pm.dao.JpmProductSaleNewDao;
import com.joymain.jecs.pm.dao.JpmSalePromoterDao;
import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.pm.model.JpmSalePromoter;
import com.joymain.jecs.pm.model.JpmSalepromoterPro;
import com.joymain.jecs.pm.model.JpmSmssendInfo;
import com.joymain.jecs.pm.service.JmiMemberTeamManager;
import com.joymain.jecs.pm.service.JpmCardSeqManager;
import com.joymain.jecs.pm.service.JpmProductSaleManager;
import com.joymain.jecs.po.dao.JpoBankBookPayListDao;
import com.joymain.jecs.po.dao.JpoMemberOrderDao;
import com.joymain.jecs.po.dao.JpoMemberOrderFeeDao;
import com.joymain.jecs.po.dao.JpoMemberOrderListDao;
import com.joymain.jecs.po.dao.JpoMovieDao;
import com.joymain.jecs.po.model.JpoBankBookPayList;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderFee;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.po.model.JpoMovie;
import com.joymain.jecs.po.model.JpoProductNumLimit;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.po.service.JpoProductNumLimitManager;
import com.joymain.jecs.pr.service.JprRefundManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysUserDao;
import com.joymain.jecs.sys.model.SysListValue;
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.model.SysUserRole;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.sys.service.SysListValueManager;
import com.joymain.jecs.sys.service.SysRoleManager;
import com.joymain.jecs.sys.service.SysUserRoleManager;
import com.joymain.jecs.util.ConfigUtil;
import com.joymain.jecs.util.GlobalVar;
import com.joymain.jecs.util.ListUtil;
import com.joymain.jecs.util.ServerDateUtil;
import com.joymain.jecs.util.SmsSend;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.io.FileUtil;
import com.joymain.jecs.util.json.JSONArray;
import com.joymain.jecs.util.json.JSONObject;
import com.joymain.jecs.util.string.StringUtil;


public class JpoMemberOrderManagerImpl extends BaseManager implements JpoMemberOrderManager { 
    private JpoMemberOrderDao dao;
    private JpoMemberOrderListDao jpoMemberOrderListDao;
    private JpoMemberOrderFeeDao jpoMemberOrderFeeDao;
    private JfiBankbookJournalManager jfiBankbookJournalManager;
    private SysUserDao sysUserDao;
    private JmiMemberDao jmiMemberDao;
    private JmiMemberUpgradeNoteDao jmiMemberUpgradeNoteDao;
    private SysRoleManager sysRoleManager;
	private SysUserRoleManager sysUserRoleManager;
    private PdSendInfoManager pdSendInfoManager;
    private SysListValueManager sysListValueManager;
    private JbdUserCardListManager jbdUserCardListManager;
    private JpmCardSeqManager jpmCardSeqManager;
    private BdPeriodManager bdPeriodManager;
    private JmiStoreManager jmiStoreManager;
    private FiCoinLogManager fiCoinLogManager;
    private EcCoinSender ecCoinSender;
    private JpmProductSaleManager jpmProductSaleManager;
    private JbdSummaryListManager jbdSummaryListManager;
    private AlCompanyManager alCompanyManager;
    private AlStateProvinceManager alStateProvinceManager;
    private FiBcoinBalanceManager fiBcoinBalanceManager = null;
    private FiBcoinJournalManager fiBcoinJournalManager = null;
    private FiBankbookJournalManager fiBankbookJournalManager = null;
    private SysIdManager sysIdManager = null;
    private static final String fileName="order_check.txt";
    //private JpmSalePromoterManager jpmSalePromoterManager;
    private JpmSalePromoterDao jpmSalePromoterDao;
    private JmiMemberManager jmiMemberManager;
    private JmiMemberTeamManager jmiMemberTeamManager;
    private JpoMovieDao jpoMovieDao;
    private JprRefundManager jprRefundManager;
    
    private JpoProductNumLimitManager jpoProductNumLimitManager;
    
    private JpmProductSaleNewDao jpmProductSaleNewDao;//Modify By WuCF 20140312 添加发送短信写入数据库操作
    private JpoBankBookPayListDao jpoBankBookPayListDao;

	/**
	 * 更改审核日期
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 * @param changeDate
	 */
    public void changeJpoMemberOrderDate(JpoMemberOrder jpoMemberOrder,SysUser operatorSysUser,Date changeDate){
    	Date oleCheckDate = jpoMemberOrder.getCheckDate();
		jpoMemberOrder.setCheckDate(changeDate);
		jpoMemberOrder.setCheckDateUserCode(operatorSysUser.getUserCode());
		if("1".equals(jpoMemberOrder.getOrderType())){
			//TODO:会员审核日期
			JmiMember jmiMember = jmiMemberDao.getJmiMember(jpoMemberOrder.getSysUser().getUserCode());
			if(jmiMember==null){
				throw new AppException("会员不存在");
			}
			jmiMember.setCheckDate(jpoMemberOrder.getCheckDate());
			jmiMember.setCheckNo(jpoMemberOrder.getCheckUserCode());
			//==============续约
			BdPeriod bdPeriod = bdPeriodManager.getBdPeriodByTime(jpoMemberOrder.getCheckDate(),null);
			Integer startMonth = bdPeriod.getWyear()*100 + bdPeriod.getWmonth();
			
			String year = startMonth.toString().substring(0, 4);
			String month = startMonth.toString().substring(4, 6);
			String period = bdPeriodManager.getFutureBdYearMonth(year, month, 12);
			
			jmiMember.setStartWeek(startMonth);
			jmiMember.setValidWeek(Integer.parseInt(period));
			//==============
			this.jmiMemberDao.saveJmiMember(jmiMember);
		}
//		if("1".equals(jpoMemberOrder.getOrderType()) || "2".equals(jpoMemberOrder.getOrderType())){
//			try{
//				jbdUserCardListManager.saveJbdUserCardList(jpoMemberOrder.getSysUser().getUserCode(), jpoMemberOrder.getCheckDate(), jpoMemberOrder.getSysUser().getJmiMember().getCardType(),jpoMemberOrder.getOrderType(),"2");
//			}catch (Exception e){
//				e.printStackTrace();
//				throw new AppException("会员级别调整有误");
//			}
//		}
		if("12".equals(jpoMemberOrder.getOrderType())){
	    	JmiStore jmiStore = jmiStoreManager.getJmiStoreByUserCode(jpoMemberOrder.getSysUser().getUserCode());
	    	if(jmiStore!=null){
	    		jmiStore.setOrderDate(jpoMemberOrder.getCheckDate());
	    	}
		}
		dao.saveJpoMemberOrder(jpoMemberOrder);
		
		//插入每日计算表
		JbdSummaryList jbdSummaryList=new JbdSummaryList();
		jbdSummaryList.setUserCode(jpoMemberOrder.getSysUser().getUserCode());
		jbdSummaryList.setCardType(jpoMemberOrder.getSysUser().getJmiMember().getCardType());
		jbdSummaryList.setInType(7);
		jbdSummaryList.setCreateTime(new Date());
		jbdSummaryList.setOrderType(jpoMemberOrder.getOrderType());
		jbdSummaryList.setOldCheckDate(oleCheckDate);
		jbdSummaryList.setNewCheckDate(jpoMemberOrder.getCheckDate());
		jbdSummaryList.setPvAmt(jpoMemberOrder.getPvAmt());
		jbdSummaryList.setOldLinkNo(null);
		jbdSummaryList.setNewLinkNo(null);
		jbdSummaryList.setOldRecommendNo(null);
		jbdSummaryList.setNewRecommendNo(null);
		jbdSummaryList.setNewCompanyCode(jpoMemberOrder.getCompanyCode());
		jbdSummaryList.setUserCreateTime(null);
		jbdSummaryList.setWweek(bdPeriodManager.getBdPeriodByTimeFormated(jpoMemberOrder.getCheckDate(), null));
		jbdSummaryList.setOrderNo(String.valueOf(jpoMemberOrder.getMoId()));  
		jbdSummaryListManager.saveJbdSummaryList(jbdSummaryList);
    }
    

    /**
     * @see com.joymain.jecs.po.service.JpoMemberOrderManager#removeJpoMemberOrder(String moId)
     */
    public void removeJpoMemberOrder(final String moId) {
    	JpoMemberOrder jpoMemberOrder = this.getJpoMemberOrder(moId);
    	if("JP".equals(jpoMemberOrder.getCompanyCode())&&"1".equals(jpoMemberOrder.getShippingPay())){
    		pdSendInfoManager.doWhileVoidOrder(jpoMemberOrder);
    	}
        dao.removeJpoMemberOrder(new Long(moId));
    }
	
	/**
	 * 订单总金额
	 * @param crm
	 * @return
	 */
	public Map getSumAmountByCrm(CommonRecord crm){
		return dao.getSumAmountByCrm(crm);
	}
    //added for getJpoMemberOrdersByCrm
    public List getJpoMemberOrdersByCrm(CommonRecord crm, Pager pager){
    	List jpoMemberOrders = dao.getJpoMemberOrdersByCrm(crm,pager);
    	
    	//Modify By WuCF 判断结果集合是否超过限度，则直接返回！
    	//提取订单相关的其他表数据,用于报表
    	if(jpoMemberOrders !=null && jpoMemberOrders.size()>0){
    		if("1".equals(jpoMemberOrders.get(0).toString())){
    			log.info("==============订单导出xls数据量太大！");
    		}else{
	    		JpoMemberOrder order = (JpoMemberOrder)jpoMemberOrders.get(0);
	    		if(order.getJpoMemberOrderList()!=null && order.getJpoMemberOrderList().size()>0){
	    			Iterator<JpoMemberOrderList> orderList = order.getJpoMemberOrderList().iterator();
	        		JpoMemberOrderList item = orderList.next();
	//        		log.info("productName:"+item.getJpmProductSaleTeamType().
	//        				getJpmProductSaleNew().getJpmProduct().getProductNo());
	    		}
    		}
    	}
    	
    	return jpoMemberOrders;
    }

    
    public Map getSumAmountSTJByCrm(CommonRecord crm){
		return dao.getSumAmountSTJByCrm(crm);
	}
    
    public List getJpoMemberOrdersSTJByCrm(CommonRecord crm, Pager pager){
    	List jpoMemberOrders = dao.getJpoMemberOrdersSTJByCrm(crm,pager);
    	
    	return jpoMemberOrders;
    }
    
	public List getJpoMemberOrdersByCrmForRefund(CommonRecord crm, Pager pager) {
		return dao.getJpoMemberOrdersByCrmForRefund(crm, pager);
	}
	
	/**
	 * 时间段内获取商品订购量
	 * @param productNo
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public int getProductsSum(String productNo,String startTime,String endTime,String payBy){
		return dao.getProductsSum(productNo, startTime, endTime,payBy);
	}
	
	/**
	 * 抢购时间：2010年5月11日-5月16日
	 * 剩下多少个
	 * @param orderProductMax
	 * @return
	 */
	public int getProductsLeave(String productNo){
		if("P08140100101CN0".equals(productNo)||"P08150100101CN0".equals(productNo)){
	    	int orderProductMax = 500;
			java.util.Calendar startc=java.util.Calendar.getInstance();
			startc.set(2010, 4, 18, 00, 00, 00);
			java.util.Date startDate=startc.getTime();
			Date curDate=new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String curDateStr = dateFormat.format(curDate);
			int sumOrderProduct = this.getProductsSum(productNo, curDateStr + " 00:00:00", curDateStr + " 23:59:59","");
			if(sumOrderProduct<orderProductMax){//超过规定的限度
				return orderProductMax - sumOrderProduct;
			}
		}
		return -1;
	}
	
	/**
	 * 抢购时间：2010年5月11日-5月16日
	 * @param productNo
	 * @return
	 */
	public boolean getIsOver(String productNo){
		if("P08140100101CN0".equals(productNo)||"P08150100101CN0".equals(productNo)){
	    	int orderProductMax = 500;
			java.util.Calendar startc=java.util.Calendar.getInstance();
			startc.set(2010, 4, 18, 00, 00, 00);
			java.util.Date startDate=startc.getTime();
			Date curDate=new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String curDateStr = dateFormat.format(curDate);
			if(curDate.after(startDate)){//时间不为促销期
				return true;
			}
			int sumOrderProduct = this.getProductsSum(productNo, curDateStr + " 00:00:00", curDateStr + " 23:59:59","");
			if(sumOrderProduct>=orderProductMax){//超过规定的限度
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 抢购时间：2012年4月21日-5月4日
	 * @param productNo
	 * @return
	 */
	public int getIsOver2(String productNo){
    	java.util.Calendar startc=java.util.Calendar.getInstance();
    	startc.set(2012, 3, 21, 00, 00, 00);
    	java.util.Calendar endc=java.util.Calendar.getInstance();
    	endc.set(2012, 4, 5, 00, 00, 00);
    	java.util.Date startDate=startc.getTime();
    	java.util.Date endDate=endc.getTime();
    	Date curDate=new Date();
    	if((curDate.after(startDate))&&(curDate.before(endDate))){
			if("P04010118001CN0".equals(productNo)){
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String curDateStr = dateFormat.format(curDate);
		    	int orderProductMax = Integer.parseInt(Constants.sysConfigMap.get("CN").get("over2.p04010118001cn0"));
				int sumOrderProduct = this.getProductsSum(productNo, curDateStr + " 00:00:00", curDateStr + " 23:59:59","byCoin");
				if(sumOrderProduct>=orderProductMax){//超过规定的限度
					return 0;
				}else{
					return orderProductMax - sumOrderProduct;
				}
			}
			if("P08420300101CN0".equals(productNo)){
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String curDateStr = dateFormat.format(curDate);
		    	int orderProductMax = Integer.parseInt(Constants.sysConfigMap.get("CN").get("over2.p08420300101cn0"));;
				int sumOrderProduct = this.getProductsSum(productNo, curDateStr + " 00:00:00", curDateStr + " 23:59:59","byCoin");
				if(sumOrderProduct>=orderProductMax){//超过规定的限度
					return 0;
				}else{
					return orderProductMax - sumOrderProduct;
				}
			}
		}
		return -1;
	}
	
	/**
	 * 积分换购抢购
	 * @param productNo
	 * @return
	 */
	public int getIsOver3(String productNo){
		int payByCoin = Integer.parseInt(this.getConfigValue("CN","paybycoin"));
		if(payByCoin==1){
    		Map limitProduct = this.getListOptions("CN","limit.product");
    		if(limitProduct.get(productNo)!=null){
				String limitTimeTmp = this.getConfigValue("CN","limit.time");
				if(!"0".equals(limitTimeTmp)){
					String[] limitTime = limitTimeTmp.split(",");
					int sumOrderProduct = this.getProductsSum(productNo, limitTime[0], limitTime[1],"byCoin");
					int orderProductMax = Integer.parseInt(limitProduct.get(productNo).toString());
	    			if(sumOrderProduct >= orderProductMax){//超过规定的限度
						return 0;
					}else{
						return orderProductMax - sumOrderProduct;
					}
				}else{
					return 1;//限购时间设成0则不检测商品限量
				}
    		}else{
    			return 1;//商品列表找不到商品则不检测商品限量
    		}
		}else{
			return 1;//积分换购没启用则所有商品都可以买
		}
	}


	/**
	 * 批量修改订单
	 * 
	 * @param poMemberOrder
	 * @param poMemberOrderSet
	 */
	public void editJpoMemberOrderBatch(List<JpoMemberOrder> jpoMemberOrders, List<Set> jpoMemberOrderSets, List<Set> jpoMemberOrderFeeSets){
		for(int i = 0 ; i < jpoMemberOrders.size() ; i++){
			this.editJpoMemberOrder(jpoMemberOrders.get(i),jpoMemberOrderSets.get(i),jpoMemberOrderFeeSets.get(i));
		}
	}

	/**
	 * 修改订单
	 * 
	 * @param poMemberOrder
	 * @param poMemberOrderSet
	 */
	public void editJpoMemberOrder(JpoMemberOrder jpoMemberOrder, Set jpoMemberOrderSet, Set jpoMemberOrderFeeSet) {
		if(StringUtil.isEmpty(jpoMemberOrder.getMemberOrderNo())){
			String memberOrderNo = sysIdManager.buildIdStr("pono");//生成订单编号
			jpoMemberOrder.setMemberOrderNo(memberOrderNo);
		}
		Set jpoMemberOrderListSet = jpoMemberOrder.getJpoMemberOrderList();
		Iterator its1 = jpoMemberOrderListSet.iterator();
		while (its1.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			jpoMemberOrderListDao.removeJpoMemberOrderList(jpoMemberOrderList.getMolId());
		}
		jpoMemberOrder.getJpoMemberOrderList().clear();
		jpoMemberOrder.setJpoMemberOrderList(jpoMemberOrderSet);
		
		Set jpoMemberOrderFeeListSet = jpoMemberOrder.getJpoMemberOrderFee();
		Iterator its2 = jpoMemberOrderFeeListSet.iterator();
		while (its2.hasNext()) {
			JpoMemberOrderFee jpoMemberOrderFee = (JpoMemberOrderFee) its2.next();
			jpoMemberOrderFeeDao.removeJpoMemberOrderFee(jpoMemberOrderFee.getMofId());
		}
		jpoMemberOrder.getJpoMemberOrderFee().clear();
		jpoMemberOrder.setJpoMemberOrderFee(jpoMemberOrderFeeSet);

		if(!this.getCheckOrderAmount(jpoMemberOrder)){
			throw new AppException("订单总金额不一致");
		}
		
		dao.saveJpoMemberOrder(jpoMemberOrder);
		if("JP".equals(jpoMemberOrder.getCompanyCode())&&"1".equals(jpoMemberOrder.getShippingPay())){
			if(jpoMemberOrder.getMoId()!=null){
				pdSendInfoManager.doWhileVoidOrder(jpoMemberOrder);
			}
			if("1".equals(jpoMemberOrder.getShippingPay())){
				pdSendInfoManager.doWhileOrderConfirmed(jpoMemberOrder);
			}
		}
	}
	
	/**
	 * JMS送分(重发)
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 */
	public void resendJmsCoin(FiCoinLog fiCoinLog, SysUser operatorSysUser){
		JpoMemberOrder jpoMemberOrder = this.getJpoMemberOrderByMemberOrderNo(fiCoinLog.getUniqueCode());
    	try{
    		HashMap fiCoinLogMap=new HashMap();
			fiCoinLogMap.put("coin", fiCoinLog.getCoin());
			fiCoinLogMap.put("coinType", fiCoinLog.getCoinType());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			fiCoinLogMap.put("createTime", sdf.format(fiCoinLog.getCreateTime()));
			fiCoinLogMap.put("createUser", fiCoinLog.getCreateUser());
			fiCoinLogMap.put("dealType", fiCoinLog.getDealType());
			fiCoinLogMap.put("receiveCoin", fiCoinLog.getReceiveCoin());
			fiCoinLogMap.put("userCode", fiCoinLog.getUserCode());
			fiCoinLogMap.put("uniqueCode", fiCoinLog.getUniqueCode());
			fiCoinLogMap.put("status", "1");
			if(jpoMemberOrder!=null){
				fiCoinLogMap.put("orderType", jpoMemberOrder.getOrderType());
				fiCoinLogMap.put("userName", jpoMemberOrder.getSysUser().getUserName());
			}
			fiCoinLogMap.put("fclId", fiCoinLog.getFclId());
			
    		int result = ecCoinSender.send(fiCoinLogMap);
    		if(result ==1 ){
    			fiCoinLog.setStatus("2");
    		}
		}catch(Exception e){
			e.printStackTrace();
			throw new AppException("JMS异常：" + e.getMessage());
		}
		fiCoinLogManager.saveFiCoinLog(fiCoinLog);
	}
	
	/**
	 * JMS送分
	 * @param uniqueCode唯一码
	 * @param userCode用户编号
	 * @param userCode用户名称
	 * @param operatorSysUser操作者
	 */
	public void sendJmsCoinByType(String uniqueCode,String userCode,String userName, SysUser operatorSysUser){
		//送B分
	    FiCoinLog fiCoinLog = new FiCoinLog();
	    //fiCoinLog.setCoinType("B");
	    fiCoinLog.setStatus("1");
	    fiCoinLog.setUniqueCode(uniqueCode);
	    fiCoinLog.setUserCode(userCode);
	    	    	
	    List fiCoinLogs = fiCoinLogManager.getFiCoinLogs(fiCoinLog);
	    if(fiCoinLogs.size()==1){
	    	fiCoinLog = (FiCoinLog)fiCoinLogs.get(0);
		    try{
		    	HashMap fiCoinLogMap=new HashMap();
			    fiCoinLogMap.put("coin", fiCoinLog.getCoin());
			    fiCoinLogMap.put("coinType", fiCoinLog.getCoinType());
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    fiCoinLogMap.put("createTime", sdf.format(fiCoinLog.getCreateTime()));
			    fiCoinLogMap.put("createUser", fiCoinLog.getCreateUser());
				fiCoinLogMap.put("dealType", fiCoinLog.getDealType());
			    fiCoinLogMap.put("receiveCoin", fiCoinLog.getReceiveCoin());
			    fiCoinLogMap.put("userCode", fiCoinLog.getUserCode());
			    fiCoinLogMap.put("uniqueCode", fiCoinLog.getUniqueCode());
			    fiCoinLogMap.put("status", "1");
			    fiCoinLogMap.put("orderType", "0");
			    fiCoinLogMap.put("userName", userName);
			    fiCoinLogMap.put("fclId", fiCoinLog.getFclId());
			    			
		    	int result = ecCoinSender.send(fiCoinLogMap);
		    	    if(result ==1 ){
		    	    	fiCoinLog.setStatus("2");
		    	    }
		    }catch(Exception e){
			    e.printStackTrace();
			}
		    fiCoinLogManager.saveFiCoinLog(fiCoinLog);
	    }
	}
	
	/**
	 * JMS送分
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 */
	public void sendJmsCoin(JpoMemberOrder jpoMemberOrder, SysUser operatorSysUser){
		//送B分
		if("CN".equals(jpoMemberOrder.getCompanyCode())){
			if("1".equals(jpoMemberOrder.getOrderType()) || "2".equals(jpoMemberOrder.getOrderType()) || 
					"4".equals(jpoMemberOrder.getOrderType()) || "6".equals(jpoMemberOrder.getOrderType()) || 
					"9".equals(jpoMemberOrder.getOrderType()) || "11".equals(jpoMemberOrder.getOrderType()) ||
					"12".equals(jpoMemberOrder.getOrderType()) || "14".equals(jpoMemberOrder.getOrderType())){
	    		java.util.Calendar startc=java.util.Calendar.getInstance();
	    		startc.set(2010, 7, 7, 00, 00, 00);
	    		java.util.Date startDate=startc.getTime();
	    		Date curDate=new Date();
	    		if((curDate.after(startDate))){
	    			FiCoinLog fiCoinLog = new FiCoinLog();
	    			//fiCoinLog.setCoinType("B");
	    			fiCoinLog.setStatus("1");
	    			fiCoinLog.setUniqueCode(jpoMemberOrder.getMemberOrderNo());
	    			fiCoinLog.setUserCode(jpoMemberOrder.getSysUser().getUserCode());
	    	    	
	    			List fiCoinLogs = fiCoinLogManager.getFiCoinLogs(fiCoinLog);
	    			if(fiCoinLogs.size()==1){
	    				fiCoinLog = (FiCoinLog)fiCoinLogs.get(0);
		    	    	try{
		    	    		HashMap fiCoinLogMap=new HashMap();
			    			fiCoinLogMap.put("coin", fiCoinLog.getCoin());
			    			fiCoinLogMap.put("coinType", fiCoinLog.getCoinType());
			    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    			fiCoinLogMap.put("createTime", sdf.format(fiCoinLog.getCreateTime()));
			    			fiCoinLogMap.put("createUser", fiCoinLog.getCreateUser());
			    			fiCoinLogMap.put("dealType", fiCoinLog.getDealType());
			    			fiCoinLogMap.put("receiveCoin", fiCoinLog.getReceiveCoin());
			    			fiCoinLogMap.put("userCode", fiCoinLog.getUserCode());
			    			fiCoinLogMap.put("uniqueCode", fiCoinLog.getUniqueCode());
			    			fiCoinLogMap.put("status", "1");
			    			fiCoinLogMap.put("orderType", jpoMemberOrder.getOrderType());
			    			fiCoinLogMap.put("userName", jpoMemberOrder.getSysUser().getUserName());
			    			fiCoinLogMap.put("fclId", fiCoinLog.getFclId());
			    			
		    	    		int result = ecCoinSender.send(fiCoinLogMap);
		    	    		if(result ==1 ){
		    	    			fiCoinLog.setStatus("2");
		    	    		}
			    		}catch(Exception e){
			    			e.printStackTrace();
			    		}
		    			fiCoinLogManager.saveFiCoinLog(fiCoinLog);
	    			}
	    		}
			}
		}
	}
	/**
	 * 自动生成订单并审核，不扣款
	 * @param json
	 * @throws Exception
	 */
	public void orderJSON(String json) throws Exception{
		JSONObject jSONObject = new JSONObject(json);
		if(!"US".equals(jSONObject.get("companyCode").toString())){
			throw new AppException("不是美国订单");
		}
		JpoMemberOrder jpoMemberOrder = new JpoMemberOrder();
		jpoMemberOrder.setCompanyCode(jSONObject.get("companyCode").toString());
		jpoMemberOrder.setCountryCode(jSONObject.get("companyCode").toString());
		jpoMemberOrder.setMemberOrderNo(jSONObject.get("orderNo").toString());
		jpoMemberOrder.setOrderType(jSONObject.get("orderType").toString());
		SysUser sysUser = sysUserDao.getSysUser(jSONObject.get("distNo").toString());
		if(sysUser==null || !"M".equals(sysUser.getUserType())){
			throw new AppException("会员不存在");
		}
		if("1".equals(jSONObject.get("abroadFlag").toString())&&"4".equals(jSONObject.get("orderType").toString())){
			jpoMemberOrder.setCompanyPay("1");
		}
		jpoMemberOrder.setSysUser(sysUser);
		jpoMemberOrder.setUserSpCode(jSONObject.get("distNo").toString());
		jpoMemberOrder.setAmount(new BigDecimal(jSONObject.get("totalCost").toString()));
		jpoMemberOrder.setPvAmt(new BigDecimal(jSONObject.get("totalPv").toString()));
		jpoMemberOrder.setConsumerAmount(new BigDecimal("0"));
		jpoMemberOrder.setPayMode(jSONObject.get("paymentType").toString());
		jpoMemberOrder.setOrderUserCode(sysUser.getUserCode());
		jpoMemberOrder.setOrderTime(new Date());
		jpoMemberOrder.setStatus("1");
		jpoMemberOrder.setRemark("json add and check.");
		jpoMemberOrder.setLocked("0");
		
		if(StringUtil.isEmpty(jSONObject.get("shippingType").toString())){
			jpoMemberOrder.setPickup("0");
		}else{
			jpoMemberOrder.setPickup(jSONObject.get("shippingType").toString());
		}
		jpoMemberOrder.setSubmitStatus("1");
		jpoMemberOrder.setFirstName(jSONObject.get("fistName").toString());
		jpoMemberOrder.setLastName(jSONObject.get("lastName").toString());
		Map alStateProvinceMap = alStateProvinceManager.getIdByCodeMap(jSONObject.get("companyCode").toString());
		jpoMemberOrder.setProvince(alStateProvinceMap.get(jSONObject.get("stateCode").toString()).toString());
		jpoMemberOrder.setCity(jSONObject.get("city").toString());
		jpoMemberOrder.setAddress(jSONObject.get("address").toString());
		jpoMemberOrder.setPostalcode(jSONObject.get("postcode").toString());
		jpoMemberOrder.setPhone(jSONObject.get("phoneNo").toString());
		jpoMemberOrder.setEmail(jSONObject.get("email").toString());
		jpoMemberOrder.setMobiletele(jSONObject.get("mobile").toString());
		
		JSONArray jSONArray = (JSONArray)jSONObject.get("products");
		for(int i = 0 ; i < jSONArray.length() ; i++){
			JSONObject jSONProductSale = (JSONObject)jSONArray.get(i);
			JpoMemberOrderList jpoMemberOrderList = new JpoMemberOrderList();
			jpoMemberOrderList.setJpoMemberOrder(jpoMemberOrder);
			Map pmProductSaleMap = jpmProductSaleManager.getProductSaleMapNew(jSONObject.get("companyCode").toString());
			jpoMemberOrderList.setJpmProductSaleTeamType((JpmProductSaleTeamType)pmProductSaleMap.get(jSONProductSale.get("productNo").toString()));
			jpoMemberOrderList.setPrice(new BigDecimal(jSONProductSale.get("price").toString()));
			jpoMemberOrderList.setProductType("");
			jpoMemberOrderList.setPv(new BigDecimal(jSONProductSale.get("PV").toString()));
			jpoMemberOrderList.setQty(Integer.parseInt(jSONProductSale.get("qty").toString()));
			jpoMemberOrderList.setVolume(new BigDecimal(jSONProductSale.get("weight").toString()));
			jpoMemberOrderList.setWeight(new BigDecimal(jSONProductSale.get("volume").toString()));
			jpoMemberOrder.getJpoMemberOrderList().add(jpoMemberOrderList);

		}
		
		JpoMemberOrderFee jpoMemberOrderFee1 = new JpoMemberOrderFee();
		jpoMemberOrderFee1.setFeeType("1");
		jpoMemberOrderFee1.setFee(new BigDecimal(jSONObject.get("shippingFee").toString()));
		jpoMemberOrderFee1.setDetailType("0000");
		jpoMemberOrderFee1.setJpoMemberOrder(jpoMemberOrder);
		jpoMemberOrder.getJpoMemberOrderFee().add(jpoMemberOrderFee1);
		
		JpoMemberOrderFee jpoMemberOrderFee2 = new JpoMemberOrderFee();
		jpoMemberOrderFee2.setFeeType("2");
		jpoMemberOrderFee2.setFee(new BigDecimal(jSONObject.get("handingFee").toString()));
		jpoMemberOrderFee2.setDetailType("0000");
		jpoMemberOrderFee2.setJpoMemberOrder(jpoMemberOrder);
		jpoMemberOrder.getJpoMemberOrderFee().add(jpoMemberOrderFee2);
		
		JpoMemberOrderFee jpoMemberOrderFee3 = new JpoMemberOrderFee();
		jpoMemberOrderFee3.setFeeType("3");
		jpoMemberOrderFee3.setFee(new BigDecimal(jSONObject.get("salesTax").toString()));
		jpoMemberOrderFee3.setDetailType("0000");
		jpoMemberOrderFee3.setJpoMemberOrder(jpoMemberOrder);
		jpoMemberOrder.getJpoMemberOrderFee().add(jpoMemberOrderFee3);
		
		JpoMemberOrderFee jpoMemberOrderFee4 = new JpoMemberOrderFee();
		jpoMemberOrderFee4.setFeeType("4");
		jpoMemberOrderFee4.setFee(new BigDecimal(jSONObject.get("enrollerBonus").toString()));
		jpoMemberOrderFee4.setDetailType("0000");
		jpoMemberOrderFee4.setJpoMemberOrder(jpoMemberOrder);
		jpoMemberOrder.getJpoMemberOrderFee().add(jpoMemberOrderFee4);
		
		jpoMemberOrder.setIsFree("1");
		this.checkJpoMemberOrder(jpoMemberOrder, sysUser, "1");
	}
	
	/**
	 * 审核会员订单
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 */
//	public void checkJpoMemberOrder(JpoMemberOrder jpoMemberOrder, SysUser operatorSysUser,String dataType)throws Exception{
//		
//		log.info("check Order start ,orderNo is:"+jpoMemberOrder.getMemberOrderNo());
//		
//		if(jpoMemberOrder.getIsPay().equals("0") && (jpoMemberOrder.getStatus().equals("3") || jpoMemberOrder.getStatus().equals("4"))){  //错误数据
//			
//				throw new AppException("salePromoter failed," +
//						"orderNo is:"+jpoMemberOrder.getMemberOrderNo() );
//			
//		}else{
//			
//
//			//未填写支付时间
//			if("".equals(jpoMemberOrder.getSubmitTime()) || null == jpoMemberOrder.getSubmitTime()){
//				if(isOverQty(jpoMemberOrder)){
//					throw new AppException("商品无库存或库存不足");
//				}
//			}
//
//			String limitcheckorder = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "limitcheckorder");
//			if("1".equals(limitcheckorder))
//			{
//				throw new AppException("系统更新,请您稍后在审单");
//			}
//			if(!this.getCheckOrderAmount(jpoMemberOrder)){
//				throw new AppException("订单总金额不一致");
//			}
//			if(!"0".equals(jpoMemberOrder.getLocked())){
//				throw new AppException("订单已锁订");
//			}
//			if("2".equals(jpoMemberOrder.getStatus())){
//				throw new AppException("订单已审核");
//			}
//			if("1".equals(jpoMemberOrder.getSysUser().getJmiMember().getActive())){
//				throw new AppException(this.getLocalLanguageByKey("checkError.Code.member",operatorSysUser));
//			}
//			
//			if(jpoMemberOrder.getSysUser().getJmiMember().getFreezeStatus() == 1 && !jpoMemberOrder.getOrderType().equals(Constants.AUTO_PURCHASE)){
//				throw new AppException("会员已冻结!");
//			}
//			
//			SysUser sysUserSp = sysUserDao.getSysUser(jpoMemberOrder.getUserSpCode());
//			if(!sysUserSp.getCompanyCode().equals(jpoMemberOrder.getCompanyCode())&&!"1".equals(jpoMemberOrder.getCompanyPay())){
//				throw new AppException("扣款人与订单不同国别");
//			}
//			
//
//	    	BigDecimal sumPrice = new BigDecimal(0);
//	    	BigDecimal sumPV = new BigDecimal(0);
//	    	
//	    	Iterator<JpoMemberOrderList> itsTmp1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
//	    	while (itsTmp1.hasNext()) {
//				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) itsTmp1.next();
//				sumPrice = sumPrice.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
//				sumPV =sumPV.add(jpoMemberOrderList.getPv().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
//			}
//	    	Iterator itsTmp2 = jpoMemberOrder.getJpoMemberOrderFee().iterator();
//	    	while (itsTmp2.hasNext()) {
//				JpoMemberOrderFee jpoMemberOrderFee = (JpoMemberOrderFee) itsTmp2.next();
//				sumPrice = sumPrice.add(jpoMemberOrderFee.getFee());
//			}
//			Date logCurDate=new Date();
//	    	Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
//	    	if("CN".equals(jpoMemberOrder.getCompanyCode())){
//	        	while (its1.hasNext()) {
//	        		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
//	        		String status = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getStatus();
//	        		if("0".equals(status)){
//	        			throw new AppException("产品(" + jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo() + ")已售完!");
//	        		}
//	        	}
//	    	}
//	    	
//			Date date = ServerDateUtil.getNowTimeFromDateServer(); //数据时间jmiMemberDao.getDsDate();
//			jpoMemberOrder.setIsSended(0);
//			jpoMemberOrder.setSubmitStatus("2");
//			
//			if(!"".equals(jpoMemberOrder.getSubmitUserCode()) && jpoMemberOrder.getSubmitUserCode() != null){
//				jpoMemberOrder.setCheckTime(jpoMemberOrder.getSubmitTime());
//				jpoMemberOrder.setCheckDate(jpoMemberOrder.getSubmitTime());
//				jpoMemberOrder.setCheckUserCode(jpoMemberOrder.getSubmitUserCode());
//				
//			}else{
//				
//				jpoMemberOrder.setSubmitTime(date);
//				jpoMemberOrder.setSubmitUserCode(operatorSysUser.getUserCode());
//				
//				jpoMemberOrder.setCheckTime(date);
//				jpoMemberOrder.setCheckDate(date);
//				jpoMemberOrder.setCheckUserCode(operatorSysUser.getUserCode());
//			}
//			
//			int orderType = Integer.parseInt(jpoMemberOrder.getOrderType());
//			
//			String oldCard="";
//			String newCard="";
//			FileUtil.saveLogger(fileName, logCurDate, new Date(), "进入判断订单前"+jpoMemberOrder.getMemberOrderNo());
//			switch(orderType){
//				case 1://会员首购
//					int businessFId = this.getJpoMemberOrderBusinessMF(jpoMemberOrder);
//					if(businessFId==10){
//						logCurDate=new Date();
//						if(jpoMemberOrder.getSysUser().getJmiMember().getNotFirst()!=0){
//							throw new AppException("会员不处于待审状态");
//						}
//						String cardType = "";
//						if("1".equals(jpoMemberOrder.getSysUser().getJmiMember().getMemberType()) && !"0".equals(jpoMemberOrder.getSysUser().getJmiMember().getOriCard())){
//							if(jpoMemberOrder.getPvAmt().compareTo(new BigDecimal("70"))!=-1){
//								cardType = jpoMemberOrder.getSysUser().getJmiMember().getOriCard();
//							}else{
//								throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "D会员首购单必须大于或等于70PV");
//							}
//						}else if("2".equals(jpoMemberOrder.getSysUser().getJmiMember().getMemberType())){
//							BigDecimal pv_amt = jpoMemberOrder.getPvAmt().add(jpoMemberOrder.getSysUser().getJmiMember().getOriPv());
//							cardType = this.getCalcFOrderCardType(pv_amt,jpoMemberOrder.getSysUser().getCompanyCode());
//						}else if("4".equals(jpoMemberOrder.getSysUser().getJmiMember().getMemberType())){
//							if("1".equals(jpoMemberOrder.getSysUser().getJmiMember().getOriCard())){
//								if(sumPrice.compareTo(new BigDecimal("140"))!=-1){
//									cardType = "4";
//								}else{
//									throw new AppException("PV不足");
//								}
//							}else{
//								if(sumPrice.compareTo(new BigDecimal("70"))!=-1){
//									cardType = "4";
//								}else{
//									throw new AppException("PV不足");
//								}
//							}
//						}else{
//							cardType = this.getCalcFOrderCardType(jpoMemberOrder.getPvAmt(),jpoMemberOrder.getSysUser().getCompanyCode());
//						}
//						FileUtil.saveLogger(fileName, logCurDate, new Date(), "判断级别"+jpoMemberOrder.getMemberOrderNo());
//						if(Integer.parseInt(cardType) > Integer.parseInt(jpoMemberOrder.getSysUser().getJmiMember().getCardType())){
//							//获取新旧卡别
//							logCurDate=new Date();
//							oldCard=jmiMemberDao.getJmiMember(jpoMemberOrder.getSysUser().getUserCode()).getCardType();
//							newCard=cardType;
//							FileUtil.saveLogger(fileName, logCurDate, new Date(), "取出级别"+jpoMemberOrder.getMemberOrderNo());
//							//升级记录
////							this.getSaveJmiMemberUpgradeNote(jpoMemberOrder, cardType, operatorSysUser, "1");	
//						}
//						//角色
//						String roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), Constants.JOCS_ROLE_NORMAL);
//	/*					if("6".equals(cardType)){
//							//VIP会员
//							Iterator ite = jpoMemberOrder.getJpoMemberOrderList().iterator();
//							while(ite.hasNext()){
//								JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) ite.next();
//	        	        		if("P13010200201CN0".equals(jpoMemberOrderList.getJpmProductSale().getJpmProduct().getProductNo())){
//	        	        			//买凳子送二级馆
//	        	        			roleId = "cn.member.62";
//	        	        		}
//							}
//						}*/
//						logCurDate=new Date();
////						this.getChangeJmiMemberRole(jpoMemberOrder.getSysUser(), roleId);
//						FileUtil.saveLogger(fileName, logCurDate, new Date(), "存角色"+jpoMemberOrder.getMemberOrderNo());
//					}else{
//						throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessFId,operatorSysUser));
//					}
//					JmiMember jmiMember = jmiMemberDao.getJmiMember(jpoMemberOrder.getSysUser().getUserCode());
//					jmiMember.setCheckDate(jpoMemberOrder.getCheckDate());
//					jmiMember.setCheckNo(jpoMemberOrder.getCheckUserCode());
//					//==============续约
//					BdPeriod bdPeriodF = bdPeriodManager.getBdPeriodByTime(jpoMemberOrder.getCheckDate(),null);
//					Integer startMonthF = bdPeriodF.getWyear()*100 + bdPeriodF.getWmonth();
//					
//					String yearF = startMonthF.toString().substring(0, 4);
//					String monthF = startMonthF.toString().substring(4, 6);
//					String periodF = bdPeriodManager.getFutureBdYearMonth(yearF, monthF, 12);
//					
//					jmiMember.setStartWeek(startMonthF);
//					jmiMember.setValidWeek(Integer.parseInt(periodF));
//					jmiMember.setNotFirst(1);
//					//==============
//					if("2".equals(jpoMemberOrder.getSysUser().getJmiMember().getMemberType())){
//						jmiMember.setOriPv(new BigDecimal("0"));
//					}
//					this.jmiMemberDao.saveJmiMember(jmiMember);
//					break;
//				case 2://会员升级
//					int businessUId = this.getJpoMemberOrderBusinessMU(jpoMemberOrder);
//					if(businessUId==20){
//						
//						String cardType = "";
//						if("0".equals(jpoMemberOrder.getIsSpecial())){
//							cardType = this.getCalcUOrderCardType(jpoMemberOrder);
//						}else if("1".equals(jpoMemberOrder.getIsSpecial())){
//							cardType = this.getCalcSpecialUOrderCardType(jpoMemberOrder);
//						}else{
//							cardType = this.getCalcUOrderCardType(jpoMemberOrder);
//						}
//						if("".equals(cardType)){
//							throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code.21",operatorSysUser));//升级单不足以升一级
//						}
//						//获取新旧卡别
//						oldCard=jmiMemberDao.getJmiMember(jpoMemberOrder.getSysUser().getUserCode()).getCardType();
//						newCard=cardType;
//						//升级记录
//						this.getSaveJmiMemberUpgradeNote(jpoMemberOrder, cardType, operatorSysUser, "2");
//						//角色
//						JmiStore jmiStore = jmiStoreManager.getJmiStoreByUserCode(jpoMemberOrder.getSysUser().getUserCode());
//						String roleId;
//						if("1".equals(jpoMemberOrder.getSysUser().getJmiMember().getIsstore())){
//							if("6".equals(newCard)){
//								roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "member_role_id.store.vip");
//							}else{
//								roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "member_role_id.store");
//							}
//						}else if("2".equals(jpoMemberOrder.getSysUser().getJmiMember().getIsstore())){
//							if("6".equals(newCard)){
//								roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "member_role_id.store2.vip");
//							}else{
//								roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "member_role_id.store2");
//							}
//						}else if("1".equals(jpoMemberOrder.getSysUser().getJmiMember().getSubStoreStatus())){
//							//二级已审
//							roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "member_role_id.store1");
//						}else if(jmiStore!=null && "1".equals(jmiStore.getCheckStatus())){
//							//一级已审
//							roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "member_role_id.store0");
//						}else{
//							roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "member_role_id." + cardType);
//						}
//						
//						this.getChangeJmiMemberRole(jpoMemberOrder.getSysUser(), roleId);
//					}else{
//						throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessUId,operatorSysUser));
//					}
//					break;
//				case 3://会员续约
//					int businessRSId = this.getJpoMemberOrderBusinessMRS(jpoMemberOrder);
//					if(businessRSId==30){
//						
//						if(jpoMemberOrder.getSysUser().getJmiMember().getFreezeStatus()==0){
//							throw new AppException("会员处于解冻状态！");
//						}else if(jpoMemberOrder.getSysUser().getJmiMember().getFreezeStatus()==1){
//							//冻，126PV订单
//							//==============续约当年
//
//							/*String years = jpoMemberOrder.getSysUser().getJmiMember().getValidWeek().toString().substring(0, 4);
//							String months = jpoMemberOrder.getSysUser().getJmiMember().getValidWeek().toString().substring(4, 6);
//							String periodS = bdPeriodManager.getFutureBdYearMonth(years, months, 2);
//							String yeare = periodS.substring(0, 4);
//							String monthe = periodS.substring(4, 6);
//							String periodE = bdPeriodManager.getFutureBdYearMonth(yeare, monthe, 13);*/
//							
//							
//							BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByTime(new Date(),null);
//							String periodS = bdPeriodManager.getFutureBdYearMonth(bdPeriod.getWyear().toString(), bdPeriod.getWmonth().toString(), 1);
//							String periodE = bdPeriodManager.getFutureBdYearMonth(bdPeriod.getWyear().toString(), bdPeriod.getWmonth().toString(), 12);
//							
//							jpoMemberOrder.getSysUser().getJmiMember().setStartWeek(Integer.parseInt(periodS));
//							jpoMemberOrder.getSysUser().getJmiMember().setValidWeek(Integer.parseInt(periodE));
//							
//							BdPeriod bdPeriodSR = bdPeriodManager.getBdPeriodByTime(jpoMemberOrder.getCheckDate(),null);
//							Integer startMonthSR = bdPeriodSR.getWyear()*100 + bdPeriodSR.getWmonth();
//							
//							if(startMonthSR<=Integer.parseInt(periodE)){
//								//当前期别小于会员的冻结期别解冻
//								jpoMemberOrder.getSysUser().getJmiMember().setFreezeStatus(0);
//								JbdUserValidList jbdUserValidList = new JbdUserValidList();
//								jbdUserValidList.setJmiMember(jpoMemberOrder.getSysUser().getJmiMember());
//								jbdUserValidList.setNewFreezeStatus(0);
//								jbdUserValidList.setOldFreezeStatus(1);
//								jbdUserValidList.setWweek(bdPeriodSR.getWyear()*100 + bdPeriodSR.getWweek());
//								Set<JbdUserValidList> jbdUserValidListSet = new HashSet<JbdUserValidList>(0);
//								jbdUserValidListSet.add(jbdUserValidList);
//								jpoMemberOrder.getSysUser().getJmiMember().setJbdUserValidList(jbdUserValidListSet);
//								
//								
//								// 角色
//								String roleId="";
//								JmiStore jmiStore = jmiStoreManager.getJmiStoreByUserCode(jpoMemberOrder.getSysUser().getUserCode());
//								if("1".equals(jpoMemberOrder.getSysUser().getJmiMember().getIsstore())){
//										roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), Constants.JOCS_STORE1);
//								}else if("2".equals(jpoMemberOrder.getSysUser().getJmiMember().getIsstore())){
//										roleId= this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), Constants.JOCS_STORE2);
//								}else if("1".equals(jpoMemberOrder.getSysUser().getJmiMember().getSubStoreStatus())){
//									//二级已审
//									roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), Constants.JOCS_STORE2_X);
//								}else if(jmiStore!=null && "1".equals(jmiStore.getCheckStatus())){
//									//一级已审
//									roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), Constants.JOCS_STORE1_X);
//								}else{
//									
//									//通过身份判断角色,默认是0 优惠顾客 1 永远优惠顾客2 会员3,两个优惠顾客都是取优惠顾客角色,会员就取普通会员,0就取待审
//									Integer greadType = jpoMemberOrder.getSysUser().getJmiMember().getGradeType();
//									if(greadType == 3){
//										roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), Constants.JOCS_ROLE_NORMAL);
//									}else if(greadType == 2 || greadType == 1){
//										roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), Constants.JOCS_ROLE5);
//									}else {
//										roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), Constants.JOCS_ROLE0);
//									}
//									
////									roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), Constants.JOCS_ROLE_NORMAL);
//								}
//								this.getChangeJmiMemberRole(jpoMemberOrder.getSysUser(), roleId);
//							}
//						}else{
//							throw new AppException("冻结状态不明确");
//						}
//					}else{
//						throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessRSId,operatorSysUser));
//					}
//					break;
//				case 4://会员重销
//					int businessRId = this.getJpoMemberOrderBusinessMR(jpoMemberOrder);
//					if(businessRId==40){
//						
//					}else{
//						throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessRId,operatorSysUser));
//					}
//					break;
//				case 5://辅销品订单
//					int businessAId = this.getJpoMemberOrderBusinessMA(jpoMemberOrder);
//					if(businessAId==40){
//						
//					}else{
//						throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessAId,operatorSysUser));
//					}
//					break;
//				case 6://店铺首购
//					int businessSFId = this.getJpoMemberOrderBusinessSF(jpoMemberOrder);
//					if(businessSFId==60){
//
//				    	BigDecimal amount = new BigDecimal(Constants.sysConfigMap.get(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase()).get("store.f.order.amount"));
//				    	BigDecimal pvAmt = new BigDecimal(Constants.sysConfigMap.get(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase()).get("store.f.order.pvamt"));
//				    	
//				    	if("LC".equals(jpoMemberOrder.getProductType())){
//					    	if(pvAmt.compareTo(jpoMemberOrder.getPvAmt())!=1){
//					    		
//					    	}else{
//					    		throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购金额不正确");
//					    	}
//				    	}else{
//					    	if(amount.compareTo(sumPrice)!=1){
//
//					    	}else{
//					    		throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购金额不正确");
//					    	}
//				    	}
//						//角色
//			    		String roleId;
//			    		roleId = this.getConfigValue(jpoMemberOrder.getSysUser()
//								.getCompanyCode(), Constants.JOCS_STORE1);
//						
//						this.getChangeJmiMemberRole(jpoMemberOrder.getSysUser(), roleId);
//						jpoMemberOrder.getSysUser().getJmiMember().setIsstore("1");
//						jpoMemberOrder.getSysUser().getJmiMember().setRecommendStore(jpoMemberOrder.getSysUser().getJmiMember().getRecommendNo());
//						
//					}else{
//						throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessSFId,operatorSysUser));
//					}
//					break;
//				case 7://店铺升级单
//					break;
//				case 8://店铺返单
//					break;
//				case 9://店铺重销
//					int businessSRId = this.getJpoMemberOrderBusinessSR(jpoMemberOrder);
//					if(businessSRId==90){
//						
//					}else{
//						throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessSRId,operatorSysUser));
//					}
//					break;
//				case 11://二级馆首购单
//					int businessSSubFId = this.getJpoMemberOrderBusinessSSubF(jpoMemberOrder);
//					if(businessSSubFId==60){
//				    	BigDecimal pvamt = new BigDecimal(Constants.sysConfigMap.get(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase()).get("store.f2.order.pvamt"));
//				    	BigDecimal amount = new BigDecimal(Constants.sysConfigMap.get(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase()).get("store.f2.order.amount"));
//				    	
//				    	if("LC".equals(jpoMemberOrder.getProductType()) && pvamt.compareTo(jpoMemberOrder.getPvAmt())!=1){
//				    		
//				    	}else if(!"LC".equals(jpoMemberOrder.getProductType())){
//				    		if(amount.compareTo(sumPrice)==1){
//				    			throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购金额不正确");
//				    		}
//				    		if("HK".equals(jpoMemberOrder.getCompanyCode())){
//				    			BigDecimal pv = new BigDecimal(Constants.sysConfigMap.get(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase()).get("store.f2.order.pv"));
//					    		if(pv.compareTo(jpoMemberOrder.getPvAmt())==1){
//					    			throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购PV不正确");
//					    		}
//				    		}
//				    	}else{
//				    		throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购金额不正确");
//				    	}
//						//角色
//				    	String roleId;
//				    	//正式二级店铺权限
//						roleId = this.getConfigValue(jpoMemberOrder.getSysUser()
//								.getCompanyCode(), "jocs.member.role.store2");
//				    	
//				    	this.getChangeJmiMemberRole(jpoMemberOrder.getSysUser(), roleId);
//						jpoMemberOrder.getSysUser().getJmiMember().setIsstore("2");
//						jpoMemberOrder.getSysUser().getJmiMember().setSubStoreStatus("2");
//						jpoMemberOrder.getSysUser().getJmiMember().setSubStoreCheckDate(jpoMemberOrder.getCheckDate());
//					}else{
//						throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessSSubFId,operatorSysUser));
//					}
//					break;
//				case 12://二级馆升级单
//					int businessSSubUId = this.getJpoMemberOrderBusinessSSubU(jpoMemberOrder);
//					if(businessSSubUId==90){
//						BigDecimal pv = new BigDecimal(Constants.sysConfigMap.get(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase()).get("store.u2.order.pv"));
//				    	BigDecimal amount = new BigDecimal(Constants.sysConfigMap.get(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase()).get("store.u2.order.amount"));
//						
//						if("LC".equals(jpoMemberOrder.getProductType())){
//							pv = new BigDecimal(Constants.sysConfigMap.get(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase()).get("store.u2.orderlc.pv"));
//							amount = new BigDecimal("0");
//						}
//						
//	        			java.util.Calendar startc=java.util.Calendar.getInstance();
//	        	    	startc.set(2011, 6, 16, 00, 00, 00);
//	        	    	java.util.Calendar endc=java.util.Calendar.getInstance();
//	        	    	endc.set(2011, 7, 6, 00, 00, 00);
//	        	    	java.util.Date startDate=startc.getTime();
//	        	    	java.util.Date endDate=endc.getTime();
//	        	    	Date curDate=new Date();
//	        	    	if((curDate.after(startDate))&&(curDate.before(endDate))){
//	        	    		Iterator its11 = jpoMemberOrder.getJpoMemberOrderList().iterator();
//	        	        	while (its11.hasNext()) {
//	        	    			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its11.next();
//	        	        		if("P13010200201CN0".equals(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo())){
//	        	        			pv = new BigDecimal("0");
//	        	        		}
//	        	    		}
//	        	    	}
//						
//						if(pv.compareTo(jpoMemberOrder.getPvAmt())<1){
//							if(amount.compareTo(sumPrice)<1){
//								//角色
//								String roleId = this.getConfigValue(jpoMemberOrder
//										.getSysUser().getCompanyCode(),
//										Constants.JOCS_STORE1);
//								this.getChangeJmiMemberRole(jpoMemberOrder.getSysUser(), roleId);
//								jpoMemberOrder.getSysUser().getJmiMember().setIsstore("1");
//							}else{
//					    		throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购金额不正确");
//							}
//				    	}else{
//				    		throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购金额不正确");
//				    	}
//				    	JmiStore jmiStore = jmiStoreManager.getJmiStoreByUserCode(jpoMemberOrder.getSysUser().getUserCode());
//				    	if(jmiStore!=null){
//				    		jmiStore.setOrderTime(jpoMemberOrder.getCheckTime());
//				    		jmiStore.setOrderDate(jpoMemberOrder.getCheckDate());
//				    		jmiStoreManager.saveJmiStore(jmiStore);
//				    	}
//				    	jpoMemberOrder.getSysUser().getJmiMember().setRecommendStore(jpoMemberOrder.getSysUser().getJmiMember().getRecommendNo());
//					}else{
//						throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessSSubUId,operatorSysUser));
//					}
//					break;
//				case 13:
//					break;
//				case 14://二级馆重销单
//					int businessSSubRId = this.getJpoMemberOrderBusinessSSubR(jpoMemberOrder);
//					if(businessSSubRId==90){
//						
//					}else{
//						throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessSSubRId,operatorSysUser));
//					}
//					break;
//				case 15://会员AUTOSHIP
//					int businessASId = this.getJpoMemberOrderBusinessAS(jpoMemberOrder);
//					if(businessASId==40){
//						
//					}else{
//						throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessASId,operatorSysUser));
//					}
//					break;
//				case 21://活力小铺首单
//					int businessMFId = this.getJpoMemberOrderBusinessSMF(jpoMemberOrder);
//					if(businessMFId==90){
//						BigDecimal amount  =new BigDecimal(this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase(), "store.m.order.amount"));
//				    	BigDecimal pv  =new BigDecimal(this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase(), "store.m.order.pv"));
//				    	if(amount.compareTo(sumPrice)==1){
//				    		throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购金额不正确");
//				    	}
//				    	if(pv.compareTo(jpoMemberOrder.getPvAmt())==1){
//				    		throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购PV不正确");
//				    	}
//					}else{
//						throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessMFId,operatorSysUser));
//					}
//					break;
//				case 24://活力小铺重销单
//					int businessMRId = this.getJpoMemberOrderBusinessSMR(jpoMemberOrder);
//					if(businessMRId==90){
//						
//					}else{
//						throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessMRId,operatorSysUser));
//					}
//					break;
//			}
//			logCurDate=new Date();
//			
//			if("4".equals(jpoMemberOrder.getOrderType()) || "9".equals(jpoMemberOrder.getOrderType()) 
//					|| "14".equals(jpoMemberOrder.getOrderType())){
//				//不冻，35PV订单
//				String yearsMember = jpoMemberOrder.getSysUser().getJmiMember().getStartWeek().toString().substring(0, 4);
//				String monthsMember = jpoMemberOrder.getSysUser().getJmiMember().getStartWeek().toString().substring(4, 6);
//				List periodsMember = bdPeriodManager.getBdPeriodsByMonth(yearsMember, monthsMember);
//				BdPeriod bdPeriod = (BdPeriod)periodsMember.get(0);
//				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				CommonRecord crm = new CommonRecord();
//				crm.addField("stauts", "2");
//				crm.addField("userCode", jpoMemberOrder.getSysUser().getUserCode());
//				crm.addField("orderType", "4,9,14");
//				crm.addField("startLogTime", dateFormat.format(bdPeriod.getStartTime()));
//				BigDecimal pvAmt = dao.getJpoMemberOrderStatics(crm);
//				pvAmt = pvAmt.add(jpoMemberOrder.getPvAmt());
//				
//				//时间限制
//				java.util.Calendar startcPre=java.util.Calendar.getInstance();
//				startcPre.set(2012, 8, 29, 00, 00, 00);
//				java.util.Date startDatePre = startcPre.getTime();
//				Date curDatePre = new Date();
//				if ("CN".equals(jpoMemberOrder.getCompanyCode())) {
//					if (curDatePre.after(startDatePre)
//							&& new BigDecimal("42").compareTo(pvAmt) != 1) {
//						BdPeriod bdPeriodSR = bdPeriodManager.getBdPeriodByTime(
//								jpoMemberOrder.getCheckDate(), null);
//						Integer startMonthSR = bdPeriodSR.getWyear() * 100
//								+ bdPeriodSR.getWmonth();
//						if (jpoMemberOrder.getSysUser().getJmiMember()
//								.getStartWeek() <= startMonthSR) {
//							if (!StringUtil.isEmpty(jpoMemberOrder.getSysUser()
//									.getJmiMember().getValidWeek().toString())) {
//								
//								String years = jpoMemberOrder.getSysUser()
//										.getJmiMember().getValidWeek().toString()
//										.substring(0, 4);
//								String months = jpoMemberOrder.getSysUser()
//										.getJmiMember().getValidWeek().toString()
//										.substring(4, 6);
//								String periodS = bdPeriodManager
//										.getFutureBdYearMonth(years, months, 2);
//								String yeare = periodS.substring(0, 4);
//								String monthe = periodS.substring(4, 6);
//								String periodE = bdPeriodManager
//										.getFutureBdYearMonth(yeare, monthe, 12);
//								jpoMemberOrder.getSysUser().getJmiMember()
//										.setStartWeek(Integer.parseInt(periodS));
//								jpoMemberOrder.getSysUser().getJmiMember()
//										.setValidWeek(Integer.parseInt(periodE));
//							} else {
//								throw new AppException("ValidWeek为空！");
//							}
//						}
//					} 
//				} else if (!"CN".equals(jpoMemberOrder.getCompanyCode())
//						&& new BigDecimal("21").compareTo(pvAmt) != 1) {
//					BdPeriod bdPeriodSR = bdPeriodManager.getBdPeriodByTime(
//							jpoMemberOrder.getCheckDate(), null);
//					Integer startMonthSR = bdPeriodSR.getWyear() * 100
//							+ bdPeriodSR.getWmonth();
//					if (jpoMemberOrder.getSysUser().getJmiMember().getStartWeek() <= startMonthSR) {
//						if (!StringUtil.isEmpty(jpoMemberOrder.getSysUser()
//								.getJmiMember().getValidWeek().toString())) {
//							String years = jpoMemberOrder.getSysUser()
//									.getJmiMember().getValidWeek().toString()
//									.substring(0, 4);
//							String months = jpoMemberOrder.getSysUser()
//									.getJmiMember().getValidWeek().toString()
//									.substring(4, 6);
//							String periodS = bdPeriodManager.getFutureBdYearMonth(
//									years, months, 2);
//							String yeare = periodS.substring(0, 4);
//							String monthe = periodS.substring(4, 6);
//							String periodE = bdPeriodManager.getFutureBdYearMonth(yeare, monthe, 12);
//							jpoMemberOrder.getSysUser().getJmiMember().setStartWeek(Integer.parseInt(periodS));
//							jpoMemberOrder.getSysUser().getJmiMember().setValidWeek(Integer.parseInt(periodE));
//						}else{
//							throw new AppException("ValidWeek为空！");
//						}
//					}
//				}
//				//==============续约次年
//			}
//			
//				/**
//				 * TODO Jun 促销策略
//				 */
//				log.info("loginUser is:["+jpoMemberOrder.getSysUser().getUserCode()+ "] " +
//					"and user oldLever is:["+jpoMemberOrder.getSysUser().getJmiMember().getCardType()+"] " +
//					"and orderNo is:"+jpoMemberOrder.getMemberOrderNo());
//			
//				/*
//				 * Date cur_date = Calendar.getInstance().getTime();
//				 * 正式环境未做时间同步, 才如此获取
//				 */
//				Date cur_date = ServerDateUtil.getNowTimeFromDateServer();
//				String stime = DateUtil.getDate(cur_date,"yyyy-MM-dd HH:mm:ss");
//				List<JpmSalePromoter> spList = jpmSalePromoterDao.
//						getSaleByDate(stime, Constants.JPMSALE_ACTIVA,null);
//				
//				log.info("curDate is:["+stime+"] jpmSalePromoter list size is:"+spList.size());
//				try{
//					for(JpmSalePromoter sp: spList){
//						if(isOrderSales(sp,jpoMemberOrder)){
//							//1.折扣促销 2.赠品促销 3.送积分促销4:按订单总金额或PV赠送
//							String saleType = sp.getSaleType();
//							if(saleType.equals(Constants.JPMSALE_SALETYPE_PRE)){
//								if(isOrderType(sp.getUserLevel(), newCard)){
//									//按对应买的商品送赠品
//									jpoMemberOrder.getJpoMemberOrderList().addAll(
//											bindPresentProduct(jpoMemberOrder,sp));
//								}
//								
//							} else if(saleType.equals(Constants.JPMSALE_SALETYPE_INTEGRAL)){
//								if(isOrderType(sp.getUserLevel(),newCard)){
//									// 赠送积分促销策略
//									getBindIntegral(jpoMemberOrder,sp,"0",operatorSysUser,dataType);
//									//是否赠送推荐人0否, 1送
//									String ispresent = sp.getIspresent();
//									SysUser ruser = jpoMemberOrder.getSysUser().
//											getJmiMember().getJmiRecommendRef().
//											getRecommendJmiMember().getSysUser();
//									
//									if(StringUtils.isNotBlank(ispresent) && ispresent.equals("1") && 
//												ruser!=null && ruser.getUserCode()!=null ){
//										getBindIntegral(jpoMemberOrder,sp,"1",operatorSysUser,dataType);
//									}
//								}
//								
//							} else if(saleType.equals(Constants.JPMSALE_SALETYPE_ORDER)){
//								if(isOrderType(sp.getUserLevel(),newCard)){
//									// 按订单总金额,订单类型或PV送赠品
//									Set<JpoMemberOrderList> orderSet= new HashSet<JpoMemberOrderList>();
//									orderSet= getOrderAmountBindProduct(jpoMemberOrder,sp);
//									if (! orderSet.isEmpty()) {
//										jpoMemberOrder.getJpoMemberOrderList().addAll(orderSet);
//									}
//								}
//							} 
//						}
//					}
//				}catch(Exception e){
//					log.error("salePromoter failed," +
//							"orderNo is:"+jpoMemberOrder.getMemberOrderNo(), e);
//					throw new AppException("salePromoter failed," +
//							"orderNo is:"+jpoMemberOrder.getMemberOrderNo());
//				}
//		
//				//购达到vip 赠品绑定 2014-07-04 23:59:59 结束
//
//				String userCode = jpoMemberOrder.getSysUser().getUserCode();
//				String comCode = jpoMemberOrder.getCompanyCode();
//				BigDecimal pvamt = jpoMemberOrder.getPvAmt();
//				
//				JmiMember member = jmiMemberDao.getJmiMember(userCode);
//				String order_Type = jpoMemberOrder.getOrderType();
//				
//				Calendar curDate = Calendar.getInstance();
//				Calendar endDate = Calendar.getInstance();
//				endDate.set(2014, 7, 1, 23, 59, 59);
//				
//				log.info("orderNo="+jpoMemberOrder.getMemberOrderNo()+" " +
//						" userCode:"+userCode+" and pvamt:"+pvamt.toString()+" " +
//						" and carType="+member.getCardType()+" orderTyp="+order_Type);
//				
//				if(curDate.before(endDate) && order_Type.equals("1") && 
//						"CN".equalsIgnoreCase(comCode) && 
//						pvamt.compareTo(new BigDecimal(2750))> -1 && 
//						member.getCardType().equals("6")){
//					
//					String order_userCode = jmiMemberTeamManager.
//							getTeamStr(jpoMemberOrder.getSysUser());
//					//不参加的团队
//					String noAcce = "CN30768473,CN58387198,CN12365064,CN18728599," +
//							"CN62827846,CN40449939,CN55092684,CN10919893,CN16481747";
//					
//					log.info("order user TeamCode is:["+userCode+"]");
//					
//					if(!(noAcce.indexOf(order_userCode) >-1)){
//						log.info("vip saleProduct is orderNo="+jpoMemberOrder.getMemberOrderNo());
//						JpoMemberOrderList jpoMemberOrderList = new JpoMemberOrderList();
//						
//						JpmProductSaleTeamType jpmProductSale = jpmProductSaleManager.
//								getJpmProductSaleTeamType(comCode, 
//										"P01170100101CN0",order_Type,
//										jpoMemberOrder.getTeamCode());
//						
//		            	jpoMemberOrderList.setJpmProductSaleTeamType(jpmProductSale);
//		            	jpoMemberOrderList.setJpoMemberOrder(jpoMemberOrder);
//		            	jpoMemberOrderList.setPrice(new BigDecimal("0"));
//		            	jpoMemberOrderList.setPv(new BigDecimal("0"));
//		            	jpoMemberOrderList.setQty(1);
//		            	
//		            	jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().
//		            		setVolume(jpmProductSale.getJpmProductSaleNew().getVolume());
//		            	jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().
//		            		setWeight(jpmProductSale.getJpmProductSaleNew().getWeight());
//		            	
//		            	jpoMemberOrder.getJpoMemberOrderList().add(jpoMemberOrderList);
//		            	
//		            	//P23020100101CN0 尊•道和”中秋月饼礼盒
//		            	JpoMemberOrderList jpoMemberOrderList1 = new JpoMemberOrderList();
//						
//		            	JpmProductSaleTeamType jpmProductSale1 = jpmProductSaleManager.
//								getJpmProductSaleTeamType(comCode, 
//										"P23020100101CN0",order_Type,
//										jpoMemberOrder.getTeamCode());
//						
//						jpoMemberOrderList1.setJpmProductSaleTeamType(jpmProductSale1);
//						jpoMemberOrderList1.setJpoMemberOrder(jpoMemberOrder);
//						jpoMemberOrderList1.setPrice(new BigDecimal("0"));
//						jpoMemberOrderList1.setPv(new BigDecimal("0"));
//						jpoMemberOrderList1.setQty(1);
//		            	
//						jpoMemberOrderList1.getJpmProductSaleTeamType().getJpmProductSaleNew().
//		            		setVolume(jpmProductSale1.getJpmProductSaleNew().getVolume());
//						jpoMemberOrderList1.getJpmProductSaleTeamType().getJpmProductSaleNew().
//		            		setWeight(jpmProductSale1.getJpmProductSaleNew().getWeight());
//		            	
//		            	jpoMemberOrder.getJpoMemberOrderList().add(jpoMemberOrderList1);
//					}
//				}
//				//end vip 赠品
//			
//			if("TW".equals(jpoMemberOrder.getCompanyCode())){
//					if(jpoMemberOrder.getPvAmt().compareTo(new BigDecimal(70))!=-1){
//		            	JpoMemberOrderList jpoMemberOrderList = new JpoMemberOrderList();
//						JpmProductSaleTeamType jpmProductSale = jpmProductSaleManager.getJpmProductSaleTeamType("104261");
//		            	jpoMemberOrderList.setJpmProductSaleTeamType(jpmProductSale);
//		            	jpoMemberOrderList.setJpoMemberOrder(jpoMemberOrder);
//		            	jpoMemberOrderList.setPrice(new BigDecimal("0"));
//		            	jpoMemberOrderList.setPv(new BigDecimal("0"));
//		            	BigDecimal qtyBig = jpoMemberOrder.getPvAmt().divide(new BigDecimal(70), BigDecimal.ROUND_DOWN);
//		            	jpoMemberOrderList.setQty(qtyBig.intValue());
//		            	jpoMemberOrderList.setVolume(jpmProductSale.getJpmProductSaleNew().getVolume());
//		            	jpoMemberOrderList.setWeight(jpmProductSale.getJpmProductSaleNew().getWeight());
//		            	jpoMemberOrder.getJpoMemberOrderList().add(jpoMemberOrderList);
//					}
//			}
//				
//				String limitIsShipments = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "limitisshipments");//1代表自动发货即审核完订单即可发货，0表示手动处理发货
//				if("1".equals(limitIsShipments))
//				{
//				   jpoMemberOrder.setIsShipping("1");//1表示已经发货
//				}else
//				{
//					 jpoMemberOrder.setIsShipping("0");//表示还没有发货
//				}
//				FileUtil.saveLogger(fileName, logCurDate, new Date(), "促销代码与积分"+jpoMemberOrder.getMemberOrderNo());
//				logCurDate=new Date();
//				
//				//扣款
//				logCurDate=new Date();
//				log.info("jpoMemberOrder.getStatus().equals('1')：  " + jpoMemberOrder.getStatus().equals("1"));
//				
//				if(jpoMemberOrder.getStatus().equals("1") && !jpoMemberOrder.getIsPay().equals("1")){  //快钱支付和后台审单需要扣电子存折
//					
//						this.getSaveMemberOrderDeduct(jpoMemberOrder, operatorSysUser, dataType);
//						jpoMemberOrder.setIsPay("1");
//						FileUtil.saveLogger(fileName, logCurDate, new Date(), "扣款"
//								+ jpoMemberOrder.getMemberOrderNo());
//				}
//				
//				jpoMemberOrder.setStatus("2");
//				jpoMemberOrder.setStatusSysMo(1);
//				dao.saveJpoMemberOrder(jpoMemberOrder);
//				FileUtil.saveLogger(fileName, logCurDate, new Date(), "保存订单表"+jpoMemberOrder.getMemberOrderNo());
//				
//				
//				logCurDate=new Date();
//				if(!"21".equals(jpoMemberOrder.getOrderType()) && !"24".equals(jpoMemberOrder.getOrderType()) && !"32".equals(jpoMemberOrder.getOrderType())){
//					//插入每日计算表
//					JbdSummaryList jbdSummaryList=new JbdSummaryList();
//					jbdSummaryList.setUserCode(jpoMemberOrder.getSysUser().getUserCode());
//					jbdSummaryList.setCardType(jpoMemberOrder.getSysUser().getJmiMember().getCardType());
//					jbdSummaryList.setInType(4);
//					jbdSummaryList.setCreateTime(jpoMemberOrder.getCheckTime());
//					jbdSummaryList.setOrderType(jpoMemberOrder.getOrderType());
//					jbdSummaryList.setOldCheckDate(null);
//					jbdSummaryList.setNewCheckDate(jpoMemberOrder.getCheckDate());
//					jbdSummaryList.setPvAmt(jpoMemberOrder.getPvAmt());
//					jbdSummaryList.setOldLinkNo(null);
//					jbdSummaryList.setNewLinkNo(null);
//					jbdSummaryList.setOldRecommendNo(null);
//					jbdSummaryList.setNewRecommendNo(null);
//					jbdSummaryList.setNewCompanyCode(jpoMemberOrder.getCompanyCode());
//					jbdSummaryList.setUserCreateTime(null);
//					jbdSummaryList.setWweek(bdPeriodManager.getBdPeriodByTimeFormated(jpoMemberOrder.getCheckDate(), null));
//					jbdSummaryList.setOrderNo(String.valueOf(jpoMemberOrder.getMoId()));
//					jbdSummaryListManager.saveJbdSummaryList(jbdSummaryList);
//				}
//				//送JOYME号
//				try{
//					jpmCardSeqManager.saveUserJpmCardSeq(jpoMemberOrder, oldCard, newCard);
//				}catch(AppException app){
//					throw new AppException(this.getLocalLanguageByKey(app.getMessage(),operatorSysUser));
//				}
//
//				FileUtil.saveLogger(fileName, logCurDate, new Date(), "实时计算与送JOYME号"+jpoMemberOrder.getMemberOrderNo());
//
//				logCurDate=new Date();
//				if(!"1".equals(jpoMemberOrder.getShippingPay())){
//				
//					//发货
//					try{
//						if("1".equals(limitIsShipments))
//						{
//							this.pdSendInfoManager.doWhileOrderConfirmed(jpoMemberOrder);
//						}
//						
//					}catch(AppException app){
//						throw new AppException(this.getLocalLanguageByKey(app.getMessage(),operatorSysUser));
//					}
//				}
//
//				FileUtil.saveLogger(fileName, logCurDate, new Date(), "发货"+jpoMemberOrder.getMemberOrderNo());
//				
//				try{
//					//TODO sms
//					String isPost = ConfigUtil.getConfigValue("CN", "sms.ispost");
//					String movieUrl = ConfigUtil.getConfigValue("CN", "sms.movieurl");
//					
//					log.info("isPost sms is:["+isPost+"] and movieUrl is:"+movieUrl);
//					
//					if(Boolean.parseBoolean(isPost)){
//						JpoMovie mv = jpoMovieDao.getMovieByOrderNo(jpoMemberOrder.getMemberOrderNo());
//						if(mv ==null){
//							Iterator<JpoMemberOrderList> orderListIte = jpoMemberOrder.getJpoMemberOrderList().iterator();
//							while(orderListIte.hasNext()){
//								JpoMemberOrderList orderList = orderListIte.next();
//								String proNo = orderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
//								
//								if(proNo.equalsIgnoreCase(Constants.MOVIE_PRONO) || proNo.equalsIgnoreCase(Constants.MOVIE_PRONO2)){
//									String url1 = ListUtil.getListValue("CN", "smssend.url", "1");
//									String url2 = ListUtil.getListValue("CN", "smssend.url", "2");
//									List<JpoMovie> movieList = jpoMovieDao.findMovieByName('0');
//									
//									if(movieList !=null && movieList.size()>0){
//										JpoMovie movie = movieList.get(0);
//										
//										StringBuffer message = new StringBuffer();
//										/* 
//										 * 您已经成功获取养生套餐包影票产品（共300张影票），用户名：XXXXXX，密码：XXXXXX；
//										 * 请凭此用户名及密码登录http://fm.daohegroup.cn/进行选票（2014年2月18日零点正式开通）。
//										 * 咨询客服：025-83617786，QQ平台：10310323，祝您观影愉快！
//										 */
//										message.append("您已经成功获取养生套餐包影票产品（共300张影票）");
//										message.append(",用户名："+movie.getMaccount());
//										message.append(",密码："+movie.getMpwd());
//										message.append("; 请凭此用户名及密码登录:"+movieUrl);
//										message.append("进行选票(2014年2月18日零点正式开通).");
//										message.append("咨询客服：025-83617786，QQ平台：10310323，祝您观影愉快！");
//										
//										SmsSend.sendSms(url1, url2, jpoMemberOrder.getMobiletele(), message.toString());
//										
//										//update jpoMovie setting OrderNo
//										log.info("movieId is:"+movie.getmId()+" and orderNo is:"+jpoMemberOrder.getMemberOrderNo());
//										movie.setOrderNo(jpoMemberOrder.getMemberOrderNo());
//										movie.setStatus('1');
//										jpoMovieDao.saveObject(movie);
//										
//										//将短信写入到数据库表
//										JpmSmssendInfo jpmSmssendInfo = new JpmSmssendInfo();
//										jpmSmssendInfo.setSmsType("3");////短信类型  例如：1：仓库发货确认    2：找回密码   3：电影票  目前只有三种，在列表中配置
//										jpmSmssendInfo.setSmsRecipient(movie.getMaccount());//短信接收人:用户会员编号
//										jpmSmssendInfo.setSmsMessage(message.toString());//短信内容
//										jpmSmssendInfo.setSmsTime(new Date());//发送时间
//										jpmSmssendInfo.setSmsOperator("");//操作人
//										jpmSmssendInfo.setSmsStatus("1");//保留字段，是否发送成功！ 默认为1：成功！ 现在短信还不能知道是否发送成功，没有返回值！
//										jpmSmssendInfo.setRemark("erp后台");//备注
//										jpmSmssendInfo.setPhoneNum(jpoMemberOrder.getMobiletele());
//										jpmProductSaleNewDao.saveJpmSmssendInfo(jpmSmssendInfo);
//										
//									} else {
//										log.warn("movie Ticket is null, orderNo is:"+jpoMemberOrder.getMemberOrderNo());
//									}
//									break;
//								}
//							}
//						}
//					}
//				}catch(Exception e){
//					log.warn("movie Error,orderNo is:"+jpoMemberOrder.getMemberOrderNo(),e);
//				}
//				
//				log.info("check Order End ,orderNo is:"+jpoMemberOrder.getMemberOrderNo());
//			//	jprRefundManager.getFunctionRp(jpoMemberOrder.getMemberOrderNo(),1);//调用sql函数 执行大订单结算降级黄砖会员，幸福会员
//				
//			
//			
//		}
//		
//		
//	}

	/**
	 * 验证产品购买数量是否超过限制 (20160101  财月) 支付
	 * @param jpoMemberOrder
	 * @return true or false
	 */
	protected boolean isOverQty(JpoMemberOrder jpoMemberOrder){
		
		Integer proSum=0, proNoCount=-1,countQty=0;
		List<String> productNos = new ArrayList();
    	List<JpoProductNumLimit> jpoProductNumLimits = jpoProductNumLimitManager.getAllPros();
    	for (JpoProductNumLimit jpoProductNumLimit : jpoProductNumLimits) {
    		productNos.add(jpoProductNumLimit.getProductNo());
		}
    	
    	Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
    	
		while (its1.hasNext()) {
			
    		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
    		
    		String proNo =jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo();
    		
    		if(productNos.contains(proNo)){
    			
    			JpoProductNumLimit plimit =  jpoProductNumLimitManager.getNum(proNo);
    			proNoCount = plimit.getNum().intValue();
    			
    			String statetime = null;
    	    	String endtime = null;
    	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			if(!"".equals(plimit.getStartime()) && plimit.getStartime() != null){
        			statetime = sdf.format(plimit.getStartime());
        		}
        		if(!"".equals(plimit.getEndtime()) && plimit.getEndtime() != null){
 
        			endtime = sdf.format(plimit.getEndtime());
        		}
    			proSum = jpoMemberOrderListDao.getProSumByProNo(proNo, statetime, endtime);
    			countQty = jpoMemberOrderList.getQty();
        		
    			log.info("proNoCount:" + proNoCount +"  已支付：" + proSum  +"  当前：" + countQty);
    			/*
    			 * 购买数量大于剩余数量, 或者统计数量大于等于库存数量
    			 */
    			if((proNoCount-proSum) < countQty || proSum >= proNoCount){
        			return true;
        		}
    		}
    	}
		return false;
	} 

	/**
	 * 发展基金抵扣审核会员订单
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 * @throws Exception 
	 */
	public void checkJpoMemberOrderByJJ(JpoMemberOrder jpoMemberOrder, SysUser operatorSysUser,String dataType) throws Exception{

		String userSpCode = jpoMemberOrder.getUserSpCode();
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = jpoMemberOrder.getJjAmount();
		Integer[] moneyType = new Integer[1];
		moneyType[0] = 13;
		String[] notes = new String[1];
		notes[0] = this.getLocalLanguageByKey("poMemberOrder.orderConfirm",operatorSysUser) + jpoMemberOrder.getMemberOrderNo();
		if(!userSpCode.equals(jpoMemberOrder.getSysUser().getUserCode())){
			notes[0] += "," + jpoMemberOrder.getSysUser().getUserCode();
		}
		SysUser sysUserSp = sysUserDao.getSysUser(userSpCode);
		
		//判断订单是否包含旅游积分补款产品,决定资金类别是否为24
    	if(this.jpoMemberOrderIsIncludeTourCoin(jpoMemberOrder)){
    		moneyType[0] = 24;
    	}
    	
		fiBankbookJournalManager.saveFiBankbookDeduct("CN", sysUserSp, moneyType, moneyArray, operatorSysUser.getUserCode(), operatorSysUser.getUserName(), jpoMemberOrder.getMemberOrderNo(), notes, "1", dataType);
		checkJpoMemberOrder(jpoMemberOrder,operatorSysUser, dataType);
	}
	
	/**
	 * 积分抵扣审核会员订单
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 */
	public void checkJpoMemberOrderByCoinAndBankbook(JpoMemberOrder jpoMemberOrder, SysUser operatorSysUser,String dataType)throws Exception{
		BigDecimal productAmount = new BigDecimal("0");
    	Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
    	while (its1.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			productAmount = productAmount.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
    	}
		FiBcoinBalance fiBcoinBalance = fiBcoinBalanceManager.getFiBcoinBalance(jpoMemberOrder.getSysUser().getUserCode());
		BigDecimal sumCoin = new BigDecimal("0");

		if(productAmount.compareTo(fiBcoinBalance.getBalance().multiply(new BigDecimal("2")))!=1){
			sumCoin = productAmount.multiply(new BigDecimal("0.5"));
		}else{
			sumCoin = fiBcoinBalance.getBalance();
		}
		if(jpoMemberOrder.getAmount().compareTo(sumCoin.multiply(new BigDecimal("2")))==-1){
			throw new AppException("积分计算有误");
		}
		java.util.Calendar startc=java.util.Calendar.getInstance();
		startc.set(2012, 6, 21, 00, 00, 00);
		java.util.Date startDate=startc.getTime();
		Date curDate=new Date();
		if(curDate.after(startDate)){//7月21日，改成辅消品6折
			if("5".equals(jpoMemberOrder.getOrderType())){
				if(productAmount.compareTo(fiBcoinBalance.getBalance().multiply(new BigDecimal("2.5")))!=1){
					sumCoin = productAmount.multiply(new BigDecimal("0.4"));
				}else{
					sumCoin = fiBcoinBalance.getBalance();
				}
				if(jpoMemberOrder.getAmount().compareTo(sumCoin.multiply(new BigDecimal("2.5")))==-1){
					throw new AppException("积分计算有误");
				}
			}
		}
		
		
		if(sumCoin.compareTo(new BigDecimal("0"))!=1){
			checkJpoMemberOrder(jpoMemberOrder,operatorSysUser, dataType);
		}else{
			BigDecimal[] moneyArray = new BigDecimal[1];
			moneyArray[0] = sumCoin;
			Integer[] moneyType = new Integer[1];
			moneyType[0] = 1;
			String[] notes = new String[1];
			notes[0] = this.getLocalLanguageByKey("poMemberOrder.orderConfirm",operatorSysUser) + jpoMemberOrder.getMemberOrderNo();
			Long[] appId = new Long[1];
			appId[0] = 2l;
	    	fiBcoinJournalManager.saveJfiBankbookDeduct(jpoMemberOrder.getCompanyCode(), jpoMemberOrder.getSysUser(), moneyType, moneyArray, operatorSysUser.getUserCode(), operatorSysUser.getUserName(), jpoMemberOrder.getMemberOrderNo(), notes, appId, dataType);
	    	
	    	jpoMemberOrder.setAmount(jpoMemberOrder.getAmount().subtract(sumCoin));
	    	jpoMemberOrder.setDiscountAmount(sumCoin);
	    	jpoMemberOrder.setPvAmt(new BigDecimal("0"));
	    	jpoMemberOrder.setPayByCoin("1");
	    	
	    	its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
	    	while (its1.hasNext()) {
				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
				jpoMemberOrderList.setPv(new BigDecimal("0"));
				jpoMemberOrderListDao.saveJpoMemberOrderList(jpoMemberOrderList);
			}
	    	
	    	dao.saveJpoMemberOrder(jpoMemberOrder);
			
			checkJpoMemberOrder(jpoMemberOrder,operatorSysUser, dataType);
		}
		
	}

	
	/**
	 * 积分抵扣审核会员订单
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 */
	public void checkJpoMemberOrderByCoin(JpoMemberOrder jpoMemberOrder, SysUser operatorSysUser,String dataType) throws Exception{
		BigDecimal sumCoin = new BigDecimal("0");
		int qty = 0;
		Iterator its = jpoMemberOrder.getJpoMemberOrderList().iterator();
    	while (its.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its.next();
			qty += jpoMemberOrderList.getQty();
		}
    	sumCoin = new BigDecimal("830").multiply(new BigDecimal(qty));
    	FiBcoinBalance fiBcoinBalance = fiBcoinBalanceManager.getFiBcoinBalance(jpoMemberOrder.getSysUser().getUserCode());
    	if(sumCoin.compareTo(fiBcoinBalance.getBalance())==1){
    		sumCoin = fiBcoinBalance.getBalance();
    	}
    	jpoMemberOrder.setAmount(jpoMemberOrder.getAmount().subtract(sumCoin));
    	if(jpoMemberOrder.getAmount().compareTo(new BigDecimal("0"))==-1){
    		throw new AppException("审核失败");
    	}

		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = sumCoin;
		Integer[] moneyType = new Integer[1];
		moneyType[0] = 1;
		String[] notes = new String[1];
		notes[0] = this.getLocalLanguageByKey("poMemberOrder.orderConfirm",operatorSysUser) + jpoMemberOrder.getMemberOrderNo();
		Long[] appId = new Long[1];
		appId[0] = 2l;
	    fiBcoinJournalManager.saveJfiBankbookDeduct(jpoMemberOrder.getCompanyCode(), jpoMemberOrder.getSysUser(), moneyType, moneyArray, operatorSysUser.getUserCode(), operatorSysUser.getUserName(), jpoMemberOrder.getMemberOrderNo(), notes, appId, dataType);
	    
	    jpoMemberOrder.setDiscountAmount(sumCoin);
	    jpoMemberOrder.setPayByCoin("1");
	    	
	    dao.saveJpoMemberOrder(jpoMemberOrder);
		
	    if(jpoMemberOrder.getJjAmount()==null || jpoMemberOrder.getJjAmount().compareTo(new BigDecimal(0))!=1){
	    	checkJpoMemberOrder(jpoMemberOrder,operatorSysUser, dataType);
	    }else{
	    	checkJpoMemberOrderByJJ(jpoMemberOrder,operatorSysUser, dataType);
	    }
	}
	
	/**
	 * 根据条件统计商品销售量
	 * @param crm
	 * @return
	 */
	public List getStatisticProductSale(CommonRecord crm){
		return dao.getStatisticProductSale(crm);
	}
	
	/**
	 * 获取一段时间内各个公司(如指定companyCode,则获取对应的公司)的已审核订单
	 * @param startDate
	 * @param endDate
	 * @param companyCode
	 * @return
	 */
	public List getJpoMemberOrderStaticsCheckedCompany(final String startDate, final String endDate, final String companyCode, final String productType, final String checkType){
		return dao.getJpoMemberOrderStaticsCheckedCompany(startDate, endDate, companyCode, productType, checkType);
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//============================================================以下为私有方法
    
    /**
     * 计算升级单新卡别
     * @param jpoMemberOrder
     * @return
     */
    private String getCalcUOrderCardType(JpoMemberOrder jpoMemberOrder){
    	JmiMember jmiMember = jpoMemberOrder.getSysUser().getJmiMember();
    	
    	
		java.util.Calendar startc=java.util.Calendar.getInstance();
		startc.set(2010, 9, 16, 00, 00, 00);
		java.util.Date startDate=startc.getTime();
		Date curDate=new Date();
		java.util.Calendar endc=java.util.Calendar.getInstance();
		endc.set(2010, 9, 21, 00, 00, 00);
		java.util.Date endDate=endc.getTime();
		if("CN".equals(jpoMemberOrder.getCompanyCode()) && "4".equals(jmiMember.getCardType()) && "1".equals(jmiMember.getIsDiscount()) && curDate.after(startDate) && curDate.before(endDate)){
			if(jpoMemberOrder.getAmount().compareTo(new BigDecimal("3000"))!=-1){
				return "6";
			}
		}
    	//优惠顾客升级
		java.util.Calendar startcPre=java.util.Calendar.getInstance();
		startcPre.set(2012, 8, 29, 00, 00, 00);
		java.util.Date startDatePre=startcPre.getTime();
		Date curDatePre=new Date();
		if("CN".equals(jpoMemberOrder.getCompanyCode()) && "5".equals(jmiMember.getCardType())  && curDatePre.after(startDatePre)){
			String member_upgrade_pre = this.getConfigValue(jmiMember.getCompanyCode().toUpperCase(), "member_upgrade_pre");
			BigDecimal memberPv5 = new BigDecimal(this.getConfigValue(jmiMember.getCompanyCode().toUpperCase(), "cardtype.5.value"));
			BigDecimal orderPv = jpoMemberOrder.getPvAmt();
			int tmp = (Integer.parseInt(member_upgrade_pre));
			for(int i=tmp;i<=tmp;i--)
			{
				if(i>=4)
				{
					
					if(orderPv.compareTo(new BigDecimal(this.getConfigValue(jmiMember.getCompanyCode().toUpperCase(), "cardtype.6.value")).subtract(memberPv5))!=-1)
					{
						return "6";
					}
					
				}
				else if(i<4)
				{
					if(i<=0)
					{
						continue;
					}
					 if(orderPv.compareTo(new BigDecimal(this.getConfigValue(jmiMember.getCompanyCode().toUpperCase(), "cardtype."+(i+1)+".value")).subtract(memberPv5))==-1 )
					{
						 continue;
					}else
					{
						return String.valueOf(i+1);
					}
				}else
				{
					return "";
				}
			}
		}
		
		
		
    	String member_upgrade = this.getConfigValue(jmiMember.getCompanyCode().toUpperCase(), "member_upgrade");
    	int cardType = Integer.parseInt(jmiMember.getCardType());
    	BigDecimal memberPv = new BigDecimal(this.getConfigValue(jmiMember.getCompanyCode().toUpperCase(), "cardtype."+jmiMember.getCardType()+".value"));
    	List<SysListValue> sysListValues = sysListValueManager.getSysListValuesByCode("bd.cardtype",jmiMember.getCompanyCode().toUpperCase());
    	
    	BigDecimal orderPv = jpoMemberOrder.getPvAmt();
    	if("2".equals(jpoMemberOrder.getSysUser().getJmiMember().getMemberType())){
    		orderPv = orderPv.add(jmiMember.getOriPv());
    		jmiMember.setOriPv(new BigDecimal("0"));
    	}
    	
    	if(cardType==5){
    		cardType = 0;
    	}
    	
    	int tmp = (Integer.parseInt(member_upgrade) + cardType);
    	if(tmp >= 5){
    		tmp ++;
    	}
    	
		for (int i = tmp; i > cardType; i--) {
			if("PH".equals(jmiMember.getCompanyCode())){
				if(i>=sysListValues.size()){
					continue;
				}
			}else{
				if(i>sysListValues.size() || i > 6){
					continue;
				}
			}
			if(i ==5 || i==0){
				continue;
			}
			if (orderPv.compareTo(new BigDecimal(this.getConfigValue(jmiMember.getCompanyCode().toUpperCase(), "cardtype."+i+".value")).subtract(memberPv))==-1)
				continue;
			else {
				return String.valueOf(i);
			}
		}
    	//return jmiMember.getCardType();
		return "";
    }
    
    /**
     * 计算特殊升级单新卡别
     * @param jpoMemberOrder
     * @return
     */
    private String getCalcSpecialUOrderCardType(JpoMemberOrder jpoMemberOrder){
    	JmiMember jmiMember = jpoMemberOrder.getSysUser().getJmiMember();
    	String member_upgrade = this.getConfigValue(jmiMember.getCompanyCode().toUpperCase(), "member_upgrade");
    	int cardType = Integer.parseInt(jmiMember.getCardType());
    	BigDecimal memberPv = new BigDecimal(this.getConfigValue(jmiMember.getCompanyCode().toUpperCase(), "cardtype."+jmiMember.getCardType()+".value"));
    	List<SysListValue> sysListValues = sysListValueManager.getSysListValuesByCode("bd.cardtype",jmiMember.getCompanyCode().toUpperCase());
    	
    	BigDecimal orderPv = jpoMemberOrder.getPvAmt();
    	if("2".equals(jpoMemberOrder.getSysUser().getJmiMember().getMemberType()) && new BigDecimal("0").compareTo(jmiMember.getOriPv())==-1){
    		//(PV2 - PV1 - X + 35) / 2 = orderPv
    		//PV2 - PV1 = orderPv * 2 - 35 + X
    		orderPv = orderPv.multiply(new BigDecimal("2")).subtract(new BigDecimal("35")).add(jmiMember.getOriPv());
    		jmiMember.setOriPv(new BigDecimal("0"));
    	}else{
    		orderPv = orderPv.multiply(new BigDecimal("2"));
    	}
    	
    	if("HK".equals(jpoMemberOrder.getCompanyCode())){
    		if("1".equals(jmiMember.getCardType()) && orderPv.compareTo(new BigDecimal("630")) != -1){
    			orderPv = orderPv.subtract(new BigDecimal("70"));
    		}
    		if("2".equals(jmiMember.getCardType()) && orderPv.compareTo(new BigDecimal("350")) != -1){
    			orderPv = orderPv.subtract(new BigDecimal("70"));
    		}
    		
    	}
    	
		for (int i = (Integer.parseInt(member_upgrade) + cardType); i > cardType; i--) {
			if("PH".equals(jmiMember.getCompanyCode())){
				if(i>=sysListValues.size()){
					continue;
				}
			}else{
				if(i>sysListValues.size() || i > 6){
					continue;
				}
			}
			if(i ==5 || i==0){
				continue;
			}
			if (orderPv.compareTo(new BigDecimal(this.getConfigValue(jmiMember.getCompanyCode().toUpperCase(), "cardtype."+i+".value")).subtract(memberPv))==-1)
				continue;
			else {
				return String.valueOf(i);
			}
		}
		return "";
    }
    
    /**
     * 计算首购单新卡别
     * @param pv
     * @param companyCode
     * @return
     */
/*    private String getCalcFOrderCardType(BigDecimal pv, String companyCode){
    	BigDecimal pv6=new BigDecimal(this.getConfigValue(companyCode.toUpperCase(), "cardtype."+6+".value"));
    	BigDecimal pv5=new BigDecimal(this.getConfigValue(companyCode.toUpperCase(), "cardtype."+5+".value"));
    	BigDecimal pv4=new BigDecimal(this.getConfigValue(companyCode.toUpperCase(), "cardtype."+4+".value"));
    	BigDecimal pv3=new BigDecimal(this.getConfigValue(companyCode.toUpperCase(), "cardtype."+3+".value"));
    	BigDecimal pv2=new BigDecimal(this.getConfigValue(companyCode.toUpperCase(), "cardtype."+2+".value"));
    	BigDecimal pv1=new BigDecimal(this.getConfigValue(companyCode.toUpperCase(), "cardtype."+1+".value"));
    	BigDecimal pv0=new BigDecimal(this.getConfigValue(companyCode.toUpperCase(), "cardtype."+0+".value"));
    	java.util.Calendar startc=java.util.Calendar.getInstance();
		startc.set(2010, 8, 4, 00, 00, 00);
		java.util.Date startDate=startc.getTime();
		Date curDate=new Date();
    	if(curDate.after(startDate) && !"PH".equals(companyCode) && pv.compareTo(pv6)!=-1){
			return "6";
		}else if(pv.compareTo(pv4)!=-1){
			return "4";
		}else if(pv.compareTo(pv3)!=-1){
			return "3";
		}else if(pv.compareTo(pv2)!=-1){
			return "2";
		}else if(pv.compareTo(pv1)!=-1){
			return "1";
		}else if(pv.compareTo(pv5)!=-1){
			return "5";
		}else if(pv.compareTo(pv0)!=-1){
			return "0";
		}
		return "0";
    }*/
    
    
    
    
    private String getCalcFOrderCardType(BigDecimal pv, String companyCode){
    	BigDecimal pv6=new BigDecimal(this.getConfigValue(companyCode.toUpperCase(), "cardtype."+6+".value"));
    	BigDecimal pv5=new BigDecimal(this.getConfigValue(companyCode.toUpperCase(), "cardtype."+5+".value"));
    	BigDecimal pv4=new BigDecimal(this.getConfigValue(companyCode.toUpperCase(), "cardtype."+4+".value"));
    	BigDecimal pv3=new BigDecimal(this.getConfigValue(companyCode.toUpperCase(), "cardtype."+3+".value"));
    	BigDecimal pv2=new BigDecimal(this.getConfigValue(companyCode.toUpperCase(), "cardtype."+2+".value"));
    	BigDecimal pv1=new BigDecimal(this.getConfigValue(companyCode.toUpperCase(), "cardtype."+1+".value"));
    	BigDecimal pv0=new BigDecimal(this.getConfigValue(companyCode.toUpperCase(), "cardtype."+0+".value"));
    	java.util.Calendar startc=java.util.Calendar.getInstance();
		startc.set(2010, 8, 4, 00, 00, 00);
		java.util.Date startDate=startc.getTime();
		
		
		java.util.Calendar startc2=java.util.Calendar.getInstance();
		startc2.set(2012, 8, 29, 00, 00, 00);
		java.util.Date startDate2=startc2.getTime();
		Date curDate=new Date();
    	if(curDate.after(startDate) && !"PH".equals(companyCode) && pv.compareTo(pv6)!=-1){
			return "6";
		}else if(pv.compareTo(pv4)!=-1){
			return "4";
		}else if(pv.compareTo(pv3)!=-1){
			return "3";
		}else if(pv.compareTo(pv2)!=-1){
			return "2";
		}else if("CN".equals(companyCode) ){
			if(curDate.after(startDate2) && pv.compareTo(pv5)!=-1){
			      return "5";
			}
			else if(curDate.before(startDate2) && pv.compareTo(pv5)!=-1){
			      return "1";
			}	
		}else if(!"CN".equals(companyCode)){
			if(pv.compareTo(pv1)!=-1 ){
			      return "1";
			}else if(pv.compareTo(pv5)!=-1)
			{
				return "5";
			}
		}else if(pv.compareTo(pv0)!=-1){
			return "0";
		}
		return "0";
    }
    
    /**
     * 获取参数
     * @param companyCode
     * @param configKey
     * @return
     */
    private String getConfigValue(String companyCode, String configKey){
    	return Constants.sysConfigMap.get(companyCode).get(configKey);
    }
    
	/**
	 * 根据键值获取对应的字符值<value, title>
	 * @param msgKey
	 * @return
	 */
	public static final LinkedHashMap<String, String> getListOptions(String companyCode, String listCode) {
		Set valueSets = Constants.sysListMap.get(listCode).entrySet();
		LinkedHashMap<String, String> optionMap=new LinkedHashMap<String, String>();
		if (valueSets != null) {
			Iterator iter = valueSets.iterator();
			while (iter.hasNext()) {
				Map.Entry entry=(Map.Entry)iter.next();
				String[] values = (String[])entry.getValue();

				if(StringUtils.contains(values[1],companyCode)){
					//如果当前用户所属公司在排除公司之内,则不显示
					continue;
				}else{
					optionMap.put(entry.getKey().toString(), values[0]);
				}
			}
		}
		
		return optionMap;
	}

	/**
	 * 会员编号查找
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	public List getJpoMemberOrdersByMiMember(JpoMemberOrder jpoMemberOrder) {
		return dao.getJpoMemberOrdersByMiMember(jpoMemberOrder);
	}
	
	/**
	 * 查询首购单的审核时间
	 * @param memberNo
	 * @return
	 */
	public String getMemberFirstOrderStatusTime(String memberNo){
		return dao.getMemberFirstOrderStatusTime(memberNo);
	}
	
	/**
	 * 扣款
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 */
	private void getSaveMemberOrderDeduct(JpoMemberOrder jpoMemberOrder, SysUser operatorSysUser,String dataType){
		String companyCode = jpoMemberOrder.getCompanyCode();
		String userSpCode = jpoMemberOrder.getUserSpCode();
		SysUser sysUserSp = sysUserDao.getSysUser(userSpCode);
		
		if("4".equals(jpoMemberOrder.getOrderType())&&"1".equals(jpoMemberOrder.getCompanyPay())){
			AlCompany alCompany = alCompanyManager.getAlCompanyByCode(jpoMemberOrder.getCompanyCode());
			sysUserSp = sysUserDao.getSysUser(alCompany.getPreAgentCode());
			if(sysUserSp==null){
				throw new AppException("公司收款会员不存在!"); 
			}
		}
		BigDecimal isPayFee = new BigDecimal(0);
		String operaterCode = operatorSysUser.getUserCode();
		String operaterName = operatorSysUser.getUserName();
		String uniqueCode = jpoMemberOrder.getMemberOrderNo();
		BigDecimal[] moneyArray = new BigDecimal[jpoMemberOrder.getJpoMemberOrderFee().size()+1];
		Integer[] moneyType = new Integer[jpoMemberOrder.getJpoMemberOrderFee().size()+1];
		String[] notes = new String[jpoMemberOrder.getJpoMemberOrderFee().size()+1];
		BigDecimal productMoney = new BigDecimal(0);
		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		while (its1.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			productMoney = productMoney.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
		}
		if("1".equals(jpoMemberOrder.getPayByCoin()) && jpoMemberOrder.getDiscountAmount()!=null){
			productMoney = productMoney.subtract(jpoMemberOrder.getDiscountAmount());
		}
		if("1".equals(jpoMemberOrder.getPayByJJ()) && jpoMemberOrder.getJjAmount()!=null){
			productMoney = productMoney.subtract(jpoMemberOrder.getJjAmount());
			if(productMoney.compareTo(new BigDecimal(0))==-1){
				isPayFee = jpoMemberOrder.getJjAmount().subtract(productMoney);
				productMoney = new BigDecimal(0);
			}
		}
		moneyArray[0] = productMoney;
		moneyType[0] = 13;
		notes[0] = this.getLocalLanguageByKey("poMemberOrder.orderConfirm",operatorSysUser) + jpoMemberOrder.getMemberOrderNo();
		if(!userSpCode.equals(jpoMemberOrder.getSysUser().getUserCode())){
			notes[0] += "," + jpoMemberOrder.getSysUser().getUserCode();
		}
		
    	Iterator its2 = jpoMemberOrder.getJpoMemberOrderFee().iterator();
    	int i = 1;
    	while (its2.hasNext()) {
			JpoMemberOrderFee jpoMemberOrderFee = (JpoMemberOrderFee) its2.next();
			if(isPayFee.compareTo(new BigDecimal(0))==1){
				if(jpoMemberOrderFee.getFee().compareTo(isPayFee)==1){
					moneyArray[i] = jpoMemberOrderFee.getFee().subtract(isPayFee);
					isPayFee = new BigDecimal(0);
				}else{
					moneyArray[i] = new BigDecimal(0);
					isPayFee = isPayFee.subtract(jpoMemberOrderFee.getFee());
				}
			}else{
				moneyArray[i] = jpoMemberOrderFee.getFee();
			}
			
			//moneyType[i] = 1000 + Integer.parseInt(jpoMemberOrderFee.getFeeType());
			if("1".equals(jpoMemberOrderFee.getFeeType())){
				notes[i] = this.getLocalLanguageByKey("poOrder.mailFee",operatorSysUser);
				moneyType[i] = 32;//物流费
			}else if("2".equals(jpoMemberOrderFee.getFeeType())){
				notes[i] = this.getLocalLanguageByKey("poOrder.handlingUSFee",operatorSysUser);
				moneyType[i] = 30;//手续费
			}else if("3".equals(jpoMemberOrderFee.getFeeType())){
				notes[i] = this.getLocalLanguageByKey("poOrder.sax",operatorSysUser);
				moneyType[i] = 33;
			}else if("4".equals(jpoMemberOrderFee.getFeeType())){
				notes[i] = this.getLocalLanguageByKey("poOrder.enrollerBonus",operatorSysUser);
				moneyType[i] = 63;
			}else{
				notes[i] = this.getLocalLanguageByKey("fiBankbookTemp.moneyType.7",operatorSysUser);
				moneyType[i] = 34;//其他
			}
			i++;
		}
    	
    	if("1".equals(jpoMemberOrder.getIsFree())){
        	for(int m = 0 ; m < moneyArray.length ; m++){
        		moneyArray[m] = new BigDecimal("0");
        	}
    	}
    	
    	//判断订单是否包含旅游积分补款产品,决定资金类别是否为24
    	if(this.jpoMemberOrderIsIncludeTourCoin(jpoMemberOrder)){
    		moneyType[0] = 24;
    	}
    	
		jfiBankbookJournalManager.saveJfiBankbookDeduct(companyCode, sysUserSp, moneyType, moneyArray, operaterCode, operaterName, uniqueCode, notes, dataType);
	}
	/**
	 * 支付改造添加
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 */
	private void getSaveMemberOrderDeduct1(JpoMemberOrder jpoMemberOrder, SysUser operatorSysUser,String dataType){
		String companyCode = jpoMemberOrder.getCompanyCode();
		String userSpCode = jpoMemberOrder.getUserSpCode();
		SysUser sysUserSp = sysUserDao.getSysUser(userSpCode);
		
		if("4".equals(jpoMemberOrder.getOrderType())&&"1".equals(jpoMemberOrder.getCompanyPay())){
			AlCompany alCompany = alCompanyManager.getAlCompanyByCode(jpoMemberOrder.getCompanyCode());
			sysUserSp = sysUserDao.getSysUser(alCompany.getPreAgentCode());
			if(sysUserSp==null){
				throw new AppException("公司收款会员不存在!"); 
			}
		}
		BigDecimal isPayFee = new BigDecimal(0);
		String operaterCode = operatorSysUser.getUserCode();
		String operaterName = operatorSysUser.getUserName();
		String uniqueCode = jpoMemberOrder.getMemberOrderNo();
		BigDecimal[] moneyArray = new BigDecimal[jpoMemberOrder.getJpoMemberOrderFee().size()+1];
		Integer[] moneyType = new Integer[jpoMemberOrder.getJpoMemberOrderFee().size()+1];
		String[] notes = new String[jpoMemberOrder.getJpoMemberOrderFee().size()+1];
		BigDecimal productMoney = new BigDecimal(0);
		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		while (its1.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			productMoney = productMoney.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
		}
		if("1".equals(jpoMemberOrder.getPayByCoin()) && jpoMemberOrder.getDiscountAmount()!=null){
			productMoney = productMoney.subtract(jpoMemberOrder.getDiscountAmount());
		}
		if("1".equals(jpoMemberOrder.getPayByJJ()) && jpoMemberOrder.getJjAmount()!=null){
			productMoney = productMoney.subtract(jpoMemberOrder.getJjAmount());
			if(productMoney.compareTo(new BigDecimal(0))==-1){
				isPayFee = jpoMemberOrder.getJjAmount().subtract(productMoney);
				productMoney = new BigDecimal(0);
			}
		}
		moneyArray[0] = productMoney;
		moneyType[0] = 13;
		notes[0] = this.getLocalLanguageByKey("poMemberOrder.orderConfirm",operatorSysUser) + jpoMemberOrder.getMemberOrderNo();
		if(!userSpCode.equals(jpoMemberOrder.getSysUser().getUserCode())){
			notes[0] += "," + jpoMemberOrder.getSysUser().getUserCode();
		}
		
    	Iterator its2 = jpoMemberOrder.getJpoMemberOrderFee().iterator();
    	int i = 1;
    	while (its2.hasNext()) {
			JpoMemberOrderFee jpoMemberOrderFee = (JpoMemberOrderFee) its2.next();
			if(isPayFee.compareTo(new BigDecimal(0))==1){
				if(jpoMemberOrderFee.getFee().compareTo(isPayFee)==1){
					moneyArray[i] = jpoMemberOrderFee.getFee().subtract(isPayFee);
					isPayFee = new BigDecimal(0);
				}else{
					moneyArray[i] = new BigDecimal(0);
					isPayFee = isPayFee.subtract(jpoMemberOrderFee.getFee());
				}
			}else{
				moneyArray[i] = jpoMemberOrderFee.getFee();
			}
			
			//moneyType[i] = 1000 + Integer.parseInt(jpoMemberOrderFee.getFeeType());
			if("1".equals(jpoMemberOrderFee.getFeeType())){
				notes[i] = this.getLocalLanguageByKey("poOrder.mailFee",operatorSysUser);
				moneyType[i] = 32;//物流费
			}else if("2".equals(jpoMemberOrderFee.getFeeType())){
				notes[i] = this.getLocalLanguageByKey("poOrder.handlingUSFee",operatorSysUser);
				moneyType[i] = 30;//手续费
			}else if("3".equals(jpoMemberOrderFee.getFeeType())){
				notes[i] = this.getLocalLanguageByKey("poOrder.sax",operatorSysUser);
				moneyType[i] = 33;
			}else if("4".equals(jpoMemberOrderFee.getFeeType())){
				notes[i] = this.getLocalLanguageByKey("poOrder.enrollerBonus",operatorSysUser);
				moneyType[i] = 63;
			}else{
				notes[i] = this.getLocalLanguageByKey("fiBankbookTemp.moneyType.7",operatorSysUser);
				moneyType[i] = 34;//其他
			}
			i++;
		}
    	
    	if("1".equals(jpoMemberOrder.getIsFree())){
        	for(int m = 0 ; m < moneyArray.length ; m++){
        		moneyArray[m] = new BigDecimal("0");
        	}
    	}
    	
    	//判断订单是否包含旅游积分补款产品,决定资金类别是否为24
    	if(this.jpoMemberOrderIsIncludeTourCoin(jpoMemberOrder)){
    		moneyType[0] = 24;
    	}
    	
		jfiBankbookJournalManager.saveJfiBankbookDeduct1(companyCode, sysUserSp, moneyType, moneyArray, operaterCode, operaterName, uniqueCode, notes, dataType);
	}
	/**
	 * 判断订单是否包含旅游积分补款产品
	 * @param jpoMemberOrder 订单对象
	 * @return 如果包含，返回true;否则，返回false
	 */
	public boolean jpoMemberOrderIsIncludeTourCoin(JpoMemberOrder jpoMemberOrder){
		
		boolean flag = false;
		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		while (its1.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			String proNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
			//如果包含旅游积分补款的商品
			if(proNo.equalsIgnoreCase("N07010100101CN0")){
				flag=true;
				break;
			}

		}
		return flag;
	}
	
	/**
	 * 更改会员角色
	 * @param sysUser
	 * @param roleId
	 */
	private void getChangeJmiMemberRole(SysUser sysUser, String roleId){
		SysRole sysRole=sysRoleManager.getSysRoleByCode(roleId);
		SysUserRole sysUserRole=sysUserRoleManager.getSysUserRoleByUserCode(sysUser.getUserCode());
		sysUserRole.setRoleId(sysRole.getRoleId());
		sysUserRoleManager.saveSysUserRole(sysUserRole);
	}
	
	/**
	 * 保存会员升级记录
	 * @param jpoMemberOrder
	 * @param cardType
	 * @param operatorSysUser
	 */
	private void getSaveJmiMemberUpgradeNote(JpoMemberOrder jpoMemberOrder, String cardType, SysUser operatorSysUser, String updateType){
		Date logCurDate=new Date();
		JmiMember jmiMember = jmiMemberDao.getJmiMember(jpoMemberOrder.getSysUser().getUserCode());
		JmiMemberUpgradeNote jmiMemberUpgradeNote = new JmiMemberUpgradeNote();
		jmiMemberUpgradeNote.setJmiMember(jmiMember);
		jmiMemberUpgradeNote.setOldCard(jmiMember.getCardType());
		jmiMemberUpgradeNote.setNewCard(cardType);
		jmiMemberUpgradeNote.setCompanyCode(jmiMember.getCompanyCode());
		jmiMemberUpgradeNote.setMemberOrderNo(jpoMemberOrder.getMemberOrderNo());
		jmiMemberUpgradeNote.setUpdateType(updateType);
		jmiMemberUpgradeNote.setUpdateDate(jpoMemberOrder.getCheckTime());
		jmiMemberUpgradeNote.setRemark("");
		jmiMemberUpgradeNoteDao.saveJmiMemberUpgradeNote(jmiMemberUpgradeNote);
		FileUtil.saveLogger(fileName, logCurDate, new Date(), "保存jmiMemberUpgradeNote表"+jpoMemberOrder.getMemberOrderNo());
		logCurDate=new Date();
		//更新奖金级别表
//		jbdUserCardListManager.saveJbdUserCardList(jmiMember.getUserCode(), jpoMemberOrder.getCheckDate(), cardType,updateType,"1");
		jmiMember.setCardType(cardType);
		FileUtil.saveLogger(fileName, logCurDate, new Date(), "更新奖金级别表"+jpoMemberOrder.getMemberOrderNo());
/*		logCurDate=new Date();
		if("0".equals(jmiMember.getCustomerLevel())&&"CN".equals(jpoMemberOrder.getSysUser().getCompanyCode())&&("3".equals(cardType)||"4".equals(cardType)||"6".equals(cardType))){
			jmiMemberDao.sendPcn(jmiMember, "ModifyLevel", "1", "审核订单赠送PCN VIP用户", operatorSysUser, "");
		}
		FileUtil.saveLogger(fileName, logCurDate, new Date(), "发送PCN");*/
	}
	
	/**
	 * 获取语言值
	 * @param key
	 * @param sysUser
	 * @return
	 */
	private String getLocalLanguageByKey(String key,SysUser sysUser){
		String notes = "";
		String defaultMessage = key;
		if ("AA".equals(sysUser.getCompanyCode())) {
			notes = (String) Constants.localLanguageMap.get("zh_CN").get(defaultMessage);
		} else {
			notes = (String) Constants.localLanguageMap.get(sysUser.getDefCharacterCoding()).get(defaultMessage);
		}
		if(notes==null){
			return defaultMessage;
		}
		return notes;
	}
	
	/**
	 * 判断订单明细与总金额是否一至
	 * @param jpoMemberOrder
	 * @return
	 */
	private boolean getCheckOrderAmount(JpoMemberOrder jpoMemberOrder){

    	BigDecimal sumPrice = new BigDecimal(0);
    	BigDecimal sumPV = new BigDecimal(0);
    	
    	Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
    	while (its1.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			
			BigDecimal disPrice = jpoMemberOrderList.getDisPrice();
			if(disPrice !=null && disPrice.compareTo(new BigDecimal(0))>0){
				sumPrice = sumPrice.add(disPrice.multiply(new BigDecimal(jpoMemberOrderList.getQty())));
			} else {
				sumPrice = sumPrice.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
			}
			sumPV =sumPV.add(jpoMemberOrderList.getPv().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
		}
    	
    	Iterator its2 = jpoMemberOrder.getJpoMemberOrderFee().iterator();
    	while (its2.hasNext()) {
			JpoMemberOrderFee jpoMemberOrderFee = (JpoMemberOrderFee) its2.next();
			sumPrice = sumPrice.add(jpoMemberOrderFee.getFee());
		}
    	
    	if(jpoMemberOrder.getDiscountAmount()!=null){
    		if(log.isDebugEnabled())
    			log.debug("Discount num is:"+jpoMemberOrder.getDiscountAmount()+"]");
    		
    		sumPrice = sumPrice.subtract(jpoMemberOrder.getDiscountAmount());
    	}
    	
    	if(jpoMemberOrder.getDiscountPvAmt()!=null){
    		sumPV = sumPV.subtract(jpoMemberOrder.getDiscountPvAmt());
    	}
    	
    	if(jpoMemberOrder.getJjAmount()!=null){
    		sumPrice = sumPrice.subtract(jpoMemberOrder.getJjAmount());
    	}
    	log.info("check order amount is:"+
    			jpoMemberOrder.getAmount().toString()+" " +
    			"and Sum price :"+sumPrice.toString());
    	
    	if(jpoMemberOrder.getOrderType().equals("32") ){ //补单pv为0
    		if(jpoMemberOrder.getAmount().compareTo(sumPrice)==0 ){
        		return true;
        	}
    		
    	}else{
    		if(jpoMemberOrder.getAmount().compareTo(sumPrice)==0 && sumPV.compareTo(jpoMemberOrder.getPvAmt())==0){
        		return true;
        	}
    	}
    	
    	return false;
	}
	/**
	 * 会员首购单业务判断1
	 * 10:成功
	 * 11:未知异常
	 * 12:已存在已审首购单
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessMF(JpoMemberOrder jpoMemberOrder){
		if("1".equals(jpoMemberOrder.getSysUser().getJmiMember().getActive())){
			return 13;//死点
		}
		if(true){//if("CN".equals(jpoMemberOrder.getCompanyCode())|| "HK".equals(jpoMemberOrder.getCompanyCode())){
			//是否有已审首购单
			SysUser sysUser = jpoMemberOrder.getSysUser();
			List jpoMemberOrders = dao.getJpoMemberOrdersByTCS("1", sysUser.getUserCode(), "2");
			if(jpoMemberOrders.size()<2){
				return 10;//成功
			}else{
				return 12;//已存在已审首购单
			}
		}
		return 11;//未知异常
	}
	/**
	 * 会员升级单业务判断2
	 * 20:成功
	 * 22:不存在已审首购单
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessMU(JpoMemberOrder jpoMemberOrder){
		
		SysUser sysUser = jpoMemberOrder.getSysUser();
		if("0".equals(sysUser.getJmiMember().getCardType())){
			return 22;//待审会员
		}else{
			return 20;//成功
		}
		//是否有已审首购单
//		List jpoMemberOrders = dao.getJpoMemberOrdersByTCS("1", sysUser.getUserCode(), "2");
//		if(jpoMemberOrders.size()>0){
//			return 20;//成功
//		}else{
//			return 22;//不存在已审首购单
//		}
	}
	private boolean upgradePV(int gradeType,BigDecimal orderPv) {
		String vipLevel = ConfigUtil.getConfigValue("CN", Constants.CARD1VIP_PV);
		String yinLevel = ConfigUtil.getConfigValue("CN", Constants.CARD1_PV);
		String jinLevel = ConfigUtil.getConfigValue("CN", Constants.CARD2_PV);
		String zuanLevel = ConfigUtil.getConfigValue("CN", Constants.CARD3_PV);
		String tgyLevel = ConfigUtil.getConfigValue("CN", Constants.CARD7_PV);
		
		log.info("yinLever is:"+yinLevel +" and jinLevel is:"+jinLevel+" "
				+ "and zuanLevel is:"+zuanLevel);
		BigDecimal zuanBig = new BigDecimal(zuanLevel);
		BigDecimal jinBig = new BigDecimal(jinLevel);
		BigDecimal yinBig = new BigDecimal(yinLevel);
		BigDecimal vipBig = new BigDecimal(vipLevel);
		BigDecimal tgyBig = new BigDecimal(tgyLevel);
		BigDecimal sub_pv = new BigDecimal(0);
		//BigDecimal subPV_1 = new BigDecimal(0);
		
		boolean isup = false;
		log.info("gradeType:"+gradeType+" and orderPV:"+orderPv);
		
		switch (gradeType) {
			case 5000://Constants.CARDTYPE_5000:
				isup = true;
				break;
			case 4000://Constants.CARDTYPE_4000:
				sub_pv = zuanBig.subtract(jinBig);
				log.info("subPV_2 is:"+sub_pv);
				
				if(orderPv.compareTo(sub_pv)<0){
					isup = true;
				}
				break;
			case 3000://Constants.CARDTYPE_3000:
				sub_pv = jinBig.subtract(yinBig);
				log.info("subPV_1 is:"+sub_pv);
				
				if(orderPv.compareTo(sub_pv)<0){
					isup = true;
				}
				break;
			case 1500://Constants.CARDTYPE_1500:
				sub_pv = yinBig.subtract(tgyBig);
				log.info("subPV_1500 is:"+sub_pv);
				
				if(orderPv.compareTo(sub_pv)<0){
					isup = true;
				}
				break;
			case 2500:
				sub_pv = tgyBig.subtract(vipBig);
				log.info("subpv_vip is:"+sub_pv);
				
				if(orderPv.compareTo(sub_pv)<0){
					isup = true;
				}
				break;
			case 1000://Constants.CARDTYPE_1000:
				if(orderPv.compareTo(tgyBig) < 0) isup = true;
				break;
			case 2000://Constants.CARDTYPE_2000:
				if(orderPv.compareTo(tgyBig) < 0) isup = true;
				break;
			default:
				isup = true;
				break;
		}
		return isup;
	}
	/**
	 * 会员续约业务判断3
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessMRS(JpoMemberOrder jpoMemberOrder){
		SysUser sysUser = jpoMemberOrder.getSysUser();
		if(sysUser.getJmiMember().getStartWeek()==0 || sysUser.getJmiMember().getValidWeek()==0){
			return 22;//待审会员
		} else {
			return 30;//成功
		}
	}
	/**
	 * 会员返单业务判断4
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessMR(JpoMemberOrder jpoMemberOrder){
//		SysUser sysUser = jpoMemberOrder.getSysUser();
//		if("0".equals(sysUser.getJmiMember().getCardType())){
//			return 22;//待审会员
//		}else{
			return 40;//成功
//		}
		//是否有已审首购单
//		List jpoMemberOrders = dao.getJpoMemberOrdersByTCS("1", sysUser.getUserCode(), "2");
//		if(jpoMemberOrders.size()>0){
//			return 40;//成功
//		}else{
//			return 22;//不存在已审首购单
//		}
	}
	/**
	 * 会员辅销品订单业务判断5
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessMA(JpoMemberOrder jpoMemberOrder){
//		SysUser sysUser = jpoMemberOrder.getSysUser();
//		if("0".equals(sysUser.getJmiMember().getCardType())){
//			return 22;//待审会员
//		}else{
			return 40;//成功
//		}
	}
	/**
	 * 店铺首购单业务判断6
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessSF(JpoMemberOrder jpoMemberOrder){
		if ("CN".equals(jpoMemberOrder.getCompanyCode())) {
			// 是否有已审首购单
			SysUser sysUser = jpoMemberOrder.getSysUser();
			List jpoMemberOrders = dao.getJpoMemberOrdersByTCS("6",
					sysUser.getUserCode(), "2");
			if (jpoMemberOrders.size() < 2) {
				return 60;// 成功
			} else {
				return 62;// 已存在已审首购单
			}
		}
		return 11;// 未知异常
	}
	/**
	 * 店铺返单业务判断8
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessSB(JpoMemberOrder jpoMemberOrder){
		
		return 1;//未知异常
	}
	/**
	 * 店铺重销单业务判断9
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessSR(JpoMemberOrder jpoMemberOrder){
		SysUser sysUser = jpoMemberOrder.getSysUser();
		JmiMember jmiMember = sysUser.getJmiMember();
		if("1".equals(jmiMember.getIsstore())){
			return 90;//成功
		}else{
			return 92;//不存在已审首购单
		}
	}
	/**
	 * 二级馆首购单业务判断11
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessSSubF(JpoMemberOrder jpoMemberOrder){
		//是否有已审首购单
		SysUser sysUser = jpoMemberOrder.getSysUser();
		List jpoMemberOrders = dao.getJpoMemberOrdersByTCS("11", sysUser.getUserCode(), "2");
		if(jpoMemberOrders.size()<2){
			return 60;//成功
		}else{
			return 62;//已存在已审首购单
		}
	}
	/**
	 * 二级馆升级单业务判断12
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessSSubU(JpoMemberOrder jpoMemberOrder){
		SysUser sysUser = jpoMemberOrder.getSysUser();
		JmiMember jmiMember = sysUser.getJmiMember();
		if("2".equals(jmiMember.getIsstore())){
			return 90;//成功
		}else{
			return 92;//不存在已审首购单
		}
	}
	/**
	 * 活力小铺首购单业务判断21
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessSMF(JpoMemberOrder jpoMemberOrder){
		SysUser sysUser = jpoMemberOrder.getSysUser();
		JmiMember jmiMember = sysUser.getJmiMember();
		if(!"1".equals(jmiMember.getShopType())&&"6".equals(jmiMember.getCardType())){
			return 90;//成功
		}else{
			return 92;//不存在已审首购单
		}
	}
	/**
	 * 活力小铺重消单业务判断24
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessSMR(JpoMemberOrder jpoMemberOrder){
		SysUser sysUser = jpoMemberOrder.getSysUser();
		JmiMember jmiMember = sysUser.getJmiMember();
		if("1".equals(jmiMember.getShopType())){
			return 90;//成功
		}else{
			return 92;//不存在已审首购单
		}
	}
	/**
	 * 二级馆重销单业务判断14
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessSSubR(JpoMemberOrder jpoMemberOrder){
		SysUser sysUser = jpoMemberOrder.getSysUser();
		JmiMember jmiMember = sysUser.getJmiMember();
		if("2".equals(jmiMember.getIsstore())){
			return 90;//成功
		}else{
			return 92;//不存在已审首购单
		}
	}
	/**
	 * 会员返单业务判断15
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessAS(JpoMemberOrder jpoMemberOrder){
		//SysUser sysUser = jpoMemberOrder.getSysUser();
//		if("0".equals(sysUser.getJmiMember().getCardType())){
//			return 22;//待审会员
//		}else{
			return 40;//成功
//		}
		//是否有已审首购单
//		List jpoMemberOrders = dao.getJpoMemberOrdersByTCS("1", sysUser.getUserCode(), "2");
//		if(jpoMemberOrders.size()>0){
//			return 40;//成功
//		}else{
//			return 22;//不存在已审首购单
//		}
	}

	/**
	 * 统计物流费
	 * @param  crm
	 * @param pager
	 * @author 罗婷
	 * 
	 */
	public List getShippingfeeByCrm(CommonRecord crm, Pager pager) {
		return dao.getShippingfeeByCrm(crm, pager);
		
	}

	//判断是不是优惠订购的产品
	public int getPreferentialOrder(JpoMemberOrder jpoMemberOrder) {
		Iterator ite = jpoMemberOrder.getJpoMemberOrderList().iterator();
		int booleanValue=0;
		int number=0;
		while(ite.hasNext()){
			JpoMemberOrderList jpoMemberOrderListTmp = (JpoMemberOrderList)ite.next();
			if("N06010100101CN0".equals(jpoMemberOrderListTmp.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo())) {
				 booleanValue=1;//代表订单通过并且不计算物流费，不允许用积分换购
				 if(number==1)
				 {
					 booleanValue = 0;
					 break;
				 }else
				 {
					 number=2;
					 
				 }		
		    }else
		    {
		    	 booleanValue=2;//代表订单通过 需要计算物流费，允许用积分换购
		    	 if(number==2)
		    	 { 
					 booleanValue = 0;
		    		 break;
		    	 }else
		    	 {
		    		 number=1;
		    		 
		    	 }
		    	 
		    }
			
		}
		return booleanValue;
	}
	public Map getTotalShippingfeeByCrm(CommonRecord crm) {
		
		return dao.getTotalShippingfeeByCrm(crm);
	}
	public boolean getROrderPv(String userCode, CommonRecord crm) {
	/*	Date  currentDate=new Date();
		BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByTime(currentDate,null);
		List periodsMember = bdPeriodManager.getBdPeriodsByMonth(bdPeriod.getFyear(), bdPeriod.getFmonth());
		BdPeriod bdPeriod = (BdPeriod)periodsMember.get(0);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommonRecord crm = new CommonRecord();
		crm.addField("stauts", "2");
		crm.addField("userCode", jpoMemberOrder.getSysUser().getUserCode());
		crm.addField("orderType", "4,9,14");
		crm.addField("startLogTime", dateFormat.format(bdPeriod.getStartTime()));
		BigDecimal pvAmt = dao.getJpoMemberOrderStatics(crm);
		pvAmt = pvAmt.add(jpoMemberOrder.getPvAmt());*/
		
		
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * <li>按购买商品绑定赠品</li>
	 * @param jpoMemberOrder
	 * @param JpmSalePromoter
	 * @throws Exception 
	 */
	private Set<JpoMemberOrderList> bindPresentProduct(JpoMemberOrder jpoMemberOrder,JpmSalePromoter sp) throws Exception{
		Set<JpoMemberOrderList> orderListSet = new HashSet<JpoMemberOrderList>();
		String sale_proNo = sp.getPresentno();
		try{
			
			String limit = sp.getPresentlimit();
			log.info("limit is:["+limit+"]");
			
			String[] limitArr = new String[2];
			String shopNum="",perNum="",firStr = "",lasStr = "";
			int lasNum=0;
			
			if(limit.length()>3){
				firStr = limit.substring(0,3);
				lasStr = limit.substring(firStr.length(),limit.length());
				limitArr= firStr.split(":");
				shopNum = limitArr[0];
				perNum = limitArr[1];
				lasNum = Integer.parseInt(lasStr.substring(1));
			} else {
				limitArr = limit.split(":");
				shopNum = limitArr[0];
				perNum = limitArr[1];
			}
			
			log.info("shopNum is:["+shopNum+"] and perNum is:["+shopNum+"] " +
					"and firStr is:["+firStr+"] and lasStr is:["+lasStr+"] " +
							"and lasNum is:["+lasNum+"]");
			
			Iterator<JpoMemberOrderList> orderItems = jpoMemberOrder.
					getJpoMemberOrderList().iterator();
			while(orderItems.hasNext()){
				JpoMemberOrderList orderItem = orderItems.next();
				String order_ProNo = orderItem.getJpmProductSaleTeamType().
						getJpmProductSaleNew().getJpmProduct().getProductNo();
				
				log.info("saleProNo is:["+sale_proNo +"] and oracle ProNo is:"+order_ProNo);
				if(sale_proNo.equalsIgnoreCase(order_ProNo)){
					/**
		    		 * 赠送模式: 2:N 即: 2为购买最低限制 ,N为赠品数量,可填N或数字
		    		 * 如果是 2:N,则 购买数量大于2 则送同等数量的赠品
		    		 * 若为2:1 , 则买2个送1个.
		    		 * 或者2:1*2 即:购买2个指定商品,就送 qty*2的赠品
		    		 */
					int num=1; 
					if(orderItem.getQty()>=Integer.parseInt(shopNum)){
						if(limit.length()>3 && lasStr.startsWith("/")){
							/*
							 * 避免除法运算为0时,赠送产品数量获取错误
							 */
							if(orderItem.getQty()>1){
								num = (int)Math.floor(orderItem.getQty()/lasNum);
							} else {
								if(perNum.equalsIgnoreCase("N"))
									num = orderItem.getQty();
								else 
									num = Integer.parseInt(perNum);
							}
							
						} else if(limit.length()>3 && lasStr.startsWith("*")){
							num = orderItem.getQty()*lasNum;
						} else {
							if(perNum.equalsIgnoreCase("N"))
								num = orderItem.getQty();
							else 
								num = Integer.parseInt(perNum);
						}
					}
					
					if(log.isDebugEnabled()){
						log.debug("saleProNo is:["+sale_proNo +"] " +
								"and order ProNo is:["+order_ProNo+"] " +
								"and persent num is:"+num+"]");
					}
					//绑定赠品
					orderListSet = bindProduct(sp, jpoMemberOrder, num);
					
				}
		    		
	    			/*
	    			 * 当购买数量,大于促销规定数量时,加送赠品
	    			 * 列:购买数量大于2加送多个赠品 例 2:PRNO3202,PRNO00921
	    			 */
	    			if(StringUtils.isNotBlank(sp.getAppendPresent())){
	    				String appendPre = sp.getAppendPresent();
	    				log.info("appendPresent is:"+appendPre);
	    				
	    				String[] appPreArr = appendPre.split(":");
	    				String[] proArr = appPreArr[1].split(",");
	    				String saleNum = appPreArr[0];
	    				if(orderItem.getQty() >= Integer.parseInt(saleNum)){
	    					
	    					for(int i=0; i<proArr.length ;i++){
		    					JpmProductSaleTeamType pstt = jpmProductSaleManager.
			    						getJpmProductSaleTeamType(jpoMemberOrder.getCompanyCode(), 
			    						proArr[i],jpoMemberOrder.getOrderType(),
			    						jpoMemberOrder.getTeamCode());
			    				
		    					if(pstt !=null && pstt.getJpmProductSaleNew()!=null){
		    						JpoMemberOrderList orderList = new JpoMemberOrderList();
				    				orderList.setJpmProductSaleTeamType(pstt);
				    				orderList.setJpoMemberOrder(jpoMemberOrder);
				    				orderList.setPrice(new BigDecimal("0"));
				    				orderList.setProductType("");
				    				orderList.setPv(new BigDecimal("0"));
				    				orderList.setQty(1);
				    				orderList.setVolume(new BigDecimal("0"));
				    				orderList.setWeight(new BigDecimal("0"));
				    		
				    				//jpoMemberOrder.getJpoMemberOrderList().add(orderList);
				    				orderListSet.add(orderList);
		    					} else {
		    						throw new AppException("未找到赠品,编号:"+proArr[i]);
		    					}
	    					}
	    				}
	    			}
				}
			return orderListSet;
		}catch(Exception e){
			throw e;
		}
	}
	/**
	 * 赠送积分
	 * @param jpoMemberOrder
	 * @param JpmSalePromoter
	 * @param ispresent 0:不赠送1:赠送
	 * @param SysUser
	 * @throws Exception 
	 */
	private void getBindIntegral(JpoMemberOrder jpoMemberOrder,
			JpmSalePromoter sp,String ispresent,SysUser operatorUser, String dataType) throws Exception{
		
		boolean flag = validateAmountOrPV(jpoMemberOrder,sp);
		log.info("orderNo is:["+jpoMemberOrder.getMemberOrderNo()+"] and isPresent is:["+flag+"]");
		
		if(flag){
			try{
				BigDecimal amount = jpoMemberOrder.getAmount();
				//积分比例
				BigDecimal sale_integral = sp.getIntegral();
				Integer[] moneyType = new Integer[]{10};
				Long[] appId = new Long[]{2L};
				String[] notes = new String[1];
				BigDecimal[] moneyArray = new BigDecimal[1];
				SysUser sysUser = new SysUser();
				moneyArray[0] = jpoMemberOrder.getAmount();
				String isCoin = jpoMemberOrder.getPayByCoin() == null ? "0"
						: jpoMemberOrder.getPayByCoin();
				String isOrder = sp.getIsorder();  // 0是积分换购的单不参与赠送

				log.info("orderNo is:[" + jpoMemberOrder.getMemberOrderNo()
						+ "] " + "and amount is:[" + amount.toString() + "] "
						+ "and fund amount is:[" + jpoMemberOrder.getJjAmount()
						+ "] " + "and isCoin is:[" + isCoin + "]");

				if (isCoin.equals("0")) {
					// 是否赠送推荐人0否, 1
					if (ispresent.equals("0")) {
						notes = new String[] { "审单赠送积分====="
								+ jpoMemberOrder.getMemberOrderNo() };
						sysUser = jpoMemberOrder.getSysUser();
						
						if(jpoMemberOrder.getJjAmount()!=null){
							moneyArray[0] = (amount.add(jpoMemberOrder.getJjAmount())).multiply(sale_integral);
						}else{
							moneyArray[0] = amount.multiply(sale_integral);
						}
					
						fiBcoinJournalManager.saveFiPayDataVerify("CN", sysUser, 
								moneyType, moneyArray, operatorUser.getUserCode(), 
								operatorUser.getUserName(), "0", notes, appId, null, dataType);
						
					}else{
						JmiMember recMember = jpoMemberOrder.getSysUser().
								getJmiMember().getJmiRecommendRef().
								getRecommendJmiMember();
						
						if(recMember.getExitDate()==null &&
								sp.getPreOrderType().indexOf(jpoMemberOrder.getOrderType())>-1){
							
							BigDecimal reIntegral = sp.getReintegral();
							sysUser = jpoMemberOrder.getSysUser().
									getJmiMember().getJmiRecommendRef().
									getRecommendJmiMember().getSysUser();
							
							if(jpoMemberOrder.getJjAmount()!=null){
								moneyArray[0] = ((amount.add(jpoMemberOrder.getJjAmount())).multiply(reIntegral));
							}else{
								moneyArray[0] = amount.multiply(reIntegral);
							}
							
							notes = new String[]{"订单编号:"+jpoMemberOrder.getMemberOrderNo()+"," +
									"审单赠送推荐人"+sysUser.getUserCode()+"积分:"+moneyArray[0]};
							
							fiBcoinJournalManager.saveFiPayDataVerify("CN", sysUser, 
									moneyType, moneyArray, operatorUser.getUserCode(), 
									operatorUser.getUserName(), "0", notes, appId, null, dataType);
						} 
						
					}
				}else{
					if("1".equals(isOrder)){

						// 是否赠送推荐人0否, 1
						if (ispresent.equals("0")) {
							notes = new String[] { "审单赠送积分====="
									+ jpoMemberOrder.getMemberOrderNo() };
							sysUser = jpoMemberOrder.getSysUser();
							
							if(jpoMemberOrder.getJjAmount()!=null){
								moneyArray[0] = (amount.add(jpoMemberOrder.getJjAmount())).multiply(sale_integral);
							}else{
								moneyArray[0] = amount.multiply(sale_integral);
							}
						
							fiBcoinJournalManager.saveFiPayDataVerify("CN", sysUser, 
									moneyType, moneyArray, operatorUser.getUserCode(), 
									operatorUser.getUserName(), "0", notes, appId, null, dataType);
							
						}else{
							JmiMember recMember = jpoMemberOrder.getSysUser().
									getJmiMember().getJmiRecommendRef().
									getRecommendJmiMember();
							
							if(recMember.getExitDate()==null &&
									sp.getPreOrderType().indexOf(jpoMemberOrder.getOrderType())>-1){
								
								BigDecimal reIntegral = sp.getReintegral();
								sysUser = jpoMemberOrder.getSysUser().
										getJmiMember().getJmiRecommendRef().
										getRecommendJmiMember().getSysUser();
								
								if(jpoMemberOrder.getJjAmount()!=null){
									moneyArray[0] = ((amount.add(jpoMemberOrder.getJjAmount())).multiply(reIntegral));
								}else{
									moneyArray[0] = amount.multiply(reIntegral);
								}
								
								notes = new String[]{"订单编号:"+jpoMemberOrder.getMemberOrderNo()+"," +
										"审单赠送推荐人"+sysUser.getUserCode()+"积分:"+moneyArray[0]};
								
								fiBcoinJournalManager.saveFiPayDataVerify("CN", sysUser, 
										moneyType, moneyArray, operatorUser.getUserCode(), 
										operatorUser.getUserName(), "0", notes, appId, null, dataType);
							} 
							
						}
					}
				}
				
				log.info("present money is:["+moneyArray[0]+"] user is:"+sysUser.getUserCode());
				
				
			}catch(Exception e){
				throw e;
			}
		}
    }
	/**
	 * 根据订单总金额或PV,或订单类型绑定赠品
	 * @param order
	 * @param sp
	 */
	private Set<JpoMemberOrderList> getOrderAmountBindProduct(
			JpoMemberOrder order, JpmSalePromoter sp) throws Exception {
		
		log.info("orderNo :" + order.getMemberOrderNo() + " "
				+ "present product num is:" + sp.getSaleProductSet().size());
		
		try {
			if (sp.getAmount().compareTo(new BigDecimal(0)) == 0
					&& sp.getPv() == 0L) {
				/*
				 * 当总金额和PV都为0时 , 按订单类型绑定赠品
				 */
				String sale_OrderType = sp.getOrdertype();
				String orderType = order.getOrderType();
				log.info("sale orderType is:["+sale_OrderType+" and orderType is:"+orderType);
				if(isOrderType(sale_OrderType, orderType)){
					return bindProduct(sp,order,0);
				}
			} else {
				if(validateAmountOrPV(order, sp)){
					return bindProduct(sp,order,0);
				}
			}
			
		}catch(Exception e){
			throw e;
		}
		return new HashSet<JpoMemberOrderList>();
	}
	/**
	 * 验证订单总金额和PV是否达到指定值
	 * @param jpoMemberOrder
	 * @param sp
	 * @return true or fasle;
	 */
	private boolean validateAmountOrPV(JpoMemberOrder jpoMemberOrder,JpmSalePromoter sp){
		boolean flag = false;
		
//		String str_startDate = LocaleUtil.getLocalText("zh_CN", "20151201cx.startDate");
//		String str_endDate = LocaleUtil.getLocalText("zh_CN", "20151201cx.endDate");
//		Date starDate = DateUtil.convertStringToDate(
//				"yyyy-MM-dd hh:mm:ss", str_startDate);
//		Date endDate = DateUtil.convertStringToDate(
//				"yyyy-MM-dd hh:mm:ss", str_endDate);
		
		String startDateS = ConfigUtil.getConfigValue("CN", "20151201cx.startdate");
		String endDateS = ConfigUtil.getConfigValue("CN", "20151201cx.enddate");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date curDate = Calendar.getInstance().getTime();
		
		try {
				if(sp.getSaleType().equals(Constants.JPMSALE_SALETYPE_ORDER) && curDate.after(sdf.parse(startDateS)) && curDate.before(sdf.parse(endDateS))){
					
			
						BigDecimal pv = jpoMemberOrder.getPvAmt();
						
						Iterator set = jpoMemberOrder.getJpoMemberOrderList().iterator();
						while (set.hasNext()) {
							JpoMemberOrderList orderList = (JpoMemberOrderList) set.next();
							String proNo = orderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo();
							
							log.info("jpoMemberOrder: "+ jpoMemberOrder.getMemberOrderNo() + "  proNo:.......20151201: " + proNo + "..." + GlobalVar.jpoList20151201.contains(proNo));
		
							if (GlobalVar.jpoList20151201.contains(proNo)) {  
								pv = pv.subtract(new BigDecimal(5500).multiply(new BigDecimal(orderList.getQty())));
							}
						}
						
						if(pv.compareTo(new BigDecimal(5500))>=0){
							
							if(sp.getAmount().compareTo(new BigDecimal(0))>0 && sp.getPv()>0L){
								if(jpoMemberOrder.getAmount().compareTo(sp.getAmount())>=0 && 
										pv.compareTo(new BigDecimal(sp.getPv()))>=0){
										flag = true;
								}
							}else if(sp.getAmount().compareTo(new BigDecimal(0))<=0 && sp.getPv()>0L){
								if(pv.compareTo(new BigDecimal(sp.getPv())) >=0){
									flag = true;
								}
							} else if(sp.getAmount().compareTo(new BigDecimal(0))>0 && sp.getPv() <= 0L){
								if(jpoMemberOrder.getAmount().compareTo(sp.getAmount())>=0){
									flag = true;
								}
							} else if(sp.getAmount().compareTo(new BigDecimal(0))==0 && sp.getPv()==0L){
								flag = true;
							}
						}
			}else{
				
				//总金额和PV值,是否达到指定数额
				if(sp.getAmount().compareTo(new BigDecimal(0))>0 && sp.getPv()>0L){
					if(jpoMemberOrder.getAmount().compareTo(sp.getAmount())>=0 && 
							jpoMemberOrder.getPvAmt().compareTo(new BigDecimal(sp.getPv()))>=0){
							flag = true;
					}
				}else if(sp.getAmount().compareTo(new BigDecimal(0))<=0 && sp.getPv()>0L){
					if(jpoMemberOrder.getPvAmt().compareTo(new BigDecimal(sp.getPv())) >=0){
						flag = true;
					}
				} else if(sp.getAmount().compareTo(new BigDecimal(0))>0 && sp.getPv() <= 0L){
					if(jpoMemberOrder.getAmount().compareTo(sp.getAmount())>=0){
						flag = true;
					}
				}else if(sp.getAmount().compareTo(new BigDecimal(0))==0 && sp.getPv()==0L){
					flag = true;
				}
			}
		} catch (Exception e) {
			log.info("201601cx.startdate:..."+"dateformaterror");
			e.printStackTrace();
		}	
		
		log.info("order amount is:["+jpoMemberOrder.getAmount()+"] " +
				"and salePromoter amount is:["+sp.getAmount()+"] " +
				"orderPV:["+jpoMemberOrder.getPvAmt()+"] " +
				"and salePromoter PV is:"+sp.getPv()+"] " +
						"and flag is:"+flag);
		return flag;
	}
	/**
	 * 绑定商品
	 * @param sp
	 * @param order
	 * @param flag 
	 * 	0:按订单类型或总金额,赠送产品数量    取策略中的数量，改为取赠送商品表的数量
	 * @throws Exception
	 */
	private Set<JpoMemberOrderList> bindProduct(JpmSalePromoter sp ,JpoMemberOrder order,int flag) throws Exception{
		try{
			Set<JpoMemberOrderList> set = new HashSet<JpoMemberOrderList>();
			Iterator<JpmSalepromoterPro> iter = sp.getSaleProductSet().iterator();
			
			while(iter.hasNext()){
				JpmSalepromoterPro spro = iter.next();
				String proNo = spro.getProno();
				
				log.info("companyCode :"+order.getCompanyCode()+" and proNo:"+proNo+" and team :"+order.getTeamCode());
				//绑定赠品
				JpmProductSaleTeamType jpmProductSale = jpmProductSaleManager.
						getJpmProductSaleTeamType(order.getCompanyCode(), 
								proNo,order.getOrderType(),order.getTeamCode());
				
				if(jpmProductSale!=null && jpmProductSale.getPttId() !=null){
					JpoMemberOrderList jpoMemberOrderList = new JpoMemberOrderList();
					jpoMemberOrderList.setJpmProductSaleTeamType(jpmProductSale);
					jpoMemberOrderList.setProNo(jpmProductSale.getJpmProductSaleNew().getProductNo());
					jpoMemberOrderList.setJpoMemberOrder(order);
					jpoMemberOrderList.setPrice(new BigDecimal("0"));
					jpoMemberOrderList.setProductType("");
					jpoMemberOrderList.setPv(new BigDecimal("0"));
					jpoMemberOrderList.setVolume(new BigDecimal("0"));
					jpoMemberOrderList.setWeight(new BigDecimal("0"));
					
					if(flag>0){  
						jpoMemberOrderList.setQty(flag);
					}else {
//						jpoMemberOrderList.setQty(sp.getOrderProNum());
						jpoMemberOrderList.setQty(Integer.parseInt(spro.getPronum().toString()));
					}
		    		//order.getJpoMemberOrderList().add(jpoMemberOrderList);
					set.add(jpoMemberOrderList);
				} else {
					log.error("JpmProductSaleTeamType is null.");
					throw new AppException("未找到赠品,编号:"+proNo);
				}
			}
			return set;
		}catch(Exception e){
			throw e;
		}
	}
	
	public List getJpoMemberOrderStaticsCheckedCompanyByTree(String startDate, String endDate, String companyCode, String checkType, String treeIndex, String relationType) {
		return dao.getJpoMemberOrderStaticsCheckedCompanyByTree(startDate, endDate, companyCode, checkType, treeIndex, relationType);
	}

	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public void setJmiStoreManager(JmiStoreManager jmiStoreManager) {
		this.jmiStoreManager = jmiStoreManager;
	}

	public void setFiCoinLogManager(FiCoinLogManager fiCoinLogManager) {
		this.fiCoinLogManager = fiCoinLogManager;
	}

	public void setEcCoinSender(EcCoinSender ecCoinSender) {
		this.ecCoinSender = ecCoinSender;
	}

	public void setJpmProductSaleManager(JpmProductSaleManager jpmProductSaleManager) {
		this.jpmProductSaleManager = jpmProductSaleManager;
	}

	public void setJbdSummaryListManager(JbdSummaryListManager jbdSummaryListManager) {
		this.jbdSummaryListManager = jbdSummaryListManager;
	}

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public void setAlStateProvinceManager(
			AlStateProvinceManager alStateProvinceManager) {
		this.alStateProvinceManager = alStateProvinceManager;
	}

	public void setFiBcoinBalanceManager(FiBcoinBalanceManager fiBcoinBalanceManager) {
		this.fiBcoinBalanceManager = fiBcoinBalanceManager;
	}

	public void setFiBcoinJournalManager(FiBcoinJournalManager fiBcoinJournalManager) {
		this.fiBcoinJournalManager = fiBcoinJournalManager;
	}

	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}

	public void setFiBankbookJournalManager(
			FiBankbookJournalManager fiBankbookJournalManager) {
		this.fiBankbookJournalManager = fiBankbookJournalManager;
	}
//获取表彰的荣誉称号
	public Map getJpoMemberPraiseMeetingUserCode() {
	    return 	dao.getJpoMemberPraiseMeetingUserCode();
		
	}
	public void editJpoMemberOrderShipments(JpoMemberOrder jpoMemberOrder, SysUser operatorSysUser)
	{
		if("1".equals(jpoMemberOrder.getIsShipments()))
		{
			throw new AppException(jpoMemberOrder.getMemberOrderNo()+"此订单已经设置延时发货");
		}
		jpoMemberOrder.setIsShipments("1");
		dao.saveJpoMemberOrder(jpoMemberOrder);
	}
	//获取团队，青岛，田阳团队是不参与中脉所有的促销
	private boolean getJpoMemberTeam(JpoMemberOrder jpoMemberOrder,String type)
	{
		Date logCurDate=new Date();
		boolean validTeam=false;
		JmiMember pdMiMember2 =jmiMemberDao.getJmiMember("CN40449939");//青岛三生
    	JmiMember pdMiMember3 =jmiMemberDao.getJmiMember("CN18766575");//田阳团队1(推荐网)
    	JmiMember pdMiMember4 =jmiMemberDao.getJmiMember("CN16481747");//田阳团队2(推荐网)
    	JmiMember pdMiMember5 =jmiMemberDao.getJmiMember("CN10919893");//田阳团队3(推荐网)
    	JmiMember pdMiMember6 =jmiMemberDao.getJmiMember("CN18645446");//云南团队(推荐网)  参与积分赠送  
    	JmiMember pdMiMember7 =jmiMemberDao.getJmiMember("CN54623113");//骆天团队(推荐网)  参与积分赠送
    	JmiMember pdMiMember8 =jmiMemberDao.getJmiMember("CN55092684");//田阳团队4(推荐网)CN55092684、CN18728599、CN62827846、 CN58387198
    	JmiMember pdMiMember9 =jmiMemberDao.getJmiMember("CN18728599");//田阳团队5(推荐网)
    	JmiMember pdMiMember10 =jmiMemberDao.getJmiMember("CN62827846");//田阳团队6(推荐网)
    	JmiMember pdMiMember11=jmiMemberDao.getJmiMember("CN58387198");//田阳团队7(推荐网)
    //	JmiMember pdMiMember12=jmiMemberDao.getJmiMember("CN15279222");//张洁团队(推荐网)
    	//JmiMember pdMiMember13=jmiMemberDao.getJmiMember("CN18736617");//宋自甫团队（太极养生）
  	   if(pdMiMember2!=null)
  	   {
    		String lcTreeIndex2=pdMiMember2.getJmiRecommendRef().getTreeIndex();
    		String loninTreeIndex2=jpoMemberOrder.getSysUser().getJmiMember().getJmiRecommendRef().getTreeIndex();
    		String rmemberIndexTmp2 = StringUtil.getLeft(loninTreeIndex2, lcTreeIndex2.length());//青岛三生
    		 if(loninTreeIndex2.length() >= lcTreeIndex2.length() && rmemberIndexTmp2.equals(lcTreeIndex2))
    		{
    			validTeam=true;
    		}
  	   } 
 
  	   if(pdMiMember4!=null)
  	   {
  			String lcTreeIndex4=pdMiMember4.getJmiRecommendRef().getTreeIndex();
    		String loninTreeIndex4=jpoMemberOrder.getSysUser().getJmiMember().getJmiRecommendRef().getTreeIndex();
    		String rmemberIndexTmp4 = StringUtil.getLeft(loninTreeIndex4, lcTreeIndex4.length());//田阳团队2(推荐网)
    		 if(loninTreeIndex4.length() >= lcTreeIndex4.length() && rmemberIndexTmp4.equals(lcTreeIndex4)){
    			
    			validTeam=true;
    		}
  	   } 
  	   if(pdMiMember5!=null)
  	   {
  			String lcTreeIndex5=pdMiMember5.getJmiRecommendRef().getTreeIndex();
    		String loninTreeIndex5=jpoMemberOrder.getSysUser().getJmiMember().getJmiRecommendRef().getTreeIndex();
    		String rmemberIndexTmp5 = StringUtil.getLeft(loninTreeIndex5, lcTreeIndex5.length());//田阳团队3(推荐网)
    		 if(loninTreeIndex5.length() >= lcTreeIndex5.length() && rmemberIndexTmp5.equals(lcTreeIndex5)){
    			
    			validTeam=true;
    		}
  	   }
  	 if(pdMiMember8!=null)
	   {
  		String lcTreeIndex8=pdMiMember8.getJmiRecommendRef().getTreeIndex();
  		String loninTreeIndex8=jpoMemberOrder.getSysUser().getJmiMember().getJmiRecommendRef().getTreeIndex();
  		String rmemberIndexTmp8 = StringUtil.getLeft(loninTreeIndex8, lcTreeIndex8.length());//田阳4
  		 if(loninTreeIndex8.length() >= lcTreeIndex8.length() && rmemberIndexTmp8.equals(lcTreeIndex8))
  		{
  			validTeam=true;
  		}
	   } 
	   if(pdMiMember9!=null)
	   {
		 String lcTreeIndex9=pdMiMember9.getJmiRecommendRef().getTreeIndex();
  		String loninTreeIndex9=jpoMemberOrder.getSysUser().getJmiMember().getJmiRecommendRef().getTreeIndex();
  		String rmemberIndexTmp9 = StringUtil.getLeft(loninTreeIndex9, lcTreeIndex9.length());//田阳团队5(推荐网)
  		 if(loninTreeIndex9.length() >= lcTreeIndex9.length() && rmemberIndexTmp9.equals(lcTreeIndex9)){
  		
  			validTeam=true;
  		}
	   }
	   if(pdMiMember10!=null)
	   {
			String lcTreeIndex10=pdMiMember10.getJmiRecommendRef().getTreeIndex();
  		String loninTreeIndex10=jpoMemberOrder.getSysUser().getJmiMember().getJmiRecommendRef().getTreeIndex();
  		String rmemberIndexTmp10 = StringUtil.getLeft(loninTreeIndex10, lcTreeIndex10.length());//田阳团队6(推荐网)
  		 if(loninTreeIndex10.length() >= lcTreeIndex10.length() && rmemberIndexTmp10.equals(lcTreeIndex10)){
  			
  			validTeam=true;
  		}
	   } 
	   if(pdMiMember11!=null)
	   {
			String lcTreeIndex11=pdMiMember11.getJmiRecommendRef().getTreeIndex();
  		String loninTreeIndex11=jpoMemberOrder.getSysUser().getJmiMember().getJmiRecommendRef().getTreeIndex();
  		String rmemberIndexTmp11 = StringUtil.getLeft(loninTreeIndex11, lcTreeIndex11.length());//田阳团队7(推荐网)
  		 if(loninTreeIndex11.length() >= lcTreeIndex11.length() && rmemberIndexTmp11.equals(lcTreeIndex11)){
  			
  			validTeam=true;
  		}
	   }
	/*   if(pdMiMember12!=null)
	   {
			String lcTreeIndex12=pdMiMember12.getJmiRecommendRef().getTreeIndex();
  		String loninTreeIndex12=jpoMemberOrder.getSysUser().getJmiMember().getJmiRecommendRef().getTreeIndex();
  		String rmemberIndexTmp12 = StringUtil.getLeft(loninTreeIndex12, lcTreeIndex12.length());//张洁团队(推荐网)
  		 if(loninTreeIndex12.length() >= lcTreeIndex12.length() && rmemberIndexTmp12.equals(lcTreeIndex12)){
  			
  			validTeam=true;
  		}
	   }*/
//	   if(pdMiMember13!=null)
//	   {
//			String lcTreeIndex13=pdMiMember13.getJmiRecommendRef().getTreeIndex();
//  		String loninTreeIndex13=jpoMemberOrder.getSysUser().getJmiMember().getJmiRecommendRef().getTreeIndex();
//  		String rmemberIndexTmp13 = StringUtil.getLeft(loninTreeIndex13, lcTreeIndex13.length());//宋自甫团队（太极养生）
//  		 if(loninTreeIndex13.length() >= lcTreeIndex13.length() && rmemberIndexTmp13.equals(lcTreeIndex13)){
//  			
//  			validTeam=true;
//  		}
//	   }
  	   if(!"1".equals(type))//云南团队中脉,骆天团队，田阳1参与积分活动的促销，中脉第7财年的促销
  	   {
		   if(pdMiMember6!=null)
	  	   {
	  			String lcTreeIndex6=pdMiMember6.getJmiRecommendRef().getTreeIndex();
	    		String loninTreeIndex6=jpoMemberOrder.getSysUser().getJmiMember().getJmiRecommendRef().getTreeIndex();
	    		String rmemberIndexTmp6 = StringUtil.getLeft(loninTreeIndex6, lcTreeIndex6.length());//云南团队(推荐网)
	    		 if(loninTreeIndex6.length() >= lcTreeIndex6.length() && rmemberIndexTmp6.equals(lcTreeIndex6)){
	    			
	    			validTeam=true;
	    		}
	  	   }
		   if(pdMiMember7!=null)
	  	   {
	  			String lcTreeIndex7=pdMiMember7.getJmiRecommendRef().getTreeIndex();
	    		String loninTreeIndex7=jpoMemberOrder.getSysUser().getJmiMember().getJmiRecommendRef().getTreeIndex();
	    		String rmemberIndexTmp7 = StringUtil.getLeft(loninTreeIndex7, lcTreeIndex7.length());//骆天团队(推荐网)
	    		 if(loninTreeIndex7.length() >= lcTreeIndex7.length() && rmemberIndexTmp7.equals(lcTreeIndex7)){
	    			
	    			validTeam=true;
	    		}
	  	   }
	    String limitteam = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "limitteam");
		if("1".equals(limitteam))//是否开启，如果为1就开启，否则就停止
		  {
		 	   if(pdMiMember3!=null)
		  	   {
		  		 String lcTreeIndex3=pdMiMember3.getJmiRecommendRef().getTreeIndex();
		    		String loninTreeIndex3=jpoMemberOrder.getSysUser().getJmiMember().getJmiRecommendRef().getTreeIndex();
		    		String rmemberIndexTmp3 = StringUtil.getLeft(loninTreeIndex3, lcTreeIndex3.length());//田阳团队1(推荐网)
		    		 if(loninTreeIndex3.length() >= lcTreeIndex3.length() && rmemberIndexTmp3.equals(lcTreeIndex3)){
		    		
		    			validTeam=true;
		    		}
		  	   }
		 }
  	   }
  	   FileUtil.saveLogger(fileName, logCurDate, new Date(), "判断团队"+jpoMemberOrder.getMemberOrderNo());
  	   return validTeam;	
	 }
 
	
	public boolean getJpoMemberMark(String papernumber, String productNo, String orderType) {
		boolean  validUser=false;//没有购买过事业锦囊
	    List  list=dao.getJpoMemberMark(papernumber, productNo, orderType);
	  if(list!=null)
	  {
	       if(list.size()>0)
	     {
	    	validUser=true;//会员购买过事业锦囊;
	     }
	  }
	 
		return validUser;
	}
	/**
	 * 根据订单类型,用户团队,国别 确定此订单是否参与促销
	 * @param JpmSalePromoter
	 * @param jpoMemberOrder
	 * @return true or false;
	 */
	public boolean isOrderSales(JpmSalePromoter sp,JpoMemberOrder jpoMemberOrder)throws Exception{
		boolean flag = false;
		try{
			String sale_teams = sp.getTeamno();
			String sale_countrys = sp.getCompanyno();
			String sale_orders = sp.getOrdertype();
			
			String order_comCode = jpoMemberOrder.getCompanyCode();
			String order_type = jpoMemberOrder.getOrderType();
			String order_userCode = jmiMemberTeamManager.getTeamStr(jpoMemberOrder.getSysUser());
			
			log.info("spId is:["+sp.getSpno()+"] and saleTeams is:["+sale_teams+"] " +
					"and order Team is:["+order_userCode+"] " +
					"and country is:["+sale_countrys+"] and order company:["+order_comCode+"] " +
					"and sale_orders is:["+sale_orders+"] and orderType is:["+order_type+"]");
			
			if(! StringUtils.isNotBlank(sale_teams)) 
				sale_teams = "";
				
			
			if(sale_countrys.indexOf(order_comCode) != -1 && 
					sale_teams.indexOf(order_userCode) <0 && 
					isOrderType(sale_orders, order_type)){
				flag = true;
			}
			
		}catch(Exception e){
			throw e;
		}
		log.info("orderNo ["+jpoMemberOrder.getMemberOrderNo()+"] isOrderSales return is:"+flag);
		return flag;
	}
	/**
	 * 如果订单类型符合此策略定义的类型则返回true
	 * @param saleType 以逗号隔开的字符串
	 * @param type  需要比对的字符
	 * @return true or false
	 * @throws Exception
	 */
	public boolean isOrderType(String saleType,String type) throws Exception{
		
		if(StringUtils.isBlank(saleType)) return true;
		
		String[] saleTypeArr = saleType.split(",");
		boolean flag = false;
		for(int i=1; i<saleTypeArr.length;i++){
			if(saleTypeArr[i].equals(type)){
				flag = true;
				break;
			}
		}
		return flag;
	}

	public void setJpmCardSeqManager(JpmCardSeqManager jpmCardSeqManager) {
		this.jpmCardSeqManager = jpmCardSeqManager;
	}

	public void setJbdUserCardListManager(
			JbdUserCardListManager jbdUserCardListManager) {
		this.jbdUserCardListManager = jbdUserCardListManager;
	}

	public void setPdSendInfoManager(PdSendInfoManager pdSendInfoManager) {
		this.pdSendInfoManager = pdSendInfoManager;
	}

	public void setSysListValueManager(SysListValueManager sysListValueManager) {
		this.sysListValueManager = sysListValueManager;
	}

	public void setSysRoleManager(SysRoleManager sysRoleManager) {
		this.sysRoleManager = sysRoleManager;
	}

	public void setSysUserRoleManager(SysUserRoleManager sysUserRoleManager) {
		this.sysUserRoleManager = sysUserRoleManager;
	}

	public void setJmiMemberUpgradeNoteDao(
			JmiMemberUpgradeNoteDao jmiMemberUpgradeNoteDao) {
		this.jmiMemberUpgradeNoteDao = jmiMemberUpgradeNoteDao;
	}

	public void setJmiMemberDao(JmiMemberDao jmiMemberDao) {
		this.jmiMemberDao = jmiMemberDao;
	}

	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}

	public void setJfiBankbookJournalManager(
			JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}

	public void setJpoMemberOrderFeeDao(JpoMemberOrderFeeDao jpoMemberOrderFeeDao) {
		this.jpoMemberOrderFeeDao = jpoMemberOrderFeeDao;
	}

	public void setJpoMemberOrderListDao(JpoMemberOrderListDao jpoMemberOrderListDao) {
		this.jpoMemberOrderListDao = jpoMemberOrderListDao;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpoMemberOrderDao(JpoMemberOrderDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberOrderManager#getJpoMemberOrders(com.joymain.jecs.po.model.JpoMemberOrder)
     */
    public List getJpoMemberOrders(final JpoMemberOrder jpoMemberOrder) {
        return dao.getJpoMemberOrders(jpoMemberOrder);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberOrderManager#getJpoMemberOrder(String moId)
     */
    public JpoMemberOrder getJpoMemberOrder(final String moId) {
        return dao.getJpoMemberOrder(new Long(moId));
    }
    
    public JpoMemberOrder getJpoMemberOrderByMemberOrderNo(String memberOrderNo){
    	return dao.getJpoMemberOrderByMemberOrderNo(memberOrderNo);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberOrderManager#saveJpoMemberOrder(JpoMemberOrder jpoMemberOrder)
     */
    public void saveJpoMemberOrder(JpoMemberOrder jpoMemberOrder) {
    	
        dao.saveJpoMemberOrder(jpoMemberOrder);
    }
    
	public List getChageableJpoMemberOrders(String userCode) {
		// TODO Auto-generated method stub
		return dao.getChageableJpoMemberOrders(userCode);
	}
	
	//查出符合发货条件的订单
    public List getShipOrder(CommonRecord crm, Pager pager) {
    	return dao.getShipOrder(crm, pager);
    }
    
    /**
	 * 查找所有支付完成，审核完成，已发货,未推送的订单
	 * @return
	 */
	public List getNotSendOrders(){
		return dao.getNotSendOrders();
	}
	
	public void updateOrderSended(JpoMemberOrder  jpo) {
		dao.updateOrderSended(jpo);
	}
	
	public JmiMemberManager getJmiMemberManager() {
		return jmiMemberManager;
	}

	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}


	public JmiMemberTeamManager getJmiMemberTeamManager() {
		return jmiMemberTeamManager;
	}


	public void setJmiMemberTeamManager(JmiMemberTeamManager jmiMemberTeamManager) {
		this.jmiMemberTeamManager = jmiMemberTeamManager;
	}
	public JpmSalePromoterDao getJpmSalePromoterDao() {
		return jpmSalePromoterDao;
	}


	public void setJpmSalePromoterDao(JpmSalePromoterDao jpmSalePromoterDao) {
		this.jpmSalePromoterDao = jpmSalePromoterDao;
	}
	
	@Override
    public void modifyOrderStatusByMoId(Map<String, String> map)
    {
        dao.modifyOrderStatusByMoId(map);
    }


	public JpoMovieDao getJpoMovieDao() {
		return jpoMovieDao;
	}


	public void setJpoMovieDao(JpoMovieDao jpoMovieDao) {
		this.jpoMovieDao = jpoMovieDao;
	}


	public JpmProductSaleNewDao getJpmProductSaleNewDao() {
		return jpmProductSaleNewDao;
	}

	public JpoProductNumLimitManager getJpoProductNumLimitManager() {
		return jpoProductNumLimitManager;
	}


	public void setJpoProductNumLimitManager(
			JpoProductNumLimitManager jpoProductNumLimitManager) {
		this.jpoProductNumLimitManager = jpoProductNumLimitManager;
	}


	public void setJpmProductSaleNewDao(JpmProductSaleNewDao jpmProductSaleNewDao) {
		this.jpmProductSaleNewDao = jpmProductSaleNewDao;
	}


	public void setJprRefundManager(JprRefundManager jprRefundManager) {
		this.jprRefundManager = jprRefundManager;
	}
	
	/**
     * 根据订单号查询订单----由暂不发货转到正常发货的接口时候用（为表独立性单独建方法）
     * @author fx 2015-8-10
     * @param memberOrderNo
     * @return JpoMemberOrder
     */
	public JpoMemberOrder getJpoMemberOrderByInterface(String memberOrderNo) {
		return dao.getJpoMemberOrderByInterface(memberOrderNo);
	}


	public void saveJpoMemberOrder(JpoMemberOrder jpoMemberOrder,
			JbdSummaryList jbdSummaryList) {
		// TODO Auto-generated method stub
		dao.saveJpoMemberOrder(jpoMemberOrder);
		jbdSummaryListManager.saveJbdSummaryList(jbdSummaryList);
	}
	
	/**
	 * 判断订单是否是挂起状态
	 * fu 2016-03-16
	 * @param memberOrderNo
	 * @return
	 */
	public boolean getOrderReturnableStatus(String memberOrderNo){
		return dao.getOrderReturnableStatus(memberOrderNo);
	}
	
	/**
	 * 订单的自助换货--允许自助换货
	 * fu 2016-03-28
	 * @param memberOrderNo
	 * @return
	 */
	public void orderSelfHelpExchangeYes(String memberOrderNo) {
		dao.orderSelfHelpExchange(memberOrderNo,"Y");
	}
	
	/**
	 * 订单的自助换货--禁止自助换货
	 * fu 2016-03-28
	 * @param memberOrderNo
	 * @return
	 */
	public void orderSelfHelpExchangeNo(String memberOrderNo) {
		dao.orderSelfHelpExchange(memberOrderNo,"N");
	}


	@Override
	public void checkJpoMemberOrder(JpoMemberOrder order,
			SysUser operatorSysUser, String dataType) throws Exception {
		
		if(order.getIsPay().equals("0") && (order.getStatus().equals("3") || order.getStatus().equals("4"))){  //错误数据
			
				throw new AppException("salePromoter failed," +
						"orderNo is:"+order.getMemberOrderNo() );
			
		}else{
			

			//未填写支付时间
			if("".equals(order.getSubmitTime()) || null == order.getSubmitTime()){
				if(isOverQty(order)){
					throw new AppException("商品无库存或库存不足");
				}
			}

			String limitcheckorder = this.getConfigValue(order.getSysUser().getCompanyCode(), "limitcheckorder");
			if("1".equals(limitcheckorder))
			{
				throw new AppException("系统更新,请您稍后在审单");
			}
			if(!this.getCheckOrderAmount(order)){
				throw new AppException("订单总金额不一致");
			}
			if(!"0".equals(order.getLocked())){
				throw new AppException("订单已锁订");
			}
			if("2".equals(order.getStatus())){
				throw new AppException("订单已审核");
			}
			if("1".equals(order.getSysUser().getJmiMember().getActive())){
				throw new AppException(this.getLocalLanguageByKey("checkError.Code.member",operatorSysUser));
			}
			
			if(order.getSysUser().getJmiMember().getFreezeStatus() == 1 && !order.getOrderType().equals(Constants.AUTO_PURCHASE)){
				throw new AppException("会员已冻结!");
			}
			
			SysUser sysUserSp = sysUserDao.getSysUser(order.getUserSpCode());
			if(!sysUserSp.getCompanyCode().equals(order.getCompanyCode())&&!"1".equals(order.getCompanyPay())){
				throw new AppException("扣款人与订单不同国别");
			}
			

	    	BigDecimal sumPrice = new BigDecimal(0);
	    	BigDecimal sumPV = new BigDecimal(0);
	    	
	    	Iterator<JpoMemberOrderList> itsTmp1 = order.getJpoMemberOrderList().iterator();
	    	while (itsTmp1.hasNext()) {
				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) itsTmp1.next();
				sumPrice = sumPrice.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
				sumPV =sumPV.add(jpoMemberOrderList.getPv().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
			}
	    	Iterator itsTmp2 = order.getJpoMemberOrderFee().iterator();
	    	while (itsTmp2.hasNext()) {
				JpoMemberOrderFee jpoMemberOrderFee = (JpoMemberOrderFee) itsTmp2.next();
				sumPrice = sumPrice.add(jpoMemberOrderFee.getFee());
			}
			Date logCurDate=new Date();
	    	Iterator its1 = order.getJpoMemberOrderList().iterator();
	    	if("CN".equals(order.getCompanyCode())){
	        	while (its1.hasNext()) {
	        		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
	        		String status = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getStatus();
	        		if("0".equals(status)){
	        			throw new AppException("产品(" + jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo() + ")已售完!");
	        		}
	        	}
	    	}
	    	
			Date date = ServerDateUtil.getNowTimeFromDateServer(); //数据时间jmiMemberDao.getDsDate();
//			order.setIsSended(0);
//			order.setSubmitStatus("2");
			
//			if(!"".equals(order.getSubmitUserCode()) && order.getSubmitUserCode() != null){
//				order.setCheckTime(order.getSubmitTime());
//				order.setCheckDate(order.getSubmitTime());
//				order.setCheckUserCode(order.getSubmitUserCode());
				
//			}else{
				
//				order.setSubmitTime(date);
//				order.setSubmitUserCode(operatorSysUser.getUserCode());
//				
//				order.setCheckTime(date);
//				order.setCheckDate(date);
//				order.setCheckUserCode(operatorSysUser.getUserCode());
//			}
			
			int orderType = Integer.parseInt(order.getOrderType());
			
			String oldCard="";
			String newCard="";
			FileUtil.saveLogger(fileName, logCurDate, new Date(), "进入判断订单前"+order.getMemberOrderNo());
			switch(orderType){
				case 1://会员首购
					int businessFId = this.getJpoMemberOrderBusinessMF(order);
					if(businessFId==10){
						logCurDate=new Date();
						if(order.getSysUser().getJmiMember().getNotFirst()!=0){
							throw new AppException("会员不处于待审状态");
						}
						String cardType = "";
						if("1".equals(order.getSysUser().getJmiMember().getMemberType()) && !"0".equals(order.getSysUser().getJmiMember().getOriCard())){
							if(order.getPvAmt().compareTo(new BigDecimal("70"))!=-1){
								cardType = order.getSysUser().getJmiMember().getOriCard();
							}else{
								throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "D会员首购单必须大于或等于70PV");
							}
						}else if("2".equals(order.getSysUser().getJmiMember().getMemberType())){
							BigDecimal pv_amt = order.getPvAmt().add(order.getSysUser().getJmiMember().getOriPv());
							cardType = this.getCalcFOrderCardType(pv_amt,order.getSysUser().getCompanyCode());
						}else if("4".equals(order.getSysUser().getJmiMember().getMemberType())){
							if("1".equals(order.getSysUser().getJmiMember().getOriCard())){
								if(sumPrice.compareTo(new BigDecimal("140"))!=-1){
									cardType = "4";
								}else{
									throw new AppException("PV不足");
								}
							}else{
								if(sumPrice.compareTo(new BigDecimal("70"))!=-1){
									cardType = "4";
								}else{
									throw new AppException("PV不足");
								}
							}
						}else{
							cardType = this.getCalcFOrderCardType(order.getPvAmt(),order.getSysUser().getCompanyCode());
						}
						FileUtil.saveLogger(fileName, logCurDate, new Date(), "判断级别"+order.getMemberOrderNo());
						if(Integer.parseInt(cardType) > Integer.parseInt(order.getSysUser().getJmiMember().getCardType())){
							//获取新旧卡别
							logCurDate=new Date();
							oldCard=jmiMemberDao.getJmiMember(order.getSysUser().getUserCode()).getCardType();
							newCard=cardType;
							FileUtil.saveLogger(fileName, logCurDate, new Date(), "取出级别"+order.getMemberOrderNo());
							//升级记录
//							this.getSaveJmiMemberUpgradeNote(order, cardType, operatorSysUser, "1");	
						}
						//角色
						String roleId = this.getConfigValue(order.getSysUser().getCompanyCode(), Constants.JOCS_ROLE_NORMAL);
	/*					if("6".equals(cardType)){
							//VIP会员
							Iterator ite = jpoMemberOrder.getJpoMemberOrderList().iterator();
							while(ite.hasNext()){
								JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) ite.next();
	        	        		if("P13010200201CN0".equals(jpoMemberOrderList.getJpmProductSale().getJpmProduct().getProductNo())){
	        	        			//买凳子送二级馆
	        	        			roleId = "cn.member.62";
	        	        		}
							}
						}*/
						logCurDate=new Date();
//						this.getChangeJmiMemberRole(jpoMemberOrder.getSysUser(), roleId);
//						FileUtil.saveLogger(fileName, logCurDate, new Date(), "存角色"+order.getMemberOrderNo());
					}else{
						throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessFId,operatorSysUser));
					}
//					JmiMember jmiMember = jmiMemberDao.getJmiMember(order.getSysUser().getUserCode());
//					jmiMember.setCheckDate(order.getCheckDate());
//					jmiMember.setCheckNo(order.getCheckUserCode());
					//==============续约
					//BdPeriod bdPeriodF = bdPeriodManager.getBdPeriodByTime(order.getCheckDate(),null);
					//Integer startMonthF = bdPeriodF.getWyear()*100 + bdPeriodF.getWmonth();
					
//					String yearF = startMonthF.toString().substring(0, 4);
//					String monthF = startMonthF.toString().substring(4, 6);
//					String periodF = bdPeriodManager.getFutureBdYearMonth(yearF, monthF, 12);
					
//					jmiMember.setStartWeek(startMonthF);
//					jmiMember.setValidWeek(Integer.parseInt(periodF));
//					jmiMember.setNotFirst(1);
					//==============
//					if("2".equals(order.getSysUser().getJmiMember().getMemberType())){
//						jmiMember.setOriPv(new BigDecimal("0"));
//					}
//					this.jmiMemberDao.saveJmiMember(jmiMember);
					break;
				case 2://会员升级
					JmiMember user = jmiMemberDao.getJmiMember(order.getSysUser().getUserCode());
					if(upgradePV(user.getMemberLevel(), order.getPvAmt())){ 	
						throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + 
								this.getLocalLanguageByKey("checkError.Code.21",operatorSysUser));
					}
					break;
				case 3://会员续约
					int businessRSId = this.getJpoMemberOrderBusinessMRS(order);
					if(businessRSId==30){
						
						if(order.getSysUser().getJmiMember().getFreezeStatus()==0){
							throw new AppException("会员处于解冻状态！");
						}else if(order.getSysUser().getJmiMember().getFreezeStatus()==1){
							//冻，126PV订单
							//==============续约当年

							/*String years = order.getSysUser().getJmiMember().getValidWeek().toString().substring(0, 4);
							String months = order.getSysUser().getJmiMember().getValidWeek().toString().substring(4, 6);
							String periodS = bdPeriodManager.getFutureBdYearMonth(years, months, 2);
							String yeare = periodS.substring(0, 4);
							String monthe = periodS.substring(4, 6);
							String periodE = bdPeriodManager.getFutureBdYearMonth(yeare, monthe, 13);*/
							
							
//							BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByTime(new Date(),null);
//							String periodS = bdPeriodManager.getFutureBdYearMonth(bdPeriod.getWyear().toString(), bdPeriod.getWmonth().toString(), 1);
//							String periodE = bdPeriodManager.getFutureBdYearMonth(bdPeriod.getWyear().toString(), bdPeriod.getWmonth().toString(), 12);
							
//							order.getSysUser().getJmiMember().setStartWeek(Integer.parseInt(periodS));
//							order.getSysUser().getJmiMember().setValidWeek(Integer.parseInt(periodE));
							
//							BdPeriod bdPeriodSR = bdPeriodManager.getBdPeriodByTime(order.getCheckDate(),null);
//							Integer startMonthSR = bdPeriodSR.getWyear()*100 + bdPeriodSR.getWmonth();
							
//							if(startMonthSR<=Integer.parseInt(periodE)){
								//当前期别小于会员的冻结期别解冻
//								order.getSysUser().getJmiMember().setFreezeStatus(0);
//								JbdUserValidList jbdUserValidList = new JbdUserValidList();
//								jbdUserValidList.setJmiMember(order.getSysUser().getJmiMember());
//								jbdUserValidList.setNewFreezeStatus(0);
//								jbdUserValidList.setOldFreezeStatus(1);
//								jbdUserValidList.setWweek(bdPeriodSR.getWyear()*100 + bdPeriodSR.getWweek());
//								Set<JbdUserValidList> jbdUserValidListSet = new HashSet<JbdUserValidList>(0);
//								jbdUserValidListSet.add(jbdUserValidList);
//								order.getSysUser().getJmiMember().setJbdUserValidList(jbdUserValidListSet);
								
								
								// 角色
//								String roleId="";
//								JmiStore jmiStore = jmiStoreManager.getJmiStoreByUserCode(order.getSysUser().getUserCode());
//								if("1".equals(order.getSysUser().getJmiMember().getIsstore())){
//										roleId = this.getConfigValue(order.getSysUser().getCompanyCode(), Constants.JOCS_STORE1);
//								}else if("2".equals(order.getSysUser().getJmiMember().getIsstore())){
//										roleId= this.getConfigValue(order.getSysUser().getCompanyCode(), Constants.JOCS_STORE2);
//								}else if("1".equals(order.getSysUser().getJmiMember().getSubStoreStatus())){
//									//二级已审
//									roleId = this.getConfigValue(order.getSysUser().getCompanyCode(), Constants.JOCS_STORE2_X);
//								}else if(jmiStore!=null && "1".equals(jmiStore.getCheckStatus())){
//									//一级已审
//									roleId = this.getConfigValue(order.getSysUser().getCompanyCode(), Constants.JOCS_STORE1_X);
//								}else{
//									
//									//通过身份判断角色,默认是0 优惠顾客 1 永远优惠顾客2 会员3,两个优惠顾客都是取优惠顾客角色,会员就取普通会员,0就取待审
//									Integer greadType = order.getSysUser().getJmiMember().getGradeType();
//									if(greadType == 3){
//										roleId = this.getConfigValue(order.getSysUser().getCompanyCode(), Constants.JOCS_ROLE_NORMAL);
//									}else if(greadType == 2 || greadType == 1){
//										roleId = this.getConfigValue(order.getSysUser().getCompanyCode(), Constants.JOCS_ROLE5);
//									}else {
//										roleId = this.getConfigValue(order.getSysUser().getCompanyCode(), Constants.JOCS_ROLE0);
//									}
//									
//									roleId = this.getConfigValue(order.getSysUser().getCompanyCode(), Constants.JOCS_ROLE_NORMAL);
//								}
//								this.getChangeJmiMemberRole(order.getSysUser(), roleId);
							}
//						}else{
//							throw new AppException("冻结状态不明确");
//						}
					}else{
						throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessRSId,operatorSysUser));
					}
					break;
				case 4://会员重销
					int businessRId = this.getJpoMemberOrderBusinessMR(order);
					if(businessRId==40){
						
					}else{
						throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessRId,operatorSysUser));
					}
					break;
				case 5://辅销品订单
					int businessAId = this.getJpoMemberOrderBusinessMA(order);
					if(businessAId==40){
						
					}else{
						throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessAId,operatorSysUser));
					}
					break;
				case 6://店铺首购
					int businessSFId = this.getJpoMemberOrderBusinessSF(order);
					if(businessSFId==60){

				    	BigDecimal amount = new BigDecimal(Constants.sysConfigMap.get(order.getSysUser().getCompanyCode().toUpperCase()).get("store.f.order.amount"));
				    	BigDecimal pvAmt = new BigDecimal(Constants.sysConfigMap.get(order.getSysUser().getCompanyCode().toUpperCase()).get("store.f.order.pvamt"));
				    	
				    	if("LC".equals(order.getProductType())){
					    	if(pvAmt.compareTo(order.getPvAmt())!=1){
					    		
					    	}else{
					    		throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购金额不正确");
					    	}
				    	}else{
					    	if(amount.compareTo(sumPrice)!=1){

					    	}else{
					    		throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购金额不正确");
					    	}
				    	}
						//角色
			    		String roleId;
			    		roleId = this.getConfigValue(order.getSysUser()
								.getCompanyCode(), Constants.JOCS_STORE1);
						
//						this.getChangeJmiMemberRole(order.getSysUser(), roleId);
//						order.getSysUser().getJmiMember().setIsstore("1");
//						order.getSysUser().getJmiMember().setRecommendStore(order.getSysUser().getJmiMember().getRecommendNo());
						
					}else{
						throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessSFId,operatorSysUser));
					}
					break;
				case 7://店铺升级单
					break;
				case 8://店铺返单
					break;
				case 9://店铺重销
					int businessSRId = this.getJpoMemberOrderBusinessSR(order);
					if(businessSRId==90){
						
					}else{
						throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessSRId,operatorSysUser));
					}
					break;
				case 11://二级馆首购单
					int businessSSubFId = this.getJpoMemberOrderBusinessSSubF(order);
					if(businessSSubFId==60){
				    	BigDecimal pvamt = new BigDecimal(Constants.sysConfigMap.get(order.getSysUser().getCompanyCode().toUpperCase()).get("store.f2.order.pvamt"));
				    	BigDecimal amount = new BigDecimal(Constants.sysConfigMap.get(order.getSysUser().getCompanyCode().toUpperCase()).get("store.f2.order.amount"));
				    	
				    	if("LC".equals(order.getProductType()) && pvamt.compareTo(order.getPvAmt())!=1){
				    		
				    	}else if(!"LC".equals(order.getProductType())){
				    		if(amount.compareTo(sumPrice)==1){
				    			throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购金额不正确");
				    		}
				    		if("HK".equals(order.getCompanyCode())){
				    			BigDecimal pv = new BigDecimal(Constants.sysConfigMap.get(order.getSysUser().getCompanyCode().toUpperCase()).get("store.f2.order.pv"));
					    		if(pv.compareTo(order.getPvAmt())==1){
					    			throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购PV不正确");
					    		}
				    		}
				    	}else{
				    		throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购金额不正确");
				    	}
						//角色
				    	String roleId;
				    	//正式二级店铺权限
						roleId = this.getConfigValue(order.getSysUser()
								.getCompanyCode(), "jocs.member.role.store2");
				    	
//				    	this.getChangeJmiMemberRole(order.getSysUser(), roleId);
//						order.getSysUser().getJmiMember().setIsstore("2");
//						order.getSysUser().getJmiMember().setSubStoreStatus("2");
//						order.getSysUser().getJmiMember().setSubStoreCheckDate(order.getCheckDate());
					}else{
						throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessSSubFId,operatorSysUser));
					}
					break;
				case 12://二级馆升级单
					int businessSSubUId = this.getJpoMemberOrderBusinessSSubU(order);
					if(businessSSubUId==90){
						BigDecimal pv = new BigDecimal(Constants.sysConfigMap.get(order.getSysUser().getCompanyCode().toUpperCase()).get("store.u2.order.pv"));
				    	BigDecimal amount = new BigDecimal(Constants.sysConfigMap.get(order.getSysUser().getCompanyCode().toUpperCase()).get("store.u2.order.amount"));
						
						if("LC".equals(order.getProductType())){
							pv = new BigDecimal(Constants.sysConfigMap.get(order.getSysUser().getCompanyCode().toUpperCase()).get("store.u2.orderlc.pv"));
							amount = new BigDecimal("0");
						}
						
	        			java.util.Calendar startc=java.util.Calendar.getInstance();
	        	    	startc.set(2011, 6, 16, 00, 00, 00);
	        	    	java.util.Calendar endc=java.util.Calendar.getInstance();
	        	    	endc.set(2011, 7, 6, 00, 00, 00);
	        	    	java.util.Date startDate=startc.getTime();
	        	    	java.util.Date endDate=endc.getTime();
	        	    	Date curDate=new Date();
	        	    	if((curDate.after(startDate))&&(curDate.before(endDate))){
	        	    		Iterator its11 = order.getJpoMemberOrderList().iterator();
	        	        	while (its11.hasNext()) {
	        	    			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its11.next();
	        	        		if("P13010200201CN0".equals(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo())){
	        	        			pv = new BigDecimal("0");
	        	        		}
	        	    		}
	        	    	}
						
						if(pv.compareTo(order.getPvAmt())<1){
							if(amount.compareTo(sumPrice)<1){
								//角色
								String roleId = this.getConfigValue(order
										.getSysUser().getCompanyCode(),
										Constants.JOCS_STORE1);
//								this.getChangeJmiMemberRole(order.getSysUser(), roleId);
//								order.getSysUser().getJmiMember().setIsstore("1");
							}else{
					    		throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购金额不正确");
							}
				    	}else{
				    		throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购金额不正确");
				    	}
				    	JmiStore jmiStore = jmiStoreManager.getJmiStoreByUserCode(order.getSysUser().getUserCode());
//				    	if(jmiStore!=null){
//				    		jmiStore.setOrderTime(order.getCheckTime());
//				    		jmiStore.setOrderDate(order.getCheckDate());
//				    		jmiStoreManager.saveJmiStore(jmiStore);
//				    	}
//				    	order.getSysUser().getJmiMember().setRecommendStore(order.getSysUser().getJmiMember().getRecommendNo());
					}else{
						throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessSSubUId,operatorSysUser));
					}
					break;
				case 13:
					break;
				case 14://二级馆重销单
					int businessSSubRId = this.getJpoMemberOrderBusinessSSubR(order);
					if(businessSSubRId==90){
						
					}else{
						throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessSSubRId,operatorSysUser));
					}
					break;
				case 15://会员AUTOSHIP
					int businessASId = this.getJpoMemberOrderBusinessAS(order);
					if(businessASId==40){
						
					}else{
						throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessASId,operatorSysUser));
					}
					break;
				case 21://活力小铺首单
					int businessMFId = this.getJpoMemberOrderBusinessSMF(order);
					if(businessMFId==90){
						BigDecimal amount  =new BigDecimal(this.getConfigValue(order.getSysUser().getCompanyCode().toUpperCase(), "store.m.order.amount"));
				    	BigDecimal pv  =new BigDecimal(this.getConfigValue(order.getSysUser().getCompanyCode().toUpperCase(), "store.m.order.pv"));
				    	if(amount.compareTo(sumPrice)==1){
				    		throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购金额不正确");
				    	}
				    	if(pv.compareTo(order.getPvAmt())==1){
				    		throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购PV不正确");
				    	}
					}else{
						throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessMFId,operatorSysUser));
					}
					break;
				case 24://活力小铺重销单
					int businessMRId = this.getJpoMemberOrderBusinessSMR(order);
					if(businessMRId==90){
						
					}else{
						throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessMRId,operatorSysUser));
					}
					break;
			}
			logCurDate=new Date();
			
			/*if("4".equals(order.getOrderType()) || "9".equals(order.getOrderType()) 
					|| "14".equals(order.getOrderType())){
				//不冻，35PV订单
				String yearsMember = order.getSysUser().getJmiMember().getStartWeek().toString().substring(0, 4);
				String monthsMember = order.getSysUser().getJmiMember().getStartWeek().toString().substring(4, 6);
				List periodsMember = bdPeriodManager.getBdPeriodsByMonth(yearsMember, monthsMember);
				BdPeriod bdPeriod = (BdPeriod)periodsMember.get(0);
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				CommonRecord crm = new CommonRecord();
				crm.addField("stauts", "2");
				crm.addField("userCode", order.getSysUser().getUserCode());
				crm.addField("orderType", "4,9,14");
				crm.addField("startLogTime", dateFormat.format(bdPeriod.getStartTime()));
				BigDecimal pvAmt = dao.getJpoMemberOrderStatics(crm);
				pvAmt = pvAmt.add(order.getPvAmt());
				
				//时间限制
				java.util.Calendar startcPre=java.util.Calendar.getInstance();
				startcPre.set(2012, 8, 29, 00, 00, 00);
				java.util.Date startDatePre = startcPre.getTime();
				Date curDatePre = new Date();
				if ("CN".equals(order.getCompanyCode())) {
					if (curDatePre.after(startDatePre)
							&& new BigDecimal("42").compareTo(pvAmt) != 1) {
						BdPeriod bdPeriodSR = bdPeriodManager.getBdPeriodByTime(
								order.getCheckDate(), null);
						Integer startMonthSR = bdPeriodSR.getWyear() * 100
								+ bdPeriodSR.getWmonth();
						if (order.getSysUser().getJmiMember()
								.getStartWeek() <= startMonthSR) {
							if (!StringUtil.isEmpty(order.getSysUser()
									.getJmiMember().getValidWeek().toString())) {
								
								String years = order.getSysUser()
										.getJmiMember().getValidWeek().toString()
										.substring(0, 4);
								String months = order.getSysUser()
										.getJmiMember().getValidWeek().toString()
										.substring(4, 6);
								String periodS = bdPeriodManager
										.getFutureBdYearMonth(years, months, 2);
								String yeare = periodS.substring(0, 4);
								String monthe = periodS.substring(4, 6);
								String periodE = bdPeriodManager
										.getFutureBdYearMonth(yeare, monthe, 12);
//								order.getSysUser().getJmiMember()
//										.setStartWeek(Integer.parseInt(periodS));
//								order.getSysUser().getJmiMember()
//										.setValidWeek(Integer.parseInt(periodE));
							} else {
								throw new AppException("ValidWeek为空！");
							}
						}
					} 
				} else if (!"CN".equals(order.getCompanyCode())
						&& new BigDecimal("21").compareTo(pvAmt) != 1) {
					BdPeriod bdPeriodSR = bdPeriodManager.getBdPeriodByTime(
							order.getCheckDate(), null);
					Integer startMonthSR = bdPeriodSR.getWyear() * 100
							+ bdPeriodSR.getWmonth();
					if (order.getSysUser().getJmiMember().getStartWeek() <= startMonthSR) {
						if (!StringUtil.isEmpty(order.getSysUser()
								.getJmiMember().getValidWeek().toString())) {
							String years = order.getSysUser()
									.getJmiMember().getValidWeek().toString()
									.substring(0, 4);
							String months = order.getSysUser()
									.getJmiMember().getValidWeek().toString()
									.substring(4, 6);
							String periodS = bdPeriodManager.getFutureBdYearMonth(
									years, months, 2);
							String yeare = periodS.substring(0, 4);
							String monthe = periodS.substring(4, 6);
							String periodE = bdPeriodManager.getFutureBdYearMonth(yeare, monthe, 12);
//							order.getSysUser().getJmiMember().setStartWeek(Integer.parseInt(periodS));
//							order.getSysUser().getJmiMember().setValidWeek(Integer.parseInt(periodE));
						}else{
							throw new AppException("ValidWeek为空！");
						}
					}
				}
				//==============续约次年
			}*/
		}
		
		/*支付改造*/
		this.getSaveMemberOrderDeduct1(order, operatorSysUser, dataType);
		log.info("write JpoBankBookPayList start ,orderNo is:"+order.getMemberOrderNo());
		String orderNo = order.getMemberOrderNo();
		
		List<JpoBankBookPayList> bankbookList = jpoBankBookPayListDao.
				getBankBookPayListByOrderNo(orderNo);
		
		if(bankbookList.isEmpty()){
			JpoBankBookPayList bbp = new JpoBankBookPayList();
			bbp.setAmount(order.getAmount());
			Date dbDate = jpoBankBookPayListDao.getDBdate();
			bbp.setCreateTime(dbDate);
			bbp.setDzAmt(order.getAmount());
			bbp.setInType(2);
			bbp.setMemberOrderNo(order.getMemberOrderNo());
			bbp.setOrderType(order.getOrderType());
			bbp.setUserSPcode(order.getSysUser().getUserCode());
			String temcode = jmiMemberTeamManager.getTeamStr(order.getSysUser());
			bbp.setTeamCode(temcode);
			bbp.setUserCode(order.getSysUser().getUserCode());
			
			String notes = this.getLocalLanguageByKey("poMemberOrder.orderConfirm",
					operatorSysUser) + orderNo;
			String userSpCode = order.getUserSpCode();
			notes += "," + userSpCode;
			Iterator iter = order.getJpoMemberOrderFee().iterator();
			while (iter.hasNext()) {
				JpoMemberOrderFee fee = (JpoMemberOrderFee)iter.next();
				if(fee.getFeeType().equals("1")){
					notes += ",其中物流费为:"+fee.getFee().toString();
					break;
				}
			}
			bbp.setNotes(notes);
			bbp.setCheckUserCode(operatorSysUser.getUserCode());
			jpoBankBookPayListDao.saveObject(bbp);
			
		} else {
			log.warn("table bankBookpayList exist orderNo is:"+orderNo);
		}
		log.info("check Order End ,orderNo is:"+order.getMemberOrderNo());
		
	}

	public JpoBankBookPayListDao getJpoBankBookPayListDao() {
		return jpoBankBookPayListDao;
	}


	public void setJpoBankBookPayListDao(JpoBankBookPayListDao jpoBankBookPayListDao) {
		this.jpoBankBookPayListDao = jpoBankBookPayListDao;
	}

	public JfiBankbookJournalManager getJfiBankbookJournalManager() {
		return jfiBankbookJournalManager;
	}
	

	
}
