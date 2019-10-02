package com.joymain.jecs.mi.webapp.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.bd.service.JbdMemberLinkCalcHistManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiStore;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.service.JmiStoreManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JmiStoreFormController extends BaseFormController {
    private JmiStoreManager jmiStoreManager = null;
    private BdPeriodManager bdPeriodManager;
    private JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager;
    private JmiMemberManager jmiMemberManager;
    private AlCountryManager alCountryManager;
    public void setJbdMemberLinkCalcHistManager(
			JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager) {
		this.jbdMemberLinkCalcHistManager = jbdMemberLinkCalcHistManager;
	}
	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
	public void setJmiStoreManager(JmiStoreManager jmiStoreManager) {
        this.jmiStoreManager = jmiStoreManager;
    }
    public JmiStoreFormController() {
        setCommandName("jmiStore");
        setCommandClass(JmiStore.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
    	SysUser defSysUser=SessionLogin.getLoginUser(request);
    	
    	
        String id = request.getParameter("id");
        JmiStore jmiStore = null;
        
        
        if (!StringUtils.isEmpty(id)) {
            jmiStore = jmiStoreManager.getJmiStore(id);
        } else {
            jmiStore = new JmiStore();
        }
        if(null==jmiStore.getId()){
            if("M".equals(defSysUser.getUserType())){
            	jmiStore.setJmiMember(jmiMemberManager.getJmiMember(defSysUser.getJmiMember().getUserCode()));
            	String honorStar=jbdMemberLinkCalcHistManager.getLastHonorStar(defSysUser.getUserCode());
            	if(StringUtil.isEmpty(honorStar)){
            		jmiStore.setHonorStar(0);
            	}else{
                	jmiStore.setHonorStar(new Integer(honorStar));
            	}
            }else if("C".equals(defSysUser.getUserType())){
            	jmiStore.setJmiMember(new JmiMember());
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
    	
    	
    	
    	
    	if("editJmiStore".equals(strAction)||"addJmiStore".equals(strAction)){
        	request.setAttribute("modifyStatus", "yes");
    	}
    	if("viewJmiStore".equals(strAction)){
        	request.setAttribute("modifyStatus", "no");
    	}
        return jmiStore;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

    	SysUser defSysUser=SessionLogin.getLoginUser(request);
        JmiStore jmiStore = (JmiStore) command;
    	String strAction=request.getParameter("strAction");
        
        
    	
    	
    	
        
        if("checkJmiStore".equals(strAction)||"unCheckJmiStore".equals(strAction)||"confirmJmiStore".equals(strAction)||"unConfirmJmiStore".equals(strAction)){
        	try {
        		if("checkJmiStore".equals(strAction)){
        			jmiStoreManager.checkJmiStore(jmiStore, defSysUser, "1");
                }else if("unCheckJmiStore".equals(strAction)){
                	jmiStoreManager.checkJmiStore(jmiStore, defSysUser, "3");
                }else if("confirmJmiStore".equals(strAction)){
                	jmiStoreManager.confirmJmiStore(jmiStore, defSysUser, "1");
                }else if("unConfirmJmiStore".equals(strAction)){
                	jmiStoreManager.confirmJmiStore(jmiStore, defSysUser, "2");
                }
        		saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.operaterSuccess"));
			} catch (Exception e) {
				saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.operaterFail"));
			}
	        return new ModelAndView("redirect:jmiStores.html?strAction=jmiStores");
			
        }
    	
    	
    	
    	
    	  JmiMember jmiMember=jmiMemberManager.getJmiMember(jmiStore.getJmiMember().getUserCode());
          if(null==jmiMember){
	  			errors.reject("miMember.notFound");
	      		return showForm(request, response, errors);
          }
          
    	if("addJmiStore".equals(strAction)){
    		
    		JmiStore curJmiStore=jmiStoreManager.getJmiStoreByUserCode(jmiStore.getJmiMember().getUserCode());
    		if(null!=curJmiStore){//如果申请过，不能再次申请 
    			errors.reject("subRecommendStore.apply.exist");
        		return showForm(request, response, errors);
    		}
    	}
    	
        jmiStore.setJmiMember(jmiMember);

        String honorStar=  jbdMemberLinkCalcHistManager.getLastHonorStar(jmiStore.getJmiMember().getUserCode());
        if(!StringUtil.isEmpty(honorStar)){
            jmiStore.setHonorStar(new Integer(honorStar));
        }
  		if(checkJmiStore(jmiStore, errors,request)){
      		return showForm(request, response, errors);
  		}
        
    	if("addJmiStore".equals(strAction)){
    		if(null!=jmiStoreManager.getJmiStoreByUserCode(jmiMember.getUserCode())){//如果申请过，不能再次申请 
    			errors.reject("subRecommendStore.apply.exist");
        		return showForm(request, response, errors);
    		}
    	}
    	Date curDate=new Date();
    	try {
    		if("editJmiStore".equals(strAction)){
    			jmiStore.setEditTime(curDate);
    		}else if("addJmiStore".equals(strAction)){
    			jmiStore.setCreateTime(curDate);
    			jmiStore.setEditTime(curDate);
    			jmiStore.setCheckStatus("0");
    			jmiStore.setConfirmStatus("0");
    			jmiStore.setCreateUser(defSysUser.getUserCode());
    		}
    		jmiStoreManager.saveJmiStore(jmiStore);
    		this.saveMessage(request, this.getText("sys.message.updateSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
    		this.saveMessage(request, this.getText("sys.message.updateFail"));
		}
        
   

        return new ModelAndView(getSuccessView());
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	
	
	

    private boolean checkJmiStore(JmiStore jmiStore,BindException errors,HttpServletRequest request){
    	boolean isNotPass=false;

    	if(StringUtil.isEmpty(jmiStore.getSubStoreAddr())){
			errors.rejectValue("subStoreAddr", "isNotNull",new Object[] {this.getText("store.subStoreAddr") }, "");
			isNotPass = true;
    	}

    	if(StringUtil.isEmpty(jmiStore.getMobiletele())){
			errors.rejectValue("mobiletele", "isNotNull",new Object[] {this.getText("subStore.mobiletele") }, "");
			isNotPass = true;
    	}
    	
    	if(null==jmiStore.getProvince()){
			errors.rejectValue("province", "isNotNull",new Object[] {this.getText("store.storeAddr") }, "");
			isNotPass = true;
    	}
    	/*if(null==jmiStore.getCity()){
			errors.rejectValue("city", "isNotNull",new Object[] {this.getText("subStore.city") }, "");
			isNotPass = true;
    	}
    	
    	if(StringUtil.isEmpty(jmiStore.getAddress())){
			errors.rejectValue("address", "isNotNull",new Object[] {this.getText("store.storeAddr") }, "");
			isNotPass = true;
    	}*/

    	if(null==jmiStore.getHonorStar()){
			errors.rejectValue("honorStar", "isNotNull",new Object[] {"奖街" }, "");
			isNotPass = true;
    	}
    	
    	
    	if(!"agree".equals(request.getParameter("agree"))){
			errors.reject("miMember.notAgree");
			isNotPass = true;
    	}
    	return isNotPass;
    }
	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}
	
	
	
	
}
