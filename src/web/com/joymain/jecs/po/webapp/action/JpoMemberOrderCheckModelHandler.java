package com.joymain.jecs.po.webapp.action;

import java.util.Iterator;




import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.po.model.JpoCheckedFailed;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderCheckModel;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.po.service.JpoCheckedFailedManager;
import com.joymain.jecs.po.service.JpoMemberOrderListManager;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.pr.service.JprRefundManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.ibmmq.IJmsHandler;
import com.joymain.jecs.webapp.util.ConfigUtil;

public class JpoMemberOrderCheckModelHandler  implements IJmsHandler {
	
	private final Log log = LogFactory.getLog(JpoMemberOrderCheckModelHandler.class);
	private JpoMemberOrderManager jpoMemberOrderManager;
	private JpoMemberOrderListManager jpoMemberOrderListManager;
	private JprRefundManager jprRefundManager;
	private JpoCheckedFailedManager checkedFailedManager;
	
	@Override
	public void handMessage(Object model) {
		//System.out.println("========");
		if(model !=null){
			JpoMemberOrderCheckModel orderMode = (JpoMemberOrderCheckModel)model;
			String moId = orderMode.getDataType();
			SysUser operatorSysUser = orderMode.getJsysUser();
			JpoMemberOrder order = jpoMemberOrderManager.getJpoMemberOrder(moId);
			
			log.info("orderNO is:["+order.getMemberOrderNo()+"] and moid is:"+moId);
			Iterator<JpoMemberOrderList> its1 = order.getJpoMemberOrderList().iterator();

			try {
				Integer proSum = 0, proNoCount = 0, countQty = 0;
				String proCount_str = ConfigUtil.getConfigValue("CN", "product.count");
				log.info("product sum is:[" + proCount_str + "]");
				if (StringUtils.isNotBlank(proCount_str)) {
					proNoCount = Integer.parseInt(proCount_str);
				}

				while (its1.hasNext()) {
					JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();

					String proNo = jpoMemberOrderList.getJpmProductSaleTeamType().
							getJpmProductSaleNew().getJpmProduct().getProductNo();

					if (proNo.equalsIgnoreCase(com.joymain.jecs.Constants.PRONO) 
							|| proNo.equalsIgnoreCase(com.joymain.jecs.Constants.PRONO1)
							|| proNo.equalsIgnoreCase(com.joymain.jecs.Constants.PRONO2)) {

						Integer temQty = 0;
						if (proNo.equalsIgnoreCase(com.joymain.jecs.Constants.PRONO)) {
							temQty = jpoMemberOrderList.getQty();
						} else if (proNo.equalsIgnoreCase(com.joymain.jecs.Constants.PRONO1)) {
							temQty = jpoMemberOrderList.getQty() * 3;
						} else if (proNo.equalsIgnoreCase(com.joymain.jecs.Constants.PRONO2)) {
							temQty = jpoMemberOrderList.getQty() * 5;
						}
						countQty += temQty;

						log.info("countQty=" + countQty);
						proSum = jpoMemberOrderListManager.getSumQtyByProNo();

						if ((proNoCount - proSum) < countQty) {
							
							log.error("IBMMQ check order failed,product not enough. "
									+ "orderNo is:"+order.getMemberOrderNo());
							
							JpoCheckedFailed failedOrder = new JpoCheckedFailed();
							failedOrder.setDataType("3");
							failedOrder.setJpoMemberOrder(order);
							failedOrder.setOperatorSysuser(operatorSysUser);
							checkedFailedManager.saveCheckedFailed(failedOrder);
							//return new ModelAndView("fi/jfiOrderProductMsg", "isOver", "产品剩余不足!");
						}
					}

					log.info("proNoCount =[" + proNoCount + "] " + "and proSum is=[" + proSum + "] countQty=" + countQty);

					if (proSum >= proNoCount) {
						log.error("IBMMQ check order failed,product sale over. "
								+ "proNo is:"+proNo);
						JpoCheckedFailed failedOrder = new JpoCheckedFailed();
						failedOrder.setDataType("3");
						failedOrder.setJpoMemberOrder(order);
						failedOrder.setOperatorSysuser(operatorSysUser);
						checkedFailedManager.saveCheckedFailed(failedOrder);
						//return new ModelAndView("fi/jfiOrderProductMsg", "isOver", "产品(" + proNo + ")已售完!");
					}
				}

				jpoMemberOrderManager.checkJpoMemberOrder(order, operatorSysUser, "1");
				jprRefundManager.getFunctionRp(order.getMemberOrderNo(), 1);// 调用sql函数,执行大订单结算降级黄砖会员，幸福会员
				jpoMemberOrderManager.sendJmsCoin(order, operatorSysUser);
				
			} catch (Exception e) {
				log.error("", e);
				JpoCheckedFailed failedOrder = new JpoCheckedFailed();
				failedOrder.setDataType("3");
				failedOrder.setJpoMemberOrder(order);
				failedOrder.setOperatorSysuser(operatorSysUser);
				checkedFailedManager.saveCheckedFailed(failedOrder);
			}
		}
	}

	public JpoMemberOrderManager getJpoMemberOrderManager() {
		return jpoMemberOrderManager;
	}

	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	public JpoMemberOrderListManager getJpoMemberOrderListManager() {
		return jpoMemberOrderListManager;
	}

	public void setJpoMemberOrderListManager(
			JpoMemberOrderListManager jpoMemberOrderListManager) {
		this.jpoMemberOrderListManager = jpoMemberOrderListManager;
	}

	public JprRefundManager getJprRefundManager() {
		return jprRefundManager;
	}

	public void setJprRefundManager(JprRefundManager jprRefundManager) {
		this.jprRefundManager = jprRefundManager;
	}

	public JpoCheckedFailedManager getCheckedFailedManager() {
		return checkedFailedManager;
	}

	public void setCheckedFailedManager(JpoCheckedFailedManager checkedFailedManager) {
		this.checkedFailedManager = checkedFailedManager;
	}
	
}
