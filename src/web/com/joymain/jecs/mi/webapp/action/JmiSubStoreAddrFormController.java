package com.joymain.jecs.mi.webapp.action;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiRecommendRef;
import com.joymain.jecs.mi.model.JmiSubStore;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.service.JmiRecommendRefManager;
import com.joymain.jecs.mi.service.JmiSubStoreManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JmiSubStoreAddrFormController extends BaseFormController {
    private JmiMemberManager jmiMemberManager;
    private JmiSubStoreManager jmiSubStoreManager = null;

    private AlCountryManager alCountryManager;

	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
    private JmiRecommendRefManager jmiRecommendRefManager;
    public void setJmiRecommendRefManager(
			JmiRecommendRefManager jmiRecommendRefManager) {
		this.jmiRecommendRefManager = jmiRecommendRefManager;
	}
    
    public void setJmiSubStoreManager(JmiSubStoreManager jmiSubStoreManager) {
        this.jmiSubStoreManager = jmiSubStoreManager;
    }
    public JmiSubStoreAddrFormController() {
        setCommandName("jmiSubStore");
        setCommandClass(JmiSubStore.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
    	SysUser defSysUser=SessionLogin.getLoginUser(request);
        String id = request.getParameter("id");
        JmiSubStore jmiSubStore = null;

        if (!StringUtils.isEmpty(id)) {
            jmiSubStore = jmiSubStoreManager.getJmiSubStore(id);
        } else {
            jmiSubStore = new JmiSubStore();
        }
        if(null==jmiSubStore.getId()){
            if("M".equals(defSysUser.getUserType())){
            	jmiSubStore.setJmiMember(jmiMemberManager.getJmiMember(defSysUser.getJmiMember().getUserCode()));
            }else if("C".equals(defSysUser.getUserType())){
            	jmiSubStore.setJmiMember(new JmiMember());
            }
        }
        
    	String countryCode=null;
    	
    	JmiMember jmiMember=null;
    	if("M".equals(defSysUser.getUserType())){
        	jmiMember=jmiMemberManager.getJmiMember(defSysUser.getUserCode());
        	countryCode=jmiMember.getCompanyCode();
        }else{
        	countryCode=defSysUser.getCompanyCode();
        	
        }
    	
        AlCountry alCountry = new AlCountry();//获取地区
    	alCountry = alCountryManager.getAlCountryByCode(countryCode);
    	alCountry.getAlStateProvinces().iterator();
    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
    	

    	String strAction=request.getParameter("strAction");
    	if("editJmiSubStoreAddr".equals(strAction)){
    		if("M".equals(defSysUser.getUserType())&&"0".equals(jmiSubStore.getAddrConfirm())){
            	request.setAttribute("modifyStatus", "yes");
    		}else if("M".equals(defSysUser.getUserType())&&"1".equals(jmiSubStore.getAddrConfirm())){
            	request.setAttribute("modifyStatus", "no");
    		}
        	if("C".equals(defSysUser.getUserType())){
        		request.setAttribute("modifyStatus", "yes");
        	}
    	}
    	if("viewJmiSubStoreAddr".equals(strAction)){
        	request.setAttribute("modifyStatus", "no");
    		
    	}
    	if("M".equals(defSysUser.getUserType())){
    		request.setAttribute("provinceDisabled", "true");
    	}
    		request.setAttribute("recommendStoreName", jmiMemberManager.getJmiMember(jmiSubStore.getJmiMember().getSubRecommendStore()).getMiUserName());

    		
        return jmiSubStore;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

    	SysUser defSysUser=SessionLogin.getLoginUser(request);

    	String strAction=request.getParameter("strAction");

        JmiSubStore jmiSubStore = (JmiSubStore) command;
        

        
        if("addrCheckJmiSubStore".equals(strAction)||"addrUnCheckJmiSubStore".equals(strAction)||"addrConfirmJmiSubStore".equals(strAction)||"addrUnConfirmJmiSubStore".equals(strAction)){
        	try {
        		if("addrCheckJmiSubStore".equals(strAction)){
                	jmiSubStoreManager.addrCheckJmiSubStore(jmiSubStore.getId().toString(), defSysUser, "1");
                }else if("addrUnCheckJmiSubStore".equals(strAction)){
                	jmiSubStoreManager.addrCheckJmiSubStore(jmiSubStore.getId().toString(), defSysUser, "3");
                }else if("addrConfirmJmiSubStore".equals(strAction)){
                	jmiSubStoreManager.addrConfirmJmiSubStore(jmiSubStore.getId().toString(), defSysUser, "1");
                }else if("addrUnConfirmJmiSubStore".equals(strAction)){
                	jmiSubStoreManager.addrConfirmJmiSubStore(jmiSubStore.getId().toString(), defSysUser, "2");
                }
        		saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.operaterSuccess"));
			} catch (Exception e) {
				saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.operaterFail"));
			}
	        return new ModelAndView("redirect:jmiSubStoreAddrs.html?strAction=jmiSubStoreAddrs");
			
        }
        
        
        
    	String subRecommendStore=jmiSubStore.getJmiMember().getSubRecommendStore();
    	
    	
        JmiMember jmiMember=jmiMemberManager.getJmiMember(jmiSubStore.getJmiMember().getUserCode());
        if(null==jmiMember){
			errors.reject("miMember.notFound");
    		return showForm(request, response, errors);
        	
        }
        jmiSubStore.setJmiMember(jmiMember);

        if("addJmiSubStore".equals(strAction)){
    	    
    		jmiSubStore.getJmiMember().setSubRecommendStore(subRecommendStore);
    	}
       

		if(checkJmiSubStore(jmiSubStore, errors,request)){
    		return showForm(request, response, errors);
		}

    	
    	
    	if("addJmiSubStore".equals(strAction)){
    	    
    		jmiSubStore.getJmiMember().setSubRecommendStore(jmiMember.getSubRecommendStore());
    		if(!StringUtil.isEmpty(jmiSubStore.getJmiMember().getSubStoreStatus())){//如果申请过，不能再次申请 
    			errors.reject("subRecommendStore.apply.exist");
        		return showForm(request, response, errors);
    		}
    	}
//    	if("editJmiSubStore".equals(strAction)){
//    		if("M".equals(defSysUser.getUserType())&&"1".equals(jmiSubStore.getJmiMember().getSubStoreStatus())){//待审不审状态才可以修改
//    			errors.reject("alCharacterValue.updateFail");
//        		return showForm(request, response, errors);
//    		}
//    	}
    	
    	try {
//    		if("M".equals(defSysUser.getUserType())){
//    		}

        	if("addJmiSubStore".equals(strAction)){
        		jmiSubStoreManager.saveJmiSubStoreCheck(jmiSubStore,jmiMember);
        	}else{
        		jmiSubStore.setAddrCheck("0");
        		jmiSubStore.setEditTime(new Date());
        		jmiSubStoreManager.saveJmiSubStore(jmiSubStore);
        		
        	}
    		this.saveMessage(request, this.getText("sys.message.updateSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
    		this.saveMessage(request, this.getText("sys.message.updateFail"));
		}
    	
        
        


        return new ModelAndView("redirect:jmiSubStoreAddrs.html?strAction=jmiSubStoreAddrs");
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
    
    private boolean checkJmiSubStore(JmiSubStore jmiSubStore,BindException errors,HttpServletRequest request){
    	boolean isNotPass=false;
    	if(StringUtil.isEmpty(jmiSubStore.getJmiMember().getSubRecommendStore())){
			errors.rejectValue("jmiMember.subRecommendStore", "isNotNull",new Object[] {this.getText("miMember.subRecommendStore") }, "");
			isNotPass = true;
    	}else{
    		JmiMember jmiMemberSubRecommendStore=jmiMemberManager.getJmiMember(jmiSubStore.getJmiMember().getSubRecommendStore());
        	if(null==jmiMemberSubRecommendStore){
    			errors.reject("miMember.subRecommendStore.notExist");
    			isNotPass = true;
        	}else if(checkJmiRecommend(jmiSubStore)){
    			errors.reject("miMember.subRecommendStore.notExist");
    			isNotPass = true;
        	}
//        	else if("1".equals(jmiMemberSubRecommendStore.getActive())){
//    			errors.reject("miMember.subRecommendStore.active1");
//    			isNotPass = true;
//        	}else if(null!=jmiMemberSubRecommendStore.getExitDate()){
//    			errors.reject("miMember.subRecommendStore.exited");
//    			isNotPass = true;
//        	}else if(!"4".equals(jmiMemberSubRecommendStore.getCardType())){
//    			errors.reject("miMember.subRecommendStore.card4.not");
//    			isNotPass = true;
//        	}
    	}

    	if(null==jmiSubStore.getProvince()){
			errors.rejectValue("province", "isNotNull",new Object[] {this.getText("subStore.province") }, "");
			isNotPass = true;
    	}
    	if(null==jmiSubStore.getCity()){
			errors.rejectValue("city", "isNotNull",new Object[] {this.getText("subStore.city") }, "");
			isNotPass = true;
    	}
//    	if(null==jmiSubStore.getDistrict()){
//			errors.rejectValue("district", "isNotNull",new Object[] {this.getText("subStore.district") }, "");
//			isNotPass = true;
//    	}
    	if(StringUtil.isEmpty(jmiSubStore.getAddress())){
			errors.rejectValue("address", "isNotNull",new Object[] {this.getText("subStore.address") }, "");
			isNotPass = true;
    	}
//    	if(StringUtil.isEmpty(jmiSubStore.getEmail())){
//			errors.rejectValue("email", "isNotNull",new Object[] {this.getText("subStore.email") }, "");
//			isNotPass = true;
//    	}
//    	if(StringUtil.isEmpty(jmiSubStore.getPhone())){
//			errors.rejectValue("phone", "isNotNull",new Object[] {this.getText("subStore.phone") }, "");
//			isNotPass = true;
//    	}
    	if(StringUtil.isEmpty(jmiSubStore.getMobiletele())){
			errors.rejectValue("mobiletele", "isNotNull",new Object[] {this.getText("subStore.mobiletele") }, "");
			isNotPass = true;
    	}else {
    		Pattern pattern = Pattern.compile("^[1]([0-9]{10})$");
    		Matcher matcher = pattern.matcher(jmiSubStore.getMobiletele());
    		if(!matcher.find()){
    			errors.rejectValue("mobiletele", "errors.phone",new Object[] {this.getText("miMember.mobiletele") }, "");
    			isNotPass = true;
    		}
    	}
//    	if(!StringUtil.isEmpty(jmiSubStore.getPhone())&&jmiSubStore.getPhone().length()>11){
//			errors.rejectValue("phone", "errors.phone",new Object[] {this.getText("subStore.phone") }, "");
//			isNotPass = true;
//    	}

    	return isNotPass;
    }
    
    private boolean checkJmiRecommend(JmiSubStore jmiSubStore){
    	boolean isNotPass=false;
    	JmiRecommendRef defRecommendRef = jmiRecommendRefManager.getJmiRecommendRef(jmiSubStore.getJmiMember().getSubRecommendStore());
		//判断推荐人是否在当前会员下
		String rdefIndex=defRecommendRef.getTreeIndex();
		String rIndex=jmiSubStore.getJmiMember().getJmiRecommendRef().getTreeIndex();
		if(rIndex.length()<rdefIndex.length()){
			isNotPass = true;
		}
		String rmemberIndexTmp = StringUtil.getLeft(rIndex, rdefIndex.length());
		if(!rdefIndex.equals(rmemberIndexTmp)){//不为会员的下级组织
			isNotPass = true;
		}
		if(rdefIndex.equals(rIndex)){
			isNotPass = true;
		}
    	return isNotPass;
    }
    
    

}
