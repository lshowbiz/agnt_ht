package com.joymain.jecs.fi.webapp.action;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.FiBillAccount;
import com.joymain.jecs.fi.model.FiCommonAddr;
import com.joymain.jecs.fi.model.FiGetbillaccountLog;
import com.joymain.jecs.fi.service.FiBillAccountManager;
import com.joymain.jecs.fi.service.FiBillAccountRelationManager;
import com.joymain.jecs.fi.service.FiCommonAddrManager;
import com.joymain.jecs.fi.service.FiGetbillaccountLogManager;
import com.joymain.jecs.mi.model.JmiAddrBook;
import com.joymain.jecs.mi.service.JmiAddrBookManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 快钱商户号检测控制器
 * 根据会员编号，查找出匹配的收款商户号,匹配关系为根据会员的默认收货地址
 * @author Administrator
 *
 */
public class FiBillAccountCheckController extends BaseController implements Controller {

	private FiBillAccountManager fiBillAccountManager = null;
	private SysUserManager sysUserManager = null;
	private FiCommonAddrManager fiCommonAddrManager = null;
	private FiGetbillaccountLogManager fiGetbillaccountLogManager = null;
	private JmiAddrBookManager jmiAddrBookManager = null;

    public JmiAddrBookManager getJmiAddrBookManager() {
		return jmiAddrBookManager;
	}

	public void setJmiAddrBookManager(JmiAddrBookManager jmiAddrBookManager) {
		this.jmiAddrBookManager = jmiAddrBookManager;
	}

	public void setFiGetbillaccountLogManager(FiGetbillaccountLogManager fiGetbillaccountLogManager) {
        this.fiGetbillaccountLogManager = fiGetbillaccountLogManager;
    }

    public void setFiCommonAddrManager(FiCommonAddrManager fiCommonAddrManager) {
        this.fiCommonAddrManager = fiCommonAddrManager;
    }
	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public void setFiBillAccountManager(
			FiBillAccountManager fiBillAccountManager) {
		this.fiBillAccountManager = fiBillAccountManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		FiGetbillaccountLog fiGetbillaccountLog =new FiGetbillaccountLog();
		String userCode = request.getParameter("userCode");
		
		if(!StringUtil.isEmpty(userCode)){
			
			//验证会员是否存在
			SysUser sysUser = sysUserManager.getSysUser(userCode);
			if(sysUser==null){
				
				saveMessage(request,"该会员编号不存在！");
				return new ModelAndView("fi/fiBillAccountCheck");
			}
			
			//获取会员的默认收货地址
			FiCommonAddr fiCommonAddr = fiCommonAddrManager.getFiCommonAddr(userCode);
			request.setAttribute("fiCommonAddr", fiCommonAddr);
			
			FiBillAccount fiBillAccount = fiBillAccountManager.getFiBillAccountByUserCode(userCode);
			request.setAttribute("fiBillAccount", fiBillAccount);
			
			//日志记录
			fiGetbillaccountLog.setUserCode(userCode);
			fiGetbillaccountLog.setLogDes("校验测试数据");
			fiGetbillaccountLog.setCreateTime(new Date());
			if(fiBillAccount!=null){
				fiGetbillaccountLog.setAccountCode(fiBillAccount.getBillAccountCode());
				fiGetbillaccountLog.setStatus("1");
				
				if(fiCommonAddr==null){
					fiGetbillaccountLog.setLogDes("校验测试数据，未找到会员常用收货地址，获取默认商户号");
				}
			}else{
				fiGetbillaccountLog.setStatus("2");
				fiGetbillaccountLog.setLogDes("校验测试数据，未找到合适的商户号");
			}
			
			fiGetbillaccountLogManager.saveFiGetbillaccountLog(fiGetbillaccountLog);
		}

		return new ModelAndView("fi/fiBillAccountCheck");
	}
}
