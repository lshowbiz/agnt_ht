package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.dao.PdWarehouseStockDao;
import com.joymain.jecs.pd.model.PdWarehouseStock;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;

public class PdWarehouseStockDaoHibernate extends BaseDaoHibernate implements
		PdWarehouseStockDao {

	public List getPdWarehouseStocksByWarehouseNo(String warehouseNo) {
		// TODO Auto-generated method stub
		String hql = "from PdWarehouseStock pdWarehouseStock where 1=1";
		hql += " and pdWarehouseStock.pdWarehouse.warehouseNo = '"
				+ warehouseNo + "' ";
		return this.getHibernateTemplate().find(hql);
	}

	public List getSumOnWay(CommonRecord crm) {
		// TODO Auto-generated method stub
		String sql = "select pfd.product_no product_no,sum(nvl(pfd.qty,0)) qty "
				+ "from pd_flit_warehouse pf,pd_flit_warehouse_detail pfd "
				+ "where pfd.fw_no=pf.fw_no and pf.order_flag=2";
		String companyCode = crm.getString("companyCode", "");
		if (StringUtils.isNotEmpty(companyCode)) {
			sql += " and pf.IN_COMPANY_CODE='" + companyCode + "' ";
		}
		String productNo = crm.getString("productNo", "");
		if (StringUtils.isNotEmpty(productNo)) {
			sql += " and pfd.product_No = '" + productNo + "' ";
		}
		String warehouseNo = crm.getString("warehouseNo", "");
		if (StringUtils.isNotEmpty(warehouseNo)) {
			sql += " and pf.in_warehouse_no = '" + warehouseNo + "' ";
		}
		sql += " group by pfd.product_no";
		return this.getJdbcTemplate().queryForList(sql);
	}

	public List getSumUnSendInfo(CommonRecord crm) {
		//按物流部要求 只包含审核数
		String sql = "select pd.product_No,nvl(sum(pd.qty),0)qty   from pd_send_info p ,pd_send_info_detail pd "
				+ " where p.si_no = pd.si_no   and p.order_flag=1 ";

		String companyCode = crm.getString("companyCode", "");
		if (StringUtils.isNotEmpty(companyCode)) {
			sql += " and p.company_Code='" + companyCode + "' ";
		}
		String productNo = crm.getString("productNo", "");
		if (StringUtils.isNotEmpty(productNo)) {
			sql += " and pd.product_No = '" + productNo + "' ";
		}
		String warehouseNo = crm.getString("warehouseNo", "");
		if (StringUtils.isNotEmpty(warehouseNo)) {
			sql += " and p.warehouse_No = '" + warehouseNo + "' ";
		}
		sql += " group by pd.product_No";
		return this.getJdbcTemplate().queryForList(sql);
		/*
		 * String companyCode = crm.getString("companyCode", "");
		 * if(StringUtils.isNotEmpty(companyCode)){ String sql =
		 * "select nvl(sum(pd.qty),0)  from pd_send_info p ,pd_send_info_detail pd "
		 * + " where p.si_no = pd.si_no  and p.send_warehouse_no in "+
		 * " (select t.warehouse_no from pd_warehouse t where t.company_Code='"+
		 * companyCode +"') " +
		 * " and pd.product_no='"+productNo+"' and p.order_flag<2"; return
		 * this.getJdbcTemplate().queryForLong(sql); }else{ return new Long(0);
		 * }
		 */
	}

	
	public List getPdWarehouseStocksByProductNo(CommonRecord crm,
			String groupType) {
		// TODO Auto-generated method stub
		String hql = "select pws.product_no,sum(nvl(pws.normal_qty, 0)) normal_qty, "
				+ "sum(nvl(pws.damage_qty, 0)) damage_qty,"
				+ "sum(nvl(pws.unknown_qty, 0)) unknown_qty from pd_warehouse_stock pws,jpm_Product_Sale_new ps " +
						"where pws.product_no=ps.product_no and pws.company_Code=ps.company_Code ";
		String companyCode = crm.getString("companyCode", "");
		if (StringUtils.isNotEmpty(companyCode)) {
			hql += " and pws.company_Code='" + companyCode + "' ";
		}
		String productNo = crm.getString("productNo", "");
		if (StringUtils.isNotEmpty(productNo)) {
			hql += " and pws.product_No = '" + productNo + "' ";
		}
		String warehouseNo = crm.getString("warehouseNo", "");
		if (StringUtils.isNotEmpty(warehouseNo)) {
			hql += " and pws.warehouse_No = '" + warehouseNo + "' ";
		}
		String statusNo = crm.getString("status", "");
		if (StringUtils.isNotEmpty(statusNo)) {
			hql += " and ps.status in( " + statusNo + ")";
		}
		//Modify By WuCF 20150708仓库权限
		String defaultWarehouse = crm.getString("defaultWarehouse", "");
		if(StringUtils.isNotEmpty(defaultWarehouse)){
    		hql += " and pws.warehouse_No in("+defaultWarehouse+")";
    	}
		hql += " group by pws.product_No order by pws.product_No";//没有按仓库分组查询:,pws.warehouse_no
		return this.getJdbcTemplate().queryForList(hql);
	}

	public List getOnWay(CommonRecord crm) {
		// TODO Auto-generated method stub
		String sql = "select pf.in_warehouse_no warehouse_no,pfd.product_no product_no,sum(nvl(pfd.qty,0)) qty "
				+ "from pd_flit_warehouse pf,pd_flit_warehouse_detail pfd "
				+ "where pfd.fw_no=pf.fw_no and pf.order_flag=2 ";
		sql += "group by pf.in_warehouse_no ,pfd.product_no";
		return this.getJdbcTemplate().queryForList(sql);
	}

	public List getUnSendInfo(CommonRecord crm) {
		// TODO Auto-generated method stub
		String sql = "select psi.warehouse_no  warehouse_no, psd.product_no product_no,sum(nvl(psd.qty,0)) qty "
			+ "from pd_send_info psi ,pd_send_info_detail psd where psi.si_no=psd.si_no and psi.order_flag = 1 ";
					//Modify By WuCF 20131113 修改条件为只有审核状态的
		sql += " group by psi.warehouse_no, psd.product_no";
		return this.getJdbcTemplate().queryForList(sql);
	}

	/**
	 * Add By WuCF 20140508
	 * 出库单已经审核状态
	 * @param crm
	 * @return
	 */
	public List getOutWarehouses(CommonRecord crm){
		// TODO Auto-generated method stub
		String sql = "select po.warehouse_no warehouse_no,pod.product_no product_no,sum(nvl(pod.qty, 0)) qty "
			+ " from PD_OUT_WAREHOUSE po, PD_OUT_WAREHOUSE_detail pod where po.ow_no = pod.ow_no and po.order_flag = 1 ";
					//Modify By WuCF 20131113 修改条件为只有审核状态的
		sql += " group by po.warehouse_no, pod.product_no ";
		return this.getJdbcTemplate().queryForList(sql);
	}
	
	/**
	 * Add By WuCF 20140508
	 * 调拨已经审核状态数量
	 * @param crm
	 * @return
	 */
	public List getPdFlitWarehouse(CommonRecord crm){
		// TODO Auto-generated method stub
		String sql = "select pf.out_warehouse_no warehouse_no,pfd.product_no product_no,sum(nvl(pfd.qty,0)) qty "
			+ "from pd_flit_warehouse pf,pd_flit_warehouse_detail pfd "
			+ "where pfd.fw_no=pf.fw_no and pf.order_flag=1 ";//已审核 
		sql += "group by pf.out_warehouse_no ,pfd.product_no";
	return this.getJdbcTemplate().queryForList(sql);
	}
	
	public Integer getUnSendInfo(String warehouseNo, String productNo) {
		// TODO Auto-generated method stub
		String sql = "select nvl(sum(pd.qty),0)  from pd_send_info p ,pd_send_info_detail pd "
				+ " where p.si_no = pd.si_no  and p.warehouse_no = '"
				+ warehouseNo
				+ "'"
				+ " and pd.product_no='"
				+ productNo
				+ "' and p.order_flag<2";
		return this.getJdbcTemplate().queryForInt(sql);
	}

	public PdWarehouseStock getPdWarehouseStockByProductNo(String warehouseNo,
			String productNo) {
		// TODO Auto-generated method stub
		String sql = "from PdWarehouseStock pdWarehouseStock where "
				+ "pdWarehouseStock.pdWarehouse.warehouseNo ='" + warehouseNo
				+ "' and pdWarehouseStock.productNo='" + productNo + "'";
		Object o = this.getObjectByHqlQuery(sql);
		if (o != null) {
			return (PdWarehouseStock) o;
		} else {
			return null;
		}
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdWarehouseStockDao#getPdWarehouseStocks(com.joymain.jecs.pd.model.PdWarehouseStock)
	 */
	public List getPdWarehouseStocks(final PdWarehouseStock pdWarehouseStock) {
		return getHibernateTemplate().find("from PdWarehouseStock");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (pdWarehouseStock == null) {
		 * return getHibernateTemplate().find("from PdWarehouseStock"); } else {
		 * // filter on properties set in the pdWarehouseStock HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex
		 * =Example.create(pdWarehouseStock).ignoreCase().enableLike(MatchMode.
		 * ANYWHERE); return
		 * session.createCriteria(PdWarehouseStock.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdWarehouseStockDao#getPdWarehouseStock(Long
	 *      uniNo)
	 */
	public PdWarehouseStock getPdWarehouseStock(final Long uniNo) {
		PdWarehouseStock pdWarehouseStock = (PdWarehouseStock) getHibernateTemplate()
				.get(PdWarehouseStock.class, uniNo);
		if (pdWarehouseStock == null) {
			log.warn("uh oh, pdWarehouseStock with uniNo '" + uniNo
					+ "' not found...");
			throw new ObjectRetrievalFailureException(PdWarehouseStock.class,
					uniNo);
		}

		return pdWarehouseStock;
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdWarehouseStockDao#savePdWarehouseStock(PdWarehouseStock
	 *      pdWarehouseStock)
	 */
	public void savePdWarehouseStock(final PdWarehouseStock pdWarehouseStock) {
		if ("1".equals(pdWarehouseStock.getPdWarehouse().getLockFlag())) {
			throw new AppException("erro.pd.warehouse.locked");
		}
		getHibernateTemplate().saveOrUpdate(pdWarehouseStock);
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdWarehouseStockDao#removePdWarehouseStock(Long
	 *      uniNo)
	 */
	public void removePdWarehouseStock(final Long uniNo) {
		getHibernateTemplate().delete(getPdWarehouseStock(uniNo));
	}

	// added for getPdWarehouseStocksByCrm
	public List getPdWarehouseStocksByCrm(CommonRecord crm, Pager pager) { 
//		String hql = "from PdWarehouseStock pdWarehouseStock where 1=1 ";//Modify By WuCF 20130520 添加隐藏查询条件  
//		String hql = "select pdWarehouseStock from PdWarehouseStock as pdWarehouseStock,JpmProductSale as jpmProductSale where jpmProductSale.isHidden='0' and pdWarehouseStock.productNo=jpmProductSale.jpmProduct.productNo ";
//		String hql = "select pdWarehouseStock from PdWarehouseStock as pdWarehouseStock right join JpmProductSale as jpmProductSale on pdWarehouseStock.productNo=jpmProductSale.jpmProduct.productNo where 1=1 ";
		String hql = "from PdWarehouseStock pdWarehouseStock where 1=1 "; 
		String companyCode = crm.getString("companyCode", "");
		
		hql += " and pdWarehouseStock.productNo in(select jpmProductSaleNew.jpmProduct.productNo from JpmProductSaleNew as jpmProductSaleNew where 1=1 ";
		if (StringUtils.isNotEmpty(companyCode)) { 
			hql += " and jpmProductSaleNew.companyCode='" + companyCode + "' ";
		}
		hql += " and (jpmProductSaleNew.isHidden='0' or jpmProductSaleNew.isHidden is null )) ";
		/**     
		 * ---example--- String userCode = crm.getString("userCode", "");
		 * if(StringUtils.isNotEmpty(userCode)){ hql += "???????????"; }
		 ***/ 
		//  
		
		if (StringUtils.isNotEmpty(companyCode)) {
			hql += " and pdWarehouseStock.companyCode='" + companyCode + "' ";
		}
		String productNo = crm.getString("productNo", "");
		if (StringUtils.isNotEmpty(productNo)) {
			hql += " and pdWarehouseStock.productNo = '" + productNo + "' ";
		}
		String warehouseNo = crm.getString("warehouseNo", "");
		if (StringUtils.isNotEmpty(warehouseNo)) {
			//Modify By WuCF 20140424仓库变成多选
			hql += " and pdWarehouseStock.pdWarehouse.warehouseNo in('" + warehouseNo.replace(",", "','") + "') ";
		}

		/*
		 * String statusNo = crm.getString("status","");
		 * if(StringUtils.isNotEmpty(statusNo)){
		 * 
		 * hql +=
		 * " and pdWarehouseStock.productNo in (select ps.jpmProduct.productNo from JpmProductSale ps where ps.status ='"
		 * +statusNo+"' and ps.companyCode='"+companyCode+"')" ; }
		 */

		String defaultWarehouse = crm.getString("defaultWarehouse", "");
		if (StringUtils.isNotEmpty(defaultWarehouse)) {
			hql += " and pdWarehouseStock.pdWarehouse.warehouseNo in("
					+ defaultWarehouse + ")";
		}
log.info("pdwarehousestock==============hql:"+hql);
		if (pager == null) {
			return this.getHibernateTemplate().find(hql);
		}
		if (!pager.getLimit().getSort().isSorted()) {
			// sort
			hql += " order by pdWarehouseStock.pdWarehouse.warehouseNo desc,pdWarehouseStock.productNo";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty()
					+ " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}

	public Long getStock(String companyCode, String warehouseNo,
			String productNo) {
		// TODO Auto-generated method stub
		String sql = "select nvl(sum(pws.normal_qty),0)qty from pd_warehouse_stock pws where pws.company_code='"
				+ companyCode + "' and pws.product_no='" + productNo + "' ";
		if (StringUtils.isNotBlank(warehouseNo)) {
			sql += " and pws.warehouse_No='" + warehouseNo + "'";
		}
		return this.getJdbcTemplate().queryForLong(sql);
	}

	
	/**
	 * param:siNo 发货单号
	 * return: 返回修改状态  true:成功  false:失败
	 */
	public boolean updateHurryFlag(String siNo) {
		boolean returnS = false;
		
		String sql = " update pd_send_info set hurry_flag='1' where si_no='"+siNo+"' ";
		int i = this.getJdbcTemplate().update(sql);
		if(i>=0){
			returnS = true;
		}
		return returnS;
	}
	
}
