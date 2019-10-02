package com.joymain.jecs.pm.webapp.action;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.pm.model.JpmCombinationRelated;
import com.joymain.jecs.pm.service.JpmCombinationRelatedManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JpmCombinationRelatedFormController extends BaseFormController {
	private JpmCombinationRelatedManager jpmCombinationRelatedManager = null;

	public void setJpmCombinationRelatedManager(JpmCombinationRelatedManager jpmCombinationRelatedManager) {
		this.jpmCombinationRelatedManager = jpmCombinationRelatedManager;
	}
	public JpmCombinationRelatedFormController() {
		setCommandName("jpmCombinationRelated");
		setCommandClass(JpmCombinationRelated.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
	throws Exception {
		String uniNo = request.getParameter("uniNo");
		JpmCombinationRelated jpmCombinationRelated = null;

		if (!StringUtils.isEmpty(uniNo)) {
			jpmCombinationRelated = jpmCombinationRelatedManager.getJpmCombinationRelated(uniNo);
		} else {
			jpmCombinationRelated = new JpmCombinationRelated();
		}

		return jpmCombinationRelated;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command,
			BindException errors)
	throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
		SysUser loginUser = SessionLogin.getLoginUser(request);
		JpmCombinationRelated jpmCombinationRelated = (JpmCombinationRelated) command;
		boolean isNew = (jpmCombinationRelated.getUniNo() == null);
		Locale locale = request.getLocale();
		String key=null;
		String strAction = request.getParameter("strAction");
		if ("deleteJpmCombinationRelated".equals(strAction)  ) {
			jpmCombinationRelatedManager.removeJpmCombinationRelated(jpmCombinationRelated.getUniNo().toString());
			key="bdOutWardBank.deleteSuccess";
		}else if("addJpmCombinationRelated".equals(strAction)){
			jpmCombinationRelated.setCreateUserCode(loginUser.getUserCode());
			jpmCombinationRelated.setCreateTime(new Date());
			
			if(!isExisPro(jpmCombinationRelated,"addJpmCombinationRelated")){
				errors.reject("jpmCombinationRelated.isExisPro.error", new Object[] {},LocaleUtil.getLocalText("jpmCombinationRelated.isExisPro.error"));
				return showForm(request, response, errors);
			}
			
			jpmCombinationRelatedManager.saveJpmCombinationRelated(jpmCombinationRelated);
			key="saveOrUpdate.success";
		}else if("editJpmCombinationRelated".equals(strAction)){
			jpmCombinationRelated.setCreateUserCode(loginUser.getUserCode());
			jpmCombinationRelated.setCreateTime(new Date());
			
			if(!isExisPro(jpmCombinationRelated,"editJpmCombinationRelated")){
				errors.reject("jpmCombinationRelated.isExisPro.error", new Object[] {},LocaleUtil.getLocalText("jpmCombinationRelated.isExisPro.error"));
				return showForm(request, response, errors);
			}
			
			jpmCombinationRelatedManager.saveJpmCombinationRelated(jpmCombinationRelated);
			key="saveOrUpdate.success";
		}
		saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(), key));  
		return new ModelAndView(getSuccessView());
	}
	
	/**
	 * @Description 验证是否有重复
	 * @author houxyu
	 * @date 2016-4-12
	 * @param jpmCombinationRelated
	 * @param opType
	 * @return
	 */
	private boolean isExisPro(JpmCombinationRelated jpmCombinationRelated,String opType){
		Boolean flag = true;
		try {
			List<JpmCombinationRelated> jpmCombinationRelatedList = jpmCombinationRelatedManager.getJpmCombinationRelateds(jpmCombinationRelated.getProductNo(), jpmCombinationRelated.getProductDate(), jpmCombinationRelated.getSubProductNo());
			if(!MeteorUtil.isBlankByList(jpmCombinationRelatedList)){
				for(JpmCombinationRelated s : jpmCombinationRelatedList){
					if("addJpmCombinationRelated".equals(opType)){
						flag = false;
						return flag;
					}else{
						if(jpmCombinationRelated.getUniNo() != s.getUniNo()){
							flag = false;
							return flag;
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
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
