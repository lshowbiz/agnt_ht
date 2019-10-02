package com.joymain.jecs.pr.webapp.action;

import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.pr.model.JprRefund;
import com.joymain.jecs.pr.model.JprRefundList;
import com.joymain.jecs.pr.service.JprRefundManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.ListUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 退款表单
 * 
 * @author Alvin
 * 
 */
public class JprRefundRefundFormController extends BaseFormController {
	private JprRefundManager jprRefundManager = null;
	private BdPeriodManager bdPeriodManager = null;

	public void setJprRefundManager(JprRefundManager jprRefundManager) {
		this.jprRefundManager = jprRefundManager;
	}

	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public JprRefundRefundFormController() {
		setCommandName("jprRefund");
		setCommandClass(JprRefund.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		// ================AiAgent LOGIN IMFORMATION
		SysUser defSysUser = SessionLogin.getLoginUser(request);
		// =========================================

		JprRefund jprRefund = new JprRefund();

		String roNo = request.getParameter("roNo");
		if (!StringUtil.isEmpty(roNo)) {
			jprRefund = jprRefundManager.getJprRefund(roNo);
			request.setAttribute("refundStatus", jprRefund.getRefundStatus());
			if (jprRefund == null || !"N".equalsIgnoreCase(jprRefund.getLocked()) || !"2".equals(jprRefund.getIntoStatus())) {
				return null;
			}
		}
		if (!"AA".equals(defSysUser.getCompanyCode())) {
			if (!defSysUser.getCompanyCode().equals(jprRefund.getCompanyCode())) {
				return null;
			}
		}
		return jprRefund;
	}

	@SuppressWarnings("unchecked")
	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
		// ================AiAgent LOGIN IMFORMATION
		SysUser defSysUser = SessionLogin.getLoginUser(request);
		// =========================================
		String refundStatus = request.getAttribute("refundStatus").toString();
		JprRefund jprRefund = (JprRefund) command;

		/*
		 * //modify gw 2014-11-04----------------------begin---结算费用 String
		 * repairFee = request.getParameter("repairFee"); String renovationFee =
		 * request.getParameter("renovationFee"); String logisticsFee =
		 * request.getParameter("logisticsFee"); String settledBonus =
		 * request.getParameter("settledBonus"); String fillFreight =
		 * request.getParameter("fillFreight"); // String logisticsFeeSix =
		 * request.getParameter("logisticsFeeSix"); // String logisticsFeeSeven
		 * = request.getParameter("logisticsFeeSeven"); // String
		 * logisticsFeeEight = request.getParameter("logisticsFeeEight");
		 * jprRefund.setRepairFee(repairFee);
		 * jprRefund.setRenovationFee(renovationFee);
		 * jprRefund.setLogisticsFee(logisticsFee);
		 * jprRefund.setSettledBonus(settledBonus);
		 * jprRefund.setFillFreight(fillFreight); //
		 * jprRefund.setLogisticsFeeSix(logisticsFeeSix); //
		 * jprRefund.setLogisticsFeeSeven(logisticsFeeSeven); //
		 * jprRefund.setLogisticsFeeEight(logisticsFeeEight);
		 * 
		 * Float sum =0f;
		 * 
		 * if(!StringUtil.isEmpty(repairFee)){ sum +=
		 * Float.parseFloat(repairFee); }
		 * if(!StringUtil.isEmpty(renovationFee)){ sum +=
		 * Float.parseFloat(renovationFee); }
		 * if(!StringUtil.isEmpty(logisticsFee)){ sum +=
		 * Float.parseFloat(logisticsFee); }
		 * if(!StringUtil.isEmpty(settledBonus)){ sum +=
		 * Float.parseFloat(settledBonus); }
		 * if(!StringUtil.isEmpty(fillFreight)){ sum +=
		 * Float.parseFloat(fillFreight); }
		 * if(!StringUtil.isEmpty(logisticsFeeSix)){ sum +=
		 * Float.parseFloat(logisticsFeeSix); }
		 * if(!StringUtil.isEmpty(logisticsFeeSeven)){ sum +=
		 * Float.parseFloat(logisticsFeeSeven); }
		 * if(!StringUtil.isEmpty(logisticsFeeEight)){ sum +=
		 * Float.parseFloat(logisticsFeeEight); }
		 * 
		 * //modify gw 2014-11-04----------------------end
		 */

		String refundTye = request.getParameter("refundTye");	
		jprRefund.setRefundTye(refundTye);
		jprRefund.setRefundUNo(defSysUser.getUserCode());
		jprRefund.setRefundTime(new Date());

		log.info("refundStatus is:" + refundStatus + "--" + jprRefund.getRefundStatus());

		if ("2".equals(refundStatus)) {
			if ("2".equals(jprRefund.getRefundStatus())) {
				// 已退款改已退
				jprRefund.getJpoMemberOrder().setIsRetreatOrder("1");// 将订单的状态改为已退货
				fillWeek(jprRefund);
				jprRefundManager.saveJprRefund(jprRefund);
			} else {
				// modify gw 2015-02-27
				try {
					// 已退款改未退款或不退款
					jprRefundManager.saveJprRefundRefund(jprRefund, SessionLogin.getOperatorUser(request));
				} catch (Exception e) {
					log.info("退货退款出现异常:"+e.toString());
					saveMessage(request, getText(defSysUser.getDefCharacterCoding(), "请勿进行重复的退款操作"));
					return new ModelAndView(getSuccessView()).addObject("needReload", "1");
				}
			}
		} else {
			if ("2".equals(jprRefund.getRefundStatus())) {
				// 未退款或不退款改已退款
				jprRefund.getJpoMemberOrder().setIsRetreatOrder("1");// 将订单的状态改为已退货

				log.info("function run.");
				JprRefund myJpr = jprRefundManager.getRefundByMoId(jprRefund.getJpoMemberOrder().getMoId(),null);
				Boolean isEoc = false;
				if (null == myJpr || myJpr.getStjFlag() == null) {// 判断该订单是否验证过
					Integer stjPrice = jprRefund.getJpoMemberOrder().getStjPrice();
					if (stjPrice != null) {// 该订单是否存在 5w，20w的订单
						Iterator<JprRefundList> it = jprRefund.getJprReFundList().iterator(); // 循环退货单
						while (it.hasNext()) {
							JprRefundList refundList = it.next();
							if (stjPrice == 5) {// 5万套餐标记
								if (StringUtils.isNotEmpty(ListUtil.getListValue("CN", "5w_product", refundList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo()))) {
									jprRefund.setStjFlag(stjPrice);
									isEoc = true;
									break;
								}
							} else if (stjPrice == 20) {// 20万套餐标记
								if (StringUtils
										.isNotEmpty(ListUtil.getListValue("CN", "20w_product", refundList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo()))) {
									jprRefund.setStjFlag(stjPrice);
									isEoc = true;
									break;
								}
							}
						}
					}
				}
				log.info("function end.");

				fillWeek(jprRefund);
				try {
					jprRefundManager.saveJprRefundRefund(jprRefund, SessionLogin.getOperatorUser(request));
					if (isEoc) {
						jprRefundManager.getFunctionRp(jprRefund.getJpoMemberOrder().getMemberOrderNo(), 2);// 45w、65w订单退款触发
					}
				} catch (Exception e) {
			        log.info("退货退款出现异常:" + e.toString());
					saveMessage(request, getText(defSysUser.getDefCharacterCoding(), "请勿进行重复的退款操作"));
					return new ModelAndView(getSuccessView()).addObject("needReload", "1");
				}

			} else {
				// 未退款或不退款改未退款或不退款
				jprRefundManager.saveJprRefund(jprRefund);
			}
		}
		//Modify By WuCF 20151211 修改对应订单的字段
		jprRefundManager.updateJpoMemberOrderFlag(jprRefund);
		saveMessage(request, getText(defSysUser.getDefCharacterCoding(), "prRefund.refund.success"));
		return new ModelAndView(getSuccessView()).addObject("needReload", "1");
	}

