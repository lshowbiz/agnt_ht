package com.joymain.jecs.bd.webapp.action;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.al.service.JalTownManager;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdComingHonorStarReportController extends BaseFormController {
	private transient final Log log = LogFactory.getLog(JbdComingHonorStarReportController.class);
	private AlCompanyManager alCompanyManager;
	private AlCountryManager alCountryManager;
	private JmiMemberManager jmiMemberManager;
    private AlStateProvinceManager alStateProvinceManager;
    private AlCityManager alCityManager;
    private AlDistrictManager alDistrictManager;
    private JalTownManager jalTownManager;
	private JdbcTemplate jdbcTemplate = null;
	private BdPeriodManager bdPeriodManager;
	private ExcelUtil eu = null;
	private WritableSheet wsheet = null;
	private HttpServletRequest innerRequest;
	private LinkedHashMap<String,String> textMap=new LinkedHashMap<String,String>();
	//改为jdbcTemplateReport
	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}



	public JbdComingHonorStarReportController() {
		setCommandName("bdPeriod");
		setCommandClass(BdPeriod.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {

        SysUser defSysUser = SessionLogin.getLoginUser(request);

        RequestUtil.freshSession(request,"jbdComingHonorStarReport", Constants.COMPANY_TIME);
		
		return new BdPeriod();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		if ("post".equalsIgnoreCase(request.getMethod()) && "jbdComingHonorStarReport".equalsIgnoreCase(request.getParameter("strAction"))) {
			this.innerRequest=request;
			String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
			if (SessionLogin.getLoginUser(request).getIsManager()) {
				companyCode = request.getParameter("companyCode");
			}
			String formatedWeek = request.getParameter("formatedWeek");

			if(RequestUtil.saveOperationSession(request, "jbdComingHonorStarReport", Constants.COMPANY_TIME)!=0){
	       		  return new ModelAndView("redirect:jbdComingHonorStarReport.html");
	       	 }
			
			
			formatedWeek=WeekFormatUtil.getFormatWeek("f",formatedWeek);
			 String formatedWeekStart = request.getParameter("formatedWeekStart");
			 String formatedWeekEnd = request.getParameter("formatedWeekEnd");
			

			 formatedWeekStart=WeekFormatUtil.getFormatWeek("f",formatedWeekStart);
			 formatedWeekEnd=WeekFormatUtil.getFormatWeek("f",formatedWeekEnd);
			
			//生成excel文件
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=jbdComingHonorStarReport.xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			eu = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			//在此创建的新excel文件创建一工作表 
			wsheet = wwb.createSheet("Sheet1", 0);
			
	        	
			//标题

			int i=0;

			eu.addString(wsheet, i++, 0, "会员编号");
			eu.addString(wsheet, i++, 0, "会员名称");
			eu.addString(wsheet, i++, 0, "奖衔期别");
			eu.addString(wsheet, i++, 0, "奖衔月份");
			eu.addString(wsheet, i++, 0, "奖衔级别");
			eu.addString(wsheet, i++, 0, "移动电话");
			eu.addString(wsheet, i++, 0, "邮编");
			eu.addString(wsheet, i++, 0, "地址");
			eu.addString(wsheet, i++, 0, "加入期别");
			eu.addString(wsheet, i++, 0, "会员类型");
			eu.addString(wsheet, i++, 0, "国别");
			eu.addString(wsheet, i++, 0, "备注");
			eu.addString(wsheet, i++, 0, "奖衔备注");
			eu.addString(wsheet, i++, 0, "是否中策");
			eu.addString(wsheet, i++, 0, LocaleUtil.getLocalText("miMember.papernumber"));
			
			Map provinceMap=alStateProvinceManager.getAlStateProvincesMapByCountry(companyCode);
			Map cityMap=alCityManager.getAlCityMap(companyCode);
			Map districtMap=alDistrictManager.getAlDistrictMap(companyCode);

			Map<String,String> w2mBeriodMap=new HashMap<String,String>();
			Map<Object,String> dateWeekMap=new HashMap();
			List list=jdbcTemplate.queryForList("select concat(w_year, Lpad(w_week, 2, 0)) as wweek, concat(f_year, Lpad(f_month, 2, 0)) as fmonth,concat(f_year, Lpad(f_week, 2, 0)) as fweek, t.* from jbd_period t order by t.start_time");
			for (int j = 0; j < list.size(); j++) {
				Map curMap=(Map) list.get(j);
				String wweek=curMap.get("wweek").toString();
				String fmonth=curMap.get("fmonth").toString();
				String f_week=curMap.get("fweek").toString();
				Date date1=(Date) curMap.get("start_time");
				Date date2=(Date) curMap.get("end_time");
				w2mBeriodMap.put(wweek, fmonth);

				dateWeekMap.put(date1+"~"+date2, f_week);
				
			}
			
			
			
			
			Map<String,String> zcw_member_map=new HashMap<String,String>();
			List zcw_member_list= jdbcTemplate.queryForList("select * from jpo_zcw_member ");
			for (int j = 0; j < zcw_member_list.size(); j++) {
				zcw_member_map.put(zcw_member_list.get(j).toString(), zcw_member_list.get(j).toString());
			}


			Map honorStarMap=ListUtil.getListOptions(companyCode, "pass.star.zero");
			Map membertypeMap=ListUtil.getListOptions(companyCode, "membertype");
			
		
			Connection conn=this.jdbcTemplate.getDataSource().getConnection();
			PreparedStatement pstmt = null;

			PreparedStatement curPstmt = null;
			
			ResultSet rs = null;
			try {

				String sql=" select  d.*,b.mi_user_name,b.mobiletele,b.mi_user_name,b.mobiletele,b.postalcode,b.address,b.check_date,b.member_type,b.company_code,b.remark,b.title_remark,b.papernumber, b.province,b.city,b.district "
						+ "from (select t.user_code, t.pass_star, nvl((select max(case when c.Pass_Star > 0 and c.pass_star <= 9 then c.pass_star else  0  end) as max_pass_star "
						+ "from v_jbd_member_link_calc c  where c.w_week >= ?  and c.w_week <= ?  and c.user_code = t.user_code), 0) as max_pass_star from v_jbd_member_link_calc t  where t.w_week = ? "
						+ "and t.pass_star >= 1 and t.pass_star <= 9) d ,jmi_member b  where d.pass_star > d.max_pass_star and d.user_code=b.user_code";
				
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, StringUtil.formatInt(formatedWeekStart));
				pstmt.setInt(2, StringUtil.formatInt(formatedWeekEnd));
				pstmt.setInt(3, StringUtil.formatInt(formatedWeek));
				
				rs=pstmt.executeQuery();

				int kk = 1;
				
				while (rs.next()) {


					String user_code=rs.getString("user_code");
					String pass_star=rs.getString("pass_star");
					
					//String w_week=rs.getString("w_week");
					String mi_user_name=rs.getString("mi_user_name");
					String mobiletele=rs.getString("mobiletele");
					String postalcode=rs.getString("postalcode");
					String province=rs.getString("province");
					String city=rs.getString("city");
					String district=rs.getString("district");
					
					String address=rs.getString("address");
					String member_type=rs.getString("member_type");
					String company_code=rs.getString("company_code");
					String remark=rs.getString("remark");
					String title_remark=rs.getString("title_remark");
					String papernumber=rs.getString("papernumber");
					if(!StringUtil.isEmpty(member_type)){
						member_type=member_type.trim();
					}

					Date check_date=rs.getDate("check_date");
					
					
					
					int index=0;
					
					
					
					eu.addString(wsheet, index++, kk, user_code);
					eu.addString(wsheet, index++, kk, mi_user_name);
					eu.addNumber(wsheet, index++, kk, StringUtil.formatInt(WeekFormatUtil.getFormatWeek("w",formatedWeek)));
					eu.addString(wsheet, index++, kk, w2mBeriodMap.get(formatedWeek));
					eu.addString(wsheet, index++, kk, LocaleUtil.getLocalText(honorStarMap.get(pass_star).toString()));
					eu.addString(wsheet, index++, kk, mobiletele);
					eu.addString(wsheet, index++, kk, postalcode);

					
					
					
					
					province=provinceMap.get(province)==null?"":provinceMap.get(province).toString();

					city=cityMap.get(city)==null?"":cityMap.get(city).toString();
					
					district=districtMap.get(district)==null?"":districtMap.get(district).toString();
					

					eu.addString(wsheet, index++, kk, province+city+district+address);
					
					
					
					boolean flag=false;
					Iterator dateWeekIt = dateWeekMap.entrySet().iterator();
					while (dateWeekIt.hasNext()) {
						Map.Entry entry = (Map.Entry) dateWeekIt.next(); 
					    String dateTime = entry.getKey().toString(); 
					    String val=entry.getValue().toString();
					    String dateTimeStr[]=dateTime.split("~");
					    Date startDate=DateUtil.convertStringToDate(dateTimeStr[0]);
					    Date endDate=DateUtil.convertStringToDate(dateTimeStr[1]);
					    if(check_date!=null && check_date.after(startDate) && check_date.before(endDate)){
					    	eu.addString(wsheet, index++, kk, val);
					    	flag=true;
					    	break;
					    }
					}
					
					if(!flag){
						eu.addString(wsheet, index++, kk, "");
					}

					eu.addString(wsheet, index++, kk, membertypeMap.get(member_type)==null?"": LocaleUtil.getLocalText(membertypeMap.get(member_type).toString()));

					
					
					eu.addString(wsheet, index++, kk, company_code);
					eu.addString(wsheet, index++, kk, remark);
					eu.addString(wsheet, index++, kk, title_remark);
					

					if(zcw_member_map.get(user_code)==null){
						eu.addString(wsheet, index++, kk, "否");
					}else{
						eu.addString(wsheet, index++, kk, "是");
					}
					eu.addString(wsheet, index++, kk, papernumber);
					kk++;
					
					
					
						
					
					
					
					
					

					
				}
				

				eu.writeExcel(wwb);
				eu.closeWritableWorkbook(wwb);
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
				} catch ( Exception e ) {}
				try {
					pstmt.close();
				} catch ( Exception e ) {}
				try {
					conn.close();
				} catch ( Exception e ) {}
			}
			return null;
		}
		return new ModelAndView(getSuccessView());
	}
	
	
	
	private Object[] getMembers(HttpServletRequest request) throws Exception{

		Object []res=new Object[2];
		res[0]=false;
		res[1]=new ArrayList();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("importExcelFile");
		if (file == null || file.getInputStream() == null || file.getSize()==0) {
			return res;
		}
		InputStream stream = file.getInputStream();
		ExcelUtil eu = new ExcelUtil();
		//获取可读的工作表对象，定位到要读取的excel文件
		Workbook workbook = eu.getWorkbook(stream);
		//读取此文件的第一个工作表，工作表序号从0开始。
		Sheet sheet = workbook.getSheet(0);

		List<String> messages = new ArrayList<String>();
		
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


	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}



}