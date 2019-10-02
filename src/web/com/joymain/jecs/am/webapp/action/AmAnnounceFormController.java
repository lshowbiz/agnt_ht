package com.joymain.jecs.am.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.am.model.AmAnnounce;
import com.joymain.jecs.am.service.AmAnnounceManager;
import com.joymain.jecs.am.service.AmPermitManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class AmAnnounceFormController extends BaseFormController {
    private AmAnnounceManager amAnnounceManager = null;
    private AmPermitManager amPermitManager = null;
    private List companyPermits = new ArrayList();
    private List agentPermits = new ArrayList();
    private List memberPermits = new ArrayList();
    private List memberAllPermits = new ArrayList();
    private List joymemberPermits = new ArrayList();
    private List teamPermits = new ArrayList();
    private List memberLevelPermits = new ArrayList();
    
	public void setAmAnnounceManager(AmAnnounceManager amAnnounceManager) {
        this.amAnnounceManager = amAnnounceManager;
    }
    public AmAnnounceFormController() {
        setCommandName("amAnnounce");
        setCommandClass(AmAnnounce.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
    	
    	SysUser sessionLogin = SessionLogin.getLoginUser(request);
    
    	String loginCompany = sessionLogin.getCompanyCode();
    	//判断是否AA
        boolean companyReadabled =false;
        if("AA".equalsIgnoreCase(loginCompany)){
        	companyReadabled = true;
        	
        }
        String aaNo = request.getParameter("aaNo");
        AmAnnounce amAnnounce = null;
        
        //读取可看角色
        Set amPermits = new HashSet();
        initAttributes(request);
        String strAction = request.getParameter("strAction");
        String editFlag = request.getParameter("editFlag");	//1代表可以编辑输入框	2代表只能查看
        
        boolean readOnly = true;	//控制发布信息审核信息的可见性
        String buttonKey = "button.back";
        if (StringUtils.isNotEmpty(aaNo)) {
        	log.info("aaNo="+aaNo);
            amAnnounce = amAnnounceManager.getAmAnnounce(aaNo);
            amPermits = amAnnounce.getAmPermits();
            
            
        } else {
            amAnnounce = new AmAnnounce();
            if(!"AA".equalsIgnoreCase(loginCompany)){
            	amAnnounce.setCompanyCode(loginCompany);
            }
        }
        if("editAmAnnounce".equals(strAction)){
        	//editFlag="0";
        	buttonKey = "button.submit";
        	if(amAnnounce.getStatus()==0){
        		//readOnly = false;
        		//editFlag="1";
        	}
        	
        }else if("checkAmAnnounce".equals(strAction)){
        	buttonKey = "button.submit";
        	//readOnly = false;
        	//editFlag="1";//2
        }else if("browserAmAnnounce".equals(strAction)){
        	buttonKey = "button.back";
        	readOnly = false;
        	//editFlag="3";
        	browserAmAnnounce(request,null, amAnnounce);
        }else if("addAmAnnounce".equals(strAction)){
        	//editFlag="1";
        	buttonKey = "button.submit";
        	readOnly = false;
        }
        request.setAttribute("strAction", strAction);
        request.setAttribute("editFlag", editFlag);
        request.setAttribute("readOnly", readOnly);
        request.setAttribute("companyReadabled", companyReadabled);
        request.setAttribute("buttonKey", buttonKey);
        request.setAttribute("amPermits", amPermits);
        return amAnnounce;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        AmAnnounce amAnnounce = (AmAnnounce) command;
        boolean isNew = (amAnnounce.getAaNo() == null);
        Locale locale = request.getLocale();
//        String permit[] = request.getParameterValues("permit");
//        log.info("permit="+permit);
        
        String key = null;
        String strAction = request.getParameter("strAction");
        String view = "redirect:amAnnounces.html?strAction="+strAction;
        if("addAmAnnounce".equals(strAction)){
        	key = "amAnnounce.add";
        	addAmAnnounce(request,response,amAnnounce);
        	view = "redirect:amAnnounces.html?strAction=editAmAnnounce";
        }else if("editAmAnnounce".equals(strAction)){
        	key = "amAnnounce.update";
        	editAmAnnounce(request,response,amAnnounce);
        }else if("checkAmAnnounce".equals(strAction)){
        	//key = "amAnnounce.check";
        	//checkAmAnnounce(request,response,amAnnounce);
        	key = "amAnnounce.update";
        	editAmAnnounce(request,response,amAnnounce);
        }else if("browserAmAnnounce".equals(strAction)){
        	key = "amAnnounce.browser";
        	browserAmAnnounce(request,response,amAnnounce);
        }else if("deleteAmAnnounce".equals(strAction)){
        	key = "amAnnounce.hasChecked";
        	if(amAnnounce.getStatus() == 0){
        		key = "amAnnounce.delete";
        		deleteAmAnnounce(request,response,amAnnounce);
        		view = "redirect:amAnnounces.html?strAction=editAmAnnounce";
        	}else if (amAnnounce.getStatus() == 1){
        		key = "amAnnounce.delete";
        		deleteAmAnnounce(request,response,amAnnounce);
        		view = "redirect:amAnnounces.html?strAction=checkAmAnnounce";
        	}
        	
        	//view = "redirect:amAnnounces.html?strAction=editAmAnnounce";
        }
        
        
        
        
        saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(),key));
        return new ModelAndView(view);
    }
	private void deleteAmAnnounce(HttpServletRequest request,
			HttpServletResponse response, AmAnnounce amAnnounce) {
		// TODO Auto-generated method stub
		amAnnounceManager.removeAmAnnounce(amAnnounce.getAaNo());
	}
	private void browserAmAnnounce(HttpServletRequest request,
			HttpServletResponse response, AmAnnounce amAnnounce) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		amAnnounceManager.browserAmAnnounce(amAnnounce,sessionLogin);
	}
	private void checkAmAnnounce(HttpServletRequest request,
			HttpServletResponse response, AmAnnounce amAnnounce) {
		// TODO Auto-generated method stub
		
		String amPermits = request.getParameter("permits");
		String[] permit = null;
		if(StringUtils.isNotEmpty(amPermits)){
			permit = amPermits.split("~");
		}
		amAnnounce.getAmPermits().clear();
		if(permit!=null && permit.length>0){
			for(int i=0;i<permit.length;i++){
//				AmPermit amPermit = new AmPermit();
				log.info("amPermit="+permit[i]);
//				amPermit.setPermitNo(permit[i]);
				amAnnounce.addAmPermit(amPermitManager.getAmPermit(permit[i]));
			}
		}
		
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		amAnnounce.setCheckUserNo(sessionLogin.getUserCode());
		amAnnounce.setCheckerName(sessionLogin.getUserName());
		amAnnounce.setCheckTime(new Date());
		amAnnounce.setStatus(1);
//		Set set = amAnnounce.getAmPermits();
//		Iterator iterator =set.iterator();
//		if(iterator.hasNext()){
//			AmPermit amPermit = (AmPermit) iterator.next();
//			amAnnounce.addAmPermit(amPermit);
//		}
//		amAnnounce.setAmPermits(amAnnounce.getAmPermits());
		
		amAnnounceManager.saveAmAnnounce(amAnnounce);
	}
	private void editAmAnnounce(HttpServletRequest request,
			HttpServletResponse response, AmAnnounce amAnnounce) {
		// TODO Auto-generated method stub
		
		String amPermits = request.getParameter("permits");
		String[] permit = null;
		if(StringUtils.isNotEmpty(amPermits)){
			permit = amPermits.split("~");
		}
		amAnnounce.getAmPermits().clear();
		if(permit!=null && permit.length>0){
			for(int i=0;i<permit.length;i++){
//				AmPermit amPermit = new AmPermit();
				log.info("amPermit="+permit[i]);
//				amPermit.setPermitNo(permit[i]);
				amAnnounce.addAmPermit(amPermitManager.getAmPermit(permit[i]));
			}
		}
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		amAnnounce.setIssueUserNo(sessionLogin.getUserCode());
		amAnnounce.setIssuerName(sessionLogin.getUserName());
		amAnnounceManager.saveAmAnnounce(amAnnounce);
	}
	private void addAmAnnounce(HttpServletRequest request,
			HttpServletResponse response, AmAnnounce amAnnounce) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		if(!"AA".equalsIgnoreCase(sessionLogin.getCompanyCode())){
			amAnnounce.setCompanyCode(sessionLogin.getCompanyCode());
		}
		String amPermits = request.getParameter("permits");
		String[] permit = null;
		if(StringUtils.isNotEmpty(amPermits)){
			permit = amPermits.split("~");
		}
		amAnnounce.setAaNo(null);
		amAnnounce.setIssueUserNo(sessionLogin.getUserCode());
		amAnnounce.setIssuerName(sessionLogin.getUserName());
		amAnnounce.setCreateTime(new Date());
		amAnnounce.setStatus(0);
		if(permit!=null && permit.length>0){
			for(int i=0;i<permit.length;i++){
//				AmPermit amPermit = new AmPermit();
				log.info("amPermit="+permit[i]);
				
				amAnnounce.addAmPermit(amPermitManager.getAmPermit(permit[i]));
			}
		}
		amAnnounceManager.saveAmAnnounce(amAnnounce);
	}
	public void setAmPermitManager(AmPermitManager amPermitManager) {
		this.amPermitManager = amPermitManager;
	}
	
	public void initAttributes(HttpServletRequest request) throws Exception{
		CommonRecord crm = new CommonRecord();
		
		
		crm.setValue("permitClass", "1");
		companyPermits = amPermitManager.getAmPermitsByCrm(crm, null);
		
		crm.setValue("permitClass", "2");
		agentPermits = amPermitManager.getAmPermitsByCrm(crm, null);
		
		crm.setValue("permitClass", "3");
		//memberPermits = amPermitManager.getAmPermitsByCrm(crm, null);
		
		crm.setValue("permitClass", "6");
		memberAllPermits = amPermitManager.getAmPermitsByCrm(crm, null);
		
		crm.setValue("permitClass", "4");
		joymemberPermits = amPermitManager.getAmPermitsByCrm(crm, null);
		
		crm.setValue("permitClass", "7");
		memberLevelPermits = amPermitManager.getAmPermitsByCrm(crm, null);
		
		request.setAttribute("companyPermits", companyPermits);
		request.setAttribute("agentPermits", agentPermits);
		request.setAttribute("memberPermits", memberPermits);
		request.setAttribute("joymemberPermits", joymemberPermits);
		request.setAttribute("memberAllPermits", memberAllPermits);
		request.setAttribute("memberLevelPermits", memberLevelPermits);

		crm.setValue("permitClass", "5");
		teamPermits = amPermitManager.getAmPermitsByCrm(crm, null);

		request.setAttribute("teamPermits", teamPermits);
		
	}
}
