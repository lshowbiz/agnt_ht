package com.joymain.jecs.bd.webapp.action;

import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.model.JbdMemberLinkCalcHist;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 奖金报表B
 * 
 * 
 */
public class JbdPinTitleReportController extends BaseFormController {
	private transient final Log log = LogFactory.getLog(JbdMemberLinkCalcHist.class);
	private AlCompanyManager alCompanyManager = null;
	private BdPeriodManager bdPeriodManager=null;
    private AlCountryManager alCountryManager;
	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}


	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
	private JdbcTemplate jdbcTemplate = null;
	private ExcelUtil eu = null;
	private WritableSheet wsheet = null;
	
	
	private int progressCurrentCount;
	private String company=null;
	private HttpServletRequest innerRequest;
	private AlCountry alCountry = new AlCountry();
	private Map memberTypeMap=null;
	private Map storeTypeMap=null;
	private Map identityTypeMap=null;
	private Map freezeStatusMap=null;
	private Map passStarMap=null;

	private Map cardTypeMap=null;
	//改为jdbcTemplateReport
	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public JbdPinTitleReportController() {
		setCommandName("bdPeriod");
		setCommandClass(BdPeriod.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		List alCompanys = this.alCompanyManager.getAlCompanysExceptAA();
		request.setAttribute("alCompanys", alCompanys);

        RequestUtil.freshSession(request,"jbdPinTitleReport", Constants.COMPANY_TIME);

		return new BdPeriod();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		if ("post".equalsIgnoreCase(request.getMethod()) && "jbdPinTitleReport".equalsIgnoreCase(request.getParameter("strAction"))) {
			

			if(RequestUtil.saveOperationSession(request, "jbdPinTitleReport", Constants.COMPANY_TIME)!=0){
	       		  return new ModelAndView("redirect:jbdPinTitleReport.html");
	       	 }
			
			this.innerRequest=request;
			String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
			if (SessionLogin.getLoginUser(request).getIsManager()) {
				companyCode = request.getParameter("companyCode");
			}
			company=companyCode;
			String formatedWeek = request.getParameter("formatedWeek");
			//生成excel文件
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=PinTitleReport_"+formatedWeek+".xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			eu = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);

			formatedWeek=WeekFormatUtil.getFormatWeek("f",formatedWeek);
			BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByFormatedWeek(formatedWeek);
			
			//在此创建的新excel文件创建一工作表 
			wsheet = wwb.createSheet("Sheet1", 0);
			//加入期别时间段显示
			eu.addString(wsheet, 0, 0, WeekFormatUtil.getFormatWeek("w",formatedWeek));
			eu.addString(wsheet, 1, 0, bdPeriod.getStartTime()+" - "+bdPeriod.getEndTime());
			//标题
			int i=0;
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.memberNo"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdCalculatingSubDetail.name"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.petName"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("member.memberType"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("jbdMemberLinkCalcHist.passStar"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("busi.poMemberOrder.company"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.postalcode"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.titleRemark"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.papernumber"));
			
			

			memberTypeMap=ListUtil.getListOptions(companyCode, "membertype");
			passStarMap=ListUtil.getListOptions(companyCode, "pass.star.zero");
			
			String sql = "select m.title_remark,c.user_code, c.Name, c.pet_name, c.member_type, c.Pass_Star, c.company_code,m.postalcode,m.papernumber From v_Jbd_Member_Link_Calc c,jmi_member  m" +
					" Where c.Pass_Star > 20 " +
					"And  c.Month_Area_Total_Pv >= 2200 and c.w_week="+formatedWeek+"  and c.User_Code=m.user_code Order By c.member_type";
			
			
			this.jdbcTemplate.query(sql, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException {
					try {
						int kk = 2;
						while (rs.next()) {
							
							String user_code = rs.getString("user_code");
							String name = rs.getString("name");
							String pet_name = rs.getString("pet_name");
							
							String member_type = rs.getString("member_type");
							if(!StringUtil.isEmpty(member_type)){
								member_type=member_type.trim();
							}
							String pass_star = rs.getString("pass_star");
							String company_code = rs.getString("company_code");
							String postalcode = rs.getString("postalcode");
							String title_remark = rs.getString("title_remark");

							String papernumber = rs.getString("papernumber");

							
							int index=0;
							
							eu.addString(wsheet, index++, kk, user_code);
							
							eu.addString(wsheet, index++, kk, name);
							eu.addString(wsheet, index++, kk, pet_name);

							eu.addString(wsheet, index++, kk, memberTypeMap.get(member_type)==null?"":LocaleUtil.getLocalText(memberTypeMap.get(member_type).toString()));

							eu.addString(wsheet, index++, kk, passStarMap.get(pass_star)==null?"":LocaleUtil.getLocalText(passStarMap.get(pass_star).toString()));

							eu.addString(wsheet, index++, kk, company_code);
							eu.addString(wsheet, index++, kk, postalcode);
							eu.addString(wsheet, index++, kk, title_remark);
							eu.addString(wsheet, index++, kk, papernumber);
							kk++;
						} 

			
	
					} catch (Exception e) {
						e.printStackTrace();
					}finally {  
						JdbcUtils.closeResultSet(rs);
					}
					return null;
				}
			});
			eu.writeExcel(wwb);
			eu.closeWritableWorkbook(wwb);
			os.close();

			return null;
		}

		return new ModelAndView(getSuccessView());
	}



}