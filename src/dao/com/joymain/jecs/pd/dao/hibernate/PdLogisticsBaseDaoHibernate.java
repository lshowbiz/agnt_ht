
package com.joymain.jecs.pd.dao.hibernate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import com.joymain.jecs.Constants;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdLogisticsBase;
import com.joymain.jecs.pd.model.PdLogisticsBaseDetail;
import com.joymain.jecs.pd.model.PdLogisticsBaseNum;
import com.joymain.jecs.pd.model.PdSendInfo;
import com.joymain.jecs.pd.dao.PdLogisticsBaseDao;
import com.joymain.jecs.pd.dao.PdSendInfoDao;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class PdLogisticsBaseDaoHibernate extends BaseDaoHibernate implements PdLogisticsBaseDao {
	
	//发货单的DAO
	private PdSendInfoDao pdSendInfoDao;
	
	public void setPdSendInfoDao(PdSendInfoDao pdSendInfoDao) {
		this.pdSendInfoDao = pdSendInfoDao;
	}

	/**
     * @see com.joymain.jecs.pd.dao.PdLogisticsBaseDao#getPdLogisticsBases(com.joymain.jecs.pd.model.PdLogisticsBase)
     */
    public List getPdLogisticsBases(final PdLogisticsBase pdLogisticsBase) {
        return getHibernateTemplate().find("from PdLogisticsBase");
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdLogisticsBaseDao#getPdLogisticsBase(BigDecimal baseId)
     */
    public PdLogisticsBase getPdLogisticsBase(final BigDecimal baseId) {
        PdLogisticsBase pdLogisticsBase = (PdLogisticsBase) getHibernateTemplate().get(PdLogisticsBase.class, baseId);
        if (pdLogisticsBase == null) {
            log.warn("uh oh, pdLogisticsBase with baseId '" + baseId + "' not found...");
            throw new ObjectRetrievalFailureException(PdLogisticsBase.class, baseId);
        }

        return pdLogisticsBase;
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdLogisticsBaseDao#savePdLogisticsBase(PdLogisticsBase pdLogisticsBase)
     */    
    public void savePdLogisticsBase(final PdLogisticsBase pdLogisticsBase) {
        getHibernateTemplate().saveOrUpdate(pdLogisticsBase);
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdLogisticsBaseDao#removePdLogisticsBase(BigDecimal baseId)
     */
    public void removePdLogisticsBase(final BigDecimal baseId) {
        getHibernateTemplate().delete(getPdLogisticsBase(baseId));
    }
    //added for getPdLogisticsBasesByCrm
    public List getPdLogisticsBasesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PdLogisticsBase pdLogisticsBase where 1=1";
    	/** 
	---example---
	String userCode = crm.getString("userCode", "");
	if(StringUtils.isNotEmpty(userCode)){
		hql += "???????????";
	}
	 ***/
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by baseId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }


	/**
	 * 根据发货单号和do号查询
	 * @author gw 2014-12-03
	 * @param pdLogisticsBase
	 * @return list
	 */
	public List getPdLogisticsBaseListByBin(PdLogisticsBase pdLogisticsBase) {
		 String hql = " from PdLogisticsBase pdLogisticsBase where pdLogisticsBase.si_no='"+pdLogisticsBase.getSi_no()+"' and pdLogisticsBase.wms_do='"+pdLogisticsBase.getWms_do()+"'";
		 
		 return this.findObjectsByHqlQuery(hql);
	}

	/**
	 * 后面几次非仓内作业的DO接口数据的保存
	 * @author 2014-12-03
	 * @param pdLogisticsBaseThree
	 * @return string
	 */
	public String savePdLogisticsBaseTheSecondNotBin(
			PdLogisticsBase pdLogisticsBaseThree) {
		try{
			 getHibernateTemplate().saveOrUpdate(pdLogisticsBaseThree);
		     return Constants.INTERFACE_NORMAL;
		}catch(Exception e){
			return Constants.INTERFACE_JSON_FORMAT;//
		}
	}

	/**
	 * 保存合法LO的数据
	 * @author gw 2014-12-03
	 * @param listOne
	 * @return
	 */
	public String saveLoAll(List<PdLogisticsBase> listOne) {
		try{
			 this.getHibernateTemplate().saveOrUpdateAll(listOne);
		     return Constants.INTERFACE_NORMAL;
		}catch(Exception e){
			return Constants.INTERFACE_JSON_FORMAT;//
		}
	}

	/**
	 * DO的仓内作业的保存方法
	 * @author gw 2014-12-03
	 * @param  pdLogisticsBaseList 根据发货单号和wmsDo查询pdLogisticsBase的结果集
	 * @param  pdLogisticsBase
	 * @return string
	 */
	public String getBinResult(List pdLogisticsBaseList,PdLogisticsBase pdLogisticsBase) {
		try{
			 //因为json转对象的时候，主键被弄成了0，因此这里重新赋值
   		     String hql = "select SEQ_PD.nextval from dual";
			//仓内作业的话，就拒绝存那些无关紧要的数据
			pdLogisticsBase.setMail_list(null);
			if(null!=pdLogisticsBaseList){
				//更新操作
				if(pdLogisticsBaseList.size()>0){
					PdLogisticsBase pdLogisticsBaseN = (PdLogisticsBase) pdLogisticsBaseList.get(0);
					pdLogisticsBaseN.setMember_order_no(pdLogisticsBase.getMember_order_no());
					pdLogisticsBaseN.setSi_no(pdLogisticsBase.getSi_no());
					pdLogisticsBaseN.setStatus(pdLogisticsBase.getStatus());
					pdLogisticsBaseN.setWms_do(pdLogisticsBase.getWms_do());
					pdLogisticsBaseN.setStatus_name(pdLogisticsBase.getStatus_name());
					pdLogisticsBaseN.setStatus_code(pdLogisticsBase.getStatus_code());
					pdLogisticsBaseN.setStatus_time(pdLogisticsBase.getStatus_time());
					pdLogisticsBaseN.setOperator(pdLogisticsBase.getOperator());
					//modify  gw 添加订单号类型判断  2015-04-24  订单号类型(MO表示是订单，EX表示是关联到换货单)---begin
					pdLogisticsBaseN.setNum_order_type(pdLogisticsBase.getNum_order_type());
					//modify  gw 添加订单号类型判断  2015-04-24  订单号类型(MO表示是订单，EX表示是关联到换货单)---begin
       			    //-----------------------modify fu 订单全额支付的字段，这个是第二期要上线的功能，因此现在将这个校验屏蔽掉，第二期的时候再打开
					//pdLogisticsBaseN.setIsfull_pay(pdLogisticsBase.getIsfull_pay());//modify gw 2015-07-15 订单全额支付字段
					
					this.savePdLogisticsBase(pdLogisticsBaseN);
				}
				//新增操作
				else{
		    		 Long idOne = jdbcTemplate.queryForLong(hql);
					pdLogisticsBase.setBaseId(idOne);//转过来的对象主键被默认设为0了,因此在新增的时候，要将主键赋空
					this.savePdLogisticsBase(pdLogisticsBase);
				}
				//执行结果正常
				//保存或修改保存正常返回的值 INTERFACE_NORMAL = "interfaceNormal"
				return Constants.INTERFACE_NORMAL;//正常返回的结果
			}
			//新增操作
			else{
				    Long idTwo = jdbcTemplate.queryForLong(hql);
				    pdLogisticsBase.setBaseId(idTwo);//转过来的对象主键被默认设为0了,因此在新增的时候，要将主键赋空
				    this.savePdLogisticsBase(pdLogisticsBase);
				    //执行结果正常
					//保存或修改保存正常返回的值 INTERFACE_NORMAL = "interfaceNormal"
					return Constants.INTERFACE_NORMAL;//正常返回的结果
			}
		}catch(Exception e){
			e.printStackTrace();
			return " save the anomaly ";//保存异常
		}
	}

	/**
	 * 判断数据库中是否存在这条非仓内作业的数据
	 * @author gw 2014-12-03
	 * @param pdLogisticsBase
	 * @return pdLogisticsBase
	 */
	public PdLogisticsBase getPdLogisticsBaseFirstNotBin(PdLogisticsBase pdLogisticsBase) {
		    String hql = " from PdLogisticsBase pdLogisticsBase where pdLogisticsBase.si_no='"+pdLogisticsBase.getSi_no()+"' and pdLogisticsBase.wms_do='"+pdLogisticsBase.getWms_do()+"'";
			List list = this.findObjectsByHqlQuery(hql);
			if(null==list){
				return null;//表明数据库中没有存储该发货单号si_no和wms_do物流单号的DO信息
			}else{
				if(list.size()>0){
					PdLogisticsBase pdLogisticsBaseTwo = (PdLogisticsBase) list.get(0);
					return pdLogisticsBaseTwo;
				}else{
					return null;//表明数据库中没有存储该发货单号si_no和wms_do物流单号的DO信息
				}
			}
	}

	/**
	 * 接口数据保存
	 * @author gw 2014-12-03
	 * @param pdLogisticsBase
	 * @return
	 */
	public String saveFirstNotBinPdLogisticsBase(PdLogisticsBase pdLogisticsBase) {
		try{
			String hql = "select SEQ_PD.nextval from dual";
			Long baseId = pdLogisticsBase.getBaseId();
			if(null==baseId){
				baseId = jdbcTemplate.queryForLong(hql);
				pdLogisticsBase.setBaseId(baseId);
			}
			
			//modify fu 2015-11-26  修改物流单号重复的问题---begin
			String wmsDo = pdLogisticsBase.getWms_do();
			//modify fu 2015-11-26  修改物流单号重复的问题---end
			
			//第一次非仓内作业：这个时间点表面是发货时间
			//给发货时间设值
			Date date = new Date();
			for(int i=0;i<pdLogisticsBase.getMail_list().size();i++){
				Long idOne = jdbcTemplate.queryForLong(hql);
				pdLogisticsBase.getMail_list().get(i).setNumId(idOne);
				pdLogisticsBase.getMail_list().get(i).setPdLogisticsBase(pdLogisticsBase);
				pdLogisticsBase.getMail_list().get(i).setMailTime(date);
				
				//modify fu 2015-11-26  修改物流单号重复的问题---begin
				String pdLogisticsBaseNum_no = pdLogisticsBase.getMail_list().get(i).getPdLogisticsBaseNum_no();
				String otherOne = wmsDo+"wmsDo"+pdLogisticsBaseNum_no;//do单号+wmsDo+物流单号
				pdLogisticsBase.getMail_list().get(i).setOtherOne(otherOne);
				//modify fu 2015-11-26  修改物流单号重复的问题---end
				
				for(int j=0;j<pdLogisticsBase.getMail_list().get(i).getPdLogisticsBaseDetail_items().size();j++){
					 Long idTwo = jdbcTemplate.queryForLong(hql);
					 pdLogisticsBase.getMail_list().get(i).getPdLogisticsBaseDetail_items().get(j).setCreateTime(date);
					 pdLogisticsBase.getMail_list().get(i).getPdLogisticsBaseDetail_items().get(j).setDetailId(idTwo);
					 pdLogisticsBase.getMail_list().get(i).getPdLogisticsBaseDetail_items().get(j).setPdLogisticsBaseNum(pdLogisticsBase.getMail_list().get(i));
				}
			}
			//新增操作
			this.savePdLogisticsBase(pdLogisticsBase);
		    //执行结果正常
			return Constants.INTERFACE_NORMAL;
		}
		catch(Exception e){
			return null;
		}
	}
	
	/**
	 * 定义ID
	 * @author gw 2014-12-25
	 * @return Long
	 */
	public Long definitionIdGenerate() {
		String hql = "select SEQ_PD.nextval from dual";
		return jdbcTemplate.queryForLong(hql);
	}
	
	/**
	 * 更新的LO单（发货单）数据后，同步的将相关的发货单状态改为已发货
	 * @author gw 2014-12-02
	 * @param 
	 */
	public void setPdSendInfoShipped(List<PdLogisticsBase> pdLogisticsBaseList){
		//根据这个接口LO单（发货单）的信息，表明发货单已经发货，那么就将LO单的发货状态改为已发货
		PdSendInfo pdSendInfo = pdSendInfoDao.getPdSendInfo(((PdLogisticsBase)pdLogisticsBaseList.get(0)).getSi_no());
		if(null!=pdSendInfo){
			pdSendInfo.setOrderFlag(2);
			pdSendInfo.setOkTime(new Date());
			pdSendInfoDao.savePdSendInfo(pdSendInfo);
		}
	}
	
	/**
	 * Add By WuCF 201411
	 * 中心物流状态结构数据接收并修改本地订单明细数据：仓内作业状态、物流跟踪单号、确认收货状态；
	 * @param pdLogisticsBase
	 */
	public void updateJpoMemberOrderList(PdLogisticsBase pdLogisticsBase){
		//UNDO:修改MO_LIST Modify By WuCF 20141203
		//1.修改仓内作业状态
		StringBuffer sqlBuf1 = new StringBuffer("UPDATE JPO_MEMBER_ORDER_LIST T1 ");
		sqlBuf1.append(" SET T1.WAREHOUSE_OPERATION = ");
		sqlBuf1.append(" (SELECT T2.STATUS_CODE B2 ");
		sqlBuf1.append(" FROM JPO_MEMBER_ORDER T0, PD_LOGISTICS_BASE T2 ");
		sqlBuf1.append(" WHERE T0.MO_ID = T1.MO_ID ");
		sqlBuf1.append(" AND T0.MEMBER_ORDER_NO = T2.MEMBER_ORDER_NO AND T2.BASE_ID='");
		sqlBuf1.append(pdLogisticsBase.getBaseId());
		sqlBuf1.append("')  WHERE EXISTS (SELECT 1 ");
		sqlBuf1.append(" FROM JPO_MEMBER_ORDER T0, PD_LOGISTICS_BASE T2 ");
		sqlBuf1.append(" WHERE T0.MO_ID = T1.MO_ID ");
		sqlBuf1.append(" AND T0.MEMBER_ORDER_NO = T2.MEMBER_ORDER_NO AND T2.BASE_ID='");
		sqlBuf1.append(pdLogisticsBase.getBaseId());
		sqlBuf1.append("') ");
		this.jdbcTemplate.execute(sqlBuf1.toString());
		
		//2.修改物流跟踪单号
		StringBuffer sqlBuf2 = new StringBuffer("UPDATE JPO_MEMBER_ORDER_LIST T1 ");
		sqlBuf2.append(" SET T1.TRACKING_SINGLE =  ");
		sqlBuf2.append(" T1.TRACKING_SINGLE||'</br>'||(SELECT T3.NUM_ID FROM JPO_MEMBER_ORDER T0,JPO_MEMBER_ORDER_LIST T1,PD_LOGISTICS_BASE T2,PD_LOGISTICS_BASE_NUM T3 "); 
		sqlBuf2.append(" WHERE T0.MO_ID=T1.MO_ID AND T0.MEMBER_ORDER_NO=T2.MEMBER_ORDER_NO AND T2.BASE_ID=T3.BASE_ID AND T2.BASE_ID='");
		sqlBuf2.append(pdLogisticsBase.getBaseId());
		sqlBuf2.append("')  WHERE EXISTS(SELECT 1 FROM JPO_MEMBER_ORDER T0,JPO_MEMBER_ORDER_LIST T1,PD_LOGISTICS_BASE T2,PD_LOGISTICS_BASE_NUM T3  ");
		sqlBuf2.append(" WHERE T0.MO_ID=T1.MO_ID AND T0.MEMBER_ORDER_NO=T2.MEMBER_ORDER_NO AND T2.BASE_ID=T3.BASE_ID AND T2.BASE_ID='");
		sqlBuf2.append(pdLogisticsBase.getBaseId());
		sqlBuf2.append("') ");
		this.jdbcTemplate.execute(sqlBuf2.toString());
		
		//3.修改确认收货状态
		List<PdLogisticsBaseNum> pdLogisticsBaseNumList = pdLogisticsBase.getMail_list();
		Integer numTemp = 0;
		Map<String,Integer> productNoMap = new HashMap<String,Integer>();//发过来的数据
		for(PdLogisticsBaseNum pbn : pdLogisticsBaseNumList){//物流状态接口物流信息表
			if("0020".equals(pbn.getStatus())){//已经签收的物流单号
				List<PdLogisticsBaseDetail> list = pbn.getPdLogisticsBaseDetail_items();
				for(PdLogisticsBaseDetail pbd : list){
					if(productNoMap.containsKey(pbd.getproduct_no())){//是否已经包含了指定商品编码的数据
						numTemp = productNoMap.get(pbd.getproduct_no());//以前存放的值
						productNoMap.put(pbd.getproduct_no(),Integer.parseInt(pbd.getQty())+numTemp);
					}else{//如果是新的商品数据，直接存放数据
						productNoMap.put(pbd.getproduct_no(),Integer.parseInt(pbd.getQty()));
					}
				}
			}
		}
		StringBuffer sqlBuf3 = new StringBuffer("SELECT T2.PTT_ID||'#'||T1.Product_No PRODUCT_NO,T0.QTY QTY FROM JPO_MEMBER_ORDER T,JPO_MEMBER_ORDER_LIST T0,JPM_PRODUCT_SALE_NEW T1,JPM_PRODUCT_SALE_TEAM_TYPE T2");
		sqlBuf3.append("WHERE T.MO_ID=T0.MO_ID AND T0.PRODUCT_ID=T2.PTT_ID AND T1.UNI_NO=T2.UNI_NO AND T.MEMBER_ORDER_NO='");
		sqlBuf3.append(pdLogisticsBase.getMember_order_no());
		sqlBuf3.append("'");
		List<Map<String,Integer>> jpoMemberOrderLists = this.jdbcTemplate.queryForList(sqlBuf3.toString());
		StringBuffer pttIdStr = new StringBuffer("");
		//Map2查询的是以:  pttId#productNo字符串格式，因为传过来的是productNo,但我们修改的是pttId
		for(Map<String,Integer> map2 : jpoMemberOrderLists){
			if(productNoMap.containsKey(map2.get("PRODUCT_NO").toString().split("#")[1])){
				if(Integer.parseInt(productNoMap.get(map2.get("PRODUCT_NO").toString().split("#")[1]).toString())==map2.get("QTY")){
					pttIdStr.append(","+map2.get("PRODUCT_NO").toString().split("#")[0]);
				}
			}
		}
		//修改满足条件的商品集合数据的收货状态
		if(pttIdStr!=null && !"".equals(pttIdStr.toString())){
			StringBuffer sqlBuf4 = new StringBuffer();
			sqlBuf4.append("UPDATE JPO_MEMBER_ORDER_LIST T SET T.CONFIRM_RECEIPT='2' ");
			sqlBuf4.append("WHERE T.PRODUCT_ID IN('");
			sqlBuf4.append(pttIdStr.toString().replace(",", "','"));
			sqlBuf4.append("') AND EXISTS(SELECT 1 FROM JPO_MEMBER_ORDER T1 WHERE T.MO_ID=T1.MO_ID AND T1.MEMBER_ORDER_NO='");
			sqlBuf4.append(pdLogisticsBase.getMember_order_no());
			sqlBuf4.append("') ");
			this.jdbcTemplate.execute(sqlBuf4.toString());
		}
	}
	
	
	/**
	 * Add By WuCF 201411
	 * 中心物流状态结构数据接收并修改本地订单明细数据：仓内作业状态、物流跟踪单号、确认收货状态；
	 * @param pdLogisticsBase
	 */
	public void confirmReceipt(PdLogisticsBase pdLogisticsBase){
		//修改确认收货状态
		List<PdLogisticsBaseNum> pdLogisticsBaseNumList = pdLogisticsBase.getMail_list();
		Integer numTemp = 0;
		Map<String,Integer> productNoMap = new HashMap<String,Integer>();//发过来的数据
		//将接口中的商品信息放进Map集合中---begin
		for(PdLogisticsBaseNum pbn : pdLogisticsBaseNumList){//物流状态接口物流信息表
			if("0020".equals(pbn.getStatus())){//已经签收的物流单号
				List<PdLogisticsBaseDetail> list = pbn.getPdLogisticsBaseDetail_items();
				for(PdLogisticsBaseDetail pbd : list){
					if(productNoMap.containsKey(pbd.getproduct_no())){//是否已经包含了指定商品编码的数据
						numTemp = productNoMap.get(pbd.getproduct_no());//以前存放的值
						productNoMap.put(pbd.getproduct_no(),Integer.parseInt(pbd.getQty())+numTemp);
					}else{//如果是新的商品数据，直接存放数据
						productNoMap.put(pbd.getproduct_no(),Integer.parseInt(pbd.getQty()));
					}
				}
			}
		}
		//将接口中的商品信息放进Map集合中---end

		StringBuffer sqlBuf3 = new StringBuffer("SELECT T2.PTT_ID||'#'||T1.Product_No PRODUCT_NO,T0.QTY QTY FROM JPO_MEMBER_ORDER T,JPO_MEMBER_ORDER_LIST T0,JPM_PRODUCT_SALE_NEW T1,JPM_PRODUCT_SALE_TEAM_TYPE T2");
		sqlBuf3.append("WHERE T.MO_ID=T0.MO_ID AND T0.PRODUCT_ID=T2.PTT_ID AND T1.UNI_NO=T2.UNI_NO AND T.MEMBER_ORDER_NO='");
		sqlBuf3.append(pdLogisticsBase.getMember_order_no());
		sqlBuf3.append("'");
		List<Map<String,Integer>> jpoMemberOrderLists = this.jdbcTemplate.queryForList(sqlBuf3.toString());
		StringBuffer pttIdStr = new StringBuffer("");
		//Map2查询的是以:  pttId#productNo字符串格式，因为传过来的是productNo,但我们修改的是pttId
		for(Map<String,Integer> map2 : jpoMemberOrderLists){
			if(productNoMap.containsKey(map2.get("PRODUCT_NO").toString().split("#")[1])){
				if(Integer.parseInt(productNoMap.get(map2.get("PRODUCT_NO").toString().split("#")[1]).toString())==map2.get("QTY")){
					pttIdStr.append(","+map2.get("PRODUCT_NO").toString().split("#")[0]);
				}
			}
		}
		//修改满足条件的商品集合数据的收货状态
		if(pttIdStr!=null && !"".equals(pttIdStr.toString())){
			StringBuffer sqlBuf4 = new StringBuffer();
			sqlBuf4.append("UPDATE JPO_MEMBER_ORDER_LIST T SET T.CONFIRM_RECEIPT='2' ");
			sqlBuf4.append("WHERE T.PRODUCT_ID IN('");
			sqlBuf4.append(pttIdStr.toString().replace(",", "','"));
			sqlBuf4.append("') AND EXISTS(SELECT 1 FROM JPO_MEMBER_ORDER T1 WHERE T.MO_ID=T1.MO_ID AND T1.MEMBER_ORDER_NO='");
			sqlBuf4.append(pdLogisticsBase.getMember_order_no());
			sqlBuf4.append("') ");
			this.jdbcTemplate.execute(sqlBuf4.toString());
		}
	}
	

	/**
	 * 修改仓内作业的状态
	 * @author gw 2015-01-25
	 * @param pdLogisticsBaseT
	 */
	public void updatePdLogisticsBaseStatus(PdLogisticsBase pdLogisticsBase) {
		//UNDO:修改MO_LIST Modify By WuCF 20141203
		//1.修改仓内作业状态
		StringBuffer sqlBuf1 = new StringBuffer("UPDATE JPO_MEMBER_ORDER_LIST T1 ");
		sqlBuf1.append(" SET T1.WAREHOUSE_OPERATION = ");
		sqlBuf1.append(" (SELECT T2.STATUS_CODE B2 ");
		sqlBuf1.append(" FROM JPO_MEMBER_ORDER T0, PD_LOGISTICS_BASE T2 ");
		sqlBuf1.append(" WHERE T0.MO_ID = T1.MO_ID ");
		sqlBuf1.append(" AND T0.MEMBER_ORDER_NO = T2.MEMBER_ORDER_NO AND T2.BASE_ID='");
		sqlBuf1.append(pdLogisticsBase.getBaseId());
		sqlBuf1.append("')  WHERE EXISTS (SELECT 1 ");
		sqlBuf1.append(" FROM JPO_MEMBER_ORDER T0, PD_LOGISTICS_BASE T2 ");
		sqlBuf1.append(" WHERE T0.MO_ID = T1.MO_ID ");
		sqlBuf1.append(" AND T0.MEMBER_ORDER_NO = T2.MEMBER_ORDER_NO AND T2.BASE_ID='");
		sqlBuf1.append(pdLogisticsBase.getBaseId());
		sqlBuf1.append("') ");
		this.jdbcTemplate.execute(sqlBuf1.toString());
	}

	/**
	 * 修改物流单号信息--该方法弃用
	 * @author gw 2015-01-25
	 * @param pdLogisticsBaseT
	 */
	public void updatePdLogisticsBaseNumno(PdLogisticsBaseNum pdLogisticsBaseNum){
		StringBuffer sqlBuf2 = new StringBuffer("UPDATE JPO_MEMBER_ORDER_LIST T1 ");
		sqlBuf2.append(" SET T1.TRACKING_SINGLE =  ");
		sqlBuf2.append(" T1.TRACKING_SINGLE||'</br>'||(SELECT T3.NUM_ID FROM JPO_MEMBER_ORDER T0,JPO_MEMBER_ORDER_LIST T1,PD_LOGISTICS_BASE T2,PD_LOGISTICS_BASE_NUM T3 "); 
		sqlBuf2.append(" WHERE T0.MO_ID=T1.MO_ID AND T0.MEMBER_ORDER_NO=T2.MEMBER_ORDER_NO AND T2.BASE_ID=T3.BASE_ID AND T2.BASE_ID='");
		sqlBuf2.append(pdLogisticsBaseNum.getPdLogisticsBase().getBaseId());
		sqlBuf2.append("' GROUP BY  T3.NUM_ID )  WHERE EXISTS(SELECT 1 FROM JPO_MEMBER_ORDER T0,JPO_MEMBER_ORDER_LIST T1,PD_LOGISTICS_BASE T2,PD_LOGISTICS_BASE_NUM T3  ");
		sqlBuf2.append(" WHERE T0.MO_ID=T1.MO_ID AND T0.MEMBER_ORDER_NO=T2.MEMBER_ORDER_NO AND T2.BASE_ID=T3.BASE_ID AND T2.BASE_ID='");
		sqlBuf2.append(pdLogisticsBaseNum.getPdLogisticsBase().getBaseId());
		sqlBuf2.append("' GROUP BY  T3.NUM_ID ) ");
		this.jdbcTemplate.execute(sqlBuf2.toString());
	}
	
	/**
     * 判断发货单有没有关联的DO单传过来
     * @author fu 2015-09-16
     * @param siNo
     * @return boolean
     */
	public boolean getDoResult(String siNo){
		String sql = " select si_no from pd_logistics_base a where a.si_no = '"+siNo+"'";
		List list = this.jdbcTemplate.queryForList(sql);
		if(null!=list && list.size()>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 给表的pd_logistics_base字段other_one赋值为Y，表示Y表示已经查询过物流实时信息查询了,不需要进行再查询的
	 * @author fu 2016-05-05
	 * @param baseId  
	 * @return int
	 */
	public int reSetOtherOne(String baseId){
		int a = 0;
		if(!StringUtil.isEmpty(baseId)){
			String sql = " update pd_logistics_base set other_one = 'Y' where base_id = '"+baseId+"'";
			a = this.jdbcTemplate.update(sql);
		}
		return a;
	}

}
