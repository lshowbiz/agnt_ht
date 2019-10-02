package com.joymain.jecs.mi.webapp.action;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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

import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.al.service.JalTownManager;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JmiMemberKpvTotalReportController extends BaseFormController {
	private transient final Log log = LogFactory.getLog(JmiMemberKpvTotalReportController.class);
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


	public JmiMemberKpvTotalReportController() {
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

		if ("post".equalsIgnoreCase(request.getMethod()) && "jmiMemberKpvTotalReport".equalsIgnoreCase(request.getParameter("strAction"))) {
			this.innerRequest=request;
			String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
			if (SessionLogin.getLoginUser(request).getIsManager()) {
				companyCode = request.getParameter("companyCode");
			}
			Map map=request.getParameterMap();


			//生成excel文件
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=jmiMemberKpvTotalReport.xls");
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

			int i=0;
			
        	eu.addString(wsheet, i++, 0, LocaleUtil.getLocalText("miMember.memberNo"));
			eu.addString(wsheet, i++, 0, LocaleUtil.getLocalText("miLinkRef.total"));
			eu.addString(wsheet, i++, 0, LocaleUtil.getLocalText("miMember.recommendCount"));
			eu.addString(wsheet, i++, 0, LocaleUtil.getLocalText("bdBonusStatReport.company.keey.pv"));
			
			
			

			//设置进度条总记录数
			
			
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
//					if(userCodes.split("','").length>=1000){
//						this.saveMessage(request, "数据过多");
//						return new ModelAndView(getSuccessView());
//					}
				}
			}
			

			
			
			 String formatedWeek = request.getParameter("formatedWeek");


			 formatedWeek=WeekFormatUtil.getFormatWeek("f",formatedWeek);
		
			
			
		
			Connection conn=this.jdbcTemplate.getDataSource().getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {

				
				

				InputStream stream = file.getInputStream();
				Workbook workbook = eu.getWorkbook(stream);
				Sheet sheet = workbook.getSheet(0);
				int kk = 1;
				for (int j = 1; j < sheet.getRows(); j++) {


					int index=0;
					
					String userCode = eu.getContents(sheet, 0, j);
					
					String recommend_sql = "select count(*) as recommend_sum  from jmi_recommend_ref r where r.tree_index like  (select tree_index from jmi_recommend_ref where user_code=?) || '%'";
					pstmt = conn.prepareStatement(recommend_sql);
					pstmt.setString(1, userCode);
					rs=pstmt.executeQuery();
					rs.next();
					String recommend_sum=rs.getString("recommend_sum");
					
					rs.close();
					pstmt.close();
					
					
					String link_sql = "select count(*) as link_sum  from jmi_link_ref l where l.tree_index like  (select tree_index from jmi_link_ref where user_code=?) || '%'";
					
					
					pstmt = conn.prepareStatement(link_sql);
					pstmt.setString(1, userCode);
					rs=pstmt.executeQuery();
					rs.next();
					String link_sum=rs.getString("link_sum");
					

					rs.close();
					pstmt.close();
					
					String keepPv="select t.keep_pv from jbd_member_link_calc_hist t where t.user_code=? and t.w_week=? ";
					
					
					pstmt = conn.prepareStatement(keepPv);
					pstmt.setString(1, userCode);
					pstmt.setInt(2, StringUtil.formatInt(formatedWeek));
					rs=pstmt.executeQuery();

					String keep_pv="0";
					if(rs.next()){
						keep_pv=rs.getString("keep_pv");
					}
					

					rs.close();
					pstmt.close();
					
					eu.addString(wsheet, index++, kk, userCode);
					eu.addNumber(wsheet, index++, kk, StringUtil.formatInt(link_sum));
					eu.addNumber(wsheet, index++, kk, StringUtil.formatInt(recommend_sum));
					eu.addNumber(wsheet, index++, kk, new Double(keep_pv));
					
					kk++;
//					System.out.println(recommend_sum);
//					System.out.println(link_sum);
//					
//					
//					System.out.println();
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



}