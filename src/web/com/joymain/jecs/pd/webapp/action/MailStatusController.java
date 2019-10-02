package com.joymain.jecs.pd.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pd.model.MailStatus;
import com.joymain.jecs.pd.model.PdSendInfo;
import com.joymain.jecs.pd.service.MailStatusManager;
import com.joymain.jecs.pd.service.PdSendInfoManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class MailStatusController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(MailStatusController.class);
    private MailStatusManager mailStatusManager = null;
    private PdSendInfoManager pdSendInfoManager;

    public void setMailStatusManager(MailStatusManager mailStatusManager) {
        this.mailStatusManager = mailStatusManager;
    }
    
    public void setPdSendInfoManager(PdSendInfoManager pdSendInfoManager) {
		this.pdSendInfoManager = pdSendInfoManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        String strAction = request.getParameter("strAction");
        //根据物流单号获取物流信息
        if((!StringUtil.isEmpty(strAction))&&"logisticsDo".equals(strAction)){
        	String logisticsDo = request.getParameter("logisticsDo");
        	
        	//modify 2015-11-26 fu---- 
        	String siNo = request.getParameter("siNo");
        	String memberOrderNo = request.getParameter("memberOrderNo");
        	PdSendInfo pdSendInfo = null;
        	if(!StringUtil.isEmpty(siNo)){
        		pdSendInfo = pdSendInfoManager.getPdSendInfo(siNo);
        	}
        	//modify 2015-11-26 fu----
        	//jpoMemberList
        	String jpoMemberList = request.getParameter("jpoMemberList");
        	if(!StringUtil.isEmpty(logisticsDo)){
        		//订单明细页面查询物流单号的详细物流信息(物流单号做一下特殊处理)-------------------------begin
        		//if((!StringUtil.isEmpty(jpoMemberList))&&"jpoMemberList".equals(jpoMemberList)){
        			String[] a = logisticsDo.split("</br>");
        			String b = "";
        			for(int i=0;i<a.length;i++){
        				if(StringUtil.isEmpty(b)){
        					b = a[i];
        				}else{
        					b += ","+a[i];
        				}
        			}
        			logisticsDo = b;
        		//}
        		//订单明细页面查询物流单号的详细物流信息-------------------------end
        		List<MailStatus> list = mailStatusManager.sendInterfaceByMailStatus(logisticsDo,siNo,memberOrderNo,pdSendInfo);
        		 request.setAttribute("list", list);
        		 return new ModelAndView("pd/mailStatusList");
        	}
        	return null;
        }else{
        	MailStatus mailStatus = new MailStatus();
            // populate object with request parameters
            BeanUtils.populate(mailStatus, request.getParameterMap());

    	//List mailStatuss = mailStatusManager.getMailStatuss(mailStatus);
    	/**** auto pagination ***/
    	CommonRecord crm=RequestUtil.toCommonRecord(request);
            Pager pager = new Pager("mailStatusListTable",request, 20);
            List mailStatuss = mailStatusManager.getMailStatussByCrm(crm,pager);
            request.setAttribute("mailStatusListTable_totalRows", pager.getTotalObjects());
            /*****/
            //Constants.MAILSTATUS_LIST
           return null;
        }
        
        
    }
}
