package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdAdjustStockDetail;
import com.joymain.jecs.pd.dao.PdAdjustStockDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class PdAdjustStockDetailDaoHibernate extends BaseDaoHibernate implements
		PdAdjustStockDetailDao {

	public List getPdAdjustStockDetails(String productNo, String asNo) {
		// TODO Auto-generated method stub
		String hql = "from PdAdjustStockDetail pd where pd.productNo='"+productNo+"' and pd.asNo='"+asNo+"'";
		return getHibernateTemplate().find(hql);
	}

	public List getTotalPdAdjustStockDetails(CommonRecord crm) {
		// TODO Auto-generated method stub
		String sql = "select pm.product_no,pm.product_name,"
				+ "nvl(sum(case when pdd.qty>0 then pdd.qty end),0) as qty_positive,"
				+ "nvl(sum(case when pdd.qty<0 then pdd.qty end),0) as qty_negative,"
				+ "nvl(sum(pdd.qty),0) as qty_total"
				+ " from pd_adjust_stock pd,pd_adjust_stock_detail pdd,jpm_product_sale_new pm"
				+ " where pd.as_no=pdd.as_no and pdd.product_no=pm.product_no and pm.company_Code=pd.company_Code ";
		//Modify By WuCF 20150602 添加仓库权限统计
		String defaultWarehouse = crm.getString("defaultWarehouse", "");
		if(StringUtils.isNotEmpty(defaultWarehouse)){
			sql += " and pd.WAREHOUSE_NO in("+defaultWarehouse+")";
    	}
		String companyCode = crm.getString("companyCode", "");
		if (StringUtils.isNotEmpty(companyCode)) {
			sql += " and pd.company_Code='" + companyCode + "'";
		}
		String warehouseNo = crm.getString("warehouseNo", "");
		if (StringUtils.isNotEmpty(warehouseNo)) {
			sql += " and pd.WAREHOUSE_NO='" + warehouseNo + "'";
		}
		String productNo = crm.getString("productNo", "");
		if (StringUtils.isNotEmpty(productNo)) {
			sql += " and pdd.product_no='" + productNo + "'";
		}
		String asNo = crm.getString("asNo", "");
		if (StringUtils.isNotEmpty(asNo)) {
			sql += " and pd.as_No='" + asNo + "'";
		}
		String astNo = crm.getString("astNo", "");
		if (StringUtils.isNotEmpty(astNo)) {
			sql += " and pd.ast_No='" + astNo + "'";
		}
		String createTimeStart = crm.getString("createTimeStart", "");
		String createTimeEnd = crm.getString("createTimeEnd", "");
		if (StringUtils.isNotEmpty(createTimeStart)) {
			sql += " and pd.create_Time >=to_date('" + createTimeStart
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		if (StringUtils.isNotEmpty(createTimeEnd)) {
			sql += " and pd.create_Time <=to_date('" + createTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}

		// 确认时间
		String okTimeBegain = crm.getString("okTimeStart", "");
		if (StringUtils.isNotEmpty(okTimeBegain)) {
			sql += " and pd.ok_Time >=to_date('" + okTimeBegain
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		String okTimeEnd = crm.getString("okTimeEnd", "");
		if (StringUtils.isNotEmpty(okTimeEnd)) {
			sql += " and pd.ok_Time <=to_date('" + okTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}
		String orderFlag = crm.getString("orderFlag","");
    	if(StringUtils.isNotEmpty(orderFlag)){
    		sql +=" and pd.order_Flag in("+ orderFlag +")";
    	}
    	
    	//Modify By WuCF 20150617 加上创建者、审核者、确认者查询条件判断查询
    	String createUsrNo = crm.getString("createusrcode", ""); 
		if (StringUtils.isNotEmpty(createUsrNo)) {
			sql += " and pd.CREATE_URS_CODE ='" + createUsrNo.trim() + "' ";
		}
		
		String checkUsrNo = crm.getString("checkusrcode", "");
		if (StringUtils.isNotEmpty(checkUsrNo)) {
			sql += " and pd.CHECK_URS_CODE ='" + checkUsrNo.trim() + "' ";
		}
		
		String okUsrNo = crm.getString("okusrcode", "");
		if (StringUtils.isNotEmpty(okUsrNo)) {
			sql += " and pd.OK_URS_CODE ='" + okUsrNo.trim() + "' ";
		}
    	
		/*String okStatus = crm.getString("okStatus", "");
		if (StringUtils.isNotEmpty(okStatus)) {
			sql += " and pd.ok_Status='" + okStatus + "'";
		}
		String checkStatus = crm.getString("checkStatus", "");
		if (StringUtils.isNotEmpty(checkStatus)) {
			sql += " and pd.check_Status='" + checkStatus + "'";
		}*/
		sql += " group by pm.product_no,pm.product_name order by pm.product_no ";
		return this.getJdbcTemplate().queryForList(sql);
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdAdjustStockDetailDao#getPdAdjustStockDetails(com.joymain.jecs.pd.model.PdAdjustStockDetail)
	 */
	public List getPdAdjustStockDetails(
			final PdAdjustStockDetail pdAdjustStockDetail) {
		return getHibernateTemplate().find("from PdAdjustStockDetail");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (pdAdjustStockDetail ==
		 * null) { return
		 * getHibernateTemplate().find("from PdAdjustStockDetail"); } else { //
		 * filter on properties set in the pdAdjustStockDetail HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex
		 * =
		 * Example.create(pdAdjustStockDetail).ignoreCase().enableLike(MatchMode
		 * .ANYWHERE); return
		 * session.createCriteria(PdAdjustStockDetail.class).add(ex).list(); }
		 * }; return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdAdjustStockDetailDao#getPdAdjustStockDetail(Long
	 *      uniNo)
	 */
	public PdAdjustStockDetail getPdAdjustStockDetail(final Long uniNo) {
		PdAdjustStockDetail pdAdjustStockDetail = (PdAdjustStockDetail) getHibernateTemplate()
				.get(PdAdjustStockDetail.class, uniNo);
		if (pdAdjustStockDetail == null) {
			log.warn("uh oh, pdAdjustStockDetail with uniNo '" + uniNo
					+ "' not found...");
			throw new ObjectRetrievalFailureException(
					PdAdjustStockDetail.class, uniNo);
		}

		return pdAdjustStockDetail;
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdAdjustStockDetailDao#savePdAdjustStockDetail(PdAdjustStockDetail
	 *      pdAdjustStockDetail)
	 */
	public void savePdAdjustStockDetail(
			final PdAdjustStockDetail pdAdjustStockDetail) {
		getHibernateTemplate().saveOrUpdate(pdAdjustStockDetail);
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdAdjustStockDetailDao#removePdAdjustStockDetail(Long
	 *      uniNo)
	 */
	public void removePdAdjustStockDetail(final Long uniNo) {
		getHibernateTemplate().delete(getPdAdjustStockDetail(uniNo));
	}

	// added for getPdAdjustStockDetailsByCrm
	public List getPdAdjustStockDetailsByCrm(CommonRecord crm, Pager pager) {
		String hql = "from PdAdjustStockDetail pdAdjustStockDetail where 1=1";
		/**
		 * ---example--- String userCode = crm.getString("userCode", "");
		 * if(StringUtils.isNotEmpty(userCode)){ hql += "???????????"; }
		 ***/
		// 
		if (!pager.getLimit().getSort().isSorted()) {
			// sort
			hql += " order by uniNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty()
					+ " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}
}
