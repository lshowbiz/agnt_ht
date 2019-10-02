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
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class BdAlgebraBonusController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(BdAlgebraBonusController.class);


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
    	String type=request.getParameter("type");
    	String passStar=request.getParameter("passStar");
    	String showLevel=request.getParameter("showLevel");
    	
    	
    	String retrunView="bd/bdAlgebraBonus";
    	
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
        if(StringUtil.blankOrNull(type) ){
			this.saveMessage(request, "请选择奖衔类型");
			return new ModelAndView(retrunView);
        }
        if(!StringUtil.isInteger(showLevel)){
			this.saveMessage(request, "请输入代数");
			return new ModelAndView(retrunView);
        }

		String limitNum = ConfigUtil.getConfigValue(defSysUser.getCompanyCode().toUpperCase(), "select.link.ref.limit");
		if( StringUtil.isInteger(showLevel) && StringUtil.formatInt(showLevel)>StringUtil.formatInt(limitNum)){
			MessageUtil.saveLocaleMessage(request, "查询代数过多，只能查询"+limitNum+"代");
			 return new ModelAndView("redirect:bdAlgebraBonus.html");
		}
        
        
        String tableName="";
		
        
        if("1".equals(type)){//合格奖衔
        	if("1".equals(passStar)){
            	tableName="v_jbd_gem_link";//宝石
            }else if("2".equals(passStar)){
            	tableName="v_jbd_diamond_link";//钻石
            }else if("3".equals(passStar)){
            	tableName="v_jbd_crown_link";//皇冠
            }else if (StringUtil.isEmpty(passStar)){
            	tableName="v_jbd_pass_link";//整网
            }
        }else if ("2".equals(type)){//店铺
        	tableName="v_jbd_store_link";
        }else if ("3".equals(type)){//首席
        	tableName="v_jbd_crown_member";
        }
        

        
        
        List bdlist=new ArrayList();

        

		String sql="select * from "+tableName +" where user_code='"+userCode+"' and w_week="+WeekFormatUtil.getFormatWeek("f",formatedWeek);

		if("3".equals(type)){
			sql+=" and cio_net=1";
		}
		
		
		List curList=jdbcTemplate.queryForList(sql);
        
        
        
		Map map=new HashMap();
		if(!curList.isEmpty()){
			map=(Map) curList.get(0);
			
			if("3".equals(type)){
				map.put("link_no", map.get("newlink_no"));
			}
			
			bdlist.add(map);
		}else{
			return new ModelAndView(retrunView);
		}
        

		setPetName(map);
        this.getBdBonusTree(bdlist, map, WeekFormatUtil.getFormatWeek("f",StringUtil.formatInt(formatedWeek)), StringUtil.formatInt(showLevel), 0,tableName,type);
    
        request.setAttribute("bdlist", bdlist);

		return new ModelAndView(retrunView);
	}
	private void setPetName(Map map){
		JmiMember jmiMember=jmiMemberManager.getJmiMember(map.get("user_code").toString());
		if(jmiMember!=null){
			map.put("pet_name", jmiMember.getPetName());
		}
	}
	
	public void getBdBonusTree(List list, Map map, Integer formatedWeek, Integer maxShowLevel, int level,String tableName,String type) {
		String linkNoStr="link_no";
		if("3".equals(type)){
			linkNoStr="newlink_no";
			map.put("link_no", map.get("newlink_no"));
		}
		
		String sql1="select * from "+tableName +" where "+linkNoStr+"='"+map.get("user_code")+"' and w_week="+formatedWeek;
		
		if("3".equals(type)){
			sql1+=" and cio_net=1";
		}
		
		
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
    				setPetName(curMap);
    				list.add(curMap);
					this.getBdBonusTree(list, curMap, formatedWeek, maxShowLevel, level,tableName, type);
				}
    		}
    	}
	
		
		
	}
}