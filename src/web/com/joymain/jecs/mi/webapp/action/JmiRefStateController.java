package com.joymain.jecs.mi.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.mi.model.JmiLinkRef;
import com.joymain.jecs.mi.model.JmiRecommendRef;
import com.joymain.jecs.mi.service.JmiLinkRefManager;
import com.joymain.jecs.mi.service.JmiRecommendRefManager;
import com.joymain.jecs.mi.service.JmiRefStateLogManager;
import com.joymain.jecs.mi.service.JmiStateLogManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JmiRefStateController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiRefStateController.class);
    private JmiLinkRefManager jmiLinkRefManager;//安置
    private JmiRecommendRefManager jmiRecommendRefManager;//推荐
    private JmiRefStateLogManager jmiRefStateLogManager;
    private JmiStateLogManager jmiStateLogManager;
    public void setJmiStateLogManager(JmiStateLogManager jmiStateLogManager) {
		this.jmiStateLogManager = jmiStateLogManager;
	}
	public void setJmiRefStateLogManager(JmiRefStateLogManager jmiRefStateLogManager) {
        this.jmiRefStateLogManager = jmiRefStateLogManager;
    }
    public void setJmiRecommendRefManager(JmiRecommendRefManager jmiRecommendRefManager) {
        this.jmiRecommendRefManager = jmiRecommendRefManager;
    }
    public void setJmiLinkRefManager(JmiLinkRefManager jmiLinkRefManager) {
		this.jmiLinkRefManager = jmiLinkRefManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
		SysUser defSysUser = SessionLogin.getLoginUser(request);
		
        String userCode=request.getParameter("userCode");
        String operateStr=request.getParameter("operateStr");
        //选择的网络类型
        String netType=request.getParameter("netType");
        String remark=request.getParameter("remark");
        
        String treeIndex="";
        if("link_no".equals(netType)) {
            JmiLinkRef jmiLinkRef=jmiLinkRefManager.getJmiLinkRef(userCode);
            if(jmiLinkRef==null) {//会员不存在
                this.saveMessage(request, LocaleUtil.getLocalText("miMember.notFound"));
                return new ModelAndView("mi/jmiRefStateList");
            }
            treeIndex = jmiLinkRef.getTreeIndex();
            if(!"AA".equals(defSysUser.getCompanyCode())){
                if(!jmiLinkRef.getJmiMember().getCompanyCode().equals(defSysUser.getCompanyCode())){
                    return new ModelAndView("mi/jmiRefStateList");
                }
            }
            int count=jmiLinkRefManager.getLinkRefCount(jmiLinkRef.getTreeIndex());
            request.setAttribute("count", count);
            
            String excludeVal=request.getParameter("excludeVal");
            //找出历史操作日志
            if("changeState0".equals(operateStr)){
                jmiLinkRefManager.changeStateByLink(jmiLinkRef, "0",remark,excludeVal);
                this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
                return new ModelAndView("redirect:jmiRefState.html");
            }else if("changeState1".equals(operateStr)){
                jmiLinkRefManager.changeStateByLink(jmiLinkRef, "1",remark,excludeVal);
                this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
                return new ModelAndView("redirect:jmiRefState.html");
            } 
        } else if("recommend_no".equals(netType)) {
            JmiRecommendRef jmiRecommendRef=jmiRecommendRefManager.getJmiRecommendRef(userCode);
            if(jmiRecommendRef==null){//会员不存在
                this.saveMessage(request, LocaleUtil.getLocalText("miMember.notFound"));
                return new ModelAndView("mi/jmiRefStateList");
            }
            treeIndex = jmiRecommendRef.getTreeIndex();
            if(!"AA".equals(defSysUser.getCompanyCode())){
                if(!jmiRecommendRef.getJmiMember().getCompanyCode().equals(defSysUser.getCompanyCode())){
                    return new ModelAndView("mi/jmiRefStateList");
                }
            }
            int count=jmiRecommendRefManager.getRecommendRefCount(jmiRecommendRef.getTreeIndex());
            request.setAttribute("count", count);
            
            String excludeVal=request.getParameter("excludeVal");
            if("changeState0".equals(operateStr)){
                jmiRecommendRefManager.changeStateByRecommend(jmiRecommendRef, "0",remark,excludeVal);
                this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
                return new ModelAndView("redirect:jmiRefState.html");
            }else if("changeState1".equals(operateStr)){
                jmiRecommendRefManager.changeStateByRecommend(jmiRecommendRef, "1",remark,excludeVal);
                this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
                return new ModelAndView("redirect:jmiRefState.html");
            }
        }
        if(StringUtils.isNotEmpty(userCode) && StringUtils.isNotEmpty(netType)&&StringUtils.isNotEmpty(treeIndex)) {
            CommonRecord crm=RequestUtil.toCommonRecord(request);
            crm.addField("treeIndex", treeIndex);
            Pager pager = new Pager("jmiRefStateLogTable",request, 0);
            List jmiRefStateLogs = jmiRefStateLogManager.getJmiRefStateLogsByCrm(crm, pager);
            request.setAttribute("jmiRefStateLogTable_totalRows", pager.getTotalObjects());
            request.setAttribute("jmiRefStateLogs", jmiRefStateLogs);
            
            List jmiStateLogs =jmiStateLogManager.getJmiStateLogsByCrm(crm, pager);

            request.setAttribute("jmiStateLogTable_totalRows", pager.getTotalObjects());
            request.setAttribute("jmiStateLogs", jmiStateLogs);
            
        }
        
        return new ModelAndView("mi/jmiRefStateList");
    }
    
    
}
