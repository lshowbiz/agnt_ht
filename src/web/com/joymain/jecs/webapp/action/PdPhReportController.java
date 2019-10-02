package com.joymain.jecs.webapp.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class PdPhReportController extends BaseController implements Controller {
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
		log.info("company-------:" + company);
		log.info("userName-------:" + userName);
		log.info("fName-------:" + fName);
		log.info("lName-------:" + lName);
		String strAction = request.getParameter("strAction");
//		System.out.println("strAction----------:" + strAction);
		//菲律宾报表
		if (company != null && company.toUpperCase().equals("PH")) {
			String showFlag = request.getParameter("showFlag");
			if (showFlag != null && showFlag.equals("show")) {
//			log.info("当点击pdJpShipmentReport的‘生成报表’---：");
				String exportXsl = "/WEB-INF/xls/pdPhReport.xls";
				List list = pdJpReportManager.getPhReportByCrm(crm);
				Map<String, Long> map = pdJpReportManager.getQty();
				long premiumQty = map.get("premiumQty");
				long pantilinerQty = map.get("pantilinerQty");
				long energyQty = map.get("energyQty");
				long premiumHuoYun = map.get("premiumHuoYun");
				long pantilinerHuoYun = map.get("pantilinerHuoYun");
				long energyHuoYun = map.get("energyHuoYun");
				long premiumZhiTi = map.get("premiumZhiTi");
				long pantilinerZhiTi = map.get("pantilinerZhiTi");
				long energyZhiTi = map.get("energyZhiTi");
				String startTime = request.getParameter("sCreateTime");
				String endTime = request.getParameter("eCreateTime");
				String companyCode = request.getParameter("companyCode");
				
				JSONArray ja = new JSONArray(list);
//			log.info("ja.toString()-------:" + ja.toString());
				ExcelUtils.addValue("list", list);
				ExcelUtils.addValue("startTime", startTime);
				ExcelUtils.addValue("endTime", endTime);
				ExcelUtils.addValue("companyCode", companyCode);
				ExcelUtils.addValue("userName", userName);
				ExcelUtils.addValue("sysdate", DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss", new Date()));
				ExcelUtils.addValue("premiumQty", premiumQty);
				ExcelUtils.addValue("pantilinerQty", pantilinerQty);
				ExcelUtils.addValue("energyQty", energyQty);
				ExcelUtils.addValue("premiumHuoYun", premiumHuoYun);
				ExcelUtils.addValue("pantilinerHuoYun", pantilinerHuoYun);
				ExcelUtils.addValue("energyHuoYun", energyHuoYun);
				ExcelUtils.addValue("premiumZhiTi", premiumZhiTi);
				ExcelUtils.addValue("pantilinerZhiTi", pantilinerZhiTi);
				ExcelUtils.addValue("energyZhiTi", energyZhiTi);
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
				String view = "pd/pdPhShipmentReport";
				return new ModelAndView(view);
			}
		}
		
		return null;
	}
}
