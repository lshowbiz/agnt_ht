
package com.joymain.jecs.fi.service.impl;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.fi.model.FiLovecoinBalance;
import com.joymain.jecs.fi.model.FoundationOrder;
import com.joymain.jecs.fi.dao.FoundationOrderDao;
import com.joymain.jecs.fi.service.FiLovecoinJournalManager;
import com.joymain.jecs.fi.service.FoundationOrderManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
public class FoundationOrderManagerImpl extends BaseManager implements FoundationOrderManager {
    
	private FoundationOrderDao dao;
    private FiLovecoinJournalManager fiLovecoinJournalManager;
    private JfiBankbookJournalManager jfiBankbookJournalManager;
    
    public void setJfiBankbookJournalManager(
			JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}

    public void setFiLovecoinJournalManager(FiLovecoinJournalManager fiLovecoinJournalManager) {
        this.fiLovecoinJournalManager = fiLovecoinJournalManager;
    }

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFoundationOrderDao(FoundationOrderDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FoundationOrderManager#getFoundationOrders(com.joymain.jecs.fi.model.FoundationOrder)
     */
    public List getFoundationOrders(final FoundationOrder foundationOrder) {
        return dao.getFoundationOrders(foundationOrder);
    }

    /**
     * @see com.joymain.jecs.fi.service.FoundationOrderManager#getFoundationOrder(String orderId)
     */
    public FoundationOrder getFoundationOrder(final String orderId) {
        return dao.getFoundationOrder(new Long(orderId));
    }

    /**
     * @see com.joymain.jecs.fi.service.FoundationOrderManager#saveFoundationOrder(FoundationOrder foundationOrder)
     */
    public void saveFoundationOrder(FoundationOrder foundationOrder) {
        dao.saveFoundationOrder(foundationOrder);
    }
    
    /**
     * @see com.joymain.jecs.fi.service.FoundationOrderManager#saveFoundationOrder(FoundationOrder foundationOrder)
     */
    public Long saveFoundationOrder2(FoundationOrder foundationOrder) {
        return dao.saveFoundationOrder2(foundationOrder);
    }

    /**
     * @see com.joymain.jecs.fi.service.FoundationOrderManager#removeFoundationOrder(String orderId)
     */
    public void removeFoundationOrder(final String orderId) {
        dao.removeFoundationOrder(new Long(orderId));
    }
    //added for getFoundationOrdersByCrm
    public List getFoundationOrdersByCrm(CommonRecord crm, Pager pager){
	return dao.getFoundationOrdersByCrm(crm,pager);
    }

	public List foundationItemsByCrm(CommonRecord crm, Pager pager) {
		return dao.foundationItemsByCrm(crm,pager);
	}

	/**
	 * 慈善公益订单审单
	 */
	public void checkFoundationOrder(FoundationOrder foundationOrder,SysUser sysUser) throws AppException {
		
		//1.扣钱
		//公司编码
		String companyCode = sysUser.getCompanyCode();
		
		//资金类别
		Integer[] moneyType = new Integer[1];
		moneyType[0] = 99;//99代表审核公益产品订单
		
		//要扣的金额
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = foundationOrder.getAmount();
		
		//摘要
		String[] notes = new String[1];
		notes[0] = "慈善公益订单支付,订单号：" + foundationOrder.getOrderId();
		
		//电子存折扣钱
		jfiBankbookJournalManager.saveJfiBankbookDeduct(companyCode, sysUser, moneyType, moneyArray, sysUser.getUserCode(), sysUser.getUserName(), "CJ"+foundationOrder.getOrderId(), notes,"1");
		
		//2.审单
        foundationOrder.setStatus("1");
        foundationOrder.setStartTime(new Date());
        dao.saveFoundationOrder(foundationOrder);
		
		//3.赠送爱心积分,爱心积分认购活动才赠送积分
        if(("2").equals(foundationOrder.getFoundationItem().getType())){
        	
        	this.saveLoveCoin(foundationOrder, sysUser);  
        }             
	}
	
	/**
	 * 赠送爱心积分具体处理方法
	 * @param foundationOrder
	 * @param sysUser
	 */
	public void saveLoveCoin(FoundationOrder foundationOrder,SysUser sysUser) throws AppException{
		
		//爱心积分账户开户(会自动判断账户是否存在，不存在的情况下才会创建)
		fiLovecoinJournalManager.createLovecoin(sysUser.getUserCode());
		
		 //资金类型
        Integer[] moneyType = new Integer[1];
		moneyType[0] = 99;//99代表审核公益产品订单
		
		//赠送的爱心积分金额
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = foundationOrder.getAmount();
		
		//摘要
		String[] notes = new String[1];
		notes[0] = "慈善公益订单支付赠送爱心积分,订单号：" + foundationOrder.getOrderId();
		
		Long[] appId = new Long[1];
		appId[0] = 2l;
		
		//爱心积分账户进账
        fiLovecoinJournalManager.saveFiPayDataVerify("CN", sysUser, moneyType, moneyArray, sysUser.getUserCode(), sysUser.getUserName(), foundationOrder.getOrderId().toString(), notes, appId, null);
	}

	//查询会员在过去的1年里面参加的爱心365活动的订单
	public List getOrdersByItemTypeAndTime(String userCode) {
		// TODO Auto-generated method stub
		return dao.getOrdersByItemTypeAndTime(userCode);
	}
}
