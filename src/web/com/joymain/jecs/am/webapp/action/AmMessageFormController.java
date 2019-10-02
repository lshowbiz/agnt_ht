package com.joymain.jecs.am.webapp.action;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.am.model.AmMessage;
import com.joymain.jecs.am.service.AmMessageManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;


public class AmMessageFormController extends BaseFormController {
    private AmMessageManager amMessageManager = null;
    private SysUserManager sysUserManager = null;
    private JmiMemberManager jmiMemberManager;
    private Integer statusPre ;//保持前状态
    public void setAmMessageManager(AmMessageManager amMessageManager) {
        this.amMessageManager = amMessageManager;
    }
    public AmMessageFormController() {
        setCommandName("amMessage");
        setCommandClass(AmMessage.class);
    }
    private String uniNo ="";
    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        uniNo = request.getParameter("uniNo");
        AmMessage amMessage = null;
        if (!StringUtils.isEmpty(uniNo)) {
            amMessage = amMessageManager.getAmMessage(uniNo);
        } else {
            amMessage = new AmMessage();
        }
        String returnView = request.getParameter("returnView");
        
        String strAction = request.getParameter("strAction");
        String buttonKey = "button.back";
        Map map = RequestUtil.populateMap(request);
        
        //控制参数
        String flag = "-1";
        String sendDivDisplay = "display:none";//发送区是否可见
        String controlDivDisplay = "display:none";//控制区是否可见
        String replyDivDisplay = "display:none";//回复区是否可见
        boolean sendDisabled = true;//发送区是否可编辑
        boolean replyDisabled = true;//回复区是否可编辑
        
        
//        boolean displayButton = false;//出现提交等按钮标志
//        boolean sendFlag = false;
//        boolean controlFlag = false;
//        boolean replyFlag = false;
        
        
        if("addAmMessage".equals(strAction)){//新建信息
        	sendDivDisplay = "display:blank";
        	sendDisabled = false;
        	
        	
        	buttonKey = "button.send";
        	flag = "-1";
//        	sendFlag = true;
//        	displayButton = true;
        	
        	
        }else if("editSendAmMessage".equals(strAction)){//编辑 发送消息
        	sendDivDisplay = "display:blank";
        	sendDisabled = false;
        	
        	
        	
        }else if("editReplyAmMessage".equals(strAction)){//编辑 回复消息
        	sendDivDisplay = "display:blank";
        	replyDivDisplay = "display:blank";
        	replyDisabled = false;
        	//查出最近三次的消息
        	List amMessageRecents=amMessageManager.getRecentlyAmMessage(amMessage.getSenderNo(), defSysUser.getCompanyCode());
        	request.setAttribute("amMessageRecents", amMessageRecents);
        	
        }else if("viewAmMessage".equals(strAction)){
        	sendDivDisplay = "display:blank";
        	replyDivDisplay = "display:blank";
        	
        }
        
        /**
        else if("sendAmMessage".equals(strAction)){//发信息
        	buttonKey = "button.send";
        	flag = "0";
//        	sendFlag = true;
        	if( -1 == amMessage.getStatus()){
        		sendFlag = true;
        		displayButton = true;
        	}else if(3 == amMessage.getStatus()){
//        		controlDivDisplay = "display:blank";
            	replyDivDisplay = "display:blank";
        	}
        }else if("replyAmMessage".equals(strAction)){//回复
        	buttonKey = "button.reply";
        	flag = "2";
        	displayButton = true;
    		replyFlag = true;
//        	if(amMessage.getStatus()<3 &&  amMessage.getStatus()>=0){
//        		displayButton = true;
//        		replyFlag = true;
//        	}
//        	controlFlag = true;
        	
//        	controlDivDisplay = "display:blank";
        	replyDivDisplay = "display:blank";
        }else if("checkAmMessage".equals(strAction)){//审核回复
        	buttonKey = "button.send";
        	flag = "3";
//        	controlFlag = true;
//        	if(amMessage.getStatus()>=2){
//        		replyFlag = true;
//        		displayButton = true;
//        	}
        	
//        	controlDivDisplay = "display:blank";
        	replyDivDisplay = "display:blank";
        }else if("searchAmMessage".equals(strAction)){
        	buttonKey = "button.back";
        	flag = "4";
        	
        	
        	controlDivDisplay = "display:blank";
        	replyDivDisplay = "display:blank";
        }else if("viewAmMessage".equals(strAction)){
        	
        	replyDivDisplay = "display:blank";
        	request.setAttribute("isView", true);
        }
        
        
        request.setAttribute("sendDivDisplay", sendDivDisplay);
        request.setAttribute("controlDivDisplay", controlDivDisplay);
        request.setAttribute("replyDivDisplay", replyDivDisplay);
        request.setAttribute("sendFlag", sendFlag);
        request.setAttribute("controlFlag", controlFlag);
        request.setAttribute("replyFlag", replyFlag);
        
        request.setAttribute("buttonKey", buttonKey);
        request.setAttribute("displayButton", displayButton);
     
        **/
        request.setAttribute("flag", flag);
        request.setAttribute("sendDivDisplay", sendDivDisplay);
        request.setAttribute("controlDivDisplay", controlDivDisplay);
        request.setAttribute("replyDivDisplay", replyDivDisplay);
        
