package com.joymain.jecs;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Constant values used throughout the application.
 *
 * <p>
 * <a href="Constants.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@SuppressWarnings("rawtypes")
public class Constants {
    //~ Static fields/initializers =============================================
	public static final String FIRST_PURCHASE = "1";
	public static final String MAINTAIN_PURCHASE="4";
	public static final String UPDATE_PURCHASE = "2";
	/**
	 * 3 ：续约单
	 */
	public static final String AUTO_PURCHASE = "3";
	/**
	 * 32:补单
	 */
	public static final String B_PURCHASE="32"; 
	
	public static final String STORE_FIRST_PURCHASE = "6";
	public static final String STORE_MAINTAIN_PURCHASE="9";
	public static final String STORE_UPDATE_PURCHASE = "S3";
	
	public static final String SUBSTORE_FIRST_PURCHASE = "11";
	public static final String SUBSTORE_MAINTAIN_PURCHASE="14";
	public static final String SUBSTORE_UPDATE_PURCHASE = "12";
	public static final String SPECIAL_PURCHASE = "6";//暂时和一级店铺首购一�?
	
	public static final String DREAM_FIRST_PURCHASE = "11";
	public static final String DREAM_MAINTAIN_PURCHASE="14";
	public static final String DREAM_UPDATE_PURCHASE = "12";
	
	public static final String PONT_EXCHANGE_PURCHASE = "PE";
	
	public static final String NOPV_PROCDUCT = "5";
	
	public static Map<String, String> creditCardUniqueCode = Collections.synchronizedMap(new HashMap<String, String>());
	
	public static final String ENCRYPTOR_KEY="8u7fd@3*7dfr@#$%VFTO";
	/**
	 * 普通会员角色*/
	public static final String JOCS_ROLE_NORMAL="jocs.member.role.normal";
	/**
	 * 优惠顾客*/
	public static final String JOCS_ROLE5="jocs.member.role.5";
	/**
	 * 一级会员 */
	public static final String JOCS_ROLE10="jocs.member.role.10";
	public static final String JOCS_ROLE20="jocs.member.role.20";
	public static final String JOCS_ROLE30="jocs.member.role.30";
	public static final String JOCS_ROLE40="jocs.member.role.40";
	public static final String JOCS_ROLE50="jocs.member.role.50";
	public static final String JOCS_ROLE60="jocs.member.role.60";
	public static final String JOCS_ROLE70="jocs.member.role.70";
	/**
	 *待审会员*/
	public static final String JOCS_ROLE0="jocs.member.role.0";
	/**
	 *已审二级店铺*/
	public static final String JOCS_STORE2_X="jocs.member.role.store2.x";
	/**
	 *正式二级店铺*/
	public static final String JOCS_STORE2="jocs.member.role.store2";
	/**
	 * 正式一级店铺*/
	public static final String JOCS_STORE1="jocs.member.role.store1";
	/**
	 * 已审一级店铺*/
	public static final String JOCS_STORE1_X="jocs.member.role.store1.x";
	/**
	 * JOCS已审一级店铺（二升一）*/
	public static final String JOCS_STORE21 ="jocs.member.role.store21.x";
	/**
	 * 冻结状态： 1冻结
	 */
	public static final int FREEZE_STATUS_ONE = 1;
	/**
	 * 冻结状态： 0 未冻结
	 */
	public static final int FREEZE_STATUS_ZERO = 0;
	public static final String JECS_BAatCK_SERVER_HOST="jecs_back_server_host";
	public static final String JECS_BACK_SERVER_PORT="jecs_back_server_port";

	
	public static Boolean TEST_FLAG=false;
	public static  ServletContext context;
	public static final Boolean AUTHORITY_IGNORE = false;
	
	public static final String PERMISSION_DENIED_FLAG = "permissionDeniedFlag";

	
	public static final String REFERER = "referer";

	public static final String REFERER_FLAG = "refererFlag";

	public static final String SESSION_CURRENT_USER = "sessionLogin";
	
	public static final String SESSION_CURRENT_OPERATOR = "sessionOperator";

	public static final String SESSION_CURRENT_ROLES = "sessionCurrentRoles";
	
	public static final String SESSION_CURRENT_POWERS_BY_CODE = "sessionPowersByCode";

	public static final String CURRENT_MENUS = "currentMenus";

	public static final String SESSION_CURRENT_POWERS_BY_URL = "sessionCurrentPowersByUrl";

	public static final String APPLICATION_POWERS_BY_URL = "applicationPowersByUrl";
    /** The name of the ResourceBundle used in this application */
	public static final String REG_EX = "[<>']+";
    public static final String BUNDLE_KEY = "ApplicationResources";
	public static final String ROOT_ACCOUNT="root";
	public static final String GLOBAL_ACCOUNT="global";

    /** The encryption algorithm key to be used for passwords */
    public static final String ENC_ALGORITHM = "algorithm";

    /** A flag to indicate if passwords should be encrypted */
    public static final String ENCRYPT_PASSWORD = "if4#$%^&Ujnds7r";

    /** File separator from System properties */
    public static final String FILE_SEP = System.getProperty("file.separator");

    /** User home from System properties */
    public static final String USER_HOME = System.getProperty("user.home") + FILE_SEP;

    /** The name of the configuration hashmap stored in application scope. */
    public static final String CONFIG = "appConfig";

    /** 
     * Session scope attribute that holds the locale set by the user. By setting this key
     * to the same one that Struts uses, we get synchronization in Struts w/o having
     * to do extra work or have two session-level variables.
     */ 
    public static final String PREFERRED_LOCALE_KEY = "org.apache.struts.action.LOCALE";
    
    /**
     * The request scope attribute under which an editable user form is stored
     */
    
    public static final String USER_KEY = "userForm";

    /**
     * The request scope attribute that holds the user list
     */
    public static final String USER_LIST = "userList";

    /**
     * The request scope attribute for indicating a newly-registered user
     */
    public static final String REGISTERED = "registered";

    /**
     * The name of the Administrator role, as specified in web.xml
     */
    public static final String ADMIN_ROLE = "admin";

    /**
     * The name of the User role, as specified in web.xml
     */
    public static final String USER_ROLE = "user";

    /**
     * The name of the user's role list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String USER_ROLES = "userRoles";

    /**
     * The name of the available roles list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String AVAILABLE_ROLES = "availableRoles";

    /**
     * The name of the CSS Theme setting.
     */
    public static final String CSS_THEME = "csstheme";
	//AlBranch-START
	/**
	 * The request scope attribute that holds the alBranch form.
	 */
	public static final String ALBRANCH_KEY = "alBranchForm";

	/**
	 * The request scope attribute that holds the alBranch list
	 */
	public static final String ALBRANCH_LIST = "alBranchList";

	//AlBranch-END

	//AlCharacterCoding-START
	/**
	 * The request scope attribute that holds the alCharacterCoding form.
	 */
	public static final String ALCHARACTERCODING_KEY = "alCharacterCodingForm";

	/**
	 * The request scope attribute that holds the alCharacterCoding list
	 */
	public static final String ALCHARACTERCODING_LIST = "alCharacterCodingList";

	//AlCharacterCoding-END

	//AlCharacterKey-START
	/**
	 * The request scope attribute that holds the alCharacterKey form.
	 */
	public static final String ALCHARACTERKEY_KEY = "alCharacterKeyForm";

	/**
	 * The request scope attribute that holds the alCharacterKey list
	 */
	public static final String ALCHARACTERKEY_LIST = "alCharacterKeyList";

	//AlCharacterKey-END

	//AlCharacterValue-START
	/**
	 * The request scope attribute that holds the alCharacterValue form.
	 */
	public static final String ALCHARACTERVALUE_KEY = "alCharacterValueForm";

	/**
	 * The request scope attribute that holds the alCharacterValue list
	 */
	public static final String ALCHARACTERVALUE_LIST = "alCharacterValueList";

	//AlCharacterValue-END

	//AlCompany-START
	/**
	 * The request scope attribute that holds the alCompany form.
	 */
	public static final String ALCOMPANY_KEY = "alCompanyForm";

	/**
	 * The request scope attribute that holds the alCompany list
	 */
	public static final String ALCOMPANY_LIST = "alCompanyList";

	//AlCompany-END

	//AlCountry-START
	/**
	 * The request scope attribute that holds the alCountry form.
	 */
	public static final String ALCOUNTRY_KEY = "alCountryForm";

	/**
	 * The request scope attribute that holds the alCountry list
	 */
	public static final String ALCOUNTRY_LIST = "alCountryList";

	//AlCountry-END

	//AlCurrency-START
	/**
	 * The request scope attribute that holds the alCurrency form.
	 */
	public static final String ALCURRENCY_KEY = "alCurrencyForm";

	/**
	 * The request scope attribute that holds the alCurrency list
	 */
	public static final String ALCURRENCY_LIST = "alCurrencyList";

	//AlCurrency-END

	//AlRegion-START
	/**
	 * The request scope attribute that holds the alRegion form.
	 */
	public static final String ALREGION_KEY = "alRegionForm";

	/**
	 * The request scope attribute that holds the alRegion list
	 */
	public static final String ALREGION_LIST = "alRegionList";

	//AlRegion-END

	//AlStateProvince-START
	/**
	 * The request scope attribute that holds the alStateProvince form.
	 */
	public static final String ALSTATEPROVINCE_KEY = "alStateProvinceForm";

	/**
	 * The request scope attribute that holds the alStateProvince list
	 */
	public static final String ALSTATEPROVINCE_LIST = "alStateProvinceList";

	//AlStateProvince-END

	// ----------------System manage module -START-------------------//
	public static final int PAGE_SIZE=20;
	
	public static final String SYSUSER_KEY = "sysUserForm";

	public static final String SYSUSER_LIST = "sysUserList";

	// ----------------System manage module -END-------------------//

	public static Map<String, Map<String, String>> localLanguageMap;

	public static Map<String, String> companyMap;
	
	public static boolean isLoad = false;

	public static List companyList;
	
	public static List periodList;
	
	public static List alStateProvinceList;
	
	public static List alCityList;
	
	public static List alDistrictList;
	
	public static List jalTownList;
	
	public static List companyHasAAList;
	public static boolean isLoad() {

		return isLoad;
	





}
	public static void setLoad(boolean isLoad) {

		Constants.isLoad = isLoad;





}

	public static Map<String, Map<String, String>> sysConfigMap;
	
	public static Map<String, Map<String, String[]>> sysListMap;

//SysListKey-START
    /**
     * The request scope attribute that holds the sysListKey form.
     */
    public static final String SYSLISTKEY_KEY = "sysListKeyForm";

    /**
     * The request scope attribute that holds the sysListKey list
     */
    public static final String SYSLISTKEY_LIST = "sysListKeyList";
//SysListKey-END

//SysListValue-START
    /**
     * The request scope attribute that holds the sysListValue form.
     */
    public static final String SYSLISTVALUE_KEY = "sysListValueForm";

    /**
     * The request scope attribute that holds the sysListValue list
     */
    public static final String SYSLISTVALUE_LIST = "sysListValueList";
//SysListValue-END

//SysConfigKey-START
    /**
     * The request scope attribute that holds the sysConfigKey form.
     */
    public static final String SYSCONFIGKEY_KEY = "sysConfigKeyForm";

    /**
     * The request scope attribute that holds the sysConfigKey list
     */
    public static final String SYSCONFIGKEY_LIST = "sysConfigKeyList";
//SysConfigKey-END

//SysConfigValue-START
    /**
     * The request scope attribute that holds the sysConfigValue form.
     */
    public static final String SYSCONFIGVALUE_KEY = "sysConfigValueForm";

    /**
     * The request scope attribute that holds the sysConfigValue list
     */
    public static final String SYSCONFIGVALUE_LIST = "sysConfigValueList";
//SysConfigValue-END
//SysId-START
    /**
     * The request scope attribute that holds the sysId form.
     */
    public static final String SYSID_KEY = "sysIdForm";

    /**
     * The request scope attribute that holds the sysId list
     */
    public static final String SYSID_LIST = "sysIdList";
//SysId-END

//AlCharacterType-START
    /**
     * The request scope attribute that holds the alCharacterType form.
     */
    public static final String ALCHARACTERTYPE_KEY = "alCharacterTypeForm";

