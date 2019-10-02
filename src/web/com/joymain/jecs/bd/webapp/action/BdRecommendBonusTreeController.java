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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.bd.service.JbdDayBounsCalcManager;
import com.joymain.jecs.bd.service.JbdMemberLinkCalcHistManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiRecommendRef;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.service.JmiRecommendRefManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class BdRecommendBonusTreeController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(BdRecommendBonusTreeController.class);
	private BdPeriodManager bdPeriodManager = null;
	private JmiMemberManager jmiMemberManager;
	private JmiRecommendRefManager jmiRecommendRefManagerReport;
	
	private JbdDayBounsCalcManager jbdDayBounsCalcManager;
	
	public void setJbdDayBounsCalcManager(
			JbdDayBounsCalcManager jbdDayBounsCalcManager) {
		this.jbdDayBounsCalcManager = jbdDayBounsCalcManager;
	}

	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}



	public void setJmiRecommendRefManagerReport(
			JmiRecommendRefManager jmiRecommendRefManagerReport) {
		this.jmiRecommendRefManagerReport = jmiRecommendRefManagerReport;
	}

	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		SysUser defSysUser = SessionLogin.getLoginUser(request);
		if("M".equals(defSysUser.getUserType())){
    		RequestUtil.freshSession(request,"bdRecommendBonusTreeQuery", Constants.MEMBER_TIME);
	  	}else if("C".equals(defSysUser.getUserType())){
    		RequestUtil.freshSession(request,"bdRecommendBonusTreeQuery", Constants.COMPANY_TIME);
	  	}


        String doubleView = ConfigUtil.getConfigValue(SessionLogin.getLoginUser(request).getCompanyCode().toUpperCase(), "double.view");
        request.setAttribute("doubleView", doubleView);
        
		if ("bdRecommendBonusTreeQuery".equals(request.getParameter("strAction"))) {
			String formatedWeek = request.getParameter("formatedWeek");
			formatedWeek=WeekFormatUtil.getFormatWeek("f",formatedWeek);
			//判断用户所输入的期数是否存在
			BdPeriod bdPeriod = this.bdPeriodManager.getBdPeriodByFormatedWeek(formatedWeek);
			if (bdPeriod == null) {
				this.saveMessage(request, LocaleUtil.getLocalText("error.wweek.not.existed"));
				return new ModelAndView("bd/bd_recommend_bonus_tree");
			}
			//获取上一期的资料
			String formatedPreBdPeriodWeek = "999999";
			BdPeriod preBdPeriod = this.bdPeriodManager.getPreviousWeek(bdPeriod);
			if (preBdPeriod != null) {
				formatedPreBdPeriodWeek = preBdPeriod.getWyear().toString() + StringUtils.leftPad(preBdPeriod.getWweek().toString(), 2, '0');
			}

			if (SessionLogin.getLoginUser(request).getIsManager() || SessionLogin.getLoginUser(request).getIsCompany()) {
				//如果为管理中心人员
				String memberNo = request.getParameter("memberNo");

				JmiMember miMember = jmiMemberManager.getJmiMember(memberNo);
				//如果会员不存在
				if (miMember == null|| (SessionLogin.getLoginUser(request).getIsCompany()&&!SessionLogin.getLoginUser(request).getCompanyCode().equals(miMember.getCompanyCode()))) {
					this.saveMessage(request, LocaleUtil.getLocalText("bdBounsDeduct.error.memberNo.notExists"));
					return new ModelAndView("bd/bd_recommend_bonus_tree");
				}

				 if(RequestUtil.saveOperationSession(request, "bdRecommendBonusTreeQuery", Constants.COMPANY_TIME)!=0){
	        		  return new ModelAndView("redirect:bd_recommend_bonus_tree.html");
	        	 }
				
				 
				request.setAttribute("miMember", miMember);
				String showLevel = request.getParameter("showLevel");
				if (StringUtils.isEmpty(showLevel)) {
					showLevel = "5";
				}
				Long maxShowLevel=miMember.getJmiRecommendRef().getLayerId()+new Long(showLevel);
				List bdSendRecords = new ArrayList();
				this.getBdBonusTree(bdSendRecords, miMember, new Integer(formatedWeek), new Integer(formatedPreBdPeriodWeek), maxShowLevel);

				Map<String, Integer> rowMap = new HashMap<String, Integer>();

				if (bdSendRecords != null && bdSendRecords.size() > 0) {
					for (int i = 0; i < bdSendRecords.size(); i++) {
						Map bdSendRecordMap = (HashMap) bdSendRecords.get(i);
						//记录号
						//bdSendRecordMap.put("rowId", i+1);
						rowMap.put(bdSendRecordMap.get("user_code").toString(), new Integer(i + 1));
						if (memberNo.equals(bdSendRecordMap.get("user_code").toString())) {
							bdSendRecordMap.put("parentId", new Integer(0));
						} else {
							//String recommendNo = bdSendRecordMap.get("recommend_no").toString();
							bdSendRecordMap.put("parentId", new Integer(rowMap.get(bdSendRecordMap.get("recommend_no").toString()).toString()));
						}
					}
				}
				request.setAttribute("bdSendRecords", bdSendRecords);
			} else if (SessionLogin.getLoginUser(request).getIsMember()) {
				//如果当前登录人员为会员
				String memberNo = request.getParameter("memberNo");
				if (StringUtils.isEmpty(memberNo)) {
					//如果会员编号为空,则默认为当前登录会员
					memberNo = SessionLogin.getLoginUser(request).getUserCode();
				}

				JmiMember miMember = this.jmiMemberManager.getJmiMember(memberNo);
				//如果会员不存在,或者会员所属的分公司与当前操作者的公司不同(管理中心除外)
				if (miMember == null) {
					this.saveMessage(request, LocaleUtil.getLocalText("bdBounsDeduct.error.memberNo.notExists"));
					return new ModelAndView("bd/bd_recommend_bonus_tree");
				}

				 if(RequestUtil.saveOperationSession(request, "bdRecommendBonusTreeQuery", Constants.MEMBER_TIME)!=0){
	        		  return new ModelAndView("redirect:bd_recommend_bonus_tree.html");
	        	 }
				 
				//获取当前登录会员的接点信息
				JmiRecommendRef currentRecommendRef = jmiRecommendRefManagerReport.getJmiRecommendRef(SessionLogin.getLoginUser(request).getUserCode());
				if (!miMember.getJmiRecommendRef().getTreeIndex().startsWith(currentRecommendRef.getTreeIndex())) {
					//如果所查询的会员不是处于当前会员的节点内
					this.saveMessage(request, LocaleUtil.getLocalText("bdBonus.tree.notInTree"));
					return new ModelAndView("bd/bd_recommend_bonus_tree");
				}
				request.setAttribute("miMember", miMember);
				String showLevel = "5";
				Long maxShowLevel=miMember.getJmiRecommendRef().getLayerId()+new Long(showLevel);
				List bdSendRecords = new ArrayList();
				this.getBdBonusTree(bdSendRecords, miMember, new Integer(formatedWeek), new Integer(formatedPreBdPeriodWeek), maxShowLevel);
				
				Map<String, Integer> rowMap = new HashMap<String, Integer>();

				if (bdSendRecords != null && bdSendRecords.size() > 0) {
					for (int i = 0; i < bdSendRecords.size(); i++) {
						Map bdSendRecordMap = (HashMap) bdSendRecords.get(i);
						//记录号
						//bdSendRecordMap.put("rowId", i+1);
						rowMap.put(bdSendRecordMap.get("user_code").toString(), new Integer(i + 1));
						if (memberNo.equals(bdSendRecordMap.get("user_code").toString())) {
							bdSendRecordMap.put("parentId", new Integer(0));
						} else {
							//String recommendNo = bdSendRecordMap.get("recommend_no").toString();
							bdSendRecordMap.put("parentId", new Integer(rowMap.get(bdSendRecordMap.get("recommend_no").toString()).toString()));
						}
					}
				}
				request.setAttribute("bdSendRecords", bdSendRecords);

				//如果当前所显示的最高层会员不是当前登录会员
				if (!SessionLogin.getLoginUser(request).getUserCode().equals(memberNo)) {
					List upMiRecommendRefs=new ArrayList();
					String upMemberNo=miMember.getUserCode();
					while(!upMemberNo.equals(SessionLogin.getLoginUser(request).getUserCode())){
						
						JmiRecommendRef jmiRecommendRef=this.jmiRecommendRefManagerReport.getJmiRecommendRef(upMemberNo);
						upMiRecommendRefs.add(jmiRecommendRef);
						
						upMemberNo=jmiRecommendRef.getRecommendJmiMember().getUserCode();
					}
					Collections.reverse(upMiRecommendRefs);
					request.setAttribute("upMiRecommends", upMiRecommendRefs);
				}

			}
		}

		return new ModelAndView("bd/bd_recommend_bonus_tree");
	}

	public void getBdBonusTree(List bdSendRecords, final JmiMember miMember, Integer formatedWeek, Integer formatedPreBdPeriodWeek, final Long maxShowLevel) {

		
		Map singleSendRecordMap = jbdDayBounsCalcManager.getBdDayBounsCalcByUserCode(miMember.getUserCode(), formatedWeek.toString());
		if (singleSendRecordMap != null) {
			Map sendRecordMap = new HashMap();
			sendRecordMap.put("recommend_no", miMember.getRecommendNo());
			sendRecordMap.put("layer_id", miMember.getJmiRecommendRef().getLayerId());
			sendRecordMap.put("user_code", singleSendRecordMap.get("user_code"));
			sendRecordMap.put("company_code", singleSendRecordMap.get("company_code"));
			sendRecordMap.put("name", singleSendRecordMap.get("name"));
			sendRecordMap.put("pet_name", singleSendRecordMap.get("pet_name"));
			sendRecordMap.put("card_type", singleSendRecordMap.get("card_type"));
			sendRecordMap.put("recommend_num", singleSendRecordMap.get("recommend_num"));
			sendRecordMap.put("month_consumer_pv", singleSendRecordMap.get("month_consumer_pv"));
			sendRecordMap.put("month_consumer_pv1", singleSendRecordMap.get("month_consumer_pv1"));
			sendRecordMap.put("week_group_pv", singleSendRecordMap.get("week_group_pv"));
			
			sendRecordMap.put("pending_pv", singleSendRecordMap.get("pending_pv"));
			sendRecordMap.put("pass_star", singleSendRecordMap.get("pass_star"));
			sendRecordMap.put("pass_grade", singleSendRecordMap.get("pass_grade"));
			sendRecordMap.put("isstore", singleSendRecordMap.get("isstore"));
			sendRecordMap.put("pending_recommend_pv", singleSendRecordMap.get("pending_recommend_pv"));
			sendRecordMap.put("month_recommend_pv", singleSendRecordMap.get("month_recommend_pv"));
			sendRecordMap.put("w_week", singleSendRecordMap.get("w_week"));
			sendRecordMap.put("double_pass_star", singleSendRecordMap.get("double_pass_star"));
			sendRecordMap.put("month_double_area_pv", singleSendRecordMap.get("month_double_area_pv"));

			sendRecordMap.put("first_pv", singleSendRecordMap.get("first_pv"));
			sendRecordMap.put("month_recommend_cypv", singleSendRecordMap.get("month_recommend_cypv"));
			sendRecordMap.put("month_recommend_cxpv", singleSendRecordMap.get("month_recommend_cxpv"));

			bdSendRecords.add(sendRecordMap);

			if (maxShowLevel > miMember.getJmiRecommendRef().getLayerId()) {
				List miRecommendRefs = jmiRecommendRefManagerReport.getJmiRecommendRefsByRecommendNo(miMember.getUserCode());
				if (miRecommendRefs != null && miRecommendRefs.size() > 0) {
					for (int i = 0; i < miRecommendRefs.size(); i++) {
						JmiRecommendRef miRecommendRef = (JmiRecommendRef) miRecommendRefs.get(i);

						this.getBdBonusTree(bdSendRecords, miRecommendRef.getJmiMember(), formatedWeek, formatedPreBdPeriodWeek, maxShowLevel);
					}
				}
			}
		}
	}
}