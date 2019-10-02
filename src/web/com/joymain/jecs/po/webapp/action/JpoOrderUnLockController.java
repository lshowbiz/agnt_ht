package com.joymain.jecs.po.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.fi.model.JfiBankbookJournal;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.webapp.action.BaseFormController;

public class JpoOrderUnLockController extends BaseFormController {

	private Log log = LogFactory.getLog(JpoOrderUnLockController.class);
	private JpoMemberOrderManager jpoMemberOrderManager;
	private JfiBankbookJournalManager jfiBankbookJournalManager;

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		String orderNo = request.getParameter("orderNo");
		// 0解锁,1审单
		String flag = request.getParameter("flag");
		String msg = "已执行!";

		try {
			JpoMemberOrder order = jpoMemberOrderManager
					.getJpoMemberOrderByMemberOrderNo(orderNo);

			if (order != null && order.getMemberOrderNo() != null) {
				if (flag.equals("0")) {
					// 0表示未支付,1表示已支付
					order.setIsPay("0");
				} else {
					order.setStatus("2");
					if (order.getCheckTime() == null
							|| order.getCheckDate() == null) {
						JfiBankbookJournal bj = jfiBankbookJournalManager
								.getBankbookJournal(order.getSysUser()
										.getUserCode(), "D", order
										.getMemberOrderNo());
						if (bj != null) {
							order.setCheckDate(bj.getDealDate());
							order.setCheckTime(bj.getDealDate());
							order.setRemark("system operating");
						} else {
							log.error("bankBook is null.");
							throw new Exception();
						}
					}

				}
				jpoMemberOrderManager.saveJpoMemberOrder(order);
			} else {
				msg = "订单不存在!";
			}

		} catch (Exception e) {
			log.error("", e);
			msg = "执行错误.";
		}
		saveMessage(request, msg);
		return super.onSubmit(request, response, command, errors);
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		return super.formBackingObject(request);
	}

	public JpoMemberOrderManager getJpoMemberOrderManager() {
		return jpoMemberOrderManager;
	}

	public void setJpoMemberOrderManager(
			JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	public JfiBankbookJournalManager getJfiBankbookJournalManager() {
		return jfiBankbookJournalManager;
	}

	public void setJfiBankbookJournalManager(
			JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}

}