    /**
     * The request scope attribute that holds the alCharacterType list
     */
    public static final String ALCHARACTERTYPE_LIST = "alCharacterTypeList";
//AlCharacterType-END

//SysClickLog-START
    /**
     * The request scope attribute that holds the sysClickLog form.
     */
    public static final String SYSCLICKLOG_KEY = "sysClickLogForm";

    /**
     * The request scope attribute that holds the sysClickLog list
     */
    public static final String SYSCLICKLOG_LIST = "sysClickLogList";
//SysClickLog-END

//SysDataLog-START
    /**
     * The request scope attribute that holds the sysDataLog form.
     */
    public static final String SYSDATALOG_KEY = "sysDataLogForm";

    /**
     * The request scope attribute that holds the sysDataLog list
     */
    public static final String SYSDATALOG_LIST = "sysDataLogList";
//SysDataLog-END

   
    public static final String SYSOPERATIONLOG_LIST = "sysOperationLogList";
//SysUsrTypePow-START
    /**
     * The request scope attribute that holds the sysUsrTypePow form.
     */
    public static final String SYSUSRTYPEPOW_KEY = "sysUsrTypePowForm";

    /**
     * The request scope attribute that holds the sysUsrTypePow list
     */
    public static final String SYSUSRTYPEPOW_LIST = "sysUsrTypePowList";
//SysUsrTypePow-END



//SysManagerModlRoll-START
    /**
     * The request scope attribute that holds the sysManagerModlRoll form.
     */
    public static final String SYSMANAGERMODLROLL_KEY = "sysManagerModlRollForm";

    /**
     * The request scope attribute that holds the sysManagerModlRoll list
     */
    public static final String SYSMANAGERMODLROLL_LIST = "sysManagerModlRollList";
//SysManagerModlRoll-END



//SysCompanyPow-START
    /**
     * The request scope attribute that holds the sysCompanyPow form.
     */
    public static final String SYSCOMPANYPOW_KEY = "sysCompanyPowForm";

    /**
     * The request scope attribute that holds the sysCompanyPow list
     */
    public static final String SYSCOMPANYPOW_LIST = "sysCompanyPowList";
//SysCompanyPow-END

//SysDepartment-START
    /**
     * The request scope attribute that holds the sysDepartment form.
     */
    public static final String SYSDEPARTMENT_KEY = "sysDepartmentForm";

    /**
     * The request scope attribute that holds the sysDepartment list
     */
    public static final String SYSDEPARTMENT_LIST = "sysDepartmentList";
//SysDepartment-END

//SysManager-START
    /**
     * The request scope attribute that holds the sysManager form.
     */
    public static final String SYSMANAGER_KEY = "sysManagerForm";

    /**
     * The request scope attribute that holds the sysManager list
     */
    public static final String SYSMANAGER_LIST = "sysManagerList";
//SysManager-END

//SysManagerUser-START
    /**
     * The request scope attribute that holds the sysManagerUser form.
     */
    public static final String SYSMANAGERUSER_KEY = "sysManagerUserForm";

    /**
     * The request scope attribute that holds the sysManagerUser list
     */
    public static final String SYSMANAGERUSER_LIST = "sysManagerUserList";
//SysManagerUser-END

//SysModule-START
    /**
     * The request scope attribute that holds the sysModule form.
     */
    public static final String SYSMODULE_KEY = "sysModuleForm";

    /**
     * The request scope attribute that holds the sysModule list
     */
    public static final String SYSMODULE_LIST = "sysModuleList";
//SysModule-END

//SysPower-START
    /**
     * The request scope attribute that holds the sysPower form.
     */
    public static final String SYSPOWER_KEY = "sysPowerForm";

    /**
     * The request scope attribute that holds the sysPower list
     */
    public static final String SYSPOWER_LIST = "sysPowerList";
//SysPower-END

//SysRole-START
    /**
     * The request scope attribute that holds the sysRole form.
     */
    public static final String SYSROLE_KEY = "sysRoleForm";

    /**
     * The request scope attribute that holds the sysRole list
     */
    public static final String SYSROLE_LIST = "sysRoleList";
//SysRole-END

//SysUserRole-START
    /**
     * The request scope attribute that holds the sysUserRole form.
     */
    public static final String SYSUSERROLE_KEY = "sysUserRoleForm";

    /**
     * The request scope attribute that holds the sysUserRole list
     */
    public static final String SYSUSERROLE_LIST = "sysUserRoleList";
//SysUserRole-END

//SysRolePower-START
    /**
     * The request scope attribute that holds the sysRolePower form.
     */
    public static final String SYSROLEPOWER_KEY = "sysRolePowerForm";

    /**
     * The request scope attribute that holds the sysRolePower list
     */
    public static final String SYSROLEPOWER_LIST = "sysRolePowerList";
//SysRolePower-END
//SysBank-START
    /**
     * The request scope attribute that holds the sysBank form.
     */
    public static final String SYSBANK_KEY = "sysBankForm";

    /**
     * The request scope attribute that holds the sysBank list
     */
    public static final String SYSBANK_LIST = "sysBankList";
//SysBank-END
//SysVisitLog-START
    /**
     * The request scope attribute that holds the sysVisitLog form.
     */
    public static final String SYSVISITLOG_KEY = "sysVisitLogForm";

    /**
     * The request scope attribute that holds the sysVisitLog list
     */
    public static final String SYSVISITLOG_LIST = "sysVisitLogList";
//SysVisitLog-END

//SysUserIp-START
    /**
     * The request scope attribute that holds the sysUserIp form.
     */
    public static final String SYSUSERIP_KEY = "sysUserIpForm";

    /**
     * The request scope attribute that holds the sysUserIp list
     */
    public static final String SYSUSERIP_LIST = "sysUserIpList";
//SysUserIp-END

//SysReportLog-START
    /**
     * The request scope attribute that holds the sysReportLog form.
     */
    public static final String SYSREPORTLOG_KEY = "sysReportLogForm";

    /**
     * The request scope attribute that holds the sysReportLog list
     */
    public static final String SYSREPORTLOG_LIST = "sysReportLogList";
//SysReportLog-END
//AlCompanyArea-START
    /**
     * The request scope attribute that holds the alCompanyArea form.
     */
    public static final String ALCOMPANYAREA_KEY = "alCompanyAreaForm";

    /**
     * The request scope attribute that holds the alCompanyArea list
     */
    public static final String ALCOMPANYAREA_LIST = "alCompanyAreaList";
//AlCompanyArea-END
    public static final String JECS_SERVER_ADDR="server_address";
    public static final String JECS_SERVER_PORT="server_port";
    private static final String CONFIG_FILE = "/config.properties" ;
    public static final String REPORT_TMP_PATH="/WEB-INF/report_tmp/";
    private static Properties prop;
    static {
		prop = new Properties();
		Log log = LogFactory.getLog(Constants.class);
		try {
			prop.load(Constants.class.getResourceAsStream(CONFIG_FILE));
		} catch (Exception ioe) {
			log.error("read app config file; 类路径上没有文件config.properties");
		}
		prop.putAll(System.getProperties());
	}
    
	public static String getConfig(String key) {
		String result = prop.getProperty(key);
		return result;
	}
	
	





//JmiMember-START
    /**
     * The request scope attribute that holds the jmiMember form.
     */
    public static final String JMIMEMBER_KEY = "jmiMemberForm";

    /**
     * The request scope attribute that holds the jmiMember list
     */
    public static final String JMIMEMBER_LIST = "jmiMemberList";
//JmiMember-END

//JmiMemberUpgradeNote-START
    /**
     * The request scope attribute that holds the jmiMemberUpgradeNote form.
     */
    public static final String JMIMEMBERUPGRADENOTE_KEY = "jmiMemberUpgradeNoteForm";

    /**
     * The request scope attribute that holds the jmiMemberUpgradeNote list
     */
    public static final String JMIMEMBERUPGRADENOTE_LIST = "jmiMemberUpgradeNoteList";
//JmiMemberUpgradeNote-END

//JfiBankbookBalance-START
    /**
     * The request scope attribute that holds the jfiBankbookBalance form.
     */
    public static final String JFIBANKBOOKBALANCE_KEY = "jfiBankbookBalanceForm";

    /**
     * The request scope attribute that holds the jfiBankbookBalance list
     */
    public static final String JFIBANKBOOKBALANCE_LIST = "jfiBankbookBalanceList";
//JfiBankbookBalance-END
    
//JfiBankbookJournal-START
    /**
     * The request scope attribute that holds the jfiBankbookJournal form.
     */
    public static final String JFIBANKBOOKJOURNAL_KEY = "jfiBankbookJournalForm";

    /**
     * The request scope attribute that holds the jfiBankbookJournal list
     */
    public static final String JFIBANKBOOKJOURNAL_LIST = "jfiBankbookJournalList";
//JfiBankbookJournal-END

//JfiBankbookTemp-START
    /**
     * The request scope attribute that holds the jfiBankbookTemp form.
     */
    public static final String JFIBANKBOOKTEMP_KEY = "jfiBankbookTempForm";

    /**
     * The request scope attribute that holds the jfiBankbookTemp list
     */
    public static final String JFIBANKBOOKTEMP_LIST = "jfiBankbookTempList";
//JfiBankbookTemp-END

//JfiPayAdvice-START
    /**
     * The request scope attribute that holds the jfiPayAdvice form.
     */
    public static final String JFIPAYADVICE_KEY = "jfiPayAdviceForm";

    /**
     * The request scope attribute that holds the jfiPayAdvice list
     */
    public static final String JFIPAYADVICE_LIST = "jfiPayAdviceList";
//JfiPayAdvice-END

//JfiPayBank-START
    /**
     * The request scope attribute that holds the jfiPayBank form.
     */
    public static final String JFIPAYBANK_KEY = "jfiPayBankForm";

    /**
     * The request scope attribute that holds the jfiPayBank list
     */
    public static final String JFIPAYBANK_LIST = "jfiPayBankList";
//JfiPayBank-END

//JfiPayData-START
    /**
     * The request scope attribute that holds the jfiPayData form.
     */
    public static final String JFIPAYDATA_KEY = "jfiPayDataForm";

    /**
     * The request scope attribute that holds the jfiPayData list
     */
    public static final String JFIPAYDATA_LIST = "jfiPayDataList";
//JfiPayData-END

//JmiAddrBook-START
    /**
     * The request scope attribute that holds the jmiAddrBook form.
     */
    public static final String JMIADDRBOOK_KEY = "jmiAddrBookForm";

    /**
     * The request scope attribute that holds the jmiAddrBook list
     */
    public static final String JMIADDRBOOK_LIST = "jmiAddrBookList";
//JmiAddrBook-END

//JmiTreeIndex-START
    /**
     * The request scope attribute that holds the jmiTreeIndex form.
     */
    public static final String JMITREEINDEX_KEY = "jmiTreeIndexForm";

    /**
     * The request scope attribute that holds the jmiTreeIndex list
     */
    public static final String JMITREEINDEX_LIST = "jmiTreeIndexList";
//JmiTreeIndex-END

//JmiLinkRef-START
    /**
     * The request scope attribute that holds the jmiLinkRef form.
     */
    public static final String JMILINKREF_KEY = "jmiLinkRefForm";

    /**
     * The request scope attribute that holds the jmiLinkRef list
     */
    public static final String JMILINKREF_LIST = "jmiLinkRefList";
//JmiLinkRef-END

//JmiRecommendRef-START
    /**
     * The request scope attribute that holds the jmiRecommendRef form.
     */
    public static final String JMIRECOMMENDREF_KEY = "jmiRecommendRefForm";

    /**
     * The request scope attribute that holds the jmiRecommendRef list
     */
    public static final String JMIRECOMMENDREF_LIST = "jmiRecommendRefList";
//JmiRecommendRef-END

//JpmProduct-START
    /**
     * The request scope attribute that holds the jpmProduct form.
     */
    public static final String JPMPRODUCT_KEY = "jpmProductForm";

