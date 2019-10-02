package com.joymain.jecs.bd.webapp.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.bd.model.JbdCubMember;
import com.joymain.jecs.bd.model.JbdCubMemberList;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.webapp.action.BaseController;

/**
 * 创办人查询
 * @author xiaoman001
 *
 */
public class JbdCubMembersController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(JbdCubMembersController.class);
	
	private SysUserManager sysUserManager;


	public SysUserManager getSysUserManager() {
		return sysUserManager;
	}


	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}


	private JdbcTemplate jdbcTemplate = null;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
    
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		String returnMav = "bd/jbdCubMembers";
		String strAction = request.getParameter("strAction");
		
		String userCode = request.getParameter("userCode");
		String status = request.getParameter("status");
		String fyear = request.getParameter("fyear");
		
		if(null == strAction ){
			
			String sql = " select * from JBD_CUB_MEMBER where 1=1";
			
			if(null != userCode && !"".equals(userCode)){
				sql += " and user_Code = '" + userCode +"'";
			}
			if(null != status && !"".equals(status)){
				sql += " and status = " + status;
			}
			if(null != fyear && !"".equals(fyear)){
				sql += " and f_year = " + fyear;
			}
			
		
	        List  jbdCubMembers = this.jdbcTemplate.queryForList(sql);
	        List<JbdCubMember> cubs = new ArrayList<JbdCubMember>();
	        for (int i = 0; i < jbdCubMembers.size(); i++) {
	        	Map jbdCubMemberMap = (Map) jbdCubMembers.get(i);
	        	JbdCubMember cubMember = new JbdCubMember();
	        	cubMember.setUserCode(jbdCubMemberMap.get("USER_CODE").toString());
	        	cubMember.setUserName(getUserName(jbdCubMemberMap.get("USER_CODE").toString()));
	        	cubMember.setFyear(new BigDecimal(jbdCubMemberMap.get("f_year").toString()));
	        	cubMember.setPassStar(jbdCubMemberMap.get("pass_star").toString());
	        	cubMember.setStatus(new Integer(jbdCubMemberMap.get("status").toString()));
	        	cubMember.setDepartmentNum(new BigDecimal(jbdCubMemberMap.get("department_num").toString()));
	        	cubMember.setFstHgtsNum(new BigDecimal(jbdCubMemberMap.get("fst_hgts_num").toString()));
	        	cubMember.setFstHgdsNum(new BigDecimal(jbdCubMemberMap.get("fst_hgds_num").toString()));
	        	cubMember.setFstHg4Num(new BigDecimal(jbdCubMemberMap.get("fst_hg4_num").toString()));
	        	cubMember.setSecHgtsNum(new BigDecimal(jbdCubMemberMap.get("sec_hgts_num").toString()));
	        	cubMember.setSecHgdsNum(new BigDecimal(jbdCubMemberMap.get("sec_hgds_num").toString()));
	        	cubMember.setSecHg4Num(new BigDecimal(jbdCubMemberMap.get("sec_hg4_num").toString()));
	        	cubMember.setElsHgtsNum(new BigDecimal(jbdCubMemberMap.get("els_hgts_num").toString()));
	        	cubMember.setElsHgdsNum(new BigDecimal(jbdCubMemberMap.get("els_hgds_num").toString()));
	        	cubMember.setElsHg4Num(new BigDecimal(jbdCubMemberMap.get("els_hg4_num").toString()));
	        	cubs.add(cubMember);
			}
	        request.setAttribute("jbdCubMembers", cubs);
	        
		}else  if(null != strAction && !"".equals(strAction) && strAction.equals("viewJbdCubMember")){
        	
        	returnMav = "bd/jbdCubMemberList";
        	String usd = request.getParameter("usd");
        
        	String sql = " select * from JBD_CUB_MEMBER_list where team_code = '" +usd+"' and w_month like '" + fyear + "%'";
        	List  list = this.jdbcTemplate.queryForList(sql);
        	
//        	W_MONTH    NUMBER       Y                财月                               
//        	TEAM_CODE  VARCHAR2(20) Y                星级特使编号                       
//        	USER_CODE  VARCHAR2(20) Y                点位编号                           
//        	PASS_STAR  VARCHAR2(2)  Y                点位奖衔                           
//        	DEPARTMENT NUMBER       Y                部门：1基础部门以外最大部门，2基础部门以外第二大部门，99其他部门 
//        	HG_COUNT   NUMBER 
        	
        	List<JbdCubMemberList> jbdCubMemberlist = new ArrayList<JbdCubMemberList>();
            for (int i = 0; i < list.size(); i++) {
            	Map jbdCubMemberMap = (Map) list.get(i);
            	JbdCubMemberList cublist = new JbdCubMemberList();
            	cublist.setwMonth(new BigDecimal(jbdCubMemberMap.get("W_MONTH").toString()));
            	cublist.setTeamCode(jbdCubMemberMap.get("team_code").toString());
            	cublist.setUserCode(jbdCubMemberMap.get("user_code").toString());
            	cublist.setUserName(getUserName(jbdCubMemberMap.get("user_code").toString()));
            	cublist.setPassStar(jbdCubMemberMap.get("pass_star").toString());
            	cublist.setDapartment(new BigDecimal(jbdCubMemberMap.get("department").toString()));
            	cublist.setHgCount(new BigDecimal(jbdCubMemberMap.get("hg_count").toString()));
            	jbdCubMemberlist.add(cublist);
    		}
            request.setAttribute("jbdCubMemberlist", jbdCubMemberlist);
            
        }
        
		return new ModelAndView(returnMav);
	}
	
	
	 private String getUserName(String userCode){
		 SysUser sysUser = sysUserManager.getSysUser(userCode);
		 if(null != sysUser){
			 return sysUser.getUserName();
		 }
		 return "name";
	 }

	
}
