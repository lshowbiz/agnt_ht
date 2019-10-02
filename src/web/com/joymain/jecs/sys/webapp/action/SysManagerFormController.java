package com.joymain.jecs.sys.webapp.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.service.AlCharacterCodingManager;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
//import com.joymain.jecs.fi.model.FiBankbookBalance;
//import com.joymain.jecs.fi.service.FiBankbookBalanceManager;
import com.joymain.jecs.sys.model.SysDepartment;
import com.joymain.jecs.sys.model.SysManager;
import com.joymain.jecs.sys.model.SysManagerUser;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.model.SysUserIp;
import com.joymain.jecs.sys.service.SysDepartmentManager;
import com.joymain.jecs.sys.service.SysManagerManager;
import com.joymain.jecs.sys.service.SysManagerUserManager;
import com.joymain.jecs.sys.service.SysUserIpManager;
import com.joymain.jecs.util.bean.PasswordGenerator;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class SysManagerFormController extends BaseFormController {
	private SysManagerManager sysManagerManager = null;
	private SysDepartmentManager sysDepartmentManager = null;
	private AlCharacterCodingManager alCharacterCodingManager = null;
	private AlCompanyManager alCompanyManager = null;
	private AlCountryManager alCountryManager = null;
	private SysManagerUserManager sysManagerUserManager = null;
	private SysUserIpManager sysUserIpManager=null;
//	private FiBankbookBalanceManager fiBankbookBalanceManager=null;
//	
//	public void setFiBankbookBalanceManager(FiBankbookBalanceManager fiBankbookBalanceManager) {
//		this.fiBankbookBalanceManager = fiBankbookBalanceManager;
//	}
	
	public void setSysUserIpManager(SysUserIpManager sysUserIpManager) {
		this.sysUserIpManager = sysUserIpManager;
	}

    public void setSysManagerUserManager(SysManagerUserManager sysManagerUserManager) {
        this.sysManagerUserManager = sysManagerUserManager;
    }

	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public void setAlCharacterCodingManager(AlCharacterCodingManager alCharacterCodingManager) {
		this.alCharacterCodingManager = alCharacterCodingManager;
	}

	public void setSysDepartmentManager(SysDepartmentManager sysDepartmentManager) {
		this.sysDepartmentManager = sysDepartmentManager;
	}

	public void setSysManagerManager(SysManagerManager sysManagerManager) {
		this.sysManagerManager = sysManagerManager;
	}

	public SysManagerFormController() {
		setCommandName("sysManager");
		setCommandClass(SysManager.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		List alCharacterCodings = this.alCharacterCodingManager.getAlCharacterCodings(null);
		request.setAttribute("alCharacterCodings", alCharacterCodings);

		SysManager sysManager = null;
		if ("addSysManager".equals(request.getParameter("strAction"))) {
			String companyCode = request.getParameter("companyCode");
			String departmentId = request.getParameter("departmentId");
			/*if (StringUtils.isEmpty(companyCode) || StringUtils.isEmpty(departmentId)) {
				throw new AppException("缺少参数");
			}*/
			String password = PasswordGenerator.generateCode(8);//新增默认密码
			
			sysManager = new SysManager();
			sysManager.setCompanyCode(companyCode);
			if(!StringUtils.isEmpty(departmentId)){
				sysManager.setDepartmentId(new Long(departmentId));
			}

			SysUser sysUser = new SysUser();
			sysUser.setCompanyCode(companyCode);
			if(!StringUtils.isEmpty(companyCode)){
				AlCompany alCompany = this.alCompanyManager.getAlCompanyByCode(companyCode);
				request.setAttribute("alCompany", alCompany);
				
				sysUser.setDefCharacterCoding(alCompany.getCharacterCode());
			}
			sysUser.setPassword(password);
			sysUser.setPassword2(password);
			sysManager.setSysUser(sysUser);
		} else {
			String userCode = request.getParameter("userCode");
			sysManager = sysManagerManager.getSysManager(userCode);

			AlCompany alCompany = this.alCompanyManager.getAlCompanyByCode(sysManager.getCompanyCode());
			request.setAttribute("alCompany", alCompany);
			
			List sysUserIps=this.sysUserIpManager.getSysUserIpsByUser(userCode);
			request.setAttribute("sysUserIps", sysUserIps);
		}
		
		if(sysManager.getDepartmentId()!=null){
			SysDepartment sysDepartment = this.sysDepartmentManager.getSysDepartment(sysManager.getDepartmentId().toString());
			request.setAttribute("sysDepartment", sysDepartment);
		}
		
		List alCountrys=this.alCountryManager.getAlCountrys(null);
		request.setAttribute("alCountrys", alCountrys);

		return sysManager;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		SysManager sysManager = (SysManager) command;

		if ("deleteSysManager".equals(request.getParameter("strAction"))) {
			sysManagerManager.removeSysManager(sysManager.getUserCode().toString());

			saveMessage(request, getText("sysManager.deleted"));
		} else {
			String key = "sysManager.updated";
			if ("addSysManager".equals(request.getParameter("strAction"))) {
				key = "sysManager.added";

				//验证是否存在
				try {
					SysManager oldSysManager = this.sysManagerManager.getSysManager(sysManager.getUserCode());
					if (oldSysManager != null) {
						// 存在
						errors.rejectValue("userCode", "sysManager.userCode.exists");
						return showForm(request, response, errors);
					}
				} catch (ObjectRetrievalFailureException ex) {
					//不存在
					String md5Password = StringUtil.encodePassword(sysManager.getSysUser().getPassword(), "md5");
					sysManager.getSysUser().setPassword(md5Password);
					sysManager.getSysUser().setPassword2(md5Password);
					sysManager.getSysUser().setUserCode(sysManager.getUserCode());
				}
			} else if ("editSysManager".equals(request.getParameter("strAction"))) {
			}
			
			if("1".equals(request.getParameter("ipCheck"))){
				sysManager.getSysUser().setIpCheck(new Integer(1));
			}else{
				sysManager.getSysUser().setIpCheck(new Integer(0));
			}
			sysManager.getSysUser().setCompanyCode(sysManager.getCompanyCode());
			sysManager.getSysUser().setUserType("C");
			if(sysManager.getDepOrder()==null){
				sysManager.setDepOrder(new Long(0));
			}

			sysManagerManager.saveSysManager(sysManager);
			//增加存折余额记录
//			FiBankbookBalance fiBankbookBalance=this.fiBankbookBalanceManager.getFiBankbookBalance(sysManager.getUserCode());
//			if(fiBankbookBalance==null){
//				fiBankbookBalance=new FiBankbookBalance();
//				fiBankbookBalance.setUserCode(sysManager.getUserCode());
//				fiBankbookBalance.setBalance(new BigDecimal(0));
//				this.fiBankbookBalanceManager.saveFiBankbookBalance(fiBankbookBalance);
//			}
			//增加当前操作者对被增加帐号的权限控制
			if ("addSysManager".equals(request.getParameter("strAction"))) {
				SysManagerUser sysManagerUser=new SysManagerUser();
				sysManagerUser.setMasterUserCode(SessionLogin.getLoginUser(request).getUserCode());
				sysManagerUser.setSlaveUserCode(sysManager.getUserCode());
				
				this.sysManagerUserManager.saveSysManagerUser(sysManagerUser);
				//获取所有对当前用户有控制权限的用户, 并且将当前所增加的用户的控制权增加给这些用户
				List sysManagerUsers=this.sysManagerUserManager.getSysManagerUsersBySlave(SessionLogin.getLoginUser(request).getUserCode());
				if(sysManagerUsers!=null && sysManagerUsers.size()>0){
					for(int i=0;i<sysManagerUsers.size();i++){
						SysManagerUser slaveSysManagerUser=(SysManagerUser)sysManagerUsers.get(i);
						
						SysManagerUser newSysManagerUser=new SysManagerUser();
						newSysManagerUser.setMasterUserCode(slaveSysManagerUser.getMasterUserCode());
						newSysManagerUser.setSlaveUserCode(sysManager.getUserCode());
						
						this.sysManagerUserManager.saveSysManagerUser(newSysManagerUser);
					}
				}
			}
			//增加IP过滤列表
			String allowedIpStr=request.getParameter("allowedIpStr");
			if(!StringUtils.isEmpty(allowedIpStr)){
				List<SysUserIp> sysUserIps=new ArrayList<SysUserIp>();
				String[] allowedIps=allowedIpStr.split(",");
				this.sysUserIpManager.removeSysUserIpsNotIn(sysManager.getUserCode(), allowedIps);
				for(int i=0;i<allowedIps.length;i++){
					SysUserIp sysUserIp=this.sysUserIpManager.getSysUserIp(sysManager.getUserCode(), allowedIps[i]);
					if(sysUserIp==null){
						sysUserIp=new SysUserIp();
						
						sysUserIp.setUserCode(sysManager.getUserCode());
						sysUserIp.setIpAddress(allowedIps[i]);
						
						sysUserIps.add(sysUserIp);
					}
				}
				this.sysUserIpManager.saveSysUserIps(sysUserIps);
			}else{
				this.sysUserIpManager.removeSysUserIpsNotIn(sysManager.getUserCode(), null);
			}

			saveMessage(request, getText(key));
		}

		String modifyType = request.getParameter("modifyType");
		if("account".equalsIgnoreCase(modifyType)){
			ModelAndView mv = new ModelAndView("redirect:sysAccounts.html");
			mv.addObject("needReload", "1");
			return mv;
		}else{
			ModelAndView mv=new ModelAndView(getSuccessView());
			mv.addObject("companyCode", request.getParameter("companyCode"));
			mv.addObject("departmentId", request.getParameter("departmentId"));
			mv.addObject("needReload", "1");
			return mv;
		}
	}
}