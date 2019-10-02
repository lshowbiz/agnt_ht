package com.joymain.jecs.fi.webapp.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.fi.model.FiBillAccount;
import com.joymain.jecs.fi.model.JfiQuota;
import com.joymain.jecs.fi.service.FiBillAccountManager;
import com.joymain.jecs.fi.service.JfiQuotaManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;
@SuppressWarnings("rawtypes")
public class FiBillAccountController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiBillAccountController.class);
    private FiBillAccountManager fiBillAccountManager = null;
    private JfiQuotaManager jfiQuotaManager = null;
    
    public void setJfiQuotaManager(JfiQuotaManager jfiQuotaManager) {
		this.jfiQuotaManager = jfiQuotaManager;
	}

	public void setFiBillAccountManager(FiBillAccountManager fiBillAccountManager) {
        this.fiBillAccountManager = fiBillAccountManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        SysUser loginUser = SessionLogin.getLoginUser(request);
        FiBillAccount fiBillAccount = new FiBillAccount();
        // populate object with request parameters
        BeanUtils.populate(fiBillAccount, request.getParameterMap());
        
        String flag = request.getParameter("flag");
        
        if(!StringUtil.isEmpty(flag)){
        	
        	String accountId = request.getParameter("accountId");
        	FiBillAccount tempFiBillAccount = fiBillAccountManager.getFiBillAccount(accountId);
        	fiBillAccount = fiBillAccountManager.getFiBillAccount(accountId);
        	//启用时需要进行判断是否可以启用
            if(("0").equals(flag)){
            	
            	//验证经销商是否已经存在
				FiBillAccount fi = new FiBillAccount();
				fi.setAccountType(tempFiBillAccount.getAccountType());
				fi.setUserCode(tempFiBillAccount.getUserCode());
				fi.setStatus("0");
				List tempList = fiBillAccountManager.getFiBillAccounts(fi);
				if(tempList.size()>0){
					
					saveMessage(request, "该经销商的启用收款商户号已经存在！");
				}else{
					//更新态
		        	tempFiBillAccount.setStatus(flag);
		        	fiBillAccountManager.saveFiBillAccount(tempFiBillAccount);
		        	
		        	//增加最大额度
		        	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
					String validityPeriod = sdf.format(new Date());// 期别
		        	
		        	CommonRecord crm = new CommonRecord();
					crm.setValue("validityPeriod",validityPeriod);
					crm.setValue("accountId", fiBillAccount.getAccountId());
					crm.setValue("operatorName",loginUser.getUserCode());
					jfiQuotaManager.updateJfiQuotaStatus(crm);
		        	
		        	
					JfiQuota jfiQuota = new JfiQuota();
					jfiQuota.setValidityPeriod(validityPeriod);// 期别
					jfiQuota.setAccountId(fiBillAccount.getAccountId());// 商户号表主键
					jfiQuota.setStatus("0");// 状态默认启用
					jfiQuota.setMaxMoney(fiBillAccount.getMaxMoney());// 最大限额
					jfiQuota.setOperateName(loginUser.getUserCode());
					jfiQuota.setOperateTime(new Date());// 新增数据
					jfiQuotaManager.saveJfiQuota(jfiQuota);
				}
            }
            if(("1").equals(flag)){
            	
            	//更新态
	        	tempFiBillAccount.setStatus(flag);
	        	fiBillAccountManager.saveFiBillAccount(tempFiBillAccount);
            }
        }
        

        //List fiBillAccounts = fiBillAccountManager.getFiBillAccounts(fiBillAccount);
        /**** auto pagination ***/
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("fiBillAccountListTable",request, 20);
		List fiBillAccounts = fiBillAccountManager.getFiBillAccountsByCrm(crm,pager);
        request.setAttribute("fiBillAccountListTable_totalRows", pager.getTotalObjects());
        /*****/
        
      
        return new ModelAndView("fi/fiBillAccountList", "fiBillAccountList", fiBillAccounts);
    }
}