    /**
     * The request scope attribute that holds the jpmProduct list
     */
    public static final String JPMPRODUCT_LIST = "jpmProductList";
//JpmProduct-END

//JpmProductSale-START
    /**
     * The request scope attribute that holds the jpmProductSale form.
     */
    public static final String JPMPRODUCTSALE_KEY = "jpmProductSaleForm";

    /**
     * The request scope attribute that holds the jpmProductSale list
     */
    public static final String JPMPRODUCTSALE_LIST = "jpmProductSaleList";
//JpmProductSale-END

//JpmProductSaleLog-START
    /**
     * The request scope attribute that holds the jpmProductSaleLog form.
     */
    public static final String JPMPRODUCTSALELOG_KEY = "jpmProductSaleLogForm";

    /**
     * The request scope attribute that holds the jpmProductSaleLog list
     */
    public static final String JPMPRODUCTSALELOG_LIST = "jpmProductSaleLogList";
//JpmProductSaleLog-END

//Jfi99billLog-START
    /**
     * The request scope attribute that holds the jfi99billLog form.
     */
    public static final String JFI99BILLLOG_KEY = "jfi99billLogForm";

    /**
     * The request scope attribute that holds the jfi99billLog list
     */
    public static final String JFI99BILLLOG_LIST = "jfi99billLogList";
//Jfi99billLog-END

//Jfi99billTemp-START
    /**
     * The request scope attribute that holds the jfi99billTemp form.
     */
    public static final String JFI99BILLTEMP_KEY = "jfi99billTempForm";

    /**
     * The request scope attribute that holds the jfi99billTemp list
     */
    public static final String JFI99BILLTEMP_LIST = "jfi99billTempList";
//Jfi99billTemp-END

//PdWarehouse-START
    /**
     * The request scope attribute that holds the pdWarehouse form.
     */
    public static final String PDWAREHOUSE_KEY = "pdWarehouseForm";

    /**
     * The request scope attribute that holds the pdWarehouse list
     */
    public static final String PDWAREHOUSE_LIST = "pdWarehouseList";
//PdWarehouse-END

//PdWarehouseStockTrace-START
    /**
     * The request scope attribute that holds the pdWarehouseStockTrace form.
     */
    public static final String PDWAREHOUSESTOCKTRACE_KEY = "pdWarehouseStockTraceForm";

    /**
     * The request scope attribute that holds the pdWarehouseStockTrace list
     */
    public static final String PDWAREHOUSESTOCKTRACE_LIST = "pdWarehouseStockTraceList";
//PdWarehouseStockTrace-END

//PdWarehouseStock-START
    /**
     * The request scope attribute that holds the pdWarehouseStock form.
     */
    public static final String PDWAREHOUSESTOCK_KEY = "pdWarehouseStockForm";

    /**
     * The request scope attribute that holds the pdWarehouseStock list
     */
    public static final String PDWAREHOUSESTOCK_LIST = "pdWarehouseStockList";
//PdWarehouseStock-END

//PdSendInfo-START
    /**
     * The request scope attribute that holds the pdSendInfo form.
     */
    public static final String PDSENDINFO_KEY = "pdSendInfoForm";

    /**
     * The request scope attribute that holds the pdSendInfo list
     */
    public static final String PDSENDINFO_LIST = "pdSendInfoList";
//PdSendInfo-END

//PdSendInfoDetail-START
    /**
     * The request scope attribute that holds the pdSendInfoDetail form.
     */
    public static final String PDSENDINFODETAIL_KEY = "pdSendInfoDetailForm";

    /**
     * The request scope attribute that holds the pdSendInfoDetail list
     */
    public static final String PDSENDINFODETAIL_LIST = "pdSendInfoDetailList";
//PdSendInfoDetail-END

//PdShipmentsDetail-START
    /**
     * The request scope attribute that holds the pdShipmentsDetail form.
     */
    public static final String PDSHIPMENTSDETAIL_KEY = "pdShipmentsDetailForm";

    /**
     * The request scope attribute that holds the pdShipmentsDetail list
     */
    public static final String PDSHIPMENTSDETAIL_LIST = "pdShipmentsDetailList";
//PdShipmentsDetail-END

//PdWarehouseUser-START
    /**
     * The request scope attribute that holds the pdWarehouseUser form.
     */
    public static final String PDWAREHOUSEUSER_KEY = "pdWarehouseUserForm";

    /**
     * The request scope attribute that holds the pdWarehouseUser list
     */
    public static final String PDWAREHOUSEUSER_LIST = "pdWarehouseUserList";
//PdWarehouseUser-END

//PdWarehouseArea-START
    /**
     * The request scope attribute that holds the pdWarehouseArea form.
     */
    public static final String PDWAREHOUSEAREA_KEY = "pdWarehouseAreaForm";

    /**
     * The request scope attribute that holds the pdWarehouseArea list
     */
    public static final String PDWAREHOUSEAREA_LIST = "pdWarehouseAreaList";
//PdWarehouseArea-END

//JpoMemberOrder-START
    /**
     * The request scope attribute that holds the jpoMemberOrder form.
     */
    public static final String JPOMEMBERORDER_KEY = "jpoMemberOrderForm";

    /**
     * The request scope attribute that holds the jpoMemberOrder list
     */
    public static final String JPOMEMBERORDER_LIST = "jpoMemberOrderList";
//JpoMemberOrder-END

	public static final String JPOMEMBERNYC_LIST="jpoMemberNycList";

	public static final String JPOMEMBERNYCQUALIFIED="jpoMemberNycQualifiedList";

//JpoMemberOrderFee-START
    /**
     * The request scope attribute that holds the jpoMemberOrderFee form.
     */
    public static final String JPOMEMBERORDERFEE_KEY = "jpoMemberOrderFeeForm";

    /**
     * The request scope attribute that holds the jpoMemberOrderFee list
     */
    public static final String JPOMEMBERORDERFEE_LIST = "jpoMemberOrderFeeList";
//JpoMemberOrderFee-END

//JpoMemberOrderList-START
    /**
     * The request scope attribute that holds the jpoMemberOrderList form.
     */
    public static final String JPOMEMBERORDERLIST_KEY = "jpoMemberOrderListForm";

    /**
     * The request scope attribute that holds the jpoMemberOrderList list
     */
    public static final String JPOMEMBERORDERLIST_LIST = "jpoMemberOrderListList";
//JpoMemberOrderList-END

//JprRefund-START
    /**
     * The request scope attribute that holds the jprRefund form.
     */
    public static final String JPRREFUND_KEY = "jprRefundForm";

    /**
     * The request scope attribute that holds the jprRefund list
     */
    public static final String JPRREFUND_LIST = "jprRefundList";
//JprRefund-END

//JprRefundList-START
    /**
     * The request scope attribute that holds the jprRefundList form.
     */
    public static final String JPRREFUNDLIST_KEY = "jprRefundListForm";

    /**
     * The request scope attribute that holds the jprRefundList list
     */
    public static final String JPRREFUNDLIST_LIST = "jprRefundListList";
//JprRefundList-END

//BdOutwardBank-START
    /**
     * The request scope attribute that holds the bdOutwardBank form.
     */
    public static final String BDOUTWARDBANK_KEY = "bdOutwardBankForm";

    /**
     * The request scope attribute that holds the bdOutwardBank list
     */
    public static final String BDOUTWARDBANK_LIST = "bdOutwardBankList";
//BdOutwardBank-END

//JbdSendRecord-START
    /**
     * The request scope attribute that holds the jbdSendRecord form.
     */
    public static final String JBDSENDRECORD_KEY = "jbdSendRecordForm";

    /**
     * The request scope attribute that holds the jbdSendRecord list
     */
    public static final String JBDSENDRECORD_LIST = "jbdSendRecordList";
//JbdSendRecord-END

//JbdSendRecordHist-START
    /**
     * The request scope attribute that holds the jbdSendRecordHist form.
     */
    public static final String JBDSENDRECORDHIST_KEY = "jbdSendRecordHistForm";

    /**
     * The request scope attribute that holds the jbdSendRecordHist list
     */
    public static final String JBDSENDRECORDHIST_LIST = "jbdSendRecordHistList";
//JbdSendRecordHist-END

//JbdBonusBalance-START
    /**
     * The request scope attribute that holds the jbdBonusBalance form.
     */
    public static final String JBDBONUSBALANCE_KEY = "jbdBonusBalanceForm";

    /**
     * The request scope attribute that holds the jbdBonusBalance list
     */
    public static final String JBDBONUSBALANCE_LIST = "jbdBonusBalanceList";
//JbdBonusBalance-END

//PdEnterWarehouse-START
    /**
     * The request scope attribute that holds the pdEnterWarehouse form.
     */
    public static final String PDENTERWAREHOUSE_KEY = "pdEnterWarehouseForm";

    /**
     * The request scope attribute that holds the pdEnterWarehouse list
     */
    public static final String PDENTERWAREHOUSE_LIST = "pdEnterWarehouseList";
//PdEnterWarehouse-END

//PdEnterWarehouseDetail-START
    /**
     * The request scope attribute that holds the pdEnterWarehouseDetail form.
     */
    public static final String PDENTERWAREHOUSEDETAIL_KEY = "pdEnterWarehouseDetailForm";

    /**
     * The request scope attribute that holds the pdEnterWarehouseDetail list
     */
    public static final String PDENTERWAREHOUSEDETAIL_LIST = "pdEnterWarehouseDetailList";
//PdEnterWarehouseDetail-END

//PdOutWarehouse-START
    /**
     * The request scope attribute that holds the pdOutWarehouse form.
     */
    public static final String PDOUTWAREHOUSE_KEY = "pdOutWarehouseForm";

    /**
     * The request scope attribute that holds the pdOutWarehouse list
     */
    public static final String PDOUTWAREHOUSE_LIST = "pdOutWarehouseList";
//PdOutWarehouse-END

//PdOutWarehouseDetail-START
    /**
     * The request scope attribute that holds the pdOutWarehouseDetail form.
     */
    public static final String PDOUTWAREHOUSEDETAIL_KEY = "pdOutWarehouseDetailForm";

    /**
     * The request scope attribute that holds the pdOutWarehouseDetail list
     */
    public static final String PDOUTWAREHOUSEDETAIL_LIST = "pdOutWarehouseDetailList";
//PdOutWarehouseDetail-END



//PdReturnPurchase-START
    /**
     * The request scope attribute that holds the pdReturnPurchase form.
     */
    public static final String PDRETURNPURCHASE_KEY = "pdReturnPurchaseForm";

    /**
     * The request scope attribute that holds the pdReturnPurchase list
     */
    public static final String PDRETURNPURCHASE_LIST = "pdReturnPurchaseList";
//PdReturnPurchase-END

//PdReturnPurchaseDetail-START
    /**
     * The request scope attribute that holds the pdReturnPurchaseDetail form.
     */
    public static final String PDRETURNPURCHASEDETAIL_KEY = "pdReturnPurchaseDetailForm";

    /**
     * The request scope attribute that holds the pdReturnPurchaseDetail list
     */
    public static final String PDRETURNPURCHASEDETAIL_LIST = "pdReturnPurchaseDetailList";
//PdReturnPurchaseDetail-END

//PdAdjustStock-START
    /**
     * The request scope attribute that holds the pdAdjustStock form.
     */
    public static final String PDADJUSTSTOCK_KEY = "pdAdjustStockForm";

    /**
     * The request scope attribute that holds the pdAdjustStock list
     */
    public static final String PDADJUSTSTOCK_LIST = "pdAdjustStockList";
//PdAdjustStock-END

//PdAdjustStockDetail-START
    /**
     * The request scope attribute that holds the pdAdjustStockDetail form.
     */
    public static final String PDADJUSTSTOCKDETAIL_KEY = "pdAdjustStockDetailForm";

    /**
     * The request scope attribute that holds the pdAdjustStockDetail list
     */
    public static final String PDADJUSTSTOCKDETAIL_LIST = "pdAdjustStockDetailList";
//PdAdjustStockDetail-END

//PdFlitWarehouse-START
    /**
     * The request scope attribute that holds the pdFlitWarehouse form.
     */
    public static final String PDFLITWAREHOUSE_KEY = "pdFlitWarehouseForm";

