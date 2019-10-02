package com.joymain.jecs.fi.webapp.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.excelutils.ExcelUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.fi.model.JfiBankbookJournal;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.io.FileUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 存折日报表
 * @author Alvin
 *
 */
@SuppressWarnings({"rawtypes","unused","unchecked"})
public class JfiBankbookDayReportController extends BaseFormController {
	private JfiBankbookJournalManager jfiBankbookJournalManager = null;
	private AlCompanyManager alCompanyManager = null;

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public void setJfiBankbookJournalManager(JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}

	public JfiBankbookDayReportController() {
		setCommandName("jfiBankbookJournal");
		setCommandClass(JfiBankbookJournal.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
        RequestUtil.freshSession(request,"fiBankbookDayReport",10l);
		JfiBankbookJournal jfiBankbookJournal = new JfiBankbookJournal();
		
		List alCompanys = this.alCompanyManager.getAlCompanysExceptAA();
		request.setAttribute("alCompanys", alCompanys);

		return jfiBankbookJournal;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
		if("C".equals(loginSysUser.getUserType())){
    		if(RequestUtil.saveOperationSession(request,"fiBankbookDayReport",10l)!=0){
    			return new ModelAndView("redirect:jfiBankbookDayReport.html");
    		}
    	}else if("M".equals(loginSysUser.getUserType())){
    		if(RequestUtil.saveOperationSession(request,"fiBankbookDayReport",1l)!=0){
    			return new ModelAndView("redirect:jfiBankbookDayReport.html");
    		}
    	}
		
		BigDecimal inItems1TotalMoney=new BigDecimal(0);
		BigDecimal inItems2TotalMoney=new BigDecimal(0);
		
		String companyCode=SessionLogin.getLoginUser(request).getCompanyCode();
		if(!StringUtils.isEmpty(request.getParameter("companyCode"))){
			companyCode=request.getParameter("companyCode");
		}

		//统计日期
		String reportDate=request.getParameter("reportDate");
		//前一天
		String preDate=DateUtil.convertDateToString(DateUtil.getDateOffset(DateUtil.convertStringToDate(reportDate), 5, -1));
		
		//截止至统计日期时的存折余额总计
		BigDecimal totalBalance=this.jfiBankbookJournalManager.getTotalBalanceByDate(companyCode, reportDate);
		//前一天存折余额总计
		BigDecimal preTotalBalance=this.jfiBankbookJournalManager.getTotalBalanceByDate(companyCode, preDate);

		Map<String, Object> reportMap=new HashMap<String, Object>();
		reportMap.put("reportDate", LocaleUtil.getLocalText("jfiBankbookDayReport.daymoneys", new String[]{reportDate}));
		reportMap.put("preDate",LocaleUtil.getLocalText("jfiBankbookDayReport.daymoney", new String[]{preDate}));
		reportMap.put("totalBalance", totalBalance);
		reportMap.put("preTotalBalance", preTotalBalance);
		
		//Modify By WuCF 20150929 易宝支付140  {160,170:顺首付,银盛} 200 环迅支付
		Integer[] inMoneyTypes=new Integer[]{1,2,21,29,31,90,91,120,130,140,160,170,200};
		List inItems1=this.jfiBankbookJournalManager.getJfiBankbookJournalsStat(companyCode, "A", inMoneyTypes, false, reportDate, reportDate);
		
		if(inItems1!=null && inItems1.size()>0){
			for(int i=0;i<inItems1.size();i++){
				HashMap resultMap=(HashMap)inItems1.get(i);
				if(resultMap.get("total_money")!=null && !StringUtils.isEmpty(resultMap.get("total_money").toString())){
					inItems1TotalMoney=inItems1TotalMoney.add(new BigDecimal(resultMap.get("total_money").toString()));
				}
			}
		}
		
		List inItems2=this.jfiBankbookJournalManager.getJfiBankbookJournalsStat(companyCode, "A", inMoneyTypes, true, reportDate, reportDate);
		
		if(inItems2!=null && inItems2.size()>0){
			for(int i=0;i<inItems2.size();i++){
				HashMap resultMap=(HashMap)inItems2.get(i);
				if(resultMap.get("total_money")!=null && !StringUtils.isEmpty(resultMap.get("total_money").toString())){
					inItems2TotalMoney=inItems2TotalMoney.add(new BigDecimal(resultMap.get("total_money").toString()));
				}
			}
		}
		
		List outItems=this.jfiBankbookJournalManager.getJfiBankbookJournalsStat(companyCode, "D", null, true, reportDate, reportDate);
		BigDecimal outItemsTotalMoney=new BigDecimal(0);
		if(outItems!=null && outItems.size()>0){
			for(int i=0;i<outItems.size();i++){
				HashMap resultMap=(HashMap)outItems.get(i);
				if(resultMap.get("total_money")!=null && !StringUtils.isEmpty(resultMap.get("total_money").toString())){
					outItemsTotalMoney=outItemsTotalMoney.add(new BigDecimal(resultMap.get("total_money").toString()));
				}
			}
		}
		
		BigDecimal maybeBalance=preTotalBalance.add(inItems1TotalMoney).add(inItems2TotalMoney).subtract(outItemsTotalMoney);
		reportMap.put("maybeBalance", maybeBalance);
		
/*Alvin		//根据财务部要求,取消银行到款条目类别的数据也加入至"确认收款入电子存折"及"电子存折借方非当日正常收款存入数"
		Map<String, BigDecimal> returnMap1=this.specialDeal(inItems1TotalMoney, inItems2TotalMoney,outItems, inItems1, inItems2, "22", "21");
		inItems1TotalMoney=returnMap1.get("inItems1TotalMoney");
		inItems2TotalMoney=returnMap1.get("inItems2TotalMoney");
		//现金
		Map<String, BigDecimal> returnMap2=this.specialDeal(inItems1TotalMoney, inItems2TotalMoney,outItems, inItems1, inItems2, "1", "1");
		inItems1TotalMoney=returnMap2.get("inItems1TotalMoney");
		inItems2TotalMoney=returnMap2.get("inItems2TotalMoney");
		//手续费
		Map<String, BigDecimal> returnMap3=this.specialDeal(inItems1TotalMoney, inItems2TotalMoney,outItems, inItems1, inItems2, "2", "2");
		inItems1TotalMoney=returnMap3.get("inItems1TotalMoney");
		inItems2TotalMoney=returnMap3.get("inItems2TotalMoney");*/
			
		ExcelUtils.addValue("inItems1", inItems1);
		ExcelUtils.addValue("inItems2", inItems2);
		ExcelUtils.addValue("outItems", outItems);
		reportMap.put("inItems1TotalMoney", inItems1TotalMoney);
		reportMap.put("inItems2TotalMoney", inItems2TotalMoney);
		reportMap.put("outItemsTotalMoney", outItemsTotalMoney);
		ExcelUtils.addValue("reportMap", reportMap);
		ExcelUtils.addValue("moneyTypes", ListUtil.getListOptions(companyCode, "fibankbooktemp.moneytype"));
		
		ExcelUtils.addService("langTool", LocaleUtil.class);
		
		String config = "/WEB-INF/xls/jfiBankbookDayReport.xls";

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + FileUtil.encodeFileName(getText("menu.fi.fiBankbookDayReport"), request) + "_"+reportDate+".xls\"");
		// 输出Excel
		ExcelUtils.export(request.getSession().getServletContext(), config, response.getOutputStream());
		return null;
	}
	
