package com.joymain.jecs.pd.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdExchangeOrder;
import com.joymain.jecs.pd.dao.PdExchangeOrderDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;


public class PdExchangeOrderDaoHibernate extends BaseDaoHibernate implements
		PdExchangeOrderDao {
	
	public List getTotalPdExchangeOrder(CommonRecord crm) {
		List list = new ArrayList();
		
		String sqlA =
		  " select t1.product_no BProductNo,t2.product_no DProductNo,nvl(t1.qty,0) bqty,nvl(t2.qty,0) dqty from" 
		+ " (select ps.product_no,sum(nvl(eob.qty,0)) qty from                           "
		+ " pd_exchange_order eo,pd_exchange_order_back eob,jpm_product_sale_new ps          "
		+ " where eo.eo_no=eob.eo_no and eob.product_no=ps.product_no and                "
		+ " eo.company_code=ps.company_code                                              ";
		/*加上查询条件hql  */
		String sqlB =
		  " group by ps.product_no) t1                                         "
		+ " full join                                                          "
		+ " (select ps.product_no,sum(nvl(eod.qty,0)) qty from                 "
		+ " pd_exchange_order eo,pd_exchange_order_detail eod,jpm_product_sale_new "
		+ " ps where eo.eo_no=eod.eo_no and eod.product_no=ps.product_no and   "
		+ " eo.company_code=ps.company_code                                    ";
		/*加上查询条件hql  */
		String sqlC = " group by ps.product_no) t2 on t1.product_no = t2.product_no";
		
		String hql = "";
		String defaultWarehouse = crm.getString("defaultWarehouse", "");
		if (StringUtils.isNotEmpty(defaultWarehouse)) {
			hql += " and eo.warehouse_No in(" + defaultWarehouse
					+ ")";
		}
		String eoNo = crm.getString("eoNo","");
		if (StringUtils.isNotEmpty(eoNo)) {
			hql += " and eo.eo_No ='" + eoNo + "'";
		}
		
		
		String orderNo = crm.getString("orderNo","");
		if (StringUtils.isNotEmpty(orderNo)) {
			hql += " and eo.order_No ='" + orderNo + "'";
		}
		String warehouseNo = crm.getString("warehouseNo", "");
		if (StringUtils.isNotEmpty(warehouseNo)) {
			hql += " and eo.warehouse_No ='" + warehouseNo + "'";
		}
		String hasCheckUsrCodeBlank = crm
				.getString("hasCheckUsrCodeBlank", "0");

		String createUsrCode = crm.getString("createUsrCode", "");
		if (StringUtils.isNotEmpty(createUsrCode)) {
			hql += " and eo.create_Usr_Code='" + createUsrCode
					+ "'";
		}
		String checkUsrCode = crm.getString("checkUsrCode", "");
		if (StringUtils.isNotEmpty(checkUsrCode)) {
			if (hasCheckUsrCodeBlank.equals("1")) {
				hql += " and eo.check_Usr_Code='" + checkUsrCode
						+ "' or eo.check_Usr_Code is null";
			} else {
				hql += " and eo.check_Usr_Code='" + checkUsrCode
						+ "' ";
			}

		}
		String okUsrCode = crm.getString("okUsrCode", "");
		if (StringUtils.isNotEmpty(okUsrCode)) {
			if (hasCheckUsrCodeBlank.equals("1")) {
				hql += " and eo.ok_Usr_Code='" + okUsrCode
						+ "' or eo.ok_Usr_Code is null ";
			} else {
				hql += " and eo.ok_Usr_Code='" + okUsrCode + "'";
			}

		}
		
		
		String companyCode = crm.getString("companyCode", "");
    	if(StringUtils.isNotEmpty(companyCode)){
    		hql +=" and eo.company_Code='"+companyCode+"'";
    	}
    	String customCode = crm.getString("customCode", "");
    	if(StringUtils.isNotEmpty(customCode)){
    		hql +=" and eo.CUSTOMER_CODE='"+customCode+"'";
    	}
    	
    	String orderFlagDefault = crm.getString("orderFlagDefault", "");
    	if(StringUtils.isNotEmpty(orderFlagDefault)){
    		hql +=" and eo.order_Flag in("+orderFlagDefault+")";
    	}
    	String orderFlag = crm.getString("orderFlag", "");
    	if(StringUtils.isNotEmpty(orderFlag)){
    		hql +=" and eo.order_Flag in("+orderFlag+")";
    	}
    	//创建时间
    	String createTimeStart = crm.getString("createTimeStart","");
    	String createTimeEnd = crm.getString("createTimeEnd","");
    	if(StringUtils.isNotEmpty(createTimeStart)){
    		hql += " and eo.create_Time >=to_date('"+createTimeStart+" 00:00:00','yyyy-mm-dd HH24:MI:SS')";
    	}
    	if(StringUtils.isNotEmpty(createTimeEnd)){
    		hql += " and eo.create_Time <=to_date('"+createTimeEnd+" 23:59:59','yyyy-mm-dd HH24:MI:SS')";
    	}
    	
    	//确认时间
    	String okTimeBegain = crm.getString("okTimeStart", "");
    	if(StringUtils.isNotEmpty(okTimeBegain)){
    		hql += " and eo.ok_Time >=to_date('"+okTimeBegain+" 00:00:00','yyyy-mm-dd HH24:MI:SS')";
    	}
    	String okTimeEnd = crm.getString("okTimeEnd", "");
    	if(StringUtils.isNotEmpty(okTimeEnd)){
    		hql += " and eo.ok_Time <=to_date('"+okTimeEnd+" 23:59:59','yyyy-mm-dd HH24:MI:SS')";
    	}
    	
    	//modify by fu 2016-04-07 添加自助换货的查询条件-----begin
    	//是否是自助换货:Y是，N或空表示否 
    	String selfReplacement = crm.getString("selfReplacement", "");
    	if(StringUtils.isNotEmpty(selfReplacement)){
    		if("Y".equals(selfReplacement)){
        		hql +=" and eo.self_Replacement='"+selfReplacement+"'";
    		}else{
        		hql +=" and ( eo.self_Replacement='N' or eo.self_Replacement is null )";
    		}
    	}
    	//modify by fu 2016-04-07 添加自助换货的查询条件-----end
    	
		/*
		 * String okStatus = crm.getString("okStatus", "");
		 * if(StringUtils.isNotEmpty(okStatus)){ sql +=
		 * " and ew.ok_Status='"+okStatus+"'"; } String checkStatus =
		 * crm.getString("checkStatus","");
		 * if(StringUtils.isNotEmpty(checkStatus)){ sql +=
		 * " and ew.check_Status='"+checkStatus+"'"; }
		 */
		//hql += " group by ewd.product_no,pm.product_name order by ewd.product_no ";
    	//add by lihao
    	//换货单商品明细查询,增加了FProductNo查询字段，以确定最终在页面上显示的商品编号
//    	String sql_first = " select nvl(tem.BProductNo,tem.DProductNo) FProductNo,tem.bqty,tem.dqty from ( ";
//    	String sql_last = " ) tem ";
    	//组装sql
    	String sql = sqlA + hql + sqlB + hql + sqlC;
    	//add by lihao
//    	sql = sql_first + sql + sql_last;
    	log.debug("换货统计sql-------------：" + sql);
		return this.getJdbcTemplate().queryForList(sql);
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdExchangeOrderDao#getPdExchangeOrders(com.joymain.jecs.pd.model.PdExchangeOrder)
	 */
	public List getPdExchangeOrders(final PdExchangeOrder pdExchangeOrder) {
		return getHibernateTemplate().find("from PdExchangeOrder");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (pdExchangeOrder == null) {
		 * return getHibernateTemplate().find("from PdExchangeOrder"); } else {
		 * // filter on properties set in the pdExchangeOrder HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex
		 * =Example.create(pdExchangeOrder).ignoreCase().enableLike(MatchMode.
		 * ANYWHERE); return
		 * session.createCriteria(PdExchangeOrder.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdExchangeOrderDao#getPdExchangeOrder(String
	 *      eoNo)
	 */
	public PdExchangeOrder getPdExchangeOrder(final String eoNo) {
		PdExchangeOrder pdExchangeOrder = (PdExchangeOrder) getHibernateTemplate()
				.get(PdExchangeOrder.class, eoNo);
		/*if (pdExchangeOrder == null) {
			log.warn("uh oh, pdExchangeOrder with eoNo '" + eoNo
					+ "' not found...");
			throw new ObjectRetrievalFailureException(PdExchangeOrder.class,
					eoNo);
		}*/

		return pdExchangeOrder;
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdExchangeOrderDao#savePdExchangeOrder(PdExchangeOrder
	 *      pdExchangeOrder)
	 */
	public void savePdExchangeOrder(final PdExchangeOrder pdExchangeOrder) {
		//modify by fu 2016-05-11 
		//modify by fu 2016-05-11 --------------------------------------begin
		//自助换货单会员不填退回仓库编号，为了规避页面的校验，特意在这里给自助换货单的退回仓库赋值，但是这个仓库值并不能正在的保存进数据库
		//后台制的换货单对应的仓库编号还是必填的
		String selfReplacement =  pdExchangeOrder.getSelfReplacement();
		if((!StringUtil.isEmpty(selfReplacement))&&("Y".equals(selfReplacement))){
			String warehouseNo = pdExchangeOrder.getWarehouseNo();
			if(!StringUtil.isEmpty(warehouseNo)){
				pdExchangeOrder.setWarehouseNo("");
			}
		}
	    //modify by fu 2016-05-11 --------------------------------------end
		getHibernateTemplate().saveOrUpdate(pdExchangeOrder);
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdExchangeOrderDao#removePdExchangeOrder(String
	 *      eoNo)
	 */
	public void removePdExchangeOrder(final String eoNo) {
		getHibernateTemplate().delete(getPdExchangeOrder(eoNo));
	}

	// added for getPdExchangeOrdersByCrm
	public List getPdExchangeOrdersByCrm(CommonRecord crm, Pager pager) {
		String hql = "from PdExchangeOrder pdExchangeOrder where 1=1";
		/**
		 * ---example--- String userCode = crm.getString("userCode", "");
		 * if(StringUtils.isNotEmpty(userCode)){ hql += "???????????"; }
		 ***/
		String defaultWarehouse = crm.getString("defaultWarehouse", "");
		if (StringUtils.isNotEmpty(defaultWarehouse)) {
			hql += " and (pdExchangeOrder.warehouseNo in(" + defaultWarehouse
			+ ") or pdExchangeOrder.warehouseNo is null )";
		}
		String eoNo = crm.getString("eoNo","");
		if (StringUtils.isNotEmpty(eoNo)) {
			hql += " and pdExchangeOrder.eoNo ='" + eoNo + "'";
		}
		
		String productNo = crm.getString("productNo", "");
		if (StringUtils.isNotEmpty(productNo)) {
			hql += " and pdExchangeOrder.eoNo in( select  eoNo from PdExchangeOrderDetail psd where psd.productNo='"+productNo+"' )";
		}

		//Modify By WuCF 20131114 仓库权限管理修改，查询时过滤 
//		String userCodeT = crm.getString("userCodeT", ""); 
//		if (StringUtils.isNotEmpty(userCodeT) && !"root".equals(userCodeT)) {
//			hql += " and exists (select 1 from PdWarehouseUser pdWarehouseUser where pdExchangeOrder.warehouseNo=pdWarehouseUser.warehouseNo and pdWarehouseUser.userCode='"+userCodeT+"') ";
//		}
		
		
		String orderNo = crm.getString("orderNo","");
		if (StringUtils.isNotEmpty(orderNo)) {
			hql += " and pdExchangeOrder.orderNo ='" + orderNo + "'";
		}
		String warehouseNo = crm.getString("warehouseNo", "");
		if (StringUtils.isNotEmpty(warehouseNo)) {
			hql += " and pdExchangeOrder.warehouseNo ='" + warehouseNo + "'";
		}
		String hasCheckUsrCodeBlank = crm
				.getString("hasCheckUsrCodeBlank", "0");

		String createUsrCode = crm.getString("createUsrCode", "");
		if (StringUtils.isNotEmpty(createUsrCode)) {
			hql += " and pdExchangeOrder.createUsrCode='" + createUsrCode
					+ "'";
		}
		String checkUsrCode = crm.getString("checkUsrCode", "");
		if (StringUtils.isNotEmpty(checkUsrCode)) {
			if (hasCheckUsrCodeBlank.equals("1")) {
				hql += " and pdExchangeOrder.checkUsrCode='" + checkUsrCode
						+ "' or pdExchangeOrder.checkUsrCode is null";
			} else {
				hql += " and pdExchangeOrder.checkUsrCode='" + checkUsrCode
						+ "' ";
			}

		}
		String okUsrCode = crm.getString("okUsrCode", "");
		if (StringUtils.isNotEmpty(okUsrCode)) {
			if (hasCheckUsrCodeBlank.equals("1")) {
				hql += " and pdExchangeOrder.okUsrCode='" + okUsrCode
						+ "' or pdExchangeOrder.okUsrCode is null ";
			} else {
				hql += " and pdExchangeOrder.okUsrCode='" + okUsrCode + "'";
			}

		}
		
		
		String companyCode = crm.getString("companyCode", "");
    	if(StringUtils.isNotEmpty(companyCode)){
    		hql +=" and pdExchangeOrder.companyCode='"+companyCode+"'";
    	}
    	String customCode = crm.getString("customCode", "");
    	if(StringUtils.isNotEmpty(customCode)){
    		hql +=" and pdExchangeOrder.customer.userCode='"+customCode+"'";
    	}
    	
    	String orderFlagDefault = crm.getString("orderFlagDefault", "");
    	if(StringUtils.isNotEmpty(orderFlagDefault)){
    		hql +=" and pdExchangeOrder.orderFlag in("+orderFlagDefault+")";
    	}
    	String orderFlag = crm.getString("orderFlag", "");
    	if(StringUtils.isNotEmpty(orderFlag)){
    		hql +=" and pdExchangeOrder.orderFlag in("+orderFlag+")";
    	}
    	//创建时间
    	String createTimeStart = crm.getString("createTimeStart","");
    	String createTimeEnd = crm.getString("createTimeEnd","");
    	if(StringUtils.isNotEmpty(createTimeStart)){
    		hql += " and pdExchangeOrder.createTime >=to_date('"+createTimeStart+" 00:00:00','yyyy-mm-dd HH24:MI:SS')";
    	}
    	if(StringUtils.isNotEmpty(createTimeEnd)){
    		hql += " and pdExchangeOrder.createTime <=to_date('"+createTimeEnd+" 23:59:59','yyyy-mm-dd HH24:MI:SS')";
    	}
    	
    	//确认时间
    	String okTimeBegain = crm.getString("okTimeStart", "");
    	if(StringUtils.isNotEmpty(okTimeBegain)){
    		hql += " and pdExchangeOrder.okTime >=to_date('"+okTimeBegain+" 00:00:00','yyyy-mm-dd HH24:MI:SS')";
    	}
    	String okTimeEnd = crm.getString("okTimeEnd", "");
    	if(StringUtils.isNotEmpty(okTimeEnd)){
    		hql += " and pdExchangeOrder.okTime <=to_date('"+okTimeEnd+" 23:59:59','yyyy-mm-dd HH24:MI:SS')";
    	}
    	
    	//modify by fu 2016-04-07 添加自助换货的查询条件-----begin
    	//是否是自助换货:Y是，N或空表示否 
    	String selfReplacement = crm.getString("selfReplacement", "");
    	if(StringUtils.isNotEmpty(selfReplacement)){
    		if("Y".equals(selfReplacement)){
        		hql +=" and pdExchangeOrder.selfReplacement='"+selfReplacement+"'";
    		}else{
        		hql +=" and ( pdExchangeOrder.selfReplacement='N' or pdExchangeOrder.selfReplacement is null )";
    		}
    	}
    	//modify by fu 2016-04-07 添加自助换货的查询条件-----end

		if(pager==null){
			return this.getHibernateTemplate().find(hql);
		}
		// 
		if (!pager.getLimit().getSort().isSorted()) {
			// sort
			hql += " order by eoNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty()
					+ " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}


	@Override
	public List getPdExchangeOrderDetailStaticsCheckedCompany(String startDate,
			String endDate, String companyCode, String productType) {
		String sqlQuery = "select jmo.order_type as order_type,jp.product_name as product_name," +
				" peod.product_no as product_no,"+
				"peod.price as price," +
				"sum(peod.qty) as qty," +
				"sum(peod.qty * peod.price) as amount," +
				"sum(peod.qty * peod.pv) as pv " +
				"from pd_exchange_order peo," +
				"pd_exchange_order_detail peod," +
				"jpm_product jp, " +
				"jpo_member_order jmo " +
				"where peo.order_no = jmo.member_order_no and peo.eo_no = peod.eo_no and jp.product_no = peod.product_no and peo.order_flag='2' ";
		if(!StringUtil.isEmpty(companyCode)){
			sqlQuery += "and peo.company_code = '" + companyCode + "' ";
		}

		if(!StringUtils.isEmpty(productType)){
			if("ALL".equals(productType)){//所有产品
		
		}else if("LC".equals(productType)){//内衣产品
			sqlQuery += "and jmo.product_type = 'LC' ";
		}else{
			sqlQuery += "and jmo.product_type = 'else' ";
		}
		}else{//中脉产品
			sqlQuery += "and jmo.product_type is null ";
		}

		sqlQuery += 
				" and peo.OK_TIME >= to_date('" + startDate + "', 'yyyy-MM-dd hh24:mi:ss') " +
				"and peo.OK_TIME <= to_date('" + endDate + "', 'yyyy-MM-dd hh24:mi:ss') " +
				"group by jmo.order_type,jp.product_name,peod.product_no,peod.price,peod.pv " +
				"order by jmo.order_type,peod.product_no";
		return this.findObjectsBySQL(sqlQuery);
		
		
	}
	
	@Override
	public List getPdExchangeOrderBackStaticsCheckedCompany(String startDate,
			String endDate, String companyCode, String productType) {
		String sqlQuery = "select jmo.order_type as order_type,jp.product_name as product_name," +
				" peob.product_no as product_no,"+
				"peob.price as price," +
				"sum(peob.qty) as qty," +
				"sum(peob.qty * peob.price) as amount," +
				"sum(peob.qty * peob.pv) as pv " +
				"from pd_exchange_order peo," +
				"pd_exchange_order_back peob," +
				"jpm_product jp, " +
				"jpo_member_order jmo " +
				"where peo.order_no = jmo.member_order_no and peo.eo_no = peob.eo_no and jp.product_no = peob.product_no and peo.order_flag='2' ";
		if(!StringUtil.isEmpty(companyCode)){
			sqlQuery += "and peo.company_code = '" + companyCode + "' ";
		}

		if(!StringUtils.isEmpty(productType)){
			if("ALL".equals(productType)){//所有产品
		
		}else if("LC".equals(productType)){//内衣产品
			sqlQuery += "and jmo.product_type = 'LC' ";
		}else{
			sqlQuery += "and jmo.product_type = 'else' ";
		}
		}else{//中脉产品
			sqlQuery += "and jmo.product_type is null ";
		}

		sqlQuery += 
				" and peo.OK_TIME >= to_date('" + startDate + "', 'yyyy-MM-dd hh24:mi:ss') " +
				"and peo.OK_TIME <= to_date('" + endDate + "', 'yyyy-MM-dd hh24:mi:ss') " +
				"group by jmo.order_type,jp.product_name,peob.product_no,peob.price,peob.pv " +
				"order by jmo.order_type,peob.product_no";
		return this.findObjectsBySQL(sqlQuery);
		
		
	}
	
	public static void main(String[] args) {
		System.out.println("select jmo.order_type as order_type,jp.product_name as product_name," +
				" peod.product_no as product_no,"+
				"peod.price as price," +
				"sum(peod.qty) as qty," +
				"sum(peod.qty * peod.price) as amount," +
				"sum(peod.qty * peod.pv) as pv " +
				"from pd_exchange_order peo," +
				"pd_exchange_order_detail peod," +
				"jpm_product jp, " +
				"jpo_member_order jmo " +
			"where peo.order_no = jmo.member_order_no and peo.eo_no = peod.eo_no and jp.product_no = peod.product_no "+
		
			"group by jmo.order_type,jp.product_name,peod.product_no,peod.price,peod.pv " +
			"order by jmo.order_type,peod.product_no");
	}

	@Override
	public List getTotalPdExchangeOrder2(CommonRecord crm) {
		List list = new ArrayList();
		
		String sqlA =
		  " select t1.product_no BProductNo,t2.product_no DProductNo,nvl(t1.qty,0) bqty,nvl(t2.qty,0) dqty from" 
		+ " (select ps.product_no,sum(nvl(eob.qty,0)) qty from                           "
		+ " pd_exchange_order eo,pd_exchange_order_back eob,jpm_product_sale_new ps          "
		+ " where eo.eo_no=eob.eo_no and eob.product_no=ps.product_no and                "
		+ " eo.company_code=ps.company_code                                              ";
		/*加上查询条件hql  */
		String sqlB =
		  " group by ps.product_no) t1                                         "
		+ " full join                                                          "
		+ " (select ps.product_no,sum(nvl(eod.qty,0)) qty from                 "
		+ " pd_exchange_order eo,pd_exchange_order_detail eod,jpm_product_sale_new "
		+ " ps where eo.eo_no=eod.eo_no and eod.product_no=ps.product_no and   "
		+ " eo.company_code=ps.company_code                                    ";
		/*加上查询条件hql  */
		String sqlC = " group by ps.product_no) t2 on t1.product_no = t2.product_no";
		
		String hql = "";
		String defaultWarehouse = crm.getString("defaultWarehouse", "");
		if (StringUtils.isNotEmpty(defaultWarehouse)) {
			hql += " and eo.warehouse_No in(" + defaultWarehouse
					+ ")";
		}
		String eoNo = crm.getString("eoNo","");
		if (StringUtils.isNotEmpty(eoNo)) {
			hql += " and eo.eo_No ='" + eoNo + "'";
		}
		
		//页面上添加是否自助换货的查询条件
		//是否是自助换货:Y是，N或空表示否 
    	String selfReplacement = crm.getString("selfReplacement", "");
    	if(StringUtils.isNotEmpty(selfReplacement)){
    		if("Y".equals(selfReplacement)){
        		hql +=" and eo.self_Replacement='"+selfReplacement+"'";
    		}else{
        		hql +=" and ( eo.self_Replacement='N' or eo.self_Replacement is null )";
    		}
    	}

		
		String orderNo = crm.getString("orderNo","");
		if (StringUtils.isNotEmpty(orderNo)) {
			hql += " and eo.order_No ='" + orderNo + "'";
		}
		String warehouseNo = crm.getString("warehouseNo", "");
		if (StringUtils.isNotEmpty(warehouseNo)) {
			hql += " and eo.warehouse_No ='" + warehouseNo + "'";
		}
		String hasCheckUsrCodeBlank = crm
				.getString("hasCheckUsrCodeBlank", "0");

		String createUsrCode = crm.getString("createUsrCode", "");
		if (StringUtils.isNotEmpty(createUsrCode)) {
			hql += " and eo.create_Usr_Code='" + createUsrCode
					+ "'";
		}
		String checkUsrCode = crm.getString("checkUsrCode", "");
		if (StringUtils.isNotEmpty(checkUsrCode)) {
			if (hasCheckUsrCodeBlank.equals("1")) {
				hql += " and eo.check_Usr_Code='" + checkUsrCode
						+ "' or eo.check_Usr_Code is null";
			} else {
				hql += " and eo.check_Usr_Code='" + checkUsrCode
						+ "' ";
			}

		}
		String okUsrCode = crm.getString("okUsrCode", "");
		if (StringUtils.isNotEmpty(okUsrCode)) {
			if (hasCheckUsrCodeBlank.equals("1")) {
				hql += " and eo.ok_Usr_Code='" + okUsrCode
						+ "' or eo.ok_Usr_Code is null ";
			} else {
				hql += " and eo.ok_Usr_Code='" + okUsrCode + "'";
			}

		}
		
		
		String companyCode = crm.getString("companyCode", "");
    	if(StringUtils.isNotEmpty(companyCode)){
    		hql +=" and eo.company_Code='"+companyCode+"'";
    	}
    	String customCode = crm.getString("customCode", "");
    	if(StringUtils.isNotEmpty(customCode)){
    		hql +=" and eo.CUSTOMER_CODE='"+customCode+"'";
    	}
    	
    	String orderFlagDefault = crm.getString("orderFlagDefault", "");
    	if(StringUtils.isNotEmpty(orderFlagDefault)){
    		hql +=" and eo.order_Flag in("+orderFlagDefault+")";
    	}
    	String orderFlag = crm.getString("orderFlag", "");
    	if(StringUtils.isNotEmpty(orderFlag)){
    		hql +=" and eo.order_Flag in("+orderFlag+")";
    	}
    	//创建时间
    	String createTimeStart = crm.getString("createTimeStart","");
    	String createTimeEnd = crm.getString("createTimeEnd","");
    	if(StringUtils.isNotEmpty(createTimeStart)){
    		hql += " and eo.create_Time >=to_date('"+createTimeStart+" 00:00:00','yyyy-mm-dd HH24:MI:SS')";
    	}
    	if(StringUtils.isNotEmpty(createTimeEnd)){
    		hql += " and eo.create_Time <=to_date('"+createTimeEnd+" 23:59:59','yyyy-mm-dd HH24:MI:SS')";
    	}
    	
    	//确认时间
    	String okTimeBegain = crm.getString("okTimeStart", "");
    	if(StringUtils.isNotEmpty(okTimeBegain)){
    		hql += " and eo.ok_Time >=to_date('"+okTimeBegain+" 00:00:00','yyyy-mm-dd HH24:MI:SS')";
    	}
    	String okTimeEnd = crm.getString("okTimeEnd", "");
    	if(StringUtils.isNotEmpty(okTimeEnd)){
    		hql += " and eo.ok_Time <=to_date('"+okTimeEnd+" 23:59:59','yyyy-mm-dd HH24:MI:SS')";
    	}
		/*
		 * String okStatus = crm.getString("okStatus", "");
		 * if(StringUtils.isNotEmpty(okStatus)){ sql +=
		 * " and ew.ok_Status='"+okStatus+"'"; } String checkStatus =
		 * crm.getString("checkStatus","");
		 * if(StringUtils.isNotEmpty(checkStatus)){ sql +=
		 * " and ew.check_Status='"+checkStatus+"'"; }
		 */
		//hql += " group by ewd.product_no,pm.product_name order by ewd.product_no ";
    	//add by lihao
    	//换货单商品明细查询,增加了FProductNo查询字段，以确定最终在页面上显示的商品编号
    	String sql_first = " select nvl(tem.BProductNo,tem.DProductNo) FProductNo,tem.bqty,tem.dqty from ( ";
    	String sql_last = " ) tem ";
    	//组装sql
    	String sql = sqlA + hql + sqlB + hql + sqlC;
    	//add by lihao
    	sql = sql_first + sql + sql_last;
    	log.debug("换货统计sql-------------：" + sql);
		return this.getJdbcTemplate().queryForList(sql);
	}

	
	 /**
	 * 查询未推送到后续系统的自助换货单
	 * @author fu 2016-04-12
	 * @return
	 */
	public List<PdExchangeOrder> getNotSendPdExchangeOrder(){
		log.info("在类PdExchangeOrderDaoHibernate的getNotSendPdExchangeOrder方法中运行：查询未推送到后续系统的自助换货单");
		String hql = " from PdExchangeOrder pdExchangeOrder where pdExchangeOrder.selfReplacement='Y' and pdExchangeOrder.whetherPd=1 and pdExchangeOrder.orderFlag=2 and pdExchangeOrder.sendStatus is null ";
		return this.findObjectsByHqlQuery(hql);
	}
	
	/**
	 * 将自助换货单状态改为已推送状态
	 * @author fu 2016-04-12
	 * @param eoNo
	 * @return
	 */
	public void reSetpdExchangeOrderSendStatus(String eoNo){
		String sql = " update pd_exchange_order set send_status=1 where eo_no = '"+eoNo+"' ";
		log.info("在类PdExchangeOrderDaoHibernate的reSetpdExchangeOrderSendStatus方法中运行：将自助换货单"+eoNo+"修改成已推送状态");
		this.jdbcTemplate.execute(sql);
	}
	
	/**
	 * 取消或恢复自助换货单
	 * @author fu 2016-04-12
	 * @param eoNo 自助换货单号
	 * @param  cancelExchange  是否取消自助换货单：Y取消，空恢复
	 * @return string
	 */
	public String reSetCancelExchange(String eoNo, String cancelExchange){
		String returnRusult = "succChengGong" ;
		String sql = "";
		try{
			if((!StringUtil.isEmpty(cancelExchange))&&("Y".equals(cancelExchange))){
				//取消自助换货单的同时，将自助换货单变成未审核状态；
				sql = " update pd_exchange_order set cancel_exchange='"+cancelExchange+"' where eo_no = '"+eoNo+"' ";
			}else{
				sql = " update pd_exchange_order set cancel_exchange='"+cancelExchange+"' ,order_flag=0 where eo_no = '"+eoNo+"' ";
			}
			this.jdbcTemplate.execute(sql);
			log.info("在类PdExchangeOrderDaoHibernate的reSetCancelExchange方法中运行：取消或恢复自助换货单");
		}catch(Exception e){
			e.printStackTrace();
			log.info("在类PdExchangeOrderDaoHibernate的reSetCancelExchange方法中运行：取消或恢复自助换货单失败:"+e.toString());
			returnRusult = "failShiBai";
			return returnRusult;
		}
		return returnRusult;
	}
	
	/**
	 * 获取换货单的状态
	 * @author fu 2016-04-25
	 * @param eoNo
	 * @return
	 */
	public Integer getPdExchangeOrderOrderFlag(String eoNo){
		String sql = "select order_flag from pd_exchange_order where eo_no = '"+eoNo+"'";
		List list = this.findObjectsBySQL(sql);
		if((null!=list)&& list.size()>0){
			Map map = (Map) list.get(0);
			String orderFlagB = (String) map.get("order_flag");
			Integer orderFlag = Integer.parseInt(orderFlagB);
			return orderFlag;
		}
		return null;
	}

}