    /**
     * The request scope attribute that holds the pdFlitWarehouse list
     */
    public static final String PDFLITWAREHOUSE_LIST = "pdFlitWarehouseList";
//PdFlitWarehouse-END

//PdFlitWarehouseDetail-START
    /**
     * The request scope attribute that holds the pdFlitWarehouseDetail form.
     */
    public static final String PDFLITWAREHOUSEDETAIL_KEY = "pdFlitWarehouseDetailForm";

    /**
     * The request scope attribute that holds the pdFlitWarehouseDetail list
     */
    public static final String PDFLITWAREHOUSEDETAIL_LIST = "pdFlitWarehouseDetailList";
//PdFlitWarehouseDetail-END

//PdStatusExcStock-START
    /**
     * The request scope attribute that holds the pdStatusExcStock form.
     */
    public static final String PDSTATUSEXCSTOCK_KEY = "pdStatusExcStockForm";

    /**
     * The request scope attribute that holds the pdStatusExcStock list
     */
    public static final String PDSTATUSEXCSTOCK_LIST = "pdStatusExcStockList";
//PdStatusExcStock-END

//PdStatusExcStockDetail-START
    /**
     * The request scope attribute that holds the pdStatusExcStockDetail form.
     */
    public static final String PDSTATUSEXCSTOCKDETAIL_KEY = "pdStatusExcStockDetailForm";

    /**
     * The request scope attribute that holds the pdStatusExcStockDetail list
     */
    public static final String PDSTATUSEXCSTOCKDETAIL_LIST = "pdStatusExcStockDetailList";
//PdStatusExcStockDetail-END

//BdPeriod-START
    /**
     * The request scope attribute that holds the bdPeriod form.
     */
    public static final String BDPERIOD_KEY = "bdPeriodForm";

    /**
     * The request scope attribute that holds the bdPeriod list
     */
    public static final String BDPERIOD_LIST = "bdPeriodList";
//BdPeriod-END

//JbdSellCalcSubHist-START
    /**
     * The request scope attribute that holds the jbdSellCalcSubHist form.
     */
    public static final String JBDSELLCALCSUBHIST_KEY = "jbdSellCalcSubHistForm";

    /**
     * The request scope attribute that holds the jbdSellCalcSubHist list
     */
    public static final String JBDSELLCALCSUBHIST_LIST = "jbdSellCalcSubHistList";
//JbdSellCalcSubHist-END

//JbdMemberLinkCalcHist-START
    /**
     * The request scope attribute that holds the jbdMemberLinkCalcHist form.
     */
    public static final String JBDMEMBERLINKCALCHIST_KEY = "jbdMemberLinkCalcHistForm";

    /**
     * The request scope attribute that holds the jbdMemberLinkCalcHist list
     */
    public static final String JBDMEMBERLINKCALCHIST_LIST = "jbdMemberLinkCalcHistList";
//JbdMemberLinkCalcHist-END

//AlDistrict-START
    /**
     * The request scope attribute that holds the alDistrict form.
     */
    public static final String ALDISTRICT_KEY = "alDistrictForm";

    /**
     * The request scope attribute that holds the alDistrict list
     */
    public static final String ALDISTRICT_LIST = "alDistrictList";
//AlDistrict-END

//AlCity-START
    /**
     * The request scope attribute that holds the alCity form.
     */
    public static final String ALCITY_KEY = "alCityForm";

    /**
     * The request scope attribute that holds the alCity list
     */
    public static final String ALCITY_LIST = "alCityList";
//AlCity-END

//JbdUserCardList-START
    /**
     * The request scope attribute that holds the jbdUserCardList form.
     */
    public static final String JBDUSERCARDLIST_KEY = "jbdUserCardListForm";

    /**
     * The request scope attribute that holds the jbdUserCardList list
     */
    public static final String JBDUSERCARDLIST_LIST = "jbdUserCardListList";
//JbdUserCardList-END

//JpoShippingFee-START
    /**
     * The request scope attribute that holds the jpoShippingFee form.
     */
    public static final String JPOSHIPPINGFEE_KEY = "jpoShippingFeeForm";

    /**
     * The request scope attribute that holds the jpoShippingFee list
     */
    public static final String JPOSHIPPINGFEE_LIST = "jpoShippingFeeList";
//JpoShippingFee-END

//JbdDayBounsCalc-START
    /**
     * The request scope attribute that holds the jbdDayBounsCalc form.
     */
    public static final String JBDDAYBOUNSCALC_KEY = "jbdDayBounsCalcForm";

    /**
     * The request scope attribute that holds the jbdDayBounsCalc list
     */
    public static final String JBDDAYBOUNSCALC_LIST = "jbdDayBounsCalcList";
//JbdDayBounsCalc-END

//JbdDayBounsCalcHist-START
    /**
     * The request scope attribute that holds the jbdDayBounsCalcHist form.
     */
    public static final String JBDDAYBOUNSCALCHIST_KEY = "jbdDayBounsCalcHistForm";

    /**
     * The request scope attribute that holds the jbdDayBounsCalcHist list
     */
    public static final String JBDDAYBOUNSCALCHIST_LIST = "jbdDayBounsCalcHistList";
//JbdDayBounsCalcHist-END



//JbdManuallyAdjustStar-START
    /**
     * The request scope attribute that holds the jbdManuallyAdjustStar form.
     */
    public static final String JBDMANUALLYADJUSTSTAR_KEY = "jbdManuallyAdjustStarForm";

    /**
     * The request scope attribute that holds the jbdManuallyAdjustStar list
     */
    public static final String JBDMANUALLYADJUSTSTAR_LIST = "jbdManuallyAdjustStarList";
//JbdManuallyAdjustStar-END

//JbdVentureLeaderSubHist-START
    /**
     * The request scope attribute that holds the jbdVentureLeaderSubHist form.
     */
    public static final String JBDVENTURELEADERSUBHIST_KEY = "jbdVentureLeaderSubHistForm";

    /**
     * The request scope attribute that holds the jbdVentureLeaderSubHist list
     */
    public static final String JBDVENTURELEADERSUBHIST_LIST = "jbdVentureLeaderSubHistList";
//JbdVentureLeaderSubHist-END

//JbdSellCalcSubDetailHist-START
    /**
     * The request scope attribute that holds the jbdSellCalcSubDetailHist form.
     */
    public static final String JBDSELLCALCSUBDETAILHIST_KEY = "jbdSellCalcSubDetailHistForm";

    /**
     * The request scope attribute that holds the jbdSellCalcSubDetailHist list
     */
    public static final String JBDSELLCALCSUBDETAILHIST_LIST = "jbdSellCalcSubDetailHistList";
//JbdSellCalcSubDetailHist-END

//AmMessagePermit-START
    /**
     * The request scope attribute that holds the amMessagePermit form.
     */
    public static final String AMMESSAGEPERMIT_KEY = "amMessagePermitForm";

    /**
     * The request scope attribute that holds the amMessagePermit list
     */
    public static final String AMMESSAGEPERMIT_LIST = "amMessagePermitList";
//AmMessagePermit-END

//SysBackServiceInfo-START
    /**
     * The request scope attribute that holds the sysBackServiceInfo form.
     */
    public static final String SYSBACKSERVICEINFO_KEY = "sysBackServiceInfoForm";

    /**
     * The request scope attribute that holds the sysBackServiceInfo list
     */
    public static final String SYSBACKSERVICEINFO_LIST = "sysBackServiceInfoList";
//SysBackServiceInfo-END


//BillAccount-START
    /**
     * The request scope attribute that holds the billAccount form.
     */
    public static final String BILLACCOUNT_KEY = "billAccountForm";

    /**
     * The request scope attribute that holds the billAccount list
     */
    public static final String BILLACCOUNT_LIST = "billAccountList";
//BillAccount-END


//JpoCounterOrder-START
    /**
     * The request scope attribute that holds the jpoCounterOrder form.
     */
    public static final String JPOCOUNTERORDER_KEY = "jpoCounterOrderForm";

    /**
     * The request scope attribute that holds the jpoCounterOrder list
     */
    public static final String JPOCOUNTERORDER_LIST = "jpoCounterOrderList";
//JpoCounterOrder-END

//JpoCounterOrderDetail-START
    /**
     * The request scope attribute that holds the jpoCounterOrderDetail form.
     */
    public static final String JPOCOUNTERORDERDETAIL_KEY = "jpoCounterOrderDetailForm";

    /**
     * The request scope attribute that holds the jpoCounterOrderDetail list
     */
    public static final String JPOCOUNTERORDERDETAIL_LIST = "jpoCounterOrderDetailList";
//JpoCounterOrderDetail-END

//JamMsnType-START
    /**
     * The request scope attribute that holds the jamMsnType form.
     */
    public static final String JAMMSNTYPE_KEY = "jamMsnTypeForm";

    /**
     * The request scope attribute that holds the jamMsnType list
     */
    public static final String JAMMSNTYPE_LIST = "jamMsnTypeList";
//JamMsnType-END

//JamMsnDetail-START
    /**
     * The request scope attribute that holds the jamMsnDetail form.
     */
    public static final String JAMMSNDETAIL_KEY = "jamMsnDetailForm";

    /**
     * The request scope attribute that holds the jamMsnDetail list
     */
    public static final String JAMMSNDETAIL_LIST = "jamMsnDetailList";
//JamMsnDetail-END

//SysLoginLog-START
    /**
     * The request scope attribute that holds the sysLoginLog form.
     */
    public static final String SYSLOGINLOG_KEY = "sysLoginLogForm";

    /**
     * The request scope attribute that holds the sysLoginLog list
     */
    public static final String SYSLOGINLOG_LIST = "sysLoginLogList";
//SysLoginLog-END



//JpmProductCombination-START
    /**
     * The request scope attribute that holds the jpmProductCombination form.
     */
    public static final String JPMPRODUCTCOMBINATION_KEY = "jpmProductCombinationForm";

    /**
     * The request scope attribute that holds the jpmProductCombination list
     */
    public static final String JPMPRODUCTCOMBINATION_LIST = "jpmProductCombinationList";
//JpmProductCombination-END
    
    
    public static final String  JFIPAYLOG_LIST ="jfiPayLogList";
    

//PdCombinationOrder-START
    /**
     * The request scope attribute that holds the pdCombinationOrder form.
     */
    public static final String PDCOMBINATIONORDER_KEY = "pdCombinationOrderForm";

    /**
     * The request scope attribute that holds the pdCombinationOrder list
     */
    public static final String PDCOMBINATIONORDER_LIST = "pdCombinationOrderList";
//PdCombinationOrder-END

//PdCombinationDetail-START
    /**
     * The request scope attribute that holds the pdCombinationDetail form.
     */
    public static final String PDCOMBINATIONDETAIL_KEY = "pdCombinationDetailForm";

    /**
     * The request scope attribute that holds the pdCombinationDetail list
     */
    public static final String PDCOMBINATIONDETAIL_LIST = "pdCombinationDetailList";
//PdCombinationDetail-END

//JbdCaculateLog-START
    /**
     * The request scope attribute that holds the jbdCaculateLog form.
     */
    public static final String JBDCACULATELOG_KEY = "jbdCaculateLogForm";

    /**
     * The request scope attribute that holds the jbdCaculateLog list
     */
    public static final String JBDCACULATELOG_LIST = "jbdCaculateLogList";
//JbdCaculateLog-END


//SmsSendLog-START
    /**
     * The request scope attribute that holds the smsSendLog form.
     */
    public static final String SMSSENDLOG_KEY = "smsSendLogForm";

    /**
     * The request scope attribute that holds the smsSendLog list
     */
    public static final String SMSSENDLOG_LIST = "smsSendLogList";
//SmsSendLog-END


//PdCheckstockOrder-START
    /**
     * The request scope attribute that holds the pdCheckstockOrder form.
     */
    public static final String PDCHECKSTOCKORDER_KEY = "pdCheckstockOrderForm";

    /**
     * The request scope attribute that holds the pdCheckstockOrder list
     */
    public static final String PDCHECKSTOCKORDER_LIST = "pdCheckstockOrderList";
//PdCheckstockOrder-END

//PdCheckstockOrderDetail-START
    /**
     * The request scope attribute that holds the pdCheckstockOrderDetail form.
     */
    public static final String PDCHECKSTOCKORDERDETAIL_KEY = "pdCheckstockOrderDetailForm";

