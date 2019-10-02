package com.joymain.jecs.fi.webapp.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.Bill99PosConstants;
import com.joymain.jecs.fi.model.Jfi99billPosSendLog;
import com.joymain.jecs.fi.service.Jfi99billPosLogManager;
import com.joymain.jecs.fi.service.Jfi99billPosSendLogManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 快钱POS发起请求
 * @author Alvin
 *
 */
public class Jfi99billPosLogController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(Jfi99billPosLogController.class);
    private JpoMemberOrderManager jpoMemberOrderManager = null;

    public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
        this.jpoMemberOrderManager = jpoMemberOrderManager;
    }
    private Jfi99billPosLogManager jfi99billPosLogManager = null;

    public void setJfi99billPosLogManager(Jfi99billPosLogManager jfi99billPosLogManager) {
        this.jfi99billPosLogManager = jfi99billPosLogManager;
    }
    
    private SysIdManager sysIdManager = null;
	
	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}
	
    private Jfi99billPosSendLogManager jfi99billPosSendLogManager = null;

    public void setJfi99billPosSendLogManager(
			Jfi99billPosSendLogManager jfi99billPosSendLogManager) {
		this.jfi99billPosSendLogManager = jfi99billPosSendLogManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        String strAction = request.getParameter("strAction");
        if("isPay".equals(strAction)){
        	SysUser loginSysUser = SessionLogin.getLoginUser(request);
        	String account = "";
        	if(Bill99PosConstants.accountMap.get(loginSysUser.getUserCode()) != null){
        		account = Bill99PosConstants.accountMap.get(loginSysUser.getUserCode()).toString();
        	}else{
        		return new ModelAndView("redirect:editJfi99billPosLog.html");
        	}
        	String orderId = request.getParameter("orderId");
        	String userCode = request.getParameter("userCode");
        	String url = Bill99PosConstants.url;
        	String loginKey = Bill99PosConstants.loginKey;
        	String terminalOperId = account;
        	String txnType = Bill99PosConstants.txnType;
        	String externalCustomerId = "";
        	String amt = "";
        	String externalTraceNo = "";
        	String actionNo = "";
        	String actionType = "";
        	if(orderId==null){
        		int amtInt = 0;
        		try{
        			amtInt = Integer.parseInt(request.getParameter("orderAmount"));
        		}catch(Exception e){
        			return new ModelAndView("redirect:editJfi99billPosLog.html");
        		}
        		if( amtInt <= 10 ){
        			return new ModelAndView("redirect:editJfi99billPosLog.html");
        		}
        		amt = String.valueOf(amtInt);
        		actionNo = sysIdManager.buildIdStr("advicecode_cn");
        		actionType = "0";
        	}else{
            	JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);
            	if("1".equals(jpoMemberOrder.getStatus()) || !"1".equals(jpoMemberOrder.getIsPay()) || "1".equals(jpoMemberOrder.getSubmitStatus())){
                	amt = jpoMemberOrder.getAmount().toString();
            		actionNo = orderId;
            		actionType = "1";
            		userCode = jpoMemberOrder.getSysUser().getUserCode();
            	}else{
            		return new ModelAndView("redirect:editJfi99billPosLog.html");
            	}
        	}
        	//===================
        	Jfi99billPosSendLog jfi99billPosSendLog = new Jfi99billPosSendLog();
        	jfi99billPosSendLog.setActionNo(actionNo);
        	jfi99billPosSendLog.setActionType(actionType);
        	jfi99billPosSendLog.setCompanyCode("CN");
        	jfi99billPosSendLog.setCreateCode(SessionLogin.getLoginUser(request).getUserCode());
        	jfi99billPosSendLog.setCreateTime(new Date());
        	jfi99billPosSendLog.setReturnTime(0);
        	jfi99billPosSendLog.setUserCode(userCode);
        	jfi99billPosSendLogManager.saveJfi99billPosSendLog(jfi99billPosSendLog);
        	String tmpId = jfi99billPosSendLog.getLogId().toString();
        	String hashMd5Code = StringUtil.encodePassword( jfi99billPosSendLog.getUserCode() + jfi99billPosSendLog.getActionNo() + jfi99billPosSendLog.getActionType() + tmpId, "md5");
        	if(!StringUtil.isEmpty(hashMd5Code)){
            	jfi99billPosSendLog.setHashMd5Code(hashMd5Code);
            	externalTraceNo = hashMd5Code;
            	url = url + "?loginKey=" + loginKey;
            	url = this.appendParam(url, "terminalOperId", terminalOperId);
            	url = this.appendParam(url, "txnType", txnType);
            	url = this.appendParam(url, "amt", amt);
            	url = this.appendParam(url, "externalTraceNo", externalTraceNo);
            	url = this.appendParam(url, "externalCustomerId", externalCustomerId);
            	jfi99billPosSendLog.setUrl(url);
            	jfi99billPosSendLogManager.saveJfi99billPosSendLog(jfi99billPosSendLog);
            	return new ModelAndView("redirect:" + url);
        	}else{
        		return new ModelAndView("redirect:editJfi99billPosLog.html");
        	}
        }
        
        Map map = request.getSession().getAttribute("map") == null?null:(Map)request.getSession().getAttribute("map");
        request.getSession().setAttribute("map", null);
        if(map == null){
        	return new ModelAndView("redirect:editJfi99billPosLog.html");
        }
        boolean isCheckOrder = (Boolean)map.get("isCheckOrder");
        SysUser sysUser =(SysUser)map.get("sysUser");
        if(isCheckOrder){
        	CommonRecord crm = new CommonRecord();
        	crm.addField("sysUser.userCode", sysUser.getUserCode());
        	crm.addField("status", "1");
        	crm.addField("isPay", "0");
        	crm.addField("submitStatus", "1");
        	Pager pager = new Pager("jpoMemberOrderListTable",request, 0);
        	List jpoMemberOrders = jpoMemberOrderManager.getJpoMemberOrdersByCrm(crm,pager);
            request.setAttribute("jpoMemberOrderListTable_totalRows", pager.getTotalObjects());
            request.setAttribute("payType", "1");
            return new ModelAndView("fi/jfi99billPosLogList", Constants.JPOMEMBERORDER_LIST, jpoMemberOrders);
        }else{
        	request.setAttribute("sysUser", sysUser);
        	request.setAttribute("payType", "0");
            return new ModelAndView("fi/jfi99billPosLogList");
        }
    }

    /**
     * 功能函数。将变量值不为空的参数组成字符串
     * @param returnStr
     * @param paramId
     * @param paramValue
     * @return
     */
	private String appendParam(String returnStr,String paramId,String paramValue)
	{
		if(!returnStr.equals(""))
		{
			if(!paramValue.equals(""))
			{
				returnStr=returnStr+"&"+paramId+"="+paramValue;
			}
		}
		else
		{
			if(!paramValue.equals(""))
			{
			returnStr=paramId+"="+paramValue;
			}
		}	
		return returnStr;
	}
}