	/**
	 * 填入退货单结算期别 如果会员订单未结算，则退货单与会员订单同一期结算 如果会员订单已结算，则退货单当期结算
	 * 
	 * @param jprRefund
	 */
	private void fillWeek(JprRefund jprRefund) {
		JpoMemberOrder jpoMemberOrder = jprRefund.getJpoMemberOrder();
		if (jpoMemberOrder != null && !"1".equals(jpoMemberOrder.getLocked())) {
			// 审核时间
			BdPeriod bdPeriod = bdPeriodManager.getBdPeriodByTime(jpoMemberOrder.getCheckDate(), null);
			if (bdPeriod != null) {
				jprRefund.setWyear(bdPeriod.getWyear());
				jprRefund.setWmonth(bdPeriod.getWmonth());
				String Wweek = bdPeriod.getWyear().toString() + StringUtils.leftPad(bdPeriod.getWweek().toString(), 2, "0");
				jprRefund.setWweek(Integer.parseInt(Wweek));
			}
		}
	}

	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		String[] allowedFields = { "refundStatus", "refundRemark" };
		binder.setAllowedFields(allowedFields);
		// TODO Auto-generated method stub
		// binder.setAllowedFields(allowedFields);
		// binder.setDisallowedFields(disallowedFields);
		// binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