    /**
     * The request scope attribute that holds the pdCheckstockOrderDetail list
     */
    public static final String PDCHECKSTOCKORDERDETAIL_LIST = "pdCheckstockOrderDetailList";
//PdCheckstockOrderDetail-END


//JamMsnModule-START
    /**
     * The request scope attribute that holds the jamMsnModule form.
     */
    public static final String JAMMSNMODULE_KEY = "jamMsnModuleForm";

    /**
     * The request scope attribute that holds the jamMsnModule list
     */
    public static final String JAMMSNMODULE_LIST = "jamMsnModuleList";
//JamMsnModule-END

//VtVote-START
    /**
     * The request scope attribute that holds the vtVote form.
     */
    public static final String VTVOTE_KEY = "vtVoteForm";

    /**
     * The request scope attribute that holds the vtVote list
     */
    public static final String VTVOTE_LIST = "vtVoteList";
//VtVote-END

//VtVoteDetail-START
    /**
     * The request scope attribute that holds the vtVoteDetail form.
     */
    public static final String VTVOTEDETAIL_KEY = "vtVoteDetailForm";

    /**
     * The request scope attribute that holds the vtVoteDetail list
     */
    public static final String VTVOTEDETAIL_LIST = "vtVoteDetailList";
//VtVoteDetail-END

//VtVoteNote-START
    /**
     * The request scope attribute that holds the vtVoteNote form.
     */
    public static final String VTVOTENOTE_KEY = "vtVoteNoteForm";

    /**
     * The request scope attribute that holds the vtVoteNote list
     */
    public static final String VTVOTENOTE_LIST = "vtVoteNoteList";
//VtVoteNote-END

//VtVotePow-START
    /**
     * The request scope attribute that holds the vtVotePow form.
     */
    public static final String VTVOTEPOW_KEY = "vtVotePowForm";

    /**
     * The request scope attribute that holds the vtVotePow list
     */
    public static final String VTVOTEPOW_LIST = "vtVotePowList";
//VtVotePow-END

//JbdUserCompanyCode-START
    /**
     * The request scope attribute that holds the jbdUserCompanyCode form.
     */
    public static final String JBDUSERCOMPANYCODE_KEY = "jbdUserCompanyCodeForm";

    /**
     * The request scope attribute that holds the jbdUserCompanyCode list
     */
    public static final String JBDUSERCOMPANYCODE_LIST = "jbdUserCompanyCodeList";
//JbdUserCompanyCode-END







//JmiMemberCompanyNote-START
    /**
     * The request scope attribute that holds the jmiMemberCompanyNote form.
     */
    public static final String JMIMEMBERCOMPANYNOTE_KEY = "jmiMemberCompanyNoteForm";

    /**
     * The request scope attribute that holds the jmiMemberCompanyNote list
     */
    public static final String JMIMEMBERCOMPANYNOTE_LIST = "jmiMemberCompanyNoteList";
//JmiMemberCompanyNote-END



//Jfi99billPosLog-START
    /**
     * The request scope attribute that holds the jfi99billPosLog form.
     */
    public static final String JFI99BILLPOSLOG_KEY = "jfi99billPosLogForm";

    /**
     * The request scope attribute that holds the jfi99billPosLog list
     */
    public static final String JFI99BILLPOSLOG_LIST = "jfi99billPosLogList";
//Jfi99billPosLog-END

//Jfi99billPosSendLog-START
    /**
     * The request scope attribute that holds the jfi99billPosSendLog form.
     */
    public static final String JFI99BILLPOSSENDLOG_KEY = "jfi99billPosSendLogForm";

    /**
     * The request scope attribute that holds the jfi99billPosSendLog list
     */
    public static final String JFI99BILLPOSSENDLOG_LIST = "jfi99billPosSendLogList";
//Jfi99billPosSendLog-END



//JpmCardSeq-START
    /**
     * The request scope attribute that holds the jpmCardSeq form.
     */
    public static final String JPMCARDSEQ_KEY = "jpmCardSeqForm";

    /**
     * The request scope attribute that holds the jpmCardSeq list
     */
    public static final String JPMCARDSEQ_LIST = "jpmCardSeqList";
//JpmCardSeq-END

//JfiSunMemberOrderList-START
    /**
     * The request scope attribute that holds the jfiSunMemberOrderList form.
     */
    public static final String JFISUNMEMBERORDERLIST_KEY = "jfiSunMemberOrderListForm";

    /**
     * The request scope attribute that holds the jfiSunMemberOrderList list
     */
    public static final String JFISUNMEMBERORDERLIST_LIST = "jfiSunMemberOrderListList";
//JfiSunMemberOrderList-END

//JfiSunMemberOrderFee-START
    /**
     * The request scope attribute that holds the jfiSunMemberOrderFee form.
     */
    public static final String JFISUNMEMBERORDERFEE_KEY = "jfiSunMemberOrderFeeForm";

    /**
     * The request scope attribute that holds the jfiSunMemberOrderFee list
     */
    public static final String JFISUNMEMBERORDERFEE_LIST = "jfiSunMemberOrderFeeList";
//JfiSunMemberOrderFee-END

//JfiSunMemberOrder-START
    /**
     * The request scope attribute that holds the jfiSunMemberOrder form.
     */
    public static final String JFISUNMEMBERORDER_KEY = "jfiSunMemberOrderForm";

    /**
     * The request scope attribute that holds the jfiSunMemberOrder list
     */
    public static final String JFISUNMEMBERORDER_LIST = "jfiSunMemberOrderList";
//JfiSunMemberOrder-END

//JfiSunDistribution-START
    /**
     * The request scope attribute that holds the jfiSunDistribution form.
     */
    public static final String JFISUNDISTRIBUTION_KEY = "jfiSunDistributionForm";

    /**
     * The request scope attribute that holds the jfiSunDistribution list
     */
    public static final String JFISUNDISTRIBUTION_LIST = "jfiSunDistributionList";
//JfiSunDistribution-END

//JfiSunConfigKey-START
    /**
     * The request scope attribute that holds the jfiSunConfigKey form.
     */
    public static final String JFISUNCONFIGKEY_KEY = "jfiSunConfigKeyForm";

    /**
     * The request scope attribute that holds the jfiSunConfigKey list
     */
    public static final String JFISUNCONFIGKEY_LIST = "jfiSunConfigKeyList";
//JfiSunConfigKey-END

//JbdTaiwanTravelPoint-START
    /**
     * The request scope attribute that holds the jbdTaiwanTravelPoint form.
     */
    public static final String JBDTAIWANTRAVELPOINT_KEY = "jbdTaiwanTravelPointForm";

    /**
     * The request scope attribute that holds the jbdTaiwanTravelPoint list
     */
    public static final String JBDTAIWANTRAVELPOINT_LIST = "jbdTaiwanTravelPointList";
//JbdTaiwanTravelPoint-END

//JmiTaiwanTravel-START
    /**
     * The request scope attribute that holds the jmiTaiwanTravel form.
     */
    public static final String JMITAIWANTRAVEL_KEY = "jmiTaiwanTravelForm";

    /**
     * The request scope attribute that holds the jmiTaiwanTravel list
     */
    public static final String JMITAIWANTRAVEL_LIST = "jmiTaiwanTravelList";
//JmiTaiwanTravel-END

//PdExchangeOrder-START
    /**
     * The request scope attribute that holds the pdExchangeOrder form.
     */
    public static final String PDEXCHANGEORDER_KEY = "pdExchangeOrderForm";

    /**
     * The request scope attribute that holds the pdExchangeOrder list
     */
    public static final String PDEXCHANGEORDER_LIST = "pdExchangeOrderList";
//PdExchangeOrder-END

//PdExchangeOrderBack-START
    /**
     * The request scope attribute that holds the pdExchangeOrderBack form.
     */
    public static final String PDEXCHANGEORDERBACK_KEY = "pdExchangeOrderBackForm";

    /**
     * The request scope attribute that holds the pdExchangeOrderBack list
     */
    public static final String PDEXCHANGEORDERBACK_LIST = "pdExchangeOrderBackList";
//PdExchangeOrderBack-END

//PdExchangeOrderDetail-START
    /**
     * The request scope attribute that holds the pdExchangeOrderDetail form.
     */
    public static final String PDEXCHANGEORDERDETAIL_KEY = "pdExchangeOrderDetailForm";

    /**
     * The request scope attribute that holds the pdExchangeOrderDetail list
     */
    public static final String PDEXCHANGEORDERDETAIL_LIST = "pdExchangeOrderDetailList";
//PdExchangeOrderDetail-END

//JmiSubStore-START
    /**
     * The request scope attribute that holds the jmiSubStore form.
     */
    public static final String JMISUBSTORE_KEY = "jmiSubStoreForm";

    /**
     * The request scope attribute that holds the jmiSubStore list
     */
    public static final String JMISUBSTORE_LIST = "jmiSubStoreList";
//JmiSubStore-END

//JpoAutoShip-START
    /**
     * The request scope attribute that holds the jpoAutoShip form.
     */
    public static final String JPOAUTOSHIP_KEY = "jpoAutoShipForm";

    /**
     * The request scope attribute that holds the jpoAutoShip list
     */
    public static final String JPOAUTOSHIP_LIST = "jpoAutoShipList";
//JpoAutoShip-END

//JbdUserValidList-START
    /**
     * The request scope attribute that holds the jbdUserValidList form.
     */
    public static final String JBDUSERVALIDLIST_KEY = "jbdUserValidListForm";

    /**
     * The request scope attribute that holds the jbdUserValidList list
     */
    public static final String JBDUSERVALIDLIST_LIST = "jbdUserValidListList";
//JbdUserValidList-END

//JalTown-START
    /**
     * The request scope attribute that holds the jalTown form.
     */
    public static final String JALTOWN_KEY = "jalTownForm";

    /**
     * The request scope attribute that holds the jalTown list
     */
    public static final String JALTOWN_LIST = "jalTownList";
//JalTown-END

//JmiStore-START
    /**
     * The request scope attribute that holds the jmiStore form.
     */
    public static final String JMISTORE_KEY = "jmiStoreForm";

    /**
     * The request scope attribute that holds the jmiStore list
     */
    public static final String JMISTORE_LIST = "jmiStoreList";
//JmiStore-END

//FiCoinLog-START
    /**
     * The request scope attribute that holds the fiCoinLog form.
     */
    public static final String FICOINLOG_KEY = "fiCoinLogForm";

    /**
     * The request scope attribute that holds the fiCoinLog list
     */
    public static final String FICOINLOG_LIST = "fiCoinLogList";
//FiCoinLog-END

//JfiAlipayLog-START
    /**
     * The request scope attribute that holds the jfiAlipayLog form.
     */
    public static final String JFIALIPAYLOG_KEY = "jfiAlipayLogForm";

    /**
     * The request scope attribute that holds the jfiAlipayLog list
     */
    public static final String JFIALIPAYLOG_LIST = "jfiAlipayLogList";
//JfiAlipayLog-END

//JamDownload-START
    /**
     * The request scope attribute that holds the jamDownload form.
     */
    public static final String JAMDOWNLOAD_KEY = "jamDownloadForm";

    /**
     * The request scope attribute that holds the jamDownload list
     */
    public static final String JAMDOWNLOAD_LIST = "jamDownloadList";
//JamDownload-END

//JmiBlacklist-START
    /**
     * The request scope attribute that holds the jmiBlacklist form.
     */
    public static final String JMIBLACKLIST_KEY = "jmiBlacklistForm";

    /**
     * The request scope attribute that holds the jmiBlacklist list
     */
    public static final String JMIBLACKLIST_LIST = "jmiBlacklistList";
//JmiBlacklist-END

//JfiHiTrustLog-START
    /**
     * The request scope attribute that holds the jfiHiTrustLog form.
     */
    public static final String JFIHITRUSTLOG_KEY = "jfiHiTrustLogForm";

