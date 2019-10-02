package com.joymain.jecs.pd.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdEnterWarehouseDetail;
import com.joymain.jecs.pd.dao.PdEnterWarehouseDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class PdEnterWarehouseDetailDaoHibernate extends BaseDaoHibernate
		implements PdEnterWarehouseDetailDao {

	public List getPdEnterWarehouseDetails(String productNo, String ewNo) {
		// TODO Auto-generated method stub
		String hql = "from PdEnterWarehouseDetail pe where pe.productNo='"
				+ productNo + "' and pe.ewNo = '" + ewNo + "'";
		return getHibernateTemplate().find(hql);
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdEnterWarehouseDetailDao#getPdEnterWarehouseDetails(com.joymain.jecs.pd.model.PdEnterWarehouseDetail)
	 */
	public List getPdEnterWarehouseDetails(
			final PdEnterWarehouseDetail pdEnterWarehouseDetail) {
		return getHibernateTemplate().find("from PdEnterWarehouseDetail");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (pdEnterWarehouseDetail ==
		 * null) { return
		 * getHibernateTemplate().find("from PdEnterWarehouseDetail"); } else {
		 * // filter on properties set in the pdEnterWarehouseDetail
		 * HibernateCallback callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex
		 * =
		 * Example.create(pdEnterWarehouseDetail).ignoreCase().enableLike(MatchMode
		 * .ANYWHERE); return
		 * session.createCriteria(PdEnterWarehouseDetail.class).add(ex).list();
		 * } }; return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdEnterWarehouseDetailDao#getPdEnterWarehouseDetail(BigDecimal
	 *      uniNo)
	 */
	public PdEnterWarehouseDetail getPdEnterWarehouseDetail(final Long uniNo) {
		PdEnterWarehouseDetail pdEnterWarehouseDetail = (PdEnterWarehouseDetail) getHibernateTemplate()
				.get(PdEnterWarehouseDetail.class, uniNo);
		if (pdEnterWarehouseDetail == null) {
			log.warn("uh oh, pdEnterWarehouseDetail with uniNo '" + uniNo
					+ "' not found...");
			throw new ObjectRetrievalFailureException(
					PdEnterWarehouseDetail.class, uniNo);
		}

		return pdEnterWarehouseDetail;
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdEnterWarehouseDetailDao#savePdEnterWarehouseDetail(PdEnterWarehouseDetail
	 *      pdEnterWarehouseDetail)
	 */
	public void savePdEnterWarehouseDetail(
			final PdEnterWarehouseDetail pdEnterWarehouseDetail) {
		getHibernateTemplate().saveOrUpdate(pdEnterWarehouseDetail);
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdEnterWarehouseDetailDao#removePdEnterWarehouseDetail(BigDecimal
	 *      uniNo)
	 */
	public void removePdEnterWarehouseDetail(final Long uniNo) {
		getHibernateTemplate().delete(getPdEnterWarehouseDetail(uniNo));
	}

	public List getTotolPdEnterWarehouseDetails(CommonRecord crm) {
		List list = new ArrayList();
		String sql = "select ewd.product_no,pm.product_name,sum(ewd.qty) qty from pd_enter_warehouse_detail ewd,pd_enter_warehouse ew,jpm_product_sale_new pm "
				+ "where ewd.ew_no=ew.ew_no and pm.product_no=ewd.product_no  and ew.company_Code=pm.company_Code ";

		//Modify By WuCF 20150602 添加仓库权限统计
		String defaultWarehouse = crm.getString("defaultWarehouse", "");
		if(StringUtils.isNotEmpty(defaultWarehouse)){
			sql += " and ew.WAREHOUSE_NO in("+defaultWarehouse+")";
    	}
		
		String warehouseNo = crm.getString("warehouseNo","");
    	if(StringUtils.isNotEmpty(warehouseNo)){
    		sql += " and ew.warehouse_No='"+warehouseNo+"'";
    	}
		
    	
		String companyCode = crm.getString("companyCode", "");
		if (StringUtils.isNotEmpty(companyCode)) {
			sql += " and ew.company_Code='" + companyCode + "'";
		}
		String ewNo = crm.getString("ewNo", "");
		if (StringUtils.isNotEmpty(ewNo)) {
			sql += " and ew.ew_No='" + ewNo + "'";
		}
		String productNo = crm.getString("productNo", "");
		if (StringUtils.isNotEmpty(productNo)) {
			sql += " and ewd.product_no='" + productNo + "'";
		}
		String createTimeStart = crm.getString("createTimeStart", "");
		String createTimeEnd = crm.getString("createTimeEnd", "");
		if (StringUtils.isNotEmpty(createTimeStart)) {
			sql += " and ew.create_Time >=to_date('" + createTimeStart
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		if (StringUtils.isNotEmpty(createTimeEnd)) {
			sql += " and ew.create_Time <=to_date('" + createTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}

		// ȷ��ʱ��
		String okTimeBegain = crm.getString("okTimeStart", "");
		if (StringUtils.isNotEmpty(okTimeBegain)) {
			sql += " and ew.ok_Time >=to_date('" + okTimeBegain
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		String okTimeEnd = crm.getString("okTimeEnd", "");
		if (StringUtils.isNotEmpty(okTimeEnd)) {
			sql += " and ew.ok_Time <=to_date('" + okTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}
		
		String orderFlag = crm.getString("orderFlag", "");
    	if(StringUtils.isNotEmpty(orderFlag)){
    		sql +=" and ew.order_Flag ="+orderFlag+" ";
    	}
		/*
		 * String okStatus = crm.getString("okStatus", "");
		 * if(StringUtils.isNotEmpty(okStatus)){ sql +=
		 * " and ew.ok_Status='"+okStatus+"'"; } String checkStatus =
		 * crm.getString("checkStatus","");
		 * if(StringUtils.isNotEmpty(checkStatus)){ sql +=
		 * " and ew.check_Status='"+checkStatus+"'"; }
		 */
		sql += " group by ewd.product_no,pm.product_name order by ewd.product_no ";
		return this.getJdbcTemplate().queryForList(sql);
	}

	public List getPdEnterWarehouseDetailsByCrm(CommonRecord crm, Pager pager) {
		String hql = "from PdEnterWarehouseDetail pd where 1=1";
		String ewNo = crm.getString("ewNo", "");
		if (StringUtils.isNotEmpty(ewNo)) {
			hql += " and pd.ewNo='" + ewNo + "'";
		}

		if (pager == null) {
			return this.getHibernateTemplate().find(hql);
		}
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

	public List getEnterWarehouseStaticsByCrm(CommonRecord crm) {
		// TODO Auto-generated method stub
		String startTime = crm.getString("startTime", "");
		String companyCode = crm.getString("companyCode", "");
		String warehouseNo = crm.getString("warehouseNo", "");
		String endTime = crm.getString("endTime", "");
		String sql="select ewd.product_no product_no,sum(nvl(ewd.qty,0))qty from pd_enter_warehouse ew ,pd_enter_warehouse_detail ewd" +
				" where ew.ew_no=ewd.ew_no and ew.company_Code='"+companyCode+"' ";
		sql+=" and ew.OK_TIME >=to_date('"
				+ startTime
				+ " 00:00:00','yyyy-mm-dd hh24:mi:ss') ";
		sql+=" and ew.OK_TIME <to_date('"
			+ endTime
			+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')+1 ";
		if(StringUtils.isNotEmpty(warehouseNo)){
			sql+=" and ew.warehouse_no ='"+warehouseNo+"' ";
		}
		sql+=" group by ewd.product_no";
		
		log.info("getEnterWarehouseStaticsByCrm="+sql);
		return this.getJdbcTemplate().queryForList(sql);
	}
	
	
}
