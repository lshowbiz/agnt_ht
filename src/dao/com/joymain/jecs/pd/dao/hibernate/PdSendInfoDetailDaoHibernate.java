package com.joymain.jecs.pd.dao.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.dao.PdSendInfoDetailDao;
import com.joymain.jecs.pd.model.PdSendInfoDetail;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class PdSendInfoDetailDaoHibernate extends BaseDaoHibernate implements
		PdSendInfoDetailDao {

	
	public List getShipDetailsByTown(CommonRecord crm){
		String sql = "select state.state_province_name,city.city_name,district.district_name,town.town_name,ps.product_no,ps.product_name,sum(sid.qty) qty "+
		"  from pd_send_info        si,                                    "+
		"       pd_send_info_detail sid,                                   "+
		"       jpm_product_sale_new     ps,                                    "+
		"       jal_state_province  state,                                 "+
		"       jal_city            city,                                  "+
		"       jal_district        district,                              "+
		"       jal_town            town                                   "+
	
		" where si.si_no = sid.si_no                                       "+
		"   and si.company_code = ps.company_code                          "+
		"   and si.recipient_ca_no = state.state_province_id               "+
		"   and si.city=city.city_id and si.district=district.district_id  "+
		"   and sid.product_no=ps.product_no "+
		"   and si.town_id = town.town_id                                  ";
		String recipientCaNo = crm.getString("recipientCaNo", "");
		if (StringUtils.isNotEmpty(recipientCaNo)) {
			sql += " and si.recipient_Ca_No ='" + recipientCaNo + "'";
		}
		String productNo = crm.getString("productNo", "");
		if (StringUtils.isNotEmpty(productNo)) {
			sql += " and sid.product_no='" + productNo + "'";
		}
		String companyCode = crm.getString("companyCode", "");
		if (StringUtils.isNotEmpty(companyCode)) {
			sql += " and si.COMPANY_CODE='" + companyCode + "'";
		}
		String shNo = crm.getString("shNo", "");
		if (StringUtils.isNotEmpty(shNo)) {
			sql += " and si.sh_No='" + shNo + "'";
		}
		String orderType = crm.getString("orderType", "");
		if (StringUtils.isNotEmpty(orderType)) {
			sql += " and si.order_Type = '" + orderType + "'";
		}
		
		String subOrderType = crm.getString("subOrderType", "");
		if (StringUtils.isNotEmpty(subOrderType)) {
			sql += " and si.sub_Order_Type = '" + subOrderType + "'";
		}
		
		String warehouseNo = crm.getString("warehouseNo", "");
		if (StringUtils.isNotEmpty(warehouseNo)) {
//			sql += " and si.warehouse_No='" + warehouseNo + "'";//Modify By WuCF 20130510
			sql += " and si.warehouse_No in('" + warehouseNo.replace(",", "','") + "') ";
		} 
		
		String createTimeStart = crm.getString("createTimeStart", "");
		String createTimeEnd = crm.getString("createTimeEnd", "");
		if (StringUtils.isNotEmpty(createTimeStart)) {
			sql += " and si.create_Time >=to_date('" + createTimeStart
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		if (StringUtils.isNotEmpty(createTimeEnd)) {
			sql += " and si.create_Time <=to_date('" + createTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}

		String checkTimeStart = crm.getString("checkTimeStart", "");
		String checkTimeEnd = crm.getString("checkTimeEnd", "");
		if (StringUtils.isNotEmpty(checkTimeStart)) {
			sql += " and si.check_Time >=to_date('" + checkTimeStart
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		if (StringUtils.isNotEmpty(checkTimeEnd)) {
			sql += " and si.check_Time <=to_date('" + checkTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}
		// 确认时间
		String okTimeBegain = crm.getString("okTimeStart", "");
		if (StringUtils.isNotEmpty(okTimeBegain)) {
			sql += " and si.ok_Time >=to_date('" + okTimeBegain
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		String okTimeEnd = crm.getString("okTimeEnd", "");
		if (StringUtils.isNotEmpty(okTimeEnd)) {
			sql += " and si.ok_Time <=to_date('" + okTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}

		String orderFlag = crm.getString("orderFlag", "");
		if (StringUtils.isNotEmpty(orderFlag)) {
			sql += " and si.order_Flag in (" + orderFlag + ") ";
		}
		
		sql +=" group by state.state_province_name,city.city_name,district.district_name,town.town_name,ps.product_no,ps.product_name";
		log.info("getShipDetailsByTown="+sql);
		return this.getJdbcTemplate().queryForList(sql);
		
	}
	public List getShipDetailsByState(CommonRecord crm) {
		// TODO Auto-generated method stub
		String sql ="select al.state_province_name,                     "+
		"       pms.product_no,                             "+
		"       pms.product_name,                           "+
		"       sum(sid.qty) qty                            "+
		"  from pd_send_info        si,                     "+
		"       pd_send_info_detail sid,                    "+
		"       jpm_product_sale_new    pms,                    "+
		"       jal_state_province  al                      "+
		" where si.si_no = sid.si_no                        "+
		"   and si.recipient_ca_no = al.state_province_id   "+
		"   and sid.product_no = pms.product_no             "+
		"   and si.company_code = pms.company_code          ";
		
		String recipientCaNo = crm.getString("recipientCaNo", "");
		if (StringUtils.isNotEmpty(recipientCaNo)) {
			sql += " and si.recipient_Ca_No ='" + recipientCaNo + "'";
		}
		
		//Modify By WuCF 20140604商品编码查询条件
		String productNo = crm.getString("productNo", "");
		if (StringUtils.isNotEmpty(productNo)) {
			sql += " and sid.product_no='" + productNo.trim() + "'";
		}
		String companyCode = crm.getString("companyCode", "");
		if (StringUtils.isNotEmpty(companyCode)) {
			sql += " and si.COMPANY_CODE='" + companyCode + "'";
		}
		String shNo = crm.getString("shNo", "");
		if (StringUtils.isNotEmpty(shNo)) {
			sql += " and si.sh_No='" + shNo + "'";
		}
		String orderType = crm.getString("orderType", "");
		if (StringUtils.isNotEmpty(orderType)) {
			sql += " and si.order_Type = '" + orderType + "'";
		}
		
		String subOrderType = crm.getString("subOrderType", "");
		if (StringUtils.isNotEmpty(subOrderType)) {
			sql += " and si.sub_Order_Type = '" + subOrderType + "'";
		}
		
		String warehouseNo = crm.getString("warehouseNo", "");
		if (StringUtils.isNotEmpty(warehouseNo)) {
//			sql += " and si.warehouse_No='" + warehouseNo + "'";//Modify By WuCF 20130510
			sql += " and si.warehouse_No in('" + warehouseNo.replace(",", "','") + "') ";
		}
		
		String createTimeStart = crm.getString("createTimeStart", "");
		String createTimeEnd = crm.getString("createTimeEnd", "");
		if (StringUtils.isNotEmpty(createTimeStart)) {
			sql += " and si.create_Time >=to_date('" + createTimeStart
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		if (StringUtils.isNotEmpty(createTimeEnd)) {
			sql += " and si.create_Time <=to_date('" + createTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}

		String checkTimeStart = crm.getString("checkTimeStart", "");
		String checkTimeEnd = crm.getString("checkTimeEnd", "");
		if (StringUtils.isNotEmpty(checkTimeStart)) {
			sql += " and si.check_Time >=to_date('" + checkTimeStart
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		if (StringUtils.isNotEmpty(checkTimeEnd)) {
			sql += " and si.check_Time <=to_date('" + checkTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}
		// 确认时间
		String okTimeBegain = crm.getString("okTimeStart", "");
		if (StringUtils.isNotEmpty(okTimeBegain)) {
			sql += " and si.ok_Time >=to_date('" + okTimeBegain
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		String okTimeEnd = crm.getString("okTimeEnd", "");
		if (StringUtils.isNotEmpty(okTimeEnd)) {
			sql += " and si.ok_Time <=to_date('" + okTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}

		String orderFlag = crm.getString("orderFlag", "");
		if (StringUtils.isNotEmpty(orderFlag)) {
			sql += " and si.order_Flag in (" + orderFlag + ") ";
		}
		
		//modify by fu 2016-09-14 发货单报表优化  添加查询条件------begin
				//modify by fu 2016-09-14 新加查询条件：物流跟踪号
				String trackingNo = crm.getString("trackingNo", "");
				if (StringUtils.isNotEmpty(trackingNo)) {
					sql += " and si.tracking_No like '%" + trackingNo + "%'";
				}
				
				//modify by fu 2016-09-14 新加查询条件： 接口物流单号
				String logisticsDo = crm.getString("logisticsDo","" );
				if (StringUtils.isNotEmpty(logisticsDo)) {
					sql += " and si.logistics_do like '%" + logisticsDo+ "%'";
				}

				//modify by fu 2016-09-14 新加查询条件： 使用积分支付
		        String codFlag = crm.getString("codFlag", "");
				if (StringUtils.isNotEmpty(codFlag)) {
					if("1".equals(codFlag)){
						sql += " and si.cod_Flag = '" + codFlag + "'";
					}else{
						sql += " and (si.cod_Flag = '0' or si.cod_Flag is null)";
					}
				}

				//modify by fu 2016-09-14 新加查询条件：加急发货
		        String hurryFlag = crm.getString("hurryFlag", "");
				if (StringUtils.isNotEmpty(hurryFlag)) {
					if("1".equals(hurryFlag)){
						sql += " and si.hurry_flag = '" + hurryFlag + "'";
					}else{
						sql += " and (si.hurry_flag = '0' or si.hurry_flag is null)";
					}
				}

		        //modify by fu 2016-09-14 新加查询条件 是否已备货
				String whetherStock = crm.getString("whetherStock","" );
				if (StringUtils.isNotEmpty(whetherStock)) {
					if("Y".equals(whetherStock)){
						sql += " and si.whether_stock = '"+whetherStock+"'";
					}else{
						sql += " and ( si.whether_stock = '"+whetherStock+"' or  si.whether_stock is null )";	
				    }
				}

				//modify by fu 2016-09-14 新加查询条件 发货方式
		        String shipType = crm.getString("shipType", "");
				if (StringUtils.isNotEmpty(shipType)) {
					sql += " and si.ship_Type='" + shipType + "'";
				}

		        //modify by fu 2016-09-14 新加查询条件   添加关键字功能查询
				String keyword = crm.getString("keyword", "");
				if (StringUtils.isNotEmpty(keyword)) {
					keyword = keyword.trim();
					sql += " and (si.recipient_Name like'"+keyword+"' " +
						   " or si.recipient_Addr like'"+keyword+"' "+
						   " or si.recipient_Mobile like'"+keyword+"' "+
						   " or si.recipient_Phone like'"+keyword+"') ";
				}
				
				//modify by fu 2016-09-14 新加查询条件 条形码
		        String barCode = crm.getString("barCode", "");
				if (StringUtils.isNotEmpty(barCode)) {
					sql += " and si.bar_code='" + barCode + "'";
				}
		        
				//modify by fu 2016-09-14 发货单报表优化  添加查询条件------end
		
		sql +=" group by al.state_province_name, pms.product_no, pms.product_name";
		log.info("getShipDetailsByState="+sql);
		return this.getJdbcTemplate().queryForList(sql);
	}

	public List getShipDetails(CommonRecord crm) {
		// TODO Auto-generated method stub
		String sql = "select si.SI_NO,si.ORDER_NO,si.CUSTOM_CODE,si.CREATE_USR_CODE,si.CREATE_TIME,si.OK_USR_CODE,si.OK_TIME," +
		" si.WAREHOUSE_NO,si.RECIPIENT_NAME,si.RECIPIENT_PHONE,si.RECIPIENT_MOBILE,si.RECIPIENT_ADDR,si.SH_NO,si.TRACKING_NO,si.OK_REMARK, "+
		" sid.product_no,sid.price, " +
		"pms.product_name, sid.qty,state.STATE_PROVINCE_NAME,city.CITY_NAME,si.BAR_CODE,si.logistics_do,DECODE(si.ship_type,'2','暂不发货','3','暂停发货','4','发货作废',0,'正常发货','') ship_type, DECODE(si.WHETHER_STOCK,'Y','已备货','未备货') WHETHER_STOCK "
		+ " from pd_send_info si, pd_send_info_detail sid, jpm_product_sale_new pms," +
				"jal_state_province state,jal_city city "
		+ " where si.si_no = sid.si_no and state.STATE_PROVINCE_ID=si.RECIPIENT_CA_NO" +
				" and city.CITY_ID=si.CITY  "
		+ " and sid.product_no = pms.product_no"
		+ " and si.company_code = pms.company_code";
/*
		String sql = "select si.si_no,si.order_no,si.custom_code,si.create_usr_code," +
				"si.create_time,si.ok_usr_code,si.ok_time,si.warehouse_no, sid.product_no, " +
				"pms.product_name, sid.qty,si.RECIPIENT_NAME,state.STATE_PROVINCE_NAME,city.CITY_NAME,si.RECIPIENT_ADDR "
				+ " from pd_send_info si, pd_send_info_detail sid, jpm_product_sale_new pms," +
						"jal_state_province state,jal_city city "
				+ " where si.si_no = sid.si_no and state.STATE_PROVINCE_ID=si.RECIPIENT_CA_NO" +
						" and city.CITY_ID=si.CITY  "
				+ " and sid.product_no = pms.product_no"
				+ " and si.company_code = pms.company_code";
		*/
		
		String orderType = crm.getString("orderType", "");
		if (StringUtils.isNotEmpty(orderType)) {
			sql += " and si.order_Type = '" + orderType + "'";
		}
		
		
		String recipientCaNo = crm.getString("recipientCaNo", "");
		if (StringUtils.isNotEmpty(recipientCaNo)) {
			sql += " and si.recipient_Ca_No ='" + recipientCaNo + "'";
		}
		//Modify By WuCF 20140604商品编码查询条件
		String productNo = crm.getString("productNo", "");
		if (StringUtils.isNotEmpty(productNo)) {
			sql += " and sid.product_no='" + productNo.trim() + "'";
		}
		String companyCode = crm.getString("companyCode", "");
		if (StringUtils.isNotEmpty(companyCode)) {
			sql += " and si.COMPANY_CODE='" + companyCode + "'";
		}
		String shNo = crm.getString("shNo", "");
		if (StringUtils.isNotEmpty(shNo)) {
			sql += " and si.sh_No='" + shNo + "'";
		}

		String warehouseNo = crm.getString("warehouseNo", "");
		if (StringUtils.isNotEmpty(warehouseNo)) { 
//			sql += " and si.warehouse_No='" + warehouseNo + "'";//Modify By WuCF 20130510  
			sql += " and si.warehouse_No in('" + warehouseNo.replace(",", "','") + "') ";
		}
		
		String createTimeStart = crm.getString("createTimeStart", "");
		String createTimeEnd = crm.getString("createTimeEnd", "");
		if (StringUtils.isNotEmpty(createTimeStart)) {
			sql += " and si.create_Time >=to_date('" + createTimeStart
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		if (StringUtils.isNotEmpty(createTimeEnd)) {
			sql += " and si.create_Time <=to_date('" + createTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}

		String checkTimeStart = crm.getString("checkTimeStart", "");
		String checkTimeEnd = crm.getString("checkTimeEnd", "");
		if (StringUtils.isNotEmpty(checkTimeStart)) {
			sql += " and si.check_Time >=to_date('" + checkTimeStart
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		if (StringUtils.isNotEmpty(checkTimeEnd)) {
			sql += " and si.check_Time <=to_date('" + checkTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}
		// 确认时间
		String okTimeBegain = crm.getString("okTimeStart", "");
		if (StringUtils.isNotEmpty(okTimeBegain)) {
			sql += " and si.ok_Time >=to_date('" + okTimeBegain
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		String okTimeEnd = crm.getString("okTimeEnd", "");
		if (StringUtils.isNotEmpty(okTimeEnd)) {
			sql += " and si.ok_Time <=to_date('" + okTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}

		String orderFlag = crm.getString("orderFlag", "");
		if (StringUtils.isNotEmpty(orderFlag)) {
			sql += " and si.order_Flag in (" + orderFlag + ") ";
		}
		
		String customCode = crm.getString("customCode", "");
		if (StringUtils.isNotEmpty(customCode)) {
			sql += " and si.CUSTOM_CODE='" + customCode + "'";
		}
		
		//modify by fu 2016-09-14 发货单报表优化  添加查询条件------begin
		//modify by fu 2016-09-14 新加查询条件：物流跟踪号
		String trackingNo = crm.getString("trackingNo", "");
		if (StringUtils.isNotEmpty(trackingNo)) {
			sql += " and si.tracking_No like '%" + trackingNo + "%'";
		}
		
		//modify by fu 2016-09-14 新加查询条件： 接口物流单号
		String logisticsDo = crm.getString("logisticsDo","" );
		if (StringUtils.isNotEmpty(logisticsDo)) {
			sql += " and si.logistics_do like '%" + logisticsDo+ "%'";
		}

		String subOrderType = crm.getString("subOrderType", "");
		if (StringUtils.isNotEmpty(subOrderType)) {
			sql += " and si.sub_Order_Type = '" + subOrderType + "'";
		}
		
		//modify by fu 2016-09-14 新加查询条件： 使用积分支付
        String codFlag = crm.getString("codFlag", "");
		if (StringUtils.isNotEmpty(codFlag)) {
			if("1".equals(codFlag)){
				sql += " and si.cod_Flag = '" + codFlag + "'";
			}else{
				sql += " and (si.cod_Flag = '0' or si.cod_Flag is null)";
			}
		}

		//modify by fu 2016-09-14 新加查询条件：加急发货
        String hurryFlag = crm.getString("hurryFlag", "");
		if (StringUtils.isNotEmpty(hurryFlag)) {
			if("1".equals(hurryFlag)){
				sql += " and si.hurry_flag = '" + hurryFlag + "'";
			}else{
				sql += " and (si.hurry_flag = '0' or si.hurry_flag is null)";
			}
		}

        //modify by fu 2016-09-14 新加查询条件 是否已备货
		String whetherStock = crm.getString("whetherStock","" );
		if (StringUtils.isNotEmpty(whetherStock)) {
			if("Y".equals(whetherStock)){
				sql += " and si.whether_stock = '"+whetherStock+"'";
			}else{
				sql += " and ( si.whether_stock = '"+whetherStock+"' or  si.whether_stock is null )";	
		    }
		}

		//modify by fu 2016-09-14 新加查询条件 发货方式
        String shipType = crm.getString("shipType", "");
		if (StringUtils.isNotEmpty(shipType)) {
			sql += " and si.ship_Type='" + shipType + "'";
		}

        //modify by fu 2016-09-14 新加查询条件   添加关键字功能查询
		String keyword = crm.getString("keyword", "");
		if (StringUtils.isNotEmpty(keyword)) {
			keyword = keyword.trim();
			sql += " and (si.recipient_Name like'"+keyword+"' " +
				   " or si.recipient_Addr like'"+keyword+"' "+
				   " or si.recipient_Mobile like'"+keyword+"' "+
				   " or si.recipient_Phone like'"+keyword+"') ";
		}
		
		//modify by fu 2016-09-14 新加查询条件 条形码
        String barCode = crm.getString("barCode", "");
		if (StringUtils.isNotEmpty(barCode)) {
			sql += " and si.bar_code='" + barCode + "'";
		}
        
		//modify by fu 2016-09-14 发货单报表优化  添加查询条件------end




		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		log.info("getShipDetails="+sql);
		
		
		//Modify By WuCF 20140514  查询记录条数，控制记录条数
		String listNum = crm.getString("listNum");//最大限额数
		
		String sqlCount = "select count(*) from ("+sql+") ";
		Integer num = this.getJdbcTemplate().queryForInt(sqlCount);
		log.info("===发货报表统计-发货报表明细，导出数："+num);
		if(num>Integer.parseInt(listNum)){//如果超过限额，则返回
			List list = new ArrayList<String>();
			list.add(1);
			return list;
		}
		
		return this.getJdbcTemplate().queryForList(sql);
	}

	public List getTotalPdSendInfoDetails(CommonRecord crm) {
		// TODO Auto-generated method stub
		String sql = "select pm.product_no,pm.product_name,sum(pdd.qty) qty,sum(pdd.qty*pdd.price) amount from pd_send_info pd,pd_send_info_detail pdd,jpm_product_sale_new pm"
				+ " ,JSYS_USER ju where pd.custom_code=ju.user_code and pd.si_no=pdd.si_no and pdd.product_no=pm.product_no and pm.company_Code=pd.company_Code";

		//Modify By WuCF 20150602 添加仓库权限统计
		String defaultWarehouse = crm.getString("defaultWarehouse", "");
		if(StringUtils.isNotEmpty(defaultWarehouse)){
			sql += " and pd.WAREHOUSE_NO in("+defaultWarehouse+")";
    	}
		
		//Modify By WuCF 20130701 添加关键字功能查询
		String keyword = crm.getString("keyword", "");
		if (StringUtils.isNotEmpty(keyword)) {
			keyword = keyword.trim();
			sql += " and (pd.recipient_Name like'"+keyword+"' " +
				   " or pd.recipient_Addr like'"+keyword+"' "+
				   " or ju.user_name like'"+keyword+"' "+ 
				   " or pd.recipient_Mobile like'"+keyword+"' "+
				   " or pd.recipient_Phone like'"+keyword+"') ";
		}
		
		String price = crm.getString("price", "");
		if(StringUtils.isNotBlank(price)){
			sql += " and pdd.price =" + price ;
		}
		String recipientCaNo = crm.getString("recipientCaNo", "");
		if (StringUtils.isNotEmpty(recipientCaNo)) {
			sql += " and pd.recipient_Ca_No ='" + recipientCaNo + "'";
		}
		String codFlag = crm.getString("codFlag", "");
		if (StringUtils.isNotEmpty(codFlag)) {
			if("1".equals(codFlag)){
				sql += " and pd.cod_Flag = '" + codFlag + "'";
			}else{
				sql += " and (pd.cod_Flag = '0' or pd.cod_Flag is null)";
			}
		}
		
		
		String trackingNo = crm.getString("trackingNo", "");
		if (StringUtils.isNotEmpty(trackingNo)) {
			sql += " and pd.tracking_No like '%" + trackingNo + "%'";
		}
		
		//modify by fu 2016-02-25 接口物流单号
		String logisticsDo = crm.getString("logisticsDo","" );
		if (StringUtils.isNotEmpty(logisticsDo)) {
			sql += " and pd.logistics_do like '%" + logisticsDo
					+ "%'";
		}
		
		//modify by fu 2016-02-25 是否已备货
		String whetherStock = crm.getString("whetherStock","" );
		if (StringUtils.isNotEmpty(whetherStock)) {
			if("Y".equals(whetherStock)){
				sql += " and pd.whether_stock = '"+whetherStock+"'";
			}else{
				sql += " and ( pd.whether_stock = '"+whetherStock+"' or  pd.whether_stock is null )";	
		    }
		}
		String warehouseNo = crm.getString("warehouseNo", "");
		if (StringUtils.isNotEmpty(warehouseNo)) {
//			sql += " and pd.warehouse_No='" + warehouseNo + "'";//Modify By WuCF 
			sql += " and pd.warehouse_No in('" + warehouseNo.replace(",", "','") + "') ";
		}

		String siNo = crm.getString("siNo", "");
		if (StringUtils.isNotEmpty(siNo)) {
			sql += " and pd.si_No='" + siNo + "'";
		}

		String shNo = crm.getString("shNo", "");
		if (StringUtils.isNotEmpty(shNo)) {
			sql += " and pd.sh_No='" + shNo + "'";
		}

		String orderNo = crm.getString("orderNo", "");
		if (StringUtils.isNotEmpty(orderNo)) {
			sql += " and pd.order_No = '" + orderNo + "'";
		}
		String orderType = crm.getString("orderType", "");
		if (StringUtils.isNotEmpty(orderType)) {
			sql += " and pd.order_type = '" + orderType + "'";
		}
		String subOrderType = crm.getString("subOrderType", "");
		if (StringUtils.isNotEmpty(subOrderType)) {
			sql += " and pd.sub_Order_Type = '" + subOrderType + "'";
		}
		String customCode = crm.getString("customCode", "");
		if (StringUtils.isNotEmpty(customCode)) {
			sql += " and pd.CUSTOM_CODE='" + customCode + "'";
		}
		String productNo = crm.getString("productNo", "");
		if (StringUtils.isNotEmpty(productNo)) {
			sql += " and pdd.product_no='" + productNo + "'";
		}

		String shipType = crm.getString("shipType", "");
		if (StringUtils.isNotEmpty(shipType)) {
			sql += " and pd.ship_Type='" + shipType + "'";
		}

		String companyCode = crm.getString("companyCode", "");
		if (StringUtils.isNotEmpty(companyCode)) {
			sql += " and pd.COMPANY_CODE='" + companyCode + "'";
		}
		sql += " and pdd.qty > 0 ";//Modify By WuCF 先判断不为空的
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

		String checkTimeStart = crm.getString("checkTimeStart", "");
		String checkTimeEnd = crm.getString("checkTimeEnd", "");
		if (StringUtils.isNotEmpty(checkTimeStart)) {
			sql += " and pd.check_Time >=to_date('" + checkTimeStart
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		if (StringUtils.isNotEmpty(checkTimeEnd)) {
			sql += " and pd.check_Time <=to_date('" + checkTimeEnd
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

		String orderFlag = crm.getString("orderFlag", "");
		if (StringUtils.isNotEmpty(orderFlag)) {
			sql += " and pd.order_Flag in (" + orderFlag + ") ";
		}
		
		//Modify By WuCF 20130905 having sum(pdd.qty)<>0 去掉
		sql += " group by pm.product_no,pm.product_name  order by pm.product_no ";
		return this.getJdbcTemplate().queryForList(sql);
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdSendInfoDetailDao#getPdSendInfoDetails(com.joymain.jecs.pd.model.PdSendInfoDetail)
	 */
	public List getPdSendInfoDetails(final PdSendInfoDetail pdSendInfoDetail) {
		return getHibernateTemplate().find("from PdSendInfoDetail");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (pdSendInfoDetail == null) {
		 * return getHibernateTemplate().find("from PdSendInfoDetail"); } else {
		 * // filter on properties set in the pdSendInfoDetail HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex
		 * =Example.create(pdSendInfoDetail).ignoreCase().enableLike(MatchMode.
		 * ANYWHERE); return
		 * session.createCriteria(PdSendInfoDetail.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdSendInfoDetailDao#getPdSendInfoDetail(BigDecimal
	 *      uniNo)
	 */
	public PdSendInfoDetail getPdSendInfoDetail(final BigDecimal uniNo) {
		PdSendInfoDetail pdSendInfoDetail = (PdSendInfoDetail) getHibernateTemplate()
				.get(PdSendInfoDetail.class, uniNo);
		if (pdSendInfoDetail == null) {
			log.warn("uh oh, pdSendInfoDetail with uniNo '" + uniNo
					+ "' not found...");
			throw new ObjectRetrievalFailureException(PdSendInfoDetail.class,
					uniNo);
		}

		return pdSendInfoDetail;
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdSendInfoDetailDao#savePdSendInfoDetail(PdSendInfoDetail
	 *      pdSendInfoDetail)
	 */
	public void savePdSendInfoDetail(final PdSendInfoDetail pdSendInfoDetail) {
		getHibernateTemplate().saveOrUpdate(pdSendInfoDetail);
	}

	/**
	 * @see com.joymain.jecs.pd.dao.PdSendInfoDetailDao#removePdSendInfoDetail(BigDecimal
	 *      uniNo)
	 */
	public void removePdSendInfoDetail(final BigDecimal uniNo) {
		getHibernateTemplate().delete(getPdSendInfoDetail(uniNo));
	}

	// added for getPdSendInfoDetailsByCrm
	public List getPdSendInfoDetailsByCrm(CommonRecord crm, Pager pager) {
		String hql = "from PdSendInfoDetail pdSendInfoDetail where 1=1";
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
	
	/**
	 * @see com.joymain.jecs.pd.dao.PdSendInfoDetailDao#removePdSendInfoDetail(BigDecimal
	 *      uniNo)
	 */
	public void deletePdSendInfoDetail(final Long uniNo) {
		String hql = "delete from PdSendInfoDetail psid where psid.uniNo='"+uniNo+"' ";
		this.getHibernateTemplate().bulkUpdate(hql);
	}
	
	/**
	 * 根据发货单号获取发货明细
	 * @author gw 2015-04-22
	 * @param siNo
	 * @return
	 */
	public List getPdSendInfoInterFaceList(String siNo) {
		String hql = " from PdSendInfoDetail pdSendInfoDetail where pdSendInfoDetail.siNo='"+siNo+"'";
		List<PdSendInfoDetail> list = this.findObjectsByHqlQuery(hql);
		if(null!=list){
			if(list.size()>0){
				return list;
			}
		}
		return null;
	}
		
}
