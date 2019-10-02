package com.joymain.jecs.bd.webapp.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;


public class BdOrganiseStatusController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(BdOrganiseStatusController.class);
	private BdPeriodManager bdPeriodManager = null;
	private JmiMemberManager jmiMemberManager;
	private JmiLinkRefManager jmiLinkRefManager;
	private JbdDayBounsCalcManager jbdDayBounsCalcManager;
	private JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager;
	
	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public void setJbdDayBounsCalcManager(
			JbdDayBounsCalcManager jbdDayBounsCalcManager) {
		this.jbdDayBounsCalcManager = jbdDayBounsCalcManager;
	}

	public void setJbdMemberLinkCalcHistManager(
			JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager) {
		this.jbdMemberLinkCalcHistManager = jbdMemberLinkCalcHistManager;
	}

	public void setJmiLinkRefManager(JmiLinkRefManager jmiLinkRefManager) {
		this.jmiLinkRefManager = jmiLinkRefManager;
	}

	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		SysUser defSysUser = SessionLogin.getLoginUser(request);
		
		//TODO:viewSomeData

		String organiseNum=ConfigUtil.getConfigValue(defSysUser.getCompanyCode().toUpperCase(), "bd.organise.num");
		
		List latesBdPeriods = this.bdPeriodManager.getLatestBdPeriodsFweek(DateUtil.convertDateToString(new Date()), StringUtil.formatInt(organiseNum));
		
		//String userCodeStr=ConfigUtil.getConfigValue(defSysUser.getCompanyCode().toUpperCase(), "memberno.unlimit.user");
		
    	boolean unlimitUserFlag=false;
    	Set valueSets = Constants.sysListMap.get("memberno.unlimit.user").entrySet();
    	if (valueSets != null) {
			Iterator iter = valueSets.iterator();
			while (iter.hasNext()) {
				Map.Entry entry=(Map.Entry)iter.next();
				String curUserCode=(String) entry.getKey();
				String loginUserCode=defSysUser.getUserCode();
				if(curUserCode.equals(loginUserCode)){
					unlimitUserFlag=true;
				}
				
			}
		}
		
		
		
		boolean flag=false;
		if("C".equals(defSysUser.getUserType()) || ("M".equals(defSysUser.getUserType()) && unlimitUserFlag)){
			latesBdPeriods = this.bdPeriodManager.getLatestBdPeriodsFweek(DateUtil.convertDateToString(new Date()), 6);
			flag=true;
    	}
		if(!"CN".equals(defSysUser.getCompanyCode())){
			flag=true;
		}
		request.setAttribute("latesBdPeriods", latesBdPeriods);

		if ("bdOrganiseStatusQuery".equals(request.getParameter("strAction"))) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	Date actionTime = null;
			try {
				actionTime = dateFormat.parse(Constants.sysConfigMap.get("AA").get("us_auto_placement_time"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			CommonRecord crm = RequestUtil.toCommonRecord(request);
			String memberNo = crm.getString("memberNo");
			String wweek = crm.getString("wweek");//查询的期数,格式为200802类型
			//重置政周
			wweek=WeekFormatUtil.getFormatWeek("f",wweek);
			
			JmiMember topMiMember=jmiMemberManager.getJmiMember(memberNo);

			if ("showLastMember".equalsIgnoreCase(request.getParameter("doType"))) {
				//跳转至最后一个会员
				String lastMemberNo = memberNo;
				List tmpList= jmiLinkRefManager.getLinkRefbyLinkNoOrderByCreateTime(memberNo);
				
				if (tmpList.size() > 0) {
					JmiLinkRef miLinkRefTmp1 = (JmiLinkRef) tmpList.get(0);
					tmpList = jmiLinkRefManager.getLinkRefbyLinkNo(miLinkRefTmp1.getUserCode());
					while (tmpList.size() > 0) {
						miLinkRefTmp1 = (JmiLinkRef) tmpList.get(0);
						lastMemberNo = miLinkRefTmp1.getUserCode();
						
			
		    			tmpList = jmiLinkRefManager.getLinkRefbyLinkNoOrderByCreateTime(miLinkRefTmp1.getUserCode());
					}
				}
				
				ModelAndView mv = new ModelAndView("redirect:bd_organise_status.html");
				mv.addObject("strAction", "bdOrganiseStatusQuery");
				mv.addObject("firstMemberNo", request.getParameter("firstMemberNo"));
				mv.addObject("memberNo", lastMemberNo);
				mv.addObject("wweek", wweek);
				return mv;
			}
			//获取对应的日结算资料
			HashMap bdDayBounsCalcMap = jbdDayBounsCalcManager.getBdDayBounsCalcBySql(memberNo, new Integer(wweek));
			if (bdDayBounsCalcMap == null) {
				this.saveMessage(request, LocaleUtil.getLocalText("opration.notice.js.bdDayBounsCalc.noExists"));
				return new ModelAndView("bd/bd_organise_status");
			}
			//验证
			if (SessionLogin.getLoginUser(request).getIsManager()) {
				//如果是管理中心,则不验证
			} else if (SessionLogin.getLoginUser(request).getIsCompany()) {
				//如果是公司管理员,则验证用户所属国家
				if (!SessionLogin.getLoginUser(request).getCompanyCode().equals(
				        bdDayBounsCalcMap.get("company_code").toString())) {
					this.saveMessage(request, LocaleUtil.getLocalText("opration.notice.js.companyCode.not.accordant"));
					return new ModelAndView("bd/bd_organise_status");
				}
			} else if (SessionLogin.getLoginUser(request).getIsMember()) {
				//获取当前登录人资料
				JmiMember miMember = jmiMemberManager.getJmiMember(SessionLogin.getLoginUser(request).getUserCode());
				//TODO:viewSomeData
				if(!flag && !SessionLogin.getLoginUser(request).getUserCode().equals(memberNo)){
					return new ModelAndView("bd/bd_organise_status");
				}
				if (!bdDayBounsCalcMap.get("tree_index").toString().startsWith(miMember.getJmiLinkRef().getTreeIndex())) {
					//如果不在接点内
					this.saveMessage(request, LocaleUtil.getLocalText("opration.notice.js.treeIndex.not.accordant"));
					return new ModelAndView("bd/bd_organise_status");
				}
			}

			request.setAttribute("bdDayBounsCalcMap", bdDayBounsCalcMap);
			//获取最近一次发放奖金的保留业绩
			BdPeriod lastCacBdPeriod = this.bdPeriodManager.getLatestSendBonus();
			request.setAttribute("lastCacBdPeriod", lastCacBdPeriod);
			if (lastCacBdPeriod != null) {
				String formatedLastCacWeek = lastCacBdPeriod.getWyear().toString()+ StringUtils.leftPad(lastCacBdPeriod.getWweek().toString(), 2, '0');
				
				Map bdSendRecord = jbdMemberLinkCalcHistManager.getBdSendRecordMap(memberNo,formatedLastCacWeek);
				request.setAttribute("bdSendRecord", bdSendRecord);
			}
			
			List childBdDayBonusCalcs = jbdDayBounsCalcManager.getChildBdDayBounsCalcBySql(topMiMember, new Integer(wweek));
			request.setAttribute("childBdDayBonusCalcs", childBdDayBonusCalcs);

			//如果当前所显示的最高层会员不是当前登录会员
			String firstMemberNo = request.getParameter("firstMemberNo");
			if (StringUtils.isEmpty(firstMemberNo)) {
				firstMemberNo = memberNo;
			}
			JmiMember firstMiMember = jmiMemberManager.getJmiMember(firstMemberNo);
			if (!firstMemberNo.equals(memberNo)) {
				List upMiLinkRefs = new ArrayList();
				JmiMember miMember = jmiMemberManager.getJmiMember(memberNo);
				String upMemberNo = miMember.getUserCode();

				while (!upMemberNo.equals(firstMemberNo)) {

					JmiLinkRef miLinkRef = jmiLinkRefManager.getJmiLinkRef(upMemberNo);
					upMiLinkRefs.add(miLinkRef);

					upMemberNo = miLinkRef.getLinkJmiMember().getUserCode();
				}
				Collections.reverse(upMiLinkRefs);

				request.setAttribute("upMiLinkRefs", upMiLinkRefs);
			}

			request.setAttribute("firstMiMember", firstMiMember);
		}

		return new ModelAndView("bd/bd_organise_status");
	}
}