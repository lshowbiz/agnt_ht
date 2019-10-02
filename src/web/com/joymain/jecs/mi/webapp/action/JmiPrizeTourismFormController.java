package com.joymain.jecs.mi.webapp.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.mi.model.CheckBoxUtil;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiPrizeTourism;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.service.JmiPrizeTourismManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JmiPrizeTourismFormController extends BaseFormController {
    private JmiPrizeTourismManager jmiPrizeTourismManager = null;
    private JmiMemberManager jmiMemberManager = null;
    private AlCountryManager alCountryManager;
    
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}
	public void setJmiPrizeTourismManager(JmiPrizeTourismManager jmiPrizeTourismManager) {
        this.jmiPrizeTourismManager = jmiPrizeTourismManager;
    }
    public JmiPrizeTourismFormController() {
        setCommandName("jmiPrizeTourism");
        setCommandClass(JmiPrizeTourism.class);
    }

    
    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
    	SysUser defSysUser=SessionLogin.getLoginUser(request);

    	String countryCode=null;
    	
    	if("M".equals(defSysUser.getUserType())){
        	JmiMember jmiMember=jmiMemberManager.getJmiMember(defSysUser.getUserCode());
        	countryCode=jmiMember.getCompanyCode();
        }else{
        	countryCode=defSysUser.getCompanyCode();
        	
        }
    	
        AlCountry alCountry = new AlCountry();//获取地区
    	alCountry = alCountryManager.getAlCountryByCode(countryCode);
    	alCountry.getAlStateProvinces().iterator();
    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
    	
        String prizeTouismId = request.getParameter("prizeTouismId");
        String userCode = request.getParameter("userCode");
        JmiPrizeTourism jmiPrizeTourism = null;

        if (!StringUtils.isEmpty(prizeTouismId)) {
            jmiPrizeTourism = jmiPrizeTourismManager.getJmiPrizeTourism(Long.valueOf(prizeTouismId));
        } else {
            jmiPrizeTourism = new JmiPrizeTourism();
        }
        String passStar = jmiPrizeTourismManager.getPassStarByUserCode(userCode);
        if(StringUtils.isEmpty(passStar)){
        	passStar = "0";
        }
        jmiPrizeTourism.setPassStar(Integer.valueOf(passStar));//奖衔级别

        List avoidcertainfoodList = new ArrayList();
        List peerAvoidcertainfoodList = new ArrayList();
        CheckBoxUtil checkBoxUtil = null;
        CheckBoxUtil peerCheckBoxUtil = null;
       
        Map<String, String[]> map = Constants.sysListMap.get("jmiprizetourism.avoidcertainfood");//餐饮忌口  复选框
		if(map==null || map.isEmpty()){
			avoidcertainfoodList = null;
			peerAvoidcertainfoodList = null;
		}else{
			Set codes = map.entrySet();
			if (codes != null) {
				Iterator iter = codes.iterator();
				while (iter.hasNext()) {
					Map.Entry entry=(Map.Entry)iter.next();
					String[] values = (String[])entry.getValue();
					String key = (String)entry.getKey();
					checkBoxUtil  = new CheckBoxUtil();
					checkBoxUtil.setcId(key);
					checkBoxUtil.setcValue(values[0]);
					peerCheckBoxUtil  = new CheckBoxUtil();
					peerCheckBoxUtil.setcId(key);
					peerCheckBoxUtil.setcValue(values[0]);
					
					if(null!=jmiPrizeTourism && null!=jmiPrizeTourism.getAvoidcertainfood()){
						if(MeteorUtil.useList(jmiPrizeTourism.getAvoidcertainfood().split(","), key)){
							checkBoxUtil.setChecked("1");
						}
					}
					if(null!=jmiPrizeTourism && null!=jmiPrizeTourism.getPeeravoidcertainfood()){
						if(MeteorUtil.useList(jmiPrizeTourism.getPeeravoidcertainfood().split(","), key)){
							peerCheckBoxUtil.setChecked("1");
						}
					}
					
					avoidcertainfoodList.add(checkBoxUtil);
					peerAvoidcertainfoodList.add(peerCheckBoxUtil);
				}
			}
		}

		request.setAttribute("avoidcertainfoodList", avoidcertainfoodList);
		request.setAttribute("peerAvoidcertainfoodList", peerAvoidcertainfoodList);

        return jmiPrizeTourism;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JmiPrizeTourism jmiPrizeTourism = (JmiPrizeTourism) command;
        
        String userCode=null;
        userCode=jmiPrizeTourism.getUserCode();
        JmiMember jmiMember=jmiMemberManager.getJmiMember(userCode);
    	if(null==jmiMember){//会员不存在
			errors.reject("miMember.notFound");
    		return showForm(request, response, errors);
    	}
        
        boolean isNew = (jmiPrizeTourism.getUserCode() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJmiPrizeTourism".equals(strAction)  ) {
		jmiPrizeTourismManager.removeJmiPrizeTourism(jmiPrizeTourism.getPrizeTouismId());
		key="删除成功";
	}else{
		jmiPrizeTourismManager.saveJmiPrizeTourism(jmiPrizeTourism);
		key="更新成功";
	}
	saveMessage(request, getText(SessionLogin.getLoginUser(request)
			.getDefCharacterCoding(), key));
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
}
