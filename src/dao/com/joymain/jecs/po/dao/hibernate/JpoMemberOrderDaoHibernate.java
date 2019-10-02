package com.joymain.jecs.po.dao.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.po.dao.JpoMemberOrderDao;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

public class JpoMemberOrderDaoHibernate extends BaseDaoHibernate implements
		JpoMemberOrderDao {
	
	//查出符合发货条件的订单
    public List getShipOrder(CommonRecord crm, Pager pager) {
    	String sql = "select * FROM JPO_MEMBER_ORDER JPO where jpo.is_shipping = '0' and status = '2'" 
         + " and (exists (select 1 from jfi_bankbook_journal jfi where jfi.unique_code = jpo.member_order_no) or exists "
         + " (select 1 from fi_bankbook_journal jfi where jfi.unique_code = jpo.member_order_no) or exists " 
         + " (select 1 from fi_bcoin_journal jfi where jfi.unique_code = jpo.member_order_no))";
    	
    	log.debug("查出符合发货条件的订单--------:" + sql);
    	
    	return this.findObjectsBySQL(sql);
    }

	public List getChageableJpoMemberOrders(String userCode) {
		// TODO Auto-generated method stub
		String queryString = "from JpoMemberOrder order where  order.sysUser.userCode = '"
				+ userCode
				+ "' and order.status='2' and order.memberOrderNo not in"
				+ "(select orderNo from PdExchangeOrder eo where eo.customer.userCode ='"
				+ userCode + "') order by orderTime desc ";
		return this.getHibernateTemplate().find(queryString);
	}

	/**
	 * @see com.joymain.jecs.po.dao.JpoMemberOrderDao#getJpoMemberOrders(com.joymain.jecs.po.model.JpoMemberOrder)
	 */
	public List getJpoMemberOrders(final JpoMemberOrder jpoMemberOrder) {
//		return getHibernateTemplate().find("from JpoMemberOrder");
		return null;
		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (jpoMemberOrder == null) {
		 * return getHibernateTemplate().find("from JpoMemberOrder"); } else {
		 * // filter on properties set in the jpoMemberOrder HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex
		 * =
		 * Example.create(jpoMemberOrder).ignoreCase().enableLike(MatchMode.ANYWHERE
		 * ); return
		 * session.createCriteria(JpoMemberOrder.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.joymain.jecs.po.dao.JpoMemberOrderDao#getJpoMemberOrder(Long
	 *      moId)
	 */
	public JpoMemberOrder getJpoMemberOrder(final Long moId) {
		JpoMemberOrder jpoMemberOrder = (JpoMemberOrder) getHibernateTemplate()
				.get(JpoMemberOrder.class, moId);
		if (jpoMemberOrder == null) {
			log.warn("uh oh, jpoMemberOrder with moId '" + moId
					+ "' not found...");
			throw new ObjectRetrievalFailureException(JpoMemberOrder.class,
					moId);
		}
		Iterator<JpoMemberOrderList> listIte = jpoMemberOrder.getJpoMemberOrderList().iterator();
		while(listIte.hasNext()){
			JpoMemberOrderList ol =  listIte.next();
			log.info("dao orderList="+ol.getMolId()+" and qty=="+ol.getQty());
		}
		return jpoMemberOrder;
	}

	/**
	 * @see com.joymain.jecs.po.dao.JpoMemberOrderDao#saveJpoMemberOrder(JpoMemberOrder
	 *      jpoMemberOrder)
	 */
	public void saveJpoMemberOrder(final JpoMemberOrder jpoMemberOrder) {
		getHibernateTemplate().saveOrUpdate(jpoMemberOrder);
	}

	public JpoMemberOrder getJpoMemberOrderByMemberOrderNo(String memberOrderNo) {
		return (JpoMemberOrder) this
				.getObjectByHqlQuery("from JpoMemberOrder where memberOrderNo ='"
						+ memberOrderNo + "'");
	}

	/**
	 * @see com.joymain.jecs.po.dao.JpoMemberOrderDao#removeJpoMemberOrder(Long
	 *      moId)
	 */
	public void removeJpoMemberOrder(final Long moId) {
		getHibernateTemplate().delete(getJpoMemberOrder(moId));
	}

	/**
	 * 按条件获取订单总金额
	 * 
	 * @param crm
	 * @return
	 */
	public BigDecimal getJpoMemberOrderStatics(CommonRecord crm){
		String sql = "select nvl(Sum(Jmo.pv_amt),0) pv from jpo_member_order jmo where 1=1";
		
		String stauts = crm.getString("stauts", "");
		if (!StringUtils.isEmpty(stauts)) {
			sql += " and jmo.status = '" + stauts + "'";
		}
		
		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			sql += " and jmo.user_code = '" + userCode + "'";
		}
		
		String orderType = crm.getString("orderType", "");
		if (!StringUtils.isEmpty(orderType)) {
			sql += " and jmo.order_type in (" + orderType + ")";
		}
		
		String startLogTime = crm.getString("startLogTime", "");
		if (!StringUtils.isEmpty(startLogTime)) {
			sql += " and jmo.check_time >=to_date('" + startLogTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		
		String endLogTime = crm.getString("endLogTime", "");
		if (!StringUtils.isEmpty(endLogTime)) {
			sql += " and jmo.check_time < to_date('" + startLogTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		
		Map map = (Map)this.findObjectsBySQL(sql).get(0);
		return new BigDecimal(map.get("pv").toString());
	}
	
	/**
	 * 时间段内获取商品订购量
	 * @param productNo
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public int getProductsSum(String productNo,String startTime,String endTime,String payBy){
		String sql = "Select nvl(Sum(Jmol.Qty),0) sumqty";
		sql += " From Jpo_Member_Order Jmo, Jpo_Member_Order_List Jmol";
		sql += " Where Jmo.Mo_Id = Jmol.Mo_Id";
		sql += " And jmo.check_time >= to_date('" + startTime + "','yyyy-mm-dd hh24:mi:ss')";
		sql += " And jmo.check_time <= to_date('" + endTime + "','yyyy-mm-dd hh24:mi:ss')";
		sql += " And Jmo.Status = 2";
		if(!StringUtil.isEmail(payBy)){
			if("byCoin".equals(payBy)){
				sql += " And Jmo.PAY_BY_COIN = 1";
			}
		}
		sql += " And Jmol.Product_Id In (Select Uni_No";
		sql += " From Jpm_Product_Sale";
		sql += " Where Product_No = '" + productNo + "'";
		sql += " And Company_Code = 'CN')";
		Map map = (Map)this.findObjectsBySQL(sql).get(0);
		return Integer.parseInt(map.get("sumqty").toString());
	}
	
	/**
	 * 订单总金额
	 * @param crm
	 * @return
	 */
	public Map getSumAmountByCrm(CommonRecord crm) {
		String hql = "select nvl(sum(jpoMemberOrder.amount),0) as amount,nvl(sum(jpoMemberOrder.jjAmount),0) as jjAmount,nvl(sum(jpoMemberOrder.pvAmt),0) as pvAmt,nvl(sum(jpoMemberOrder.discountAmount),0) as discountAmount "
				+ ",nvl(sum(jpoMemberOrder.productPointAmount),0) as productPointAmount,nvl(sum(jpoMemberOrder.cpValue),0) as cpValue from JpoMemberOrder jpoMemberOrder where 1=1";

		hql += this.buildListHqlQuery(crm);
		
		Object[] sum = (Object[])this.getObjectByHqlQuery(hql);
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		map.put("amount", (BigDecimal) sum[0]);
		map.put("jjAmount", (BigDecimal) sum[1]);
		map.put("pvAmt", (BigDecimal) sum[2]);
		map.put("discountAmount", (BigDecimal) sum[3]);
		map.put("productPointAmount", (BigDecimal) sum[4]);
		map.put("cpValue", (BigDecimal) sum[5]);
		
		return map;
	}

	public Map getSumAmountSTJByCrm(CommonRecord crm) {
		String sql =  "SELECT " +
				"NVL(SUM(AMOUNT), 0) AS AMOUNT, " +
				"NVL(SUM(JJ_AMOUNT), 0) AS JJAMOUNT, " +
				"NVL(SUM(PV_AMT), 0) AS PVAMT," +
				"NVL(SUM(DISCOUNT_AMOUNT), 0) AS DISCOUNTAMOUNT " +
				"FROM " +
				"jpo_member_order " +
				"WHERE mo_id IN " +
				"(" +
				"SELECT  DISTINCT  mo.mo_id " +
				"FROM " +
				"JPO_MEMBER_ORDER MO, " +
//				"JPO_MEMBER_ORDER_LIST MOL, " +
				"JMI_MEMBER MI " +
				"WHERE " +
//				"MO.MO_ID = MOL.MO_ID  " +
				"MO.USER_CODE = MI.USER_CODE " +
				"AND mo.productflag is not null" ;

		sql += buildListHqlSTJQuery(crm);
		
		sql += ") ";
		
		Map<String, BigDecimal> map = (Map<String, BigDecimal>) this.findObjectsBySQL(sql).get(0);
//		map.put("amount", (BigDecimal) map.get("amount"));
//		map.put("jjAmount", (BigDecimal) map.get("jjAmount"));
//		map.put("pvAmt", (BigDecimal) map.get("pvAmt"));
//		map.put("discountAmount", (BigDecimal) map.get("discountAmount"));
		return map;
	}
	
	public List getJpoMemberOrdersSTJByCrm(CommonRecord crm, Pager pager) {
		String sql = "WITH m4 AS (SELECT * " +
		  " FROM (SELECT MO2.MOID MID, MAX(MOVIENUM) MOVIE" +
		          " FROM ( " +
		              " SELECT DISTINCT M.MO_ID MOID," +
		                              "  CASE"+
		                                 " WHEN MOL.QTY = '30' THEN" +
		                                "   '30'" +
		                                "  WHEN MOL.QTY = '60' THEN" +
		                                "   '60'" +
		                                 " ELSE" +
		                                "   '0'" +
		                                "END MOVIENUM" +
		                "  FROM JPO_MEMBER_ORDER      M," +
		                "       JPO_MEMBER_ORDER_LIST MOL" +
		                " WHERE M.MO_ID = MOL.MO_ID" +
		              "   AND m.productflag is not null ) MO2"+
		       "  GROUP BY MO2.MOID) M3)";
					
		sql += "SELECT distinct " +
				"mo.mo_id moId," +
				"mo.member_order_no memberOrderNo," +
				"mo.order_type orderType, " +
				"mo.user_code userCode," +
				"mi.pet_name userName, " +
				"mo.productflag productflag , " +
				"mi.create_time createTime," +
				"mi.papertype papertype, " +
				"mi.papernumber papernumber ," +
				"nvl(mo.amount,0) amount, " +
				"nvl(mo.jj_amount,0) jjAmount," +
				"nvl(mo.pv_amt,0) pvAmt," +
				"mo.IS_RETREAT_ORDER isRetreatOrder , " + 
//				"nvl(mol.qty,0) movienum ," +
				"m4.movie movienum," +
				"mo.order_user_code orderUserCode," +
				"mo.check_user_code checkUserCode," +
				"mo.check_time checkTime," +
				"mo.check_date checkDate," +
				"mo.first_name firstName," +
				"mo.last_name lastName, " +
				"mo.province province," +
				"mo.city city," +
				"mo.district district," +
				"mo.address address, " +
				"mo.mobiletele mobiletele," +
				"mo.phone phone " +
				"FROM jpo_member_order mo, " +
//				"jpo_member_order_list mol, " +
				"jmi_member mi, " +
				"m4 " +
				"WHERE mo.user_code = mi.user_code " +
				"and m4.mid = mo.mo_id";

		sql += buildListHqlSTJQuery(crm);
		
        if (pager == null) {
			String listNum = crm.getString("listNum");//最大限额数
			String sqlCount = "select count(*) "+sql;
			Integer num = Integer.parseInt(this.getSession().createQuery(sqlCount).uniqueResult().toString());
			log.info("===会员订单统计，导出数："+num);
			if(num>Integer.parseInt(listNum)){//如果超过限额，则返回
				List list = new ArrayList<String>();
				list.add(1);
				return list;
			}else{
				sql += " order by moId desc";
				 return this.findObjectsBySQL(sql, pager);
			}
		}else{        
			if (!pager.getLimit().getSort().isSorted()) {
				// sort
				sql += " order by moId desc";
			} else {
				sql += " ORDER BY " + pager.getLimit().getSort().getProperty()
						+ " " + pager.getLimit().getSort().getSortOrder();
			}
			log.info("getJpoMemberOrdersByCrm-:"+sql);
			return this.findObjectsBySQL(sql, pager);
		}
     
       
		
	}
	
	/**
	 * 根据外部参数生成查询语句
	 * 
	 * @param crm
	 * @return
	 */
	private String buildListHqlSTJQuery(CommonRecord crm) {
		
		String sql = " ";
		
		String productFlag = crm.getString("productFlag", "");
		if (!StringUtils.isEmpty(productFlag)) {
			if("0".equals(productFlag)){
				sql += " and mo.productFlag is not null ";
			}else{
				sql += " and mo.productFlag='" + productFlag + "' ";
			}
		}
		String memberOrderNo = crm.getString("memberOrderNo", "");
		if (!StringUtils.isEmpty(memberOrderNo)) {
			sql += " and mo.member_order_no='" + memberOrderNo + "'";
		}

		String userCode = crm.getString("sysUser.userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			sql += " and mo.user_Code='" + userCode + "'";
		}
		
		String isRetreatOrder = crm.getString("isRetreatOrder", "");//退的单量
		if (!StringUtils.isEmpty(isRetreatOrder)) {
			if("0".equals(isRetreatOrder)){
				sql += " and (mo.IS_RETREAT_ORDER='" + isRetreatOrder + "' or mo.IS_RETREAT_ORDER is null)";
			}else{
				sql += " and mo.IS_RETREAT_ORDER='" + isRetreatOrder + "'";
			}
		}
		String logType = crm.getString("logType", "");
		if (!StringUtils.isEmpty(logType)) {
			String startLogTime = crm.getString("startLogTime", "");
			if (!StringUtils.isEmpty(startLogTime)) {
				if ("A".equals(logType)) {
					sql += " and mo.order_Time>=to_date('" + startLogTime
							+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("C".equals(logType)) {
					sql += " and mo.check_Date>=to_date('" + startLogTime
							+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("BC".equals(logType)) {
					sql += " and mo.check_Time>=to_date('" + startLogTime
							+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
				}
			}
			String endLogTime = crm.getString("endLogTime", "");
			if (!StringUtils.isEmpty(endLogTime)) {
				if ("A".equals(logType)) {
					sql += " and mo.order_Time<=to_date('" + endLogTime
							+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("C".equals(logType)) {
					sql += " and mo.check_Date<=to_date('" + endLogTime
							+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("BC".equals(logType)) {
					sql += " and mo.check_Time<=to_date('" + endLogTime
							+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
				}
			}
		}
		
		return sql;
	}
	
	
	// added for getJpoMemberOrdersByCrm
	public List getJpoMemberOrdersByCrm(CommonRecord crm, Pager pager) {
		String hql = "from JpoMemberOrder jpoMemberOrder where 1=1";

		hql += this.buildListHqlQuery(crm);
		
		//Modify By WuCF 20140515 不用分页需要的处理，需要控制数据数量
        if (pager == null) {
			
        	//Modify By WuCF 20140514  查询记录条数，控制记录条数
			String listNum = crm.getString("listNum");//最大限额数
			
			String sqlCount = "select count(*) "+hql;
			Integer num = Integer.parseInt(this.getSession().createQuery(sqlCount).uniqueResult().toString());
			log.info("===会员订单统计，导出数："+num);
			if(num>Integer.parseInt(listNum)){//如果超过限额，则返回
				List list = new ArrayList<String>();
				list.add(1);
				return list;
			}else{
				hql += " order by moId desc";
				return this.getHibernateTemplate().find(hql);
			}
		}else{        
	        //分页需要的处理
			if (!pager.getLimit().getSort().isSorted()) {
				// sort
				hql += " order by moId desc";
			} else {
				hql += " ORDER BY " + pager.getLimit().getSort().getProperty()
						+ " " + pager.getLimit().getSort().getSortOrder();
			}
			log.info("getJpoMemberOrdersByCrm-:"+hql);
			return this.findObjectsByHqlQuery(hql, pager);
		}
	}

	

	
	public List getJpoMemberOrdersByCrmForRefund(CommonRecord crm, Pager pager) {
		//modify gw 2015-07-08 奖衔补单（体外单）不允许退单 orderType 为32，表明是体外单（奖衔补单）
		//modify by fu 2017-5-27 43宝宝单，42亲亲单,93代金券换购单。云店年费单，亲亲单及宝宝单及代金券换购单不能退单；
		//String hql = "select pmo from JpoMemberOrder pmo where pmo.orderType not in(3,32,41,42,43,93) ";
		//modify ljl 奖衔补单 允许 退单 20181009
		String hql = "select pmo from JpoMemberOrder pmo where pmo.orderType not in(3,41) ";
		
		
		/** * 增加自己的查询条件在下面** */
		String memberOrderNo = crm.getString("memberOrderNo", "");
		if (!StringUtils.isEmpty(memberOrderNo)) {
			hql += " and pmo.memberOrderNo = '" + memberOrderNo + "'";
		}

		String jprRefund = crm.getString("jprRefund", "");
		if (!StringUtils.isEmpty(jprRefund)) {
			hql += " and (pmo.jprRefund.size=0)";
		}
		
		String isRetreatOrder = crm.getString("isRetreatOrder", "");
		if (!StringUtils.isEmpty(isRetreatOrder)) {
			hql += " and pmo.isRetreatOrder !='"+isRetreatOrder+"' ";
		}

		String userCode = crm.getString("sysUser.userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and pmo.sysUser.userCode = '" + userCode + "'";
		}

		String status = crm.getString("status", "");
		if (!StringUtils.isEmpty(status) && !"0".equals(status)) {
			hql += " and status in (" + status + ")";
		}

		String companyCode = crm.getString("companyCode", "");
		if (!StringUtils.isEmpty(companyCode)) {
			hql += " and pmo.companyCode = '" + companyCode + "'";
		}
		
		//modify by fu 挂起状态的订单不允许退货
    	//private String returnableStatus;//退货状态   1：退货中，0正常
		hql += " and ( pmo.returnableStatus is null or pmo.returnableStatus=0 )";

		// 设置排序
		if (!pager.getLimit().getSort().isSorted()) {
			// 缺省排序ȱʡ����
			hql += " order by pmo.moId desc";
		} else {
			hql += " ORDER BY pmo." + pager.getLimit().getSort().getProperty()
					+ " " + pager.getLimit().getSort().getSortOrder();
		}

		return this.findObjectsByHqlQuery(hql, pager);
	}

	/**
	 * 根据条件统计商品销售量
	 * 
	 * @param crm
	 * @return
	 */
	public List getStatisticProductSale(CommonRecord crm) {
		String sql = "Select pnew.product_no as product_no, pnew.product_name as product_name, Sum(a.Price*a.Qty) as price, Sum(a.Qty) as qty " +
				"From Jpo_Member_Order_List a, Jpm_Product_Sale_Team_Type c,Jpm_Product_Sale_New pnew ";
		sql += " Where a.Product_Id = c.ptt_id  and c.uni_no=pnew.uni_no And Exists (Select 1 From Jpo_Member_Order b Where a.mo_Id = b.mo_Id";

		
		String productFlag = crm.getString("productFlag", "");
		if (!StringUtils.isEmpty(productFlag)) {
			if("0".equals(productFlag)){
				sql += " and b.productFlag is not null ";
			}else{
				sql += " and b.productFlag='" + productFlag + "' ";
			}
		}
		
		String team = crm.getString("team", "");
		if(StringUtils.isNotBlank(team)){
			sql +=" AND B.USER_CODE IN (SELECT USER_CODE FROM JMI_MEMBER MI WHERE MI.MEMBER_TYPE='" + team + "')";
		}
		
		
		String papernumber = crm.getString("sysUser.jmiMember.papernumber", "");
		if (!StringUtils.isEmpty(papernumber)) {
			sql += " and b.user_code IN (SELECT user_code FROM jmi_member mi WHERE mi.papernumber='" + papernumber + "')";
		}
		
		String companyCode = crm.getString("companyCode", "");
		if (!"AA".equals(companyCode) && !StringUtils.isEmpty(companyCode)) {
			sql += " And b.company_Code = '" + companyCode + "'";
		}

		String memberOrderNo = crm.getString("memberOrderNo", "");
		if (!StringUtils.isEmpty(memberOrderNo)) {
			sql += " And b.member_Order_No = '" + memberOrderNo + "'";
		}

		String province = crm.getString("province", "");
		if (!StringUtils.isEmpty(province)) {
			sql += " and b.province = '" + province + "'";
		}

		String city = crm.getString("city", "");
		if (!StringUtils.isEmpty(city)) {
			sql += " and b.city = '" + city + "'";
		}

		String productType = crm.getString("productType", "");
		if (!StringUtils.isEmpty(productType)) {
			if("ALL".equals(productType)){
				
			}else if("JOYMAIN".equals(productType)){
				sql += " and b.product_Type is null";
			}else if("LC".equals(productType)){
				sql += " and b.product_Type='" + productType + "'";
			}
		}

		String district = crm.getString("district", "");
		if (!StringUtils.isEmpty(district)) {
			sql += " and b.district = '" + district + "'";
		}

		String userCode = crm.getString("sysUser.userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			sql += " and b.user_code = '" + userCode + "'";
		}

		String orderType = crm.getString("orderType", "");
		if (!StringUtils.isEmpty(orderType)) {
			sql += " and b.order_Type='" + orderType + "'";
		}

		String payByCoin = crm.getString("payByCoin", "");
		if (!StringUtils.isEmpty(payByCoin)) {
			if("0".equals(payByCoin)){
				sql += " and (b.pay_By_Coin='" + payByCoin + "' or b.pay_By_Coin is null)";
			}else{
				sql += " and b.pay_By_Coin='" + payByCoin + "'";
			}
		}
		
		String payByJJ = crm.getString("payByJJ", "");
		if (!StringUtils.isEmpty(payByJJ)) {
			if (!StringUtils.isEmpty(payByJJ)) {
				if("0".equals(payByJJ)){
					sql += " and (b.pay_By_Jj='" + payByJJ + "' or b.pay_By_Jj is null)";
				}else{
					sql += " and b.pay_By_Jj='" + payByJJ + "'";
				}
			}
		}
		
		String isRetreatOrder = crm.getString("isRetreatOrder", "");//退单的商品
		if (!StringUtils.isEmpty(isRetreatOrder)) {
			if("0".equals(isRetreatOrder)){
				sql += " and (b.IS_RETREAT_ORDER='" + isRetreatOrder + "' or b.IS_RETREAT_ORDER is null)";
			}else{
				sql += " and b.IS_RETREAT_ORDER='" + isRetreatOrder + "'";
			}
		}

		String locked = crm.getString("locked", "");
		if (!StringUtils.isEmpty(locked)) {
			sql += " and locked='" + locked + "'";
		}

		String status = crm.getString("status", "");
		if (!StringUtils.isEmpty(status)) {
			sql += " And b.status = '" + status + "'";
		}

		String ismobile = crm.getString("ismobile", "");
		if (!StringUtils.isEmpty(ismobile)) {
			sql += " And b.is_mobile = '" + ismobile + "'";
		}
		
		//dengmh update by 2016/5/17 过滤订单延期状态
		String returnableStatus = crm.getString("returnableStatus", "");
		if (!StringUtils.isEmpty(returnableStatus)) {
			if("0".equals(returnableStatus)){
				sql += " And (b.RETURNABLE_STATUS = '" + returnableStatus + "' or b.RETURNABLE_STATUS is null) ";
			}
			if("1".equals(returnableStatus)){
				sql += " And b.RETURNABLE_STATUS = '" + returnableStatus + "' ";
			}
		}
		
		String logType = crm.getString("logType", "");
		if (!StringUtils.isEmpty(logType)) {
			String startLogTime = crm.getString("startLogTime", "");
			if (!StringUtils.isEmpty(startLogTime)) {
				if ("A".equals(logType)) {
					sql += " and b.order_Time>=to_date('" + startLogTime
							+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("C".equals(logType)) {
					sql += " and b.check_Date>=to_date('" + startLogTime
							+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("BC".equals(logType)) {
					sql += " and b.check_Time>=to_date('" + startLogTime
							+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
				}
			}
			String endLogTime = crm.getString("endLogTime", "");
			if (!StringUtils.isEmpty(endLogTime)) {
				if ("A".equals(logType)) {
					sql += " and b.order_Time<=to_date('" + endLogTime
							+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("C".equals(logType)) {
					sql += " and b.check_Date<=to_date('" + endLogTime
							+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("BC".equals(logType)) {
					sql += " and b.check_Time<=to_date('" + endLogTime
							+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
				}
			}
		}
		
		sql += ") Group By pnew.product_no,pnew.product_name";

		return this.findObjectsBySQL(sql);
	}

	/**
	 * 根据外部参数生成查询语句
	 * 
	 * @param crm
	 * @return
	 */
	private String buildListHqlQuery(CommonRecord crm) {
		String hql = "";
		String orderTypeT = crm.getString("orderTypeT", "");
		if (!StringUtils.isEmpty(orderTypeT)) {
			if("Y".equals(orderTypeT)){
				hql += " and orderType!='16' ";
			}
		}
		String productFlag = crm.getString("productFlag", "");
		if (!StringUtils.isEmpty(productFlag)) {
			if("0".equals(productFlag)){
				hql += " and productFlag is not null ";
			}else{
				hql += " and productFlag='" + productFlag + "' ";
			}
		}
		
		String papernumber = crm.getString("sysUser.jmiMember.papernumber", "");
		if (!StringUtils.isEmpty(papernumber)) {
//			hql += " and sysUser.jmiMember.papernumber='" + papernumber + "'";
			hql += " and sysUser.userCode in ( select userCode from VjmiUsercodePapernumber where papernumber = '" + papernumber + "')";
		}
		
		String companyCode = crm.getString("companyCode", "");
		if (!"AA".equals(companyCode) && !StringUtils.isEmpty(companyCode)) {
			hql += " and companyCode='" + companyCode + "'";
		}

		String userCode = crm.getString("sysUser.userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and sysUser.userCode='" + userCode + "'";
		}

		String recommendCompanyCode = crm.getString("sysUser.jmiMember.jmiRecommendRef.jmiMember.companyCode", "");
		if (!StringUtils.isEmpty(recommendCompanyCode)) {
			hql += " and sysUser.jmiMember.jmiRecommendRef.jmiMember.companyCode='" + recommendCompanyCode + "'";
		}

		String productType = crm.getString("productType", "");
		if (!StringUtils.isEmpty(productType)) {
			if("ALL".equals(productType)){
				
			}else if("JOYMAIN".equals(productType)){
				hql += " and productType is null";
			}else if("LC".equals(productType)){
				hql += " and productType='" + productType + "'";
			}
		}

		String orderType = crm.getString("orderType", "");
		if (!StringUtils.isEmpty(orderType)) {
			hql += " and orderType='" + orderType + "'";
		}

		String orderTypeIn = crm.getString("orderTypeIn", "");
		if (!StringUtils.isEmpty(orderTypeIn)) {
			hql += " and orderType in (" + orderTypeIn + ")";
		}

		String isSpecial = crm.getString("isSpecial", "");
		if (!StringUtils.isEmpty(isSpecial)) {
			hql += " and isSpecial='" + isSpecial + "'";
		}

		String status = crm.getString("status", "");
		if (!StringUtils.isEmpty(status)) {
			hql += " and status in (" + status + ")";
		}

		String isPay = crm.getString("isPay", "");
		if (!StringUtils.isEmpty(isPay)) {
			hql += " and isPay='" + isPay + "'";
		}
		String isShipments = crm.getString("isShipments", "");
		if (!StringUtils.isEmpty(isShipments)) {
			if("2".equals(isShipments))
			{
				hql += " and (isShipments='0' or  isShipments is null)";
			}else
			{
				hql += " and (isShipments='"+isShipments+"')";
			}
			
		}

		String payByCoin = crm.getString("payByCoin", "");
		if (!StringUtils.isEmpty(payByCoin)) {
			if("0".equals(payByCoin)){
				hql += " and (payByCoin='" + payByCoin + "' or payByCoin is null)";
			}else{
				hql += " and payByCoin='" + payByCoin + "'";
			}
		}

		String payByJJ = crm.getString("payByJJ", "");
		if (!StringUtils.isEmpty(payByJJ)) {
			if("0".equals(payByJJ)){
				hql += " and (payByJJ='" + payByJJ + "' or payByJJ is null)";
			}else{
				hql += " and payByJJ='" + payByJJ + "'";
			}
		}
		
		String isRetreatOrder = crm.getString("isRetreatOrder", "");//退的单量
		if (!StringUtils.isEmpty(isRetreatOrder)) {
			if("0".equals(isRetreatOrder)){
				hql += " and (isRetreatOrder='" + isRetreatOrder + "' or isRetreatOrder is null)";
			}else{
				hql += " and isRetreatOrder='" + isRetreatOrder + "'";
			}
		}

		String province = crm.getString("province", "");
		if (!StringUtils.isEmpty(province)) {
			hql += " and province = '" + province + "'";
		}

		String city = crm.getString("city", "");
		if (!StringUtils.isEmpty(city)) {
			hql += " and city = '" + city + "'";
		}

		String district = crm.getString("district", "");
		if (!StringUtils.isEmpty(district)) {
			hql += " and district = '" + district + "'";
		}

		String locked = crm.getString("locked", "");
		if (!StringUtils.isEmpty(locked)) {
			hql += " and locked='" + locked + "'";
		}

		String submitStatus = crm.getString("submitStatus", "");
		if (!StringUtils.isEmpty(submitStatus)) {
			hql += " and submitStatus='" + submitStatus + "'";
		}

		String memberOrderNo = crm.getString("memberOrderNo", "");
		if (!StringUtils.isEmpty(memberOrderNo)) {
			hql += " and memberOrderNo='" + memberOrderNo + "'";
		}

		String startOrderTime = crm.getString("startOrderTime", "");
		if (!StringUtils.isEmpty(startOrderTime)) {
			hql += " and orderTime>=to_date('" + startOrderTime
					+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}

		String endOrderTime = crm.getString("endOrderTime", "");
		if (!StringUtils.isEmpty(endOrderTime)) {
			hql += " and orderTime<=to_date('" + endOrderTime
					+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}

		String startOrderCheckTime = crm.getString("startOrderCheckTime", "");
		if (!StringUtils.isEmpty(startOrderCheckTime)) {
			hql += " and checkTime>=to_date('" + startOrderCheckTime
					+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}

		String endOrderCheckTime = crm.getString("endOrderCheckTime", "");
		if (!StringUtils.isEmpty(endOrderCheckTime)) {
			hql += " and checkTime<=to_date('" + endOrderCheckTime
					+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		String startOrderCheckDate = crm.getString("createBDate", "");
		if (!StringUtils.isEmpty(startOrderCheckDate)) {
			hql += " and checkDate>=to_date('" + startOrderCheckDate
					+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}

		String endOrderCheckDate = crm.getString("createEDate", "");
		if (!StringUtils.isEmpty(endOrderCheckDate)) {
			hql += " and checkDate<=to_date('" + endOrderCheckDate
					+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		
		//判断订单是否有发货------开始
		String isShipping = crm.getString("isShipping", "");
		if (!StringUtils.isEmpty(isShipping)) {
			if("1".equals(isShipping))
			{
				hql += " and (isShipping!='" + isShipping + "' or isShipping is null)";
			}
			
		}
		String shippingPay = crm.getString("shippingPay", "");
		if (!StringUtils.isEmpty(shippingPay)) {
			if("1".equals(shippingPay))
			{
				hql += " and (shippingPay!='" + shippingPay + "' or shippingPay is null)";
			}
			
		}
		String checkTimeF = crm.getString("checkTimeF", "");//3月20
		if (!StringUtils.isEmpty(checkTimeF)) {
			hql += " and checkTime>=to_date('" + checkTimeF
					+ " 10:00:00','yyyy-mm-dd hh24:mi:ss')";
		}
		
		
		
        //判断订单是否有发货------结束	

		String inPeriod = crm.getString("inPeriod", "");
		if (!StringUtils.isEmpty(inPeriod)) {
			if ("A".equals(inPeriod)) {
				hql += " and checkDate>=to_date('"
						+ crm.getString("inPeriodStartTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss')";
				hql += " and checkDate< to_date('"
						+ crm.getString("inPeriodEndTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss')";
				hql += " and (checkTime< to_date('"
						+ crm.getString("inPeriodStartTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss')";
				hql += " or checkTime>= to_date('"
						+ crm.getString("inPeriodEndTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss'))";
			} else if ("D".equals(inPeriod)) {
				hql += " and checkTime>=to_date('"
						+ crm.getString("inPeriodStartTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss')";
				hql += " and checkTime< to_date('"
						+ crm.getString("inPeriodEndTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss')";
				hql += " and (checkDate< to_date('"
						+ crm.getString("inPeriodStartTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss')";
				hql += " or checkDate>= to_date('"
						+ crm.getString("inPeriodEndTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss'))";
			}
		}
		
		String petName = crm.getString("sysUser.jmiMember.petName", "");
		if (!StringUtils.isEmpty(petName)) {
			hql += " and sysUser.jmiMember.petName = '" + petName + "'";
		}
		
		String firstNameKana = crm.getString("sysUser.jmiMember.firstNameKana", "");
		if (!StringUtils.isEmpty(firstNameKana)) {
			hql += " and sysUser.jmiMember.firstNameKana = '" + firstNameKana + "'";
		}
		
		String lastNameKana = crm.getString("sysUser.jmiMember.lastNameKana", "");
		if (!StringUtils.isEmpty(lastNameKana)) {
			hql += " and sysUser.jmiMember.lastNameKana = '" + lastNameKana + "'";
		}

		String mode = crm.getString("mode", "");
		if (!StringUtils.isEmpty(mode) && !mode.equals("0")) {
			hql += " and sysUser.userCode in (select sysUser.userCode from JfiBankbookJournal b where journalId=(select max(journalId) from JfiBankbookJournal c where c.sysUser.userCode = b.sysUser.userCode";
			String company = crm.getString("company", "");
			if (!StringUtils.isEmpty(company)) {
				hql += " and c.companyCode = '" + company + "'";
			}
			hql += " ) and b.balance>=" + mode + ")";
		}

		String logType = crm.getString("logType", "");
		if (!StringUtils.isEmpty(logType)) {
			String startLogTime = crm.getString("startLogTime", "");
			if (!StringUtils.isEmpty(startLogTime)) {
				if ("A".equals(logType)) {
					hql += " and orderTime>=to_date('" + startLogTime
							+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("C".equals(logType)) {
					hql += " and checkDate>=to_date('" + startLogTime
							+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("BC".equals(logType)) {
					hql += " and checkTime>=to_date('" + startLogTime
							+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
				}
			}
			String endLogTime = crm.getString("endLogTime", "");
			if (!StringUtils.isEmpty(endLogTime)) {
				if ("A".equals(logType)) {
					hql += " and orderTime<=to_date('" + endLogTime
							+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("C".equals(logType)) {
					hql += " and checkDate<=to_date('" + endLogTime
							+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("BC".equals(logType)) {
					hql += " and checkTime<=to_date('" + endLogTime
							+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
				}
			}
		}
		
		String team = crm.getString("team", "");
		if(StringUtils.isNotBlank(team)){
			hql +=" and sysUser.jmiMember.memberType='"+team.trim()+"' ";
		}
		
		String isMobile = crm.getString("isMobile", "");
		if(StringUtils.isNotBlank(isMobile)){
			hql +=" and isMobile='"+isMobile+"' ";
		}
		
		//dengmh update by 2016/5/17 过滤订单延期状态
		String returnableStatus = crm.getString("returnableStatus", "");
		if(StringUtils.isNotBlank(returnableStatus)){
			if("0".equals(returnableStatus)){
				hql +=" and (returnableStatus='"+returnableStatus+"' or returnableStatus is null) ";
			}
			if("1".equals(returnableStatus)){
				hql +=" and returnableStatus='"+returnableStatus+"' ";
			}
		}
		//EXCHANGE_FLAG	是否换货，1为已换货，0为换货取消
		String exchangeFlag = crm.getString("exchangeFlag", "");
		if(StringUtils.isNotBlank(exchangeFlag)){
			if("0".equals(exchangeFlag)){
				hql +=" and (exchangeFlag='"+exchangeFlag+"' or exchangeFlag is null) ";
			}
			if("1".equals(exchangeFlag)){
				hql +=" and exchangeFlag='"+exchangeFlag+"' ";
			}
		}
		return hql;
	}

	
	private String buildListSqlQuery(CommonRecord crm) {
		String hql = "";

		String companyCode = crm.getString("companyCode", "");
		if (!"AA".equals(companyCode) && !StringUtils.isEmpty(companyCode)) {
			hql += " and po.companyCode='" + companyCode + "'";
		}

		String userCode = crm.getString("sysUser.userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and po.sysUser.userCode='" + userCode + "'";
		}

		String recommendCompanyCode = crm.getString("po.sysUser.jmiMember.jmiRecommendRef.jmiMember.companyCode", "");
		if (!StringUtils.isEmpty(recommendCompanyCode)) {
			hql += " and po.sysUser.jmiMember.jmiRecommendRef.jmiMember.companyCode='" + recommendCompanyCode + "'";
		}

		String productType = crm.getString("productType", "");
		if (!StringUtils.isEmpty(productType)) {
			if("ALL".equals(productType)){
				
			}else if("JOYMAIN".equals(productType)){
				hql += " and po.productType is null";
			}else if("LC".equals(productType)){
				hql += " and po.productType='" + productType + "'";
			}
		}

		String orderType = crm.getString("orderType", "");
		if (!StringUtils.isEmpty(orderType)) {
			hql += " and po.orderType='" + orderType + "'";
		}

		String orderTypeIn = crm.getString("orderTypeIn", "");
		if (!StringUtils.isEmpty(orderTypeIn)) {
			hql += " and po.orderType in (" + orderTypeIn + ")";
		}

		String isSpecial = crm.getString("isSpecial", "");
		if (!StringUtils.isEmpty(isSpecial)) {
			hql += " and po.isSpecial='" + isSpecial + "'";
		}

		String status = crm.getString("status", "");
		if (!StringUtils.isEmpty(status)) {
			hql += " and po.status='" + status + "'";
		}

		String isPay = crm.getString("isPay", "");
		if (!StringUtils.isEmpty(isPay)) {
			hql += " and po.isPay='" + isPay + "'";
		}

		String payByCoin = crm.getString("payByCoin", "");
		if (!StringUtils.isEmpty(payByCoin)) {
			if("0".equals(payByCoin)){
				hql += " and (po.payByCoin='" + payByCoin + "' or po.payByCoin is null)";
			}else{
				hql += " and po.payByCoin='" + payByCoin + "'";
			}
		}

		String payByJJ = crm.getString("payByJJ", "");
		if (!StringUtils.isEmpty(payByJJ)) {
			if("0".equals(payByJJ)){
				hql += " and (po.payByJJ='" + payByJJ + "' or po.payByJJ is null)";
			}else{
				hql += " and po.payByJJ='" + payByJJ + "'";
			}
		}

		String province = crm.getString("province", "");
		if (!StringUtils.isEmpty(province)) {
			hql += " and po.province = '" + province + "'";
		}

		String city = crm.getString("city", "");
		if (!StringUtils.isEmpty(city)) {
			hql += " and po.city = '" + city + "'";
		}

		String district = crm.getString("district", "");
		if (!StringUtils.isEmpty(district)) {
			hql += " and po.district = '" + district + "'";
		}

		String locked = crm.getString("locked", "");
		if (!StringUtils.isEmpty(locked)) {
			hql += " and po.locked='" + locked + "'";
		}

		String submitStatus = crm.getString("submitStatus", "");
		if (!StringUtils.isEmpty(submitStatus)) {
			hql += " and po.submitStatus='" + submitStatus + "'";
		}

		String memberOrderNo = crm.getString("memberOrderNo", "");
		if (!StringUtils.isEmpty(memberOrderNo)) {
			hql += " and po.memberOrderNo='" + memberOrderNo + "'";
		}

		String startOrderTime = crm.getString("startOrderTime", "");
		if (!StringUtils.isEmpty(startOrderTime)) {
			hql += " and po.orderTime>=to_date('" + startOrderTime
					+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}

		String endOrderTime = crm.getString("endOrderTime", "");
		if (!StringUtils.isEmpty(endOrderTime)) {
			hql += " and po.orderTime<=to_date('" + endOrderTime
					+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}

		String startOrderCheckTime = crm.getString("startOrderCheckTime", "");
		if (!StringUtils.isEmpty(startOrderCheckTime)) {
			hql += " and po.checkTime>=to_date('" + startOrderCheckTime
					+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}

		String endOrderCheckTime = crm.getString("endOrderCheckTime", "");
		if (!StringUtils.isEmpty(endOrderCheckTime)) {
			hql += " and po.checkTime<=to_date('" + endOrderCheckTime
					+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}

		String inPeriod = crm.getString("inPeriod", "");
		if (!StringUtils.isEmpty(inPeriod)) {
			if ("A".equals(inPeriod)) {
				hql += " and po.checkDate>=to_date('"
						+ crm.getString("inPeriodStartTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss')";
				hql += " and po.checkDate< to_date('"
						+ crm.getString("inPeriodEndTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss')";
				hql += " and (po.checkTime< to_date('"
						+ crm.getString("inPeriodStartTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss')";
				hql += " or po.checkTime>= to_date('"
						+ crm.getString("inPeriodEndTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss'))";
			} else if ("D".equals(inPeriod)) {
				hql += " and po.checkTime>=to_date('"
						+ crm.getString("inPeriodStartTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss')";
				hql += " and po.checkTime< to_date('"
						+ crm.getString("inPeriodEndTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss')";
				hql += " and (po.checkDate< to_date('"
						+ crm.getString("inPeriodStartTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss')";
				hql += " or po.checkDate>= to_date('"
						+ crm.getString("inPeriodEndTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss'))";
			}
		}
		
		String petName = crm.getString("sysUser.jmiMember.petName", "");
		if (!StringUtils.isEmpty(petName)) {
			hql += " and po.sysUser.jmiMember.petName = '" + petName + "'";
		}
		
		String firstNameKana = crm.getString("sysUser.jmiMember.firstNameKana", "");
		if (!StringUtils.isEmpty(firstNameKana)) {
			hql += " and po.sysUser.jmiMember.firstNameKana = '" + firstNameKana + "'";
		}
		
		String lastNameKana = crm.getString("sysUser.jmiMember.lastNameKana", "");
		if (!StringUtils.isEmpty(lastNameKana)) {
			hql += " and po.sysUser.jmiMember.lastNameKana = '" + lastNameKana + "'";
		}

		String mode = crm.getString("mode", "");
		if (!StringUtils.isEmpty(mode)&&!"0".equals(mode)) {
			hql += " and sysUser.userCode in (select sysUser.userCode from JfiBankbookJournal b where journalId=(select max(journalId) from JfiBankbookJournal c where c.sysUser.userCode = b.sysUser.userCode";
			String company = crm.getString("company", "");
			if (!StringUtils.isEmpty(company)) {
				hql += " and c.companyCode = '" + company + "'";
			}
			hql += " ) and b.balance>=" + mode + ")";
		}

		String logType = crm.getString("logType", "");
		if (!StringUtils.isEmpty(logType)) {
			String startLogTime = crm.getString("startLogTime", "");
			if (!StringUtils.isEmpty(startLogTime)) {
				if ("A".equals(logType)) {
					hql += " and po.orderTime>=to_date('" + startLogTime
							+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("C".equals(logType)) {
					hql += " and po.checkDate>=to_date('" + startLogTime
							+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("BC".equals(logType)) {
					hql += " and po.checkTime>=to_date('" + startLogTime
							+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
				}
			}
			String endLogTime = crm.getString("endLogTime", "");
			if (!StringUtils.isEmpty(endLogTime)) {
				if ("A".equals(logType)) {
					hql += " and po.orderTime<=to_date('" + endLogTime
							+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("C".equals(logType)) {
					hql += " and po.checkDate<=to_date('" + endLogTime
							+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("BC".equals(logType)) {
					hql += " and po.checkTime<=to_date('" + endLogTime
							+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
				}
			}
		}

		return hql;
	}
	
	
	private String buildListSqlQueryF(CommonRecord crm) {
		String hql = "";

		String companyCode = crm.getString("companyCode", "");
		if (!"AA".equals(companyCode) && !StringUtils.isEmpty(companyCode)) {
			hql += " and poFee.jpoMemberOrder.companyCode='" + companyCode + "'";
		}

		String userCode = crm.getString("sysUser.userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and poFee.jpoMemberOrder.sysUser.userCode='" + userCode + "'";
		}

		String recommendCompanyCode = crm.getString("poFee.jpoMemberOrder.sysUser.jmiMember.jmiRecommendRef.jmiMember.companyCode", "");
		if (!StringUtils.isEmpty(recommendCompanyCode)) {
			hql += " and poFee.jpoMemberOrder.sysUser.jmiMember.jmiRecommendRef.jmiMember.companyCode='" + recommendCompanyCode + "'";
		}

		String productType = crm.getString("productType", "");
		if (!StringUtils.isEmpty(productType)) {
			if("ALL".equals(productType)){
				
			}else if("JOYMAIN".equals(productType)){
				hql += " and poFee.jpoMemberOrder.productType is null";
			}else if("LC".equals(productType)){
				hql += " and poFee.jpoMemberOrder.productType='" + productType + "'";
			}
		}

		String orderType = crm.getString("orderType", "");
		if (!StringUtils.isEmpty(orderType)) {
			hql += " and poFee.jpoMemberOrder.orderType='" + orderType + "'";
		}

		String orderTypeIn = crm.getString("orderTypeIn", "");
		if (!StringUtils.isEmpty(orderTypeIn)) {
			hql += " and poFee.jpoMemberOrder.orderType in (" + orderTypeIn + ")";
		}

		String isSpecial = crm.getString("isSpecial", "");
		if (!StringUtils.isEmpty(isSpecial)) {
			hql += " and poFee.jpoMemberOrder.isSpecial='" + isSpecial + "'";
		}

		String status = crm.getString("status", "");
		if (!StringUtils.isEmpty(status)) {
			hql += " and poFee.jpoMemberOrder.status='" + status + "'";
		}

		String isPay = crm.getString("isPay", "");
		if (!StringUtils.isEmpty(isPay)) {
			hql += " and poFee.jpoMemberOrder.isPay='" + isPay + "'";
		}

		String payByCoin = crm.getString("payByCoin", "");
		if (!StringUtils.isEmpty(payByCoin)) {
			if("0".equals(payByCoin)){
				hql += " and (poFee.jpoMemberOrder.payByCoin='" + payByCoin + "' or poFee.jpoMemberOrder.payByCoin is null)";
			}else{
				hql += " and poFee.jpoMemberOrder.payByCoin='" + payByCoin + "'";
			}
		}

		String payByJJ = crm.getString("payByJJ", "");
		if (!StringUtils.isEmpty(payByJJ)) {
			if("0".equals(payByJJ)){
				hql += " and (poFee.jpoMemberOrder.payByJJ='" + payByJJ + "' or poFee.jpoMemberOrder.payByJJ is null)";
			}else{
				hql += " and poFee.jpoMemberOrder.payByJJ='" + payByJJ + "'";
			}
		}

		String province = crm.getString("province", "");
		if (!StringUtils.isEmpty(province)) {
			hql += " and poFee.jpoMemberOrder.province = '" + province + "'";
		}

		String city = crm.getString("city", "");
		if (!StringUtils.isEmpty(city)) {
			hql += " and poFee.jpoMemberOrder.city = '" + city + "'";
		}

		String district = crm.getString("district", "");
		if (!StringUtils.isEmpty(district)) {
			hql += " and poFee.jpoMemberOrder.district = '" + district + "'";
		}

		String locked = crm.getString("locked", "");
		if (!StringUtils.isEmpty(locked)) {
			hql += " and poFee.jpoMemberOrder.locked='" + locked + "'";
		}

		String submitStatus = crm.getString("submitStatus", "");
		if (!StringUtils.isEmpty(submitStatus)) {
			hql += " and poFee.jpoMemberOrder.submitStatus='" + submitStatus + "'";
		}

		String memberOrderNo = crm.getString("memberOrderNo", "");
		if (!StringUtils.isEmpty(memberOrderNo)) {
			hql += " and poFee.jpoMemberOrder.memberOrderNo='" + memberOrderNo + "'";
		}

		String startOrderTime = crm.getString("startOrderTime", "");
		if (!StringUtils.isEmpty(startOrderTime)) {
			hql += " and poFee.jpoMemberOrder.orderTime>=to_date('" + startOrderTime
					+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}

		String endOrderTime = crm.getString("endOrderTime", "");
		if (!StringUtils.isEmpty(endOrderTime)) {
			hql += " and poFee.jpoMemberOrder.orderTime<=to_date('" + endOrderTime
					+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}

		String startOrderCheckTime = crm.getString("startOrderCheckTime", "");
		if (!StringUtils.isEmpty(startOrderCheckTime)) {
			hql += " and poFee.jpoMemberOrder.checkTime>=to_date('" + startOrderCheckTime
					+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}

		String endOrderCheckTime = crm.getString("endOrderCheckTime", "");
		if (!StringUtils.isEmpty(endOrderCheckTime)) {
			hql += " and poFee.jpoMemberOrder.checkTime<=to_date('" + endOrderCheckTime
					+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}

		String inPeriod = crm.getString("inPeriod", "");
		if (!StringUtils.isEmpty(inPeriod)) {
			if ("A".equals(inPeriod)) {
				hql += " and poFee.jpoMemberOrder.checkDate>=to_date('"
						+ crm.getString("inPeriodStartTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss')";
				hql += " and poFee.jpoMemberOrder.checkDate< to_date('"
						+ crm.getString("inPeriodEndTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss')";
				hql += " and (poFee.jpoMemberOrder.checkTime< to_date('"
						+ crm.getString("inPeriodStartTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss')";
				hql += " or poFee.jpoMemberOrder.checkTime>= to_date('"
						+ crm.getString("inPeriodEndTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss'))";
			} else if ("D".equals(inPeriod)) {
				hql += " and poFee.jpoMemberOrder.checkTime>=to_date('"
						+ crm.getString("inPeriodStartTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss')";
				hql += " and poFee.jpoMemberOrder.checkTime< to_date('"
						+ crm.getString("inPeriodEndTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss')";
				hql += " and (poFee.jpoMemberOrder.checkDate< to_date('"
						+ crm.getString("inPeriodStartTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss')";
				hql += " or poFee.jpoMemberOrder.checkDate>= to_date('"
						+ crm.getString("inPeriodEndTime", "")
						+ "','yyyy-mm-dd hh24:mi:ss'))";
			}
		}
		
		String petName = crm.getString("sysUser.jmiMember.petName", "");
		if (!StringUtils.isEmpty(petName)) {
			hql += " and poFee.jpoMemberOrder.sysUser.jmiMember.petName = '" + petName + "'";
		}
		
		String firstNameKana = crm.getString("sysUser.jmiMember.firstNameKana", "");
		if (!StringUtils.isEmpty(firstNameKana)) {
			hql += " and poFee.jpoMemberOrder.sysUser.jmiMember.firstNameKana = '" + firstNameKana + "'";
		}
		
		String lastNameKana = crm.getString("sysUser.jmiMember.lastNameKana", "");
		if (!StringUtils.isEmpty(lastNameKana)) {
			hql += " and poFee.jpoMemberOrder.sysUser.jmiMember.lastNameKana = '" + lastNameKana + "'";
		}

		String mode = crm.getString("mode", "");
		if (!StringUtils.isEmpty(mode)&&!"0".equals(mode)) {
			hql += " and sysUser.userCode in (select sysUser.userCode from JfiBankbookJournal b where journalId=(select max(journalId) from JfiBankbookJournal c where c.sysUser.userCode = b.sysUser.userCode";
			String company = crm.getString("company", "");
			if (!StringUtils.isEmpty(company)) {
				hql += " and c.companyCode = '" + company + "'";
			}
			hql += " ) and b.balance>=" + mode + ")";
		}

		String logType = crm.getString("logType", "");
		if (!StringUtils.isEmpty(logType)) {
			String startLogTime = crm.getString("startLogTime", "");
			if (!StringUtils.isEmpty(startLogTime)) {
				if ("A".equals(logType)) {
					hql += " and poFee.jpoMemberOrder.orderTime>=to_date('" + startLogTime
							+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("C".equals(logType)) {
					hql += " and poFee.jpoMemberOrder.checkDate>=to_date('" + startLogTime
							+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("BC".equals(logType)) {
					hql += " and poFee.jpoMemberOrder.checkTime>=to_date('" + startLogTime
							+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
				}
			}
			String endLogTime = crm.getString("endLogTime", "");
			if (!StringUtils.isEmpty(endLogTime)) {
				if ("A".equals(logType)) {
					hql += " and poFee.jpoMemberOrder.orderTime<=to_date('" + endLogTime
							+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("C".equals(logType)) {
					hql += " and poFee.jpoMemberOrder.checkDate<=to_date('" + endLogTime
							+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
				}
				if ("BC".equals(logType)) {
					hql += " and poFee.jpoMemberOrder.checkTime<=to_date('" + endLogTime
							+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
				}
			}
		}

		return hql;
	}


	/**
	 * 获取订单数量
	 * 
	 * @param orderType
	 * @param userCode
	 * @param status
	 * @return
	 */
	public List getJpoMemberOrdersByTCS(String orderType, String userCode,
			String status) {
		String hqlQuery = "from JpoMemberOrder where orderType='" + orderType
				+ "' and sysUser.userCode='" + userCode + "' and status = '"
				+ status + "'";
		return this.findObjectsByHqlQuery(hqlQuery);
	}

	/**
	 * 会员编号查找
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	public List getJpoMemberOrdersByMiMember(JpoMemberOrder jpoMemberOrder) {
		String hql = "from JpoMemberOrder jpoMemberOrder where sysUser.userCode='"
				+ jpoMemberOrder.getSysUser().getUserCode() + "'";

		String orderType = jpoMemberOrder.getOrderType();
		if (!StringUtils.isEmpty(orderType)) {
			hql += " and jpoMemberOrder.orderType = '" + orderType + "'";
		}
		String status = jpoMemberOrder.getStatus();
		if (!StringUtils.isEmpty(status)) {
			hql += " and jpoMemberOrder.status = '" + status + "'";
		}

		return this.getHibernateTemplate().find(hql);
	}

	/**
	 * 查询首购单的审核时间
	 * 
	 * @param memberNo
	 * @return
	 */
	public String getMemberFirstOrderStatusTime(String memberNo) {
		String sqlQuery = "select max(CHECK_DATE) log_create_time from jpo_member_order a where a.order_type='1' and a.status='2'";
		sqlQuery += " and user_code='" + memberNo + "'";
		Map m = this.findOneObjectBySQL(sqlQuery);
		String logCreateTime = (String) m.get("log_create_time");
		if (StringUtils.isEmpty(logCreateTime)) {
			sqlQuery = "select max(CHECK_DATE) check_date,max(CREATE_TIME) create_time from jmi_member m where m.user_code='"
					+ memberNo + "'";
			m = this.findOneObjectBySQL(sqlQuery);
			if (!StringUtils.isEmpty(m.get("check_date").toString())) {
				return m.get("check_date").toString();
			}else if(!StringUtils.isEmpty(m.get("create_time").toString())){
				return m.get("create_time").toString();
			}else{
				return "2007-05-01";
			}
		}
		return logCreateTime;
	}

	/**
	 * 获取一段时间内各个公司(如指定companyCode,则获取对应的公司)的已审核订单
	 * 
	 * @param startDate
	 * @param endDate
	 * @param companyCode
	 * @return
	 */
	public List getJpoMemberOrderStaticsCheckedCompany(final String startDate,
			final String endDate, final String companyCode, final String productType,
			final String checkType) {
		String sqlQuery = "select pmo.ORDER_TYPE as ORDER_TYPE,"
				+ "pnew.product_name as PRODUCT_NAME,"
				+ " pnew.product_no as product_no," + "pmolist.price as price,"
				+ "SUM(pmolist.qty) as QTY_AMT,"
				+ "SUM(pmolist.Price*pmolist.qty) as AMOUNT,"
				+ "SUM(pmolist.Pv*pmolist.qty) as PV_AMT "
				+ "from JPO_MEMBER_ORDER pmo,"
				+ "JPO_MEMBER_ORDER_LIST pmolist," + "JPM_PRODUCT_SALE_Team_type prs, "+"JPM_PRODUCT_SALE_New pnew "
				+ " where pmo.mo_id = pmolist.mo_id "
				+ " and pmolist.product_id = prs.ptt_id "
				+" and prs.uni_no=pnew.uni_no "
				+" and pmo.STATUS = '2' "
				+" and (pmo.RETURNABLE_STATUS != '1' or pmo.RETURNABLE_STATUS is null) ";
		if ("C".equals(checkType)) {
			sqlQuery += "and pmo.CHECK_DATE >= " + "to_date('" + startDate
					+ "', 'yyyy-MM-dd hh24:mi:ss') " + "and pmo.CHECK_DATE <= "
					+ "to_date('" + endDate + "', 'yyyy-MM-dd hh24:mi:ss') ";
		} else {
			sqlQuery += "and pmo.CHECK_TIME >= " + "to_date('" + startDate
					+ "', 'yyyy-MM-dd hh24:mi:ss') " + "and pmo.CHECK_TIME <= "
					+ "to_date('" + endDate + "', 'yyyy-MM-dd hh24:mi:ss') ";
		}
		if(!StringUtils.isEmpty(productType)){
			if("ALL".equals(productType)){//所有产品
				
			}else if("LC".equals(productType)){//内衣产品
				sqlQuery += "and pmo.product_type = 'LC' ";
			}else{
				sqlQuery += "and pmo.product_type = 'else' ";
			}
		}else{//中脉产品
			sqlQuery += "and pmo.product_type is null ";
		}
		if(!StringUtils.isEmpty(companyCode)){
			sqlQuery += "and pmo.COMPANY_CODE = '"+ companyCode+ "' ";
		}
		sqlQuery += "GROUP BY pmo.order_type, pnew.product_name, pnew.product_no, pmolist.price, pmolist.Pv order by pmo.order_type, pnew.product_no";
		return this.findObjectsBySQL(sqlQuery);
	}

	public List getJpoMemberOrderStaticsCheckedCompanyByTree(String startDate, String endDate, String companyCode, String checkType, String treeIndex,String relationType) {

		String sqlQuery = "select pmo.ORDER_TYPE as ORDER_TYPE,"
				+ "pnew.product_name as PRODUCT_NAME,"
				+ "pnew.product_no as product_no," + "pmolist.price as price,"
				+ "SUM(pmolist.qty) as QTY_AMT,"
				+ "SUM(pmolist.Price*pmolist.qty) as AMOUNT,"
				+ "SUM(pmolist.Pv*pmolist.qty) as PV_AMT "
				+ "from JPO_MEMBER_ORDER pmo,"
				+ "JPO_MEMBER_ORDER_LIST pmolist," + "JPM_PRODUCT_SALE_Team_type prs, "+" JPM_PRODUCT_SALE_New pnew "
				+ " where pmo.mo_id = pmolist.mo_id "
				+ " and pmolist.product_id = prs.ptt_id "
				+" and prs.uni_no=pnew.uni_no"
				+ " and pmo.STATUS = '2' "
				+ " and (pmo.RETURNABLE_STATUS != '1' or pmo.RETURNABLE_STATUS is null) ";
		if ("C".equals(checkType)) {
			sqlQuery += "and pmo.CHECK_DATE >= " + "to_date('" + startDate
					+ "', 'yyyy-MM-dd hh24:mi:ss') " + "and pmo.CHECK_DATE <= "
					+ "to_date('" + endDate + "', 'yyyy-MM-dd hh24:mi:ss') ";
		} else {
			sqlQuery += "and pmo.CHECK_TIME >= " + "to_date('" + startDate
					+ "', 'yyyy-MM-dd hh24:mi:ss') " + "and pmo.CHECK_TIME <= "
					+ "to_date('" + endDate + "', 'yyyy-MM-dd hh24:mi:ss') ";
		}
		if(!"AA".equals(companyCode)){
			sqlQuery += "and pmo.COMPANY_CODE = '"+companyCode+"' ";
		}
		
		sqlQuery +=" and pmo.user_code in (select user_code from "+relationType+" t where t.tree_index like '"+treeIndex+"%')"
				+ "GROUP BY pmo.order_type, pnew.product_name, pnew.product_no, pmolist.price, pmolist.Pv order by pmo.order_type, pnew.product_no";
		return this.findObjectsBySQL(sqlQuery);
	}
	
	public List getShippingfeeByCrm(CommonRecord crm, Pager pager)
	{
		String hql = "from JpoMemberOrderFee poFee where poFee.feeType=1 and poFee.fee>0  ";
		hql += this.buildListSqlQueryF(crm);
		if (!pager.getLimit().getSort().isSorted()) {
			// sort
			hql += " order by poFee.jpoMemberOrder.moId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty()
					+ " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}
	public Map getTotalShippingfeeByCrm(CommonRecord crm) {
		String hql = "select nvl(sum(poFee.fee),0) as amountFee from JpoMemberOrderFee poFee where poFee.feeType=1";

		hql += this.buildListSqlQueryF(crm);
		
		BigDecimal sum = (BigDecimal)this.getObjectByHqlQuery(hql);
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		map.put("amountFee", (BigDecimal) sum);
		
		return map;
	}
	/**
	 * 
	 * @param papernumber  身份证号
	 * @param productNo 产品编号
	 * @param orderType  订单类型
	 * @return
	 */
	public List getJpoMemberMark(String papernumber,String productNo,String orderType)
	{
		String sqlQuery="select a.user_code from jmi_member  a," +
			"jpo_member_order  b,jpo_member_order_list c," +
			"jpm_product_sale_team_type d,jpm_product_sale_new pnew where  " +
			"a.user_code = b.user_code " +
			"and c.product_id = d.ptt_id " +
			" and d.uni_no=pnew.uni_no" +
			" and b.mo_id = c.mo_id " +
			" and b.status='2' and b.company_code='CN' " +
			" and pnew.product_no='"+productNo+"' " +
			"and a.papernumber='"+papernumber+"' and b.order_type ='"+orderType+"' ";
		return this.findObjectsBySQL(sqlQuery);
	}
	
	
	
	
	
	
	
	
	public Map getJpoMemberPraiseMeetingUserCode(){
		 String sqlQueryT = "select * from  view_teshi";//特使
		 String sqlQueryD = "select * from view_dashi t where  not exists (select 1 from view_teshi b where b.user_code=t.user_code)";//大使
		 String sqlQueryS = "select * from view_shizhe sz where  not exists (select 1 from view_dashi ds where ds.user_code=sz.user_code)" +
		 		"and not exists (select 1 from view_teshi ts where ts.user_code=sz.user_code) ";//使者
		 String sqlQueryZ = "select * from view_zhichizhe t where  not exists (select 1 from view_shizhe b where b.user_code=t.user_code)";//支持者
		   
		  
		   List listS = (List)this.findObjectsBySQL(sqlQueryS);
		   List listD = (List)this.findObjectsBySQL(sqlQueryD);
		   List listT = (List)this.findObjectsBySQL(sqlQueryT);
		   List listZ = (List)this.findObjectsBySQL(sqlQueryZ);
		   Map<String, List<String>> mapValue=new LinkedHashMap<String,List<String>>();
		   List<String> listUserCodeS=new ArrayList<String>();
		   List<String> listUserCodeD=new ArrayList<String>();
		   List<String> listUserCodeT=new ArrayList<String>();
		   List<String> listUserCodeZ=new ArrayList<String>();
		   
		   
		   //爱心慈善特使
		   if(listT.size()>0)
		   {
			  for(int i=0;i<listT.size();i++)
			  {
				  Map mapList=(Map)listT.get(i);
				  listUserCodeT.add(mapList.get("user_code").toString());
				  listUserCodeT.add(mapList.get("qtycount").toString());
				  listUserCodeT.add(mapList.get("qtycounttotal").toString());
			  }
		  
		 
		   }
		   mapValue.put("1", listUserCodeT);
		   //爱心慈善大使
		   if(listD.size()>0)
		   {

				  for(int i=0;i<listD.size();i++)
				  {
					  Map mapList=(Map)listD.get(i);
					  listUserCodeD.add(mapList.get("user_code").toString());
					  listUserCodeD.add(mapList.get("qtycount").toString());
					  listUserCodeD.add(mapList.get("qtycounttotal").toString());
					
				  }
		 
		   }
		   mapValue.put("2", listUserCodeD);
		 
		   //爱心慈善使者
		   if(listS.size()>0)
		   {
			   for(int i=0;i<listS.size();i++)
				  {
					  Map mapList=(Map)listS.get(i);
					  listUserCodeS.add(mapList.get("user_code").toString());
					  listUserCodeS.add(mapList.get("qtycount").toString());
					  listUserCodeS.add(mapList.get("qtycounttotal").toString());
					
				  }
		  
		   }
		   mapValue.put("3", listUserCodeS);
		   
		   //爱心慈善支持者
		   if(listZ.size()>0)
		   {
			   for(int i=0;i<listZ.size();i++)
				  {
					  Map mapList=(Map)listZ.get(i);
					  listUserCodeZ.add(mapList.get("user_code").toString());
					  listUserCodeZ.add(mapList.get("qtycount").toString());
					  listUserCodeZ.add(mapList.get("qtycounttotal").toString());
					
				  }
		
		   }
		   mapValue.put("4", listUserCodeZ);

		   return mapValue;
		
	}

	@Override
    public void modifyOrderStatusByMoId(Map<String, String> map)
    {
        StringBuffer sql = new StringBuffer("update jpo_member_order set config_status = '" + map.get("status") + "' where mo_id = "+ map.get("moId"));
        this.jdbcTemplate.execute(sql.toString());
    }

	 /**
     * 根据订单号查询订单----由暂不发货转到正常发货的接口时候用（为表独立性单独建方法）
     * @author gw 2014-12-04
     * @param memberOrderNo
     * @return JpoMemberOrder
     */
	public JpoMemberOrder getJpoMemberOrderByInterface(String memberOrderNo) {
		String hql = " from JpoMemberOrder jpoMemberOrder where jpoMemberOrder.memberOrderNo='"+memberOrderNo+"' ";
		List<JpoMemberOrder> list = this.findObjectsByHqlQuery(hql);
		if(null==list){
			return null;
		}else{
			if(list.size()>0){
				return list.get(0);
			}else{
				return null;
			}
		}
	}

	public List getNotSendOrders(){
		
		String hql = " from JpoMemberOrder jpoMemberOrder where jpoMemberOrder.isSended=0 and jpoMemberOrder.status='2' and jpoMemberOrder.isPay='1' " +
				" and jpoMemberOrder.statusSysMo = 1 and jpoMemberOrder.isShipping='1'" +
//				" order by isShipping.checkTime";
				" and rownum <=600 ";
		
		List<JpoMemberOrder> list = this.findObjectsByHqlQuery(hql);
		if(null==list){
			return null;
		}else{
			return list;
		}
	}
		
	public void updateOrderSended(JpoMemberOrder jpoMemberOrder){
		String sql = "update jpo_member_order  set issended=1 where mo_id = "+ jpoMemberOrder.getMoId();
//		System.out.println("update sql ==================="+sql);
		this.jdbcTemplate.execute(sql);
	}
	
	/**
	 * 判断订单是否是挂起状态
	 * fu 2016-03-16
	 * @param memberOrderNo
	 * @return
	 */
	public boolean getOrderReturnableStatus(String memberOrderNo){
		boolean result = false;
		if(!StringUtil.isEmpty(memberOrderNo)){
			String sql = " select returnable_status from jpo_member_order where member_order_no = '"+memberOrderNo+"'";
			List  list = this.findObjectsBySQL(sql);
			if((null!=list)&&list.size()>0){
				Map map = (Map) list.get(0);
				String returnableStatus = (String) map.get("returnable_status");
				if((!StringUtil.isEmpty(returnableStatus))&&"1".equals(returnableStatus)){
					result = true;//订单处于挂起状态
				}
			}
		}
		return result;
	}
	
	/**
	 * 订单的自助换货
	 * fu 2016-03-28
	 * @param memberOrderNo
	 * @param yesOrNo Y表示允许自助换货;空或N表示禁止自助换货
	 * @return
	 */
	public void orderSelfHelpExchange(String memberOrderNo, String yesOrNo){
		if((!StringUtil.isEmpty(memberOrderNo))&&(!StringUtil.isEmpty(yesOrNo))){
			String sql = "update jpo_member_order set self_help_exchange = '"+yesOrNo+"' where member_order_no = '"+memberOrderNo+"'";
			this.getJdbcTemplate().update(sql);
		}
	}
	
}
