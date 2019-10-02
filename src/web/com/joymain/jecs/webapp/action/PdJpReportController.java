package com.joymain.jecs.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.excelutils.ExcelUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jettison.json.JSONArray;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.pd.service.PdJpReportManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class PdJpReportController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(Class.class);
    private PdJpReportManager pdJpReportManager = null;

	public void setPdJpReportManager(PdJpReportManager pdJpReportManager) {
		this.pdJpReportManager = pdJpReportManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		SysUser u = SessionLogin.getLoginUser(request);
		String userName = u.getUserName();
		String fName = u.getFirstName();
		String lName = u.getLastName();
		String company = u.getCompanyCode();
		String strAction = request.getParameter("strAction");
//		System.out.println("strAction----------:" + strAction);
		if (company != null && company.toUpperCase().equals("JP")) {
			String showFlag = request.getParameter("showFlag");
			if (showFlag != null && showFlag.equals("show")) {
//			log.info("当点击pdJpShipmentReport的‘生成报表’---：");
				String exportXsl = "/WEB-INF/xls/pdJpReport.xls";
				List list = pdJpReportManager.getJpReportByCrm(crm);
				String startTime = request.getParameter("sOrderTime");
				String endTime = request.getParameter("eOrderTime");
				String companyCode = request.getParameter("companyCode");
				
				JSONArray ja = new JSONArray(list);
//			log.info("ja.toString()-------:" + ja.toString());
				//番号：indexOfList
				ExcelUtils.addValue("list", list);
				ExcelUtils.addValue("panhao", 160);
				ExcelUtils.addValue("startTime", startTime);
				ExcelUtils.addValue("endTime", endTime);
				ExcelUtils.addValue("companyCode", companyCode);
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=\""
						+ companyCode + "-(" + startTime + "-"
						+ endTime + ")" + ".xls\"");
				ExcelUtils.export(request.getSession().getServletContext(),
						exportXsl, response.getOutputStream());
				return null;
			} else {
//			log.info("log直接跳转到pdJpShipmentReport页面---：");
				String view = "pd/pdJpShipmentReport";
				return new ModelAndView(view);
			}
		}
		
		return null;
	}
}
