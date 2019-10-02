package com.joymain.jecs.pd.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.dao.PdFlitWarehouseDao;
import com.joymain.jecs.pd.model.PdFlitWarehouse;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class PdFlitWarehouseDaoHibernate extends BaseDaoHibernate implements
		PdFlitWarehouseDao {

	public Long getOnWayCountByWarehouseNo(String warehouseNo, String productNo) {
		// TODO Auto-generated method stub
		String sql = "select nvl(sum(nvl(pfd.qty,0)),0) qty from pd_flit_warehouse pfw ,pd_flit_warehouse_detail pfd ";
		sql += " where pfw.fw_no=pfd.fw_no and pfw.order_flag =2 ";
		sql += " and pfd.product_no='"+productNo+"'";
		sql += " and pfw.in_warehouse_no='"+ warehouseNo+"'";
		return this.getJdbcTemplate().queryForLong(sql);
	}

	public Long getSumOnWayByWarehouseNo(CommonRecord crm, String productNo) {
		// TODO Auto-generated method stub
		String companyCode = crm.getString("companyCode", "");
		if(StringUtils.isNotEmpty(companyCode)){
			String sql = "select nvl(sum(nvl(pfd.qty,0)),0) qty from pd_flit_warehouse pfw ,pd_flit_warehouse_detail pfd ";
			sql += " where pfw.fw_no=pfd.fw_no and pfw.order_flag =2 ";
			sql += " and pfd.product_no='"+productNo+"'";
			sql += " and pfw.in_warehouse_no in ("+
				"select  t.warehouse_no from pd_warehouse t where t.company_code='"+companyCode+"')";
			return this.getJdbcTemplate().queryForLong(sql);
		}else{
			return 0l;
		}
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdFlitWarehouseDao#getPdFlitWarehouses(com.joymain.jecs.pd.model.PdFlitWarehouse)
	 */
	public List getPdFlitWarehouses(final PdFlitWarehouse pdFlitWarehouse) {
		return getHibernateTemplate().find("from PdFlitWarehouse");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (pdFlitWarehouse == null) {
		 * return getHibernateTemplate().find("from PdFlitWarehouse"); } else {
		 * // filter on properties set in the pdFlitWarehouse HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex
		 * =Example.create(pdFlitWarehouse).ignoreCase().enableLike(MatchMode.
		 * ANYWHERE); return
		 * session.createCriteria(PdFlitWarehouse.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdFlitWarehouseDao#getPdFlitWarehouse(String
	 *      fwNo)
	 */
	public PdFlitWarehouse getPdFlitWarehouse(final String fwNo) {
		PdFlitWarehouse pdFlitWarehouse = (PdFlitWarehouse) getHibernateTemplate()
				.get(PdFlitWarehouse.class, fwNo);
		if (pdFlitWarehouse == null) {
			log.warn("uh oh, pdFlitWarehouse with fwNo '" + fwNo
					+ "' not found...");
			throw new ObjectRetrievalFailureException(PdFlitWarehouse.class,
					fwNo);
		}

		return pdFlitWarehouse;
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdFlitWarehouseDao#savePdFlitWarehouse(PdFlitWarehouse
	 *      pdFlitWarehouse)
	 */
	public void savePdFlitWarehouse(final PdFlitWarehouse pdFlitWarehouse) {
		getHibernateTemplate().saveOrUpdate(pdFlitWarehouse);
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdFlitWarehouseDao#removePdFlitWarehouse(String
	 *      fwNo)
	 */
	public void removePdFlitWarehouse(final String fwNo) {
		getHibernateTemplate().delete(getPdFlitWarehouse(fwNo));
	}

	// added for getPdFlitWarehousesByCrm
	public List getPdFlitWarehousesByCrm(CommonRecord crm, Pager pager) {
		String hql = "from PdFlitWarehouse pdFlitWarehouse where 1=1";
		String inDefaultWarehouse = crm.getString("inDefaultWarehouse", "");
		if (StringUtils.isNotEmpty(inDefaultWarehouse)) {
			hql += " and pdFlitWarehouse.inWarehouse.warehouseNo in("
					+ inDefaultWarehouse + ") ";
		}

		//Modify By WUCF 20150918 统计机上商品编码过滤
		String productNo = crm.getString("productNo", "");
		if (StringUtils.isNotEmpty(productNo)) {
			hql += " and exists ( select 1 from PdFlitWarehouseDetail psd where pdFlitWarehouse.fwNo=psd.fwNo and psd.productNo in('"+productNo.trim().replace(",", "','")+"') )";
		}
		
		//Modify By WuCF 20150617 加上创建者、审核者、确认者查询条件判断查询
    	String createUsrNo = crm.getString("createusrcode", "");
		if (StringUtils.isNotEmpty(createUsrNo)) {
			hql += " and pdFlitWarehouse.createUsrCode ='" + createUsrNo.trim() + "' ";
		}
		
		String checkUsrNo = crm.getString("checkusrcode", "");
		if (StringUtils.isNotEmpty(checkUsrNo)) {
			hql += " and pdFlitWarehouse.checkUsrCode ='" + checkUsrNo.trim() + "' ";
		}
		
		String okUsrNo = crm.getString("okusrcode", "");
		if (StringUtils.isNotEmpty(okUsrNo)) {
			hql += " and pdFlitWarehouse.okUsrCode ='" + okUsrNo.trim() + "' ";
		}

		//Modify By WuCF 20131114 仓库权限管理修改，查询时过滤 
//		String userCodeT = crm.getString("userCodeT", ""); 
//		if (StringUtils.isNotEmpty(userCodeT) && !"root".equals(userCodeT)) {
//			hql += " and exists (select 1 from PdWarehouseUser pdWarehouseUser where (pdFlitWarehouse.outWarehouse.warehouseNo=pdWarehouseUser.warehouseNo or pdFlitWarehouse.inWarehouse.warehouseNo=pdWarehouseUser.warehouseNo) and pdWarehouseUser.userCode='"+userCodeT+"') ";
//		}
		
		String outDefaultWarehouse = crm.getString("outDefaultWarehouse", "");
		if (StringUtils.isNotEmpty(outDefaultWarehouse)) {
			hql += " and pdFlitWarehouse.outWarehouse.warehouseNo in("
					+ outDefaultWarehouse + ") ";
		}

		String orderFlagDefault = crm.getString("orderFlagDefault", "");
		if (StringUtils.isNotEmpty(orderFlagDefault)) {
			hql += " and pdFlitWarehouse.orderFlag in(" + orderFlagDefault + ")";
		}
		
//		log.info("crm.checkStatus=" + crm.getString("checkStatus", ""));
		String fwNo = crm.getString("fwNo", "");
		if (StringUtils.isNotEmpty(fwNo)) {
			hql += " and pdFlitWarehouse.fwNo='" + fwNo + "'";
		}
		String companyCode = crm.getString("companyCode", "");
		//Modify By WuCF 20150615 针对具体的海外仓库数据的查询，不用限制，故去掉
		/*String searchSwitch = crm.getString("searchSwitch", "");
		if (StringUtils.isNotEmpty(companyCode)) {
			if ("in".equals(searchSwitch)) {
				hql += " and pdFlitWarehouse.inCompanyCode='" + companyCode + "'";
			} else if ("out".equals(searchSwitch)) {
				hql += " and pdFlitWarehouse.outCompanyCode='" + companyCode + "'";
			} else {
				hql += " and (pdFlitWarehouse.outCompanyCode='" + companyCode
						+ "' or pdFlitWarehouse.inCompanyCode='" + companyCode
						+ "')";
			}
			// hql += " and pdFlitWarehouse.companyNo='"+companyNo+"'";
		}*/

		String inCompanyCode = crm.getString("inCompanyCode", "");
		if (StringUtils.isNotEmpty(inCompanyCode)) {
			hql += " and pdFlitWarehouse.inCompanyCode='" + inCompanyCode + "'";
		}

		String outCompanyCode = crm.getString("outCompanyCode", "");
		if (StringUtils.isNotEmpty(outCompanyCode)) {
			hql += " and pdFlitWarehouse.outCompanyCode='" + outCompanyCode + "'";
		}

		String outWarehouseNo = crm.getString("outWarehouseNo", "");
		if (StringUtils.isNotEmpty(outWarehouseNo)) {
			hql += " and pdFlitWarehouse.outWarehouse.warehouseNo='" + outWarehouseNo
					+ "'";
		}
		String inWarehouseNo = crm.getString("inWarehouseNo", "");
		if (StringUtils.isNotEmpty(inWarehouseNo)) {
			hql += " and pdFlitWarehouse.inWarehouse.warehouseNo='" + inWarehouseNo + "'";
		}
		/*String checkStatus = crm.getString("checkStatus", "");
		if (StringUtils.isNotEmpty(checkStatus)) {
			hql += " and pdFlitWarehouse.checkStatus='" + checkStatus + "'";
		}*/
		String checkUsrCode = crm.getString("checkUsrCode", "");
		String hasCheckUsrCodeBlank = crm.getString("hasCheckUsrCodeBlank", "0");
		if (StringUtils.isNotEmpty(checkUsrCode)) {
			if (hasCheckUsrCodeBlank.equals("1")) {

				hql += " and (pdFlitWarehouse.checkUsrCode ='" + checkUsrCode
						+ "' or pdFlitWarehouse.checkUsrCode is null)";
			} else {
				hql += " and pdFlitWarehouse.checkUsrCode ='" + checkUsrCode + "'";
			}

		}
		// 确认者
		String okUsrCode = crm.getString("okUsrCode", "");
		if (StringUtils.isNotEmpty(okUsrCode)) {
			if (hasCheckUsrCodeBlank.equals("1")) {

				hql += " and (pdFlitWarehouse.okUsrCode ='" + okUsrCode
						+ "' or pdFlitWarehouse.okUsrCode is null)";
			} else {
				hql += " and pdFlitWarehouse.okUsrCode ='" + okUsrCode + "'";
			}

		}

		/*String okStatus = crm.getString("okStatus", "");
		if (StringUtils.isNotEmpty(okStatus)) {
			hql += " and pdFlitWarehouse.okStatus='" + okStatus + "'";
		}*/
		 /*String okUsrCode = crm.getString("okUsrCode", "");
		 if(StringUtils.isNotEmpty(okUsrCode)){
		 hql += " and pdFlitWarehouse.okUsrCode='"+okUsrCode+"'";
		 }*/
		/*String toStatus = crm.getString("toStatus", "");
		if (StringUtils.isNotEmpty(toStatus)) {
			hql += " and pdFlitWarehouse.toStatus='" + toStatus + "'";
		}*/
		String createUsrCode = crm.getString("createUsrCode", "");
		if (StringUtils.isNotEmpty(createUsrCode)) {
			hql += " and pdFlitWarehouse.createUsrCode='" + createUsrCode + "'";
		}

		// 创建
		String startTime = crm.getString("createTimeStart", "");
		if (StringUtils.isNotEmpty(startTime)) {
			hql += " and pdFlitWarehouse.createTime >=to_date('" + startTime
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		String createTimeEnd = crm.getString("createTimeEnd", "");
		if (StringUtils.isNotEmpty(createTimeEnd)) {
			hql += " and pdFlitWarehouse.createTime <=to_date('"
					+ createTimeEnd + " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}

		String orderFlag = crm.getString("orderFlag","");
    	if(StringUtils.isNotEmpty(orderFlag)){
    		hql +=" and pdFlitWarehouse.orderFlag in("+ orderFlag +")";
    	}
    	
		// 制单时间
		/*String fwDateBegain = crm.getString("fwDateStart", "");
		if (StringUtils.isNotEmpty(fwDateBegain)) {
			hql += " and pdFlitWarehouse.fwDate >=to_date('" + fwDateBegain
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		String fwDateEnd = crm.getString("fwDateEnd", "");
		if (StringUtils.isNotEmpty(fwDateEnd)) {
			hql += " and pdFlitWarehouse.fwDate <=to_date('" + fwDateEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}
*/
		// 初审时间
		String checkTimeBegain = crm.getString("checkTimeStart", "");
		if (StringUtils.isNotEmpty(checkTimeBegain)) {
			hql += " and pdFlitWarehouse.checkTime >=to_date('"
					+ checkTimeBegain + " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		String checkTimeEnd = crm.getString("checkTimeEnd", "");
		if (StringUtils.isNotEmpty(checkTimeEnd)) {
			hql += " and pdFlitWarehouse.checkTime <=to_date('" + checkTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}

		// 确认时间
		String okTimeBegain = crm.getString("okTimeStart", "");
		if (StringUtils.isNotEmpty(okTimeBegain)) {
			hql += " and pdFlitWarehouse.okTime >=to_date('" + okTimeBegain
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		String okTimeEnd = crm.getString("okTimeEnd", "");
		if (StringUtils.isNotEmpty(okTimeEnd)) {
			hql += " and pdFlitWarehouse.okTime <=to_date('" + okTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}

		// 到库时间
		String toTimeBegain = crm.getString("toTimeStart", "");
		if (StringUtils.isNotEmpty(toTimeBegain)) {
			hql += " and pdFlitWarehouse.toTime >=to_date('" + toTimeBegain
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		String toTimeEnd = crm.getString("toTimeEnd", "");
		if (StringUtils.isNotEmpty(toTimeEnd)) {
			hql += " and pdFlitWarehouse.toTime <=to_date('" + toTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}
		// 
		if (!pager.getLimit().getSort().isSorted()) {
			// sort
			hql += " order by fwNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty()
					+ " " + pager.getLimit().getSort().getSortOrder();
		}
		log.info("====getPdFlitWarehousesByCrm:"+hql);
		return this.findObjectsByHqlQuery(hql, pager);
	}

	public List getFlitDetail(CommonRecord crm) {
		// TODO Auto-generated method stub
		//Modify By WuCF 20140728
		StringBuffer sqlBufferCount = new StringBuffer();
		StringBuffer paramsBufCount = new StringBuffer("");
		
		sqlBufferCount.append(" select count(*) from pd_flit_warehouse fw, ");
		sqlBufferCount.append(" pd_flit_warehouse_detail fwd,jpm_product_sale_new ps where fw.fw_no=fwd.fw_no ");
		sqlBufferCount.append(" and fw.in_company_code=ps.company_code and fwd.product_no=ps.product_no ");
		
		String startTimeCount = crm.getString("startTime", "");
		String endTimeCount = crm.getString("endTime", "");
		if(StringUtils.isNotEmpty(startTimeCount)){
			sqlBufferCount.append(" and fw.create_Time >=to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
			paramsBufCount.append(","+startTimeCount+" 00:00:00");
		}
		if(StringUtils.isNotEmpty(endTimeCount)){
			sqlBufferCount.append(" and fw.create_Time <=to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
			paramsBufCount.append(","+endTimeCount+" 23:59:59");
		}
		String okStartTimeCount = crm.getString("okStartTime", "");
		String okEndTimeCount = crm.getString("okEndTime", "");
		if(StringUtils.isNotEmpty(okStartTimeCount)){
			sqlBufferCount.append(" and fw.ok_Time >=to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
			paramsBufCount.append(","+okStartTimeCount+" 00:00:00");
		}
		if(StringUtils.isNotEmpty(okEndTimeCount)){
			sqlBufferCount.append(" and fw.ok_Time <=to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
			paramsBufCount.append(","+okEndTimeCount+" 23:59:59");
		}
		sqlBufferCount.append(" order by fw.fw_no ");

		//返回时调用分页的查询的方法
		Object[] parametersCount = null;
		if(paramsBufCount.toString().length()>=1){
	    	parametersCount = paramsBufCount.toString().substring(1).split(",");
		} 
		List listCount = this.getJdbcTemplateReport().queryForList(sqlBufferCount.toString(),parametersCount);
		
		//Modify By WuCF 20140514  查询记录条数，控制记录条数
		String listNum = crm.getString("listNum");//最大限额数
		
		Integer num = Integer.parseInt(((Map)listCount.get(0)).get("COUNT(*)").toString());
		log.info("===会员订单统计，导出数："+num);
		if(num>Integer.parseInt(listNum)){//如果超过限额，则返回
			List list = new ArrayList<String>();
			sqlBufferCount = new StringBuffer("select '' FW_NO,'' OUT_WAREHOUSE_NO,'' IN_WAREHOUSE_NO,'' ORDER_FLAG,'' CREATE_TIME, ");
			sqlBufferCount.append(" '' OK_TIME,'' REMARK,'' RECIPIENT_ADDR,'' QTY,'' PRODUCT_NO,'导出数据数据量");
			sqlBufferCount.append(num);
			sqlBufferCount.append("超过最大限制数：");
			sqlBufferCount.append(listNum);
			sqlBufferCount.append("导出失败！请缩小查询条件范围！' PRODUCT_NAME ");
			sqlBufferCount.append(" from dual");
			list = this.getJdbcTemplateReport().queryForList(sqlBufferCount.toString());
			return list;
		}
		
		
		//=============================================预编译参数
		//Modify By WuCF 20140728
		//FW_NO=LE012012070200002, OUT_WAREHOUSE_NO=NJCDC, IN_WAREHOUSE_NO=NJRDC4, ORDER_FLAG=3, CREATE_TIME=2012-07-02 08:05:30.0, 
		//OK_TIME=2012-07-02 09:36:33.0, REMARK=null, RECIPIENT_ADDR=null, QTY=1, PRODUCT_NO=N01190100101CN0, PRODUCT_NAME=二级生活馆辅销品+保证金
		StringBuffer sqlBuffer = new StringBuffer();
		StringBuffer paramsBuf = new StringBuffer("");
		
		sqlBuffer.append(" select fw.FW_NO,fw.OUT_WAREHOUSE_NO,fw.IN_WAREHOUSE_NO,fw.ORDER_FLAG,fw.CREATE_TIME,fw.OK_TIME,fw.REMARK,fw.RECIPIENT_ADDR,fwd.qty,fwd.product_no,ps.product_name from pd_flit_warehouse fw, ");
		sqlBuffer.append(" pd_flit_warehouse_detail fwd,jpm_product_sale_new ps where fw.fw_no=fwd.fw_no ");
		sqlBuffer.append(" and fw.in_company_code=ps.company_code and fwd.product_no=ps.product_no ");
		
		String startTime = crm.getString("startTime", "");
		String endTime = crm.getString("endTime", "");
		if(StringUtils.isNotEmpty(startTime)){
			sqlBuffer.append(" and fw.create_Time >=to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
			paramsBuf.append(","+startTime+" 00:00:00");
		}
		if(StringUtils.isNotEmpty(endTime)){
			sqlBuffer.append(" and fw.create_Time <=to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
			paramsBuf.append(","+endTime+" 23:59:59");
		}
		String okStartTime = crm.getString("okStartTime", "");
		String okEndTime = crm.getString("okEndTime", "");
		if(StringUtils.isNotEmpty(okStartTime)){
			sqlBuffer.append(" and fw.ok_Time >=to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
			paramsBuf.append(","+okStartTime+" 00:00:00");
		}
		if(StringUtils.isNotEmpty(okEndTime)){
			sqlBuffer.append(" and fw.ok_Time <=to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
			paramsBuf.append(","+okEndTime+" 23:59:59");
		}
		sqlBuffer.append(" order by fw.fw_no ");

		//返回时调用分页的查询的方法
		Object[] parameters = null;
		if(paramsBuf.toString().length()>=1){
	    	parameters = paramsBuf.toString().substring(1).split(",");
		} 
		List list = this.getJdbcTemplateReport().queryForList(sqlBuffer.toString(),parameters);
		return list;
	}
	
}