    /**
     * The request scope attribute that holds the jfiHiTrustLog list
     */
    public static final String JFIHITRUSTLOG_LIST = "jfiHiTrustLogList";
//JfiHiTrustLog-END



//JbdManuallyAdjustAlgebra-START
    /**
     * The request scope attribute that holds the jbdManuallyAdjustAlgebra form.
     */
    public static final String JBDMANUALLYADJUSTALGEBRA_KEY = "jbdManuallyAdjustAlgebraForm";

    /**
     * The request scope attribute that holds the jbdManuallyAdjustAlgebra list
     */
    public static final String JBDMANUALLYADJUSTALGEBRA_LIST = "jbdManuallyAdjustAlgebraList";
//JbdManuallyAdjustAlgebra-END

//JpoMemberOrderTask-START
    /**
     * The request scope attribute that holds the jpoMemberOrderTask form.
     */
    public static final String JPOMEMBERORDERTASK_KEY = "jpoMemberOrderTaskForm";

    /**
     * The request scope attribute that holds the jpoMemberOrderTask list
     */
    public static final String JPOMEMBERORDERTASK_LIST = "jpoMemberOrderTaskList";
//JpoMemberOrderTask-END

//JpoMemberOrderListTask-START
    /**
     * The request scope attribute that holds the jpoMemberOrderListTask form.
     */
    public static final String JPOMEMBERORDERLISTTASK_KEY = "jpoMemberOrderListTaskForm";

    /**
     * The request scope attribute that holds the jpoMemberOrderListTask list
     */
    public static final String JPOMEMBERORDERLISTTASK_LIST = "jpoMemberOrderListTaskList";
//JpoMemberOrderListTask-END

//JamPromotion-START
    /**
     * The request scope attribute that holds the jamPromotion form.
     */
    public static final String JAMPROMOTION_KEY = "jamPromotionForm";

    /**
     * The request scope attribute that holds the jamPromotion list
     */
    public static final String JAMPROMOTION_LIST = "jamPromotionList";
//JamPromotion-END

//UpsInteractiveLog-START
    /**
     * The request scope attribute that holds the upsInteractiveLog form.
     */
    public static final String UPSINTERACTIVELOG_KEY = "upsInteractiveLogForm";

    /**
     * The request scope attribute that holds the upsInteractiveLog list
     */
    public static final String UPSINTERACTIVELOG_LIST = "upsInteractiveLogList";
//UpsInteractiveLog-END

//JbdTravelPoint-START
    /**
     * The request scope attribute that holds the jbdTravelPoint form.
     */
    public static final String JBDTRAVELPOINT_KEY = "jbdTravelPointForm";

    /**
     * The request scope attribute that holds the jbdTravelPoint list
     */
    public static final String JBDTRAVELPOINT_LIST = "jbdTravelPointList";
//JbdTravelPoint-END

//JbdTravelPointDetail-START
    /**
     * The request scope attribute that holds the jbdTravelPointDetail form.
     */
    public static final String JBDTRAVELPOINTDETAIL_KEY = "jbdTravelPointDetailForm";

    /**
     * The request scope attribute that holds the jbdTravelPointDetail list
     */
    public static final String JBDTRAVELPOINTDETAIL_LIST = "jbdTravelPointDetailList";
//JbdTravelPointDetail-END

//JbdTravelPointLog-START
    /**
     * The request scope attribute that holds the jbdTravelPointLog form.
     */
    public static final String JBDTRAVELPOINTLOG_KEY = "jbdTravelPointLogForm";

    /**
     * The request scope attribute that holds the jbdTravelPointLog list
     */
    public static final String JBDTRAVELPOINTLOG_LIST = "jbdTravelPointLogList";
//JbdTravelPointLog-END

//JbdSummaryList-START
    /**
     * The request scope attribute that holds the jbdSummaryList form.
     */
    public static final String JBDSUMMARYLIST_KEY = "jbdSummaryListForm";

    /**
     * The request scope attribute that holds the jbdSummaryList list
     */
    public static final String JBDSUMMARYLIST_LIST = "jbdSummaryListList";
//JbdSummaryList-END

//PdSendExtra-START
    /**
     * The request scope attribute that holds the pdSendExtra form.
     */
    public static final String PDSENDEXTRA_KEY = "pdSendExtraForm";

    /**
     * The request scope attribute that holds the pdSendExtra list
     */
    public static final String PDSENDEXTRA_LIST = "pdSendExtraList";
//PdSendExtra-END

//JbdVentureFundList-START
    /**
     * The request scope attribute that holds the jbdVentureFundList form.
     */
    public static final String JBDVENTUREFUNDLIST_KEY = "jbdVentureFundListForm";

    /**
     * The request scope attribute that holds the jbdVentureFundList list
     */
    public static final String JBDVENTUREFUNDLIST_LIST = "jbdVentureFundListList";
//JbdVentureFundList-END

//JfiUsCreditCardLog-START
    /**
     * The request scope attribute that holds the jfiUsCreditCardLog form.
     */
    public static final String JFIUSCREDITCARDLOG_KEY = "jfiUsCreditCardLogForm";

    /**
     * The request scope attribute that holds the jfiUsCreditCardLog list
     */
    public static final String JFIUSCREDITCARDLOG_LIST = "jfiUsCreditCardLogList";
//JfiUsCreditCardLog-END







//JfiSunOrderList-START
    /**
     * The request scope attribute that holds the jfiSunOrderList form.
     */
    public static final String JFISUNORDERLIST_KEY = "jfiSunOrderListForm";

    /**
     * The request scope attribute that holds the jfiSunOrderList list
     */
    public static final String JFISUNORDERLIST_LIST = "jfiSunOrderListList";
//JfiSunOrderList-END

//JfiSunOrder-START
    /**
     * The request scope attribute that holds the jfiSunOrder form.
     */
    public static final String JFISUNORDER_KEY = "jfiSunOrderForm";

    /**
     * The request scope attribute that holds the jfiSunOrder list
     */
    public static final String JFISUNORDER_LIST = "jfiSunOrderList";
//JfiSunOrder-END

//JfiSunJmiMember-START
    /**
     * The request scope attribute that holds the jfiSunJmiMember form.
     */
    public static final String JFISUNJMIMEMBER_KEY = "jfiSunJmiMemberForm";

    /**
     * The request scope attribute that holds the jfiSunJmiMember list
     */
    public static final String JFISUNJMIMEMBER_LIST = "jfiSunJmiMemberList";
//JfiSunJmiMember-END

//JalPostalcode-START
    /**
     * The request scope attribute that holds the jalPostalcode form.
     */
    public static final String JALPOSTALCODE_KEY = "jalPostalcodeForm";

    /**
     * The request scope attribute that holds the jalPostalcode list
     */
    public static final String JALPOSTALCODE_LIST = "jalPostalcodeList";
//JalPostalcode-END

//SunDistShip-START
    /**
     * The request scope attribute that holds the sunDistShip form.
     */
    public static final String SUNDISTSHIP_KEY = "sunDistShipForm";

    /**
     * The request scope attribute that holds the sunDistShip list
     */
    public static final String SUNDISTSHIP_LIST = "sunDistShipList";
//SunDistShip-END

//SunProductInfo-START
    /**
     * The request scope attribute that holds the sunProductInfo form.
     */
    public static final String SUNPRODUCTINFO_KEY = "sunProductInfoForm";

    /**
     * The request scope attribute that holds the sunProductInfo list
     */
    public static final String SUNPRODUCTINFO_LIST = "sunProductInfoList";
//SunProductInfo-END

//JbdSendNote-START
    /**
     * The request scope attribute that holds the jbdSendNote form.
     */
    public static final String JBDSENDNOTE_KEY = "jbdSendNoteForm";

    /**
     * The request scope attribute that holds the jbdSendNote list
     */
    public static final String JBDSENDNOTE_LIST = "jbdSendNoteList";
//JbdSendNote-END

//JbdNetworkList-START
    /**
     * The request scope attribute that holds the jbdNetworkList form.
     */
    public static final String JBDNETWORKLIST_KEY = "jbdNetworkListForm";

    /**
     * The request scope attribute that holds the jbdNetworkList list
     */
    public static final String JBDNETWORKLIST_LIST = "jbdNetworkListList";
//JbdNetworkList-END

//JmiCustomerLevelNote-START
    /**
     * The request scope attribute that holds the jmiCustomerLevelNote form.
     */
    public static final String JMICUSTOMERLEVELNOTE_KEY = "jmiCustomerLevelNoteForm";

    /**
     * The request scope attribute that holds the jmiCustomerLevelNote list
     */
    public static final String JMICUSTOMERLEVELNOTE_LIST = "jmiCustomerLevelNoteList";
//JmiCustomerLevelNote-END

//JfiCreditCardLog-START
    /**
     * The request scope attribute that holds the jfiCreditCardLog form.
     */
    public static final String JFICREDITCARDLOG_KEY = "jfiCreditCardLogForm";

    /**
     * The request scope attribute that holds the jfiCreditCardLog list
     */
    public static final String JFICREDITCARDLOG_LIST = "jfiCreditCardLogList";
//JfiCreditCardLog-END

//FiBcoinJournal-START
    /**
     * The request scope attribute that holds the fiBcoinJournal form.
     */
    public static final String FIBCOINJOURNAL_KEY = "fiBcoinJournalForm";

    /**
     * The request scope attribute that holds the fiBcoinJournal list
     */
    public static final String FIBCOINJOURNAL_LIST = "fiBcoinJournalList";
//FiBcoinJournal-END

//FiBcoinBalance-START
    /**
     * The request scope attribute that holds the fiBcoinBalance form.
     */
    public static final String FIBCOINBALANCE_KEY = "fiBcoinBalanceForm";

    /**
     * The request scope attribute that holds the fiBcoinBalance list
     */
    public static final String FIBCOINBALANCE_LIST = "fiBcoinBalanceList";
//FiBcoinBalance-END

//FiCcoinJournal-START
    /**
     * The request scope attribute that holds the fiCcoinJournal form.
     */
    public static final String FICCOINJOURNAL_KEY = "fiCcoinJournalForm";

    /**
     * The request scope attribute that holds the fiCcoinJournal list
     */
    public static final String FICCOINJOURNAL_LIST = "fiCcoinJournalList";
//FiCcoinJournal-END

//FiCcoinBalance-START
    /**
     * The request scope attribute that holds the fiCcoinBalance form.
     */
    public static final String FICCOINBALANCE_KEY = "fiCcoinBalanceForm";

    /**
     * The request scope attribute that holds the fiCcoinBalance list
     */
    public static final String FICCOINBALANCE_LIST = "fiCcoinBalanceList";
//FiCcoinBalance-END

//PdShipStrategy-START
    /**
     * The request scope attribute that holds the pdShipStrategy form.
     */
    public static final String PDSHIPSTRATEGY_KEY = "pdShipStrategyForm";

    /**
     * The request scope attribute that holds the pdShipStrategy list
     */
    public static final String PDSHIPSTRATEGY_LIST = "pdShipStrategyList";
//PdShipStrategy-END

//PdShipStrategyDetail-START
    /**
     * The request scope attribute that holds the pdShipStrategyDetail form.
     */
    public static final String PDSHIPSTRATEGYDETAIL_KEY = "pdShipStrategyDetailForm";

    /**
     * The request scope attribute that holds the pdShipStrategyDetail list
     */
    public static final String PDSHIPSTRATEGYDETAIL_LIST = "pdShipStrategyDetailList";
//PdShipStrategyDetail-END

//FiBankbookBalance-START
    /**
     * The request scope attribute that holds the fiBankbookBalance form.
     */
    public static final String FIBANKBOOKBALANCE_KEY = "fiBankbookBalanceForm";

    /**
     * The request scope attribute that holds the fiBankbookBalance list
     */
    public static final String FIBANKBOOKBALANCE_LIST = "fiBankbookBalanceList";
    
    public static final String FIPRODUCTPOINTBALANCE_LIST = "fiProductPointBalanceList";
    
    public static final String FIPRODUCTPOINTJOURNAL_LIST = "fiProductPointJournalList";
//FiBankbookBalance-END

//FiBankbookJournal-START
    /**
     * The request scope attribute that holds the fiBankbookJournal form.
     */
    public static final String FIBANKBOOKJOURNAL_KEY = "fiBankbookJournalForm";

