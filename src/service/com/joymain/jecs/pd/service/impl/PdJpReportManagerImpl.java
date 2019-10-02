package com.joymain.jecs.pd.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.pd.dao.PdJpReportDao;
import com.joymain.jecs.pd.service.PdJpReportManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;

public class PdJpReportManagerImpl extends BaseManager implements PdJpReportManager {
	private PdJpReportDao pdJpReportDao;
	private Map<String, Long> map;
	
	public void setPdJpReportDao(PdJpReportDao pdJpReportDao) {
		this.pdJpReportDao = pdJpReportDao;
	}

	public List getJpReportByCrm(CommonRecord crm) {
		String sOrderTime = crm.getString("sOrderTime", "");
		String eOrderTime = crm.getString("eOrderTime", "");
		List allList = pdJpReportDao.getJpReport(sOrderTime, eOrderTime);
		List timeList = pdJpReportDao.getTimeList(sOrderTime, eOrderTime);
		if (allList != null && allList.size() > 0) {
			final String noFee = "0";
			final String wuliufee = "1";
			final String shouxufee = "2";
			final String xiaofeifee = "3";
			for (int i = 0; i< allList.size(); i++) {
				BigDecimal hejifee = new BigDecimal(0);
				Map allMap = (Map) allList.get(i);
				String userCode = (String) allMap.get("user_code");
				String fee = (String) allMap.get("fee");
				String feeType = (String) allMap.get("fee_type");
				String amount = (String) allMap.get("amount");
				allMap.put("indexOfList", i + 1);
				hejifee = hejifee.add(new BigDecimal(amount));
				hejifee = hejifee.add(new BigDecimal(650));
				//hejifee += Long.parseLong(amount);
				
				//1,物流 2,手续费 3,税
				//'wuliufee' As Wuliufee,  
				// 'xiaofeifee' as xiaofeifee, 'shouxufee' as shouxufee, 'hejifee' as hejifee
//				if (feeType.equals(wuliufee) && !fee.equals(noFee)) {
//					allMap.put("wuliufee", fee);
//					hejifee = hejifee.add(new BigDecimal(fee));
//					//hejifee += Long.parseLong(fee);
//				} else {
//					allMap.put("wuliufee", noFee);
//				}
//				if (feeType.equals(shouxufee) && !fee.equals(noFee)) {
//					allMap.put("shouxufee", fee);
//					hejifee = hejifee.add(new BigDecimal(650));
//					//hejifee += Long.parseLong(fee);
//				} else {
//					allMap.put("shouxufee", 650);
//				}
//				if (feeType.equals(xiaofeifee) && !fee.equals(noFee)) {
//					allMap.put("xiaofeifee", fee);
//					hejifee = hejifee.add(new BigDecimal(fee));
//					//hejifee += Long.parseLong(fee);
//				} else {
//					allMap.put("xiaofeifee", noFee);
//				}
				allMap.put("shouxufee", "650");
				allMap.put("hejifee", hejifee);
				
				//根据“会员编码”查找该会员首次登录的时间
				if (timeList != null && timeList.size() > 0) {
					for (int j = 0; j < timeList.size(); j++) {
						Map timeMap = (Map) timeList.get(j);
						String uCode = (String) timeMap.get("user_code");
						String firstlogintime = (String) timeMap.get("firstlogintime");
						if (userCode.equals(uCode)) {
							allMap.put("firstlogintime", firstlogintime);
						}
					}
				}
				
			}
			
		}
		
		return allList;
	}
	
	/**
	 * 菲律宾报表
	 */
	public List getPhReportByCrm(CommonRecord crm) {
		String sCreateTime = crm.getString("sCreateTime", "");
		String eCreateTime = crm.getString("eCreateTime", "");
		List allList = pdJpReportDao.getPhReport(sCreateTime, eCreateTime);
		List proNoList = pdJpReportDao.getProNo(sCreateTime, eCreateTime);
		if (allList != null && allList.size() > 0) {
			final String huoYun = "0";//货运
			final String zhiTi = "1";//自提
			final String yes = "YES";
			final String premium = "P08060400101EN0";
			final String pantiliner = "P08060500101EN0";
			final String energy = "P05010200101EN0";
			long premiumQty = 0;
			long pantilinerQty = 0;
			long energyQty = 0;
			long premiumHuoYun = 0;
			long pantilinerHuoYun = 0;
			long energyHuoYun = 0;
			long premiumZhiTi = 0;
			long pantilinerZhiTi = 0;
			long energyZhiTi = 0;
			for (int i = 0; i< allList.size(); i++) {
				Map allMap = (Map) allList.get(i);
				String shipType = (String) allMap.get("ship_type");
				String mainSiNo = (String) allMap.get("si_no");
				
				if (shipType.equals(huoYun)) {
					allMap.put("huoyun", yes);
				}
				if (shipType.equals(zhiTi)) {
					allMap.put("zhiti", yes);
				}
				
				if (proNoList != null && proNoList.size() > 0) {
					for (int j = 0; j < proNoList.size(); j++) {
						Map proMap = (Map) proNoList.get(j);
						String siNo = (String) proMap.get("si_no");
						String productNo = (String) proMap.get("product_no");
						String qty = (String) proMap.get("qty") != null ? (String) proMap.get("qty") : "0";
						if (mainSiNo.equals(siNo)) {
							if (productNo.equals(premium)) {
								allMap.put("premium_qty", qty);
								premiumQty += Long.parseLong(qty);
								if (shipType.equals(huoYun)) {
									premiumHuoYun += Long.parseLong(qty);
								}
								if (shipType.equals(zhiTi)) {
									premiumZhiTi += Long.parseLong(qty);
								}
							}
							if (productNo.equals(pantiliner)) {
								allMap.put("pantiliner_qty", qty);
								pantilinerQty += Long.parseLong(qty);
								if (shipType.equals(huoYun)) {
									pantilinerHuoYun += Long.parseLong(qty);
								}
								if (shipType.equals(zhiTi)) {
									pantilinerZhiTi += Long.parseLong(qty);
								}
							}
							if (productNo.equals(energy)) {
								allMap.put("energy_qty", qty);
								energyQty += Long.parseLong(qty);
								if (shipType.equals(huoYun)) {
									energyHuoYun += Long.parseLong(qty);
								}
								if (shipType.equals(zhiTi)) {
									energyZhiTi += Long.parseLong(qty);
								}
							}
						}
					}
				}
			}
			this.setQty(premiumQty, pantilinerQty, energyQty, premiumHuoYun, pantilinerHuoYun,
					energyHuoYun, premiumZhiTi, pantilinerZhiTi, energyZhiTi);
		}
		
		return allList;
	}

	private void setQty(
			long premiumQty,
			long pantilinerQty,
			long energyQty,
			long premiumHuoYun,
			long pantilinerHuoYun,
			long energyHuoYun,
			long premiumZhiTi,
			long pantilinerZhiTi,
			long energyZhiTi) {
		map = new HashMap<String, Long>();
		map.put("premiumQty", premiumQty);
		map.put("pantilinerQty", pantilinerQty);
		map.put("energyQty", energyQty);
		map.put("premiumHuoYun", premiumHuoYun);
		map.put("pantilinerHuoYun", pantilinerHuoYun);
		map.put("energyHuoYun", energyHuoYun);
		map.put("premiumZhiTi", premiumZhiTi);
		map.put("pantilinerZhiTi", pantilinerZhiTi);
		map.put("energyZhiTi", energyZhiTi);
	}
	
	public Map<String, Long> getQty() {
		return map;
	}
}
