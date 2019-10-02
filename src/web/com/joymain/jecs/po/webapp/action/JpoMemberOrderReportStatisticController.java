package com.joymain.jecs.po.webapp.action;

import java.io.OutputStream;
import java.math.BigDecimal;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 会员辅销品订购列表
 * @author Alvin
 *
 */
public class JpoMemberOrderReportStatisticController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoMemberOrderReportStatisticController.class);
	private JdbcTemplate jdbcTemplate = null;
	private BdPeriodManager bdPeriodManager=null;
	
	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        RequestUtil.freshSession(request,"jpoMemberOrderReportStatistic1",60l);
		if("post".equals(request.getMethod().toLowerCase())){
			if(RequestUtil.saveOperationSession(request,"jpoMemberOrderReportStatistic1",60l)!=0){
    			return new ModelAndView("redirect:jpoMemberOrderReportStatistic.html");
    		}
			String formatedWeek = request.getParameter("formatedWeek");
			formatedWeek = WeekFormatUtil.getFormatWeek("f",formatedWeek);
			
			String formatedMonth = request.getParameter("formatedMonth");
			String formatedYear = request.getParameter("formatedYear");
	
	
			String startDate=request.getParameter("startDate");
			String endDate=request.getParameter("endDate");
			if((!StringUtil.isDate(startDate)||!StringUtil.isDate(endDate))&&StringUtil.isEmpty(formatedWeek)&&(!StringUtil.isInteger(formatedYear)||!StringUtil.isInteger(formatedMonth))){
				this.saveMessage(request, LocaleUtil.getLocalText("please.input.search.condition"));
				return new ModelAndView("po/jpoMemberOrderReportStatistic");
			}
			
			if(!StringUtil.isEmpty(formatedMonth)||!StringUtil.isEmpty(formatedYear)){
				List bdPeriodList=bdPeriodManager.getBdPeriodsByFmonth(formatedYear, formatedMonth);
				if(!bdPeriodList.isEmpty()){
					for (int i = 0; i < bdPeriodList.size(); i++) {
						BdPeriod curBdPeriod=(BdPeriod) bdPeriodList.get(i);
						if(i==0){
							startDate=DateUtil.getDateTime("yyyy-MM-dd HH:ss:mm",  curBdPeriod.getStartTime());
						}else if(i==(bdPeriodList.size()-1)){
							endDate=DateUtil.getDateTime("yyyy-MM-dd HH:ss:mm",  curBdPeriod.getEndTime());
						}
					}
				}else{
					this.saveMessage(request, LocaleUtil.getLocalText("please.input.search.condition"));
					return new ModelAndView("po/jpoMemberOrderReportStatistic");
				}
			}else{
				BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByFormatedWeek(formatedWeek);
				if(bdPeriod!=null){
					startDate=DateUtil.getDateTime("yyyy-MM-dd HH:ss:mm",  bdPeriod.getStartTime());
					endDate=DateUtil.getDateTime("yyyy-MM-dd HH:ss:mm",  bdPeriod.getEndTime());
				}else{
					startDate+=" 00:00:00";
					endDate+=" 23:59:59";
				}
			}
			
			String sql = "select product_name,product_category,sum(qty) qty,sum(price) price,sum(qty6) qty6,sum(price6)price6,sum(qty9)qty9,sum(price9)price9,sum(qty11)qty11,sum(price11)price11,sum(qty12)qty12,sum(price12)price12,sum(qty14)qty14,sum(price14)price14,"
					+ "sum(qty1)qty1,sum(price1)price1,sum(qty2)qty2,sum(price2)price2,sum(qty3)qty3,sum(price3)price3,sum(qty4)qty4,sum(price4)price4 from (select pnew.product_name,jp.product_category,sum(jmol.qty) qty,"
					+ "sum(jmol.price * jmol.qty) price,"
					+ "decode(jmo.order_type, 6, sum(jmol.qty), 0) qty6,"
					+ "decode(jmo.order_type, 6, sum(jmol.price * qty), 0) price6,"
					+ "decode(jmo.order_type, 9, sum(jmol.qty), 0) qty9,"
					+ "decode(jmo.order_type, 9, sum(jmol.price * qty), 0) price9,"
					+ "decode(jmo.order_type, 11, sum(jmol.qty), 0) qty11,"
					+ "decode(jmo.order_type, 11, sum(jmol.price * qty), 0) price11,"
					+ "decode(jmo.order_type, 12, sum(jmol.qty), 0) qty12,"
					+ "decode(jmo.order_type, 12, sum(jmol.price * qty), 0) price12,"
					+ "decode(jmo.order_type, 14, sum(jmol.qty), 0) qty14,"
					+ "decode(jmo.order_type, 14, sum(jmol.price * qty), 0) price14,"
					+ "decode(jmo.order_type, 1, sum(jmol.qty), 0) qty1,"
					+ "decode(jmo.order_type, 1, sum(jmol.price * qty), 0) price1,"
					+ "decode(jmo.order_type, 2, sum(jmol.qty), 0) qty2,"
					+ "decode(jmo.order_type, 2, sum(jmol.price * qty), 0) price2,"
					+ "decode(jmo.order_type, 3, sum(jmol.qty), 0) qty3,"
					+ "decode(jmo.order_type, 3, sum(jmol.price * qty), 0) price3,"
					+ "decode(jmo.order_type, 4, sum(jmol.qty), 0) qty4,"
					+ "decode(jmo.order_type, 4, sum(jmol.price * qty), 0) price4"
					+ " from jpo_member_order      jmo,"
					+ "jpo_member_order_list jmol,"
					+ "jpm_product_sale_team_type      jps,"
					+" jpm_product_sale_new        pnew,"
					+ "jpm_product           jp"
					+ " where jmo.mo_id = jmol.mo_id"
					+ " and product_id = jps.ptt_id"
					+" and jps.uni_no=pnew.uni_no"
					+ " and pnew.product_no = jp.product_no"
					+ " and jmo.status = '2'"
					+ " and jmo.check_time >=To_Date('"+startDate+"', 'yyyy-mm-dd hh24:mi:ss') "
					+ " and jmo.check_time <= To_Date('"+endDate+"', 'yyyy-mm-dd hh24:mi:ss')"
					+ " and jmo.company_code = '" + loginSysUser.getCompanyCode() + "'"
					+ " and jmo.order_type in (6, 9, 11, 12, 14, 1, 2, 3, 4)"
					+ " group by jp.product_category,pnew.product_name,jmo.order_type"
					+ ") group by product_name,product_category order by product_category,product_name";
			List jpoMemberOrderReportStatistic = this.jdbcTemplate.queryForList(sql);

//	    	生成excel文件
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=jpoMemberOrderReportStatistic.xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			ExcelUtil eu = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			//在此创建的新excel文件创建一工作表
			WritableSheet wsheet = wwb.createSheet("Sheet1", 0);
			eu.addString(wsheet, 0, 1, "");
			eu.addString(wsheet, 1, 1, "");
			eu.addString(wsheet, 2, 1, LocaleUtil.getLocalText("jpoMemberOrder.huizong"));
			eu.addString(wsheet, 3, 1, "");
			eu.addString(wsheet, 4, 1, LocaleUtil.getLocalText("ordertype.6"));
			eu.addString(wsheet, 5, 1, "");
			eu.addString(wsheet, 6, 1, LocaleUtil.getLocalText("ordertype.9"));
			eu.addString(wsheet, 7, 1, "");
			eu.addString(wsheet, 8, 1, LocaleUtil.getLocalText("ordertype.11"));
			eu.addString(wsheet, 9, 1, "");
			eu.addString(wsheet, 10, 1, LocaleUtil.getLocalText("ordertype.12"));
			eu.addString(wsheet,11, 1, "");
			eu.addString(wsheet,12, 1, LocaleUtil.getLocalText("ordertype.14"));
			eu.addString(wsheet,13, 1, "");
			eu.addString(wsheet,14, 1, LocaleUtil.getLocalText("ordertype.1"));
			eu.addString(wsheet,15, 1, "");
			eu.addString(wsheet,16, 1, LocaleUtil.getLocalText("ordertype.2"));
			eu.addString(wsheet,17, 1, "");
			eu.addString(wsheet,18, 1, LocaleUtil.getLocalText("ordertype.3"));
			eu.addString(wsheet,19, 1, "");
			eu.addString(wsheet,20, 1, LocaleUtil.getLocalText("ordertype.4"));
			eu.addString(wsheet,21, 1, "");
			eu.addString(wsheet, 0, 2, LocaleUtil.getLocalText("piProduct.categoryNo"));
			eu.addString(wsheet, 1, 2, LocaleUtil.getLocalText("pmProduct.productName"));
			eu.addString(wsheet, 2, 2, LocaleUtil.getLocalText("pd.qty"));
			eu.addString(wsheet, 3, 2, LocaleUtil.getLocalText("pd.price"));
			eu.addString(wsheet, 4, 2, LocaleUtil.getLocalText("pd.qty"));
			eu.addString(wsheet, 5, 2, LocaleUtil.getLocalText("pd.price"));
			eu.addString(wsheet, 6, 2, LocaleUtil.getLocalText("pd.qty"));
			eu.addString(wsheet, 7, 2, LocaleUtil.getLocalText("pd.price"));
			eu.addString(wsheet, 8, 2, LocaleUtil.getLocalText("pd.qty"));
			eu.addString(wsheet, 9, 2, LocaleUtil.getLocalText("pd.price"));
			eu.addString(wsheet, 10, 2, LocaleUtil.getLocalText("pd.qty"));
			eu.addString(wsheet,11, 2, LocaleUtil.getLocalText("pd.price"));
			eu.addString(wsheet,12, 2, LocaleUtil.getLocalText("pd.qty"));
			eu.addString(wsheet,13, 2, LocaleUtil.getLocalText("pd.price"));
			eu.addString(wsheet,14, 2, LocaleUtil.getLocalText("pd.qty"));
			eu.addString(wsheet,15, 2, LocaleUtil.getLocalText("pd.price"));
			eu.addString(wsheet,16, 2, LocaleUtil.getLocalText("pd.qty"));
			eu.addString(wsheet,17, 2, LocaleUtil.getLocalText("pd.price"));
			eu.addString(wsheet,18, 2, LocaleUtil.getLocalText("pd.qty"));
			eu.addString(wsheet,19, 2, LocaleUtil.getLocalText("pd.price"));
			eu.addString(wsheet,20, 2, LocaleUtil.getLocalText("pd.qty"));
			eu.addString(wsheet,21, 2, LocaleUtil.getLocalText("pd.price"));
			for(int m = 0 ; m<jpoMemberOrderReportStatistic.size();m++){
				Map map = (Map)jpoMemberOrderReportStatistic.get(m);
				Map productCategory = ListUtil.getListOptions(loginSysUser.getCompanyCode(), "pmproduct.productcategory");
				try{
					eu.addString(wsheet, 0, m+3, LocaleUtil.getLocalText(productCategory.get(map.get("product_category").toString()).toString()));
				}catch(Exception e){
					eu.addString(wsheet, 0, m+3, "");
				}
				eu.addString(wsheet, 1, m+3, map.get("product_name").toString());
				eu.addNumber(wsheet, 2, m+3, new BigDecimal(map.get("qty").toString()).doubleValue());
				eu.addNumber(wsheet, 3, m+3, new BigDecimal(map.get("price").toString()).doubleValue());
				eu.addNumber(wsheet, 4, m+3, new BigDecimal(map.get("qty6").toString()).doubleValue());
				eu.addNumber(wsheet, 5, m+3, new BigDecimal(map.get("price6").toString()).doubleValue());
				eu.addNumber(wsheet, 6, m+3, new BigDecimal(map.get("qty9").toString()).doubleValue());
				eu.addNumber(wsheet, 7, m+3, new BigDecimal(map.get("price9").toString()).doubleValue());
				eu.addNumber(wsheet, 8, m+3, new BigDecimal(map.get("qty11").toString()).doubleValue());
				eu.addNumber(wsheet, 9, m+3, new BigDecimal(map.get("price11").toString()).doubleValue());
				eu.addNumber(wsheet, 10, m+3, new BigDecimal(map.get("qty12").toString()).doubleValue());
				eu.addNumber(wsheet,11, m+3, new BigDecimal(map.get("price12").toString()).doubleValue());
				eu.addNumber(wsheet,12, m+3, new BigDecimal(map.get("qty14").toString()).doubleValue());
				eu.addNumber(wsheet,13, m+3, new BigDecimal(map.get("price14").toString()).doubleValue());
				eu.addNumber(wsheet,14, m+3, new BigDecimal(map.get("qty1").toString()).doubleValue());
				eu.addNumber(wsheet,15, m+3, new BigDecimal(map.get("price1").toString()).doubleValue());
				eu.addNumber(wsheet,16, m+3, new BigDecimal(map.get("qty2").toString()).doubleValue());
				eu.addNumber(wsheet,17, m+3, new BigDecimal(map.get("price2").toString()).doubleValue());
				eu.addNumber(wsheet,18, m+3, new BigDecimal(map.get("qty3").toString()).doubleValue());
				eu.addNumber(wsheet,19, m+3, new BigDecimal(map.get("price3").toString()).doubleValue());
				eu.addNumber(wsheet,20, m+3, new BigDecimal(map.get("qty4").toString()).doubleValue());
				eu.addNumber(wsheet,21, m+3, new BigDecimal(map.get("price4").toString()).doubleValue());
			}
			eu.writeExcel(wwb);
			eu.closeWritableWorkbook(wwb);
			os.close();
	        return null;
		}
        return new ModelAndView("po/jpoMemberOrderReportStatistic");
    }

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