    /**
     * The request scope attribute that holds the fiBankbookJournal list
     */
    public static final String FIBANKBOOKJOURNAL_LIST = "fiBankbookJournalList";
//FiBankbookJournal-END

//FiBankbookTemp-START
    /**
     * The request scope attribute that holds the fiBankbookTemp form.
     */
    public static final String FIBANKBOOKTEMP_KEY = "fiBankbookTempForm";

    /**
     * The request scope attribute that holds the fiBankbookTemp list
     */
    public static final String FIBANKBOOKTEMP_LIST = "fiBankbookTempList";
    
    //Modify By WuCF 20170104 产品积分
    public static final String FIPRODUCTPOINTTEMP_LIST = "fiProductPointTempList";
//FiBankbookTemp-END

//JbdTravelPoint2012-START
    /**
     * The request scope attribute that holds the jbdTravelPoint2012 form.
     */
    public static final String JBDTRAVELPOINT2012_KEY = "jbdTravelPoint2012Form";

    /**
     * The request scope attribute that holds the jbdTravelPoint2012 list
     */
    public static final String JBDTRAVELPOINT2012_LIST = "jbdTravelPoint2012List";
//JbdTravelPoint2012-END

//JbdTravelPointDetail2012-START
    /**
     * The request scope attribute that holds the jbdTravelPointDetail2012 form.
     */
    public static final String JBDTRAVELPOINTDETAIL2012_KEY = "jbdTravelPointDetail2012Form";

    /**
     * The request scope attribute that holds the jbdTravelPointDetail2012 list
     */
    public static final String JBDTRAVELPOINTDETAIL2012_LIST = "jbdTravelPointDetail2012List";
//JbdTravelPointDetail2012-END

//JbdTravelPointLog2012-START
    /**
     * The request scope attribute that holds the jbdTravelPointLog2012 form.
     */
    public static final String JBDTRAVELPOINTLOG2012_KEY = "jbdTravelPointLog2012Form";

    /**
     * The request scope attribute that holds the jbdTravelPointLog2012 list
     */
    public static final String JBDTRAVELPOINTLOG2012_LIST = "jbdTravelPointLog2012List";
//JbdTravelPointLog2012-END

//JfiTenpayLog-START
    /**
     * The request scope attribute that holds the jfiTenpayLog form.
     */
    public static final String JFITENPAYLOG_KEY = "jfiTenpayLogForm";

    /**
     * The request scope attribute that holds the jfiTenpayLog list
     */
    public static final String JFITENPAYLOG_LIST = "jfiTenpayLogList";
//JfiTenpayLog-END

//PdShipFee-START
    /**
     * The request scope attribute that holds the pdShipFee form.
     */
    public static final String PDSHIPFEE_KEY = "pdShipFeeForm";

    /**
     * The request scope attribute that holds the pdShipFee list
     */
    public static final String PDSHIPFEE_LIST = "pdShipFeeList";
//PdShipFee-END

//Jfi99billmsLog-START
    /**
     * The request scope attribute that holds the jfi99billmsLog form.
     */
    public static final String JFI99BILLMSLOG_KEY = "jfi99billmsLogForm";

    /**
     * The request scope attribute that holds the jfi99billmsLog list
     */
    public static final String JFI99BILLMSLOG_LIST = "jfi99billmsLogList";
//Jfi99billmsLog-END

//PdWarehouseFrozenBatch-START
    /**
     * The request scope attribute that holds the pdWarehouseFrozenBatch form.
     */
    public static final String PDWAREHOUSEFROZENBATCH_KEY = "pdWarehouseFrozenBatchForm";

    /**
     * The request scope attribute that holds the pdWarehouseFrozenBatch list
     */
    public static final String PDWAREHOUSEFROZENBATCH_LIST = "pdWarehouseFrozenBatchList";
//PdWarehouseFrozenBatch-END

//PdWarehouseFrozenDetail-START
    /**
     * The request scope attribute that holds the pdWarehouseFrozenDetail form.
     */
    public static final String PDWAREHOUSEFROZENDETAIL_KEY = "pdWarehouseFrozenDetailForm";

    /**
     * The request scope attribute that holds the pdWarehouseFrozenDetail list
     */
    public static final String PDWAREHOUSEFROZENDETAIL_LIST = "pdWarehouseFrozenDetailList";
//PdWarehouseFrozenDetail-END

//AmNew-START
    /**
     * The request scope attribute that holds the amNew form.
     */
    public static final String AMNEW_KEY = "amNewForm";

    /**
     * The request scope attribute that holds the amNew list
     */
    public static final String AMNEW_LIST = "amNewList";
//AmNew-END

//JbdGradeNote-START
    /**
     * The request scope attribute that holds the jbdGradeNote form.
     */
    public static final String JBDGRADENOTE_KEY = "jbdGradeNoteForm";

    /**
     * The request scope attribute that holds the jbdGradeNote list
     */
    public static final String JBDGRADENOTE_LIST = "jbdGradeNoteList";

    public static final Long MEMBER_TIME = 60L;
    public static final Long COMPANY_TIME = 30L;
//JbdGradeNote-END

//JfiPosImport-START
    /**
     * The request scope attribute that holds the jfiPosImport form.
     */
    public static final String JFIPOSIMPORT_KEY = "jfiPosImportForm";

    /**
     * The request scope attribute that holds the jfiPosImport list
     */
    public static final String JFIPOSIMPORT_LIST = "jfiPosImportList";
//JfiPosImport-END

//JbdTravelPoint2013-START
    /**
     * The request scope attribute that holds the jbdTravelPoint2013 form.
     */
    public static final String JBDTRAVELPOINT2013_KEY = "jbdTravelPoint2013Form";

    /**
     * The request scope attribute that holds the jbdTravelPoint2013 list
     */
    public static final String JBDTRAVELPOINT2013_LIST = "jbdTravelPoint2013List";
//JbdTravelPoint2013-END

//JbdTravelPointLog2013-START
    /**
     * The request scope attribute that holds the jbdTravelPointLog2013 form.
     */
    public static final String JBDTRAVELPOINTLOG2013_KEY = "jbdTravelPointLog2013Form";

    /**
     * The request scope attribute that holds the jbdTravelPointLog2013 list
     */
    public static final String JBDTRAVELPOINTLOG2013_LIST = "jbdTravelPointLog2013List";
//JbdTravelPointLog2013-END

//JbdTravelPointDetail2013-START
    /**
     * The request scope attribute that holds the jbdTravelPointDetail2013 form.
     */
    public static final String JBDTRAVELPOINTDETAIL2013_KEY = "jbdTravelPointDetail2013Form";

    /**
     * The request scope attribute that holds the jbdTravelPointDetail2013 list
     */
    public static final String JBDTRAVELPOINTDETAIL2013_LIST = "jbdTravelPointDetail2013List";
//JbdTravelPointDetail2013-END

//JbdSpecialStar-START
    /**
     * The request scope attribute that holds the jbdSpecialStar form.
     */
    public static final String JBDSPECIALSTAR_KEY = "jbdSpecialStarForm";

    /**
     * The request scope attribute that holds the jbdSpecialStar list
     */
    public static final String JBDSPECIALSTAR_LIST = "jbdSpecialStarList";
//JbdSpecialStar-END

//JsysResource-START
    /**
     * The request scope attribute that holds the jsysResource form.
     */
    public static final String JSYSRESOURCE_KEY = "jsysResourceForm";

    /**
     * The request scope attribute that holds the jsysResource list
     */
    public static final String JSYSRESOURCE_LIST = "jsysResourceList";
//JsysResource-END

    public static final String JPOPRODUCTNUMLIMIT_LIST = "jpoProductNumLimitList"; 

//JsysResRole-START
    /**
     * The request scope attribute that holds the jsysResRole form.
     */
    public static final String JSYSRESROLE_KEY = "jsysResRoleForm";

    /**
     * The request scope attribute that holds the jsysResRole list
     */
    public static final String JSYSRESROLE_LIST = "jsysResRoleList";
//JsysResRole-END

//FiTransferAccount-START
    /**
     * The request scope attribute that holds the fiTransferAccount form.
     */
    public static final String FITRANSFERACCOUNT_KEY = "fiTransferAccountForm";

    /**
     * The request scope attribute that holds the fiTransferAccount list
     */
    public static final String FITRANSFERACCOUNT_LIST = "fiTransferAccountList";
    
    public static final String FITRANSFERACCOUNTCHK_KEY = "fiTransferAccountChkForm";
    
    public static final String FITRANSFERACCOUNTCHK_LIST = "fiTransferAccountChkList";
//FiTransferAccount-END



//JmiMemberTeam-START
    /**
     * The request scope attribute that holds the jmiMemberTeam form.
     */
    public static final String JMIMEMBERTEAM_KEY = "jmiMemberTeamForm";

    /**
     * The request scope attribute that holds the jmiMemberTeam list
     */
    public static final String JMIMEMBERTEAM_LIST = "jmiMemberTeamList";
//JmiMemberTeam-END

//JpmProductSaleNew-START
    /**
     * The request scope attribute that holds the jpmProductSaleNew form.
     */
    public static final String JPMPRODUCTSALENEW_KEY = "jpmProductSaleNewForm";

    /**
     * The request scope attribute that holds the jpmProductSaleNew list
     */
    public static final String JPMPRODUCTSALENEW_LIST = "jpmProductSaleNewList";
//JpmProductSaleNew-END

//JpmProductSaleImage-START
    /**
     * The request scope attribute that holds the jpmProductSaleImage form.
     */
    public static final String JPMPRODUCTSALEIMAGE_KEY = "jpmProductSaleImageForm";

    /**
     * The request scope attribute that holds the jpmProductSaleImage list
     */
    public static final String JPMPRODUCTSALEIMAGE_LIST = "jpmProductSaleImageList";
//JpmProductSaleImage-END

//JpmProductSaleTeamType-START
    /**
     * The request scope attribute that holds the jpmProductSaleTeamType form.
     */
    public static final String JPMPRODUCTSALETEAMTYPE_KEY = "jpmProductSaleTeamTypeForm";

    /**
     * The request scope attribute that holds the jpmProductSaleTeamType list
     */
    public static final String JPMPRODUCTSALETEAMTYPE_LIST = "jpmProductSaleTeamTypeList";
//JpmProductSaleTeamType-END

//JpmProductSaleRelated-START
    /**
     * The request scope attribute that holds the jpmProductSaleRelated form.
     */
    public static final String JPMPRODUCTSALERELATED_KEY = "jpmProductSaleRelatedForm";

    /**
     * The request scope attribute that holds the jpmProductSaleRelated list
     */
    public static final String JPMPRODUCTSALERELATED_LIST = "jpmProductSaleRelatedList";
//JpmProductSaleRelated-END




//JalFileLibarary-START
    /**
     * The request scope attribute that holds the jalFileLibarary form.
     */
    public static final String JALFILELIBARARY_KEY = "jalFileLibararyForm";

    /**
     * The request scope attribute that holds the jalFileLibarary list
     */
    public static final String JALFILELIBARARY_LIST = "jalFileLibararyList";

    /**
     * The request scope attribute that holds the scheduleManage form.
     */
    public static final String SCHEDULEMANAGE_KEY = "scheduleManageForm";

    /**
     * The request scope attribute that holds the scheduleManage list
     */
    public static final String SCHEDULEMANAGE_LIST = "scheduleManageList";
//ScheduleManage-END

//PublicSchedule-START
    /**
     * The request scope attribute that holds the publicSchedule form.
     */
    public static final String PUBLICSCHEDULE_KEY = "publicScheduleForm";

    /**
     * The request scope attribute that holds the publicSchedule list
     */
    public static final String PUBLICSCHEDULE_LIST = "publicScheduleList";
//PublicSchedule-END

  //JpmSalePromoter-START
    /**
     * The request scope attribute that holds the jpmSalePromoter form.
     */
    public static final String JPMSALEPROMOTER_KEY = "jpmSalePromoterForm";

