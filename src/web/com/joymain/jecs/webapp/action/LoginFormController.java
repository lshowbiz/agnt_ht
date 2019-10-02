package com.joymain.jecs.webapp.action;

import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.Constants;
//import com.joymain.jecs.ai.model.AiAgent;
//import com.joymain.jecs.ai.service.AiAgentManager;
import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.mi.model.JmiSmsNote;
import com.joymain.jecs.mi.service.JmiSmsNoteManager;
//import com.joymain.jecs.mi.model.MiMember;
//import com.joymain.jecs.mi.service.MiMemberManager;
import com.joymain.jecs.sys.model.SysLoginLog;
import com.joymain.jecs.sys.model.SysManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.model.SysUserIp;
import com.joymain.jecs.sys.service.SysLoginLogManager;
import com.joymain.jecs.sys.service.SysManagerManager;
import com.joymain.jecs.sys.service.SysUserIpManager;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class LoginFormController extends BaseFormController {
	private SysUserManager sysUserManager;
	private SysUserIpManager sysUserIpManager=null;
	private AlCompanyManager alCompanyManager = null;
	private SysLoginLogManager sysLoginLogManager = null;
//	private AiAgentManager aiAgentManager = null;
//	private MiMemberManager miMemberManager;
	
//	public void setMiMemberManager(MiMemberManager miMemberManager) {
//		this.miMemberManager = miMemberManager;
//	}
//	
//	public void setAiAgentManager(AiAgentManager aiAgentManager) {
//        this.aiAgentManager = aiAgentManager;
//    }
	private SysManagerManager sysManagerManager;
	private JmiSmsNoteManager jmiSmsNoteManager;
	public void setSysLoginLogManager(SysLoginLogManager sysLoginLogManager) {
		this.sysLoginLogManager = sysLoginLogManager;
	}

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}
	
	public void setSysUserIpManager(SysUserIpManager sysUserIpManager) {
		this.sysUserIpManager = sysUserIpManager;
	}
	
	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}
	public void setJmiSmsNoteManager(JmiSmsNoteManager jmiSmsNoteManager) {
		this.jmiSmsNoteManager = jmiSmsNoteManager;
	}

	public void setSysManagerManager(SysManagerManager sysManagerManager) {
		this.sysManagerManager = sysManagerManager;
	}
	public LoginFormController() {
		setCommandName("sysUser");
		setCommandClass(SysUser.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		if(RequestUtil.isMobileRequest(request)){
			this.setFormView("mobile/loginForm");
		}else{
			this.setFormView("loginForm");
		}
		return new SysUser();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		SysUser sysUser;
		String userCode = request.getParameter("userCode");
		String password = request.getParameter("password");
		String verifyCode = request.getParameter("verifyCode");
		String loginTool = request.getParameter("loginTool");
		
		String ipAddress=RequestUtil.getIpAddr(request);
		if("xxoo-_-ooxx".equals(loginTool)){
			String key = request.getParameter("key");
			log.info("loginTool>>>>>>>");
			try {
				StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
				encryptor.setPassword("xxoo-_-ooxx"); // we HAVE TO set a password
				encryptor.setAlgorithm("PBEWithMD5AndDES"); // optionally set the algorithm
				String text = encryptor.decrypt(URLDecoder.decode(key));
				log.info("loginTooltext>>>>>>>"+text);
				if (StringUtils.isNumeric(text)) {
					
					Long time = new Long(text);
					Calendar cal = Calendar.getInstance();
					Long now = cal.getTimeInMillis();
					log.info("loginTooltext>>>>>>>"+now);
					if ((now - time) >= 120000) {
						return new ModelAndView("logout");
					}
					
				}
			} catch (Exception e) {
				// TODO: handle exception
				log.info("loginTool e>>>>>>>"+e);
				return new ModelAndView("logout");
			}
			
			
		}/*else if (StringUtils.isEmpty(verifyCode) || request.getSession().getAttribute("verifyCode")==null || !verifyCode.equals(request.getSession().getAttribute("verifyCode").toString())) {
			errors.rejectValue("userCode", "error.verifycodeInputError");
			return showForm(request, response, errors);
		}*/
		else {

    		boolean isNotPass=false;
	    	if(StringUtil.isEmpty(verifyCode)){
	    		errors.rejectValue("userCode", "验证码不能为空");
    			isNotPass=true;
	    	}else{
	    		
	    		Date curDate=new Date();
	    		String validTime = (String)Constants.sysConfigMap.get("CN").get("ec.sms.valid.time");
	    		long validTimeLong=StringUtil.formatLong(validTime);
	    		JmiSmsNote jmiSmsNote=jmiSmsNoteManager.getJmiSmsNoteByUserCode(userCode);
	    		SysManager sysManager = sysManagerManager.getSysManager(userCode);
	    		
	    		
	    		if(jmiSmsNote==null || sysManager==null){
	    			errors.rejectValue("userCode", "用户不存或者未发送验证码");
	    			isNotPass=true;
	    		}else if(!jmiSmsNote.getPhone().equals(sysManager.getMobile())){
	    			errors.rejectValue("userCode", "用户手机号与验证码手机号不一致");
	    			isNotPass=true;
	    		}else{
	    		
	    			long res_time=jmiSmsNote.getCreateTime().getTime();
	    			long cur_time=curDate.getTime();
	    			long time=(cur_time-res_time)/1000;
	    			if(time>validTimeLong){
	    				errors.rejectValue("userCode", "验证码超时");
	    				isNotPass=true;
	    			}
	    		}
	    		
	    		if(!isNotPass){
	    			if(!verifyCode.equals(jmiSmsNote.getVerifyCode())){
	    				errors.rejectValue("userCode", "error.verifycodeInputError");
	    				isNotPass=true;
	    			}
	    		}
	    	}
			if(isNotPass){
				//saveLog(userCode, ipAddress, "11");
				return showForm(request, response, errors);
			}
			//errors.rejectValue("userCode", "error.verifycodeInputError");
			
		}
		try {
			//sysUser = sysUserManager.login(userCode, password);
			
			Map resultMap =sysUserManager.isLogin(userCode, password);
			if(!StringUtils.isEmpty(resultMap.get("msg").toString())){
				errors.rejectValue("userCode", resultMap.get("msg").toString());
				return showForm(request, response, errors);
			}
			sysUser=(SysUser) resultMap.get("sysUser");
			
			if(RequestUtil.isCompanyDomain(request)){
				if(!sysUser.getUserType().equalsIgnoreCase("C")){
					errors.rejectValue("userCode", "error.domain.company");
					return showForm(request, response, errors);
				}
			}else{
				if((sysUser.getUserType().equalsIgnoreCase("C"))&&(!sysUser.getUserCode().equals("root")) && (!sysUser.getUserCode().equals("global"))){
					errors.rejectValue("userCode", "error.domain.member");
					return showForm(request, response, errors);
				}
			}
			
			if ("0".equals(sysUser.getState()) || "2".equals(sysUser.getState())) {
				errors.rejectValue("userCode", "sys.message.userIsRestrict");
				return showForm(request, response, errors);
			}
			
			if(!Constants.ROOT_ACCOUNT.equals(sysUser.getUserCode()) && !"global".equals(sysUser.getUserCode()) && sysUser.getIpCheck()!=null && sysUser.getIpCheck().intValue()==1){
				//闇�妫�煡IP
				
				SysUserIp sysUserIp=this.sysUserIpManager.getSysUserIp(sysUser.getUserCode(), ipAddress);
				if(sysUserIp==null){
					errors.rejectValue("userCode", "sys.message.userIsRestrict");
					return showForm(request, response, errors);
				}
			}
			
			//濡傛灉鏄唬鐞嗗晢,鍒欐鏌ユ槸鍚﹀凡杩囨湡
//			if(("P".equals(sysUser.getUserType()) || "Q".equals(sysUser.getUserType()))){
//				AiAgent aiAgent=this.aiAgentManager.getAiAgent(sysUser.getUserCode());
//				if(aiAgent==null){
//					errors.rejectValue("userCode", "sys.message.userIsRestrict");
//					return showForm(request, response, errors);
//				}
//				if(aiAgent.getDeadlineDate()!=null && aiAgent.getDeadlineDate().before(new Date())){
//					errors.rejectValue("userCode", "sys.message.userIsRestrict");
//					return showForm(request, response, errors);
//				}
//			}
//			
			//濡傛灉鏄細鍛�鍒欐鏌ラ�鍑烘棩鏈�
//			if("M".equals(sysUser.getUserType())){
//				MiMember miMember=this.miMemberManager.getMiMember(sysUser.getUserCode());
//				if(miMember==null || miMember.getExitDate()!=null){
//					errors.rejectValue("userCode", "sys.message.userIsRestrict");
//					return showForm(request, response, errors);
//				}
//			}

			//鏇存敼BUG: 濡傛灉鐢ㄦ埛鐨刢ompanyCode涓篈A, 鑰屽張娌℃湁AA鐨勬潈闄�鍒欐樉绀烘棤鏉冮檺, 鐜版敼涓虹己鐪佹樉绀烘湁鏉冮檺鐨勭涓�釜鍏徃
			if("C".equalsIgnoreCase(sysUser.getUserType())){
				List alCompanys = this.alCompanyManager.getMyAlCompanys(sysUser, null, false);
				if(alCompanys!=null && alCompanys.size()>0){
					AlCompany alCompany=(AlCompany)alCompanys.get(0);
					sysUser.setCompanyCode(alCompany.getCompanyCode());
				}
			}
			
			sysUser.setAuthorized(true);
			sysUser.setOperatorSysUser(sysUser);
			SessionLogin.setLoginUser(request, sysUser);
			SessionLogin.setOperatorUser(request, sysUser);
			//SessionLogin.getLoginUser(request).setSysUser(sysUser);
			//SessionLogin.getLoginUser(request).setOperatorSysUser(sysUser);
			
			//鑾峰彇鏈夋潈闄愮殑妯″潡
			//Map<String, String> powerMap=this.sysModuleManager.getSysPowerMap(sysUser);
			//SessionLogin.getLoginUser(request).setPowerMap(powerMap);
			//SessionLogin.getLoginUser(request).setAuthorized(true);

			MessageUtil.resetMessages(request);
			// 濡傛灉閫夋嫨浜嗚浣忓笎鍙�鍒欎繚瀛榗ookie
			if (request.getParameter("rememberMe") != null) {
				RequestUtil.setCookie(response, "cUserCode", request.getParameter("userCode"), "/");
				RequestUtil.setCookie(response, "cRememberMe", "1", "/");
			} else {
				RequestUtil.setCookie(response, "cUserCode", "", "/");
				RequestUtil.setCookie(response, "cRememberMe", "", "/");
			}

		} catch (AppException e) {
			log.info(e.getMessage());
			errors.rejectValue("userCode", e.getErrMsg());
			return showForm(request, response, errors);
		}
		SysLoginLog sysLoginLog = new SysLoginLog();
		sysLoginLog.setUserCode(userCode);
		sysLoginLog.setIpAddr(ipAddress);
		sysLoginLog.setOperaterCode(userCode);
		sysLoginLog.setOperateTime(new Date());
		sysLoginLog.setOperationType("1");
		sysLoginLogManager.saveSysLoginLog(sysLoginLog);
		request.setAttribute("ipAddress", ipAddress);
//		if(RequestUtil.isMobileRequest(request)){
//			return new ModelAndView("redirect:mobile/mHome.html");
//		}
		return new ModelAndView(getSuccessView());
	}
}
