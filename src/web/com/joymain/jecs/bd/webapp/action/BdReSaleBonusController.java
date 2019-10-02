package com.joymain.jecs.bd.webapp.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.bd.service.JbdDayBounsCalcManager;
import com.joymain.jecs.bd.service.JbdMemberLinkCalcHistManager;
import com.joymain.jecs.mi.model.JmiLinkRef;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiLinkRefManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class BdReSaleBonusController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(BdReSaleBonusController.class);


	private BdPeriodManager bdPeriodManager = null;
	private JmiMemberManager jmiMemberManager;
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}


	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

    	SysUser defSysUser = SessionLogin.getLoginUser(request);
	
    	String formatedWeek=request.getParameter("formatedWeek");
    	String userCode=request.getParameter("userCode");
    	String showLevel=request.getParameter("showLevel");
    	
    	
    	String retrunView="bd/bdReSaleBonus";
    	
        if(StringUtil.isEmpty(formatedWeek)||StringUtil.isEmpty(userCode)){
        	return new ModelAndView(retrunView);
        }
        BdPeriod bdPeriod = this.bdPeriodManager.getBdPeriodByFormatedWeek(WeekFormatUtil.getFormatWeek("f",formatedWeek));
		if (bdPeriod == null) {
			this.saveMessage(request, LocaleUtil.getLocalText("error.wweek.not.existed"));
			return new ModelAndView(retrunView);
		}
		JmiMember miMember = jmiMemberManager.getJmiMember(userCode);
		//如果会员不存在
		if (miMember == null) {
			this.saveMessage(request, LocaleUtil.getLocalText("bdBounsDeduct.error.memberNo.notExists"));
			return new ModelAndView(retrunView);
		}
		
        if(!StringUtil.isInteger(showLevel)){
			this.saveMessage(request, "请输入代数");
			return new ModelAndView(retrunView);
        }

		String limitNum = ConfigUtil.getConfigValue(defSysUser.getCompanyCode().toUpperCase(), "select.cxys.ref.limit");
		if(MeteorUtil.isBlank(limitNum)){
			limitNum = "30";
		}
		if( StringUtil.isInteger(showLevel) && StringUtil.formatInt(showLevel)>StringUtil.formatInt(limitNum)){
			MessageUtil.saveLocaleMessage(request, "查询代数过多，只能查询"+limitNum+"代");
			 return new ModelAndView("redirect:bdReSaleBonus.html");
		}
        
        
        String tableName="";
        
        List bdlist=new ArrayList();

        StringBuffer sb = new StringBuffer(" ");
        sb.append(" Select a.User_Code, a.Link_No, c.User_Name, b.Check_Date, c.State, a.Com_Group_Pv,b.member_level,lv");
		sb.append(" From (Select aa.*, Level Lv From (Select User_Code, Link_No, Com_Group_Pv, w_Week");
		sb.append(" From v_Jbd_Cx_Member Where w_Week ="+formatedWeek+") aa");
		sb.append(" Start With aa.User_Code = '"+userCode+"' Connect By Prior aa.Link_No = aa.User_Code And Level <= "+showLevel+") a left join");
		sb.append(" (Select * From v_Jbd_Member_Link_Calc Where w_Week ="+formatedWeek+") b on a.User_Code = b.User_Code left join");
		sb.append(" Jsys_User c on b.User_Code = c.User_Code Order By Lv");

//		String sql="select a.user_code,a.link_no,b.mi_user_name,a.pass_star,b.check_date,c.state,a.week_group_pv from v_jbd_pass_link a ,jmi_member b ,jsys_user c where a.user_code=b.user_code and b.user_code=c.user_code and a.W_WEEK="+formatedWeek+" and a.USER_CODE='"+userCode+"'";
		log.info("sb.toString()==="+sb.toString());
		List curList=jdbcTemplate.queryForList(sb.toString());
        
		Map map=new HashMap();
		if(!curList.isEmpty()){
			for (int i = 0; i < curList.size(); i++) {
				Map curMap=(Map) curList.get(i);
				log.info("curMap.toString()==="+curMap.toString());
				bdlist.add(curMap);
			}
		}else{
			return new ModelAndView(retrunView);
		}

//        this.getBdBonusTree(bdlist, map, StringUtil.formatInt(formatedWeek), StringUtil.formatInt(showLevel), 0,tableName);
    
        request.setAttribute("bdlist", bdlist);

		return new ModelAndView(retrunView);
	}
 
	
	public void getBdBonusTree(List list, Map map, Integer formatedWeek, Integer maxShowLevel, int level,String tableName) {

		
		String sql1="select a.*,b.check_date,c.state,c.user_name from v_jbd_cx_link a , jmi_member b,jsys_user c    where  a.user_code=b.user_code and a.user_code=c.user_code and  a.link_no='"+map.get("user_code")+"' and a.w_week="+formatedWeek;

		
		List linkList=jdbcTemplate.queryForList(sql1);
		
		
		if(maxShowLevel==0){
    		level = -1;
    	}else{
        	level++;
    	}

    	if(level <= maxShowLevel){
    		if(!linkList.isEmpty()){
    			for (int i = 0; i < linkList.size(); i++) {
    				Map curMap=(Map) linkList.get(i);
    				list.add(curMap);
					this.getBdBonusTree(list, curMap, formatedWeek, maxShowLevel, level,tableName);
				}
    		}
    	}
	
		
		
	}
}