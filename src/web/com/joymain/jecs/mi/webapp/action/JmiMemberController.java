package com.joymain.jecs.mi.webapp.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.JbdSummaryList;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiStateLog;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.service.JmiStateLogManager;
import com.joymain.jecs.sys.model.SysLoginLog;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysLoginLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JmiMemberController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiMemberController.class);
    private JmiMemberManager jmiMemberManager = null;
	private SysLoginLogManager sysLoginLogManager;
	private JmiStateLogManager jmiStateLogManager;
	public void setJmiStateLogManager(JmiStateLogManager jmiStateLogManager) {
		this.jmiStateLogManager = jmiStateLogManager;
	}

	public void setSysLoginLogManager(SysLoginLogManager sysLoginLogManager) {
		this.sysLoginLogManager = sysLoginLogManager;
	}

    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
        this.jmiMemberManager = jmiMemberManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        
        String strAction=request.getParameter("strAction");
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        
        Pager pager = new Pager("jmiMemberListTable",request, 20);
        
        String userCode=request.getParameter("userCode");
        String petName=request.getParameter("petName");
        String userName=request.getParameter("userName");
        String papernumber=request.getParameter("papernumber");
        String checkBDate=request.getParameter("checkBDate");
        String checkEDate=request.getParameter("checkEDate");
        String createBTime=request.getParameter("createBTime");
        String createETime=request.getParameter("createETime");
        String cardType=request.getParameter("cardType");
        String isstore=request.getParameter("isstore");
        String mobiletele=request.getParameter("mobiletele");
        String spouseIdno=request.getParameter("spouseIdno");
        String shopType=request.getParameter("shopType");
        List jmiMembers =null;
        
        if("M".equals(defSysUser.getUserType())){
        	crm.addField("cardType", "0");
        	crm.addField("createNo", defSysUser.getUserCode());
        }else{
            crm.addField("companyCode", defSysUser.getCompanyCode());
        }

		/* 密码重置操作 */
		if ("resetPassword".equals(strAction)) {
			JmiMember member = jmiMemberManager.getJmiMember(userCode);
			if(member!=null){
				String password = StringUtil.encodePassword(StringUtil.getRight(member.getUserCode(),6), "MD5");
				member.getSysUser().setPassword(password);
				member.getSysUser().setToken("");
//				member.getSysUser().setPassword2(password);
				try {
					jmiMemberManager.saveJmiMember(member);
					this.saveLogin(request, member.getSysUser());
					MessageUtil.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
				} catch (Exception e) {
					MessageUtil.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateFail"));
				}
		        return new ModelAndView("redirect:jmiMembers.html?strAction=memberSearch&needReload=1");
			}
			
		}
		/* 高阶密码重置操作 */
		if ("resetPassword2".equals(strAction)) {
			JmiMember member = jmiMemberManager.getJmiMember(userCode);
			if(member!=null){
				String password = StringUtil.encodePassword(StringUtil.getRight(member.getUserCode(),6), "MD5");
//				member.getSysUser().setPassword(password);
				member.getSysUser().setPassword2(password);
				try {
					jmiMemberManager.saveJmiMember(member);
//					this.saveLogin(request, member.getSysUser());
					MessageUtil.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
				} catch (Exception e) {
					MessageUtil.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateFail"));
				}
		        return new ModelAndView("redirect:jmiMembers.html?strAction=memberSearch&needReload=1");
			}
			
		}
		
		//会员状态修改
		if("changeStatus".equals(strAction)){
			JmiMember m = jmiMemberManager.getJmiMember(userCode);
			if(m!=null){
				Date curDate=new Date();
				JbdSummaryList jbdSummaryList=new JbdSummaryList();
				jbdSummaryList.setUserCode(userCode);
				jbdSummaryList.setCreateTime(curDate);
				jbdSummaryList.setNewCompanyCode(m.getCompanyCode());
				
				if(null==m.getExitDate()){
					m.setExitDate(new Date());
					m.getSysUser().setState("0");
					jbdSummaryList.setInType(12);
					jbdSummaryList.setNewCheckDate(curDate);
				}else{
					m.setExitDate(null);
					m.getSysUser().setState("1");
					jbdSummaryList.setInType(13);
				}
				//jmiMemberManager.saveJmiMember(m);
				jmiMemberManager.saveExitMimember(m,jbdSummaryList);
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
		        return new ModelAndView("redirect:jmiMembers.html?strAction=memberSearch&needReload=1");
			}
		}
		//死点激活与关闭
		if("activeMember".equals(strAction)){
			JmiMember m = jmiMemberManager.getJmiMember(userCode);
			if(m!=null){
				
				if("1".equals(m.getActive())){
					m.setActive("0");//激活
					m.getSysUser().setState("1");
					m.setActiveTime(new Date());
				}else{
					m.setActive("1");
					m.getSysUser().setState("0");
				}
				jmiMemberManager.saveJmiMember(m);
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
		        return new ModelAndView("redirect:jmiMembers.html?strAction=memberSearch&needReload=1");
			}
		}

		//登陆权限修改
		if("loginFlag".equals(strAction)){
			JmiMember m = jmiMemberManager.getJmiMember(userCode);
			if(m!=null){
				String state="";
				if("1".equals(m.getSysUser().getState())){
					m.getSysUser().setState("0");
					state="0";
				}else{
					m.getSysUser().setState("1");
					state="1";
				}
				jmiMemberManager.saveJmiMember(m);
				JmiStateLog jmiStateLog=new JmiStateLog();
				jmiStateLog.setCreateNo(defSysUser.getUserCode());
				jmiStateLog.setCreateTime(new Date());
				jmiStateLog.setLogType("1");
				jmiStateLog.setState(state);
				jmiStateLog.setUserCode(userCode);
				jmiStateLogManager.saveJmiStateLog(jmiStateLog);
				
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
		        return new ModelAndView("redirect:jmiMembers.html?strAction=memberSearch&needReload=1");
			}
		}
		//修改登陆错误提示
		if("errorPwdFlag".equals(strAction)){
			JmiMember m = jmiMemberManager.getJmiMember(userCode);
			if(m!=null){
				m.getSysUser().setLastLoginErrorTime(null);
				m.getSysUser().setFailureTimes(0);
				jmiMemberManager.saveJmiMember(m);
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
		        return new ModelAndView("redirect:jmiMembers.html?strAction=memberSearch&needReload=1");
			}
		}
		//取消身份证格式提示
		if("changeFlag".equals(strAction)){
			JmiMember m = jmiMemberManager.getJmiMember(userCode);
			if(m!=null){
				
					m.setFlag(null);
	
				jmiMemberManager.saveJmiMember(m);
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
		        return new ModelAndView("redirect:jmiMembers.html?strAction=memberSearch&needReload=1");
			}
		}
		/* 删除员会*/
		if ("deleteMember".equals(strAction)) {
			JmiMember m = jmiMemberManager.getJmiMember(userCode);
    		try{
    			jmiMemberManager.removeJmiMember(m.getUserCode());
    			MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miMember.deleted"));
		        return new ModelAndView("redirect:jmiMembers.html?strAction=memberSearch&needReload=1");
    		}catch(AppException e){
    			if("miLinkRef.hasMember".equals(e.getErrMsg())){
    				MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miLinkRef.hasMember"));
            	}else if("member.remove.0".equals(e.getErrMsg())){
    				MessageUtil.saveMessage(request, LocaleUtil.getLocalText("member.remove.0"));
            	}else if("jfiBankbookBalance.greatthan.0".equals(e.getErrMsg())){
    				MessageUtil.saveMessage(request, LocaleUtil.getLocalText(e.getErrMsg()));
            	}else{
            		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miMember.deleteFail"));
            	}
		        return new ModelAndView("redirect:jmiMembers.html?strAction=memberSearch&needReload=1");
    		}
		}
		
        if ("M".equals(defSysUser.getUserType())||!StringUtil.isEmpty(userCode)||!StringUtil.isEmpty(petName)||
        		!StringUtil.isEmpty(userName)||!StringUtil.isEmpty(papernumber)||
        		StringUtil.isDate(checkBDate)||StringUtil.isDate(checkEDate)||
        		StringUtil.isDate(createBTime)||StringUtil.isDate(createETime)||!StringUtil.isEmpty(cardType)||!StringUtil.isEmpty(isstore)||!StringUtil.isEmpty(mobiletele)||!StringUtil.isEmpty(spouseIdno)||!StringUtil.isEmpty(shopType)){
            jmiMembers = jmiMemberManager.getJmiMembersByCrm(crm,pager);
        }
        request.setAttribute("jmiMemberListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("mi/jmiMemberList", Constants.JMIMEMBER_LIST, jmiMembers);
    }
	private void saveLogin(HttpServletRequest request,SysUser sysUser){
		SysUser defSysUser=SessionLogin.getLoginUser(request);
		String ipAddress=RequestUtil.getIpAddr(request);
		SysLoginLog sysLoginLog = new SysLoginLog();
		sysLoginLog.setUserCode(sysUser.getUserCode());
		sysLoginLog.setIpAddr(ipAddress);
		sysLoginLog.setOperaterCode(defSysUser.getUserCode());
		sysLoginLog.setOperateTime(new Date());
		sysLoginLog.setOperationType("2");
		sysLoginLogManager.saveSysLoginLog(sysLoginLog);
	}
}
