package com.joymain.jecs.fi.webapp.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.fi.model.JfiDepositSend;
import com.joymain.jecs.fi.service.JfiDepositSendManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JfiDepositSendController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiDepositSendController.class);
    private JfiDepositSendManager jfiDepositSendManager = null;

    public void setJfiDepositSendManager(JfiDepositSendManager jfiDepositSendManager) {
        this.jfiDepositSendManager = jfiDepositSendManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }


        SysUser defSysUser = SessionLogin.getLoginUser(request);
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jfiDepositSendListTable",request, 20);

    	WeekFormatUtil.setSearchFweek(crm);
        String strAction=request.getParameter("strAction");
        String userCode=request.getParameter("userCode");
        String wweek=request.getParameter("wweek");
        
        List<String> errorList=new ArrayList<String>();
        
        

        if ("post".equalsIgnoreCase(request.getMethod()) &&"deleteDepositMoney".equals(request.getParameter("strAction"))) {
        	String[] adviceCodes = request.getParameter("strAdviceCodes").split(",");
        	boolean flag=false;
        	for (int i = 0; i < adviceCodes.length; i++) {
    			if(!StringUtils.isEmpty(adviceCodes[i])){
    				JfiDepositSend jfiDepositSend=jfiDepositSendManager.getJfiDepositSend(adviceCodes[i]);
    				if("1".equals(jfiDepositSend.getStatus())){
    					jfiDepositSendManager.removeJfiDepositSend(adviceCodes[i]);
    					flag=true;
    				}else{
    					errorList.add(jfiDepositSend.getUserCode()+":已发放，删除失败");
    				}
    			}
    			
        	}
        	if(flag){
        		this.saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.operaterSuccess"));
        	}
        	
        }
        
        
        //发放
        if ("post".equalsIgnoreCase(request.getMethod()) &&"depositMoneySend".equals(request.getParameter("strAction"))) {
        	String[] adviceCodes = request.getParameter("strAdviceCodes").split(",");
        	
    		
    		for (int i = 0; i < adviceCodes.length; i++) {
        			if(!StringUtils.isEmpty(adviceCodes[i])){
        				try {
            				jfiDepositSendManager.sendToBankbook(defSysUser, adviceCodes[i]);
						} catch (Exception e) {
							log.info("保证金发放");
							log.info(e);
							errorList.add(e.getMessage());
						}
        			}
    		}
			this.saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.operaterSuccess"));
        }
        

        if ("post".equalsIgnoreCase(request.getMethod()) &&"depositMoneySendAll".equals(request.getParameter("strAction"))) {

            //String sendWeek=request.getParameter("sendWeek");
            
            //sendWeek=WeekFormatUtil.getFormatWeek("f", sendWeek);
            

            List jfiDepositSends = jfiDepositSendManager.getJfiDepositSendsByCrm(crm,pager);
            for (int i = 0; i < jfiDepositSends.size(); i++) {
            	JfiDepositSend curJfiDepositSend=(JfiDepositSend) jfiDepositSends.get(i);
            	try {
            		jfiDepositSendManager.sendToBankbook(defSysUser, curJfiDepositSend.getId().toString());
				} catch (Exception e) {
					log.info("保证金发放");
					log.info(e);
					errorList.add(e.getMessage());
				}
            	
			}
            if(!jfiDepositSends.isEmpty()){
            	this.saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.operaterSuccess"));
            }
			
            
        }
        List jfiDepositSends =null;
        if(!StringUtil.isEmpty(wweek) || !StringUtil.isEmpty(userCode)){

             jfiDepositSends = jfiDepositSendManager.getJfiDepositSendsByCrm(crm,pager);
            BigDecimal depositMoneySum=jfiDepositSendManager.getSumDepositMoney(crm);
            request.setAttribute("depositMoneySum", depositMoneySum);
            
            /*****/
        }
        

        request.setAttribute("errorList", errorList);

        request.setAttribute("jfiDepositSendListTable_totalRows", pager.getTotalObjects());
        
        

        return new ModelAndView("fi/jfiDepositSendList", "jfiDepositSendList", jfiDepositSends);
    }
}
