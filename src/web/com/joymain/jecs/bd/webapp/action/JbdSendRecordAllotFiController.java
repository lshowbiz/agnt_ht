package com.joymain.jecs.bd.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.service.BdOutwardBankManager;
import com.joymain.jecs.bd.service.JbdSendRecordHistManager;
import com.joymain.jecs.bd.service.JbdSendRecordNoteManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysBankManager;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.GlobalVar;
import com.joymain.jecs.util.MeteorUtil;
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
import com.threadpool.ThreadPool;
import com.threadpool.ThreadTask;

public class JbdSendRecordAllotFiController extends BaseController implements Controller{
    private final Log log = LogFactory.getLog(JbdSendRecordAllotFiController.class);
    private JbdSendRecordHistManager jbdSendRecordHistManager;
    private JbdSendRecordNoteManager jbdSendRecordNoteManager;
	public void setJbdSendRecordNoteManager(
			JbdSendRecordNoteManager jbdSendRecordNoteManager) {
		this.jbdSendRecordNoteManager = jbdSendRecordNoteManager;
	}
    private BdOutwardBankManager bdOutwardBankManager;
    private SysUserManager sysUserManager;
    private SysBankManager sysBankManager;
    public void setSysBankManager(SysBankManager sysBankManager) {
		this.sysBankManager = sysBankManager;
	}

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public void setBdOutwardBankManager(BdOutwardBankManager bdOutwardBankManager) {
		this.bdOutwardBankManager = bdOutwardBankManager;
	}


    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        String companyCode="";
        final SysUser defSysUser = SessionLogin.getLoginUser(request);
        if("C".equals(defSysUser.getUserType())){
        	companyCode=defSysUser.getCompanyCode();  
    		if("AA".equals(defSysUser.getCompanyCode())){
    			companyCode = request.getParameter("company");
    		}
    	}
        
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdSendRecordListTable",request, 0);

        crm.addField("companyCode", companyCode);
        crm.addField("registerStatus","1");
        crm.addField("reissueStatus","1");
        crm.addField("remittanceMoneyGreater","0");
        crm.addField("notEqualCardType","0");
        crm.addField("exitDateNull","exitDateNull");
        crm.addField("active","0");
        crm.addField("freezeStatus","0");
        crm.addField("allot","0");
        crm.addField("flag","0");
//        crm.addField("allotFi","allotFi");
        
//        String allot=request.getParameter("allot");
//        request.setAttribute("allot", allot);
//        request.setAttribute("startAllotMoney", 500);

    	List errorList=new ArrayList();

        WeekFormatUtil.setSearchFweek(crm);
        
        String startAllotMoney=request.getParameter("startAllotMoney");
        String endAllotMoney=request.getParameter("endAllotMoney");

        List jbdSendRecords =new ArrayList();
        //String tf = "";

    	
        if ("post".equalsIgnoreCase(request.getMethod()) &&"allotSelectedFi".equals(request.getParameter("strAction"))) {
        	
            	String[] adviceCodes = request.getParameter("strAdviceCodes").split(",");
            	/*String stime=MeteorUtil.doDateToConvert(new Date());
            	log.info("执行开始时间："+stime);
            	for (int i = 0; i < adviceCodes.length; i++) {
        			if(!StringUtils.isEmpty(adviceCodes[i])){
                		jbdSendRecordHistManager.saveInFiBook(defSysUser, adviceCodes[i],"1");
        			}
				}	
            		
        		String etime=MeteorUtil.doDateToConvert(new Date());
            	log.info("执行结束时间："+etime);*/
            	jbdSendRecordHistManager.saveInFiBookAll(adviceCodes, defSysUser, null);
    				this.saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.operaterSuccess"));
        }

