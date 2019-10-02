package com.joymain.jecs.fi.webapp.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.fi.model.JfiPayLog;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.service.UserExistsException;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.PayFactory;
import com.joymain.jecs.util.ServerDateUtil;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.sypay.IPayUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.util.yspay.PayUtils;
import com.joymain.jecs.util.yspay.RemarkBean;
import com.joymain.jecs.webapp.action.PayBaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;

/**
 * 所有支付后台接收总控
 *宝义互通，银盛使用
 * @author lzg /jfipayReceiveController.html
 */
public class JfipayReceiveController extends PayBaseController implements Controller {

	private final Log log = LogFactory.getLog(JfipayReceiveController.class);

	private JfiBankbookJournalManager jfiBankbookJournalManager = null;
	private SysUserManager sysUserManager = null;
	private JpoMemberOrderManager jpoMemberOrderManager = null;
	private IPayUtil payUtil = null;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("=========第三方请求支付地址===============" +  RequestUtil.paramStr2(request));
			log.debug("=========第三方请求支付地址===============" +  request.getRequestURL().toString() + "?" + RequestUtil.paramStr(request));
			log.debug("entering 'handleRequest' method...");
		}
		try {
			String payType = request.getParameter("zmType");
			payUtil = PayFactory.payFirm(payType);
			if (payUtil == null) {
				throw new UserExistsException("请求参数错误" + LocaleUtil.getLocalText("fiPay99bill.payFail") + " Code:0");
			}
			JfiPayLog entity = payUtil.getJfiPayLog(request, "CN");
			String[] config = payUtil.getConfiguration();
			if (entity == null) {
				throw new UserExistsException("请求参数错误" + LocaleUtil.getLocalText("fiPay99bill.payFail") + " Code:2");
			}
			SysUser sysUser = sysUserManager.getSysUser(entity.getUserCode());
			if (sysUser == null) {
				throw new UserExistsException("请求参数错误,会员不存在" + LocaleUtil.getLocalText("fiPay99bill.payFail") + " Code:3");
			}
			log.info("JfipayReceiveController :" + payUtil.getClass());
			if ("10".equals(entity.getReturnMsg())) {//验签成功
				jfiBankbookJournalManager.saveJfiPayDataVerify(sysUser, new BigDecimal(entity.getOrderAmount()), entity.getOrderId(), entity, config);
				if ("1".equals(entity.getFlag())) {// 订单审核
					String orderId = entity.getOrderId();
					JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);
					// jpoMemberOrder.setPayCode(entity.getOrderId());
					//=======================================是否全额支付
					RemarkBean bean = PayUtils.getRemarkBean(entity.getRemark());
					Integer isFullPay = 0;
		    		if(bean.getIsFull()){
		    			isFullPay = getFullpay(entity, jpoMemberOrder);
		    		}
					jpoMemberOrder.setIsFullPay(isFullPay);
					//=======================================是否全额支付
					// 支付时间
					jpoMemberOrder.setSubmitTime(ServerDateUtil.getNowTimeFromDateServer());
					jpoMemberOrder.setSubmitUserCode(jpoMemberOrder.getSysUser().getUserCode());
					jpoMemberOrder.setPayCode(entity.getMerchantId());
					jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder);
					boolean isCheck = this.checkFlagOne(orderId, sysUser);
					log.info("JfipayReceiveController ischeck:" + isCheck + "  jpoMemberOrder1.getIsPay: " + jpoMemberOrder.getIsPay());
					if (isCheck) {
						saveMessage(request, LocaleUtil.getLocalText(sysUser.getDefCharacterCoding(), "opration.notice.js.orderNo.auditSuccess"));
					} else {
						saveMessage(request, LocaleUtil.getLocalText(sysUser.getDefCharacterCoding(), "poOrder.editedFail"));
					}
				}
				response.getWriter().write(config[2]);// 通知不用在从新发送数据
				return null;
			} else {
				// 不成功
				switch (Integer.parseInt(entity.getReturnMsg())) {
				case 0:// 数据被篡改
					MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiPay99bill.payFail") + " Code:4");
					break;
				case 1:// 扣款失败
					MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiPay99bill.payFail") + " Code:5");
					break;
				case 2:// 自定义MD5签名被篡改(签名被破解)
					MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiPay99bill.payFail") + " Code:6");
					break;
				case 3:// 数据重新发送
					response.getWriter().write(config[2]);// 通知不用在从新发送数据
					MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiPay99bill.payFail") + " Code:7");
					return null;
					// break;
				}
			}
			return new ModelAndView("fi/jfiPay99billmsReceive").addObject("returnMsg", entity.getReturnMsg());
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.saveMessage(request, RequestUtil.paramStr2(request));
			System.err.println("=========失败第三方请求支付地址===============" +  RequestUtil.paramStr2(request));
			log.debug("=========失败第三方请求支付地址===============" +  RequestUtil.paramStr2(request));
			return new ModelAndView("fi/jfiPay99billmsReceive").addObject("returnMsg", e.getMessage() + "\n" + e);
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

	public void setPayUtil(IPayUtil payUtil) {
		this.payUtil = payUtil;
	}


	public static void main(String[] args) {
		try {
			Date nextMonthFirstDate = nextMonthFirstDate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			System.out.println(sdf.format(nextMonthFirstDate));
			/*IPayUtil payUtil = PayFactory.payFirm("sypay");
			String[] config = payUtil.getConfiguration();
			System.out.println(config[0]);*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Date nextMonthFirstDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		return calendar.getTime();
	}

	
}
