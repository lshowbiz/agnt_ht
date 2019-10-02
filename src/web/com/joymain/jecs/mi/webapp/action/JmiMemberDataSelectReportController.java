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
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.service.JmiRecommendRefManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.DateUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.io.FileUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JmiMemberDataSelectReportController extends BaseFormController {
	private transient final Log log = LogFactory.getLog(JmiMemberDataSelectReportController.class);
	private AlCompanyManager alCompanyManager;
	private AlCountryManager alCountryManager;
	private JmiMemberManager jmiMemberManager;
	private JmiRecommendRefManager jmiRecommendRefManager;
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


	public JmiMemberDataSelectReportController() {
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

		if ("post".equalsIgnoreCase(request.getMethod()) && "jmiMemberDataSelectReport".equalsIgnoreCase(request.getParameter("strAction"))) {
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
	        	

        	//文件导入
    		Map memberMap=null;
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("importExcelFile");
			if (file!=null && file.getSize()!=0) {
				memberMap=this.getMembers(request);
			}
			
        	//------
        	
        	if(memberMap==null){
				this.saveMessage(request, "未找到数据");
				return new ModelAndView(getSuccessView());
        	}
		
			
			
		
			Connection conn=this.jdbcTemplate.getDataSource().getConnection();
			Statement stmt = null;
			ResultSet rs = null;
			try {
				Map resultMemberMap=new HashMap<String, List>();
				eu.addString(wsheet, 0, 0,"会员编号");
				eu.addString(wsheet, 1, 0,"推荐会员");
				
				
				
				Iterator memberIt = memberMap.entrySet().iterator();
					while (memberIt.hasNext()) {

						Map.Entry entry = (Map.Entry) memberIt.next(); 
					    String userCode = entry.getKey().toString(); 
					    List list=jmiRecommendRefManager.getMembersByUserCode(userCode);
					    List recommends=new ArrayList();
					    for (int i = 0; i < list.size(); i++) {
							Map curMap=(Map) list.get(i);
							String curUserCode=curMap.get("user_code").toString();
							if(memberMap.get(curUserCode)!=null){
								recommends.add(curUserCode);
							}	
						}
					    resultMemberMap.put(userCode, recommends);

					    
					}
				
					//写EXCEL
					Iterator resultMemberIt = resultMemberMap.entrySet().iterator();
					
					int kk=1;
						while (resultMemberIt.hasNext()) {

							Map.Entry entry = (Map.Entry) resultMemberIt.next(); 
						    String userCode = entry.getKey().toString(); 
						    List list= (List) entry.getValue();
						    
							eu.addString(wsheet,0, kk++, userCode);

							for (int i = 0; i < list.size(); i++) {
								eu.addString(wsheet,1, kk++, list.get(i).toString());
							}
							
							
							
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
					stmt.close();
				} catch ( Exception e ) {}
				try {
					conn.close();
				} catch ( Exception e ) {}
			}
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


	
	private Map getMembers(HttpServletRequest request) throws Exception{

		Map<String, String> map=new HashMap<String, String>();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("importExcelFile");
		if (file == null || file.getInputStream() == null || file.getSize()==0) {
			return null;
		}
		InputStream stream = file.getInputStream();
		ExcelUtil eu = new ExcelUtil();
		//获取可读的工作表对象，定位到要读取的excel文件
		Workbook workbook = eu.getWorkbook(stream);
		//读取此文件的第一个工作表，工作表序号从0开始。
		Sheet sheet = workbook.getSheet(0);
		
		//从第2行开始读,第一行为标题
		
		
		for (int i = 1; i < sheet.getRows(); i++) {
			String userCode = eu.getContents(sheet, 0, i);
			if(!StringUtil.isEmpty(userCode)){
				JmiMember miMember = this.jmiMemberManager.getJmiMember(userCode.trim());
				if (miMember != null) {
					System.out.println(miMember.getUserCode());
					map.put(miMember.getUserCode(), "");
				}
			}
		}

		return map;
		
	}


	public void setJmiRecommendRefManager(
			JmiRecommendRefManager jmiRecommendRefManager) {
		this.jmiRecommendRefManager = jmiRecommendRefManager;
	}


}