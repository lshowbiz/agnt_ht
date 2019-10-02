package com.joymain.jecs.webapp.action;

import java.text.MessageFormat;
import java.util.Date;
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
//import com.joymain.jecs.ai.model.AiAgent;
//import com.joymain.jecs.ai.service.AiAgentManager;
import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.service.AlCharacterCodingManager;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.am.service.AmMessageManager;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiSubStore;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.service.JmiSubStoreManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.model.SysUserRole;
import com.joymain.jecs.sys.service.SysModuleManager;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.sys.service.SysUserRoleManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 框架页面
 * @author 
 * 
 */
public class IndexController implements Controller {
	private final Log log = LogFactory.getLog(IndexController.class);
	private AlCompanyManager alCompanyManager = null;
	private AlCharacterCodingManager alCharacterCodingManager = null;
	private BdPeriodManager bdPeriodManager = null;
	private SysModuleManager sysModuleManager = null;
	private SysUserManager sysUserManager=null;
	private JdbcTemplate jdbcTemplate = null;
	private JmiSubStoreManager jmiSubStoreManager;
	private SysUserRoleManager sysUserRoleManager;
	private AmMessageManager amMessageManager;
	private JmiMemberManager jmiMemberManager;
//	private AiAgentManager aiAgentManager=null;
	
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	public void setAmMessageManager(AmMessageManager amMessageManager) {
		this.amMessageManager = amMessageManager;
	}

	public void setJmiSubStoreManager(JmiSubStoreManager jmiSubStoreManager) {
		this.jmiSubStoreManager = jmiSubStoreManager;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}
	
	public void setSysModuleManager(SysModuleManager sysModuleManager) {
		this.sysModuleManager = sysModuleManager;
	}

	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public void setAlCharacterCodingManager(AlCharacterCodingManager alCharacterCodingManager) {
		this.alCharacterCodingManager = alCharacterCodingManager;
	}

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		String characterCode = request.getParameter("characterCode");
		String companyCode = request.getParameter("companyCode");
		String ipAddress=RequestUtil.getIpAddr(request);
		if ("post".equalsIgnoreCase(request.getMethod())) {
			SysUser loginUser=SessionLogin.getLoginUser(request);
			if (!StringUtils.isEmpty(characterCode)) {
				loginUser.setDefCharacterCoding(characterCode);
				//SessionLogin.getLoginUser(request).setDefCharacterCoding(characterCode);
				SysUser sysUser=this.sysUserManager.getSysUser(SessionLogin.getLoginUser(request).getUserCode());
				sysUser.setDefCharacterCoding(characterCode);
				this.sysUserManager.saveSysUser(sysUser);
			}
			if (!StringUtils.isEmpty(companyCode)) {
				loginUser.setCompanyCode(companyCode);
			}
			SessionLogin.setLoginUser(request, loginUser);
			//获取有权限的模块
			//Map<String, String> powerMap=this.sysModuleManager.getSysPowerMap(SessionLogin.getLoginUser(request));
			//SessionLogin.getLoginUser(request).setPowerMap(powerMap);

			return new ModelAndView("redirect:index.html");
		}
		//获取登录人公司的资料
		AlCompany alCompany = alCompanyManager.getAlCompanyByCode(SessionLogin.getLoginUser(request).getCompanyCode());
		request.setAttribute("alCompany", alCompany);
		//获取登录人可选用的公司,仅公司用户可用
		if(SessionLogin.getLoginUser(request).getIsCompany() || SessionLogin.getLoginUser(request).getIsManager()){
			List alCompanys = this.alCompanyManager.getMyAlCompanys(SessionLogin.getLoginUser(request), null, false);
			request.setAttribute("alCompanys", alCompanys);
		}
		//获取系统可用的语言
		List alCharacterCodings = this.alCharacterCodingManager.getAlCharacterCodings(null);
		request.setAttribute("alCharacterCodings", alCharacterCodings);
		//获取当前对应的期数
		BdPeriod bdPeriod = this.bdPeriodManager.getBdPeriodByTime(new Date(), null);
		if (bdPeriod == null) {
			bdPeriod = new BdPeriod();
		}
		long time = bdPeriod.getEndTime().getTime()-1000l;
		bdPeriod.setEndTime(new Date(time));
		request.setAttribute("bdPeriod", bdPeriod);
		request.setAttribute("date", new Date());
		//显示系统名称
		String webappName = "webapp.name";
		if (SessionLogin.getLoginUser(request).getIsAgent()) {
			webappName = "sysMenu.belongType.agent";
		} else if (SessionLogin.getLoginUser(request).getIsMember()) {
			webappName = "webapp.name.member";
		} else if (SessionLogin.getLoginUser(request).getIsManager()) {
			webappName = "webapp.name.manager";
		} else if (SessionLogin.getLoginUser(request).getIsCompany()) {
			webappName = "webapp.name.company";
		}
		request.setAttribute("webappName", LocaleUtil.getLocalText(webappName));
		