    /**
     * The request scope attribute that holds the jpmSalePromoter list
     */
    public static final String JPMSALEPROMOTER_LIST = "jpmSalePromoterList";
//JpmSalePromoter-END

//JpmSalepromoterPro-START
    /**
     * The request scope attribute that holds the jpmSalepromoterPro form.
     */
    public static final String JPMSALEPROMOTERPRO_KEY = "jpmSalepromoterProForm";

    /**
     * The request scope attribute that holds the jpmSalepromoterPro list
     */
    public static final String JPMSALEPROMOTERPRO_LIST = "jpmSalepromoterProList";
    
    public static final String JALFILETYPE_LIST = "jalFileTypeList";
    
    public static final String JALFILECOLUMN_LIST = "jalFileColumnList";
    
    public static final String INWDEMAND_LIST = "inwDemandList";
    public static final String INWPROBLEM_LIST = "inwProblemList";
    public static final String INWSUGGESTION_LIST = "inwSuggestionList";

    public static final String JALLIBRARYPLATE_LIST = "jalLibraryPlateList";
    
    public static final String JALLIBRARYFILE_LIST = "jalLibraryFileList";
    
    public static final String JALLIBRARYCOLUMN_LIST = "jalLibraryColumnList";
    /**
     * <li>促销策略 已激活</li>
     */
    public static final String JPMSALE_ACTIVA="1";
    /**
     * <li>促销策略 未激活</li>
     */
    public static final String JPMSALE_NO_ACTIVA="0";
    /**
     * 策略类型  1:折扣促销
     */
    public static final String JPMSALE_SALETYPE_DISCOUNT="1";
    /**
     * 策略类型  2:赠品促销
     */
    public static final String JPMSALE_SALETYPE_PRE="2";
    /**
     * 策略类型  3:积分赠送促销
     */
    public static final String JPMSALE_SALETYPE_INTEGRAL="3";
    /**
     * 策略类型  4:按订单总金额或PV赠送
     */
    public static final String JPMSALE_SALETYPE_ORDER="4";
    /**
	 * 用户类型M: 会员
	 */
	public static final String MEMBER_M="M";
	/**
	 * 用户类型C: 公司用户
	 */
	public static final String MEMBER_C="C";
	/**
	 * 团队状态 0:可用
	 */
	public static final String TEAM_Y="0";
	/**
	 * 团队状态 1:不可用
	 */
	public static final String TEAM_N="1";
	/**
	 * 中脉
	 */
	public static final String JM="8888888888";
	
	public static final String JPMSALE_DISCOUNT_ORDER="5";
	
    public static final String JBDTRAVELPOINT2014_KEY = "jbdTravelPoint2014Form";

    public static final String JBDTRAVELPOINT2014_LIST = "jbdTravelPoint2014List";

    public static final String JBDTRAVELPOINTLOG2014_KEY = "jbdTravelPointLog2014Form";

    public static final String JBDTRAVELPOINTLOG2014_LIST = "jbdTravelPointLog2014List";

    public static final String JBDTRAVELPOINTDETAIL2014_KEY = "jbdTravelPointDetail2014Form";

    public static final String JBDTRAVELPOINTDETAIL2014_LIST = "jbdTravelPointDetail2014List";
	
	public static final String FOUNDATIONITEM_LIST="foundationItemList";
	
	public static final String FOUNDATIONORDER_LIST="foundationOrderList";
	
	public static final String FILovecoinBalance_LIST="fiLovecoinBalanceList";
	
	public static final String JMIZCWMEMBER_LIST="jmiZcwMemberList";
	
	public static final String FISECURITYDEPOSIT_LIST="fiSecurityDepositList";

	public static final String FISECURITYDEPOSITJOURNAL_LIST="fiSecurityDepositJournalList";
	
	public static final String JPMMEMBERCONFIG_LIST="jpmMemberConfigList";
	
	public static final String JPMCONFIGSPECDETAILED_LIST="jpmConfigSpecDetailedList";
	
	public static final String JPMCONFIGDETAILED_LIST="jpmConfigDetailedList";
	
	public static final String JPMSENDCONSIGNMENT_LIST="jpmSendConsignmentList";
	
	public static final String JPMWINEMEMBERORDER_LIST="jpmWineMemberOrderList";
	
	public static final String FICOINCONFIG_LIST="fiCoinConfigList";
	
	/**
     * 生态养生套餐产品编号
     */
    public static final String MOVIE_PRONO="P21010100101CN0";
    /**
     * 生态养生套餐产品编号
     */
    public static final String MOVIE_PRONO2="P21020100101CN0";
    /**P23010100101CN0 1箱*/
    public static final String PRONO="P23010100101CN0";
    /**P23010200101CN0 3箱*/
    public static final String PRONO1="P23010200101CN0";
    /**P23010300101CN0 5箱*/
    public static final String PRONO2="P23010300101CN0";
    //创新共赢扩展功能 部门    add  by gw 2014-05-20
    public static final String INWDEPARTMENT_LIST = "inwDepartmentList";
    //创新共赢扩展功能 部门权限    add  by gw 2014-05-20
    public static final String INWDEPARTCOMPETENCE_LIST = "inwDepartCompetenceList";
    
    //Add By WuCF 20140813 shell脚本调用存储过程导出数据
    public static final String ECEXPORTLOG = "pdExportLogList";
    
    //Add By WuCF 20140909 短信功能查询
    public static final String JPMSMSSENDINFO_LIST = "jpmSmssendInfoList";    
    
    //Add By WuCF 20141117 接口日志查询
    public static final String JOCSINTERFACELOG_LIST = "jocsInterfaceLogList";   
    
    //Add By WuCF 20141117 接口日志查询
    public static final String JOCSINTERFACERETRANSMISSION_LIST = "jocsInterfaceRetransmissionList";   
    
    // Add By GW  2014-12-01接口数据保存失败相关的参数
    //保存或修改保存正常返回的值
    public static final String INTERFACE_NORMAL = "interfaceNormal";
    //接口传递过来的数据为空的
    public static final String INTERFACE_NULL = "interfaceNull";
    //接口传递过来的格式有误
    public static final String INTERFACE_FORMAT_EXCEPTION = "interfaceFormatException";
    //接口传递过来的数据未不相干数据-比如：响应的发货单号我们这边没有，却发过来了该发货单号的数据
    public static final String INTERFACE_INDEPENDENT_DATA = "interfaceIndependentData";
    //接口传递过来的数据转换成对象的时候异常
    public static final String INTERFACE_JSON_FORMAT = "interfaceJsonFormat";
    //接口传递过来的数据保存是报异常
    public static final String INTERFACE_SAVE_EXCEPTION = "interfaceSaveException";
    //接口传递过来的数据是非法的，比如传个空值，传个无效字符Illegal data
    public static final String INTERFACE_ILLEGAL_DATA = "interfaceIllegalData";
    //响应接口成功的标志successString = "succ"
    public static final String SUCCESS_STRING = "succ";
    //响应接口失败的标志noSuccessString = "e000006"
    public static final String NO_SUCCESS_STRING = "e000006";
    
    //Add By WuCF 20150715 宝易互通
    public static final String JFIUMBPAYLOG_LIST = "jfiUmbpayLogList";   
    //Add By lzg 20150714 汇付天下
    public static final String JFICHINAPNRLOG_LIST = "jfiChinapnrLogList";  
    
    //Modify By WuCF 20151129 生成发票保证金
    public static final String FIINVOICECHANGE_LIST = "fiInvoiceChangeList";  
    public static final String FIINVOICEBALANCE_LIST = "fiInvoiceBalanceList";  
    
    /**
     * 系统参数丢失或错误
     */
    public static final String REPONSE_SYS_PARAM_ERR = "e000001";
    
    /**
     * 签名错误
     */
    public static final String REPONSE_SIGN_ERR = "e000002";
    
    /**
     * 接口不存在
     */
    public static final String REPONSE_METHOD_NOT_EXIST = "e000003";
    
    /**
     * 无权限请求接口
     */
    public static final String REPONSE_NO_PERMISSIONS = "e000004";
    
    /**
     * 接口调用失败
     */
    public static final String REPONSE_INIT_INTERFACE_FAIL = "e000005";
    
    /**
     * 应用参数错误
     */
    public static final String REPONSE_APP_PARAM_ERR = "e000006";
    
    /**
     * 接口配置信息错误
     */
    public static final String REPONSE_INIT_TEMPLATE_FAIL = "e000007";
    
    /**
     * 渠道平台
     */
    public static final String QU_SEND = "1";
    
    /**
     * OMS平台
     */
    public static final String OMS_SEND = "2";
    
    /**
     * CRM平台
     */
    public static final String CRM_SEND = "3";
    
    /**
     * 过河兵系统
     */
    public static final String GHB_SEND = "4";
    
    /**
     * WMS平台
     */
    public static final String WMS_SEND = "5";
    
    //已签收 Received
    public static final String RECEIVED = "0020";
    
    //客户拒收  Customer rejection
    public static final String CUSTOMER_REJECTION = "0022";
    
    
    
    public static final String JECS_BACK_SERVER_HOST="jecs_back_server_host";
 public static final String POS_TYPE_MA = "1";   
    
    public static final String POS_TYPE_USER = "2";   
    
    public static final String POS_TYPE_PHONE = "3"; 
    
    public static final String CHANGJIE_POS_TYPE = "106";
    
    public static final String POS_BACK = "0";
    
    //Modify By WuCF 2016-01-19 发货策略数据
    public static final String PDSHIPSTRATEGYMAIN_LIST = "pdShipStrategyMainList";

    //Modify By WuCF 2016-02-15商户号限额表
    public static final String JFIBUSINESSNUM_LIST = "jfiBusinessNumList";
    public static final String JFIQUOTA_LIST = "jfiQuotaList";
    public static final String JFIAMOUNTDETAIL_LIST = "jfiAmountDetailList";
    
  //modify fu 2015-12-29 为解决发货单编辑接口的高并发问题而创建
    public static final  Set setPdSendInfo=new HashSet();
    
   //Modify By WuCF 2016-01-19 退单套餐商品数据
    public static final String JPMCOMBINATIONRELATED_LIST = "jpmCombinationRelatedList";
    
    //modify by fu 2016-04-05 自助换货-不可换商品
    public static final String PDNOTCHANGEPRODUCT_LIST = "pdNotChangeProductList";
    /**待审会员 PV值*/
	public static final String CARD0_PV ="cardtype.0.value";
	/**银级会员 PV值*/
	public static final String CARD1_PV ="cardtype.1.value";
	/**金级会员 PV值*/
	public static final String CARD2_PV ="cardtype.2.value";
	/**白金会员 PV值*/
	public static final String CARD3_PV ="cardtype.3.value";
	/** 推广员 */
	public static final String CARD7_PV ="cardtype.7.value";
	
	
	/**钻石会员 PV值*/
	public static final String CARD4_PV ="cardtype.4.value";
	/**优惠顾客会员 PV值*/
	public static final String CARD5_PV ="cardtype.5.value";

	/**vip 180PV*/
	public static final String CARD1VIP_PV = "cardtype.2500.value";
	/**订单类型1 首购*/
	public static final String ORDER_TYPE_1 = "1";
	/**订单类型2 升级*/
	public static final String ORDER_TYPE_2 = "2";
	/**订单类型3 续约*/
	public static final String ORDER_TYPE_3 = "3";
	/**订单类型4 重消*/
	public static final String ORDER_TYPE_4 = "4";
	/**订单类型5 辅消*/
	public static final String ORDER_TYPE_5 = "5";
	/**订单类型6 一级店铺首单*/
	public static final String ORDER_TYPE_6 = "6";
	/**订单类型9 一级店铺重消*/
	public static final String ORDER_TYPE_9 = "9";
	
    /**角色: 1000  优惠顾客*/
	public static final Integer CARDTYPE_1000 = 1000;
	/**角色: 2000 永久优惠顾客*/
	public static final Integer CARDTYPE_2000 = 2000;
	/**角色: 1500  推广员 */
	public static final Integer CARDTYPE_1500 = 1500;
	/**角色: 3000 普通*/
	public static final Integer CARDTYPE_3000 = 3000;
	/**角色: 4000 高级*/
	public static final Integer CARDTYPE_4000 = 4000;
	/**角色: 5000特级*/
	public static final Integer CARDTYPE_5000 = 5000;
}