package com.joymain.jecs.pd.webapp.action;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.pd.model.PdSendInfo;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;


public class PdSendInfoTeamReportController extends BaseFormController {
	private transient final Log log = LogFactory.getLog(PdSendInfoTeamReportController.class);
	private JdbcTemplate jdbcTemplate = null;
	private ExcelUtil eu = null;
	private WritableSheet wsheet = null;
	private HttpServletRequest innerRequest;
	private LinkedHashMap<String,String> textMap=new LinkedHashMap<String,String>();
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public PdSendInfoTeamReportController() {
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
			 String okTimeStart = request.getParameter("okTimeStart");
			 String okTimeEnd   = request.getParameter("okTimeEnd");
	        
		if ("post".equalsIgnoreCase(request.getMethod())) {
			this.innerRequest=request;
			String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
			if (SessionLogin.getLoginUser(request).getIsManager()) {
				companyCode = request.getParameter("companyCode");
			}

			//生成excel文件
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=pdSendInfoTeamReport.xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			//获得输出流
			OutputStream os = response.getOutputStream();
			eu = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			
			//在此创建的新excel文件创建一工作表 
			wsheet = wwb.createSheet("Sheet1", 0);
			
	        //标题
        	int i=0;
        	eu.addString(wsheet, i++, 0, "B级公司名称");
			eu.addString(wsheet, i++, 0, "C级公司名称");
			eu.addString(wsheet, i++, 0, "订单日期");
			eu.addString(wsheet, i++, 0, "订单类型");
			eu.addString(wsheet, i++, 0, "身份证号");
			eu.addString(wsheet, i++, 0, "发货单号");
			eu.addString(wsheet, i++, 0, "订单号");
			eu.addString(wsheet, i++, 0, "客户编号");
			eu.addString(wsheet, i++, 0, "客户名称");
			eu.addString(wsheet, i++, 0, "收货人");
			eu.addString(wsheet, i++, 0, "产品编码");
			eu.addString(wsheet, i++, 0, "产品名称");
			eu.addString(wsheet, i++, 0, "数量");
			eu.addString(wsheet, i++, 0, "单价");
			eu.addString(wsheet, i++, 0, "确认时间");
			eu.addString(wsheet, i++, 0, "发货仓");
			eu.addString(wsheet, i++, 0, "移动电话");
			eu.addString(wsheet, i++, 0, "收货省");
			eu.addString(wsheet, i++, 0, "收货市");
			eu.addString(wsheet, i++, 0, "收货地址");
			eu.addString(wsheet, i++, 0, "物流公司");
			eu.addString(wsheet, i++, 0, "物流跟踪号");
			
			//设置进度条总记录数
			String paperNumbers="";
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("importExcelFile");
			if (file!=null && file.getSize()!=0) {
				Object obj[]=this.getPaperNumbers(request);
				if((Boolean)obj[0]==false){
						request.getSession().setAttribute("errorMessages", obj[1]);
			            return new ModelAndView(getSuccessView());
				}else{
					paperNumbers=(String) obj[1];
					if(StringUtil.isEmpty(paperNumbers)){
						this.saveMessage(request, "未找到数据");
						return new ModelAndView(getSuccessView());
					}
					if(paperNumbers.split("','").length>=1000){
						this.saveMessage(request, "数据过多");
						return new ModelAndView(getSuccessView());
					}
				}
				
				String[] paperNumber  = new String[2];
				paperNumber[1] = obj[1].toString();
				String sql = "Select * From table(Fn_Get_Bc_Data(PAPERNUMBER_ARRAY("+paperNumber[1]+")"+","+"TO_DATE"+"("+"'"+okTimeStart+"'"+","+"'YYYY-MM-dd "+"'"+"),"+"TO_DATE"+"("+"'"+okTimeEnd+"'"+","+"'"+"YYYY-MM-dd"+"'"+")"+")"+")";
				List queryForList = jdbcTemplate.queryForList(sql);
				if(queryForList.size()>=65536){
					this.saveMessage(request, "导出数据过多，请分次导出");
					return new ModelAndView(getSuccessView());
				}
				this.jdbcTemplate.query(sql, new ResultSetExtractor(){
						@Override
						public Object extractData(java.sql.ResultSet rs) throws SQLException, DataAccessException {
							try {
								
								int kk = 1;
								while (rs.next()) {
									PdSendInfo sendInfo = new PdSendInfo();
									String b_companyName = "";
									String c_companyName = "";
									Date check_date = rs.getDate("CHECK_DATE");
									String order_type = rs.getString("ORDER_TYPE");
									String paperNumber = rs.getString("PAPERNUMBER");
									String si_no = rs.getString("SI_NO");
									String order_no = rs.getString("ORDER_NO");
									String custom_code = rs.getString("CUSTOM_CODE");
									String custom_name = rs.getString("CUSTOM_NAME");
									String recipient_name = rs.getString("RECIPIENT_NAME");
									String product_no = rs.getString("PRODUCT_NO");
									String product_name = rs.getString("PRODUCT_NAME");
									long qty = rs.getLong("QTY");
									String qtys = qty+"";
									long price = rs.getLong("PRICE");
									String prices = price+"";
									Date ok_time = rs.getDate("OK_TIME");
									String warehouse_no = rs.getString("WAREHOUSE_NO");
									String recipient_mobile = rs.getString("RECIPIENT_MOBILE");
									String province_name = rs.getString("PROVINCE_NAME");
									String city_name = rs.getString("CITY_NAME");
									String recipient_addr = rs.getString("RECIPIENT_ADDR");
									String sh_no = rs.getString("SH_NO");
									String tracking_no = rs.getString("TRACKING_NO");
									
									switch (Integer.parseInt(order_type)) {
									case 1:
										sendInfo.setOrderType(LocaleUtil.getLocalText("ordertype.1"));
										break;
									case 2:
										sendInfo.setOrderType(LocaleUtil.getLocalText("ordertype.2"));
										break;
									case 3:
										sendInfo.setOrderType(LocaleUtil.getLocalText("ordertype.3"));
										break;
									case 4:
										sendInfo.setOrderType(LocaleUtil.getLocalText("ordertype.4"));
										break;
									case 5:
										sendInfo.setOrderType(LocaleUtil.getLocalText("ordertype.5"));
										break;
									case 6:
										sendInfo.setOrderType(LocaleUtil.getLocalText("ordertype.6"));
										break;
									case 9:
										sendInfo.setOrderType(LocaleUtil.getLocalText("ordertype.9"));
										break;
									case 11:
										sendInfo.setOrderType(LocaleUtil.getLocalText("ordertype.11"));
										break;
									case 12:
										sendInfo.setOrderType(LocaleUtil.getLocalText("ordertype.12"));
										break;
									case 14:
										sendInfo.setOrderType(LocaleUtil.getLocalText("ordertype.14"));
										break;
									case 16:
										sendInfo.setOrderType(LocaleUtil.getLocalText("ordertype.16"));
										break;
									case 30:
										sendInfo.setOrderType(LocaleUtil.getLocalText("ordertype.30"));
										break;
									case 32:
										sendInfo.setOrderType(LocaleUtil.getLocalText("ordertype.32"));
										break;
									case 40:
										sendInfo.setOrderType(LocaleUtil.getLocalText("ordertype.40"));
										break;
									case 41:
										sendInfo.setOrderType(LocaleUtil.getLocalText("ordertype.41"));
										break;
									default:
										break;
									}
									
									int index=0;
									eu.addString(wsheet, index++, kk, b_companyName);
									eu.addString(wsheet, index++, kk, c_companyName);
									eu.addString(wsheet, index++, kk, formatDate(check_date));
									eu.addString(wsheet, index++, kk, sendInfo.getOrderType());
									eu.addString(wsheet, index++, kk, paperNumber);
									eu.addString(wsheet, index++, kk, si_no);
									eu.addString(wsheet, index++, kk, order_no);
									eu.addString(wsheet, index++, kk, custom_code);
									eu.addString(wsheet, index++, kk, custom_name);
									eu.addString(wsheet, index++, kk, recipient_name);
									eu.addString(wsheet, index++, kk, product_no);
									eu.addString(wsheet, index++, kk, product_name);
									eu.addString(wsheet, index++, kk, qtys);
									eu.addString(wsheet, index++, kk, prices);
									eu.addString(wsheet, index++, kk, formatDate(ok_time));
									eu.addString(wsheet, index++, kk, warehouse_no);
									eu.addString(wsheet, index++, kk, recipient_mobile);
									eu.addString(wsheet, index++, kk, province_name);
									eu.addString(wsheet, index++, kk, city_name);
									eu.addString(wsheet, index++, kk, recipient_addr);
									eu.addString(wsheet, index++, kk, sh_no);
									eu.addString(wsheet, index++, kk, tracking_no);
									
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
			}
		}
		return new ModelAndView(getSuccessView());
	}
	
	private Object[] getPaperNumbers(HttpServletRequest request) throws Exception{

		Object []res=new Object[2];
		res[0]=false;
		res[1]=new ArrayList<Object>();
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
		
		String paperNumbers = "";
		for (int i = 1; i < sheet.getRows(); i++) {
			String paperNumber = eu.getContents(sheet, 0, i);
			String content = " ([ " + paperNumber + " ])";
			String message = (i + 1) + ": ";
			if (StringUtils.isEmpty(paperNumber)) {
				messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + LocaleUtil.getLocalText("bdBounsDeduct.error.memberNo.empty") + content+"</font>");
				errCount++;
				continue;
			}
			String sql = "select * from jmi_member where paperNumber = "+"'"+paperNumber+"'";
			List list = jdbcTemplate.queryForList(sql);
			//检验身份证是否存在
			if(list.size()==0){
				messages.add("<font color=red>" + message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
				+ " - " + "请检查身份证是否有误" + content + "</font>");
				errCount++;continue;
			}
			if(StringUtil.isEmpty(paperNumbers)){
				paperNumbers+="'"+paperNumber.trim()+"'";
			}else{
				paperNumbers+=",'"+paperNumber.trim()+"'";
			}
		}
		if(errCount!=0){
			res[1]=messages;
			return res;
		}
		
		res[0]=true;
		res[1]=paperNumbers;
		return res;
		
	}
	public static String formatDate(Date date){
		if(date == null){
			return "";
		}
		return sdf.format(date);
	}
	
	
}