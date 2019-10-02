package com.joymain.jecs.po.webapp.action;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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

import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 订单统计(地区FLEX)
 * 
 * @author Alvin
 * 
 */
public class JpoMemberOrderAreaStatisticController extends BaseController
		implements Controller {
	private final Log log = LogFactory
			.getLog(JpoMemberOrderAreaStatisticController.class);

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
			
			String sql = "Select column_value FROM TABLE(Jbd_Sales_Performance('" + loginSysUser.getCompanyCode().toUpperCase() + "'," + "To_Date('" + request.getParameter("startTime").substring(0, 10)+" 00:00:00" + "', 'yyyy-mm-dd hh24:mi:ss')"
				+ ",To_Date('" + request.getParameter("endTime").substring(0, 10)+" 23:59:59" + "', 'yyyy-mm-dd hh24:mi:ss')))";
			List poMemberOrders = this.jdbcTemplate.queryForList(sql);
			
			// 创建根节点 list;
			Element root = new Element("provinces");

			// 根节点添加到文档中；
			Document Doc = new Document(root);
			
			List poMemberOrdersTmp = new ArrayList();
			for (int i = 0; i < poMemberOrders.size(); i++) {
				Map poMemberOrderMap = (Map) poMemberOrders.get(i);
				String[] poMemberOrderStr = poMemberOrderMap.get("column_value").toString().split(",");
				int m = 0 ;
				for(m = 0 ; m < poMemberOrdersTmp.size() ; m++ ){
					Map map = (Map)poMemberOrdersTmp.get(m);
					if(map.get("province").toString().equals(poMemberOrderStr[0].toString())){
						map.put("count", Long.parseLong(map.get("count").toString()) + Long.parseLong(poMemberOrderStr[1]));
						map.put("amount", Double.parseDouble(map.get("amount").toString()) + Double.parseDouble(poMemberOrderStr[2]));
						map.put("pv", Double.parseDouble(map.get("pv").toString()) + Double.parseDouble(poMemberOrderStr[3]));
						map.put("jj", Double.parseDouble(map.get("jj").toString()) + Double.parseDouble(poMemberOrderStr[4]));
						break;
					}
				}
				if( m == poMemberOrdersTmp.size()){
					Map map = new HashMap(0);
					map.put("province", poMemberOrderStr[0].toString());
					map.put("count", poMemberOrderStr[1].toString());
					map.put("amount", poMemberOrderStr[2].toString());
					map.put("pv", poMemberOrderStr[3].toString());
					map.put("jj", poMemberOrderStr[4].toString());
					poMemberOrdersTmp.add(map);
				}
			}
			// 此处 for 循环可替换成 遍历 数据库表的结果集操作;
			for (int i = 0; i < poMemberOrdersTmp.size(); i++) {
				Map poMemberOrderMap = (Map) poMemberOrdersTmp.get(i);
				// 创建节点 user;
				Element elements = new Element("province");

				// 给 user 节点添加属性 id;

				Element stateProvinceName = new Element("stateProvinceName");
				stateProvinceName.addContent(poMemberOrderMap.get("province").toString());
				elements.addContent(stateProvinceName);
				Element count = new Element("count");
				count.addContent(poMemberOrderMap.get("count").toString());
				elements.addContent(count);
				Element amount = new Element("amount");
				amount.addContent(poMemberOrderMap.get("amount").toString());
				elements.addContent(amount);
				Element pv = new Element("pv");
				pv.addContent(poMemberOrderMap.get("pv").toString());
				elements.addContent(pv);
				Element jj = new Element("jj");
				pv.addContent(poMemberOrderMap.get("jj").toString());
				elements.addContent(jj);

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
			response.addHeader("Content-Disposition", "attachment; filename=Generate Report(Members Vs Commissions)to MemberOrders_"+request.getParameter("startTime")+"_"+request.getParameter("endTime")+".xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			ExcelUtil excelUtil = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet wsheet = wwb.createSheet("Sheet1", 0);
			//加入期别时间段显示
			//标题
			int m=0;
			excelUtil.addString(wsheet, m++, 1, LocaleUtil.getLocalText("menu.alStateProvinces"));
			excelUtil.addString(wsheet, m++, 1, LocaleUtil.getLocalText("pd.qty"));
			excelUtil.addString(wsheet, m++, 1, LocaleUtil.getLocalText("busi.finance.amount"));
			excelUtil.addString(wsheet, m++, 1, LocaleUtil.getLocalText("busi.direct.pv"));
			excelUtil.addString(wsheet, m++, 1, LocaleUtil.getLocalText("busi.jjmoney"));//Modify By WuCF 发展基金
			excelUtil.addString(wsheet, m++, 1, LocaleUtil.getLocalText("pd.orderType"));
			
			
			String sql = "Select column_value FROM TABLE(Jbd_Sales_Performance('" + loginSysUser.getCompanyCode().toUpperCase() + "'," + "To_Date('" + request.getParameter("startTime").substring(0, 10)+" 00:00:00" + "', 'yyyy-mm-dd hh24:mi:ss')"
				+ ",To_Date('" + request.getParameter("endTime").substring(0, 10)+" 23:59:59" + "', 'yyyy-mm-dd hh24:mi:ss')))";
			List poMemberOrders = this.jdbcTemplate.queryForList(sql);
			
			int kk = 2;
			Map cardTypeMap = ListUtil.getListOptions(loginSysUser.getCompanyCode(), "po.all.order_type");
			for (int i = 0; i < poMemberOrders.size(); i++) {
				Map poMemberOrderMap = (Map) poMemberOrders.get(i);
				String[] poMemberOrderStr = poMemberOrderMap.get("column_value").toString().split(",");
				int index=0;
				excelUtil.addString(wsheet, index++, kk, poMemberOrderStr[0]);
				excelUtil.addNumber(wsheet, index++, kk, Integer.parseInt(poMemberOrderStr[1]));
				excelUtil.addNumber(wsheet, index++, kk, Double.parseDouble(poMemberOrderStr[2]));
				excelUtil.addNumber(wsheet, index++, kk, Double.parseDouble(poMemberOrderStr[3]));
				excelUtil.addNumber(wsheet, index++, kk, Double.parseDouble(poMemberOrderStr[4]));//Modify By WuCF 发展基金
				if(cardTypeMap.get(poMemberOrderStr[5])==null){
					excelUtil.addString(wsheet, index++, kk, "");
				}else{
					excelUtil.addString(wsheet, index++, kk, LocaleUtil.getLocalText(cardTypeMap.get(poMemberOrderStr[5]).toString()));
				}
				kk++;
			}
			excelUtil.writeExcel(wwb);
			excelUtil.closeWritableWorkbook(wwb);
			os.close();
		}

		return null;
	}
}
