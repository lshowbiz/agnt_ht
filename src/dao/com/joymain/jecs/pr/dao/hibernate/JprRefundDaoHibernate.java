
package com.joymain.jecs.pr.dao.hibernate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import javax.sql.DataSource;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pr.model.JprRefund;
import com.joymain.jecs.pr.dao.JprRefundDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.data.SpringStoredProcedure;
import com.joymain.jecs.util.string.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JprRefundDaoHibernate extends BaseDaoHibernate implements JprRefundDao {

	private DataSource dataSource2 ;
    public void setDataSource2(DataSource dataSource2) {
		this.dataSource2 = dataSource2;
	}
    
	/**
	 * public List getPiProductsByHql(String hql){ return
	 * getHibernateTemplate().find(hql); }
	 * 
	 * public List getPiProductsBySql(String sql){ }
	 */
	/**
	 * @see com.joymain.jecs.pr.dao.JprRefundDao#getJprRefunds(com.joymain.jecs.pr.model.JprRefund)
	 */
	public List getJprRefunds(final JprRefund jprRefund) {
		return getHibernateTemplate().find("from JprRefund");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (jprRefund == null) { return
		 * getHibernateTemplate().find("from JprRefund"); } else { // filter on
		 * properties set in the jprRefund HibernateCallback callback = new
		 * HibernateCallback() { public Object doInHibernate(Session session)
		 * throws HibernateException { Example ex =
		 * Example.create(jprRefund).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return session.createCriteria(JprRefund.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.joymain.jecs.pr.dao.JprRefundDao#getJprRefund(String roNo)
	 */
	public JprRefund getJprRefund(final String roNo) {
		
		JprRefund jprRefund = (JprRefund) getHibernateTemplate().get(JprRefund.class, roNo);
		if (null == jprRefund) {
			//log.warn("uh oh, jprRefund with roNo '" + roNo + "' not found...");
			//throw new ObjectRetrievalFailureException(JprRefund.class, roNo);
			return null;
		}

		return jprRefund;
	}

	/**
	 * @see com.joymain.jecs.pr.dao.JprRefundDao#saveJprRefund(JprRefund jprRefund)
	 */
	public void saveJprRefund(final JprRefund jprRefund) {
		getHibernateTemplate().saveOrUpdate(jprRefund);
	}

	/**
	 * @see com.joymain.jecs.pr.dao.JprRefundDao#removeJprRefund(String roNo)
	 */
	public void removeJprRefund(final String roNo) {
		getHibernateTemplate().delete(getJprRefund(roNo));
	}

	// added for getJprRefundsByCrm
	public List getJprRefundsByCrm(CommonRecord crm, Pager pager) {
		String hql = "from JprRefund jprRefund where 1=1";
		/** * ����Լ��Ĳ�ѯ���������** */
		String roNo = crm.getString("roNo", "");
		if (!StringUtil.isEmpty(roNo)) {
			hql += " and roNo='" + roNo + "'";
		}
		
		String companyCode = crm.getString("companyCode", "");
		if (!StringUtil.isEmpty(companyCode)) {
			hql += " and companyCode='" + companyCode + "'";
		}

		String memberOrderNo = crm.getString("jpoMemberOrder.memberOrderNo", "");
		if (!StringUtil.isEmpty(memberOrderNo)) {
			hql += " and jpoMemberOrder.memberOrderNo='" + memberOrderNo + "'";
		}

		String isNull = crm.getString("poMemberOrder.memberOrderNo.isNull", "");
		if (!StringUtil.isEmpty(isNull)) {
			hql += " and poMemberOrder is " + isNull + "";
		}

		String intoStatus = crm.getString("intoStatus", "");
		if (!StringUtil.isEmpty(intoStatus)) {
			hql += " and intoStatus='" + intoStatus + "'";
		}

		String resendSpNo = crm.getString("resendSpNo", "");
		if (!StringUtil.isEmpty(resendSpNo)) {
			hql += " and resendSpNo='" + resendSpNo + "'";
		}

		String refundStatus = crm.getString("refundStatus", "");
		if (!StringUtil.isEmpty(refundStatus)) {
			hql += " and refundStatus='" + refundStatus + "'";
		}
		String returnType = crm.getString("returnType", "");
		if (!StringUtil.isEmpty(returnType)) {
			hql += " and returnType='" + returnType + "'";
		}
		String userCode = crm.getString("sysUser.userCode", "");
		if (!StringUtil.isEmpty(userCode)) {
			hql += " and sysUser.userCode like '%" + userCode + "%'";
		}

		String refundTye = crm.getString("refundTye", "");
		if (!StringUtil.isEmpty(refundTye)) {
			hql += " and refundTye='" + refundTye + "'";
		}
		
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String createBTime = crm.getString("createBTime", "");
		if (StringUtil.isDate(createBTime)) {
			try {
				hql += " and createTime>=to_date('" + dateFormat.format(dateFormat.parse(createBTime)) + " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String createETime = crm.getString("createETime", "");
		if (StringUtil.isDate(createBTime) && StringUtil.isDate(createETime)) {
			try {
				hql += " and createTime<=to_date('" + dateFormat.format(dateFormat.parse(createETime)) + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String intoBTime = crm.getString("intoBTime", "");
		if (StringUtil.isDate(intoBTime)) {
			try {
				hql += " and intoTime>=to_date('" + dateFormat.format(dateFormat.parse(intoBTime)) + " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String intoETime = crm.getString("intoETime", "");
		if (StringUtil.isDate(intoETime)) {
			try {
				hql += " and intoTime<=to_date('" + dateFormat.format(dateFormat.parse(intoETime)) + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String refundBTime = crm.getString("refundBTime", "");
		if (StringUtil.isDate(refundBTime)) {
			try {
				hql += " and refundTime>=to_date('" + dateFormat.format(dateFormat.parse(refundBTime)) + " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String refundETime = crm.getString("refundETime", "");
		if (StringUtil.isDate(refundBTime) && StringUtil.isDate(refundETime)) {
			try {
				hql += " and refundTime<=to_date('" + dateFormat.format(dateFormat.parse(refundETime)) + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//是否奖金结算
		String locked = crm.getString("locked", "");
		if (!StringUtil.isEmpty(locked)) {
			hql += " and jprRefund.jpoMemberOrder.locked='" + locked + "'";
		}
		
		// ��������
		if (pager==null || !pager.getLimit().getSort().isSorted()) {
			// ȱʡ����
			hql += " order by roNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}
	
	/**
	 * 根据条件统计商品销售量
	 * @param crm
	 * @return
	 */
	public List statisticProductSale(CommonRecord crm){
		String sql = "select pmnew.product_no as product_no, pmnew.product_name as product_name, sum(pl.qty) as qty, pl.pv as pv, pl.price as price, sum(pl.qty*pl.pv) as totalpv, sum(pl.qty*pl.price) as totalprice from Jpr_refund p, Jpr_refund_list pl, JPm_Product_Sale_Team_Type pm,JPm_Product_Sale_New pmnew,JPO_MEMBER_ORDER o";
		
		String agentNo = crm.getString("agentNo", "");
		if (!StringUtils.isEmpty(agentNo)) {
			sql += ", po_member_order pmo";
		}
		
		sql += " where p.ro_no = pl.ro_no and pl.product_ID=pm.ptt_ID  and pm.uni_no=pmnew.uni_no and p.user_code=o.user_code and p.mo_id = o.mo_id ";
		
		String memberNo = crm.getString("sysUser.userCode", "");
		if (!StringUtils.isEmpty(memberNo)) {
			sql += " And p.user_code like '%" + memberNo + "%'";
		}
		
		String companyCode = crm.getString("companyCode", "");
		if (!StringUtils.isEmpty(companyCode)) {
			sql += " And p.company_Code = '" + companyCode + "'";
		}

		String resendSpNo = crm.getString("resendSpNo", "");
		if (!StringUtil.isEmpty(resendSpNo)) {
			sql += " and p.RESEND_SP_NO='" + resendSpNo + "'";
		}

		String intoStatus = crm.getString("intoStatus", "");
		if (!StringUtil.isEmpty(intoStatus)) {
			sql += " and p.into_Status='" + intoStatus + "'";
		}

		String refundStatus = crm.getString("refundStatus", "");
		if (!StringUtil.isEmpty(refundStatus)) {
			sql += " and p.refund_Status='" + refundStatus + "'";
		}
		String returnType = crm.getString("returnType", "");
		if (!StringUtil.isEmpty(returnType)) {
			sql += " and p.return_Type='" + returnType + "'";
		}
		String roNo = crm.getString("roNo", "");
		if (!StringUtil.isEmpty(roNo)) {
			sql += " and p.ro_no='" + roNo + "'";
		}
		String refundTye = crm.getString("refundTye", "");
		if (!StringUtil.isEmpty(refundTye)) {
			sql += " and p.refund_type='" + refundTye + "'";
		}
		String memberOrderNo = crm.getString("jpoMemberOrder.memberOrderNo", "");
		if (!StringUtils.isEmpty(memberOrderNo)) {
			sql += " And p.mo_id in (select pao.mo_id from jpo_member_order pao where pao.member_order_no = '" + memberOrderNo + "'";
			if (!StringUtils.isEmpty(companyCode)) {
				sql += " And pao.company_code = '" + companyCode + "'";
			}
			sql += ")";
		}
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String createBTime = crm.getString("createBTime", "");
		if (StringUtil.isDate(createBTime)) {
			try {
				sql += " and p.create_Time>=to_date('" + dateFormat.format(dateFormat.parse(createBTime)) + " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String createETime = crm.getString("createETime", "");
		if (StringUtil.isDate(createETime)) {
			try {
				sql += " and p.create_Time<=to_date('" + dateFormat.format(dateFormat.parse(createETime)) + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String intoBTime = crm.getString("intoBTime", "");
		if (StringUtil.isDate(intoBTime)) {
			try {
				sql += " and p.into_Time>=to_date('" + dateFormat.format(dateFormat.parse(intoBTime)) + " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String intoETime = crm.getString("intoETime", "");
		if (StringUtil.isDate(intoETime)) {
			try {
				sql += " and p.into_Time<=to_date('" + dateFormat.format(dateFormat.parse(intoETime)) + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String refundBTime = crm.getString("refundBTime", "");
		if (StringUtil.isDate(refundBTime)) {
			try {
				sql += " and p.refund_Time>=to_date('" + dateFormat.format(dateFormat.parse(refundBTime)) + " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String refundETime = crm.getString("refundETime", "");
		if (StringUtil.isDate(refundBTime) && StringUtil.isDate(refundETime)) {
			try {
				sql += " and p.refund_Time<=to_date('" + dateFormat.format(dateFormat.parse(refundETime)) + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//是否奖金结算
		String locked = crm.getString("locked", "");
		if (!StringUtil.isEmpty(locked)) {
			sql += " and o.locked='" + locked + "'";
		}
		
        sql += " group by pmnew.product_no,pmnew.product_name,pl.pv,pl.price";
        
        return this.findObjectsBySQL(sql);
	}
	
	/**
	 * 根据时间获取订单总价格与总PV
	 * @param companyCode
	 * @param createBTime
	 * @param createETime
	 * @return
	 */
	public List getJprRefundCByTime(String companyCode, String createBTime, String createETime){
		String sql = "select pmo.agent_no as agent_no, 0 as in_amount, sum(pr.amount) as out_amount, 0 as out_pv_amt, sum(pr.pv_amt) as out_pv_amt";
		sql += " from wecs.pr_refund pr, wecs.po_member_order pmo";
		sql += " where pmo.company='" + companyCode + "'";
		sql += " and pr.mo_id = pmo.mo_id";
		sql += " and pr.order_date >= to_date('"+ createBTime + " 00:00:00','yyyy-MM-dd hh24:mi:ss')";
		sql += " and pr.order_date <= to_date('"+ createETime + " 23:59:59','yyyy-MM-dd hh24:mi:ss')";
		sql += " group by pmo.agent_No";
		return this.findObjectsBySQL(sql);
	}
	
	/**
	 * 获取一段时间内各个公司(如指定companyCode,则获取对应的公司)的已退货订单
	 * @param startDate
	 * @param endDate
	 * @param companyCode
	 * @param returnType
	 * @return
	 */
	public List getJprReRefundStaticsCheckedCompany(final String startDate, final String endDate, final String companyCode, final String productType, final String returnType){
		String sqlQuery = "select a.order_type as order_type,pnew.product_name as product_name," +
						" pnew.product_no as product_no,"+
						"b.price as price," +
						"sum(b.qty) as qty," +
						"sum(b.qty * b.price) as amount," +
						"sum(b.qty * b.pv) as pv " +
						"from jpr_refund a," +
						"jpr_refund_list b," +
						"jPM_PRODUCT_SALE_Team_type p, " +
						"jPM_PRODUCT_SALE_NEW pnew, " +
						" jpo_member_order o " +
						"where a.ro_no = b.ro_no and o.mo_id = a.mo_id " +
						//dengmh update by 2016/5/17 过滤延迟订单挂起状态
						" and (o.RETURNABLE_STATUS != '1' or o.RETURNABLE_STATUS is null)  ";
		if(!StringUtil.isEmpty(companyCode)){
			sqlQuery += "and a.company_code = '" + companyCode + "' ";
		}
		
		if(!StringUtils.isEmpty(productType)){
			if("ALL".equals(productType)){//所有产品
				
			}else if("LC".equals(productType)){//内衣产品
				sqlQuery += "and o.product_type = 'LC' ";
			}else{
				sqlQuery += "and o.product_type = 'else' ";
			}
		}else{//中脉产品
			sqlQuery += "and o.product_type is null ";
		}
		
		sqlQuery += " and b.product_id=p.ptt_id  and p.uni_no=pnew.uni_no " +
						"and a.refund_status = '2' " +
						"and a.return_type = '" + returnType + "' " +
						"and a.refund_time >= to_date('" + startDate + "', 'yyyy-MM-dd hh24:mi:ss') " +
						"and a.refund_time <= to_date('" + endDate + "', 'yyyy-MM-dd hh24:mi:ss') " +
						"group by a.order_type,pnew.product_name,pnew.product_no,b.price,b.pv " +
						"order by a.order_type,pnew.product_no";
		return this.findObjectsBySQL(sqlQuery);
	}
	
	/**
	 * 获取会员订单的剩余量=订购量-退货量
	 * @param memberOrderNo
	 * @return
	 */
	public List getPoMemberOrderStock(final Long moId){
		String sqlQuery = "";
		
		return this.findObjectsBySQL(sqlQuery);
	}

	public List getRefundGathering(CommonRecord crm) {
		String sql="select p.order_type, ps.product_no, l.price, l.pv, sum(l.qty) qty " +
				"from wecs.pr_refund p, wecs.pr_refund_list l, wecs.pm_product_sale_new ps, wecs.pm_product_sale_team_type pmtt " +
				"where p.return_type = '1' and p.ro_no = l.ro_no and p.refund_status = '2' ";
				String companyCode = crm.getString("companyCode", "");
				if (!StringUtils.isEmpty(companyCode)) {
					sql += " and company_code= '" + companyCode + "'";
				} 
				sql+=" and l.product_no = pmtt.ptt_id  and ps.uni_no=pmtt.uni_no " ;
				
				String auditBDate = crm.getString("auditBDate", "");
				String auditEDate = crm.getString("auditEDate", "");
	

				if(StringUtil.isDate(auditBDate)){
					sql += 	"and p.refund_time >=to_date('"+auditBDate+" 00:00:00', 'yyyy-MM-dd hh24:mi:ss') ";
				}
				if(StringUtil.isDate(auditEDate)){
					sql += "and p.refund_time <=to_date('"+auditEDate+" 23:59:59', 'yyyy-MM-dd hh24:mi:ss')";
				}
					
				
			
				sql+=" group by ps.product_no, l.price, l.pv, p.order_type";
				return this.findObjectsBySQL(sql);
	}

	public List getRefundSum(CommonRecord crm) {
		String sql=" select nvl(sum(t.amount),0) amount,nvl(sum(t.pv_amt),0) pv_amt,nvl(sum(l.qty),0) qty " +
				" from wecs.pr_refund t ,wecs.pr_refund_list l " +
				" where t.ro_no=l.ro_no  and t.refund_status='2' and t.return_type = '1' ";

				String companyCode = crm.getString("companyCode", "");
				if (!StringUtils.isEmpty(companyCode)) {
					sql += " and t.company_code= '" + companyCode + "'";
				} 
				
				String auditBDate = crm.getString("auditBDate", "");
				String auditEDate = crm.getString("auditEDate", "");
	

				if(StringUtil.isDate(auditBDate)){
					sql += 	"and t.refund_time >=to_date('"+auditBDate+" 00:00:00', 'yyyy-MM-dd hh24:mi:ss') ";
				}
				if(StringUtil.isDate(auditEDate)){
					sql += "and t.refund_time <=to_date('"+auditEDate+" 23:59:59', 'yyyy-MM-dd hh24:mi:ss')";
				}
					
		return this.findObjectsBySQL(sql);
	}

	public List getJprReRefundStaticsCheckedCompanyByTree(String startDate, String endDate, String companyCode, String returnType, String treeIndex, String relationType) {

		String sqlQuery = "select a.order_type as order_type,pnew.product_name as product_name," +
						"pnew.product_no as product_no,"+
						"b.price as price," +
						"sum(b.qty) as qty," +
						"sum(b.qty * b.price) as amount," +
						"sum(b.qty * b.pv) as pv " +
						"from jpr_refund     a," +
						"jpr_refund_list b," +
						"jPM_PRODUCT_SALE_Team_type p, " +
						"jPM_PRODUCT_SALE_NEW pnew," +
						"JPO_MEMBER_ORDER o" +
						" where a.ro_no = b.ro_no " +
						" AND o.mo_id = a.mo_id " +
						//dengmh update by 2016/5/17 过滤延迟订单挂起状态
						" and (o.RETURNABLE_STATUS != '1' or o.RETURNABLE_STATUS is null) ";
						
						
						if(!"AA".equals(companyCode)){
							sqlQuery+=" and a.company_code = '" + companyCode + "' " ;
						}
						
						sqlQuery+= " and b.product_id=p.ptt_id " +
						" and p.uni_no=pnew.uni_no" +
						" and a.refund_status = '2' " +
						" and a.return_type = '" + returnType + "' " +
						" and a.refund_time >= to_date('" + startDate + "', 'yyyy-MM-dd hh24:mi:ss') " +
						" and a.refund_time <= to_date('" + endDate + "', 'yyyy-MM-dd hh24:mi:ss') " +
						" and a.user_code in (select user_code from "+relationType+" t where t.tree_index like '"+treeIndex+"%')"+
						" group by a.order_type,pnew.product_name,pnew.product_no,b.price,b.pv " +
						" order by a.order_type,pnew.product_no";
		return this.findObjectsBySQL(sqlQuery);
	}
	
	/**
	 * 获取会员级别PV=首单PV+升级单PV-退货单PV
	 * @param userCode
	 * @return
	 */
	public BigDecimal getUserOrderPv(String userCode){
		String sqlQuery = "select (select nvl(sum(pv_amt), 0)" +
							" from jpo_member_order pmo" +
							" where pmo.user_code = '" + userCode + "'" +
							" and order_type in (1, 2)" +
							" and status = 2) -" +
							" (select nvl(sum(pr.pv_amt), 0)" +
							" from jpo_member_order pmo, jpr_refund pr" +
							" where pr.user_code = 'CN16318193'" +
							" and pr.refund_status = '2'" +
							" and pmo.mo_id = pr.mo_id" +
							" and pmo.order_type in (1, 2)) as pv" +
							" from dual";
		Map map = (Map)this.findObjectsBySQL(sqlQuery).get(0);
		return new BigDecimal(map.get("pv").toString());
	}

	@Override
	public List<JprRefund> getJprRefundByMoId(Long moId,String roNo) {
		String hql = "from JprRefund r where r.jpoMemberOrder.moId=? ";
		if(!StringUtil.isEmpty(roNo)){
			hql += " and r.roNo <> '"+roNo+"'";
		}
		Query query = getSession().createQuery(hql);
		query.setParameter(0, moId);
		List<JprRefund> jprRefundList = query.list();
		return jprRefundList;
	}
	
	
	/**
	 * 调用sql函数 执行大订单结算降级黄砖会员，幸福会员
	 * @param moId 订单主键或退货单主键
	 * @param njtcType  --jtc_type 1 订单 2 退单
	 */
	public String getFunctionRp(String moId,Integer njtcType) {
		SpringStoredProcedure spComp = new SpringStoredProcedure(this.dataSource2, "pro_jtc_com",true);
		
		spComp.setOutParameter("out", oracle.jdbc.OracleTypes.VARCHAR);
		spComp.setParameter("vorder_no", java.sql.Types.VARCHAR);
		spComp.setParameter("njtc_type", java.sql.Types.INTEGER);

		spComp.compile();

		// 传入输入参数值
		Map<String, Object> inComp = new HashMap<String, Object>();
		inComp.put("vorder_no", moId);
		inComp.put("njtc_type", njtcType);
		spComp.SetInParam(inComp);
		// 执行存储过程
		Map resultComp = spComp.execute();

		return "";

	}
	
	/**
	 * 获取这张订单所关联的退货单
	 * @author gw 2015-07-13
	 * @param memberOrderNo
	 * @return list
	 */
	public List<JprRefund> getJprRefundForMemberOrder(String memberOrderNo) {
		String hql = " from JprRefund jprRefund where jprRefund.jpoMemberOrder.memberOrderNo='"+memberOrderNo+"'";
		List list = this.findObjectsByHqlQuery(hql);
		if(null!=list){
			if(list.size()>0){
				return list;
			}
		}
		return null;
	}
	
	/**
	 * 根据退货单号查询退货入库状态
	 * @author fu 2015-09-10 
	 * @param roNo
	 * @return string 
	 */
	public String getIntoStatus(String roNo){
		if(!StringUtil.isEmpty(roNo)){
			String sql = " select into_status from jpr_refund where ro_no = '"+roNo+"'";
			List list =  this.getJdbcTemplate().queryForList(sql);
            if(null!=list && list.size()>0){
            	Map map = (Map) list.get(0);
            	return (String) map.get("into_status");
            }
		}
		return null;
	}
	
	/**
	 * 根据退货单号查询退货单
	 * @author fu 2015-09-10 
	 * @param roNo
	 * @return list 
	 */
	public List getJprRefundForRoNo(String roNo){
		if(!StringUtil.isEmpty(roNo)){
			String sql = " select * from jpr_refund where ro_no = '"+roNo+"'";
			List list =  this.getJdbcTemplate().queryForList(sql);
            if(null!=list && list.size()>0){
            	return list;
            }
		}
		return null;
	}
	
	
	/**
	 * Modify By WuCF 20151211
     * 修改订单的IS_RETREAT_ORDER2标示
     * @param jprRefund
     * @param JprRefundSet
     */
	public String updateJpoMemberOrderFlag(JprRefund jprRefund){
		String returnStr = "";
		String sql = "update jpo_member_order t set t.is_retreat_order2= "+
		" (select (case when count(*)>=1 then 0 else 1 end) from jpr_refund t2 where t.mo_id=t2.mo_id and t2.refund_type='1') "+
		" where t.mo_id='"+jprRefund.getJpoMemberOrder().getMoId()+"' ";
		this.jdbcTemplate.update(sql);
		log.info("====updateJpoMemberOrderFlag:"+sql);
		System.out.println("====updateJpoMemberOrderFlag:"+sql);
		return returnStr;
	}

	public void removeJprRefundBySql(String roNo) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer(200);
		sb.append(" delete from jpr_refund s ");
		sb.append(" where s.RO_NO='"+roNo+"' ");
		
		this.jdbcTemplate.execute(sb.toString());
	}
}
