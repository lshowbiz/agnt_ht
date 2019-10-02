package com.joymain.jecs.mi.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiRecommendRef;
import com.joymain.jecs.mi.service.JmiRecommendRefManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 推荐网络移线
 * @author Alvin
 *
 */
public class MiMemberChangeRecommendRefController implements Controller {
    private final Log log = LogFactory.getLog(MiMemberChangeRecommendRefController.class);
    private JmiRecommendRefManager jmiRecommendRefManager = null;

    public void setJmiRecommendRefManager(
			JmiRecommendRefManager jmiRecommendRefManager) {
		this.jmiRecommendRefManager = jmiRecommendRefManager;
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
        	String recommendNo = request.getParameter("recommendNo");
        	JmiRecommendRef miRecommendRef = jmiRecommendRefManager.getJmiRecommendRef(memberNo);
        	JmiRecommendRef newMiRecommendRef = jmiRecommendRefManager.getJmiRecommendRef(recommendNo);
        	if(miRecommendRef==null){//会员不存在
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miMember.notFound"));
        		return new ModelAndView("mi/memberChangeRecommendRef");
        	}else if(newMiRecommendRef==null){//新推荐人不存在
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miRecommendRef.isNotFound"));
        		return new ModelAndView("mi/memberChangeRecommendRef");
        	}
        	JmiMember miMember = miRecommendRef.getJmiMember();
        	JmiMember newMiMember = newMiRecommendRef.getJmiMember();
        	if(miMember==null){//会员不存在
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miMember.notFound"));
        		return new ModelAndView("mi/memberChangeRecommendRef");
        	}else if(newMiMember==null){//新推荐人不存在
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miRecommendRef.isNotFound"));
        		return new ModelAndView("mi/memberChangeRecommendRef");
        	}
        	if(!isSameCompanyCode(defSysUser,miRecommendRef.getJmiMember())){//会员跟登陆者不属同一分公司
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miMember.notFound"));
        		return new ModelAndView("mi/memberChangeRecommendRef");
        	}
//        	if(1==miMember.getBeforeFreezeStatus()){//会员冻结，不能转移
//        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("bdsendrecord.sendlateremark.17"));
//        		return new ModelAndView("mi/memberChangeRecommendRef");
//        	}
        	
        	String noCheckLinkRef = request.getParameter("noCheckLinkRef");
        	if("1".equals(noCheckLinkRef)){//强行移线开启(1不检查,0为检查)
        		noCheckLinkRef = "1";
        	}else{
        		noCheckLinkRef = "0";
        	}
        	
        	String returnMsg = jmiRecommendRefManager.getCallProcChangeRecommend(memberNo, recommendNo, defSysUser.getUserCode(), noCheckLinkRef);
        	if("miMember.notFound".equals(returnMsg)){//会员不存在
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miMember.notFound"));
        		return new ModelAndView("mi/memberChangeRecommendRef");
        	}else if("miRecommendRef.isNotFound".equals(returnMsg)){//新推荐人不存在
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miRecommendRef.isNotFound"));
        		return new ModelAndView("mi/memberChangeRecommendRef");
        	}else if("miRecommendRefMiLinkRef.isNotEquals".equals(returnMsg)){//判断是否与新的推荐人同属一接点体系
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miRecommendRefMiLinkRef.isNotEquals"));
        		return new ModelAndView("mi/memberChangeRecommendRef");
        	}else if("miRecommendRefMiLinkRef.error.round".equals(returnMsg)){//形成回圈
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miRecommendRefMiLinkRef.error.round"));
        		return new ModelAndView("mi/memberChangeRecommendRef");
        	}else if("miRecommendRef.hasCounted.changeIsFailed".equals(returnMsg)){//分公司管理员只能移未结算会员接点体系
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miRecommendRef.hasCounted.changeIsFailed"));
        		return new ModelAndView("mi/memberChangeRecommendRef");
        	}else if("miMemberChangeRecommendRef.success".equals(returnMsg)){//成功
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miMemberChangeRecommendRef.success"));
        		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("re.counting"));
        		return new ModelAndView("mi/memberChangeRecommendRef");
        	}
        	
        }
		return new ModelAndView("mi/memberChangeRecommendRef");
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
