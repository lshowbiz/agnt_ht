package com.joymain.jecs.fi.webapp.action;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.fi.model.JfiChinapnrLog;
import com.joymain.jecs.fi.model.JfiPayLog;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.ServerDateUtil;
import com.joymain.jecs.util.chinapnr.ChinapnrUtil;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.util.yspay.PayUtils;
import com.joymain.jecs.util.yspay.RemarkBean;
import com.joymain.jecs.webapp.action.PayBaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;

/**
 * 汇付天下接收页面
 * 
 * @author lzg
 * 
 */
public class JfiChinapnrReceiveController extends PayBaseController implements Controller {

	private final Log log = LogFactory.getLog(JfiChinapnrReceiveController.class);

	private JfiBankbookJournalManager jfiBankbookJournalManager = null;
	private SysUserManager sysUserManager = null;
	private JpoMemberOrderManager jpoMemberOrderManager = null;
	private ChinapnrUtil chinapnrUtil = null;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		try {
			String remark = request.getParameter("MerPriv");
			String retType = request.getParameter("RetType"); // 返回类型
			RemarkBean bean = PayUtils.getRemarkBean(remark);
			
			SysUser sysUser = sysUserManager.getSysUser(bean.getUserCode());
			JfiChinapnrLog entity = null;
			if (sysUser != null) {
				entity = chinapnrUtil.getJfiChinapnrLog(request, sysUser.getUserCode(), "CN");
				if ("10".equals(entity.getReturnMsg())) {
					jfiBankbookJournalManager.saveJfiPayDataVerifyByChinapnr("CN", sysUser, new BigDecimal(entity.getAmountsum()), sysUser.getUserCode(), sysUser.getUserName(),
							entity.getPayorderid(), entity);
					if ("1".equals(bean.getPayType())) {// 订单审核
						String orderId = entity.getMerorderid();
						JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);
						
						//=======================================是否全额支付
						Integer isFullPay = 0;
			    		if(bean.getIsFull()){
			    			isFullPay = getFullpay(entity, jpoMemberOrder);
			    		}
			    		jpoMemberOrder.setIsFullPay(isFullPay);
						//=======================================是否全额支付
						// 支付时间
						jpoMemberOrder.setSubmitTime(ServerDateUtil.getNowTimeFromDateServer());
						jpoMemberOrder.setSubmitUserCode(jpoMemberOrder.getSysUser().getUserCode());
						jpoMemberOrder.setPayCode(entity.getMerchantid());
						jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder);
						
						boolean isCheck = this.checkFlagOne(orderId, sysUser);
	                	log.info("JfiChinapnrReceive ischeck:" + isCheck +"  jpoMemberOrder1: " + jpoMemberOrder.getIsPay());
						
						if (isCheck) {
							saveMessage(request, LocaleUtil.getLocalText(sysUser.getDefCharacterCoding(), "opration.notice.js.orderNo.auditSuccess"));
						} else {
							saveMessage(request, LocaleUtil.getLocalText(sysUser.getDefCharacterCoding(), "poOrder.editedFail"));
						}
					}
					if ("2".equals(retType)){
						response.getWriter().write("RECV_ORD_ID_" + entity.getMerorderid());// 通知不用在从新发送数据
						return null;
					}
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
						if ("2".equals(retType)){
							response.getWriter().write("RECV_ORD_ID_" + entity.getMerorderid());// 通知不用在从新发送数据
							return null;
						}
							
					}
				}
			} else {
				MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiPay99bill.payFail") + " Code:0");
			}
			return new ModelAndView("fi/jfiPay99billmsReceive").addObject("returnMsg", entity.getReturnMsg());
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("=========失败第三方请求支付地址===============" +  RequestUtil.paramStr2(request));
			return new ModelAndView("fi/jfiPay99billmsReceive").addObject("returnMsg", e.getMessage()+"\n"+e);
		}
	}

	
	
	public Integer getFullpay(JfiChinapnrLog entity, JpoMemberOrder jpoMemberOrder) {
		JfiPayLog payLog = new JfiPayLog();
		payLog.setMerchantId(entity.getMerchantid());
		payLog.setOrderAmount(entity.getAmountsum());
		payLog.setUserCode(entity.getUserCode());
		return super.getFullpay(payLog, jpoMemberOrder);
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

	public void setChinapnrUtil(ChinapnrUtil chinapnrUtil) {
		this.chinapnrUtil = chinapnrUtil;
	}

	
}
