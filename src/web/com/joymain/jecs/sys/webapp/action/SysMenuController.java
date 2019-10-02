package com.joymain.jecs.sys.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.sys.service.SysModuleManager;
import com.joymain.jecs.webapp.util.SessionLogin;

public class SysMenuController implements Controller {
	private final Log log = LogFactory.getLog(SysMenuController.class);
	private SysModuleManager sysModuleManager = null;

	public void setSysModuleManager(SysModuleManager sysModuleManager) {
		this.sysModuleManager = sysModuleManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String parentId=request.getParameter("parentId");
		List sysModules = this.sysModuleManager.getSysMenus(SessionLogin.getLoginUser(request),null,null);
	
		List arrangeList= arrange(sysModules);
		for (int i = 0; i < arrangeList.size(); i++) {
			Map map=(Map) arrangeList.get(i);
			//System.out.println(map);
		}
		request.setAttribute("sysModules", arrangeList);

		return new ModelAndView("sys/sys_menu");
	}
	
	private List arrange(List sysModules){
		List firstMenuList=this.getByParentId("0", sysModules);
		//System.out.println("firstMenuList读取"+firstMenuList.size());
		for (int i = 0; i < firstMenuList.size(); i++) {
			Map map=(Map) firstMenuList.get(i);
			List menu_list=new ArrayList();
			getTree(menu_list, map, sysModules);
			//System.out.println("读取次数"+i);
			map.put("menu_list", menu_list);
		}
		return firstMenuList;
		
	}
	
	
	private void getTree(List list,Map map,List sysModules){
		String moduleId=map.get("module_id").toString();
		List listTmps=getByParentId(moduleId, sysModules);
		if(!listTmps.isEmpty()){
			for (int i = 0; i < listTmps.size(); i++) {
				Map curMap=(Map) listTmps.get(i);
				list.add(curMap);
				getTree(list, curMap, sysModules);
			}
		}
	}
	
	private List getByParentId(String parentId,List sysModules){
		List list=new ArrayList();
		for (int i = 0; i < sysModules.size(); i++) {
			Map map=(Map) sysModules.get(i);
			if(parentId.equals(map.get("parent_id").toString())){
				list.add(map);
			}
		}
		
		return list;
	}
	
	
}