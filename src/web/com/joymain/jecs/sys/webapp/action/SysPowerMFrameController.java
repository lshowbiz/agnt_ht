package com.joymain.jecs.sys.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.sys.service.SysManagerModlPowManager;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class SysPowerMFrameController implements Controller {
	private final Log log = LogFactory.getLog(SysPowerMFrameController.class);
	private SysManagerModlPowManager sysManagerModlPowManager = null;
	private AlCompanyManager alCompanyManager = null;

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public void setSysManagerModlPowManager(SysManagerModlPowManager sysManagerModlPowManager) {
		this.sysManagerModlPowManager = sysManagerModlPowManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		//Modify By WuCF 20140507 仓库权限批量分配 addPdWarehouseUser
		if("addPdWarehouseUser".equals(request.getParameter("strAction"))){
			String selectedUserCode = request.getParameter("selectedUserCode");//所选择的可管理人员 格式: M#code1,M#code2,M#code3
			String warehouseNo = request.getParameter("pdWarehouseId");
			String companyCode = request.getParameter("companyCode");
			/*---- 保存用户权限 ---- */
			this.sysManagerModlPowManager.savePdWarehousePowsByModule(SessionLogin.getLoginUser(request), selectedUserCode,
					warehouseNo, companyCode);
			MessageUtil.saveMessage(request, LocaleUtil.getLocalText("sysModuel.setting.sucess"));
			ModelAndView mv = new ModelAndView("redirect:sys_my_man_m_tree.html");
			mv.addObject("companyCode", companyCode);
			mv.addObject("warehouseNo", warehouseNo);
			mv.addObject("strAction", "pdWarehouseUserTree");
			return mv;
		}else if("addPdWarehouseUser2".equals(request.getParameter("strAction"))){//EC需求优化5、仓库权限新增以员工账号为单位勾选仓库信息
			String selectedWarehouseCode = request.getParameter("selectedWarehouseCode");//所选择的可管理仓库格式: code1,code2,code3。。。
			String userCode = request.getParameter("userCode");
			String companyCode = request.getParameter("companyCode");
			/*---- 保存用户仓库权限 ---- */
			this.sysManagerModlPowManager.savePdWarehousePowsByUser(userCode, selectedWarehouseCode);
			MessageUtil.saveMessage(request, LocaleUtil.getLocalText("sysModuel.setting.sucess"));
			ModelAndView mv = new ModelAndView("redirect:pdWarehouses.html");
			mv.addObject("companyCode", companyCode);
			mv.addObject("userCode", userCode);
			mv.addObject("strAction", "pdWarehouseTree2");
			return mv;
		}else{		
			if ("post".equalsIgnoreCase(request.getMethod()) && "sysPowerMFrm".equals(request.getParameter("strAction"))) {
				String selectedUserCode = request.getParameter("selectedUserCode");//所选择的可管理人员 格式: M#code1,M#code2,M#code3
				String moduleId = request.getParameter("moduleId");
				String companyCode = request.getParameter("companyCode");
				/*---- 保存用户权限 ---- */
				this.sysManagerModlPowManager.saveSysManagerModlPowsByModule(SessionLogin.getLoginUser(request), selectedUserCode,
				        moduleId, companyCode);
	
				MessageUtil.saveMessage(request, LocaleUtil.getLocalText("sysModuel.setting.sucess"));
				ModelAndView mv = new ModelAndView("redirect:sys_power_m_frm.html");
				mv.addObject("companyCode", companyCode);
				mv.addObject("moduleId", moduleId);
				return mv;
			}
			//读取所管理的人员所处公司
			List alCompanys = this.alCompanyManager.getMyAlCompanys(SessionLogin.getLoginUser(request), null, false);
			request.setAttribute("alCompanys", alCompanys);
	
			if (!StringUtils.isEmpty(request.getParameter("companyCode"))) {
				request.setAttribute("companyCode", request.getParameter("companyCode"));
			} else {
				request.setAttribute("companyCode", SessionLogin.getLoginUser(request).getCompanyCode());
			}
	
			return new ModelAndView("sys/sys_power_m_frm");
		}
	}
}