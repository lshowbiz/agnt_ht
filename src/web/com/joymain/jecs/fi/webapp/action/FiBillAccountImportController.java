package com.joymain.jecs.fi.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.io.InputStream;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.fi.model.FiBillAccount;
import com.joymain.jecs.fi.service.FiBillAccountManager;

import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
/**
 * 经销商收款商户号导入
 * @author Administrator
 *
 */
public class FiBillAccountImportController extends BaseFormController {
    private FiBillAccountManager fiBillAccountManager = null;
    private SysUserManager sysUserManager = null;
	
	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}
    public void setFiBillAccountManager(FiBillAccountManager fiBillAccountManager) {
        this.fiBillAccountManager = fiBillAccountManager;
    }
    public FiBillAccountImportController() {
        setCommandName("fiBillAccount");
        setCommandClass(FiBillAccount.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        
        return new FiBillAccount();
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
    	
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        
        Long limitNum = new Long(ConfigUtil.getConfigValue(SessionLogin.getLoginUser(request).getCompanyCode(), "bankbook_temp_import_limit"));
        try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("xlsFile");
			//retrieve the file data
			InputStream stream = file.getInputStream();

			ExcelUtil eu = new ExcelUtil();
			//获取可读的工作表对象，定位到要读取的excel文件
			Workbook workbook = eu.getWorkbook(stream);
			//读取此文件的第一个工作表，工作表序号从0开始。
			Sheet sheet = workbook.getSheet(0);
			
			List<FiBillAccount> fiBillAccounts = new ArrayList<FiBillAccount>();
			for (int i = 1; i < sheet.getRows(); i++) {
				
				//适用类型、平台类型、商户号、经销商编号、注册名称、联系方式、邮箱、备注
				String accountType = eu.getContents(sheet, 0, i);
				if (!StringUtils.isEmpty(accountType)) {
					accountType = StringUtils.trim(accountType);
				}
				
				String providerType = eu.getContents(sheet, 1, i);
				if (!StringUtils.isEmpty(providerType)) {
					providerType = StringUtils.trim(providerType);
				}
				
				String billAccountCode = eu.getContents(sheet, 2, i);
				if (!StringUtils.isEmpty(billAccountCode)) {
					billAccountCode = StringUtils.trim(billAccountCode);
				}
				
				String userCode = eu.getContents(sheet, 3, i);
				if (!StringUtils.isEmpty(userCode)) {
					userCode = StringUtils.trim(userCode);
				}

				String accountName = eu.getContents(sheet, 4, i);
				if (!StringUtils.isEmpty(accountName)) {
					accountName = StringUtils.trim(accountName);
				}
				
				String linkNum = null;
				if(sheet.getColumns() >= 6)
				{
				    linkNum = eu.getContents(sheet, 5, i);
	                if (!StringUtils.isEmpty(linkNum)) {
	                    linkNum = StringUtils.trim(linkNum);
	                }
				}

				String registerEmail = null;
				if(sheet.getColumns() >= 7)
                {
    				registerEmail = eu.getContents(sheet, 6, i);
    				if (!StringUtils.isEmpty(registerEmail)) {
    					registerEmail = StringUtils.trim(registerEmail);
    				}
                }

				String remark = null;
				if(sheet.getColumns() >= 8)
                {
    				remark = eu.getContents(sheet, 7, i);
    				if (!StringUtils.isEmpty(remark)) {
    					remark = StringUtils.trim(remark);
    				}
                }

				if(StringUtil.isEmpty(billAccountCode)){
					throw new Exception("第"+ (i+1) +"行商户号不能为空!");
				}
				if(StringUtil.isEmpty(userCode)){
					throw new Exception("第"+ (i+1) +"行经销商编号不能为空!");
				}
				if(StringUtil.isEmpty(accountType)){
					throw new Exception("第"+ (i+1) +"行适用类型不能为空!");
				}
				
				SysUser sysUser = this.sysUserManager.getSysUser(userCode);
				if (sysUser == null) {
					throw new Exception("第"+ (i+1) +"行经销商编号不存在!");
				}
				
				//验证是否重复
				List list = fiBillAccountManager.getFiBillAccountsByUserCode(billAccountCode, userCode);
				
				for(FiBillAccount fba : fiBillAccounts)
				{
				    if(userCode.equals(fba.getUserCode()))
				    {
				        if(billAccountCode.equals(fba.getBillAccountCode()))
				        {
				            throw new Exception("第"+ (i+1) +"行经销商对应的商户号在文件中重复!");
				        }
				    }
				}
				if(list.size() > 0){
					
					throw new Exception("第"+ (i+1) +"行经销商对应的商户号在系统中已经存在!");
				}
				
				try {
					FiBillAccount fiBillAccount = new FiBillAccount();
					fiBillAccount.setAccountType(accountType);
					fiBillAccount.setProviderType(providerType);
					fiBillAccount.setUserCode(userCode);
					fiBillAccount.setBillAccountCode(billAccountCode);
					fiBillAccount.setAccountName(accountName);
					fiBillAccount.setRegisterEmail(registerEmail);
					fiBillAccount.setRemark(remark);
					fiBillAccount.setLinkNum(linkNum);
					fiBillAccount.setStatus("1");
					
					fiBillAccount.setCreateUserCode(SessionLogin.getOperatorUser(request).getUserCode());
					fiBillAccount.setCreateUserName(SessionLogin.getOperatorUser(request).getUserName());
					fiBillAccount.setCreateTime(new Date());
					
					fiBillAccounts.add(fiBillAccount);
				} catch (Exception ex) {
					log.error("记录: " + i);
				}
			}	
			eu.closeWorkbook(workbook);

			if (fiBillAccounts != null && fiBillAccounts.size() > 0) {
				if (fiBillAccounts.size() > limitNum) {
					saveMessage(request, "导入失败，导入的记录超过制定的额度" + ":" + limitNum.toString());
				} else {
					this.fiBillAccountManager.saveFiBillAccounts(fiBillAccounts);
					saveMessage(request, "导入成功！");
				}
			} else {
				saveMessage(request, "导入的文件数据内容为空！");
			}
			
		} catch (Exception ex) {
			saveMessage(request, getText("dataImport.unSuc")+": "+ex.getMessage());
			log.error("xls文件导入失败!", ex);
		}
      
		ModelAndView mv = new ModelAndView(getSuccessView());
		mv.addObject("needReload", "1");
		return mv;
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
