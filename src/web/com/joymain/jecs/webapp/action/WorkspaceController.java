package com.joymain.jecs.webapp.action;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.am.service.AmAnnounceManager;
import com.joymain.jecs.am.service.AmNewManager;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.fi.service.FiCoinLogManager;
import com.joymain.jecs.fi.service.FoundationItemManager;
import com.joymain.jecs.fi.service.FoundationOrderManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysLoginLog;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysLoginLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.data.SpringStoredProcedure;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 欢迎页面
 * @author Alvin
 *
 */
public class WorkspaceController implements Controller {
	private final Log log = LogFactory.getLog(WorkspaceController.class);
	
	private JdbcTemplate jdbcTemplateReport = null;
	private AmAnnounceManager amAnnounceManager = null;
	private SysLoginLogManager sysLoginLogManager = null;
	private BdPeriodManager bdPeriodManager = null;
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    private FiCoinLogManager fiCoinLogManager = null;
    
    private AmNewManager amNewManager = null;
    private FoundationOrderManager foundationOrderManager = null;
	private FoundationItemManager foundationItemManager = null;

	public void setFoundationOrderManager(FoundationOrderManager foundationOrderManager) {
		this.foundationOrderManager = foundationOrderManager;
	}

	public void setFoundationItemManager(FoundationItemManager foundationItemManager) {
		this.foundationItemManager = foundationItemManager;
	}
    public void setAmNewManager(AmNewManager amNewManager) {
        this.amNewManager = amNewManager;
    }

	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
        this.jpoMemberOrderManager = jpoMemberOrderManager;
    }

	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public void setSysLoginLogManager(SysLoginLogManager sysLoginLogManager) {
		this.sysLoginLogManager = sysLoginLogManager;
	}

	public void setAmAnnounceManager(AmAnnounceManager amAnnounceManager) {
		this.amAnnounceManager = amAnnounceManager;
	}


	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplateReport) {
		this.jdbcTemplateReport = jdbcTemplateReport;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

        String doubleView = ConfigUtil.getConfigValue(SessionLogin.getLoginUser(request).getCompanyCode().toUpperCase(), "double.view");
        request.setAttribute("doubleView", doubleView);
        
    	SysUser defSysUser = SessionLogin.getLoginUser(request);
    	if(defSysUser.getIsMember()){
    		JmiMember jmiMember = defSysUser.getJmiMember();
    		String [] msg = {amAnnounceManager.getNoReadNum(jmiMember) + ""};
    		String systemThingMsg = LocaleUtil.getLocalText("system.thing.msg", msg);
    		
    		BigDecimal coin = fiCoinLogManager.getFiCoinLogAmtByUserCode(jmiMember.getUserCode(), "B");
    		request.setAttribute("coin", coin);

    		//获取当前对应的期数
    		BdPeriod bdPeriod = this.bdPeriodManager.getBdPeriodByTime(new Date(), null);
    		if (bdPeriod == null) {
    			bdPeriod = new BdPeriod();
    		}
    		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		String week = bdPeriod.getFyear() + (bdPeriod.getFweek().toString().length()==2 ? bdPeriod.getFweek().toString() : "0" + bdPeriod.getFweek().toString());
    		String month = bdPeriod.getFyear() + (bdPeriod.getFmonth().toString().length()==2 ? bdPeriod.getFmonth().toString() : "0" + bdPeriod.getFmonth().toString());
    		String sql1 = "Select Min(start_time) start_time ,Max(end_time) end_time From Jbd_Period Where f_year=" + bdPeriod.getFyear() + " And f_month=" + bdPeriod.getFmonth() + "";
    		List jbdPeriod = this.jdbcTemplateReport.queryForList(sql1);
    		Map mapPeriod = (Map)jbdPeriod.get(0);
    		
			long endTime = bdPeriod.getEndTime().getTime()-1000l;
    		
    		
    		String [] cale = {week, dateFormat.format(bdPeriod.getStartTime()), dateFormat.format(new Date(endTime)), month, dateFormat.format(new Date(((java.sql.Timestamp)mapPeriod.get("start_time")).getTime())), dateFormat.format(new Date(((java.sql.Timestamp)mapPeriod.get("end_time")).getTime()-1000l)),bdPeriod.getFyear().toString(),bdPeriod.getFmonth().toString(),bdPeriod.getFweek().toString() };
    		String systemCale = LocaleUtil.getLocalText("system.cale", cale);
    		
    		
    		DateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");
    		Map mapCAL = new HashMap();
    		//mapCAL.put("min", arg1);
    		//mapCAL.put("max", arg1);
    		mapCAL.put("selection1", dateFormat1.format(bdPeriod.getStartTime()));
    		mapCAL.put("selection2", dateFormat1.format(new Date(endTime)));
    		mapCAL.put("week", week);
    		mapCAL.put("month", month);
    		request.setAttribute("mapCAL", mapCAL);
    		
    		int days = 0;
    		int num=Integer.parseInt(Constants.sysConfigMap.get(defSysUser.getCompanyCode()).get("member_upgrade_day"));
        	Date checkDate=jmiMember.getCheckDate()==null?jmiMember.getCreateTime():jmiMember.getCheckDate();
        	int subDate = DateUtil.daysBetweenDates(new Date(),checkDate);
        	if(num-subDate>0){
        		days = num-subDate;
        	}
    		String [] upGrade = { "<b>&nbsp;" + days + "&nbsp;</b>" };
    		String systemUpgradeDay = LocaleUtil.getLocalText("system.upgrade.day", upGrade);
    		
    		String sql = "SELECT column_value FROM TABLE(jbd_dealer_statistics('"+ defSysUser.getUserCode() + "'))";
    		List jmiRecommendRefs = this.jdbcTemplateReport.queryForList(sql);
    		Map map = (Map)jmiRecommendRefs.get(0);
    		String[] str = map.get("column_value").toString().split(",");
    		String systemLeaderStr = "";
    		if("0".equals(str[0])){
    			systemLeaderStr = LocaleUtil.getLocalText("system.leader.str1");
    		}else{
    			String strTmp = "" + LocaleUtil.getLocalText("pass.star"+str[0]) + "";
    			systemLeaderStr = LocaleUtil.getLocalText("system.leader.str0",strTmp);
    		}
    		String [] leader = {systemLeaderStr,"" + LocaleUtil.getLocalText("pass.star"+str[1]) + ""};
    		String systemLeader = LocaleUtil.getLocalText("system.leader", leader);
    		String [] leader1 = {str[2] + "PV",str[3] + "PV"};
    		String systemLeader1 = LocaleUtil.getLocalText("system.leader.1", leader1);

			java.util.Calendar startc=java.util.Calendar.getInstance();
			startc.set(2013, 0, 19, 00, 00, 00);
			java.util.Date startDate=startc.getTime();
			Date curDate=new Date();
			if(curDate.after(startDate)){
	    		String [] leader11 = {str[8] + "PV",str[9] + "PV"};
	    		String systemLeader11 = LocaleUtil.getLocalText("system.leader.1.1", leader11);
	    		request.setAttribute("systemLeader11", systemLeader11);
			}
    		String [] leader2 = {str[4] + "PV",str[5] + "PV"};
    		String systemLeader2 = LocaleUtil.getLocalText("system.leader.2", leader2);
    		
    		//
    		String [] leader22 = {str[10] + "PV",LocaleUtil.getLocalText("pass.star"+str[11])}; 
    		String systemLeader22 = LocaleUtil.getLocalText("systemLeader22", leader22);
    		request.setAttribute("systemLeader22", systemLeader22);
    		//
    		
    		
    		String leaderTime = "0";
    		try{
    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			Date date = sdf.parse((str[7]));
    			leaderTime = dateFormat.format(date.getTime()-1000l);
    		}catch(Exception e){
    			//e.printStackTrace();
    		}
    		String [] leader3 = {str[6],leaderTime};
     		String systemLeader3 = LocaleUtil.getLocalText("system.leader.3", leader3);
    		
    		SysLoginLog sysLoginLog = sysLoginLogManager.getLastLogByType(defSysUser.getUserCode(),"1");
    		String loginTime = "";
    		String loginIp = "";
    		if(sysLoginLog!=null){
    			if(sysLoginLog.getOperateTime()!=null){
    				loginTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sysLoginLog.getOperateTime());
    			}
    			if(sysLoginLog.getIpAddr()!=null){
        			loginIp = sysLoginLog.getIpAddr();
    			}
    		}
    		String [] safe1 = {loginTime,loginIp};
    		String systemSafe1 = LocaleUtil.getLocalText("system.safe.1", safe1);
    		request.setAttribute("systemThingMsg", systemThingMsg);
    		request.setAttribute("systemUpgradeDay", systemUpgradeDay);
    		request.setAttribute("systemCale", systemCale);
    		request.setAttribute("days", days);
    		request.setAttribute("systemLeader", systemLeader);
    		request.setAttribute("systemLeader1", systemLeader1);
    		request.setAttribute("systemLeader2", systemLeader2);
    		request.setAttribute("systemLeader3", systemLeader3);
    		request.setAttribute("systemSafe1", systemSafe1);
    	}else{
    		
    	}
    	
    	//查询会员是否参与过爱心365公益基金活动
		if("M".equals(SessionLogin.getLoginUser(request).getUserType())){
			
			SysUser loginUser=SessionLogin.getLoginUser(request);
			//查询当前会员订单
			List tempList=foundationOrderManager.getOrdersByItemTypeAndTime(loginUser.getUserCode());
			if(tempList.size()<1){

				//提示信息
				request.setAttribute("str365FTitle", "Y");
			}
		}
    	
    	//获取新闻列表
    	CommonRecord crm=RequestUtil.toCommonRecord(request);
    	Pager pager = new Pager("amNewListTable",request, 20);
        List amNews = amNewManager.getAmNewsByCrm(crm,pager);
        request.setAttribute("amNews", amNews);
        
		return new ModelAndView("workspace");
	}
    
    /**
     * 会员在MEMBER_UPGRADE_DAY时间内可几次
     * @param sysUser
     * @param member_upgrade_time
     * @return
     */
    private boolean checkMiMemberIsUpGraded(SysUser sysUser, int member_upgrade_time){
        JpoMemberOrder jpoMemberOrder = new JpoMemberOrder();
        jpoMemberOrder.setSysUser(sysUser);
        jpoMemberOrder.setOrderType("2");
        jpoMemberOrder.setStatus("2");
        List poMemberOrderList = jpoMemberOrderManager.getJpoMemberOrdersByMiMember(jpoMemberOrder);
    	if(poMemberOrderList.size() < member_upgrade_time){
    		return true;
    	}
    	return false;
    }

	public void setFiCoinLogManager(FiCoinLogManager fiCoinLogManager) {
		this.fiCoinLogManager = fiCoinLogManager;
	}
}
