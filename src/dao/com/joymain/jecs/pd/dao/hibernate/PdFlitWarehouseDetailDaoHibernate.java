package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdFlitWarehouseDetail;
import com.joymain.jecs.pd.dao.PdFlitWarehouseDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class PdFlitWarehouseDetailDaoHibernate extends BaseDaoHibernate
		implements PdFlitWarehouseDetailDao {

	public List getOnWayStaticsByCrm(CommonRecord crm) {
		// TODO Auto-generated method stub
		String startTime = crm.getString("startTime", "");
		String endTime = crm.getString("endTime", "");
		String companyCode = crm.getString("companyCode", "");
		String warehouseNo = crm.getString("warehouseNo", "");
		String sql = "select fwd.product_no,sum(nvl(fwd.qty,0))qty "
				+ "from pd_flit_warehouse fw,pd_flit_warehouse_detail fwd"
				+ " where fw.fw_no=fwd.fw_no and fw.ORDER_FLAG>1 ";
		sql += " and fw.in_company_code='" + companyCode + "' ";
		sql += " and fw.OK_TIME >=to_date('" + startTime
				+ " 00:00:00','yyyy-mm-dd hh24:mi:ss') ";
		sql += " and fw.OK_TIME <to_date('" + endTime
				+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')+1 ";
		sql += " and (fw.TO_TIME >= to_date('" + endTime
				+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')+1 or fw.TO_TIME is null)";

		if (StringUtils.isNotEmpty(warehouseNo)) {
//			sql += " and fw.in_warehouse_no ='" + warehouseNo + "' ";//Modify By WuCF 20130503
			sql += " and fw.in_warehouse_no in('" + warehouseNo.replace(",", "','") + "') ";
		}
		sql += " group by fwd.product_no";
		log.info("getOnWayStaticsByCrm="+sql);
		return this.getJdbcTemplate().queryForList(sql);
	}

	public List getFlitWarehouseStaticsByCrm(CommonRecord crm, String flag) {
		// TODO Auto-generated method stub
		String startTime = crm.getString("startTime", "");
		String endTime = crm.getString("endTime", "");
		String companyCode = crm.getString("companyCode", "");
		String warehouseNo = crm.getString("warehouseNo", "");
		String sql = " select fwd.product_no,sum(nvl(fwd.qty,0)) qty"
				+ "from pd_flit_warehouse fw,pd_flit_warehouse_detail fwd  where 1=1 ";

		if ("in".equals(flag)) {
			sql += " and fw.in_company_code='" + companyCode + "' ";
			sql += " and fw.TO_TIME >=to_date('" + startTime
					+ " 00:00:00','yyyy-mm-dd hh24:mi:ss') ";
			sql += " and fw.TO_TIME <to_date('" + endTime
					+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')+1 ";
			if (StringUtils.isNotEmpty(warehouseNo)) {
//				sql += " and fw.in_warehouse_no ='" + warehouseNo + "' ";//Modify By WuCF 20130503
				sql += " and fw.in_warehouse_no in('" + warehouseNo.replace(",", "','") + "') ";
			}
		} else {
			sql += " and fw.out_company_code='" + companyCode + "' ";
			sql += " and fw.OK_TIME >=to_date('" + startTime
					+ " 00:00:00','yyyy-mm-dd hh24:mi:ss') ";
			sql += " and fw.OK_TIME <to_date('" + endTime
					+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')+1 ";
			if (StringUtils.isNotEmpty(warehouseNo)) {
//				sql += " and fw.out_warehouse_no ='" + warehouseNo + "' ";//Modify By WuCF 20130503
				sql += " and fw.out_warehouse_no in('" + warehouseNo.replace(",", "','") + "') ";
			}
		}
		sql += " group by fwd.product_no";
		log.info("getFlitWarehouseStaticsByCrm="+sql);
		return this.getJdbcTemplate().queryForList(sql);
	}

	public List getPdFlitWarehouseDetails(String productNo, String fwNo) {
		// TODO Auto-generated method stub
		String hql = "from PdFlitWarehouseDetail pf where pf.productNo='"
				+ productNo + "' and pf.fwNo='" + fwNo + "'";
		return getHibernateTemplate().find(hql);
	}

	public List getTotalPdFlitWarehouseDetails(CommonRecord crm) {
		// TODO Auto-generated method stub
		String sql = "select pm.product_no,pm.product_name,sum(pdd.qty) qty from pd_flit_warehouse pd,pd_flit_warehouse_detail pdd,jpm_product_sale_new pm"
				+ " where pd.fw_no=pdd.fw_no and pdd.product_no=pm.product_no and pd.in_company_Code=pm.company_Code ";
		String companyCode = crm.getString("companyCode", "");
		if (StringUtils.isNotEmpty(companyCode)) {
			sql += " and (pd.OUT_COMPANY_Code = '" + companyCode
					+ "' or pd.IN_COMPANY_code = '" + companyCode + "' ) ";
		}

		//Modify By WUCF 统计添加上商品编码20150918
		String productNo = crm.getString("productNo", "");
		if (StringUtils.isNotEmpty(productNo)) {
			sql += " and exists(select 1 from pd_flit_warehouse_detail tt where tt.fw_no=pd.fw_no and tt.product_no in('"+productNo.trim().replace(",", "','")+"') ) ";
		}
		
		String fwNo = crm.getString("fwNo", "");
		if (StringUtils.isNotEmpty(fwNo)) {
			sql += " and pd.fw_No='" + fwNo + "' ";
		}
		
		//Modify By WuCF 20150617 加上创建者、审核者、确认者查询条件判断查询
    	String createUsrNo = crm.getString("createusrcode", ""); 
		if (StringUtils.isNotEmpty(createUsrNo)) {
			sql += " and pd.CREATE_USR_CODE ='" + createUsrNo.trim() + "' ";
		}
		
		String checkUsrNo = crm.getString("checkusrcode", "");
		if (StringUtils.isNotEmpty(checkUsrNo)) {
			sql += " and pd.CHECK_USR_CODE ='" + checkUsrNo.trim() + "' ";
		}
		
		String okUsrNo = crm.getString("okusrcode", "");
		if (StringUtils.isNotEmpty(okUsrNo)) {
			sql += " and pd.OK_USR_CODE ='" + okUsrNo.trim() + "' ";
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

		String inCompanyNo = crm.getString("inCompanyNo", "");
		if (StringUtils.isNotEmpty(inCompanyNo)) {
			sql += " and pd.in_Company_No='" + inCompanyNo + "'";
		}

		String outCompanyNo = crm.getString("outCompanyNo", "");
		if (StringUtils.isNotEmpty(outCompanyNo)) {
			sql += " and pd.out_Company_No='" + outCompanyNo + "'";
		}

		String outWarehouseNo = crm.getString("outWarehouseNo", "");
		if (StringUtils.isNotEmpty(outWarehouseNo)) {
			sql += " and pd.out_Warehouse_No='" + outWarehouseNo + "'";
		}
		String inWarehouseNo = crm.getString("inWarehouseNo", "");
		if (StringUtils.isNotEmpty(inWarehouseNo)) {
			sql += " and pd.in_Warehouse_No='" + inWarehouseNo + "'";
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

		// 到库确认时间
		String toTimeBegain = crm.getString("toTimeStart", "");
		if (StringUtils.isNotEmpty(toTimeBegain)) {
			sql += " and pd.to_Time >=to_date('" + toTimeBegain
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		String toTimeEnd = crm.getString("toTimeEnd", "");
		if (StringUtils.isNotEmpty(toTimeEnd)) {
			sql += " and pd.to_Time <=to_date('" + toTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}

		String orderFlag = crm.getString("orderFlag", "");
		if (StringUtils.isNotEmpty(orderFlag)) {
			sql += " and pd.order_Flag in(" + orderFlag + ")";
		}
		
		String createUsrCode = crm.getString("createUsrCode", "");
		if (StringUtils.isNotEmpty(createUsrCode)) {
			sql += " and pd.CREATE_USR_CODE='" + createUsrCode + "'";
		}
		
		String checkUsrCode = crm.getString("checkUsrCode", "");
		if (StringUtils.isNotEmpty(checkUsrCode)) {
			sql += " and pd.CHECK_USR_CODE='" + checkUsrCode + "'";
		}
		
		String okUsrCode = crm.getString("okUsrCode", "");
		if (StringUtils.isNotEmpty(okUsrCode)) {
			sql += " and pd.OK_USR_CODE='" + okUsrCode + "'";
		}
		
		/*
		 * String okStatus = crm.getString("okStatus", ""); if
		 * (StringUtils.isNotEmpty(okStatus)) { sql += " and pd.ok_Status='" +
		 * okStatus + "'"; }
		 * 
		 * String toStatus = crm.getString("toStatus", ""); if
		 * (StringUtils.isNotEmpty(toStatus)) { sql += " and pd.to_Status='" +
		 * toStatus + "'"; }
		 * 
		 * String checkStatus = crm.getString("checkStatus", ""); if
		 * (StringUtils.isNotEmpty(checkStatus)) { sql +=
		 * " and pd.check_Status='" + checkStatus + "'"; }
		 */
		sql += " group by pm.product_no,pm.product_name having sum(pdd.qty)<>0 order by pm.product_no";
		return this.getJdbcTemplate().queryForList(sql);
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdFlitWarehouseDetailDao#getPdFlitWarehouseDetails(com.joymain.jecs.pd.model.PdFlitWarehouseDetail)
	 */
	public List getPdFlitWarehouseDetails(
			final PdFlitWarehouseDetail pdFlitWarehouseDetail) {
		return getHibernateTemplate().find("from PdFlitWarehouseDetail");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (pdFlitWarehouseDetail ==
		 * null) { return
		 * getHibernateTemplate().find("from PdFlitWarehouseDetail"); } else {
		 * // filter on properties set in the pdFlitWarehouseDetail
		 * HibernateCallback callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex
		 * =
		 * Example.create(pdFlitWarehouseDetail).ignoreCase().enableLike(MatchMode
		 * .ANYWHERE); return
		 * session.createCriteria(PdFlitWarehouseDetail.class).add(ex).list(); }
		 * }; return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdFlitWarehouseDetailDao#getPdFlitWarehouseDetail(Long
	 *      uniNo)
	 */
	public PdFlitWarehouseDetail getPdFlitWarehouseDetail(final Long uniNo) {
		PdFlitWarehouseDetail pdFlitWarehouseDetail = (PdFlitWarehouseDetail) getHibernateTemplate()
				.get(PdFlitWarehouseDetail.class, uniNo);
		if (pdFlitWarehouseDetail == null) {
			log.warn("uh oh, pdFlitWarehouseDetail with uniNo '" + uniNo
					+ "' not found...");
			throw new ObjectRetrievalFailureException(
					PdFlitWarehouseDetail.class, uniNo);
		}

		return pdFlitWarehouseDetail;
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdFlitWarehouseDetailDao#savePdFlitWarehouseDetail(PdFlitWarehouseDetail
	 *      pdFlitWarehouseDetail)
	 */
	public void savePdFlitWarehouseDetail(
			final PdFlitWarehouseDetail pdFlitWarehouseDetail) {
		getHibernateTemplate().saveOrUpdate(pdFlitWarehouseDetail);
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdFlitWarehouseDetailDao#removePdFlitWarehouseDetail(Long
	 *      uniNo)
	 */
	public void removePdFlitWarehouseDetail(final Long uniNo) {
		getHibernateTemplate().delete(getPdFlitWarehouseDetail(uniNo));
	}

	// added for getPdFlitWarehouseDetailsByCrm
	public List getPdFlitWarehouseDetailsByCrm(CommonRecord crm, Pager pager) {
		String hql = "from PdFlitWarehouseDetail pdFlitWarehouseDetail where 1=1";
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
