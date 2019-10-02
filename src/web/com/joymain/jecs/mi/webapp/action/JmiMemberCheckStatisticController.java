package com.joymain.jecs.mi.webapp.action;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 审核会员统计(级别FLEX)
 * 
 * @author Alvin
 * 
 */
public class JmiMemberCheckStatisticController extends BaseController
		implements Controller {
	private final Log log = LogFactory
			.getLog(JmiMemberCheckStatisticController.class);

	private JdbcTemplate jdbcTemplate = null;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		SysUser loginSysUser = SessionLogin.getLoginUser(request);
		SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
		
		String type = request.getParameter("type");
		if("xml".equals(type.toLowerCase())){
			// 生成excel文件
			response.setContentType("text/xml");
			OutputStream os = response.getOutputStream();
			
			String sql = "Select column_value FROM TABLE(jbd_check_statistics('" + loginSysUser.getCompanyCode().toUpperCase() + "'," + "To_Date('" + request.getParameter("startTime") + "', 'yyyy-mm-dd hh24:mi:ss')"
				+ ",To_Date('" + request.getParameter("endTime") + "', 'yyyy-mm-dd hh24:mi:ss')))";
			List miMembers = this.jdbcTemplate.queryForList(sql);
			
			// 创建根节点 list;
			Element root = new Element("provinces");

			// 根节点添加到文档中；
			Document Doc = new Document(root);

			// 此处 for 循环可替换成 遍历 数据库表的结果集操作;
			for (int i = 0; i < miMembers.size(); i++) {
				Map miMemberMap = (Map) miMembers.get(i);
				String[] miMemberStr = miMemberMap.get("column_value").toString().split(",");
				// 创建节点 user;
				Element elements = new Element("province");

				// 给 user 节点添加属性 id;
				elements.setAttribute("label",LocaleUtil.getLocalText("bd.cardType" + miMemberStr[0]));
				elements.setAttribute("data",miMemberStr[1]);
				

				// 给 user 节点添加子节点并赋值；
				// new Element("name")中的 "name" 替换成表中相应字段，setText("xuehui")中 "xuehui
				// 替换成表中记录值；
				//elements.addContent(new Element("name").setText("xuehui"));
				//elements.addContent(new Element("age").setText("28"));
				//elements.addContent(new Element("sex").setText("Male"));

				// 给父节点list添加user子节点;
				root.addContent(elements);

			}
			XMLOutputter XMLOut = new XMLOutputter();

			// 输出 user.xml 文件；
			XMLOut.output(Doc, os);
		}else if("excel".equals(type.toLowerCase())){
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=Generate Report(Members Vs Commissions)to Members_"+request.getParameter("startTime")+"_"+request.getParameter("endTime")+".xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			ExcelUtil excelUtil = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet wsheet = wwb.createSheet("Sheet1", 0);
			//加入期别时间段显示
			//标题
			int m=0;
			excelUtil.addString(wsheet, m++, 1, LocaleUtil.getLocalText("miMember.cardType"));
			excelUtil.addString(wsheet, m++, 1, LocaleUtil.getLocalText("pd.qty"));
			

			String sql = "Select column_value FROM TABLE(jbd_check_statistics('" + loginSysUser.getCompanyCode().toUpperCase() + "'," + "To_Date('" + request.getParameter("startTime") + "', 'yyyy-mm-dd hh24:mi:ss')"
				+ ",To_Date('" + request.getParameter("endTime") + "', 'yyyy-mm-dd hh24:mi:ss')))";
			List miMembers = this.jdbcTemplate.queryForList(sql);
			int kk = 2;
			for (int i = 0; i < miMembers.size(); i++) {
				Map miMemberMap = (Map) miMembers.get(i);
				String[] miMemberStr = miMemberMap.get("column_value").toString().split(",");
				int index=0;
				excelUtil.addString(wsheet, index++, kk, LocaleUtil.getLocalText("bd.cardType" + miMemberStr[0]));
				excelUtil.addNumber(wsheet, index++, kk, Integer.parseInt(miMemberStr[1]));
				kk++;
			}
			excelUtil.writeExcel(wwb);
			excelUtil.closeWritableWorkbook(wwb);
			os.close();
		}
		

		return null;
	}
}
