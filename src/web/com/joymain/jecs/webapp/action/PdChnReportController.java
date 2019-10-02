package com.joymain.jecs.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.excelutils.ExcelUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.pd.service.PdChnReportManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class PdChnReportController  extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(Class.class);
    private PdChnReportManager pdChnReportManager = null;

	public void setPdChnReportManager(PdChnReportManager pdChnReportManager) {
		this.pdChnReportManager = pdChnReportManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView flag = null;
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		SysUser u = SessionLogin.getLoginUser(request);
		String company = u.getCompanyCode();
		//中国报表
		if (company != null && company.toUpperCase().equals("CN")) {
			String showFlag = request.getParameter("showFlag");
			if (showFlag != null && showFlag.equals("show")) {
				//导出报表
				String exportXsl = "/WEB-INF/xls/pdChnReport.xls";
				List list = pdChnReportManager.getChnReportByCrm(crm);
				String companyCode = request.getParameter("companyCode");
				ExcelUtils.addValue("list", list);
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=\""
						+ companyCode + ".xls\"");
				ExcelUtils.export(request.getSession().getServletContext(),
						exportXsl, response.getOutputStream());
			} else {
				String view = "pd/pdChnShipmentReport";
				flag = new ModelAndView(view);
			}
		}
		return flag;
	}

}
