package com.joymain.jecs.fi.webapp.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ibm.icu.text.SimpleDateFormat;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.fi.service.JfiInvoiceManager;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.webapp.action.BaseController;

/**
 * 保证金和发票报表
 * @author xiaoman001
 *
 */

public class JfiInvoiceDepositExportController  extends BaseController
implements Controller  {
	
	private JfiInvoiceManager jfiInvoiceManager;

	private BdPeriodManager bdPeriodManager;
	
	
	public BdPeriodManager getBdPeriodManager() {
		return bdPeriodManager;
	}

	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public JfiInvoiceManager getJfiInvoiceManager() {
		return jfiInvoiceManager;
	}

	public void setJfiInvoiceManager(JfiInvoiceManager jfiInvoiceManager) {
		this.jfiInvoiceManager = jfiInvoiceManager;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strAction = request.getParameter("strAction");
		String wyear = request.getParameter("wyear");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String userCode = request.getParameter("userCode");
		
		String wweekS = "";
		String wweekE = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ss");
		if(null != startDate && !"".equals(startDate) && null != endDate && !"".equals(endDate)){
			Date sDate = sdf.parse(startDate);
			Date eDate = sdf.parse(endDate);
			BdPeriod bdPeriod = bdPeriodManager.getBdPeriodByTime(sDate, null);
			BdPeriod bdPeriod2 = bdPeriodManager.getBdPeriodByTime(eDate, null);
			
			wweekS = bdPeriod.getFyear()+(bdPeriod.getFweek()<10?"0" + bdPeriod.getFweek():bdPeriod.getFweek()).toString();
			wweekE = bdPeriod2.getFyear()+(bdPeriod2.getFweek()<10?"0" + bdPeriod2.getFweek():bdPeriod2.getFweek()).toString();
		}
		
		List list = new ArrayList();
		CommonRecord crm = initCommonRecord(request);
		
		if("Y".equals(request.getParameter("flag"))){
			
			crm.addField("wweekS", WeekFormatUtil.getFormatWeek("f", wweekS));
			crm.addField("wweekE", WeekFormatUtil.getFormatWeek("f", wweekE));
			crm.addField("wyear", wyear);
			
			//发票
			if("invoiceExport".equals(strAction)){
				
				crm.addField("userCode", userCode);
				
				//查询数据
				list = jfiInvoiceManager.getJfiInvoiceExportByCrm(crm);
				
				//生成报表
				response.setContentType("application/vnd.ms-excel");
				response.addHeader("Content-Disposition", "attachment; filename=InvoicesExport_"+wweekS+"-"+wweekE+".xls");//设置报表名称
				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control", "max-age=30" ); 
				OutputStream os = response.getOutputStream();
				ExcelUtil excelUtil = new ExcelUtil();
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				WritableSheet wsheet = wwb.createSheet("sheet1", 0);
				//加入期别时间段显示
				//标题
				int m=0;
				excelUtil.addString(wsheet, m++, 1, "建立日期(保证金)");	
				excelUtil.addString(wsheet, m++, 1, "建立日期(已提交发票)");
				excelUtil.addString(wsheet, m++, 1, "建立日期(合规发票)");
				excelUtil.addString(wsheet, m++, 1, "财年");
				excelUtil.addString(wsheet, m++, 1, "结算月");
				
				excelUtil.addString(wsheet, m++, 1, "奖金金额");
				excelUtil.addString(wsheet, m++, 1, "应提交发票金额");
				excelUtil.addString(wsheet, m++, 1, "已提交发票金额");
				excelUtil.addString(wsheet, m++, 1, "合规可用发票金额");
				excelUtil.addString(wsheet, m++, 1, "未提交发票金额");
				
				excelUtil.addString(wsheet, m++, 1, "备注(保证金)");
				excelUtil.addString(wsheet, m++, 1, "备注(已提交发票)");
				excelUtil.addString(wsheet, m++, 1, "备注(合规发票)");
				
				int kk = 2;
				for (int i = 0; i < list.size(); i++) {
					Map map = (Map) list.get(i);
					int index=0;
					excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("userCode")));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("createtime1")));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("createtime2")));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("createtime3")));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("WYEAR")));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(WeekFormatUtil.getFormatWeek("w", String.valueOf(map.get("WWEEK")))));
					
					excelUtil.addString(wsheet, index++, kk, String.valueOf(String.valueOf(map.get("aa"))));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(String.valueOf(map.get("bb"))));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(String.valueOf(map.get("cc"))));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(String.valueOf(map.get("dd"))));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(String.valueOf(map.get("ee"))));
					
					excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("REMARK1")));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("REMARK2")));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("REMARK3")));
					
					kk++;
				}
				
				excelUtil.writeExcel(wwb);
				excelUtil.closeWritableWorkbook(wwb);
				os.close();
				
				return null;
				
			}
			
			//保证金
			if("depositExport".equals(strAction)){
				
				crm.addField("userCode", userCode);
				list = jfiInvoiceManager.getJfiDepositExportByCrm(crm);
				
				//生成报表
				response.setContentType("application/vnd.ms-excel");
				response.addHeader("Content-Disposition", "attachment; filename=depositExport"+wweekS+"-"+wweekE+".xls");//设置报表名称
				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control", "max-age=30" ); 
				OutputStream os = response.getOutputStream();
				ExcelUtil excelUtil = new ExcelUtil();
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				WritableSheet wsheet = wwb.createSheet("sheet1", 0);
				//加入期别时间段显示
				//标题
				int m=0;
				excelUtil.addString(wsheet, m++, 1, "会员号");	
				excelUtil.addString(wsheet, m++, 1, "建立日期(保证金)");	
				excelUtil.addString(wsheet, m++, 1, "建立日期(已提交发票)");
				excelUtil.addString(wsheet, m++, 1, "建立日期(合规发票)");
				excelUtil.addString(wsheet, m++, 1, "财年");
				excelUtil.addString(wsheet, m++, 1, "结算月");
				
				excelUtil.addString(wsheet, m++, 1, "奖金金额");
				excelUtil.addString(wsheet, m++, 1, "应提交发票金额");
				excelUtil.addString(wsheet, m++, 1, "暂扣保证金");
				excelUtil.addString(wsheet, m++, 1, "应退保证金");
				excelUtil.addString(wsheet, m++, 1, "实退保证金");
				excelUtil.addString(wsheet, m++, 1, "暂扣保证金结余");
				excelUtil.addString(wsheet, m++, 1, "未退保证金结余");
				
				excelUtil.addString(wsheet, m++, 1, "备注(保证金)");
				excelUtil.addString(wsheet, m++, 1, "备注(已提交发票)");
				excelUtil.addString(wsheet, m++, 1, "备注(合规发票)");
				
				int kk = 2;
				for (int i = 0; i < list.size(); i++) {
					Map map = (Map) list.get(i);
					int index=0; 
					excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("userCode")));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("createtime1")));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("createtime2")));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("createtime3")));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("WYEAR")));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(WeekFormatUtil.getFormatWeek("w", String.valueOf(map.get("WWEEK")))));
					
					excelUtil.addString(wsheet, index++, kk, String.valueOf(String.valueOf(map.get("aa"))));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(String.valueOf(map.get("bb"))));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(String.valueOf(map.get("ff"))));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(String.valueOf(map.get("gg"))));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(String.valueOf(map.get("hh"))));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(String.valueOf(map.get("ii"))));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(String.valueOf(map.get("jj"))));
					
					excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("REMARK1")));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("REMARK2")));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("REMARK3")));
					
					kk++;
				}
				
				excelUtil.writeExcel(wwb);
				excelUtil.closeWritableWorkbook(wwb);
				os.close();
				
				return null;
			}
			
			//发票保证金汇总
			if("depositInvoiceExport".equals(strAction)){
				
				list = jfiInvoiceManager.getJfiDepositInvoiceExportByCrm(crm);
				//生成报表
				response.setContentType("application/vnd.ms-excel");
				response.addHeader("Content-Disposition", "attachment; filename=depositInvoiceExport"+wweekS+"-"+wweekE+".xls");//设置报表名称
				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control", "max-age=30" ); 
				OutputStream os = response.getOutputStream();
				ExcelUtil excelUtil = new ExcelUtil();
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				WritableSheet wsheet = wwb.createSheet("sheet1", 0);
				//加入期别时间段显示
				//标题
				int m=0;
				excelUtil.addString(wsheet, m++, 1, "会员号");	
				excelUtil.addString(wsheet, m++, 1, "姓名");
				excelUtil.addString(wsheet, m++, 1, "所属中策委");
				excelUtil.addString(wsheet, m++, 1, "年度");
				excelUtil.addString(wsheet, m++, 1, "结算月");
				
				excelUtil.addString(wsheet, m++, 1, "奖金金额");
				excelUtil.addString(wsheet, m++, 1, "应提交发票金额");
				excelUtil.addString(wsheet, m++, 1, "已提交发票金额");
				excelUtil.addString(wsheet, m++, 1, "合规可用发票金额");
				excelUtil.addString(wsheet, m++, 1, "未提交发票金额");
				
				excelUtil.addString(wsheet, m++, 1, "暂扣保证金");
				excelUtil.addString(wsheet, m++, 1, "应退保证金");
				excelUtil.addString(wsheet, m++, 1, "实退保证金");
				excelUtil.addString(wsheet, m++, 1, "暂扣保证金结余");
				excelUtil.addString(wsheet, m++, 1, "未退保证金结余");
				
				int kk = 2;
				for (int i = 0; i < list.size(); i++) {
					Map map = (Map) list.get(i);
					int index=0;
					excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("userCode")));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("userName")));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("zcw")));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("WYEAR")));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(WeekFormatUtil.getFormatWeek("w", String.valueOf(map.get("WWEEK")))));
					
					excelUtil.addString(wsheet, index++, kk, String.valueOf(String.valueOf(map.get("aa"))));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(String.valueOf(map.get("bb"))));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(String.valueOf(map.get("cc"))));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(String.valueOf(map.get("dd"))));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(String.valueOf(map.get("ee"))));
					
					excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("ff")));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("gg")));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("hh")));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("ii")));
					excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("jj")));
					
					kk++;
				}
				
				excelUtil.writeExcel(wwb);
				excelUtil.closeWritableWorkbook(wwb);
				os.close();
				
				return null;
			}
			
		}else{
			
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("wyear", wyear);
			request.setAttribute("wweekS", wweekS);
			request.setAttribute("wweekE", wweekE);
			request.setAttribute("strAction", strAction);
			return new ModelAndView("fi/jfiInvoiceDespostExport");
		}
		
		
		
		
		
		return null;
	}

	
	
}
