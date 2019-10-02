package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdShipmentsDetail;
import com.joymain.jecs.pd.dao.PdShipmentsDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class PdShipmentsDetailDaoHibernate extends BaseDaoHibernate implements
		PdShipmentsDetailDao {

	public PdShipmentsDetail getPdShipmentsDetail(String orderNo,
			String productNo) {
		// TODO Auto-generated method stub
		String hqlQuery = " from PdShipmentsDetail pdShipmentsDetail where pdShipmentsDetail.orderNo='"
				+ orderNo
				+ "' and pdShipmentsDetail.productNo='"
				+ productNo
				+ "'";
		
		return (PdShipmentsDetail) this.getObjectByHqlQuery(hqlQuery);
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdShipmentsDetailDao#getPdShipmentsDetails(com.joymain.jecs.pd.model.PdShipmentsDetail)
	 */
	public List getPdShipmentsDetails(final PdShipmentsDetail pdShipmentsDetail) {
		return getHibernateTemplate().find("from PdShipmentsDetail");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (pdShipmentsDetail == null)
		 * { return getHibernateTemplate().find("from PdShipmentsDetail"); }
		 * else { // filter on properties set in the pdShipmentsDetail
		 * HibernateCallback callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex
		 * =
		 * Example.create(pdShipmentsDetail).ignoreCase().enableLike(MatchMode.
		 * ANYWHERE); return
		 * session.createCriteria(PdShipmentsDetail.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdShipmentsDetailDao#getPdShipmentsDetail(Long
	 *      sdId)
	 */
	public PdShipmentsDetail getPdShipmentsDetail(final Long sdId) {
		PdShipmentsDetail pdShipmentsDetail = (PdShipmentsDetail) getHibernateTemplate()
				.get(PdShipmentsDetail.class, sdId);
		if (pdShipmentsDetail == null) {
			log.warn("uh oh, pdShipmentsDetail with sdId '" + sdId
					+ "' not found...");
			throw new ObjectRetrievalFailureException(PdShipmentsDetail.class,
					sdId);
		}

		return pdShipmentsDetail;
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdShipmentsDetailDao#savePdShipmentsDetail(PdShipmentsDetail
	 *      pdShipmentsDetail)
	 */
	public void savePdShipmentsDetail(final PdShipmentsDetail pdShipmentsDetail) {
		getHibernateTemplate().saveOrUpdate(pdShipmentsDetail);
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdShipmentsDetailDao#removePdShipmentsDetail(Long
	 *      sdId)
	 */
	public void removePdShipmentsDetail(final Long sdId) {
		getHibernateTemplate().delete(getPdShipmentsDetail(sdId));
	}

	// added for getPdShipmentsDetailsByCrm
	public List getPdShipmentsDetailsByCrm(CommonRecord crm, Pager pager) {
		String hql = "from PdShipmentsDetail pdShipmentsDetail where 1=1";
		String control = crm.getString("control", "");// 控制
		if (StringUtils.isNotEmpty(control)) {
			// hql+=" and pdShipmentsDetail.orderNo='"+orderNo+"' ";
		}
		// 
		String productNo = crm.getString("productNo", "");
		if (StringUtils.isNotEmpty(productNo)) {
			hql += " and pdShipmentsDetail.productNo='" + productNo + "' ";
		}
		String companyCode = crm.getString("companyCode", "");
		if (StringUtils.isNotEmpty(companyCode)) {
			hql += " and pdShipmentsDetail.companyCode='" + companyCode + "' ";
		}
		String userCode = crm.getString("userCode", "");
		if (StringUtils.isNotEmpty(userCode)) {
			hql += " and pdShipmentsDetail.consignee.userCode='" + userCode
					+ "' ";
		}
		String orderType = crm.getString("orderType", "");
		if (StringUtils.isNotEmpty(orderType)) {
			hql += " and pdShipmentsDetail.orderType='" + orderType + "' ";
		}
		String orderNo = crm.getString("orderNo", "");
		if (StringUtils.isNotEmpty(orderNo)) {
			hql += " and pdShipmentsDetail.orderNo='" + orderNo + "' ";
		}

		String stateCode = crm.getString("stateCode", "");
		if (StringUtils.isNotEmpty(stateCode)) {
			hql += " and pdShipmentsDetail.stateCode='" + stateCode + "' ";
		}
		// String siNo = crm.getString("siNo", "");
		// if(StringUtils.isNotEmpty(siNo)){
		// hql+=" and pdShipmentsDetail.orderNo='"+siNo+"' ";
		// }
		if (pager == null) {
			return this.getHibernateTemplate().find(hql);
		}
		// 
		if (!pager.getLimit().getSort().isSorted()) {
			// sort
			hql += " order by sdId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty()
					+ " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}
}
