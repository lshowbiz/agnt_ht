package com.joymain.jecs.util.report;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.joymain.jecs.util.io.FileUtil;

public class ReportUtil {
	private static final String REPORT_FILE_PATH = "/WEB-INF/reports/";
	private static final String CACHE_DIR="/cacheDir";
	private static final int CACHE_PAGE_SIZE=100;
	private HttpServletRequest request;
	private HttpServletResponse response;

	/**
	 * 构造函数
	 * @param request
	 * @param response
	 */
	public ReportUtil(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	/**
	 * 生成报表文件
	 * @param format 报表格式:pdf,csv,rtf,xls,html
	 * @param jasperFileName jasper对应的文件名,如 bd/a/test.jasper
	 * @param destFileName 输出的文件名,不需要加后缀,如test,建议用英文
	 * @param inParameters 传给报表的参数集
	 * @throws JRException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void exportReport(final String format, final String jasperFileName, final String destFileName, final Map<String, Object> inParameters) throws JRException, IOException, SQLException {
		inParameters.put("logoPicUrl", request.getSession().getServletContext().getRealPath("/images/report/logo.gif"));
		
		FileUtil.createFolder(new File(request.getSession().getServletContext().getRealPath(CACHE_DIR)));
		JRFileVirtualizer fileVirtualizer = new JRFileVirtualizer(CACHE_PAGE_SIZE, request.getSession().getServletContext().getRealPath(CACHE_DIR));
		inParameters.put(JRParameter.REPORT_VIRTUALIZER, fileVirtualizer);

		ServletContext context = request.getSession().getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		Connection connection = dataSource.getConnection();
		File reportFile = new File(request.getSession().getServletContext().getRealPath( REPORT_FILE_PATH + jasperFileName));
		//System.out.println("****:"+request.getSession().getServletContext().getRealPath( REPORT_FILE_PATH + jasperFileName));
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getPath());
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, inParameters, connection);

		JRExporter exporter = null;
		String contentType = null;
		if ("html".equalsIgnoreCase(format)) {
			exporter = new JRHtmlExporter();
			response.setContentType("text/html;charset=UTF-8");

			PrintWriter printWriter = response.getWriter();

			request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, printWriter);
			exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "reportImage?image=");
		} else {
			if ("pdf".equalsIgnoreCase(format)) {
				exporter = new JRPdfExporter();
				contentType = "application/pdf";
			} else if ("xls".equalsIgnoreCase(format)) {
				exporter = new JRXlsExporter();
				exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE); // 删除记录最下面的空行
			    exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);
			    exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);// 显示边框
			    exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS,Boolean.FALSE);
			    exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,Boolean.FALSE);
			    exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,Boolean.TRUE);
			    exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER,Boolean.TRUE);
			    exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
			    exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
				contentType = "application/vnd.ms-excel";
			} else if ("rtf".equalsIgnoreCase(format)) {
				exporter = new JRRtfExporter();
				contentType = "application/rtf";
			} else if ("csv".equalsIgnoreCase(format)) {
				exporter = new JRCsvExporter();
				contentType = "application/csv";
			}

			response.setContentType(contentType);
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			response.addHeader("Content-Disposition", "attachment; filename=" + FileUtil.encodeFileName(destFileName, request) + "." + format);
			ServletOutputStream output = response.getOutputStream();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
		}
		exporter.exportReport();
		
		connection.close();
	}
}