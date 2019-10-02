package com.joymain.jecs.bd.webapp.action;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
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

import net.sf.excelutils.ExcelUtils;

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
import com.joymain.jecs.bd.model.VJbdMemberLinkCalc;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.bd.service.JbdMemberLinkCalcHistManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.DateUtil;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.io.FileUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdMonthlyAnalysisReportController extends BaseFormController {
	private transient final Log log = LogFactory.getLog(JbdMonthlyAnalysisReportController.class);
	private AlCompanyManager alCompanyManager;
	private AlCountryManager alCountryManager;
	private JmiMemberManager jmiMemberManager;
    private AlStateProvinceManager alStateProvinceManager;
    private AlCityManager alCityManager;
    private AlDistrictManager alDistrictManager;
    private JalTownManager jalTownManager;
	private JdbcTemplate jdbcTemplate = null;
	private ExcelUtil eu = null;
	private WritableSheet wsheet = null;
	private HttpServletRequest innerRequest;
	private LinkedHashMap<String,String> textMap=new LinkedHashMap<String,String>();
	
	//改为jdbcTemplateReport
	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	private BdPeriodManager bdPeriodManager;
	private JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager;
	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public JbdMonthlyAnalysisReportController() {
		setCommandName("bdPeriod");
		setCommandClass(BdPeriod.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {

        SysUser defSysUser = SessionLogin.getLoginUser(request);
        
		
		return new BdPeriod();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
		
		if ("post".equalsIgnoreCase(request.getMethod()) && "jbdMonthlyAnalysisReport".equalsIgnoreCase(request.getParameter("strAction"))) {
			this.innerRequest=request;
			String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
			if (SessionLogin.getLoginUser(request).getIsManager()) {
				companyCode = request.getParameter("companyCode");
			}
			Map map=request.getParameterMap();


			//生成excel文件
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=jbdMonthlyAnalysisReport.xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			eu = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			//在此创建的新excel文件创建一工作表 
			wsheet = wwb.createSheet("Sheet1", 0);
			
			
			
	    	String formatedWeek=request.getParameter("formatedWeek");
	    	String userCode=request.getParameter("userCode");
	    	
	    	if(StringUtil.isEmpty(formatedWeek)||StringUtil.isEmpty(userCode)){
				MessageUtil.saveMessage(request,"请输入条件");
		        return new ModelAndView("redirect:jbdMonthlyAnalysisReport.html?strAction=jbdMonthlyAnalysisReport&needReload=1");
	    	}
	    	BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByFormatedWeek(WeekFormatUtil.getFormatWeek("f",formatedWeek));
	    	if(bdPeriod==null||bdPeriod.getMonthStatus()==0){
				MessageUtil.saveMessage(request,"期别输入错误，请输入月结周");
		        return new ModelAndView("redirect:jbdMonthlyAnalysisReport.html?strAction=jbdMonthlyAnalysisReport&needReload=1");	
	    	}

	    	formatedWeek=WeekFormatUtil.getFormatWeek("f",formatedWeek);
			
	    	//查出当前会员当前期别数据
	    	//VJbdMemberLinkCalc jbdMemberLinkCalcHist = jbdMemberLinkCalcHistManager.getJbdMemberLinkCalcHistsByUserCodeWeek(userCode, formatedWeek);

	    	
	    	JmiMember jmiMember=jmiMemberManager.getJmiMember(userCode);
	    	
	    	
			int rownum=0;
			int column=0;
			
			

			Map passStarMap=ListUtil.getListOptions(companyCode, "pass.star.zero");
			

			Integer startWeek=0;
			Integer endWeek=0;
			String startDate=null;
			String endDate=null;

			Integer startYear=0;
			Integer endYear=0;
			
			List<BdPeriod> list=bdPeriodManager.getBdPeriodsByFmonth(bdPeriod.getFyear().toString(), bdPeriod.getFmonth().toString());
			
			startYear=list.get(0).getWyear()*100;
			endYear=list.get(list.size()-1).getWyear()*100;
			
			startWeek=startYear+list.get(0).getWweek();
			endWeek=endYear+list.get(list.size()-1).getWweek();
			
			startDate=DateUtil.getDateTime("yyyy-MM-dd HH:ss:mm",  list.get(0).getStartTime());

			long endTimeLong=list.get(list.size()-1).getEndTime().getTime()-1000l;
			endDate=DateUtil.getDateTime("yyyy-MM-dd HH:ss:mm",  new Date(endTimeLong));
			
    		List list1=this.jdbcTemplate.queryForList("Select a.w_Week,nvl( a.Week_Group_Pv,0) as Week_Group_Pv,nvl(a.MONTH_GROUP_PV,0) as MONTH_GROUP_PV,a.PASS_STAR ,nvl(a.MONTH_CONSUMER_PV1,0) as MONTH_CONSUMER_PV1" +
    				" From v_Jbd_Day_Bouns_Calc a Where User_Code = '"+userCode+"' And w_Week >= "+startWeek+" And w_Week <= "+endWeek+" order by w_week desc");
    		
    		

    		//ExcelUtils.addValue("totalMonthPv", ((Map)list1.get(0)).get("month_group_pv"));//总业绩
    		
    		List listPassStar=this.jdbcTemplate.queryForList("select Pass_Star from v_Jbd_Day_Bouns_Calc where user_code='"+userCode+"' and w_week="+formatedWeek);
    		
    		String passStar="";
    		if(!listPassStar.isEmpty()){
    			passStar=((Map)listPassStar.get(0)).get("pass_star").toString();
    		}
    		

			eu.addString(wsheet,  column, rownum, "系统姓名");
			eu.addString(wsheet,  column++, rownum+1,jmiMember.getMiUserName());
			
			eu.addString(wsheet,  column,rownum, "会员编号");
			eu.addString(wsheet,  column++, rownum+1,userCode);

			eu.addString(wsheet,  column, rownum,"会员当月奖衔");
			eu.addString(wsheet,  column++,rownum+1, this.getListValue(passStarMap, passStar));
			
			//总业绩
			String totalMonthPv=((Map)list1.get(0)).get("month_group_pv").toString();
			eu.addString(wsheet, column, rownum, "总业绩");
			eu.addNumber(wsheet,  column++,rownum+1,new Double(totalMonthPv));

			String month_consumer_pv1=((Map)list1.get(0)).get("month_consumer_pv1").toString();
			eu.addString(wsheet, column, rownum, "当月个人重消业绩");
			eu.addNumber(wsheet,  column++,rownum+1,new Double(month_consumer_pv1));
			
			rownum++;
			rownum++;
			column=0;
			//部门业绩
			List list2=this.jdbcTemplate.queryForList("Select a.User_Code, nvl( a.Month_Group_Pv,0) as Month_Group_Pv From v_Jbd_Day_Bouns_Calc a " +
					"Where Link_No = '"+userCode+"' And w_Week = "+formatedWeek+" Order By Month_Group_Pv Desc");

			eu.addString(wsheet, column++, rownum, "会员编号");
			eu.addString(wsheet, column++, rownum, "会员姓名");
			eu.addString(wsheet, column++, rownum,"业绩");
			
			rownum++;
			column=0;
			
			for (int i = 0; i < list2.size(); i++) {
				Map curMap=(Map)list2.get(i);
				String user_code=curMap.get("user_code").toString();
				String month_group_pv=curMap.get("month_group_pv").toString();

				eu.addString(wsheet, column++, rownum, user_code);
				
				JmiMember curJmiMember=jmiMemberManager.getJmiMember(user_code);
				if(curJmiMember!=null){
					eu.addString(wsheet, column++, rownum, curJmiMember.getMiUserName());
				}else{
					eu.addString(wsheet, column++, rownum, "");
				}
				
				
				eu.addNumber(wsheet,  column++, rownum,new Double(month_group_pv));

				rownum++;
				column=0;
			}

			
			
			eu.addString(wsheet, column++, rownum, "周期");
			eu.addString(wsheet, column++, rownum, "业绩");

			rownum++;
			column=0;
			
			for (int i = 0; i < list1.size(); i++) {
				Map curMap=(Map)list1.get(i);
				String w_week=curMap.get("w_week").toString();
				String week_group_pv=curMap.get("week_group_pv").toString();
				

				eu.addString(wsheet,  column++,rownum, WeekFormatUtil.getFormatWeek("w",w_week));
				eu.addNumber(wsheet,  column++, rownum,new Double(week_group_pv));
				

				rownum++;
				column=0;
				
			}
			Map provinceMap=alStateProvinceManager.getAlStateProvincesMapByCountry("CN");
			
			Map cityMap= alCityManager.getAlCityMap("CN");
			
			//省业绩
			List list3=this.jdbcTemplate.queryForList(" Select Sum( Case When (o.Order_Type = '1' And " +
					"o.Check_Date >= To_Date('"+startDate+"', 'yyyy-mm-dd hh24:mi:ss') and " +
					"o.Check_Date <= To_Date('"+endDate+"', 'yyyy-mm-dd hh24:mi:ss')) Then o.Pv_Amt Else o.Pv_Amt End) as pv_amt,o.province,o.city,Sum( Case When (o.Order_Type = '1' And " +
					"o.Check_Date >= To_Date('"+startDate+"', 'yyyy-mm-dd hh24:mi:ss') and " +
					"o.Check_Date <= To_Date('"+endDate+"', 'yyyy-mm-dd hh24:mi:ss')) Then 1 Else 0 End) As Vip, " +
					"Count(Decode(o.Order_Type, 6, 1)) As Isstore1, Count(Decode(o.Order_Type, 11, 1)) As Isstore2, Count(Decode(o.Order_Type, 12, 1)) As Isstore3 " +
					"From Jmi_Link_Ref r, Jpo_Member_Order o Left Join Jmi_Member m On m.User_Code = o.User_Code " +
					"Where o.User_Code = r.User_Code And r.Tree_Index Like (select tree_index from jmi_link_ref " +
					"where user_code = '"+userCode+"') || '%' And o.Check_Date >= To_Date('"+startDate+"', 'yyyy-mm-dd hh24:mi:ss') " +
					"And o.Check_Date <= To_Date('"+endDate+"', 'yyyy-mm-dd hh24:mi:ss') And Status = '2' And o.Company_Code = 'CN' " +
					"and not exists (select 1 from Jpr_Refund n where n.Mo_Id = o.Mo_Id And n.Refund_Status = '2') " +
					"Group By o.Province,o.city Order By Sum(o.Pv_Amt) Desc");

			eu.addString(wsheet, column++, rownum, "市场名称（省）");
			eu.addString(wsheet, column++, rownum, "市场名称（市）");
			eu.addString(wsheet, column++, rownum, "市场业绩");

			rownum++;
			column=0;

			double vipNum=0;
			double isstore1Num=0;
			double isstore2Num=0;
			double isstore3Num=0;
			for (int i = 0; i < list3.size(); i++) {
				Map curMap=(Map)list3.get(i);
				String pv_amt=curMap.get("pv_amt").toString();
				String province=curMap.get("province").toString();
				String city=curMap.get("city").toString();
				String vip=curMap.get("vip").toString();
				String isstore1=curMap.get("isstore1").toString();
				String isstore2=curMap.get("isstore2").toString();
				String isstore3=curMap.get("isstore3").toString();
				
				

				eu.addString(wsheet, column++, rownum, getListValue(provinceMap, province));
				eu.addString(wsheet, column++, rownum, getListValue(cityMap, city));
				eu.addNumber(wsheet, column++, rownum, new Double(pv_amt));
				
				vipNum+=StringUtil.formatDouble(vip);
				isstore1Num+=StringUtil.formatDouble(isstore1);
				isstore2Num+=StringUtil.formatDouble(isstore2);
				isstore3Num+=StringUtil.formatDouble(isstore3);
				

				rownum++;
				column=0;
				
			}


			//eu.addString(wsheet,  column++, rownum,"VIP数量");
			eu.addString(wsheet,  column++, rownum,"一级店数量");
			eu.addString(wsheet,  column++, rownum,"二级店数量");
			eu.addString(wsheet,  column++, rownum,"二升一数量");
			

			rownum++;
			column=0;
			//eu.addNumber(wsheet, column++, rownum, new Double(vipNum));
			eu.addNumber(wsheet, column++,rownum,  new Double(isstore1Num));
			eu.addNumber(wsheet,  column++,rownum, new Double(isstore2Num));
			eu.addNumber(wsheet,  column++,rownum, new Double(isstore3Num));

			rownum++;
			column=0;
			
			//预奖衔人数(安置网络)

			eu.addString(wsheet,  column++, rownum,"绿宝");
			eu.addString(wsheet,  column++, rownum,"红宝");
			eu.addString(wsheet,  column++, rownum, "蓝宝");
			eu.addString(wsheet,  column++, rownum, "黄钻");
			eu.addString(wsheet,  column++, rownum, "蓝钻");
			eu.addString(wsheet,  column++, rownum, "黑钻");
			eu.addString(wsheet,  column++, rownum, "皇冠");
			eu.addString(wsheet,  column++, rownum, "皇冠大使");
			eu.addString(wsheet,  column++, rownum,"皇冠特使");

			rownum++;
			column=0;

			List list4=this.jdbcTemplate.queryForList("Select Sum(Decode(Pass_Star, 1, 1, 0)) Pass_Star1,Sum(Decode(Pass_Star, 2, 1, 0)) Pass_Star2, " +
					"Sum(Decode(Pass_Star, 3, 1, 0)) Pass_Star3,Sum(Decode(Pass_Star, 4, 1, 0)) Pass_Star4, " +
					"Sum(Decode(Pass_Star, 5, 1, 0)) Pass_Star5,Sum(Decode(Pass_Star, 6, 1, 0)) Pass_Star6, " +
					"Sum(Decode(Pass_Star, 7, 1, 0)) Pass_Star7, Sum(Decode(Pass_Star, 8, 1, 0)) Pass_Star8, " +
					"Sum(Decode(Pass_Star, 9, 1, 0)) Pass_Star9 From v_Jbd_Day_Bouns_Calc a Where User_Code In " +
					"(Select User_Code From Jmi_Link_Ref Where Tree_Index Like (select tree_index from jmi_link_ref " +
					"where user_code = '"+userCode+"') || '%') and a.PASS_STAR > 0 And w_Week = "+formatedWeek);
			
			
			if(!list4.isEmpty()){

				Map pass_star_map=(Map)list4.get(0);
				

				eu.addString(wsheet, column++,rownum, pass_star_map.get("pass_Star1")+"");
				eu.addString(wsheet, column++,rownum, pass_star_map.get("pass_Star2")+"");
				eu.addString(wsheet, column++,rownum,  pass_star_map.get("pass_Star3")+"");
				eu.addString(wsheet, column++,rownum,  pass_star_map.get("pass_Star4")+"");
				eu.addString(wsheet, column++,rownum,  pass_star_map.get("pass_Star5")+"");
				eu.addString(wsheet, column++,rownum,  pass_star_map.get("pass_Star6")+"");
				eu.addString(wsheet, column++,rownum,  pass_star_map.get("pass_Star7")+"");
				eu.addString(wsheet, column++,rownum,  pass_star_map.get("pass_Star8")+"");
				eu.addString(wsheet, column++,rownum,  pass_star_map.get("pass_Star9")+"");
				
			}
			
			eu.writeExcel(wwb);
			eu.closeWritableWorkbook(wwb);
			os.close();
	
			return null;
		}
		return new ModelAndView(getSuccessView());
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

	public void setJbdMemberLinkCalcHistManager(
			JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager) {
		this.jbdMemberLinkCalcHistManager = jbdMemberLinkCalcHistManager;
	}

	private String getListValue(Map listMap,Object data){
		String value="";
		if(data!=null){
			Object oo=listMap.get(data.toString());
			if(oo!=null){
				return LocaleUtil.getLocalText(oo.toString());
			}else{
				return data.toString();
			}
		}
		return value;
		
	}

}