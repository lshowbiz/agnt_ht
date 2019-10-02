package com.joymain.jecs.po.webapp.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.model.JalTown;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.al.service.JalTownManager;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.ConfigUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JpoMemberOrderStatisticAllController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(JpoMemberOrderStatisticController.class);

	private JpoMemberOrderManager jpoMemberOrderManagerReport = null;

	private AlCityManager alCityManager;

	private AlDistrictManager alDistrictManager;

	private AlCountryManager alCountryManager;
	private AlStateProvinceManager alStateProvinceManager; 
	
	private JalTownManager jalTownManager;

	public void setAlCityManager(AlCityManager alCityManager) {
		this.alCityManager = alCityManager;
	}

	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	public void setAlDistrictManager(AlDistrictManager alDistrictManager) {
		this.alDistrictManager = alDistrictManager;
	}

	public void setJalTownManager(JalTownManager jalTownManager) {
		this.jalTownManager = jalTownManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		if(RequestUtil.saveOperationSession(request,"poMemberOrderStatisticAll",20l)!=0){
			return new ModelAndView("redirect:jpoMemberOrderStatistic.html");
		}
		try{
			SysUser loginSysUser = SessionLogin.getLoginUser(request);
			
			CommonRecord crm = RequestUtil.toCommonRecord(request);
			crm.addField("companyCode", loginSysUser.getCompanyCode());
			if (loginSysUser.getIsMember()) {
				crm.addField("sysUser.userCode", loginSysUser.getUserCode());
			}
			
//			List<JpoMemberOrder> jpoMemberOrders = jpoMemberOrderManagerReport.
//					getJpoMemberOrdersByCrm(crm, null);

			String proInfo = request.getParameter("proInfo");
			request.setAttribute("proInfo", proInfo);
			log.info("proInfo is:"+proInfo);
			
			//Modify By WuCF 20140514  查询记录条数，控制记录条数
			Integer listNum = Integer.parseInt(ConfigUtil.getConfigValue(loginSysUser.getCompanyCode(), "export.listnum"));
			crm.setValue("listNum", listNum);
			List jpoMemberOrders = jpoMemberOrderManagerReport.getJpoMemberOrdersByCrm(crm, null);
			log.info("===============jpoMemberOrders集合数据大小："+jpoMemberOrders.size());
			//判断结果集合是否超过限度
			String key = "";
			if(jpoMemberOrders!=null && jpoMemberOrders.get(0)!=null && "1".equals(jpoMemberOrders.get(0).toString())){
				JpoMemberOrder jmo = new JpoMemberOrder();
				key = "导出数据数量超过最大限制数："+listNum+"，导出失败！请缩小查询条件范围！";
				jmo.setMoId(Long.parseLong(listNum.toString()));
				jmo.setIsRetreatOrder(key);
				jpoMemberOrders = new ArrayList();
				jpoMemberOrders.add(jmo);
			}else{
				if("1".equals(proInfo)){//导出报表包含明细
					Integer total = jpoMemberOrders.size();
					JpoMemberOrder jmoTemp = null;
					for(Object obj : jpoMemberOrders){
						jmoTemp = (JpoMemberOrder)obj;
						if(jmoTemp.getJpoMemberOrderList()!=null){
							total += jmoTemp.getJpoMemberOrderList().size();
						}
					}
					log.info("=====导出订单&商品数据量："+total);
					if(total>=65530){
						JpoMemberOrder jmo = new JpoMemberOrder();
						key = "导出订单数据(包含商品)数据量太大："+total+"，导出失败！请缩小查询条件范围！";
						jmo.setMoId(Long.parseLong(listNum.toString()));
						jmo.setIsRetreatOrder(key);
						jpoMemberOrders = new ArrayList();
						jpoMemberOrders.add(jmo);
					}
				}
			}
			
			bindProvince(jpoMemberOrders);
			
			
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename=orderInfo.xls");
			
			log.info("jpoMemberOrders list size is:"+jpoMemberOrders.size());
			if(!"".equals(key)){
				MessageUtil.saveMessage(request, key);  
			}
			
			return new ModelAndView("po/expOrder","jpoMemberOrderList", jpoMemberOrders);
		}catch(Exception e){
			log.error("",e);
			return null;
		}
		
	}
	
	private String periodDate(Date checkDate){
	
		long dateLong = checkDate.getTime();
		for (int i = 0 ; i < Constants.periodList.size() ; i++){
			BdPeriod bdPeriod = (BdPeriod)Constants.periodList.get(i);
			long startTime = bdPeriod.getStartTime().getTime();
			long endTime = bdPeriod.getEndTime().getTime();
			if(dateLong>=startTime && dateLong<endTime){
				String strPeriod = "";
				if(bdPeriod.getFmonth().toString().length()==2){
					strPeriod = bdPeriod.getFyear().toString() +bdPeriod.getFmonth().toString();
				} else {
					strPeriod = bdPeriod.getFyear().toString() +"0"+bdPeriod.getFmonth().toString();
				}
				if(bdPeriod.getFweek().toString().length()==2){
					strPeriod += bdPeriod.getFweek().toString();
				}else{
					strPeriod += "0" + bdPeriod.getFweek().toString();
				}
				return strPeriod;
			}
		}
		return null;
	}
	
	
	/**
	 * 绑定省份城市名称
	 * @param jpoMemberOrders
	 * @throws Exception
	 */
	private void bindProvince(List<JpoMemberOrder> jpoMemberOrders)throws Exception{
		
		for (int i = 0; i < jpoMemberOrders.size(); i++) {
			
			JpoMemberOrder order = (JpoMemberOrder) jpoMemberOrders.get(i);
			String orderProvince = order.getProvince();
			String orderCity = order.getCity();
			String orderDis = order.getDistrict();
			String orderTown = order.getTown();
			String orderType = order.getOrderType();
			String status = order.getStatus();
			//String period = periodDate(order.getCheckDate());
			switch(Integer.parseInt(status)){
				case 1:
					order.setStatus(LocaleUtil.
							getLocalText("pdReturnPurchase.orderflag.0"));
					break;
				case 2:
					order.setStatus(LocaleUtil.
							getLocalText("error.poMemberOrder.audited"));
					break;
				default:
					order.setStatus(LocaleUtil.
							getLocalText("pr.returned"));
					break;
			}
			
			switch (Integer.parseInt(orderType)) {
				case 1:
					order.setOrderType(LocaleUtil.
							getLocalText("jpmSalePromoter.firstOrder"));
					break;
				case 2:
					order.setOrderType(LocaleUtil.
							getLocalText("miMemberUpgradeNote.updateRemark"));
					break;
				case 3:
					order.setOrderType(LocaleUtil.
							getLocalText("jpmSalePromoter.needOrder"));
					break;
				case 4:
					order.setOrderType(LocaleUtil.
							getLocalText("jpmSalePromoter.reOrder"));
					break;
				case 5:
					order.setOrderType(LocaleUtil.
							getLocalText("jpmSalePromoter.aidOrder"));
					break;
				case 6:
					order.setOrderType(LocaleUtil.
							getLocalText("jpmSalePromoter.shopFiOrder"));
					break;
				case 8:
					
					break;
				case 9:
					order.setOrderType(LocaleUtil.
							getLocalText("jpmSalePromoter.shopReOrder"));
					break;
				case 11:
					order.setOrderType(LocaleUtil.
							getLocalText("ordertype.11"));
					break;
				case 12:
					order.setOrderType(LocaleUtil.
							getLocalText("ordertype.12"));
					break;
				case 14:
					order.setOrderType(LocaleUtil.
							getLocalText("ordertype.14"));
					break;
				case 16:
					order.setOrderType(LocaleUtil.
							getLocalText("ordertype.16"));
					break;
				case 30:
					order.setOrderType(LocaleUtil.
							getLocalText("ordertype.30"));
					break;
				case 32:
					order.setOrderType(LocaleUtil.
							getLocalText("ordertype.32"));
					break;
				case 40:
					order.setOrderType(LocaleUtil.
							getLocalText("ordertype.40"));
					break;
				case 41:
					order.setOrderType(LocaleUtil.
							getLocalText("ordertype.41"));
					break;
				case 42:
					order.setOrderType(LocaleUtil.
							getLocalText("ordertype.42"));
					break;
				case 43:
					order.setOrderType(LocaleUtil.
							getLocalText("ordertype.43"));
					break;
				
				default:
					break;
			}
			
			if(StringUtils.isNotBlank(orderProvince)){
				AlStateProvince dbPro = alStateProvinceManager.getAlStateProvince(orderProvince);
				order.setProvince(dbPro.getStateProvinceName());
			}
			
			if(StringUtils.isNotBlank(orderCity)){
				AlCity city = alCityManager.getAlCity(orderCity);
				if(city!=null){
					order.setCity(city.getCityName());
				}else{
					order.setCity("未知城市编码："+orderCity);
				}
				
			}
			
			if(StringUtils.isNotBlank(orderDis)){
				AlDistrict alDistrict = alDistrictManager.getAlDistrict(orderDis);
				if(alDistrict!=null){
					order.setDistrict(alDistrict.getDistrictName());
				}else{
					order.setDistrict("未知县编码："+orderDis);
				}
			}
			
			if(StringUtils.isNotBlank(orderTown)){
				JalTown town = jalTownManager.getJalTown(orderTown);
				if(town!=null){
					order.setTown(town.getTownName());
				}else{
					order.setTown("未知镇编码："+orderTown);
				}
			}
		}
	}
	
	public void setJpoMemberOrderManagerReport(
			JpoMemberOrderManager jpoMemberOrderManagerReport) {
		this.jpoMemberOrderManagerReport = jpoMemberOrderManagerReport;
	}

	public AlStateProvinceManager getAlStateProvinceManager() {
		return alStateProvinceManager;
	}

	public void setAlStateProvinceManager(
			AlStateProvinceManager alStateProvinceManager) {
		this.alStateProvinceManager = alStateProvinceManager;
	}

	public JpoMemberOrderManager getJpoMemberOrderManagerReport() {
		return jpoMemberOrderManagerReport;
	}

	public AlCityManager getAlCityManager() {
		return alCityManager;
	}

	public AlDistrictManager getAlDistrictManager() {
		return alDistrictManager;
	}

	public AlCountryManager getAlCountryManager() {
		return alCountryManager;
	}

	public JalTownManager getJalTownManager() {
		return jalTownManager;
	}

}