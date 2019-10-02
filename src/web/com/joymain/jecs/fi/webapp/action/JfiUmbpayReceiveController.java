package com.joymain.jecs.fi.webapp.action;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.fi.model.JfiUmbpayLog;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.umbpay.UmbpayUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
/**
 * 宝易互通接收页面
 * @author lzg
 * 
 */
public class JfiUmbpayReceiveController extends BaseController implements Controller {

	private final Log log = LogFactory.getLog(JfiUmbpayReceiveController.class);

	private JfiBankbookJournalManager jfiBankbookJournalManager = null;
	private SysUserManager sysUserManager = null;
	private JpoMemberOrderManager jpoMemberOrderManager = null;
	private UmbpayUtil umbpayUtil = null;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		try {
			String remark = request.getParameter("remark");
			String userCode = "";
			String flag = "";
			if (StringUtils.isNotEmpty(remark)) {
				String[] ext2 = remark.substring(remark.indexOf("[") + 1, remark.indexOf("]")).split(",");
				userCode = ext2[0];
				flag = ext2[1];
			}
			SysUser sysUser = sysUserManager.getSysUser(userCode);
			JfiUmbpayLog entity = null;
			if (sysUser != null) {
				entity = umbpayUtil.getJfiUmbpayLog(request, sysUser.getUserCode(), "CN");
				if ("10".equals(entity.getReturnMsg())) {
					jfiBankbookJournalManager.saveJfiPayDataVerifyByUmbpay("CN", sysUser, new BigDecimal(entity.getAmountsum()), sysUser.getUserCode(), sysUser.getUserName(),
							entity.getMerorderid(), entity);
					if ("1".equals(flag)) {// 订单审核
						String orderId = entity.getMerorderid();
						JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);
//						jpoMemberOrder.setIsPay("1");
						jpoMemberOrder.setPayCode(entity.getMerchantid());

						jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder);
						boolean isCheck = this.checkFlagOne(orderId, sysUser);
						
						log.info("JfiUmbpayReceive ischeck:" + isCheck +"  jpoMemberOrder1.getIsPay: " + jpoMemberOrder.getIsPay());
						
						if (isCheck) {
							saveMessage(request, LocaleUtil.getLocalText(sysUser.getDefCharacterCoding(), "opration.notice.js.orderNo.auditSuccess"));
						} else {
							JpoMemberOrder jpoMemberOrder2 = jpoMemberOrderManager.getJpoMemberOrder(orderId);
							jpoMemberOrder2.setIsPay("0");
							jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder2);
							log.info("审核订单失败！修改支付标示为0！ 订单号：" + jpoMemberOrder2.getMemberOrderNo());
							saveMessage(request, LocaleUtil.getLocalText(sysUser.getDefCharacterCoding(), "poOrder.editedFail"));
						}
					}
					response.getWriter().write("success=true");//通知不用在从新发送数据
					return null;
				} else {
					// 不成功
					switch (Integer.parseInt(entity.getReturnMsg())) {
					case 0:// 数据被篡改
						MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiPay99bill.payFail") + " Code:0");
						break;
					case 1:// 扣款失败
						MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiPay99bill.payFail") + " Code:1");
						break;
					case 2:// 自定义MD5签名被篡改(签名被破解)
						MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiPay99bill.payFail") + " Code:2");
						break;
					case 3:// 数据重新发送
						MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiPay99bill.payFail") + " Code:3");
						response.getWriter().write("success=true");//通知不用在从新发送数据
						return null;
					}
				}
			} else {
				MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiPay99bill.payFail") + " Code:0");
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("redirect:403.jsp");
		}
	}

	/**
	 * 审核订单
	 * 
	 * @param orderId
	 * @param operatorSysUser
	 */
	private boolean checkFlagOne(String orderId, SysUser operatorSysUser) {
		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);
		try {
			jpoMemberOrderManager.checkJpoMemberOrder(jpoMemberOrder, operatorSysUser, "1");
			// jpoMemberOrderManager.checkJpoMemberOrderByJfiBankbook(jpoMemberOrder,operatorSysUser,"1");
			// jpoMemberOrderManager.sendJmsCoin(jpoMemberOrder,operatorSysUser);
		} catch (AppException app) {
			log.info("审核订单抛出异常-----checkFlagOne===:orderId" + orderId + "---err:" + app);
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void setJfiBankbookJournalManager(JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	public void setUmbpayUtil(UmbpayUtil umbpayUtil) {
		this.umbpayUtil = umbpayUtil;
	}
}
