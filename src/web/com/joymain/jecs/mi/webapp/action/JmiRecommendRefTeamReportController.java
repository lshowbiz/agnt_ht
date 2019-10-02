package com.joymain.jecs.mi.webapp.action;

import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.model.JbdMemberLinkCalcHist;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.pr.service.JprRefundManager;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 
 * 
 * 
 */
public class JmiRecommendRefTeamReportController extends BaseFormController {
	private transient final Log log = LogFactory.getLog(JbdMemberLinkCalcHist.class);
	private AlCompanyManager alCompanyManager = null;
	private BdPeriodManager bdPeriodManager=null;
    private AlCountryManager alCountryManager;
    private AlStateProvinceManager alStateProvinceManager;
    private AlCityManager alCityManager;
    private AlDistrictManager alDistrictManager;
    private JmiMemberManager jmiMemberManager;
    private JpoMemberOrderManager jpoMemberOrderManager;
    private JprRefundManager jprRefundManager ;
    
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}


	public void setAlCityManager(AlCityManager alCityManager) {
		this.alCityManager = alCityManager;
	}


	public void setAlDistrictManager(AlDistrictManager alDistrictManager) {
		this.alDistrictManager = alDistrictManager;
	}


	public void setAlStateProvinceManager(
			AlStateProvinceManager alStateProvinceManager) {
		this.alStateProvinceManager = alStateProvinceManager;
	}


	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}


	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
	private JdbcTemplate jdbcTemplate = null;
	private ExcelUtil eu = null;
	private WritableSheet wsheet = null;
	
	private HttpServletRequest innerRequest;
	private Map cardTypeMap=null;
	private Map honorStarMap=null;
	private Map honorGradeMap=null;
	private Map storeTypeMap=null;
	private Map memberLevelMap=null;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public JmiRecommendRefTeamReportController() {
		setCommandName("bdPeriod");
		setCommandClass(BdPeriod.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		List alCompanys = this.alCompanyManager.getAlCompanysExceptAA();
		request.setAttribute("alCompanys", alCompanys);
		

		return new BdPeriod();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		if ("post".equalsIgnoreCase(request.getMethod()) && "jmiRecommendRefTeamReport".equalsIgnoreCase(request.getParameter("strAction"))) {		

			String userCode = request.getParameter("userCode");
			JmiMember jmiMember=jmiMemberManager.getJmiMember(userCode);

			String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();

			String startDate=request.getParameter("startDate");
			String endDate=request.getParameter("endDate");

			if(jmiMember==null||(!"AA".equals(companyCode)&&!jmiMember.getCompanyCode().equals(companyCode))){
				this.saveMessage(request, this.getText("miMember.notFound"));
				return showForm(request, response, errors);
			}
			
			if(!StringUtil.isDate(startDate)||!StringUtil.isDate(endDate)){
				this.saveMessage(request, this.getText("please.input.search.condition"));
				return showForm(request, response, errors);
			}
			
			startDate+=" 00:00:00";
			endDate+=" 23:59:59";
			
			this.innerRequest=request;
			
			BdPeriod bdPeriod= bdPeriodManager.getBdPeriodByTime(DateUtil.convertStringToDate(endDate), null);


			String bdWeek= bdPeriod.getWyear()+""+ (bdPeriod.getWweek()<10?"0" + bdPeriod.getWweek():bdPeriod.getWweek());
			
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=recommendRef_team_"+userCode+".xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" );
			
			OutputStream os = response.getOutputStream();
			eu = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			//在此创建的新excel文件创建一工作表 
			wsheet = wwb.createSheet("Sheet1", 0);

			//加入期别时间段显示
			eu.addString(wsheet, 0, 0, startDate+"~"+endDate);
			//标题
			int i=0;

			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.memberNo"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.cardType"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.cardType"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.isstore"));
			
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("jbdMemberLinkCalcHist.honorStar"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("jbdMemberLinkCalcHist.honorGrade"));
			

 
			cardTypeMap=ListUtil.getListOptions(companyCode, "bd.cardtype");
			memberLevelMap=ListUtil.getListOptions(companyCode, "member.level");
			

			storeTypeMap=ListUtil.getListOptions(companyCode, "store.type");
			
			honorStarMap=ListUtil.getListOptions(companyCode, "honor.star.zero");

			honorGradeMap=ListUtil.getListOptions(companyCode, "honor.grade.zero");
			
			String treeIndex=jmiMember.getJmiRecommendRef().getTreeIndex();
			
			String sql="select m.user_code, m.card_type,m.member_level, m.isstore, h.honor_star, h.honor_grade from jmi_member m " +
					" left join jbd_member_link_calc_hist h on m.user_code = h.user_code and h.w_week = "+bdWeek+" " +
							" where m.user_code in (select r.user_code from jmi_recommend_ref r where r.tree_index like '"+treeIndex+"%') " +
							" and m.check_date >= To_Date('"+startDate+"', 'yyyy-mm-dd hh24:mi:ss') " +
							" and m.check_date <= To_Date('"+endDate+"', 'yyyy-mm-dd hh24:mi:ss') ";
				if(!"AA".equals(companyCode)){
					sql+=" and m.company_code='"+companyCode+"'";
				}
			this.jdbcTemplate.query(sql, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException {
					try {
						int kk = 2;
						while (rs.next()) {
							String user_code = rs.getString("user_code");
							String card_type = rs.getString("card_type");
							String isstore = rs.getString("isstore");
							String honor_star = rs.getString("honor_star");
							String honor_grade = rs.getString("honor_grade");

							String member_level = rs.getString("member_level");

							
							int index=0;
			
							eu.addString(wsheet, index++, kk, user_code);
							
							eu.addString(wsheet, index++, kk, cardTypeMap.get(card_type)==null?"":LocaleUtil.getLocalText(cardTypeMap.get(card_type).toString()));
							eu.addString(wsheet, index++, kk, memberLevelMap.get(member_level)==null?"":LocaleUtil.getLocalText(memberLevelMap.get(member_level).toString()));
							
							eu.addString(wsheet, index++, kk, storeTypeMap.get(isstore)==null?"":LocaleUtil.getLocalText(storeTypeMap.get(isstore).toString()));
							
							if(!"0".equals(honor_star)){
								eu.addString(wsheet, index++, kk, honorStarMap.get(honor_star)==null?"":LocaleUtil.getLocalText(honorStarMap.get(honor_star).toString()));
							}else{
								index++;
							}
							if(!"0".equals(honor_grade)){
								eu.addString(wsheet, index++, kk, honorGradeMap.get(honor_grade)==null?"":LocaleUtil.getLocalText(honorGradeMap.get(honor_grade).toString()));
							}else{
								index++;
							}
							

							kk++;
						} 
						

			
	
					} catch (Exception e) {
						e.printStackTrace();
						 throw new AppException(e);
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


	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}


	public void setJprRefundManager(JprRefundManager jprRefundManager) {
		this.jprRefundManager = jprRefundManager;
	}



}