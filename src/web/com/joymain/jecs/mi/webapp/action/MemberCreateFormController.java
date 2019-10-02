package com.joymain.jecs.mi.webapp.action;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.mi.model.JmiLinkRef;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiRecommendRef;
import com.joymain.jecs.mi.service.JmiLinkRefManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.service.JmiRecommendRefManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysBankManager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class MemberCreateFormController extends BaseFormController {
    private JmiMemberManager jmiMemberManager = null;
    private SysBankManager sysBankManager;
    private AlCountryManager alCountryManager;
    private JmiRecommendRefManager jmiRecommendRefManager;
    private JmiLinkRefManager jmiLinkRefManager;
    private AlCompanyManager alCompanyManager;
	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}
	public void setJmiLinkRefManager(JmiLinkRefManager jmiLinkRefManager) {
		this.jmiLinkRefManager = jmiLinkRefManager;
	}
	public void setJmiRecommendRefManager(
			JmiRecommendRefManager jmiRecommendRefManager) {
		this.jmiRecommendRefManager = jmiRecommendRefManager;
	}
	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}
	public void setSysBankManager(SysBankManager sysBankManager) {
		this.sysBankManager = sysBankManager;
	}
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
        this.jmiMemberManager = jmiMemberManager;
    }
    public MemberCreateFormController() {
        setCommandName("jmiMember");
        setCommandClass(JmiMember.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {

    	SysUser defSysUser=SessionLogin.getLoginUser(request);
    	JmiMember jmiMember = new JmiMember();
    	
    	String countryCode="";
    	//找出可用银行
        List sysBankList=sysBankManager.getSysBankByCompanyCode(defSysUser.getCompanyCode());
        request.setAttribute("sysBankList", sysBankList);
        if("M".equals(defSysUser.getUserType())){
        	JmiMember tmpMember = jmiMemberManager.getJmiMember(defSysUser.getUserCode());
        	jmiMember.setCountryCode(tmpMember.getCountryCode());
        	countryCode=tmpMember.getCountryCode();
        	

        }else if("C".equals(defSysUser.getUserType())){
        	if(!"AA".equals(defSysUser.getCompanyCode())){
    			AlCompany alCompany = alCompanyManager.getAlCompanyByCode(defSysUser.getCompanyCode());
    			jmiMember.setCountryCode(alCompany.getCountryCode());
        		countryCode=alCompany.getCountryCode();
        	}
        }

        AlCountry alCountry = new AlCountry();//获取地区
    	alCountry = alCountryManager.getAlCountryByCode(countryCode);
    	alCountry.getAlStateProvinces().iterator();
    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
    	
		JmiLinkRef miLinkRef = new JmiLinkRef();
    	miLinkRef.setLinkJmiMember(new JmiMember());
    	
    	JmiRecommendRef miRecommendRef = new JmiRecommendRef();
    	miRecommendRef.setRecommendJmiMember(new JmiMember());
    	
    	jmiMember.setJmiRecommendRef(miRecommendRef);
    	jmiMember.setJmiLinkRef(miLinkRef);
    	
        jmiMember.setSysUser(new SysUser());
        
        //保存发货信息
    	String shippingFirstName=request.getParameter("shippingFirstName");
    	String shippingLastName=request.getParameter("shippingLastName");
    	String shippingProvince=request.getParameter("shippingProvince");
    	String shippingCity=request.getParameter("shippingCity");
    	String shippingAddress=request.getParameter("shippingAddress");
    	String shippingPostalcode=request.getParameter("shippingPostalcode");
    	String shippingPhone=request.getParameter("shippingPhone");
    	String shippingEmail=request.getParameter("shippingEmail");
    	String shippingMobiletele=request.getParameter("shippingMobiletele");
    	String shippingDistrict=request.getParameter("shippingDistrict");
        
    	request.setAttribute("shippingFirstName", shippingFirstName==null?"":shippingFirstName);
    	request.setAttribute("shippingLastName", shippingLastName==null?"":shippingLastName);
    	request.setAttribute("shippingProvince", shippingProvince==null?"":shippingProvince);
    	request.setAttribute("shippingCity", shippingCity==null?"":shippingCity);
    	request.setAttribute("shippingAddress", shippingAddress==null?"":shippingAddress);
    	request.setAttribute("shippingPostalcode", shippingPostalcode==null?"":shippingPostalcode);
    	request.setAttribute("shippingPhone", shippingPhone==null?"":shippingPhone);
    	request.setAttribute("shippingEmail", shippingEmail==null?"":shippingEmail);
    	request.setAttribute("shippingMobiletele", shippingMobiletele==null?"":shippingMobiletele);
    	request.setAttribute("shippingDistrict", shippingDistrict==null?"":shippingDistrict);
    	
    	if("TW".equals(defSysUser.getCompanyCode())){
//    		台湾预设省
        	String twProvince=ConfigUtil.getConfigValue(defSysUser.getCompanyCode().toUpperCase(), "tw.province");
        	jmiMember.setProvince(twProvince);
        	jmiMember.setCommProvince(twProvince);
    	}
        if(RequestUtil.isMobileRequest(request)){
        	this.setFormView("/mobile/mi/memberCreateForm");
        }else{
        	this.setFormView("/mi/memberCreateForm");
        }
        return jmiMember;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

    	SysUser defSysUser=SessionLogin.getLoginUser(request);
    	
        JmiMember jmiMember = (JmiMember) command;    

        
		//修改密码
    	String password1 =jmiMember.getSysUser().getPassword();
    	
    	
        String password2 =jmiMember.getSysUser().getPassword2();
        
        
        
		String password1Confirm=request.getParameter("password1Confirm");
		String password2Confirm=request.getParameter("password2Confirm");
    	

		if("TW".equals(defSysUser.getCompanyCode())||"JP".equals(defSysUser.getCompanyCode())){
        	jmiMember.getSysUser().setPassword2(password1);
        	password2=password1;
        	password2Confirm=password1;
        }

		
		if(!StringUtil.isEmpty(password1)){
			if(!password1.equals(password1Confirm)){
				errors.reject("error.password.not.accord");
				return showForm(request, response, errors);
			}	
		}
		if(!StringUtil.isEmpty(password2)){
			if(!password2.equals(password2Confirm)){
				errors.reject("miMember.pwd2.isNotEqual");
				return showForm(request, response, errors);
			}

		}

    	if(jmiMemberManager.getCheckMiMember(request, jmiMember, errors, defSysUser.getDefCharacterCoding(),"1")){
    		return showForm(request, response, errors);
    	}  
    	
    	
    	/*发货信息*/
//    	String shippingFirstName=request.getParameter("shippingFirstName");
//    	String shippingLastName=request.getParameter("shippingLastName");
//    	String shippingProvince=request.getParameter("shippingProvince");
//    	String shippingCity=request.getParameter("shippingCity");
//    	String shippingAddress=request.getParameter("shippingAddress");
//    	String shippingPostalcode=request.getParameter("shippingPostalcode");
//    	String shippingPhone=request.getParameter("shippingPhone");
//    	String shippingEmail=request.getParameter("shippingEmail");
//    	String shippingMobiletele=request.getParameter("shippingMobiletele");
//    	String shippingDistrict=request.getParameter("shippingDistrict");
//    	
//    	JmiAddrBook jmiAddrBook=new JmiAddrBook();
//    	jmiAddrBook.setFirstName(shippingFirstName);
//    	jmiAddrBook.setLastName(shippingLastName);
//    	jmiAddrBook.setProvince(shippingProvince);
//    	jmiAddrBook.setCity(shippingCity);
//    	jmiAddrBook.setAddress(shippingAddress);
//    	jmiAddrBook.setPostalcode(shippingPostalcode);
//    	jmiAddrBook.setPhone(shippingPhone);
//    	jmiAddrBook.setEmail(shippingEmail);
//    	jmiAddrBook.setMobiletele(shippingMobiletele);
//    	jmiAddrBook.setDistrict(shippingDistrict);
//    	jmiMember.getJmiAddrBooks().add(jmiAddrBook);
//    	jmiAddrBook.setJmiMember(jmiMember);
    	
    	
    	
    	//推荐人不存在
        JmiRecommendRef miRecommendRef = jmiRecommendRefManager.getJmiRecommendRef(jmiMember.getJmiRecommendRef().getRecommendJmiMember().getUserCode());
		if (miRecommendRef == null) {
			errors.reject("miRecommendRef.isNotFound", new Object[] {},LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(),"miRecommendRef.isNotFound"));
			return showForm(request, response, errors);
		}
		
		if("M".equals(defSysUser.getUserType())){
			
			JmiRecommendRef defRecommendRef = jmiRecommendRefManager.getJmiRecommendRef(defSysUser.getUserCode());
			//判断推荐人是否在当前会员下
			String rdefIndex=defRecommendRef.getTreeIndex();
			String rIndex=miRecommendRef.getTreeIndex();
			if(rIndex.length()<rdefIndex.length()){
				errors.reject("miRecommendRef.isNotFound");
				return showForm(request, response, errors);
			}
			String rmemberIndexTmp = StringUtil.getLeft(rIndex, rdefIndex.length());
			if(!rdefIndex.equals(rmemberIndexTmp)){//不为会员的下级组织
				errors.reject("miRecommendRef.isNotFound");
				return showForm(request, response, errors);
			}
			
		}
		
		// 判断接点人是否存在
		JmiLinkRef miLinkRef =jmiLinkRefManager.getJmiLinkRef(jmiMember.getJmiLinkRef().getLinkJmiMember().getUserCode());
		if (miLinkRef == null) {
			errors.reject("miLinkRef.isNotFound", new Object[] {},LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(),"miLinkRef.isNotFound"));
			return showForm(request, response, errors);
		}

		// 接点人不是推荐人的下线
		String rTreeIndex = miRecommendRef.getJmiMember().getJmiLinkRef().getTreeIndex();
		String lTreeIndex = miLinkRef.getTreeIndex();
		if (lTreeIndex.length() < rTreeIndex.length() || !rTreeIndex.equals(StringUtil.getLeft(lTreeIndex, rTreeIndex.length()))) {
			errors.reject("miLinkRefMiRecommendRef.isNotEquals", new Object[] {},LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(),"miLinkRefMiRecommendRef.isNotEquals"));
			return showForm(request, response, errors);
		}
		
