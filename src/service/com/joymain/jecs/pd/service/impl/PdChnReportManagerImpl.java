package com.joymain.jecs.pd.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.dao.PdChnReportDao;
import com.joymain.jecs.pd.service.PdChnReportManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;

public class PdChnReportManagerImpl extends BaseManager implements PdChnReportManager {
	private PdChnReportDao pdChnReportDao;
	private Map<String, Long> map;
	
	public void setPdChnReportDao(PdChnReportDao pdChnReportDao) {
		this.pdChnReportDao = pdChnReportDao;
	}

	
	public List getChnReportByCrm(CommonRecord crm) {
		//获取商品名字的list
		List productList = pdChnReportDao.getChnReport();
		
		//获取商品数量的list
		List qtyList = pdChnReportDao.getChnQtyReport();
		
		//北京的仓库号：BJRDC
		final String bj = "BJRDC";
		
		//武汉的仓库号：WHRDC
		final String wh = "WHRDC";
		
		//广州的仓库号：GZRDC2+GZRDC
		final String[] gz = {"GZRDC", "GZRDC2"};
		
		//新疆的仓库号：WLMQRDC2+WLMQRDC
		final String[] xj = {"WLMQRDC", "WLMQRDC2"};
		
		//南京的仓库号：NJRDC5+NJRDC4+NJRDC3+NJRDC2+NJRDC1+NJCDC,XNC
		final String[] nj = {"NJCDC", "NJRDC1", "NJRDC2", "NJRDC3", "NJRDC4", "NJRDC5", "XNC"};
		
		//重庆的仓库号：QCRDC
		final String[] cq = {"QCRDC"};
		
		//其他的仓库号：BMDC, DCL3, DCL2, JSXNC
		final String[] others = {"BMDC", "DCL3", "DCL2", "JSXNC"};
		
		//分公司的仓库号：
		//ZJFGS, XJFGS, WHFGS, SZFGS, SHFGS, SDFGS, NBFGS, LNFGS, JSFGS, HLJFGS, GDFGS, DLFGS, CQFGS, BJFGS
		final String[] subCompany = {"ZJFGS", "XJFGS", "WHFGS", "SZFGS", "SHFGS", 
				"SDFGS", "NBFGS", "LNFGS", "JSFGS", "HLJFGS", "GDFGS", "DLFGS", "CQFGS", "BJFGS"};

		//DCL的仓库号：DCL
		final String dcl = "DCL";
		
		if (productList != null && productList.size() > 0) {
			for (int i = 0; i < productList.size(); i++) {
				//北京：BJRDC,库存量
				int bjQty = 0;
				//武汉：WHRDC,库存量
				int whQty = 0;
				//广州：GZRDC2+GZRDC,库存量
				int gzQty = 0;
				//新疆：WLMQRDC2+WLMQRDC,库存量
				int xjQty = 0;
				//南京：NJRDC5+NJRDC4+NJRDC3+NJRDC2+NJRDC1+NJCDC,库存量
				int njQty = 0;
				
				//重庆的库存量：QCRDC
				int cqQty = 0;
				
				//其他的库存量：BMDC, DCL3, DCL2, JSXNC
				int othersQty = 0;
				
				//分公司的库存量：
				int subCompanyQty = 0;
				
				
				//DCL,库存量
				int dclQty = 0;
				int totalQty = 0;
				
				Map productMap = (Map) productList.get(i);
				String productNo = (String) productMap.get("product_no");
				
				if (qtyList != null && qtyList.size() > 0) {
					for (int j = 0; j < qtyList.size(); j++) {
						Map qtyMap = (Map) qtyList.get(j);
						String productNoOfQty = (String) qtyMap.get("product_no");
						
						String tempQty = (String) qtyMap.get("normal_qty");
						int normalQty = Integer.parseInt(tempQty);
						String warehouseNo = (String) qtyMap.get("warehouse_no");
						if (productNo.equals(productNoOfQty)) {
							if (warehouseNo.equals(bj)) {
								bjQty += normalQty;
							}
							if (warehouseNo.equals(wh)) {
								whQty += normalQty;
							}
							for (int gzIndex = 0; gzIndex < gz.length; gzIndex++) {
								if (warehouseNo.equals(gz[gzIndex])) {
									gzQty += normalQty;
								}
							}
							for (int xjIndex = 0; xjIndex < xj.length; xjIndex++) {
								if (warehouseNo.equals(xj[xjIndex])) {
									xjQty += normalQty;
								}
							}
							for (int njIndex = 0; njIndex < nj.length; njIndex++) {
								if (warehouseNo.equals(nj[njIndex])) {
									njQty += normalQty;
								}
							}
							
							for (int cqIndex = 0; cqIndex < cq.length; cqIndex++) {
								if (warehouseNo.equals(cq[cqIndex])) {
									cqQty += normalQty;
								}
							}
							
							for (int othersIndex = 0; othersIndex < others.length; othersIndex++) {
								if (warehouseNo.equals(others[othersIndex])) {
									othersQty += normalQty;
								}
							}
							
							for (int subCompanyIndex = 0; subCompanyIndex < subCompany.length; subCompanyIndex++) {
								if (warehouseNo.equals(subCompany[subCompanyIndex])) {
									subCompanyQty += normalQty;
								}
							}
							
							/*if (warehouseNo.equals(dcl)) {
								dclQty += normalQty;
							}*/
						}
					} 
				}
				totalQty += bjQty + whQty + gzQty + xjQty + njQty + cqQty + othersQty + subCompanyQty;
//				totalQty += bjQty + whQty + gzQty + xjQty + njQty + dclQty;
				productMap.put("bjQty", bjQty);
				productMap.put("whQty", whQty);
				productMap.put("gzQty", gzQty);
				productMap.put("xjQty", xjQty);
				productMap.put("njQty", njQty);
				productMap.put("cqQty", cqQty);
				productMap.put("othersQty", othersQty);
				productMap.put("subCompanyQty", subCompanyQty);
				//productMap.put("dclQty", dclQty);
				productMap.put("totalQty", totalQty);
			}
		}
		
		return productList;
	}

	@Override
	public Object getObject(Class clazz, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getObjects(Class clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeObject(Class clazz, Serializable id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveObject(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDao(Dao dao) {
		// TODO Auto-generated method stub

	}

}