        if ("post".equalsIgnoreCase(request.getMethod()) &&"allotSelectedFiAll".equals(request.getParameter("strAction"))) {

        	jbdSendRecords = jbdSendRecordNoteManager.getJbdSendRecordsBySqlByCrmNew(crm,pager);
        	

        	jbdSendRecordHistManager.saveInFiBookAll(null, defSysUser, jbdSendRecords);
        	this.saveMessage(request, "进电子存折正在运行中，请不要重复操作");
    		/*String bdSend = ConfigUtil.getConfigValue("AA", "bd.send.fi");
    		String bdSendNum = ConfigUtil.getConfigValue("AA", "bd.send.num");
    		long startTime = System.currentTimeMillis();
    		if("0".equals(bdSend)){
    			log.info("普通批量处理电子存折条数:" + jbdSendRecords.size());
    			for (int i = 0; i <jbdSendRecords.size(); i++) {
    				Map map=(Map) jbdSendRecords.get(i);
    				String id=map.get("id").toString();
    				try {
            			jbdSendRecordHistManager.saveInFiBook(defSysUser, id,"1");
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
	   						  new FiThread(list,defSysUser,jbdSendRecordHistManager).start();
	   						  list=new ArrayList();
	   					  }
	   				  }
	   			  }
	   			  if(list.size()>0){
	   				  new FiThread(list,defSysUser,jbdSendRecordHistManager).start();
	
	   			  }
    		}else if("2".equals(bdSend)){//使用线程池，
    			List jbdSendRecords1 = jbdSendRecordNoteManager.getJbdSendRecordsBySqlByCrmNew(crm,pager);
//    			log.info("11111111====="+jbdSendRecords1.size());
    			if(null!=jbdSendRecords1 && jbdSendRecords1.size()>0){
    				log.info("线程池批量处理电子存折条数:" + jbdSendRecords1.size());
//    				log.info("2222222====="+GlobalVar.theadStatus+"--------"+GlobalVar.theadListSize);
    				if("0".equals(GlobalVar.theadStatus)){
    					GlobalVar.theadListSize = jbdSendRecords1.size();
        				GlobalVar.theadStatus = "1";
//        				log.info("333====="+GlobalVar.theadStatus+"--------"+GlobalVar.theadListSize);
        				*//**
            			 * 使用线程池
            			 *//*
        				for (int i = 0; i <jbdSendRecords1.size(); i++) {
        					final Map map=(Map) jbdSendRecords1.get(i);
	            			ThreadPool.addThreadToPool(new ThreadTask(map){
	            				private static final long serialVersionUID = -232113818997617512L;
	            				
	            				public void run(){
	            					try {
        								String id=map.get("id").toString();
        								jbdSendRecordHistManager.saveInFiBook(defSysUser, id,"1");
	        							
        								dijian();
//	        							log.info("44444====="+GlobalVar.theadStatus+"--------"+GlobalVar.theadListSize);
	        							if(GlobalVar.theadListSize==0){
	        								GlobalVar.theadStatus = "0";
	        							}
//	        							log.info("555555555====="+GlobalVar.theadStatus+"--------"+GlobalVar.theadListSize);
	        							Thread.sleep(3000);//一个线程执行完后休息一下
	        						} catch (Exception e) {
	        							// TODO Auto-generated catch block
	        							log.info("id: "+map.get("id").toString()+" 失败!");
	        							
	        							dijian();
	        							log.info("66666666异常====="+GlobalVar.theadStatus+"--------"+GlobalVar.theadListSize);
	        							if(GlobalVar.theadListSize==0){
	        								GlobalVar.theadStatus = "0";
	        							}
	        							log.info("7777777异常====="+GlobalVar.theadStatus+"--------"+GlobalVar.theadListSize);
	        							log.error(e.getMessage(),e);
	        						}
	            				}
	            			});
        				}
    				}else{
    					tf = "1";
    				}
    				
    			}
    			
    		}
*/
/*        	List jbdBonusBalances=jbdSendRecordHistManager.getJbdBonusBalanceUserCodes(startAllotMoney, endAllotMoney, "0", "0");
			for (int j = 0; j < jbdBonusBalances.size(); j++) {
				Map jbdBonusBalanceMap=(Map) jbdBonusBalances.get(j);
				String resUserCode=jbdBonusBalanceMap.get("user_code").toString();
				List curBonus=jbdSendRecordHistManager.getJbdSendRecordsByUserCode(resUserCode, companyCode,"fi");
				for (int i = 0; i < curBonus.size(); i++) {
					Map map=(Map) curBonus.get(i);
					try {
	        			jbdSendRecordHistManager.saveInFiBook(defSysUser, map.get("id").toString());
	    			} catch (AppException e) {
	    				errorList.add("id: "+map.get("id").toString()+" 失败!");
	    			}
				}
			}*/
			
			
			
//        	for (int i = 0; i <jbdSendRecords.size(); i++) {
//				Map map=(Map) jbdSendRecords.get(i);
//				String id=map.get("id").toString();
//				
//				
//			}
    		
        }
        
      
        
        
        //系统银行
//        List sysBanks=sysBankManager.getSysBankByCompanyCode(companyCode);
//        request.setAttribute("sysBanks", sysBanks);
        
		
        String userCode=request.getParameter("userCode");
        
        
//        BigDecimal sumMoney=new BigDecimal(0);
        if(userCode!=null){
            jbdSendRecords = jbdSendRecordNoteManager.getJbdSendRecordsBySqlByCrmNew(crm,pager);
//            sumMoney=jbdSendRecordHistManager.getJbdSendRecordsBySqlByCrmSumNew(crm);

/*			if(RequestUtil.saveOperationSession(request, "jbdSendRecordAllotFi", 120L)!=0){
	       		  return new ModelAndView("redirect:jbdSendRecordAllotFi.html?strAction=jbdSendRecordAllotFi");
	       	 }
			
        	List jbdBonusBalances=jbdSendRecordHistManager.getJbdBonusBalanceUserCodes(startAllotMoney, endAllotMoney, "0", "0");
			for (int j = 0; j < jbdBonusBalances.size(); j++) {
				Map jbdBonusBalanceMap=(Map) jbdBonusBalances.get(j);
				String resUserCode=jbdBonusBalanceMap.get("user_code").toString();
				List curBonus=jbdSendRecordHistManager.getJbdSendRecordsByUserCode(resUserCode, companyCode,"fi");
				jbdSendRecords.addAll(curBonus);
			}*/
        	
        	
        }
//        request.setAttribute("sumMoney", sumMoney);
        request.setAttribute("jbdSendRecordListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("bd/jbdSendRecordAllotFi", Constants.JBDSENDRECORD_LIST, jbdSendRecords);
    }

	public void setJbdSendRecordHistManager(
			JbdSendRecordHistManager jbdSendRecordHistManager) {
		this.jbdSendRecordHistManager = jbdSendRecordHistManager;
	}
	
	public synchronized void dijian(){
		GlobalVar.theadListSize--;
	}

}




/*class FiThread extends Thread {  
	private List list=new ArrayList();
	private SysUser defSysUser;
	private JbdSendRecordHistManager jbdSendRecordHistManager;
	public FiThread(List list,SysUser defSysUser,JbdSendRecordHistManager jbdSendRecordHistManager) {  
		this.list=list;
		this.defSysUser=defSysUser;
		this.jbdSendRecordHistManager=jbdSendRecordHistManager;
	}
	public void run() { 

		
		for (int i = 0; i < list.size(); i++) {
			Map map=(Map) list.get(i);
			String id=map.get("id").toString();
			
			try {
				this.jbdSendRecordHistManager.saveInFiBook(defSysUser, id,"2");
			} catch (Exception e) {
				e.printStackTrace();
			}
   		
				
		}
	}

	
}*/