/*		//检查接点体系是否已满
		int maxLink = 6;//生成最大接点人数
		CommonRecord crm = new CommonRecord();
        Pager pager = new Pager("jmiMemberListTable",request, 0);
		crm.addField("linkNo",  jmiMember.getJmiLinkRef().getLinkJmiMember().getUserCode());
		if(miLinkRef.getNum().compareTo(new BigDecimal(0))!=0){
			maxLink=miLinkRef.getNum().intValue()+2;
		}
		List miLinkRefs = jmiMemberManager.getJmiMembersByCrm(crm, pager);
		if(miLinkRefs.size()<6){
			maxLink=6;
		}
		if (miLinkRefs.size() >= maxLink) {// 接点体系最多只有6个下属部门
			errors.reject("miLinkRef.isFull", new Object[] {},LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(),"miLinkRef.isFull"));
			return showForm(request, response, errors);
		}*/

    	
		
		jmiMember.getSysUser().setPassword(StringUtil.encodePassword(password1, "md5"));
		jmiMember.getSysUser().setPassword2(StringUtil.encodePassword(password2, "md5"));
		//设置汇款银行
		if("CN".equals(defSysUser.getCompanyCode())){
			jmiMember.setPbNo("bankprivate001-wfx");
		}
		
    	try {
			String userCode=jmiMemberManager.saveNewJmiMember(jmiMember, defSysUser,request);
			this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
			return new ModelAndView("redirect:miMemberCreated.html?strAction=memberCreate&userCode="+userCode);
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			System.out.println(sw.toString());
			this.saveMessage(request, LocaleUtil.getLocalText(e.getMessage()));
		}

        

		return showForm(request, response, errors);
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
