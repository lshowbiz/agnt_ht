package com.joymain.jecs.webapp.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.multipart.support.StringMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.model.User;
import com.joymain.jecs.pm.model.JpmProductSaleNew;
import com.joymain.jecs.pm.service.JpmProductSaleManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.service.UserManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.GlobalVar;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.mail.MailEngine;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;


/**
 * Implementation of <strong>SimpleFormController</strong> that contains
 * convenience methods for subclasses.  For example, getting the current
 * user and saving messages/errors. This class is intended to
 * be a base class for all Form controllers.
 *
 * <p><a href="BaseFormController.java.html"><i>View Source</i></a></p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class BaseFormController extends SimpleFormController {
    protected final transient Log log = LogFactory.getLog(getClass());
    protected final String MESSAGES_KEY = "successMessages";
    private UserManager userManager = null;
    protected MailEngine mailEngine = null;
    protected SimpleMailMessage message = null;
    protected String templateName = null;
    protected String cancelView;
    private AlStateProvinceManager alStateProvinceManager = null;
    private JpmProductSaleManager jpmProductSaleManager = null;
    private AlCityManager alCityManager = null;
    protected JpoMemberOrderManager jpoMemberOrderManager;
    
    public void setAlCityManager(AlCityManager alCityManager) {
		this.alCityManager = alCityManager;
	}

	public void setJpmProductSaleManager(JpmProductSaleManager jpmProductSaleManager) {
		this.jpmProductSaleManager = jpmProductSaleManager;
	}

	public void setAlStateProvinceManager(
			AlStateProvinceManager alStateProvinceManager) {
		this.alStateProvinceManager = alStateProvinceManager;
	}

	public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public UserManager getUserManager() {
        return this.userManager;
    }

    public void initPmProductMap(HttpServletRequest request){
    	SysUser sessionLogin = SessionLogin.getLoginUser(request);
		String companyCode = sessionLogin.getCompanyCode();
		//WuCF JpmProductSaleNew Modify By WuCF 20130917
    	/*Map compamyProductMap = jpmProductSaleManager.getCompanyProductMap(companyCode);*/
		Map compamyProductMap = jpmProductSaleManager.getCompanyProductMapNew(companyCode);
