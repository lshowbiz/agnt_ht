package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdCombinationDetail;
import com.joymain.jecs.pd.dao.PdCombinationDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class PdCombinationDetailDaoHibernate extends BaseDaoHibernate implements
		PdCombinationDetailDao {

	public List getPdCombinationDetailTotals(CommonRecord crm) {
		// TODO Auto-generated method stub
		String sql = "select pm.product_no,pm.product_name,sum(pdd.qty) qty from pd_combination_order pd,pd_combination_detail pdd,jpm_product_sale_new pm"
				+ " where pd.pc_no=pdd.pc_no and pdd.product_no=pm.product_no and pm.company_Code=pd.company_Code ";
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

		String pcNo = crm.getString("pcNo", "");
		if (StringUtils.isNotEmpty(pcNo)) {
			sql += " and pd.pc_No='" + pcNo + "'";
		}
		String productNo = crm.getString("productNo", "");
		if (StringUtils.isNotEmpty(productNo)) {
			sql += " and pdd.product_no='" + productNo + "'";
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
		/*
		 * String okStatus = crm.getString("okStatus", ""); if
		 * (StringUtils.isNotEmpty(okStatus)) { sql += " and pd.ok_Status='" +
		 * okStatus + "'"; } String checkStatus = crm.getString("checkStatus",
		 * ""); if (StringUtils.isNotEmpty(checkStatus)) { sql +=
		 * " and pd.check_Status='" + checkStatus + "'"; }
		 */
		sql += " group by pm.product_no,pm.product_name order by pm.product_no ";
		return this.getJdbcTemplate().queryForList(sql);
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdCombinationDetailDao#getPdCombinationDetails(com.joymain.jecs.pd.model.PdCombinationDetail)
	 */
	public List getPdCombinationDetails(
			final PdCombinationDetail pdCombinationDetail) {
		return getHibernateTemplate().find("from PdCombinationDetail");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (pdCombinationDetail ==
		 * null) { return
		 * getHibernateTemplate().find("from PdCombinationDetail"); } else { //
		 * filter on properties set in the pdCombinationDetail HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex
		 * =
		 * Example.create(pdCombinationDetail).ignoreCase().enableLike(MatchMode
		 * .ANYWHERE); return
		 * session.createCriteria(PdCombinationDetail.class).add(ex).list(); }
		 * }; return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdCombinationDetailDao#getPdCombinationDetail(Long
	 *      uniNo)
	 */
	public PdCombinationDetail getPdCombinationDetail(final Long uniNo) {
		PdCombinationDetail pdCombinationDetail = (PdCombinationDetail) getHibernateTemplate()
				.get(PdCombinationDetail.class, uniNo);
		if (pdCombinationDetail == null) {
			log.warn("uh oh, pdCombinationDetail with uniNo '" + uniNo
					+ "' not found...");
			throw new ObjectRetrievalFailureException(
					PdCombinationDetail.class, uniNo);
		}

		return pdCombinationDetail;
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdCombinationDetailDao#savePdCombinationDetail(PdCombinationDetail
	 *      pdCombinationDetail)
	 */
	public void savePdCombinationDetail(
			final PdCombinationDetail pdCombinationDetail) {
		getHibernateTemplate().saveOrUpdate(pdCombinationDetail);
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdCombinationDetailDao#removePdCombinationDetail(Long
	 *      uniNo)
	 */
	public void removePdCombinationDetail(final Long uniNo) {
		getHibernateTemplate().delete(getPdCombinationDetail(uniNo));
	}

	// added for getPdCombinationDetailsByCrm
	public List getPdCombinationDetailsByCrm(CommonRecord crm, Pager pager) {
		String hql = "from PdCombinationDetail pdCombinationDetail where 1=1";
		String pcNo = crm.getString("pcNo", "");
		if (StringUtils.isNotEmpty(pcNo)) {
			hql += " and pdCombinationDetail.pcNo='" + pcNo + "' ";
		}
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
