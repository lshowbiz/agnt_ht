package com.joymain.jecs.mi.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
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

public class JmiMemberReSendController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiMemberReSendController.class);
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
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        
        String strAction=request.getParameter("strAction");
        
        
        if("reSendECPP".equals(strAction)){
        	String[] strRegisterSuccessCodes = request.getParameter("strRegisterSuccessCodes").split(",");
        	String userCodeStr=request.getParameter("userCodeStr");
        	if(!StringUtil.isEmpty(userCodeStr)){
        		strRegisterSuccessCodes=userCodeStr.split(",");
        	}
        	List errorList=new ArrayList();
        	String res="";
        	try {
				res=jmiMemberManager.reSendJmiMember(strRegisterSuccessCodes);
				if(StringUtil.isEmpty(res)){
					saveMessage(request, "success");
				}else{
					errorList.add("错误："+res);
				}
			} catch (Exception e) {
				e.printStackTrace();
				errorList.add("fail"+e.getMessage()+"错误："+res);
			}
            request.setAttribute("errorList", errorList);
        }
        if("reSendPCN".equals(strAction)){
        	String[] strRegisterSuccessCodes = request.getParameter("strRegisterSuccessCodes").split(",");
        	String userCodeStr=request.getParameter("userCodeStr");
        	if(!StringUtil.isEmpty(userCodeStr)){
        		strRegisterSuccessCodes=userCodeStr.split(",");
        	}
        	

        	jmiMemberManager.sendPcnsNewMember(strRegisterSuccessCodes);
			saveMessage(request, "success");
        	
        }
        
        
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        
//        crm.addField("sendStatus", "1");
        crm.addField("userType", "M");
        
        Pager pager = new Pager("jmiMemberListTable",request, 20);
        String userCodeStr=request.getParameter("userCodeStr");
        List jmiMembers = null;
        if(userCodeStr!=null){
        	jmiMembers = jmiMemberManager.getJmiMembersByCrm(crm,pager);
        }
       
        
        request.setAttribute("jmiMemberListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("mi/jmiMemberReSendList", Constants.JMIMEMBER_LIST, jmiMembers);
    }

}
