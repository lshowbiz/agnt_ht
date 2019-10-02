package com.joymain.jecs.po.webapp.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.pd.service.PdSendInfoManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.po.service.JpoMemberOrderListManager;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.pr.service.JprRefundManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 审核订单
 * 
 * @author Alvin
 * 
 */
public class JpoMemberOrderCheckController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(JpoMemberOrderCheckController.class);

	private JpoMemberOrderManager jpoMemberOrderManager = null;
	private SysIdManager sysIdManager = null;

	/**
	 * @author 罗婷 审核订单成功后发送邮件
	 */
	private PdSendInfoManager pdSendInfoManager = null;

	private AlStateProvinceManager alStateProvinceManager = null;

	private AlCityManager alCityManager = null;
	private AlDistrictManager alDistrictManager = null;
	private JpoMemberOrderListManager jpoMemberOrderListManager;
	private JprRefundManager jprRefundManager;

	
	public void setJprRefundManager(JprRefundManager jprRefundManager) {
		this.jprRefundManager = jprRefundManager;
	}

	public void setAlDistrictManager(AlDistrictManager alDistrictManager) {
		this.alDistrictManager = alDistrictManager;
	}

	public void setAlStateProvinceManager(AlStateProvinceManager alStateProvinceManager) {
		this.alStateProvinceManager = alStateProvinceManager;
	}

	public void setAlCityManager(AlCityManager alCityManager) {
		this.alCityManager = alCityManager;
	}

	public void setPdSendInfoManager(PdSendInfoManager pdSendInfoManager) {
		this.pdSendInfoManager = pdSendInfoManager;
	}

	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("JpoMemberOrderCheckController entering 'handleRequest' method...");
		}
		SysUser loginSysUser = SessionLogin.getLoginUser(request);
		SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
		if ("C".equals(loginSysUser.getUserType())) {
//			RequestUtil.freshSession(request, "poMemberOrdersCheck", 10l);
		} else if ("M".equals(loginSysUser.getUserType())) {
			RequestUtil.freshSession(request, "poMemberOrdersCheck", 5l);
		}
		String orderIdStr = request.getParameter("orderId");
		if (!StringUtils.isEmpty(orderIdStr)) {
			if ("C".equals(loginSysUser.getUserType())) {
//				if (RequestUtil.saveOperationSession(request, "poMemberOrdersCheck1", 10l) != 0) {
//					return new ModelAndView("redirect:jpoMemberOrdersCheck.html");
//				}
			}
			List successList = new ArrayList();
			List errorList = new ArrayList();
			Exception exception = null;
			AppException appException = null;
			String[] orderId = orderIdStr.split(",");
			String submitType = request.getParameter("submitType");
			if ("2".equals(submitType)) {
				if (orderId != null && orderId.length > 0) {
					for (int i = 0; i < orderId.length; i++) {
						JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId[i]);
						/*
						 * 支付改造添加验证bug:2551
						 */
						if(validateOrder(jpoMemberOrder)){
							errorList.add(LocaleUtil.getLocalText("role.error") + ":" +
									jpoMemberOrder.getMemberOrderNo() +
									LocaleUtil.getLocalText("poOrder.editedFail"));
							if (jpoMemberOrder.getOrderType().equals("93")) {
								errorList.add(LocaleUtil.getLocalText("代金券换购单无法审核"));
							}
						} else {
							try{
								if(! jpoMemberOrder.getIsPay().equals("1")){
									jpoMemberOrderManager.checkJpoMemberOrder(jpoMemberOrder, operatorSysUser, "1");
									successList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + ":" +
											jpoMemberOrder.getMemberOrderNo() + LocaleUtil.getLocalText("poOrder.editedSuc"));
								} else {
									log.warn("order ispay is 1 , orderNo is:"+jpoMemberOrder.getMemberOrderNo());
								}
							}catch (AppException app) {
								log.error("", app);
								appException = app;
								errorList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + ":" +
										jpoMemberOrder.getMemberOrderNo() + LocaleUtil.getLocalText("poOrder.editedFail") + "," +
										app.getMessage());

							} catch (Exception e) {
								log.error("", e);
								exception = e;
								errorList.add(LocaleUtil.getLocalText("checkError.Code.111") + ":" +
										jpoMemberOrder.getMemberOrderNo() + LocaleUtil.getLocalText("poOrder.editedFail"));
							}
						}
						/* 支付改造
						Iterator<JpoMemberOrderList> its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
						if ("2".equals(submitType)) {// 未审核转审核
							try {
								// TODO Jun
								Integer proSum = 0, proNoCount = 0, countQty = 0;
								String proCount_str = ConfigUtil.getConfigValue("CN", "product.count");
								log.info("product sum is:[" + proCount_str + "]");
								if (StringUtils.isNotBlank(proCount_str)) {
									proNoCount = Integer.parseInt(proCount_str);
								}

								while (its1.hasNext()) {
									JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();

									String proNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();

									if (proNo.equalsIgnoreCase(com.joymain.jecs.Constants.PRONO) || proNo.equalsIgnoreCase(com.joymain.jecs.Constants.PRONO1)
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
											return new ModelAndView("fi/jfiOrderProductMsg", "isOver", "产品剩余不足!");
										}
									}

									log.info("proNoCount =[" + proNoCount + "] " + "and proSum is=[" + proSum + "] countQty=" + countQty);

									if (proSum >= proNoCount) {
										return new ModelAndView("fi/jfiOrderProductMsg", "isOver", "产品(" + proNo + ")已售完!");
									}
								}
								// end jun

								jpoMemberOrderManager.checkJpoMemberOrder(jpoMemberOrder, operatorSysUser, "1");
								jprRefundManager.getFunctionRp(jpoMemberOrder.getMemberOrderNo(), 1);// 调用sql函数
																										// 执行大订单结算降级黄砖会员，幸福会员
								jpoMemberOrderManager.sendJmsCoin(jpoMemberOrder, operatorSysUser);
								if ("PH".equals(jpoMemberOrder.getCompanyCode())) {
									jpoMemberOrder.setTransactionNumber(sysIdManager.buildIdStr("transactionnumber"));
									jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder);
								}

								successList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + ":" + jpoMemberOrder.getMemberOrderNo()
										+ LocaleUtil.getLocalText("poOrder.editedSuc"));
							} catch (AppException app) {
								log.error("", app);
								appException = app;
								errorList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + ":" + jpoMemberOrder.getMemberOrderNo()
										+ LocaleUtil.getLocalText("poOrder.editedFail") + "," + app.getMessage());

							} catch (Exception e) {
								log.error("", e);
								exception = e;
								errorList.add(LocaleUtil.getLocalText("checkError.Code.111") + ":" + jpoMemberOrder.getMemberOrderNo()
										+ LocaleUtil.getLocalText("poOrder.editedFail"));
							}

							if ("US".equals(jpoMemberOrder.getCompanyCode())) {
								*//**
								 * @author 罗婷 处理发送邮件时需要展示数据的格式
								 *//*
								try {
									pdSendInfoManager.getSendEmail(jpoMemberOrder);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									errorList.add(LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + ":" + jpoMemberOrder.getMemberOrderNo()
											+ LocaleUtil.getLocalText("error.send.email"));
								}

							}
						}*/
					}
				}
			}
			request.setAttribute("successList", successList);
			request.setAttribute("errorList", errorList);
			request.setAttribute("exception", exception);
			request.setAttribute("appException", appException);
		}

		CommonRecord crm = RequestUtil.toCommonRecord(request);
		String mode = request.getParameter("mode");
		crm.addField("mode", mode);
		crm.addField("companyCode", loginSysUser.getCompanyCode());
		crm.addField("locked", "0");
		crm.addField("status", "1,3,4");

		Pager pager = new Pager("jpoMemberOrderListTable", request, 20);
		List jpoMemberOrders = null;
		if (!StringUtils.isEmpty(crm.getString("search", ""))) {
			if ("C".equals(loginSysUser.getUserType())) {
//				if (RequestUtil.saveOperationSession(request, "poMemberOrdersCheck", 10l) != 0) {
//					return new ModelAndView("redirect:jpoMemberOrdersCheck.html");
//				}
			} else if ("M".equals(loginSysUser.getUserType())) {
				if (RequestUtil.saveOperationSession(request, "poMemberOrdersCheck", 5l) != 0) {
					return new ModelAndView("redirect:jpoMemberOrdersCheck.html");
				}
			}
			crm.addField("orderTypeT", "Y");
			jpoMemberOrders = jpoMemberOrderManager.getJpoMemberOrdersByCrm(crm, pager);
		}
		request.setAttribute("jpoMemberOrderListTable_totalRows", pager.getTotalObjects());

		return new ModelAndView("po/jpoMemberOrdersCheckList", "jpoMemberOrderList", jpoMemberOrders);
	}

	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}

	public JpoMemberOrderListManager getJpoMemberOrderListManager() {
		return jpoMemberOrderListManager;
	}

	public void setJpoMemberOrderListManager(JpoMemberOrderListManager jpoMemberOrderListManager) {
		this.jpoMemberOrderListManager = jpoMemberOrderListManager;
	}
	/**
	 * 根据订单类型验证,如果不是对应的角色返回true
	 * @param order
	 * @return true or false
	 */
	private boolean validateOrder(JpoMemberOrder order){
		String isstore  = order.getSysUser().getJmiMember().getIsstore();
		String substore = order.getSysUser().getJmiMember().getSubStoreStatus();
		String orderType = order.getOrderType();
		String active = order.getSysUser().getJmiMember().getActive();
		int notfirst = order.getSysUser().getJmiMember().getNotFirst();
		//1冻结 0非冻结
		int userStatus = order.getSysUser().getJmiMember().getFreezeStatus();
		//1 死点
		if(active.equals("1")){
			return true;
		}
		if(order.getOrderType().equals("1") && notfirst==1){
			return true;
		}
		
		if(orderType.equals("9")){
			//1 一级店铺
			if(! isstore.equals("1")){
				return true;
			}
		}
		if(orderType.equals("11")){
			if(!substore.equals("1")){
				return true;
			}
		}
		if(orderType.equals("93")){
				return true;
		}
		
		if(orderType.equals("12") || orderType.equals("14")){
			//2  二级店铺
			if(! isstore.equals("2")){
				return true;
			}
		}
		//冻结状态只允许审核续约单
		if(userStatus ==1 && ! orderType.equals("3")){
			return true;
		}
		//支付改造bug:2538 续约单
		if(orderType.equals("3") && userStatus==0){
			return true;
		}
		
		return false;
	}
}
