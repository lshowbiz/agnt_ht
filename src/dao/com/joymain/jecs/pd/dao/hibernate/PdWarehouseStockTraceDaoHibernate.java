package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdWarehouseStockTrace;
import com.joymain.jecs.pd.dao.PdWarehouseStockTraceDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;



public class PdWarehouseStockTraceDaoHibernate extends BaseDaoHibernate
		implements PdWarehouseStockTraceDao {

	/**
	 * @see com.joymain.jecs.pd.dao.PdWarehouseStockTraceDao#getPdWarehouseStockTraces(com.joymain.jecs.pd.model.PdWarehouseStockTrace)
	 */
	public List getPdWarehouseStockTraces(
			final PdWarehouseStockTrace pdWarehouseStockTrace) {
		return getHibernateTemplate().find("from PdWarehouseStockTrace");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (pdWarehouseStockTrace ==
		 * null) { return
		 * getHibernateTemplate().find("from PdWarehouseStockTrace"); } else {
		 * // filter on properties set in the pdWarehouseStockTrace
		 * HibernateCallback callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex
		 * =
		 * Example.create(pdWarehouseStockTrace).ignoreCase().enableLike(MatchMode
		 * .ANYWHERE); return
		 * session.createCriteria(PdWarehouseStockTrace.class).add(ex).list(); }
		 * }; return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdWarehouseStockTraceDao#getPdWarehouseStockTrace(Long
	 *      uniNo)
	 */
	public PdWarehouseStockTrace getPdWarehouseStockTrace(final Long uniNo) {
		PdWarehouseStockTrace pdWarehouseStockTrace = (PdWarehouseStockTrace) getHibernateTemplate()
				.get(PdWarehouseStockTrace.class, uniNo);
		if (pdWarehouseStockTrace == null) {
			log.warn("uh oh, pdWarehouseStockTrace with uniNo '" + uniNo
					+ "' not found...");
			throw new ObjectRetrievalFailureException(
					PdWarehouseStockTrace.class, uniNo);
		}

		return pdWarehouseStockTrace;
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdWarehouseStockTraceDao#savePdWarehouseStockTrace(PdWarehouseStockTrace
	 *      pdWarehouseStockTrace)
	 */
	public void savePdWarehouseStockTrace(
			final PdWarehouseStockTrace pdWarehouseStockTrace) {
		getHibernateTemplate().saveOrUpdate(pdWarehouseStockTrace);
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdWarehouseStockTraceDao#removePdWarehouseStockTrace(Long
	 *      uniNo)
	 */
	public void removePdWarehouseStockTrace(final Long uniNo) {
		getHibernateTemplate().delete(getPdWarehouseStockTrace(uniNo));
	}

	// added for getPdWarehouseStockTracesByCrm
	public List getPdWarehouseStockTracesByCrm(CommonRecord crm, Pager pager) {
		String hql = "from PdWarehouseStockTrace pdWarehouseStockTrace where 1=1";
		String companyCode = crm.getString("companyCode", "");
		if (StringUtils.isNotEmpty(companyCode)) {
			hql += " and pdWarehouseStockTrace.companyCode='" + companyCode
					+ "'";
		}
		String operatorCode = crm.getString("operatorCode", "");
		if (StringUtils.isNotEmpty(operatorCode)) {
			hql += " and pdWarehouseStockTrace.operatorCode='" + operatorCode
					+ "'";
		}

		String customCode = crm.getString("customCode", "");
		if (StringUtils.isNotEmpty(customCode)) {
			hql += " and pdWarehouseStockTrace.customCode='" + customCode + "'";
		}

		String actionType = crm.getString("actionType", "");
		if (StringUtils.isNotEmpty(actionType)) {
			hql += " and pdWarehouseStockTrace.actionType='" + actionType + "'";
		}

		String orderType = crm.getString("orderType", "");
		if (StringUtils.isNotEmpty(orderType)) {
			hql += " and pdWarehouseStockTrace.orderType='" + orderType + "'";
		}

		String subType = crm.getString("subType", "");
		if (StringUtils.isNotEmpty(subType)) {
			hql += " and pdWarehouseStockTrace.subType='" + subType + "'";
		}

		String orderNo = crm.getString("orderNo", "");
		if (StringUtils.isNotEmpty(orderNo)) {
			hql += " and pdWarehouseStockTrace.orderNo='" + orderNo + "'";
		}

		String warehouseNo = crm.getString("warehouseNo", "");
		if (StringUtils.isNotEmpty(warehouseNo)) {
			hql += " and pdWarehouseStockTrace.pdWarehouse.warehouseNo='"
					+ warehouseNo + "'";
		}

		String productNo = crm.getString("productNo", "");
		if (StringUtils.isNotEmpty(productNo)) {
			hql += " and pdWarehouseStockTrace.productNo='" + productNo + "'";
		}

		String startTime = crm.getString("startTime", "");
		if (StringUtils.isNotEmpty(startTime)) {
			hql += " and pdWarehouseStockTrace.createTime >= to_date('"
					+ startTime + " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		String endTime = crm.getString("endTime", "");
		if (StringUtils.isNotEmpty(endTime)) {
			hql += " and pdWarehouseStockTrace.createTime <= to_date('"
					+ endTime + " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}
		// 
		if (!pager.getLimit().getSort().isSorted()) {
			// sort
			hql += " order by pdWarehouseStockTrace.createTime,pdWarehouseStockTrace.uniNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty()
					+ " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}

	public List getBeginStocksByCrm(CommonRecord crm) {
		// TODO Auto-generated method stub
		String startTime = crm.getString("startTime", "");
		String companyCode = crm.getString("companyCode", "");
		String warehouseNo = crm.getString("warehouseNo", "");

		String idsql = "select row_number() over(partition by pst.warehouse_no,pst.product_no  order by create_time desc,uni_no desc) r,pst.* "
				+ "from  pd_warehouse_stock_trace pst "
				+ "where pst.create_time <to_date('"
				+ startTime
				+ " 00:00:00','yyyy-mm-dd hh24:mi:ss') and pst.company_Code='"
				+ companyCode + "' ";
		if (StringUtils.isNotEmpty(warehouseNo)) {
//			idsql += " and pst.warehouse_No='" + warehouseNo + "' ";//Modify By WuCF 20130503
			idsql += " and pst.warehouse_No in('" + warehouseNo.replace(",", "','") + "') ";
		}

		String sql = "select t.product_no,sum(nvl(t.behind_qty,0)) qty from ("
				+ idsql + ") t where t.r=1 group by t.product_no";
		// String maxIdSql =
		// "select max(uni_no)  from pd_warehouse_stock_trace pst "
		// + "where pst.create_time <to_date('"
		// + startTime
		// + " 00:00:00','yyyy-mm-dd hh24:mi:ss') and pst.company_Code='"
		// + companyCode + "' ";
		// if (StringUtils.isNotEmpty(warehouseNo)) {
		// maxIdSql += " and pst.warehouse_No='" + warehouseNo + "' ";
		// }
		// maxIdSql += " group by pst.warehouse_No,pst.product_no";
		// String sql =
		// "select product_no,sum(nvl(behind_qty,0)) qty from pd_warehouse_stock_trace where uni_no in("
		// + maxIdSql + ") group by product_no ";
		log.info("getBeginStocksByCrm=" + sql);

		return this.getJdbcTemplate().queryForList(sql);
	}

	public List getEndStocksByCrm(CommonRecord crm) {
		// TODO Auto-generated method stub
		String endTime = crm.getString("endTime", "");
		String companyCode = crm.getString("companyCode", "");
		String warehouseNo = crm.getString("warehouseNo", "");
		String idsql = "select row_number() over(partition by pst.warehouse_no,pst.product_no "
				+ " order by create_time desc,uni_no desc) r,pst.* "
				+ "from  pd_warehouse_stock_trace pst "
				+ "where pst.create_time <to_date('"
				+ endTime
				+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')+1 and pst.company_Code='"
				+ companyCode + "' ";
		if (StringUtils.isNotEmpty(warehouseNo)) {
//			idsql += " and pst.warehouse_No='" + warehouseNo + "' ";//Modify By WuCF 20130503
			idsql += " and pst.warehouse_No in('" + warehouseNo.replace(",", "','") + "') ";
		}
		String sql = "select t.product_no,sum(nvl(t.behind_qty,0)) qty from ("
			+ idsql + ") t where t.r=1 group by t.product_no";
//		String maxIdSql = "select max(uni_no)  from pd_warehouse_stock_trace pst "
//				+ "where pst.create_time <to_date('"
//				+ endTime
//				+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')+1 and pst.company_Code='"
//				+ companyCode + "' ";
//		if (StringUtils.isNotEmpty(warehouseNo)) {
//			maxIdSql += " and pst.warehouse_No='" + warehouseNo + "' ";
//		}
//		maxIdSql += " group by pst.warehouse_No,pst.product_no";
//		String sql = "select product_no,sum(nvl(behind_qty,0)) qty from pd_warehouse_stock_trace where uni_no in("
//				+ maxIdSql + ") group by product_no";
		log.info("getEndStocksByCrm=" + sql);
		return this.getJdbcTemplate().queryForList(sql);
	}

	public List getPdWarehouseStockTraceStaticByCrm(CommonRecord crm,
			String actionType) {
		// TODO Auto-generated method stub
		String startTime = crm.getString("startTime", "");
		String endTime = crm.getString("endTime", "");
		String companyCode = crm.getString("companyCode", "");
		String warehouseNo = crm.getString("warehouseNo", "");
		String sql = "select wst.product_no, sum(nvl(wst.change_qty, 0))qty"
				+ " from pd_warehouse_stock_trace wst where wst.company_code='"
				+ companyCode
				+ "' and wst.create_time >= to_date('"
				+ startTime
				+ " 00:00:00','yyyy-mm-dd hh24:mi:ss') and wst.create_time < to_date('"
				+ endTime
				+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')+1 and wst.action_type='"
				+ actionType + "' ";
		if (StringUtils.isNotEmpty(warehouseNo)) {
//			sql += " and wst.warehouse_No='" + warehouseNo + "' ";//Modify By WuCF 20130503
			sql += " and wst.warehouse_No in('" + warehouseNo.replace(",", "','") + "') ";
		}
		sql += " group by wst.product_no";
		return this.getJdbcTemplate().queryForList(sql);
	}

	public List getPdWarehouseStockTraceStaticByCrm(CommonRecord crm) {
		// TODO Auto-generated method stub
		String startTime = crm.getString("startTime", "");
		String endTime = crm.getString("endTime", "");
		String companyCode = crm.getString("companyCode", "");
		String warehouseNo = crm.getString("warehouseNo", "");
		String sql = "select wst.product_no,wst.action_type, sum(nvl(wst.change_qty, 0))qty"
				+ " from pd_warehouse_stock_trace wst where wst.company_code='"
				+ companyCode
				+ "' and wst.create_time >= to_date('"
				+ startTime
				+ " 00:00:00','yyyy-mm-dd hh24:mi:ss') and wst.create_time < to_date('"
				+ endTime + " 00:00:00','yyyy-mm-dd hh24:mi:ss')+1 ";
		if (StringUtils.isNotEmpty(warehouseNo)) {
//			sql += " and wst.warehouse_No='" + warehouseNo + "' ";//Modify By WuCF 20130503
			sql += " and wst.warehouse_No in('" + warehouseNo.replace(",", "','") + "') ";
		}
		sql += " group by wst.product_no,wst.action_type";
		log.info("getPdWarehouseStockTraceStaticByCrm=" + sql);
		return this.getJdbcTemplate().queryForList(sql);
	}

	public List getStockTraceDetailByCrm(CommonRecord crm) {
		// TODO Auto-generated method stub
		String startTime = crm.getString("startTime", "");
		String endTime = crm.getString("endTime", "");
		String companyCode = crm.getString("companyCode", "");
		String warehouseNo = crm.getString("warehouseNo", "");
		String sql = "select wst.product_no,wst.order_type,wst.sub_type, sum(nvl(wst.change_qty, 0))qty"
				+ " from pd_warehouse_stock_trace wst where wst.company_code='"
				+ companyCode
				+ "' and wst.create_time >= to_date('"
				+ startTime
				+ " 00:00:00','yyyy-mm-dd hh24:mi:ss') and wst.create_time < to_date('"
				+ endTime + " 00:00:00','yyyy-mm-dd hh24:mi:ss')+1 ";
		if (StringUtils.isNotEmpty(warehouseNo)) {
			//sql += " and wst.warehouse_No='" + warehouseNo + "' ";//Modify By WuCF 20130502
			sql += " and wst.warehouse_No in('" + warehouseNo.replace(",", "','") + "') ";
		}
		sql += " group by wst.product_no,wst.order_type,wst.sub_type";
		log.info("getPdWarehouseStockTraceStaticByCrm=" + sql);
		return this.getJdbcTemplate().queryForList(sql);
	}
	
	public List getStockTraceFinceByCrm(CommonRecord crm) {
		// TODO Auto-generated method stub
		String startTime = crm.getString("startTime", "");
		String endTime = crm.getString("endTime", "");
		String companyCode = crm.getString("companyCode", "");
		String warehouseNo = crm.getString("warehouseNo", "");
		String sql = "select wst.product_no,wst.order_type,wst.sub_type, sum(nvl(wst.change_qty, 0))qty"
			+ " from pd_warehouse_stock_trace wst where wst.company_code='"
			+ companyCode
			+ "' and wst.create_time >= to_date('"
			+ startTime
			+ " 00:00:00','yyyy-mm-dd hh24:mi:ss') and wst.create_time < to_date('"
			+ endTime + " 00:00:00','yyyy-mm-dd hh24:mi:ss')+1 ";
		if (StringUtils.isNotEmpty(warehouseNo)) {
//			sql += " and wst.warehouse_No='" + warehouseNo + "' ";//Modify By WuCF 20130503
			sql += " and wst.warehouse_No in('" + warehouseNo.replace(",", "','") + "') ";
		}
		sql += " group by wst.product_no,wst.order_type,wst.sub_type";
		log.info("getStockTraceFinceByCrm=" + sql);
		return this.getJdbcTemplate().queryForList(sql);
	}

	/**
	 * 判断发货退回单是否已经做过入库的操作
	 * @author modify fu 2015-12-26 
	 * @param rpNo
	 * @param productNo
	 * @return boolean true 表明发货退回单已经做过入库的操作了
	 */
	public boolean getPdReturnPurhcaseChangeStock(String rpNo,String productNo){
		boolean result = false;
		if(!StringUtil.isEmpty(rpNo)){
		    String sql = "select *  from pd_warehouse_stock_trace where order_no = '"+rpNo+"' and product_no = '"+productNo+"'";
		    List list = this.findObjectsBySQL(sql);
		    if(null!=list){
		    	if(list.size()>0){
		    		result = true;
		    	}
		    }
		}
		return result;
	}

}
