package com.joymain.jecs.mi.webapp.action;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.mi.model.JmiAssure;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseFormController;

public class JmiAssureReportController extends BaseFormController {
	
	private JdbcTemplate jdbcTemplate = null;
	private ExcelUtil eu = null;
	private WritableSheet wsheet = null;
	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}    
	public JmiAssureReportController() {
		setCommandName("jmiAssureReport");
		setCommandClass(JmiAssure.class);
	}
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

//		System.out.println("---------------123--------");
		JmiAssure jmiAssure = new JmiAssure();
		return jmiAssure;
	}
	

	public ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception {
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		String assure_type = request.getParameter("assure_type");
		//生成excel文件
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-Disposition", "attachment; filename=jmiAssureXBWLReport.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=30" ); 
		OutputStream os = response.getOutputStream();
		eu = new ExcelUtil();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		//在此创建的新excel文件创建一工作表 
		wsheet = wwb.createSheet("Sheet1", 0);
		
		     
//		3 下不为例担保     4 不退货担保  
		if("3".equals(assure_type)) {
			//下不为例担保报表

			String startTime = crm.getString("startTime", "");
			String endTime = crm.getString("endTime", "");
			String userCode = crm.getString("userCode", "");
			
			//设置列头
			excelTopColumn(eu,wsheet,0,"会员编号,会员名称,提交次数");
			
			StringBuffer sb = new StringBuffer("select b.user_code,b.user_name,count(a.user_code) as tjcs from JMI_ASSURE a left join jsys_user b on a.user_code=b.user_code ");
			sb.append(" where 1=1 and assure_type ='"+assure_type+"' ");
			
			if (!StringUtils.isEmpty(userCode)) {
				sb.append(" and b.user_code = '"+userCode+"' ");
			}
			if (!StringUtils.isEmpty(startTime)) {
				sb.append(" and a.start_time>=to_date('"+startTime+"','yyyy-MM-dd') ");
			}
			if (!StringUtils.isEmpty(endTime)) {
				sb.append(" and a.end_time<=to_date('"+endTime+"','yyyy-MM-dd') ");
			}
			
			sb.append(" group by b.user_code,b.user_name ");
			
			
			log.info("sb.toString==========="+sb.toString());
			this.jdbcTemplate.query(sb.toString(), new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException {
					try {
						int kk = 1;
						while (rs.next()) {
							int index=0;
							eu.addString(wsheet, index++, kk, rs.getString("USER_CODE"));
							eu.addString(wsheet, index++, kk, rs.getString("USER_NAME"));
							eu.addString(wsheet, index++, kk, rs.getString("TJCS"));
			
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
			
		}else if("4".equals(assure_type)) {
			String userCode = crm.getString("userCode", "");
			
			//设置列头
			excelTopColumn(eu,wsheet,0,"会员编号,会员名称,提交次数,总金额,基金总额,抵扣积分,总PV");
			
			StringBuffer sb = new StringBuffer("select b.user_code,b.user_name,count(a.user_code) as tjcs from JMI_ASSURE a left join jsys_user b on a.user_code=b.user_code ");
			sb.append(" where 1=1 and assure_type ='"+assure_type+"' ");
			
			if (!StringUtils.isEmpty(userCode)) {
				sb.append(" and b.user_code = '"+userCode+"' ");
			}
			
			sb.append(" group by b.user_code,b.user_name ");
			
			
			List<Map<String,Object>>  list = this.jdbcTemplate.queryForList(sb.toString());
			if(null!=list && list.size()>0){
				int kk = 1;
				for(Map<String,Object> map : list){
					int index=0;
					String ocodes = ""; 
					String sc = String.valueOf(map.get("USER_CODE"));
					eu.addString(wsheet, index++, kk, sc);
					eu.addString(wsheet, index++, kk, String.valueOf(map.get("USER_NAME")));
					eu.addString(wsheet, index++, kk, String.valueOf(map.get("TJCS").toString()));
					
					StringBuffer sb1 = new StringBuffer("select ASSURE_ORDER_CODE from JMI_ASSURE where USER_CODE='"+sc+"' and assure_type ='"+assure_type+"' ");
					List<Map<String,String>>  list1 = this.jdbcTemplate.queryForList(sb1.toString());
					if(null!=list1 && list1.size()>0){
						for(Map<String,String> map1 : list1){
							String aoc = map1.get("ASSURE_ORDER_CODE");
							if(!MeteorUtil.isBlank(aoc)){
								String [] aocs = aoc.split(",");
								if(null!=aocs && aocs.length>0){
									for (String o1:aocs){
										ocodes += "'"+o1+"',";
									}
								}
							}
							
						}
					}
					
					if(!MeteorUtil.isBlank(ocodes)){
						ocodes = ocodes.substring(0,ocodes.length()-1);
						
						StringBuffer sb2 = new StringBuffer("  select sum(AMOUNT) as AMOUNT,sum(PV_AMT)as PV_AMT,sum(DISCOUNT_AMOUNT) as DISCOUNT_AMOUNT,sum(JJ_AMOUNT) as JJ_AMOUNT  from jpo_member_order where MEMBER_ORDER_NO in("+ocodes+") ");
						List<Map<String,Object>>  list2 = this.jdbcTemplate.queryForList(sb2.toString());
						if(null!=list2 && list2.size()>0){
							for(Map<String,Object> map2 : list2){
//								总金额,基金总额,抵扣积分,PV
								eu.addString(wsheet, index++, kk, String.valueOf(map2.get("AMOUNT").toString()));
								eu.addString(wsheet, index++, kk, String.valueOf(map2.get("JJ_AMOUNT").toString()));
								eu.addString(wsheet, index++, kk, String.valueOf(map2.get("DISCOUNT_AMOUNT").toString()));
								eu.addString(wsheet, index++, kk, String.valueOf(map2.get("PV_AMT").toString()));
								
							}
						}
					}
					kk ++;
				}
			}
			
			eu.writeExcel(wwb);
			eu.closeWritableWorkbook(wwb);
			os.close();

			return null;
		}
		return null;
	}
	
	
	/**
     * @Description excel列头
     * @author houxyu
     * @date 2016-3-28
     * @param eu
     * @param wsheet
     * @param row  列头填充到第几行
     */
    public void excelTopColumn(ExcelUtil eu,WritableSheet wsheet,int row,String columns) throws Exception{
    	String [] cs = null;
		if(!MeteorUtil.isBlank(columns)){
			cs = columns.split(",");
		}
		for(int i=0;i<cs.length;i++){
			//列头  后续改为公共方法。
			eu.addString(wsheet, i, row, cs[i]);//工作表对象，列，行，值
		}
    }

}
