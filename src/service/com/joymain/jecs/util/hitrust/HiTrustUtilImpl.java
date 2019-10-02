package com.joymain.jecs.util.hitrust;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import com.hitrust.b2ctoolkit.b2cpay.B2CPayAuth;
import com.joymain.jecs.fi.model.Jfi99billLog;
import com.joymain.jecs.fi.model.JfiHiTrustLog;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.service.SysIdManager;

public class HiTrustUtilImpl implements HiTrustUtil {
    private SysIdManager sysIdManager = null;
    private JpoMemberOrderManager jpoMemberOrderManager = null;

	public String getHiTrustUrl(String moId){
		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(moId);
		if(jpoMemberOrder==null || !"TW".equals(jpoMemberOrder.getCompanyCode())){
			return "jpoMemberOrder.null";
		}
		if("1".equals(jpoMemberOrder.getIsPay())){
			return "jpoMemberOrder.isPay";
		}
		if("2".equals(jpoMemberOrder.getStatus())){
			return "jpoMemberOrder.status2";
		}
		
		B2CPayAuth auth = new B2CPayAuth();
		auth.setStoreId(HiTrustConstants.storeId);
		auth.setOrderNo(jpoMemberOrder.getMoId() + "," + jpoMemberOrder.getSysUser().getUserCode());
		//auth.setOrderDesc(new String(((String)request.getParameter("orderdesc")).getBytes("8859_1"), "Big5"));
		//auth.setOrderDesc(jpoMemberOrder.getSysUser().getUserCode());
		auth.setAmount(jpoMemberOrder.getAmount().multiply(new BigDecimal("100")).toString());
		auth.setTicketNo("");
		auth.setDepositFlag(HiTrustConstants.depositFlag);
		auth.transaction();
		if (auth.getRetCode().equals("00")){
			return auth.getToken();
		}else{
			return auth.getRetCode();
		}
	}

	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}
}
