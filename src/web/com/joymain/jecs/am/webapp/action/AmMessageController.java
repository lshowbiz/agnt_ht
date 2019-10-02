package com.joymain.jecs.am.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.am.model.AmMessagePermit;
import com.joymain.jecs.am.service.AmMessageManager;
import com.joymain.jecs.am.service.AmMessagePermitManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class AmMessageController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(AmMessageController.class);
    private AmMessageManager amMessageManager = null;
    private AlStateProvinceManager alStateProvinceManager = null;
    private AmMessagePermitManager amMessagePermitManager;
    public void setAmMessagePermitManager(
			AmMessagePermitManager amMessagePermitManager) {
		this.amMessagePermitManager = amMessagePermitManager;
	}

	public void setAmMessageManager(AmMessageManager amMessageManager) {
        this.amMessageManager = amMessageManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        //SysUser sessionLogin = SessionLogin.getLoginUser(request);
//        AmMessage amMessage = new AmMessage();
        String view = "am/amMessageList";
        
        // populate object with request parameters
//        BeanUtils.populate(amMessage, request.getParameterMap());
        
        
        Map map = RequestUtil.populateMap(request);
        String strAction = request.getParameter("strAction");
        
        String flag = "-1";
		//List amMessages = amMessageManager.getAmMessages(amMessage);
		/**** auto pagination ***/
		CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("amMessageListTable",request, 20);
       
        SysUser login = SessionLogin.getLoginUser(request);
        List amMessages ;
        
        if(! login.getIsManager()){
        	crm.setValue("companyCode", login.getCompanyCode());
        }
        /*      
        if("C".equalsIgnoreCase(login.getUserType()) || ("A".equalsIgnoreCase(login.getUserType()))){
        	
        	if(!"AA".equalsIgnoreCase(login.getCompanyCode())){
       		crm.setValue("companyCode", login.getCompanyCode());
        	}
        	
        }else {
        	view = "am/amMessageAgentList";
        	crm.setValue("agentNo", login.getUserCode());
        crm.setValue("senderNo", login.getUserCode());
        }
         */
        crm.setValue("loginUserNo", login.getUserCode());
        
        if("companyAmMessage".equals(strAction)){//信息回复
        	view = "am/amMessageList";
        
        	crm.setValue("searchFlag", "company");
        	
        	String noReplyNum = amMessageManager.getCompanyReplyNum(crm,"noreply");
            String replyNum = amMessageManager.getCompanyReplyNum(crm,"reply");
            
            request.setAttribute("noReplyNum", noReplyNum);
            request.setAttribute("replyNum", replyNum);
        	
        }else if("controlAmMessage".equals(strAction)){//消息管控
        	 view = "am/controlAmMessageList";
        	 crm.setValue("searchFlag", "control");
        	 
        	 
        	 Integer readNum = (Integer) request.getSession().getAttribute("readMsg");        	 
        	 if(readNum == null)
        		 readNum = 0;
        	 else
        		 readNum++;        	 
        	 request.getSession().setAttribute("readMsg", readNum);        	 
        	 request.setAttribute("readMsg", request.getSession().getAttribute("readMsg"));
        	 String nocheckNum = "0";
        	 String noReplyNum = amMessageManager.getAmMessagesReplyNum(crm,"noreply");
             String replyNum = amMessageManager.getAmMessagesReplyNum(crm,"reply");
             if(readNum.equals(0))
            	 nocheckNum = amMessageManager.getAmMessagesReplyNum(crm,"nocheck");
             request.setAttribute("noReplyNum", noReplyNum);
             request.setAttribute("replyNum", replyNum);
             request.setAttribute("nocheckNum", nocheckNum);
             
             request.setAttribute("logUser", login.getUserCode());
             
             List alStateProvinces = null;
           	String companyCode = login.getCompanyCode();
           	
//           	Map compamyProductMap = pmProductSaleManager.getCompanyProductMap(companyCode);
//           	
//           	request.setAttribute("compamyProductMap", compamyProductMap);
           	if(login.getIsManager()){
        		
        		alStateProvinces = alStateProvinceManager.getAlStateProvinces(null);
        	}else{
        		alStateProvinces = alStateProvinceManager.getAlStateProvincesByCountry(companyCode);
        	}
        	request.setAttribute("alStateProvinces", alStateProvinces);
             
             
        }else if("csAmMessage".equals(strAction)){//客服信息系统
        	view = "am/csAmMessageList";
        	crm.setValue("searchFlag", "cs");
        	
        	String noReplyNum = amMessageManager.getAmMessagesReplyNum(crm,"noreply");
            String replyNum = amMessageManager.getAmMessagesReplyNum(crm,"reply");
            request.setAttribute("noReplyNum", noReplyNum);
            request.setAttribute("replyNum", replyNum);
        }else if("agentAmMessage".equals(strAction)){//代理商发送信息
        	view = "am/amMessageAgentList";
        	if(RequestUtil.isMobileRequest(request)){
            	view="mobile/am/amMessageAgentList";
            }
        	crm.setValue("searchFlag", "agent");
        	crm.setValue("agentNo", login.getUserCode());
        	
        	//crm.setValue("browserNo", sessionLogin.getUserCode());
        	
        	String noReplyNum = amMessageManager.getAgentReplyNum(crm,"noreply");
            String replyNum = amMessageManager.getAgentReplyNum(crm,"reply");
            String noreadNum = amMessageManager.getAgentReplyNum(crm,"noread");
            
            request.setAttribute("noReplyNum", noReplyNum);
            request.setAttribute("replyNum", replyNum);
            request.setAttribute("noreadNum", noreadNum);
        }else if("typeAmMessage".equals(strAction)){//按类型回复消息
        	view = "am/amMessageTypeList";
        
        	crm.setValue("searchFlag", "type");
        	
        	List<AmMessagePermit> amMessagePermit=amMessagePermitManager.getAmMessagePermitsByUserCode(login.getUserCode());
        	if(amMessagePermit!=null&&amMessagePermit.size()>0){
        		String amMessageType="";
    			for (int i = 0; i < amMessagePermit.size(); i++) {
    				if(i>0){
    					amMessageType+=",";
    				}
    				amMessageType+="'"+((AmMessagePermit)(amMessagePermit.get(i))).getMsgClassNo()+"'";
    			}
    			crm.setValue("amMessageType", amMessageType);
        	}
        	String noReplyNum="0";
        	String replyNum="0";
        	if(amMessagePermit!=null&&amMessagePermit.size()>0){
            	noReplyNum = amMessageManager.getCompanyReplyNum(crm,"noreply");
                replyNum = amMessageManager.getCompanyReplyNum(crm,"reply");
        	}
            request.setAttribute("noReplyNum", noReplyNum);
            request.setAttribute("replyNum", replyNum);
        }else if("searchAmMessage".equals(strAction)){//信息查询 
        	view = "am/amMessageSearchList";
//        	crm.setValue("searchFlag", "cs");
        	
//	       	String noReplyNum = amMessageManager.getAmMessagesReplyNum(crm,"noreply");
//	        String replyNum = amMessageManager.getAmMessagesReplyNum(crm,"reply");
//            request.setAttribute("noReplyNum", noReplyNum);
//            request.setAttribute("replyNum", replyNum);
        }     
        
        amMessages = amMessageManager.getAmMessagesByCrm(crm,pager);
        
        
        /*****/
        request.setAttribute("amMessageExample", map);
        request.setAttribute("strAction", strAction);
        
        request.setAttribute("amMessageListTable_totalRows", pager.getTotalObjects());
        return new ModelAndView(view,  "amMessageList", amMessages);
    }


	public void setAlStateProvinceManager(
			AlStateProvinceManager alStateProvinceManager) {
		this.alStateProvinceManager = alStateProvinceManager;
	}
}
