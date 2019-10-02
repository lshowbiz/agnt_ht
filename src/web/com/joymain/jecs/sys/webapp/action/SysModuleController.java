package com.joymain.jecs.sys.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.sys.model.SysModule;
import com.joymain.jecs.sys.service.SysModuleManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;

public class SysModuleController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(SysModuleController.class);
	private SysModuleManager sysModuleManager = null;

	public void setSysModuleManager(SysModuleManager sysModuleManager) {
		this.sysModuleManager = sysModuleManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		String parentId=request.getParameter("parentId");
		SysModule parentSysModule=null;
		if(!StringUtils.isEmpty(parentId)){
			parentSysModule = this.sysModuleManager.getSysModule(parentId);
		}
		if(parentSysModule==null){
			parentSysModule=new SysModule();
			parentSysModule.setModuleName("node.root.name");
		}
		request.setAttribute("parentSysModule", parentSysModule);

		CommonRecord crm = RequestUtil.toCommonRecord(request);
		Pager pager = new Pager("sysModuleListTable", request, 20);
		List sysModules = sysModuleManager.getSysModulesByCrm(crm, pager);
		request.setAttribute("sysModuleListTable_totalRows", pager.getTotalObjects());

		return new ModelAndView("sys/sysModuleList", Constants.SYSMODULE_LIST, sysModules);
	}
}
