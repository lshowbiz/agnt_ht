package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdOutWarehouseDetail;
import com.joymain.jecs.pd.dao.PdOutWarehouseDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class PdOutWarehouseDetailDaoHibernate extends BaseDaoHibernate
		implements PdOutWarehouseDetailDao {

	public List getPdOutWarehouseDetails(String productNo, String owNo,
			Long owdNo) {
		// TODO Auto-generated method stub
		String hql = "from PdOutWarehouseDetail pd where pd.productNo='"+productNo+"' and pd.owNo='"+owNo+"'";
		if(owdNo != null){
			hql +=" and pd.uniNo <> '"+owdNo+"'"; 
		}
		return getHibernateTemplate().find(hql);
	}

	public List getTotalPdOutWarehouseDetails(CommonRecord crm) {
		// TODO Auto-generated method stub
		String sql = "select pm.product_no,pm.product_name,sum(pdd.qty) qty from pd_out_warehouse pd,pd_out_warehouse_detail pdd,jpm_product_sale_new pm"
				+ " where pd.ow_no=pdd.ow_no and pdd.product_no=pm.product_no and pm.company_Code=pd.company_Code ";
		
		//Modify By WuCF 20150602 添加仓库权限统计
		String defaultWarehouse = crm.getString("defaultWarehouse", "");
		if(StringUtils.isNotEmpty(defaultWarehouse)){
			sql += " and pd.WAREHOUSE_NO in("+defaultWarehouse+")";
    	}
		
		//Modify By WuCF 20150617 加上创建者、审核者、确认者查询条件判断查询
    	String createUsrNo = crm.getString("createusrcode", ""); 
		if (StringUtils.isNotEmpty(createUsrNo)) {
			sql += " and pd.CREATE_USR_CODE ='" + createUsrNo.trim() + "' ";
		}
		
		String companyCode = crm.getString("companyCode", "");
		if (StringUtils.isNotEmpty(companyCode)) {
			sql += " and pd.company_Code='" + companyCode + "'";
		}

		String warehouseNo = crm.getString("warehouseNo", "");
		if (StringUtils.isNotEmpty(warehouseNo)) {
			sql += " and pd.WAREHOUSE_NO='" + warehouseNo + "'";
		}

		String orderFlag = crm.getString("orderFlag", "");
		if (StringUtils.isNotEmpty(orderFlag)) {
			sql += " and pd.order_Flag in(" + orderFlag + ")";
		}
		
		String owNo = crm.getString("owNo", "");
		if (StringUtils.isNotEmpty(owNo)) {
			sql += " and pd.ow_No='" + owNo + "'";
		}
		/*String productNo = crm.getString("productNo", "");
		if (StringUtils.isNotEmpty(productNo)) {
			sql += " and pdd.product_no='" + productNo + "'";
		}*/
		
		//Modify By WUCF 统计添加上商品编码20150918
		String productNo = crm.getString("productNo", "");
		if (StringUtils.isNotEmpty(productNo)) {
			sql += " and exists(select 1 from pd_out_warehouse_detail tt where tt.ow_no=pd.ow_no and tt.product_no in('"+productNo.trim().replace(",", "','")+"') ) ";
		}
		
		String owtNo = crm.getString("owtNo", "");
		if (StringUtils.isNotEmpty(owtNo)) {
			sql += " and pd.owt_No='" + owtNo + "'";
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
	 * @see com.joymain.jecs.pd.dao.PdOutWarehouseDetailDao#getPdOutWarehouseDetails(com.joymain.jecs.pd.model.PdOutWarehouseDetail)
	 */
	public List getPdOutWarehouseDetails(
			final PdOutWarehouseDetail pdOutWarehouseDetail) {
		return getHibernateTemplate().find("from PdOutWarehouseDetail");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (pdOutWarehouseDetail ==
		 * null) { return
		 * getHibernateTemplate().find("from PdOutWarehouseDetail"); } else { //
		 * filter on properties set in the pdOutWarehouseDetail
		 * HibernateCallback callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex
		 * =
		 * Example.create(pdOutWarehouseDetail).ignoreCase().enableLike(MatchMode
		 * .ANYWHERE); return
		 * session.createCriteria(PdOutWarehouseDetail.class).add(ex).list(); }
		 * }; return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdOutWarehouseDetailDao#getPdOutWarehouseDetail(Long
	 *      uniNo)
	 */
	public PdOutWarehouseDetail getPdOutWarehouseDetail(final Long uniNo) {
		PdOutWarehouseDetail pdOutWarehouseDetail = (PdOutWarehouseDetail) getHibernateTemplate()
				.get(PdOutWarehouseDetail.class, uniNo);
		if (pdOutWarehouseDetail == null) {
			log.warn("uh oh, pdOutWarehouseDetail with uniNo '" + uniNo
					+ "' not found...");
			throw new ObjectRetrievalFailureException(
					PdOutWarehouseDetail.class, uniNo);
		}

		return pdOutWarehouseDetail;
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdOutWarehouseDetailDao#savePdOutWarehouseDetail(PdOutWarehouseDetail
	 *      pdOutWarehouseDetail)
	 */
	public void savePdOutWarehouseDetail(
			final PdOutWarehouseDetail pdOutWarehouseDetail) {
		getHibernateTemplate().saveOrUpdate(pdOutWarehouseDetail);
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdOutWarehouseDetailDao#removePdOutWarehouseDetail(Long
	 *      uniNo)
	 */
	public void removePdOutWarehouseDetail(final Long uniNo) {
		getHibernateTemplate().delete(getPdOutWarehouseDetail(uniNo));
	}

	// added for getPdOutWarehouseDetailsByCrm
	public List getPdOutWarehouseDetailsByCrm(CommonRecord crm, Pager pager) {
		String hql = "from PdOutWarehouseDetail pdOutWarehouseDetail where 1=1";
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
