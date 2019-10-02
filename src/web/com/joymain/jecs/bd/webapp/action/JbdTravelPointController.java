package com.joymain.jecs.bd.webapp.action;

import java.io.IOException;
import java.io.OutputStream;
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

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.service.JbdTravelPointManager;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;


public class JbdTravelPointController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdTravelPointController.class);
    private JbdTravelPointManager jbdTravelPointManager = null;
    private JdbcTemplate jdbcTemplate;
    private ExcelUtil eu = null;
	private WritableSheet wsheet = null;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
    public void setJbdTravelPointManager(JbdTravelPointManager jbdTravelPointManager) {
        this.jbdTravelPointManager = jbdTravelPointManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdTravelPointListTable",request, 20);
        

        String userCode=request.getParameter("userCode");
        String wmonth = crm.getString("wmonth");
        String exportFlag = crm.getString("exportFlag");
        List jbdTravelPoints = null;
        if("export".equals(exportFlag)){
        	if(StringUtil.isEmpty(wmonth)){
        		request.setAttribute("jbdTravelPointListTable_totalRows", pager.getTotalObjects());
        		return new ModelAndView("bd/jbdTravelPointList", Constants.JBDTRAVELPOINT_LIST, jbdTravelPoints);
        	}
        	Integer fm_wmonth = Integer.valueOf(wmonth.substring(4, 6));
        	Integer fyear = Integer.valueOf(wmonth.substring(0, 4));
        	List list = this.getReportData(userCode,fyear,fm_wmonth);
        	this.exportExcel(response, wmonth, list);
        }else{
	        if(!StringUtil.isEmpty(userCode) ){
	           jbdTravelPoints = jbdTravelPointManager.getJbdTravelPointsByCrm(crm,pager);
	        }
	        request.setAttribute("jbdTravelPointListTable_totalRows", pager.getTotalObjects());
        }
        return new ModelAndView("bd/jbdTravelPointList", Constants.JBDTRAVELPOINT_LIST, jbdTravelPoints);
    }
    
    /**
     * @Description excel列头
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
			eu.addString(wsheet, i, row, cs[i]);//工作表对象，列，行，值
		}
    }
    
    /**
	 * 导出旅游积分数据到Excel
	 * @param response
	 * @param version
	 * @param bdlist
	 * @param map
	 * @throws IOException
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private void exportExcel(HttpServletResponse response, String wmonth,
			List bdlist) throws IOException, Exception {
		//生成excel文件
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-Disposition", "attachment; filename=jbdTravelPoint_"+wmonth+".xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=30" ); 
		OutputStream os = response.getOutputStream();
		eu = new ExcelUtil();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		//在此创建的新excel文件创建一工作表 
		wsheet = wwb.createSheet("Sheet1", 0);
		
		excelTopColumn(eu,wsheet,0,"会员编号,会员姓名,当财月新增旅游积分,截止目前累计旅游积分");
		
		int kk = 1;// 填充内容起始行
		if(bdlist!=null&&!bdlist.isEmpty()){
			for (int i = 0; i < bdlist.size(); i++) {
				Map cmap=(Map) bdlist.get(i);
				eu.addString(wsheet, 0, kk, String.valueOf(cmap.get("user_code")));
				eu.addString(wsheet, 1, kk, String.valueOf(cmap.get("user_name")));
				eu.addString(wsheet, 2, kk, cmap.get("curr_total")==null?"":String.valueOf(cmap.get("curr_total")));
				eu.addString(wsheet, 3, kk, cmap.get("total")==null?"":String.valueOf(cmap.get("total")));
				kk++;
			}
		}
		
		eu.writeExcel(wwb);
		eu.closeWritableWorkbook(wwb);
		os.close();
	}
	
	/**
	 * 获取旅游积分数据
	 * @param userCode
	 * @param fyear
	 * @param wmonth
	 * @return List
	 */
	@SuppressWarnings("rawtypes")
	public List getReportData(String userCode,Integer fyear,Integer wmonth){
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select t.user_code,u.user_name,");
		sbSql.append("(select sum(USE_POINT) from Jbd_Travel_Point_Log_ALL where user_code=t.user_code ");
		sbSql.append("and DEAL_TYPE='A' and F_YEAR=").append(fyear).append(" and W_MONTH=").append(wmonth).append(" group by F_YEAR,W_MONTH,USER_CODE) as curr_total,");
		sbSql.append("t.total from Jbd_Travel_Point_ALL t,jsys_user u where t.user_code=u.user_code");
		List list = jdbcTemplate.queryForList(sbSql.toString(), new Object[]{});
		return list;
	} 
}