        request.setAttribute("sendDisabled", sendDisabled);
        request.setAttribute("replyDisabled", replyDisabled);
        request.setAttribute("strAction", strAction);
        request.setAttribute("returnView", returnView);
        
        request.setAttribute("requestMap", map);
        statusPre = amMessage.getStatus();
        log.info("statusPre="+statusPre);
        

    	if(RequestUtil.isMobileRequest(request)){
        	this.setFormView("mobile/am/amMessageForm");
        }else{
        	this.setFormView("am/amMessageForm");
        }
        
        
        return amMessage;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        AmMessage amMessage = (AmMessage) command;
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        boolean isNew = (amMessage.getUniNo() == null);
        Locale locale = request.getLocale();
        String strAction = request.getParameter("strAction");
        String returnView = request.getParameter("returnView");
        String view = "redirect:amMessages.html?strAction="+returnView;
        String key = null;
        log.info(amMessage.getStatus());
        
        if("addAmMessage".equals(strAction)){//新增消息
        	if(sessionLogin.getIsCompany()){
        		if(StringUtils.isEmpty(amMessage.getAgentNo())||null==jmiMemberManager.getJmiMember(amMessage.getAgentNo())){
					errors.rejectValue("agentNo", "isNotNull",new Object[] { getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(),"bdNetWorkCostReport.memberCH") }, "");
            		return showForm(request, response, errors);
        		}
        	}
        	if("M".equals(sessionLogin.getUserType())&&"9".equals(amMessage.getMsgClassNo())){
        		List list=amMessageManager.getAmMessageByUserCode(sessionLogin.getUserCode(), amMessage.getMsgClassNo());
        		if(!list.isEmpty()){
        			errors.reject("message.9.exist","message.9.exist");
            		return showForm(request, response, errors);
        		}
        	}
        	addAmMessage(request,response,amMessage);
        	//if(-1==statusPre)
        	//	key="amMessage.update";
        	//else
        		key="amMessage.add";
        }else if("editSendAmMessage".equals(strAction)){//编辑发送消息
        	
        	key="error.amMessage.hasSended";
        	if(-1==statusPre){
        		key="amMessage.update";
        		editAmMessage(request,response,amMessage);
        	}else{
        		key="amMessage.update";
        		controleditAmMessage(request,response,amMessage);
        	}

        }else if("editReplyAmMessage".equals(strAction)){//编辑回复消息
        	
        	String reContent = amMessage.getReplyContent();
        	
        	if(reContent == null) {
        		errors.reject("amMessage.replyContent.isNull", new Object[] {},getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(),"amMessage.replyContent.isNull"));
        		return showForm(request, response, errors);
        	}
        	
        	key="error.amMessage.hasSended";
        	if(("0".equals(amMessage.getSendRoute()))&&(statusPre<=4)){//发送者为公司
        		key="amMessage.update";
        		replyAmMessage(request,response,amMessage);
        	}else if(("1".equals(amMessage.getSendRoute()))&&(2 >= statusPre)){//发送者为会员
        		key="amMessage.update";
        		replyAmMessage(request,response,amMessage);
        	}

        }else if ("deleteAmMessage".equals(strAction)){//删除未提交的消息
        	key="error.amMessage.hasSended";

        	if(statusPre == -1){
        		key="amMessage.delete";
        		deleteAmMessage(request,response,amMessage);
        	}
        	
            
        }
        
        /**
        else if("sendAmMessage".equals(strAction)){//-1,0
        	key="error.amMessage.hasSended";
        	
        	if(statusPre == -1){
        		sendAmMessage(request,response,amMessage);
            	key="amMessage.send";
        	}

        }else if("controlAmMessage".equals(strAction)){//0,1
        	key="amMessage.hasReplied";
        	
        	if(statusPre<2 &&  statusPre>=0){
        		key="amMessage.control";
        		controlAmMessage(request,response,amMessage);
        	}

        }else if("replyAmMessage".equals(strAction)){//0,1,2

        	key="amMessage.reply";
        	replyAmMessage(request,response,amMessage);

        }else if("checkAmMessage".equals(strAction)){//2,3

        	key="amMessage.check";
        	checkAmMessage(request,response,amMessage);
        
        }else if ("deleteAmMessage".equals(strAction)  ) 
        {
        	key="error.amMessage.hasSended";

        	if(statusPre == -1){
        		key="amMessage.delete";
        		deleteAmMessage(request,response,amMessage);
        	}
        	
            
        }**/
        saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(),key));
        return new ModelAndView(view);
    }
	private void deleteAmMessage(HttpServletRequest request,
			HttpServletResponse response, AmMessage amMessage) {
		// TODO Auto-generated method stub
		amMessageManager.removeAmMessage(amMessage.getUniNo().toString());
	}
	private void checkAmMessage(HttpServletRequest request,
			HttpServletResponse response, AmMessage amMessage) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		amMessage.setCheckMsgTime(new Date());
		amMessage.setCheckUserNo(sessionLogin.getUserCode());
		amMessageManager.saveAmMessage(amMessage);
	}
	private void replyAmMessage(HttpServletRequest request,
			HttpServletResponse response, AmMessage amMessage) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		amMessage.setReceiverNo(sessionLogin.getUserCode());
		amMessage.setReceiverName(sessionLogin.getUserName());
		amMessage.setReplyTime(new Date());
		if(sessionLogin.getIsCompany() || sessionLogin.getIsManager()){
			amMessage.setStatus(2);
		}else{
			amMessage.setStatus(4);
		}
		amMessageManager.saveAmMessage(amMessage);
	}
	private void controlAmMessage(HttpServletRequest request,
			HttpServletResponse response, AmMessage amMessage) {
		// TODO Auto-generated method stub
		amMessageManager.saveAmMessage(amMessage);
	}
	private void sendAmMessage(HttpServletRequest request,
			HttpServletResponse response, AmMessage amMessage) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		amMessage.setSenderNo(sessionLogin.getUserCode());
		amMessage.setIssueTime(new Date());
		amMessageManager.saveAmMessage(amMessage);
	}
	private void editAmMessage(HttpServletRequest request,
			HttpServletResponse response, AmMessage amMessage) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		amMessage.setSenderNo(sessionLogin.getUserCode());
		amMessage.setSenderName(sessionLogin.getUserName());
		amMessage.setIssueTime(new Date());
		amMessageManager.saveAmMessage(amMessage);
	}
	
	private void controleditAmMessage(HttpServletRequest request,
			HttpServletResponse response, AmMessage amMessage) {
		
		SysUser sessionLogin = SessionLogin.getLoginUser(request);

		if(sessionLogin.getIsAgent() || sessionLogin.getIsMember()){//代理商。会员
		}else{//公司
			//amMessage.setStatus(2);
			//amMessage.setSendRoute("0");
			//String reNo = amMessage.getAgentNo();
			//amMessage.setReceiverNo(reNo);
			//amMessage.setReceiverName(sysUserManager.getSysUser(reNo).getUserName());
		}
		
		amMessageManager.saveAmMessage(amMessage);
	}
	
	private void addAmMessage(HttpServletRequest request,
			HttpServletResponse response, AmMessage amMessage) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		amMessage.setCompanyCode(sessionLogin.getCompanyCode());
		amMessage.setUniNo(null);
		if(sessionLogin.getIsAgent() || sessionLogin.getIsMember()){//代理商。会员
			amMessage.setAgentNo(sessionLogin.getUserCode());
			amMessage.setAgentName(sessionLogin.getUserName());
			amMessage.setSendRoute("1");
		}else{//公司
			amMessage.setStatus(2);
			amMessage.setSendRoute("0");
			String reNo = amMessage.getAgentNo();
			amMessage.setReceiverNo(reNo);
			amMessage.setReceiverName(sysUserManager.getSysUser(reNo).getUserName());
		}
		amMessage.setContent(amMessage.getContent().replaceAll("form", "span"));
		amMessage.setSenderNo(sessionLogin.getUserCode());
		amMessage.setSenderName(sessionLogin.getUserName());
		amMessage.setIssueTime(new Date());
		
		amMessageManager.saveAmMessage(amMessage);
	}
	
	private void viewAmMessage(HttpServletRequest request,HttpServletResponse response, AmMessage amMessage){
		amMessage.setStatus(9);
		amMessageManager.saveAmMessage(amMessage);
	}
	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
}
