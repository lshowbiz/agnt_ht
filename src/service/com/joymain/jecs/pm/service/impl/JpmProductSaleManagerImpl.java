package com.joymain.jecs.pm.service.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pm.dao.JpmProductDao;
import com.joymain.jecs.pm.dao.JpmProductSaleDao;
import com.joymain.jecs.pm.dao.JpmSalePromoterDao;
import com.joymain.jecs.pm.model.JmiMemberTeam;
import com.joymain.jecs.pm.model.JpmProductSaleNew;
import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.pm.model.JpmSalePromoter;
import com.joymain.jecs.pm.model.JpmSalepromoterPro;
import com.joymain.jecs.pm.service.JmiMemberTeamManager;
import com.joymain.jecs.pm.service.JpmProductSaleManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.date.DateUtil;

public class JpmProductSaleManagerImpl extends BaseManager implements
		JpmProductSaleManager {
	
	private JpmSalePromoterDao jpmSalePromoterDao;
	private JpmProductSaleDao dao;
	private JpmProductDao jpmProductDao;
	private JmiMemberTeamManager jmiMemberTeamManager;
	
	public List getAlCompanyNotSaled(String productNo) {
		return dao.getAlCompanyNotSaled(productNo);
	}
	
	public List getProductSales(String companyCode, String type, String status)
			throws Exception {
		
		CommonRecord crm = new CommonRecord(); 
		List list = new ArrayList();
		if ("1".equals(status)) {
			crm.setValue("teamCode", "8888888888");
		}  else{
			crm.setValue("teamCode",status);
		}
		crm.setValue("companyCode",companyCode);
		crm.setValue("orderType",type);
		crm.setValue("status", "1");//只有在售商品才能看到
		list = this.getJpmProductSalesByCrmNew(crm, null);
		return list;
	}
	
	
	/**
	 * Add By WuCF 20140617 
	 * 得到换货单换货的商品信息
	 * @param companyCode
	 * @param type
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List getExProductSales(String companyCode, String type, String status)
		throws Exception {

		CommonRecord crm = new CommonRecord(); 
		List list = new ArrayList();
		if ("1".equals(status) || status==null || "".equals(status)) {//默认为中脉团队
			crm.setValue("teamCode", "8888888888");
		}  else{
			crm.setValue("teamCode",status);
		}
		//Modify By WuCF 20140620 如果是会员收购单，则将辅消单订单的商品也查询出来
		if("1".equals(type)){
			type = "1,5";
		}
		
		crm.setValue("companyCode",companyCode);
		crm.setValue("orderType",type);
		crm.setValue("status", "1,2");//只有在售商品才能看到
		list = this.getExJpmProductSalesByCrmNew(crm, null);
		return list;
	}
	
	
	//销售管理新增的方法 :所有的辅销品数据
	@Override
	public List getJpoCounterProductSales(String companyCode) throws Exception {
		return dao.getJpoCounterProductSales(companyCode);
	}

	/**
	 * @see com.joymain.jecs.pm.service.JpmProductSaleManager#removeJpmProductSale(String
	 *      uniNo)
	 */
	public void removeJpmProductSale(final String uniNo) {
		dao.removeJpmProductSale(new Long(uniNo));
	}

	

	/**
     * Add By WuCF 20130523 
     * Gets jpmProductSale's JmiMemberTeams information based on uniNo. 
     */
	public List<HashMap<String, String>> getJmiMemberTeams(ResultSet rs) {
		return dao.getJmiMemberTeams(rs);
	}

	/**
     * Add By WuCF 20130523 
     * Gets jpmProductSale's JmiMemberTeams information based on uniNo. 
     */
	public JmiMemberTeam getJmiMemberTeam(String code) {
		
		return dao.getJmiMemberTeam(code);
	}
	
	
	
	/**********************************************************************************/
	/**
     * Add By WuCF 20130916 商品新表相关，通过UNI_NO查询指定商品对象 
     * @param uniNo：商品uni_no主键
     * @return
     */
	public JpmProductSaleNew getJpmProductSaleNew(final String uniNo) {
		return dao.getJpmProductSaleNew(new Long(uniNo));
	}
	
	 /**
     * Add By WuCF 20130916 商品新表相关，通过companyCode和product_no查询指定商品对象 
     * @param companyCode：分公司编码
     * @param productNo：商品编码
     * @return
     */
	public JpmProductSaleNew getJpmProductSaleNew(String companyCode, String productNo) {
	
		return dao.getJpmProductSaleNew(companyCode, productNo);
	}
	
	/**
     * Add By WuCF 20130916 商品团队类型对象
     * @param pttId：主键pttId
     * @return
     */
	public JpmProductSaleTeamType getJpmProductSaleTeamType(String pttId) {
		return dao.getJpmProductSaleTeamType(pttId);
	}
		
	/**
     * Add By WuCF 20130916 商品团队类型对象     
     * @param companyCode：分公司编码
     * @param productNo：商品编码     
     * @param orderType：订单类型
     * @param teamCode：团队类型
     * @return
     */
	public JpmProductSaleTeamType getJpmProductSaleTeamType(String companyCode,String productNo,String orderType,String teamCode) {
		return dao.getJpmProductSaleTeamType(companyCode,productNo,orderType, teamCode);
	}


	/**
     * Add By WuCF 20130916 获得商品新表对象数据
     * @param companyCode：分公司编码
     * @param type：订单类型
     * @param status：类型
     * @return
     * @throws Exception
     */
	public Map<String, List<JpmProductSaleTeamType>> getPmProductSalesNew(
			String companyCode, String type, String status) throws Exception {
		Map<String, List<JpmProductSaleTeamType>> retMap = new LinkedMap();
		
		
	
		return retMap;
	}
	
	public Map<String, List<JpmProductSaleNew>> getBackPmProductSalesNew(
			String companyCode, String type, String status) throws Exception {
		Map<String, List<JpmProductSaleNew>> retMap = new LinkedMap();
		
		CommonRecord crm = new CommonRecord();
		
	
		return retMap;
	}
	
	/**
     * Add By WuCF 20130916 获得商品新表对象数据
     * @param companyCode：分公司编码
     * @param type：订单类型
     * @param status：类型
     * @param teamCode：团队编码
     * @return
     * @throws Exception
     */
	public Map<String, List<JpmProductSaleTeamType>> getPmProductSalesNew(
			String companyCode, String orderType,String status,String teamCode,SysUser user) throws Exception {
		
		Map<String, List<JpmProductSaleTeamType>> map = dao.
				getPmProductSalesNew(companyCode, orderType, status, teamCode);
		try{
			/*
			 * TODO Jun 按商品打折促销
			 */
	    	String stime = DateUtil.convertDateToString(Calendar.getInstance().getTime());
	    	List<JpmSalePromoter> salePromoterList = jpmSalePromoterDao.
	    			getSaleByDate(stime, Constants.JPMSALE_ACTIVA, 
	    					Constants.JPMSALE_SALETYPE_DISCOUNT);
	    	
	    	JpoMemberOrder order = new JpoMemberOrder();
	    	order.setCompanyCode(companyCode);
	    	order.setOrderType(orderType);
	    	order.setTeamCode(teamCode);
	    	order.setSysUser(user);
	    	
	    	Iterator<String>  iter = map.keySet().iterator();
			while(iter.hasNext()){
				String key = iter.next();
				List<JpmProductSaleTeamType> psList = map.get(key);
				for(JpmProductSaleTeamType sat: psList){
					String proNo = sat.getJpmProductSaleNew().
							getJpmProduct().getProductNo();
					BigDecimal price = sat.getPrice();
					
					for(JpmSalePromoter sale: salePromoterList)
					{
						if(isOrderSales(sale, order))
						{
							Iterator<JpmSalepromoterPro> iterPro = sale.getSaleProductSet().iterator();
				    		while(iterPro.hasNext())
				    		{
				    			JpmSalepromoterPro pro = iterPro.next();
				    			String saleProNo = pro.getProno();
				    			if(proNo.equalsIgnoreCase(saleProNo)){
				    				//添加商品打折后的价格
				    				BigDecimal disPrice = price.multiply(sale.getDiscount());
				    				sat.setDiscountPrice(disPrice);
				    			}
				    		}
						}
			    	}
					
				}
			}
		}catch(Exception e){
			log.error("",e);
		}
		return map;
	}
	
	/**
     * Add By WuCF 20130916 获得指定分公司的商品团队类型信息
     * @param companyCode
     * @return
     */
	public Map getCompanyProductMapNew(String companyCode) {
		// TODO Auto-generated method stub
		Map retMap = new HashMap();
		CommonRecord crm = new CommonRecord();
		try {
			crm.setValue("companyCode", companyCode);
		} catch (Exception e) {
			log.error(e.fillInStackTrace());
		}
		List list = dao.getJpmProductSaleNewsByCrm(crm, null);

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				JpmProductSaleNew pmProductSaleNew = (JpmProductSaleNew) list.get(i);
//				retMap.put(pmProductSale.getJpmProduct().getProductNo(),
//						pmProductSale.getProductName());
				retMap.put(pmProductSaleNew.getJpmProduct().getProductNo(),
						pmProductSaleNew);
			}
		}
		return retMap;
	}
	
	/**
	 * Add By WuCF 20130916 批量修改状态
	 */
	public void batchUpdateStatusNew() {
	
		CommonRecord crm = new CommonRecord();
		try {
			crm.setValue("companyCode", "CN");
			crm.setValue("status", "1,2");
			crm.setValue("saleControl", "1");//停售
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<JpmProductSaleNew> jpmProductSales = dao.getJpmProductSaleNewsByCrm(crm,null);
		if(jpmProductSales != null){
			for(JpmProductSaleNew jpmProductSale:jpmProductSales){
				jpmProductSale.setStatus("0");
				dao.saveJpmProductSaleNew(jpmProductSale);
			}
			
		}
	}
	
	/**
	 * Add By WuCF 20130916 商品新表数据是否存在
	 * @param companyCode
	 * @param productNo
	 * @return
	 */
	public boolean existProductNoNew(String companyCode, String productNo) {
		JpmProductSaleNew pmProductSaleNew = dao.getJpmProductSaleNew(companyCode,
				productNo);
		if (pmProductSaleNew == null) {
			return false;
		} else {
			return true;
		}

	}
	
	// added for getJpmProductSalesByCrm
	public List getJpmProductSalesByCrm(CommonRecord crm, Pager pager) {
		return dao.getJpmProductSalesByCrm(crm, pager);
	}
	
	/**
	 * Add By WuCF 20130916 得到商品团队类型数据
	 * @param companyCode
	 * @param productNo
	 * @return
	 */
	public List getJpmProductSalesByCrmNew(CommonRecord crm, Pager pager) {
		return dao.getJpmProductSalesByCrmNew(crm, pager);
	}
	 
	/**
	 * Add By WuCF 20130916 得到换货单商品团队类型数据
	 * @param companyCode
	 * @param productNo
	 * @return
	 */
	public List getExJpmProductSalesByCrmNew(CommonRecord crm, Pager pager) {
		return dao.getExJpmProductSalesByCrmNew(crm, pager);
	}
	 
	
	public Map<String, JpmProductSaleTeamType> getProductSaleMapNew(String companyCode) {
		Map<String, JpmProductSaleTeamType> retMap = new HashMap<String, JpmProductSaleTeamType>();
		CommonRecord crm = new CommonRecord();
		try {
			crm.setValue("companyCode", companyCode);
		} catch (Exception e) {
			log.error(e.fillInStackTrace());
		}
		List list = dao.getJpmProductSalesByCrmNew(crm, null);

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				JpmProductSaleTeamType pmProductSale = (JpmProductSaleTeamType) list.get(i);
				retMap.put(pmProductSale.getJpmProductSaleNew().getJpmProduct().getProductNo(),
						pmProductSale);
			}
		}
		return retMap;
	}
	
	/**
	 * 得到JpmProductSaleNew
	 */
	public Map<String, JpmProductSaleNew> getProductSaleMapNew2(CommonRecord crm,String companyCode) {
		Map<String, JpmProductSaleNew> retMap = new HashMap<String, JpmProductSaleNew>();
		//CommonRecord crm = new CommonRecord();
		try {
			crm.setValue("companyCode", companyCode);
		} catch (Exception e) {
			log.error(e.fillInStackTrace());
		}
		List list = dao.getJpmProductSalesByCrm(crm, null);

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				JpmProductSaleNew pmProductSale = (JpmProductSaleNew) list.get(i);
				retMap.put(pmProductSale.getJpmProduct().getProductNo(),
						pmProductSale);
			}
		}
		return retMap;
	}
	/**
	 * 根据国别,团队,订单类型判断是否参与促销
	 * @param sp
	 * @param order
	 * @return
	 * @throws Exception
	 */
	private boolean isOrderSales(JpmSalePromoter sp,JpoMemberOrder order) throws Exception{
		boolean flag = false;
		try{
			String sale_teams = sp.getTeamno();
			String sale_countrys = sp.getCompanyno();
			String sale_orders = sp.getOrdertype();
			
			String order_comCode = order.getCompanyCode();
			String order_type = order.getOrderType();
			String order_userCode = jmiMemberTeamManager.getTeamStr(order.getSysUser());
			
			log.info("saleTeams is:["+sale_teams+"] and order Team is:["+order_userCode+"] " +
					"and country is:["+sale_countrys+"] and order company:["+order_comCode+"] " +
					"and sale_orders is:["+sale_orders+"] and orderType is:["+order_type+"]");
			
			if(! StringUtils.isNotBlank(sale_teams)) 
				sale_teams = "";
				
			
			
			if(sale_countrys.indexOf(order_comCode) != -1 && 
					sale_teams.indexOf(order_userCode) <0 && 
					sale_orders.indexOf(order_type)!=-1){
				flag = true;
			}
			
		}catch(Exception e){
			throw e;
		}
		log.info("orderNo ["+order.getMemberOrderNo()+"] isOrderSales return is:"+flag);
		return flag;
	}
	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setJpmProductSaleDao(JpmProductSaleDao dao) {
		this.dao = dao;
	}
	
	
	public JpmSalePromoterDao getJpmSalePromoterDao() {
		return jpmSalePromoterDao;
	}
	public void setJpmSalePromoterDao(JpmSalePromoterDao jpmSalePromoterDao) {
		this.jpmSalePromoterDao = jpmSalePromoterDao;
	}

	public JpmProductDao getJpmProductDao() {
		return jpmProductDao;
	}

	public void setJpmProductDao(JpmProductDao jpmProductDao) {
		this.jpmProductDao = jpmProductDao;
	}

	public JmiMemberTeamManager getJmiMemberTeamManager() {
		return jmiMemberTeamManager;
	}

	public void setJmiMemberTeamManager(JmiMemberTeamManager jmiMemberTeamManager) {
		this.jmiMemberTeamManager = jmiMemberTeamManager;
	}

	
	
	/**
     * 换货单查询换货商品
     * @author fx 2015-08-10
     * @param crm
     * @param pager
     * @return
	 * @throws Exception 
     */
	private List getExJpmProductSalesByCrmNewTwo(CommonRecord crm, Pager pager) {
		return dao.getExJpmProductSalesByCrmNewTwo(crm, pager);
	}

	/**
     * 换货单查询换货商品
     * @author gw 2015-06-19
     * @param crm
     * @param pager
     * @return
	 * @throws Exception 
     */
	public List getExProductSalesTwo(CommonRecord crm, Pager pager) throws Exception{
		//String companyCode, String type, String status
	       String status = crm.getString("teamCode", "");
	       String type = crm.getString("orderType", "");
			List list = new ArrayList();
			if ("1".equals(status) || null== status || "".equals(status)) {//默认为中脉团队
				crm.setValue("teamCode", "8888888888");
			}  else{
				crm.setValue("teamCode",status);
			}
			//Modify By WuCF 20140620 如果是会员收购单，则将辅消单订单的商品也查询出来
			if("1".equals(type)){
				type = "1,5";
			}
			
			crm.setValue("orderType",type);
			crm.setValue("status", "1,2");//只有在售商品才能看到
			list = this.getExJpmProductSalesByCrmNewTwo(crm, pager);
			return list;
	}
	
}
