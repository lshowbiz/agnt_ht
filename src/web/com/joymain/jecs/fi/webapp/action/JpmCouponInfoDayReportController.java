package com.joymain.jecs.fi.webapp.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.excelutils.ExcelUtils;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.pm.model.JpmCouponInfo;
import com.joymain.jecs.pm.service.JpmCouponInfoManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.io.FileUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 代金券日报表  2017-07-17 
 * @author fu 
 *
 */
public class JpmCouponInfoDayReportController extends BaseFormController {
	
	private JpmCouponInfoManager jpmCouponInfoManager = null;
	
	public void setJpmCouponInfoManager(JpmCouponInfoManager jpmCouponInfoManager) {
		this.jpmCouponInfoManager = jpmCouponInfoManager;
	}

	public JpmCouponInfoDayReportController() {
		setCommandName("jpmCouponInfo");//??
		setCommandClass(JpmCouponInfo.class);//??
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		log.info("在类JpmCouponInfoDayReportController的方法中formBackingObject运行");
		JpmCouponInfo jpmCouponInfo = new JpmCouponInfo();
		return jpmCouponInfo;
		
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		
		log.info("在类JpmCouponInfoDayReportController的方法中onSubmit开始运行");

		SysUser loginSysUser = SessionLogin.getLoginUser(request);
		String companyCode=loginSysUser.getCompanyCode();
		Map<String, Object> reportMap=new HashMap<String, Object>();
		String reportDate=request.getParameter("reportDate");
		reportMap.put("reportDate", reportDate);
		
		//获取reportDate的前一天
		log.info("在类JpmCouponInfoDayReportController的方法中onSubmit运行：即将执行方法getReportDateBefore");
		String dayBefore = jpmCouponInfoManager.getReportDateBefore(reportDate);
		reportMap.put("dayBefore", dayBefore);
		//获取dayBefore当天及之前的代金券未使用的金额（获取reportDate的前一天日电子存折余额(代金券未使用金额））
		BigDecimal dayBeforeCpvalueSumWsy = jpmCouponInfoManager.getJpmCoumponInfoCpValueS(dayBefore,"0");
		if(null==dayBeforeCpvalueSumWsy){
			dayBeforeCpvalueSumWsy = new BigDecimal(0);
		}
		reportMap.put("dayBeforeCpvalueSumWsy", dayBeforeCpvalueSumWsy);
		
		// 获取reportDate当天升级单赠送的代金券的总面额（ 即是：审核订单赠送代金金额  （是实际当天升级单赠送代金券的金额））
		BigDecimal upgradeSheetCpValue = jpmCouponInfoManager.getUpgradeSheetCpValueS(reportDate);
		if(null==upgradeSheetCpValue){
			upgradeSheetCpValue = new BigDecimal(0);
		}
		reportMap.put("upgradeSheetCpValue", upgradeSheetCpValue);
		
		/*log.info("在类JpmCouponInfoDayReportController的方法中onSubmit运行：即将执行方法getJpmCoumponInfoCpValueS");
		//获取到reportDate为止，未使用代金券的总额
		BigDecimal cpvalueSumWsy = jpmCouponInfoManager.getJpmCoumponInfoCpValueS(reportDate,"0");
		if(null==cpvalueSumWsy){
			cpvalueSumWsy = new BigDecimal(0);
		}
		reportMap.put("cpvalueSumWsy", cpvalueSumWsy);
		
		log.info("在类JpmCouponInfoDayReportController的方法中onSubmit运行：即将执行方法getJpmCoumponInfoCpValueS-已使用");
		//获取到reportDate为止，已经使用代金券的总额
		BigDecimal cpvalueSumYsy = jpmCouponInfoManager.getJpmCoumponInfoCpValueS(reportDate,"1");
		if(null==cpvalueSumYsy){
			cpvalueSumYsy = new BigDecimal(0);
		}
		reportMap.put("cpvalueSumYsy", cpvalueSumYsy);*/
		
		log.info("在类JpmCouponInfoDayReportController的方法中onSubmit运行：即将执行方法getJpmCouponInfoCpValueS-代金券面额总数");
		//获取reportDate当天，代金券的使用面额总数
		BigDecimal cpvalueSumMezs = jpmCouponInfoManager.getJpmCouponInfoCpValueS(reportDate);
		if(null==cpvalueSumMezs){
			cpvalueSumMezs = new BigDecimal(0);
		}
		reportMap.put("cpvalueSumMezs", cpvalueSumMezs);

		log.info("在类JpmCouponInfoDayReportController的方法中onSubmit运行：即将执行方法getJpoMemberOrderCpValueS");
		/*//获取reportDate当天，订单实际使用的代金券总额
		BigDecimal cpvalueSumDdzs = jpmCouponInfoManager.getJpoMemberOrderCpValueS(reportDate);
		if(null==cpvalueSumDdzs){
			cpvalueSumDdzs = new BigDecimal(0);
		}
		reportMap.put("cpvalueSumDdzs", cpvalueSumDdzs);*/

		BigDecimal phss = dayBeforeCpvalueSumWsy.add(upgradeSheetCpValue).subtract(cpvalueSumMezs);
		reportMap.put("phss", phss);

		ExcelUtils.addValue("reportMap", reportMap);
		ExcelUtils.addValue("moneyTypes", ListUtil.getListOptions(companyCode, "fibankbooktemp.moneytype"));
		
		log.info("在类JpmCouponInfoDayReportController的方法中onSubmit运行：开始处理xls数据");
		ExcelUtils.addService("langTool", LocaleUtil.class);
		
		String config = "/WEB-INF/xls/jpmCouponInfoDayReport.xls";

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + FileUtil.encodeFileName(getText("menu.fi.jpmCouponInfoDayReport"), request) + "_"+reportDate+".xls\"");
		// 输出Excel
		log.info("在类JpmCouponInfoDayReportController的方法中onSubmit运行：开始向xls中插入数据");
		ExcelUtils.export(request.getSession().getServletContext(), config, response.getOutputStream());
		return null;
	}
	
}