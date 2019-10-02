package com.joymain.jecs.pd.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.pd.model.PdExchangeOrder;
import com.joymain.jecs.pd.model.PdExchangeOrderDetail;
import com.joymain.jecs.pd.model.PdReturnPurchase;
import com.joymain.jecs.pd.model.PdReturnPurchaseDetail;
import com.joymain.jecs.pd.model.PdSendInfo;
import com.joymain.jecs.pd.model.PdSendInfoDetail;
import com.joymain.jecs.pd.model.PdShipmentsDetail;
import com.joymain.jecs.pd.dao.PdShipmentsDetailDao;
import com.joymain.jecs.pd.service.PdSendInfoManager;
import com.joymain.jecs.pd.service.PdShipmentsDetailManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;


public class PdShipmentsDetailManagerImpl extends BaseManager implements
		PdShipmentsDetailManager {

	

	private PdShipmentsDetailDao dao;
	private SysUserManager sysUserManager;
//	private PdSendInfoManager pdSendInfoManager;

	
	public void addPdShipmentsDetailsByOrder(PdReturnPurchase order) {
		// TODO Auto-generated method stub
		log.info("-->发货退货单 待发货");
		Set pdReturnPurchasedetails = order.getPdReturnPurchaseDetails();
		Iterator iterator= pdReturnPurchasedetails.iterator();
		
		
		while(iterator.hasNext()){
			
			PdReturnPurchaseDetail pdReturnPurchasedetail = (PdReturnPurchaseDetail) iterator.next();
			PdShipmentsDetail pdShipmentsDetail = new PdShipmentsDetail();
//			pdShipmentsDetail.setSdId(null);
			pdShipmentsDetail.setCompanyCode(order.getCompanyCode());
			pdShipmentsDetail.setOrderNo(order.getRpNo());
			pdShipmentsDetail.setOrderType("0");
			pdShipmentsDetail.setConsignee(order.getCustomer());
			pdShipmentsDetail.setCustomer(order.getCustomer());
			
			
			
			pdShipmentsDetail.setProductNo(pdReturnPurchasedetail.getProductNo());
			
			
			pdShipmentsDetail.setPrice(pdReturnPurchasedetail.getPrice());
			pdShipmentsDetail.setQuantity(pdReturnPurchasedetail.getQty());
			pdShipmentsDetail.setRemainQuantity(pdReturnPurchasedetail.getQty());
			
			pdShipmentsDetail.setCreateTime(new Date());
//			pdShipmentsDetail.setPriority(0);
			dao.savePdShipmentsDetail(pdShipmentsDetail);
			
		}
	}

	public void addPdShipmentsDetailsByOrder(PdExchangeOrder order) {
		// TODO Auto-generated method stub
		log.info("-->发货退货单 待发货");
		Set pdExchangeOrderDetails = order.getPdExchangeOrderDetails();
		Iterator iterator= pdExchangeOrderDetails.iterator();
		
		
		while(iterator.hasNext()){
			
			PdExchangeOrderDetail pdExchangeOrderDetail = (PdExchangeOrderDetail) iterator.next();
			PdShipmentsDetail pdShipmentsDetail = new PdShipmentsDetail();
//			pdShipmentsDetail.setSdId(null);
			pdShipmentsDetail.setCompanyCode(order.getCompanyCode());
			pdShipmentsDetail.setOrderNo(order.getEoNo());
			pdShipmentsDetail.setOrderType("10");
			pdShipmentsDetail.setConsignee(order.getCustomer());
			pdShipmentsDetail.setCustomer(order.getCustomer());
			
			
			
			pdShipmentsDetail.setProductNo(pdExchangeOrderDetail.getProductNo());
			
			
			pdShipmentsDetail.setPrice(pdExchangeOrderDetail.getPrice());
			pdShipmentsDetail.setQuantity(pdExchangeOrderDetail.getQty());
			pdShipmentsDetail.setRemainQuantity(pdExchangeOrderDetail.getQty());
			
			pdShipmentsDetail.setCreateTime(new Date());
//			pdShipmentsDetail.setPriority(0);
			dao.savePdShipmentsDetail(pdShipmentsDetail);
			
		}
	}

	public PdShipmentsDetail getPdShipmentsDetail(String orderNo,
			String productNo) {
		// TODO Auto-generated method stub
		return dao.getPdShipmentsDetail(orderNo, productNo);
	}

	public String saveMatchSendInfo(PdSendInfo pdSendInfo) {
		// TODO Auto-generated method stub
		Set sets = pdSendInfo.getPdSendInfoDetails();
		Iterator iterator = sets.iterator();
		while (iterator.hasNext()) {
			PdSendInfoDetail pdSendInfoDetail = (PdSendInfoDetail) iterator
					.next();
			PdShipmentsDetail pdShipmentsDetail = dao.getPdShipmentsDetail(
					pdSendInfo.getOrderNo(), pdSendInfoDetail.getProductNo());

			if (pdShipmentsDetail != null) {
				if (pdShipmentsDetail.getRemainQuantity() >= pdSendInfoDetail
						.getQty()) {
					pdShipmentsDetail.setRemainQuantity(pdShipmentsDetail
							.getRemainQuantity()
							- pdSendInfoDetail.getQty());
					dao.savePdShipmentsDetail(pdShipmentsDetail);
				} else {
					throw new AppException("order.remainqty.notenough");
				}
			} else {
				throw new AppException("order.notfound");
			}

		}
		return null;
	}



	public void addPdShipmentsDetailsByOrder(Object order, String className) {
		// TODO Auto-generated method stub
		if ("JpoMemberOrder".equals(className)) {
			addPdShipmentsDetailsByOrder((JpoMemberOrder) order);
		}

	}

	public void addPdShipmentsDetailsByOrder(JpoMemberOrder order) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		log.info("-->会员订单 待发货");
		Set<JpoMemberOrderList> poMemberOrderLists = order
				.getJpoMemberOrderList();
		Iterator<JpoMemberOrderList> iterator = poMemberOrderLists.iterator();
		// Integer i = 1;

		while (iterator.hasNext()) {
			JpoMemberOrderList poMemberOrderList = (JpoMemberOrderList) iterator
					.next();
			PdShipmentsDetail pdShipmentsDetail = new PdShipmentsDetail();

			pdShipmentsDetail.setOrderType(order.getOrderType());
			pdShipmentsDetail.setCustomer(order.getSysUser());
			pdShipmentsDetail.setConsignee(order.getSysUser());
			pdShipmentsDetail.setCompanyCode(order.getCompanyCode());
			pdShipmentsDetail.setOrderNo(order.getMemberOrderNo());

			//WuCF JpmProductSaleNew Modify By WuCF 20130917
			/*pdShipmentsDetail.setProductNo(poMemberOrderList
					.getJpmProductSale().getJpmProduct().getProductNo());*/
			pdShipmentsDetail.setProductNo(poMemberOrderList
					.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo());
			pdShipmentsDetail.setPrice(poMemberOrderList.getPrice());
			pdShipmentsDetail.setQuantity(new Long(poMemberOrderList.getQty()));
			pdShipmentsDetail.setRemainQuantity(new Long(poMemberOrderList.getQty()));

			//			

			pdShipmentsDetail.setCreateTime(new Date());
			dao.savePdShipmentsDetail(pdShipmentsDetail);
		}
	}

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setPdShipmentsDetailDao(PdShipmentsDetailDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdShipmentsDetailManager#getPdShipmentsDetails(com.joymain.jecs.pd.model.PdShipmentsDetail)
	 */
	public List getPdShipmentsDetails(final PdShipmentsDetail pdShipmentsDetail) {
		return dao.getPdShipmentsDetails(pdShipmentsDetail);
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdShipmentsDetailManager#getPdShipmentsDetail(String
	 *      sdId)
	 */
	public PdShipmentsDetail getPdShipmentsDetail(final String sdId) {
		return dao.getPdShipmentsDetail(new Long(sdId));
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdShipmentsDetailManager#savePdShipmentsDetail(PdShipmentsDetail
	 *      pdShipmentsDetail)
	 */
	public void savePdShipmentsDetail(PdShipmentsDetail pdShipmentsDetail) {
		dao.savePdShipmentsDetail(pdShipmentsDetail);
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdShipmentsDetailManager#removePdShipmentsDetail(String
	 *      sdId)
	 */
	public void removePdShipmentsDetail(final String sdId) {
		dao.removePdShipmentsDetail(new Long(sdId));
	}

	// added for getPdShipmentsDetailsByCrm
	public List getPdShipmentsDetailsByCrm(CommonRecord crm, Pager pager) {
		return dao.getPdShipmentsDetailsByCrm(crm, pager);
	}
}
