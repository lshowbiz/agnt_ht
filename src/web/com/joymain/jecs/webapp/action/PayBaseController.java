package com.joymain.jecs.webapp.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.joymain.jecs.fi.model.JfiAmountDetail;
import com.joymain.jecs.fi.model.JfiPayLog;
import com.joymain.jecs.fi.model.JfiQuota;
import com.joymain.jecs.fi.service.JfiAmountDetailManager;
import com.joymain.jecs.fi.service.JfiQuotaManager;
import com.joymain.jecs.po.model.JpoMemberOrder;

public class PayBaseController extends BaseController{

	private JfiAmountDetailManager jfiAmountDetailManager;
	private JfiQuotaManager jfiQuotaManager;

	public Integer getFullpay(JfiPayLog entity, JpoMemberOrder jpoMemberOrder) {
		Integer isFullPay = 0;
		if (jpoMemberOrder.getAmount2().compareTo(new BigDecimal(entity.getOrderAmount())) == 0) {
			JfiQuota quota = new JfiQuota();
			quota.setValidityPeriod(new SimpleDateFormat("yyyyMM").format(new Date()));
			quota = jfiQuotaManager.getJfiQuota(quota, entity.getMerchantId());
			if (quota != null && quota.getFiBillAccount() != null) {
				JfiAmountDetail amDetail = new JfiAmountDetail();
				amDetail.setCreateTime(new Date());
				amDetail.setUserCode(entity.getUserCode());
				amDetail.setMemberOrderNo(jpoMemberOrder.getMemberOrderNo());
				amDetail.setMoney(entity.getOrderAmount());
				amDetail.setQuotaId(quota.getQuotaId());
				jfiAmountDetailManager.saveJfiAmountDetail(amDetail);// 增加明细
				isFullPay = 1;
			}
		}
		return isFullPay;
	}
	
	public void setJfiAmountDetailManager(JfiAmountDetailManager jfiAmountDetailManager) {
		this.jfiAmountDetailManager = jfiAmountDetailManager;
	}

	public void setJfiQuotaManager(JfiQuotaManager jfiQuotaManager) {
		this.jfiQuotaManager = jfiQuotaManager;
	}

}
