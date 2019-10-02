package com.joymain.jecs.fi.webapp.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.fi.model.FiBillAccount;
import com.joymain.jecs.fi.model.JfiQuota;
import com.joymain.jecs.fi.service.FiBillAccountManager;
import com.joymain.jecs.fi.service.JfiQuotaManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class FiBillAccountFormController extends BaseFormController {
    private FiBillAccountManager fiBillAccountManager = null;
    private SysUserManager sysUserManager = null;
    private JfiQuotaManager jfiQuotaManager = null;

    public void setJfiQuotaManager(JfiQuotaManager jfiQuotaManager) {
        this.jfiQuotaManager = jfiQuotaManager;
    }
    
	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}
    public void setFiBillAccountManager(FiBillAccountManager fiBillAccountManager) {
        this.fiBillAccountManager = fiBillAccountManager;
    }
    public FiBillAccountFormController() {
        setCommandName("fiBillAccount");
        setCommandClass(FiBillAccount.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
    	
        String accountId = request.getParameter("accountId");
        String strAction = request.getParameter("strAction");
        FiBillAccount fiBillAccount = null;

        if (!StringUtils.isEmpty(accountId)) {
        	
            fiBillAccount = fiBillAccountManager.getFiBillAccount(accountId);
        } else {
        	
            fiBillAccount = new FiBillAccount();
        }
        request.setAttribute("strAction", strAction);
        return fiBillAccount;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        FiBillAccount fiBillAccount = (FiBillAccount) command;
        boolean isNew = (fiBillAccount.getAccountId() == null);
        Locale locale = request.getLocale();
        String key=null;
        String strAction = request.getParameter("strAction");
        SysUser loginUser = SessionLogin.getLoginUser(request);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String validityPeriod = sdf.format(new Date());//期别
		if ("deleteFiBillAccount".equals(strAction)  ) {
			
			fiBillAccountManager.removeFiBillAccount(fiBillAccount.getAccountId().toString());
			key="fiBillAccount.delete";
		}
		if ("addFiBillAccount".equals(strAction)) {
		
//			//同省份下如果设了默认，验证同省份下是否有其他默认商户号
//			if(("1").equals(fiBillAccount.getIsDefault())){
//				
//				List tempList=fiBillAccountManager.getFiBillAccountsByIsDefault(fiBillAccount.getBillAccountCode());
//            	if(tempList.size()>0){
//            		
//            		saveMessage(request, "已经存在默认商户号！");
//            		return showForm(request, response, errors);	
//            	}
//			}
//		
			//验证商户号是否重复
//        	if(isNew){
//        		
//	        	List sList=fiBillAccountManager.getFiBillAccountsByBillAccountCode(fiBillAccount.getBillAccountCode());
//	        	if(sList.size()>0){
//	        		
//	        		saveMessage(request, "该商户号已经存在！");
//	        		return showForm(request, response, errors);	
//	        	}
//        	}
			if(isNew){
			    if("0".equals(fiBillAccount.getStatus()))
			    {
			      //验证经销商是否已经存在
	                FiBillAccount tempFiBillAccount = new FiBillAccount();
	                tempFiBillAccount.setAccountType(fiBillAccount.getAccountType());
	                tempFiBillAccount.setUserCode(fiBillAccount.getUserCode());
	                tempFiBillAccount.setStatus("0");
	                List tempList = fiBillAccountManager.getFiBillAccounts(tempFiBillAccount);
	                if(tempList.size()>0){
	                    
	                    saveMessage(request, "该经销商对应的收款商户号已经存在！");
	                    return showForm(request, response, errors); 
	                }
			    }
			}
			
			SysUser sysUser = this.sysUserManager.getSysUser(fiBillAccount.getUserCode());
			if (sysUser == null) {

				saveMessage(request, "经销商编号不存在！");
				return showForm(request, response, errors);	
			}
			
        	//操作人信息
        	SysUser loginSysUser = SessionLogin.getLoginUser(request);
        	fiBillAccount.setCreateTime(new Date());
        	fiBillAccount.setCreateUserCode(loginSysUser.getUserCode());
        	fiBillAccount.setCreateUserName(loginSysUser.getUserName());
        	
        	//Modify By WuCF 20160223 新增商户号存在的校验
        	List list = fiBillAccountManager.getFiBillAccountsByBillAccountCode(fiBillAccount.getBillAccountCode());
        	if(list!=null && list.size()>=1){
        		 saveMessage(request, "商户号"+fiBillAccount.getBillAccountCode()+"已经存在！");
                 return showForm(request, response, errors); 
        	}
        	
        	
        	//查询该经销商是否存在启用数据
        	//如果数据为启用
        	if("0".equals(fiBillAccount.getStatus()))
        	{
        	    FiBillAccount fba = new FiBillAccount();
        	    fba.setUserCode(fiBillAccount.getUserCode());
        	    fba.setStatus("0");
        	    List<FiBillAccount> fbaList = fiBillAccountManager.getFiBillAccounts(fba);
        	    if(null != fbaList && fbaList.size() > 0)
        	    {
        	        saveMessage(request, "已存在启用的经销商帐号！");
                    return showForm(request, response, errors); 
        	    }
        	}
        	fiBillAccount.setBusinessType("2");//全额支付标示2
			fiBillAccountManager.saveFiBillAccount(fiBillAccount);
			
			//Modify By WuCF 20160222 新增限额数据表数据
			JfiQuota jfiQuota = new JfiQuota();
			jfiQuota.setValidityPeriod(sdf.format(new Date()));//期别
			jfiQuota.setAccountId(fiBillAccount.getAccountId());//商户号表主键
			jfiQuota.setStatus("0");//状态默认启用
			jfiQuota.setMaxMoney(fiBillAccount.getMaxMoney());//最大限额
			jfiQuota.setOperateName(loginUser.getUserCode());
			jfiQuota.setOperateTime(new Date());//新增数据
			jfiQuotaManager.saveJfiQuota(jfiQuota);
			
			saveMessage(request, "保存成功！");
		}
		if ("editFiBillAccount".equals(strAction)) {
			//操作人信息
        	SysUser loginSysUser = SessionLogin.getLoginUser(request);
        	fiBillAccount.setCreateTime(new Date());
        	fiBillAccount.setCreateUserCode(loginSysUser.getUserCode());
        	fiBillAccount.setCreateUserName(loginSysUser.getUserName());
        	
        	//查询该经销商是否存在启用数据
            //如果数据为启用
            if("0".equals(fiBillAccount.getStatus()))
            {
                FiBillAccount fba = new FiBillAccount();
                fba.setUserCode(fiBillAccount.getUserCode());
                fba.setStatus("0");
                List<FiBillAccount> fbaList = fiBillAccountManager.getFiBillAccounts(fba);
                if(null != fbaList && fbaList.size() > 0)
                {
                    saveMessage(request, "已存在启用的经销商帐号！");
                    return showForm(request, response, errors); 
                }
            }
            fiBillAccount.setBusinessType("2");//全额支付标示2
			fiBillAccountManager.saveFiBillAccount(fiBillAccount);
			
			//Modify By WuCF 20160222 新增限额数据表数据
			String oldMaxMoney = request.getParameter("oldMaxMoney");
			System.out.println("oldMaxMoney:"+oldMaxMoney);
			if(!oldMaxMoney.equals(String.valueOf(fiBillAccount.getMaxMoney()))){//如果最大限额变化，则修改新增一条记录，然后把以前记录禁用 
				CommonRecord crm = new CommonRecord();
				crm.setValue("validityPeriod",validityPeriod);
				crm.setValue("accountId", fiBillAccount.getAccountId());
				crm.setValue("operatorName",loginUser.getUserCode());
				jfiQuotaManager.updateJfiQuotaStatus(crm);
				
				JfiQuota jfiQuota = new JfiQuota();
				jfiQuota.setValidityPeriod(validityPeriod);//期别
				jfiQuota.setAccountId(fiBillAccount.getAccountId());//商户号表主键
				jfiQuota.setStatus("0");//状态默认启用
				jfiQuota.setMaxMoney(fiBillAccount.getMaxMoney());//最大限额
				jfiQuota.setOperateName(loginUser.getUserCode());
				jfiQuota.setOperateTime(new Date());//新增数据
				jfiQuotaManager.saveJfiQuota(jfiQuota);
				
			}
			

			saveMessage(request, "修改成功！");
		}
		request.setAttribute("strAction", strAction);
        return new ModelAndView(getSuccessView());
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
