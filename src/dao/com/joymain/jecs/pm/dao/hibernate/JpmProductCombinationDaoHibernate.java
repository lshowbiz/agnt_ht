package com.joymain.jecs.pm.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.model.JpmProductCombination;
import com.joymain.jecs.pm.dao.JpmProductCombinationDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JpmProductCombinationDaoHibernate extends BaseDaoHibernate
		implements JpmProductCombinationDao {

	/**
	 * @see com.joymain.jecs.pm.dao.JpmProductCombinationDao#getJpmProductCombinations(com.joymain.jecs.pm.model.JpmProductCombination)
	 */
	public List getJpmProductCombinations(
			final JpmProductCombination jpmProductCombination) {
		return getHibernateTemplate().find("from JpmProductCombination");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (jpmProductCombination ==
		 * null) { return
		 * getHibernateTemplate().find("from JpmProductCombination"); } else {
		 * // filter on properties set in the jpmProductCombination
		 * HibernateCallback callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex
		 * =
		 * Example.create(jpmProductCombination).ignoreCase().enableLike(MatchMode
		 * .ANYWHERE); return
		 * session.createCriteria(JpmProductCombination.class).add(ex).list(); }
		 * }; return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.joymain.jecs.pm.dao.JpmProductCombinationDao#getJpmProductCombination(Long
	 *      jpcId)
	 */
	public JpmProductCombination getJpmProductCombination(final Long jpcId) {
		JpmProductCombination jpmProductCombination = (JpmProductCombination) getHibernateTemplate()
				.get(JpmProductCombination.class, jpcId);
		if (jpmProductCombination == null) {
			log.warn("uh oh, jpmProductCombination with jpcId '" + jpcId
					+ "' not found...");
			throw new ObjectRetrievalFailureException(
					JpmProductCombination.class, jpcId);
		}

		return jpmProductCombination;
	}

	/**
	 * @see com.joymain.jecs.pm.dao.JpmProductCombinationDao#saveJpmProductCombination(JpmProductCombination
	 *      jpmProductCombination)
	 */
	public void saveJpmProductCombination(
			final JpmProductCombination jpmProductCombination) {
		getHibernateTemplate().saveOrUpdate(jpmProductCombination);
	}

	/**
	 * @see com.joymain.jecs.pm.dao.JpmProductCombinationDao#removeJpmProductCombination(Long
	 *      jpcId)
	 */
	public void removeJpmProductCombination(final Long jpcId) {
		getHibernateTemplate().delete(getJpmProductCombination(jpcId));
	}

	// added for getJpmProductCombinationsByCrm
	public List getJpmProductCombinationsByCrm(CommonRecord crm, Pager pager) {
		String hql = "from JpmProductCombination jpmProductCombination where 1=1";
		/**
		 * ---example--- String userCode = crm.getString("userCode", "");
		 * if(StringUtils.isNotEmpty(userCode)){ hql += "???????????"; }
		 ***/

		String productNo = crm.getString("productNo", "");
		if (StringUtils.isNotEmpty(productNo)) {
			hql += " and jpmProductCombination.productNo='" + productNo + "'";
		}
		String subProductNo = crm.getString("subProductNo", "");
		if (StringUtils.isNotEmpty(subProductNo)) {
			hql += " and jpmProductCombination.subProductNo='" + subProductNo
					+ "'";
		}
		// 
		if (!pager.getLimit().getSort().isSorted()) {
			// sort
			hql += " order by jpcId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty()
					+ " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}

	public List getJpmProductCombinations(String subProductNo, String productNo) {
		// TODO Auto-generated method stub
		String queryString = "from JpmProductCombination jpmProductCombination where jpmProductCombination.productNo='"
				+ productNo
				+ "' and jpmProductCombination.subProductNo='"
				+ subProductNo + "'";

		return this.getHibernateTemplate().find(queryString);
	}
	
	/**
	 * 查询组合商品下所有的子商品及数量
	 * @author fx 2015-08-11
	 * @param productNo
	 * @return list
	 */
	public List getCombinationProduct(String productNo){
		if(!StringUtil.isEmpty(productNo)){
			String sql = " select product_no,sub_product_no,qty from v_pm_combination where product_no='"+productNo+"'";
			List list = this.getJdbcTemplate().queryForList(sql);
			if(null!=list){
				if(list.size()>0){
					return list;
				}
			}
			return null;
		}
		return null;
	}
	
	//add by lihao 
	public List getJpmProductCombinationsByCrmSql(CommonRecord crm, Pager pager) {
		
		String sql = " select jpc.JPC_ID as jpcid,jpc.PRODUCT_NO as productno,jpc.sub_product_no as subproductno,"
          +"jp.product_name as subproductname,jpc.qty as qty "
          +"FROM jpm_product_combination jpc, jpm_product jp "
          +"WHERE jpc.sub_product_no = jp.product_no";
		
		String productNo = crm.getString("productNo", "");
		if (StringUtils.isNotEmpty(productNo)) {
			sql += " and jpc.PRODUCT_NO='" + productNo + "'";
		}
		String subProductNo = crm.getString("subProductNo", "");
		if (StringUtils.isNotEmpty(subProductNo)) {
			sql += " and jpc.sub_product_no='" + subProductNo
					+ "'";
		}
		// 
		if (!pager.getLimit().getSort().isSorted()) {
			// sort
			sql += " order by jpc.jpc_id desc";
		} else {
			sql += " ORDER BY " + pager.getLimit().getSort().getProperty()
					+ " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsBySQL(sql, pager);
	}

}
