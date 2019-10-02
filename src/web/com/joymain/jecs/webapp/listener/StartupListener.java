package com.joymain.jecs.webapp.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.acegisecurity.providers.AuthenticationProvider;
import org.acegisecurity.providers.ProviderManager;
import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.acegisecurity.providers.rememberme.RememberMeAuthenticationProvider;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCharacterCoding;
import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.model.JalTown;
import com.joymain.jecs.al.service.AlCharacterCodingManager;
import com.joymain.jecs.al.service.AlCharacterValueManager;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.al.service.JalTownManager;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.sys.service.SysConfigValueManager;
import com.joymain.jecs.sys.service.SysListKeyManager;
import com.joymain.jecs.util.bean.ContextUtil;
import com.joymain.jecs.util.bean.WeekFormatUtil;

/**
 * <p>
 * StartupListener class used to initialize and database settings and populate
 * any application-wide drop-downs.
 * 
 * <p>
 * Keep in mind that this listener is executed outside of
 * OpenSessionInViewFilter, so if you're using Hibernate you'll have to
 * explicitly initialize all loaded data at the Dao or service level to avoid
 * LazyInitializationException. Hibernate.initialize() works well for doing
 * this.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class StartupListener extends ContextLoaderListener implements ServletContextListener {

	private static final Log log = LogFactory.getLog(StartupListener.class);

	public void contextInitialized(ServletContextEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("initializing context...");
		}

		// call Spring's context ContextLoaderListener to initialize
		// all the context files specified in web.xml
		super.contextInitialized(event);

		// 初始化ContextUtil
		if (ContextUtil.getContext() == null) {
			ContextUtil c = new ContextUtil();
			c.contextInitialized(event);
		}

		ServletContext context = event.getServletContext();

		// Orion starts Servlets before Listeners, so check if the config
		// object already exists
		Map config = (HashMap) context.getAttribute(Constants.CONFIG);

		if (config == null) {
			config = new HashMap();
		}

		/*
		 * if (context.getInitParameter(Constants.CSS_THEME) != null) {
		 * config.put(Constants.CSS_THEME,
		 * context.getInitParameter(Constants.CSS_THEME)); }
		 */

		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);

		boolean encryptPassword = false;
		try {
			ProviderManager provider = (ProviderManager) ctx.getBean("authenticationManager");
			for (Iterator it = provider.getProviders().iterator(); it.hasNext();) {
				AuthenticationProvider p = (AuthenticationProvider) it.next();
				if (p instanceof RememberMeAuthenticationProvider) {
					config.put("rememberMeEnabled", Boolean.TRUE);
				}
			}

			if (ctx.containsBean("passwordEncoder")) {
				encryptPassword = true;
				config.put(Constants.ENCRYPT_PASSWORD, Boolean.TRUE);
				String algorithm = "SHA";
				if (ctx.getBean("passwordEncoder") instanceof Md5PasswordEncoder) {
					algorithm = "MD5";
				}
				config.put(Constants.ENC_ALGORITHM, algorithm);
			}
		} catch (NoSuchBeanDefinitionException n) {
			// ignore, should only happen when testing
		}

		context.setAttribute(Constants.CONFIG, config);

		// output the retrieved values for the Init and Context Parameters
		if (log.isDebugEnabled()) {
			log.debug("Remember Me Enabled? " + config.get("rememberMeEnabled"));
			log.debug("Encrypt Passwords? " + encryptPassword);
			if (encryptPassword) {
				log.debug("Encryption Algorithm: " + config.get(Constants.ENC_ALGORITHM));
			}
			log.debug("Populating drop-downs...");
		}

		setupContext(context, false);
	}

	public static void setupContext(ServletContext context, Boolean reload) {
		/*
		 * ApplicationContext ctx =
		 * WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		 * 
		 * LookupManager mgr = (LookupManager) ctx.getBean("lookupManager"); //
		 * get list of possible roles
		 * context.setAttribute(Constants.AVAILABLE_ROLES, mgr.getAllRoles());
		 */

		try {
			// 装载初始化内容�
			initContext(context, reload);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		if (log.isDebugEnabled()) {
			log.debug("Drop-down initialization complete [OK]");
		}
		context.setAttribute("companyList", Constants.companyList);
		context.setAttribute("companyHasAAList", Constants.companyHasAAList);
	}

	/**
	 * 初始化系统参数,语言等
	 * 
	 * @param context
	 * @param reload
	 */
	private static void initContext(ServletContext context, boolean reload) {
		if (Constants.isLoad() && !reload) {
			return;
		}

		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		// 装载语言
		loadLanguage(ctx, reload);
		// 装载系统权限 by Asii
		loadSystemPower(context, ctx);
		// 装载系统参数
		loadSystemConfig(ctx, reload);
		loadSystemList(ctx, reload);
		loadComanyList(ctx, reload);
		// 装载期别
		loadBdPeriod(ctx, reload);
		// 装载省、市、区
		loadBdArea(ctx, reload);
		Constants.context=context;
		Constants.setLoad(true);
	}
	
	/**
	 * 装载期别
	 * @param context
	 * @param ctx
	 */
	public static void loadBdArea(ApplicationContext ctx, boolean reload){
		if (Constants.isLoad() && !reload) {
			return;
		}
		AlStateProvinceManager alStateProvinceManager = (AlStateProvinceManager) ctx.getBean("alStateProvinceManager");
		List<AlStateProvince> alStateProvinceList=alStateProvinceManager.getAlStateProvinces(null);
		Constants.alStateProvinceList = alStateProvinceList;

		AlCityManager alCityManager = (AlCityManager) ctx.getBean("alCityManager");
		List<AlCity> alCityList=alCityManager.getAlCitys(new AlCity());
		Constants.alCityList = alCityList;

		AlDistrictManager alDistrictManager = (AlDistrictManager) ctx.getBean("alDistrictManager");
		List<AlDistrict> alDistrictList=alDistrictManager.getAlDistricts(new AlDistrict());
		Constants.alDistrictList = alDistrictList;

		JalTownManager jalTownManager = (JalTownManager) ctx.getBean("jalTownManager");
		List<JalTown> jalTownList= jalTownManager.getJalTowns(new JalTown());
		Constants.jalTownList = jalTownList;
	}
	
	/**
	 * 装载期别
	 * @param context
	 * @param ctx
	 */
	public static void loadBdPeriod(ApplicationContext ctx, boolean reload){
		if (Constants.isLoad() && !reload) {
			return;
		}
		BdPeriodManager bdPeriodManager = (BdPeriodManager) ctx.getBean("bdPeriodManager");
		List<BdPeriod> bdPeriodList=bdPeriodManager.getAllBdPeriods();
		Constants.periodList = bdPeriodList;
		
		try {
			for (BdPeriod period : bdPeriodList) {
				
				Integer wweek=period.getWyear()*100+period.getWweek();
				Integer fweek=period.getFyear()*100+period.getFweek();
				

				Integer wmonth=period.getWyear()*100+period.getWmonth();
				Integer fmonth=period.getFyear()*100+period.getFmonth();
				
				
				WeekFormatUtil.findFweekMap.put(wweek.toString(), fweek.toString());
				WeekFormatUtil.findWweekMap.put(fweek.toString(), wweek.toString());
				
				WeekFormatUtil.findFmonthMap.put(wmonth.toString(), fmonth.toString());
				WeekFormatUtil.findWmonthMap.put(fmonth.toString(), wmonth.toString());
				
				
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		
	}

	/**
	 * 装载系统权限
	 * 
	 * @param ctx
	 */
	public static void loadSystemPower(ServletContext context, ApplicationContext ctx) {
		//TODO 修改
		//SysPowerDao powerDao = (SysPowerDao) ctx.getBean("sysPowerDao");
		//Map permissionMap = powerDao.initPowerPermissionMap();
		//context.setAttribute(AttributeName.APPLICATION_POWERS_BY_URL, permissionMap);
	}

	/**
	 * 装载子公司
	 * 
	 * @param ctx
	 * @param reload
	 */
	private static void loadComanyList(ApplicationContext ctx, boolean reload) {

		if (Constants.isLoad() && !reload) {
			return;
		}
		// Constants.companyMap = new HashMap<String,String>();
		Constants.companyList = new ArrayList();
		Constants.companyHasAAList = new ArrayList();
		AlCompanyManager alCompanyManager = (AlCompanyManager) ctx.getBean("alCompanyManager");
		Constants.companyList = alCompanyManager.getAlCompanysExceptAA();
		Constants.companyHasAAList = alCompanyManager.getAlCompanys(null);
	}

	/**
	 * 装载语言
	 * 
	 * @param ctx
	 * @param reload
	 */
	private static void loadLanguage(ApplicationContext ctx, boolean reload) {
		if (Constants.isLoad() && !reload) {
			return;
		}
		
		Map<String, Map<String, String>> languageTmpMap=new HashMap<String, Map<String, String>>();
		AlCharacterCodingManager alCharacterCodingManager = (AlCharacterCodingManager) ctx.getBean("alCharacterCodingManager");
		AlCharacterValueManager alCharacterValueManager = (AlCharacterValueManager) ctx.getBean("alCharacterValueManager");

		List alCharacterCodings = alCharacterCodingManager.getAlCharacterCodings(null);
		if (alCharacterCodings != null && !alCharacterCodings.isEmpty()) {

			for (int i = 0; i < alCharacterCodings.size(); i++) {
				Map<String, String> languageDetail = new HashMap<String, String>();
				List alCharacterValues = alCharacterValueManager.getAlCharacterValuesByCodeSQL(((AlCharacterCoding) alCharacterCodings.get(i))
						.getCharacterCode());

				if (alCharacterValues != null && !alCharacterValues.isEmpty()) {
					for (int j = 0; j < alCharacterValues.size(); j++) {
						HashMap alCharacterValue = (HashMap) alCharacterValues.get(j);
						languageDetail.put((String) alCharacterValue.get("character_key"), (String) alCharacterValue.get("character_value"));
					}
				}

				languageTmpMap.put(((AlCharacterCoding) alCharacterCodings.get(i)).getCharacterCode(), languageDetail);
				alCharacterValues.clear();
			}
		}
		Constants.localLanguageMap=languageTmpMap;
		alCharacterCodings.clear();
	}

	/**
	 * 装载系统参数
	 * 
	 * @param ctx
	 * @param reload
	 */
	private static void loadSystemConfig(ApplicationContext ctx, boolean reload) {
		if (Constants.isLoad() && !reload) {
			return;
		}
		Map<String, Map<String, String>> sysConfigTmpMap=new HashMap<String, Map<String, String>>();
		Map<String, String> companyTmpMap= new HashMap<String, String>();
		
		AlCompanyManager alCompanyManager = (AlCompanyManager) ctx.getBean("alCompanyManager");
		SysConfigValueManager sysConfigValueManager = (SysConfigValueManager) ctx.getBean("sysConfigValueManager");

		List alCompanys = alCompanyManager.getAlCompanys(null);
		if (alCompanys != null && !alCompanys.isEmpty()) {
			for (int i = 0; i < alCompanys.size(); i++) {
				companyTmpMap.put(((AlCompany) alCompanys.get(i)).getCompanyCode(), ((AlCompany) alCompanys.get(i)).getCompanyName());// 新增分公司列表
				Map<String, String> configDetail = new HashMap<String, String>();
				List sysConfigValues = sysConfigValueManager.getSysConfigValuesByCodeSQL(((AlCompany) alCompanys.get(i)).getCompanyCode());

				if (sysConfigValues != null && !sysConfigValues.isEmpty()) {
					for (int j = 0; j < sysConfigValues.size(); j++) {
						HashMap sysConfigValue = (HashMap) sysConfigValues.get(j);
						String configValue = (String) sysConfigValue.get("config_value");
						if (StringUtils.isEmpty(configValue)) {
							configValue = (String) sysConfigValue.get("default_value");
						}
						configDetail.put((String) sysConfigValue.get("config_code"), configValue);
					}
				}

				sysConfigTmpMap.put(((AlCompany) alCompanys.get(i)).getCompanyCode(), configDetail);
				sysConfigValues.clear();
			}
		}
		Constants.companyMap=companyTmpMap;
		Constants.sysConfigMap=sysConfigTmpMap;
		alCompanys.clear();
	}

	/**
	 * 装载系统参数
	 * 
	 * @param ctx
	 * @param reload
	 */
	private static void loadSystemList(ApplicationContext ctx, boolean reload) {
		if (Constants.isLoad() && !reload) {
			return;
		}
		Map<String, Map<String, String[]>> sysListTmpMap=new HashMap<String, Map<String, String[]>>();
		//Constants.sysListMap = new HashMap<String, Map<String, String[]>>();
		SysListKeyManager sysListKeyManager = (SysListKeyManager) ctx.getBean("sysListKeyManager");

		List sysListKeys = sysListKeyManager.getSysListBySQL();

		for (int i = 0; i < sysListKeys.size(); i++) {
			Map sysListKeyMap=(HashMap)sysListKeys.get(i);
			String listCode=(String)sysListKeyMap.get("list_code");
			String valueCode=(String)sysListKeyMap.get("value_code");
			String valueTitle=(String)sysListKeyMap.get("value_title");
			String exCompanyCode=sysListKeyMap.get("ex_company_code")==null?"":sysListKeyMap.get("ex_company_code").toString();
			
			if(sysListTmpMap.containsKey(sysListKeyMap.get("list_code"))){
				//如果已经存在此键值
				Map<String, String[]> valueMap=sysListTmpMap.get(listCode);
				if(valueMap.containsKey(sysListKeyMap.get(valueCode))){
					//如果已包含,则不处理
					continue;
				}else{
					valueMap.put(valueCode, new String[]{valueTitle,exCompanyCode});
				}
			}else{
				Map<String, String[]> valueMap = new LinkedHashMap<String, String[]>();
				valueMap.put(valueCode, new String[]{valueTitle,exCompanyCode});
				
				sysListTmpMap.put(listCode, valueMap);
			}
		}
		Constants.sysListMap=sysListTmpMap;
		sysListKeys.clear();
	}
}