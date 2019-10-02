package com.joymain.jecs.mi.webapp.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
/**
 * 检查接点跟推荐体系(linkNo或recommendNo与treeIndex的完整性)
 *
 */
public class MiMemberCheckRefController implements Controller {
    private final Log log = LogFactory.getLog(MiMemberCheckRefController.class);
    private JmiMemberManager jmiMemberManager = null;


    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}


	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
    	
        if("post".equalsIgnoreCase(request.getMethod())){
        	String checkType = "";//1查接点,0查推荐
        	String strAction = request.getParameter("strAction");
        	if("memberCheckRecommendRef".equals(strAction)){
        		checkType = "0";
        	}else if("memberCheckLinkRef".equals(strAction)){
        		checkType = "1";
        	}
        	   
        	if("1".equals(checkType) || "0".equals(checkType)){
        		String lanStr = "miLinkRef";
        		if("0".equals(checkType)){
        			lanStr = "miRecommendRef";
        		}
        		Map resultComp = jmiMemberManager.callProcCheckRef(checkType);
        		String Vc_msg = ((String)resultComp.get("Vc_Errmsg"));
        		String Vc_Ref_No_Null = ((String)resultComp.get("Vc_Ref_No_Null"));
        		if(!StringUtil.isEmpty(Vc_msg) && !StringUtil.isEmpty(Vc_msg.trim())){
        			Vc_msg = Vc_msg.trim();
        			String[] memberNo ={Vc_msg};
        			MessageUtil.saveMessage(request, LocaleUtil.getLocalText(lanStr + ".notEqual",memberNo));
        		}else if(!StringUtil.isEmpty(Vc_Ref_No_Null) && !StringUtil.isEmpty(Vc_Ref_No_Null.trim())){
        			Vc_Ref_No_Null = Vc_Ref_No_Null.trim();
        			String[] memberNo ={Vc_Ref_No_Null};
        			MessageUtil.saveMessage(request, LocaleUtil.getLocalText(lanStr + ".notFound",memberNo));
        		}else{
        			MessageUtil.saveMessage(request, LocaleUtil.getLocalText(lanStr + ".success"));
        		}
        	}
        }
		return new ModelAndView("mi/memberCheckRef");
    }
}
