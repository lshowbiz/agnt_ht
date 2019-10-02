package com.joymain.jecs.am.webapp.action;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.am.model.JamMsnModule;
import com.joymain.jecs.am.model.JamMsnType;
import com.joymain.jecs.am.service.JamMsnModuleManager;
import com.joymain.jecs.am.service.JamMsnTypeManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JamMsnModuleFormController extends BaseFormController {
    private JamMsnModuleManager jamMsnModuleManager = null;
    private JamMsnTypeManager jamMsnTypeManager;
    public void setJamMsnTypeManager(JamMsnTypeManager jamMsnTypeManager) {
		this.jamMsnTypeManager = jamMsnTypeManager;
	}
	public void setJamMsnModuleManager(JamMsnModuleManager jamMsnModuleManager) {
        this.jamMsnModuleManager = jamMsnModuleManager;
    }
    public JamMsnModuleFormController() {
        setCommandName("jamMsnModule");
        setCommandClass(JamMsnModule.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String jmmNo = request.getParameter("jmmNo");
        JamMsnModule jamMsnModule = null;
        String mtId=request.getParameter("mtId");
        JamMsnType jamMsnType=jamMsnTypeManager.getJamMsnType(mtId);

        if (!StringUtils.isEmpty(jmmNo)) {
            jamMsnModule = jamMsnModuleManager.getJamMsnModule(jmmNo);
        } else {
            jamMsnModule = new JamMsnModule();
            jamMsnModule.setJamMsnType(jamMsnType);
        }
        request.setAttribute("mtId", jamMsnModule.getJamMsnType().getMtId());

		String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
		Map cardTypeMap=ListUtil.getListOptions(companyCode, "msnmodule.type");
		List<JamMsnModule> mmTypeSet=jamMsnModuleManager.getJamMsnModuleByMtId(jamMsnModule.getJamMsnType().getMtId());

		String strAction = request.getParameter("strAction");
		if("addJamMsnModule".equals(strAction)){
			Iterator ite = cardTypeMap.keySet().iterator();
			if(ite.hasNext()){
				for (JamMsnModule module : mmTypeSet) {
					if(cardTypeMap.get(module.getMmType())!=null){
						cardTypeMap.remove(module.getMmType());
					}
					
				}
			}
		}
		
		request.setAttribute("mmTypeSet", cardTypeMap);

		
        return jamMsnModule;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JamMsnModule jamMsnModule = (JamMsnModule) command;
        boolean isNew = (jamMsnModule.getJmmNo() == null);
        Locale locale = request.getLocale();
		String key=null;
		String strAction = request.getParameter("strAction");
		

		String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
		Map cardTypeMap=ListUtil.getListOptions(companyCode, "msnmodule.type");
		
		
		if ("deleteJamMsnModule".equals(strAction)  ) {
			jamMsnModuleManager.removeJamMsnModule(jamMsnModule.getJmmNo().toString());
		}else{
			if(StringUtil.isEmpty(jamMsnModule.getMmKey())){
				errors.rejectValue("mmKey", "isNotNull",new Object[] { this.getText("bdOutwardBank.bankCode") }, "");
				return showForm(request, response, errors);
			}
			if(StringUtil.isEmpty(jamMsnModule.getTitile())){
				errors.rejectValue("titile", "isNotNull",new Object[] { this.getText("alCharacterType.typeName") }, "");
				return showForm(request, response, errors);
			}
			if(StringUtil.isEmpty(jamMsnModule.getContent())){
				errors.rejectValue("content", "isNotNull",new Object[] { this.getText("amAnnounce.content") }, "");
				return showForm(request, response, errors);
			}
			if(StringUtil.isEmpty(jamMsnModule.getMmType())){
				errors.rejectValue("mmType", "isNotNull",new Object[] { this.getText("jamMsnModule.mmType") }, "");
				return showForm(request, response, errors);
			}
			if("addJamMsnModule".equals(strAction) ){
				JamMsnModule searchJamMsnModule=new JamMsnModule();
				searchJamMsnModule.setMmKey(jamMsnModule.getMmKey());
				List list=jamMsnModuleManager.getJamMsnModules(searchJamMsnModule);
				if(list.size()>0){
					this.saveMessage(request, LocaleUtil.getLocalText("code.exist"));
					return showForm(request, response, errors);
				}
			}
			
			jamMsnModuleManager.saveJamMsnModule(jamMsnModule);
		}
		this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));

        return new ModelAndView("redirect:jamMsnModules.html?mtId="+jamMsnModule.getJamMsnType().getMtId());
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
