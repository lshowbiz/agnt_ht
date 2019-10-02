package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.fi.model.FoundationItem;
import com.joymain.jecs.fi.service.FoundationItemManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class FoundationItemFormController extends BaseFormController {
	private FoundationItemManager foundationItemManager = null;

	public void setFoundationItemManager(FoundationItemManager foundationItemManager) {
		this.foundationItemManager = foundationItemManager;
	}
	public FoundationItemFormController() {
		setCommandName("foundationItem");
		setCommandClass(FoundationItem.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
	throws Exception {
		String fiId = request.getParameter("fiId");
		FoundationItem foundationItem = null;

		if (!StringUtils.isEmpty(fiId)) {
			foundationItem = foundationItemManager.getFoundationItem(fiId);
		} else {
			foundationItem = new FoundationItem();
		}

		return foundationItem;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command,
			BindException errors)
	throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		FoundationItem foundationItem = (FoundationItem) command;
		boolean isNew = (foundationItem.getFiId() == null);
		Locale locale = request.getLocale();
		String key=null;
		String strAction = request.getParameter("strAction");
		if ("deleteFoundationItem".equals(strAction)  ) {
			foundationItemManager.removeFoundationItem(foundationItem.getFiId().toString());
			//key="foundationItem.delete";
		}else{
			
			if(("1").equals(foundationItem.getType())){
				
				FoundationItem fItem = foundationItemManager.getFoundationItemByType("1");//查询是否存在365
				if(fItem != null){
					
					saveMessage(request, "已经存在爱心365活动，无需再添加或更改！"); 
		    		
		    		ModelAndView mv=new ModelAndView("redirect:foundationItems.html");
		    		mv.addObject("strAction", "selectFoundationItemList");
		    		return mv;
				}				
			}
			
			foundationItemManager.saveFoundationItem(foundationItem);
			saveMessage(request, "保存成功"); 
		}

		//saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(), key));  
		//return new ModelAndView(getSuccessView());
		
		ModelAndView mv=new ModelAndView("redirect:foundationItems.html");
		mv.addObject("strAction", "selectFoundationItemList");
		//mv.addObject("needReload", "1");
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
