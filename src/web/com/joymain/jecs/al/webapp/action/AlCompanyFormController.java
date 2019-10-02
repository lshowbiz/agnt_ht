package com.joymain.jecs.al.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

//import com.joymain.jecs.ai.model.AiAgent;
//import com.joymain.jecs.ai.service.AiAgentManager;
import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCharacterCodingManager;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
//import com.joymain.jecs.pm.service.PmProductSaleManager;
import com.joymain.jecs.sys.model.SysCompanyPow;
import com.joymain.jecs.sys.service.SysCompanyPowManager;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.listener.StartupListener;
import com.joymain.jecs.webapp.util.LocaleUtil;

public class AlCompanyFormController extends BaseFormController {
	private AlCompanyManager alCompanyManager = null;
	private AlCharacterCodingManager alCharacterCodingManager = null;
	private AlCountryManager alCountryManager = null;
//	private AiAgentManager aiAgentManager = null;
	private AlStateProvinceManager alStateProvinceManager = null;
	private SysCompanyPowManager sysCompanyPowManager = null;
//	private PmProductSaleManager pmProductSaleManager=null;

//	public void setPmProductSaleManager(PmProductSaleManager pmProductSaleManager) {
//        this.pmProductSaleManager = pmProductSaleManager;
//    }
//	
    public void setSysCompanyPowManager(SysCompanyPowManager sysCompanyPowManager) {
        this.sysCompanyPowManager = sysCompanyPowManager;
    }
	
	public void setAlStateProvinceManager(AlStateProvinceManager alStateProvinceManager) {
		this.alStateProvinceManager = alStateProvinceManager;
	}
	
//	public void setAiAgentManager(AiAgentManager aiAgentManager) {
//        this.aiAgentManager = aiAgentManager;
//    }

    public void setAlCountryManager(AlCountryManager alCountryManager) {
        this.alCountryManager = alCountryManager;
    }

    public void setAlCharacterCodingManager(AlCharacterCodingManager alCharacterCodingManager) {
        this.alCharacterCodingManager = alCharacterCodingManager;
    }

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public AlCompanyFormController() {
		setCommandName("alCompany");
		setCommandClass(AlCompany.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String companyId = request.getParameter("companyId");
		AlCompany alCompany = null;

		if (!StringUtils.isEmpty(companyId)) {
			alCompany = alCompanyManager.getAlCompany(companyId);
		} else {
			alCompany = new AlCompany();
			
			alCompany.setCountryCode("CN");
			List refCompanys=this.alCompanyManager.getAlCompanysExceptAA();
			request.setAttribute("refCompanys", refCompanys);
		}
		
		List alCharacterCodings = alCharacterCodingManager.getAlCharacterCodings(null);
		request.setAttribute("alCharacterCodings", alCharacterCodings);
		
		List alCountrys = alCountryManager.getAlCountrys(null);
		if(alCountrys!=null){
			for(int i=0;i<alCountrys.size();i++){
				AlCountry alCountry=(AlCountry)alCountrys.get(i);
				alCountry.setCountryName(LocaleUtil.getLocalText(alCountry.getCountryName()));
			}
		}
		request.setAttribute("alCountrys", alCountrys);
		
		List alStateProvinces=new ArrayList();
		if(!StringUtils.isEmpty(alCompany.getCountryCode())){
			alStateProvinces=this.alStateProvinceManager.getAlStateProvincesByCountry(alCompany.getCountryCode());
			request.setAttribute("alStateProvinces", alStateProvinces);
		}

		return alCompany;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		AlCompany alCompany = (AlCompany) command;
		boolean isNew = (alCompany.getCompanyId() == null);

		if ("deleteAlCompany".equalsIgnoreCase(request.getParameter("strAction"))) {
			alCompanyManager.removeAlCompany(alCompany.getCompanyId().toString());
			StartupListener.setupContext(request.getSession().getServletContext(),true);

			saveMessage(request, getText("alCompany.deleted"));
		} else {
			List<SysCompanyPow> sysCompanyPows=new ArrayList<SysCompanyPow>();
			if ("addAlCompany".equalsIgnoreCase(request.getParameter("strAction"))) {
				// 判断是否存在
				AlCompany oldAlCompany = this.alCompanyManager.getAlCompanyByCode(alCompany.getCompanyCode());
				if (oldAlCompany != null) {
					// 存在
					errors.rejectValue("companyCode","error.companyCode.existed");
					return showForm(request, response, errors);
				}
				//判断输入的产品是否可以重消
//				if(!StringUtils.isEmpty(alCompany.getProductNo()) && !pmProductSaleManager.isAutoSell(alCompany.getCompanyCode(),alCompany.getProductNo())){
//					// 产品不能重消
//					errors.rejectValue("productNo","error.autoproduct.invalid");
//					return showForm(request, response, errors);
//				}
				
				String refCompanyCode=request.getParameter("refCompanyCode");
				if(!StringUtils.isEmpty(refCompanyCode)){
					List refSysCompanyPows=this.sysCompanyPowManager.getSysCompanyPowsByCompany(refCompanyCode);
					if(refSysCompanyPows!=null){
						for(int i=0;i<refSysCompanyPows.size();i++){
							SysCompanyPow refSysCompanyPow=(SysCompanyPow)refSysCompanyPows.get(i);
							
							SysCompanyPow sysCompanyPow=new SysCompanyPow();
							sysCompanyPow.setCompanyCode(alCompany.getCompanyCode());
							sysCompanyPow.setSysModule(refSysCompanyPow.getSysModule());
							
							sysCompanyPows.add(sysCompanyPow);
						}
					}
				}
			}
			//判断预设收款代理商是否存在
//			if(!StringUtils.isEmpty(alCompany.getPreAgentCode())){
//				AiAgent aiAgent=this.aiAgentManager.getAiAgent(alCompany.getPreAgentCode());
//				if(aiAgent==null){
//					errors.rejectValue("preAgentCode","warn.notExistAgentNo");
//					return showForm(request, response, errors);
//				}
//			}
			alCompanyManager.saveAlCompany(alCompany);
			if ("addAlCompany".equalsIgnoreCase(request.getParameter("strAction")) && sysCompanyPows!=null && sysCompanyPows.size()>0) {
				this.sysCompanyPowManager.saveSysCompanyPows(sysCompanyPows);
			}
			
			//缺省将所有的权限赋给此公司

			String key = (isNew) ? "alCompany.added" : "alCompany.updated";
			saveMessage(request, getText(key));

			StartupListener.setupContext(request.getSession().getServletContext(),true);
		}
		
		ModelAndView mv=new ModelAndView(getSuccessView());
		mv.addObject("needReload", "1");
		return mv;
	}
}