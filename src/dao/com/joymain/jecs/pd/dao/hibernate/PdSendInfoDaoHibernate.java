package com.joymain.jecs.pd.dao.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.dao.PdSendInfoDao;
import com.joymain.jecs.pd.model.PdSendInfo;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

public class PdSendInfoDaoHibernate extends BaseDaoHibernate implements
		PdSendInfoDao {
 
	/**
	 * @see com.joymain.jecs.pd.dao.PdSendInfoDao#getPdSendInfos(com.joymain.jecs.pd.model.PdSendInfo)
	 */
	public List getPdSendInfos(final PdSendInfo pdSendInfo) { 
//		return getHibernateTemplate().find("from PdSendInfo");
		return null;

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (pdSendInfo == null) {
		 * return getHibernateTemplate().find("from PdSendInfo"); } else { //
		 * filter on properties set in the pdSendInfo HibernateCallback callback
		 * = new HibernateCallback() { public Object doInHibernate(Session
		 * session) throws HibernateException { Example ex =
		 * Example.create(pdSendInfo
		 * ).ignoreCase().enableLike(MatchMode.ANYWHERE); return
		 * session.createCriteria(PdSendInfo.class).add(ex).list(); } }; return
		 * (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdSendInfoDao#getPdSendInfo(String siNo)
	 */
	public PdSendInfo getPdSendInfo(final String siNo) {
		PdSendInfo pdSendInfo = (PdSendInfo) getHibernateTemplate().get(
				PdSendInfo.class, siNo);
		if (pdSendInfo == null) {
			log.warn("uh oh, pdSendInfo with siNo '" + siNo + "' not found...");
			throw new ObjectRetrievalFailureException(PdSendInfo.class, siNo);
		}

		return pdSendInfo;
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdSendInfoDao#savePdSendInfo(PdSendInfo
	 *      pdSendInfo)
	 */
	public void savePdSendInfo(final PdSendInfo pdSendInfo) {
		Integer orderFlag = pdSendInfo.getOrderFlag();
		if((null!=orderFlag)&&(orderFlag != 1)){
			 pdSendInfo.setPartSend("");
		}
		//modify by fu 2016-07-12 如果发货单恢复正常发货，那么暂停发货的原因设置为空---begin
		String shipType = pdSendInfo.getShipType();
		if(StringUtil.isEmpty(shipType)||((!StringUtil.isEmpty(shipType))&&(Integer.parseInt(shipType)==0))){
			pdSendInfo.setSuspendShipment("");
		}
		//modify by fu 2016-07-12 如果发货单恢复正常发货，那么暂停发货的原因设置为空---end
		
		getHibernateTemplate().saveOrUpdate(pdSendInfo);
	}
	
	

	/*
	 * 得到发货报表 (non-Javadoc)
	 * 
	 * @see
	 * com.joymain.jecs.pd.dao.PdSendInfoDao#getShippingReportList(com.joymain
	 * .jecs.util.data.CommonRecord)
	 */
	public List getShippingReportList(CommonRecord crm) {
		// TODO Auto-generated method stub
		String companyCode = crm.getString("companyCode", "");
		String startTime = crm.getString("checkTimeStart", "");
		String endTime = crm.getString("checkTimeEnd", "");
		String warehouseNo = crm.getString("warehouseNo", "");
		String shNo = crm.getString("shNo", "");
		String orderFlag = crm.getString("orderFlag", "");
		String shipType = crm.getString("shipType", "");
		
		//add comment by lihao,发货单管理-发货报表只允许导出“已审核”-“正常发货”的单据。
		String sql = " select psi.SI_NO,psi.ORDER_NO,psi.CHECK_TIME,psi.CUSTOM_CODE,psi.RECIPIENT_NAME,psi.RECIPIENT_ZIP," +
					" psi.RECIPIENT_ADDR,psi.RECIPIENT_PHONE,psi.RECIPIENT_MOBILE,psi.EMAIL,psi.WAREHOUSE_NO,psi.COD_FLAG,psi.HURRY_FLAG,psi.order_type,DECODE(psi.WHETHER_STOCK,'Y','已备货','未备货') WHETHER_STOCK, "+
				  " jps.PRODUCT_NO,jps.PRODUCT_NAME,psd.price,psd.QTY,state.STATE_PROVINCE_NAME,city.CITY_NAME," +
				  " (select DISTRICT_NAME from jal_district district where psi.district = district.district_id  and rownum = 1) DISTRICT_NAME,u.USER_NAME," +
				  " psd.COMBINATION_PRODUCT_NO,(select product_name from jpm_product where product_no=psd.combination_product_no and rownum=1) COMBINATION_PRODUCT_NAME from "
				+ " pd_send_info psi,                                      "
				+ " pd_send_info_detail psd,                               "
				+ " jpm_product_sale_new jps,                                  "
//				+ " jal_country country,                                   "
				+ " jal_state_province state,                              "
				+ " jal_city city,                                          "
				//+ " jal_district district,"
				+ " jsys_user u "
				+ " where psi.si_no = psd.si_no                            "
				+ " and psi.company_code = jps.company_code                "
				+ " and psi.CUSTOM_CODE = u.user_code "
//				+ " and psi.company_code = country.country_code            "
//				+ " and country.country_id=state.country_id                "
//				+ " and city.state_province_id=state.state_province_id     "
				+ " and psi.recipient_ca_no=state.state_province_id      "
				+ " and psi.city = city.CITY_ID                          "
				//+ " and psi.district = district.district_id				"
				+ " and psd.product_no=jps.product_no                      ";
		sql += " and psi.company_code='" + companyCode + "' ";
		sql += " and psi.WAREHOUSE_NO in('" + warehouseNo.replace(",", "','") + "') ";
		if (StringUtils.isNotEmpty(shNo)) {
			sql += " and psi.SH_NO='" + shNo + "'";
		}
		if (StringUtils.isNotEmpty(startTime)) {
			sql += " and (psi.CHECK_TIME>= to_date ('" + startTime
			+ " 00:00:00','yyyy-mm-dd hh24:mi:ss') or psi.CHECK_TIME is null) ";
		}
		if (StringUtils.isNotEmpty(endTime)) {
			sql += " and (psi.CHECK_TIME<= to_date ('" + endTime
			+ " 23:59:59','yyyy-mm-dd hh24:mi:ss') or psi.CHECK_TIME is null) ";
		}
		//add comment by lihao,发货单管理-发货报表只允许导出“已审核”-“正常发货”的单据。
		/*
		if (StringUtils.isNotEmpty(orderFlag)) {
			sql += " and psi.ORDER_FLAG='" + orderFlag + "'";
		}*/
		sql += " and psi.ORDER_FLAG=1 ";	//ORDER_FLAG 为1 表示已审核
		//-----------------detail
		//add comment by lihao,发货单管理-发货报表只允许导出“已审核”-“正常发货”的单据。
		/*
		if (StringUtils.isNotEmpty(shipType)) {
			if((!StringUtil.isEmpty(shipType))&&("shipTypeShippingRepor".equals(shipType))){
				sql += " and ( psi.ship_Type is null or psi.ship_Type = 0 ) ";
			}else{
			    sql += " and psi.ship_Type = '" + shipType + "'";
			}
		}
		*/
		sql += " and ( psi.ship_Type is null or psi.ship_Type = 0 ) ";//ship_type为0或者null表示正常发货
		
		String productNo = crm.getString("productNo", "");
		if(StringUtils.isNotEmpty(productNo)){
			
		}
		String exportFlag = crm.getString("exportFlag", "");
		if("1".equals(exportFlag)){
			sql += " and psd.product_No='N05200800101CN0' ";
		}else if("0".equals(exportFlag)){
			sql += " and psd.product_No <> 'N05200800101CN0' ";

		}
		sql += " order by psi.si_no";
		log.info("pdsendreport="+sql);

		//Modify By WuCF 20140514  查询记录条数，控制记录条数
		String listNum = crm.getString("listNum");//最大限额数
		
		String sqlCount = "select count(*) from ("+sql+") ";
		Integer num = this.getJdbcTemplate().queryForInt(sqlCount);
		log.info("===发货报表-List执行报表，导出数："+num);
		if(num>Integer.parseInt(listNum)){//如果超过限额，则返回
			List list = new ArrayList<String>();
			list.add(1);
			return list;
		}
		
		return this.getJdbcTemplate().queryForList(sql);
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdSendInfoDao#removePdSendInfo(String siNo)
	 */
	public void removePdSendInfo(final String siNo) {
		getHibernateTemplate().delete(getPdSendInfo(siNo));
	}

	// added for getPdSendInfosByCrm
	public List getPdSendInfosByCrm(CommonRecord crm, Pager pager) {
		String hql = " from PdSendInfo pdSendInfo where 1=1 "; 
//		select new PdSendInfo(siNo,customer.userCode,customer.userName,recipientName,recipientCaNo,city,recipientAddr,recipientZip,recipientPhone,email,checkTime,orderNo,recipientMobile,codFlag,hurryFlag,createUsrCode,createTime,okUsrCode,okTime,warehouseNo,shNo,orderFlag,trackingNo,barCode,pdSendInfoDetails) 
		/**
		 * ---------------
		 */
		
		String cxFlag = crm.getString("cxFlag",""); 
		if("n".equals(cxFlag)){
			hql += " and 1=2 "; 
		}else{			
			//批量打印的发货单号
			String siNoTextarea = crm.getString("siNoTextarea", "");
			if(siNoTextarea!=null && !"".equals(siNoTextarea.trim())){
				siNoTextarea = siNoTextarea.replace("\r\n", ",");
				String[] strs = siNoTextarea.split(",");
				StringBuffer strBuf = new StringBuffer(" (");
				for(int i=0;i<strs.length;i++){
					strBuf.append("'");
					strBuf.append(strs[i]);
					strBuf.append("'");
					if(i<strs.length-1){
						strBuf.append(",");
					}
				}
				strBuf.append(") ");
				hql += " and pdSendInfo.siNo in"+strBuf.toString()+" ";
			}else{			
				String exportFlag = crm.getString("exportFlag", "");
				if("1".equals(exportFlag)){
					hql += " and pdSendInfo.siNo in( select  siNo from PdSendInfoDetail psd where psd.productNo='N05200800101CN0' )";
				}else if("0".equals(exportFlag)){
					hql += " and pdSendInfo.siNo in( select distinct siNo from PdSendInfoDetail psd where psd.productNo <> 'N05200800101CN0' )";
		
				}
				
				String hurryFlag = crm.getString("hurryFlag", "");
				if (StringUtils.isNotEmpty(hurryFlag)) {
					if("1".equals(hurryFlag)){
						hql += " and pdSendInfo.hurryFlag ='1' ";
					}else if("0".equals(hurryFlag)){
						hql += " and (pdSendInfo.hurryFlag ='0' or pdSendInfo.hurryFlag is null)";
			
					}
				}
				
				//modify by fu 2016-1-19 是否已备货
				String whetherStock = crm.getString("whetherStock", "");
				if (StringUtils.isNotEmpty(whetherStock)) {
					if("Y".equals(whetherStock)){
						hql += " and pdSendInfo.whetherStock ='Y' ";
					}else if("N".equals(whetherStock)){
						hql += " and (pdSendInfo.whetherStock ='N' or pdSendInfo.whetherStock is null)";
			
					}
				}
				
				String productNo = crm.getString("productNo", "");
				if (StringUtils.isNotEmpty(productNo)) {
					hql += " and pdSendInfo.siNo in( select  siNo from PdSendInfoDetail psd where psd.productNo='"+productNo+"' )";
				}
				//Modify By WuCF 20130701 添加关键字功能查询
				String keyword = crm.getString("keyword", "");
				if (StringUtils.isNotEmpty(keyword)) { 
					keyword = keyword.trim(); 
					hql += " and (pdSendInfo.recipientName like'"+keyword+"' " +
						   " or pdSendInfo.customer.userName like'"+keyword+"' "+
						   " or pdSendInfo.recipientAddr like'"+keyword+"' "+
						   " or pdSendInfo.recipientMobile like'"+keyword+"' "+
						   " or pdSendInfo.recipientPhone like'"+keyword+"') ";
				} 
				
				
				String codFlag = crm.getString("codFlag", "");
				if (StringUtils.isNotEmpty(codFlag)) {
					if("1".equals(codFlag)){
						hql += " and pdSendInfo.codFlag = '" + codFlag + "'";
					}else{
						hql += " and (pdSendInfo.codFlag = '0' or pdSendInfo.codFlag is null)";
					}
					
				}
				
				String price = crm.getString("price", "");
				if(StringUtils.isNotBlank(price)){
					hql += " and pdSendInfo.siNo in( select distinct siNo from PdSendInfoDetail psd where psd.price="+price+" )";
				}
				
				String defaultWarehouse = crm.getString("defaultWarehouse", "");
				if (StringUtils.isNotEmpty(defaultWarehouse)) {
					hql += " and pdSendInfo.warehouseNo in(" + defaultWarehouse + ")";
				}
		
				
				String orderType = crm.getString("orderType", "");
				if (StringUtils.isNotEmpty(orderType)) {
					hql += " and pdSendInfo.orderType = '" + orderType + "'";
				}
				
				//Modify By WuCF 20140711条形码查询
				String barCode = crm.getString("barCode", "");
				if (StringUtils.isNotEmpty(barCode)) {
					hql += " and pdSendInfo.barCode like '%" + barCode.trim() + "%'";
				}
		
				
				String shipType = crm.getString("shipType", "");
				//------pt			
				if (StringUtils.isNotEmpty(shipType)) {
					if((!StringUtil.isEmpty(shipType))&&("shipTypeShippingRepor".equals(shipType))){
						hql += " and ( pdSendInfo.shipType is null or pdSendInfo.shipType = 0 ) ";
					}else{
						hql += " and pdSendInfo.shipType = '" + shipType + "'";
					}
				}
				
				String trackingNo = crm.getString("trackingNo", "");
				if (StringUtils.isNotEmpty(trackingNo)) {
					hql += " and pdSendInfo.trackingNo like '%" + trackingNo + "%'";
				}
				
				String orderNo = crm.getString("orderNo", "");
				if (StringUtils.isNotEmpty(orderNo)) {
					hql += " and pdSendInfo.orderNo = '" + orderNo + "'";
				}
		
				String warehouseNo = crm.getString("warehouseNo", "");
				if (StringUtils.isNotEmpty(warehouseNo)) {
					hql += " and pdSendInfo.warehouseNo in('" + warehouseNo.replace(",", "','") + "') ";
				} 
		
				String orderFlagDefault = crm.getString("orderFlagDefault", "");
				if (StringUtils.isNotEmpty(orderFlagDefault)) {
					hql += " and pdSendInfo.orderFlag in(" + orderFlagDefault + ")";
				}
				String companyCode = crm.getString("companyCode", "");
				if (StringUtils.isNotEmpty(companyCode)) {
					hql += " and pdSendInfo.companyCode='" + companyCode + "'";
				}
				String siNo = crm.getString("siNo", "");
				if (StringUtils.isNotEmpty(siNo)) {
		//			hql += " and pdSendInfo.siNo='" + siNo + "'";//Modify By WuCF 20130509 
					hql += " and pdSendInfo.siNo in('" + siNo.replace(",", "','") + "')";
				}
		
				String recipientCaNo = crm.getString("recipientCaNo", "");
				if (StringUtils.isNotEmpty(recipientCaNo)) {
					hql += " and pdSendInfo.recipientCaNo ='" + recipientCaNo + "'";
				}
		
				String customCode = crm.getString("customCode", "");
				if (StringUtils.isNotEmpty(customCode)) {
					hql += " and pdSendInfo.customer.userCode='" + customCode + "'";
				}
				
				//Modify By WuCF 20131114 仓库权限管理修改，查询时过滤 
//				String userCodeT = crm.getString("userCodeT", ""); 
//				if (StringUtils.isNotEmpty(userCodeT) && !"root".equals(userCodeT)) {
//					hql += " and exists (select 1 from PdWarehouseUser pdWarehouseUser where pdSendInfo.warehouseNo=pdWarehouseUser.warehouseNo and pdWarehouseUser.userCode='"+userCodeT+"') ";
//				}
		
				String createUsrCode = crm.getString("createUsrCode", "");
				if (StringUtils.isNotEmpty(createUsrCode)) {
					hql += " and pdSendInfo.createUsrCode='" + createUsrCode + "'";
				}
		
				String checkUsrCode = crm.getString("checkUsrCode", "");
				String hasCheckUsrCodeBlank = crm
						.getString("hasCheckUsrCodeBlank", "0");
				if (StringUtils.isNotEmpty(checkUsrCode)) {
					if (hasCheckUsrCodeBlank.equals("1")) {
		
						hql += " and (pdSendInfo.checkUsrCode ='" + checkUsrCode
								+ "' or pdSendInfo.checkUsrCode is null)";
					} else {
						hql += " and pdSendInfo.checkUsrCode='" + checkUsrCode + "'";
					}
		
					// hql +=" and pdSendInfo.checkUsrCode='"+checkUsrCode+"'";
				}
		
				String okUsrCode = crm.getString("okUsrCode", "");
				if (StringUtils.isNotEmpty(okUsrCode)) {
					if (hasCheckUsrCodeBlank.equals("1")) {
		
						hql += " and (pdSendInfo.okUsrCode ='" + okUsrCode
								+ "' or pdSendInfo.okUsrCode is null)";
					} else {
						hql += " and pdSendInfo.okUsrCode='" + okUsrCode + "'";
					}
		
					// hql +=" and pdSendInfo.checkUsrCode='"+checkUsrCode+"'";
				}
				/*
				 * String checkStatus = crm.getString("checkStatus","");
				 * if(StringUtils.isNotEmpty(checkStatus)){ hql
				 * +=" and pdSendInfo.checkStatus='"+checkStatus+"'"; }
				 * 
				 * String okStatus = crm.getString("okStatus","");
				 * if(StringUtils.isNotEmpty(okStatus)){ hql
				 * +=" and pdSendInfo.okStatus='"+okStatus+"'"; }
				 */
		
				String orderFlag = crm.getString("orderFlag", "");
				if (StringUtils.isNotEmpty(orderFlag)) {
					hql += " and pdSendInfo.orderFlag in(" + orderFlag + ")";
				}
				String subOrderType = crm.getString("subOrderType", "");
				if (StringUtils.isNotEmpty(subOrderType)) {
					hql += " and pdSendInfo.subOrderType ='" + subOrderType + "'";
				}
				
				String shNo = crm.getString("shNo", "");
				if (StringUtils.isNotEmpty(shNo)) {
					hql += " and pdSendInfo.shNo ='" + shNo + "'";
				}
		
				String createTimeStart = crm.getString("createTimeStart", "");
				String createTimeEnd = crm.getString("createTimeEnd", "");
				if (StringUtils.isNotEmpty(createTimeStart)) {
					hql += " and pdSendInfo.createTime >=to_date('" + createTimeStart
							+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
				}
				if (StringUtils.isNotEmpty(createTimeEnd)) {
					hql += " and pdSendInfo.createTime <=to_date('" + createTimeEnd
							+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
				}
				
				//Modify By WuCF 20150906
				//是否有审核人
				String hasCheckUser = crm.getString("hasCheckUser", "");
				if(StringUtils.isNotEmpty(hasCheckUser)) {
					if("1".equals(hasCheckUser)){
						hql += " and pdSendInfo.checkUsrCode is not null ";
					}else if("0".equals(hasCheckUser)){
						hql += " and pdSendInfo.checkUsrCode is null ";
					}
				}
				
				//审核者
				if(StringUtils.isNotEmpty(checkUsrCode)) {
					hql += " and pdSendInfo.checkUsrCode='"+checkUsrCode.trim()+"' ";
				}
				
				
				//审核时间		
				String checkTimeStart = crm.getString("checkTimeStart", "");
				String checkTimeEnd = crm.getString("checkTimeEnd", "");
				if (StringUtils.isNotEmpty(checkTimeStart)) {
					hql += " and pdSendInfo.checkTime >=to_date('" + checkTimeStart
							+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
				}
				if (StringUtils.isNotEmpty(checkTimeEnd)) {
					hql += " and pdSendInfo.checkTime <=to_date('" + checkTimeEnd
							+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
				}
		
				String okTimeStart = crm.getString("okTimeStart", "");
				String okTimeEnd = crm.getString("okTimeEnd", "");
				if (StringUtils.isNotEmpty(okTimeStart)) {
					hql += " and pdSendInfo.okTime >=to_date('" + okTimeStart
							+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
				}
				if (StringUtils.isNotEmpty(okTimeEnd)) {
					hql += " and pdSendInfo.okTime <=to_date('" + okTimeEnd
							+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
				}
		
				String recipientName = crm.getString("recipientName", "");
				if (StringUtils.isNotEmpty(recipientName)) {
					hql += " and pdSendInfo.recipientName like '%" + recipientName
							+ "%'";
				}
				
				//modify by fu 2016-02-25 接口物流单号
				String logisticsDo = crm.getString("logisticsDo","" );
				if (StringUtils.isNotEmpty(logisticsDo)) {
					hql += " and pdSendInfo.logisticsDo like '%" + logisticsDo
							+ "%'";
				}
			}
		}
		log.info("============getPdSendInfosByCrm:"+hql);
		if (pager == null) {
			hql+="  order by pdSendInfo.siNo";
			
			//Modify By WuCF 20140514  查询记录条数，控制记录条数
			String listNum = crm.getString("listNum");//最大限额数
			
			// 去除结束
			int start = hql.toLowerCase().indexOf("from ");			
			String sqlCount = "select count(*) from " + hql.substring(start + 4);
			Object obj = this.getSession().createQuery(sqlCount).uniqueResult().toString();
			Integer num = Integer.parseInt(String.valueOf(obj));
			log.info("===发货报表-执行报表，导出数："+num);
			if(listNum!=null && num>Integer.parseInt(listNum)){//如果超过限额，则返回
				List list = new ArrayList<String>();
				list.add(1);
				return list;
			}
			
			return this.getHibernateTemplate().find(hql);
//			return this.findObjectsByHqlQuery(hql, pager);
		}
		// Modify By WuCF 20130509
		if(pager!=null && pager.getLimit()!=null && pager.getLimit().getSort()!=null){
			if (!pager.getLimit().getSort().isSorted()) {
				// sort
				hql += " order by pdSendInfo.siNo desc";
			} else {
				hql += " ORDER BY " + pager.getLimit().getSort().getProperty()
						+ " " + pager.getLimit().getSort().getSortOrder();
			}
		} 
		return this.findObjectsByHqlQuery(hql, pager);
	}

	public List getSunShipmentsByCrm(CommonRecord crm, Pager pager) {
		// TODO Auto-generated method stub
		String sql = "select o.AGENT_NO,o.agent_name,s.* from jfi_sun_member_order o,pd_send_info s where s.order_no=o.member_order_no";
		String trackingNo = crm.getString("trackingNo", "");
		if (StringUtils.isNotEmpty(trackingNo)) {
			sql += " and s.tracking_No like '%" + trackingNo + "%'";
		}
		
		String orderNo = crm.getString("orderNo", "");
		if (StringUtils.isNotEmpty(orderNo)) {
			sql += " and s.order_No = '" + orderNo + "'";
		}
		
		String codFlag = crm.getString("codFlag", "");
		if (StringUtils.isNotEmpty(codFlag)) {
			if("1".equals(codFlag)){
				sql += " and s.codFlag = '" + codFlag + "'";
			}else{
				sql += " and (s.codFlag = '0' or s.codFlag is null)";
			}
			
		}

		String warehouseNo = crm.getString("warehouseNo", "");
		if (StringUtils.isNotEmpty(warehouseNo)) {
			sql += " and s.warehouse_No ='" + warehouseNo + "'";
		}
		
		String createTimeStart = crm.getString("createTimeStart", "");
		String createTimeEnd = crm.getString("createTimeEnd", "");
		if (StringUtils.isNotEmpty(createTimeStart)) {
			sql += " and s.create_Time >=to_date('" + createTimeStart
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		if (StringUtils.isNotEmpty(createTimeEnd)) {
			sql += " and s.create_Time <=to_date('" + createTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}

		String checkTimeStart = crm.getString("checkTimeStart", "");
		String checkTimeEnd = crm.getString("checkTimeEnd", "");
		if (StringUtils.isNotEmpty(checkTimeStart)) {
			sql += " and s.check_Time >=to_date('" + checkTimeStart
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		if (StringUtils.isNotEmpty(checkTimeEnd)) {
			sql += " and s.check_Time <=to_date('" + checkTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}

		String okTimeStart = crm.getString("okTimeStart", "");
		String okTimeEnd = crm.getString("okTimeEnd", "");
		if (StringUtils.isNotEmpty(okTimeStart)) {
			sql += " and s.ok_Time >=to_date('" + okTimeStart
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		if (StringUtils.isNotEmpty(okTimeEnd)) {
			sql += " and s.ok_Time <=to_date('" + okTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}

		String recipientName = crm.getString("recipientName", "");
		if (StringUtils.isNotEmpty(recipientName)) {
			sql += " and s.recipient_Name like '%" + recipientName
					+ "%'";
		}
		String siNo = crm.getString("siNo", "");
		if (StringUtils.isNotEmpty(siNo)) {
			sql += " and s.si_No='" + siNo + "'";
		}

		String recipientCaNo = crm.getString("recipientCaNo", "");
		if (StringUtils.isNotEmpty(recipientCaNo)) {
			sql += " and s.recipient_Ca_No ='" + recipientCaNo + "'";
		}

		String agentNo = crm.getString("agentNo", "");
		if (StringUtils.isNotEmpty(agentNo)) {
			sql += " and o.agent_No='" + agentNo + "'";
		}
		if (!pager.getLimit().getSort().isSorted()) {
			// sort
			sql += " order by si_No desc";
		} else {
			sql += " ORDER BY " + pager.getLimit().getSort().getProperty()
					+ " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsBySQL(sql, pager);
	}

	public Long getUnsendByProductNo(String companyCode, String productNo,String warehouseNo) {
		// TODO Auto-generated method stub
		String sql="select nvl(sum(psd.qty),0) qty from pd_send_info ps, pd_send_info_detail psd where ps.si_no = psd.si_no  and ps.order_flag < 2";
		sql +=" and ps.company_code='"+companyCode+"' and psd.product_no = '"+productNo+"'";
		if(StringUtils.isNotBlank(warehouseNo)){
			sql +=" and ps.warehouse_no='"+warehouseNo+"'";
		}
		return this.getJdbcTemplate().queryForLong(sql);
		
	}

	/**
	 * 判断物流公司是否存在
	 * @param listCode:list编码
	 * @param valueCode：list值
	 * @return
	 */
	public List jsysListValues(String listCode,String valueCode) {
		List list = new ArrayList();
		StringBuffer sqlBuf = new StringBuffer("select t2.value_code from JSYS_LIST_KEY t1,JSYS_LIST_VALUE t2 where t1.key_id=t2.key_id ");
		if (StringUtils.isNotEmpty(listCode)) {
			sqlBuf.append(" and t1.list_code='");
			sqlBuf.append(listCode);
			sqlBuf.append("' ");
		}
		
		if (StringUtils.isNotEmpty(valueCode)) {
			sqlBuf.append(" and t2.value_code='");
			sqlBuf.append(valueCode);
			sqlBuf.append("' ");
		}
		sqlBuf.append(" order by t2.value_code ");
		list = this.getJdbcTemplate().queryForList(sqlBuf.toString());
		return list; 
	}

	
	public List getAlCitysByCountry(String companyCode) {
		// TODO Auto-generated method stub
    	String hql = "from AlCity alCity where alCity.alStateProvince.alCountry.countryCode='"+companyCode+"'";
    	
		return this.getHibernateTemplate().find(hql);
	}
	
	/**
	 * 根据国家获取对应的省/州列表
	 * @param countryCode
	 * @return
	 */
	public List getAlStateProvincesByCountry(final String countryCode){
		return this.getHibernateTemplate().find("from AlStateProvince where alCountry.countryCode=? order by stateProvinceCode",countryCode);
	}
	
	public List getCompanyCode() {
		// TODO Auto-generated method stub
    	String sql = "select company_code from JAL_COMPANY";
    	
    	return  this.getJdbcTemplate().queryForList(sql);
	}

	
	/**
	 * 修改指定区域、商品编号的库存警戒量
	 * @param companyCode：区域
	 * @param productNo：商品编码
	 * @param storageCordon：警戒库存量
	 * @return
	 */
	public int updateStorageCordon(String companyCode, String productNo,String storageCordon) {
		if(StringUtils.isEmpty(storageCordon)){
			storageCordon = "0";
		}
		int returnNum = 0;
		StringBuffer sqlBuf = new StringBuffer("update jpm_product_sale_new t set t.storage_cordon='");
		sqlBuf.append(storageCordon);
		sqlBuf.append("' where 1=1 ");
		sqlBuf.append(" and t.company_code='");
		sqlBuf.append(companyCode);
		sqlBuf.append("' and t.product_no='");
		sqlBuf.append(productNo);
		sqlBuf.append("' ");
		returnNum = this.getJdbcTemplate().update(sqlBuf.toString());
		return returnNum;
	}
	
	/**
     * @see com.joymain.jecs.al.dao.AlCityDao#getAlCity(Long cityId)
     */
    public AlCity getAlCity(final Long cityId) {
        AlCity alCity = (AlCity) getHibernateTemplate().get(AlCity.class, cityId);
//        if (alCity == null) {
//            log.warn("uh oh, alCity with cityId '" + cityId + "' not found...");
//            throw new ObjectRetrievalFailureException(AlCity.class, cityId);
//        }

        return alCity;
    }

    /**
	 * 查询发货单信息
	 * @return
	 */
    public List getPdSendInfos() {
    	String hql = " from PdSendInfo psi ";
		return this.getHibernateTemplate().find(hql);
	}
    
    /**
	 * 查询发货单详细信息
	 * @return
	 */
	public List getPdSendInfoDetails() {
		String hql = " from PdSendInfo psid ";
		return this.getHibernateTemplate().find(hql);
	}

	
	/**
	 * Add By WuCF
	 * 定时任务，自动处理暂不发货发送短信
	 * @return
	 */
	public List checkSmssendTransferTask() {
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" select member_order_no,mobiletele,USER_CODE from jpo_member_order t1 where t1.IS_SHIPMENTS='1' and not exists( ");
		sqlBuf.append(" select 1 from pd_send_info t2 where t1.member_order_no=t2.order_no and t2.order_flag>=2) ");
		sqlBuf.append(" and t1.order_time < trunc(sysdate)-5 and t1.order_time >= trunc(sysdate)-6 ");
		sqlBuf.append(" and not exists(select 1 from jpm_smssend_info t3 where t1.member_order_no=t3.remark2) ");
		return this.getJdbcTemplate().queryForList(sqlBuf.toString());
	}
	
	/**
	 * 1.中策加入人数统计
	 * @param crm
	 * @return
	 */
	public List getZhongcenumByCrm(CommonRecord crm) {
		String yearMonth = crm.getString("yearMonth");//201508
		if(yearMonth!=null && yearMonth.trim().length()==6){
			String year = yearMonth.substring(0,4);
			String month = yearMonth.substring(4,6);
			//预编译参数
			StringBuffer paramsBuf = new StringBuffer("");
			//查询语句
			StringBuffer sqlBuf = new StringBuffer("");
			sqlBuf.append(" select team_code,MEMBER_LEVEL,max(cnt)cnt,sum(CASE WHEN sub_cnt=1 THEN 1 ELSE 0 END) S,sum(CASE WHEN sub_cnt>1 THEN 1 ELSE 0 END) M "); 
			sqlBuf.append(" from(select team_code,MEMBER_LEVEL,PAPERNUMBER, ");
			sqlBuf.append(" count(count(USER_CODE)) OVER(PARTITION BY team_code,MEMBER_LEVEL) cnt, ");
			sqlBuf.append(" count(sub_user_code) sub_cnt ");
			sqlBuf.append(" from (SELECT distinct C.TEAM_CODE,A.MEMBER_LEVEL,C.USER_CODE,b.PAPERNUMBER,d.USER_CODE sub_user_code ");
			sqlBuf.append(" FROM jbd_member_link_calc_hist A,JMI_MEMBER B,JMI_MEMBER d, ");
			sqlBuf.append(" (SELECT CONNECT_BY_ROOT(USER_CODE)TEAM_CODE,USER_CODE,LINK_NO  ");
			sqlBuf.append(" FROM JMI_LINK_REF START WITH USER_CODE in (select user_code from ZCW_USERS) ");
			sqlBuf.append(" CONNECT BY PRIOR USER_CODE=LINK_NO ");
			sqlBuf.append(" AND USER_CODE NOT IN (select user_code from ZCW_USERS))C ");
			sqlBuf.append(" WHERE A.W_MONTH=? AND A.W_YEAR=? ");
			sqlBuf.append(" AND A.USER_CODE=B.USER_CODE ");
			sqlBuf.append(" AND B.USER_CODE=C.USER_CODE ");
			sqlBuf.append(" and d.PAPERNUMBER=B.PAPERNUMBER ");
			sqlBuf.append(" AND b.CREATE_TIME >=(select min(start_time) from jbd_period where f_month=? and f_year=?) ");
			sqlBuf.append(" AND b.CREATE_TIME < (select max(end_time) from jbd_period where f_month=? and f_year=?))tab ");
			sqlBuf.append(" group by team_code,MEMBER_LEVEL,PAPERNUMBER)tt group by team_code,MEMBER_LEVEL ");
			 
			//参数
			paramsBuf.append(","+month);
			paramsBuf.append(","+year);
			paramsBuf.append(","+month);
			paramsBuf.append(","+year);
			paramsBuf.append(","+month);
			paramsBuf.append(","+year);
			
			//返回时调用分页的查询的方法
			Object[] parameters = null;
			if(paramsBuf.toString().length()>=1){
		    	parameters = paramsBuf.toString().substring(1).split(",");
			} 
			log.info("tongji====getProvinceByCrm:"+sqlBuf.toString());
			return this.getJdbcTemplateReport().queryForList(sqlBuf.toString(), parameters);
		}
		return new ArrayList();		
		/*StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" select 'CN46308947' TEAM_CODE, '30' MEMBER_LEVEL,'1' CNT, '2' S,'3' M from dual ");
		sqlBuf.append(" union all ");
		sqlBuf.append(" select 'CN46308947' TEAM_CODE, '20' MEMBER_LEVEL,'11' CNT, '22' S,'33' M from dual ");
		sqlBuf.append(" union all ");
		sqlBuf.append(" select 'CN46308947' TEAM_CODE, '10' MEMBER_LEVEL,'111' CNT, '222' S,'333' M from dual ");
		sqlBuf.append(" union all ");
		sqlBuf.append(" select 'CN46308947' TEAM_CODE, '0' MEMBER_LEVEL,'1111' CNT, '2222' S,'3333' M from dual ");
		sqlBuf.append(" union all ");
		sqlBuf.append(" select 'CN46308947' TEAM_CODE, '5' MEMBER_LEVEL,'11111' CNT, '22222' S,'33333' M from dual ");
		sqlBuf.append(" union all ");
		sqlBuf.append(" select 'CN95932673' TEAM_CODE, '30' MEMBER_LEVEL,'123' CNT, '456' S,'789' M from dual ");
		sqlBuf.append(" union all ");
		sqlBuf.append(" select 'CN55051960' TEAM_CODE, '0' MEMBER_LEVEL,'321' CNT, '654' S,'987' M from dual ");
		return this.getJdbcTemplateReport().queryForList(sqlBuf.toString());*/
	}
	
	/**
	 * 2.日业绩统计 
	 * @param crm
	 * @return
	 */
	public List getDayPerformanceByCrm(CommonRecord crm) {
		String startLogTime = crm.getString("startLogTime");
		String endLogTime = crm.getString("endLogTime");
		
		//预编译参数
		StringBuffer paramsBuf = new StringBuffer("");
		//查询语句
		StringBuffer sqlBuf = new StringBuffer("");
		/*sqlBuf.append("select f_day,amt,pv_amt,lj_amt,lj_pv_amt from (");
		sqlBuf.append(" SELECT tab.f_day,SUM(tab.amt)amt,sum(tab.pv_amt)pv_amt, ");
		sqlBuf.append(" SUM(sum(tab.lj_amt)) OVER(ORDER BY tab.f_day ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) lj_amt, ");
		sqlBuf.append(" SUM(sum(tab.pv_amt)) OVER(ORDER BY tab.f_day ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) lj_pv_amt ");
		sqlBuf.append(" FROM(SELECT to_Date(?,'yyyy-mm-dd') f_day,a.user_code,0 AMT,0 pv_amt,c.price*c.qty lj_amt,c.pv * c.qty lj_pv_amt ");
		sqlBuf.append(" FROM JPO_MEMBER_ORDER_list c,JPO_MEMBER_ORDER A ");
		sqlBuf.append(" WHERE A.STATUS = '2' ");
		sqlBuf.append(" AND A.CHECK_time <to_Date(?,'yyyy-mm-dd') ");
		sqlBuf.append(" AND NVL(IS_RETREAT_ORDER, 0) = 0 ");
		sqlBuf.append(" AND A.COMPANY_CODE = 'CN' ");
		sqlBuf.append(" AND A.MO_ID = C.Mo_ID         ");                                      
		sqlBuf.append(" UNION all ");
		sqlBuf.append(" SELECT to_Date(?,'yyyy-mm-dd') f_day,B.USER_CODE,0 AMT,0 pv_amt,d.qty * d.price*-1 lj_amt ,d.qty * d.pV*-1 lj_pv_amt ");                
		sqlBuf.append(" FROM JPR_REFUND B,jpr_refund_list d                     ");
		sqlBuf.append(" WHERE B.REFUND_STATUS = '2'   ");
		sqlBuf.append(" AND B.REFUND_TIME < to_Date(?,'yyyy-mm-dd') ");          
		sqlBuf.append(" and d.ro_no = b.ro_no ");
		sqlBuf.append(" UNION ALL ");
		sqlBuf.append(" SELECT trunc(A.CHECK_time)f_day,a.user_code,c.price*c.qty AMT,c.pv*c.qty pv_amt,c.price*c.qty lj_amt,c.pv*c.qty lj_pv_amt ");
		sqlBuf.append(" FROM JPO_MEMBER_ORDER_list c,JPO_MEMBER_ORDER A ");
		sqlBuf.append(" WHERE A.STATUS='2' ");
		sqlBuf.append(" AND A.CHECK_time >= to_Date(?,'yyyy-mm-dd') "); 
		sqlBuf.append(" AND A.CHECK_time < to_Date(?,'yyyy-mm-dd') ");
		sqlBuf.append(" AND NVL(IS_RETREAT_ORDER,0)=0 ");
		sqlBuf.append(" AND A.COMPANY_CODE='CN' ");
		sqlBuf.append(" AND A.MO_ID=C.MO_ID ");
		sqlBuf.append(" UNION all ");
		sqlBuf.append(" SELECT trunc(b.REFUND_TIME)f_day,B.USER_CODE,d.qty*d.price*-1 AMT,d.qty*d.pV*-1 pv_amt,d.qty*d.price*-1 lj_amt,d.qty*d.pV*-1 lj_pv_amt ");                
		sqlBuf.append(" FROM JPR_REFUND B,jpr_refund_list d                     ");
		sqlBuf.append(" WHERE B.REFUND_STATUS = '2' ");
		sqlBuf.append(" AND b.REFUND_TIME >= to_Date(?,'yyyy-mm-dd') ");  
		sqlBuf.append(" AND B.REFUND_TIME < to_Date(?,'yyyy-mm-dd')      ");     
		sqlBuf.append(" and d.ro_no = b.ro_no ");
		sqlBuf.append(" )tab  ");
		sqlBuf.append(" WHERE EXISTS(select 1 from (SELECT USER_CODE FROM JMI_LINK_REF START WITH USER_CODE in (select user_code from ZCW_USERS) ");
		sqlBuf.append(" CONNECT BY nocycle PRIOR USER_CODE=LINK_NO)M where TAB.user_code=m.user_code) ");
		sqlBuf.append(" group by tab.f_day )where amt<>0 and pv_amt<>0 ");*/
		sqlBuf.append(" select f_day,amt,pv_amt,lj_amt,lj_pv_amt from  ");
		sqlBuf.append(" (SELECT tab.f_day,SUM(tab.amt)amt,sum(tab.pv_amt)pv_amt, ");
		sqlBuf.append(" SUM(sum(tab.lj_amt)) OVER(ORDER BY tab.f_day ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) lj_amt, ");
		sqlBuf.append(" SUM(sum(tab.lj_pv_amt)) OVER(ORDER BY tab.f_day ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) lj_pv_amt FROM(");
		sqlBuf.append(" SELECT trunc(A.CHECK_time)f_day,a.user_code,c.price*c.qty AMT,c.pv*c.qty pv_amt,c.price*c.qty lj_amt,c.pv*c.qty lj_pv_amt ");
		sqlBuf.append(" FROM JPO_MEMBER_ORDER_list c,JPO_MEMBER_ORDER A ");
		sqlBuf.append(" WHERE A.STATUS='2' ");
		sqlBuf.append(" AND A.CHECK_time >= to_Date(?,'yyyy-mm-dd') "); 
		sqlBuf.append(" AND A.CHECK_time < to_Date(?,'yyyy-mm-dd')+1   ");
//		sqlBuf.append(" AND NVL(IS_RETREAT_ORDER,0)=0 ");
		sqlBuf.append(" AND A.COMPANY_CODE='CN' ");
		sqlBuf.append(" AND A.MO_ID=C.MO_ID ");
		sqlBuf.append(" UNION all ");
		sqlBuf.append(" SELECT trunc(b.REFUND_TIME)f_day,B.USER_CODE,d.qty*d.price*-1 AMT,d.qty*d.pV*-1 pv_amt,d.qty*d.price*-1 lj_amt,d.qty*d.pV*-1 lj_pv_amt ");                
		sqlBuf.append(" FROM JPR_REFUND B,jpr_refund_list d  ");
		sqlBuf.append(" WHERE B.REFUND_STATUS = '2' ");
		sqlBuf.append(" AND B.RETURN_TYPE='0' ");
		sqlBuf.append(" AND b.REFUND_TIME >= to_Date(?,'yyyy-mm-dd') ");
		sqlBuf.append(" AND B.REFUND_TIME < to_Date(?,'yyyy-mm-dd')+1 ");  
		sqlBuf.append(" and d.ro_no = b.ro_no ");
		sqlBuf.append(" )tab  ");
		sqlBuf.append(" group by tab.f_day)where amt<>0 and pv_amt<>0 ");

		 
		//参数
		paramsBuf.append(","+startLogTime);
		paramsBuf.append(","+endLogTime);
		paramsBuf.append(","+startLogTime);
		paramsBuf.append(","+endLogTime);
		
		//返回时调用分页的查询的方法
		Object[] parameters = null;
		if(paramsBuf.toString().length()>=1){
	    	parameters = paramsBuf.toString().substring(1).split(",");
		} 
		log.info("tongji====getProvinceByCrm:"+sqlBuf.toString());
		return this.getJdbcTemplateReport().queryForList(sqlBuf.toString(), parameters);
	}

	/**
	 * 3.决策委省份统计
	 * @author gw 2015-05-26
	 * @param crm
	 * @return
	 */
	public List getProvinceByCrm(CommonRecord crm) {
		
		//sql最后语句
		/* SELECT TT.TEAM_CODE,SUM(T.AMT),
		 * (select e.state_province_name from jal_state_province e where e.state_province_id = C.PROVINCE) province  
		  FROM 
		  ( SELECT A.USER_CODE,D.PRICE*D.qty AMT FROM JPO_MEMBER_ORDER A,JPO_MEMBER_ORDER_list D
		         WHERE A.STATUS='2' AND A.COMPANY_CODE='CN' AND A.MO_ID =D.Mo_ID  
		           AND A.CHECK_DATE >=(select min(start_time) from jbd_period where f_month=10 and f_year=2014)
		           AND A.CHECK_DATE<(select max(end_time) from jbd_period where f_month=10 and f_year=2014)
		        UNION ALL
		        SELECT B.USER_CODE,d.qty * d.price*-1 AS AMT                    
		          FROM JPR_REFUND B,jpr_refund_list d                    
		         WHERE B.REFUND_STATUS = '2'   AND d.ro_no = b.ro_no 
		           AND B.REFUND_TIME >= (select min(start_time) from jbd_period where f_month=10 and f_year=2014)          
		           AND B.REFUND_TIME < (select max(end_time) from jbd_period where f_month=10 and f_year=2014)               
		          )T,JMI_MEMBER C,
		           (SELECT CONNECT_BY_ROOT(USER_CODE) TEAM_CODE,USER_CODE,LINK_NO 
		                                                  FROM JMI_LINK_REF START WITH USER_CODE in (select user_code from ZCW_USERS) 
		                                  CONNECT BY nocycle PRIOR USER_CODE=LINK_NO
		                             AND USER_CODE NOT IN (select user_code from ZCW_USERS))TT
		 WHERE T.USER_CODE=C.USER_CODE
		   AND C.USER_CODE=TT.USER_CODE
		GROUP BY C.PROVINCE,TT.TEAM_CODE;*/
		
		String exercice = crm.getString("exercice");//财年
		String fortuneMonth = crm.getString("fortuneMonth");//财月
		//查询语句
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" ");
		sqlBuf.append(" SELECT TT.TEAM_CODE,SUM(T.AMT) AMT, ");
		sqlBuf.append(" (select e.state_province_name from jal_state_province e where e.state_province_id = C.PROVINCE) province ");
		sqlBuf.append(" FROM ");
		sqlBuf.append(" ( SELECT A.USER_CODE,D.PRICE*D.qty AMT FROM JPO_MEMBER_ORDER A,JPO_MEMBER_ORDER_list D ");
		sqlBuf.append(" WHERE A.STATUS='2' AND A.COMPANY_CODE='CN' AND A.MO_ID =D.Mo_ID ");
		sqlBuf.append(" AND A.CHECK_DATE >=(select min(start_time) from jbd_period where f_month='"+fortuneMonth+"' and f_year='"+exercice+"') ");
		sqlBuf.append(" AND A.CHECK_DATE<(select max(end_time) from jbd_period where f_month='"+fortuneMonth+"' and f_year='"+exercice+"') ");
		sqlBuf.append(" UNION ALL ");
		sqlBuf.append(" SELECT B.USER_CODE,d.qty * d.price*-1 AS AMT ");
		sqlBuf.append(" FROM JPR_REFUND B,jpr_refund_list d ");
		sqlBuf.append(" WHERE B.REFUND_STATUS = '2'   AND d.ro_no = b.ro_no ");
		sqlBuf.append(" AND B.REFUND_TIME >= (select min(start_time) from jbd_period where f_month='"+fortuneMonth+"' and f_year='"+exercice+"') ");
		sqlBuf.append(" AND B.REFUND_TIME < (select max(end_time) from jbd_period where f_month='"+fortuneMonth+"' and f_year='"+exercice+"') ");
		sqlBuf.append("  )T,JMI_MEMBER C,");
		sqlBuf.append(" (SELECT CONNECT_BY_ROOT(USER_CODE) TEAM_CODE,USER_CODE,LINK_NO ");
		sqlBuf.append("  FROM JMI_LINK_REF START WITH USER_CODE in (select user_code from ZCW_USERS) ");
		sqlBuf.append(" CONNECT BY nocycle PRIOR USER_CODE=LINK_NO ");
		sqlBuf.append(" AND USER_CODE NOT IN (select user_code from ZCW_USERS))TT ");
		sqlBuf.append(" WHERE T.USER_CODE=C.USER_CODE ");
		sqlBuf.append(" AND C.USER_CODE=TT.USER_CODE");
		sqlBuf.append(" GROUP BY C.PROVINCE,TT.TEAM_CODE ");
		log.info("tongji====getProvinceByCrm:"+sqlBuf.toString());
		return this.getJdbcTemplateReport().queryForList(sqlBuf.toString());
	}
	
	
	/**
	 * 查询临时表中的数据
	 * @param crm
	 * @return
	 */
	public List getZcwUsers(CommonRecord crm) {
		
		//预编译参数
		StringBuffer paramsBuf = new StringBuffer("SELECT XH ID,USER_CODE USER_CODE,PET_NAME USER_NAME FROM ZCW_USERS ");
		
		return this.getJdbcTemplateReport().queryForList(paramsBuf.toString());
	}
	
	/**
	 * 2.1领导人网体业绩
	 * @param crm
	 * @return
	 */
	public List getNetLeadByCrm(CommonRecord crm) {
		String stDate = "";
		String enDate = "";
		String year = null;
		String week = null;
		String period = null;
		String startDate = crm.getString("startDate","");
		String endDate = crm.getString("endDate","");
		String fyearweek = crm.getString("fyearweek","");
		String wyear = crm.getString("wyear","");
		String wweek = crm.getString("wweek","");
		String dateType = crm.getString("dateType","");//Modify By WuCF 20160517 新增日期类型状态
		
			if(StringUtils.isNotEmpty(startDate)){
				stDate = startDate;;
			}
			if(StringUtils.isNotEmpty(endDate)){
				enDate = endDate;
			}
		
		if(StringUtils.isNotEmpty(wyear)){
			year = wyear;
		}
		if(StringUtils.isNotEmpty(wweek)){
			week = wweek;
		}
		
		if(StringUtils.isNotEmpty(fyearweek)){
			period = fyearweek;
		}
		
		//查询语句
		String sql = "select USER_CODE,USER_NAME,NVL(PV_AMT, 0) PV_AMT,NVL(AMOUNT, 0) AMOUNT,NVL(re_PV_AMT, 0) re_PV_AMT,NVL(re_AMOUNT, 0) re_AMOUNT,NVL(AZPV_AMT, 0) AZPV_AMT,NVL(AZAMOUNT, 0) AZAMOUNT,NVL(re_AZPV_AMT, 0) re_AZPV_AMT,NVL(re_AZAMOUNT, 0) re_AZAMOUNT from table(FN_GET_TEAM_YJ(to_date('" + stDate + "','yyyy-mm-dd HH24:MI:SS')," +
				"to_date('" + enDate + "','yyyy-mm-dd HH24:MI:SS')," +
						" "+ period+","+ year +","+ week +","+dateType+"))";
		log.info("---2.1sql:"+sql.toString());
		System.out.println("---2.1sql:"+sql.toString());
		return this.getJdbcTemplateReport().queryForList(sql);
	}
	
	/**
	 * 2.2领导人区域业绩
	 * @param crm
	 * @return
	 */
	public List getAreaLeadByCrm(CommonRecord crm) {
		String stDate = "";
		String enDate = "";
		String year = null;
		String week = null;
		String period = null;
		String startDate = crm.getString("startDate","");
		String endDate = crm.getString("endDate","");
		String fyearweek = crm.getString("fyearweek","");
		String wyear = crm.getString("wyear","");
		String wweek = crm.getString("wweek","");
		String dateType = crm.getString("dateType","");//Modify By WuCF 20160517 新增日期类型状态
		
			if(StringUtils.isNotEmpty(startDate)){
				stDate = startDate;;
			}
			if(StringUtils.isNotEmpty(endDate)){
				enDate = endDate;
			}
		
		if(StringUtils.isNotEmpty(wyear)){
			year = wyear;
		}
		if(StringUtils.isNotEmpty(wweek)){
			week = wweek;
		}
		
		if(StringUtils.isNotEmpty(fyearweek)){
			period = fyearweek;
		}
		
		//查询语句
		String sql = "select * from table(FN_get_team_REA_YJ(to_date('" + stDate + "','yyyy-mm-dd HH24:MI:SS')," +
				"to_date('" + enDate + "','yyyy-mm-dd HH24:MI:SS')," +
						" "+ period+","+ year +","+ week +","+dateType+"))";
		log.info("---2.2sql:"+sql.toString());
		System.out.println("---2.2sql:"+sql.toString());
		return this.getJdbcTemplateReport().queryForList(sql);
	}
	
	/**
	 * 2.3领导人推荐网体加入人数
	 * @param crm
	 * @return
	 */
	public List getRecommendLeadByCrm(CommonRecord crm) {
		String stDate = "";
		String enDate = "";
		String year = null;
		String week = null;
		String period = null;
		String startDate = crm.getString("startDate","");
		String endDate = crm.getString("endDate","");
		String fyearweek = crm.getString("fyearweek","");
		String wyear = crm.getString("wyear","");
		String wweek = crm.getString("wweek","");
		String dateType = crm.getString("dateType","");//Modify By WuCF 20160517 新增日期类型状态
		
			if(StringUtils.isNotEmpty(startDate)){
				stDate = startDate;;
			}
			if(StringUtils.isNotEmpty(endDate)){
				enDate = endDate;
			}
		
		if(StringUtils.isNotEmpty(wyear)){
			year = wyear;
		}
		if(StringUtils.isNotEmpty(wweek)){
			week = wweek;
		}
		
		if(StringUtils.isNotEmpty(fyearweek)){
			period = fyearweek;
		}
		
		//查询语句
		String sql = "select * from table(FN_GET_TEAM_recommends(to_date('" + stDate + "','yyyy-mm-dd HH24:MI:SS')," +
				"to_date('" + enDate + "','yyyy-mm-dd HH24:MI:SS')," +
						" "+ period+","+ year +","+ week +","+dateType+"))";
		log.info("---2.3sql:"+sql.toString());
		System.out.println("---2.3sql:"+sql.toString());
		return this.getJdbcTemplateReport().queryForList(sql);
	}
	
	/**
	 * 2.4  30万大单数据
	 * @param crm
	 * @return
	 */
	public List getbigJpomemberorderByCrm(CommonRecord crm) {
		
		//查询语句
		String startDate = crm.getString("startDate","");
		String endDate = crm.getString("endDate","");
		String fyearweek = crm.getString("fyearweek","");
		String wyear = crm.getString("wyear","");
		String wweek = crm.getString("wweek","");
		
		String sql = "SELECT PAPERNUMBER," +
	       "USER_CODE," +
	       "MI_USER_NAME," +
	       "CARD_TYPE," +
	       "ISSTORE," +
	       "SUBSTR(TAB.ZCW_MESSAGE, 1, INSTR(TAB.ZCW_MESSAGE, '||', 1, 1) - 1) ZCW_CODE," +
	       "SUBSTR(TAB.ZCW_MESSAGE, INSTR(TAB.ZCW_MESSAGE, '||', 1, 1) + 2,(INSTR(TAB.ZCW_MESSAGE, '||', 1, 2)-1-INSTR(TAB.ZCW_MESSAGE, '||', 1, 1) -1)) ZCWTYPE," +
	       "SUBSTR(TAB.ZCW_MESSAGE, INSTR(TAB.ZCW_MESSAGE, '||', 1, 2) +2 ) ZCW_NAME,   " +
	       "(AMOUNT+JJ_AMOUNT) AMOUNT," +
	       "(select e.state_province_name " +
	       " 	from jal_state_province e " +
	       " 	where e.state_province_id = tab.province) province," +
	       " (select f.city_name from jal_city f where f.city_id = tab.city) city," +
	       " ( select s.district_name " +
	       "    from jal_district s" +
	       "    where s.district_id = district )DISTRICT," +
	       " ADDRESS," +
	       " MOBILETELE " +
	       "		FROM (SELECT nvl(A.AMOUNT,0) AMOUNT,nvl(a.JJ_AMOUNT,0)JJ_AMOUNT," +
	       "            sum(nvl(A.AMOUNT,0)+nvl(a.JJ_AMOUNT,0)) OVER(PARTITION BY B.PAPERNUMBER) SUM_AMOUNT," +
	       "           B.PAPERNUMBER," +
	       "          B.USER_CODE," +
	       "         B.MI_USER_NAME," +
	       "        DECODE(B.CARD_TYPE,'0','待审会员','1','银卡','2','金卡','3','白金卡','4','钻石卡','5','优惠顾客','6','VIP')CARD_TYPE," +
	       "       DECODE(B.ISSTORE, 0, '会员', 1, '一级店铺', 2, '二级店铺') ISSTORE," +
	       "      B.PROVINCE," +
	       "     B.CITY," +
	       "    B.DISTRICT," +
	       "   B.ADDRESS," +
	       "  B.MOBILETELE," +
	       " Fn_Get_Zcw_Message_new(B.USER_CODE) ZCW_MESSAGE  " +
	       " FROM JPO_MEMBER_ORDER A, JMI_MEMBER B " +
	       " WHERE A.USER_CODE = B.USER_CODE ";
		
		 // --时间
		if(StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)){
			sql += 	"   AND A.CHECK_DATE >= to_date('" + startDate + " 00:00:00','yyyy-mm-dd HH24:MI:SS')" +
					"  AND A.CHECK_DATE <= to_date('" + endDate + " 23:59:59','yyyy-mm-dd HH24:MI:SS')" ;
			sql += 	" AND A.STATUS = 2  AND NVL(A.IS_RETREAT_ORDER, 0) = 0 ) TAB" +
					" WHERE SUM_AMOUNT >= 300000 ";
			log.info("---2.4sql1:"+sql.toString());
			System.out.println("---2.4sql1:"+sql.toString());
			return this.getJdbcTemplateReport().queryForList(sql);
			
		}
		// --期别
		if(StringUtils.isNotEmpty(fyearweek) && StringUtils.isNotEmpty(fyearweek)){
			sql +="    AND A.CHECK_DATE >= (select min(start_time) from jbd_period where f_year= "+fyearweek.substring(0, 4) +" and f_week="+ fyearweek.substring(4) +")" +
			"    AND A.CHECK_DATE <= (select max(end_time) from jbd_period where f_year="+ fyearweek.substring(0, 4)+" and f_week="+ fyearweek.substring(4) +")";
			sql += 	" AND A.STATUS = 2  AND NVL(A.IS_RETREAT_ORDER, 0) = 0 ) TAB" +
					" WHERE SUM_AMOUNT >= 300000 ";
			log.info("---2.4sql2:"+sql.toString());
			System.out.println("---2.4sql2:"+sql.toString());
			return this.getJdbcTemplateReport().queryForList(sql);
		}
	     //   --工作年
		if(StringUtils.isNotEmpty(wyear) && StringUtils.isNotEmpty(wyear)){
			sql +=	"     AND A.CHECK_DATE >= (select min(start_time) from jbd_period where f_year="+ wyear +")" +
					"     AND A.CHECK_DATE <= (select max(end_time) from jbd_period where f_year="+ wyear +")";
			sql += 	" AND A.STATUS = 2  AND NVL(A.IS_RETREAT_ORDER, 0) = 0 ) TAB" +
					" WHERE SUM_AMOUNT >= 300000 ";
			log.info("---2.4sql3:"+sql.toString());
			System.out.println("---2.4sql3:"+sql.toString());
			return this.getJdbcTemplateReport().queryForList(sql);
		}
	     //   --工作月
		if(StringUtils.isNotEmpty(wweek) && StringUtils.isNotEmpty(wweek)){
			sql += 	"     AND A.CHECK_DATE >= (select min(start_time) from jbd_period where f_year="+ wweek.substring(0, 4) +" and f_month="+ wweek.substring(4) +")" +
					"     AND A.CHECK_DATE <= (select max(end_time) from jbd_period where f_year="+ wweek.substring(0, 4) +" and f_month="+ wweek.substring(4) +")";
			sql += 	" AND A.STATUS = 2  AND NVL(A.IS_RETREAT_ORDER, 0) = 0 ) TAB" +
					" WHERE SUM_AMOUNT >= 300000 ";
			log.info("---2.4sql4:"+sql.toString());
			System.out.println("---2.4sql4:"+sql.toString());
			return this.getJdbcTemplateReport().queryForList(sql);
		}
		
			sql +=	" AND A.STATUS = 2  AND NVL(A.IS_RETREAT_ORDER, 0) = 0 ) TAB " +
					" WHERE SUM_AMOUNT >= 300000 ";
			log.info("---2.4sql5:"+sql.toString());
			System.out.println("---2.4sql5:"+sql.toString());
		return this.getJdbcTemplateReport().queryForList(sql);
	}
	
	/**
	 * 2.5 颐萃产品统计
	 * @param crm
	 * @return
	 */
	public List getHuicuiProductByCrm(CommonRecord crm) {
		
		String stDate = "";
		String enDate = "";
		String year = null;
		String week = null;
		String period = null;
		String startDate = crm.getString("startDate","");
		String endDate = crm.getString("endDate","");
		String fyearweek = crm.getString("fyearweek","");
		String wyear = crm.getString("wyear","");
		String wweek = crm.getString("wweek","");
		
			if(StringUtils.isNotEmpty(startDate)){
				stDate = startDate;;
			}
			if(StringUtils.isNotEmpty(endDate)){
				enDate = endDate;
			}
		
		if(StringUtils.isNotEmpty(wyear)){
			year = wyear;
		}
		if(StringUtils.isNotEmpty(wweek)){
			week = wweek;
		}
		if(StringUtils.isNotEmpty(fyearweek)){
			period = fyearweek;
		}
		
		//查询语句
		String sql = "select * from table(FN_GET_pro_order(to_date('" + stDate + "','yyyy-mm-dd HH24:MI:SS')," +
				"to_date('" + enDate + "','yyyy-mm-dd HH24:MI:SS')," +
						" "+ period+","+ year +","+ week +"))";
		log.info("---2.5sql:"+sql.toString());
		System.out.println("---2.5sql:"+sql.toString());
		return this.getJdbcTemplateReport().queryForList(sql);
	}
	
	/**
	 * 2.6云南团队保养贴
	 * @param crm
	 * @return
	 */
	public List getYunnanchongxiaoByCrm(CommonRecord crm) {
		
		String startDate = crm.getString("startDate","");
		String endDate = crm.getString("endDate","");
		String fyearweek = crm.getString("fyearweek","");
		String wyear = crm.getString("wyear","");
		String wweek = crm.getString("wweek","");
		//查询语句
//		StringBuffer paramsBuf = new StringBuffer(" ");
		String sql = "	SELECT B.USER_CODE,f.MI_USER_NAME,f.mobiletele," + 
					" DECODE(f.ISSTORE,'0','会员','1', '一级店铺', '2', '二级店铺',f.ISSTORE) STYPE," + 
					" DECODE(B.ORDER_TYPE,1,'首购单',2,'升级单',3,'续约单',5,'辅销品',4,'重消'," + 
					"            6,'店铺首购',8,'店铺返单',9,'店铺重消',11,'二级店铺首购单',12,'二级店铺升级单'," + 
					"           14,'二级店铺重消单',15,'AUTOSHIP',21,'梦想馆首单',24,'梦想馆重消单',30,'公益基金',40,'全年重消单',32,'奖衔补单',B.ORDER_TYPE) ORDER_TYPE," + 
					"	b.member_order_no,C.PRODUCT_NO,a.qty,b.order_time" + 
					"	FROM JPO_MEMBER_ORDER_LIST      A," + 
					"   JPO_MEMBER_ORDER           B," + 
					"   JPM_PRODUCT_SALE_NEW       C," + 
					"  	JPM_PRODUCT_SALE_TEAM_TYPE E," + 
					" 	JMI_MEMBER f " + 
					"	WHERE A.MO_ID = B.MO_ID" + 
					" 	and b.user_code=f.user_code" + 
					"	AND A.PRODUCT_ID = E.PTT_ID" +
					//dengmh update by 2016/5/17 过滤延迟订单挂起状态
					"   AND (B.RETURNABLE_STATUS != '1' or B.RETURNABLE_STATUS is null) ";
		// --时间
		if(StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)){
			sql += 	"   AND B.CHECK_DATE >= to_date('" + startDate + " 00:00:00','yyyy-mm-dd HH24:MI:SS')" +
					"  AND B.CHECK_DATE <= to_date('" + endDate + " 23:59:59','yyyy-mm-dd HH24:MI:SS')" ;
			
			sql += " AND B.STATUS = '2' " +
			"	AND nvl(B.IS_RETREAT_ORDER, 0) = 0" +
			"	AND C.UNI_NO = E.UNI_NO" +
			"	AND C.PRODUCT_NO IN ('P03200100101CN0','P03190100101CN0','P03190100102CN0')" +
			"	and B.ORDER_TYPE in (9,14,40,32)" +
			"	and exists(select 1 from (SELECT user_code" +
			"   FROM JMI_RECOMMEND_REF" +
			"   START WITH USER_CODE = 'CN18645446'" +
			"   CONNECT BY PRIOR  USER_CODE=RECOMMEND_NO) m where m.user_code=b.user_code)" ;
			log.info("---2.6sql1:"+sql.toString());
			System.out.println("---2.6sql1:"+sql.toString());
			return this.getJdbcTemplateReport().queryForList(sql);
		}
		// --期别
		if(StringUtils.isNotEmpty(fyearweek) && StringUtils.isNotEmpty(fyearweek)){
			sql +=	"    AND B.CHECK_DATE >= (select min(start_time) from jbd_period where f_year= "+fyearweek.substring(0, 4) +" and f_week="+ fyearweek.substring(4) +")" +
				  	"    AND B.CHECK_DATE <= (select max(end_time) from jbd_period where f_year="+ fyearweek.substring(0, 4)+" and f_week="+ fyearweek.substring(4) +")";
			sql += 	" AND B.STATUS = '2' " +
					"	AND nvl(B.IS_RETREAT_ORDER, 0) = 0" +
					"	AND C.UNI_NO = E.UNI_NO" +
					"	AND C.PRODUCT_NO IN ('P03200100101CN0','P03190100101CN0','P03190100102CN0')" +
					"	and B.ORDER_TYPE in (9,14,40,32)" +
					"	and exists(select 1 from (SELECT user_code" +
					"   FROM JMI_RECOMMEND_REF" +
					"   START WITH USER_CODE = 'CN18645446'" +
					"   CONNECT BY PRIOR  USER_CODE=RECOMMEND_NO) m where m.user_code=b.user_code)" ;
			log.info("---2.6sql2:"+sql.toString());
			System.out.println("---2.6sql2:"+sql.toString());
			return this.getJdbcTemplateReport().queryForList(sql);
		}
	     //   --工作年
		if(StringUtils.isNotEmpty(wyear) && StringUtils.isNotEmpty(wyear)){
			sql += 	"     AND B.CHECK_DATE >= (select min(start_time) from jbd_period where f_year="+ wyear +")" +
					"     AND B.CHECK_DATE <= (select max(end_time) from jbd_period where f_year="+ wyear +")";
			
			sql += 	" AND B.STATUS = '2' " +
					"	AND nvl(B.IS_RETREAT_ORDER, 0) = 0" +
					"	AND C.UNI_NO = E.UNI_NO" +
					"	AND C.PRODUCT_NO IN ('P03200100101CN0','P03190100101CN0','P03190100102CN0')" +
					"	and B.ORDER_TYPE in (9,14,40,32)" +
					"	and exists(select 1 from (SELECT user_code" +
					"   FROM JMI_RECOMMEND_REF" +
					"   START WITH USER_CODE = 'CN18645446'" +
					"   CONNECT BY PRIOR  USER_CODE=RECOMMEND_NO) m where m.user_code=b.user_code)" ;
			log.info("---2.6sql3:"+sql.toString());
			System.out.println("---2.6sql3:"+sql.toString());
			return this.getJdbcTemplateReport().queryForList(sql);
			
		}
	     //   --工作月
		if(StringUtils.isNotEmpty(wweek) && StringUtils.isNotEmpty(wweek)){
			sql += 	"     AND B.CHECK_DATE >= (select min(start_time) from jbd_period where f_year="+ wweek.substring(0, 4) +" and f_month="+ wweek.substring(4) +")" +
					"     AND B.CHECK_DATE <= (select max(end_time) from jbd_period where f_year="+ wweek.substring(0, 4) +" and f_month="+ wweek.substring(4) +")";
			
			sql += 	" AND B.STATUS = '2' " +
					"	AND nvl(B.IS_RETREAT_ORDER, 0) = 0" +
					"	AND C.UNI_NO = E.UNI_NO" +
					"	AND C.PRODUCT_NO IN ('P03200100101CN0','P03190100101CN0','P03190100102CN0')" +
					"	and B.ORDER_TYPE in (9,14,40,32)" +
					"	and exists(select 1 from (SELECT user_code" +
					"   FROM JMI_RECOMMEND_REF" +
					"   START WITH USER_CODE = 'CN18645446'" +
					"   CONNECT BY PRIOR  USER_CODE=RECOMMEND_NO) m where m.user_code=b.user_code)" ;
			log.info("---2.6sql4:"+sql.toString());
			System.out.println("---2.6sql4:"+sql.toString());
			return this.getJdbcTemplateReport().queryForList(sql);
		}
		
		sql += 	" AND B.STATUS = '2' " +
				"	AND nvl(B.IS_RETREAT_ORDER, 0) = 0" +
				"	AND C.UNI_NO = E.UNI_NO" +
				"	AND C.PRODUCT_NO IN ('P03200100101CN0','P03190100101CN0','P03190100102CN0')" +
				"	and B.ORDER_TYPE in (9,14,40,32)" +
				"	and exists(select 1 from (SELECT user_code" +
				"   FROM JMI_RECOMMEND_REF" +
				"   START WITH USER_CODE = 'CN18645446'" +
				"   CONNECT BY PRIOR  USER_CODE=RECOMMEND_NO) m where m.user_code=b.user_code)" ;
		log.info("---2.6sql5:"+sql.toString());
		System.out.println("---2.6sql5:"+sql.toString());
		return this.getJdbcTemplateReport().queryForList(sql);
		
	}
	
	
	/**
	 * 2.7道和坛酒数订单
	 * @param crm
	 * @return
	 */
	public List getDaoheWineJpomemberorderByCrm(CommonRecord crm) {
		String startDate = crm.getString("startDate", "");//起始时间
		String endDate = crm.getString("endDate", "");//截止时间
		String formatedWeek = crm.getString("formatedWeek", "");//期别
		String formatedYear = crm.getString("formatedYear", "");//工作年
		String formatedMonth = crm.getString("formatedMonth", "");//工作月
		
		//查询语句
		StringBuffer sqlBuf = new StringBuffer(" ");
		sqlBuf.append(" SELECT distinct USER_CODE,USERNAME,MEMBER_ORDER_NO,PRODUCT_NO,PV_AMT,province||city||DISTRICT||RECIPIENT_ADDR ADDR,CHECK_DATE, ");
		sqlBuf.append(" SUBSTR(ZCW_MESSAGE, 1, INSTR(ZCW_MESSAGE, '||', 1, 1) - 1) ZCW_CODE, ");
		sqlBuf.append(" SUBSTR(ZCW_MESSAGE, INSTR(ZCW_MESSAGE, '||', 1, 2) +2 ) ZCW_NAME,QTY ");
		sqlBuf.append(" FROM(select B.USER_CODE, ");
		sqlBuf.append(" (SELECT G.MI_USER_NAME ");
		sqlBuf.append(" FROM JMI_MEMBER G ");
		sqlBuf.append(" WHERE G.USER_CODE = B.USER_CODE) USERNAME,B.MEMBER_ORDER_NO,C.PRODUCT_NO,(A.PV * A.QTY) PV_AMT, ");
		sqlBuf.append(" (select O.state_province_name ");
		sqlBuf.append(" from jal_state_province O ");
		sqlBuf.append(" where O.state_province_id = B.PROVINCE) province, ");
		sqlBuf.append(" (select P.city_name from jal_city P where P.city_id = b.city) city, ");
		sqlBuf.append(" (select K.district_name ");
		sqlBuf.append(" from jal_district K ");
		sqlBuf.append(" where K.district_id = b.district) DISTRICT, ");
		sqlBuf.append(" B.Address RECIPIENT_ADDR,b.CHECK_DATE,Fn_Get_Zcw_Message_new(b.USER_CODE) ZCW_MESSAGE,A.Qty QTY ");
		sqlBuf.append(" FROM JPO_MEMBER_ORDER_LIST      A, ");
		sqlBuf.append(" JPO_MEMBER_ORDER           B, ");
		sqlBuf.append(" JPM_PRODUCT_SALE_NEW       C, ");
		sqlBuf.append(" pd_send_info               F, ");
		sqlBuf.append(" JPM_PRODUCT_SALE_TEAM_TYPE E ");
		sqlBuf.append(" WHERE A.MO_ID = B.MO_ID ");
		sqlBuf.append(" AND B.MEMBER_ORDER_NO = F.ORDER_NO ");
		sqlBuf.append(" AND A.PRODUCT_ID = E.PTT_ID ");
		sqlBuf.append(" AND B.STATUS = '2' ");
		sqlBuf.append(" AND nvl(B.IS_RETREAT_ORDER, 0) = 0 ");
		//dengmh update by 2016/5/17 过滤延迟订单挂起状态
		sqlBuf.append(" AND (B.RETURNABLE_STATUS != '1' or B.RETURNABLE_STATUS is null) ");

		//起始日期、截止日期
		if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
			sqlBuf.append(" AND B.CHECK_DATE >= to_date('"+startDate+" 00:00:00','yyyy-mm-dd HH24:MI:SS') ");
			sqlBuf.append(" AND B.CHECK_DATE < to_date('"+endDate+" 23:59:59','yyyy-mm-dd HH24:MI:SS') ");
//			sqlBuf.append(" AND B.CHECK_DATE >= DATE '"+startDate+"' ");
//			sqlBuf.append(" AND B.CHECK_DATE < DATE '"+endDate+"' ");
			sqlBuf.append(" AND C.UNI_NO = E.UNI_NO ");
			sqlBuf.append(" AND C.PRODUCT_NO IN ('P1505010101CN0','P1503010101CN0', 'P1506010101CN0','P1501010101CN0', ");
			sqlBuf.append(" 'P1502010101CN0','P1507010101CN0','P1504010101CN0','P1508010101CN0')) ");
			log.info("---2.7sql1:"+sqlBuf.toString());
			System.out.println("---2.7sql1:"+sqlBuf.toString());
			return this.getJdbcTemplateReport().queryForList(sqlBuf.toString());
		}
		

		//期别
		if (StringUtils.isNotEmpty(formatedWeek) && formatedWeek.trim().length()==6) {
			sqlBuf.append(" AND B.CHECK_DATE >= (select min(start_time) from jbd_period where f_year='"+formatedWeek.trim().substring(0,4)+"' and f_week='"+formatedWeek.trim().substring(4,6)+"') ");
			sqlBuf.append(" AND B.CHECK_DATE < (select max(end_time) from jbd_period where f_year='"+formatedWeek.trim().substring(0,4)+"' and f_week='"+formatedWeek.trim().substring(4,6)+"') ");
			sqlBuf.append(" AND C.UNI_NO = E.UNI_NO ");
			sqlBuf.append(" AND C.PRODUCT_NO IN ('P1505010101CN0','P1503010101CN0', 'P1506010101CN0','P1501010101CN0', ");
			sqlBuf.append(" 'P1502010101CN0','P1507010101CN0','P1504010101CN0','P1508010101CN0')) ");
			log.info("---2.7sql2:"+sqlBuf.toString());
			System.out.println("---2.7sql2:"+sqlBuf.toString());
			return this.getJdbcTemplateReport().queryForList(sqlBuf.toString());
		}
		//年份
		if (StringUtils.isNotEmpty(formatedYear)) {
			sqlBuf.append(" AND B.CHECK_DATE >= (select min(start_time) from jbd_period where f_year='"+formatedYear.trim()+"') ");
			sqlBuf.append(" AND B.CHECK_DATE < (select max(end_time) from jbd_period where f_year='"+formatedYear.trim()+"') ");
			sqlBuf.append(" AND C.UNI_NO = E.UNI_NO ");
			sqlBuf.append(" AND C.PRODUCT_NO IN ('P1505010101CN0','P1503010101CN0', 'P1506010101CN0','P1501010101CN0', ");
			sqlBuf.append(" 'P1502010101CN0','P1507010101CN0','P1504010101CN0','P1508010101CN0')) ");
			log.info("---2.7sql3:"+sqlBuf.toString());
			System.out.println("---2.7sql3:"+sqlBuf.toString());
			return this.getJdbcTemplateReport().queryForList(sqlBuf.toString());
		}

		//月份
		if (StringUtils.isNotEmpty(formatedMonth)) {
			sqlBuf.append(" AND B.CHECK_DATE >= (select min(start_time) from jbd_period where f_year='"+formatedMonth.trim().substring(0,4)+"' and f_month='"+formatedMonth.trim().substring(5,6)+"') ");
			sqlBuf.append(" AND B.CHECK_DATE < (select max(end_time) from jbd_period where f_year='"+formatedMonth.trim().substring(0,4)+"' and f_month='"+formatedMonth.trim().substring(5,6)+"') ");
			sqlBuf.append(" AND C.UNI_NO = E.UNI_NO ");
			sqlBuf.append(" AND C.PRODUCT_NO IN ('P1505010101CN0','P1503010101CN0', 'P1506010101CN0','P1501010101CN0', ");
			sqlBuf.append(" 'P1502010101CN0','P1507010101CN0','P1504010101CN0','P1508010101CN0')) ");
			log.info("---2.7sql4:"+sqlBuf.toString());
			System.out.println("---2.7sql4:"+sqlBuf.toString());
			return this.getJdbcTemplateReport().queryForList(sqlBuf.toString());
		}
		
		sqlBuf.append(" AND C.UNI_NO = E.UNI_NO ");
		sqlBuf.append(" AND C.PRODUCT_NO IN ('P1505010101CN0','P1503010101CN0', 'P1506010101CN0','P1501010101CN0', ");
		sqlBuf.append(" 'P1502010101CN0','P1507010101CN0','P1504010101CN0','P1508010101CN0')) ");
		log.info("---2.7sql5:"+sqlBuf.toString());
		System.out.println("---2.7sql5:"+sqlBuf.toString());
		return this.getJdbcTemplateReport().queryForList(sqlBuf.toString());
	}
	
	/**
	 * 2.8道和坛酒数退单数据
	 * @param crm
	 * @return
	 */
	public List getDaoheWineJprrefundByCrm(CommonRecord crm) {
		String startDate = crm.getString("startDate", "");//起始时间
		String endDate = crm.getString("endDate", "");//截止时间
		String formatedWeek = crm.getString("formatedWeek", "");//期别
		String formatedYear = crm.getString("formatedYear", "");//工作年
		String formatedMonth = crm.getString("formatedMonth", "");//工作月
		
		//查询语句
		StringBuffer sqlBuf = new StringBuffer(" ");
		sqlBuf.append(" select  TT.USER_CODE,(SELECT FIRST_NAME || LAST_NAME FROM JMI_MEMBER WHERE USER_CODE = TT.USER_CODE) USER_NAME, ");
		sqlBuf.append(" TT.CHECK_DATE,TT.MEMBER_ORDER_NO,TT.PRODUCT_NO,TT.PV_AMT,(select t.state_province_name from jal_state_province t ");
		sqlBuf.append(" where t.state_province_id = TT.province) ||(select t.city_name from jal_city t where t.city_id = TT.city) || ");
		sqlBuf.append(" (select t.district_name from jal_district t where t.DISTRICT_ID = TT.district) || RECIPIENT_ADDR addr, ");
		sqlBuf.append(" SUBSTR(TT.ZCW_MESSAGE, 1, INSTR(TT.ZCW_MESSAGE, '||', 1, 1) - 1) ZCW_CODE,SUBSTR(TT.ZCW_MESSAGE, INSTR(TT.ZCW_MESSAGE, '||', 1, 1) + 2) ZCW_NAME,CHECK_DATE,QTY ");
		sqlBuf.append(" from (SELECT USER_CODE,MEMBER_ORDER_NO,PRODUCT_NO,SUM(PV_AMT)PV_AMT,province,city,district,RECIPIENT_ADDR,CHECK_DATE, ");
		sqlBuf.append(" fn_get_zcw_message_new(USER_CODE) ZCW_MESSAGE,QTY FROM (SELECT distinct B.USER_CODE,B.MEMBER_ORDER_NO, ");
		sqlBuf.append(" C.PRODUCT_NO,G.PV * G.QTY  PV_AMT,b.province,b.city,b.district,B.address RECIPIENT_ADDR,B.CHECK_DATE,g.qty QTY ");
		sqlBuf.append(" FROM JPO_MEMBER_ORDER           B, ");
		sqlBuf.append(" Jpr_Refund_list            g, ");
		sqlBuf.append(" Jpr_Refund                 f, ");
		sqlBuf.append(" JPM_PRODUCT_SALE_NEW       C, ");
		sqlBuf.append(" JPM_PRODUCT_SALE_TEAM_TYPE E, ");
		sqlBuf.append(" pd_send_info               D ");
		sqlBuf.append(" WHERE B.COMPANY_CODE = 'CN' AND G.PRODUCT_ID = E.PTT_ID AND B.MO_ID = f.MO_ID ");
		sqlBuf.append(" AND g.RO_NO = f.RO_NO AND nvl(B.IS_RETREAT_ORDER, 0) = 1 ");
		
		//--时间
		//起始日期、截止日期
		if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
			sqlBuf.append(" AND B.CHECK_DATE >= to_date('"+startDate+" 00:00:00', 'yyyy-mm-dd HH24:MI:SS') ");
			sqlBuf.append(" AND B.CHECK_DATE < to_date('"+endDate+" 23:59:59', 'yyyy-mm-dd HH24:MI:SS') ");
			sqlBuf.append(" AND B.STATUS = '2' AND B.MEMBER_ORDER_NO = D.ORDER_NO ");
			sqlBuf.append(" AND C.UNI_NO = E.UNI_NO AND C.PRODUCT_NO IN ('P1505010101CN0', ");
			sqlBuf.append(" 'P1503010101CN0','P1506010101CN0','P1501010101CN0','P1502010101CN0','P1507010101CN0','P1504010101CN0', ");
			sqlBuf.append(" 'P1508010101CN0')) GROUP BY USER_CODE,MEMBER_ORDER_NO,PRODUCT_NO, province,city,district,RECIPIENT_ADDR,CHECK_DATE,qty)TT");
			log.info("---2.8sql2:"+sqlBuf.toString());
			System.out.println("---2.8sql2:"+sqlBuf.toString());
			return this.getJdbcTemplateReport().queryForList(sqlBuf.toString());
		}
		
		//期别
		if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
			sqlBuf.append(" AND B.CHECK_DATE >= (select min(start_time) from jbd_period where f_year = '"+formatedWeek.trim().substring(0,4)+"' and f_week = "+formatedWeek.trim().substring(5,6)+") ");
			sqlBuf.append(" AND B.CHECK_DATE < (select max(end_time)from jbd_period where f_year = '"+formatedWeek.trim().substring(0,4)+"' and f_week = "+formatedWeek.trim().substring(5,6)+") ");
			sqlBuf.append(" AND B.STATUS = '2' AND B.MEMBER_ORDER_NO = D.ORDER_NO ");
			sqlBuf.append(" AND C.UNI_NO = E.UNI_NO AND C.PRODUCT_NO IN ('P1505010101CN0', ");
			sqlBuf.append(" 'P1503010101CN0','P1506010101CN0','P1501010101CN0','P1502010101CN0','P1507010101CN0','P1504010101CN0', ");
			sqlBuf.append(" 'P1508010101CN0')) GROUP BY USER_CODE,MEMBER_ORDER_NO,PRODUCT_NO, province,city,district,RECIPIENT_ADDR,CHECK_DATE,qty)TT");
			log.info("---2.8sql2:"+sqlBuf.toString());
			System.out.println("---2.8sql2:"+sqlBuf.toString());
			return this.getJdbcTemplateReport().queryForList(sqlBuf.toString());
		}
		
		//年份
		if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
			sqlBuf.append(" AND B.CHECK_DATE >= (select min(start_time) from jbd_period where f_year = '"+formatedYear+"') ");
			sqlBuf.append(" AND B.CHECK_DATE < (select max(end_time) from jbd_period where f_year = '"+formatedYear+"') ");
			sqlBuf.append(" AND B.STATUS = '2' AND B.MEMBER_ORDER_NO = D.ORDER_NO ");
			sqlBuf.append(" AND C.UNI_NO = E.UNI_NO AND C.PRODUCT_NO IN ('P1505010101CN0', ");
			sqlBuf.append(" 'P1503010101CN0','P1506010101CN0','P1501010101CN0','P1502010101CN0','P1507010101CN0','P1504010101CN0', ");
			sqlBuf.append(" 'P1508010101CN0')) GROUP BY USER_CODE,MEMBER_ORDER_NO,PRODUCT_NO, province,city,district,RECIPIENT_ADDR,CHECK_DATE,qty)TT");
			log.info("---2.8sql2:"+sqlBuf.toString());
			System.out.println("---2.8sql2:"+sqlBuf.toString());
			return this.getJdbcTemplateReport().queryForList(sqlBuf.toString());
		}
		
		//月份
		if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
			sqlBuf.append(" AND B.CHECK_DATE >= (select min(start_time) from jbd_period where f_year = '"+formatedMonth.substring(0,4)+"' and f_month = '"+formatedMonth.substring(5,6)+"') ");
			sqlBuf.append(" AND B.CHECK_DATE < (select max(end_time) from jbd_period where f_year = '"+formatedMonth.substring(0,4)+"' and f_month = '"+formatedMonth.substring(5,6)+"') ");
			sqlBuf.append(" AND B.STATUS = '2' AND B.MEMBER_ORDER_NO = D.ORDER_NO ");
			sqlBuf.append(" AND C.UNI_NO = E.UNI_NO AND C.PRODUCT_NO IN ('P1505010101CN0', ");
			sqlBuf.append(" 'P1503010101CN0','P1506010101CN0','P1501010101CN0','P1502010101CN0','P1507010101CN0','P1504010101CN0', ");
			sqlBuf.append(" 'P1508010101CN0')) GROUP BY USER_CODE,MEMBER_ORDER_NO,PRODUCT_NO, province,city,district,RECIPIENT_ADDR,CHECK_DATE,qty)TT");
			log.info("---2.8sql2:"+sqlBuf.toString());
			System.out.println("---2.8sql2:"+sqlBuf.toString());
			return this.getJdbcTemplateReport().queryForList(sqlBuf.toString());
		}
		
		sqlBuf.append(" AND B.STATUS = '2' AND B.MEMBER_ORDER_NO = D.ORDER_NO ");
		sqlBuf.append(" AND C.UNI_NO = E.UNI_NO AND C.PRODUCT_NO IN ('P1505010101CN0', ");
		sqlBuf.append(" 'P1503010101CN0','P1506010101CN0','P1501010101CN0','P1502010101CN0','P1507010101CN0','P1504010101CN0', ");
		sqlBuf.append(" 'P1508010101CN0')) GROUP BY USER_CODE,MEMBER_ORDER_NO,PRODUCT_NO, province,city,district,RECIPIENT_ADDR,CHECK_DATE,qty)TT ");
		log.info("---2.8sql5:"+sqlBuf.toString());
		System.out.println("---2.8sql5:"+sqlBuf.toString());
		return this.getJdbcTemplateReport().queryForList(sqlBuf.toString());
		
		/*//查询语句
		StringBuffer sqlBuf = new StringBuffer(" ");
		sqlBuf.append(" select distinct USER_CODE,USER_NAME,CHECK_DATE,MEMBER_ORDER_NO,PRODUCT_NO,PV_AMT,ADDR, ");
		sqlBuf.append(" SUBSTR(ZCW_MESSAGE, 1, INSTR(ZCW_MESSAGE, '||', 1, 1) - 1) ZCW_CODE, ");
		sqlBuf.append(" SUBSTR(ZCW_MESSAGE,INSTR(ZCW_MESSAGE, '||', 1, 1) + 2) ZCW_NAME ");
		sqlBuf.append(" from (SELECT USER_CODE,(SELECT FIRST_NAME || LAST_NAME FROM JMI_MEMBER ");
		sqlBuf.append(" WHERE USER_CODE = t.USER_CODE) USER_NAME,MEMBER_ORDER_NO,PRODUCT_NO,PV_AMT,(select t.state_province_name from jal_state_province t where t.state_province_id=province) || (select  t.city_name from jal_city t where t.city_id=city) || (select t.district_name from jal_district t where t.DISTRICT_ID=district) || RECIPIENT_ADDR addr, ");
		sqlBuf.append(" CHECK_DATE,fn_get_zcw_message_new(t.USER_CODE) ZCW_MESSAGE FROM (SELECT B.USER_CODE, ");
		sqlBuf.append(" B.MEMBER_ORDER_NO,C.PRODUCT_NO,SUM((G.PV * G.QTY)) PV_AMT,b.province,b.city,b.district, ");
		sqlBuf.append(" B.address RECIPIENT_ADDR,B.CHECK_DATE ");
		sqlBuf.append(" FROM JPO_MEMBER_ORDER           B, ");
		sqlBuf.append(" Jpr_Refund_list            g, ");
		sqlBuf.append(" Jpr_Refund                 f, ");
		sqlBuf.append(" JPM_PRODUCT_SALE_NEW       C, ");
		sqlBuf.append(" JPM_PRODUCT_SALE_TEAM_TYPE E, ");
		sqlBuf.append(" pd_send_info               D ");
		sqlBuf.append(" WHERE B.COMPANY_CODE = 'CN' AND G.PRODUCT_ID = E.PTT_ID AND B.MO_ID = f.MO_ID AND g.RO_NO = f.RO_NO ");
		sqlBuf.append(" AND nvl(B.IS_RETREAT_ORDER, 0) = 1 ");
		
		//--时间
		//起始日期、截止日期
		if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
			sqlBuf.append(" AND B.CHECK_DATE >= to_date('"+startDate+" 00:00:00','yyyy-mm-dd HH24:MI:SS') ");
			sqlBuf.append(" AND B.CHECK_DATE < to_date('"+endDate+" 23:59:59','yyyy-mm-dd HH24:MI:SS') ");
//			sqlBuf.append(" AND B.CHECK_DATE >= DATE '"+startDate+"' ");
//			sqlBuf.append(" AND B.CHECK_DATE < DATE'"+endDate+"' ");
			sqlBuf.append(" AND B.STATUS = '2' AND B.MEMBER_ORDER_NO = D.ORDER_NO AND C.UNI_NO = E.UNI_NO ");
			sqlBuf.append(" AND C.PRODUCT_NO IN ('P1505010101CN0','P1503010101CN0','P1506010101CN0', 'P1501010101CN0', ");
			sqlBuf.append(" 'P1502010101CN0','P1507010101CN0','P1504010101CN0','P1508010101CN0') ");
			sqlBuf.append(" GROUP BY B.USER_CODE,B.MEMBER_ORDER_NO,C.PRODUCT_NO,b.province,b.city,b.district,B.address,B.CHECK_DATE) T) ");
			log.info("---2.8sql1:"+sqlBuf.toString());
			System.out.println("---2.8sql1:"+sqlBuf.toString());
			return this.getJdbcTemplateReport().queryForList(sqlBuf.toString());
		}
		
		//期别
		if (StringUtils.isNotEmpty(formatedWeek) && formatedWeek.trim().length()==6) {
			sqlBuf.append(" AND B.CHECK_DATE >= (select min(start_time) from jbd_period where f_year = '"+formatedWeek.trim().substring(0,4)+"' and f_week = "+formatedWeek.trim().substring(5,6)+") ");
			sqlBuf.append(" AND B.CHECK_DATE < (select max(end_time)from jbd_period where f_year = '"+formatedWeek.trim().substring(0,4)+"' and f_week = "+formatedWeek.trim().substring(5,6)+") ");
			sqlBuf.append(" AND B.STATUS = '2' AND B.MEMBER_ORDER_NO = D.ORDER_NO AND C.UNI_NO = E.UNI_NO ");
			sqlBuf.append(" AND C.PRODUCT_NO IN ('P1505010101CN0','P1503010101CN0','P1506010101CN0', 'P1501010101CN0', ");
			sqlBuf.append(" 'P1502010101CN0','P1507010101CN0','P1504010101CN0','P1508010101CN0') ");
			sqlBuf.append(" GROUP BY B.USER_CODE,B.MEMBER_ORDER_NO,C.PRODUCT_NO,b.province,b.city,b.district, B.address,,B.CHECK_DATE) T) ");
			log.info("---2.8sql2:"+sqlBuf.toString());
			System.out.println("---2.8sql2:"+sqlBuf.toString());
			return this.getJdbcTemplateReport().queryForList(sqlBuf.toString());
		}
		
		//年份
		if (StringUtils.isNotEmpty(formatedYear)) {
			sqlBuf.append(" AND B.CHECK_DATE >= (select min(start_time) from jbd_period where f_year = '"+formatedYear+"') ");
			sqlBuf.append(" AND B.CHECK_DATE < (select max(end_time) from jbd_period where f_year = '"+formatedYear+"') ");
			sqlBuf.append(" AND B.STATUS = '2' AND B.MEMBER_ORDER_NO = D.ORDER_NO AND C.UNI_NO = E.UNI_NO ");
			sqlBuf.append(" AND C.PRODUCT_NO IN ('P1505010101CN0','P1503010101CN0','P1506010101CN0', 'P1501010101CN0', ");
			sqlBuf.append(" 'P1502010101CN0','P1507010101CN0','P1504010101CN0','P1508010101CN0') ");
			sqlBuf.append(" GROUP BY B.USER_CODE,B.MEMBER_ORDER_NO,C.PRODUCT_NO,b.province,b.city,b.district,B.address,B.CHECK_DATE) T) ");
			log.info("---2.8sql3:"+sqlBuf.toString());
			System.out.println("---2.8sql3:"+sqlBuf.toString());
			return this.getJdbcTemplateReport().queryForList(sqlBuf.toString());
		}
		
		//月份
		if (StringUtils.isNotEmpty(formatedMonth)) {
			sqlBuf.append(" AND B.CHECK_DATE >= (select min(start_time) from jbd_period where f_year = '"+formatedMonth.substring(0,4)+"' and f_month = '"+formatedMonth.substring(5,6)+"') ");
			sqlBuf.append(" AND B.CHECK_DATE < (select max(end_time) from jbd_period where f_year = '"+formatedMonth.substring(0,4)+"' and f_month = '"+formatedMonth.substring(5,6)+"') ");
			sqlBuf.append(" AND B.STATUS = '2' AND B.MEMBER_ORDER_NO = D.ORDER_NO AND C.UNI_NO = E.UNI_NO ");
			sqlBuf.append(" AND C.PRODUCT_NO IN ('P1505010101CN0','P1503010101CN0','P1506010101CN0', 'P1501010101CN0', ");
			sqlBuf.append(" 'P1502010101CN0','P1507010101CN0','P1504010101CN0','P1508010101CN0') ");
			sqlBuf.append(" GROUP BY B.USER_CODE,B.MEMBER_ORDER_NO,C.PRODUCT_NO,b.province,b.city,b.district,B.address,B.CHECK_DATE) T) ");
			log.info("---2.8sql4:"+sqlBuf.toString());
			System.out.println("---2.8sql4:"+sqlBuf.toString());
			return this.getJdbcTemplateReport().queryForList(sqlBuf.toString());
		}
		
		sqlBuf.append(" AND B.STATUS = '2' AND B.MEMBER_ORDER_NO = D.ORDER_NO AND C.UNI_NO = E.UNI_NO ");
		sqlBuf.append(" AND C.PRODUCT_NO IN ('P1505010101CN0','P1503010101CN0','P1506010101CN0', 'P1501010101CN0', ");
		sqlBuf.append(" 'P1502010101CN0','P1507010101CN0','P1504010101CN0','P1508010101CN0') ");
		sqlBuf.append(" GROUP BY B.USER_CODE,B.MEMBER_ORDER_NO,C.PRODUCT_NO,b.province,b.city,b.district,d.RECIPIENT_ADDR,B.CHECK_DATE) T) ");
		log.info("---2.8sql5:"+sqlBuf.toString());
		System.out.println("---2.8sql5:"+sqlBuf.toString());
		return this.getJdbcTemplateReport().queryForList(sqlBuf.toString());*/
	}
	
	/**
	 * 2.9网络费物流费
	 * @param crm
	 * @return
	 */
	public List getJponetfeeByCrm(CommonRecord crm) {
		String stDate = "";
		String enDate = "";
		String year = null;
		String week = null;
		String period = null;
		String startDate = crm.getString("startDate","");
		String endDate = crm.getString("endDate","");
		String fyearweek = crm.getString("fyearweek","");
		String wyear = crm.getString("wyear","");
		String wweek = crm.getString("wweek","");
		
			if(StringUtils.isNotEmpty(startDate)){
				stDate = startDate;
			}
			if(StringUtils.isNotEmpty(endDate)){
				enDate = endDate + " 23:59:59";
			}
		
		if(StringUtils.isNotEmpty(wyear)){
			year = wyear;
		}
		if(StringUtils.isNotEmpty(wweek)){
			week = wweek;
		}
		
		if(StringUtils.isNotEmpty(fyearweek)){
			period = fyearweek;
		}
		
		//查询语句
		String sql = "select * from table(FN_GET_NET_CXFEE(to_date('" + stDate + "','yyyy-mm-dd HH24:MI:SS')," +
				"to_date('" + enDate + "','yyyy-mm-dd HH24:MI:SS')," +
						" "+ period+","+ year +","+ week +"))";
		log.info("---2.9sql:"+sql);
		System.out.println("---2.9sql:"+sql);
		return this.getJdbcTemplateReport().queryForList(sql);
	}
	
	/**
	 * 获取业体领导人安置网体首单5500PV及以上业绩
	 * @param crm
	 * @return
	 */
	public List getShiyeTiLingDaoShouDanByCrm(CommonRecord crm) {
		String stDate = "";
		String enDate = "";
		String year = null;
		String week = null;
		String period = null;
		String startDate = crm.getString("startDate","");
		String endDate = crm.getString("endDate","");
		String fyearweek = crm.getString("fyearweek","");
		String wyear = crm.getString("wyear","");
		String wweek = crm.getString("wweek","");
		
			if(StringUtils.isNotEmpty(startDate)){
				stDate = startDate;;
			}
			if(StringUtils.isNotEmpty(endDate)){
				enDate = endDate;
			}
		
		if(StringUtils.isNotEmpty(wyear)){
			year = wyear;
		}
		if(StringUtils.isNotEmpty(wweek)){
			week = wweek;
		}
		
		if(StringUtils.isNotEmpty(fyearweek)){
			period = fyearweek;
		}
		
		//查询语句
		String sql = "select * from table(FN_GET_SYT_SDYJ(to_date('" + stDate + "','yyyy-mm-dd HH24:MI:SS')," +
				"to_date('" + enDate + "','yyyy-mm-dd HH24:MI:SS')," +
						" "+ period+","+ year +","+ week +"))";
		return this.getJdbcTemplateReport().queryForList(sql);
	}
	
	
	/**
	 * 根据订单号查询该订单下所有的发货单
	 * @author gw 2015-01-27
	 * @param memberOrderNo
	 * @return list
	 */
	public List<PdSendInfo> getPdSendInfoList(String memberOrderNo) {
		String hql = " from PdSendInfo pdSendInfo where pdSendInfo.orderNo = '"+memberOrderNo+"' ";
		return this.findObjectsByHqlQuery(hql);
	}
	
	/**
	 * 根据订单号和商品编号查询发货单号
	 * @author gw 2015-02-01
	 * @param memberOrderNo
	 * @param productNo
	 * @return pdSendInfo
	 */
	public PdSendInfo getPdSendInfoForOrderNoAndProductNo(String memberOrderNo,String productNo) {
		StringBuffer hql = new StringBuffer(" from PdSendInfo pdSendInfo where pdSendInfo.orderNo = '"+memberOrderNo+"' and pdSendInfo.pdSendInfoDetails.productNo='"+productNo+"' ");
		List<PdSendInfo> list = this.findObjectsByHqlQuery(hql.toString());
		if(null!=list){
			if(list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}
	
	/**
	 * 根据发货单查询发货单号
	 * @author gw 2015-04-15
	 * @param siNo
	 * @return pdSendInfo
	 */
	public PdSendInfo getPdSendInfoForSiNo(String siNo) {
		StringBuffer hql = new StringBuffer(" from PdSendInfo pdSendInfo where pdSendInfo.siNo = '"+siNo+"'  ");
		List<PdSendInfo> list = this.findObjectsByHqlQuery(hql.toString());
		if(null!=list){
			if(list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}
	
	/**
	 * 获取数据库中的pdSendInfo发货单对象
	 * @author fu  2015-09-16
	 * @param pdSendInfo
	 * @return pdSendInfo
	 */
	public PdSendInfo getPdSendInfoDBA(PdSendInfo pdSendInfo){
		String sql = " select warehouse_no,recipient_name,recipient_zip,recipient_ca_no,city," +
				" recipient_addr,recipient_phone,recipient_mobile,ship_type,sh_no,order_flag," +
				" check_remark,bar_code,check_usr_code,cod_flag,tracking_no,remark,hurry_flag,shipment_identification_number " +
				" from pd_send_info a where a.si_no = '"+pdSendInfo.getSiNo()+"'";
		List list = this.jdbcTemplate.queryForList(sql);
		if(null!=list && list.size()>0){
			Map map = (Map) list.get(0);
			pdSendInfo.setWarehouseNo((String)map.get("warehouse_no"));
			pdSendInfo.setRecipientName((String)map.get("recipient_name"));
			pdSendInfo.setRecipientZip((String)map.get("recipient_zip"));
			pdSendInfo.setRecipientCaNo((String)map.get("recipient_ca_no"));
			pdSendInfo.setCity((String)map.get("city"));
			pdSendInfo.setRecipientAddr((String)map.get("recipient_addr"));
			pdSendInfo.setRecipientPhone((String)map.get("recipient_phone"));
			pdSendInfo.setRecipientMobile((String)map.get("recipient_mobile"));
			pdSendInfo.setShipType((String)map.get("ship_type"));
			pdSendInfo.setShNo((String)map.get("sh_no"));
			pdSendInfo.setCanDo("");
			BigDecimal a = (BigDecimal)map.get("order_flag");
			pdSendInfo.setOrderFlag(Integer.parseInt(a.toString()));
			
			pdSendInfo.setCodFlag((String)map.get("cod_flag"));
			pdSendInfo.setCheckUsrCode((String)map.get("check_usr_code"));
			pdSendInfo.setBarCode((String)map.get("bar_code"));
			pdSendInfo.setCheckRemark((String)map.get("check_remark"));
			pdSendInfo.setTrackingNo((String)map.get("tracking_no"));
			pdSendInfo.setRemark((String)map.get("remark"));
			pdSendInfo.setHurryFlag((String)map.get("hurry_flag"));
			
			pdSendInfo.setShipmentIdentificationNumber((String)map.get("shipment_identification_number"));//用这个值，在前台判断发货单有没有DO单传过来。
		}
		return pdSendInfo;
	}
	
	 /**
	  * 防止LO单接口和发货单发货确认菜单扣两次库存,所以先去数据库中查询发货单的状态
	  * @author fu 2015-09-20
	  * @param pdSendInfo
	  * @return boolean
	  */
	public boolean getPdSendInfoOrderFlagDBA(PdSendInfo pdSendInfo){
		String sql = " select order_flag from pd_send_info a where a.si_no = '"+pdSendInfo.getSiNo()+"'";
        List list = this.jdbcTemplate.queryForList(sql);
		if(null!=list && list.size()>0){
			    Map map = (Map) list.get(0);
			    BigDecimal a = (BigDecimal)map.get("order_flag");
			    if((null!=a) && "2".equals(a.toString())){
			    	return true;
			    }
			}
		return false;
	   }
	
	/**
	 * 确认正常发货
	 * @author fu 2015-10-22
	 * param:siNo 发货单号
	 * return: 返回修改状态  true:成功  false:失败
	 */
	public boolean updateShipType(String siNo){
        boolean returnS = false;
        //modify by fu 2016-07-12 确认正常发货的同时，将暂停发货原因的字段清空
		String sql = " update pd_send_info set ship_type='0',suspend_shipment=''  where si_no='"+siNo+"' ";
		int i = this.getJdbcTemplate().update(sql);
		if(i>=0){
			returnS = true;
		}
		return returnS;
	}
	
	/**
	 * 加急发货
	 * @author fu 2015-10-19
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
	
	/**
	 * 发货退回订单入库生成发货单之前首先判断该单有没有发货单生成
	 * @author modify fu 2015-12-26 
	 * param:siNo 发货单号
	 * return: 返回修改状态  true:发货退回单之前没有生成发货单  false:发货退回单已经生成了发货单
	 */
	public boolean getPdSendInfoOrderForOrderNo(String rpNo){
		boolean result = true;
		if(!StringUtil.isEmpty(rpNo)){
			String sql = "select * from pd_send_info where order_no = '"+rpNo+"'";
			List list = this.findObjectsBySQL(sql);
				if(null!=list){
					if(list.size()>0){
						result = false;
					}
				}
		}
		return result;
	}

	/**
	 * @author fu 2016-06-24 将WMS那边已撤单的发货单设为发货作废
	 * @param siNo
	 * @return 
	 */
	public void reSetPdSendInfoShipTypeFour(String siNo) {
		String sql = "update pd_send_info set ship_type=4 where si_no = '"+siNo+"' ";
		this.jdbcTemplate.execute(sql);
	}
	
	/**
	 * 发货退回订单入库是否只生成一张发货单
	 * @author modify fu 2015-12-26 
	 * param:siNo 发货单号
	 * return: 返回修改状态  true:发货退回单之前有且仅有一张发货单  false:发货退回单已经生成多张发货单
	 *//*
	public boolean getPdSendInfoForOrderNo(String rpNo){
		boolean result = false;
		if(!StringUtil.isEmpty(rpNo)){
			String sql = "select * from pd_send_info where order_no = '"+rpNo+"'";
			List list = this.findObjectsBySQL(sql);
				if(null!=list){
					if(list.size()==1){
						result = true;
					}
				}
		}
		return result;
	}*/

	/**
	 * @author WuCF 2016-09-05 获得需要重发的日志的logId集合
	 * @param siNo
	 * @return 
	 */
	public List<Map<String,Object>> geetJocsInterfaceRetranLogids(){
		List<Map<String,Object>> returnList = new ArrayList<Map<String,Object>>();
		String sql = "select LOG_ID from jocs_interface_retransmission where log_type='0' and retran_status='0' and method!='ship.search' and send_time>=sysdate-7 order by log_id";
		returnList = (List<Map<String,Object>>)this.jdbcTemplate.queryForList(sql);
		if(returnList==null){
			returnList = new ArrayList<Map<String,Object>>();
		}
		return returnList;
	}
	/**
	 * @author fu 2016-09-06 获取发货单的发货状态
	 * @param siNo
	 * @return orderFlag
	 */
	public String getPdSendInfoOrderFlag(String siNo){
		String sql = " select order_flag from pd_send_info a where a.si_no = '"+siNo+"'";
        List list = this.jdbcTemplate.queryForList(sql);
		if(null!=list && list.size()>0){
			    Map map = (Map) list.get(0);
			    BigDecimal a = (BigDecimal)map.get("order_flag");
			    if(null!=a){
			    	return a.toString();
			    }
		}
		return null;
	}
	
	/**
	 * @author fu 2016-09-06 更新发货单的条形码的信息
	 * @param siNo 发货单号
	 * @param barCode 条形码
	 * @return 
	 */
	public void reSetPdSendInfoBarCode(String siNo,String barCode){
		String sql = "update pd_send_info set bar_code='"+barCode+"' where si_no = '"+siNo+"' ";
		this.jdbcTemplate.execute(sql);
	}
	
	
	// added for getPdSendInfosByCrm2  by lihao,发货单管理-发货报表只允许导出“已审核”-“正常发货”的单据。
	
	public List getPdSendInfosReport(CommonRecord crm, Pager pager) {
		log.info("在方法getPdSendInfosByCrm2中开始运行");
		String hql = " from PdSendInfo pdSendInfo where 1=1 "; 
		try{
//		select new PdSendInfo(siNo,customer.userCode,customer.userName,recipientName,recipientCaNo,city,recipientAddr,recipientZip,recipientPhone,email,checkTime,orderNo,recipientMobile,codFlag,hurryFlag,createUsrCode,createTime,okUsrCode,okTime,warehouseNo,shNo,orderFlag,trackingNo,barCode,pdSendInfoDetails) 
		/**
		 * ---------------
		 */
		
		String cxFlag = crm.getString("cxFlag",""); 
		if("n".equals(cxFlag)){
			hql += " and 1=2 "; 
		}else{			
			//批量打印的发货单号
			String siNoTextarea = crm.getString("siNoTextarea", "");
			if(siNoTextarea!=null && !"".equals(siNoTextarea.trim())){
				siNoTextarea = siNoTextarea.replace("\r\n", ",");
				String[] strs = siNoTextarea.split(",");
				StringBuffer strBuf = new StringBuffer(" (");
				for(int i=0;i<strs.length;i++){
					strBuf.append("'");
					strBuf.append(strs[i]);
					strBuf.append("'");
					if(i<strs.length-1){
						strBuf.append(",");
					}
				}
				strBuf.append(") ");
				hql += " and pdSendInfo.siNo in"+strBuf.toString()+" ";
			}else{	
				log.info("在方法getPdSendInfosByCrm2中开始运行，hql为"+hql);
				String exportFlag = crm.getString("exportFlag", "");
				if("1".equals(exportFlag)){
					hql += " and pdSendInfo.siNo in( select  siNo from PdSendInfoDetail psd where psd.productNo='N05200800101CN0' )";
				}else if("0".equals(exportFlag)){
					hql += " and pdSendInfo.siNo in( select distinct siNo from PdSendInfoDetail psd where psd.productNo <> 'N05200800101CN0' )";
		
				}
				
				String hurryFlag = crm.getString("hurryFlag", "");
				if (StringUtils.isNotEmpty(hurryFlag)) {
					if("1".equals(hurryFlag)){
						hql += " and pdSendInfo.hurryFlag ='1' ";
					}else if("0".equals(hurryFlag)){
						hql += " and (pdSendInfo.hurryFlag ='0' or pdSendInfo.hurryFlag is null)";
			
					}
				}
				
				//modify by fu 2016-1-19 是否已备货
				String whetherStock = crm.getString("whetherStock", "");
				if (StringUtils.isNotEmpty(whetherStock)) {
					if("Y".equals(whetherStock)){
						hql += " and pdSendInfo.whetherStock ='Y' ";
					}else if("N".equals(whetherStock)){
						hql += " and (pdSendInfo.whetherStock ='N' or pdSendInfo.whetherStock is null)";
			
					}
				}
				log.info("在方法getPdSendInfosByCrm2中开始运行，hql二为"+hql);
				String productNo = crm.getString("productNo", "");
				if (StringUtils.isNotEmpty(productNo)) {
					hql += " and pdSendInfo.siNo in( select  siNo from PdSendInfoDetail psd where psd.productNo='"+productNo+"' )";
				}
				//Modify By WuCF 20130701 添加关键字功能查询
				String keyword = crm.getString("keyword", "");
				if (StringUtils.isNotEmpty(keyword)) { 
					keyword = keyword.trim(); 
					hql += " and (pdSendInfo.recipientName like'"+keyword+"' " +
						   " or pdSendInfo.customer.userName like'"+keyword+"' "+
						   " or pdSendInfo.recipientAddr like'"+keyword+"' "+
						   " or pdSendInfo.recipientMobile like'"+keyword+"' "+
						   " or pdSendInfo.recipientPhone like'"+keyword+"') ";
				} 
				
				
				String codFlag = crm.getString("codFlag", "");
				if (StringUtils.isNotEmpty(codFlag)) {
					if("1".equals(codFlag)){
						hql += " and pdSendInfo.codFlag = '" + codFlag + "'";
					}else{
						hql += " and (pdSendInfo.codFlag = '0' or pdSendInfo.codFlag is null)";
					}
					
				}
				
				String price = crm.getString("price", "");
				if(StringUtils.isNotBlank(price)){
					hql += " and pdSendInfo.siNo in( select distinct siNo from PdSendInfoDetail psd where psd.price="+price+" )";
				}
				
				String defaultWarehouse = crm.getString("defaultWarehouse", "");
				if (StringUtils.isNotEmpty(defaultWarehouse)) {
					hql += " and pdSendInfo.warehouseNo in(" + defaultWarehouse + ")";
				}
		
				
				String orderType = crm.getString("orderType", "");
				if (StringUtils.isNotEmpty(orderType)) {
					hql += " and pdSendInfo.orderType = '" + orderType + "'";
				}
				
				//Modify By WuCF 20140711条形码查询
				String barCode = crm.getString("barCode", "");
				if (StringUtils.isNotEmpty(barCode)) {
					hql += " and pdSendInfo.barCode like '%" + barCode.trim() + "%'";
				}
		
				log.info("在方法getPdSendInfosByCrm2中开始运行，hql三为"+hql);

				String shipType = crm.getString("shipType", "");
				//------pt		
				/*
				if (StringUtils.isNotEmpty(shipType)) {
					if((!StringUtil.isEmpty(shipType))&&("shipTypeShippingRepor".equals(shipType))){
						hql += " and ( pdSendInfo.shipType is null or pdSendInfo.shipType = 0 ) ";
					}else{
						hql += " and pdSendInfo.shipType = '" + shipType + "'";
					}
				} */
				
				//发货单管理-发货报表只允许导出“已审核”-“正常发货”的单据。正常发货 ，ship_type为0或者null
				hql += " and ( pdSendInfo.shipType is null or pdSendInfo.shipType = 0 ) ";
				
				String trackingNo = crm.getString("trackingNo", "");
				if (StringUtils.isNotEmpty(trackingNo)) {
					hql += " and pdSendInfo.trackingNo like '%" + trackingNo + "%'";
				}
				
				String orderNo = crm.getString("orderNo", "");
				if (StringUtils.isNotEmpty(orderNo)) {
					hql += " and pdSendInfo.orderNo = '" + orderNo + "'";
				}
		
				String warehouseNo = crm.getString("warehouseNo", "");
				if (StringUtils.isNotEmpty(warehouseNo)) {
					hql += " and pdSendInfo.warehouseNo in('" + warehouseNo.replace(",", "','") + "') ";
				} 
		
				String orderFlagDefault = crm.getString("orderFlagDefault", "");
				if (StringUtils.isNotEmpty(orderFlagDefault)) {
					hql += " and pdSendInfo.orderFlag in(" + orderFlagDefault + ")";
				}
				String companyCode = crm.getString("companyCode", "");
				if (StringUtils.isNotEmpty(companyCode)) {
					hql += " and pdSendInfo.companyCode='" + companyCode + "'";
				}
				String siNo = crm.getString("siNo", "");
				if (StringUtils.isNotEmpty(siNo)) {
		//			hql += " and pdSendInfo.siNo='" + siNo + "'";//Modify By WuCF 20130509 
					hql += " and pdSendInfo.siNo in('" + siNo.replace(",", "','") + "')";
				}
		
				String recipientCaNo = crm.getString("recipientCaNo", "");
				if (StringUtils.isNotEmpty(recipientCaNo)) {
					hql += " and pdSendInfo.recipientCaNo ='" + recipientCaNo + "'";
				}
		
				String customCode = crm.getString("customCode", "");
				if (StringUtils.isNotEmpty(customCode)) {
					hql += " and pdSendInfo.customer.userCode='" + customCode + "'";
				}
				
				//Modify By WuCF 20131114 仓库权限管理修改，查询时过滤 
//				String userCodeT = crm.getString("userCodeT", ""); 
//				if (StringUtils.isNotEmpty(userCodeT) && !"root".equals(userCodeT)) {
//					hql += " and exists (select 1 from PdWarehouseUser pdWarehouseUser where pdSendInfo.warehouseNo=pdWarehouseUser.warehouseNo and pdWarehouseUser.userCode='"+userCodeT+"') ";
//				}
		
				String createUsrCode = crm.getString("createUsrCode", "");
				if (StringUtils.isNotEmpty(createUsrCode)) {
					hql += " and pdSendInfo.createUsrCode='" + createUsrCode + "'";
				}
		
				String checkUsrCode = crm.getString("checkUsrCode", "");
				String hasCheckUsrCodeBlank = crm
						.getString("hasCheckUsrCodeBlank", "0");
				if (StringUtils.isNotEmpty(checkUsrCode)) {
					if (hasCheckUsrCodeBlank.equals("1")) {
		
						hql += " and (pdSendInfo.checkUsrCode ='" + checkUsrCode
								+ "' or pdSendInfo.checkUsrCode is null)";
					} else {
						hql += " and pdSendInfo.checkUsrCode='" + checkUsrCode + "'";
					}
		
					// hql +=" and pdSendInfo.checkUsrCode='"+checkUsrCode+"'";
				}
		
				String okUsrCode = crm.getString("okUsrCode", "");
				if (StringUtils.isNotEmpty(okUsrCode)) {
					if (hasCheckUsrCodeBlank.equals("1")) {
		
						hql += " and (pdSendInfo.okUsrCode ='" + okUsrCode
								+ "' or pdSendInfo.okUsrCode is null)";
					} else {
						hql += " and pdSendInfo.okUsrCode='" + okUsrCode + "'";
					}
		
					// hql +=" and pdSendInfo.checkUsrCode='"+checkUsrCode+"'";
				}
				/*
				 * String checkStatus = crm.getString("checkStatus","");
				 * if(StringUtils.isNotEmpty(checkStatus)){ hql
				 * +=" and pdSendInfo.checkStatus='"+checkStatus+"'"; }
				 * 
				 * String okStatus = crm.getString("okStatus","");
				 * if(StringUtils.isNotEmpty(okStatus)){ hql
				 * +=" and pdSendInfo.okStatus='"+okStatus+"'"; }
				 */
				log.info("在方法getPdSendInfosByCrm2中开始运行，hql四为"+hql);

				String orderFlag = crm.getString("orderFlag", "");
				/*
				if (StringUtils.isNotEmpty(orderFlag)) {
					hql += " and pdSendInfo.orderFlag in(" + orderFlag + ")";
				}*/
				//发货单管理-发货报表只允许导出“已审核”-“正常发货”的单据。order_flag为1，发货单已审核
				hql += " and pdSendInfo.orderFlag = 1 ";
				
				String subOrderType = crm.getString("subOrderType", "");
				if (StringUtils.isNotEmpty(subOrderType)) {
					hql += " and pdSendInfo.subOrderType ='" + subOrderType + "'";
				}
				
				String shNo = crm.getString("shNo", "");
				if (StringUtils.isNotEmpty(shNo)) {
					hql += " and pdSendInfo.shNo ='" + shNo + "'";
				}
		
				String createTimeStart = crm.getString("createTimeStart", "");
				String createTimeEnd = crm.getString("createTimeEnd", "");
				if (StringUtils.isNotEmpty(createTimeStart)) {
					hql += " and pdSendInfo.createTime >=to_date('" + createTimeStart
							+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
				}
				if (StringUtils.isNotEmpty(createTimeEnd)) {
					hql += " and pdSendInfo.createTime <=to_date('" + createTimeEnd
							+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
				}
				
				//Modify By WuCF 20150906
				//是否有审核人
				String hasCheckUser = crm.getString("hasCheckUser", "");
				if(StringUtils.isNotEmpty(hasCheckUser)) {
					if("1".equals(hasCheckUser)){
						hql += " and pdSendInfo.checkUsrCode is not null ";
					}else if("0".equals(hasCheckUser)){
						hql += " and pdSendInfo.checkUsrCode is null ";
					}
				}
				
				//审核者
				if(StringUtils.isNotEmpty(checkUsrCode)) {
					hql += " and pdSendInfo.checkUsrCode='"+checkUsrCode.trim()+"' ";
				}
				
				
				//审核时间		
				String checkTimeStart = crm.getString("checkTimeStart", "");
				String checkTimeEnd = crm.getString("checkTimeEnd", "");
				if (StringUtils.isNotEmpty(checkTimeStart)) {
					hql += " and pdSendInfo.checkTime >=to_date('" + checkTimeStart
							+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
				}
				if (StringUtils.isNotEmpty(checkTimeEnd)) {
					hql += " and pdSendInfo.checkTime <=to_date('" + checkTimeEnd
							+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
				}
		
				String okTimeStart = crm.getString("okTimeStart", "");
				String okTimeEnd = crm.getString("okTimeEnd", "");
				if (StringUtils.isNotEmpty(okTimeStart)) {
					hql += " and pdSendInfo.okTime >=to_date('" + okTimeStart
							+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
				}
				if (StringUtils.isNotEmpty(okTimeEnd)) {
					hql += " and pdSendInfo.okTime <=to_date('" + okTimeEnd
							+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
				}
				log.info("在方法getPdSendInfosByCrm2中开始运行，hql五为"+hql);

				String recipientName = crm.getString("recipientName", "");
				if (StringUtils.isNotEmpty(recipientName)) {
					hql += " and pdSendInfo.recipientName like '%" + recipientName
							+ "%'";
				}
				
				//modify by fu 2016-02-25 接口物流单号
				String logisticsDo = crm.getString("logisticsDo","" );
				if (StringUtils.isNotEmpty(logisticsDo)) {
					hql += " and pdSendInfo.logisticsDo like '%" + logisticsDo
							+ "%'";
				}
			}
		}
		}catch(Exception e){
			log.info("在getPdSendInfosByCrm2方法中运行异常"+e.toString());
			e.printStackTrace();
		}
		log.info("============getPdSendInfosByCrm:"+hql);
		if (pager == null) {
			hql+="  order by pdSendInfo.siNo";
			
			//Modify By WuCF 20140514  查询记录条数，控制记录条数
			String listNum = crm.getString("listNum");//最大限额数
			log.info("在方法getPdSendInfosByCrm2中开始运行，hql六为"+hql);

			// 去除结束
			int start = hql.toLowerCase().indexOf("from ");			
			String sqlCount = "select count(*) from " + hql.substring(start + 4);
			Object obj = this.getSession().createQuery(sqlCount).uniqueResult().toString();
			Integer num = Integer.parseInt(String.valueOf(obj));
			log.info("===发货报表-执行报表，导出数："+num);
			if(listNum!=null && num>Integer.parseInt(listNum)){//如果超过限额，则返回
				List list = new ArrayList<String>();
				list.add(1);
				return list;
			}
			log.info("在方法getPdSendInfosByCrm2中开始运行，hql七为"+hql);

			return this.getHibernateTemplate().find(hql);
//			return this.findObjectsByHqlQuery(hql, pager);
		}
		// Modify By WuCF 20130509
		if(pager!=null && pager.getLimit()!=null && pager.getLimit().getSort()!=null){
			if (!pager.getLimit().getSort().isSorted()) {
				// sort
				hql += " order by pdSendInfo.siNo desc";
			} else {
				hql += " ORDER BY " + pager.getLimit().getSort().getProperty()
						+ " " + pager.getLimit().getSort().getSortOrder();
			}
		} 
		return this.findObjectsByHqlQuery(hql, pager);
	}

	
}
