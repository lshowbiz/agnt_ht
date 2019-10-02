package com.joymain.jecs.fi.webapp.action;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.fi.model.FiBankbookJournal;
import com.joymain.jecs.fi.service.FiBankbookJournalManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 基金使用、余额表（按财月分）
 * @author Alvin
 *
 */
public class FiBankbookFMonthReportController extends BaseFormController {
	private FiBankbookJournalManager fiBankbookJournalManager = null;
	private AlCompanyManager alCompanyManager = null;
	private JdbcTemplate jdbcTemplate = null;

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public void setFiBankbookJournalManager(FiBankbookJournalManager fiBankbookJournalManager) {
		this.fiBankbookJournalManager = fiBankbookJournalManager;
	}

	public FiBankbookFMonthReportController() {
		setCommandName("fiBankbookJournal");
		setCommandClass(FiBankbookJournal.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
        RequestUtil.freshSession(request,"fiBankbookFMonthReportJJ",10l);
		FiBankbookJournal fiBankbookJournal = new FiBankbookJournal();
		
		List alCompanys = this.alCompanyManager.getAlCompanysExceptAA();
		request.setAttribute("alCompanys", alCompanys);

		return fiBankbookJournal;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
		if("C".equals(loginSysUser.getUserType())){
    		if(RequestUtil.saveOperationSession(request,"fiBankbookFMonthReportJJ",10l)!=0){
    			return new ModelAndView("redirect:fiBankbookFMonthReport.html");
    		}
    	}else if("M".equals(loginSysUser.getUserType())){
    		if(RequestUtil.saveOperationSession(request,"fiBankbookFMonthReportJJ",1l)!=0){
    			return new ModelAndView("redirect:fiBankbookFMonthReport.html");
    		}
    	}
		
		String companyCode=SessionLogin.getLoginUser(request).getCompanyCode();
		if(!StringUtils.isEmpty(request.getParameter("companyCode"))){
			companyCode=request.getParameter("companyCode");
		}
		
		String bankbookType = request.getParameter("bankbookType");
		
		String fyear = request.getParameter("fyear");
		
		String sql = "select jp.f_year || lpad(jp.f_month,2,0) fmonth, sum(money) smoney";
		sql += "  from fi_bankbook_journal fbj, jbd_period jp";
		sql += " where fbj.deal_type = 'D'";
		sql += "   and fbj.deal_date >= jp.start_time";
		sql += "   and fbj.deal_date < jp.end_time";
		sql += "   and jp.f_year = '" + fyear + "'";
		sql += " and fbj.company_code = '" + companyCode + "'";
		sql += " and fbj.bankbook_type = '" + bankbookType + "'";
		sql += " group by jp.f_year || lpad(jp.f_month,2,0) order by jp.f_year || lpad(jp.f_month,2,0)";
		List fiMoneyList = this.jdbcTemplate.queryForList(sql);
		Map fiBalanceMap = new HashMap();
		for(int i = 0 ; i < fiMoneyList.size() ; i++){
			Map fiMap = (Map)fiMoneyList.get(i);
			sql = "select sum(balance) sbalance from fi_bankbook_journal where journal_id in(";
			sql += " select max(journal_id)";
			sql += "  from fi_bankbook_journal fbj, jbd_period jp";
			sql += " where fbj.deal_date < jp.end_time";
			sql += "   and fbj.bankbook_type = '" + bankbookType + "'";
			sql += "   and fbj.company_code = '" + companyCode + "'";
			sql += "   and jp.f_year || lpad(jp.f_month,2,0) = '" + fiMap.get("fmonth").toString() + "' group by user_code)";
			List fiBalanceListTmp = this.jdbcTemplate.queryForList(sql);
			Map fiBalanceMapTmp = (Map)fiBalanceListTmp.get(0);
			fiBalanceMap.put(fiMap.get("fmonth").toString(), fiBalanceMapTmp.get("sbalance").toString());
		}

//    	生成excel文件
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-Disposition", "attachment; filename=fiBankbookFMonthReport.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=30" ); 
		OutputStream os = response.getOutputStream();
		ExcelUtil eu = new ExcelUtil();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		//在此创建的新excel文件创建一工作表
		WritableSheet wsheet = wwb.createSheet("Sheet1", 0);
		for(int i = 0 ; i < fiMoneyList.size() ; i++){
			Map fiMap = (Map)fiMoneyList.get(i);
			eu.addString(wsheet, 0 + i * 2, 0, fiMap.get("fmonth").toString());
			eu.addString(wsheet, 0 + i * 2+1, 0, "财月");
			eu.addString(wsheet, 0 + i * 2, 1, fiMap.get("smoney").toString());
			eu.addString(wsheet, 0 + i * 2 + 1, 1, fiBalanceMap.get(fiMap.get("fmonth").toString()).toString());
		}
		eu.writeExcel(wwb);
		eu.closeWritableWorkbook(wwb);
		os.close();
        return null;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}