		//TODO:Alvin 代理商合同截止期提示
//		if("CN".equals(SessionLogin.getLoginUser(request).getCompanyCode()) && ("P".equals(SessionLogin.getLoginUser(request).getUserType()) || "Q".equals(SessionLogin.getLoginUser(request).getUserType()))){
//			AiAgent aiAgent = aiAgentManager.getAiAgent(SessionLogin.getLoginUser(request).getUserCode());
//			if(aiAgent.getDeadlineDate()!=null){
//				Date nowDate = new Date();
//				if(DateUtil.daysBetweenDates(aiAgent.getDeadlineDate(),nowDate)<31 ){
//					request.setAttribute("deadlineDate", DateUtil.daysBetweenDates(aiAgent.getDeadlineDate(),nowDate));
//				}
//			}
//		}
		//当每月小于10PV重消时，提示消息
		if("M".equals(SessionLogin.getLoginUser(request).getUserType())){
			BdPeriod cuBdPeriod=bdPeriodManager.getBdPeriodByTime(new Date(), null);

			String wyear= bdPeriod.getWyear()+""+ (bdPeriod.getWmonth()<10?"0" + bdPeriod.getWmonth():bdPeriod.getWmonth());
			String startDate=null;
			String endDate=null;
			
			String notTipsMemberStr="CN10111767,CN10716695,CN12420821,CN12898280,CN13234245,CN13767892,CN15127332,CN17162209,CN17969214,CN19117719,CN19506487,CN19586220,CN21305849,CN21736826,CN23770918,CN32448960,CN33177159,CN33964954,CN35436309,CN39249753,CN39946324,CN41578183,CN41914148,CN43920921,CN45149899,CN46053558,CN47086094,CN50286556,CN52594654,CN53806311,CN55651283,CN55731860,CN60337189,CN64084584,CN66797927,CN70012697,CN71307943,CN73979900,CN74053562,CN77634454,CN79744255,CN83812440,CN84041158,CN84331187,CN85606100,CN86512772,CN91670213,CN91793911,CN92504790,CN97613480,CN99455440,CN99456088,CN11250891,CN98817038,CN17978514,CN86677808,CN89404186,CN13761602,CN25207240,CN14583255,CN15009737,CN14843039,CN12642515,CN59803060,CN20927321,CN10040104,CN13193334,CN18766575,CN77911511,CN70585804,CN21449995,CN19278434,CN14094272,CN72480232,CN13819542,CN12736898,CN84010762,CN17678496,CN13493338,CN97416865,CN17074043,CN12048204,CN15650495,CN36537943,CN30821124,CN13454735,CN78214050,CN83105421";
			
			
			if(cuBdPeriod!=null){

				//先判断是否存在全年重消订单
				boolean flag=false;
				List list3 = this.jdbcTemplate.queryForList("select min(start_time) as start_time, max(end_time) as end_time " +
						"from jbd_period where w_year = "+bdPeriod.getWyear());
				if(!list3.isEmpty()){
					startDate=DateUtil.convertDateToString( DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",((Map) list3.get(0)).get("start_time").toString()));
					endDate=DateUtil.convertDateToString( DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",((Map) list3.get(0)).get("end_time").toString()));
					

				/*	String sql3="select nvl(sum(pv_amt),0) as pv_amt from jpo_member_order where " +
					"check_date >= to_date('"+startDate+" 00:00:00', 'yyyy-mm-dd hh24:mi:ss')"+
					"and check_date < to_date('"+endDate+" 00:00:00', 'yyyy-mm-dd hh24:mi:ss')"+
					"and order_type in ('4') " +
					"and user_code='"+SessionLogin.getLoginUser(request).getUserCode()+"' and company_code='"+SessionLogin.getLoginUser(request).getCompanyCode()+"' ";
					*/
					//判断团队

					int pvAmt=3276;
					
	        		String loninTreeIndex=SessionLogin.getLoginUser(request).getJmiMember().getJmiRecommendRef().getTreeIndex();
	        		
	            	JmiMember qdMiMember = jmiMemberManager.getJmiMember("CN40449939");
	            	if(qdMiMember!=null ){
	            		String tsTreeIndex=qdMiMember.getJmiRecommendRef().getTreeIndex();
	            		String indexTmp = StringUtil.getLeft(loninTreeIndex, tsTreeIndex.length());
	            		if(loninTreeIndex.length() >= tsTreeIndex.length() && indexTmp.equals(tsTreeIndex)){
	            			pvAmt=130;
	            		}
	            	}
	            	JmiMember ty4MiMember = jmiMemberManager.getJmiMember("CN55092684");
	            	if(ty4MiMember!=null ){
	            		String tsTreeIndex=ty4MiMember.getJmiRecommendRef().getTreeIndex();
	            		String indexTmp = StringUtil.getLeft(loninTreeIndex, tsTreeIndex.length());
	            		if(loninTreeIndex.length() >= tsTreeIndex.length() && indexTmp.equals(tsTreeIndex)){
	            			pvAmt=130;
	            		}
	            	}
					
					
					String sql3="select check_date from jpo_member_order where order_type in ('4') and user_code = '"+SessionLogin.getLoginUser(request).getUserCode()+"' " +
							"and company_code = '"+SessionLogin.getLoginUser(request).getCompanyCode()+"' and pv_amt >= "+pvAmt+"  and status='2' order by check_date desc";
					
					
					
					List list4 = this.jdbcTemplate.queryForList(sql3);
					
				
						if(!list4.isEmpty()){
							Map map=(Map) list4.get(0);
							if(map.get("check_date")!=null){
								String check_date=map.get("check_date").toString();
								BdPeriod checkDatebdPeriod=bdPeriodManager.getBdPeriodByTime(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", check_date), null);
								String period = bdPeriodManager.getFutureBdYearMonth(checkDatebdPeriod.getWyear().toString(), checkDatebdPeriod.getWmonth().toString(), 13);
								if(StringUtil.formatInt(wyear)<=StringUtil.formatInt(period)){
									flag=true;
									if(pvAmt==3276){
										List endDateList = this.jdbcTemplate.queryForList("select TO_CHAR(max(end_time-1), 'yyyy-MM-dd') as end_time from Jbd_Period where concat(w_year, Lpad(w_month, 2, 0))=" +period);
										if(!endDateList.isEmpty()){
											Map datemap=(Map) endDateList.get(0);
											if(datemap.get("end_time")!=null){
												request.setAttribute("tips3276", datemap.get("end_time"));
											}
										}
									}
								}
							}
						}
					
				}
				//
				
				
				List list2 = this.jdbcTemplate.queryForList("select min(start_time) as start_time, max(end_time) as end_time " +
						"from jbd_period where concat(w_year, lpad(w_month, 2, 0)) = "+wyear+" ");
				
				
				if(!list2.isEmpty()&&!flag && SessionLogin.getLoginUser(request).getJmiMember().getCheckDate()!=null){
					

					BdPeriod miBdPeriod=bdPeriodManager.getBdPeriodByTime(SessionLogin.getLoginUser(request).getJmiMember().getCheckDate(), null);
					String mi_wyear= miBdPeriod.getWyear()+""+ (miBdPeriod.getWmonth()<10?"0" + miBdPeriod.getWmonth():miBdPeriod.getWmonth());
					
					if(StringUtil.formatInt(wyear)>StringUtil.formatInt(mi_wyear)){
						startDate=DateUtil.convertDateToString( DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",((Map) list2.get(0)).get("start_time").toString()));
						endDate=DateUtil.convertDateToString( DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",((Map) list2.get(0)).get("end_time").toString()));
							
						String sql="select nvl(sum(pv_amt),0) as pv_amt from jpo_member_order where " +
								"check_date >= to_date('"+startDate+" 00:00:00', 'yyyy-mm-dd hh24:mi:ss')"+
								"and check_date < to_date('"+endDate+" 00:00:00', 'yyyy-mm-dd hh24:mi:ss')"+
								"and order_type in ('4','9','14') " +
								"and user_code='"+SessionLogin.getLoginUser(request).getUserCode()+"' and company_code='"+SessionLogin.getLoginUser(request).getCompanyCode()+"' ";
						List list1 = this.jdbcTemplate.queryForList(sql);
						
						
						
						
						if(!notTipsMemberStr.contains(SessionLogin.getLoginUser(request).getUserCode())){

							if(!list1.isEmpty()){
								String  pv_amt=((Map) list1.get(0)).get("pv_amt").toString();
								if(StringUtil.isDouble(pv_amt))
								{
									if(StringUtil.formatDouble(pv_amt)<10){
										request.setAttribute("memberPV10", "memberPV10");
									}
								}
								
							}
						}
					}
					
					
					
				}
			}
			
			
		}

		if("C".equals(SessionLogin.getLoginUser(request).getUserType())){

			CommonRecord crm=RequestUtil.toCommonRecord(request);

	        crm.setValue("loginUserNo", SessionLogin.getLoginUser(request).getUserCode());
        	String noReplyNum = amMessageManager.getCompanyReplyNum(crm,"noreply");
        	if(StringUtil.isInteger(noReplyNum)&&StringUtil.formatInt(noReplyNum)>0){
        		request.setAttribute("noReplyNum", "您有"+noReplyNum+"条消息尚未回复，请及时回复");
        	}
        	
		}
		
		//
		
		
		
		//如果会员银行资料不完全，提示补充完整
		if("M".equals(SessionLogin.getLoginUser(request).getUserType())){
			if(StringUtil.isEmpty(SessionLogin.getLoginUser(request).getJmiMember().getBank())||StringUtil.isEmpty(SessionLogin.getLoginUser(request).getJmiMember().getBankbook())||StringUtil.isEmpty(SessionLogin.getLoginUser(request).getJmiMember().getBankcard())){
				request.setAttribute("bankinfo", "bankinfo");
			}
			//如为待审会员，并且在14天内未支付，提示
			int activeTime=StringUtil.formatInt((String) Constants.sysConfigMap.get(SessionLogin.getLoginUser(request).getCompanyCode()).get("active_time"));
			Date activeTimeDate=SessionLogin.getLoginUser(request).getJmiMember().getActiveTime();
			if(new Date().after(DateUtil.getDateOffset(activeTimeDate, 5, activeTime))){
				activeTime=0;
			}else{
				activeTime=DateUtil.daysBetweenDates( DateUtil.getDateOffset(activeTimeDate, 5, activeTime),new Date());
			}
	        String activeinfo= MessageFormat.format(LocaleUtil.getLocalText("activeinfo.member"), new Integer[]{activeTime});
			if("0".equals(SessionLogin.getLoginUser(request).getJmiMember().getCardType())&&activeTime!=0){
				request.setAttribute("activeinfo", activeinfo);
			}
			try{
				String sql = "Select flag From jmi_member Where member_type ='1' and flag='1' and user_code='"+SessionLogin.getLoginUser(request).getJmiMember().getUserCode()+"'";
				List miMembers = this.jdbcTemplate.queryForList(sql);
				if(miMembers.size()>0){
					request.setAttribute("ylMemberInfo", "1");
				}
			}catch (Exception e){
				e.printStackTrace();
			}
			//续约提示
			if("CN".equals(SessionLogin.getLoginUser(request).getCompanyCode())&&null!=SessionLogin.getLoginUser(request).getJmiMember().getValidWeek()&&!"0".equals(SessionLogin.getLoginUser(request).getJmiMember().getCardType())){
				boolean daysflag=false;
				int days=0;
				BdPeriod endBdPeriod=null;
				Integer validWeek=SessionLogin.getLoginUser(request).getJmiMember().getValidWeek();
				if(validWeek!=null&&validWeek.toString().length()==6){
					List list=bdPeriodManager.getBdPeriodsByMonth(validWeek.toString().substring(0, 4), validWeek.toString().substring(4, validWeek.toString().length()));
					if(list!=null){
						endBdPeriod=(BdPeriod) list.get(list.size()-1);
						days=DateUtil.daysBetweenDates( endBdPeriod.getEndTime(),new Date());
						days=days-1;
						if(days<=56&&days>0){
							daysflag=true;
						}
					}
				}
				String notTipsMemberStr="CN64084584,CN10716695,CN41578183,CN50286556,CN55731860,CN97613480,CN55651283,CN70012697,CN53806311,CN83812440,CN66797927,CN21305849,CN77634454,CN91670213,CN17162209,CN23770918,CN84331187,CN71307943,CN39946324,CN21736826,CN19117719,CN32448960,CN92504790,CN91793911,CN52594654,CN84041158,CN35436309,CN19586220,CN86512772,CN99455440,CN10111767,CN12898280,CN33177159,CN85606100,CN99456088,CN74053562,CN47086094,CN41914148,CN13234245,CN79744255,CN73979900,CN46053558,CN45149899,CN33964954,CN39249753,CN43920921,CN13767892,CN17969214,CN19506487,CN12420821,CN60337189,CN15127332,CN20474358,CN38323488,CN13717634,CN18310026,CN18243407,CN15090165,CN96233029,CN83900978,CN18660277,CN14608168,CN11824008,CN10464346,CN10617844,CN10729883,CN11172032,CN11452263,CN11559009,CN11585153,CN11907956,CN12058869,CN12083524,CN12251194,CN12560662,CN12631305,CN12782804,CN13373545,CN14009631,CN14453844,CN14716319,CN14741550,CN14815994,CN14823937,CN14906272,CN15080894,CN15090496,CN15137534,CN15501681,CN15626010,CN15706464,CN15739402,CN15784794,CN15791776,CN15815471,CN16031161,CN16136630,CN16395227,CN16437501,CN16618897,CN16635190,CN16662165,CN16715985,CN17061791,CN17180134,CN17372626,CN17505030,CN17593683,CN17720449,CN17754204,CN17926873,CN17980773,CN18229484,CN18556467,CN18671898,CN19491179,CN19775103,CN19895242,CN19957084,CN20438681,CN20609518,CN20659232,CN20906596,CN20944994,CN20961593,CN21015637,CN21184741,CN21404193,CN22300305,CN22610462,CN24307902,CN25741947,CN26087322,CN26793752,CN29829710,CN30449556,CN30547247,CN31684160,CN32516700,CN32866520,CN35039296,CN36138860,CN36918962,CN44506917,CN44934472,CN47812866,CN48935974,CN49342538,CN49347849,CN49408282,CN49986061,CN51335852,CN51747631,CN52627657,CN52813747,CN54000276,CN54480278,CN54753826,CN54821871,CN55236159,CN57290735,CN58556088,CN59718127,CN60375630,CN63528318,CN63857537,CN66050801,CN70859695,CN71819204,CN76880668,CN82553736,CN85066224,CN85305624,CN86533116,CN90911563,CN91354778,CN91708384,CN96159424,CN96949931,CN97994687,CN98385538,CN99336145,CN99864423,CN10104602,CN10455546,CN10457760,CN10560478,CN10758040,CN10943495,CN11943289,CN12591322,CN12857054,CN13205653,CN14253927,CN14303708,CN14440025,CN14865421,CN15369885,CN15506468,CN16330225,CN16403834,CN17004367,CN17042229,CN17498593,CN17600476,CN18121010,CN18713244,CN19604348,CN20238913,CN20304214,CN20564641,CN20808430,CN20904861,CN22201758,CN23827598,CN25132811,CN31881950,CN37016679,CN39110534,CN39416036,CN42208491,CN42601175,CN50349950,CN50510780,CN54596689,CN54687372,CN55559359,CN58162095,CN59638085,CN61988886,CN62562318,CN64879226,CN65615472,CN66026879,CN66723009,CN67190599,CN68002152,CN70297237,CN75187970,CN75883696,CN84663089,CN94772815,CN97926295,CN11439675,CN12017065,CN12154532,CN12741413,CN13111598,CN14780313,CN15200042,CN15219237,CN15589273,CN15729928,CN18814381,CN19745762,CN20202260,CN21332947,CN23754927,CN26392321,CN28010527,CN41186142,CN44194131,CN45162671,CN47625909,CN62817847,CN69863518,CN73470757,CN74876512,CN78164812,CN78482132,CN80350845,CN89492252,CN92358415,CN11896462,CN17045887,CN23394553,CN84567065,CN96787198,CN11250891,CN98817038,CN17978514,CN86677808,CN89404186,CN13761602,CN25207240,CN14583255,CN15009737,CN14843039,CN12642515,CN59803060,CN20927321,CN10040104,CN13193334,CN16052242,CN34381389,CN13601547,CN81002295,CN16177580,CN79701821,CN14979831,CN65395655,CN18129096,CN88735101,CN18911055,CN74364867,CN14094085";
				if(!notTipsMemberStr.contains((SessionLogin.getLoginUser(request).getUserCode()))){
					if(SessionLogin.getLoginUser(request).getJmiMember().getFreezeStatus()==1){
						int offDays=DateUtil.daysBetweenDates( new Date(),endBdPeriod.getEndTime());
						int offDaysDivisible=offDays/365;
						offDaysDivisible=offDaysDivisible+1;
				        String msgMemberFreeze1= MessageFormat.format(LocaleUtil.getLocalText("memberFreeze.tips1"), new Integer[]{252});
						request.setAttribute("memberFreeze1",msgMemberFreeze1);
					}else{
						if(daysflag){
							request.setAttribute("memberFreeze",days);
						}
						
					}
				}
			}
			//
			//二级生活馆地址未审核提示
			if("CN".equals(SessionLogin.getLoginUser(request).getCompanyCode())){
				if("2".equals(SessionLogin.getLoginUser(request).getJmiMember().getSubStoreStatus())){
					JmiSubStore jmiSubStore=jmiSubStoreManager.getJmiSubStoresByUserCode(SessionLogin.getLoginUser(request).getUserCode());
					if(jmiSubStore!=null && (!"1".equals(jmiSubStore.getAddrCheck())||!"1".equals(jmiSubStore.getAddrConfirm()))){
						request.setAttribute("addrTips", "addrTips");
					}else if(jmiSubStore!=null && "1".equals(jmiSubStore.getAddrConfirm()) && ("0".equals(jmiSubStore.getBusinessLicese())||"0".equals(jmiSubStore.getContract())||"0".equals(jmiSubStore.getStorePic()))){
						request.setAttribute("othersTips", "othersTips");
					}
				}

				SysUserRole sysUserRole= sysUserRoleManager.getSysUserRoleByUserCode(SessionLogin.getLoginUser(request).getUserCode());
				if(sysUserRole!=null&&sysUserRole.getRoleId()==951589){
					request.setAttribute("jmiStoreTips", "jmiStoreTips");
				}
			}
			//
		}
		
		List sysModules = this.sysModuleManager.getSysMenus(SessionLogin.getLoginUser(request),"0",null);
		request.setAttribute("sysModules", sysModules);
		
		
		
		request.setAttribute("ipAddress", ipAddress);
		if(RequestUtil.isMobileRequest(request)){
			return new ModelAndView("mobile/mHome");
		}
		return new ModelAndView("index");
	}

	public void setSysUserRoleManager(SysUserRoleManager sysUserRoleManager) {
		this.sysUserRoleManager = sysUserRoleManager;
	}

//	public void setAiAgentManager(AiAgentManager aiAgentManager) {
//		this.aiAgentManager = aiAgentManager;
//	}
}