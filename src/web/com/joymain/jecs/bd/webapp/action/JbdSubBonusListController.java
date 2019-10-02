package com.joymain.jecs.bd.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.JbdSubBonusList;
import com.joymain.jecs.bd.service.JbdSendRecordHistManager;
import com.joymain.jecs.bd.service.JbdSubBonusListManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdSubBonusListController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdSubBonusListController.class);
    private JbdSubBonusListManager jbdSubBonusListManager = null;

    public void setJbdSubBonusListManager(JbdSubBonusListManager jbdSubBonusListManager) {
        this.jbdSubBonusListManager = jbdSubBonusListManager;
    }
    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }


        CommonRecord crm=RequestUtil.toCommonRecord(request);

        String treatedNo=request.getParameter("treatedNo");
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        Pager pager = new Pager("jbdSubBonusListListTable",request, 0);

        crm.addField("status","1");
        
        List jbdSubBonusLists = null;

        if ("post".equalsIgnoreCase(request.getMethod()) &&"allotSelectedSubBonusAll".equals(request.getParameter("strAction")) && !StringUtil.isEmpty(treatedNo)) {
        	/*jbdSubBonusLists = jbdSubBonusListManager.getJbdSubBonusListsBySql(crm,pager);
        	
        	 List list=new ArrayList();
			 for (int i = 0; i < jbdSubBonusLists.size(); i++) {
				  list.add(jbdSubBonusLists.get(i));
				  if(i>0){
					  if(i%5000==0){
						  new SubBonusThread(list,defSysUser,jbdSubBonusListManager).start();

						  list=new ArrayList();
					  }
				  }
			  }
			  if(list.size()>0){
				  new SubBonusThread(list,defSysUser,jbdSubBonusListManager).start();
			  }
			  if(!jbdSubBonusLists.isEmpty()){
				  this.saveMessage(request, "正在执行");
			  }*/
        	String sql="insert into jpo_bankbook_pay_list (id,user_code,user_sp_code,member_order_no,in_type,create_time,dz_amt,data_type,money_type1,notes,check_user_code) "
        			+ " (select SEQ_LIST.Nextval,user_code,user_code,id,13,sysdate,amount,'1',180,remark,'"+defSysUser.getUserCode()+"' from jbd_sub_bonus_list where status='1' and treated_no='"+treatedNo+"' )";
			 
        	jbdSubBonusLists = jbdSubBonusListManager.getJbdSubBonusListsBySql(crm,pager);
        	 if(!jbdSubBonusLists.isEmpty()){
        		 jdbcTemplate.execute(sql);
        		 this.saveMessage(request, "正在执行");
        	 }
        	
        }
        
        
        
        if(!StringUtil.isEmpty(treatedNo)){
        	 jbdSubBonusLists = jbdSubBonusListManager.getJbdSubBonusListsBySql(crm,pager);
        }
        
        
        request.setAttribute("jbdSubBonusListListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdSubBonusListList", "jbdSubBonusListList", jbdSubBonusLists);
    }
    
    
    
    
/*    class SubBonusThread extends Thread {  
    	private List list=new ArrayList();
    	private SysUser defSysUser;
    	private JbdSubBonusListManager jbdSubBonusListManager ;
    	public SubBonusThread(List list,SysUser defSysUser,JbdSubBonusListManager jbdSubBonusListManager) {  
    		this.list=list;
    		this.defSysUser=defSysUser;
    		this.jbdSubBonusListManager=jbdSubBonusListManager;
    	}
    	public void run() { 
    		
    		for (int i = 0; i < list.size(); i++) {
    			Map map=(Map) list.get(i);
    			String id=map.get("id").toString();
    			
    			try {
    				this.jbdSubBonusListManager.saveInSubBonusFiBook(defSysUser, id);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
        		
    				
    		}
    	}

    	
    }*/
}
