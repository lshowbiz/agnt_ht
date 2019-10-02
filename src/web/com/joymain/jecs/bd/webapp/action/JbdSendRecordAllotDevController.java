package com.joymain.jecs.bd.webapp.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.service.BdOutwardBankManager;
import com.joymain.jecs.bd.service.JbdSendRecordHistManager;
import com.joymain.jecs.bd.service.JbdSendRecordNoteManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysBankManager;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdSendRecordAllotDevController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdSendRecordAllotDevController.class);
    private JbdSendRecordHistManager jbdSendRecordHistManager;   
    private JbdSendRecordNoteManager jbdSendRecordNoteManager;


	public void setJbdSendRecordNoteManager(
			JbdSendRecordNoteManager jbdSendRecordNoteManager) {
		this.jbdSendRecordNoteManager = jbdSendRecordNoteManager;
	}



    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        String companyCode="";
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        if("C".equals(defSysUser.getUserType())){
        	companyCode=defSysUser.getCompanyCode();  
    		if("AA".equals(defSysUser.getCompanyCode())){
    			companyCode = request.getParameter("company");
    		}
    	}
        
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdSendRecordListTable",request, 0);

        crm.addField("companyCode", companyCode);
        
        crm.addField("currentDevGreater","0");

        crm.addField("sendStatusDev","1");
        crm.addField("freezeStatus","0");

    	List errorList=new ArrayList();

        WeekFormatUtil.setSearchFweek(crm);
        
        List jbdSendRecords =null;
        
//        if ("post".equalsIgnoreCase(request.getMethod()) &&"allotSelectedFi".equals(request.getParameter("strAction"))) {
//        	
//            	String[] adviceCodes = request.getParameter("strAdviceCodes").split(",");
//            	
//            		
//            		for (int i = 0; i < adviceCodes.length; i++) {
//	            			if(!StringUtils.isEmpty(adviceCodes[i])){
//		                			jbdSendRecordHistManager.saveInFiBook(defSysUser, adviceCodes[i]);
//
//            			}
//            		}
//    				this.saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.operaterSuccess"));
//        }

        if ("post".equalsIgnoreCase(request.getMethod()) &&"allotSelectedDevAll".equals(request.getParameter("strAction"))) {
        	pager = new Pager("jbdSendRecordListTable",request, 0);
        	jbdSendRecords = jbdSendRecordNoteManager.getJbdSendRecordsBySqlByCrm(crm,pager);
        	
        	jbdSendRecordHistManager.saveInDevFiBookAll(defSysUser, jbdSendRecords);
    		/*String bdSend = ConfigUtil.getConfigValue("AA", "bd.send.dev");
    		String bdSendNum = ConfigUtil.getConfigValue("AA", "bd.send.num");
        	
    		if("0".equals(bdSend)){

            	for (int i = 0; i <jbdSendRecords.size(); i++) {
    				Map map=(Map) jbdSendRecords.get(i);
    				String id=map.get("id").toString();
    				try {
            			jbdSendRecordHistManager.saveInDevFiBook(defSysUser, id);
        			} catch (AppException e) {
        				errorList.add("id: "+id+" 失败!");
        			}
    			}
            	
    		}else if("1".equals(bdSend)){
    			
    			 List list=new ArrayList();
    			  for (int i = 0; i < jbdSendRecords.size(); i++) {
    				  list.add(jbdSendRecords.get(i));
    				  if(i>0){
    					  if(i%StringUtil.formatInt(bdSendNum)==0){
    						  new DevThread(list,defSysUser,jbdSendRecordHistManager).start();

    						  list=new ArrayList();
    					  }
    				  }
    			  }
    			  if(list.size()>0){
    				  new DevThread(list,defSysUser,jbdSendRecordHistManager).start();

    			  }
    			
    		}*/
        	
        	
        	
			this.saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.operaterSuccess"));
			
        }
        
        
        
        String userCode=request.getParameter("userCode");
        BigDecimal sumMoney=new BigDecimal(0);
        if(userCode!=null){

            jbdSendRecords = jbdSendRecordNoteManager.getJbdSendRecordsBySqlByCrm(crm,pager);
            //sumMoney=jbdSendRecordHistManager.getJbdSendRecordsBySqlByCrmSumDev(crm);
        }
        //request.setAttribute("sumMoney", sumMoney);
        request.setAttribute("jbdSendRecordListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("bd/jbdSendRecordAllotDev", Constants.JBDSENDRECORD_LIST, jbdSendRecords);
    }

	public void setJbdSendRecordHistManager(
			JbdSendRecordHistManager jbdSendRecordHistManager) {
		this.jbdSendRecordHistManager = jbdSendRecordHistManager;
	}
	
	
}



/* class DevThread extends Thread {  
	private List list=new ArrayList();
	private SysUser defSysUser;
	private JbdSendRecordHistManager jbdSendRecordHistManager;
	public DevThread(List list,SysUser defSysUser,JbdSendRecordHistManager jbdSendRecordHistManager) {  
		this.list=list;
		this.defSysUser=defSysUser;
		this.jbdSendRecordHistManager=jbdSendRecordHistManager;
	}
	public void run() { 

//		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
//
//		JbdSendRecordHistManager jbdSendRecordHistManager=(JbdSendRecordHistManager) ac.getBean("jbdSendRecordHistManager");

		
		for (int i = 0; i < list.size(); i++) {
			Map map=(Map) list.get(i);
			String id=map.get("id").toString();
			
			try {
				this.jbdSendRecordHistManager.saveInDevFiBook(defSysUser, id);
			} catch (Exception e) {
				e.printStackTrace();
			}
    		
				
		}
	}

	
}*/


