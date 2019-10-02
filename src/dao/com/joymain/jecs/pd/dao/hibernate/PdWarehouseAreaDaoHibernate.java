package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdWarehouseArea;
import com.joymain.jecs.pd.dao.PdWarehouseAreaDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class PdWarehouseAreaDaoHibernate extends BaseDaoHibernate implements
		PdWarehouseAreaDao {

	public List getPdWarehouses(String companyCode, String province, String shNo) {
		// TODO Auto-generated method stub
		String queryString = "from PdWarehouse pdWarehouse where pdWarehouse.companyCode='"
				+ companyCode
				+ "' "
				+ "and pdWarehouse.stateCode='"
				+ province
				+ "' and pdWarehouse.shNo='" + shNo + "'";

		return this.getHibernateTemplate().find(queryString);
	}

	public List getPdWarehouseNo(String companyCode, String province,
			String shNo) {
		String sql = "select pa.warehouse_No from pd_Warehouse p,Pd_Warehouse_Area pa " +
				"where p.warehouse_No = pa.warehouse_No and  p.sh_no='"+shNo+"' and p.company_Code='"+companyCode+"'" +
						" and pa.area_Code = '"+province+"'";
		log.info("getPdWarehouseNo="+sql);
		return this.getJdbcTemplate().queryForList(sql);
	}

	public List getPdWarehouses(String companyCode, String areaCode) {
		// TODO Auto-generated method stub
		String queryString = "from PdWarehouse pdWarehouse where pdWarehouse.companyCode='"
				+ companyCode
				+ "' "
				+ "and pdWarehouse.stateCode='"
				+ areaCode
				+ "'";

		return this.getHibernateTemplate().find(queryString);

	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdWarehouseAreaDao#getPdWarehouseAreas(com.joymain.jecs.pd.model.PdWarehouseArea)
	 */
	public List getPdWarehouseAreas(final PdWarehouseArea pdWarehouseArea) {
		return getHibernateTemplate().find("from PdWarehouseArea");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (pdWarehouseArea == null) {
		 * return getHibernateTemplate().find("from PdWarehouseArea"); } else {
		 * // filter on properties set in the pdWarehouseArea HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex
		 * =Example.create(pdWarehouseArea).ignoreCase().enableLike(MatchMode.
		 * ANYWHERE); return
		 * session.createCriteria(PdWarehouseArea.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdWarehouseAreaDao#getPdWarehouseArea(Long
	 *      waId)
	 */
	public PdWarehouseArea getPdWarehouseArea(final Long waId) {
		PdWarehouseArea pdWarehouseArea = (PdWarehouseArea) getHibernateTemplate()
				.get(PdWarehouseArea.class, waId);
		if (pdWarehouseArea == null) {
			log.warn("uh oh, pdWarehouseArea with waId '" + waId
					+ "' not found...");
			throw new ObjectRetrievalFailureException(PdWarehouseArea.class,
					waId);
		}

		return pdWarehouseArea;
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdWarehouseAreaDao#savePdWarehouseArea(PdWarehouseArea
	 *      pdWarehouseArea)
	 */
	public void savePdWarehouseArea(final PdWarehouseArea pdWarehouseArea) {
		getHibernateTemplate().saveOrUpdate(pdWarehouseArea);
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdWarehouseAreaDao#removePdWarehouseArea(Long
	 *      waId)
	 */
	public void removePdWarehouseArea(final Long waId) {
		getHibernateTemplate().delete(getPdWarehouseArea(waId));
	}

	// added for getPdWarehouseAreasByCrm
	public List getPdWarehouseAreasByCrm(CommonRecord crm, Pager pager) {
		String hql = "from PdWarehouseArea pdWarehouseArea where 1=1";
		/**
		 * ---example--- String userCode = crm.getString("userCode", "");
		 * if(StringUtils.isNotEmpty(userCode)){ hql += "???????????"; }
		 ***/
		String warehouseNo = crm.getString("warehouseNo", "");
		if (StringUtils.isNotEmpty(warehouseNo)) {
			hql += " and pdWarehouseArea.warehouseNo='" + warehouseNo + "'";
		}
		String areaCode = crm.getString("areaCode", "");
		if (StringUtils.isNotEmpty(areaCode)) {
			hql += " and pdWarehouseArea.areaCode='" + areaCode + "'";
		}
		// 
		if (!pager.getLimit().getSort().isSorted()) {
			// sort
			hql += " order by waId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty()
					+ " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}
}