Map productMap = new HashMap();
    	
    	Set set = compamyProductMap.entrySet();
    	Iterator iterator =set.iterator();
    	while (iterator.hasNext()){
    		Entry entry = (Entry) iterator.next();
    		//WuCF JpmProductSaleNew Modify By WuCF 20130917
    		/*productMap.put(entry.getKey(), ((JpmProductSale)entry.getValue()).getProductName());*/
    		productMap.put(entry.getKey(), ((JpmProductSaleNew)entry.getValue()).getProductName());
    	}
    	request.setAttribute("compamyProductMap", productMap);
    }
    public void initStateCodeParem(HttpServletRequest request){
    	
SysUser sessionLogin = SessionLogin.getLoginUser(request);
    	
    	Map<String,String> alStateProvinceMap = new HashMap();
    	Map alCityMap = new HashMap();
    	List alStateProvinces = null;
    	alStateProvinceMap = alStateProvinceManager.getAlStateProvincesMapByCountry(sessionLogin.getCompanyCode());
    	alCityMap=alCityManager.getAlCityMap(sessionLogin.getCompanyCode());
    	if (sessionLogin.getIsManager()) {

			alStateProvinces = alStateProvinceManager.getAlStateProvinces(null);
		} else {
			alStateProvinces = alStateProvinceManager
					.getAlStateProvincesByCountry(sessionLogin.getCompanyCode());
		}
		request.setAttribute("alStateProvinces", alStateProvinces);
		request.setAttribute("alStateProvinceMap", alStateProvinceMap);
		request.setAttribute("alCityMap", alCityMap);
    	/*SysUser sessionLogin = SessionLogin.getLoginUser(request);
    	List alStateProvinces = null;
    	if (sessionLogin.getIsManager()) {

			alStateProvinces = alStateProvinceManager.getAlStateProvinces(null);
		} else {
			alStateProvinces = alStateProvinceManager
					.getAlStateProvincesByCountry(sessionLogin.getCompanyCode());
		}
		request.setAttribute("alStateProvinces", alStateProvinces);*/
    }
    public void saveMessage(HttpServletRequest request, String msg) {
        List messages = (List) request.getSession().getAttribute(MESSAGES_KEY);

        if (messages == null) {
            messages = new ArrayList();
        }
		if(StringUtils.isNotEmpty(msg)){
			messages.add(msg);
		}
        request.getSession().setAttribute(MESSAGES_KEY, messages);
    }
    
    /**
     * Convenience method for getting a i18n key's value.  Calling
     * getMessageSourceAccessor() is used because the RequestContext variable
     * is not set in unit tests b/c there's no DispatchServlet Request.
     *
     * @param msgKey
     * @param locale the current locale
     * @return
     */
	public String getText(String characterCoding, String msgKey) {
		return LocaleUtil.getLocalText(characterCoding, msgKey);
		// return getMessageSourceAccessor().getMessage(msgKey, locale);
	}
	
	/**
	 * ��ȡ��������ж�Ӧ������ֵ
	 * @param msgKey
	 * @return
	 */
	public String getText(String msgKey){
		return LocaleUtil.getLocalText(msgKey);
	}
	
	 public String getText(String msgKey,  Locale locale) {
	        return getText( msgKey, msgKey,  locale);
	    }
	/**
     * Convenient method for getting a i18n key's value with a single
     * string argument.
     *
     * @param msgKey
     * @param arg
     * @param locale the current locale
     * @return
     */
    public String getText(String msgKey, String arg, Locale locale) {
        return getText(msgKey, new Object[] { arg }, locale);
    }

    /**
     * Convenience method for getting a i18n key's value with arguments.
     *
     * @param msgKey
     * @param args
     * @param locale the current locale
     * @return
     */
    public String getText(String msgKey, Object[] args, Locale locale) {
        return getMessageSourceAccessor().getMessage(msgKey, args, locale);
    }

    /**
     * Convenience method to get the Configuration HashMap
     * from the servlet context.
     *
     * @return the user's populated form from the session
     */
    public Map getConfiguration() {
        Map config =
            (HashMap) getServletContext().getAttribute(Constants.CONFIG);

        // so unit tests don't puke when nothing's been set
        if (config == null) {
            return new HashMap();
        }

        return config;
    }

    /**
     * Default behavior for FormControllers - redirect to the successView
     * when the cancel button has been pressed.
     */
    public ModelAndView processFormSubmission(HttpServletRequest request,
                                              HttpServletResponse response,
                                              Object command,
                                              BindException errors)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return new ModelAndView(getCancelView());
        }

        return super.processFormSubmission(request, response, command, errors);
    }
    
    /**
     * Set up a custom property editor for converting form inputs to real objects
     */
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
//		binder.setAllowedFields(allowedFields);
//		binder.setDisallowedFields(disallowedFields);
//		binder.setRequiredFields(requiredFields);
		binder.registerCustomEditor(Integer.class, null, new CustomNumberEditor(Integer.class, null, true));
		binder.registerCustomEditor(Long.class, null, new CustomNumberEditor(Long.class, null, true));
		
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());//BLOB   
		binder.registerCustomEditor(String.class, new StringMultipartFileEditor());//CLOB
		//SimpleDateFormat dateFormat = new SimpleDateFormat(getText("date.format"));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

    /**
     * Convenience message to send messages to users, includes app URL as footer.
     * @param user
     * @param msg
     * @param url
     */
    protected void sendUserMessage(User user, String msg, String url) {
        if (log.isDebugEnabled()) {
            log.debug("sending e-mail to user [" + user.getEmail() + "]...");
        }

        message.setTo(user.getFullName() + "<" + user.getEmail() + ">");

        Map model = new HashMap();
        model.put("user", user);

        // TODO: once you figure out how to get the global resource bundle in
        // WebWork, then figure it out here too.  In the meantime, the Username
        // and Password labels are hard-coded into the template. 
        // model.put("bundle", getTexts());
        model.put("message", msg);
        model.put("applicationURL", url);
        mailEngine.send(message, templateName, model);
    }

    public void setMailEngine(MailEngine mailEngine) {
        this.mailEngine = mailEngine;
    }

    public void setMessage(SimpleMailMessage message) {
        this.message = message;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
    /**
     * Indicates what view to use when the cancel button has been pressed.
     */
    public final void setCancelView(String cancelView) {  
        this.cancelView = cancelView;  
    }

    public final String getCancelView() {
        // Default to successView if cancelView is invalid
        if (this.cancelView == null || this.cancelView.length()==0) {
            return getSuccessView();
        }
        return this.cancelView;   
    }

    /**
	 * 是否单独购买(9种商品)
	 * @param cartOrder
	 * @return
	 */
	protected boolean jpoIsOnly(Set<JpoMemberOrderList> jpoMemberOrderSet,Collection jpoList){
		boolean isOnly = true;
		Collection carProList= new  ArrayList();
//		Collection jpoList= GlobalVar.jpoList;
		
		Iterator<JpoMemberOrderList> lists = jpoMemberOrderSet.iterator();
		while(lists.hasNext()){
			JpoMemberOrderList jpoOrderList = lists.next();
			String jpoProNo = jpoOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
			carProList.add(jpoProNo);
		}
	
		
		if(Collections.disjoint(carProList,jpoList)){ //没有相同元素
			isOnly = true;
		}else{
			Iterator<JpoMemberOrderList> iters = jpoMemberOrderSet.iterator();
			while(iters.hasNext()){
				JpoMemberOrderList carOrderList = iters.next();
	    		String carProNo = carOrderList.getJpmProductSaleTeamType().
	    				getJpmProductSaleNew().getJpmProduct().getProductNo();
	    		if(jpoList.contains(carProNo)){
	    			isOnly = true;
	    		}else{
	    			return false;
	    		}
			}
			
		}
		
		log.info("isOnly: " + isOnly);
		return isOnly;
	}
	/**
	 * <li>判断是否需要下首购单，若会员需要下首购单，
	 * 则进入判断是否存在首购单,如果不存在则返回true</li>
	 * @param notfirst
	 * @param userCode
	 * @return true or false
	 * 
	 */
	protected boolean isBuyFirstOrder(Integer notfirst,String userCode,HttpServletRequest request){
		
		if(notfirst ==null || notfirst ==0){
			CommonRecord crm = new CommonRecord();
			crm.addField("sysUser.userCode", userCode);
			crm.addField("orderType", "1");
			crm.addField("listNum", "0");
			crm.addField("status", "2");
			List<JpoMemberOrder> jpoMemberOrders = jpoMemberOrderManager
					.getJpoMemberOrdersByCrm(crm, new Pager(request, 0));
			if(jpoMemberOrders.isEmpty()){
				return true;
			}
		}
		return false;
	}
	/**
	 * 会员是否冻结状态
	 * @param member
	 * @return true or false
	 */
	protected boolean isFreeze(Integer freezeStatus, BindException error,String encoding){
		if(freezeStatus==Constants.FREEZE_STATUS_ONE){
			error.reject("bdsendrecord.sendlateremark.17", new Object[] {},
					LocaleUtil.getLocalText(encoding,"bdsendrecord.sendlateremark.17"));
			return true;
		}
		return false;
	}
	public JpoMemberOrderManager getJpoMemberOrderManager() {
		return jpoMemberOrderManager;
	}

	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}
	/**
	 * 支付改造bug :2539
	 * @param order
	 * @param errors
	 * @param loginSysUser
	 * @return
	 */
	protected boolean validateOrder(JpoMemberOrder order,BindException errors,SysUser loginSysUser){
		
		SysUser user = order.getSysUser();
		log.info("user active is:"+order.getSysUser().getJmiMember().getActive());
		
		if("1".equals(user.getJmiMember().getActive())){
    		
    		errors.reject("checkError.Code.member", new Object[] {},
					LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),
							"checkError.Code.member"));
    		return true;
		}
		
		return false;
	}
}
