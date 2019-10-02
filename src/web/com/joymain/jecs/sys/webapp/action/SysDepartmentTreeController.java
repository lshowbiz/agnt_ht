package com.joymain.jecs.sys.webapp.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.sys.service.SysDepartmentManager;
import com.joymain.jecs.webapp.util.SessionLogin;

public class SysDepartmentTreeController implements Controller {
	private final Log log = LogFactory.getLog(SysDepartmentTreeController.class);

	private SysDepartmentManager sysDepartmentManager;
	private AlCompanyManager alCompanyManager = null;

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public void setSysDepartmentManager(SysDepartmentManager sysDepartmentManager) {
		this.sysDepartmentManager = sysDepartmentManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		Map<AlCompany, List<?>> treeMap=new LinkedHashMap<AlCompany, List<?>>();
		//List alCompanys = this.alCompanyManager.getAlCompanys(null);
		//读取所管理的人员所处公司
		List alCompanys= this.alCompanyManager.getMyAlCompanys(SessionLogin.getLoginUser(request),SessionLogin.getLoginUser(request).getCompanyCode(),false);
		if(alCompanys!=null && alCompanys.size()>0){
			for(int i=0;i<alCompanys.size();i++){
				AlCompany alCompany=(AlCompany)alCompanys.get(i);
				List sysDepartments = sysDepartmentManager.getSysDepartmentsByCompany(alCompany.getCompanyCode());
				treeMap.put((AlCompany)alCompanys.get(i), sysDepartments);
			}
		}
		
		return new ModelAndView("sys/sys_department_tree", "treeMap", treeMap);

	}
}