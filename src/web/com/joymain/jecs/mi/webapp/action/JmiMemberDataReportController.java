package com.joymain.jecs.mi.webapp.action;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.model.JalTown;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.al.service.JalTownManager;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.log.util.ReportLogUtil;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiLinkRefManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.DateUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.io.FileUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JmiMemberDataReportController extends BaseFormController {
	private transient final Log log = LogFactory.getLog(JmiMemberDataReportController.class);
	private AlCompanyManager alCompanyManager;
	private AlCountryManager alCountryManager;
	private JmiMemberManager jmiMemberManager;
    private AlStateProvinceManager alStateProvinceManager;
    private JmiLinkRefManager jmiLinkRefManager;
    private AlCityManager alCityManager;
    private AlDistrictManager alDistrictManager;
    private JalTownManager jalTownManager;
	private JdbcTemplate jdbcTemplate = null;
	private ExcelUtil eu = null;
	private WritableSheet wsheet = null;
	private HttpServletRequest innerRequest;
	private LinkedHashMap<String,String> textMap=new LinkedHashMap<String,String>();
	//改为用jdbcTemplateReport
	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public JmiMemberDataReportController() {
		setCommandName("bdPeriod");
		setCommandClass(BdPeriod.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {

        SysUser defSysUser = SessionLogin.getLoginUser(request);
        
		textMap.put("user_code", "miMember.memberNo");
		textMap.put("mi_user_name", "bdCalculatingSubDetail.name");
		
		//所属区域
        textMap.put("belong_area", "belong.area");
        
		textMap.put("first_name", "miMember.firstName");
		textMap.put("last_name", "miMember.lastName");
		textMap.put("link_no", "miMember.linkNo");
		textMap.put("recommend_no", "miMember.recommendNo");
		textMap.put("card_type", "miMember.cardType");
		textMap.put("pet_name", "miMember.petName");
		textMap.put("sex", "miMember.sex");
		textMap.put("birthday", "miMember.birthday");
		textMap.put("email", "miMember.email");
		textMap.put("member_star", "miMember.memberStar");
		textMap.put("papertype", "miMember.papertype");
		textMap.put("papernumber", "miMember.papernumber");
		if(!"PH".equals(defSysUser.getCompanyCode())){
			textMap.put("bank_province", "miMember.bankProvince");
			textMap.put("bank_city", "miMember.bankCity");
		}else{
			textMap.put("bank_province", "miMember.island");
			textMap.put("bank_city", "miMember.bankCity");
		}
		
		textMap.put("bank", "miMember.openBank");
		textMap.put("bankaddress", "miMember.bcity");
		textMap.put("bankcard", "miMember.bnum");
		textMap.put("bankbook", "miMember.bname");
		textMap.put("phone", "miMember.phone");
		textMap.put("faxtele", "miMember.faxtele");
		textMap.put("mobiletele", "miMember.mobiletele");
		
		if(!"PH".equals(defSysUser.getCompanyCode())){
			textMap.put("province", "miMember.province");
			textMap.put("city", "busi.city");
			textMap.put("district", "miMember.district");
		}else{
			textMap.put("province", "miMember.island");
			textMap.put("city", "miMember.region");
			textMap.put("district", "miMember.province");
			textMap.put("town", "busi.city");
		}
		
		textMap.put("address", "busi.address");
		textMap.put("postalcode", "miMember.postalcode");
		textMap.put("exit_date", "miMember.exitDate");
		textMap.put("active", "is.active");
		textMap.put("remark", "miMember.remark");
		textMap.put("isstore", "miMember.store.type");
		textMap.put("check_no", "miMember.checkNo");
		textMap.put("check_date", "logType.C");
		textMap.put("create_no", "miMember.createNo");
		textMap.put("create_time", "miMember.createTime");
		textMap.put("spouse_name", "miMember.spouseName");
		textMap.put("spouse_idno", "miMember.spouseIdno");
		textMap.put("member_type", "member.memberType");
		textMap.put("identity_type", "miMember.identityType");
		textMap.put("comm_postalcode", "miMember.commPostalcode");
		textMap.put("comm_province", "miMember.commProvince");
		textMap.put("comm_city", "miMember.commCity");
		textMap.put("comm_district", "miMember.commDistrict");
		textMap.put("comm_addr", "miMember.commAddr");
		textMap.put("village_addr", "miMember.villageAddr");
		textMap.put("company_name", "miMember.companyName");
		textMap.put("person_charge", "miMember.personCharge");
		textMap.put("company_addr", "miMember.companyAddr");
		textMap.put("unite_number", "miMember.uniteNumber");
		textMap.put("town_addr", "miMember.townAddr");
		textMap.put("nationality", "miMember.nationality");
		textMap.put("title_remark", "miMember.titleRemark");
		textMap.put("freeze_status", "miMember.freezestatus");
		textMap.put("member_level", "miMember.cardType");
		textMap.put("recommend_num", "miMember.recommend_num");
		//修改EC优化需求：3、资料汇出条新增中策编号及中策姓名汇出项
		textMap.put("zcw_no", "miMember.zcw_no");
		textMap.put("zcw_name", "miMember.zcw_name");
		textMap.put("zlw_no", "miMember.zlw_no");
		textMap.put("zlw_name", "miMember.zlw_name");
		RequestUtil.freshSession(request,"jmiMemberDataReport", 30l);
		Map cardTypeMap=ListUtil.getListOptions(defSysUser.getCompanyCode(), "bd.cardtype");
		Map memberLevelMap=ListUtil.getListOptions(defSysUser.getCompanyCode(), "member.level");
		request.setAttribute("textMap", textMap);
		request.setAttribute("memberLevelMap", memberLevelMap);
		request.setAttribute("cardTypeMap", cardTypeMap);
		return new BdPeriod();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		if ("post".equalsIgnoreCase(request.getMethod()) && "jmiMemberDataReport".equalsIgnoreCase(request.getParameter("strAction"))) {
			this.innerRequest=request;
			String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
			if (SessionLogin.getLoginUser(request).getIsManager()) {
				companyCode = request.getParameter("companyCode");
			}
			Map map=request.getParameterMap();


			//生成excel文件
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=member_data.xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			eu = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			//在此创建的新excel文件创建一工作表 
			wsheet = wwb.createSheet("Sheet1", 0);
			
			AlCompany alCompany = alCompanyManager.getAlCompanyByCode(companyCode);
            AlCountry alCountry = new AlCountry();
        	alCountry = alCountryManager.getAlCountryByCode(alCompany.getCountryCode());
        	alCountry.getAlStateProvinces().iterator();
	        	
			//标题
//			String[] header = new String[] { LocaleUtil.getLocalText("bdBounsDeduct.wweek"), LocaleUtil.getLocalText("miMember.memberNo"), LocaleUtil.getLocalText("bdCalculatingSubDetail.name"), LocaleUtil.getLocalText("bdSendRecordToBankReport.memberIdCH"), LocaleUtil.getLocalText("bdSendRecordToBankReport.bankNameCH"),  LocaleUtil.getLocalText("bdSendRecordToBankReport.bankNumCH"), LocaleUtil.getLocalText("bdSendRemittanceReport.openBankCH"), LocaleUtil.getLocalText("bdSendRecordToBankReport.bonusPvCH"), LocaleUtil.getLocalText("bdSendRecord.bonusMoney")};
//			for (int i = 0; i < header.length; i++) {
//				eu.addString(wsheet, i, 1, header[i]);
//			}
			Iterator it =map.entrySet().iterator();
			Map<String,String> exMap=new HashMap<String,String>();
			
			
			
			Integer maxColumn=0;
			String linkNoNum=request.getParameter("linkNoNum");
			
			while(it.hasNext()){
				Map.Entry entry=(Map.Entry)it.next();
				Object key=entry.getKey();
				String value = request.getParameter(key.toString());
				if(!"memberLevelGroup".equals(key)&&!"cardTypeGroup".equals(key)&&StringUtil.isInteger(value) && !"isStoreGroup".equals(key)&& !"linkNoNum".equals(key)&&StringUtil.isInteger(value)){
					eu.addString(wsheet, StringUtil.formatInt(value)-1, 0, LocaleUtil.getLocalText(textMap.get(key).toString()));
					exMap.put(key.toString(), value);
					if(StringUtil.formatInt(value)>maxColumn){
						maxColumn=StringUtil.formatInt(value);
					}
				}
			}

			if("1".equals(linkNoNum)){
				eu.addString(wsheet, maxColumn, 0, LocaleUtil.getLocalText("miMember.linkNoNum"));
			}
			
			
			//设置进度条总记录数
			String createBTime=request.getParameter("createBTime");
			String createETime=request.getParameter("createETime");
			

			String checkBTime=request.getParameter("checkBTime");
			String checkETime=request.getParameter("checkETime");
			
			String cardTypeGroup[]=request.getParameterValues("cardTypeGroup");
			String isStoreGroup[]=request.getParameterValues("isStoreGroup");
			String memberLevelGroup[]=request.getParameterValues("memberLevelGroup");
			
			
			String userCodes="";
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("importExcelFile");
			if (file!=null && file.getSize()!=0) {
				Object obj[]=this.getMembers(request);
				if((Boolean)obj[0]==false){
					request.getSession().setAttribute("errorMessages", obj[1]);
					return new ModelAndView(getSuccessView());
				}else{
					userCodes=(String) obj[1];
					if(StringUtil.isEmpty(userCodes)){
						this.saveMessage(request, "未找到数据");
						return new ModelAndView(getSuccessView());
					}
					if(userCodes.split("','").length>=1000){
						this.saveMessage(request, "数据过多");
						return new ModelAndView(getSuccessView());
					}
				}
			}
			//添加限制要上传图片
			String uploadfileNeed =ConfigUtil.getConfigValue(companyCode, "jmimemberdatareport.uploadfile.need");

			if("Y".equalsIgnoreCase(uploadfileNeed)){
				if(StringUtil.blankOrNull(userCodes)){
					this.saveMessage(request, "请上传文件!");
					return new ModelAndView(getSuccessView());
				}
			}
			
			
			String cardTypeStr="";
			for (int i = 0; i < cardTypeGroup.length; i++) {
				String curCardTypeStr=cardTypeGroup[i];
				if(i==0){
					cardTypeStr+="'"+curCardTypeStr+"'";
				}else{
					cardTypeStr+=",'"+curCardTypeStr+"'";
				}
				
			}
			
			String memberLevelStr="";
			for (int i = 0; i < memberLevelGroup.length; i++) {
				String curMemberLevelStr=memberLevelGroup[i];
				if(i==0){
					memberLevelStr+=""+curMemberLevelStr+"";
				}else{
					memberLevelStr+=","+curMemberLevelStr+"";
				}
				
			}

			String isstoreStr="";
			if(isStoreGroup!=null){
				for (int i = 0; i < isStoreGroup.length; i++) {
					String curIsstoreStr=isStoreGroup[i];
					if(i==0){
						isstoreStr+="'"+curIsstoreStr+"'";
					}else{
						isstoreStr+=",'"+curIsstoreStr+"'";
					}
					
				}
			}
			
			
			
			
			
			
			//修改EC优化需求：3、资料汇出条新增中策编号及中策姓名汇出项
			String sql = "select m.*,Fn_Get_Zcw_Message_NEW(m.user_code) as zcw_str ,FN_GET_SYT(m.user_code) as zlw_str  from jmi_member m where m.company_code='"+companyCode+"'";
			
			
			if(StringUtil.isDate(createBTime)){
				sql+=" and m.create_time >=to_date('" + createBTime + " 00:00:00','yyyy-MM-dd hh24:mi:ss')"; 
				
			}
			if(StringUtil.isDate(createETime)){
				sql+=" and m.create_time <=to_date('" + createETime + " 23:59:59','yyyy-MM-dd hh24:mi:ss')"; 
			}

			if(StringUtil.isDate(checkBTime)){
				sql+=" and m.check_date >=to_date('" + checkBTime + " 00:00:00','yyyy-MM-dd hh24:mi:ss')"; 
				
			}
			if(StringUtil.isDate(checkETime)){
				sql+=" and m.check_date <=to_date('" + checkETime + " 23:59:59','yyyy-MM-dd hh24:mi:ss')"; 
			}
			if(!StringUtil.isEmpty(cardTypeStr)){
				sql+=" and m.card_type in ("+cardTypeStr+")";
			}
			if(!StringUtil.isEmpty(isstoreStr)){
				sql+=" and m.isstore in ("+isstoreStr+")";
			}
			if(!StringUtil.isEmpty(userCodes)){
				sql+=" and m.user_Code in ("+userCodes+")";
			}

			if(!StringUtil.isEmpty(memberLevelStr)){
				sql+=" and m.member_level in ("+memberLevelStr+")";
			}
			
			
			
			
			//获得会员级别列表
			Map cardTypeMap=ListUtil.getListOptions(companyCode, "bd.cardtype");
			Map memberLevelMap=ListUtil.getListOptions(companyCode, "member.level");
			//获得性别列表
			Map sexMap=ListUtil.getListOptions(companyCode, "sex");
			Map memberStarMap=ListUtil.getListOptions(companyCode, "pass.star");
			Map paperTypeMap=null;
			if(!"TW".equals(companyCode)){
				paperTypeMap=ListUtil.getListOptions("AA", "papertype");
			}else{
				paperTypeMap=ListUtil.getListOptions(companyCode, "papertype.tw");
			}

			Map isstoreMap=ListUtil.getListOptions(companyCode, "isstore");
			Map membertypeMap=ListUtil.getListOptions(companyCode, "membertype");
			Map identitytypeMap=ListUtil.getListOptions(companyCode, "identitytype.tw");
			
			 if(RequestUtil.saveOperationSession(request, "jmiMemberDataReport", 30l)!=0){
       		  return new ModelAndView("redirect:jmiMemberDataReport.html");
			 }

			//开始日志--start
			Long refId2 =ReportLogUtil.beginReport(SessionLogin.getLoginUser(request).getUserCode(), RequestUtil.getIpAddr(request), "jmiMemberDataReport", "");
			//开始日志--end
				
	    	log.info("sql--" +sql);
			Connection conn=this.jdbcTemplate.getDataSource().getConnection();
			Statement stmt = null;
			ResultSet rs = null;
			try {

				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs=stmt.executeQuery(sql);
				rs.last();
				int rowCount = rs.getRow(); 
				if(rowCount>60000){
					this.saveMessage(request, "导出数据过多");
					return new ModelAndView(getSuccessView());
				}
				if(rowCount==0){
					this.saveMessage(request, "找不到记录");
					return new ModelAndView(getSuccessView());
				}
				rs.beforeFirst();
						int kk = 1;
						while (rs.next()) {
							String value="";
							Object data=null;
							Iterator exIt =exMap.entrySet().iterator();
							while(exIt.hasNext()){
								Map.Entry entry=(Map.Entry)exIt.next();
								Object key=entry.getKey();
								value = request.getParameter(key.toString());
								
								if("belong_area".equals(key)) {
								    data=rs.getObject("province");
								}else if("recommend_num".equals(key)) {
								    data=rs.getObject("user_code");
								}else if("zcw_no".equals(key)||"zcw_name".equals(key)){//修改EC优化需求：3、资料汇出条新增中策编号及中策姓名汇出项
									data=rs.getObject("zcw_str");
								}else if("zlw_no".equals(key)||"zlw_name".equals(key)){//修改EC优化需求：3、资料汇出条新增战略委编号及战略委姓名汇出项
									data=rs.getObject("zlw_str");
								}else {
								    data=rs.getObject(key.toString());
								}
								
								if("card_type".equals(key)){
									if(null!=data){
									eu.addString(wsheet, StringUtil.formatInt(value)-1, kk, LocaleUtil.getLocalText(cardTypeMap.get(data.toString()).toString()));
									}
								}else if("sex".equals(key)){
									if(null!=data){
									eu.addString(wsheet, StringUtil.formatInt(value)-1, kk, LocaleUtil.getLocalText(sexMap.get(data.toString()).toString()));
									}
								}else if("member_level".equals(key)){
									if(null!=data){
									eu.addString(wsheet, StringUtil.formatInt(value)-1, kk, LocaleUtil.getLocalText(memberLevelMap.get(data.toString()).toString()));
									}
								}else if("recommend_num".equals(key)){
									if(null!=data){
										int recommend_num=jdbcTemplate.queryForInt("select count(*) from jmi_member where recommend_no='"+data+"'");
										eu.addNumber(wsheet, StringUtil.formatInt(value)-1, kk, recommend_num);
									}
								}else if("member_star".equals(key)){
									if(null!=data){
										eu.addString(wsheet, StringUtil.formatInt(value)-1, kk, LocaleUtil.getLocalText(memberStarMap.get(data.toString()).toString()));
									}
								}else if("active".equals(key)){
									if(null!=data){
										if("0".equals(data)){
											eu.addString(wsheet, StringUtil.formatInt(value)-1, kk, LocaleUtil.getLocalText("yesno.no"));
										}else{
											eu.addString(wsheet, StringUtil.formatInt(value)-1, kk, LocaleUtil.getLocalText("yesno.yes"));
										}
									}
								}else if("freeze_status".equals(key)){
									if(null!=data){
										if("0".equals(data.toString())){
											eu.addString(wsheet, StringUtil.formatInt(value)-1, kk, LocaleUtil.getLocalText("yesno.no"));
										}else{
											eu.addString(wsheet, StringUtil.formatInt(value)-1, kk, LocaleUtil.getLocalText("yesno.yes"));
										}
									}
								}else if("isstore".equals(key)){
									if(null!=data){
										eu.addString(wsheet, StringUtil.formatInt(value)-1, kk, LocaleUtil.getLocalText(isstoreMap.get(data.toString()).toString()));
									}
								}else if("member_type".equals(key)){
									if(null!=data&&!StringUtil.isEmpty(data+"".trim())){
										data=(data+"").trim();
										if(membertypeMap.get(data.toString())!=null){
											eu.addString(wsheet, StringUtil.formatInt(value)-1, kk, LocaleUtil.getLocalText(membertypeMap.get(data.toString()).toString()));
										}
									}
								}else if("identitytype".equals(key)){
									if(null!=data){
										eu.addString(wsheet, StringUtil.formatInt(value)-1, kk, LocaleUtil.getLocalText(identitytypeMap.get(data.toString()).toString()));
									}
								}else if("papertype".equals(key)){
									if(null!=data){
										eu.addString(wsheet, StringUtil.formatInt(value)-1, kk, LocaleUtil.getLocalText(paperTypeMap.get(data.toString()).toString()));
									}
								}else if("province".equals(key)){
									if(null!=data){
										String alStateProvinceStr="";
										AlStateProvince alStateProvince=alStateProvinceManager.getAlStateProvince(data.toString());
										if(alStateProvince!=null){
											alStateProvinceStr=alStateProvince.getStateProvinceName();
										}
										eu.addString(wsheet, StringUtil.formatInt(value)-1, kk,alStateProvinceStr);
									}
								}else if("city".equals(key)){
									if(null!=data){
										String alCityStr="";
										AlCity alCity=alCityManager.getAlCity(data.toString());
										if(alCity!=null){
											alCityStr=alCity.getCityName();
										}
										eu.addString(wsheet, StringUtil.formatInt(value)-1, kk,alCityStr);
									}
								}else if("district".equals(key)){
									if(null!=data){
										String alDistrictStr="";
										AlDistrict alDistrict=alDistrictManager.getAlDistrict(data.toString());
										if(alDistrict!=null){
											alDistrictStr=alDistrict.getDistrictName();
										}
										eu.addString(wsheet, StringUtil.formatInt(value)-1, kk,alDistrictStr);
									}
								}else if("town".equals(key)){
									if(null!=data){
										String alTownStr="";
										JalTown jalTown=jalTownManager.getJalTown(data.toString());
										if(jalTown!=null){
											alTownStr=jalTown.getTownName();
										}
										eu.addString(wsheet, StringUtil.formatInt(value)-1, kk,alTownStr);
									}
								}else if("bank_province".equals(key)){
									if(null!=data){
										String alStateProvinceStr="";
										AlStateProvince alStateProvince=alStateProvinceManager.getAlStateProvince(data.toString());
										if(alStateProvince!=null){
											alStateProvinceStr=alStateProvince.getStateProvinceName();
										}
										eu.addString(wsheet, StringUtil.formatInt(value)-1, kk,alStateProvinceStr);
									}
								}else if("bank_city".equals(key)){
									if(null!=data){
										String alCityStr="";
										AlCity alCity=alCityManager.getAlCity(data.toString());
										if(alCity!=null){
											alCityStr=alCity.getCityName();
										}
										eu.addString(wsheet, StringUtil.formatInt(value)-1, kk,alCityStr);
									}
								}else if("comm_province".equals(key)){
									if(null!=data){
										String alStateProvinceStr="";
										AlStateProvince alStateProvince=alStateProvinceManager.getAlStateProvince(data.toString());
										if(alStateProvince!=null){
											alStateProvinceStr=alStateProvince.getStateProvinceName();
										}
										eu.addString(wsheet, StringUtil.formatInt(value)-1, kk,alStateProvinceStr);
									}
								}else if("comm_city".equals(key)){
									if(null!=data){
										String alCityStr="";
										AlCity alCity=alCityManager.getAlCity(data.toString());
										if(alCity!=null){
											alCityStr=alCity.getCityName();
										}
										eu.addString(wsheet, StringUtil.formatInt(value)-1, kk,alCityStr);
									}
								}else if("comm_district".equals(key)){
									if(null!=data){
										String alDistrictStr="";
										AlDistrict alDistrict=alDistrictManager.getAlDistrict(data.toString());
										if(alDistrict!=null){
											alDistrictStr=alDistrict.getDistrictName();
										}
										eu.addString(wsheet, StringUtil.formatInt(value)-1, kk,alDistrictStr);
									}
								} else if("belong_area".equals(key)) {
								    if(null!=data){
								        String alBelongAreaStr="";
                                        AlStateProvince alStateProvince=alStateProvinceManager.getAlStateProvince(data.toString());
                                        if(alStateProvince!=null){
                                            alBelongAreaStr=alStateProvince.getBelongArea();
                                        }
                                        eu.addString(wsheet, StringUtil.formatInt(value)-1, kk,alBelongAreaStr);
                                    }
								}else if("zcw_no".equals(key)){//修改EC优化需求：3、资料汇出条新增中策编号及中策姓名汇出项
									if(null!=data){
										String zcwStr = data.toString();
										String zcwNo = "";
										if(!StringUtil.isEmpty(zcwStr)){
											String[] zcwArray = zcwStr.split("\\|\\|");
											zcwNo = zcwArray.length>=1?zcwArray[0]:"";
										}
										eu.addString(wsheet, StringUtil.formatInt(value)-1, kk, zcwNo);
									}
								}else if("zcw_name".equals(key)){//修改EC优化需求：3、资料汇出条新增中策编号及中策姓名汇出项
									if(null!=data){
										String zcwStr = data.toString();
										String zcwName = "";
										if(!StringUtil.isEmpty(zcwStr)){
											String[] zcwArray = zcwStr.split("\\|\\|");
											zcwName = zcwArray.length>=3?zcwArray[2]:"";
										}
										eu.addString(wsheet, StringUtil.formatInt(value)-1, kk, zcwName);
									}
								}else if("zlw_no".equals(key)){//修改EC优化需求：3、资料汇出条新增中策编号及中策姓名汇出项
									if(null!=data){
										String zlwStr = data.toString();
										String zlwNo = "";
										if(!StringUtil.isEmpty(zlwStr)){
											String[] zlwArray = zlwStr.split("\\|\\|");
											zlwNo = zlwArray.length>=1?zlwArray[0]:"";
										}
										eu.addString(wsheet, StringUtil.formatInt(value)-1, kk, zlwNo);
									}
								}else if("zlw_name".equals(key)){//修改EC优化需求：3、资料汇出条新增中策编号及中策姓名汇出项
									if(null!=data){
										String zlwStr = data.toString();
										String zlwName = "";
										if(!StringUtil.isEmpty(zlwStr)){
											String[] zlwArray = zlwStr.split("\\|\\|");
											zlwName = zlwArray.length>=3?zlwArray[2]:"";
										}
										eu.addString(wsheet, StringUtil.formatInt(value)-1, kk, zlwName);
									}
								} else{
									if(null!=data){
										eu.addString(wsheet, StringUtil.formatInt(value)-1, kk, data.toString());
									}
									
								}
								
							}
							if("1".equals(linkNoNum)){
								String userCode=rs.getObject("user_code").toString();
								List list=jmiLinkRefManager.getLinkRefbyLinkNo(userCode);
								eu.addNumber(wsheet,maxColumn, kk, list.size());
							}
							kk++;
						} 

						eu.writeExcel(wwb);
						eu.closeWritableWorkbook(wwb);
						os.close();
			} catch (Exception e) {
				e.printStackTrace();
				log.info(e.getMessage());
			} finally {
				try {
					rs.close();
				} catch ( Exception e ) {}
				try {
					stmt.close();
				} catch ( Exception e ) {}
				try {
					conn.close();
				} catch ( Exception e ) {}
			}


			//结束日志--start
			ReportLogUtil.endReport(refId2, "jmiMemberDataReport");
			//结束日志--end
			
			
			return null;
		}
		return new ModelAndView(getSuccessView());
	}
	
	
	
	private Object[] getMembers(HttpServletRequest request) throws Exception{

		Object []res=new Object[2];
		res[0]=false;
		res[1]=new ArrayList();
		List<String> messages = new ArrayList<String>();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("importExcelFile");
		if (file == null || file.getInputStream() == null || file.getSize()==0) {
			messages.add("文件内容为空");
			res[1]=messages;
			return res;
		}
		InputStream stream = file.getInputStream();
		ExcelUtil eu = new ExcelUtil();
		Workbook workbook =null;
		Sheet sheet =null;
		try{
			//获取可读的工作表对象，定位到要读取的excel文件
			workbook= eu.getWorkbook(stream);
		 //读取此文件的第一个工作表，工作表序号从0开始。
		 sheet = workbook.getSheet(0);
		}catch(Exception e){
			log.info(e.getMessage());
			messages.add("<font color=red>请使用标准的xls格式的文件</font>");
			res[1]=messages;
			return res;
		}
		
		
		int totalCount = sheet.getRows() - 1;
		int okCount = 0;
		int errCount = 0;
		//从第2行开始读,第一行为标题
		messages.add(LocaleUtil.getLocalText("bdBounsDeduct.impot.skip.first"));
		
		List<Map> jmiMembers=new ArrayList<Map>();
		
		String userCodes="";
		
		
		for (int i = 1; i < sheet.getRows(); i++) {
			String userCode = eu.getContents(sheet, 0, i);

			String content = " ([ " + userCode + " ])";
			String message = (i + 1) + ": ";
			if (StringUtils.isEmpty(userCode)) {
				messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + LocaleUtil.getLocalText("bdBounsDeduct.error.memberNo.empty") + content+"</font>");
				errCount++;
				continue;
			}
			JmiMember miMember = this.jmiMemberManager.getJmiMember(userCode.trim());
			if (miMember == null) {
				messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + LocaleUtil.getLocalText("bdBounsDeduct.error.memberNo.notExists") + content+"</font>");
				errCount++;
				continue;
			}
			if(StringUtil.isEmpty(userCodes)){
				userCodes+="'"+userCode.trim()+"'";
			}else{
				userCodes+=",'"+userCode.trim()+"'";
			}
		}
		
		if(errCount!=0){
			res[1]=messages;
			return res;
		}
		
		res[0]=true;
		res[1]=userCodes;
		return res;
		
	}


	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}


	public void setAlCityManager(AlCityManager alCityManager) {
		this.alCityManager = alCityManager;
	}


	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}


	public void setAlDistrictManager(AlDistrictManager alDistrictManager) {
		this.alDistrictManager = alDistrictManager;
	}


	public void setAlStateProvinceManager(
			AlStateProvinceManager alStateProvinceManager) {
		this.alStateProvinceManager = alStateProvinceManager;
	}


	public void setJalTownManager(JalTownManager jalTownManager) {
		this.jalTownManager = jalTownManager;
	}


	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}


	public void setJmiLinkRefManager(JmiLinkRefManager jmiLinkRefManager) {
		this.jmiLinkRefManager = jmiLinkRefManager;
	}



}