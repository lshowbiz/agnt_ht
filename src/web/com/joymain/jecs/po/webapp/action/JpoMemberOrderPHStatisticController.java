package com.joymain.jecs.po.webapp.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.io.OutputStream;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.model.JalTown;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.al.service.JalTownManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 
 * @author Alvin
 *
 */
public class JpoMemberOrderPHStatisticController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoMemberOrderPHStatisticController.class);
    private SysUserManager sysUserManager;
    private JpoMemberOrderManager jpoMemberOrderManager;
    public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
    	SysUser loginSysUser = SessionLogin.getLoginUser(request);
    	
    	if("post".equalsIgnoreCase(request.getMethod())){

    		String createBTime = request.getParameter("createBTime");
    		String createETime = request.getParameter("createETime");
			if(StringUtil.isEmpty(createBTime) || StringUtil.isEmpty(createETime)) {
				saveMessage(request, LocaleUtil.getLocalText("error.wweek.not.existed"));
				return new ModelAndView("redirect:jpoRecommendStatistic.html");
			}
//			生成excel文件
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=Sales Invoice Summary for Request.xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			ExcelUtil eu = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			//在此创建的新excel文件创建一工作表 
			WritableSheet wsheet = wwb.createSheet("Sheet1", 0);
			eu.addString(wsheet, 0, 0, "Joy Life International Philippines, Inc");
			eu.addString(wsheet, 0, 1, "Sales Invoice Summary");
			eu.addString(wsheet, 0, 2, createBTime);
			eu.addString(wsheet, 1, 2, createETime);
			
			eu.addString(wsheet, 0, 4, "SI No.");
			eu.addString(wsheet, 1, 4, "Order No.");
			eu.addString(wsheet, 2, 4, "Issued By");
			eu.addString(wsheet, 3, 4, "Date Issued");
			eu.addString(wsheet, 4, 4, "Dist. ID");
			eu.addString(wsheet, 5, 4, "Dist. Name");
			eu.addString(wsheet, 6, 4, "Amount");

	        Pager pager = new Pager("jpoMemberOrderListTable",request, 0);
	        CommonRecord crm = new CommonRecord();
	        crm.addField("companyCode", "PH");
	        crm.addField("logType", "C");
	        crm.addField("startLogTime", createBTime);
	        crm.addField("endLogTime", createETime);
	        
	        List jpoMemberOrders = jpoMemberOrderManager.getJpoMemberOrdersByCrm(crm,pager);
			int i = 5;
			BigDecimal sumAmount = new BigDecimal("0");
			for(  int m = 0 ; m < jpoMemberOrders.size() ; m++){
				JpoMemberOrder jpoMemberOrder = (JpoMemberOrder)jpoMemberOrders.get(m);
				eu.addString(wsheet, 0, i+m, "");
				eu.addString(wsheet, 1, i+m, jpoMemberOrder.getMemberOrderNo());
				SysUser sysUser = sysUserManager.getSysUser(jpoMemberOrder.getCheckUserCode());
				if(sysUser!=null){
					eu.addString(wsheet, 2, i+m, sysUser.getUserName());
				}
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
				eu.addString(wsheet, 3, i+m, df.format(jpoMemberOrder.getCheckDate()));
				eu.addString(wsheet, 4, i+m, jpoMemberOrder.getSysUser().getUserCode());
				eu.addString(wsheet, 5, i+m, jpoMemberOrder.getSysUser().getUserName());
				eu.addString(wsheet, 6, i+m, jpoMemberOrder.getAmount().toString());
				sumAmount = sumAmount.add(jpoMemberOrder.getAmount());
			}
			i = i + jpoMemberOrders.size();
			i++;
			eu.addString(wsheet, 0, i, "TOTAL");
			eu.addString(wsheet, 6, i, sumAmount.toString());
			
			i +=3;
			eu.addString(wsheet, 2, i, "Prepared by:");
			eu.addString(wsheet, 4, i, "Checked by:");
			
			i +=3;
			eu.addString(wsheet, 2, i, loginSysUser.getUserName());

			eu.writeExcel(wwb);
			eu.closeWritableWorkbook(wwb);
			os.close();

			return null;
    	}
    	
    	return new ModelAndView("po/jpoMemberOrderPHStatistic");
    }

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}
}
