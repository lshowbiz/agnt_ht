package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdOutWarehouse;
import com.joymain.jecs.pd.dao.PdOutWarehouseDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class PdOutWarehouseDaoHibernate extends BaseDaoHibernate implements
		PdOutWarehouseDao {

	/**
	 * @see com.joymain.jecs.pd.dao.PdOutWarehouseDao#getPdOutWarehouses(com.joymain.jecs.pd.model.PdOutWarehouse)
	 */
	public List getPdOutWarehouses(final PdOutWarehouse pdOutWarehouse) {
		return getHibernateTemplate().find("from PdOutWarehouse");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (pdOutWarehouse == null) {
		 * return getHibernateTemplate().find("from PdOutWarehouse"); } else {
		 * // filter on properties set in the pdOutWarehouse HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex
		 * =
		 * Example.create(pdOutWarehouse).ignoreCase().enableLike(MatchMode.ANYWHERE
		 * ); return
		 * session.createCriteria(PdOutWarehouse.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdOutWarehouseDao#getPdOutWarehouse(String
	 *      owNo)
	 */
	public PdOutWarehouse getPdOutWarehouse(final String owNo) {
		PdOutWarehouse pdOutWarehouse = (PdOutWarehouse) getHibernateTemplate()
				.get(PdOutWarehouse.class, owNo);
		if (pdOutWarehouse == null) {
			log.warn("uh oh, pdOutWarehouse with owNo '" + owNo
					+ "' not found...");
			throw new ObjectRetrievalFailureException(PdOutWarehouse.class,
					owNo);
		}

		return pdOutWarehouse;
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdOutWarehouseDao#savePdOutWarehouse(PdOutWarehouse
	 *      pdOutWarehouse)
	 */
	public void savePdOutWarehouse(final PdOutWarehouse pdOutWarehouse) {
		getHibernateTemplate().saveOrUpdate(pdOutWarehouse);
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdOutWarehouseDao#removePdOutWarehouse(String
	 *      owNo)
	 */
	public void removePdOutWarehouse(final String owNo) {
		getHibernateTemplate().delete(getPdOutWarehouse(owNo));
	}

	// added for getPdOutWarehousesByCrm
	public List getPdOutWarehousesByCrm(CommonRecord crm, Pager pager) {
		String hql = "from PdOutWarehouse pdOutWarehouse where 1=1";
		String defaultWarehouse = crm.getString("defaultWarehouse", "");
		if (StringUtils.isNotEmpty(defaultWarehouse)) {
			hql += " and pdOutWarehouse.warehouseNo in(" + defaultWarehouse
					+ ")";
		}
		String orderFlagDefault = crm.getString("orderFlagDefault", "");
		if (StringUtils.isNotEmpty(orderFlagDefault)) {
			hql += " and pdOutWarehouse.orderFlag in(" + orderFlagDefault + ")";
		}

		//Modify By WuCF 20131114 仓库权限管理修改，查询时过滤 
//		String userCodeT = crm.getString("userCodeT", ""); 
//		if (StringUtils.isNotEmpty(userCodeT) && !"root".equals(userCodeT)) {
//			hql += " and exists (select 1 from PdWarehouseUser pdWarehouseUser where pdOutWarehouse.warehouseNo=pdWarehouseUser.warehouseNo and pdWarehouseUser.userCode='"+userCodeT+"') ";
//		}
		
		//Modify By WUCF 统计添加上商品编码20150918
		String productNo = crm.getString("productNo", "");
		if (StringUtils.isNotEmpty(productNo)) {
			hql += " and exists ( select 1 from PdOutWarehouseDetail psd where pdOutWarehouse.owNo=psd.owNo and psd.productNo in('"+productNo.trim().replace(",", "','")+"') )";
		}
		
		//Modify By WuCF 20150617 加上创建者、审核者、确认者查询条件判断查询
    	String createUsrNo = crm.getString("createusrcode", "");
		if (StringUtils.isNotEmpty(createUsrNo)) {
			hql += " and pdOutWarehouse.createUsrCode ='" + createUsrNo.trim() + "' ";
		}
		
		String orderFlag = crm.getString("orderFlag", "");
		if (StringUtils.isNotEmpty(orderFlag)) {
			hql += " and pdOutWarehouse.orderFlag in(" + orderFlag + ")";
		}
		
		String owNo = crm.getString("owNo", "");
		String hasCheckUsrCodeBlank = crm.getString("hasCheckUsrCodeBlank", "0");

		/*if ("1".equals(hasCheckUsrCodeBlank)) {// 新增提交功能
			hql += " and pdOutWarehouse.checkStatus in('1','2')";
		}*/
		if (StringUtils.isNotEmpty(owNo)) {
			hql += " and pdOutWarehouse.owNo='" + owNo + "'";
		}
		// 审核者createUsrCode
		String checkUsrCode = crm.getString("checkUsrCode", "");
		if (StringUtils.isNotEmpty(checkUsrCode)) {
			if (hasCheckUsrCodeBlank.equals("1")) {

				hql += " and (pdOutWarehouse.checkUsrCode ='" + checkUsrCode
						+ "' or pdOutWarehouse.checkUsrCode is null)";
			} else {
				hql += " and pdOutWarehouse.checkUsrCode='" + checkUsrCode + "'";
			}

		}
		// 审核者createUsrCode
		String okUsrCode = crm.getString("okUsrCode", "");
		if (StringUtils.isNotEmpty(okUsrCode)) {
			if (hasCheckUsrCodeBlank.equals("1")) {

				hql += " and (pdOutWarehouse.okUsrCode ='" + okUsrCode
						+ "' or pdOutWarehouse.okUsrCode is null)";
			} else {
				hql += " and pdOutWarehouse.okUsrCode='" + okUsrCode + "'";
			}

		}
		//
		String warehouseNo = crm.getString("warehouseNo", "");
		if (StringUtils.isNotEmpty(warehouseNo)) {

			hql += " and pdOutWarehouse.warehouseNo='" + warehouseNo + "'";

		}

		// 审核者createUsrCode
		String createUsrCode = crm.getString("createUsrCode", "");
		if (StringUtils.isNotEmpty(createUsrCode)) {

			hql += " and pdOutWarehouse.createUsrCode='" + createUsrCode + "'";

		}
		/*//
		String checkStatus = crm.getString("checkStatus", "");
		if (StringUtils.isNotEmpty(checkStatus)) {
			hql += " and pdOutWarehouse.checkStatus ='" + checkStatus + "'";
		}
		String okStatus = crm.getString("okStatus", "");
		if (StringUtils.isNotEmpty(okStatus)) {
			hql += " and pdOutWarehouse.okStatus='" + okStatus + "'";
		}*/

		String owtNo = crm.getString("owtNo", "");
		if (StringUtils.isNotEmpty(owtNo)) {
			hql += " and pdOutWarehouse.owtNo='" + owtNo + "'";
		}

		String companyCode = crm.getString("companyCode", "");
		if (StringUtils.isNotEmpty(companyCode)) {
			hql += " and pdOutWarehouse.companyCode='" + companyCode + "'";
		}

		// 创建时间
		String createTimeStart = crm.getString("createTimeStart", "");
		String createTimeEnd = crm.getString("createTimeEnd", "");
		if (StringUtils.isNotEmpty(createTimeStart)) {
			hql += " and pdOutWarehouse.createTime >=to_date('"
					+ createTimeStart + " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		if (StringUtils.isNotEmpty(createTimeEnd)) {
			hql += " and pdOutWarehouse.createTime <=to_date('" + createTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}
		// 确认时间
		String okTimeBegain = crm.getString("okTimeStart", "");
		if (StringUtils.isNotEmpty(okTimeBegain)) {
			hql += " and pdOutWarehouse.okTime >=to_date('" + okTimeBegain
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		String okTimeEnd = crm.getString("okTimeEnd", "");
		if (StringUtils.isNotEmpty(okTimeEnd)) {
			hql += " and pdOutWarehouse.okTime <=to_date('" + okTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}
		// 
		if (!pager.getLimit().getSort().isSorted()) {
			// sort
			hql += " order by owNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty()
					+ " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}

	public List getSumGroupByOwt(CommonRecord crm) {
		// TODO Auto-generated method stub
		String companyCode = crm.getString("companyCode", "");
		String warehouseNo = crm.getString("warehouseNo", "");
		String startTime = crm.getString("startTime", "");
		String endTime = crm.getString("endTime", "");
		String t1="select pd.product_no,pm.PRODUCT_NAME, owt_no,                                    "+
		"decode(owt_no,'1',qty,0) count1,                                   "+
		"decode(owt_no,'2',qty,0) count2,                                   "+
		"decode(owt_no,'3',qty,0) count3,                                   "+
		"decode(owt_no,'4',qty,0) count4,                                   "+
		"decode(owt_no,'5',qty,0) count5,                                   "+
		"decode(owt_no,'6',qty,0) count6                                    "+
		"from pd_out_warehouse p,pd_out_warehouse_detail pd,jpm_product_sale_new pm               "+
		"where p.ow_no=pd.ow_no and p.company_code=pm.company_code and pd.product_no=pm.product_no ";
		if(StringUtils.isNotEmpty(companyCode)){
			t1+=" and p.company_code='"+companyCode+"' ";
		}
		if(StringUtils.isNotEmpty(warehouseNo)){
			t1+=" and p.warehouse_no='"+warehouseNo+"' ";
		}
		if(StringUtils.isNotEmpty(startTime)){
			t1 += " and p.ok_Time >=to_date('" + startTime
			+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		if(StringUtils.isNotEmpty(endTime)){
			t1 += " and p.ok_Time <=to_date('" + endTime
			+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}
		String sql ="select t.product_no,t.PRODUCT_NAME ,sum(count1) s1,sum(count2) s2,sum(count3) s3,"+ 
		"sum(count4) s4,sum(count5) s5,sum(count6) s6                     "+
		"from( "+t1+" ) t   "+
		"group by t.product_no,t.PRODUCT_NAME  order by t.product_no                     ";
		
		return this.getJdbcTemplate().queryForList(sql);
	}

	public List getOutDetails(CommonRecord crm) {
		// TODO Auto-generated method stub
		//预编译参数
		//Modify By WuCF 20140728
		StringBuffer sqlBuffer = new StringBuffer();
		StringBuffer paramsBuf = new StringBuffer("");
		
		sqlBuffer.append(" select ow.OW_NO,ow.OWT_NO,ow.WAREHOUSE_NO,ow.ORDER_FLAG,ow.REMARK,ow.CREATE_TIME,ow.OK_TIME,ow.RECIPIENT_ADDR,od.product_no,od.qty,ps.product_no,ps.product_name ");
		sqlBuffer.append(" from pd_out_warehouse ow,pd_out_warehouse_detail od,jpm_product_sale_new ps ");
		sqlBuffer.append(" where ow.ow_no=od.ow_no and od.product_no=ps.product_no and ow.company_code=ps.company_code ");
		String startTime = crm.getString("startTime", "");
		String endTime = crm.getString("endTime", "");
		if(StringUtils.isNotEmpty(startTime)){
			sqlBuffer.append(" and ow.create_Time >=to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
			paramsBuf.append(","+startTime+" 00:00:00");
		}
		if(StringUtils.isNotEmpty(endTime)){
			sqlBuffer.append(" and ow.create_Time <=to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
			paramsBuf.append(","+endTime+" 23:59:59");
		}
		
		String okStartTime = crm.getString("okStartTime", "");
		String okEndTime = crm.getString("okEndTime", "");
		if(StringUtils.isNotEmpty(okStartTime)){
			sqlBuffer.append(" and ow.ok_Time >=to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
			paramsBuf.append(","+okStartTime+" 00:00:00");
		}
		if(StringUtils.isNotEmpty(okEndTime)){
			sqlBuffer.append(" and ow.ok_Time <=to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
			paramsBuf.append(","+okEndTime+" 23:59:59");
		}
		sqlBuffer.append(" order by ow.ow_no ");
		
		//返回时调用分页的查询的方法
		Object[] parameters = null;
		if(paramsBuf.toString().length()>=1){
	    	parameters = paramsBuf.toString().substring(1).split(",");
		} 
		List list = this.getJdbcTemplateReport().queryForList(sqlBuffer.toString(),parameters);
		return list;
	}
	
	
}