	/**
	 * 将转出的条目中对应的类别从收款净额中减少,同时在借方收款存入数中加一条记录
	 * @param outItems
	 * @param inItems1
	 * @param inItems1TotalMoney
	 * @param inItems2
	 * @param inItems2TotalMoney
	 * @param outMoneyType
	 * @param inMoneyType
	 */
	private Map<String, BigDecimal> specialDeal(BigDecimal inItems1TotalMoney, BigDecimal inItems2TotalMoney, List outItems, List inItems1, List inItems2, String outMoneyType, String inMoneyType){
		if(outItems!=null && outItems.size()>0){
			for(int i=0;i<outItems.size();i++){
				HashMap outItem=(HashMap)outItems.get(i);
				if(outMoneyType.equals(outItem.get("money_type"))){
					BigDecimal outMoney22=new BigDecimal(outItem.get("total_money").toString());
					//往确认收款入电子存折中的对应条目中增加此条目
					boolean existIn=false;
					if(inItems1!=null && inItems1.size()>0){
						for(int j=0;j<inItems1.size();j++){
							HashMap inItem=(HashMap)inItems1.get(j);
							if(inMoneyType.equals(inItem.get("money_type"))){
								BigDecimal oldTotalMoney=new BigDecimal(inItem.get("total_money").toString());
								inItem.put("total_money", oldTotalMoney.subtract(outMoney22).toString());
								existIn=true;
								break;
							}
						}
					}
					//如果不存在
					if(!existIn){
						HashMap<String, String> inItemMap=new HashMap<String, String>();
						inItemMap.put("deal_type", "A");
						inItemMap.put("money_type", inMoneyType);
						inItemMap.put("total_money", "-"+outMoney22.toString());
						
						inItems1.add(inItemMap);
					}
					inItems1TotalMoney=inItems1TotalMoney.subtract(outMoney22);
					
					//往存折借方增加记录
					HashMap<String, String> inItemMap=new HashMap<String, String>();
					inItemMap.put("deal_type", "D");
					inItemMap.put("money_type", outMoneyType);
					inItemMap.put("total_money", outMoney22.toString());
					
					inItems2.add(inItemMap);
					inItems2TotalMoney=inItems2TotalMoney.add(outMoney22);
					
					break;
				}
			}
		}
		Map<String, BigDecimal> returnMap=new HashMap<String, BigDecimal>();
		returnMap.put("inItems1TotalMoney", inItems1TotalMoney);
		returnMap.put("inItems2TotalMoney", inItems2TotalMoney);
		return returnMap;
	}
}