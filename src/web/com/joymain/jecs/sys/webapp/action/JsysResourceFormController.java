package com.joymain.jecs.sys.webapp.action;

import java.util.List;
import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.sys.model.JsysResource;
import com.joymain.jecs.sys.model.SysModule;
import com.joymain.jecs.sys.service.JsysResourceManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JsysResourceFormController extends BaseFormController {
	private JsysResourceManager jsysResourceManager = null;

	public void setJsysResourceManager(JsysResourceManager jsysResourceManager) {
		this.jsysResourceManager = jsysResourceManager;
	}

	public JsysResourceFormController() {
		setCommandName("jsysResource");
		setCommandClass(JsysResource.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		
		JsysResource jsysResource = null;
		JsysResource parentResource = null;
		
//		if (!StringUtils.isEmpty(resId)) {
//			jsysResource = jsysResourceManager.getJsysResource(resId);
//		} else {
//			jsysResource = new JsysResource();
//		}

		if ("addJsysResource".equals(request.getParameter("strAction"))) {
			jsysResource = new JsysResource();
			String parentId = request.getParameter("parentId");
			if (!StringUtils.isEmpty(parentId)) {
				
				parentResource = jsysResourceManager.getJsysResource(parentId);
				jsysResource.setParentId(new Long(parentId));
			} else {
				jsysResource.setParentId(new Long(0));
			}
		} else if ("editJsysResource".equals(request.getParameter("strAction")) || "deleteJsysResource".equals(request.getParameter("strAction"))) {
			
			String resId = request.getParameter("resId");
			jsysResource = jsysResourceManager.getJsysResource(resId);
			parentResource = this.jsysResourceManager.getJsysResource(jsysResource.getParentId().toString());

		}
		
		if(parentResource==null){
			parentResource=new JsysResource();
			parentResource.setResName("node.root.name");
		}
		request.setAttribute("parentSysResource", parentResource);
		
		return jsysResource;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
		
		JsysResource jsysResource = (JsysResource) command;

		//boolean isNew = (jsysResource.getResId() == null);
		//Locale locale = request.getLocale();
		String key = null;
		String strAction = request.getParameter("strAction");
		//判断
		if ("deleteJsysResource".equals(strAction)) {
			
			List childJsysResources = this.jsysResourceManager.getDirectChildJsysResources(jsysResource.getResId().toString(), "orderNo");
			if (childJsysResources != null && !childJsysResources.isEmpty()) {
				errors.reject("jsysResource.error.child.exists");
				return showForm(request, response, errors);
			}
			//删除
			jsysResourceManager.removeJsysResource(jsysResource.getResId().toString());
			
			//重整资源树
			this.jsysResourceManager.saveJsysResourcesRebuild();
			//key = "jsysResource.delete";
			saveMessage(request, getText("jsysResource.delete"));
		} else {
			
			key = "jsysResource.update";
			//新增
			if ("addJsysResource".equals(strAction)) {
				key = "jsysResource.added";
				//验证是否存在
				
			}else if ("editJsysResource".equals(strAction)) {
				
				//验证是否存在
				
				//验证所选择的父模块是否是自己的子模块
			}
			jsysResourceManager.saveJsysResource(jsysResource);
			
			
			//重整资源树
			this.jsysResourceManager.saveJsysResourcesRebuild();
			
			//执行结果提示
			saveMessage(request, getText(key));
		}
		
		//return new ModelAndView(getSuccessView());
		ModelAndView mv = new ModelAndView(getSuccessView());
		mv.addObject("parentId", request.getParameter("parentId"));
		return mv;
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		// binder.setAllowedFields(allowedFields);
		// binder.setDisallowedFields(disallowedFields);
		// binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
