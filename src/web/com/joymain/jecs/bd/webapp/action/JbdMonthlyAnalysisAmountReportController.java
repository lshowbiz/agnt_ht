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

public class JbdMonthlyAnalysisAmountReportController extends BaseFormController {
	private transient final Log log = LogFactory.getLog(JbdMonthlyAnalysisAmountReportController.class);
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
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	private BdPeriodManager bdPeriodManager;
	private JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager;
	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public JbdMonthlyAnalysisAmountReportController() {
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
		
		if ("post".equalsIgnoreCase(request.getMethod()) && "jbdMonthlyAnalysisAmountReport".equalsIgnoreCase(request.getParameter("strAction"))) {
			this.innerRequest=request;
			String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
			if (SessionLogin.getLoginUser(request).getIsManager()) {
				companyCode = request.getParameter("companyCode");
			}
			Map map=request.getParameterMap();

			String characterCode=SessionLogin.getLoginUser(request).getDefCharacterCoding();
			//生成excel文件
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=jbdMonthlyAnalysisAmountReport.xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			eu = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			//在此创建的新excel文件创建一工作表 
			wsheet = wwb.createSheet("Sheet1", 0);
			
			
			
	    	String formatedWeek=request.getParameter("formatedWeek");
	    	String userCode=request.getParameter("userCode");
	    	if("M".equals(SessionLogin.getLoginUser(request).getUserType())){
	    		userCode=SessionLogin.getLoginUser(request).getUserCode();
	    	}
	    	if(StringUtil.isEmpty(formatedWeek)||StringUtil.isEmpty(userCode)){
				MessageUtil.saveMessage(request,"请输入条件");
		        return new ModelAndView("redirect:jbdMonthlyAnalysisAmountReport.html?strAction=jbdMonthlyAnalysisAmountReport");
	    	}

			
	    	//查出当前会员当前期别数据
	    	//VJbdMemberLinkCalc jbdMemberLinkCalcHist = jbdMemberLinkCalcHistManager.getJbdMemberLinkCalcHistsByUserCodeWeek(userCode, formatedWeek);

	    	
	    	
	    	
			int rownum=0;
			int column=0;
			
			
/*			List<BdPeriod> list=bdPeriodManager.getBdPeriodsByFmonth(bdPeriod.getFyear().toString(), bdPeriod.getFmonth().toString());
			
			startYear=list.get(0).getWyear()*100;
			endYear=list.get(3).getWyear()*100;
			
			startWeek=startYear+list.get(0).getWweek();
			endWeek=endYear+list.get(3).getWweek();
			
			startDate=DateUtil.getDateTime("yyyy-MM-dd HH:ss:mm",  list.get(0).getStartTime());

			long endTimeLong=list.get(3).getEndTime().getTime()-1000l;
			endDate=DateUtil.getDateTime("yyyy-MM-dd HH:ss:mm",  new Date(endTimeLong));*/
			
    		List list1=this.jdbcTemplate.queryForList("select * from jbd_analyse_year where user_code='"+userCode+"' and w_month="+formatedWeek);
    		
    
   /* 		List listPassStar=this.jdbcTemplate.queryForList("select Pass_Star from v_Jbd_Day_Bouns_Calc where user_code='"+userCode+"' and w_week="+formatedWeek);
    		
    		String passStar="";
    		if(!listPassStar.isEmpty()){
    			passStar=((Map)listPassStar.get(0)).get("pass_star").toString();
    		}*/
    		
    		String user_code="";
    		String user_name="";
    		String pass_star_thismonth="";//当月奖衔
    		String pass_star_year_max="";//年度最高奖衔
    		Double pv_amount=new Double(0);//业绩PV
    		Double amount=new Double(0);//金额
    		Double jj_amount=new Double(0);//发展基金
    		Double discount_amount=new Double(0);//积分
    		Double total_money=new Double(0);//总金额
    		Double cx_amount=new Double(0);//当月个人重消业绩


    		if(!list1.isEmpty()){
    			Map curMap=(Map) list1.get(0);
    			user_code=this.getValueString(curMap, "user_code");
    			user_name=this.getValueString(curMap, "user_name");
    			
    			pass_star_thismonth=this.getValueString(curMap, "pass_star_thismonth");
    			pass_star_year_max=this.getValueString(curMap, "pass_star_year_max");
    			
    			pv_amount=this.getValueDouble(curMap, "pv_amount");
        		amount=this.getValueDouble(curMap, "amount");
        		jj_amount=this.getValueDouble(curMap, "jj_amount");
        		discount_amount=this.getValueDouble(curMap, "discount_amount");
        		total_money=this.getValueDouble(curMap, "total_money");
        		cx_amount=this.getValueDouble(curMap, "cx_amount");
    		}
    		

			eu.addString(wsheet,  column, rownum, "系统姓名");
			eu.addString(wsheet,  column++, rownum+1,user_name);
			
			eu.addString(wsheet,  column,rownum, "会员编号");
			eu.addString(wsheet,  column++, rownum+1,user_code);

			eu.addString(wsheet,  column, rownum,"会员当月奖衔");
			eu.addString(wsheet,  column++,rownum+1, ListUtil.getListValue(characterCode, "pass.star.zero", pass_star_thismonth));
			
			eu.addString(wsheet,  column, rownum,"年度最高奖衔");
			eu.addString(wsheet,  column++,rownum+1, ListUtil.getListValue(characterCode, "pass.star.zero", pass_star_year_max));

			eu.addString(wsheet,  column, rownum,"业绩（PV）");
			eu.addNumber(wsheet,  column++,rownum+1, pv_amount);
			
			eu.addString(wsheet,  column, rownum,"金额");
			eu.addNumber(wsheet,  column++,rownum+1, amount);
			
			eu.addString(wsheet,  column, rownum,"发展基金");
			eu.addNumber(wsheet,  column++,rownum+1, jj_amount);
			
			eu.addString(wsheet,  column, rownum,"积分");
			eu.addNumber(wsheet,  column++,rownum+1, discount_amount);
			
			eu.addString(wsheet,  column, rownum,"总金额");
			eu.addNumber(wsheet,  column++,rownum+1, total_money);
			
			eu.addString(wsheet,  column, rownum,"当月个人重消业绩");
			eu.addNumber(wsheet,  column++,rownum+1, cx_amount);
			
			
			
			
			rownum++;
			rownum++;
			column=0;
			
			
			
			List list2=this.jdbcTemplate.queryForList("select * from jbd_analyse_year_list1 where user_code='"+userCode+"' and w_month="+formatedWeek);

			eu.addString(wsheet, column++, rownum, "期别");
			eu.addString(wsheet, column++, rownum, "业绩（PV）");
			eu.addString(wsheet, column++, rownum,"金额");
			eu.addString(wsheet, column++, rownum,"发展基金");
			eu.addString(wsheet, column++, rownum,"积分");
			
			rownum++;
			column=0;
			
			for (int i = 0; i < list2.size(); i++) {
				Map curMap=(Map)list2.get(i);
				String w_week=this.getValueString(curMap, "w_week");
				pv_amount=this.getValueDouble(curMap, "pv_amount");
				amount=this.getValueDouble(curMap, "amount");

				jj_amount=this.getValueDouble(curMap, "jj_amount");
				discount_amount=this.getValueDouble(curMap, "discount_amount");

				
				
				eu.addString(wsheet, column++, rownum, w_week);
				eu.addNumber(wsheet,  column++, rownum,pv_amount);
				eu.addNumber(wsheet,  column++, rownum,amount);
				eu.addNumber(wsheet,  column++, rownum,jj_amount);
				eu.addNumber(wsheet,  column++, rownum,discount_amount);

				rownum++;
				column=0;
			}


			eu.addString(wsheet, column++, rownum,"会员编号");
			eu.addString(wsheet, column++, rownum,"会员姓名");
			eu.addString(wsheet, column++, rownum,"当月奖衔");
			eu.addString(wsheet, column++, rownum,"年度最高奖衔");
			eu.addString(wsheet, column++, rownum,"业绩（PV）");
			eu.addString(wsheet, column++, rownum,"金额");
			eu.addString(wsheet, column++, rownum,"发展基金");
			eu.addString(wsheet, column++, rownum,"积分");
			eu.addString(wsheet, column++, rownum,"总金额");
			eu.addString(wsheet, column++, rownum,"当月个人重消业绩");

			List list3=this.jdbcTemplate.queryForList("select * from jbd_analyse_year_ref where recommend_no='"+userCode+"' and w_month="+formatedWeek);
			
			rownum++;
			column=0;
			

			for (int i = 0; i < list3.size(); i++) {
				Map curMap=(Map)list3.get(i);
				

    			user_code=this.getValueString(curMap, "user_code");
    			user_name=this.getValueString(curMap, "user_name");
    			

    			pass_star_thismonth=this.getValueString(curMap, "pass_star_thismonth");
    			pass_star_year_max=this.getValueString(curMap, "pass_star_year_max");
    			
    			pv_amount=this.getValueDouble(curMap, "pv_amount");
        		amount=this.getValueDouble(curMap, "amount");
        		jj_amount=this.getValueDouble(curMap, "jj_amount");
        		discount_amount=this.getValueDouble(curMap, "discount_amount");
        		total_money=this.getValueDouble(curMap, "total_money");
        		cx_amount=this.getValueDouble(curMap, "cx_amount");
				
				

				eu.addString(wsheet, column++, rownum, user_code);
				eu.addString(wsheet, column++, rownum, user_name);
				eu.addString(wsheet, column++, rownum, ListUtil.getListValue(characterCode, "pass.star.zero", pass_star_thismonth));
				eu.addString(wsheet, column++, rownum, ListUtil.getListValue(characterCode, "pass.star.zero", pass_star_year_max));
        		

				eu.addNumber(wsheet,  column++, rownum,pv_amount);
				eu.addNumber(wsheet,  column++, rownum,amount);
				eu.addNumber(wsheet,  column++, rownum,jj_amount);
				eu.addNumber(wsheet,  column++, rownum,discount_amount);
				eu.addNumber(wsheet,  column++, rownum,total_money);
				eu.addNumber(wsheet,  column++, rownum,cx_amount);

				rownum++;
				column=0;
			}
			
			
			List list4=this.jdbcTemplate.queryForList("select * from jbd_analyse_year_list2 where user_code='"+userCode+"' and w_month="+formatedWeek);

			eu.addString(wsheet, column++, rownum, "市场名称（省）");
			eu.addString(wsheet, column++, rownum, "市场名称（市）");
			eu.addString(wsheet, column++, rownum, "市场业绩");
			eu.addString(wsheet, column++, rownum, "金额");
			eu.addString(wsheet, column++, rownum,"发展基金");
			eu.addString(wsheet, column++, rownum,"积分");

			rownum++;
			column=0;

			
			for (int i = 0; i < list4.size(); i++) {
				Map curMap=(Map)list4.get(i);
				
				String province_name=this.getValueString(curMap, "province_name");
				String city_name=this.getValueString(curMap, "city_name");
    			
    			pv_amount=this.getValueDouble(curMap, "pv_amount");
    			amount=this.getValueDouble(curMap, "amount");

				jj_amount=this.getValueDouble(curMap, "jj_amount");
				discount_amount=this.getValueDouble(curMap, "discount_amount");

				eu.addString(wsheet, column++, rownum,province_name);
				eu.addString(wsheet, column++, rownum,city_name);
				eu.addNumber(wsheet, column++, rownum,pv_amount);
				eu.addNumber(wsheet, column++, rownum,amount);

				eu.addNumber(wsheet, column++, rownum,jj_amount);
				eu.addNumber(wsheet, column++, rownum,discount_amount);

				rownum++;
				column=0;
				
			}


			List list5=this.jdbcTemplate.queryForList("select * from jbd_analyse_year_list3 where user_code='"+userCode+"' and w_month="+formatedWeek);
			

			eu.addString(wsheet, column++, rownum, "订单类型");
			eu.addString(wsheet, column++, rownum, "订单数量");
			eu.addString(wsheet, column++, rownum, "业绩（PV）");
			eu.addString(wsheet, column++, rownum, "金额");
			eu.addString(wsheet, column++, rownum,"发展基金");
			eu.addString(wsheet, column++, rownum,"积分");
			

			rownum++;
			column=0;
			

			for (int i = 0; i < list5.size(); i++) {
				Map curMap=(Map)list5.get(i);

				

				String order_type=this.getValueString(curMap, "order_type");

				Double order_qty=this.getValueDouble(curMap, "order_qty");
				pv_amount=this.getValueDouble(curMap, "pv_amount");
				amount=this.getValueDouble(curMap, "amount");

				jj_amount=this.getValueDouble(curMap, "jj_amount");
				discount_amount=this.getValueDouble(curMap, "discount_amount");
				
				eu.addString(wsheet, column++, rownum,ListUtil.getListValue(characterCode, "po.all.order_type", order_type));
				eu.addNumber(wsheet, column++, rownum,order_qty);
				eu.addNumber(wsheet, column++, rownum,pv_amount);
				eu.addNumber(wsheet, column++, rownum,amount);
				eu.addNumber(wsheet, column++, rownum,jj_amount);
				eu.addNumber(wsheet, column++, rownum,discount_amount);

				rownum++;
				column=0;
			}
			
			
			
			

			List list6=this.jdbcTemplate.queryForList("select * from jbd_analyse_year_list4 where user_code='"+userCode+"' and w_month="+formatedWeek);
			

			eu.addString(wsheet, column++, rownum, "产品类型");
			eu.addString(wsheet, column++, rownum, "本月订购数量");
			

			rownum++;
			column=0;
			

			for (int i = 0; i < list6.size(); i++) {
				Map curMap=(Map)list6.get(i);

				

				String product_category=this.getValueString(curMap, "product_category");
				Double order_qty=this.getValueDouble(curMap, "order_qty");
				
				eu.addString(wsheet, column++, rownum,ListUtil.getListValue(characterCode, "pmproduct.productcategory", product_category));
				eu.addNumber(wsheet, column++, rownum,order_qty);

				rownum++;
				column=0;
			}
			

			

			List list7=this.jdbcTemplate.queryForList("select * from jbd_analyse_year_list5 where user_code='"+userCode+"' and w_month="+formatedWeek +" order by w_week,card_type");
			

			eu.addString(wsheet, column++, rownum, "周期");
			eu.addString(wsheet, column++, rownum, "优惠顾客");
			eu.addString(wsheet, column++, rownum, "金级");
			eu.addString(wsheet, column++, rownum, "白金级");
			eu.addString(wsheet, column++, rownum, "钻石级");
			eu.addString(wsheet, column++, rownum, "VIP数量");
			eu.addString(wsheet, column++, rownum, "一级店数量");
			eu.addString(wsheet, column++, rownum, "二级店数量");
			eu.addString(wsheet, column++, rownum, "二升一数量");
			

/*			rownum++;
			column=0;*/
			
			String wweek="";
			for (int i = 0; i < list7.size(); i++) {
				Map curMap=(Map)list7.get(i);


				String card_type=this.getValueString(curMap, "card_type");

				String w_week=this.getValueString(curMap, "w_week");
				Double member_num=this.getValueDouble(curMap, "member_num");
				
				
				if(!wweek.equals(w_week)){
					

					rownum++;
					column=0;
					eu.addString(wsheet, column++, rownum,w_week);
					eu.addNumber(wsheet, column++, rownum,member_num);

					wweek=w_week;
				}else{

					eu.addNumber(wsheet, column++, rownum,member_num);
					
				}
				
				
				
				
		/*		rownum++;
				column=0;*/
			}
			
			

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

			List list8=this.jdbcTemplate.queryForList("select * from jbd_analyse_year_list6   where  user_code='"+userCode+"' and w_month="+formatedWeek+" order by pass_star ");
				
			
			if(!list8.isEmpty()){

				for (int i = 0; i < list8.size(); i++) {
					Map curMap=(Map)list8.get(i);
					Double passnew_num=this.getValueDouble(curMap, "passnew_num");

					eu.addNumber(wsheet, column++, rownum,passnew_num);
					
				}
				
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
	private String getValueString(Map map,String key){
		if(map.get(key)==null){
			return "";
		}else{
			return map.get(key).toString();
		}
	}
	private Double getValueDouble(Map map,String key){
		if(map.get(key)==null){
			return new Double(0);
		}else{
			return new Double(map.get(key).toString());
		}
	}
}