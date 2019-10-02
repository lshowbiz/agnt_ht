package com.joymain.jecs.fi.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.FiCoinLog;
import com.joymain.jecs.fi.service.FiCoinLogManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class FiCoinLogController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiCoinLogController.class);
    private FiCoinLogManager fiCoinLogManager = null;
    private JpoMemberOrderManager jpoMemberOrderManager = null;

    public void setFiCoinLogManager(FiCoinLogManager fiCoinLogManager) {
        this.fiCoinLogManager = fiCoinLogManager;
    }

	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
        
        String logIdStr = request.getParameter("logId");
        if(!StringUtils.isEmpty(logIdStr)){
        	List successList=new ArrayList();
        	List errorList=new ArrayList();
        	Exception exception = null;
        	AppException appException = null;
            String[] logId = logIdStr.split(",");
            String submitType = request.getParameter("submitType");
            if("2".equals(submitType)){
                if(logId!=null && logId.length>0){
                	for(int i=0; i<logId.length;i++){
                		FiCoinLog fiCoinLog = fiCoinLogManager.getFiCoinLog(logId[i]);
                		if("2".equals(submitType)){
                			if("2".equals(fiCoinLog.getStatus())){
                				errorList.add(LocaleUtil.getLocalText("checkError.Code.111") + ":" + fiCoinLog.getUniqueCode() + LocaleUtil.getLocalText("fiCoinLog.editedFail"));
                			}else{
                    			try{
                    				jpoMemberOrderManager.resendJmsCoin(fiCoinLog, operatorSysUser);
                    				successList.add(LocaleUtil.getLocalText("fiCoinLog.uniqueCode") + ":" + fiCoinLog.getUniqueCode() + LocaleUtil.getLocalText("fiCoinLog.editedSuc"));
                    			}catch(AppException app){
                    				app.printStackTrace();
                    				appException = app;
                    				errorList.add(LocaleUtil.getLocalText("fiCoinLog.uniqueCode") + ":" + fiCoinLog.getUniqueCode() + LocaleUtil.getLocalText("fiCoinLog.editedFail") + "," + app.getMessage());
                    			}catch(Exception e){
                    				e.printStackTrace();
                    				exception = e;
                    				errorList.add(LocaleUtil.getLocalText("checkError.Code.111") + ":" + fiCoinLog.getUniqueCode() + LocaleUtil.getLocalText("fiCoinLog.editedFail"));
                    			}
                			}
                		}
                	}
                }
            }
            request.setAttribute("successList", successList);
            request.setAttribute("errorList", errorList);
            request.setAttribute("exception", exception);
            request.setAttribute("appException", appException);
        	
        }
        
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("fiCoinLogListTable",request, 20);
        List fiCoinLogs = fiCoinLogManager.getFiCoinLogsByCrm(crm,pager);
        request.setAttribute("fiCoinLogListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("fi/fiCoinLogList", Constants.FICOINLOG_LIST, fiCoinLogs);
    }
}
