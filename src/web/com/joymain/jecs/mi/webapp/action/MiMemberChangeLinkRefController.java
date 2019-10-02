package com.joymain.jecs.mi.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.mi.model.JmiLinkRef;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiLinkRefManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;


/**
 * 接点网络移线
 */
public class MiMemberChangeLinkRefController implements Controller {
    private final Log log = LogFactory.getLog(MiMemberChangeLinkRefController.class);
    private JmiLinkRefManager jmiLinkRefManager = null;
    public void setJmiLinkRefManager(JmiLinkRefManager jmiLinkRefManager) {
		this.jmiLinkRefManager = jmiLinkRefManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

    	//================AiAgent LOGIN IMFORMATION
    	SysUser defSysUser = SessionLogin.getLoginUser(request);
    	//=========================================
    	
        if("post".equalsIgnoreCase(request.getMethod())){
        	
        	String memberNo = request.getParameter("memberNo");
        	String linkNo = request.getParameter("linkNo");
        	JmiLinkRef miLinkRef = jmiLinkRefManager.getJmiLinkRef(memberNo);
        	JmiLinkRef newMiLinkRef = jmiLinkRefManager.getJmiLinkRef(linkNo);
        	if(miLinkRef==null){//会员不存在
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miMember.notFound"));
        		return new ModelAndView("mi/memberChangeLinkRef");
        	}else if(newMiLinkRef==null){//新接点人不存在
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miLinkRef.isNotFound"));
        		return new ModelAndView("mi/memberChangeLinkRef");
        	}
        	JmiMember miMember = miLinkRef.getJmiMember();
        	JmiMember newMiMember = newMiLinkRef.getJmiMember();
        	if(miMember==null){//会员不存在
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miMember.notFound"));
        		return new ModelAndView("mi/memberChangeLinkRef");
        	}else if(newMiMember==null){//新接点人不存在
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miLinkRef.isNotFound"));
        		return new ModelAndView("mi/memberChangeLinkRef");
        	}
        	if(!isSameCompanyCode(defSysUser,miLinkRef.getJmiMember())){//当前会员跟登陆者不属同一分公司
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miMember.notFound"));
        		return new ModelAndView("mi/memberChangeLinkRef");
        	}
//        	if(1==miMember.getBeforeFreezeStatus()){//会员冻结，不能转移
//        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("bdsendrecord.sendlateremark.17"));
//        		return new ModelAndView("mi/memberChangeRecommendRef");
//        	}
        	
        	String noCheckRommendRef = request.getParameter("noCheckRommendRef");
        	if("1".equals(noCheckRommendRef)){//强行移线开启(1不检查,0为检查)
        		noCheckRommendRef = "1";
        	}else{
        		noCheckRommendRef = "0";
        	}
        	
        	String returnMsg = jmiLinkRefManager.getCallProcChangeLink(memberNo, linkNo, defSysUser.getUserCode(), noCheckRommendRef);
        	if("miMember.notFound".equals(returnMsg)){//会员不存在
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miMember.notFound"));
        		return new ModelAndView("mi/memberChangeLinkRef");
        	}else if("miLinkRef.isNotFound".equals(returnMsg)){//新接点人不存在
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miLinkRef.isNotFound"));
        		return new ModelAndView("mi/memberChangeLinkRef");
        	}else if("miLinkRefMiRecommendRef.isNotEquals".equals(returnMsg)){//判断是否与新的接点人同属一推荐体系
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miLinkRefMiRecommendRef.isNotEquals"));
        		return new ModelAndView("mi/memberChangeLinkRef");
        	}else if("miLinkRefMiRecommendRef.error.round".equals(returnMsg)){//形成回圈
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miLinkRefMiRecommendRef.error.round"));
        		return new ModelAndView("mi/memberChangeLinkRef");
        	}else if("miLinkRef.isFull".equals(returnMsg)){//下级接点已满
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miLinkRef.isFull"));
        		return new ModelAndView("mi/memberChangeLinkRef");
        	}else if("miLinkRef.hasCounted.changeIsFailed".equals(returnMsg)){//分公司管理员只能移未结算会员接点体系
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miLinkRef.hasCounted.changeIsFailed"));
        		return new ModelAndView("mi/memberChangeLinkRef");
        	}else if("miMemberChangeLinkRef.success".equals(returnMsg)){//成功
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miMemberChangeLinkRef.success"));
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("re.counting"));
        		return new ModelAndView("mi/memberChangeLinkRef");
        	}
        	
        }
		return new ModelAndView("mi/memberChangeLinkRef");
    }
    
    /**
     * 检查是否与当前用户处于同一分公司(AA除外)
     * @param sysUser
     * @param miMember
     * @return
     */
    private boolean isSameCompanyCode(SysUser sysUser, JmiMember miMember){
    	if("AA".equals(sysUser.getCompanyCode())){
    		return true;
    	}else{
    		if(sysUser.getCompanyCode().equals(miMember.getCompanyCode())){
    			return true;
    		}
    	}
    	return false;
    }
}
